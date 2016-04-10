package com.tlongdev.spicio.storage.dao.impl;

import com.tlongdev.spicio.controller.request.UserBody;
import com.tlongdev.spicio.controller.response.ActivityResponse;
import com.tlongdev.spicio.controller.response.SeriesSimpleResponse;
import com.tlongdev.spicio.controller.response.UserResponse;
import com.tlongdev.spicio.controller.response.UserResponseFull;
import com.tlongdev.spicio.converter.EpisodeConverter;
import com.tlongdev.spicio.converter.SeriesConverter;
import com.tlongdev.spicio.converter.UserConverter;
import com.tlongdev.spicio.exception.DocumentNotFoundException;
import com.tlongdev.spicio.storage.dao.SequenceDao;
import com.tlongdev.spicio.storage.dao.UserDao;
import com.tlongdev.spicio.storage.document.EpisodeDocument;
import com.tlongdev.spicio.storage.document.SeriesDocument;
import com.tlongdev.spicio.storage.document.UserDocument;
import com.tlongdev.spicio.storage.document.UserSeriesDocument;
import com.tlongdev.spicio.storage.mongo.SeriesRepository;
import com.tlongdev.spicio.storage.mongo.UserRepository;
import com.tlongdev.spicio.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired private UserRepository userRepository;
    @Autowired private SequenceDao sequenceDao;
    @Autowired private SeriesRepository seriesRepository;

    @Override
    public UserResponse getUser(long userId) throws DocumentNotFoundException {
        //Find the user
        UserDocument userDoc = userRepository.findUserById(userId);

        if (userDoc != null) {
            //Convert user to response
            return UserConverter.convertToUserResponse(userDoc);
        } else {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }
    }

    @Override
    public List<UserResponse> findUsers(String searchTerm) {
        //Find users
        List<UserDocument> userDocs = userRepository.findBySearchTermLike(searchTerm.toLowerCase());

        //Convert users to responses
        List<UserResponse> users = new LinkedList<>();
        for (UserDocument userDoc : userDocs) {
            users.add(UserConverter.convertToUserResponse(userDoc));
        }

        return users;
    }

    @Override
    public List<UserResponse> findFriends(long userId) {
        //Find the user
        UserDocument userDoc = userRepository.findUserById(userId);

        if (userDoc == null) {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }

        // Convert documents to responses
        List<UserResponse> response = new LinkedList<>();
        for (UserDocument friend : userRepository.findAll(userDoc.getFriends().keySet())) {
            response.add(UserConverter.convertToUserResponse(friend));
        }

        return response;
    }

    @Override
    public boolean deleteAllUserData(long userId) {
        // TODO: 2016.03.24. delete all documents related to user, check if user exists?
        userRepository.delete(userId);
        return true;
    }

    @Override
    public long addUser(UserBody user) {
        //Convert the user to a document
        UserDocument userDoc = UserConverter.convertToUserDocument(user);

        //Check if the user already exists
        UserDocument result = userRepository.findUserByEmail(user.getEmail());

        if (result == null) {
            //User doesn't exist, create a new one
            result = userDoc;
            result.setId(sequenceDao.nextValue("user"));
            result.buildSearchTerm();
            result = userRepository.insert(result);
        } else {
            //User already exists, update ids
            if (result.getFacebookId() == null) {
                result.setFacebookId(user.getFacebookId());
            }
            if (result.getGoogleId() == null) {
                result.setGoogleId(user.getGoogleId());
            }

            //update user
            result = userRepository.save(result);
        }

        return result.getId();
    }

    @Override
    public UserResponseFull getUserFull(long userId) throws DocumentNotFoundException {
        //Get the user
        UserDocument userDoc = userRepository.findUserById(userId);

        //Check if user exists
        if (userDoc == null) {
            throw new DocumentNotFoundException();
        }

        //Get the series objects for the user
        Iterable<SeriesDocument> seriesDocs = seriesRepository.findAll(userDoc.getSeries().keySet());

        //Get the friends of the user
        Iterable<UserDocument> friendDocs = userRepository.findAll(userDoc.getFriends().keySet());

        //Convert the user to a response
        return UserConverter.convertToUserResponseFull(userDoc, seriesDocs, friendDocs);
    }

    @Override
    public void addFriend(long userId, long friendId) throws DocumentNotFoundException {
        //Get the user
        UserDocument userDoc = userRepository.findUserById(userId);

        //Get the friend
        UserDocument friendDoc = userRepository.findUserById(friendId);

        //Check if both users exists
        if (userDoc == null || friendDoc == null) {
            throw new DocumentNotFoundException();
        }

        userDoc.getFriends().put(friendId, Util.currentTimeSeconds());
        friendDoc.getFriends().put(userId, Util.currentTimeSeconds());
        userRepository.save(userDoc);
        userRepository.save(friendDoc);
    }

    @Override
    public void removeFriend(long userId, long friendId) throws DocumentNotFoundException {
        //Get the user
        UserDocument userDoc = userRepository.findUserById(userId);

        //Get the friend
        UserDocument friendDoc = userRepository.findUserById(friendId);

        //Check if both users exists
        if (userDoc == null || friendDoc == null) {
            throw new DocumentNotFoundException();
        }

        userDoc.getFriends().remove(friendId);
        friendDoc.getFriends().remove(userId);
        userRepository.save(userDoc);
        userRepository.save(friendDoc);
    }

    @Override
    public List<ActivityResponse> getHistory(long userId) {
        //Get the user
        UserDocument userDoc = userRepository.findUserById(userId);

        //Check if user exists
        if (userDoc == null) {
            throw new DocumentNotFoundException();
        }

        List<ActivityResponse> activities = new LinkedList<>();

        //Add the befriending activities
        for (UserDocument friendDoc : userRepository.findAll(userDoc.getFriends().keySet())) {
            ActivityResponse response = new ActivityResponse();
            response.setType(ActivityResponse.BECAME_FRIENDS);
            response.setTimestamp(userDoc.getFriends().get(friendDoc.getId()));
            response.setVictim(UserConverter.convertToUserResponse(friendDoc));
            activities.add(response);
        }

        //Add the series/episode activities
        for (SeriesDocument seriesDoc : seriesRepository.findAll(userDoc.getSeries().keySet())) {

            //Get the series
            UserSeriesDocument userSeries = userDoc.getSeries().get(seriesDoc.getTraktId());
            SeriesSimpleResponse seriesResponse = SeriesConverter.convertToSeriesSimpleResponse(seriesDoc);

            for (Map.Entry<Integer, Long> watched : userSeries.getWatched().entrySet()) {

                EpisodeDocument episodeDoc = seriesDoc.getEpisodes().get(watched.getKey());

                ActivityResponse response = new ActivityResponse();
                response.setType(ActivityResponse.WATCHED);
                response.setEpisode(EpisodeConverter.convertToEpisodeSimpleResponse(episodeDoc));
                response.setSeries(seriesResponse);
                response.setTimestamp(watched.getValue());

                activities.add(response);
            }

            for (Map.Entry<Integer, Long> skipped : userSeries.getSkipped().entrySet()) {

                EpisodeDocument episodeDoc = seriesDoc.getEpisodes().get(skipped.getKey());

                ActivityResponse response = new ActivityResponse();
                response.setType(ActivityResponse.SKIPPED);
                response.setEpisode(EpisodeConverter.convertToEpisodeSimpleResponse(episodeDoc));
                response.setSeries(seriesResponse);
                response.setTimestamp(skipped.getValue());

                activities.add(response);
            }

            for (Map.Entry<Integer, Long> liked : userSeries.getLiked().entrySet()) {

                EpisodeDocument episodeDoc = seriesDoc.getEpisodes().get(liked.getKey());

                ActivityResponse response = new ActivityResponse();
                response.setType(ActivityResponse.LIKED);
                response.setEpisode(EpisodeConverter.convertToEpisodeSimpleResponse(episodeDoc));
                response.setSeries(seriesResponse);
                response.setTimestamp(liked.getValue());

                activities.add(response);
            }
        }

        return activities;
    }
}

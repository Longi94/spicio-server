package com.tlongdev.spicio.storage.dao.impl;

import com.tlongdev.spicio.controller.request.UserBody;
import com.tlongdev.spicio.controller.response.SeriesResponse;
import com.tlongdev.spicio.controller.response.UserResponse;
import com.tlongdev.spicio.controller.response.UserResponseFull;
import com.tlongdev.spicio.converter.SeriesConverter;
import com.tlongdev.spicio.converter.UserConverter;
import com.tlongdev.spicio.exception.DocumentNotFoundException;
import com.tlongdev.spicio.storage.dao.SequenceDao;
import com.tlongdev.spicio.storage.dao.UserDao;
import com.tlongdev.spicio.storage.document.SeriesDocument;
import com.tlongdev.spicio.storage.document.UserDocument;
import com.tlongdev.spicio.storage.mongo.SeriesRepository;
import com.tlongdev.spicio.storage.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

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

        //Get the series objects for the suer
        Iterable<SeriesDocument> seriesDocs = seriesRepository.findAll(userDoc.getSeries());

        //Convert the series ióobjects to responses
        List<SeriesResponse> seriesResponses = new LinkedList<>();
        for (SeriesDocument seriesDoc: seriesDocs) {
            seriesResponses.add(SeriesConverter.convertToSeriesResponse(seriesDoc));
        }

        //Convert the user to a response
        UserResponseFull response = UserConverter.convertToUserResponseFull(userDoc);

        //Add the series
        response.setSeries(seriesResponses);

        return response;
    }
}

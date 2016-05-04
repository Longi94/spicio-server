package com.tlongdev.spicio.converter;

import com.tlongdev.spicio.controller.request.UserBody;
import com.tlongdev.spicio.controller.response.SeriesResponse;
import com.tlongdev.spicio.controller.response.UserResponse;
import com.tlongdev.spicio.controller.response.UserResponseFull;
import com.tlongdev.spicio.storage.document.SeriesDocument;
import com.tlongdev.spicio.storage.document.UserDocument;

import java.util.LinkedList;
import java.util.List;

/**
 * @author longi
 * @since 2016.03.22.
 */
public class UserConverter {

    public static UserResponse convertToUserResponse(UserDocument user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setAvatar(user.getAvatar());
        return userResponse;
    }

    public static UserResponseFull convertToUserResponseFull(UserDocument user, Iterable<SeriesDocument> seriesDocs,
                                                             Iterable<UserDocument> userDocs) {
        UserResponseFull userResponse = new UserResponseFull();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setFacebookId(user.getFacebookId());
        userResponse.setGoogleId(user.getGoogleId());
        userResponse.setAvatar(user.getAvatar());

        //Convert the series objects to responses
        List<SeriesResponse> seriesResponses = new LinkedList<>();
        for (SeriesDocument seriesDoc: seriesDocs) {
            SeriesResponse seriesResponse = SeriesConverter.convertToSeriesResponse(seriesDoc);

            seriesResponse.setUserEpisodes(
                    SeriesConverter.convertToUserSeriesResponse(user.getSeries().get(seriesDoc.getTraktId()))
            );
            seriesResponses.add(seriesResponse);
        }
        //Add the series
        userResponse.setSeries(seriesResponses);

        //Convert the friend objects to responses
        List<UserResponse> friendResponses = new LinkedList<>();
        for (UserDocument friendDoc: userDocs) {
            friendResponses.add(convertToUserResponse(friendDoc));
        }
        //Add the friends
        userResponse.setFriends(friendResponses);

        return userResponse;
    }

    public static UserDocument convertToUserDocument(UserBody userBody) {
        UserDocument userDocument = new UserDocument();
        userDocument.setName(userBody.getName());
        userDocument.setEmail(userBody.getEmail());
        userDocument.setFacebookId(userBody.getFacebookId());
        userDocument.setGoogleId(userBody.getGoogleId());
        userDocument.setAvatar(userBody.getAvatar());
        return userDocument;
    }
}

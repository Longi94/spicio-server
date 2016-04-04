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
        return userResponse;
    }

    public static UserResponseFull convertToUserResponseFull(UserDocument user, Iterable<SeriesDocument> seriesDocs) {
        UserResponseFull userResponse = new UserResponseFull();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setFacebookId(user.getFacebookId());
        userResponse.setGoogleId(user.getGoogleId());
        userResponse.setFriends(user.getFriends());

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

        return userResponse;
    }

    public static UserDocument convertToUserDocument(UserBody userBody) {
        UserDocument userDocument = new UserDocument();
        userDocument.setName(userBody.getName());
        userDocument.setEmail(userBody.getEmail());
        userDocument.setFacebookId(userBody.getFacebookId());
        userDocument.setGoogleId(userBody.getGoogleId());
        return userDocument;
    }
}

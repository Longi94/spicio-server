package com.tlongdev.spicio.controller.converter;

import com.tlongdev.spicio.controller.response.UserResponse;
import com.tlongdev.spicio.controller.response.UserResponseFull;
import com.tlongdev.spicio.storage.document.UserDocument;

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

    public static UserResponseFull convertToUserResponseFull(UserDocument user) {
        UserResponseFull userResponse = new UserResponseFull();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setFacebookId(user.getFacebookId());
        userResponse.setGoogleId(user.getGoogleId());
        userResponse.setFriends(user.getFriends());
        userResponse.setHistory(user.getHistory());
        userResponse.setSeries(user.getSeries());
        return userResponse;
    }
}

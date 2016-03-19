package com.tlongdev.spicio.storage.dao;

import com.tlongdev.spicio.domain.User;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
public interface UserDao {

    User getUser(long userId);

    User saveUser(User user);

    User getUserByFacebookId(String facebookId);

    User getUserByGoogleId(String googleId);
}
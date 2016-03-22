package com.tlongdev.spicio.storage.dao;

import com.tlongdev.spicio.storage.document.UserDocument;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
public interface UserDao {

    UserDocument getUser(long userId);

    UserDocument saveUser(UserDocument user);

    UserDocument getUserByFacebookId(String facebookId);

    UserDocument getUserByGoogleId(String googleId);
}

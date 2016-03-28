package com.tlongdev.spicio.storage.dao;

import com.tlongdev.spicio.storage.document.UserDocument;

import java.util.List;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
public interface UserDao {

    UserDocument getUser(long userId);

    List<UserDocument> findUsers(String name);

    boolean deleteAllUserData(long userId);

    long addUser(UserDocument userDocument);
}

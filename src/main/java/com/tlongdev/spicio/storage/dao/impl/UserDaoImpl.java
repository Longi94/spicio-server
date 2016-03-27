package com.tlongdev.spicio.storage.dao.impl;

import com.tlongdev.spicio.storage.dao.UserDao;
import com.tlongdev.spicio.storage.document.UserDocument;
import com.tlongdev.spicio.storage.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired private UserRepository userRepository;

    @Override
    public UserDocument getUser(long userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public UserDocument saveUser(UserDocument user) {
        return userRepository.save(user);
    }

    @Override
    public UserDocument getUserByFacebookId(String facebookId) {
        return userRepository.findUserByFacebookId(facebookId);
    }

    @Override
    public UserDocument getUserByGoogleId(String googleId) {
        return userRepository.findUserByGoogleId(googleId);
    }

    @Override
    public List<UserDocument> findUsers(String searchTerm) {
        return userRepository.findBySearchTermLike(searchTerm.toLowerCase());
    }

    @Override
    public boolean deleteAllUserData(long userId) {
        // TODO: 2016.03.24. delete all documents related to user, check if user exists?
        userRepository.delete(userId);
        return true;
    }
}

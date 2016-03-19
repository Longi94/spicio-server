package com.tlongdev.spicio.storage.dao.impl;

import com.tlongdev.spicio.domain.User;
import com.tlongdev.spicio.storage.dao.UserDao;
import com.tlongdev.spicio.storage.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired private UserRepository userRepository;

    @Override
    public User getUser(long userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByFacebookId(String facebookId) {
        return userRepository.findUserByFacebookId(facebookId);
    }

    @Override
    public User getUserByGoogleId(String googleId) {
        return userRepository.findUserByGoogleId(googleId);
    }
}

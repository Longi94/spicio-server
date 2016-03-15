package com.tlongdev.spicio.user;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
public class UserDaoImpl implements UserDao {

    @Autowired private UserRepository userRepository;

    @Override
    public User getUser(long userId) {
        return userRepository.findUserById(userId);
    }
}

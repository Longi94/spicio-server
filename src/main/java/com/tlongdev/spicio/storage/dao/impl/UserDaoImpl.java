package com.tlongdev.spicio.storage.dao.impl;

import com.tlongdev.spicio.storage.dao.SequenceDao;
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
    @Autowired private SequenceDao sequenceDao;

    @Override
    public UserDocument getUser(long userId) {
        return userRepository.findUserById(userId);
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

    @Override
    public long addUser(UserDocument user) {

        UserDocument result;
        result = userRepository.findUserByEmail(user.getEmail());

        if (result == null) {
            result = user;
            result.setId(sequenceDao.nextValue("user"));
            result.buildSearchTerm();
            result = userRepository.save(result);
        } else {
            if (result.getFacebookId() == null) {
                result.setFacebookId(user.getFacebookId());
            }
            if (result.getGoogleId() == null) {
                result.setGoogleId(user.getGoogleId());
            }
        }

        return result.getId();
    }
}

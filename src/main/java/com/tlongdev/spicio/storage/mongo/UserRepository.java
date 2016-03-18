package com.tlongdev.spicio.storage.mongo;

import com.tlongdev.spicio.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
public interface UserRepository extends MongoRepository<User, Long> {
    User findUserById(Long id);

    User findUserByFacebookId(String facebookId);

    User findUserByGoogleId(String googleId);
}

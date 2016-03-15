package com.tlongdev.spicio.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@Service
public interface UserRepository extends MongoRepository<User, Long> {
    User findUserById(Long id);
}

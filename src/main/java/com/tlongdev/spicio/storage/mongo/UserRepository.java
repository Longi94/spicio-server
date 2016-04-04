package com.tlongdev.spicio.storage.mongo;

import com.tlongdev.spicio.storage.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@Repository
public interface UserRepository extends MongoRepository<UserDocument, Long> {
    UserDocument findUserById(Long id);

    UserDocument findUserByEmail(String email);

    List<UserDocument> findBySearchTermLike(String searchTerm);
}

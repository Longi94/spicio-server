package com.tlongdev.spicio.storage.mongo;

import com.tlongdev.spicio.domain.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Long
 * @since 2016. 03. 19.
 */
public interface SequenceRepository extends MongoRepository<Sequence, String> {
    Sequence findSequenceByName(String id);
}

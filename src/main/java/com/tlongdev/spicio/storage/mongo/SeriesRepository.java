package com.tlongdev.spicio.storage.mongo;

import com.tlongdev.spicio.storage.document.SeriesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author longi
 * @since 2016.03.24.
 */
@Repository
public interface SeriesRepository extends MongoRepository<SeriesDocument, Integer> {
}

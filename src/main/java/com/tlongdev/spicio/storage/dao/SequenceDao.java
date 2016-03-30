package com.tlongdev.spicio.storage.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Long
 * @since 2016. 03. 19.
 */
@Repository
public interface SequenceDao {

    Long nextValue(String id);
}

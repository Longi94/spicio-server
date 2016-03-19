package com.tlongdev.spicio.storage.dao;

/**
 * @author Long
 * @since 2016. 03. 19.
 */
public interface SequenceDao {

    int nextValue(String id);
}

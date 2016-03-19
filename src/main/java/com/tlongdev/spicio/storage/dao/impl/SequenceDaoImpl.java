package com.tlongdev.spicio.storage.dao.impl;

import com.tlongdev.spicio.domain.Sequence;
import com.tlongdev.spicio.storage.dao.SequenceDao;
import com.tlongdev.spicio.storage.mongo.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Long
 * @since 2016. 03. 19.
 */
@Repository
public class SequenceDaoImpl implements SequenceDao {

    @Autowired private SequenceRepository repository;

    @Override
    public int nextValue(String name) {
        Sequence sequence = repository.findSequenceByName(name);

        if (sequence == null) {
            sequence = new Sequence();
            sequence.setName(name);
            sequence.setValue(-1);
        }

        int value = sequence.nextValue();

        repository.save(sequence);

        return value;
    }
}

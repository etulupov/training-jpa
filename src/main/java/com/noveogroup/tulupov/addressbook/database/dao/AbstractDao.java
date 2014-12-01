package com.noveogroup.tulupov.addressbook.database.dao;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract dao interface.
 *
 * @param <K> the key
 * @param <E> the entity
 */
public interface AbstractDao<K extends Serializable, E> {
    void add(E entity);

    void update(E entity);

    void remove(K key);

    E get(K key);

    List<E> query(Pageable pageable);

    long count();
}

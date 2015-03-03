package com.noveogroup.tulupov.addressbook.service.impl;


import com.noveogroup.tulupov.addressbook.database.dao.AbstractDao;
import com.noveogroup.tulupov.addressbook.service.AbstractService;
import com.noveogroup.tulupov.addressbook.util.PaginationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Abstract service implementation.
 *
 * @param <K> the key
 * @param <E> the entity
 */
@Transactional
public abstract class AbstractServiceImpl<K extends Serializable, E> implements AbstractService<K, E> {

    private AbstractDao<K, E> dao;

    protected AbstractServiceImpl(final AbstractDao<K, E> dao) {
        this.dao = dao;
    }

    @Override
    public void add(final E entity) {
        dao.add(entity);
    }

    @Override
    public void remove(final K key) {
        dao.remove(key);
    }

    @Override
    public E get(final K key) {
        return dao.get(key);
    }

    @Override
    public void update(final E entity) {
        dao.update(entity);
    }

    @Override
    public Page<E> query(final Pageable pageableRequest) {
        final long count = dao.count();
        final Pageable pageable = PaginationUtils.checkRange(pageableRequest, count);

        return new PageImpl<E>(dao.query(pageable), pageable, count);
    }

    @Override
    public long count() {
        return dao.count();
    }

}

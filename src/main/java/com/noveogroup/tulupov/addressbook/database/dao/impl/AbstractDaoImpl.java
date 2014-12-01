package com.noveogroup.tulupov.addressbook.database.dao.impl;


import com.noveogroup.tulupov.addressbook.database.dao.AbstractDao;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract dao implementation.
 *
 * @param <K> the key
 * @param <E> the entity
 */
public abstract class AbstractDaoImpl<K extends Serializable, E> implements AbstractDao<K, E> {
    protected Class<E> entityClass;
    protected HibernateTemplate hibernateTemplate;

    public AbstractDaoImpl(final Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void add(final E entity) {
        hibernateTemplate.save(entity);
    }

    @Override
    public void remove(final K key) {
        final E entity = hibernateTemplate.load(entityClass, key);
        hibernateTemplate.delete(entity);
    }

    @Override
    public E get(final K key) {
        final E entity = hibernateTemplate.get(entityClass, key);

        if (entity != null) {
            hibernateTemplate.refresh(entity);
        }

        return entity;
    }

    @Override
    public void update(final E entity) {
        hibernateTemplate.merge(entity);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<E> query(final Pageable pageable) {
        final DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

        return query(pageable, criteria);
    }

    protected List<E> query(final Pageable pageable, final DetachedCriteria criteria) {
        final Sort sort = pageable.getSort();

        if (sort != null) {
            for (final Sort.Order order : sort) {
                criteria.addOrder(order.isAscending() ? Order.asc(order.getProperty())
                        : Order.desc(order.getProperty()));
            }
        }

        return hibernateTemplate.findByCriteria(criteria,
                pageable.getOffset(), pageable.getPageSize());
    }

    @Override
    public long count() {
        return count(DetachedCriteria.forClass(entityClass));
    }

    protected long count(final DetachedCriteria criteria) {
        return DataAccessUtils.longResult(hibernateTemplate.findByCriteria(criteria
                .setProjection(Projections.rowCount())));
    }
}

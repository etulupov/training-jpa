package com.noveogroup.tulupov.addressbook.database.dao.impl;


import com.noveogroup.tulupov.addressbook.database.dao.AbstractDao;
import org.jdal.dao.jpa.JpaUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract dao implementation.
 *
 * @param <K> the key
 * @param <E> the entity
 */
public abstract class AbstractDaoImpl<K extends Serializable, E> implements AbstractDao<K, E> {
    protected Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractDaoImpl(final Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void add(final E entity) {



//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setName("rootTransaction");
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        TransactionStatus status = txManager.getTransaction(def);
        entityManager.persist(entity);
//        txManager.commit(status);
    }

    @Override
    public void remove(final K key) {
        final E entity = entityManager.getReference(entityClass, key);
        entityManager.remove(entity);
    }

    @Override
    public E get(final K key) {
        final E entity = entityManager.find(entityClass, key);

        if (entity != null) {
            entityManager.refresh(entity);
        }

        return entity;
    }

    @Override
    public void update(final E entity) {
        entityManager.merge(entity);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<E> query(final Pageable pageable) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> criteria = builder
                .createQuery(entityClass);
        final Root<E> root = criteria.from(entityClass);

        criteria.select(root);

        return query(pageable, criteria);
    }

    @SuppressWarnings("unchecked")
    protected <T> List<E> query(final Pageable pageable, final CriteriaQuery<T> criteria) {
        final Sort sort = pageable.getSort();

        if (sort != null) {
            final List<Order> orders = new ArrayList<Order>();
            final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            final Root<E> root = criteria.from(entityClass);

            for (final Sort.Order order : sort) {
                orders.add(order.isAscending() ? builder.asc(root.get(order.getProperty()))
                        : builder.desc(root.get(order.getProperty())));
            }

            criteria.orderBy(orders);
        }

        final Query query = entityManager.createQuery(criteria);
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return query.getResultList();
    }

    @Override
    public long count() {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> criteriaQuery = builder.createQuery(entityClass);
        final Root<E> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        return count(criteriaQuery);
    }

    protected <T> long count(final CriteriaQuery<T> criteria) {
        return JpaUtils.count(entityManager, criteria);
    }
}

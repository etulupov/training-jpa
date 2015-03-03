package com.noveogroup.tulupov.addressbook.database.dao.impl;

import com.noveogroup.tulupov.addressbook.database.dao.GroupDao;
import com.noveogroup.tulupov.addressbook.entity.GroupEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Group dao implementation.
 */
@Repository
public class GroupDaoImpl extends AbstractDaoImpl<Integer, GroupEntity> implements GroupDao {
    public GroupDaoImpl() {
        super(GroupEntity.class);
    }

    @Override
    public List<GroupEntity> get(final List<Integer> ids) {
        final CriteriaQuery<GroupEntity> criteria = entityManager.getCriteriaBuilder()
                .createQuery(entityClass);

        final Root<GroupEntity> root = criteria.from(entityClass);
        criteria.select(root);
        criteria.where(root.get("id").in(ids));

        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<GroupEntity> queryAll() {
        final CriteriaQuery<GroupEntity> criteria = entityManager.getCriteriaBuilder()
                .createQuery(entityClass);

        criteria.select(criteria.from(entityClass));

        return entityManager.createQuery(criteria).getResultList();
    }
}

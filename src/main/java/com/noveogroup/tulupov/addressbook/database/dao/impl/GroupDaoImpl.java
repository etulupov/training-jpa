package com.noveogroup.tulupov.addressbook.database.dao.impl;

import com.noveogroup.tulupov.addressbook.database.dao.GroupDao;
import com.noveogroup.tulupov.addressbook.entity.GroupEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

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
        final DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.add(Restrictions.in("id", ids));

        return hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    public List<GroupEntity> queryAll() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

        return hibernateTemplate.findByCriteria(criteria);
    }
}

package com.noveogroup.tulupov.addressbook.database.dao.impl;


import com.noveogroup.tulupov.addressbook.database.dao.ContactDao;
import com.noveogroup.tulupov.addressbook.entity.ContactEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Contact dao Hibernate template implementation.
 */
@Repository
public class ContactDaoImpl extends AbstractDaoImpl<Integer, ContactEntity>
        implements ContactDao {

    public ContactDaoImpl() {
        super(ContactEntity.class);
    }

    @Override
    public ContactEntity get(final Integer key) {
        return hibernateTemplate.execute(new HibernateCallback<ContactEntity>() {
            @Override
            public ContactEntity doInHibernate(final Session session) {
                final ContactEntity entity = (ContactEntity) session.get(entityClass, key);

                if (entity != null) {
                    session.refresh(entity);
                    initEntityCollections(entity);
                }

                return entity;
            }
        });
    }

    @Override
    public List<ContactEntity> query(final Pageable pageable) {
        final List<ContactEntity> list = ContactDaoImpl.super.query(pageable);

        return init(list);
    }


    public List<ContactEntity> init(final List<ContactEntity> list) {
        return hibernateTemplate.execute(new HibernateCallback<List<ContactEntity>>() {
            @Override
            public List<ContactEntity> doInHibernate(final Session session) {
                List<ContactEntity> result = null;

                if (list != null) {
                    result = new ArrayList<ContactEntity>();

                    for (final ContactEntity entity : list) {
                        final ContactEntity mergedEntity = (ContactEntity) session.merge(entity);
                        initEntityCollections(mergedEntity);
                        result.add(mergedEntity);
                    }
                }

                return result;
            }
        });
    }

    private void initEntityCollections(final ContactEntity entity) {
        Hibernate.initialize(entity.getGroups());
        Hibernate.initialize(entity.getAddresses());
        Hibernate.initialize(entity.getFields());
    }

    @Override
    public List<ContactEntity> queryByGroup(final Integer groupId, final Pageable pageable) {
        return init(query(pageable, createGroupCriteria(groupId)));
    }

    @Override
    public long countByGroup(final Integer groupId) {

        return count(createGroupCriteria(groupId));
    }

    private DetachedCriteria createGroupCriteria(final Integer groupId) {
        final DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.createAlias("groups", "group");
        criteria.add(Restrictions.eq("group.id", groupId));

        return criteria;
    }
}

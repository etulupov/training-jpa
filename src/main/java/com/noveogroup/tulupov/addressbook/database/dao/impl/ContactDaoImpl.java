package com.noveogroup.tulupov.addressbook.database.dao.impl;


import com.noveogroup.tulupov.addressbook.database.dao.ContactDao;
import com.noveogroup.tulupov.addressbook.entity.ContactEntity;
import com.noveogroup.tulupov.addressbook.entity.GroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Contact dao implementation.
 */
@Repository
public class ContactDaoImpl extends AbstractDaoImpl<Integer, ContactEntity>
        implements ContactDao {

    public ContactDaoImpl() {
        super(ContactEntity.class);
    }


    @Override
    public List<ContactEntity> queryByGroup(final Integer groupId, final Pageable pageable) {
        return query(pageable, createGroupCriteria(groupId));
    }

    @Override
    public long countByGroup(final Integer groupId) {
        return count(createGroupCriteria(groupId));
    }

    private CriteriaQuery<ContactEntity> createGroupCriteria(final Integer groupId) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ContactEntity> criteriaQuery = builder.createQuery(ContactEntity.class);
        final Root from = criteriaQuery.from(ContactEntity.class);
        final From join = from.join(ContactEntity.GROUPS);
        final Predicate predicate = builder.equal(join.get(GroupEntity.ID), groupId);

        criteriaQuery.where(predicate);
        criteriaQuery.select(from);

        return criteriaQuery;
    }


}

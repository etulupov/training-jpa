package com.noveogroup.tulupov.addressbook.database.dao;

import com.noveogroup.tulupov.addressbook.entity.ContactEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Contact dao interface.
 */
public interface ContactDao extends AbstractDao<Integer, ContactEntity> {
    List<ContactEntity> queryByGroup(Integer groupId, Pageable pageable);
    long countByGroup(Integer groupId);
}

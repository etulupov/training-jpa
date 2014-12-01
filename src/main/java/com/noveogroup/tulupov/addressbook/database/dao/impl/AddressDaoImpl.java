package com.noveogroup.tulupov.addressbook.database.dao.impl;

import com.noveogroup.tulupov.addressbook.database.dao.AddressDao;
import com.noveogroup.tulupov.addressbook.entity.AddressEntity;
import org.springframework.stereotype.Repository;

/**
 * Group dao implementation.
 */
@Repository
public class AddressDaoImpl extends AbstractDaoImpl<Integer, AddressEntity> implements AddressDao {
    public AddressDaoImpl() {
        super(AddressEntity.class);
    }
}

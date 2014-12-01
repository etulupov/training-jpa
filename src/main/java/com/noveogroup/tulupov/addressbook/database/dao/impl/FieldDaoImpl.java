package com.noveogroup.tulupov.addressbook.database.dao.impl;

import com.noveogroup.tulupov.addressbook.database.dao.FieldDao;
import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import org.springframework.stereotype.Repository;

/**
 * Group dao implementation.
 */
@Repository
public class FieldDaoImpl extends AbstractDaoImpl<Integer, FieldEntity> implements FieldDao {
    public FieldDaoImpl() {
        super(FieldEntity.class);
    }
}

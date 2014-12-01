package com.noveogroup.tulupov.addressbook.convertor.field;

import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import com.noveogroup.tulupov.addressbook.model.FieldModel;

/**
 * Field converter.
 */
public interface FieldConverter {
    FieldModel convertEntityToModel(FieldEntity entity);

    FieldEntity convertModelToEntity(FieldModel model);
}

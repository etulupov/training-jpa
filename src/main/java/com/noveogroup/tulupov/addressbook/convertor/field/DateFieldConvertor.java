package com.noveogroup.tulupov.addressbook.convertor.field;


import com.noveogroup.tulupov.addressbook.entity.field.DateFieldEntity;
import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import com.noveogroup.tulupov.addressbook.model.FieldModel;
import com.noveogroup.tulupov.addressbook.model.FieldType;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Date field convertor.
 */
public class DateFieldConvertor extends AbstractFieldConvertor<DateFieldEntity> {
    public DateFieldConvertor() {
        super(DateFieldEntity.class, FieldType.DATE);
    }

    @Override
    protected FieldModel convertEntity(final DateFieldEntity entity) {
        return FieldModel.builder()
                .id(entity.getId())
                .fieldType(FieldType.DATE)
                .value(new SimpleDateFormat(DateFieldEntity.DATE_FORMAT).format(entity.getDate()))
                .build();
    }

    @Override
    protected FieldEntity convertModel(final FieldModel model) {
        final DateFieldEntity entity = new DateFieldEntity();

        entity.setId(model.getId());
        try {
            entity.setDate(new SimpleDateFormat(DateFieldEntity.DATE_FORMAT).parse(model.getValue()));
        } catch (ParseException e) {
            new RuntimeException("Cannot convert date field", e);
        }

        return entity;
    }
}

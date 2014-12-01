package com.noveogroup.tulupov.addressbook.convertor.field;

import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import com.noveogroup.tulupov.addressbook.model.FieldModel;
import com.noveogroup.tulupov.addressbook.model.FieldType;

/**
 * Field convertor.
 * @param <T> the field type
 */
public abstract class AbstractFieldConvertor<T> implements ChainFieldConverter {
    protected final Class<? extends T> fieldClazz;
    protected final FieldType fieldType;
    protected FieldConverter nextConverter;

    public AbstractFieldConvertor(final Class<? extends T> fieldClazz, final FieldType fieldType) {
        this.fieldClazz = fieldClazz;
        this.fieldType = fieldType;
    }

    @Override
    public void setNext(final FieldConverter converter) {
        this.nextConverter = converter;
    }

    @Override
    public FieldModel convertEntityToModel(final FieldEntity entity) {
        if (entity.getClass() == fieldClazz) {
            return convertEntity((T) entity);
        }

        if (nextConverter != null) {
            return nextConverter.convertEntityToModel(entity);
        }

        return null;
    }

    @Override
    public FieldEntity convertModelToEntity(final FieldModel model) {
        if (model.getFieldType() == fieldType) {
            return convertModel(model);
        }

        if (nextConverter != null) {
            return nextConverter.convertModelToEntity(model);
        }

        return null;
    }

    protected abstract FieldModel convertEntity(T entity);

    protected abstract FieldEntity convertModel(FieldModel model);
}

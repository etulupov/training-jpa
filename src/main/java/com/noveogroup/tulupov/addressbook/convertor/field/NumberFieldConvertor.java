package com.noveogroup.tulupov.addressbook.convertor.field;


import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import com.noveogroup.tulupov.addressbook.entity.field.NumberFieldEntity;
import com.noveogroup.tulupov.addressbook.model.FieldModel;
import com.noveogroup.tulupov.addressbook.model.FieldType;

/**
 * Number field convertor.
 */
public class NumberFieldConvertor extends AbstractFieldConvertor<NumberFieldEntity> {
    public NumberFieldConvertor() {
        super(NumberFieldEntity.class, FieldType.NUMBER);
    }

    @Override
    protected FieldModel convertEntity(final NumberFieldEntity entity) {
        return FieldModel.builder()
                .id(entity.getId())
                .fieldType(FieldType.NUMBER)
                .value(String.valueOf(entity.getNumber()))
                .build();
    }


    @Override
    protected FieldEntity convertModel(final FieldModel model) {
        final NumberFieldEntity entity = new NumberFieldEntity();

        entity.setId(model.getId());
        entity.setNumber(Integer.valueOf(model.getValue()));

        return entity;
    }
}

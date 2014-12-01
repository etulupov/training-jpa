package com.noveogroup.tulupov.addressbook.convertor.field;


import com.noveogroup.tulupov.addressbook.entity.field.EmailFieldEntity;
import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import com.noveogroup.tulupov.addressbook.model.FieldModel;
import com.noveogroup.tulupov.addressbook.model.FieldType;

/**
 * Email field convertor.
 */
public class EmailFieldConvertor extends AbstractFieldConvertor<EmailFieldEntity> {
    public EmailFieldConvertor() {
        super(EmailFieldEntity.class, FieldType.EMAIL);
    }

    @Override
    protected FieldModel convertEntity(final EmailFieldEntity entity) {
        return FieldModel.builder()
                .id(entity.getId())
                .fieldType(FieldType.EMAIL)
                .value(entity.getEmail())
                .build();
    }

    @Override
    protected FieldEntity convertModel(final FieldModel model) {
        final EmailFieldEntity entity = new EmailFieldEntity();

        entity.setId(model.getId());
        entity.setEmail(model.getValue());

        return entity;
    }
}

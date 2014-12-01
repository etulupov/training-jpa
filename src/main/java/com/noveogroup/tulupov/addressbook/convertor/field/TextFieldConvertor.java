package com.noveogroup.tulupov.addressbook.convertor.field;


import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import com.noveogroup.tulupov.addressbook.entity.field.TextFieldEntity;
import com.noveogroup.tulupov.addressbook.model.FieldModel;
import com.noveogroup.tulupov.addressbook.model.FieldType;

/**
 * Text field convertor.
 */
public class TextFieldConvertor extends AbstractFieldConvertor<TextFieldEntity> {
    public TextFieldConvertor() {
        super(TextFieldEntity.class, FieldType.TEXT);
    }

    @Override
    protected FieldModel convertEntity(final TextFieldEntity entity) {
        return FieldModel.builder()
                .id(entity.getId())
                .fieldType(FieldType.TEXT)
                .value(entity.getText())
                .build();
    }

    @Override
    protected FieldEntity convertModel(final FieldModel model) {
        final TextFieldEntity entity = new TextFieldEntity();

        entity.setId(model.getId());
        entity.setText(model.getValue());

        return entity;
    }
}

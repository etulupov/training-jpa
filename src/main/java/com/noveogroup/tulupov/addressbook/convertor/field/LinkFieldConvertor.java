package com.noveogroup.tulupov.addressbook.convertor.field;


import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import com.noveogroup.tulupov.addressbook.entity.field.LinkFieldEntity;
import com.noveogroup.tulupov.addressbook.model.FieldModel;
import com.noveogroup.tulupov.addressbook.model.FieldType;

/**
 * Link field convertor.
 */
public class LinkFieldConvertor extends AbstractFieldConvertor<LinkFieldEntity> {
    public LinkFieldConvertor() {
        super(LinkFieldEntity.class, FieldType.LINK);
    }

    @Override
    protected FieldModel convertEntity(final LinkFieldEntity entity) {
        return FieldModel.builder()
                .id(entity.getId())
                .fieldType(FieldType.LINK)
                .value(String.valueOf(entity.getLink()))
                .build();
    }

    @Override
    protected FieldEntity convertModel(final FieldModel model) {
        final LinkFieldEntity entity = new LinkFieldEntity();

        entity.setId(model.getId());
        entity.setLink(model.getValue());

        return entity;
    }
}

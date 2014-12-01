package com.noveogroup.tulupov.addressbook.convertor;


import com.noveogroup.tulupov.addressbook.convertor.field.ChainFieldConvertorFactory;
import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import com.noveogroup.tulupov.addressbook.model.FieldModel;
import org.springframework.stereotype.Component;

/**
 * Field converter.
 */
@Component
public class FieldConvertor extends AbstractConvertor<FieldEntity, FieldModel> {

    public FieldConvertor() {
        super(FieldEntity.class, FieldModel.class);
    }

    @Override
    protected FieldModel convertEntity(final FieldEntity entity) {
        return ChainFieldConvertorFactory.createChain().convertEntityToModel(entity);
    }

    @Override
    protected FieldEntity convertModel(final FieldModel model) {
        return ChainFieldConvertorFactory.createChain().convertModelToEntity(model);
    }
}

package com.noveogroup.tulupov.addressbook.convertor;


import com.noveogroup.tulupov.addressbook.entity.AddressEntity;
import com.noveogroup.tulupov.addressbook.model.AddressModel;
import org.springframework.stereotype.Component;

/**
 * Address convertor.
 */
@Component
public class AddressConvertor extends AbstractConvertor<AddressEntity, AddressModel> {

    public AddressConvertor() {
        super(AddressEntity.class, AddressModel.class);
    }

    @Override
    protected AddressModel convertEntity(final AddressEntity entity) {
        final AddressModel model = new AddressModel();

        model.setId(entity.getId());
        model.setValue(entity.getValue());

        return model;
    }

    @Override
    protected AddressEntity convertModel(final AddressModel model) {
        final AddressEntity entity = new AddressEntity();

        entity.setId(model.getId());
        entity.setValue(model.getValue());

        return entity;
    }
}

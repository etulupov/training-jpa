package com.noveogroup.tulupov.addressbook.convertor;


import com.noveogroup.tulupov.addressbook.entity.ContactEntity;
import com.noveogroup.tulupov.addressbook.entity.GroupEntity;
import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import com.noveogroup.tulupov.addressbook.model.ContactModel;
import com.noveogroup.tulupov.addressbook.model.FieldModel;
import com.noveogroup.tulupov.addressbook.model.FieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Field converter.
 */
@Component
public class ContactConvertor extends AbstractConvertor<ContactEntity, ContactModel> {

    @Autowired
    private GroupConvertor groupConvertor;

    @Autowired
    private FieldConvertor fieldConverter;

    @Autowired
    private AddressConvertor addressConvertor;

    public ContactConvertor() {
        super(ContactEntity.class, ContactModel.class);
    }

    @Override
    protected ContactModel convertEntity(final ContactEntity entity) {
        final ContactModel model = new ContactModel();

        model.setId(entity.getId());
        model.setFirstname(entity.getFirstname());
        model.setLastname(entity.getLastname());

        model.setGroups(groupConvertor.convertEntitiesToModels(entity.getGroups()));

        model.setAddresses(addressConvertor.convertEntitiesToModels(entity.getAddresses()));

        if (entity.getFields() != null) {
            final List<FieldEntity> fieldEntities = entity.getFields();
            final List<FieldType> fieldTypes = new ArrayList<FieldType>();
            final List<FieldModel> fieldModels = fieldConverter.convertEntitiesToModels(entity.getFields());

            for (final FieldModel m : fieldModels) {
                fieldTypes.add(m.getFieldType());
            }

            model.setFields(fieldModels);
            model.setFieldTypes(fieldTypes);
        }


        return model;
    }

    @Override
    protected ContactEntity convertModel(final ContactModel model) {
        final ContactEntity entity = new ContactEntity();

        entity.setId(model.getId());
        entity.setFirstname(model.getFirstname());
        entity.setLastname(model.getLastname());

        final MultipartFile photoFile = model.getPhoto();

        if (photoFile != null && !photoFile.isEmpty()) {
            try {
                entity.setPhoto(photoFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (model.getGroups() != null) {
            entity.setGroups(new HashSet<GroupEntity>(groupConvertor.convertModelsToEntities(model.getGroups())));
        }

        if (model.getAddresses() != null) {
            entity.setAddresses(addressConvertor.convertModelsToEntities(model.getAddresses()));
        }

        if (model.getFields() != null) {
            final List<FieldModel> fieldModels = new ArrayList<>(model.getFields());
            final List<FieldType> fieldTypes = model.getFieldTypes();

            for (int i = 0; i < fieldModels.size(); i++) {
                fieldModels.get(i).setFieldType(fieldTypes.get(i));
            }

            entity.setFields(fieldConverter.convertModelsToEntities(fieldModels));
        }


        return entity;
    }
}

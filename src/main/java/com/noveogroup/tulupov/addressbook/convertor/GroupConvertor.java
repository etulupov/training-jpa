package com.noveogroup.tulupov.addressbook.convertor;


import com.noveogroup.tulupov.addressbook.entity.GroupEntity;
import com.noveogroup.tulupov.addressbook.model.GroupModel;
import org.springframework.stereotype.Component;

/**
 * Group converter.
 */
@Component
public class GroupConvertor extends AbstractConvertor<GroupEntity, GroupModel> {

    public GroupConvertor() {
        super(GroupEntity.class, GroupModel.class);
    }

    @Override
    protected GroupModel convertEntity(final GroupEntity entity) {
        final GroupModel model = new GroupModel();

        model.setId(entity.getId());
        model.setValue(entity.getName());

        return model;
    }

    @Override
    protected GroupEntity convertModel(final GroupModel model) {
        final GroupEntity entity = new GroupEntity();

        entity.setId(model.getId());
        entity.setName(model.getValue());

        return entity;
    }
}

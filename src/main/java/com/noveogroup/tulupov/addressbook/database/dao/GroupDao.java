package com.noveogroup.tulupov.addressbook.database.dao;

import com.noveogroup.tulupov.addressbook.entity.GroupEntity;

import java.util.List;

/**
 * Contact dao interface.
 */
public interface GroupDao extends AbstractDao<Integer, GroupEntity> {
    List<GroupEntity> get(List<Integer> ids);

    List<GroupEntity> queryAll();
}

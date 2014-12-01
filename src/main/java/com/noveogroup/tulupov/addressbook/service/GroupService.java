package com.noveogroup.tulupov.addressbook.service;


import com.noveogroup.tulupov.addressbook.entity.GroupEntity;

import java.util.List;

/**
 * Group service interface.
 */
public interface GroupService extends AbstractService<Integer, GroupEntity> {
    List<GroupEntity> queryAll();
}

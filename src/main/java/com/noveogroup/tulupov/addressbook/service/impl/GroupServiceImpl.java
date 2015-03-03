package com.noveogroup.tulupov.addressbook.service.impl;


import com.noveogroup.tulupov.addressbook.database.dao.GroupDao;
import com.noveogroup.tulupov.addressbook.entity.GroupEntity;
import com.noveogroup.tulupov.addressbook.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Group service implementation.
 */
@Service
@Transactional
public class GroupServiceImpl extends AbstractServiceImpl<Integer, GroupEntity> implements GroupService {

    private GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(final GroupDao groupDao) {
        super(groupDao);
        this.groupDao = groupDao;
    }

    @Override
    public List<GroupEntity> queryAll() {
        return groupDao.queryAll();
    }
}

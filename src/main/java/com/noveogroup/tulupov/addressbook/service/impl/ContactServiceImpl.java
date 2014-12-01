package com.noveogroup.tulupov.addressbook.service.impl;


import com.noveogroup.tulupov.addressbook.database.dao.AddressDao;
import com.noveogroup.tulupov.addressbook.database.dao.ContactDao;
import com.noveogroup.tulupov.addressbook.database.dao.FieldDao;
import com.noveogroup.tulupov.addressbook.database.dao.GroupDao;
import com.noveogroup.tulupov.addressbook.entity.AddressEntity;
import com.noveogroup.tulupov.addressbook.entity.ContactEntity;
import com.noveogroup.tulupov.addressbook.entity.GroupEntity;
import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import com.noveogroup.tulupov.addressbook.exception.ContactNotFoundException;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import com.noveogroup.tulupov.addressbook.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Contact service implementation.
 */
@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ContactServiceImpl extends AbstractServiceImpl<Integer, ContactEntity> implements ContactService {
    private ContactDao contactDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    public ContactServiceImpl(final ContactDao contactDao) {
        super(contactDao);
        this.contactDao = contactDao;
    }

    @Override
    public void add(final ContactEntity contactEntity) {
        contactDao.add(contactEntity);

        if (contactEntity.getFields() != null) {
            for (FieldEntity fieldEntity : contactEntity.getFields()) {
                fieldEntity.setContact(contactEntity);
                fieldDao.add(fieldEntity);
            }
        }

        if (contactEntity.getAddresses() != null) {
            for (AddressEntity addressEntity : contactEntity.getAddresses()) {
                addressEntity.setContact(contactEntity);
                addressDao.add(addressEntity);
            }
        }
    }

    @Override
    public void update(final ContactEntity contact) {
        final Integer id = contact.getId();
        if (id == null) {
            throw new IllegalStateException("Contact id is null");
        }

        final ContactEntity savedContact = get(id);
        if (savedContact == null) {
            throw new ContactNotFoundException("Cannot find contact with given id=" + id);
        }

        savedContact.setFirstname(contact.getFirstname());
        savedContact.setLastname(contact.getLastname());

        final byte[] photo = contact.getPhoto();
        if (photo != null) {
            savedContact.setPhoto(photo);
        }

        final List<Integer> groupIds = new ArrayList<>();
        for (GroupEntity groupEntity : contact.getGroups()) {
            groupIds.add(groupEntity.getId());
        }
        savedContact.setGroups(new HashSet<GroupEntity>(groupDao.get(groupIds)));

        if (contact.getAddresses() != null) {
            for (AddressEntity addressEntity : contact.getAddresses()) {
                addressEntity.setContact(savedContact);
            }
            savedContact.setAddresses(contact.getAddresses());
        }

        if (contact.getFields() != null) {
            for (FieldEntity fieldEntity : contact.getFields()) {
                fieldEntity.setContact(savedContact);
            }
            savedContact.setFields(contact.getFields());
        }

        super.update(savedContact);
    }

    @Override
    public long count() {
        return contactDao.count();
    }

    @Override
    public void removePhoto(final Integer id) {
        final ContactEntity contact = get(id);

        if (contact == null) {
            throw new ContactNotFoundException("Cannot remove photo with given id=" + id);
        }

        contact.setPhoto(null);
        contactDao.update(contact);
    }

    @Override
    public byte[] getPhoto(final Integer id) {
        final ContactEntity contact = get(id);

        if (contact != null) {
            return contact.getPhoto();
        }

        return null;
    }

    @Override
    public Page<ContactEntity> queryByGroup(final Integer groupId, final Pageable pageableRequest) {
        final long count = contactDao.countByGroup(groupId);
        final Pageable pageable = PaginationUtils.checkRange(pageableRequest, count);

        return new PageImpl<ContactEntity>(contactDao.queryByGroup(groupId, pageable), pageable, count);
    }
}

package com.noveogroup.tulupov.addressbook.service;


import com.noveogroup.tulupov.addressbook.entity.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Contact service interface.
 */
public interface ContactService extends AbstractService<Integer, ContactEntity> {

    Page<ContactEntity> queryByGroup(Integer groupId, Pageable pageableRequest);

    void removePhoto(Integer id);

    byte[] getPhoto(Integer id);

    long count();
}

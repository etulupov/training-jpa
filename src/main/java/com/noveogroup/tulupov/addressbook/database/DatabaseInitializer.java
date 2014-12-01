package com.noveogroup.tulupov.addressbook.database;

import com.noveogroup.tulupov.addressbook.entity.AddressEntity;
import com.noveogroup.tulupov.addressbook.entity.ContactEntity;
import com.noveogroup.tulupov.addressbook.entity.GroupEntity;
import com.noveogroup.tulupov.addressbook.entity.field.*;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import com.noveogroup.tulupov.addressbook.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;

/**
 * Database initializer.
 */
@Component
@Slf4j
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private static final Random RANDOM = new Random();

    private static final int COUNT = 5;
    private static final int GROUP_COUNT = 5;

    private static final int LIMIT = 5;
    private static final int RANDOM_BITS = 128;
    private static final int RADIX = 32;

    @Autowired
    private ContactService contactService;

    @Autowired
    private GroupService groupService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        init();
    }

    private void init() {
        if (groupService.count() == 0) {
            log.debug("Start filling groups");
            for (int i = 0; i < GROUP_COUNT; i++) {
                groupService.add(GroupEntity.builder()
                        .name(String.format("Group #%s", i))
                        .build());
            }
            log.debug("Completed");
        } else {
            log.debug("Groups table is not empty");
        }

        if (contactService.count() == 0) {
            final List<GroupEntity> groups = groupService.queryAll();

            log.debug("Start filling contacts");
            for (int i = 0; i < COUNT; i++) {
                contactService.add(ContactEntity.builder()
                        .firstname("Vasya")
                        .lastname("Pupkin #" + i)
                        .groups(getRandomGroups(groups))
                        .addresses(getRandomAddresses())
                        .fields(getRandomFields())
                        .build());
            }
            log.debug("Completed!");
        } else {
            log.debug("Contacts table is not empty");
        }


    }

    private List<AddressEntity> getRandomAddresses() {
        final List<AddressEntity> result = new ArrayList<>();

        for (int i = 0; i < RANDOM.nextInt(LIMIT) + 1; i++) {
            result.add(AddressEntity.builder()
                    .value(generateRandomString())
                    .build());
        }

        return result;
    }

    private List<FieldEntity> getRandomFields() {
        final List<FieldEntity> result = new ArrayList<>();

        result.add(EmailFieldEntity.builder()
                .email(String.format("%s@gmail.com", generateRandomString()))
                .build());

        result.add(DateFieldEntity.builder()
                .date(new Date(RANDOM.nextLong() % System.currentTimeMillis()))
                .build());

        result.add(LinkFieldEntity.builder()
                .link(String.format("http://%s.com", generateRandomString()))
                .build());

        result.add(TextFieldEntity.builder()
                .text(generateRandomString())
                .build());

        result.add(NumberFieldEntity.builder()
                .number(RANDOM.nextInt())
                .build());

        return result;
    }

    private Set<GroupEntity> getRandomGroups(final List<GroupEntity> groups) {
        final Set<GroupEntity> result = new HashSet<>();


        for (int i = 0; i < RANDOM.nextInt(groups.size()) + 1; i++) {
            result.add(groups.get(RANDOM.nextInt(groups.size())));
        }

        return result;
    }

    private String generateRandomString() {
        return new BigInteger(RANDOM_BITS, RANDOM).toString(RADIX);
    }
}

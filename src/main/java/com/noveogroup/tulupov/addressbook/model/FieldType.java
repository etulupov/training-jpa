package com.noveogroup.tulupov.addressbook.model;

import lombok.Getter;

/**
 * Field type.
 */
public enum FieldType {
    DATE("label.fields.date"),
    EMAIL("label.fields.email"),
    LINK("label.fields.link"),
    NUMBER("label.fields.number"),
    TEXT("label.fields.text");

    @Getter
    private final String title;

    FieldType(final String title) {
        this.title = title;
    }
}

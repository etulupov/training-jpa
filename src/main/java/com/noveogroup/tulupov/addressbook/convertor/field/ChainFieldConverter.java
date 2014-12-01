package com.noveogroup.tulupov.addressbook.convertor.field;

/**
 * Filed convertor.
 */
public interface ChainFieldConverter extends FieldConverter {
    void setNext(FieldConverter converter);
}

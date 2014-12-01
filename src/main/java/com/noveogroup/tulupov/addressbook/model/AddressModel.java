package com.noveogroup.tulupov.addressbook.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * Address model.
 */
public class AddressModel implements ValueModel {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Size(min = 3, max = 30, message = "{error.address.value.invalid_size}")
    private String value;

}

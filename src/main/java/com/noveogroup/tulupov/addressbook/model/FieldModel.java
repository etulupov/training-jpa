package com.noveogroup.tulupov.addressbook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Builder;

/**
 * Field model.
 */
@Builder
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class FieldModel implements ValueModel {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private FieldType fieldType;

    @Getter
    @Setter
    private String value;
}

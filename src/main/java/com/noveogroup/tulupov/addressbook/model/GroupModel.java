package com.noveogroup.tulupov.addressbook.model;

import lombok.*;
import lombok.experimental.Builder;

/**
 * Group model.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class GroupModel implements Model {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String value;
}

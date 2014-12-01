package com.noveogroup.tulupov.addressbook.entity;

import lombok.*;
import lombok.experimental.Builder;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static com.noveogroup.tulupov.addressbook.entity.GroupEntity.TABLE_NAME;

/**
 * Group model.
 */
@Entity
@Table(name = TABLE_NAME)
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@SuppressWarnings("unused")
public class GroupEntity {
    public static final String TABLE_NAME = "groups";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CONTACT_ID = "contact_id";

    @Id
    @Column(name = ID)
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @Column(name = NAME)
    @Getter
    @Setter
    @Size(min = 3, max = 30, message = "{error.group.name.invalid_size}")
    private String name;
}

package com.noveogroup.tulupov.addressbook.entity;

import lombok.*;
import lombok.experimental.Builder;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static com.noveogroup.tulupov.addressbook.entity.AddressEntity.TABLE_NAME;

/**
 * Address model.
 */
@Entity
@Table(name = TABLE_NAME)
@ToString(exclude = "contact")
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@SuppressWarnings("unused")
public class AddressEntity {
    public static final String TABLE_NAME = "Address";
    public static final String ID = "id";
    public static final String VALUE = "value";
    public static final String CONTACT_ID = "contact_id";

    @Id
    @Column(name = ID)
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @Column(name = VALUE)
    @Getter
    @Setter
    @Size(min = 3, max = 30, message = "{error.address.value.invalid_size}")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CONTACT_ID)
    @Getter
    @Setter
    private ContactEntity contact;
}

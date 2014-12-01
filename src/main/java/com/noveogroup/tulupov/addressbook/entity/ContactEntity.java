package com.noveogroup.tulupov.addressbook.entity;

import com.noveogroup.tulupov.addressbook.entity.field.FieldEntity;
import lombok.*;
import lombok.experimental.Builder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.noveogroup.tulupov.addressbook.entity.ContactEntity.TABLE_NAME;

/**
 * Contact entity.
 */
@Entity
@Table(name = TABLE_NAME)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@SuppressWarnings("unused")
public final class ContactEntity {
    public static final String TABLE_NAME = "Contact";
    public static final String ID = "id";
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";
    public static final String PHOTO = "photo";

    public static final String TABLE_GROUPS = "ContactGroup";
    public static final String GROUP_ID = "group_id";
    public static final String CONTACT_ID = "contact_id";

    @Id
    @Column(name = ID)
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @Column(name = FIRST_NAME)
    @Getter
    @Setter
    @Size(min = 3, max = 30, message = "{error.contact.firstname.invalid_size}")
    private String firstname;

    @Column(name = LAST_NAME)
    @Getter
    @Setter
    @Size(min = 3, max = 30, message = "{error.contact.lastname.invalid_size}")
    private String lastname;

    @Column(name = PHOTO)
    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Getter
    @Setter
    private byte[] photo;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Size(min = 1, message = "{error.contact.fields.invalid_size}")
    private List<FieldEntity> fields = new ArrayList<>();


    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Size(min = 1, message = "{error.contact.addresses.invalid_size}")
    private List<AddressEntity> addresses = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = TABLE_GROUPS,
            joinColumns = @JoinColumn(name = CONTACT_ID),
            inverseJoinColumns = @JoinColumn(name = GROUP_ID))
    @Getter
    @Setter
    @Size(min = 1, message = "{error.contact.groups.invalid_size}")
    private Set<GroupEntity> groups;

    public void setAddresses(final List<AddressEntity> addresses) {
        this.addresses.clear();
        this.addresses.addAll(addresses);
    }

    public void setFields(final List<FieldEntity> fields) {
        this.fields.clear();
        this.fields.addAll(fields);
    }
}

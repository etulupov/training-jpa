package com.noveogroup.tulupov.addressbook.entity.field;

import com.noveogroup.tulupov.addressbook.entity.ContactEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static com.noveogroup.tulupov.addressbook.entity.field.FieldEntity.TABLE_NAME;

/**
 * Abstract field model.
 */
@Entity
@Table(name = TABLE_NAME)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString(exclude = "contact")
@SuppressWarnings("unused")
public class FieldEntity {
    public static final String TABLE_NAME = "AbstractField";
    public static final String ID = "id";
    public static final String CONTACT_ID = "contact_id";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    protected Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CONTACT_ID)
    @Getter
    @Setter
    protected ContactEntity contact;
}

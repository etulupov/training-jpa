package com.noveogroup.tulupov.addressbook.entity.field;

import lombok.*;
import lombok.experimental.Builder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.noveogroup.tulupov.addressbook.entity.field.EmailFieldEntity.TABLE_NAME;

/**
 * Email field model.
 */
@Entity
@Table(name = TABLE_NAME)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@SuppressWarnings("unused")
public class EmailFieldEntity extends FieldEntity {
    public static final String TABLE_NAME = "EmailField";
    public static final String EMAIL = "email";

    @Column(name = EMAIL)
    @Getter
    @Setter
    @Email(message = "{error.contact.email.invalid_email}")
    @NotEmpty(message = "{error.contact.email.empty}")
    private String email;
}

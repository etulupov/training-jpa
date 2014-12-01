package com.noveogroup.tulupov.addressbook.entity.field;

import lombok.*;
import lombok.experimental.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import static com.noveogroup.tulupov.addressbook.entity.field.TextFieldEntity.TABLE_NAME;

/**
 * Text field model.
 */
@Entity
@Table(name = TABLE_NAME)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@SuppressWarnings("unused")
public class TextFieldEntity extends FieldEntity {
    public static final String TABLE_NAME = "TextField";
    public static final String TEXT = "text";

    @Column(name = TEXT)
    @Getter
    @Setter
    @Size(min = 3, max = 30, message = "{error.field.text.invalid_size}")
    private String text;
}

package com.noveogroup.tulupov.addressbook.entity.field;

import lombok.*;
import lombok.experimental.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.noveogroup.tulupov.addressbook.entity.field.NumberFieldEntity.TABLE_NAME;

/**
 * Number field model.
 */
@Entity
@Table(name = TABLE_NAME)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@SuppressWarnings("unused")
public class NumberFieldEntity extends FieldEntity {
    public static final String TABLE_NAME = "NumberField";
    public static final String NUMBER = "number";

    @Column(name = NUMBER)
    @Getter
    @Setter
    private Integer number;
}

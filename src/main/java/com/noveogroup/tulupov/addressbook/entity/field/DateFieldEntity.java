package com.noveogroup.tulupov.addressbook.entity.field;

import lombok.*;
import lombok.experimental.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

import static com.noveogroup.tulupov.addressbook.entity.field.DateFieldEntity.TABLE_NAME;

/**
 * Date field model.
 */
@Entity
@Table(name = TABLE_NAME)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@SuppressWarnings("unused")
public class DateFieldEntity extends FieldEntity {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String TABLE_NAME = "DateField";
    public static final String DATE = "date";

    @Column(name = DATE)
    @Getter
    @Setter
    private Date date;

}

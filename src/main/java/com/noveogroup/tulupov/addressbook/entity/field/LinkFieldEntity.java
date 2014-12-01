package com.noveogroup.tulupov.addressbook.entity.field;

import lombok.*;
import lombok.experimental.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import static com.noveogroup.tulupov.addressbook.entity.field.LinkFieldEntity.TABLE_NAME;

/**
 * Link field model.
 */
@Entity
@Table(name = TABLE_NAME)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@SuppressWarnings("unused")
public class LinkFieldEntity extends FieldEntity {
    public static final String TABLE_NAME = "LinkField";
    public static final String LINK = "link";

    private static final String REGEXP = "(https?://([-\\w\\.]+)+(:\\d+)?(/([\\w/_\\.]*(\\?\\S+)?)?)?)";

    @Column(name = LINK)
    @Getter
    @Setter
    @Pattern(regexp = REGEXP, message = "{error.field.link.invalid_format}")
    private String link;
}

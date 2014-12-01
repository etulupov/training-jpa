package com.noveogroup.tulupov.addressbook.model;

import com.noveogroup.tulupov.addressbook.validator.ContentType;
import com.noveogroup.tulupov.addressbook.validator.Length;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Contact model.
 */
@ToString
@SuppressWarnings("unused")
public final class ContactModel implements Model {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Size(min = 3, max = 30, message = "{error.contact.firstname.invalid_size}")
    private String firstname;

    @Getter
    @Setter
    @Size(min = 3, max = 30, message = "{error.contact.lastname.invalid_size}")
    private String lastname;

    @Getter
    @Setter
    @ContentType(value = "image/jpeg", message = "{error.contact.file.invalid_type}")
    @Length(value = 1048576, message = "{error.contact.file.invalid_size}")
    private MultipartFile photo;

    @Getter
    @Setter
    private List<FieldModel> fields;

    @Getter
    @Setter
    private List<FieldType> fieldTypes;

    @Getter
    @Setter
    private List<GroupModel> groups;

    @Getter
    @Setter
    private List<AddressModel> addresses;

}

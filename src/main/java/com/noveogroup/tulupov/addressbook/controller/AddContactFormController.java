package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.convertor.ContactConvertor;
import com.noveogroup.tulupov.addressbook.entity.ContactEntity;
import com.noveogroup.tulupov.addressbook.model.AddressModel;
import com.noveogroup.tulupov.addressbook.model.ContactModel;
import com.noveogroup.tulupov.addressbook.model.FieldModel;
import com.noveogroup.tulupov.addressbook.model.FieldType;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;

import static com.noveogroup.tulupov.addressbook.controller.ControllerConstants.*;

/**
 * Contact add form controller.
 */
@Controller
public class AddContactFormController extends AbstractContactFormController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactConvertor contactConvertor;

    @RequestMapping(value = "/contacts/add", method = RequestMethod.POST)
    public String submitForm(final Model model,
                             @ModelAttribute(MODEL_CONTACT)
                             @Valid final ContactModel contact,
                             final BindingResult result) throws IOException {

        if (result.hasErrors()) {
            setupModelAttributes(model);
            return VIEW_CONTACT;
        }

        final ContactEntity entity = contactConvertor.convertModelToEntity(contact);

        contactService.add(entity);

        return REDIRECT_VIEW_SHOW_CONTACT + entity.getId();
    }

    @RequestMapping(value = "/contacts/add", method = RequestMethod.GET)
    public String form(final Model model) {
        model.addAttribute(MODEL_CONTACT, generateDefaultContactModel());
        setupModelAttributes(model);

        return VIEW_CONTACT;
    }

    private ContactModel generateDefaultContactModel() {
        final ContactModel contactModel = new ContactModel();

        final FieldModel fieldModel = new FieldModel();
        fieldModel.setFieldType(FieldType.values()[0]);

        contactModel.setAddresses(Arrays.asList(new AddressModel()));
        contactModel.setFields(Arrays.asList(fieldModel));
        contactModel.setFieldTypes(Arrays.asList(fieldModel.getFieldType()));

        return contactModel;
    }
}

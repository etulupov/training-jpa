package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.convertor.ContactConvertor;
import com.noveogroup.tulupov.addressbook.entity.ContactEntity;
import com.noveogroup.tulupov.addressbook.exception.ContactNotFoundException;
import com.noveogroup.tulupov.addressbook.model.ContactModel;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;

import static com.noveogroup.tulupov.addressbook.controller.ControllerConstants.*;

/**
 * Contact edit form controller.
 */
@Controller

public class EditFormController extends AbstractContactFormController {

    private static final String MODEL_CONTACT = CONTACT;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactConvertor contactConvertor;

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editForm(
            final Model model,
            @PathVariable final int id,
            @ModelAttribute(MODEL_CONTACT)
            @Valid final ContactModel contact,
            final BindingResult result) throws IOException {

        if (result.hasErrors()) {
            setupModelAttributes(model);
            return VIEW_EDIT_CONTACT;
        }

        contact.setId(id);

        final ContactEntity entity = contactConvertor.convertModelToEntity(contact);

        try {
            contactService.update(entity);
        } catch (RuntimeException e) {
            throw new ContactNotFoundException("Cannot update contact id=" + id, e);
        }

        return REDIRECT_VIEW_SHOW_CONTACT + id;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable final int id,
                       final Model model) throws IOException {
        final ContactEntity entity = contactService.get(id);

        if (entity == null) {
            throw new ContactNotFoundException("Cannot find contact by id=" + id);
        }

        model.addAttribute(MODEL_CONTACT, contactConvertor.convertEntityToModel(entity));
        setupModelAttributes(model);

        return VIEW_EDIT_CONTACT;
    }


}

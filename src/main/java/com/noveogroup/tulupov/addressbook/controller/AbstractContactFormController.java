package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.convertor.GroupConvertor;
import com.noveogroup.tulupov.addressbook.model.*;
import com.noveogroup.tulupov.addressbook.service.GroupService;
import com.noveogroup.tulupov.addressbook.util.ModelPropertyEditor;
import com.noveogroup.tulupov.addressbook.util.ValueModelPropertyEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Arrays;

import static com.noveogroup.tulupov.addressbook.controller.ControllerConstants.MODEL_FIELD_TYPES;
import static com.noveogroup.tulupov.addressbook.controller.ControllerConstants.MODEL_GROUPS_LIST;

/**
 * Abstract contact form controller.
 */
@SuppressWarnings("unchecked")
public abstract class AbstractContactFormController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupConvertor groupConvertor;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        registerModelEditor(binder, GroupModel.class);
        registerValueModelEditor(binder, AddressModel.class);
        registerValueModelEditor(binder, FieldModel.class);
    }

    private void registerModelEditor(final WebDataBinder binder, final Class<? extends Model> clazz) {
        binder.registerCustomEditor(clazz, new ModelPropertyEditor(clazz));
    }

    private void registerValueModelEditor(final WebDataBinder binder, final Class<? extends Model> clazz) {
        binder.registerCustomEditor(clazz, new ValueModelPropertyEditor(clazz));
    }

    protected void setupModelAttributes(final org.springframework.ui.Model model) {
        model.addAttribute(MODEL_GROUPS_LIST, groupConvertor.convertEntitiesToModels(groupService.queryAll()));
        model.addAttribute(MODEL_FIELD_TYPES, Arrays.asList(FieldType.values()));
    }
}

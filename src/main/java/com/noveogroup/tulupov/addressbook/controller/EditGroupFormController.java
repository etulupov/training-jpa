package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.convertor.GroupConvertor;
import com.noveogroup.tulupov.addressbook.entity.GroupEntity;
import com.noveogroup.tulupov.addressbook.exception.GroupNotFoundException;
import com.noveogroup.tulupov.addressbook.model.GroupModel;
import com.noveogroup.tulupov.addressbook.service.GroupService;
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
public class EditGroupFormController extends AbstractContactFormController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupConvertor groupConvertor;

    @RequestMapping(value = "/groups/edit/{id}", method = RequestMethod.POST)
    public String editForm(
            final Model model,
            @PathVariable final int id,
            @ModelAttribute(MODEL_CONTACT)
            @Valid final GroupModel group,
            final BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return VIEW_EDIT_GROUP;
        }

        group.setId(id);

        final GroupEntity entity = groupConvertor.convertModelToEntity(group);

        try {
            groupService.update(entity);
        } catch (RuntimeException e) {
            throw new GroupNotFoundException("Cannot update group id=" + id, e);
        }

        return REDIRECT_VIEW_SHOW_GROUPS;
    }


    @RequestMapping(value = "/groups/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable final int id,
                       final Model model) throws IOException {
        final GroupEntity entity = groupService.get(id);

        if (entity == null) {
            throw new GroupNotFoundException("Cannot find group by id=" + id);
        }

        model.addAttribute(MODEL_GROUP, groupConvertor.convertEntityToModel(entity));

        return VIEW_EDIT_GROUP;
    }
}

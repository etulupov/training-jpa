package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.convertor.GroupConvertor;
import com.noveogroup.tulupov.addressbook.entity.GroupEntity;
import com.noveogroup.tulupov.addressbook.model.GroupModel;
import com.noveogroup.tulupov.addressbook.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;

import static com.noveogroup.tulupov.addressbook.controller.ControllerConstants.*;

/**
* Group add form controller.
*/
@Controller
public class AddGroupFormController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupConvertor groupConvertor;

    @RequestMapping(value = "/groups/add", method = RequestMethod.POST)
    public String submitForm(
            @ModelAttribute(MODEL_GROUP)
            @Valid final GroupModel groupModel,
            final BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return VIEW_GROUP;
        }

        final GroupEntity groupEntity = groupConvertor.convertModelToEntity(groupModel);

        groupService.add(groupEntity);

        return REDIRECT_VIEW_SHOW_GROUPS;
    }

    @RequestMapping(value = "/groups/add", method = RequestMethod.GET)
    public String form(final Model model) {
        model.addAttribute(MODEL_GROUP, new GroupModel());

        return VIEW_GROUP;
    }
}

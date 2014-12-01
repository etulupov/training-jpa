package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.convertor.GroupConvertor;
import com.noveogroup.tulupov.addressbook.entity.GroupEntity;
import com.noveogroup.tulupov.addressbook.exception.GroupIsUsedException;
import com.noveogroup.tulupov.addressbook.exception.GroupNotFoundException;
import com.noveogroup.tulupov.addressbook.service.GroupService;
import com.noveogroup.tulupov.addressbook.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.noveogroup.tulupov.addressbook.controller.ControllerConstants.*;
import static com.noveogroup.tulupov.addressbook.model.PageItemModel.PAGE_SIZE;

/**
 * Group controller.
 */
@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupConvertor groupConvertor;

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public String listGroups(final Model model,
                             @PageableDefault(size = PAGE_SIZE)
                             final Pageable pageable) {
        final Page<GroupEntity> page = groupService.query(pageable);
        final PageWrapper wrapper = new PageWrapper(page);

        model.addAttribute(MODEL_LIST, groupConvertor.convertEntitiesToModels(page.getContent()));
        model.addAttribute(MODEL_PAGES, wrapper.getItems());
        model.addAttribute(MODEL_PAGE, pageable.getPageNumber());
        model.addAttribute(MODEL_SORT, pageable.getSort());

        return VIEW_GROUPS;
    }

    @RequestMapping(value = "/groups/delete/{id}", method = RequestMethod.GET)
    public String deleteGroup(@PathVariable("id") final Integer id,
                              @RequestParam(required = false) final String page) {
        try {
            groupService.remove(id);
        } catch (DataIntegrityViolationException e) {
            throw new GroupIsUsedException("Cannot delete group with contacts", e);
        } catch (DataAccessException e) {
            throw new GroupNotFoundException("Cannot delete group by id=" + id, e);
        }

        if (page != null) {
            return REDIRECT_VIEW_GROUP_WITH_PAGINATION + page;
        } else {
            return REDIRECT_VIEW_SHOW_GROUPS;
        }
    }
}

package com.noveogroup.tulupov.addressbook.controller;

/**
 * Controller constants.
 */
public final class ControllerConstants {
    public static final String PREFIX_PAGE = "/?page=";

    public static final String VIEW_LIST = "contacts";
    public static final String VIEW_SHOW_CONTACT = "show_contact";
    public static final String REDIRECT_VIEW_LIST = "redirect:/contacts";
    public static final String REDIRECT_VIEW_LIST_WITH_PAGINATION = REDIRECT_VIEW_LIST + PREFIX_PAGE;
    public static final String REDIRECT_VIEW_EDIT_CONTACT = "redirect:/edit/";

    public static final String VIEW_PAGE_NOT_FOUND = "error_404";
    public static final String REDIRECT_PAGE_NOT_FOUND = "redirect:/error/404.html";

    public static final String VIEW_CONTACT_NOT_FOUND = "error_contact_not_found";
    public static final String VIEW_INVALID_SORT_ORDER = "error_invalid_sort_order";
    public static final String VIEW_GROUP_NOT_FOUND = "error_group_not_found";
    public static final String VIEW_GROUP_IS_USED = "error_group_is_used";
    public static final String VIEW_INVALID_ENTITY = "error_invalid_entity";

    public static final String CONTACT = "contact";
    public static final String VIEW_EDIT_CONTACT = "edit_contact";
    public static final String VIEW_EDIT_GROUP = "edit_group";
    public static final String REDIRECT_VIEW_SHOW_CONTACT = "redirect:/contacts/";
    public static final String REDIRECT_VIEW_SHOW_GROUPS = "redirect:/groups";
    public static final String REDIRECT_VIEW_GROUP_WITH_PAGINATION = REDIRECT_VIEW_SHOW_GROUPS + PREFIX_PAGE;

    public static final String VIEW_CONTACT = "add_contact";
    public static final String VIEW_GROUP = "add_group";
    public static final String VIEW_GROUPS = "groups";

    public static final String MODEL_CONTACT = CONTACT;
    public static final String MODEL_GROUP = "group";
    public static final String MODEL_LIST = "list";

    public static final String MODEL_CONTACT_LIST = "contactList";
    public static final String MODEL_PAGES = "pages";
    public static final String MODEL_PAGE = "page";
    public static final String MODEL_SORT = "sort";
    public static final String MODEL_GROUPS_LIST = "groupsList";
    public static final String MODEL_FIELD_TYPES = "fieldTypes";

    private ControllerConstants() {
        throw new UnsupportedOperationException();
    }
}

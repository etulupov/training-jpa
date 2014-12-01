package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.exception.ContactNotFoundException;
import com.noveogroup.tulupov.addressbook.exception.GroupIsUsedException;
import com.noveogroup.tulupov.addressbook.exception.GroupNotFoundException;
import org.hibernate.QueryException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.noveogroup.tulupov.addressbook.controller.ControllerConstants.*;

/**
 * Exception handling controller.
 */
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ContactNotFoundException.class)
    public String contactNotFoundError() {
        return VIEW_CONTACT_NOT_FOUND;
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public String groupNotFoundError() {
        return VIEW_GROUP_NOT_FOUND;
    }

    @ExceptionHandler(GroupIsUsedException.class)
    public String groupIsUsedError() {
        return VIEW_GROUP_IS_USED;
    }

    @ExceptionHandler(QueryException.class)
    public String invalidSortOrderError() {
        return VIEW_INVALID_SORT_ORDER;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String invalidEntityError() {
        return VIEW_INVALID_ENTITY;
    }
}

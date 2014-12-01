package com.noveogroup.tulupov.addressbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.noveogroup.tulupov.addressbook.controller.ControllerConstants.REDIRECT_PAGE_NOT_FOUND;
import static com.noveogroup.tulupov.addressbook.controller.ControllerConstants.VIEW_PAGE_NOT_FOUND;
/**
 * Http errors controller.
 */
@Controller
public class HttpErrorController {
    @RequestMapping(value = "/error/404", method = { RequestMethod.GET, RequestMethod.POST })
    public String contactNotFoundError() {
        return REDIRECT_PAGE_NOT_FOUND;
    }

    @RequestMapping(value = "/error/404.html", method = RequestMethod.GET)
    public String sss() {
        return VIEW_PAGE_NOT_FOUND;
    }
}

package com.noveogroup.tulupov.addressbook.util;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Stores in session sort parameters.
 */
public class SessionSortHandlerMethodArgumentResolver extends SortHandlerMethodArgumentResolver {
    private static final String SESSION_SORT = "sort";
    private static final String SESSION_REQUEST_URL = "request_url";

    @Override
    public Sort resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
                                final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        final HttpSession session = request.getSession();
        final String requestUrl = request.getRequestURL().toString();
        final String storedRequestUrl = (String) session.getAttribute(SESSION_REQUEST_URL);

        Sort sort = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);

        if (requestUrl.equals(storedRequestUrl)) {
            if (sort == null) {
                sort = (Sort) session.getAttribute(SESSION_SORT);
            } else {
                session.setAttribute(SESSION_SORT, sort);
            }
        } else {
            session.setAttribute(SESSION_SORT, sort);
        }

        session.setAttribute(SESSION_REQUEST_URL, requestUrl);

        return sort;
    }
}

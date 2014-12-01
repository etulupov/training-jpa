<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tags/functions.tld" prefix="f" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="title"/></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-table.css"/>">
</head>
<body>

<div>
    <div class="page-header"><h3><spring:message code="label.groups.list"/></h3></div>
    <c:choose>
        <c:when test="${!empty list}">
            <my:paginator/>

            <table class="table table-striped fixed-table-container">
                <thead>
                <tr>
                    <my:column name="id" title="label.id"/>
                    <my:column name="name" title="label.value"/>
                    <th class="button">&nbsp;</th>
                    <th class="button">&nbsp;</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="group">
                    <tr href="<c:url value="/contacts/by-group/${group.id}"/>" class="table-row">
                        <td>${group.id}</td>
                        <td>${group.value}</td>
                        <td class="button"><a href="<c:url value="/groups/edit/${group.id}"/>"><img
                                src="<c:url value="/resources/image/edit.png"/>" alt=""/> </a></td>
                        <td class="button"><a href="<c:url value="/groups/delete/${group.id}?page=${page}"/>"><img
                                src="<c:url value="/resources/image/delete.png"/>" alt=""/>
                        </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <my:paginator/>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info"><spring:message code="error.no_groups"/></div>
        </c:otherwise>
    </c:choose>

</div>

</body>
</html>






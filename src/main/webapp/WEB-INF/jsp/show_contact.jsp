<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tags/functions.tld" prefix="f" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="label.title"/></title>
</head>
<body>


<div class="page-header"><h3><spring:message code="label.show_contact"/></h3></div>

<div class="col-sm-3">
    <img src="../photo/${contact.id}" alt="" class="photo-big"/>
</div>
<div class="col-sm-5">

    <ul class="list-group">
        <li class="list-group-item"><b><spring:message code="label.firstname"/></b>: ${contact.firstname}</li>
        <li class="list-group-item"><b><spring:message code="label.lastname"/></b>: ${contact.lastname}</li>
        <li class="list-group-item"><b><spring:message code="label.groups"/></b>:
            <ul class="list">
                <c:forEach var="group" items="${contact.groups}">
                    <li class="group"><a href="<c:url value="/contacts/by-group/${group.id}"/>">${group.value}</a></li>
                </c:forEach>
            </ul>
        </li>

        <li class="list-group-item"><b><spring:message code="label.address"/></b>:
            <ul class="list">
                <c:forEach var="address" items="${contact.addresses}">
                    <li class="address"><a href="http://maps.google.com/?q=${address.value}">${address.value}</a></li>
                </c:forEach>
            </ul>
        </li>

        <li class="list-group-item"><b><spring:message code="label.fields"/></b>:
            <ul class="list">
                <c:forEach var="field" items="${contact.fields}">
                    <c:set var="type" value="${field.fieldType.toString().toLowerCase()}"/>
                    <c:set var="value" value="${field.value}"/>

                    <c:choose>
                        <c:when test="${type == 'email'}">
                            <c:set var="value" value="<a href='mailto:${field.value}'>${field.value}</a>"/>
                        </c:when>
                        <c:when test="${type == 'link'}">
                            <c:set var="value" value="<a href='${field.value}'>${field.value}</a>"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="value" value="${field.value}"/>
                        </c:otherwise>
                    </c:choose>

                    <li class="${type}">${value}</li>
                </c:forEach>
            </ul>
        </li>


        <li class="list-group-item"><b><spring:message code="label.actions"/></b>:
            <a href="../edit/${contact.id}"> <img src="../resources/image/edit.png" alt=""/> </a>
            <a href="../delete/${contact.id}"><img src="../resources/image/delete.png" alt=""/></a>
        </li>
    </ul>
</div>


</body>
</html>






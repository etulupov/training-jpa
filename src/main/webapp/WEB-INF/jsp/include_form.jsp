<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="form-group">
    <form:label path="firstname" class="col-sm-2 control-label"><spring:message
            code="label.firstname"/></form:label>
    <div class="col-sm-5">
        <form:input path="firstname" class="form-control"/>
        <small class="help-block has-error"><form:errors path="firstname" cssClass="help-block"/></small>
    </div>
</div>

<div class="form-group">
    <form:label path="lastname" class="col-sm-2 control-label"><spring:message
            code="label.lastname"/></form:label>
    <div class="col-sm-5">
        <form:input path="lastname" class="form-control"/>
        <small class="help-block has-error"><form:errors path="lastname" cssClass="help-block"/></small>
    </div>
</div>

<div class="form-group">
    <form:label path="photo" class="col-sm-2 control-label"><spring:message
            code="label.photo"/></form:label>
    <div class="col-sm-4">
        <input name="photo" type="file" class="form-control"/>
        <small class="help-block has-error"><form:errors path="photo" cssClass="help-block"/></small>
    </div>
</div>

<div class="form-group">
    <form:label path="groups" class="col-sm-2 control-label"><spring:message
            code="label.groups"/></form:label>

    <div class="col-sm-5" id="checkbox-list">
        <form:checkboxes items="${groupsList}"
                         path="groups"
                         itemValue="id"
                         itemLabel="value"/>
        <small class="help-block has-error"><form:errors path="groups" cssClass="help-block"/></small>
    </div>
</div>

<div id="container-addresses">
    <c:forEach items="${contact.addresses}" var="address" varStatus="status">

        <div class="form-group">
            <form:label path="addresses[${status.index}]" class="col-sm-2 control-label"><spring:message
                    code="label.address"/></form:label>
            <div class="col-sm-5">


                <form:input path="addresses[${status.index}]" class="form-control"/>
                <small class="help-block has-error"><form:errors path="addresses[${status.index}]"
                                                                 cssClass="help-block"/></small>
            </div>
            <c:if test="${status.index == 0}">
                <div class="col-sm-1">
                    <button type="button" id="add_address" class="btn btn-default">Add</button>
                </div>
            </c:if>
        </div>

    </c:forEach>
</div>

<div class="form-group hide" id="address-template">
    <label class="col-sm-2 control-label"><spring:message
            code="label.address"/></label>

    <div class="col-sm-5">
        <input class="form-control"/>
        <small class="help-block has-error"></small>
    </div>
</div>


<div id="container-fields">
    <c:forEach items="${contact.fields}" var="field" varStatus="status">
        <div class="form-group field-row">
            <form:label path="fields[${status.index}]" class="col-sm-2 control-label"><spring:message
                    code="label.fields"/></form:label>
            <div class="col-sm-3">
                <form:input path="fields[${status.index}]" class="form-control"/>
                <small class="help-block has-error"><form:errors path="addresses[${status.index}]"
                                                                 cssClass="help-block"/></small>
            </div>
            <div class="col-sm-2">

                <select class="form-control" name="fieldTypes[${status.index}]">
                    <c:forEach items="${fieldTypes}" var="type">
                        <c:choose>
                            <c:when test="${type == contact.fieldTypes[status.index]}">
                                <option value="${type}" selected="selected"><spring:message
                                        code="${type.getTitle()}"/></option>
                            </c:when>
                            <c:otherwise>
                                <option value="${type}"><spring:message code="${type.getTitle()}"/></option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>

                <small class="help-block has-error"></small>
            </div>
            <c:if test="${status.index == 0}">
                <div class="col-sm-1">
                    <button type="button" id="add_field" class="btn btn-default">Add</button>
                </div>
            </c:if>
        </div>
    </c:forEach>
</div>


<div class="form-group hide" id="field-template">
    <label class="col-sm-2 control-label"><spring:message
            code="label.fields"/></label>

    <div class="col-sm-3">
        <input class="form-control"/>
        <small class="help-block has-error"></small>
    </div>

    <div class="col-sm-2">

        <select class="form-control">
            <c:forEach items="${fieldTypes}" var="type">
                <option value="${type}"><spring:message code="${type.getTitle()}"/></option>
            </c:forEach>
        </select>

        <small class="help-block has-error"></small>
    </div>
</div>
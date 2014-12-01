<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="label.title"/></title>
    <script type="text/javascript" src="<c:url value="/resources/js/group_validator.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/validator_strings.json?callback=init"/>"></script>
</head>
<body>


<h2><spring:message code="label.title"/></h2>

<div id="post">
    <form:form class="form-horizontal" role="form" id="group-form" method="post" action="" commandName="group" enctype="multipart/form-data">
        <fieldset>


            <div class="form-group">
                <form:label path="value" class="col-sm-2 control-label"><spring:message
                        code="label.group.name"/></form:label>
                <div class="col-sm-4">
                    <form:input path="value" class="form-control"/>
                    <small class="help-block has-error"><form:errors path="value" cssClass="help-block"/></small>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-default"><spring:message code="label.group.add"/></button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>


</body>
</html>






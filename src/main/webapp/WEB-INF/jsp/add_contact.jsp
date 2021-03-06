<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="label.title"/></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/form.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/contact_validator.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/validator_strings.json?callback=init"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/contact_form.js"/>"></script>
</head>
<body>


<h2><spring:message code="label.title"/></h2>

<div id="post">
    <form:form class="form-horizontal" role="form" id="add-form" method="post" commandName="contact"
               enctype="multipart/form-data">
        <fieldset>

                <jsp:include page="include_form.jsp"/>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-5">
                    <button type="submit" class="btn btn-default"><spring:message code="label.addcontact"/></button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>


</body>
</html>






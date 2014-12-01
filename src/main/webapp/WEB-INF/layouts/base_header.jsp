<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><sitemesh:write property='title'/></title>
    <link rel="shortcut icon" href="<c:url value="/resources/favicon.ico"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrapValidator.min.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrapValidator.min.js"/>"></script>
    <sitemesh:write property='head'/>
</head>

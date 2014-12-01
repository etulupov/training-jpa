<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<jsp:include page="base_header.jsp"/>
<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/"/>"><spring:message code="title"/></a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/contacts/add"/>"><spring:message code="menu.add"/></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="menu.groups.title"/><b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/groups"/>"><spring:message
                                code="menu.groups.list"/>
                        </a></li>
                        <li><a href="<c:url value="/groups/add"/>"><spring:message
                                code="menu.groups.add"/>
                        </a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="menu.language"/><b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="?language=en"><img src="<c:url value="/resources/image/en.png"/>" alt=""/><spring:message
                                code="menu.language.english"/>
                        </a></li>
                        <li><a href="?language=ru"><img src="<c:url value="/resources/image/ru.png"/>" alt=""/><spring:message
                                code="menu.language.russian"/>
                        </a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<jsp:include page="base_footer.jsp"/>
</body>
</html>



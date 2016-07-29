<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<meta charset="UTF-8"/>
<head>
    <title>${title}</title>
</head>
<body>
<ul>
    <c:forEach var="it" items="${comments}">
        <li>
            <h5>${it.author}</h5>
            <p>${it.content}</p>
        </li>
    </c:forEach>
</ul>
</body>
</html>
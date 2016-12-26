<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<c:forEach items="${list}" var="l">
    <h1>${l.getTitle()}</h1>
    <p>${l.getText()}</p>
    <p>${l.getDate()}</p>
    <c:if test="${permission.equals('admin')}">
        <a href="${pageContext.request.contextPath}/delete?id=${l.getId()}">Удалить</a>
    </c:if>
</c:forEach>

</body>
</html>
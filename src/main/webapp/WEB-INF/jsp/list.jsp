<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<c:forEach items="${list}" var="l">
    <h1>${l.getTitle()}</h1>
    <p>${l.getText()}</p>
    <p>${l.getId()}</p>
    <p>${l.getDate()}</p>
</c:forEach>

</body>
</html>
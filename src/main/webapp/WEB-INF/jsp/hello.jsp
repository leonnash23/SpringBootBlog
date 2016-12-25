<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<c:forEach items="${name.toCharArray()}" var="c">
    <h1>${c}</h1>
</c:forEach>

</body>
</html>
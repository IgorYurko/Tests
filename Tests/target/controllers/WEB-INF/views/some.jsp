
<%@include file="head.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<h1>
    Hello world!
</h1>


    <c:forEach items="${message}" var="res" >
        <P>
         ${res}
        </P>
    </c:forEach>

</body>
</html>
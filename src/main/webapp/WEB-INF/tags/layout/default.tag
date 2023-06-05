<%@ tag language="java" pageEncoding="UTF-8" description="기본 레이아웃" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@attribute name="style" type="java.lang.String" description="style tag" %>
<html>
<head>
    <c:if test="${not empty style}">
        ${style}
    </c:if>
    <c:import url="/template/head"/>
</head>
<title>배달의부족</title>

<body>
<c:import url="/template/nav"/>
<br/>
<br/>
<jsp:doBody/>

<c:import url="/template/footer"/>
</body>
</html>
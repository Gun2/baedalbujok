<%@ tag language="java" pageEncoding="UTF-8" description="기본 레이아웃" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ attribute name="title" type="java.lang.String" description="제목" %>
<%@ attribute name="subTitle" type="java.lang.String" description="부제목" %>

<div style="display: flex;flex-direction: column;align-items: center;">
    <h1 style="font-weight: bold">
        <c:out value="${title}"/>
    </h1>
    <h3>
        <c:out value="${subTitle}"/>
    </h3>
</div>
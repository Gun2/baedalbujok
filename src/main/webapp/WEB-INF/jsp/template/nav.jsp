<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="/">배달의부족</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="" href="#" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-phone-vibrate-fill m-sm-1"></i>배달
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="">
                        <li><a class="dropdown-item" href="/">주문하기</a></li>
                        <li><a class="dropdown-item" href="/order/history">주문내역</a></li>
                        <sec:authorize access="hasAnyRole('ADMIN', 'BOSS')">
                            <li><a class="dropdown-item" href="/menu/form">메뉴등록</a></li>
                        </sec:authorize>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-file-ppt m-sm-1"></i>포인트
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="">
                        <li><a class="dropdown-item" href="/point/present">선물하기</a></li>
                        <li><a class="dropdown-item" href="/point/history">사용내역</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-gift-fill m-sm-1"></i>기프티콘
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="">
                        <li><a class="dropdown-item" href="/gifticon/use">사용하기</a></li>
                        <sec:authorize access="hasRole('ADMIN')">
                            <li><a class="dropdown-item" href="/gifticon">사용내역</a></li>
                        </sec:authorize>
                    </ul>
                </li>
                <sec:authorize access="hasAnyRole('ADMIN')">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-gear-fill m-sm-1"></i>시스템관리
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="">
                            <li><a class="dropdown-item" href="/member">사용자관리</a></li>
                        </ul>
                    </li>
                </sec:authorize>

            </ul>
            <div style="display: flex;gap:10px;">
                <sec:authorize access="isAnonymous()">

                    <button class="btn btn-primary" onclick="goToLogin()">
                        <i class="bi bi-box-arrow-in-right"></i> 로그인
                    </button>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <div class="flex-center-all">
                        <i style="cursor: pointer" class="bi bi-person-circle" onclick="goMyInfo()"></i>
                        <sec:authentication property="principal.name"/>님 환영합니다.
                    </div>
                    <c:if test="${not empty member.point}">
                        <div class="point-div">
                            <text>포인트</text>
                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${member.point.balance}"/>
                        </div>
                    </c:if>
                    <button class="btn btn-dark" onclick="goToLogout()">
                        <i class="bi bi-box-arrow-left"></i> 로그아웃
                    </button>
                </sec:authorize>
            </div>
        </div>
    </div>
</nav>
<script>
    function goToLogin() {
        window.location.href = '/login';
    }

    function goToLogout() {
        if(confirm('로그아웃 하시겠습니까?')){
            window.location.href = '/logout';
        }
    }

    function goMyInfo() {
        location.replace('/my-info')
    }
</script>
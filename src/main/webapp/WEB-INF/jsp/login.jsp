<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<layout:default>
    <form id="loginForm" action="/login" method="post" onsubmit="">
        <input type="hidden" name="redirect" value="<c:out value="${param.redirect}"/>">
        <div class="flex-center-col" style="margin: 50px auto; width: 500px; gap:5px;">
            <img height="300" src="/images/server/caveman_sit_1127x1280.png">
            <h1>배달의부족</h1>
            <h2>로그인</h2>
            <div class="input-group">
            <span class="input-group-text">
                <i class="bi bi-person-fill"></i>
            </span>
                <input type="text"
                       name="username"
                       id="username"
                       onkeydown=""
                       class="form-control" placeholder="아이디를 입력하세요."/>
            </div>
            <div class="input-group">
            <span class="input-group-text">
                <i class="bi bi-key-fill"></i>
            </span>
                <input type="password"
                       name="password"
                       id="password"
                       onkeydown=""
                       class="form-control" placeholder="비밀번호를 입력하세요."/>
            </div>
            <button
                    class="btn btn-primary"
                    onclick=""
                    style="width:100%">로그인
            </button>
            <a class="btn btn-secondary"
               onclick="goToSignUp()"
               style="width:100%">회원가입</a>
        </div>
    </form>


    <script>
        if(${error}){
            alert('로그인에 실패했습니다.');
        }
        function doLogin() {
            document.forms.loginForm.submit();
        }

        function goToSignUp(){
            window.location.href = '/sign-up'
        }
    </script>
</layout:default>

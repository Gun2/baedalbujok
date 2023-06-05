<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<layout:default>
        <div class="flex-center-col" style="margin: 50px auto; width: 500px; gap:5px;">
            <img height="300" src="/images/server/caveman_660x1280.png">
            <h1>배달의부족</h1>
            <h2>회원가입</h2>
            <div class="input-group" error-field="username">
                <span class="input-group-text">
                    <i class="bi bi-person-fill"></i>
                </span>
                <input type="text"
                       name="username"
                       id="username"
                       onkeydown=""
                       class="form-control" placeholder="아이디를 입력하세요."/>
            </div>
            <div class="input-group" error-field="password">
                <span class="input-group-text">
                    <i class="bi bi-key-fill"></i>
                </span>
                <input type="password"
                       name="password"
                       id="password"
                       onkeydown=""
                       class="form-control" placeholder="비밀번호를 입력하세요."/>
            </div>
            <div class="input-group" error-field="passwordd">
                <span class="input-group-text">
                    <i class="bi bi-key-fill"></i>
                </span>
                <input type="password"
                       name="passwordConfirm"
                       id="passwordConfirm"
                       onkeydown=""
                       class="form-control" placeholder="비밀번호를 다시 입력하세요."/>
            </div>
            <div class="input-group" error-field="name">
                <span class="input-group-text">
                    <i class="bi bi-journal-bookmark-fill"></i>
                </span>
                <input type="text"
                       name="name"
                       id="name"
                       onkeydown=""
                       class="form-control" placeholder="닉네임을 입력하세요"/>
            </div>
            <button
                    class="btn btn-primary"
                    onclick="doSignUp()"
                    style="width:100%">회원가입
            </button>
            <a class="btn btn-secondary"
               onclick="goToLogin()"
               style="width:100%">돌아가기</a>
        </div>


    <script>

        function getData(){
            const username = $('#username').val();
            const password = $('#password').val();
            const passwordConfirm = $('#passwordConfirm').val();
            const name = $('#name').val();
            return{
                username,
                password,
                passwordConfirm,
                name
            }
        }

        function doSignUp() {
            const data = getData();

            if(confirm('회원가입을 진행하시겠습니까?')){
                if(data.password != data.passwordConfirm){
                    alert('입력하신 비밀번호가 동일하지 않습니다.');
                }else{
                    bodyRequest({
                        url: '/api/v1/member',
                        method: 'post',
                        data: data,
                        success: data => {
                            alert('가입을 완료하였습니다.')
                            location.replace('/login');
                        },error: (args) => {
                            if(args.responseJSON?.code == 'USERNAME_ALREADY_EXIST'){
                                alert('이미 존재하는 아이디입니다.')
                            }else{
                                alert('요청 처리를 실패했습니다.')
                            }
                        }
                    });
                }

            }

        }

        function goToLogin() {
            window.location.href = '/login'
        }
    </script>
</layout:default>

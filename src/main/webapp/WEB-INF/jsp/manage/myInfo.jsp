<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<layout:default>
    <jsp:attribute name="style">
        <style>
            .info{
                display: flex;
                justify-content: center;
                flex-direction: column;
                padding: 50px;
                gap:10px;
                margin:auto;
                width: 800px;
            }
        </style>
    </jsp:attribute>
    <jsp:body>
        <tags:Title title="내 정보" subTitle="내 정보를 확인하세요"/>
        <div class="info" onsubmit="return false;">
            <h1 style="text-align: center">
                <c:out value="${member.username}"/>
                <text>(<c:out value="${member.roleName}"/>)</text>
            </h1>
            <div>
                <input type="text"
                       class="form-control"
                       name="name" id="name"
                       placeholder="이름을 입력하세요"
                       value="<c:out value="${member.name}"/>"
                />
            </div>
            <div>
                <input type="password"
                       class="form-control"
                       name="password" id="password"
                       placeholder="현재 비밀번호를 입력하세요"
                />
            </div>
            <div>
                <input type="password"
                       class="form-control"
                       name="changePassword" id="changePassword"
                       placeholder="변경할 비밀번호를 입력하세요"
                />
            </div>
            <div>
                <input type="password"
                       class="form-control"
                       name="changePasswordConfirm" id="changePasswordConfirm"
                       error-field="equalsChangePassword"
                       placeholder="변경할 비밀번호를 다시 입력하세요"
                />
            </div>
            <button class="btn btn-primary" onclick="updateFrom()">회원정보 수정</button>
        </div>
    </jsp:body>


</layout:default>

<script>

    function getData(){
        return {
            id: '<c:out value="${member.id}"/>',
            name: $('#name').val(),
            password: $('#password').val(),
            changePassword: $('#changePassword').val(),
            changePasswordConfirm: $('#changePasswordConfirm').val()
        }
    }

    function updateFrom(){
        if(confirm('회원정보를 수정하시겠습니까?')){
            bodyRequest({
                url: '/api/v1/member',
                method: 'PUT',
                data: getData(),
                success: data => {
                    console.log(data);
                    alert(`회원정보가 수정되었습니다,`)
                    location.reload();
                },error: error => {
                    console.error(error);
                    const code = error.responseJSON?.code;
                    switch (code){
                        case "NOT_EQUALS_MEMBER_PASSWORD":
                            alert("비밀번호가 일치하지 않습니다.");
                            break;
                        default:
                            alert('회원정보 수정에 실패하였습니다.');
                    }
                }
            })
        }

    }
</script>
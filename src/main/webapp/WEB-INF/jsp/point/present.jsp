<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<layout:default>
    <jsp:attribute name="style">
        <style>
            .present{
                display: flex;
                justify-content: center;
                flex-direction: column;
                padding: 50px;
                gap:10px;
                margin:auto;
                width: 800px;
            }
            .photo-area{
                display: flex;
                flex-direction: column;
                gap: 10px;
            }
            .preview {
                max-width: 80%;
                max-height: 80%;
                width: auto;
                height: auto;
            }
        </style>
    </jsp:attribute>
    <jsp:body>
        <tags:Title title="포인트 선물" subTitle="다른 사람에게 포인트를 선물해 보세요"/>
        <div class="present" onsubmit="return false;">
            <div>
                <input type="text"
                       class="form-control"
                       name="username" id="username"
                       placeholder="빋으실 분의 아이디를 입력하세요"
                />
            </div>
            <div>
                <input type="number"
                       class="form-control"
                       name="point" id="point"
                       placeholder="선물할 포인트를 입력하세요"
                />
            </div>
            <button class="btn btn-primary" onclick="present()">선물하기</button>
        </div>
    </jsp:body>


</layout:default>

<script>

    function getData(){
        return {
            username: $('#username').val(),
            point: $('#point').val()
        }
    }

    function present(){
        bodyRequest({
            url: '/api/v1/point/present',
            method: 'post',
            data: getData(),
            success: data => {
                console.log(data);
                alert('포인트를 선물하였습니다.')
                location.reload();
            },error: error => {
                console.error(error);
                const errorCode = error.responseJSON?.code;
                switch (errorCode){
                    case "NOT_ENOUGH_POINT": {
                        alert("포인트가 부족합니다.");
                        break;
                    }case "NOT_FOUND_MEMBER": {
                        alert("선물할 대상의 아이디가 존재하지 않습니다.");
                        break;
                    }default : {
                        alert('포인트 선물에 실패하였습니다.')
                    }
                }
            }
        })
    }
</script>
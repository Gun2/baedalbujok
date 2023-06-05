<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<layout:default>
    <jsp:attribute name="style">
        <style>
            .gifticon{
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
        <tags:Title title="기프티콘 사용" subTitle="기프티콘 번호를 입력하여 사용해보세요"/>
        <div class="gifticon" onsubmit="return false;">
            <div>
                <input type="text"
                       class="form-control"
                       name="serialNumber" id="serialNumber"
                       oninput="serialOnInput()"
                       placeholder="기프티콘 번호 입력"
                />
            </div>
            <button class="btn btn-primary" onclick="useGifticon()">사용하기</button>
        </div>
    </jsp:body>


</layout:default>

<script>
    function serialOnInput(){
        var input = document.getElementById('serialNumber');
        var value = input.value.replace(/-/g, ''); // 기존에 입력된 "-" 제거
        if(value.length > 12){
            value = value.slice(0, 12);
        }
        input.value = serialFormat(value);
    }

    function getData(){
        return {
            serialNumber: $('#serialNumber').val().replaceAll('-', '')
        }
    }

    function useGifticon(){
        bodyRequest({
            url: '/api/v1/gifticon/use',
            method: 'post',
            data: getData(),
            success: data => {
                console.log(data);
                alert(`\${numberFormat(data.data.amount)}포인트가 충전되었습니다.`)
                location.reload();
            },error: error => {
                console.error(error);
                const errorCode = error.responseJSON?.code;
                switch (errorCode){
                    case "NOT_FOUND_GIFTICON": {
                        alert("해당하는 기프티콘을 찾을 수 없습니다.");
                        break;
                    }case "ALREADY_USED_GIFTICON": {
                        alert("이미 사용된 기프티콘 입니다.");
                        break;
                    }default : {
                        alert('기프티콘 사용에 실패하였습니다.')
                    }
                }
            }
        })
    }
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<layout:default>
    <jsp:attribute name="style">
        <style>
            .history{
                display: flex;
                justify-content: center;
                flex-direction: column;
                padding: 50px;
                gap:10px;
                margin:auto;
            }
        </style>
    </jsp:attribute>
    <jsp:body>
        <tags:Title title="기프티콘 정보 조회" subTitle="기프티콘 정보를 확인하세요"/>
        <div class="history" onsubmit="return false;">
            <button class="btn btn-primary" onclick="createGifticon()">신규생성</button>
            <table id="dataTable" class="table table-striped" style="width:100%">
                <thead>
                <tr>
                    <th>기프티콘 번호</th>
                    <th>보상 포인트</th>
                    <th>사용유무</th>
                    <th>사용자</th>
                    <th>생성날짜</th>
                </tr>
                </thead>
            </table>
        </div>
    </jsp:body>


</layout:default>

<script>
    (() => {
        $('#dataTable').DataTable({
            ajax: '/api/v1/gifticon',
            columns : [
                {data: 'serialNumber', render: serialFormat},
                {data: 'amount', render: numberFormat},
                {data: 'use', render: d => d ? '사용' : '미사용'},
                {data: 'useMember.username'},
                {
                    data: 'createdDate',
                    render: data => formatDateTime(data)
                },

            ],
            aaSorting: [],
        });
    })()

    /**
     * 기프티콘을 생성한다.
     */
    function createGifticon(){
        bodyRequest({
            url: '/api/v1/gifticon',
            method: 'POST',
            success: data => {
                console.log(data);
                alert('기프티콘을 생성하였습니다.');
                location.reload();
            }, error: error => {
                alert("기프티콘 생성에 실패하였습니다.")
            }
        })
    }

    function goHome(){
        location.replace('/');
    }
</script>
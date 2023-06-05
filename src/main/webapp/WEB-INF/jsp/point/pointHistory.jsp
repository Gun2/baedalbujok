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
        <tags:Title title="포인트 사용 내역" subTitle="포인트 사용 내역을 확인하세요"/>
        <div class="history" onsubmit="return false;">

            <table id="dataTable" class="table table-striped" style="width:100%">
                <thead>
                <tr>
                    <th>변경 포인트</th>
                    <th>남은 포인트</th>
                    <th>보낸사람/받은사람</th>
                    <th>날짜</th>
                </tr>
                </thead>
            </table>
        </div>
    </jsp:body>


</layout:default>

<script>
    (() => {
        $('#dataTable').DataTable({
            ajax: '/api/v1/point-transaction',
            columns : [
                {data: 'amount', render: numberFormat},
                {data: 'balance', render: numberFormat},
                {data: 'targetPoint.member.name'},
                {
                    data: 'createdDate',
                    render: data => formatDateTime(data)
                },

            ],
            aaSorting: [],
        });
    })()
    function goHome(){
        location.replace('/');
    }
</script>
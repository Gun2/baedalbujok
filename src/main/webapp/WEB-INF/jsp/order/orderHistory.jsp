<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<layout:default>
    <jsp:attribute name="style">
        <style>
            [name=star]{
                cursor: pointer;
            }
            [fill="false"] .bi-star-fill{
                display: none;
            }
            [fill="true"] .bi-star{
                display: none;
            }
            .order{
                display: flex;
                justify-content: center;
                flex-direction: column;
                padding: 50px;
                gap:10px;
                margin:auto;
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
        <tags:Title title="주문 내역" subTitle="주문 내역을 확인하세요"/>
        <div class="order" onsubmit="return false;">

            <table id="dataTable" class="table table-striped" style="width:100%">
                <thead>
                <tr>
                    <th>주문메뉴</th>
                    <th>가격</th>
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
            ajax: '/api/v1/order',
            columns : [
                {data: 'menu.name'},
                {data: 'price'},
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
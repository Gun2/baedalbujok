<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<layout:default>
    <jsp:attribute name="style">
        <style>
            .member{
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
        <tags:Title title="사용자 관리" subTitle="사용자 정보를 확인하세요"/>
        <div class="member" onsubmit="return false;">

            <table id="dataTable" class="table table-striped" style="width:100%">
                <thead>
                <tr>
                    <th>아이디</th>
                    <th>닉네임</th>
                    <th>역할</th>
                    <th>가입날짜</th>
                </tr>
                </thead>
            </table>
        </div>
    </jsp:body>


</layout:default>

<script>
    const roleList = ${roleJsonArray};
    (() => {
        $('#dataTable').DataTable({
            ajax: '/api/v1/member',
            columns : [
                {data: 'username'},
                {data: 'name'},
                {
                    data: 'roleId',
                  render: (roleId, type, data) => roleIdToRoleNameElementMapper(roleId, data)
                },
                {
                    data: 'createdDate',
                    render: data => formatDateTime(data)
                },
            ],
            aaSorting: [],
            fnInitComplete: () => {
                $('select[name=roleSelect]').on('change', e => {
                    const roleId = e.target.value;
                    const memberId = e.target.getAttribute('member-id');
                    if(confirm('역할을 수정하시겠습니까?')){
                        const result = updateAuth(roleId, memberId);
                        if(result.status != 200){
                            cancelSelectBox(e.target);
                        }else{
                            applySelectBox(e.target);
                        }
                    }else {
                        cancelSelectBox(e.target);
                    }

                });
            }
        });
    })();

    function cancelSelectBox(element){
        element.value = element.getAttribute('default-value');
    }

    function applySelectBox(element){
        element.setAttribute('default-value', element.value);
    }

    function updateAuth(roleId, memberId){
        return bodyRequest({
            async: false,
            url: '/api/v1/member-role',
            method: 'PUT',
            data: {
                roleId, memberId
            },
            success: data => {
                console.log(data);
                alert('역할을 수정하였습니다.');
            },error: error => {
                console.error(error);
                const code = error.responseJSON?.code;
                switch (code){
                    case "IMPOSSIBLE_UPDATE_SELF":
                        alert('자기 자신의 역할은 수정할 수 없습니다.')
                        break;
                    default:
                        alert('역할 수정에 실패하였습니다.');
                }
            }
        })
    }

    function roleIdToRoleNameElementMapper(roleId, data){
        return $('<select/>', {
            "class": 'form-control',
            "name": 'roleSelect',
            "member-id": data.id,
            "default-value": roleId,
        }).append(
            roleList.map(role => $('<option/>', {
                text: role.name,
                value: role.id,
                selected: role.id == roleId
            }))
        )[0].outerHTML;
    }

    function goHome(){
        location.replace('/');
    }
</script>
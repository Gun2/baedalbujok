<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<layout:default>
    <jsp:attribute name="style">
        <style>
            .menu-img {
                background-color: #e6e6e6;
                width: 400px;
                height: 300px;
                display: flex;
                justify-content: center;
                align-items: center;
                color: #5b5b5b;
                position: relative;
            }

            .menu-upload-img {
                position: absolute;
                bottom: 5px;
                right: 5px;
                width: 50px;
                cursor: pointer;
                user-select: none;
            }

            .menu-info {
                display: flex;
                flex: 1;
                gap: 5px;
                flex-direction: column;
            }

            .preview {
                max-width: 100%;
                max-height: 100%;
                width: auto;
                height: auto;
            }
        </style>
    </jsp:attribute>
    <jsp:body>
        <c:if test="${mode eq 'CREATE'}">
            <tags:Title title="메뉴 등록" subTitle="메뉴를 등록해 주세요"/>
        </c:if>
        <c:if test="${mode eq 'READ'}">
            <tags:Title title="주문" subTitle="주문할 메뉴를 살펴보세요"/>
        </c:if>
        <c:if test="${mode eq 'UPDATE'}">
            <tags:Title title="메뉴 수정" subTitle="메뉴를 수정해 주세요"/>
        </c:if>
        <div style="padding: 50px">
            <input id="imgUploadInput" style="display: none" type="file" onchange="preview(this)"/>
            <div style="display: flex;justify-content:center;gap:50px;">
                <div>
                    <div class="menu-img">
                        <img class="preview" id="preview"
                                <c:if test="${mode ne 'READ'}">
                                    alt="음식 이미지를 업로드 해주세요"
                                </c:if>
                                <c:if test="${not empty menu.imageName}">
                                    src="/images/upload/<c:out value="${menu.imageName}"/>"
                                </c:if>
                        />
                        <c:if test="${mode ne 'READ'}">
                            <img class="menu-upload-img"
                                 src="/images/server/icon_upload.png"
                                 id="imageUpload"
                            />
                        </c:if>

                    </div>
                </div>
                <form id="form" class="menu-info" onsubmit="return false;">
                    <c:choose>
                        <c:when test="${mode eq 'READ'}">
                            <h1><c:out value="${menu.name}"/></h1>
                            <h3>
                                <fmt:formatNumber type="number" maxFractionDigits="3" value="${menu.price}"/>원
                            </h3>
                            <pre><c:out value="${menu.desc}"/></pre>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" id="id" name="id" value="${menu.id}"/>
                            <input type="text"
                                   id="name"
                                   name="name"
                                   class="form-control"
                                   placeholder="메뉴명을 입력하세요"
                                   value="<c:out value="${menu.name}"/>"
                            />
                            <input type="number"
                                   min="0"
                                   id="price"
                                   name="price"
                                   class="form-control"
                                   placeholder="가격을 입력하세요"
                                   value="<c:out value="${menu.price}"/>"
                            />
                            <textarea class="form-control"
                                      id="desc"
                                      name="desc"
                                      rows="5" placeholder="메뉴 설명을 입력하세요"><c:out value="${menu.desc}"/></textarea>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${mode eq 'CREATE'}">
                        <button class="btn btn-primary" onclick="saveForm()">등록하기</button>
                    </c:if>
                    <c:if test="${mode eq 'READ'}">
                        <button class="btn btn-primary" onclick="doOrder()">주문하기</button>
                        <sec:authorize access="isAuthenticated()">
                            <sec:authentication property="principal.id" var="principalId"/>
                            <sec:authorize access="hasRole('ADMIN')" var="isAdmin"/>

                            <c:if test="${menu.memberId == principalId || isAdmin}">
                                <button class="btn btn-secondary" onclick="goUpdate()">수정하기</button>
                            </c:if>
                        </sec:authorize>
                        <button class="btn btn-warning" onclick="goReview()">리뷰</button>
                        <button class="btn btn-dark" onclick="goBack()">돌아가기</button>
                    </c:if>
                    <c:if test="${mode eq 'UPDATE'}">
                        <button class="btn btn-primary" onclick="updateForm()">수정하기</button>
                        <button class="btn btn-danger" onclick="deleteForm()">삭제하기</button>
                        <button class="btn btn-dark" onclick="goDetail()">돌아가기</button>
                    </c:if>
                </form>
            </div>
        </div>
    </jsp:body>


</layout:default>

<script>
    $('#imageUpload').on('click', () => {
        $('#imgUploadInput').trigger('click')
    });

    function preview(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#preview')
                    .attr('src', e.target.result);
            };
            reader.readAsDataURL(input.files[0]);
        }
    }

    function saveForm() {
        if (confirm('메뉴를 등록하시겠습니까?')) {
            const formData = new FormData(document.getElementById('form'));

            const menuImageFile = document.getElementById('imgUploadInput')?.files[0];
            if (menuImageFile) {
                formData.append("menuImageFile", menuImageFile);
            }
            fileRequest({
                url: '/api/v1/menu',
                method: 'POST',
                data: formData,
                success: (response) => {
                    alert('메뉴를 등록하였습니다.');
                    location.reload();
                    console.log(response);
                }, error: (error) => {
                    console.error(error);
                }
            });
        }
    }

    function updateForm(){
        if (confirm('메뉴를 수정하시겠습니까?')) {
            const formData = new FormData(document.getElementById('form'));

            const menuImageFile = document.getElementById('imgUploadInput')?.files[0];
            if (menuImageFile) {
                formData.append("menuImageFile", menuImageFile);
            }
            fileRequest({
                url: '/api/v1/menu',
                method: 'PUT',
                data: formData,
                success: (response) => {
                    alert('메뉴를 수정하였습니다.');
                    location.reload();
                    console.log(response);
                }, error: (error) => {
                    console.error(error);
                }
            });
        }
    }

    function deleteForm(){
        if (confirm('메뉴를 삭제하시겠습니까?')) {
            bodyRequest({
                url: '/api/v1/menu/${menu.id}',
                method: 'DELETE',
                success: (response) => {
                    alert('메뉴를 삭제하였습니다.');
                    goBack();
                    console.log(response);
                }, error: (error) => {
                    console.error(error);
                }
            });
        }
    }

    function doOrder(){
        <sec:authorize access="isAuthenticated()">
            if(confirm('주문하시겠습니까?')){
                bodyRequest({
                    url: '/api/v1/order-menu/${menu.id}',
                    method: 'POST',
                    success: data => {
                        console.log(data);
                        alert('주문을 완료하였습니다.')
                        location.reload();
                    },error: error => {
                        console.error(error);
                        if(error.responseJSON.code == "NOT_ENOUGH_POINT"){
                            alert("포인트가 부족합니다.");
                        }else{
                            alert('주문에 실패하였습니다.')
                        }
                    }
                })
            }
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            location.replace('/login?redirect=' + location.pathname);
        </sec:authorize>

    }
    function goReview(){
        location.replace('/review/menu/${menu.id}');
    }

    function goUpdate(){
        location.replace('/menu/form/${menu.id}');
    }

    function goDetail(){
        location.replace('/menu/order/${menu.id}');
    }

    function goBack(){
        location.replace('/order');
    }
</script>
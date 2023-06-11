<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<layout:default>
    <jsp:attribute name="style">
        <style>
            .search-area {
                display: flex;
                justify-content: space-around;
                gap: 50px;
                align-items: center;
            }

            .review-area {
                display: flex;
                flex-direction: column;
                margin: 50px 0;
            }

            .review-info {
                display: flex;
                justify-content: space-between;
            }
        </style>
    </jsp:attribute>
    <jsp:body>
        <tags:Title title="리뷰" subTitle="다른 사람의 리뷰 내용을 확인하세요"/>
        <div style="padding: 50px">
            <div class="search-area">
                <form class="input-group"
                      style="margin:0"
                >
                    <span class="input-group-text">
                        <i class="bi bi-search"></i>
                    </span>
                    <input type="text"
                           name="memberName"
                           id="name"
                           value="<c:out value="${search.memberName}"/>"
                           class="form-control" placeholder="작성자를 입력하세요"/>
                    <button class="btn btn-primary">검색</button>
                </form>
                <div style="display: flex; gap: 5px; width:300px; flex-direction: row-reverse;">
                    <button class="btn btn-dark"
                            onclick="goMenu(<c:out value="${search.menuId}"/>)"
                    >돌아가기
                    </button>
                    <button class="btn btn-secondary"
                            style="width: 100px;"
                            onclick="goReviewInsert(<c:out value="${search.menuId}"/>)"
                    >리뷰등록
                    </button>
                </div>
            </div>
            <div class="review-area">
                <c:forEach items="${reviewList}" var="review">
                    <div>
                        <%--사용자 이름--%>
                        <h1><c:out value="${review.memberName}"/></h1>
                        <div class="review-info">
                            <div class="d-flex align-items-center small text-warning mb-2">
                                <c:forEach var="i" begin="20" end="100" step="20">
                                    <c:choose>
                                        <c:when test="${i <= review.evaluation}">
                                            <div class="bi-star-fill"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="bi-star"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <text style="color:#000;margin-left: 5px;">(${review.evaluation / 20})</text>
                            </div>
                            <div>
                                <fmt:formatDate value="${review.updatedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </div>
                        </div>
                        <div>
                            <c:if test="${not empty review.imageName}">
                                <img src="/images/upload/<c:out value="${review.imageName}"/>"/>
                            </c:if>
                        </div>
                        <%--리뷰 내용--%>
                        <pre>${review.content}</pre>
                    </div>
                    <hr/>
                </c:forEach>
            </div>
        </div>
    </jsp:body>


</layout:default>

<script>

    function goMenu(menuId) {
        location.replace(`/menu/order/\${menuId}`);
    }

    function goReviewInsert(menuId) {
        <sec:authorize access="isAnonymous()">
        location.replace(`/login?redirect=/review/insert/\${menuId}`);
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
        location.replace(`/review/insert/\${menuId}`);

        </sec:authorize>
    }
</script>
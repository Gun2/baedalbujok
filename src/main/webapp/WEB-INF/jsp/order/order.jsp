<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<layout:default>
    <header class="bg-dark py-2">
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active text-center">
                    <img style="width:70%" src="/images/server/ad01.png"/>
                </div>
                <div class="carousel-item text-center">
                    <img style="width:70%" src="/images/server/ad01.png"/>
                </div>
                <div class="carousel-item text-center">
                    <img style="width:70%" src="/images/server/ad01.png"/>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </header>
    <section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <c:forEach items="${menuList}" var="menu">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <!-- Product image-->
                            <div style="height: 200px; display: flex">
                                <img class="card-img-top" src="/images/upload/<c:out value="${menu.imageName}"/>" alt="">
                            </div>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">
                                        <c:out value="${menu.name}"/>
                                    </h5>
                                    <c:if test="${menu.reviewCnt > 0}">
                                        <c:set var="average" value="${menu.evaluationSum / menu.reviewCnt}"/>
                                        <div class="d-flex justify-content-center align-items-center small text-warning mb-2">
                                            <c:forEach var="i" begin="20" end="100" step="20">
                                                <c:choose>
                                                    <c:when test="${i <= average}">
                                                        <div class="bi-star-fill"></div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="bi-star"></div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <text style="margin-left: 5px;">(${menu.reviewCnt})</text>
                                        </div>

                                    </c:if>

                                    <!-- Product price-->
                                    <text>
                                        <fmt:formatNumber type="number" maxFractionDigits="3" value="${menu.price}"/>원
                                    </text>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center">
                                    <a class="btn btn-outline-dark mt-auto"
                                       href="#"
                                       onclick="order('<c:out value="${menu.id}"/>')">
                                        주문하기
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</layout:default>

<script>

    function order(id){
        location.replace(`/menu/order/\${id}`);
    }

</script>

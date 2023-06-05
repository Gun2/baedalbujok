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
            .review{
                display: flex;
                justify-content: center;
                flex-direction: column;
                width: 600px;
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
        <tags:Title title="리뷰 등록" subTitle="리뷰를 등록해주세요"/>
        <form id="form" class="review" onsubmit="return false;">
            <input type="hidden" name="menuId" value="${menuId}">
            <div class="evaluation-area">
                <div class="d-flex justify-content-center align-items-center small text-warning mb-2"
                     style="font-size: 60px;">
                    <input id="evaluation" name="evaluation" type="hidden" value="0">
                    <div name="star" value="1" fill="false">
                        <div class="bi-star"></div>
                        <div class="bi-star-fill"></div>
                    </div>
                    <div name="star" value="2" fill="false">
                        <div class="bi-star"></div>
                        <div class="bi-star-fill"></div>
                    </div>
                    <div name="star" value="3" fill="false">
                        <div class="bi-star"></div>
                        <div class="bi-star-fill"></div>
                    </div>
                    <div name="star" value="4" fill="false">
                        <div class="bi-star"></div>
                        <div class="bi-star-fill"></div>
                    </div>
                    <div name="star" value="5" fill="false">
                        <div class="bi-star"></div>
                        <div class="bi-star-fill"></div>
                    </div>
                </div>
            </div>
            <div class="photo-area">
                <input id="imgUploadInput" style="display: none" type="file" onchange="onChangeFileInput(this);"/>
                <div style="text-align:center;">
                    <img class="preview" id="preview"
                    />
                </div>
                <button class="btn btn-secondary" id="uploadButton">사진 첨부하기</button>
            </div>
            <div class="content-area">
                <textarea id="content" name="content" class="form-control" ROWS="14"></textarea>
            </div>
            <button class="btn btn-primary" onclick="saveForm()">리뷰등록</button>
            <button class="btn btn-dark" onclick="goReview()">돌아가기</button>
        </form>
    </jsp:body>


</layout:default>

<script>
    (() => {
        bindStar();
    })()
    $('#uploadButton').on('click', () => {
        $('#imgUploadInput').trigger('click')
    });

    function onChangeFileInput(input) {
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
        if (confirm('리뷰를 등록하시겠습니까?')) {
            const formData = new FormData(document.getElementById('form'));

            const imageFile = document.getElementById('imgUploadInput')?.files[0];
            if (imageFile) {
                formData.append("imageFile", imageFile);
            }
            fileRequest({
                url: '/api/v1/review',
                method: 'POST',
                data: formData,
                success: (response) => {
                    alert('리뷰를 등록하였습니다.');
                    console.log(response);
                    goReview();
                }, error: (error) => {
                    console.error(error);
                }
            });
        }

    }

    function bindStar(){
        $('[name=star]').on('click' , e => {
           let index = e.target.parentElement.getAttribute('value');
           //같은 별 위치를 두 번 클릭한 경우 모두 지우기
           index = index * 20 == $("#evaluation").val() ? 0 : index;
           $("#evaluation").val(index * 20);
           for(var i = 1; i <= 5; i ++){
                $(`[name=star][value="\${i}"]`).attr('fill', i <= index);
           }
        });
    }

    function goReview(){
        location.replace('/review/menu/${menuId}');
    }
</script>
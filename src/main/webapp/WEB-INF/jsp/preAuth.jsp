<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<layout:default>
    <form id="authForm" action="/pre-auth" method="post" onsubmit="">
        <div class="flex-center-col"
             style="margin: 50px auto;
             width: 500px;
             gap:5px;display: flex;
             flex-direction: column;
             justify-content: space-around;
             height: 800px;
             align-items: center;"
        >
            <h1 style="font-size:70px;">사전인증번호입력</h1>
            <div class="input-group" style="width: 100%">
            <span class="input-group-text">
                <i class="bi bi-key-fill"></i>
            </span>
                <input type="text"
                       name="preAuth"
                       id="preAuth"
                       onkeydown=""
                       class="form-control" placeholder="사전 인증 번호를 입력하세요."
                       style="width: 100%;
                       height: 50px;
                       font-size: 30px;
                        "
                />
            </div>
            <button
                    class="btn btn-primary"
                    onclick=""
                    style="width:100%;height: 50px; font-size: 30px;">사전인증
            </button>
        </div>
    </form>


    <script>

        function getUriQueryMap(){
            return location.search.replaceAll('?', '').split('&')
                .reduce((map, q) => {
                    querys = q.split('=');
                    map.set(querys[0], querys[1]);
                    return map;
                }, new Map())
        }

        (() => {
            const queryMap = getUriQueryMap();
            if(queryMap.has('error')){
                const error = queryMap.get('error');
                if(error == 'notMatched'){
                    alert('일치하지 않습니다.');
                }
                location.replace('/');
            }
        })()
    </script>
</layout:default>

<%@ page contentType="text/html; charset=UTF-8" language="java"
        pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>채팅방 입장 및 리스트</title>
    <link rel="stylesheet" href="/css/table.css"/>
    <script type="text/javascript" src="/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">

        // HTML 로딩이 완료되고 실행됨
        $(document).ready(function () {

            // 화면 로딩이 완료되면 첫 번째로 실행함
            getRoomList(); // 전체 채팅방 리스트 가져오기

            // 2번째 부터 채팅방 전체 리스트를 5초마다 로딩함
            setInterval("getRoomList()", 5000);

        })

        // 전체 채팅방 리스트 가져오기
        function getRoomList() {

            // Ajax 호출
            $.ajax({
                url: "/chat/roomList",
                type: "post",
                dataType: "JSON",
                success: function (json) {

                    // 기존 데이터 삭제하기
                    $("#room_List").empty();

                    for (let i = 0; i < json.length; i++) {
                        $("#room_list").append(json[i] + "<br/>"); // 채팅방마다 한줄씩 추가

                    }
                }
            })
        }
    </script>
</head>
<body>
<h1>채팅방 전체 리스트</h1>
<div class="divTable minimalistBlack">
    <div class="divTableHeading">
        <div class="divTableRow">
            <div class="divTableHead">대화 가능한 채팅방들</div>
        </div>
    </div>
    <div class="divTableBody">
        <div class="divTableRow">
            <div class="divTableCell" id="room_list"></div>
        </div>
    </div>
</div>
<br/><br/>
<h1>채팅방 입장 정보</h1>
<hr/>
<br/><br/>
<form name="f" id="f" method="post" action="chat/chatroom">
    <div class="divTable minimalistBlack">
        <div class="divTableBody">
            <div class="divTableRow">
                <div class="divTableCell">채팅방 이름</div>
                <div class="divTableCell">
                    <input type="text" name="roomName">
                </div>
                <div class="divTableCell">대화방(별명)</div>
                <div class="divTableCell">
                    <input type="text" name="userName">
                </div>
            </div>
        </div>
    </div>
    <div>
        <button>입장하기</button>
    </div>
</form>
</body>
</html>

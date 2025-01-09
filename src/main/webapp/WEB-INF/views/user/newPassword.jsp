<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.dto.UserInfoDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
    UserInfoDTO rDTO = (UserInfoDTO) request.getAttribute("rDTO");

    // 비밀번호 재설정 접속 가능한지 체크
    String newPassword = CmmUtil.nvl((String) session.getAttribute("NEW_PASSWORD"));

    String msg = "";

    if (CmmUtil.nvl(rDTO.getUserId()).length() > 0) {

        if (newPassword.length() == 0) {
            msg = "비정상적인 접근입니다. \n비밀번호 재설정 화면에 접근할 수 없습니다.";

        }
    } else {
        msg = "회원정보가 존재하지 않습니다.";

    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%=CmmUtil.nvl(rDTO.getUserName())%>회원님의 비밀번호 재설정</title>
    <link rel="stylesheet" href="/css/table.css"/>
    <script type="text/javascript" src="/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">

        <%
        // 비정상적인 접근 및 회원정보가 없는 경우 뒤로 가기
        if (msg.length()>0){
        %>
        alert("<%=msg%>");
        history.back();
        <%
        }
        %>

        //HTML 로딩이 완료되고 실행됨
        $(document).ready(function () {

            // 로그인 화면 이동
            $("#btnLogin").on("click", function () {
                location.href = "/user/login";
            })
            // 비밀번호 찾기
            $("btnSearchPassword").on("click", function () {
                let f = document.getElementById("f");

                if (f.password.value === "") {
                    alert("새로운 비밀번호를 입력하세요.");
                    f.password.focus();
                    return;
                }

                if (f.password2.value === "") {
                    alert("검증을 위한 새로운 비밀번호를 입력하세요.");
                    f.password2.focus();
                    return;
                }

                if (f.password.value !== f.password2.value) {
                    alert("입력한 비밀번호가 일치하지 않습니다.")
                    f.password.focus();
                    return;
                }

                f.method = "post";
                f.action = "/user/newPasswordProc"

                f.submit();
            })
        })
    </script>
</head>
<body>
<h2><%=CmmUtil.nvl(rDTO.getUserName())%>회원님의 비밀번호 재설정</h2>
<hr/>
<br/>
<form id="f">
    <div class="divTable minimalistBlack">
        <div class="divTableBody">
            <div class="divTableRow">
                <div class="divTableCell">새로운 비밀번호
                </div>
                <div class="divTableCell">
                    <input type="password" name="password" id="password" style="width: 95%"/>
                </div>
            </div>
            <div class="divTableRow">
                <div class="divTableCell">검증을 위한 새로운 비밀번호
                </div>
            </div>
            <div class="divTableCell">
                <input type="password" name="password2" id="password2" style="width: 95%"/>
            </div>
        </div>
    </div>
    <div>
        <button id="btnSearchPassword" type="button">비밀번호 재설정</button>
        <button id="btnLogin" type="button">로그인</button>
    </div>
</form>
</body>
</html>

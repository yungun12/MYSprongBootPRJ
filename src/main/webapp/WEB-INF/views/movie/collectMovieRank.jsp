<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
  // Controller 부터 전달받은 메세지
  String msg = CmmUtil.nvl((String) request.getAttribute("msg"));
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <title>CGV 영화 수집 결과</title>
</head>
<body>
<%=msg%>
</body>
</html>

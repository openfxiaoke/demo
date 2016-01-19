<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>第三方应用跳转绑定账号</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${pageContext.request.contextPath}/res/css.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/res/JSONFormatter.css" rel="stylesheet">
	<script type="text/javascript" src="http://lib.sinaapp.com/js/jquery/1.9.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/res/JSONFormatter.js" type="text/javascript"></script>
</head>
<body>
<form class="ns" style="padding-top:30px">
	<a href="${pageContext.request.contextPath}/display/bindAccount" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:80px;background:#269CE9;color:#FFF">绑定用户</a>
	<a href="${pageContext.request.contextPath}/display/addressBook" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:80px;background:#269CE9;color:#FFF">通讯录</a>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>第三方应用跳转绑定账号</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css"  href="<c:url value='/styles/css.css'/>" >
</head>
<body>
<form class="ns" style="padding-top:30px">
	<a href="<c:url value='/view/bindAccount'/>" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:80px;background:#269CE9;color:#FFF">绑定用户</a>
	<a href="<c:url value='/view/addressBook'/>" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:80px;background:#269CE9;color:#FFF">通讯录</a>
</form>
</body>
</html>
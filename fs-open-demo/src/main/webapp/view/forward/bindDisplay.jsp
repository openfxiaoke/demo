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
<form action="${bindUri}" method="GET" class="ns" style="padding-top:5px" id="form2" target="_blank">
	<p>
		<b><span class="form_label" style="width:140px;">appId：</span></b>
		<input type="text" id="appId" name="appId" title="appId" readonly alt="appId" value="${appId}" style="width: 500px;"/>
	</p>
	<p>
		<b><span class="form_label" style="width:140px;">redirectUri：</span></b>
		<input type="text" id="redirectUri" name="redirectUri" readonly title="redirectUri" alt="redirectUri" value="${redirectUri}" style="width: 380px;"/>
	</p>
	<p>
		<b><span class="form_label" style="width:140px;">responseType：</span></b>
		<input type="text" id="responseType" name="responseType" readonly title="responseType" alt="responseType" value="code" style="width: 200px;"/>
	</p>
	<p>
		<b><span class="form_label" style="width:140px;">跳转目标：</span></b>
		<input type="radio" name="scope" title="scope" alt="scope" value="login_bind" style="width:60px" checked /> 登录
		<input type="radio" name="scope" title="scope" alt="scope" value="register_bind" style="width:60px" /> 注册
	</p>
	<p>
		<b><span class="form_label" style="width:140px;">state：</span></b>
		<input type="text" id="state" name="state" title="state" readonly alt="state" value="${tempState }" style="width: 200px;"/>
	</p>
	<p>
		<input type="button" id="app_token_btn" class="form_button" style="margin-left: 15px; width:80px;" value="绑定" />
	</p>
</form>
    <script type="text/javascript">
    	$(function() {
    		$("#app_token_btn").click(function() {
    			$("#form2").get(0).submit();
    		});
    	});
	</script>
</body>
</html>
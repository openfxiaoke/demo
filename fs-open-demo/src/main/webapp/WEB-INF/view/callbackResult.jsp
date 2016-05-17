<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset==UTF-8">
	<link rel="stylesheet" type="text/css"  href="<c:url value='/styles/JSONFormatter.css'/>" >
	<script type="text/javascript" src="<c:url value='/scripts/common/common.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/jquery/jquery-1.8.2.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/JSONFormatter.js'/>" ></script>
	<title>Insert title here</title>
</head>
<body>
第三方账号绑定 : ${message} <br/>
绑定用户的openUserId为: ${openUser.errorCode ==0 ? openUser.openUserId:'未获取到用户'}
<br />
<input type="button" id="http_test" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:150px;background:#269CE9;color:#FFF" value="获取用户个人信息" />
<input type="button" id="send_test" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:150px;background:#269CE9;color:#FFF" value="发送文本消息" />

	<div style="border:1px solid #D8D8B2;background-color:#EEE;padding:10px">
		<div id="time_output" class="green"></div>
		<div id="output" style="display:block;word-break: break-all;word-wrap: break-word;"></div>
	</div>
<script>

	function getContextPath(){
		   return "${pageContext.request.contextPath}";
	}
	
	function format(json){
		   $("#output").html("");
		   if(json==null||json==""){
			   json={"msg":"没有数据"}
		   }
		   JSONFormatter.format(json, {'appendTo' : '#output', 'collapse' : true, 'list_id' : 'json' });
	}
	
	function getUserResultHandler(result){
    	format(result);
	}
	
	function sendMessageResultHandler(result){
		format(result);
	}
	
	$(function() {
		var openUserIds=new Array();
		openUserIds[0]="${openUser.openUserId}";
		if('${openUser.errorCode}' == '0') {
			// 获取 用户个人信息按钮
			$("#http_test").click(function() {
				var url = getContextPath()+'/addressBook/user/getInfo/'+openUserIds;
				ajaxAsyncGet(url,"",getUserResultHandler);
			});
			
			// 用户发送短信按钮
			$("#send_test").click(function() {
				var url = getContextPath()+'/message/send';
				ajaxAsyncPost(url,{"msgContent":"发送消息测试","openUserIds":openUserIds},sendMessageResultHandler);
			});
		}
	})
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset==UTF-8">
	<link href="${pageContext.request.contextPath}/res/css.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/res/JSONFormatter.css" rel="stylesheet">
	<script type="text/javascript" src="http://lib.sinaapp.com/js/jquery/1.9.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/res/JSONFormatter.js" type="text/javascript"></script>
	<title>Insert title here</title>
</head>
<body>
第三方账号绑定 : ${message} <br/>
绑定用户的openUserId为: ${findstatus == '0' ? bindUser.openUserId:'未获取到用户'}
<br />
<input type="button" id="http_test" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:150px;background:#269CE9;color:#FFF" value="获取用户个人信息" />
<input type="button" id="send_test" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:150px;background:#269CE9;color:#FFF" value="发送文本消息" />
<input type="button" id="send_template_test" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:150px;background:#269CE9;color:#FFF" value="发送模板消息" />

	<div style="border:1px solid #D8D8B2;background-color:#EEE;padding:10px">
		<div id="time_output" class="green"></div>
		<div id="output" style="display:block;word-break: break-all;word-wrap: break-word;"></div>
	</div>
<script>
	$(function() {
		if('${findstatus}' == '0') {
			// 获取 用户个人信息按钮start
			$("#http_test").click(function() {
				var url_send = '${pageContext.request.contextPath}/user/getInfo';
				$.ajax({
			        type: "POST",
			        url: url_send,
			        data:{'openUserId':'${bindUser.openUserId}'}, 
			        success: function(data) {
							var content = data.rs;
							if (typeof(content) == 'object') {
								content = JSON.stringify(content);
							}
							
							$('#time_output').html('<span class="red">执行时间：</span>' + data.time + '<br /><span class="red">返回状态码：</span>【' + data.code + '】<br /><span class="red">返回结果如下：</span><br /><hr />');
							var json = null;
							try { 
								json =  JSON.parse(content);
							} catch (e) {
								json = null;
								
							}
							if (json == null) {
								$('#output').append("<pre></pre>");
								$('#output pre').text(content);
							}
							else {
								$("#output").html("");
								JSONFormatter.format(json, {'appendTo' : '#output', 'collapse' : true, 'list_id' : 'json' });
							}
			          }
			    });
			});
			// 获取 用户个人信息按钮end
			
			// 用户发送短信按钮start
			$("#send_test").click(function() {
				var url_send = '${pageContext.request.contextPath}/message/send';
				$.ajax({
			        type: "POST",
			        url: url_send,
			        data:{msgContent:'Test 中文',openUserIds:'${bindUser.openUserId}'}, 
			        success: function(data) {
							var content = data.rs;
							if (typeof(content) == 'object') {
								content = JSON.stringify(content);
							}
							
							$('#time_output').html('<span class="red">执行时间：</span>' + data.time + '<br /><span class="red">返回状态码：</span>【' + data.code + '】<br /><span class="red">返回结果如下：</span><br /><hr />');
							var json = null;
							try { 
								json =  JSON.parse(content);
							} catch (e) {
								json = null;
								
							}
							if (json == null) {
								$('#output').append("<pre></pre>");
								$('#output pre').text(content);
							}
							else {
								$("#output").html("");
								JSONFormatter.format(json, {'appendTo' : '#output', 'collapse' : true, 'list_id' : 'json' });
							}
			          }
			    });
			});
			// 用户发送短信按钮end
			// 给用户发送模板消息按钮start
			$("#send_template_test").click(function() {
				var url_send = '${pageContext.request.contextPath}/message/sendTemplate';
				$.ajax({
			        type: "POST",
			        url: url_send,
			        data:{openUserIds:'${bindUser.openUserId}'},
			        success: function(data) {
							var content = data.rs;
							if (typeof(content) == 'object') {
								content = JSON.stringify(content);
							}
							
							$('#time_output').html('<span class="red">执行时间：</span>' + data.time + '<br /><span class="red">返回状态码：</span>【' + data.code + '】<br /><span class="red">返回结果如下：</span><br /><hr />');
							var json = null;
							try { 
								json =  JSON.parse(content);
							} catch (e) {
								json = null;
								
							}
							if (json == null) {
								$('#output').append("<pre></pre>");
								$('#output pre').text(content);
							}
							else {
								$("#output").html("");
								JSONFormatter.format(json, {'appendTo' : '#output', 'collapse' : true, 'list_id' : 'json' });
							}
			          }
			    });
			});
			// 给用户发送模板消信按钮end
		}
	})
</script>
</body>
</html>
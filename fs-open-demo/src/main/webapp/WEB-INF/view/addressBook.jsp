<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset==UTF-8">
	<link rel="stylesheet" type="text/css"  href="<c:url value='/styles/JSONFormatter.css'/>" >
	<script type="text/javascript" src="<c:url value='/scripts/common/common.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/jquery/jquery-1.8.2.min.js'/>"></script>
	<SCRIPT type="text/javascript" src="<c:url value='/scripts/jquery/jquery.json-2.2.js'/>"></SCRIPT>
	<script type="text/javascript" src="<c:url value='/scripts/JSONFormatter.js'/>" ></script>
	<title>通讯录</title>
</head>
<body>
<br />
<input type="button" id="deptList_test" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:150px;background:#269CE9;color:#FFF" value="获取部门列表" />
<input type="button" id="allUser_test" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:150px;background:#269CE9;color:#FFF" value="获取公司人员信息" />
<input type="button" id="addDept_test" class="form_button" style="padding:8px;font: 13.3333px Arial;margin-left: 15px;border:1px solid;height:33px;cursor: pointer; width:150px;background:#269CE9;color:#FFF" value="添加部门" />
<br/>
<div style="border:1px solid #D8D8B2;background-color:#EEE;padding:10px">
	<div id="add-depart-div" style="display:none">
            	<h3>添加部门</h3>
                <div>
                <form id="add-depart-frm" method="post">
                               <table> 
                                		<tr> 
                                			<td>部门名称：</td> 
                                			<td><input name="name" id="name" maxlength="16" style="width:150px;"></td>
                                		</tr>
                                		<tr>
                                			<td>父&nbsp;部&nbsp;门：</td> 
                                			<td>
													<select name="parentId" id="parentId" style="width:150px;">
             										</select>
											</td>
                                		</tr>
                                		<tr>
                                			<td>排&nbsp;&nbsp;&nbsp;&nbsp;序：</td> 
											<td><input name="order" id="order" maxlength="16" style="width:150px;"></td>
                                		</tr>
                                		<tr>
                                			<td colspan="2" align="center"><a href="#"  onclick="addDepart()">保存</a></td>
                                		</tr>
                                </table>
                   </form>
                </div>
      </div>
	
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

    function getDepartmentListResultHandler(result){
    	var json;
    	if(result.errorCode==0){
    		json=result.departments;
    	}else{
    		json=result;
    	}

    	format(json);
    }
    
    function getCorpUserMapResultHandler(result){
    	var json;
    	if(result.errorCode==0){
    		json=result.corpUserMap;
    	}else{
    		json=result;
    	}
    	
    	format(json);
    }
    
    function addDepartmentResultHandler(result){
    	format(result);
    	initDepartmentSelect();
    }
    
    function showAddDepartment(){
    	$("#add-depart-div").show();
    }
    
 	function hideAddDepartment(){
 		$("#add-depart-div").hide();
    }
 	
 	function initDepartmentSelect(){
 		var url = getContextPath()+'/addressBook/dept/list';
		ajaxAsyncGet(url,Math.round(new Date().getTime()/1000),function(result){
			if(result.errorCode==0){
				var departments=result.departments;
				var len=departments.length;
		 		$("#parentId").html("");
		 		for(var i=0;i<len;i++){
		 			var item=departments[i];
		 			$("#parentId").append("<option value='"+item.id+"'>"+item.name+"</option>");
		 		}
	    	}
		});
 	}
    
	$(function() {
		//获取 部门列表信息按钮
		$("#deptList_test").click(function() {
			hideAddDepartment();
			var url = getContextPath()+'/addressBook/dept/list';
			ajaxAsyncGet(url,Math.round(new Date().getTime()/1000),getDepartmentListResultHandler);
		});

		// 获取公司人员信息按钮
		$("#allUser_test").click(function() {
			hideAddDepartment();
			var url = getContextPath()+'/addressBook/user/corpUserMap';
			ajaxAsyncGet(url,Math.round(new Date().getTime()/1000),getCorpUserMapResultHandler);
		});

		//添加部门
		$("#addDept_test").click(function() {
			showAddDepartment();
			$("#output").html("");
			initDepartmentSelect();
		});
	})
	
	function addDepart(){
		var url = getContextPath()+'/addressBook/dept/add';
		var department = $("#add-depart-frm").serializeObject();
		ajaxAsyncPost(url,department,addDepartmentResultHandler);
	}
</script>
</body>
</html>
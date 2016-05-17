function showMessage(message) {
	alert(message);
}

function showErrorMessage(message) {
	message=(message==null||message=="")?"系统异常":message;
	showMessage(message);
} 

function ajaxRequest(url,method,data,callback){
	 $.ajax({  
	        type: method,
	        url:url,
	        data:JSON.stringify(data), 
	        contentType: "application/json; charset=utf-8",  
	        dataType: "json",  
	        success:callback,  
	        error:function(data){  
	        	showErrorMessage(data.responseText);  
	        }});
}

function ajaxAsyncPost(url,data,callback){
	ajaxRequest(url,"post",data,callback);
}

function ajaxAsyncPut(url,data,callback){
	ajaxRequest(url,"put",data,callback);
}

function ajaxAsyncGet(url,data,callback){
	ajaxRequest(url,"get",data,callback);
}

function ajaxAsyncDel(url,data,callback){
	ajaxRequest(url,"delete",data,callback);
}

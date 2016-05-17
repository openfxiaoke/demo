# demo
纷享开放平台接入应用的示例demo

###一、部署说明
####1.修改src/main/resources/client.properties 配置文件中的信息为自己申请的信息, 包括appId,appSecret等
####2.运行pom.xml 进行打包
####3.将打包好的war文件(target/openDemo.war)拷贝到tomcat/webapps 中启动tomcat
####4.在浏览器中输入http://{ip}:{port}/openDemo/  访问demo应用
 
###二、接口说明
####1.纷享平台web端或App端跳转到第三方应用入口，第三方应用接收身份态和获取跳转人员信息的入口
FSEndpointController.handle();
####2.第三方应用接收和解析开平推送消息的入口
FSCallbackController.decode();
####3.第三方应用跳转纷享登录/注册成功后接收身份态进行账号绑定的入口
AAAController.login()、AAAController.register();
####4.通过openUserId 换取成员信息的入口
AddressBookController.getUserInfo();
####5.获取公司部门列表入口
AddressBookController.getDeptList();
####6.获取公司人员/部门人员列表入口
AddressBookController.getCorpUserMap();
####7.增加部门入口
AddressBookController.addDept();
####8.修改部门入口
AddressBookController.modifyDept();
####9.发送文本消息入口
MessageController.sendTetxMessage();
####10.通过appId+appSecret换取appAccessToken的入口
OpenAPIUtils.getAppToken();
####11.通过appAccessToken+permanentCode获取corpAccessToken的入口
OpenAPIUtils.getCorpToken();

###三、FAQ
####1.javax.net.ssl.SSLHandshakeException: Received fatal alert: handshake_failure  
接入CGI接口时 demo会报“javax.net.ssl.SSLHandshakeException: Received fatal alert: handshake_failure”, 请安装JCE无限制权限策略文件;  
JDK6的下载地址: http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html  
JDK7的下载地址: http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html  
JDK8的下载地址: http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html  
下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt。如果安装的是JRE，将两个jar文件放到%JRE_HOME% \lib\security目录下覆盖原来的文件，如果安装的是JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件  




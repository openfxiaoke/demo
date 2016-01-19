一、部署说明
1.修改src/main/resources/client.properties 配置文件中的信息为自己申请的信息
2.运行pom.xml 进行打包
3.将打包好的war文件(target/openDemo.war)拷贝到tomcat/webapps 中启动tomcat
4.在浏览器中输入http://{ip}:{port}/openDemo/  访问demo应用
 
二、接口说明
1.App端跳转到第三方，第三方接收身份态和获取跳转人员信息的入口
ThirdForwardController.handleAppForward()方法
2.第三方应用接收和解析开平推送消息的入口
ThirdForwardController.parseReceiveMsg();
3.第三方应用跳转纷享登录/注册成功后接收身份态进行账号绑定的入口
ThirdForwardController.processCallBack();
4.通过openUserId 换取成员信息的入口
ThirdForwardController.getUserInfo();
5.获取公司部门列表入口
ThirdForwardController.getDeptList();
6.获取公司人员/部门人员列表入口
ThirdForwardController.getCorpUserMap();
6.发送文本消息入口
ThirdForwardController.sendTetxMessage();
7.发送模板消息入口
ThirdForwardController.sendTemplateMessage();
8.通过appId+appSecret换取appAccessToken的入口
OpenAPIUtils.getAppToken();
9.通过appAccessToken+permanentCode获取corpAccessToken的入口
OpenAPIUtils.getCorpToken();
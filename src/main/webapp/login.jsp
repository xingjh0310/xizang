<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>西藏物资管理系统</title>
	<%@include file="../../../common/inc/inc.inc"%>
	<%@include file="../../../common/inc/bootstrapTable.inc"%>
    <link rel="stylesheet" href="<%=basePath %>common/assets/css/style.css">
    <script type="text/javascript" src="<%=basePath %>common/qrcode/jquery.qrcode.js"></script>
	<script type="text/javascript" src="<%=basePath %>common/qrcode/qrcode.js"></script>
</head>
<body>
	<div class="container-fluid">
		<img src="<%=basePath%>common/assets/img/startpage.png" width="100%" 
			height="100%" class="img-responsive" alt="西藏电网建设工程物资管理系统"/>
		<div class="row" id="first">
			<div class="col-md-12  col-lg-12 col-sm-12 col-xs-12">
				<div class="row">
					<div class="col-md-4 col-lg-4 col-sm-6 col-xs-6">
						<div class="row">
							<img src="<%=basePath%>common/assets/img/LOGO.png" alt="西藏电网建设工程物资管理系统">
                            <p>西藏电网建设工程物资管理系统</p>
						</div>
					</div>
					<div class="col-md-4 col-lg-4 hidden-sm hidden-xs"></div>
					<div class="col-md-4 col-lg-4 col-sm-6 col-xs-6">
						<span>工程项目</span>
		     	 		<span>物资运输</span>
		     	 		<span>管理物资</span>
		     	 		<span>项目管理</span>
		     	 		<span>数据可视化管理</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row" id="row-second">
			<div class="col-md-6 col-lg-6 col-sm-6 col-xs-6">
				<h2>最安全的项目工程 最稳妥的管理方式</h2>
		     	<h4>西藏电网工程建设是为了加快西藏电力工业建设，促进西藏自治区经济社会发展。</h4>
		     	<h4>为偏远山区提供能源，为人民建设而拼搏</h4>
		     	<h4>效率同比提高30%</h4>
			</div>
			<div class="col-md-6 col-lg-6 col-sm-6 col-xs-6">
				<div class="login-right">
					<img src="<%=basePath%>common/assets/img/LOGO2.png" alt="">
					<h3 class="text-center">西藏电网建设工程物资管理系统</h3>
					<ul class="nav nav-tabs">
						<li class="active">
							<a href="###" data-target="#tab2Content1" data-toggle="tab">用户登录</a>
						</li>
						<li>
							<a href="###" data-target="#tab2Content2" data-toggle="tab">手机版<img src="<%=basePath %>common/assets/img/erweimajiao.png" id="erweimaimg" alt=""></a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane fade active in"  id="tab2Content1">
							<form action="<%=basePath%>login/system!login.do" method="post" id="loginForm">
								<input type="hidden" id="dictnmDllx" name="loginfo.dictnmDllx" value="account"/>
								<input type="hidden" id="dictnmZhlx" name="loginfo.dictnmZhlx" value="system"/>
								<div class="input-control has-icon-left">
									<input id="inputAccountExample1" name="loginfo.name" type="text" 
									class="form-control" autofocus="autofocus" placeholder="用户名"/>
									<label for="inputAccountExample1" class="input-control-icon-left"><img id="zhanghu" src="<%=basePath %>common/assets/img/accout.png" alt=""></label>
								</div>
								<div class="input-control has-icon-right">
									<label for="inputPasswordExample1" class="input-control-icon-right"><img id="mima" src="<%=basePath %>common/assets/img/pwd.png" alt=""></label>
									<input id="inputPasswordExample1" name="loginfo.pwd"
									type="password" class="form-control" placeholder="密码"/>
								</div>
								<button type="button" onclick="loginBtn()" id="btn-p" class="btn btn-primary">登&nbsp;&nbsp;录</button>
			                </form>
			                <div id="banben">版本号1.0.2</div>
						</div>
						<div class="tab-pane fade"  id="tab2Content2">
							<div id="qrcodeTable" style="height:164px;width:164px;margin-left:70px;" >
				    
				    		</div> 
							<p class="text-center">本地下载:
								<a style="color: green;text-decoration:none;" href="<%=basePath %>dow/app-debug.apk">手机版</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row text-center" id="row-third">
			<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
		    	Copyright © 北京洛斯达数字遥感技术有限公司, All Rights Reserved.
		    </div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	//生成APK二维码
	 $("#qrcodeTable").html("");
     var url=basePath+"dow/app-debug.apk";
		   $("#qrcodeTable").qrcode({
			render	: "canvas",
			text	: url,
			width:"164",
			height:"164"
	 });
});

$(document).keyup(function(event){
  if(event.keyCode ==13){
   loginBtn();
  }
});

//清除文本框中的值
$('#inputAccountExample1').click(function(){
	$(this).val('');
	$('#inputPasswordExample1').val('');
})
 
function loginBtn(){
	var url=$('#loginForm').attr('action');
	var $form=$('#loginForm').serialize();
	$.post(url,$form,function(response){
	    var response=JSON.parse(response);
	 	switch (response.loginflag) {
			case "no":
			case "error":
			case "validatorUserPwd":
				var msg = new $.zui.Messager("消息提示："+response.loginmsg, {placement: "center",type:"primary"});
				msg.show();	
				break;
			case "success":
				window.location.href=basePath+"business/system/sysWorkInfo/list.jsp";
				break;
			case "role":
				window.location.href=basePath+"business/system/home_page/list.jsp";
				break;
		}
	});
}
</script>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.lyht.Constants,java.util.ArrayList" %>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<!-- 导航头部 -->
	<div class="navbar-header">
		<!-- 移动设备上的导航切换按钮 -->
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse-example">
			<span class="sr-only">切换导航</span> 
			<span class="icon-bar"></span> 
			<span class="icon-bar"></span> 
			<span class="icon-bar"></span> 
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		
		<!-- 品牌名称或logo -->
		<a class="navbar-brand" 
			 <c:choose>
			   	 <c:when test="${sysNormal == 1}">
			     	href="<%=basePath %>business/system/home_page/list.jsp"
			   	 </c:when>
			     <c:otherwise> 
			   		href="#"
			     </c:otherwise>
			 </c:choose>
		 >
		 	<img title="西藏电网建设工程物资管理系统" alt="西藏电网建设工程物资管理系统"
		 		src="<%=basePath%>common/img_transparency/LOGO.png"/>
		 	<!-- <p class="logo_font">西藏物资系统</p> -->
		</a>
	</div>
	
	<!-- 导航菜单 -->
	<div class="collapse navbar-collapse navbar-collapse-example">
		<div class="nav_menu">
			<ul class="nav navbar-nav navbar-right">
				<s:iterator value="#session.session_menu_s" id="menua">
					<s:if test="#menua.CODE.length()<=4">
						<li class="dropdown">
							<a href='<%=basePath%><s:property value="#menua.URL"/>'
								class="dropdown-toggle" data-toggle="dropdown">
								<div class="visible-lg visible-xs visible-sm visible-md">
									<i class="<s:property value="#menua.ICON "/> menu_font"></i>
									<span class="menu_font"><s:property value="#menua.NAME"/></span>
									<b class="caret menu_font"></b>
								</div>
							</a>
							
							<ul class="dropdown-menu">
								<s:iterator value="#session.session_menu_s" id="menub">
									<s:if
										test="#menub.CODE.length()<=7&& #menub.PCODE==#menua.CODE">
										<li>
											<a href="<%=basePath%><s:property value="#menub.URL"/>">
												<i class=" icon text-warning 
												    <s:property value="#menub.ICON"/>
									    			<s:property value="#menub.ICONSPIN"/>
									    			<s:property value="#menub.ICONCOLOR"/>">
									    	    </i>
									    	    <span class="<s:property value="#menub.TITLECOLOR"/> dropdown_menu_font_css">
									    	    	  <s:property value="#menub.NAME" />
									    	    </span>
											</a>
										</li>
									</s:if>
								</s:iterator>
							</ul>
						</li>
					</s:if>
				</s:iterator>
			</ul>
		</div>
		
		<!-- 用户信息 -->
		<div class="btn-group navbar-right" id="user">
			<a href="#" class="btn dropdown-toggle btn_transparent btn_a_css" data-toggle="dropdown">
				<div class="visible-lg">
					<i class="icon icon-user"></i>
					<s:if test="#session.sys_role.code=='supadmin' || #session.sys_role.code=='admins' || #session.sys_role.code=='audmins' || #session.sys_role.code=='almins'">
						<font class="no_weight"><s:property value="#session.sys_role.name" /></font>
					</s:if> 
					<s:else>
						<font class="no_weight"><s:property value="#session.session_staff.name"/></font>
					</s:else>
					<b class="caret menu_font"></b>
				</div>
				<div class="visible-xs visible-sm visible-md">
					<i class="icon icon-user"></i>
				</div>
			</a>
			
			<ul class="dropdown-menu">
				<li class='dropdown-submenu'>
					<a href='#'>主题</a>
					<ul class='dropdown-menu pull-left' id="themes">
						<li><a data-value="green" href="#">国网绿</a></li>
						<li><a data-value="default" href="#">蓝色</a></li>
						<li><a data-value="bluegrey" href="#">蓝灰色</a></li>
						<li><a data-value="brown" href="#">棕色</a></li>
<!-- 						<li><a data-value="blue" href="#">浅蓝色</a></li> -->
						<li><a data-value="indigo" href="#">靛蓝</a></li>
						<li><a data-value="purple" href="#">紫色</a></li>
						<li><a data-value="yellow" href="#">黄色</a></li>
					</ul>
				</li>
				<li class="divider"></li>
			    <li><a href="#" onclick="updatePwd()">修改密码</a></li>
			    <li class="divider"></li>
			    <li>
			    	<a href="#" onclick="logout()">
				    	<i class="icon icon-off text-warning"></i>
				    	<span class="text-primary">注销系统</span>
					</a>
				</li>
			</ul>
		</div>
		
		<div  class="btn-group navbar-right theme-container change_engin_css">
			<a class="btn btn_transparent btn_a_css"  href="#" onclick="barcode()">
				<span class="visible-xs visible-sm visible-md">
					<i class="icon icon-spin icon-qrcode"></i>
				</span>
				<span class="visible-lg">
					<i class="icon icon-spin icon-qrcode"></i>
					<span id="font" class="no_weight">手机版</span>
				</span>
			</a>
		</div> 
		
	</div>
</nav>
<%@include file="/common/inc/list.jsp"%>
<%@include file="/common/inc/tree.jsp"%>
<%@include file="/common/inc/barcode.jsp"%>
<link rel="stylesheet" href="<%=basePath %>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=basePath %>common/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=basePath %>common/qrcode/jquery.qrcode.js"></script>
<script type="text/javascript" src="<%=basePath %>common/qrcode/qrcode.js"></script>
	<script type="text/javascript">
		$(function(){
			creat();
		})
	
	    /*生成二维码*/	
		 function barcode(){
		 		$("#barcode").modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
		
		      });
		 	}
			
		 function creat(){
		      $("#qrcodeTable").html("");
		      
		       var url=basePath+"dow/app-debug.apk";
				console.log(url)
			   $("#qrcodeTable").qrcode({
				render	: "table",
				text	: url,
				width:"200",
				height:"200"
			});
			
		   }
	
</script>
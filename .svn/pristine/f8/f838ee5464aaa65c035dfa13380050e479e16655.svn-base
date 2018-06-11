<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>系统设置</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<%@include file="../../../common/inc/treegrid.inc"%>
<style type="text/css">
	#tbinfo,#tbinfo td,#tbinfo th{
		border:1px #E4E4E4 solid; 
	}
	a{
	  cursor:pointer;
	}
	.right{
		text-align:right;
	}
	.td_width{
		width:150px;
	}
	.content_top{
		margin-top:8px;
	}
	td{
		height:60px;
	}
</style>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>功能表单</li>
					<li class="active">系统设置管理</li>
				</ol>
			</h3>
		</div>
		
		<div style="display: none;"></div>
		<div id="maincontent" class="row-fluid">
			<div class="row-fluid col-md-12 content_top">
				<form method="post">
				   <div class="row">
				   	 <table class="table table-bordered">
				   	 	<tr>
				   	 		<td class="td_width right"><span class="text-danger">*&nbsp;</span>移动端启用模块：</td>
				   	 		<td style="border-bottom:0px;">
				   	 			<label class="checkbox-inline">
								  <input type="checkbox" name="">计划管理
								</label>
								<label class="checkbox-inline">
								  <input type="checkbox" name="">物资运输
								</label>
								<label class="checkbox-inline">
								  <input type="checkbox" name="">厂商管理
								</label>
								<label class="checkbox-inline">
								  <input type="checkbox" name="">消息
								</label>
				   	 			<br>
				   	 			<label class="checkbox-inline">
								  <input type="checkbox" name="">合同管理
								</label>
								<label class="checkbox-inline">
								  <input type="checkbox" name="">物资问题
								</label>
								<label class="checkbox-inline">
								  <input type="checkbox" name="">通讯录&nbsp;&nbsp;&nbsp; 
								</label>
								<label class="checkbox-inline">
								  <input type="checkbox" name="">通知
								</label>
				   	 		</td>
				   	 	</tr>
				   	 	
				   	 	<tr>
				   	 		<td class="td_width right"><span class="text-danger">*&nbsp;</span>系统登录验证：</td>
				   	 		<td>
				   	 			<label class="radio-inline">
								  <input type="radio" name="radio_log" checked="checked">账号验证
								</label>
								<label class="radio-inline">
								  <input type="radio" name="radio_log">单选按钮
								</label>
				   	 		</td>
				   	 	</tr>
				   	 	
				   	 	<tr>
				   	 		<td class="td_width right"><span class="text-danger">*&nbsp;</span>是否启用移动端：</td>
				   	 		<td>
				   	 			<label class="radio-inline">
								  <input type="radio" name="radio_using" checked="checked">是
								</label>
								<label class="radio-inline">
								  <input type="radio" name="radio_using">否
								</label>
				   	 		</td>
				   	 	</tr>
				   	 	
				   	 	<tr>
				   	 		<td class="td_width right"><span class="text-danger">*&nbsp;</span>系统设置：</td>
				   	 		<td>
				   	 			<label class="radio-inline">
								  <input type="radio" name="radio_set" checked="checked">是
								</label>
								<label class="radio-inline">
								  <input type="radio" name="radio_set">否
								</label>
				   	 		</td>
				   	 	</tr>
				   	 	
				   	 	<tr>
				   	 		<td class="td_width right"><span class="text-danger">*&nbsp;</span>其他系统设置：</td>
				   	 		<td>
				   	 			<input id="" type="text" class="form-control" placeholder="" style="width:320px">
				   	 		</td>
				   	 	</tr>
				   	 </table>
				   	 <div style="text-align:center">
					   	 <button class="btn btn-info" id="" type="button">
					   		<i class="icon icon-plus"></i>&nbsp;提交
					   	 </button>
				   	 </div>
				   </div>
				</form>
			</div>
		</div>
		<!-- maincontent -->
	</div>

</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="/business/system/sysRole/edit.inc"%>
<script src="<%=basePath%>business/system/sysDict/def_q.js"></script>
<script src="def.js"></script>
<script src="list.js"></script>

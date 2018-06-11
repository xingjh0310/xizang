<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>通知提醒</title>
<%@include file="../../../../common/inc/inc.inc"%>
<%@include file="../../../../common/inc/bootstrapTable.inc"%>
<link rel="stylesheet" href="<%=basePath %>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" 	href="css.css">
</head>
<body>
	<div class="container-fluid" >
		<%@include file="../../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb" >
					<li>消息</li>
                    <li class="content_top_title_black">通知提醒</li>
				</ol>
			</h3>
		</div>
		<hr>
	<div style="display: none;">
	</div>
	<div id="maincontent" class="row-fluid ">
		<div class="row-fluid col-md-12">
			<!-- 按钮工具条开始 -->
			<div id="tbar"class="btn-toolbar">
				<div class="btn-group ">
					<button type="button" id="btn_add" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增通知</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-plus-sign"></i></div>
					</button>
				</div>
				<div class="btn-group">
					<button type="button" id="btn_del" class="btn btn-danger">
					   <div class="visible-md visible-lg"><i class="icon-trash"></i>&nbsp;批量删除</div>
					   <div class="visible-xs visible-sm"><i class="icon-trash"></i></div>
					</button>
				</div>
				<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
					<div class="input-group ">
						<input type="text" id="searchName" class="form-control" autofocus="autofocus" placeholder="输入关键字进行模糊查询" maxlength="20"> 
						<span class="input-group-btn">
							<button class="btn btn-primary" id="btn_ref" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
							</button>
						</span>
					</div>
				</div>
                
                <div class="btn-group pull-right visible-lg visible-md visible-sm">
                		<input type="hidden" id="notice_receiveDepts" />
						<input type="text" class="form-control" id="notice_receiveDept" 
	                            required readonly="readonly" 
	                            placeholder="选择部门">
				</div>
				<div class="btn-group pull-right visible-lg visible-md visible-sm">
					<h5>接收部门：</h5>
				</div>
				
				<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select id="notice_classify" class="form-control">
							<option value="">请选择</option>
							
						</select>
				</div>
				<div class="btn-group pull-right visible-lg visible-md visible-sm">
					<h5>分类：</h5>
				</div>
                <input type="hidden" id="equipmentTypeNm" class="form-control">
                <input type="hidden" id="zTreeData" class="form-control">
             </div>
			</div>
            
			<!-- 按钮工具条结束 -->
			<table id="tbinfo" class="table-condensed table-hover table-cursor" >
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50"  data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
                        <th data-halign="center" data-align="center" data-sortable="false" data-width="50"  data-formatter="FMT_Num">  序号</th>
                        <th data-halign="center" data-align="left" data-sortable="false"  data-width=""  data-field="TITLE"  data-formatter="FMT_Title">  标题</th> 
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="50" data-field="TYPE" >  分类</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="120"  data-field="RELEASEDEPT">  发布部门</th>
                        <th data-halign="center" data-align="left" data-sortable="false"  data-width="200"  data-field="RECEIVEDEPT" data-formatter="FMT_DeptName">  接收部门</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="80" data-field="CREATEPERSON" >  创建人</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="80"  data-field="RELEASEDATE">  发布日期</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="80"  data-field="STATE" data-formatter="FMT_State">  状态</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="200"  data-field="" data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>
        
	  </div> <!-- maincontent -->
	</div>
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.jsp"%>
<%@include file="tree.jsp"%>
<script type="text/javascript" src="<%=basePath %>common/js/ztree/js/jquery.ztree.all.js"></script>
<script src="def.js"></script>
<script src="list.js"></script>

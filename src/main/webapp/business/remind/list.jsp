<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>物资运输提醒</title>
<%@include file="../../../../common/inc/inc.inc"%>
<%@include file="../../../../common/inc/bootstrapTable.inc"%>
<link rel="stylesheet" href="<%=basePath %>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" 	href="css.css">
</head>
<body>
	<div class="container-fluid" >
		<%@include file="../../../../common/inc/top.jsp"%>
		<div class="row-fluid ">
			<h3 class="text-primary">
				<ol class="breadcrumb" >
					<li>消息通知</li>
                    <li class="content_top_title_black">物资运输提醒</li>
				</ol>
			</h3>
		</div>
		<hr>
		<div style="display: none;">
		</div>
	<div id="maincontent" class="row-fluid ">
		<div class="row-fluid col-md-12">
			<!-- 按钮工具条开始 -->
			<div id="tbar" class="btn-toolbar">
				<div class="btn-group pull-right">
					<button type="button" id="read" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-check-circle"></i> &nbsp;标记为已读</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-check-circle"></i></div>
					</button>
				</div>
				<div class="btn-group  pull-right">
					<button type="button" id="unread" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-minus-sign"></i>&nbsp;标记为未读</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-minus-sign"></i></div>
					</button>
				</div>
				<div class="btn-group visible-lg visible-md visible-sm">
					<h5>状态：</h5>
				</div>
				<div class="btn-group visible-lg visible-md visible-sm">
						<select id="select_flag" class="form-control">
							<option value="">请选择</option>
							<option value="1">已读</option>
							<option value="0">未读</option>
						</select>
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
                        <th data-halign="center" data-align="left" data-sortable="false"  data-width="" 	data-field="TITLE" >  标题</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="200"  data-field="RELEASEDEPT">  发布部门</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="200" data-field="CREATEPERSON" >  创建人</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="200"  data-field="RELEASEDATE"> 接收时间</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="80"  data-field="READSTATE" data-formatter="FMT_State"> 状态</th>
					</tr>
				</thead>
			</table>
        
	  </div> 
	</div>
	
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.jsp"%>
<script type="text/javascript" src="<%=basePath %>common/js/ztree/js/jquery.ztree.all.js"></script>
<script src="def.js"></script>
<script src="list.js"></script>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>信息维护</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/treegrid.inc"%>

<link rel="stylesheet" href="<%=basePath %>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=basePath %>common/js/ztree/js/jquery.ztree.all.js"></script>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top_s.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>功能列表</li>
					<li class="active">工程信息管理</li>
				</ol>
			</h3>
		</div>
		<div id="maincontent" class="row-fluid">
			<div id="tbar" class="row-fluid btn-toolbar">
					<div class="btn-group">
						<button class="btn btn-primary" id="btn_add" type="button">
							<div class="visible-md visible-lg">
								<i class="icon icon-file-o"></i>&nbsp;新增根节点
							</div>
							<div class="visible-xs visible-sm">
								<i class="icon icon-file-o"></i>
							</div>
						</button>
					</div>
<!-- 					<div class="btn-group"> -->
<!-- 							<button type="button" id="import_EngineerInfo" class="btn btn-primary"> -->
<!-- 								<div class="visible-md visible-lg"> -->
<!-- 									<i class="icon icon-times"></i>&nbsp;导入 -->
<!-- 								</div> -->
<!-- 								<div class="visible-xs visible-sm"> -->
<!-- 									<i class="icon icon-times"></i> -->
<!-- 								</div> -->
<!-- 							</button> -->
<!-- 						</div> -->
						<div class="btn-group">
							<button type="button" id="export_whole_project" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-times"></i>&nbsp;导出全部
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-times"></i>
								</div>
							</button>
						</div>

				<div class="btn-group pull-right">
					<button class="btn" id="btn_basedata" type="button">
						<div class="visible-md visible-lg">
							<i class="icon icon-refresh"></i>&nbsp;刷新
						</div>
						<div class="visible-xs visible-sm">
							<i class="icon icon-refresh"></i>
						</div>
					</button>
				</div>
				<div
					class="input-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
					<select id="select_pcode" class="form-control"
						onchange="sysDept.refresh();">
						<option value="">显示全部数据</option>
					</select>
				</div>
			</div>
			<br>
			<div class="row-fluid col-md-12">
				<table id="treeGrid" class="table-hover">
				</table>
			</div>
		</div>
	</div>
</body>
</html>
<%@include file="edit.inc"%>
<%@include file="edit_dept.jsp"%>
<%@include file="/common/import/importInfo.inc"%>  <!-- 导入表单 -->
<%@include file="/business/system/sysDept/deptTree.jsp"%>
<script src="def.js"></script>
<script src="<%=basePath%>common/import/importInfo.js"></script>  <!-- 导入方法 -->
<script src="list.js"></script>
<script src="<%=basePath%>common/datejs/laydate.dev.js"></script>
<script type="text/javascript" src="<%=basePath%>common/zui/lib/datetimepicker/datetimepicker.min.js"></script>

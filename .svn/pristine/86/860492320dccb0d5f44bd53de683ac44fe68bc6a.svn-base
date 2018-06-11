<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>信息维护</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<%@include file="../../../common/inc/treegrid.inc"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top_s.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>功能列表</li>
					<li class="active">字典管理</li>
				</ol>
			</h3>
		</div>
		<div style="display: none;">
			<input type="text" class="form-control" id="S_ListnmSysDictCate">
		</div>
		<div id="maincontent" class="row-fluid">
				<div class="row-fluid col-md-3">
					<ul class="nav nav-tabs">
						<li class="active"><a data-tab
							href="#tabContent_ListnmSysDictCate"
							onclick="Select_ListnmSysDictCate('')">字典分类 <i
								class="icon icon-refresh"></i></a></li>
					</ul>
					<div class="tab-content">

						<div class="tab-pane  active " id="tabContent_ListnmSysDictCate">
							<table id="tbinfo_ListnmSysDictCate"
								class="table-condensed table-hover">
								<thead>
									<tr>
										<th data-halign="center" data-align="center"
											data-sortable="false" data-width="60"
											data-formatter="FMT_Num_ListnmSysDictCate">序号</th>
										<th data-halign="center" data-align="left"
											data-sortable="true" data-field="CODE" data-width="150">
											编码</th>
										<th data-halign="center" data-align="left"
											data-sortable="true" data-field="NAME">名称</th>
										<th data-halign="center" data-align="center"
											data-sortable="false" data-width="50"
											data-formatter="FMT_Oper_ListnmSysDictCate">过滤</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>

			<div class="row-fluid col-md-9">
			<!-- 按钮工具条开始 -->
			<div id="tbar" class="btn-toolbar">
				<div class="btn-group">
					<button type="button" id="btn_add" class="btn btn-primary">
						<div class="visible-md visible-lg">
							<i class="icon icon-file-o"></i>&nbsp;新增
						</div>
						<div class="visible-xs visible-sm">
							<i class="icon icon-file-o"></i>
						</div>
					</button>
				</div>
				<div class="btn-group">
					<button type="button" id="btn_del" class="btn btn-primary">
						<div class="visible-md visible-lg">
							<i class="icon icon-times"></i>&nbsp;删除
						</div>
						<div class="visible-xs visible-sm">
							<i class="icon icon-times"></i>
						</div>
					</button>
				</div>
				<div
					class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
					<div class="input-group ">
						<input type="text" id="searchName" class="form-control"
							placeholder="输入关键字进行模糊查询"> <span class="input-group-btn">
							<button class="btn" id="btn_ref" type="button">
								<div class="visible-md visible-lg">
									<i class="icon icon-search"></i>&nbsp;查询
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-search"></i>
								</div>
							</button>
						</span>
					</div>
				</div>
			</div>
			<br>
			<!-- 按钮工具条结束 -->
			<table id="tbinfo" class="table-condensed table-hover">
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false"
							data-field="state" data-checkbox="true"
							data-formatter="FMT_Check"></th>
						<th data-halign="center" data-align="center" data-sortable="false"
							data-width="60" data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="center" data-sortable="true"
							data-field="CODE" data-width="200">编码</th>
						<th data-halign="center" data-align="center" data-sortable="true"
							data-field="NAME" data-width="200">名称</th>
						<th data-halign="center" data-align="center"
							data-sortable="false" data-width="50" 
							data-formatter="FMT_Oper">修改</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.inc"%>
<script src="<%=basePath%>business/system/sysDictCate/def_q.js"></script>
<script src="<%=basePath%>business/system/sysDict/def_q.js"></script>
<script src="def.js"></script>
<script src="list.js"></script>

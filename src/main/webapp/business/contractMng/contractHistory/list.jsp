<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>历史记录</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<link href="../common/css/contract.css" media="all" rel="stylesheet" type="text/css"/>
</head>
<style type="text/css">
	#_dialog .form-group {
	    margin-bottom: 5px;
	}
</style>
<body style="background-color: #FCFCFC;">
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>合同管理</li>
					<li style="color:black;">历史记录</li>
				</ol>
			</h3>
		</div>
		<hr>
		<div id="maincontent" class="row-fluid">
			<!-- 按钮工具条开始 -->
			<div class="row-fluid col-md-12">
				<div id="tbar" class="btn-toolbar">
					<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
						<div class="input-group">
							<input type="text" id="query_searchName" autofocus="autofocus" class="form-control" placeholder="输入关键字进行模糊查询"> 
							<span class="input-group-btn">
								<button class="btn btn-primary" id="query_ref" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
								</button>
							</span>
						</div>
					</div>
					<label  class=" col-md-2  col-lg-2 col-sm-2"></label>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select style="width: 150px;" id="select_contract" class="form-control" >
						</select>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<h5>合同名称：</h5>
					</div>
				</div>
			</div>
			<!-- 按钮工具条结束 -->
			<table id="query_table"  class="table-condensed table-hover table-cursor">
		        <thead>
		            <tr>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="40px" data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-field="CONTRACTTITLE" data-width="" data-formatter="">合同标题</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-field="MATERIELNAME" data-width="" data-formatter="">物料名称</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-field="PRECHANGENUM" data-width="" data-formatter="">变更前数量</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-field="AFTERCHANGENUM" data-width="" data-formatter="">变更后数量</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-field="PRECHANGEPRICE" data-width="" data-formatter="FMT_amount">变更前价格</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-field="AFTERCHANGEPRICE" data-width="" data-formatter="FMT_amount">变更后价格</th>
<!-- 						<th data-halign="center" data-align="right" data-sortable="false" data-field="DIFFERENCE" data-width="" data-formatter="FMT_amount">差额（不含税）</th> -->
						<th data-halign="center" data-align="right" data-sortable="false" data-field="DIFFERENCETAX" data-width="" data-formatter="FMT_amount">差额</th>
						<th data-halign="center" data-align="center" data-sortable="true" data-field="CHANGETIME" data-width="" data-formatter="FMT_date">变更日期</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-field="MATERIALSTATE" data-width="" data-formatter="FMT_handle">状态</th>
		            </tr>
		        </thead>
		    </table>
		</div>
	</div>
	<!-- maincontent -->
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.jsp"%>
<%@include file="../common/file_upload.jsp"%>
<%@include file="query_details.jsp"%>

<script src="query.js"></script>
<script src="list.js"></script>

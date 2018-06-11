<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>合同变更</title>
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
					<li style="color:black;">合同变更</li>
				</ol>
			</h3>
		</div>
		<hr>
		<div id="maincontent" class="row-fluid">
			<!-- 按钮工具条开始 -->
			<div class="row-fluid col-md-12">
				<div id="tbar" class="btn-toolbar">
					<div class="btn-group">
						<button type="button" id="btn_out_all" class="btn btn-primary">
							<div class="visible-md visible-lg">
								<i class="icon icon-reply"></i>&nbsp;导出全部
							</div>
							<div class="visible-xs visible-sm">
								<i class="icon icon-star"></i>
							</div>
						</button>
					</div>
					<div class="btn-group">
						<button type="button" id="btn_out_page" class="btn btn-primary">
							<div class="visible-md visible-lg">
								<i class="icon icon-circle-arrow-up"></i>&nbsp;导出当前页
							</div>
							<div class="visible-xs visible-sm">
								<i class="icon icon-star"></i>
							</div>
						</button>
					</div>
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
						<select id="select_supply" class="form-control" >
						</select>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<h5>供货厂商：</h5>
					</div>
				</div>
			</div>
			<!-- 按钮工具条结束 -->
			<table id="query_table"  class="table-condensed table-hover table-cursor">
		        <thead>
		            <tr>
		            	<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
						<th data-halign="center" data-align="center" data-sortable="false" data-field="id">id</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="40px" data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-field="ACCESSORY" data-width="" data-formatter="FMT_file">附件</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-field="CONTRACTTITLE" data-width="" data-formatter="">合同标题</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-field="ENGINEERNAME" data-width="" data-formatter="">工程名称</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-field="SUPPLYSTARTDATE" data-width="" data-formatter="">供货开始时间</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-field="SUPPLYENDDATE" data-width="" data-formatter="">供货结束时间</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-field="SUPPLYFULLNAME" data-width="" data-formatter="">供货商名称</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-field="CONTRACTAMOUNT" data-width="" data-formatter="FMT_amount">合同额（万元）</th>
						<th data-halign="center" data-align="center" data-sortable="true" data-field="SIGNDATE" data-width="">签订日期</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-field="ID" data-width="100px" data-formatter="FMT_handle">操作</th>
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
<%@include file="file_upload.jsp"%>

<script src="query.js"></script>
<script src="list.js"></script>

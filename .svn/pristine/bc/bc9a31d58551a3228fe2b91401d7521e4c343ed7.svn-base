<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>退料退库</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<link href="../../../common/zui/lib/uploader/zui.uploader.min.css" rel="stylesheet">
<script src="../../../common/zui/lib/uploader/zui.uploader.min.js"></script>
<link rel="stylesheet" type="text/css" 	href="css.css">
</head>
<body>
	<div class="container-fluid" >
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary" >
				<ol class="breadcrumb">
					<li>退料退库</li>
                    <li class="content_top_title_black">退料退库审核</li>
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
				   <div class="btn-group pull-left visible-lg visible-md visible-sm ">
						<button class="btn btn-primary " id="btn_adopt" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-check-circle"></i>&nbsp;批量通过</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-check-circle"></i></div>
						</button>
					</div>
					<div class="btn-group pull-left visible-lg visible-md visible-sm" >
						<button class="btn btn-danger" id="btn_refuse" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-times"></i>&nbsp;批量拒绝</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-times"></i></div>
						</button>
					</div>
					<div class="btn-group pull-left visible-lg visible-md visible-sm" >
						<button class="btn btn-primary" id="btn_record" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-file-text-o"></i>&nbsp;审核记录</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-file-text-o"></i></div>
						</button>
					</div>
					
					<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
						<div class="input-group">
							<input type="text" id="searchInput" autofocus="autofocus" class="form-control" placeholder="输入单据编号/单据标题等信息进行模糊查询"> 
							<span class="input-group-btn">
								<button class="btn btn-primary" id="btn_ref" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
								</button>
							</span>
						</div>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select id="supplier" class="form-control">
							<option value="">请选择供应商</option>
						</select>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<h5>供应商：</h5>
					</div>
				</div>
			</div><!-- 按钮工具条结束 -->
			<table id="tbinfo" class="table-condensed table-hover table-cursor">
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="state"  data-checkbox="true" data-formatter="FMT_Check">
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="NUB" data-formatter="FMT_ENCLOSURE">附件</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="150" data-field="BILLCODE" data-formatter="FMT_NUMBER">单据编号</th>
						<th data-halign="center" data-align="center"   data-sortable="false" data-width=""   data-field="BILLTITLE">单据标题</th>
						<th data-halign="center" data-align="left"  data-sortable="false" data-width="250" data-field="SUPPLIERCODE">供应商名称</th>
						<th data-halign="center" data-align="center"   data-sortable="false" data-width="100" data-field="SINGLESTAFF">制单人</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="150" data-field="SINGLEDATE">日期</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="80" data-field="STATE" data-formatter="FMT_State">状态</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="80" data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="audit_edit.jsp"%>
<%@include file="audit_details.jsp"%>
<%@include file="upload.jsp"%>
<script src="audit_def.js"></script> 
<script src="audit_list.js"></script>
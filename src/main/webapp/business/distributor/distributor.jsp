<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>供应商信息</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<link rel="stylesheet" type="text/css" 	href="css.css">
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top_s.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>基础信息</li>
                    <li class="content_top_title_black">供应商信息</li>
				</ol>
			</h3>
		</div>
		<hr>
		<div style="display: none;">
		</div>
		<div id="maincontent" class="row-fluid">
			<div class="row-fluid col-md-12">
				<!-- 按钮工具条开始 -->
				<div id="tbar" class="btn-toolbar">
					<div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-primary" id="btn_add" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-plus-sign"></i></div>
						</button>
					</div>
<!-- 					<div class="btn-group pull-left visible-lg visible-md visible-sm"> -->
<!-- 						<button class="btn btn-primary" id="btn_into" type="button"> -->
<!-- 							   <div class="visible-md visible-lg"><i class="icon icon-cloud-download"></i>&nbsp;导入</div> -->
<!-- 							   <div class="visible-xs visible-sm"><i class="icon icon-cloud-download"></i></div> -->
<!-- 						</button> -->
<!-- 					</div> -->
					<div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-primary" id="btn_out" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-reply"></i>&nbsp;导出全部</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-reply"></i></div>
						</button>
					</div>
					<div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-primary" id="btn_out_page" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-circle-arrow-up"></i>&nbsp;导出当前页</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-circle-arrow-up"></i></div>
						</button>
					</div>
					<div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-danger btn_del_color" id="btn_del" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-trash"></i>&nbsp;批量删除</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-trash"></i></div>
						</button>
					</div>
					<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
						<div class="input-group">
							<input type="text" id="searchInput" autofocus="autofocus" class="form-control" placeholder="输入供应商名称/登记证号/法人等信息进行模糊查询"> 
							<span class="input-group-btn">
								<button class="btn btn-primary" id="btn_ref" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
								</button>
							</span>
						</div>
					</div>
				</div>
			</div><!-- 按钮工具条结束 -->
           	<br>
           	<br>
			<table id="tbinfo" class="table-condensed table-hover table-cursor">
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="right"  data-sortable="false" data-width="50"  data-field="FILENUB" data-formatter="FMT_Accessory">附件</th>
						<th data-halign="center" data-align="left"  data-sortable="false" data-width="" data-field="SUPPLY_FULL_NAME">供应商</th>
						<th data-halign="center" data-align="left"   data-sortable="false" data-width=""   data-field="SUPPLY_BUILT">供应商简称</th>
						<th data-halign="center" data-align="center"   data-sortable="false" data-width=""   data-field="NAME">人员姓名</th>
						<th data-halign="center" data-align="center"   data-sortable="false" data-width="" data-field="REGISTRATION_NO">工商登记证号</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="LEGAL_PERSON">法人</th>
						<th data-halign="center" data-align="center"   data-sortable="false" data-width="" data-field="LINK_PHONE">联系方式</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="FAX">传真</th>
						<th data-halign="center" data-align="left"  data-sortable="false" data-width="" data-field="ADDRESS">地址</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="140" data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>
					
				
		</div><!-- maincontent -->
	</div>
</body>
<%@include file="distributor_edit.jsp"%>
<%@include file="distributor_info.jsp"%>
<%@include file="upload.jsp"%>
<%@include file="file_upload.jsp"%>
<script src="distributor_def.js"></script> 
<script src="distributor_list.js"></script>
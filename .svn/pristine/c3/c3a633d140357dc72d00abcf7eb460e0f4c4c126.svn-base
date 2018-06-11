<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>物资发货通知</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<link rel="stylesheet" type="text/css" 	href="css.css">
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid ">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>物资运输</li>
                    <li class="content_top_title_black">物资发货通知</li>
				</ol>
			</h3>
		</div>
		<hr>
		<div style="display: none;">
		</div>
		<div id="maincontent" class="row-fluid " >
			<div class="row-fluid col-md-12 ">
				<!-- 按钮工具条开始 -->
				<div id="tbar" class="btn-toolbar btn-left">
					<div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-primary" id="btn_add" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-plus-sign"></i></div>
						</button>
					</div>
					<!-- <div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-primary " id="btn_out_page" type="button" >
							   <div class="visible-md visible-lg"><i class="icon icon-circle-arrow-up"></i>&nbsp;导出当前页</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-circle-arrow-up"></i></div>
						</button>
					</div>
					<div class="btn-group pull-left visible-lg visible-md visible-sm ">
						<button class="btn btn-primary" id="btn_out" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-reply"></i>&nbsp;导出全部</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-reply"></i></div>
						</button>
					</div> -->
					<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
						<div class="input-group">
							<input type="text" id="searchInput" autofocus="autofocus" class="form-control" placeholder="输入合同编号/合同标题/供货商等信息进行模糊查询"> 
							<span class="input-group-btn">
								<button class="btn btn-primary" id="btn_ref" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
								</button>
							</span>
						</div>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select id="material_type" class="form-control">
							<option value="">请选择物资类型</option>
							
						</select>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<h5>物资类型：</h5>
					</div>
					
				</div>
			</div>
			<table id="tbinfo" class="table-condensed table-hover table-cursor">
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50"  data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="left"  data-sortable="false" data-width="" data-field="MATERIELNAME">物料名称</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="50" data-field="SUPPLIERPLANCODE" >采购编号</th>
						<th data-halign="center" data-align="center"   data-sortable="false" data-width=""   data-field="CONTRACTNO">合同编号</th>
						<th data-halign="center" data-align="left"   data-sortable="false" data-width="" data-field="SUPPLYFULLNAME" data-formatter="FMT_Supplier">供应商名称</th>
						<th data-halign="center" data-align="center"   data-sortable="false" data-width="" data-field="DEPTNAME">项目单位</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="DEMANDUNIT">需求单位</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-width="" data-field="PLANDELIVERIE">计划交货数量</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-width="" data-field="ACTUALDELIVERIE">实际发货数量</th>
						<!-- <th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="SUPPLIERCONTACT">供应商联系人</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="SUPPLIERLINKPHONE">供应商电话</th> -->
						<th data-halign="center" data-align="center"   data-sortable="false" data-width="" data-field="DELIVERYCONTACT">交货联系人</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="LINKMODE">交货人电话</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="DELIVERYPLACE">交货地点</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="" data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>									
		</div>
	</div>
	
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="deliverGoods_edit.jsp"%>
<%@include file="deliverGoods_details.jsp"%>
<script src="deliverGoods_def.js"></script> 
<script src="deliverGoods_list.js"></script>
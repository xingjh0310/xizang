<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>物资运输状态</title>
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
					<li>物资运输</li>
                    <li class="content_top_title_black">物资交接</li>
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
				
					<div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-primary" id="btn_add" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-plus-sign"></i></div>
						</button>
					</div>
					<div class="btn-group pull-left visible-lg visible-md visible-sm ">
						<button class="btn btn-primary " id="btn_monitor" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-cubes"></i>&nbsp;在途运输</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-cubes"></i></div>
						</button>
					</div>
					<!-- <div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-primary" id="btn_transfer" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-cube"></i>&nbsp;物资到货</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-cube"></i></div>
						</button>
					</div>
					<div class="btn-group pull-left visible-lg visible-md visible-sm" >
						<button class="btn btn-primary" id="btn_check" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-check-board"></i>&nbsp;五方验收</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-check-board"></i></div>
						</button>
					</div> 
					<div class="btn-group pull-left visible-lg visible-md visible-sm" >
						<button class="btn btn-primary" id="btn_out_page" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-circle-arrow-up"></i>&nbsp;导出当前页</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-circle-arrow-up"></i></div>
						</button>
					</div>
					<div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-primary" id="btn_out" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-reply"></i>&nbsp;导出全部</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-reply"></i></div>
						</button>
					</div>-->
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
						<select id="material_name" class="form-control">
							<option selected="selected">请选择物资类型</option>
							
						</select>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<h5>物资类型：</h5>
					</div>
				</div>
			</div><!-- 按钮工具条结束 -->
			<table id="tbinfo" class="table-condensed table-hover table-cursor">
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="state"  data-checkbox="true" data-formatter="FMT_Check">
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="SUPPLYPLANTITLE" data-formatter="FMT_Title" >标题</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="50" data-field="SUPPLIERPLANCODE" >采购编号</th>
						<th data-halign="center" data-align="center"   data-sortable="false" data-width=""   data-field="CONTRACTNAME">合同标题</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="MATERIELNAME">物资名称</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-width="" data-field="ACTUALDELIVERIE">数量</th>
						<th data-halign="center" data-align="left"  data-sortable="false" data-width="" data-field="SUPPLIERNAME">供货厂商</th>
						<th data-halign="center" data-align="center"   data-sortable="false" data-width="" data-field="RECEIVESTATE" data-formatter="FMT_receiveState">交货状态</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="RECEIVECONFIRMTIME">交货时间</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="ACCEPTANCESTATE" data-formatter="FMT_State_Check">验收状态</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="ACCEPTANCETIME">验收时间</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="100" data-field="" data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="handover_edit.jsp"%>
<%@include file="handover_details.jsp"%>
<%@include file="handover_edit_check.jsp"%>
<%@include file="handover_check_module.jsp"%>
<script src="handover_def.js"></script> 
<script src="handover_list.js"></script>
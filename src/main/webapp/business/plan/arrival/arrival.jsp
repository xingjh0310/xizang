<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>到货计划</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<link href="css/arrival.css" media="all" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>计划管理</li>
                    <li class="content_top_title_black">到货计划</li>
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
					<!-- <div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-primary" id="btn_reported" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-circle-arrow-up"></i>&nbsp;批量上报</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-circle-arrow-up"></i></div>
						</button>
					</div> -->
					<!-- <div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-danger btn_del_color" id="btn_del" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-trash"></i>&nbsp;批量删除</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-trash"></i></div>
						</button>
					</div> -->
					<!-- <div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-primary " id="btn_report" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-file-text-o"></i>&nbsp;上报记录</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-file-text-o"></i></div>
						</button>
					</div> -->
					<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
						<div class="input-group">
							<input type="text" id="searchInput" autofocus="autofocus" class="form-control" placeholder="输入关键字进行模糊查询"> 
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
							<option value="">请选择</option>
						</select>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<h5>物料名称：</h5>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select id="material_type" class="form-control">
							<option value="">请选择</option>
						</select>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<h5>物料类型：</h5>
					</div>
				</div>
			</div><!-- 按钮工具条结束 -->
			<table id="tbinfo" class="table-condensed table-hover table-cursor">
				<thead>
					<tr>
						<!-- <th data-halign="center" data-align="center" data-sortable="false" data-width="30" data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th> -->
						<th data-halign="center" data-align="center" data-sortable="false" data-width="30" data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="left"   data-sortable="false" data-width="150"   data-field="supply_plan_title"  data-formatter="FMT_Title">供货计划标题</th>
						<th data-halign="center" data-align="right"  data-sortable="false" data-width="30"  data-field="FILENUB" data-formatter="FMT_Accessory">附件</th>
						<th data-halign="center" data-align="left"   data-sortable="false" data-width="" data-field="MATERIELNAME">物料名称</th>
						<th data-halign="center" data-align="right"   data-sortable="false" data-width="" data-field="CONTRACTNUM">合同数量</th>
						<th data-halign="center" data-align="right"  data-sortable="false" data-width="" data-field="PLANDELIVERIE">计划交货数</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-width="" data-field="ACTUALDELIVERIE">实际交货数</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-width="" data-field="DEPTNAME">项目单位</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="CONTRACTDELIVERYDATE">合同交货期</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="CONFIRMDELIVERYDATE">确认交货期</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="RECEIVECONTACT">收货联系人</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="LINKMODE">联系电话</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="STATE" data-formatter="FMT_State">状态</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-width="200" data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>
					
				
		</div><!-- maincontent -->
	</div>
	
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="arrival_edit.jsp"%>
<%@include file="arrival_info.jsp"%>
<%@include file="time_up_edit.jsp"%>
<%@include file="upload.jsp"%>
<script src="arrival_def.js"></script> 
<script src="arrival_list.js"></script>
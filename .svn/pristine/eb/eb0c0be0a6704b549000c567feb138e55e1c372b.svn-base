<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>到货计划审核</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<link href="css/audit.css" media="all" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>到货计划</li>
                    <li class="content_top_title_black">计划审核</li>
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
						<button class="btn btn-primary" id="btn_goBack" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon icon-home"></i>&nbsp;返回</div>
							   <div class="visible-xs visible-sm"><i class="icon icon icon-home"></i></div>
						</button>
					</div>
					
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
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="right"  data-sortable="false" data-width="50"  data-field="FILENUB" data-formatter="FMT_Accessory">附件</th>
						<th data-halign="center" data-align="left"   data-sortable="false" data-width="" data-field="MATERIELNAME">物料名称</th>
						<th data-halign="center" data-align="left"   data-sortable="false" data-width=""   data-field="MATERIALNORMS"  data-formatter="FMT_MATERIELCODE">物料规格</th>
						<th data-halign="center" data-align="right"   data-sortable="false" data-width="" data-field="CONTRACTNUM">合同数量</th>
						<th data-halign="center" data-align="right"  data-sortable="false" data-width="" data-field="PLANDELIVERIE">计划交货数</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-width="" data-field="ACTUALDELIVERIE">实际交货数</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-width="" data-field="DEPTNAME">项目单位</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="CONTRACTDELIVERYDATE">合同交货期</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="CONFIRMDELIVERYDATE">确认交货期</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="RECEIVECONTACT">收货联系人</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="LINKMODE">联系电话</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-width="" data-field="DELIVERYPLACE" data-formatter="FMT_DELIVERYPLACE">交货地点</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="AUDITPERSON">审核人</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="AUDITTIME">审核时间</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="AUDITSTATE" data-formatter="FMT_AUDITSTATE">审核状态</th>
					</tr>
				</thead>
			</table>
					
				
		</div><!-- maincontent -->
	</div>
	
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="auditRecord_info.jsp"%>
<%@include file="upload.jsp"%>
<script src="auditRecord_def.js"></script> 
<script src="auditRecord_list.js"></script>
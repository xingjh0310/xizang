<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>甲供物资采购计划</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>计划管理</li>
                    <li class="active">甲供物资采购计划</li>
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
						<button class="btn btn-danger" id="btn_del" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-times"></i>&nbsp;批量删除</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-times"></i></div>
						</button>
					</div>
					<div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-info" id="btn_into" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-plus"></i>&nbsp;导入</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-plus"></i></div>
						</button>
					</div>
					<div class="btn-group pull-left visible-lg visible-md visible-sm">
						<button class="btn btn-info" id="btn_out" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-plus"></i>&nbsp;导出</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-plus"></i></div>
						</button>
					</div>
					<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
						<div class="input-group">
							<input type="text" id="searchInput" autofocus="autofocus" class="form-control" placeholder="输入项目名称/物料编码/施工单位等信息进行模糊查询"> 
							<span class="input-group-btn">
								<button class="btn btn-success" id="btn_ref" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
								</button>
							</span>
						</div>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select id="material_name" class="form-control">
							<option selected="selected">请选择物料名称</option>
						</select>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<h5>物料名称</h5>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select id="material_type" class="form-control">
							<option selected="selected">请选择物料种类</option>
						</select>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<h5>物料种类</h5>
					</div>
				</div>
			</div><!-- 按钮工具条结束 -->
           	<br>
           	<br>
			<table id="tbinfo" class="table-condensed table-hover">
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="id">序号</th>
						<th data-halign="center" data-align="right"  data-sortable="false" data-width="50" data-field="a" data-formatter="FMT_Accessory">附件</th>
						<th data-halign="center" data-align="left"   data-sortable="false" data-width=""   data-field="b">物料编码</th>
						<th data-halign="center" data-align="left"   data-sortable="false" data-width="" data-field="c">设备材料名称</th>
						<th data-halign="center" data-align="left"   data-sortable="false" data-width="" data-field="d">规格</th>
						<th data-halign="center" data-align="right"  data-sortable="false" data-width="" data-field="e">数量</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="f">计量单位</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="g">申报人</th>
						<th data-halign="center" data-align="right"  data-sortable="false" data-width="" data-field="h">资金</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="i">项目批准文号</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="j">预计需求日期</th>
						<th data-halign="center" data-align="left"   data-sortable="false" data-width="" data-field="k">交货地点</th>
						<th data-halign="center" data-align="right"  data-sortable="false" data-width="" data-field="l">估计总价</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="140" data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>
					
				
		</div><!-- maincontent -->
	</div>
	
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="procurement_edit.jsp"%>
<script src="procurement_def.js"></script> 
<script src="procurement_list.js"></script>
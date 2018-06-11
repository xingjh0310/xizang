<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>生产图纸确认</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
</head>
<body style="background-color: #FCFCFC;">
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>合同管理</li>
                    <li class="active">生产图纸确认</li>
				</ol>
			</h3>
		</div>
		<hr>
		<div id="maincontent" class="row-fluid">
				<!-- 按钮工具条开始 -->
				<div class="row-fluid col-md-12">
				<div id="tbar" class="btn-toolbar">
					<div class="btn-group">
						<button type="button" id="btn_refuse" class="btn btn-info">
						   <div class="visible-md visible-lg"><i class="icon icon-star"></i>&nbsp;导出当前页</div>
						   <div class="visible-xs visible-sm"><i class="icon icon-star"></i></div>
						</button>
					</div>
					<div class="btn-group">
						<button type="button" id="btn_refuse" class="btn btn-info">
						   <div class="visible-md visible-lg"><i class="icon icon-star"></i>&nbsp;导出全部</div>
						   <div class="visible-xs visible-sm"><i class="icon icon-star"></i></div>
						</button>
					</div>
					<div class="btn-group">
						<button type="button" id="query_add" class="btn btn-primary">
						   <div class="visible-md visible-lg"><i class="icon icon-star"></i>&nbsp;更新图纸日期</div>
						   <div class="visible-xs visible-sm"><i class="icon icon-star"></i></div>
						</button>
					</div>
					<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
						<div class="input-group">
							<input type="text" id="query_searchName" autofocus="autofocus" class="form-control" placeholder="输入关键字进行模糊查询"> 
							<span class="input-group-btn">
								<button class="btn btn-success" id="query_ref" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
								</button>
							</span>
						</div>
					</div>
					<label  class=" col-md-2  col-lg-2 col-sm-2"></label>
					
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select id="storage_name" class="form-control">
							<option value="">请选择物料名称</option>
							<option value="">铁塔</option>
							<option value="">导线</option>
							<option value="">地线</option>
							<option value="">绝缘子</option>
							<option value="">金线</option>
						</select>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<h5>物料名称：</h5>
					</div>
					
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select id="medium_name" class="form-control" >
							<option value="">请选择物料类型</option>
							<option value="">钢材类</option>
							<option value="">五金配件</option>
							<option value="">建材</option>
							<option value="">其他</option>
						</select>
					</div>
					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<h5>物料类型：</h5>
					</div>
				</div>
			</div>
			<div class="visible-lg visible-md">
	            <br> <br>
	        </div>
	        <div class="visible-xs  visible-sm">
	            <br>
	        </div>
			<!-- 按钮工具条结束 -->
		    <table id="query_table"  class="table-condensed table-hover table-cursor">
		        <thead>
		            <tr>
		            	<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
						<th data-halign="center" data-align="center" data-sortable="false" data-field="id">id</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="40px" data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-field="a" data-width="" data-formatter="FMT_file">附件</th>
						<th data-halign="center" data-align="left" data-sortable="true" data-field="b" data-width="">项目名称</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-field="c" data-width="" data-formatter="">物料编码</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-field="d" data-width="">物料名称</th>
						<th data-halign="center" data-align="right" data-sortable="false" data-field="e" data-width="">数量</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-field="f" data-width="" data-formatter="">计量单位</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-field="g" data-width="" data-formatter="">需求日期</th>
						<th data-halign="center" data-align="left" data-sortable="false" data-field="h" data-width="" data-formatter="">中标厂家</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-field="i" data-width="" data-formatter="">中标日期</th>
						<th data-halign="center" data-align="center" data-sortable="true" data-field="j" data-width="">图纸确认时间</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-field="" data-width="100px" data-formatter="FMT_handle">操作</th>
		            </tr>
		        </thead>
		    </table>
    	</div>
  	</div> <!-- maincontent -->
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.jsp"%>
<%@include file="../common/file_upload.jsp"%>
<%@include file="query_details.jsp" %>

<script src="query.js"></script>
<script src="list.js"></script>

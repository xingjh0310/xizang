<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>履约评价</title>
<link rel="stylesheet" type="text/css"
	href="../../../common/bootstrap/css/bootstrap.min.css">
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<link href="../../../common/starRating/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
<script src="../../../common/starRating/js/star-rating.js" type="text/javascript"></script>

<link href="../../../common/starRating/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
<script src="../../../common/starRating/js/star-rating.js" type="text/javascript"></script>
</head>
<body style="background-color: #FCFCFC;">
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>合同管理</li>
                    <li class="active">履约评价</li>
				</ol>
			</h3>
		</div>
		<hr>
	<div id="maincontent" class="row-fluid">
			<!-- 按钮工具条开始 -->
			<div class="row-fluid col-md-12">
			<div id="tbar" class="btn-toolbar">
<!-- 				<div class="btn-group"> -->
<!-- 					<button type="button" id="query_add" class="btn btn-primary"> -->
<!-- 					   <div class="visible-md visible-lg"><i class="icon icon-plus"></i>&nbsp;新增</div> -->
<!-- 					   <div class="visible-xs visible-sm"><i class="icon icon-file-o"></i></div> -->
<!-- 					</button> -->
<!-- 				</div> -->
				<div class="btn-group">
					<button type="button" id="btn_del" class="btn btn-danger">
					   <div class="visible-md visible-lg"><i class="icon-trash"></i>&nbsp;批量删除</div>
					   <div class="visible-xs visible-sm"><i class="icon-trash"></i></div>
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
<!-- 				<label  class=" col-md-2  col-lg-2 col-sm-2"></label> -->
<!-- 				<div class="btn-group pull-right visible-lg visible-md visible-sm"> -->
<%-- 					<select id="select_supply" class="form-control" > --%>
<%-- 					</select> --%>
<!-- 				</div> -->
<!-- 				<div class="btn-group pull-right visible-lg visible-md visible-sm"> -->
<!-- 					<h5>供货厂商：</h5> -->
<!-- 				</div> -->
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
					<th data-halign="center" data-align="center" data-sortable="false" data-width="40px" data-formatter="FMT_Num">序号</th>
					<th data-halign="center" data-align="right" data-sortable="false" data-field="ACCESSORY" data-width="" data-formatter="FMT_file">附件</th>
					<th data-halign="center" data-align="left" data-sortable="false" data-field="CONTRACTNO" data-width="" data-formatter="">合同编号</th>
					<th data-halign="center" data-align="left" data-sortable="false" data-field="CONTRACTTITLE" data-width="" data-formatter="">合同标题</th>
					<th data-halign="center" data-align="left" data-sortable="false" data-field="ENGINEERNAME" data-width="" data-formatter="">工程名称</th>
					<th data-halign="center" data-align="center" data-sortable="false" data-field="MATERIALARRIVAL" data-width="" data-formatter="FMT_evaluate">物资到货</th>
					<th data-halign="center" data-align="center" data-sortable="false" data-field="PRODUCTQUALITY" data-width="" data-formatter="FMT_evaluate">产品质量</th>
					<th data-halign="center" data-align="center" data-sortable="false" data-field="FIELDSERVICE" data-width="" data-formatter="FMT_evaluate">现场服务</th>
					<th data-halign="center" data-align="center" data-sortable="false" data-field="MATERIALOPERATION" data-width="" data-formatter="FMT_evaluate">物资投运</th>
					<th data-halign="center" data-align="center" data-sortable="false" data-field="WARRANTYSITUATION" data-width="" data-formatter="FMT_evaluate">质保情况</th>
					<th data-halign="center" data-align="center" data-sortable="false" data-field="EVALUATION" data-width="" data-formatter="FMT_evaluate">总体评价</th>
					<th data-halign="center" data-align="center" data-sortable="false" data-field="ID" data-width="70px" data-formatter="FMT_handle">操作</th>
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
<%-- <%@include file="query_details.jsp" %> --%>
<%@include file="evaluate_details.jsp" %>

<script src="query.js"></script>
<script src="list.js"></script>

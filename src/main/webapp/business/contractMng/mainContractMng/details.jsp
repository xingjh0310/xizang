<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>主合同管理</title>
<style type="text/css">
	.breadcrumb{
		background-color: #FCFCFC !important;
	}
</style>
<link rel="stylesheet" type="text/css"
	href="../../../common/bootstrap/css/bootstrap.min.css">
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<link href="../../../common/starRating/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
<script src="../../../common/starRating/js/star-rating.js" type="text/javascript"></script>

<link href="../../../common/starRating/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
<script src="../../../common/starRating/js/star-rating.js" type="text/javascript"></script>
<link href="../common/css/contractDetail.css" media="all" rel="stylesheet" type="text/css"/>
</head>
<body style="background-color: #FCFCFC;">
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid row">
			<h3 class="text-primary">
				<div class=" col-md-11 col-xs-11 ">
					<ol class="breadcrumb">
						<li>合同管理</li>
	                    <li style="color:black;">合同详细</li>
					</ol>
				</div>
			</h3>
		</div>
		<hr>
	<div id="maincontent" class="row-fluid" style="margin-bottom: 20px;">
		<div class="row-fluid row" style="margin-right: -11px;margin-top: -5px; */">
			<div class="col-md-11 col-xs-11 ">
				<h3 style="font-size:14px;"><i class="icon icon-tags"></i>&nbsp;合同详细</h3>
			</div>
			<div class="col-md-1 col-xs-1" style="padding-top: 5px;">
				<button type="button" id="back" onclick="goBack()" class="btn  btn-xs btn-primary">
					<div class="visible-md visible-lg">
						<i class="icon icon-home"></i>&nbsp;返&nbsp;&nbsp;回
					</div>
					<div class="visible-xs visible-sm">
						<i class="icon icon-home"></i>
					</div>
				</button>
			</div>
		</div>
<!-- 		<div class="btn-toolbar" style="margin-left: 2px;"> -->
<!-- 			<div style="float:left"> -->
<!-- 				<h3 style="font-size:14px;"><i class="icon icon-tags"></i>&nbsp;物资明细</h3> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<table class="table table-bordered" style="margin-bottom: 0px;">
			<tr>
				<td
					style="width: 10%; text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>合同标题：</lable>
				</td>
				<td style="width: 40%; text-align: left"><lable
					style="margin-left: 15px;" id="CONTRACTTITLE_detail"></lable></td>
				<td
					style="width: 10%; text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>合同编号：</lable>
				</td>
				<td style="width: 40%;text-align: left"><lable
						style="margin-left: 15px;" id="CONTRACTNO_detail"></lable></td>
			</tr>
			<tr>
				<td
					style="text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>需求单位：</lable>
				</td>
				<td style="text-align: left"><lable
					style="margin-left: 15px;" id="DEMANDUNIT_detail"></lable></td>
				<td
					style="text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>合同状态：</lable>
				</td>
				<td style="text-align: left"><lable
					style="margin-left: 15px;" id="CONTRACTSTATE_detail"></lable></td>
			</tr>
			<tr>
				<td
					style="text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>所属工程：</lable>
				</td>
				<td style="text-align: left"><lable
					style="margin-left: 15px;" id="ENGINEERNAME_detail"></lable></td>
				<td
					style="text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;段：</lable>
				</td>
				<td style="text-align: left"><lable
					style="margin-left: 15px;" id="SECTION_detail"></lable></td>
			</tr>
			<tr>
				<td
					style="text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>供货厂商：</lable>
				</td>
				<td style="text-align: left"><lable
					style="margin-left: 15px;" id="SUPPLYFULLNAME_detail"></lable></td>
				<td
					style="text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>合同金额：</lable>
				</td>
				<td style="text-align: left"><lable
					style="margin-left: 15px;" id="CONTRACTAMOUNT_detail"></lable></td>
			</tr>
			<tr>
				<td
					style="text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>招标日期：</lable>
				</td>
				<td style="text-align: left"><lable
					style="margin-left: 15px;" id="BIDDEDATE_detail"></lable></td>
				<td
					style="text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>签订日期：</lable>
				</td>
				<td style="text-align: left"><lable
					style="margin-left: 15px;" id="SIGNDATE_detail"></lable></td>
			</tr>
			<tr>
				<td
					style="text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>合同日期：</lable>
				</td>
				<td style="text-align: left"><lable
					style="margin-left: 15px;" id="CONTRACTDATE_detail"></lable></td>
				<td
					style="text-align: right; font-weight: bold; background: #f9f9f9">
					<lable>供货日期：</lable>
				</td>
				<td style="text-align: left"><lable
					style="margin-left: 15px;" id="SUPPLYDATE_detail"></lable></td>
			</tr>
			<tr>
				<td
					style="text-align: right; font-weight: bold; background: #f9f9f9;">
					<lable>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</lable>
				</td>
				<td colspan="3"><lable style="margin-left: 15px;"
					id="REMARK_detail"></lable></td>
			</tr>
		</table>

		<div class="btn-toolbar" style="margin-left: 2px;">
			<div style="float:left">
				<h3 style="font-size:14px;"><i class="icon icon-tags"></i>&nbsp;物资明细</h3>
			</div>
		</div>
		<table class="table table-bordered" style="margin-bottom:0px;">
			<thead>
				<tr>
					<th style="text-align: center;width:10%;background-color: #f9f9f9;">序号</th>
					<th style="text-align: center;width:15%;background-color: #f9f9f9;">物料名称</th>
					<th style="text-align: center;width:9%;background-color: #f9f9f9;">计量单位</th>
					<th style="text-align: center;width:9%;background-color: #f9f9f9;">规格型号</th>
					<th style="text-align: center;width:9%;background-color: #f9f9f9;">数量</th>
					<th style="text-align: center;width:9%;background-color: #f9f9f9;">单价（元）</th>
					<th style="text-align: center;width:9%;background-color: #f9f9f9;">总价（万元）</th>
					<th style="text-align: center;width:30%;background-color: #f9f9f9;">货物描述</th>
				</tr>
			</thead>
			<tbody id="detail_body">
			</tbody>
		</table>
		<div class="btn-toolbar" style="margin-left: 2px;">
			<div style="float:left">
	 			<h3 style="font-size:14px;"><i class="icon icon-tags"></i>&nbsp;履约评价</h3>
			</div>
		</div>
		<div id="noneEvaluate" class="alert alert-danger">待评价</div>
		<table class="table table-bordered" id="evaluate_table" style="margin-bottom: 0px;">
			<tbody>
				<tr style="max-height: 53px;">
					<td style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9;line-height: 76px;">
						<lable>物资到货：</lable>
					</td>
					<td style="text-align: left">
						<lable style="margin-left: 15px;">
							<input id="MATERIALARRIVAL_evaDetail" name="" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" >
						</lable>
					</td>
					<td style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9;line-height: 76px;">
						<lable>产品质量：</lable>
					</td>
					<td style="text-align: left">
						<lable style="margin-left: 15px;">
							<input id="PRODUCTQUALITY_evaDetail" name="" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" >
						</lable>
					</td>
				</tr>
				<tr>
					<td style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9;line-height: 76px;">
						<lable>现场服务：</lable>
					</td>
					<td style="text-align: left">
						<lable style="margin-left: 15px;">
							<input id="FIELDSERVICE_evaDetail" name="" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" >
						</lable>
					</td>
<!-- 					<td style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9;line-height: 76px;"> -->
<!-- 						<lable>物资投运：</lable> -->
<!-- 					</td> -->
<!-- 					<td style="text-align: left"> -->
<!-- 						<lable style="margin-left: 15px;"> -->
<!-- 							<input id="MATERIALOPERATION_evaDetail" name="" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" > -->
<!-- 						</lable> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9;line-height: 76px;"> -->
<!-- 						<lable>质保情况：</lable> -->
<!-- 					</td> -->
<!-- 					<td style="text-align: left"> -->
<!-- 						<lable style="margin-left: 15px;"> -->
<!-- 							<input id="WARRANTYSITUATION_evaDetail" name="" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" > -->
<!-- 						</lable> -->
<!-- 					</td> -->
					<td style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9;line-height: 76px;">
						<lable>总体评价：</lable>
					</td>
					<td style="text-align: left">
						<lable style="margin-left: 15px;">
							<input id="EVALUATION_evaDetail" value="" name="" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" >
						</lable>
					</td>
				</tr>
				<tr>
					<td style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9;">
						<lable>总体意见：</lable>
					</td>
					<td colspan="3"><lable style="margin-left: 15px;"
						id="EVAREMARK_detail"></lable></td>
				</tr>
			</tbody>
		</table>
	</div>
  </div> <!-- maincontent -->
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<script src="query.js"></script>
<%@include file="detailsjs.jsp"%>
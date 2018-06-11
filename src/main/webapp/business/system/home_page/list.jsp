	<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>信息维护</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<%@include file="../../../common/inc/treegrid.inc"%>
<link href="<%=basePath%>business/system/home_page/sty.css" rel="stylesheet"  type="text/css"/>
</head>
<body>
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="container-fluid" style="margin:16px 5px 5px 0px;">
			<div class="row">
				<div class="float_left first_container_div col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="first_plate_css">
						<span id="wuzi_img" class="float_left plate_img_css"/></span>
						<span class="float_left plate_font_css">
							需求清单
						</span>
						<a href="<%=basePath %>business/plan/need/need.jsp" class="demandPlanNub_span float_left plate_nub_css"></a>
					</div>
				</div>
				<div class="float_left first_container_div col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="first_plate_css">
						<img src="<%=basePath%>common/img_transparency/hetongzongjine.png" class="float_left plate_img_css"/>
						<span class="float_left plate_font_css">
							累计合同总金额
						</span>
						<a href="<%=basePath%>business/contractMng/mainContractMng/list.jsp" class="conTotalAmount_span float_left plate_nub_css"></a>
					</div>
				</div>
				<div class="float_left first_container_div col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="first_plate_css">
						<img src="<%=basePath%>common/img_transparency/jinrifahuoshu.png" class="float_left plate_img_css"/>
						<span class="float_left plate_font_css">
							到货交接单
						</span>
						<a href="#" class="float_left plate_nub_css">1</a>
					</div>
				</div>
				<div class="float_left first_container_div col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="first_plate_css">
						<img src="<%=basePath%>common/img_transparency/wuziwentihong.png" class="float_left plate_img_css"/>
						<span class="float_left plate_font_css">
							今日物资问题
						</span>
						<a href="<%=basePath %>business/problem_handling/list.jsp" class="questionByDateNub_span float_left plate_nub_css"></a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="container-fluid container_fluid_div_css container_title_css" style="margin-bottom:0px">
			<span id="daiban_img" class="title_img_css"></span>
			待办事项
			<a href="<%=basePath%>business/notice_reminding/list.jsp" title="通知">
				<span class="float_right inform_nub_css message_nub"></span>
				<i class="icon icon-bell icon-2x float_right" style="margin:12px 12px 0px 6px;"></i>
			</a> 
		</div>
		
		<div class="container-fluid" style="margin:0px 5px 5px 0px;">
			<div class="row">
				<div class="float_left second_container_div col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="second_plate_css">
						<a  href="<%=basePath%>business/plan/supply/supply.jsp" class="second_plate_count_css" style="margin-top:18px">
							供货计划
							<span class="float_right">(&nbsp;&nbsp;<span class="supplyNub_span"></span>&nbsp;&nbsp;)</span>
						</a>
						<a href="<%=basePath%>business/plan/arrival/arrival.jsp" class="second_plate_count_css">
							到货计划
							<span class="float_right">(&nbsp;&nbsp;<span class="arrivalNub_span"></span>&nbsp;&nbsp;)</span>
						</a>
					</div>
				</div>
				<div class="float_left second_container_div col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="second_plate_css">
						<a  href="<%=basePath%>business/contractMng/mainContractMng/list.jsp" class="second_plate_count_css" style="margin-top:18px">
							待执行合同
							<span class="float_right">(&nbsp;&nbsp;<span class="contractNub_span"></span>&nbsp;&nbsp;)</span>
						</a>
						<a href="<%=basePath%>business/transport/material/material.jsp" class="second_plate_count_css">
							物资到货
							<span class="float_right">(&nbsp;&nbsp;<span class="arrivalCount_span"></span>&nbsp;&nbsp;)</span>
						</a>
						
					</div>
				</div>
				<div class="float_left second_container_div col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="second_plate_css">
						<a  href="<%=basePath%>business/problem_handling/list.jsp" class="second_plate_count_css" style="margin-top:18px">
							待处理物资问题
							<span class="float_right">(&nbsp;&nbsp;<span class="questionNub_span"></span>&nbsp;&nbsp;)</span>
						</a>
						<a href="<%=basePath%>business/remind/list.jsp" class="second_plate_count_css">
							待处理通知消息
							<span class="float_right">(&nbsp;&nbsp;<span class="message_nub"></span>&nbsp;&nbsp;)</span>
						</a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="container-fluid container_fluid_div_css container_title_css" style="margin-bottom:0px";>
			<span id="yunshu_img" class="title_img_css"></span>
			物资需求清单统计图
		</div>
		<div class="btn-toolbar">
		<div class="col-md-3 col-sm-12 col-xs-12"></div>
		<div class="col-md-3 col-sm-12"></div>
		</div>
		<div class="container_fluid_div_css echar_div_css" id="echa">
			   <div class="col-md-6 col-sm-12 col-xs-12" style="position:relative;top:33px;z-index:333;">
				<div class="col-md-4 col-sm-4 col-xs-4">
					<label class="col-md-4 col-sm-4 col-xs-4" style="margin-top:6px;">年：</label>
					<select id="year_need" class="form-control col-md-8 col-sm-8 col-xs-8" style="width:60%;margin-left:-10%;border-radius:4px;color:#ccc">
						
					</select>
				</div>	
				<div class="col-md-4 col-sm-4 col-xs-4" style="padding-left:0px;margin-left:-30px">
				<label class="col-md-4 col-sm-4 col-xs-4" style="margin-top:6px;">月：</label>
				<select id="mon_need" class="form-control col-md-8 col-sm-8 col-xs-8" style="width:60%;margin-left:-10%;border-radius:4px;color:#ccc">
					<option value="">全部</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>
				</div>
				<div class="col-md-4 col-sm-4 col-xs-4" style="padding-left:0px;margin-left:-30px">
					<label class="col-md-6 col-sm-6 col-xs-6" style="margin-top:6px;">物资：</label>
					<select id="mate" class="form-control col-md-6 col-sm-6 col-xs-6" style="width:60%;margin-left:-20%;border-radius:4px;color:#ccc">
						<option value="">全部</option>
					</select>
				</div>
			</div>
			<div id="echar" style="width:100%;height:328px"></div>
		</div>
	 <div>
		<div class="container-fluid container_fluid_div_css container_title_css" style="margin-bottom:0px">
			<span id="yunshu_img" class="title_img_css"></span>
			物资到货情况统计图
		</div>
		<div class="btn-toolbar">
		<div class="col-md-3 col-sm-12 col-xs-12"></div>
		<div class="col-md-3 col-sm-12"></div>
		</div>
		
			<div class="container_fluid_div_css echar_div_css" id="echa1" style="width:70.25%;display:inline-block"">
				<div class="col-md-6 col-sm-12 col-xs-12" style="position:relative;top:33px;z-index:333;">
					<div class="col-md-4 col-sm-4 col-xs-4">
						<label class="col-md-4 col-sm-4 col-xs-4" style="margin-top:6px;">年：</label>
						<select id="year_arrive" class="form-control col-md-8 col-sm-8 col-xs-8" style="width:60%;margin-left:-9%;border-radius:4px;color:#ccc">
							
						</select>
					</div>	
					<div class="col-md-4 col-sm-4 col-xs-4" style="padding-left:0px;margin-left:-30px">
					<label class="col-md-4 col-sm-4 col-xs-4" style="margin-top:6px;">月：</label>
					<select id="mon_arrive" class="form-control col-md-8 col-sm-8 col-xs-8" style="width:60%;margin-left:-15px;border-radius:4px;color:#ccc">
						<option value="">全部</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4" style="padding-left:0px;margin-left:-38px">
						<label class="col-md-6 col-sm-6 col-xs-6" style="margin-top:6px;margin-left:6px;">物资：</label>
						<select id="arrive" class="form-control col-md-6 col-sm-6 col-xs-6" style="width:60%;margin-left:-18%;border-radius:4px;color:#ccc">
							<option value="">全部</option>
						</select>
					</div>
				</div>
				<div id="echar1" style="width:100%;height:328px">
					
				
				</div>
			</div>
			<div class="container_fluid_div_css echar_div_css" id="echa2" style="width: 28.25%; height: 0px;float:right">
		   <div class="container-fluid container_fluid_div_css container_title_css" style="background:none">
			<span id="yunshu_img" class="title_img_css"></span>
			物资问题统计图
		  </div>
			<div id="echar2" style="width:100%;height:100%"></div>
		</div>
	  </div>
			<div class="container-fluid container_fluid_div_css container_title_css" style="margin-top:0px">
				<span id="yunshu_img" class="title_img_css"></span>
				物资退料退库情况统计图
		 	 </div>
		 	<div class="btn-toolbar">
			<div class="col-md-3 col-sm-12 col-xs-12"></div>
			<div class="col-md-3 col-sm-12"></div>
			</div>
		<div class="container_fluid_div_css echar_div_css" id="echa">
			   <div class="col-md-6 col-sm-12 col-xs-12" style="position:relative;top:33px;z-index:333;">
				<div class="col-md-4 col-sm-4 col-xs-4">
					<label class="col-md-4 col-sm-4 col-xs-4" style="margin-top:6px;">年：</label>
					<select id="year_ret" class="form-control col-md-8 col-sm-8 col-xs-8" style="width:60%;margin-left:-10%;border-radius:4px;color:#ccc">
						
					</select>
				</div>	
				<div class="col-md-4 col-sm-4 col-xs-4" style="padding-left:0px;margin-left:-30px">
				<label class="col-md-4 col-sm-4 col-xs-4" style="margin-top:6px;">月：</label>
				<select id="mon_ret" class="form-control col-md-8 col-sm-8 col-xs-8" style="width:60%;margin-left:-10%;border-radius:4px;color:#ccc">
					<option value="">全部</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>
				</div>
				<div class="col-md-4 col-sm-4 col-xs-4" style="padding-left:0px;margin-left:-30px">
					<label class="col-md-6 col-sm-6 col-xs-6" style="margin-top:6px;">物资：</label>
					<select id="mate_ret" class="form-control col-md-6 col-sm-6 col-xs-6" style="width:60%;margin-left:-20%;border-radius:4px;color:#ccc">
						<option value="">全部</option>
					</select>
				</div>
			</div>
			<div id="echar3" style="width:100%;height:328px"></div>
		</div>
		
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<script type="text/javascript" src="<%=basePath%>common/eCharts/echarts.min.js"></script>
<script src="<%=basePath%>business/system/home_page/list.js"></script>
<script src="<%=basePath%>business/system/home_page/echars.js"></script>
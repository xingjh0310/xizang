<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>需求清单</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<link href="css/need.css" media="all" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>计划管理</li>
                    <li class="content_top_title_black">需求清单</li>
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
						<button class="btn btn-primary" id="btn_into" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-cloud-download"></i>&nbsp;导入</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-cloud-download"></i></div>
						</button>
					</div>
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
							<input type="text" id="searchInput" autofocus="autofocus" class="form-control" placeholder="输入关键字进行模糊查询"> 
							<span class="input-group-btn">
								<button class="btn btn-primary" id="btn_ref" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
								</button>
							</span>
						</div>
					</div>
<!-- 					<div class="btn-group pull-right visible-lg visible-md visible-sm"> -->
<%-- 						<select id="" class="form-control"> --%>
<!-- 							<option value="">请选择</option> -->
<%-- 						</select> --%>
<!-- 					</div> -->
<!-- 					<div class="btn-group pull-right visible-lg visible-md visible-sm"> -->
<!-- 						<h5>需求单位：</h5> -->
<!-- 					</div> -->
<!-- 					<div class="btn-group pull-right visible-lg visible-md visible-sm"> -->
<%-- 						<select id="" class="form-control"> --%>
<!-- 							<option value="">请选择</option> -->
<%-- 						</select> --%>
<!-- 					</div> -->
<!-- 					<div class="btn-group pull-right visible-lg visible-md visible-sm"> -->
<!-- 						<h5>项目类型：</h5> -->
<!-- 					</div> -->
				</div>
			</div><!-- 按钮工具条结束 -->
			<table id="tbinfo" class="table-condensed table-hover table-cursor">
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="right"  data-sortable="false" data-width="50" data-field="FILENUB" data-formatter="FMT_Accessory">附件</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="PLANCODE">计划编号</th>
						<th data-halign="center" data-align="left"  data-sortable="false" data-width="" data-field="PROJECTNAME">项目名称</th>
						<!-- <th data-halign="center" data-align="left"   data-sortable="false" data-width="" data-field="MATERIELNAME">物料名称</th>
						<th data-halign="center" data-align="center"   data-sortable="false" data-width=""   data-field="MATERIALNORMS">物料规格</th>
						<th data-halign="center" data-align="right"   data-sortable="false" data-width="" data-field="MATERIELNUM">数量</th>
						<th data-halign="center" data-align="center"  data-sortable="false" data-width="" data-field="MATERIELUNIT">计量单位</th> -->
						<th data-halign="center" data-align="left" data-sortable="false" data-width="" data-field="DEMANDUNIT">需求单位</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="PLANDATE">需求日期</th>
						<th data-halign="center" data-align="right"  data-sortable="false" data-width="50" data-field="ESTIMATETOTALPRICE">估算总价(万)</th>
						<th data-halign="center" data-align="left"  data-sortable="false" data-width="" data-field="DEMANDPLACE">需求地点</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="LINKMAN">联系人</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="" data-field="LINKPHONE">联系电话</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="140" data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>
					
				
		</div><!-- maincontent -->
	</div>
	
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="need_edit.jsp"%>
<%@include file="need_info.jsp"%>
<%@include file="upload.jsp"%>
<%@include file="upload_photo.jsp"%>
<script src="need_def.js"></script> 
<script src="need_list.js"></script>
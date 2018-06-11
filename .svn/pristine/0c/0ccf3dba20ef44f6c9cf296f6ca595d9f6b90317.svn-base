<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>问题处理</title>
<%@include file="../../../../common/inc/inc.inc"%>
<%@include file="../../../../common/inc/bootstrapTable.inc"%>
<script type="text/javascript" src="<%=basePath %>common/js/ztree/js/jquery.ztree.all.js"></script>
<link rel="stylesheet" href="<%=basePath %>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" 	href="css.css">

</head>
<body>
	<div class="container-fluid" >
		<%@include file="../../../../common/inc/top.jsp"%>
		<div class="row-fluid top-title">
			<h3 class="text-primary">
				<ol class="breadcrumb" >
					<li>物资问题</li>
                    <li class="content_top_title_black">问题处理</li>
				</ol>
			</h3>
		</div>
		<hr class="hr-title">
		<div style="display: none;">
		</div>
	<div id="maincontent" class="row-fluid top-body">
		<div class="row-fluid col-md-12 top-toolbar">
            
			<!-- 按钮工具条开始 -->
			<div id="tbar" class="btn-toolbar">
				<!-- <div class="btn-group">
					<button type="button" id="btn_add" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-plus"></i>&nbsp;问题处理</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-file-o"></i></div>
					</button>
				</div> -->
				<div class="btn-group">
					<button type="button" class="btn btn-primary bt-s" id="btn_out_page">
					   <div class="visible-md visible-lg"><i class="icon icon-circle-arrow-up"></i>&nbsp;导出当前页</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-circle-arrow-up"></i></div>
					</button>
				</div>
				<div class="btn-group">
					<button type="button" class="btn btn-primary" id="btn_out">
					   <div class="visible-md visible-lg"><i class="icon icon-reply"></i>&nbsp;导出全部</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-reply"></i></div>
					</button>
				</div>
				<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6 question_query_div">
					<div class="input-group ">
						<input type="text" id="searchName" class="form-control" autofocus="autofocus" placeholder="输入关键字进行模糊查询" maxlength="20"> 
						<span class="input-group-btn">
							<button class="btn btn-primary" id="btn_ref" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
							</button>
						</span>
					</div>
				</div>
                <div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select id="question_state" class="form-control">
							<option value="">请选择</option>
							<option value="0">未处理</option>
							<option value="1">已处理</option>
						</select>
				</div>
				<div class="btn-group pull-right visible-lg visible-md visible-sm">
					<h5>状态：</h5>
				</div>
                <div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select id="material_type" class="form-control">
							<option value="">请选择</option>
						</select>
				</div>
				<div class="btn-group pull-right visible-lg visible-md visible-sm">
					<h5>物资类型：</h5>
				</div>
				<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select id="question_type" class="form-control">
							<option value="">请选择</option>
						</select>
				</div>
				<div class="btn-group pull-right visible-lg visible-md visible-sm">
					<h5>问题类型：</h5>
				</div>
				
             </div>
			 </div>
			<!-- 按钮工具条结束 -->
			<table id="tbinfo" class="table-condensed table-hover table-cursor" >
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50"  data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
                        <th data-halign="center" data-align="center" data-sortable="false" data-width="50"  data-formatter="FMT_Num">  序号</th>
                        <th data-halign="center" data-align="left" data-sortable="false"  data-width=""  data-field="TITLE" >  标题</th> 
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="" data-field="MATERIELNAME" >  物资类型</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width=""  data-field="SUBDICTNAME">  问题类型</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width=""  data-field="DEPTNAME">  上报单位</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="80" data-field="REPORTTIME" >  上报时间</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width=""  data-field="DICTNAME">  处理方式</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="80"  data-field="PROCESSTIME">  处理时间</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="60"  data-field="STAFFNAME">  处理人</th>
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="50"  data-field="STATE"  data-formatter="FMT_State">  状态</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="200" data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>
			
        </div>   
        
	  </div> <!-- maincontent -->
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.jsp"%>
<%@include file="info.jsp"%>
<script src="def.js"></script>
<script src="list.js"></script>

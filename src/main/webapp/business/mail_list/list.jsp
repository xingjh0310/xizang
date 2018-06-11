<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>人员查询</title>
<%@include file="../../../../common/inc/inc.inc"%>
<%@include file="../../../../common/inc/bootstrapTable.inc"%>
<link rel="stylesheet" href="<%=basePath %>common/zui/lib/uploader/zui.uploader.min.css" type="text/css">
<link rel="stylesheet" href="<%=basePath %>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" 	href="css.css">
</head>

<body>
	<div class="container-fluid" >
		<%@include file="../../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb" >
					<li>通讯录</li>
                    <li class="content_top_title_black">人员查询</li>
				</ol>
			</h3>
		</div>
		<hr>
		<div style="display:none;">
			treeCode<input type="text" class="form-control" id="code" name="">
			searchCode<input type="text" class="form-control" id="searchCode" name="">
			name<input type="text" class="form-control" id="baseName" name="">
		</div>
	<div id="maincontent" class="row-fluid ">
		
		<div class="row-fluid col-md-10 pull-right ">
            <div class="row-fluid col-md-12">
			<!-- 按钮工具条开始 -->
			<div id="tbar" class="btn-toolbar tbar">
				<div class="btn-group btnMarginLeft">
					<button type="button" id="btn_add" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增人员</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-plus-sign"></i></div>
					</button>
				</div>
				
<!-- 				<div class="btn-group"> -->
<!-- 					<button type="button" id="btn_into" class="btn btn-primary"> -->
<!-- 					   <div class="visible-md visible-lg"><i class="icon icon-cloud-download"></i>&nbsp;导入</div> -->
<!-- 					   <div class="visible-xs visible-sm"><i class="icon icon-cloud-download"></i></div> -->
<!-- 					</button> -->
<!-- 				</div> -->
				<div class="btn-group">
					<button type="button" id="btn_out_page" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-circle-arrow-up"></i>&nbsp;导出当前页</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-circle-arrow-up"></i></div>
					</button>
				</div>
				<div class="btn-group">
					<button type="button" id="btn_out" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-reply"></i>&nbsp;导出全部</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-reply"></i></div>
					</button>
				</div>
				<div class="btn-group">
					<button type="button" id="btn_del" class="btn btn-danger">
					   <div class="visible-md visible-lg"><i class="icon-trash"></i>&nbsp;批量删除</div>
					   <div class="visible-xs visible-sm"><i class="icon-trash"></i></div>
					</button>
				</div>
				<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
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
						<select id="select_flag" class="form-control">
							<option value="">请选择</option>
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
				</div>
				<div class="btn-group pull-right visible-lg visible-md visible-sm">
					<h5>性别：</h5>
				</div>
                <input type="hidden" id="equipmentTypeNm" class="form-control">
                <input type="hidden" id="zTreeData" class="form-control">
             </div>
			</div>
			<!-- 按钮工具条结束 -->
			<table id="tbinfo" class="table-condensed table-hover table-cursor" >
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50"  data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
                        <th data-halign="center" data-align="center" data-sortable="false" data-width="50"  data-formatter="FMT_Num">  序号</th>
                        <th data-halign="center" data-align="left" data-sortable="false"  data-width="" data-field="STAFFNAME">  姓名</th>
                        <th data-halign="center" data-align="left" data-sortable="true"  data-width=""  data-field="POSITION" >  职称</th> 
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="50" data-field="SEX" data-formatter="FMT_Sex">  性别</th>
                        <th data-halign="center" data-align="left" data-sortable="false"  data-width=""  data-field="LINKPHONE">  联系电话</th>
                        <th data-halign="center" data-align="left" data-sortable="false"  data-width=""  data-field="EMAIL">  邮箱</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="140" data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>
			
        </div>   
        
         <div class="btn-group btnMarginLeft">
					<button type="button" id="addParent" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增根节点</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-plus-sign"></i></div>
					</button>
		</div> 
        
        <div class="row-fluid col-md-2">
        <div id="bc" class="bcg" style="margin-top: -3px;" >
        	<div class="ztree-sty">
        		<ul id="treeDemo" class="ztree" ></ul>
        	</div>
        </div>
        </div>
	  </div> <!-- maincontent -->
	</div>
	
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.jsp"%>
<%@include file="details.jsp"%>
<%@include file="upload.jsp"%>
<script type="text/javascript" src="<%=basePath %>common/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=basePath %>common/zui/lib/uploader/zui.uploader.min.js"></script>
<script src="def.js"></script>
<script src="list.js"></script>

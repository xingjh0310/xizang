<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>物料基础信息</title>
<%@include file="../../../../common/inc/inc.inc"%>
<%@include file="../../../../common/inc/bootstrapTable.inc"%>
<link rel="stylesheet" href="<%=basePath %>common/zui/lib/uploader/zui.uploader.min.css" type="text/css">
<link rel="stylesheet" href="<%=basePath %>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">

</head>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
<body>
	<div class="container-fluid" >
		<%@include file="../../../../common/inc/top_s.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb" >
					<li>基础信息</li>
                    <li class="active">物料基础信息</li>
				</ol>
			</h3>
		</div>
		<hr>
<div style="display: none;">
	
</div>
	<div id="maincontent" class="row-fluid">
		
		<div class="row-fluid col-md-10 pull-right">
            <div class="row-fluid col-md-12 ">
			<!-- 按钮工具条开始 -->
			<div id="tbar" class="btn-toolbar">
			
			<div style="display:none ;">
						treeCode<input type="text" class="form-control" id="code" name="">
						searchCode<input type="text" class="form-control" id="searchCode" name="">
						name<input type="text" class="form-control" id="baseName" name="">
		    </div>
		  
				<div class="btn-group btnMarginLeft">
					<button type="button" id="btn_add" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-plus"></i>&nbsp;新增物料</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-file-o"></i></div>
					</button>
				</div>
				<div class="btn-group">
					<button type="button" id="btn_del" class="btn btn-danger">
					   <div class="visible-md visible-lg"><i class="icon-trash"></i>&nbsp;批量删除</div>
					   <div class="visible-xs visible-sm"><i class="icon-trash"></i></div>
					</button>
				</div>
				&nbsp;&nbsp;
<!-- 				<div class="btn-group"> -->
<!-- 					<button type="button" id="btn_into" class="btn btn-primary"> -->
<!-- 					   <div class="visible-md visible-lg"><i class="icon icon-share-alt"></i>&nbsp;导入</div> -->
<!-- 					   <div class="visible-xs visible-sm"><i class="icon icon-share-alt"></i></div> -->
<!-- 					</button> -->
<!-- 				</div> -->
				<div class="btn-group">
					<button type="button" id="btn_out_page" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-reply"></i>&nbsp;导出当前页</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-reply"></i></div>
					</button>
				</div>
				<div class="btn-group">
					<button type="button" id="btn_out" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-reply"></i>&nbsp;导出全部</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-reply"></i></div>
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
						<select id="materiel_unit" class="form-control" >
							
						</select>
				</div>
				<div class="btn-group pull-right visible-lg visible-md visible-sm">
					<h5>计量单位：</h5>
				</div>
                <input type="hidden" id="equipmentTypeNm" class="form-control">
                <input type="hidden" id="zTreeData" class="form-control">
             </div>
			</div>
            <br>
            <br>
            
			<!-- 按钮工具条结束 -->
			<table id="tbinfo" class="table-condensed table-hover table-cursor" >
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="50"  data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
                        <th data-halign="center" data-align="center" data-sortable="false" data-width="50"  data-formatter="FMT_Num">  序号</th>
                        <th data-halign="center" data-align="center" data-sortable="true"  data-width="150" data-field="MATERIELNAME">  物料名称</th>
                        <th data-halign="center" data-align="center" data-sortable="true"  data-width="150"  data-field="MATERIALNORMS" >  物料规格</th> 
                        <th data-halign="center" data-align="center" data-sortable="true"  data-width="100" data-field="UNIT" >  计量单位</th>
                        <th data-halign="center" data-align="center" data-sortable="true"  data-width="100" data-field="PRICE" >  物料单价</th>
                        <th data-halign="center" data-align="left" data-sortable="true"  data-width=""  data-field="MATERIELDESC">物料描述</th>
						<th data-halign="center" data-align="center" data-sortable="false" data-width="130" data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>
			
        </div> 
        <div class="btn-group btnMarginLeft">
					<button type="button" id="addParent" class="btn btn-primary">
					   <div class="visible-md visible-lg"><i class="icon icon-plus"></i>&nbsp;新增根节点</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-file-o"></i></div>
					</button>
		</div> 
        <div class="row-fluid col-md-2">
        	<div class="ztree-sty">
        		<ul id="treeDemo" class="ztree" ></ul>
        	</div>
        </div>
	  </div> <!-- maincontent -->
	</div>
	
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="materiel_edit.jsp"%>
<%@include file="materiel_upload.jsp"%>
<%@include file="material_details.jsp"%>
<script type="text/javascript" src="<%=basePath %>common/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=basePath %>common/zui/lib/uploader/zui.uploader.min.js"></script>
<script src="materiel_def.js"></script>
<script src="materiel_list.js"></script>

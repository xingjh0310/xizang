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
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top_s.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>功能列表</li>
					<li class="active">人员信息管理</li>
				</ol>
			</h3>
		</div>
		<div style="display: none;">
			S_TreenmSysDept<input type="text" class="form-control"
				id="S_TreenmSysDept">
			_treenmSysDept<input type="text" class="form-control"
				id="_treenmSysDept">
		</div>
		<div id="maincontent" class="row-fluid">
			<div class="row-fluid col-md-3">
					<ul class="nav nav-tabs">
						<li class="active"><a data-tab
							href="#tabContent_TreenmSysDept"
							onclick="Select_TreenmSysDept('')">单位部门 <i
								class="icon icon-refresh"></i></a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane  active " id="tabContent_TreenmSysDept">
							<table id="tbinfo_TreenmSysDept" class="table-hover">
							</table>
						</div>
					</div>
			</div>

		<div class="row-fluid col-md-9">
			<div id="tbar" class="btn-toolbar">
				<div class="btn-group">
							<button type="button" id="btn_add" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-file-o"></i>&nbsp;新增
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-file-o"></i>
								</div>
							</button>
						</div>
						<div class="btn-group">
							<button type="button" id="btn_del" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-times"></i>&nbsp;删除
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-times"></i>
								</div>
							</button>
						</div>
						<div class="btn-group">
							<button type="button" id="export_current" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-times"></i>&nbsp;导出当前页
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-times"></i>
								</div>
							</button>
						</div>
						<div class="btn-group">
							<button type="button" id="export_whole" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-times"></i>&nbsp;导出全部
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-times"></i>
								</div>
							</button>
						</div>
						<!-- <div class="btn-group">
							<button type="button" id="batch_Import" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-times"></i>&nbsp;批量导入
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-times"></i>
								</div>
							</button>
						</div> -->
						<div class="btn-group">
							<button type="button" id="roleRefStaff" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-times"></i>&nbsp;角色配置人员
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-times"></i>
								</div>
							</button>
						</div>
				<div
					class="btn-group pull-right col-lg-2 col-md-2 col-sm-2 col-xs-6">
					<div class="input-group ">
						<input type="text" id="searchName" class="form-control"
							placeholder="输入关键字进行模糊查询"> <span class="input-group-btn">
							<button class="btn" id="btn_ref" type="button">
								<div class="visible-md visible-lg">
									<i class="icon icon-search"></i>&nbsp;查询
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-search"></i>
								</div>
							</button>
						</span>
					</div>
				</div>

				<div class="btn-group pull-right visible-lg visible-md visible-sm">
					<select class="chosen-select form-control"
						data-placeholder="选择性别..." id="S_DictnmXingbie">
					</select>
				</div>

			</div>
			<br>
			<!-- 按钮工具条结束 -->
			<table id="SysStaffRefAcctTable" class="table-condensed table-hover" style="width:1150px;">
				<thead>
					<tr>
						<th data-halign="center" data-align="center" data-sortable="false"
							data-field="state" data-checkbox="true"
							data-formatter="FMT_Check"></th>
						<th data-halign="center" data-align="center" data-sortable="false"
							data-width="60" data-formatter="FMT_Num">序号</th>
						<th data-halign="center" data-align="center" 
							data-sortable="true" data-visible="false"
							data-field="CODE" data-width="200">人员编码</th>
						<th data-halign="center" data-align="center" data-sortable="false"
							data-field="SYSDEPTNAME" data-visible="false"
							 data-width="150">单位部门</th>
					    <th data-halign="center" data-align="center" data-sortable="false"
							data-field="PHONE" data-visible="false"
							data-width="150">手机号码</th>
						<th data-halign="center" data-align="center" 
							data-sortable="false" data-visible="false"
							data-width="80" data-formatter="FMT_SysFlag">系统内置</th>
						<th data-halign="center" data-align="center" 
							data-sortable="true" data-visible="false"
							data-field="MEMO" class="visible-md visible-lg">备注</th>
						<th data-halign="center" data-align="center" 
							data-sortable="false" data-visible="false"
							data-width="80" data-formatter="FMT_Flag">状态</th>
					    <th data-halign="center" data-align="center" 
					    	data-sortable="false" data-visible="false"
							data-width="80" data-formatter="FMT_isLogin">是否允许登录</th>
					    <th data-halign="center" data-align="center" 
							data-sortable="true" data-visible="false"
							data-field="LOGINTYPE" data-width="150">登录类型</th>
						<th data-halign="center" data-align="center" data-sortable="true"
							data-field="VALIDITYTIME" data-visible="false"
							data-width="150">有效期</th>
						<th data-halign="center" data-align="center" data-sortable="false"
							data-field="ACCTTYPE" data-visible="false"
							data-width="150">账户类型</th>
						<th data-halign="center" data-align="center" data-sortable="false"
							data-field="DEPTNM" data-visible="false"
							data-width="150">部门内码</th>
						
						<th data-halign="center" data-align="center" data-sortable="true"
							data-field="FILESIZE" data-width="200" data-formatter="FMT_FILENAME">附件</th>
						<th data-halign="center" data-align="center" data-sortable="true"
							data-field="STAFFNAME" data-width="200">人员名称</th>
						<th data-halign="center" data-align="center" data-sortable="false"
							data-field="SEX" data-width="150">性别</th>
						<th data-halign="center" data-align="center" data-sortable="true"
							data-field="ACCTNAME" data-width="200">手机号</th>
						<th data-halign="center" data-align="center" data-sortable="true"
							data-field="DUTY" data-width="100">职务</th>
						<th data-halign="center" data-align="left" 
							data-sortable="true" 
							data-field="DEPTNAME" data-width="800">部门</th>
						<th data-halign="center" data-align="left" 
							data-sortable="true" data-formatter="Fmt_roleName"
							data-field="ROLE_NAME" data-width="">角色名称</th>
						<th data-halign="center" data-align="left" 
							data-sortable="true" data-formatter="Fmt_engineerName"
							data-field="ENGINEER_NAME" data-width="30">工程名称</th>
						<th data-halign="center" data-align="center"
							data-sortable="false" data-width="800" 
							data-formatter="FMT_Oper">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
  </div>
</body>
</html>
<script type="text/javascript">
var array_=new Array();
</script>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.inc"%>
<%@include file="roleInfo.jsp"%>
<%@include file="engineer_detail.jsp"%>
<%@include file="/common/import/importInfo.inc"%>  <!-- 导入表单 -->
<%@include file="/business/system/sysDept/deptTree.jsp"%>
<script src="<%=basePath%>business/system/sysDept/def_q.js"></script>
<script src="<%=basePath%>business/system/sysDict/def_q.js"></script>
<script src="def.js"></script>
<script src="<%=basePath%>common/import/importInfo.js"></script> <!-- 导入方法 -->
<script src="list.js"></script>

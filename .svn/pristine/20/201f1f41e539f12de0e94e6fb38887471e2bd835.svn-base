<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>信息维护</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top_s.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>功能列表</li>
					<li class="active">角色人员设置</li>
				</ol>
			</h3>
		</div>
		<div style="display: none;">
			S_SysStaff<input type="text" class="form-control" id="S_SysStaff">

		</div>
		<div id="maincontent" class="row-fluid">

			<div class="row-fluid col-md-4">
				<ul class="nav nav-tabs">
					<li class="active"><a data-tab
						href="#tabContent_ListnmSysStaff"
						onclick="Select_ListnmSysStaff('')">系统人员 <i
							class="icon icon-refresh"></i></a></li>
				</ul>
				<div class="tab-content">

					<div class="tab-pane  active " id="tabContent_ListnmSysStaff">
						<table id="tbinfo_ListnmSysStaff"
							class="table-condensed table-hover">
							<thead>
								<tr>
									<th data-halign="center" data-align="center"
										data-sortable="false" data-width="60"
										data-formatter="FMT_Num_ListnmSysStaff">序号</th>
									<th data-halign="center" data-visible="false"
										data-align="left" data-sortable="true"
										data-field="CODE" data-width="150">编码</th>
									<th data-halign="center" data-align="left" data-sortable="true"
										data-field="NAME">名称</th>
									<th data-halign="center" data-align="left" data-sortable="true"
										data-field="SYSDEPTNAME">所在部门</th>
									<th data-halign="center" data-align="center"
										data-sortable="false" data-width="50"
										data-formatter="FMT_Oper_ListnmSysStaff">过滤</th>
								</tr>
							</thead>
						</table>
					</div>

				</div>
			</div>

			<div class="row-fluid col-md-8">

				<!-- 按钮工具条开始 -->
				<div id="tbar" class="btn-toolbar">
						<div class="btn-group col-lg-2 col-md-2 col-sm-4 col-xs-6">
							<div class="input-group ">
								<input type="text" class="form-control" id="S_SysStaff_Name"
									readonly placeholder="请先选中人员，然后再授权">
							</div>
						</div>

						<div class="btn-group">
							<button type="button" id="btn_qx_all" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-times"></i>&nbsp;取消全部
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-times"></i>
								</div>
							</button>
						</div>

						<div class="btn-group">
							<button type="button" id="btn_sq_xz" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-check"></i>&nbsp;授权选中
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-check"></i>
								</div>
							</button>
						</div>

						<div class="btn-group">
							<button type="button" id="btn_qx_xz" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-times"></i>&nbsp;取消选中
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-times"></i>
								</div>
							</button>
						</div>
				</div>
				<br>
				<table id="tbinfo_ListnmSysRole" class="table-condensed table-hover">
					<thead>
						<tr>
							<th data-halign="center" data-align="center"
								data-sortable="false" data-field="state" data-checkbox="true"></th>
							<th data-halign="center" data-align="center"
								data-sortable="false" data-width="60"
								data-formatter="FMT_Num_ListnmSysRole">序号</th>
							<th data-halign="center" data-align="left" data-sortable="true"
								data-field="CODE" data-visible="false"
								data-width="150">编码</th>
							<th data-halign="center" data-align="center"
								data-sortable="false" data-width="60" 
								data-formatter="FMT_Oper_ListnmSysRole">授权状态</th>
							<th data-halign="center" data-align="left" data-sortable="true"
								data-field="NAME">名称</th>
							<th data-halign="center" data-align="center" 
								data-sortable="false" data-width="60" data-visible="false"
								data-formatter="FMT_Oper_Audit_Status">审核状态</th>
							<th data-halign="center" data-align="center" data-visible="false"
								data-sortable="false" data-width="60"
								data-formatter="FMT_Oper_Button">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<!-- maincontent -->
	</div>

</body>
<script type="text/javascript">
  var supadmin="${supadmin}";
  var admins="${admins}";
  var audmins="${audmins}";
</script>
</html>
<!-- 不要改变以下引用顺序 -->
<script src="<%=basePath%>business/system/sysStaff_sysRole/def_q.js"></script>
<script
	src="<%=basePath%>business/system/sysStaff_sysRole/role_def_q.js"></script>
<script src="def.js"></script>
<script src="list.js"></script>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<title>履约评价</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}
</style>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>&nbsp;&nbsp;&nbsp;合同管理</li>
					<li class="active">履约评价</li>
				</ol>
			</h3>
		</div>
		<hr>
		<div class="row-fluid">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<form class="form-horizontal" id="formRegistration">
					<!--隐藏字段  -->
					<div style="display: none;">
						id<input type="text" class="form-control" id="id"
							name="registrationFormBean.registrationInfoBean.id" value="0">
						bookTitle<input type="text" class="form-control" id="bookTitle"
							name="registrationFormBean.registrationInfoBean.bookTitles">
						linkManCode<input type="text" class="form-control" id="linkMan"
							name="registrationFormBean.registrationInfoBean.linkMan">
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">合&nbsp;&nbsp;同&nbsp;&nbsp;标&nbsp;&nbsp;题：</label>
						<div class="col-md-10 rowGroup">
							<div class="contract-title">XXXXXXXXXXXXXXXXXX</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span
							class="text-danger">*&nbsp;</span>物资到货评分：</label>
						<div class="col-md-3 rowGroup">
							<input type="text" class="form-control" id="linkTele"
								name="registrationFormBean.registrationInfoBean.linkTele"
								value="<s:property value='registrationFormBean.registrationInfoBean.linkTele' />"
								placeholder="">
						</div>
						<label class="col-md-2 control-label"><span
							class="text-danger">*&nbsp;</span>产品质量评分：</label>
						<div class="col-md-3 rowGroup">
							<input type="text" class="form-control" id="linkTele"
								name="registrationFormBean.registrationInfoBean.linkTele"
								value="<s:property value='registrationFormBean.registrationInfoBean.linkTele' />"
								placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span
							class="text-danger">*&nbsp;</span>现场服务评分：</label>
						<div class="col-md-3 rowGroup">
							<input type="text" class="form-control" id="linkTele"
								name="registrationFormBean.registrationInfoBean.linkTele"
								value="<s:property value='registrationFormBean.registrationInfoBean.linkTele' />"
								placeholder="">
						</div>
						<label class="col-md-2 control-label"><span
							class="text-danger">*&nbsp;</span>物资投运评分：</label>
						<div class="col-md-3 rowGroup">
							<input type="text" class="form-control" id="linkTele"
								name="registrationFormBean.registrationInfoBean.linkTele"
								value="<s:property value='registrationFormBean.registrationInfoBean.linkTele' />"
								placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span
							class="text-danger">*&nbsp;</span>质保情况评分：</label>
						<div class="col-md-3 rowGroup">
							<input type="text" class="form-control" id="linkTele"
								name="registrationFormBean.registrationInfoBean.linkTele"
								value="<s:property value='registrationFormBean.registrationInfoBean.linkTele' />"
								placeholder="">
						</div>
						<label class="col-md-2 control-label"></label>
						<div class="col-md-3 rowGroup">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">总&nbsp;&nbsp;体&nbsp;&nbsp;评&nbsp;&nbsp;价：</label>
						<div class="col-md-10">
							<textarea id="linkManRemark"
								name="registrationFormBean.registrationInfoBean.linkManRemark"
								rows='8' class='form-control' placeholder="请输入总体评价，字数限制500！"><s:property
								value='registrationFormBean.registrationInfoBean.linkManRemark' /></textarea>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-2">附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件：</label>
						<div class="col-md-10">
							<input type="file" class="form-control" id="linkTele"
								name="registrationFormBean.registrationInfoBean.linkTele"
								value="<s:property value='registrationFormBean.registrationInfoBean.linkTele' />"
								placeholder="">
						</div>
					</div>
					<div class="form-group pull-right">
						<div class="col-md-2">
							<button type="submit" id="btn_registration" name="submit"
								class="btn btn-lg btn-success pull-right ">
								<div class="visible-md visible-lg">确认</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-file-o"></i>
								</div>
							</button>
							<button type="submit" id="" name="submit"
								class="btn btn-lg btn-success pull-right ">
								<div class="visible-md visible-lg">取消</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-file-o"></i>
								</div>
							</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>

</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.inc"%>
<script src="def.js"></script>
<script src="list.js"></script>

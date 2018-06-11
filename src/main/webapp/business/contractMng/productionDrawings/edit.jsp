<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="info_form" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="edit_dialog">
		<div class="modal-dialog modal-lg" style="width: 800px">
			<div class="modal-content">

				<div class="modal-header"
					style="background-color: #FCFCFC; height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;">更新图纸日期</h4>
				</div>
				<div class="modal-body" style="margin-bottom: -10px;padding-bottom: 0px;">
					<table class="table table-bordered  table-condensed" id="table" style="margin-top: -5px;margin-bottom: 5px;">
						<tbody>
							<tr>
								<td colspan="8">基础信息</td>
							</tr>
							<tr>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">物料编码&nbsp;:</td>
								<td id="serialNumbers" style="text-align: left">wl0001</td>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">物料名称&nbsp;:</td>
								<td><div class='autocut' style="text-align: left"
										id="departUnitNames">铁塔</div></td>
							</tr>
							<tr>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量&nbsp;:</td>
								<td id="linkTeles" style="text-align: left">100</td>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">计量单位&nbsp;:</td>
								<td id="transportModels" style="text-align: left">基</td>
							</tr>
							<tr>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">需求日期&nbsp;:</td>
								<td id="linkTeles" style="text-align: left">2018-01-18</td>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">中标厂家&nbsp;:</td>
								<td id="transportModels" style="text-align: left">安徽宏源铁塔有限公司</td>
							</tr>
							<tr>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">中标日期&nbsp;:</td>
								<td id="linkTeles" style="text-align: left" colspan="3"></td>
							</tr>
							<tr>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;:</td>
								<td id="linkTeles" style="text-align: left" colspan="3"></td>
							</tr>
						</tbody>
					</table>
					<table class="table table-bordered  table-condensed" id="table">
						<tbody>
							<tr>
								<td colspan="8">更新日期</td>
							</tr>
							<tr>
								<td colspan="8" style="height: 37px;">
									<div class="form-group" style="margin-top: -10px;margin-bottom: -4px;">
										<label class="col-md-2 control-label" style="margin-top: 5px;">
											图纸确认时间：
										</label>
										<div class="col-md-4 rowGroup">
											<a class='input-group date' style="float: left;"> <label
												class="input" style="display: inline"> <input
													type='text' class="form-control laydate" autofocus="autofocus"
													style="width: 163px; height: 30px;" readonly="readonly"
													id="createDate" data-bv-group=".rowGroup"
													name="infoFromBean.mInfo.createDate" required
													data-bv-notempty-message="生产图纸确认时间不能为空"
													value="<s:property value='infoFromBean.mInfo.createDate' />"
													placeholder="请选择日期"> <span class="input-group-addon"
													style="width: 39px; height: 30px;"> <span
														class="icon-calendar"></span>
												</span>
											</label>
											</a>
										</div>
										<label class="col-md-2 control-label" style="margin-top: 5px;">
											协议签订时间：
										</label>
										<div class="col-md-4 rowGroup">
											<a class='input-group date' style="float: left;"> <label
												class="input" style="display: inline"> <input
													type='text' class="form-control laydate" autofocus="autofocus"
													style="width: 163px; height: 30px;" readonly="readonly"
													id="createDate" data-bv-group=".rowGroup"
													name="infoFromBean.mInfo.createDate" required
													data-bv-notempty-message="技术协议签订时间不能为空"
													value="<s:property value='infoFromBean.mInfo.createDate' />"
													placeholder="请选择日期"> <span class="input-group-addon"
													style="width: 39px; height: 30px;"> <span
														class="icon-calendar"></span>
												</span>
											</label>
											</a>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer"
					style="height: 40px; background-color: #FCFCFC;">
					<button type="submit" id="btn_save" class="btn btn-success"
						style="margin-top: -14px">
						<i class="icon icon-save"></i> 保存
					</button>

					<button type="button" class="btn btn-large btn-primary"
						data-dismiss="modal"
						style="margin-top: -14px; margin-right: -12px">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			</div>
		</div>
	</div>

</form>




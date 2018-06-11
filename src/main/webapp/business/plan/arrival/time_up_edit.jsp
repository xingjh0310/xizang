<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="time_up_edit_form" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="time_up_edit">
		<div class="modal-dialog modal-lg" style="width: 800px">
			<div class="modal-content">
				<div class="modal-header"
					style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"><i class="icon icon-refresh"></i>&nbsp;更新协议日期</h4>
				</div>
				<div class="modal-body" style="margin-bottom: -10px;padding-bottom: 0px;">
					<div style="display: none;">
						supply_play_code<input type="text" id="supply_play_code" name="fSupplyFormBean.mSupply.supplierPlanCode">
					</div>
					<table class="table table-bordered  table-condensed" id="table"  style="margin-top: -5px;margin-bottom: 5px;">
						<tbody>
							<tr>
								<td colspan="8">基础信息</td>
							</tr>
							<tr>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">物料名称:</td>
								<td id="MATERIELNAME_up_time" style="text-align: left"></td>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">物料规格:</td>
								<td id="MATERIALNORMS_up_time" style="text-align: left"></td>
							</tr>
							<tr>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量:</td>
								<td id="PLANDELIVERIE_up_time" style="text-align: left"></td>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">计量单位:</td>
								<td id="MEAUNIT_up_time" style="text-align: left"></td>
							</tr>
							<tr>
								<!-- <td
									style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">需求日期:</td>
								<td id="_up_time" style="text-align: left"></td> -->
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">中标厂家:</td>
								<td id="SUPPLIERNAME_up_time" style="text-align: left"></td>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">中标日期:</td>
								<td id="BIDDINGDATE_up_time" style="text-align: left"></td>
							</tr>
							
							<tr>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:</td>
								<td id="_up_time" style="text-align: left" colspan="3"></td>
							</tr>
						</tbody>
					</table>
					<table class="table table-bordered" id="table">
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
													type='text' class="form-control laydate" 
													style="width: 163px; height: 30px;" readonly="readonly"
													id="DRAWECONFIRMTIME_up_time" data-bv-group=".rowGroup"
													name="fSupplyFormBean.mSupply.draweConfirmTime" required
													data-bv-notempty-message="生产图纸确认时间不能为空"
													placeholder="请选择生产图纸日期"> <span class="input-group-addon"
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
													type='text' class="form-control laydate" 
													id="SIGNTIME_up_time" data-bv-group=".rowGroup"
													name="fSupplyFormBean.mSupply.signTime" required
													style="width: 163px; height: 30px;" readonly="readonly"
													data-bv-notempty-message="技术协议签订时间不能为空"
													placeholder="请选择协议签订日期"> <span class="input-group-addon"
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
					style="height: 40px;">
					<button type="submit" id="btn_up_time_save" class="btn btn-primary"
						style="margin-top: -14px">
						<i class="icon icon-download-alt"></i> 保存
					</button>

					<button type="button" class="btn"
						data-dismiss="modal"
						style="margin-top: -14px; margin-right: -5px">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>

</form>




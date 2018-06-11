<%@ page language="java" pageEncoding="UTF-8"%>

<form id="query_form" name="info_form" class="form-inline"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="query_info_show">
		<div class="modal-dialog " style="width: 850px">
			<div class="modal-content">
				<div class="modal-header"
					style="background-color: #FCFCFC; height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;">
						<i class="icon icon-home"></i>&nbsp;&nbsp;履约评价详情
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered" style="margin-bottom: 0px;">
						<tr>
							<td style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>合同标题：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="CONTRACTTITLE_detail"></lable></td>
							<td style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>合同编号：</lable>
							</td>
							<td style="text-align: left"><lable
									style="margin-left: 15px;" id="CONTRACTNO_detail"></lable></td>
						</tr>
						<tr>
							<td style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>工程名称：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="ENGINEERNAME_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable></lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id=""></lable></td>
						</tr>
						<tr>
							<td style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>物资到货：</lable>
							</td>
							<td style="text-align: left">
								<lable style="margin-left: 15px;">
									<input id="MATERIALARRIVAL_detail" name="contEvaluateFormBean.contEvaluateBean.productQuality" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" >
								</lable>
							</td>
							<td style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>产品质量：</lable>
							</td>
							<td style="text-align: left">
								<lable style="margin-left: 15px;">
									<input id="PRODUCTQUALITY_detail" name="contEvaluateFormBean.contEvaluateBean.productQuality" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" >
								</lable>
							</td>
						</tr>
						<tr>
							<td style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>现场服务：</lable>
							</td>
							<td style="text-align: left">
								<lable style="margin-left: 15px;">
									<input id="FIELDSERVICE_detail" name="contEvaluateFormBean.contEvaluateBean.productQuality" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" >
								</lable>
							</td>
							<td style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>物资投运：</lable>
							</td>
							<td style="text-align: left">
								<lable style="margin-left: 15px;">
									<input id="MATERIALOPERATION_detail" name="contEvaluateFormBean.contEvaluateBean.productQuality" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" >
								</lable>
							</td>
						</tr>
						<tr>
							<td style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>质保情况：</lable>
							</td>
							<td style="text-align: left">
								<lable style="margin-left: 15px;">
									<input id="WARRANTYSITUATION_detail" name="contEvaluateFormBean.contEvaluateBean.productQuality" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" >
								</lable>
							</td>
							<td style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>总体评价：</lable>
							</td>
							<td style="text-align: left">
								<lable style="margin-left: 15px;">
									<input id="EVALUATION_detail" name="contEvaluateFormBean.contEvaluateBean.productQuality" type="number" readonly="true" class="rating" min=0 max=5 step=1 data-size="xs" >
								</lable>
							</td>
						</tr>
						<tr>
							<td style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1;">
								<lable>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</lable>
							</td>
							<td colspan="3"><lable style="margin-left: 15px;"
								id="REMARK_detail"></lable></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer"
					style="height: 40px; background-color: #FCFCFC;">
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




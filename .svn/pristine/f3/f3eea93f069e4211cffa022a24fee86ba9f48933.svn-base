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
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class="icon icon-home"></i>&nbsp;变更详细记录
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered" style="margin-bottom: 0px;">
						<tr>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>合同标题：</lable>
							</td>
							<td style="width: 280px; text-align: left"><lable
								style="margin-left: 15px;" id="CONTRACTTITLE"></lable></td>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>合同编号：</lable>
							</td>
							<td style="width: 280px; text-align: left"><lable
								style="margin-left: 15px;" id="CONTRACTNO"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>所属工程：</lable>
							</td>
							<td style="width: 280px; text-align: left"><lable
									style="margin-left: 15px;" id="ENGINEERNAME"></lable></td>

							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>标段：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="SECTION"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>物资名称：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="MATERIELNAME"></lable></td>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>规格参数：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="MATERIALNORMS"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>物料单位：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="UNIT"></lable></td>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>物料单价（元）&nbsp;：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="PRICE"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>变更前数量：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="PRECHANGENUM"></lable></td>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>变更后数量：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="AFTERCHANGENUM"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>变更前价格：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="PRECHANGEPRICE"></lable></td>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>变更后价格：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="AFTERCHANGEPRICE"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>差额：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="DIFFERENCETAX"></lable></td>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>变更时间：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="CHANGETIME"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>操作人：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="NAME"></lable></td>
								<td
								style="width: 140px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>状态：</lable>
							</td>
							<td colspan="3"><lable style="margin-left: 15px;"
								id="MATERIALSTATE"></lable></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="button" class="btn btn-large"
						data-dismiss="modal"
						style="margin-top: -14px; margin-right: -5px">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>


</form>




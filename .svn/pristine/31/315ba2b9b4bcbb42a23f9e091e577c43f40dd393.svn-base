<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
</style>
<form id="info_form" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="edit_dialog">
		<div class="modal-dialog modal-lg" style="width: 900px">
			<div class="modal-content">

				<div class="modal-header"
					style="background-color: #FCFCFC; height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;">变更合同</h4>
				</div>

				<div class="modal-body" style="padding-top: 5px;">
					<div class="btn-toolbar" style="margin-left: 10px;">
						<div style="float:left">
							<h3 style="margin-top:10px;">合同详情</h3>
						</div>
					</div>
					<table class="table table-bordered" style="margin-bottom: 0px;">
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>合同标题：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="CONTRACTTITLE_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>合同编号：</lable>
							</td>
							<td style="text-align: left"><lable
									style="margin-left: 15px;" id="CONTRACTNO_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>需求单位：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="DEMANDUNIT_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>合同状态：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="CONTRACTSTATE_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>所属工程：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="ENGINEERNAME_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;段：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="SECTION_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>供货厂商：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="SUPPLYFULLNAME_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>合同金额：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="CONTRACTAMOUNT_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>招标日期：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="BIDDEDATE_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>签订日期：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="SIGNDATE_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>合同日期：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="CONTRACTDATE_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>供货日期：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="SUPPLYDATE_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1;">
								<lable>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</lable>
							</td>
							<td colspan="3"><lable style="margin-left: 15px;"
								id="REMARK_detail"></lable></td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td -->
<!-- 								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1"> -->
<!-- 								<lable>附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件：</lable> -->
<!-- 							</td> -->
<!-- 							<td colspan="3"><lable style="margin-left: 15px;" -->
<!-- 								id="carRemarks"><a href="javascript:void(0)">低压电力电缆（配套检修电源箱的增补）.pdf</a></lable></td> -->
<!-- 						</tr> -->
					</table>
					<div class="btn-toolbar" style="margin-left: 10px;">
						<div style="float:left">
							<h3>物资信息变更</h3>
						</div>
					</div>
					<table class="table table-bordered">
						<thead>
							<tr>
								<th style="text-align: center;padding-bottom: 15px;width:8%;">序号</th>
								<th style="text-align: center;padding-bottom: 15px;width:9%;">物料名称</th>
								<th style="text-align: center;padding-bottom: 15px;width:9%;">计量单位</th>
								<th style="text-align: center;padding-bottom: 15px;width:9%;">规格型号</th>
								<th style="text-align: center;padding-bottom: 15px;width:9%;">数量</th>
								<th style="text-align: center;padding-bottom: 15px;width:10%;">变更后数量</th>
								<th style="text-align: center;padding-bottom: 15px;width:10%;">单价（元）</th>
								<th style="text-align: center;width:10%;">差额<br>（不含税）</th>
								<th style="text-align: center;width:9%;">差额<br>（含税）</th>
								<th style="text-align: center;padding-bottom: 15px;width:10%;">总价（元）</th>
								<th style="text-align: center;padding-bottom: 15px;width:8%;">操作</th>
							</tr>
						</thead>
						<tbody id="data_body">
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




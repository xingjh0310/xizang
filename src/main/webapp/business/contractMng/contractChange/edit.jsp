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

				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class="icon icon-pencil"></i>&nbsp;变更合同</h4>
				</div>

				<div class="modal-body" style="padding-top: 5px;">
					<div class="btn-toolbar" style="margin-left: 0px;">
						<div style="float:left">
							<h3 style="margin-top:11px;font-size: 14px;"><i class="icon icon-tags"></i>&nbsp;合同详情</h3>
						</div>
					</div>
					<table class="table table-bordered" style="margin-bottom: 0px;">
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>合同标题：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="CONTRACTTITLE_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>合同编号：</lable>
							</td>
							<td style="text-align: left"><lable
									style="margin-left: 15px;" id="CONTRACTNO_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>需求单位：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="DEMANDUNIT_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>合同状态：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="CONTRACTSTATE_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>所属工程：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="ENGINEERNAME_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;段：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="SECTION_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>供货厂商：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="SUPPLYFULLNAME_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>合同金额：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="CONTRACTAMOUNT_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>招标日期：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="BIDDEDATE_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>签订日期：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="SIGNDATE_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>合同日期：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="CONTRACTDATE_detail"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9">
								<lable>供货日期：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="SUPPLYDATE_detail"></lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9;">
								<lable>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</lable>
							</td>
							<td colspan="3"><lable style="margin-left: 15px;"
								id="REMARK_detail"></lable></td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td -->
<!-- 								style="width: 100px; text-align: right; font-weight: bold; background: #f9f9f9"> -->
<!-- 								<lable>附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件：</lable> -->
<!-- 							</td> -->
<!-- 							<td colspan="3"><lable style="margin-left: 15px;" -->
<!-- 								id="carRemarks"><a href="javascript:void(0)">低压电力电缆（配套检修电源箱的增补）.pdf</a></lable></td> -->
<!-- 						</tr> -->
					</table>
					<div class="btn-toolbar" style="margin-left: 0px;">
						<div style="float:left">
							<h3 style="margin-top: 15px;font-size: 14px;"><i class="icon icon-tags"></i>&nbsp;物资信息变更</h3>
						</div>
					</div>
					<table class="table table-bordered" style="margin-bottom: 0px;">
						<thead>
							<tr>
								<th style="text-align: center;width:8%;background-color: #f9f9f9;">序号</th>
								<th style="text-align: center;width:9%;background-color: #f9f9f9;">物料名称</th>
								<th style="text-align: center;width:9%;background-color: #f9f9f9;">计量单位</th>
								<th style="text-align: center;width:9%;background-color: #f9f9f9;">规格型号</th>
								<th style="text-align: center;width:9%;background-color: #f9f9f9;">数量</th>
								<th style="text-align: center;width:10%;background-color: #f9f9f9;">变更后数量</th>
								<th style="text-align: center;width:10%;background-color: #f9f9f9;">单价（元）</th>
<!-- 								<th style="text-align: center;width:10%;">差额<br>（不含税）</th> -->
								<th style="text-align: center;width:9%;background-color: #f9f9f9;">差额</th>
								<th style="text-align: center;width:10%;background-color: #f9f9f9;">总价（元）</th>
								<th style="text-align: center;width:8%;background-color: #f9f9f9;">操作</th>
							</tr>
						</thead>
						<tbody id="data_body">
						</tbody>
					</table>
					<input type="hidden" name="contChangeFormBean.ids" id="removeIds"/>
				</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="submit" id="btn_save" class="btn btn-primary"
						style="margin-top: -14px">
						<i class="icon icon-download-alt"></i> 保存
					</button>
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




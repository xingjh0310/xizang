<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.form-group{
	margin-bottom:5px;
}
</style>

<form id="info_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="info_dialog">
		<div class="modal-dialog modal-lg" style="width:680px">
			<div class="modal-content">
			
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close modal_header_top" data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title modal_header_top"><i class="icon-star-empty"></i>&nbsp;到货计划审核</h4>
				</div>
			
				<div class="modal-body">
					<div style="display: none;">
						id<input type="text" class="form-control" id="ID" name="fArrivalFormBean.mArrival.id">
					</div>
					<div style="display: none;" id="supplierInfo_div">
					
					</div>
					
					<h4 class="model_title_margin_top"><i class="icon icon-tags"></i>&nbsp;基础信息</h4>
					<table class="table table-bordered info_table_css">
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">供应计划标题：</label>
							</td>
							<td colspan="3">
								<lable style="margin-left: 15px;" id="supply_plan_title_audit"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">供&nbsp;&nbsp;应&nbsp;商：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="SUPPLIERNAME_audit"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">合同标题：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="CONTRACTNAME_audit"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">合同数量：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="CONTRACTNUM_audit"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">物料名称：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIELNAME_audit"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">物料规格：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIALNORMS_audit"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">计量单位：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MEAUNIT_audit">100</lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">计划交货量：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PLANDELIVERIE_audit"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">实际交货量：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="ACTUALDELIVERIE_audit"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:110px;text-align:right;font-weight:bold;background: #f9f9f9;padding-left:0px">
                            	<label style="margin:0">计划交货批次：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PLANDELIVERYBATCH_audit"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">申请日期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="APPLYDATE_audit"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">需求单位：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="DEMANDUNIT_audit"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">项目单位：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="DEPTNAME_audit"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">合同交货期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="CONTRACTDELIVERYDATE_audit"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">确认交货期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="CONFIRMDELIVERYDATE_audit"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">供&nbsp;&nbsp;应&nbsp;&nbsp;商&nbsp;&nbsp;&nbsp; <br>建议交货期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="SUPPLYDELIVERYDATE_audit" ></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">需求部门 &nbsp;&nbsp;&nbsp;<br>建议交货期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="DEPTDELIVERYDATE_audit"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">收货联系人：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="RECEIVECONTACT_audit" ></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">联系电话：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="LINKMODE_audit"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">交货地点：</label>
							</td>
							<td colspan="3">
                	 			<lable  style="margin-left: 15px;" id="DELIVERYPLACE_audit"></lable>
                	 		</td>
						</tr>
					</table>
					<div class="model_title2_margin_top"><h4> <i class="icon icon-tags"></i>&nbsp;审核意见</h4></div>
					<hr class="model_title_margin_top">
					<div class="form-group" style="margin-bottom: 0px;">
                	    <div class="col-md-12">
                	    	<textarea id="AUDITOPINION" name="fArrivalFormBean.mArrival.auditOpinion" maxlength="300" rows='3' class='form-control' placeholder="请输入审核意见，字数限制500！"></textarea>
                	    </div>
                    </div>
					
				</div>
			
				<div class="modal-footer" style="height: 40px;">
					<button type="button" id="btn_audit_ok" class="btn btn-primary" style="margin-top: -14px">
						<i class="icon icon-check-circle"></i> 审核通过
					</button>

					<button type="button" id="btn_audit_no" class="btn btn-danger" style="margin-top: -14px;margin-right:-5px">
						<i class="icon icon icon-times"></i> 审核拒绝
					</button>
				</div>
			</div>
		</div>
	</div>
	
</form>
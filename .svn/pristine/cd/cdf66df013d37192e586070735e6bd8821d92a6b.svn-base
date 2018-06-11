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
					<h4 class="modal-title modal_header_top">到货计划信息</h4>
				</div>
			
				<div class="modal-body">
					<div style="display: none;">
						id<input type="text" class="form-control" id="ID" name="fArrivalFormBean.mArrival.id">
						materialDetailId<input type="text" class="form-control" id="DETAILID" name="fMaterialDetailFormBean.mMaterialDetail.id">
						arrPlanNum<input type="text" class="form-control" id="ARRPLANNUM" name="fArrivalFormBean.mArrival.arrPlanNum">
						engineerCode<input type="text" class="form-control" id="ENGINEERCODE" name="fArrivalFormBean.mArrival.engineerCode">
						planState<input type="text" class="form-control" id="PLANSTATE" name="fArrivalFormBean.mArrival.planState">
						auditState<input type="text" class="form-control" id="AUDITSTATE" name="fArrivalFormBean.mArrival.auditState">
						auditOpinion<input type="text" class="form-control" id="AUDITOPINION" name="fArrivalFormBean.mArrival.auditOpinion">
						auditPerson<input type="text" class="form-control" id="AUDITPERSON" name="fArrivalFormBean.mArrival.auditPerson">
						auditTime<input type="text" class="form-control" id="AUDITTIME" name="fArrivalFormBean.mArrival.auditTime">
					</div>
					<div style="display: none;" id="supplierInfo_div">
					
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label" style="padding-left:0px"> 
                              <span class="text-danger">*&nbsp;</span>供货计划：
						</label>
						<div class="col-md-4 rowGroup">
                            <select id="SUPPLIERPLANCODE" name="fArrivalFormBean.mArrival.supplierPlanCode" class="form-control" 
								data-bv-group=".rowGroup" 
								required 
								data-bv-notempty-message="请选择供应计划" >
                            	<option value="">请选择</option>
                            </select>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>供&nbsp;&nbsp;应&nbsp;商：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="SUPPLIERNAME" 
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
    						<input type="hidden" id="SUPPLIERCODE" name="fArrivalFormBean.mArrival.supplierCode" >                        
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label" style="padding-left:0px"> 
                              <span class="text-danger">*&nbsp;</span>合同编号：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="CONTRACTNO" 
                                   name="fArrivalFormBean.mArrival.contractNo" 
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
						</div>
						<label class="col-md-2 control-label" style="padding-left:0px"> 
                              <span class="text-danger">*&nbsp;</span>合同标题：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="CONTRACTNAME" 
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>合同数量：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="number" class="form-control" id="CONTRACTNUM" 
                                   name="fArrivalFormBean.mArrival.contractNum" 
                                   data-bv-group=".rowGroup" readonly="readonly"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>物料名称：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="MATERIELNAME" 
                                   name="fMaterialDetailFormBean.mMaterialDetail.materielName" 
                                   data-bv-group=".rowGroup" readonly="readonly"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>物料规格：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="MATERIALNORMS" 
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
                            <input type="hidden" id="MATERIELCODE" name="fArrivalFormBean.mArrival.materielCode">
						</div>
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>计量单位：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="MEAUNIT" 
                                   name="fArrivalFormBean.mArrival.meaUnit" 
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>计划交货量：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="number" class="form-control" id="PLANDELIVERIE" 
                                   name="fArrivalFormBean.mArrival.planDeliverie" 
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>实际交货量：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="number" class="form-control" id="ACTUALDELIVERIE" 
                                   name="fArrivalFormBean.mArrival.actualDeliverie" 
                                   data-bv-group=".rowGroup" 
                                   required	 
                                   onkeyup="value=value.replace(/\D/g,'')" 
								   maxlength="10"
                                   data-bv-notempty-message="实际交货数量不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入实际交货数量"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label" style="padding-left:0px"> 
                              <span class="text-danger">*&nbsp;</span>计划交货批次：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="PLANDELIVERYBATCH" 
                                   name="fArrivalFormBean.mArrival.planDeliveryBatch" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/\D/g,'')" 
                                   maxlength="20"
                                   data-bv-notempty-message="计划交货批次不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入计划交货批次"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>申请日期：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date'  style="float: left;margin-top:0px">
								<label class="input" style="display:inline">
		                            <input type="text" class="form-control laydate" id="APPLYDATE" 
		                                   name="fArrivalFormBean.mArrival.applyDate" style="width: 163px; height: 32px;"
		                                   data-bv-group=".rowGroup" readonly="readonly"
		                                   required	
		                                   data-bv-notempty-message="申请日期不能为空"   
		                                   placeholder="请选择申请日期"
		                            >
		                            <span class="input-group-addon" style=" width: 39px; height: 32px;">
		                            	<i class="icon icon-calendar"></i>
		                            </span>
	                            </label>
                            </a>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>需求单位：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="DEMANDUNIT" 
                                   name="fArrivalFormBean.mArrival.demandUnit" 
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>项目单位：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="DEPTNAME" 
                                   data-bv-group=".rowGroup" readonly="readonly"
                            >
                            <input type="hidden" id="TREENMSYSDEPT" name="fArrivalFormBean.mArrival.treenmSysDept">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>合同交货期：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="CONTRACTDELIVERYDATE" 
                                   name="fArrivalFormBean.mArrival.contractDeliveryDate" 
                                   data-bv-group=".rowGroup" readonly="readonly"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>确认交货期：
						</label>
						<div class="col-md-4 rowGroup">
                            <a class='input-group date'  style="float: left;margin-top:0px">
								<label class="input" style="display:inline">
		                            <input type="text" class="form-control laydate" id="CONFIRMDELIVERYDATE" 
		                                   name="fArrivalFormBean.mArrival.confirmDeliveryDate" style="width: 163px; height: 32px;"
		                                   data-bv-group=".rowGroup" readonly="readonly"
		                                   required	
		                                   maxlength="20"
		                                   data-bv-notempty-message="确认交货日期不能为空"   
		                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
		                                   placeholder="请选择确认交货日期"
		                            >
		                            <span class="input-group-addon" style=" width: 39px; height: 32px;">
		                            	<i class="icon icon-calendar"></i>
		                            </span>
	                            </label>
                            </a>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>供&nbsp;&nbsp;应&nbsp;&nbsp;商&nbsp;&nbsp;&nbsp; <br>建议交货期：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date'  style="float: left;">
								<label class="input" style="display:inline">
		                            <input type="text" class="form-control laydate" id="SUPPLYDELIVERYDATE" 
		                                   name="fArrivalFormBean.mArrival.supplyDeliveryDate" style="width: 163px; height: 32px;"
		                                   data-bv-group=".rowGroup" readonly="readonly"
		                                   required	
		                                   data-bv-notempty-message="供应商建议交货期不能为空"   
		                                   placeholder="请选择供应商建议交货期"
		                            >
		                            <span class="input-group-addon" style=" width: 39px; height: 32px;">
		                            	<i class="icon icon-calendar"></i>
		                            </span>
	                            </label>
                            </a>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>需求部门 &nbsp;&nbsp;&nbsp;<br>建议交货期：
						</label>
						<div class="col-md-4 rowGroup">
                            <a class='input-group date'  style="float: left;">
								<label class="input" style="display:inline">
		                            <input type="text" class="form-control laydate" id="DEPTDELIVERYDATE" 
		                                   name="fArrivalFormBean.mArrival.deptDeliveryDate" style="width: 163px; height: 32px;"
		                                   data-bv-group=".rowGroup" readonly="readonly"
		                                   required	
		                                   data-bv-notempty-message="需求部门建议交货期不能为空"   
		                                   placeholder="请选择需求部门建议交货期"
		                            >
		                            <span class="input-group-addon" style=" width: 39px; height: 32px;">
		                            	<i class="icon icon-calendar"></i>
		                            </span>
	                            </label>
                            </a>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>收货联系人：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="RECEIVECONTACT" 
                                   name="fArrivalFormBean.mArrival.receiveContact" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="20" 
                                   onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
                                   data-bv-notempty-message="收货联系人不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入收货联系人"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>联系电话：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="LINKMODE" 
                                   name="fArrivalFormBean.mArrival.linkMode" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/\D/g,'')" 
                                   maxlength="11"
                                   data-bv-notempty-message="联系电话不能为空"   
                                   data-bv-stringlength-max="11" data-bv-stringlength-message="字数不能超过11"
                                   placeholder="请输入联系电话"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>交货地点：
						</label>
						<div class="col-md-10 rowGroup">
                            <input type="text" class="form-control" id="DELIVERYPLACE" 
                                   name="fArrivalFormBean.mArrival.deliveryPlace" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="15"
                                   data-bv-notempty-message="交货地点不能为空"   
                                   data-bv-stringlength-max="15" data-bv-stringlength-message="字数不能超过15"
                                   placeholder="请输入交货地点"
                            >
						</div>
					</div>
					
					<div class="form-group">
                	    <label class="col-md-2 control-label" style="margin: 12px auto;"><span class="text-danger">&nbsp;</span>物资描述：</label>
                	    <div class="col-md-10">
                	    	<textarea id="MATERIELDESC" name="fArrivalFormBean.mArrival.materielDesc" maxlength="300" rows='2' class='form-control' placeholder="请输入物资描述信息，字数限制100！"></textarea>
                	    </div>
                    </div>
					
					<div class="form-group" style="margin-bottom: 0px;">
                	    <label class="col-md-2 control-label" style="margin: 18px auto;"><span class="text-danger"> &nbsp;</span>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	    <div class="col-md-10">
                	    	<textarea id="REMARK" name="fArrivalFormBean.mArrival.remark" maxlength="300" rows='3' class='form-control' placeholder="请输入备注信息，字数限制500！"></textarea>
                	    </div>
                    </div>
					
				</div>
			
				<div class="modal-footer" style="height: 40px;">
					<button type="submit" id="btn_save" class="btn btn-primary" style="margin-top: -14px">
						<i class="icon icon-download-alt"></i> 保存
					</button>

					<button type="button" class="btn" data-dismiss="modal" style="margin-top: -14px;margin-right:-5px">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>
	
</form>
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
		<div class="modal-dialog modal-lg" style="width:703px">
			<div class="modal-content">
			
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close modal_header_top" data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title modal_header_top">供货计划信息</h4>
				</div>
			
				<div class="modal-body-check">
					<div style="display:none">
						id<input type="text" class="form-control" id="ID" name="fSupplyFormBean.mSupply.id">
						engineerCode<input type="text" class="form-control" id="ENGINEERCODE" name="fSupplyFormBean.mSupply.engineerCode">
						supplierPlanCode<input type="text" class="form-control" id="SUPPLIERPLANCODE" name="fSupplyFormBean.mSupply.supplierPlanCode">
					materialDetailId<input type="text" class="form-control" id="DETAILID" name="fMaterialDetailFormBean.mMaterialDetail.id">
					</div>
					<div style="display:none" id="materialInfo_div"></div>
					 <div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>合同编号：
						</label>
						<div class="col-md-10 rowGroup">
                            <select class="form-control" id="CONTRACTNO" 
                                   name="fSupplyFormBean.mSupply.contractNo" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   data-bv-notempty-message="请选择合同编号">
                                 <option value="">请选择合同</option>
                            </select>
                            
						</div>
					 </div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>合同数量：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="number" class="form-control" id="CONTRACTNUM" 
                                   name="fSupplyFormBean.mSupply.contractNum" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/\D/g,'')" 
                                   maxlength="20"
                                   data-bv-notempty-message="合同数量不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入合同数量"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>供&nbsp;&nbsp;应&nbsp;商：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="SUPPLYFULLNAME" 
                                   data-bv-group=".rowGroup"  readonly="readonly"
                                   name="fSupplyFormBean.supPlyfullName"
                            >
                            <input type="hidden" id="SUPPLIERCODE" name="fSupplyFormBean.mSupply.supplierCode">
						</div>
					</div>
					
					 <div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>物料名称：
						</label>
						<div class="col-md-4 rowGroup">
                            <select class="form-control" id="MATERIELCODE" 
                                   name="fSupplyFormBean.mSupply.materielCode" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   data-bv-notempty-message="请选择物料">
                                   <option value="">请选择物料</option>
                             </select>
                             <input type="hidden" id="MATERIELNAME" name="fMaterialDetailFormBean.mMaterialDetail.materielName">
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>物料规格：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="MATERIALNORMS" 
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>计量单位：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="MEAUNIT" 
                                   name="fSupplyFormBean.mSupply.meaUnit" 
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>计划交货数：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="number" class="form-control" id="PLANDELIVERIE" 
                                   name="fSupplyFormBean.mSupply.planDeliverie" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   maxlength="10"
                                   data-bv-notempty-message="计划交货数不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入计划交货数"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>实际交货数：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="number" class="form-control" id="ACTUALDELIVERIE" 
                                   name="fSupplyFormBean.mSupply.actualDeliverie" 
                                   data-bv-group=".rowGroup" 
                                   required	 
                                   onkeyup="value=value.replace(/\D/g,'')" 
								   maxlength="10"
                                   data-bv-notempty-message="实际交货数不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入实际交货数"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>计划状态：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="radio" id="radio1" name="fSupplyFormBean.mSupply.planState" value="1"> 未执行
                            <input type="radio" id="radio2" name="fSupplyFormBean.mSupply.planState" value="2"> 已执行
						</div> 
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>工程名称：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="ENGINEERNAME" 
                                   readonly="readonly"
                                   data-bv-group=".rowGroup" 
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>项目单位：
						</label>
						<div class="col-md-4 rowGroup" id="loadTreeDate">
                            <input type="text" class="form-control" id="DEPTNAME" 
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
                            <input type="hidden" class="form-control" id="TREENMSYSDEPT" name="fSupplyFormBean.mSupply.treenmSysDept" >
							<div id="zTreeDiv_dept">
								<ul id="treeDemo" class="ztree" ></ul>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>申请日期：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date'  style="float: left;margin-top:0px">
								<label class="input" style="display:inline">
		                            <input type="text" class="form-control laydate" id="APPLYDATE" 
		                                   name="fSupplyFormBean.mSupply.applyDate" style="width: 167px; height: 32px;"
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
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>中标日期：
						</label>
						<div class="col-md-4 rowGroup">
                            <a class='input-group date'  style="float: left;margin-top:0px">
								<label class="input" style="display:inline">
		                            <input type="text" class="form-control laydate" id="BIDDINGDATE" 
		                                   name="fSupplyFormBean.mSupply.biddingDate" style="width: 167px; height: 32px;"
		                                   data-bv-group=".rowGroup" readonly="readonly"
		                                   required	
		                                   data-bv-notempty-message="中标日期不能为空"   
		                                   placeholder="请选择中标日期"
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
                              <span class="text-danger">*&nbsp;</span>合同交货期：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="CONTRACTDELIVERYDATE" 
                                   name="fSupplyFormBean.mSupply.contractDeliveryDate"
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
		                                   name="fSupplyFormBean.mSupply.confirmDeliveryDate" style="width: 167px; height: 32px;"
		                                   data-bv-group=".rowGroup" readonly="readonly"
		                                   required	
		                                   data-bv-notempty-message="确认交货期不能为空"   
		                                   placeholder="请选择确认交货期"
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
		                                   name="fSupplyFormBean.mSupply.supplyDeliveryDate" style="width: 167px; height: 32px;"
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
		                                   name="fSupplyFormBean.mSupply.deptDeliveryDate" style="width: 167px; height: 32px;"
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
                              <span class="text-danger">*&nbsp;</span>生产图纸&nbsp;&nbsp;&nbsp;<br>确认时间：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date'  style="float: left;">
								<label class="input" style="display:inline">
		                            <input type="text" class="form-control laydate" id="DRAWECONFIRMTIME" 
		                                   name="fSupplyFormBean.mSupply.draweConfirmTime" style="width: 167px; height: 32px;"
		                                   data-bv-group=".rowGroup" readonly="readonly"
		                                   required	
		                                   data-bv-notempty-message="生成图纸确认时间不能为空"   
		                                   placeholder="请选择生成图纸确认时间"
		                            >
		                            <span class="input-group-addon" style=" width: 39px; height: 32px;">
		                            	<i class="icon icon-calendar"></i>
		                            </span>
	                            </label>
                            </a>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>技术签订&nbsp;&nbsp;&nbsp;<br>确认时间：
						</label>
						<div class="col-md-4 rowGroup">
                            <a class='input-group date'  style="float: left;">
								<label class="input" style="display:inline">
		                            <input type="text" class="form-control laydate" id="SIGNTIME" 
		                                   name="fSupplyFormBean.mSupply.signTime" style="width: 167px; height: 32px;"
		                                   data-bv-group=".rowGroup" readonly="readonly"
		                                   required	
		                                   data-bv-notempty-message="技术签订确认时间不能为空"   
		                                   placeholder="请选择技术签订确认时间"
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
                              <span class="text-danger">*&nbsp;</span>交货联系人：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="DELIVERYCONTACT" 
                                   name="fSupplyFormBean.mSupply.deliveryContact" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
                                   maxlength="10"
                                   data-bv-notempty-message="交货联系人不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入交货联系人"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>交货联系&nbsp;&nbsp;&nbsp;<br>方式：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="LINKMODE" 
                                   name="fSupplyFormBean.mSupply.linkMode" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   maxlength="11"
                                   data-bv-notempty-message="联系方式不能为空"   
                                   data-bv-stringlength-max="11" data-bv-stringlength-message="字数不能超过11"
                                   placeholder="请输入交货联系方式"
                            >
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>供应商&nbsp;&nbsp;&nbsp;<br>联系人：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="SUPPLYCONTACT" 
                                   name="fSupplyFormBean.mSupply.supplyContact" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
                                   maxlength="10"
                                   data-bv-notempty-message="供应商联系人不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入供应商联系人"
                            >
						</div>
						<label class="col-md-2 control-label" style="margin-top: -3px;"> 
                              <span class="text-danger">*&nbsp;</span>供应商&nbsp;&nbsp;&nbsp;<br>联系方式：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="SUPPLYPHONE" 
                                   name="fSupplyFormBean.mSupply.supplyPhone" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   maxlength="11"
                                   data-bv-notempty-message="联系方式不能为空"   
                                   data-bv-stringlength-max="11" data-bv-stringlength-message="字数不能超过11"
                                   placeholder="请输入供应商联系方式"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>交货地点：
						</label>
						<div class="col-md-10 rowGroup">
							<input type="text" class="form-control" id="DELIVERYPLACE" 
	                                   name="fSupplyFormBean.mSupply.deliveryPlace" 
	                                   data-bv-group=".rowGroup" 
	                                   required	
	                                   maxlength="30"
	                                   data-bv-notempty-message="交货地点不能为空"   
	                                   data-bv-stringlength-max="30" data-bv-stringlength-message="字数不能超过30"
	                                   placeholder="请输入交货地点"
	                            >
						</div>
					</div>
					
					<div class="form-group" style="margin-bottom: 5px;">
                	    <label class="col-md-2 control-label" style="margin: 12px auto;"><span class="text-danger">&nbsp;</span>物资描述：</label>
                	    <div class="col-md-10">
                	    	<textarea id="MATERIALDESC" name="fSupplyFormBean.mSupply.materialDesc" maxlength="300" rows='2' class='form-control' placeholder="请输入物资描述信息，字数限制100！"></textarea>
                	    </div>
                    </div>
                    
					<div class="form-group" style="margin-bottom: 5px;">
                	    <label class="col-md-2 control-label" style="margin: 12px auto;"><span class="text-danger">&nbsp;</span>计划描述：</label>
                	    <div class="col-md-10">
                	    	<textarea id="SUPPLYPLANDESC" name="fSupplyFormBean.mSupply.supplyPlanDesc" maxlength="300" rows='2' class='form-control' placeholder="请输入计划描述信息，字数限制100！"></textarea>
                	    </div>
                    </div>
					
					<div class="form-group" style="margin-bottom: 0px;">
                	    <label class="col-md-2 control-label" style="margin: 18px auto;"><span class="text-danger"> &nbsp;</span>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	    <div class="col-md-10">
                	    	<textarea id="REMARK" name="fSupplyFormBean.mSupply.remark" maxlength="300" rows='3' class='form-control' placeholder="请输入备注信息，字数限制500！"></textarea>
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
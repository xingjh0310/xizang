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
		<div class="modal-dialog modal-lg" style="width:750px">
			<div class="modal-content">
			
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"><i class='icon icon-edit'></i> 需求清单修改</h4>
				</div>
			
				<div class="modal-body">
					<div style="display: none;">
						id<input type="text" class="form-control" id="id" name="fDistributorFormBean.mDistributor.id">
						supplierCode<input type="text" class="form-control" id="supplierCode" name="fDistributorFormBean.mDistributor.supplierCode">
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label" style="padding-left:0px"> 
                              <span class="text-danger">*&nbsp;</span>供应商(全称)：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="supplyFullName" 
                                   name="fDistributorFormBean.mDistributor.supplyFullName" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="20" 
                                   onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
                                   data-bv-notempty-message="供应商不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入供应商全称"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>供应商简称：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="supplyBuilt" 
                                   name="fDistributorFormBean.mDistributor.supplyBuilt" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="20"
                                   data-bv-notempty-message="供应商简称不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入供应商简称"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label" style="padding-left:0px"> 
                              <span class="text-danger">*&nbsp;</span>工商登记证号：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="registrationNo" 
                                   name="fDistributorFormBean.mDistributor.registrationNo" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="20"
                                   data-bv-notempty-message="工商登记证号不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入工商登记证号"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>法 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="legalPerson" 
                                   name="fDistributorFormBean.mDistributor.legalPerson" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="10" 
                                   data-bv-notempty-message="法人不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入法人"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label" style="padding-left:0px"> 
                              <span class="text-danger">*&nbsp;</span>联&nbsp;&nbsp;系&nbsp;&nbsp;方&nbsp;&nbsp;式：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="linkPhone" 
                                   name="fDistributorFormBean.mDistributor.linkPhone" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="11" 
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   data-bv-notempty-message="联系方式不能为空"   
                                   data-bv-stringlength-max="11" data-bv-stringlength-message="字数不能超过11"
                                   placeholder="请输入联系方式"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              传 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="fax" 
                                   name="fDistributorFormBean.mDistributor.fax" 
                                   placeholder="请输入传真"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 人&nbsp;&nbsp;员&nbsp;&nbsp;姓&nbsp;&nbsp;名：
						</label>
						<div class="col-md-4 rowGroup">
	                        <select id="listnmSysStaff" class="form-control" 
	                        	name="fDistributorFormBean.mDistributor.listnmSysStaff" 
	                        	required data-bv-group=".rowGroup" ></select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label" style="padding-left:0px"> 
                              <span class="text-danger">*&nbsp;</span>地 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：
						</label>
						<div class="col-md-10 rowGroup">
							<input type="text" class="form-control" id="address" 
                                   name="fDistributorFormBean.mDistributor.address" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="30" 
                                   data-bv-notempty-message="地址不能为空"   
                                   data-bv-stringlength-max="30" data-bv-stringlength-message="字数不能超过30"
                                   pattern="" data-bv-regexp-message="不能输入特殊字符"
                                   placeholder="请输入地址"
                            >
						</div>
					</div>
					
				    <div class="form-group" style="margin-bottom: 0px;">
                	    <label class="col-md-2 control-label" style="margin: 18px auto;"><span class="text-danger"> &nbsp;</span>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	    <div class="col-md-10">
                	    	<textarea id="remark" name="fDistributorFormBean.mDistributor.remark" maxlength="300" rows='3' class='form-control' placeholder="请输入备注信息，字数限制500！"></textarea>
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
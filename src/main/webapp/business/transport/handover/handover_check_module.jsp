<%@ page language="java" pageEncoding="UTF-8"%>

<form id="check_module" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="check_modal">
		<div class="modal-dialog modal-lg" style="width:500px;">
			<div class="modal-content">
			
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;"><i class="icon icon-check-board">&nbsp;验收信息</i></h4>
				</div>
			
				<div class="modal-body-check" style="padding:10px;">
					
					<div class="form-group">
						<label class="col-md-4 control-label"> 
                              <span class="text-danger">*&nbsp;</span>验收单位：
						</label>
						<div class="col-md-8 rowGroup">
                            
                            <select class="form-control" id="company" name="company" 
                                   data-bv-group=".rowGroup" 	
                                   data-bv-notempty-message="请选择合同编号">
                                 <option value="">请选择单位</option>
                                 <option value="0">物资公司</option>
                                 <option value="1">厂家</option>
                                 <option value="2">施工单位</option>
                                 <option value="3">监理单位</option>
                                 <option value="4">业主单位</option>
                            </select>
						</div>
						
						
					 </div>
					  <div class="form-group">
					  <label class="col-md-4 control-label"> 
                              <span class="text-danger">*&nbsp;</span>验收时间：
						</label>
						<div class="col-md-8 rowGroup">
							<a class='input-group date'  style="float: left;margin-top:0px">
								<label class="input" style="display:inline">
		                            <input type="text" class="form-control laydate" id="firmTime" 
		                                   name="firmTime" style="width: 266px; height: 32px;"
		                                   data-bv-group=".rowGroup" readonly="readonly"
		                                   data-bv-notempty-message="验收时间不能为空"   
		                                   placeholder="请选择验收时间"/>
		                            <span class="input-group-addon" style=" width: 39px; height: 32px;">
		                            	<i class="icon icon-calendar"></i>
		                            </span>
	                            </label>
                            </a>
						</div>
					  </div>
					 
					 
					 <div class="form-group">
						<label class="col-md-4 control-label"> 
                              <span class="text-danger">*&nbsp;</span>验收人：
						</label>
						<div class="col-md-8 rowGroup">
                            <input type="text" class="form-control" id="people" 
                                   name="people" 
                                   data-bv-group=".rowGroup" 
                                   maxlength="20"
                                   data-bv-notempty-message="合同数量不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入验收人，多个人以逗号隔开。"/>
                            
						</div>
						
					 </div>
					  <div class="form-group">
						<label class="col-md-4 control-label"> 
		                              <span class="text-danger">*&nbsp;</span>验收状态：
						</label>
						<div class="col-md-8 rowGroup">
							<select class="form-control" id="state" 
	                                   name="state" data-bv-group=".rowGroup" >
	                                 <option value="1">合格</option>
	                                 <option value="2">不合格</option>
	                                 
                            </select>
    					</div>
    					</div>
				</div>
			
				<div class="modal-footer foter" >
					<button type="button" id="check_module_save" class="btn btn-primary bottom-btn" >
						<i class="icon icon-download-alt" ></i> 保存
					</button>

					<button type="button" class="btn btn-large bottom-btn" data-dismiss="modal">
						<i class="icon icon-times" ></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>
	
</form>
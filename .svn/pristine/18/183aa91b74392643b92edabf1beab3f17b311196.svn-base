<%@ page language="java" pageEncoding="UTF-8"%>

<form id="info_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<div class="modal-header" style="background-color: #FCFCFC;height: 40px">
					<button type="button"  class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;">单位新增</h4>
				</div>

				<div class="modal-body">
					<div style="display: none;">
						id<input type="text" class="form-control" id="id" name="formBean.infoBean.id">
                        create_time<input type="text" class="form-control" id="create_time" name="formBean.infoBean.create_time">
					</div>
                    
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">(*必填)</span> 单位编号：
						</label>
						<div class="col-md-10">
                            <input type="text" class="form-control" id="depart_unit_code" 
                                   name="formBean.infoBean.depart_unit_code" 
                                   value="<s:property value='formBean.infoBean.depart_unit_code' />" 
                                   required   
                                   data-bv-notempty-message="单位编号不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                placeholder="请输入单位编号，字数限制20  ，不能为空！"
                                onKeyUp="this.value=this.value.replace(/\D/g,'')"
                                onafterpaste="this.value=this.value.replace(/\D/g,'')"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">(*必填)</span> 单位名称：
						</label>
						<div class="col-md-10">
                            <input type="text" class="form-control" id="depart_unit_name" 
                                   name="formBean.infoBean.depart_unit_name" 
                                   value="<s:property value='formBean.infoBean.depart_unit_name' />" 
                                   required   
                                   data-bv-notempty-message="名称不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="字数不能超过50"
                                placeholder="请输入单位名称，字数限制50  ，不能为空！"
                            >
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">(*必填)</span> 单位简称：
						</label>
						<div class="col-md-10">
                            <input type="text" class="form-control" id="depart_unit_short_name" 
                                   name="formBean.infoBean.depart_unit_short_name" 
                                   value="<s:property value='formBean.infoBean.depart_unit_short_name' />" 
                                   required   
                                   data-bv-notempty-message="单位简称不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                placeholder="请输入单位简称，字数限制20  ，不能为空！"
                            >
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">(*必填)</span> 单位拼音：
						</label>
						<div class="col-md-10">
                            <input type="text" class="form-control" id="depart_unit_pinyin" 
                                   name="formBean.infoBean.depart_unit_pinyin" 
                                   value="<s:property value='formBean.infoBean.depart_unit_pinyin' />" 
                                   required   
                                   data-bv-notempty-message="单位拼音不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="字数不能超过50"
                                placeholder="请输入单位拼音，字数限制50  ，不能为空！"
                            >
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">(*必填)</span> 单位地址：
						</label>
						<div class="col-md-10">
                            <input type="text" class="form-control" id="depart_unit_address" 
                                   name="formBean.infoBean.depart_unit_address" 
                                   value="<s:property value='formBean.infoBean.depart_unit_address' />" 
                                   required   
                                   data-bv-notempty-message="单位地址不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="字数不能超过50"
                                placeholder="请输入单位地址，字数限制50  ，不能为空！"
                                
                            >
						</div>
					</div>
                  <div class="form-group">
                	    <label class="col-md-2 control-label">备注：</label>
                	    <div class="col-md-10">
                	          <textarea id="depart_unit_remark" name="formBean.infoBean.depart_unit_remark"  rows='10' class='form-control' placeholder="请输入备注信息，字数限制500！"><s:property value='formBean.infoBean.depart_unit_remark' /></textarea>
                	    </div>
                  </div>  
				</div>
				<div class="modal-footer" style="height: 40px;background-color: #FCFCFC;">
					<button type="submit" id="btn_save" class="btn btn-primary" style="margin-top: -14px">
						<i class="icon icon-save"></i> 保存
					</button>

					<button type="button" class="btn" onclick="_reset()" style="margin-top: -14px">
						<i class="icon icon-refresh"></i> 重置
					</button>
					
					<button type="button" class="btn" data-dismiss="modal" style="margin-top: -14px;margin-right:-12px">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			</div>
		</div>
	</div>


</form>




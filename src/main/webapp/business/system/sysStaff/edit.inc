<%@ page language="java" pageEncoding="UTF-8"%>

<form id="info_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">信息维护-人员信息</h4>
				</div>

				<div class="modal-body">
					<div style="display: none;">
						id<input type="text" class="form-control" id="id_staff" name="formBean.infoBean.id">
                        nm<input type="text" class="form-control" id="nm_staff" name="formBean.infoBean.nm">
                        flag<input type="text" class="form-control" id="flag_staff" name="formBean.infoBean.flag">
                        islogin<input type="text" class="form-control" id="islogin_staff" name="formBean.infoBean.islogin">
                        sysflag<input type="text" class="form-control" id="sysflag_staff" name="formBean.infoBean.sysflag">
                        createtime<input type="text" class="form-control" id="createtime_staff" name="formBean.infoBean.createtime">
                        updatetime<input type="text" class="form-control" id="updatetime_staff" name="formBean.infoBean.updatetime">
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 人员编码：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="code_staff" 
                                   name="formBean.infoBean.code" 
                                   value="<s:property value='formBean.infoBean.code' />" 
                                   required  maxlength="50" 
                                   data-bv-notempty-message="人员编码不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="字数不能超过50"
                                placeholder="请输入人员编码，字数限制50  ，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 人员名称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="name_staff" 
                                   name="formBean.infoBean.name" 
                                   value="<s:property value='formBean.infoBean.name' />" 
                                   required  maxlength="100" 
                                   data-bv-notempty-message="人员名称不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入人员名称，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
                    
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 手机号码：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="phone_staff" 
                                   name="formBean.infoBean.phone" 
                                   value="<s:property value='formBean.infoBean.phone' />" 
                                   required  maxlength="100" 
                                   data-bv-notempty-message="手机号码不能为空"   
                                   data-bv-stringlength-max="100"
                                placeholder="请输入手机号码，不能为空！"
                            >
						</div>
					</div>
                    
                    <div class="form-group">
						<label class="col-md-3 control-label"> 
                              电话号码：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="telephone_staff" 
                               	name="formBean.infoBean.telephone"
                               	value="<s:property value='formBean.infoBean.telephone' />" 
                        		placeholder="请输入电话号码！"
                            >
						</div>
					</div>
                    
                    
 					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              单位部门：
						</label>
						<div class="col-md-9">
							<select class="form-control" data-placeholder="选择单位部门..."
									id="treenmSysDept" 
                                    name="formBean.infoBean.treenmSysDept">
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              职务：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="duty_staff" 
                               	name="formBean.infoBean.duty" 
                               	value="<s:property value='formBean.infoBean.duty' />" 
                        		maxlength="500" placeholder="请输入职务！"
                               	data-bv-stringlength-max="500" data-bv-stringlength-message="字数不能超过500"
                            >
						</div>
					</div>
					
 					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              性别：
						</label>
						<div class="col-md-9">
							<select class="form-control" data-placeholder="选择性别..."
									id="dictnmXingbie" 
                                    name="formBean.infoBean.dictnmXingbie">
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                      	籍贯：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="origin_staff" 
                                   name="formBean.infoBean.origin" 
                                   value="<s:property value='formBean.infoBean.origin' />" 
                                   maxlength="500" placeholder="请输入籍贯！"
                                   data-bv-stringlength-max="500" data-bv-stringlength-message="字数不能超过500"
                            >
						</div>
					</div>
					
                  <div class="form-group">
                	    <label class="col-md-3 control-label">备注：</label>
                	    <div class="col-md-9">
                	          <textarea id="memo_staff" name="formBean.infoBean.memo" maxlength="500" rows='5' class='form-control' placeholder="请输入备注信息，字数限制500！"><s:property value='formBean.infoBean.memo' /></textarea>
                	    </div>
                  </div>  


				</div>
				<div class="modal-footer">
					<button type="submit" id="btn_save" class="btn btn-primary" >
						<i class="icon icon-save"></i> 保存
					</button>
					
					<button type="button" class="btn" data-dismiss="modal">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			</div>
		</div>
	</div>


</form>




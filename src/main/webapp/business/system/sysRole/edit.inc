<%@ page language="java" pageEncoding="UTF-8"%>

<form id="info_form_" name="info_form" class="form-horizontal" autocomplete="off"
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
					<h4 class="modal-title">信息维护-系统角色</h4>
				</div>

				<div class="modal-body">
					<div style="display: none;">
						id<input type="text" class="form-control" id="id_role" name="formBean.infoBean.id">
                        nm<input type="text" class="form-control" id="nm_role" name="formBean.infoBean.nm">
                        flag<input type="text" class="form-control" id="flag_role" name="formBean.infoBean.flag">
                        sysflag<input type="text" class="form-control" id="sysflag_role" name="formBean.infoBean.sysflag">
                        createtime<input type="text" class="form-control" id="createtime_role" name="formBean.infoBean.createtime">
                        updatetime<input type="text" class="form-control" id="updatetime_role" name="formBean.infoBean.updatetime">
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 角色编码：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="code_role" 
                                   name="formBean.infoBean.code" 
                                   value="<s:property value='formBean.infoBean.code' />" 
                                   required  maxlength="50" 
                                   onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
                                   data-bv-notempty-message="角色编码不能为空"   
                                   data-bv-stringlength-max="50" 
                                   data-bv-stringlength-message="字数不能超过50"
                                placeholder="请输入角色编码，字数限制50  ，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 角色名称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="name_role" 
                                   name="formBean.infoBean.name" 
                                   value="<s:property value='formBean.infoBean.name' />" 
                                   required  maxlength="100" 
                                   data-bv-notempty-message="角色名称不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入角色名称，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
					
                  
                  <div class="form-group">
                	    <label class="col-md-3 control-label">备注：</label>
                	    <div class="col-md-9">
                	          <textarea id="memo_role" name="formBean.infoBean.memo" maxlength="500" rows='10' class='form-control' placeholder="请输入备注信息，字数限制500！"><s:property value='formBean.infoBean.memo' /></textarea>
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

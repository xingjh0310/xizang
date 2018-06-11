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
					<h4 class="modal-title">信息维护</h4>
				</div>

				<div class="modal-body">
					<div style="display: none;">
						id<input type="text" class="form-control" id="id_icon" name="formBean.infoBean.id">
                        nm<input type="text" class="form-control" id="nm_icon" name="formBean.infoBean.nm">
                        flag<input type="text" class="form-control" id="flag_icon" name="formBean.infoBean.flag">
                        createtime<input type="text" class="form-control" id="createtime_icon" name="formBean.infoBean.createtime">
                        updatetime<input type="text" class="form-control" id="updatetime_icon" name="formBean.infoBean.updatetime">
					</div>
                    
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">(*必填)</span> 编码：
						</label>
						<div class="col-md-10">
                            <input type="text" class="form-control" id="code_icon" 
                                   name="formBean.infoBean.code" 
                                   value="<s:property value='formBean.infoBean.code' />" 
                                   required   
                                   data-bv-notempty-message="编码不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                placeholder="请输入编码，字数限制20  ，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">(*必填)</span> 名称：
						</label>
						<div class="col-md-10">
                            <input type="text" class="form-control" id="name_icon" 
                                   name="formBean.infoBean.name" 
                                   value="<s:property value='formBean.infoBean.name' />" 
                                   required   
                                   data-bv-notempty-message="名称不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入名称，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
                    
                  <div class="form-group">
                	    <label class="col-md-2 control-label">备注：</label>
                	    <div class="col-md-10">
                	          <textarea id="memo_icon" name="formBean.infoBean.memo"  rows='10' class='form-control' placeholder="请输入备注信息，字数限制500！"><s:property value='formBean.infoBean.memo' /></textarea>
                	    </div>
                  </div>  


				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" >
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




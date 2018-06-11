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

					<div style="display:none ;">
						id<input type="text" class="form-control" id="id_dept" name="formBean.infoBean.id">
                        nm<input type="text" class="form-control" id="nm_dept" name="formBean.infoBean.nm">
                        pnm<input type="text" class="form-control" id="pnm_dept" name="formBean.infoBean.pnm">
                        code<input type="text" class="form-control" id="code_dept" name="formBean.infoBean.code">
                        pcode<input type="text" class="form-control" id="pcode_dept" name="formBean.infoBean.pcode">
                        flag<input type="text" class="form-control" id="flag_dept" name="formBean.infoBean.flag">
                        createtime<input type="text" class="form-control" id="createtime_dept" name="formBean.infoBean.createtime">
                        updatetime<input type="text" class="form-control" id="updatetime_dept" name="formBean.infoBean.updatetime">
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              上级信息：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="pname_dept" readonly>
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 部门编码：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="thiscode_dept" 
                                   name="formBean.infoBean.thiscode" 
                                   required onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
                                   data-bv-notempty-message="部门编码不能为空"   
                                   data-bv-stringlength-max="10" 
                                   data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入部门编码，字数限制10  ，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 部门名称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="name_dept" 
                                   name="formBean.infoBean.name" 
                                   required   
                                   data-bv-notempty-message="部门名称不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入部门名称，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                             <span class="text-danger">(*必填)</span> 单位类型：
						</label>
						<div class="col-md-9">
							<select class="form-control" data-placeholder="选择单位类型.."
								id="type"  required
                                name="formBean.infoBean.type">
							</select>
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




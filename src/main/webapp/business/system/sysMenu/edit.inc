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
						id<input type="text" class="form-control" id="id_menu_" name="formBean.infoBean.id">
                        nm<input type="text" class="form-control" id="nm_menu_" name="formBean.infoBean.nm">
                        pnm<input type="text" class="form-control" id="pnm_menu_" name="formBean.infoBean.pnm">
                        code<input type="text" class="form-control" id="code_menu_" name="formBean.infoBean.code">
                        pcode<input type="text" class="form-control" id="pcode_menu_" name="formBean.infoBean.pcode">
                        flag<input type="text" class="form-control" id="flag_menu_" name="formBean.infoBean.flag">
                        createtime<input type="text" class="form-control" id="createtime_menu_" name="formBean.infoBean.createtime">
                        updatetime<input type="text" class="form-control" id="updatetime_menu_" name="formBean.infoBean.updatetime">
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              上级信息：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="pname_menu_" readonly>
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 本级编码：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="thiscode_menu_" 
                                   name="formBean.infoBean.thiscode" 
                                   required   maxlength="10" 
                                   data-bv-notempty-message="本级编码不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                placeholder="请输入本级编码，字数限制10  ，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 名称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="name_menu_" 
                                   name="formBean.infoBean.name" 
                                   required   maxlength="100" 
                                   data-bv-notempty-message="名称不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入名称，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              图标：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="icon_menu_" 
                                   name="formBean.infoBean.icon" 
                                placeholder="请输入图标，字数限制50  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              图标大小：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="iconsize_menu_" 
                                   name="formBean.infoBean.iconsize" 
                                placeholder="请输入图标大小，字数限制20  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              图标颜色：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="iconcolor_menu_" 
                                   name="formBean.infoBean.iconcolor" 
                                placeholder="请输入图标颜色，字数限制20  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              是否旋转：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="iconspin_menu_" 
                                   name="formBean.infoBean.iconspin" 
                                placeholder="请输入是否旋转，字数限制20  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              标题颜色：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="titlecolor_menu_" 
                                   name="formBean.infoBean.titlecolor" 
                                placeholder="请输入标题颜色，字数限制20  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              url：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="url_menu_" 
                                   name="formBean.infoBean.url" 
                                placeholder="请输入url，字数限制300  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 菜单唯一标识：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="menuflag_menu_" 
                                   name="formBean.infoBean.menuflag" 
                                   required   maxlength="50" 
                                   data-bv-notempty-message="菜单唯一标识不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="字数不能超过50"
                                placeholder="请输入菜单唯一标识，字数限制50  ，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 是否按钮：
						</label>
						<div class="col-md-9">
                              <select class="form-control" id="isbtn_menu_" name="formBean.infoBean.isbtn" >
							    <option value="0">否</option>
							    <option value="1">是</option>
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




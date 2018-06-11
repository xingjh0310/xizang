<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="info_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog">
		<div class="modal-dialog modal-lg" style="width:680px">
			<div class="modal-content">
				
				<div class="modal-header" style="background-color: #FCFCFC;height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;">新增物料信息</h4>
				</div>

					<div class="modal-body">
					<div style="display:none ;">
						id<input type="text" class="form-control" id="id_materiel" name="materielBaseFormBean.materielBase.id">
						treeCode<input type="text" class="form-control" id="carCode_materiel" name="materielBaseFormBean.materielBase.materialGroup">
						Code<input type="text" class="form-control" id="materielCode_materiel" name="materielBaseFormBean.materielBase.materielCode">
					</div>
                    
					<div class="form-group" style="margin-top: 10px">
					
					<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 物料编号：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="code_materiel" 
                                   name="materielBaseFormBean.materielBase.code" 
                                   value="<s:property value='materielBaseFormBean.materielBase.code' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="20"   
                                   data-bv-notempty-message="物料编号不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                placeholder="请输入物料编号"
                            >
						</div>
					
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 物料名称：
						</label>
						<div class="col-md-4 rowGroup">
								<input type="text" class="form-control " id="materielName_materiel" 
								name="materielBaseFormBean.materielBase.materielName" 
								 data-bv-group=".rowGroup" 
								 required
								 maxlength="20" 
								 data-bv-notempty-message="物料名称不能为空" 
								 data-bv-stringlength-max="20"
								 data-bv-stringlength-message="字数不能超过20"
								placeholder="请输入物料名称" > 
						</div>
						
					</div>
					
					 <div class="form-group" style="margin-top: -10px">
					 	<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 物料规格：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="materialNorms_materiel" 
                                   name="materielBaseFormBean.materielBase.materialNorms" 
                                   value="<s:property value='materielBaseFormBean.materielBase.materialNorms' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="20"   
                                   data-bv-notempty-message="物料规格不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                placeholder="请输入物料规格"
                            >
						</div>
					 	<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 国标物料：
						</label>
						<div class="col-md-4">
							<select id="state_materiel" name="materielBaseFormBean.materielBase.state" class="form-control">
								<option value="0">是</option>
								<option value="1">否</option>
							</select>
						</div>
                    </div>
					
					<div class="form-group" style="margin-top: -10px">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 计量单位：
						</label>
						<div class="col-md-4 rowGroup">
							
                         <select id="unit_materiel" class="form-control" name="materielBaseFormBean.materielBase.unit" required data-bv-group=".rowGroup" >
							
						</select>
						</div>
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 物料单价：
						</label>
						
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="price_materiel" 
                                   name="materielBaseFormBean.materielBase.price" 
                                   value="<s:property value='materielBaseFormBean.materielBase.price' />" 
                                   required
                                   data-bv-group=".rowGroup" 
                                   maxlength="10"   
                                   data-bv-notempty-message="物料单价不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                placeholder="请输入物料单价"
                            >
						</div>
						
					</div>
					<div class="form-group" style="margin-top: -10px">
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">&nbsp;&nbsp;</span> 物料描述：
						</label>
						
						<div class="col-md-10 rowGroup">
                            <input type="text" class="form-control" id="materielDesc_materiel" 
                                   name="materielBaseFormBean.materielBase.materielDesc" 
                                   value="<s:property value='materielBaseFormBean.materielBase.materielDesc' />" 
                                   data-bv-group=".rowGroup" 
                                   maxlength="100"   
                                   data-bv-notempty-message="物料分组不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入物料描述"
                            >
						</div>
						
					</div>
					<div class="form-group" style="margin-top: -10px">
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">&nbsp;&nbsp;</span> 计量描述：
						</label>
						
						<div class="col-md-10 rowGroup">
                            <input type="text" class="form-control" id="unitDesc_materiel" 
                                   name="materielBaseFormBean.materielBase.unitDesc" 
                                   value="<s:property value='materielBaseFormBean.materielBase.unitDesc' />" 
                                   data-bv-group=".rowGroup" 
                                   maxlength="20"   
                                   data-bv-notempty-message="物料分组不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入计量单位描述"
                            >
						</div>
						
					</div>
                  <div class="form-group" style="margin-top: -10px">
                	    <label class="col-md-2 control-label" style="margin: 18px auto;"><span class="text-danger"> &nbsp;</span>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	    <div class="col-md-10">
                	          <textarea id="remark_materiel" name="materielBaseFormBean.materielBase.remark" maxlength="300" rows='3' class='form-control' placeholder="请输入备注信息，字数限制500！"><s:property value='materielBaseFormBean.materielBase.remark' /></textarea>
                	    </div>
                  </div>  

				</div>
				<div class="modal-footer" style="height: 40px;background-color: #FCFCFC;">
					<button type="submit" id="material_save" class="btn btn-success" style="margin-top: -14px">
						<i class="icon icon-save"></i> 保存
					</button>

					<button type="button" class="btn btn-large btn-primary" data-dismiss="modal" style="margin-top: -14px;margin-right:-12px">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			</div>
		</div>
	</div>

</form>




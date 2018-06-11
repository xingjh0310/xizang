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
				
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">取消</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;margin-left: -5px;">新增人员信息</h4>
				</div>

					<div class="modal-body">
					<div style="display:none;">
						id<input type="text" class="form-control" id="id_mail" name="mailFormBean.mail.id">
						treeCode<input type="text" class="form-control" id="carCode_mail" name="mailFormBean.mail.treenmSysDept">
						nm<input type="text" class="form-control" id="nm_mail" name="mailFormBean.mail.nm">
						eng<input type="text" class="form-control" id="mail_eng" name="mailFormBean.mail.engineerCode">
					</div>
                    
					<div class="form-group" style="margin-top: 10px;margin-left: -30px;">
					
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="staffName_mail" 
                                   name="mailFormBean.mail.staffName" 
                                   value="<s:property value='mailFormBean.mail.staffName' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="20"   
                                   data-bv-notempty-message="姓名不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                placeholder="请输入姓名">
						</div>
					
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：
						</label>
						<div class="col-md-4 rowGroup">
                         <select id="position_mail" class="form-control" name="mailFormBean.mail.position" required data-bv-group=".rowGroup" >
							
						</select>
						</div>
					</div>
					
					 <div class="form-group" style="margin-top: -10px;margin-left: -30px;">
					 
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 联系电话：
						</label>
						
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="linkPhone_mail" 
                                    
                                   value="<s:property value='mailFormBean.mail.linkPhone' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   maxlength="11"   
                                   data-bv-notempty-message="联系电话不能为空"   
                                   data-bv-stringlength-max="11" data-bv-stringlength-message="联系电话不能超过11"
                                placeholder="请输入联系电话" >
                                <input type="hidden" id="tel" name="mailFormBean.mail.linkPhone" />
						</div>
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：
						</label>
						<div class="col-md-4">
							<select id="sex_mail" name="mailFormBean.mail.sex" class="form-control">
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
						</div>
                    </div>
					<div class="form-group" style="margin-top: -10px;margin-left: -30px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">&nbsp;&nbsp;</span> 邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="email" class="form-control" id="email_mail" 
                                   value="<s:property value='mailFormBean.mail.email' />" 
                                   name="memail"
                                   data-bv-group=".rowGroup" 
                                   maxlength="30"   
                                   data-bv-notempty-message="邮箱不能为空"   
                                   data-bv-stringlength-max="30" data-bv-stringlength-message="字数不能超过30"
                               	 placeholder="请输入邮箱"/>
                                 <input type="hidden" id="ema" name="mailFormBean.mail.email" />
						</div>
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">&nbsp;&nbsp;</span> 固定电话：
						</label>
						
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="fixTele_mail" 
                                    
                                   value="<s:property value='mailFormBean.mail.FixTele' />" 
                                   data-bv-group=".rowGroup" 
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   maxlength="11"   
                                   data-bv-notempty-message="固定电话不能为空"   
                                   data-bv-stringlength-max="11" data-bv-stringlength-message="固定电话不能超过12"
                                placeholder="请输入固定电话 ">
                                 <input type="hidden" id="fix" name="mailFormBean.mail.FixTele" />
						</div>
						
					</div>
					<div class="form-group" style="margin-top: -10px;margin-left: -30px;">
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">&nbsp;&nbsp;</span> 地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：
						</label>
						<div class="col-md-10 rowGroup">
							<input type="text" class="form-control" id="address_mail" 
                                   
                                   value="<s:property value='mailFormBean.mail.address' />" 
                                   data-bv-group=".rowGroup" 
                                   maxlength="50"   
                                   data-bv-notempty-message="地址不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过50"
                                placeholder="请输入地址">
                                 <input type="hidden" id="addre"  name="mailFormBean.mail.address" />
						</div>
					
					</div>
					
                  <div class="form-group" style="margin-top: -10px; margin-left:-30px;">
                	    <label class="col-md-2 control-label" style="margin: 18px auto;"><span class="text-danger"> &nbsp;</span>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	    <div class="col-md-10">
                	          <textarea id="remark_mail" name="mailFormBean.mail.remark" maxlength="300" rows='3' class='form-control' placeholder="请输入备注信息，字数限制500！"><s:property value='mailFormBean.mail.remark' /></textarea>
                	    </div>
                  </div>  

				</div>
				<div class="modal-footer foter" >
					<button type="submit" id="mail_save" class="btn btn-primary bottom-btn" >
						<i class="icon icon-download-alt ion-left"></i> 保存
					</button>

					<button type="button" class="btn btn-large bottom-btn" data-dismiss="modal">
						<i class="icon icon-times ion-left"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>

</form>




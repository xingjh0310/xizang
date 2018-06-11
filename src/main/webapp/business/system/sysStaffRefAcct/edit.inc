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
						id<input type="text" class="form-control" id="id_staff_" name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.id"/>
						nm<input type="text" class="form-control" id="nm_staff_" name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.nm">
						flag<input type="text" class="form-control" id="flag_staff_" name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.flag">
						memo<input type="text" class="form-control" id="memo_staff_" name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.memo">
						sysflag<input type="text" class="form-control" id="sysflag_staff_" name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.sysflag">
						isLogin<input type="text" class="form-control" id="isLogin_staff_" name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.isLogin">
						phone<input type="text" class="form-control" id="phone_staff_" name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.phone">
						engineerNm<input type="text" class="form-control" id="engineerNm_staff_" name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.engineerNm">
						id<input type="text" class="form-control" id="id_acct_" name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.id">
						nm<input type="text" class="form-control" id="nm_acct_" name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.nm">
						pwd<input type="text" class="form-control" id="pwd_acct_" name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.pwd">
						flag<input type="text" class="form-control" id="flag_acct_" name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.flag">
						sysflag<input type="text" class="form-control" id="sysflag_acct_" name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.sysflag">
						yxq<input type="text" class="form-control" id="yxq_acct_" name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.yxq">
						yzm<input type="text" class="form-control" id="yzm_acct_" name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.yzm">
						yzmyxq<input type="text" class="form-control" id="yzmyxq_acct_" name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.yzmyxq">
						yzyxcs<input type="text" class="form-control" id="yzyxcs_acct_" name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.yzyxcs">
						dictnmZhlx<input type="text" class="form-control" id="dictnmZhlx_acct_" name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.dictnmZhlx">
						dictnmDllx<input type="text" class="form-control" id="dictnmDllx_acct_" name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.dictnmDllx">
					 	treenmSysDept_<input type="text" class="form-control" id="treenmSysDept_" name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.treenmSysDept"/>
						code_staff_<input type="text" class="form-control" id="code_staff_" name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.code"/>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 人员名称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="name_staff_" 
                                   name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.name" 
                                   required  maxlength="10" 
                                   onkeyup="value=value.replace(/[\d]/g,'') "
                                   onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\d]/g,''))"
                                   data-bv-notempty-message="人员名称不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入人员名称，字数限制10  ，不能为空！"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 手机号：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="name_acct_" 
                                   name="mSysStaffRefAcctFormBean.mSysAcctInfoBean.name" 
                                   required  maxlength="11" onblur="event_CheckName(this.value)"
                                   data-bv-notempty-message="手机号不能为空"   
                                   data-bv-stringlength-max="11" data-bv-stringlength-message="字数不能超过11位"
                                placeholder="请输入手机号，字数限制11  ，不能为空！"
                            >
						</div>
					</div>
                    
                    <div class="form-group">
						<label class="col-md-3 control-label"> 
                              固定电话：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="telephone_staff_" 
                               	name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.telephone"
                        		placeholder="请输入固定电话！"
                            >
						</div>
					</div>
                    
                    
 					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              单位部门：
						</label>
						<div class="col-md-9">
							<div class="input-group">
						    	<input type="text" class="form-control" readonly="true" id="treenmSysDept" placeholder="请选择部门！"/>
		                         <span class="input-group-btn">
									<button class="btn btn-primary" onclick="loadDeptData()" type="button">
										<div class="visible-md visible-lg"><i class="icon icon-refresh"></i>&nbsp;选择部门</div>
										<div class="visible-xs visible-sm"><i class="icon icon-refresh"></i></div>
									</button>
								</span>
						    </div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              职务：
						</label>
						<div class="col-md-9">
                            <select class="form-control" data-placeholder="选择职务..."
									id="duty_staff_" 
                                    name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.duty">
							</select>
						</div>
					</div>
					
 					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              性别：
						</label>
						<div class="col-md-9">
							<select class="form-control" data-placeholder="选择性别..."
									id="dictnmXingbie" 
                                    name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.dictnmXingbie">
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                      	籍贯：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="origin_staff_" 
                                   name="mSysStaffRefAcctFormBean.mSysStaffInfoBean.origin" 
                                   maxlength="500" placeholder="请输入籍贯！"
                                   data-bv-stringlength-max="500" data-bv-stringlength-message="字数不能超过500"
                            >
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




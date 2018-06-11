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
					<h4 class="modal-title">信息维护-账户信息</h4>
				</div>

				<div class="modal-body">
					<div style="display: none;">
					id			<input type="text" class="form-control" id="id_acct" name="formBean.infoBean.id">
                    nm			<input type="text" class="form-control" id="nm_acct" name="formBean.infoBean.nm">
                    flag		<input type="text" class="form-control" id="flag_acct" name="formBean.infoBean.flag">
                    createtime	<input type="text" class="form-control" id="createtime_acct" name="formBean.infoBean.createtime">
                    updatetime	<input type="text" class="form-control" id="updatetime_acct" name="formBean.infoBean.updatetime">
                    yzm			<input type="text" class="form-control" id="yzm_acct" name="formBean.infoBean.yzm">
                    yzmyxq		<input type="text" class="form-control" id="yzmyxq_acct" name="formBean.infoBean.yzmyxq">
					yzyxcs		<input type="text" class="form-control" id="yzyxcs_acct" name="formBean.infoBean.yzyxcs">
					dictnmZhlx	
								<input id="dictnmZhlx_xx" />
								<select class="form-control" data-placeholder="选择账户类型..."
									id="dictnmZhlx" 
                                    name="formBean.infoBean.dictnmZhlx"></select>
					sysflag		<input type="text" class="form-control" id="sysflag_acct" name="formBean.infoBean.sysflag">
					pwd			<input type="text" class="form-control" id="pwd_acct" name="formBean.infoBean.pwd">
					</div>
                    
 					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              系统人员：
						</label>
						<div class="col-md-9">
							<select class="form-control" data-placeholder="选择系统人员..."
									id="listnmSysStaff" 
                                    name="formBean.infoBean.listnmSysStaff"></select>
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 账户名称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="name_acct" 
                                   name="formBean.infoBean.name" 
                                   value="<s:property value='formBean.infoBean.name' />" 
                                   required  maxlength="100" 
                                   data-bv-notempty-message="账户名称不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入账户名称，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
                    
 					<div class="form-group">
						<label class="col-md-3 control-label"> 
                             <span class="text-danger">(*必填)</span> 登录类型：
						</label>
						<div class="col-md-9">
							<select class="form-control" data-placeholder="选择登录类型..."
								id="dictnmDllx"  required
                                name="formBean.infoBean.dictnmDllx">
							</select>
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              有效期截止日期：
						</label>
                        <div class="col-md-9">
                             <input type="date" class="form-control" id="yxq_acct" 
                                   name="formBean.infoBean.yxq" 
                                   value="<s:property value='formBean.infoBean.yxq' />" 
                                   required   
                                data-bv-notempty-message="有效期不能为空"    
                                placeholder="请选择有效期  "
                            >
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<span class="label label-danger pull-left"><h6>新增用户默认密码为：123456,请登录后修改默认密码</h6></span>
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




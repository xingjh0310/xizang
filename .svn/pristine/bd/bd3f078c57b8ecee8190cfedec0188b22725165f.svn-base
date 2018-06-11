<%@ page language="java" pageEncoding="UTF-8"%>

<form id="info_form_" name="info_form_" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog_">
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
						id<input type="text" class="form-control" id="id_sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.id">
                        nm<input type="text" class="form-control" id="nm_sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.nm">
                        pnm<input type="text" class="form-control" id="pnm_sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.pnm">
                        code<input type="text" class="form-control" id="code_sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.engineerCode">
                        pcode<input type="text" class="form-control" id="pcode_sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.pcode">
                        flag<input type="text" class="form-control" id="flag_sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.flag">
                        createtime<input type="text" class="form-control" id="createtime_sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.createtime">
                        updatetime<input type="text" class="form-control" id="updatetime_sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.updatetime">
					    treenmSysDept_sysWorkInfo<input type="text" class="form-control" id="treenmSysDept_sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.treenmSysDept"/>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              上级信息：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="pname_sysWorkInfo" readonly>
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 工程编号：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="thiscode_sysWorkInfo" 
                                   name="mSysEngineerInfoFormBean.mSysEngineerInfo.thiscode" 
                                   required onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
                                   data-bv-notempty-message="本级编码不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                placeholder="请输入本级编码，字数限制10  ，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> <span id="engineerName_span">工程名称：</span>
						</label>
						<div class="col-md-9">
                            <div class="input-group">
                            	<input type="hidden" id="type_sysWorkInfo" value="1"
                                   name="mSysEngineerInfoFormBean.mSysEngineerInfo.type">
								<input type="text" class="form-control" id="engineerName_sysWorkInfo" 
                                   name="mSysEngineerInfoFormBean.mSysEngineerInfo.engineerName" 
                                   required   
                                   data-bv-notempty-message="工程名称不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
	                                placeholder="请输入工程名称，字数限制100  ，不能为空！"
	                            >
	                            <span class="input-group-btn">
									<button class="btn btn-primary" id="switch_def" type="button">
									   <div class="visible-md visible-lg"><i class="icon icon-refresh"></i>&nbsp;切换</div>
									   <div class="visible-xs visible-sm"><i class="icon icon-refresh"></i></div>
									</button>
								</span>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 工程简称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="engineerShort_sysWorkInfo" 
                                   name="mSysEngineerInfoFormBean.mSysEngineerInfo.engineerShort" 
                                   required   
                                   data-bv-notempty-message="工程简称不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入工程简称，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
                    
 					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              建管单位：
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
                              电压等级：
						</label>
						<div class="col-md-9">
							<select class="form-control" 
									id="voltageEvel_sysWorkInfo"  required 
                                     name="mSysEngineerInfoFormBean.mSysEngineerInfo.voltageEvel" >
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              建设线路长度(千米)：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="lineLength_sysWorkInfo" 
                                   name="mSysEngineerInfoFormBean.mSysEngineerInfo.lineLength" 
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入分类，字数限制100！"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              设计投运时间：
						</label>
						<div class="col-md-9">
                            <a class="input-group date "style="float:right;margin-top:-3px;">
	                    		<label class="input" style="display:inline;">
	                    		<input class="form-control laydate"style="width:606px;height: 32px;" type="text" id="deliveryTime_sysWorkInfo" 
	                    		name="mSysEngineerInfoFormBean.mSysEngineerInfo.deliveryTime"
	                    		data-bv-group=".rowGroup" readonly="readonly" placeholder="请选择设计投运时间"/>
	                    		<span class="input-group-addon" style="float:right;width: 40px; height: 32px;">
                   		 			<span class="icon-calendar"></span>
                				</span>
                				</label>
                				</a>
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

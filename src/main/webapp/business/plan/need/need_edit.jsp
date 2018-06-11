<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.form-group{
	margin-bottom:5px;
}
</style>

<form id="info_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="info_dialog">
		<div class="modal-dialog modal-lg" style="width:800px">
			<div class="modal-content">
			
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close modal_header_top" data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title modal_header_top"><i class="icon icon-pencil"></i>&nbsp;需求清单修改</h4>
				</div>
			
				<div class="modal-body">
					<div style="display: none;">
						<!-- materialDetailId<input type="text" class="form-control" id="DetailID" name="fMaterialDetailFormBean.mMaterialDetail.id"> -->
						needId<input type="text" class="form-control" id="ID" name="fDemandFormBean.mDemand.id">
						engineerCode<input type="text" class="form-control" id="ENGINEERCODE" name="fDemandFormBean.mDemand.engineerCode">
						
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>计划编号：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="PLANCODE" 
                                   name="fDemandFormBean.mDemand.planCode" 
                                   style="background-color: #fff;"
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>计划年度：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="PLANYEAR" 
                                   name="fDemandFormBean.mDemand.planYear" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="4" 
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   data-bv-notempty-message="计划年度不能为空"   
                                   data-bv-stringlength-max="4" data-bv-stringlength-message="字数不能超过4"
                                   placeholder="请输入计划年度"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>项目名称：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="PROJECTNAME" 
                                   name="fDemandFormBean.mDemand.projectName" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="20"
                                   data-bv-notempty-message="项目名称不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入项目名称"
                            >
						</div>

						<label class="col-md-2 control-label" style="padding-left:0px"> 
                              <span class="text-danger">*&nbsp;</span>项目电压等级：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="PROJECTVOLTAGELEVE" 
                                   name="fDemandFormBean.mDemand.projectVoltageLeve" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="20"
                                   data-bv-notempty-message="项目电压不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入项目电压"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label" style="padding-left:0px"> 
                              <span class="text-danger">*&nbsp;</span>物资电压等级：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="MATERIALVOLTAGELEVE" 
                                   name="fDemandFormBean.mDemand.materialVoltageLeve" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="20"
                                   data-bv-notempty-message="物资电压不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入物资电压"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>施工单位：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="CONSTRUCTDEPT" 
                                   name="fDemandFormBean.mDemand.constructDept" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
                                   maxlength="20"
                                   data-bv-notempty-message="施工单位不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入施工单位"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label" style="padding-left:0px"> 
                              <span class="text-danger">*&nbsp;</span>估算总价(万)：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="ESTIMATETOTALPRICE" 
                                   name="fDemandFormBean.mDemand.estimateTotalPrice" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   maxlength="10"
                                   data-bv-notempty-message="估算总价不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入估算总价"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>需求单位：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="DEMANDUNIT" 
                                   name="fDemandFormBean.mDemand.demandUnit" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
                                   maxlength="20"
                                   data-bv-notempty-message="需求单位不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入需求单位"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>需求日期：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date'  style="float: left;margin-top:0px">
								<label class="input" style="display:inline">
		                            <input type="text" class="form-control laydate" id="PLANDATE" 
		                                   name="fDemandFormBean.mDemand.planDate"  style="width: 205px; height: 32px;background-color: #fff;"
		                                   data-bv-group=".rowGroup" readonly="readonly"
		                                   required	
		                                   maxlength="20"
		                                   data-bv-notempty-message="需求日期不能为空"   
		                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
		                                   placeholder="请选择需求日期">
		                             <span class="input-group-addon" style=" width: 39px; height: 32px;background-color: #f9f9f9;">
	                   		 			<i class="icon icon-calendar"></i>
	                				 </span>
	                            </label>
                            </a>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>联&nbsp;&nbsp;系&nbsp;人：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="LINKMAN" 
                                   name="fDemandFormBean.mDemand.linkMan" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
                                   maxlength="20"
                                   data-bv-notempty-message="联系人不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入联系人"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>联系电话：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="LINKPHONE" 
                                   name="fDemandFormBean.mDemand.linkPhone" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   maxlength="11"
                                   data-bv-notempty-message="联系电话不能为空"   
                                   data-bv-stringlength-max="11" data-bv-stringlength-message="字数不能超过11"
                                   placeholder="请输入联系电话"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>需求地点：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="DEMANDPLACE" 
                                   name="fDemandFormBean.mDemand.demandPlace" 
                                   data-bv-group=".rowGroup" 
                                   required	
                                   maxlength="20"
                                   data-bv-notempty-message="需求地点不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                   placeholder="请输入需求地点"
                            >
						</div>
					</div>
					
				    <div class="form-group" style="margin-bottom: 0px;">
                	    <label class="col-md-2 control-label" style="margin: 18px auto;"><span class="text-danger"> &nbsp;</span>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	    <div class="col-md-10">
                	    	<textarea id="REMARK" name="fDemandFormBean.mDemand.remark" maxlength="300" rows='3' class='form-control' placeholder="请输入备注信息，字数限制500！"></textarea>
                	    </div>
                    </div>
                    
                    <div class="btn-toolbar" style="margin: 10px 0px;">
						<div style="float:left">
							<h3 style="font-size: 14px;margin: 0;line-height: 33px;"><i class="icon icon-tags"></i>&nbsp;物资明细</h3>
						</div>
						<div class="btn-group pull-right">
							<button type="button" id="material_add" class="btn btn-primary">
							   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-plus-sign"></i></div>
							</button>
						</div>
					</div>
					
					<div class="form-group need_material_table">
						<div class="col-md-12 rowGroup" style="max-height:240px;overflow:auto">
							<table class="table table-bordered need_material_table">
								<thead>
									<tr>
										<th style="text-align: center;width:6%;background-color: #f9f9f9;">序号</th>
										<th style="text-align: center;width:15%;background-color: #f9f9f9;">物料名称</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">计量单位</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">规格型号</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">数量</th>
										<th style="text-align: center;width:26%;background-color: #f9f9f9;">货物描述</th>
										<th style="text-align: center;width:8%;background-color: #f9f9f9;">操作</th>
									</tr>
								</thead>
								<tbody id="data_body">
								</tbody>
							</table>
							<input type="hidden" name="fMaterialDetailFormBean.ids" id="removeIds"/>
						</div>
					</div>
					
				</div>
				
					
				<div class="modal-footer" style="height: 40px;">
					<button type="submit" id="btn_save" class="btn btn-primary" style="margin-top: -14px">
						<i class="icon icon-download-alt"></i> 保存
					</button>

					<button type="button" class="btn" data-dismiss="modal" style="margin-top: -14px;margin-right:-5px">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>
	
</form>
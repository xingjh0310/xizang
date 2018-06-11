<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
</style>
<form id="info_form" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="edit_dialog">
		<div class="modal-dialog modal-lg" style="width: 900px">
			<div class="modal-content">

				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>新增合同</h4>
				</div>

				<div class="modal-body-check" style="padding-top: 8px;padding-bottom: 0px;">
					<div style="display: none;">
						id<input type="text" class="form-control" id="id"
							name="contInfoFormBean.contInfoBean.id">
						contractNo<input type="text" class="form-control" id="contractNo"
							name="contInfoFormBean.contInfoBean.contractNo">
						contractState<input type="text" class="form-control" id="contractState"
							name="contInfoFormBean.contInfoBean.contractState">
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left: -70px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 合同标题：
						</label>
						<div class="col-md-10 rowGroup">
                            <input type="text" class="form-control" id="contractTitle" 
                                   name="contInfoFormBean.contInfoBean.contractTitle" 
                                   value="<s:property value='contInfoFormBean.contInfoBean.contractTitle' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="25"
                                   data-bv-notempty-message="合同标题不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="不能超过25个字"
                                placeholder="请输入合同标题"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left: -70px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 所属工程：
						</label>
						<div class="col-md-10 rowGroup" >
							<input type="hidden" id="engineerCode" name="contInfoFormBean.contInfoBean.engineerCode"/>
							<input type="text" class="form-control" id="engineerName" 
	                            name="contInfoFormBean.contInfoBean.engineerName" 	         
	                            required readonly="readonly"/>

						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left: -70px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 需求单位：
						</label>
						<div class="col-md-4 rowGroup">
                            <select id="demandUnit" name="contInfoFormBean.contInfoBean.demandUnit" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required 
                            	data-bv-notempty-message="请选择需求单位"  
                            >
							</select>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;段：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="section" 
                                   name="contInfoFormBean.contInfoBean.section" 
                                   value="<s:property value='contInfoFormBean.contInfoBean.section' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="15"
                                   data-bv-notempty-message="标段不能为空"   
                                   data-bv-stringlength-max="30" data-bv-stringlength-message="不能超过15个字"
                                placeholder="请输入标段"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left: -70px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 供货厂商：
						</label>
						<div class="col-md-4 rowGroup">
                            <select id="supply" name="contInfoFormBean.contInfoBean.supply" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required 
                            	data-bv-notempty-message="请选择供货厂商"  
                            >
							</select>
						</div>
						<label class="col-md-2 control-label"> <span
							class="text-danger">*&nbsp;</span> 合同金额(万元)：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="contractAmount" 
                                   name="contInfoFormBean.contInfoBean.contractAmount" 
                                   value="<s:property value='contInfoFormBean.contInfoBean.contractAmount' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="30"
                                   onkeyup="value=value.replace(/^(\d*\.?\d{0,2}).*/,'$1')"
                                   data-bv-notempty-message="合同金额不能为空"   
                                   data-bv-stringlength-max="30" data-bv-stringlength-message="不能超过30个字"
                                placeholder="请输入合同金额（万元）"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-left: -70px;">
						<label class="col-md-2 control-label" style="margin-top: 5px;"> <span
							class="text-danger">*&nbsp;</span> 招标日期：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date' style="float: left;"> <label
								class="input" style="display: inline"> <input
									type='text' class="form-control laydate" autofocus="autofocus"
									style="width: 253px; height: 30px;background-color:#fff;" readonly="readonly"
									id="biddeDate" data-bv-group=".rowGroup"
									name="contInfoFormBean.contInfoBean.biddeDate" required
									data-bv-notempty-message="招标日期不能为空"
									value="<s:property value='contInfoFormBean.contInfoBean.biddeDate' />"
									placeholder="请选择日期"> <span class="input-group-addon"
									style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
										class="icon-calendar"></span>
								</span>
							</label>
							</a>
						</div>
						<label class="col-md-2 control-label" style="margin-top: 5px;"> <span
							class="text-danger">*&nbsp;</span> 签订日期：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date' style="float: left;"> <label
								class="input" style="display: inline"> <input
									type='text' class="form-control laydate" autofocus="autofocus"
									style="width: 253px; height: 30px;background-color:#fff;" readonly="readonly"
									id="signDate" data-bv-group=".rowGroup"
									name="contInfoFormBean.contInfoBean.signDate" required
									data-bv-notempty-message="签订日期不能为空"
									value="<s:property value='contInfoFormBean.contInfoBean.signDate' />"
									placeholder="请选择日期"> <span class="input-group-addon"
									style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
										class="icon-calendar"></span>
								</span>
							</label>
							</a>
						</div>
					</div>
					<div class="form-group" style="margin-left: -70px;">
						<label class="col-md-2 control-label" style="margin-top: 5px;"> <span
							class="text-danger">*&nbsp;</span> 合同开始：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date' style="float: left;"> <label
								class="input" style="display: inline"> <input
									type='text' class="form-control laydate" autofocus="autofocus"
									style="width: 253px; height: 30px;background-color:#fff;" readonly="readonly"
									id="contractStartDate" data-bv-group=".rowGroup"
									name="contInfoFormBean.contInfoBean.contractStartDate" required
									data-bv-notempty-message="合同开始日期不能为空"
									value="<s:property value='contInfoFormBean.contInfoBean.contractStartDate' />"
									placeholder="请选择日期"> <span class="input-group-addon"
									style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
										class="icon-calendar"></span>
								</span>
							</label>
							</a>
						</div>
						<label class="col-md-2 control-label" style="margin-top: 5px;"> <span
							class="text-danger">*&nbsp;</span> 合同结束：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date' style="float: left;"> <label
								class="input" style="display: inline"> <input
									type='text' class="form-control laydate" autofocus="autofocus"
									style="width: 253px; height: 30px;background-color:#fff;" readonly="readonly"
									id="contractEndDate" data-bv-group=".rowGroup"
									name="contInfoFormBean.contInfoBean.contractEndDate" required
									data-bv-notempty-message="合同结束日期不能为空"
									value="<s:property value='contInfoFormBean.contInfoBean.contractEndDate' />"
									placeholder="请选择日期"> <span class="input-group-addon"
									style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
										class="icon-calendar"></span>
								</span>
							</label>
							</a>
						</div>
					</div>
					<div class="form-group" style="margin-left: -70px;">
						<label class="col-md-2 control-label" style="margin-top: 5px;"> <span
							class="text-danger">*&nbsp;</span> 供货开始：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date' style="float: left;"> <label
								class="input" style="display: inline"> <input
									type='text' class="form-control laydate" autofocus="autofocus"
									style="width: 253px; height: 30px;background-color:#fff;" readonly="readonly"
									id="supplyStartDate" data-bv-group=".rowGroup"
									name="contInfoFormBean.contInfoBean.supplyStartDate" required
									data-bv-notempty-message="供货开始日期不能为空"
									value="<s:property value='contInfoFormBean.contInfoBean.supplyStartDate' />"
									placeholder="请选择日期"> <span class="input-group-addon"
									style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
										class="icon-calendar"></span>
								</span>
							</label>
							</a>
						</div>
						<label class="col-md-2 control-label" style="margin-top: 5px;"> <span
							class="text-danger">*&nbsp;</span> 供货结束：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date' style="float: left;"> <label
								class="input" style="display: inline"> <input
									type='text' class="form-control laydate" autofocus="autofocus"
									style="width: 253px; height: 30px;background-color:#fff;" readonly="readonly"
									id="supplyEndDate" data-bv-group=".rowGroup"
									name="contInfoFormBean.contInfoBean.supplyEndDate" required
									data-bv-notempty-message="供货结束日期不能为空"
									value="<s:property value='contInfoFormBean.contInfoBean.supplyEndDate' />"
									placeholder="请选择日期"> <span class="input-group-addon"
									style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
										class="icon-calendar"></span>
								</span>
							</label>
							</a>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left: -70px;">
						<label class="col-md-2 control-label" style="margin-top: 20px"> 
							 备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
						</label>
						<div class="col-md-10 rowGroup">
                            <textarea id="remark" name="contInfoFormBean.contInfoBean.remark"  rows='3' maxlength="250" class='form-control' placeholder="请输入备注信息，字数限制250！"><s:property value='contInfoFormBean.contInfoBean.remark' /></textarea>
						</div>
					</div>
<!-- 					<div class="form-group" style="margin-top: 10px"> -->
<!-- 						<label class="col-md-2 control-label"> -->
<!-- 							 附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件： -->
<!-- 						</label> -->
<!-- 						<div class="col-md-10 rowGroup"> -->
<!--                             <input type="file" class="form-control"/> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="btn-toolbar" style="margin-top: 20px;margin-left: 0px;margin-bottom: 10px;">
						<div style="float:left">
							<h3 style="font-size: 14px;"><i class="icon icon-tags"></i>&nbsp;物资明细</h3>
						</div>
						<div class="btn-group pull-right">
							<button type="button" id="material_add" class="btn btn-primary">
							   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-file-o"></i></div>
							</button>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12 rowGroup" style="max-height:240px;overflow:auto">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th style="text-align: center;width:5.5%;background-color: #f9f9f9;">序号</th>
										<th style="text-align: center;width:15%;background-color: #f9f9f9;">物料名称</th>
										<th style="text-align: center;width:8.5%;background-color: #f9f9f9;">计量单位</th>
										<th style="text-align: center;width:8.5%;background-color: #f9f9f9;">规格型号</th>
										<th style="text-align: center;width:6.5%;background-color: #f9f9f9;">数量</th>
										<th style="text-align: center;width:11%;background-color: #f9f9f9;">单价（元）</th>
										<th style="text-align: center;width:15%;background-color: #f9f9f9;">总价（万元）</th>
										<th style="text-align: center;width:22%;background-color: #f9f9f9;">货物描述</th>
										<th style="text-align: center;width:8%;background-color: #f9f9f9;">操作</th>
									</tr>
								</thead>
								<tbody id="data_body">
								</tbody>
							</table>
							<input type="hidden" name="contInfoFormBean.ids" id="removeIds"/>
						</div>
					</div>
				</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="submit" id="mng_save" class="btn btn-primary"
						style="margin-top: -14px">
						<i class="icon icon-download-alt"></i> 保存
					</button>

					<button type="button" class="btn btn-large"
						data-dismiss="modal"
						style="margin-top: -14px; margin-right: -5px;">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>

</form>




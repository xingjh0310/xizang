<%@ page language="java" pageEncoding="UTF-8"%>

<form id="transport_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="info_dialog"  >
		<div class="modal-dialog modal-lg" style="width:1024px;">
			<div class="modal-content">
			
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;"><i class="icon icon-cube">&nbsp;物资到货</i></h4>
				</div>
			
				<div class="modal-body-check" style="padding:10px;">
				<div style="display:none">
						发货ID<input type="text" class="form-control" id="ID" name="materielFormBean.ids">
						engineerCode<input type="text" class="form-control" id="ENGINEERCODE" name="materielFormBean.transferInfoBean.engineerCode">
						handover <input type =text class="form-control"  id="ARRPLANNUMS" name ="materielFormBean.transferInfoBean.handover"  />
				</div>
				
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>物资发货通知单：
						</label>
						<div class="col-md-10 rowGroup">
                            <input type="text" class="form-control" id="CONTRACTNO" name="materielFormBean.deliveryInfoBean.contractNo" readonly="readonly"/>
						</div>
					 </div>
					 <div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>物资交接单标题：
						</label>
						<div class="col-md-10 rowGroup">
                            <input type="text" class="form-control" id="CONTRACTNO" name="materielFormBean.deliveryInfoBean.contractNo" readonly="readonly"/>
						</div>
					 </div>
					<div class="form-group">
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>所属工程：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="SUPPLYFULLNAME"  readonly="readonly">
						</div>
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>采购订单号：
						</label>
						<div class="col-md-4 rowGroup" id="loadTreeDate">
                            <input type="text" class="form-control" id="DEPTNAME" data-bv-group=".rowGroup"  readonly="readonly">
							
						</div>
					</div>
					<div class="form-group">
					<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>供货厂商：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="MATERIELNAME" readonly="readonly">
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>项目单位：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="MEAUNIT" 
                                   name="materielFormBean.deliveryInfoBean.unit" 
                                   data-bv-group=".rowGroup"  readonly="readonly">
						</div>
					</div>
					
					 <div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>供货商联系人：
						</label>
						<div class="col-md-4 rowGroup">
                            
                             <input type="text" class="form-control" id="SUPPLYCONTACT" name="materielFormBean.deliveryInfoBean.supplierContact" readonly="readonly" />
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>联系电话：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="SUPPLYPHONE" name="materielFormBean.deliveryInfoBean.supplierLinkPhone" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>供货商联系人：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="CARRIER" 
                                   name="materielFormBean.deliveryInfoBean.carrier"
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
                                   maxlength="10"
                                   data-bv-notempty-message="供货商联系人不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入供货商联系人" />
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>联系电话：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="CARRIERPHONE" 
                                   name="materielFormBean.deliveryInfoBean.carrierPhone"
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   maxlength="11"
                                   data-bv-notempty-message="联系电话不能为空"   
                                   data-bv-stringlength-max="11" data-bv-stringlength-message="字数不能超过11"
                                   placeholder="请输入联系电话"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>收货人：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="CARRIER" 
                                   name="materielFormBean.deliveryInfoBean.carrier"
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
                                   maxlength="10"
                                   data-bv-notempty-message="供货商联系人不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入供货商联系人" />
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>收货人电话：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="CARRIERPHONE" 
                                   name="materielFormBean.deliveryInfoBean.carrierPhone"
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   maxlength="11"
                                   data-bv-notempty-message="联系电话不能为空"   
                                   data-bv-stringlength-max="11" data-bv-stringlength-message="字数不能超过11"
                                   placeholder="请输入联系电话"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>承运人：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="CARRIER" 
                                   name="materielFormBean.deliveryInfoBean.carrier"
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
                                   maxlength="10"
                                   data-bv-notempty-message="供货商联系人不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入供货商联系人" />
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>承运人电话：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="CARRIERPHONE" 
                                   name="materielFormBean.deliveryInfoBean.carrierPhone"
                                   data-bv-group=".rowGroup" 
                                   required	
                                   onkeyup="value=value.replace(/\D/g,'')"
                                   maxlength="11"
                                   data-bv-notempty-message="联系电话不能为空"   
                                   data-bv-stringlength-max="11" data-bv-stringlength-message="字数不能超过11"
                                   placeholder="请输入联系电话"/>
						</div>
					</div>
					 <div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>库存地点：
						</label>
						<div class="col-md-10 rowGroup">
                            <input type="text" class="form-control" id="CONTRACTNO" name="materielFormBean.deliveryInfoBean.contractNo" readonly="readonly"/>
						</div>
					 </div>
				<div style="border:1px solid #ddd">
					 <div style="margin-top: 10px;">
					 	<label class="col-md-12 control-label"> 
                              <span class="text-danger"></span>上传物资到货附件(包括物资的合格证明、出厂证明等材料信息和物资本身):
						</label>
					</div>
					
						<div class="modal-body">
							<div id='transportUpload' class="uploader">
					  	<div class="uploader-message text-center">
						    <div class="content"></div>
						    <button type="button" class="close"><i class="icon icon-times"></i></button>
					  	</div>
					  <div class="uploader-files file-list file-list-lg" data-drag-placeholder="请拖拽文件到此处"></div>
					  <div class="uploader-actions">
							<div class="uploader-status pull-right text-muted"></div>
							<button type="button" class="btn btn-link uploader-btn-browse"><i class="icon icon-plus"></i> 选择文件</button>
							<button type="button" class="btn btn-link uploader-btn-start"><i class="icon icon-cloud-upload"></i> 开始导入</button>
					  </div>
					</div>
						</div>
				</div>
				
					<div class="btn-toolbar" style="margin-top: 20px;margin-left: 0px;margin-bottom: 10px;">
						<div style="float:left">
							<h3 style="font-size: 14px;"><i class="icon icon-tags"></i>&nbsp;交货物资明细</h3>
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
							<table  class="table table-bordered">
								<thead>
									<tr>
										<th style="text-align: center;width:4%;background-color: #f9f9f9;">序号</th>
										<th style="text-align: center;width:10%;background-color: #f9f9f9;">物资名称</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">计量单位</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">物料描述</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">合同交货期</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">预计发货日期</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">预计到货日期</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">实际交货日期</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">单位</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">合同数量</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">发货数量</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">交接数量</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">交货地点</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">操作</th>
									</tr>
								</thead>
								<tbody id="data_body">
								</tbody>
							</table>
							<input type="hidden" name="refundFormBean.ids" id="removeIds"/>
						</div>
					</div>
				
			
				<div class="modal-footer " style="height: 40px;">
					<button type="submit" id="transport_save" class="btn btn-primary bottom-btn ">
						<i class="icon icon-download-alt " ></i>保存
					</button>
					<button type="button" class="btn btn-large bottom-btn" data-dismiss="modal">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>
	</div>
</form>
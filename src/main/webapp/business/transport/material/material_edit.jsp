<%@ page language="java" pageEncoding="UTF-8"%>

<form id="transport_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="info_dialog"  >
		<div class="modal-dialog modal-lg" style="width:1100px;">
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
					<div class="labe">
						<i class="icon icon-tags"></i><label>物资运输信息</label>
					</div>
					<table class="table table-bordered table-condensed" id="table" style="margin-top: -5px;">
						<tbody>
						
							<tr>
								<td class="td-label"><span class="tr-span">标题&nbsp;:</span></td>
								<td colspan="8"><span class="td-span" id="SUPPLYPLANTITLE"></span></td>
									
							</tr>
						
							<tr>
								<td class="td-label"><span class="tr-span">合同编号&nbsp;:</span></td>
								<td ><span class="td-span" id="CONTRACTNO"></span></td>
								<td class="td-label-lg"><span class="tr-span">供应商(全称)&nbsp;:</span></td>
								<td><span class="autocut td-span"id="SUPPLIERNAME"></span></td>
								<td class="td-label"><span class="tr-span">到货数量&nbsp;:</span></td>
								<td ><span class="td-span" id="ACTUALDELIVERIE"></span></td>	
								<td class="td-label"><span class="tr-span">物料名称&nbsp;:</span></td>
								<td ><span class="td-span" id="MATERIELNAME"></span></td>	
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">计量单位&nbsp;:</span></td>
								<td ><span class="td-span" id="MEAUNIT" ></span></td>
								<td class="td-label"><span class="tr-span">需求单位&nbsp;:</span></td>
								<td ><span class="td-span" id="DEMANDUNIT"></span></td>
								<td class="td-label"><span class="tr-span">收货人&nbsp;:</span></td>
								<td ><span class="td-span" id="RECEIVECONTACT"></span></td>
								<td class="td-label"><span class="tr-span">收或人电话&nbsp;:</span></td>
								<td ><span class="td-span" id="LINKMODE"></span></td>
							</tr>
							
							<tr>
								<td class="td-label"><span class="tr-span">到货单号&nbsp;:</span></td>
								<td ><span class="td-span" id="ARRPLANNUM"></span></td>
								<td class="td-label"><span class="tr-span">项目单位&nbsp;:</span></td>
								<td ><span class="td-span" id="DEPTNAME"></span></td>
								<td class="td-label"><span class="tr-span">合同交货期&nbsp;:</span></td>
								<td ><span class="td-span" id="CONTRACTDELIVERYDATE"></span></td>
								<td class="td-label"><span class="tr-span">发货日期:</span></td>
								<td  ><span class="td-span"id="APPLYDATE"></span></td>
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">计划交货地&nbsp;:</span></td>
								<td colspan="8"><span class="td-span" id="DELIVERYPLACE"></span></td>
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">备注&nbsp;:</span></td>
								<td colspan="8"><span class="td-span" id="REMARK" ></span></td>
							</tr>
						</tbody>
					</table>
					<div class="labe-lg">
						<i class="icon icon-tags"></i><label>物资交货单</label>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>实际到货数量：
						</label>
						<div class="col-md-4 rowGroup">
                            
                            <input type="number" class="form-control" id="arrivalNumber" name="materielFormBean.transferInfoBean.arrivalNumber"
                           		 required data-bv-group=".rowGroup"  placeholder="请输入到货数量"/>
						</div>
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>到货时间：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date'  style="float: left;margin-top:0px">
								<label class="input" style="display:inline">
		                            <input type="text" class="form-control laydate" id="receiveConfirmTime" 
		                                   name="materielFormBean.transferInfoBean.receiveConfirmTime" style="width: 303px; height: 32px;"
		                                   data-bv-group=".rowGroup" readonly="readonly"
		                                   required	
		                                   data-bv-notempty-message="到货时间不能为空"   
		                                   placeholder="请选择到货时间"/>
		                            <span class="input-group-addon" style=" width: 39px; height: 32px;">
		                            	<i class="icon icon-calendar"></i>
		                            </span>
	                            </label>
                            </a>
						</div>
					 </div>
					 <div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span>实际到货地址：
						</label>
						<div class="col-md-10 rowGroup">
                            
                            <input type="text" class="form-control" id="arrivalAddress" name="materielFormBean.transferInfoBean.arrivalAddress" />
						</div>
					 </div>
					 <div class="form-group">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">&nbsp;&nbsp;</span>到货备注：
						</label>
						<div class="col-md-10 rowGroup">
                            
                            <input type="text" class="form-control" id="remark" name="materielFormBean.transferInfoBean.remark" />
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
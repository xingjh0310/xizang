<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="info_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog">
		<div class="modal-dialog modal-lg" style="width:1200px">
			<div class="modal-content">
				
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">取消</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;margin-left: -5px;"><i class="icon icon-home"></i>退料退库</h4>
				</div>
					<div class="modal-body">
					<div style="display:none;">
						id<input type="text" class="form-control" id="id" name="refundFormBean.refund.id">
						billCode<input type="text" class="form-control" id="billCode" name="refundFormBean.refund.billCode">
						state<input type="text" class="form-control" id="state" name="refundFormBean.refund.state">
					</div>
                    
					 <div class="form-group" style="margin-top: -10px;">
					 
						<label class="col-md-1 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 原出库单：
						</label>
						<div class="col-md-3 rowGroup">
							<input type="text" class="form-control" id="libraryNum" 
                                   name="refundFormBean.refund.libraryNum" 
                                   value="<s:property value='refundFormBean.refund.libraryNum' />" 
                                   data-bv-group=".rowGroup" 
                                   maxlength="30"
                                   required   
                                   data-bv-notempty-message="邮箱不能为空"   
                                   data-bv-stringlength-max="30" data-bv-stringlength-message="字数不能超过30"
                                placeholder="请输入原出库单号">
						</div>
						
						<label class="col-md-1 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 所属工程：
						</label>
						<div class="col-md-3">
							<input type="text" class="form-control" id="engineer" name="refundFormBean.refund.billTitle" readonly="readonly" >
							<input type="hidden" class="form-control" id="engCode"  name="refundFormBean.refund.engineerCode" readonly="readonly" >
						</div>
						
						<label class="col-md-1 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 日期：
						</label>
						<div class="col-md-3">
							<input type="text" class="form-control" id="singleDate_refund" name="refundFormBean.refund.singleDate" readonly="readonly" >
						</div>
						
                    </div>
					
					<div class="form-group" style="margin-top: -10px;">
						
						<label class="col-md-1 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 供应商：
						</label>
						<div class="col-md-3 rowGroup">
							<select id="supplierCode" name="refundFormBean.refund.supplierCode" class="form-control" required data-bv-group=".rowGroup" >
								<option value="">请选择供应商</option>
							</select>
						</div>
						<label class="col-md-1 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 制单人：
						</label>
						<div class="col-md-3 rowGroup">
							<input type="text" class="form-control" id="singleStaff_refund" 
                                   name="refundFormBean.refund.singleStaff" readonly="readonly" >
						</div>
						
					</div>
					
                  <div class="form-group" style="margin-top: -10px;">
                	    <label class="col-md-1 control-label" style="margin: 18px auto;"><span class="text-danger"> &nbsp;</span>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	    <div class="col-md-11">
                	          <textarea id="remark" name="refundFormBean.refund.remark" maxlength="300" rows='3' class='form-control' placeholder="请输入备注信息，字数限制500！"><s:property value='refundFormBean.refund.remark' /></textarea>
                	    </div>
                  </div>  
                  <!--  <div class="form-group" style="margin-top: -10px;">
                   		<label class="col-md-1 control-label"> 
                              <span class="text-danger">&nbsp;&nbsp;</span>附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件：
						</label>
                   		<div class="col-md-11">
                	           <div class="modal-body">
									<div id='transportUpload' class="uploader">
				  						<div class="uploader-message text-center">
					    					<div class="content"></div>
					    						<button type="button" class="close">×</button>
				  						</div>
			  							<div class="uploader-files file-list file-list-grid" data-drag-placeholder="请拖拽文件到此处"></div>
				 							<div class="uploader-actions">
												<div class="uploader-status pull-right text-muted"></div>
												<button type="button" class="btn btn-link uploader-btn-browse"><i class="icon icon-plus"></i> 选择文件</button>
												<button type="button" class="btn btn-link uploader-btn-start"><i class="icon icon-cloud-upload"></i> 开始导入</button>
				 	 						</div>
										</div>
								</div>
                	    </div>
                    </div>   -->
					<div class="btn-toolbar" style="margin-top: 20px;margin-left: 0px;margin-bottom: 10px;">
						<div style="float:left">
							<h3 style="font-size: 14px;"><i class="icon icon-tags"></i>&nbsp;物资信息</h3>
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
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">物料名称</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">计量单位</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">物料描述</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">原领用数量</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">退回数量</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">鉴定情况</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">资料完备情况</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">拟使用去向</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">拟使用时间</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">备注</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">操作</th>
									</tr>
								</thead>
								<tbody id="data_body">
								</tbody>
							</table>
							<input type="hidden" name="refundFormBean.ids" id="removeIds"/>
						</div>
					</div>
					
				</div>
				<div class="modal-footer foter" >
					<button type="submit" id="refund_save" class="btn btn-primary bottom-btn" >
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




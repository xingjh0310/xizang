<%@ page language="java" pageEncoding="UTF-8"%>

<form id="" name="" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="info_list">
		<div class="modal-dialog modal-lg" style="width:680px">
			<div class="modal-content">
			
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"><i class="icon icon-home"></i>&nbsp;查看需求清单</h4>
				</div>
				
				<div class="modal-body">
					<table class="table table-bordered info_table_css">
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">供&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;应&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="SUPPLY_FULL_NAME_distributor"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">供应商建成：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="SUPPLY_BUILT_distributor"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">工商登记证号：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="REGISTRATION_NO_distributor"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">法 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="LEGAL_PERSON_distributor"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">联&nbsp;&nbsp;系&nbsp;&nbsp;方&nbsp;&nbsp;式：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="LINK_PHONE_distributor"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">传 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="FAX_distributor"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">地 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</label>
							</td>
							<td colspan="3">
								<lable style="margin-left: 15px;" id="ADDRESS_distributor"></lable>
							</td>
						</tr>
						<tr>
                 	 		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                	    		<label style="margin:0">备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	 		</td>
                	 		<td colspan="3">
                	 			<lable  style="margin-left: 15px;" id="REMARK_distributor"></lable>
                	 		</td>
                  		</tr> 
					</table>
				</div>
				
				<div class="modal-footer" style="height: 40px;">
					<button type="button" class="btn" data-dismiss="modal" style="margin-top: -14px;margin-right:-5px">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			
			</div>
		</div>
	</div>
	
</form>
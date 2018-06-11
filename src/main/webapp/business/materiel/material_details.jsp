<%@ page language="java" pageEncoding="UTF-8"%>

<form id="" name="" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="info_list">
		<div class="modal-dialog modal-lg" style="width:680px">
			<div class="modal-content">
			
				<div class="modal-header" style="background-color: #FCFCFC;height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;"><i class="icon icon-home">&nbsp;物料详细</i></h4>
				</div>
				
				<div class="modal-body">
					<table class="table table-bordered">
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">物料名称：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIELNAME_list"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">物料规格：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIALNORMS_list"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">计量单位：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="UNIT_list"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">国标物料：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="STATE_list"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:110px;padding-left:0px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">物料单价：</label>
							</td>
							<td colspan="3">
								<lable style="margin-left: 15px;" id="PRICE_list"></lable>
							</td>
							
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">物料描述：</label>
							</td>
							<td colspan="3">
								<lable style="margin-left: 15px;" id="MATERIELDESC_list"></lable>
							</td>
							
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">计量描述：</label>
							</td>
							<td colspan="3">
								<lable style="margin-left: 15px;" id="UNITDESC_list"></lable>
							</td>
							
						</tr>
						
						<tr>
                 	 		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                	    		<label style="margin:0">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	 		</td>
                	 		<td colspan="3">
                	 			<lable  style="margin-left: 15px;" id="REMARK_list"></lable>
                	 		</td>
                  		</tr> 
					</table>
				</div>
				
				<div class="modal-footer" style="height: 40px;background-color: #FCFCFC;">
					<button type="button" class="btn btn-large btn-primary" data-dismiss="modal" style="margin-top: -14px;margin-right:-12px">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			
			</div>
		</div>
	</div>
	
</form>
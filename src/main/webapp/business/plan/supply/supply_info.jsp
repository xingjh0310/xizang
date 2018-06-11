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
					<button type="button" class="btn btn-link close modal_header_top" data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title modal_header_top"><i class="icon icon-home"></i>&nbsp;查看供货计划</h4>
				</div>
				
				<div class="modal-body">
					<table class="table table-bordered info_table_css">
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">供货计划标题：</label>
							</td>
							<td colspan="3">
								<lable style="margin-left: 15px;" id="supply_plan_title_supply"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">合同标题：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="CONTTITLE_supply"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">合同编号：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="CONTRACTNO_supply"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">合同数量：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="CONTRACTNUM_supply"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">供&nbsp;&nbsp;应&nbsp;商：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="SUPPLYFULLNAME_supply"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">物料名称：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIELNAME_supply"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">物料规格：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIALNORMS_supply"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">计量单位：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MEAUNIT_supply"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">计划交货数：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PLANDELIVERIE_supply"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">实际交货数：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="ACTUALDELIVERIE_supply"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">计划状态：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PLAY_STATE_supply"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">工程名称：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="ENGINEERNAME_supply"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">项目单位：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="DEPTNAME_supply"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">申请日期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="APPLYDATE_supply"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">合同交货期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="CONTRACTDELIVERYDATE_supply"></lable>
							</td>
						</tr>
						
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">确认交货期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="CONFIRMDELIVERYDATE_supply"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">中标日期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="BIDDINGDATE_supply"></lable>
							</td>
						</tr>
						
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">供&nbsp;&nbsp;应&nbsp;&nbsp;商&nbsp;&nbsp;&nbsp; <br>建议交货期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="SUPPLYDELIVERYDATE_supply"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">需求部门 &nbsp;&nbsp;&nbsp;<br>建议交货期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="DEPTDELIVERYDATE_supply"></lable>
							</td>
						</tr>
						
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">生产图纸&nbsp;&nbsp;&nbsp;<br>确认时间：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="DRAWECONFIRMTIME_supply"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">技术签订&nbsp;&nbsp;&nbsp;<br>确认时间：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="SIGNTIME_supply"></lable>
							</td>
						</tr>
						
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">交货联系人：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="DELIVERYCONTACT_supply"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">联系方式：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="LINKMODE_supply"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">交货地点：</label>
							</td>
							<td colspan="3">
								<lable style="margin-left: 15px;" id="DELIVERYPLACE_supply"></lable>
							</td>
						</tr>
						<tr>
                 	 		<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                	    		<label style="margin:0">物资描述：</label>
                	 		</td>
                	 		<td colspan="3">
                	 			<lable  style="margin-left: 15px;" id="MATERIALDESC_supply"></lable>
                	 		</td>
                  		</tr> 
						
						<tr>
                 	 		<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                	    		<label style="margin:0">计划描述：</label>
                	 		</td>
                	 		<td colspan="3">
                	 			<lable  style="margin-left: 15px;" id="SUPPLYPLANDESC_supply"></lable>
                	 		</td>
                  		</tr> 
						
						<tr>
                 	 		<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                	    		<label style="margin:0">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	 		</td>
                	 		<td colspan="3">
                	 			<lable  style="margin-left: 15px;" id="REMARK_supply"></lable>
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
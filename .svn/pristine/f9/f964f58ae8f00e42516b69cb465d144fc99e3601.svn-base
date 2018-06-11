<%@ page language="java" pageEncoding="UTF-8"%>

<form id="query_form" name="info_form"  class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="deliverGoods_details">
		<div class="modal-dialog " style="width: 850px">
			<div class="modal-content">
				<div class="modal-header" style=" height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;">
						<i class="icon icon-home"></i>&nbsp;&nbsp;物资详情
					</h4>
				</div>
				<div class="modal-body-check">
					<table class="table table-bordered info_table_css">
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">合同编号：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="CONTRACTNO_supply"></label>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">合同标题：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="CONTTITLE_supply"></label>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">合同数量：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="CONTRACTNUM_supply"></label>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">供&nbsp;&nbsp;应&nbsp;商：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="SUPPLYFULLNAME_supply"></label>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">物料名称：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="MATERIELNAME_supply"></label>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">物料规格：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="MATERIALNORMS_supply"></label>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">计量单位：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="MEAUNIT_supply"></label>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">计划交货数：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="PLANDELIVERIE_supply"></label>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">实际交货数：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="ACTUALDELIVERIE_supply"></label>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">计划状态：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="PLAY_STATE_supply"></label>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">工程名称：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="ENGINEERNAME_supply"></label>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">项目单位：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="DEPTNAME_supply"></label>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">申请日期：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="APPLYDATE_supply"></label>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">合同交货期：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="CONTRACTDELIVERYDATE_supply"></label>
							</td>
						</tr>
						
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">确认交货期：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="CONFIRMDELIVERYDATE_supply"></label>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">中标日期：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="BIDDINGDATE_supply"></label>
							</td>
						</tr>
						
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">供&nbsp;&nbsp;应&nbsp;&nbsp;商&nbsp;&nbsp;&nbsp; <br>建议交货期：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="SUPPLYDELIVERYDATE_supply"></label>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">需求部门 &nbsp;&nbsp;&nbsp;<br>建议交货期：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="DEPTDELIVERYDATE_supply"></label>
							</td>
						</tr>
						
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">生产图纸&nbsp;&nbsp;&nbsp;<br>确认时间：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="DRAWECONFIRMTIME_supply"></label>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">技术签订&nbsp;&nbsp;&nbsp;<br>确认时间：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="SIGNTIME_supply"></label>
							</td>
						</tr>
						
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">交货联系人：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="DELIVERYCONTACT_supply"></label>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">联系方式：</label>
							</td>
							<td style="width:200px;">
								<label style="margin-left: 15px;" id="LINKMODE_supply"></label>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">交货地点：</label>
							</td>
							<td colspan="3">
								<label style="margin-left: 15px;" id="DELIVERYPLACE_supply"></label>
							</td>
						</tr>
						<tr>
                 	 		<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                	    		<label style="margin:0">物资描述：</label>
                	 		</td>
                	 		<td colspan="3">
                	 			<label  style="margin-left: 15px;" id="MATERIALDESC_supply"></label>
                	 		</td>
                  		</tr> 
						
						<tr>
                 	 		<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                	    		<label style="margin:0">计划描述：</label>
                	 		</td>
                	 		<td colspan="3">
                	 			<label  style="margin-left: 15px;" id="SUPPLYPLANDESC_supply"></label>
                	 		</td>
                  		</tr> 
						
						<tr>
                 	 		<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                	    		<label style="margin:0">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	 		</td>
                	 		<td colspan="3">
                	 			<label  style="margin-left: 15px;" id="REMARK_supply"></label>
                	 		</td>
                  		</tr> 
					</table>
				</div>
				
				<div class="modal-footer foter" >
					<button type="button" class="btn btn-large bottom-btn"data-dismiss="modal">
						<i class="icon icon-times "></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>


</form>




<%@ page language="java" pageEncoding="UTF-8"%>

<form id="query_form" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="material_details">
		<div class="modal-dialog " style="width: 1100px">
			<div class="modal-content">
				<div class="modal-header"style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;">
						<i class="icon icon-home"></i>&nbsp;&nbsp;物资详情
					</h4>
				</div>
				<div class="modal-body-check" style="padding:10px;">
				<div style="display:none">
						
				</div>
					<div class="labe">
						<i class="icon icon-tags"></i><label>物资运输信息</label>
					</div>
					<table class="table table-bordered table-condensed" id="table" style="margin-top: -5px;">
						<tbody>
						
							<tr>
								<td class="td-label"><span class="tr-span">标题&nbsp;:</span></td>
								<td colspan="8"><span class="td-span" id="SUPPLYPLANTITLE_list"></span></td>
									
							</tr>
						
							<tr>
								<td class="td-label"><span class="tr-span">合同标题&nbsp;:</span></td>
								<td ><span class="td-span" id="CONTRACTNAME_list"></span></td>
								<td class="td-label-lg"><span class="tr-span">供应商(全称)&nbsp;:</span></td>
								<td><span class="autocut td-span"id="SUPPLIERNAME_list"></span></td>
								<td class="td-label"><span class="tr-span">到货数量&nbsp;:</span></td>
								<td ><span class="td-span" id="ACTUALDELIVERIE_list"></span></td>	
								<td class="td-label"><span class="tr-span">物料名称&nbsp;:</span></td>
								<td ><span class="td-span" id="MATERIELNAME_list"></span></td>	
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">计量单位&nbsp;:</span></td>
								<td ><span class="td-span" id="MEAUNIT_list"></span></td>
								<td class="td-label"><span class="tr-span">需求单位&nbsp;:</span></td>
								<td ><span class="td-span" id="DEMANDUNIT_list"></span></td>
								<td class="td-label"><span class="tr-span_list">收货人&nbsp;:</span></td>
								<td ><span class="td-span" id="RECEIVECONTACT_list"></span></td>
								<td class="td-label"><span class="tr-span">收或人电话&nbsp;:</span></td>
								<td ><span class="td-span" id="LINKMODE_list"></span></td>
							</tr>
							
							<tr>
								<td class="td-label"><span class="tr-span">到货单号&nbsp;:</span></td>
								<td ><span class="td-span" id="ARRPLANNUM_list"></span></td>
								<td class="td-label"><span class="tr-span">项目单位&nbsp;:</span></td>
								<td ><span class="td-span" id="DEPTNAME_list"></span></td>
								<td class="td-label"><span class="tr-span">合同交货期&nbsp;:</span></td>
								<td ><span class="td-span" id="CONTRACTDELIVERYDATE_list"></span></td>
								<td class="td-label"><span class="tr-span">发货日期:</span></td>
								<td  ><span class="td-span"id="APPLYDATE_list"></span></td>
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">计划交货地&nbsp;:</span></td>
								<td colspan="8"><span class="td-span" id="DELIVERYPLACE_list"></span></td>
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">备注&nbsp;:</span></td>
								<td colspan="8"><span class="td-span" id="REMARK_list" ></span></td>
							</tr>
						</tbody>
					</table>
					<div class="labe-lg" id="arrive_list">
						<i class="icon icon-tags"></i><label>物资交货单</label>
						<table class="table table-bordered table-condensed" id="table" style="margin-top: -5px;">
							<tr>
									<td class="td-label"><span class="tr-span">到货接收人&nbsp;:</span></td>
								<td ><span class="td-span" id="RECEIVEPARTY_list"></span></td>
									<td class="td-label"><span class="tr-span">到货数量&nbsp;:</span></td>
									<td><span class='autocut td-span' id="ARRIVALNUMBER_list"></span></td>
									<td class="td-label"><span class="tr-span">到货时间&nbsp;:</span></td>
									<td ><span class="td-span" id="RECEIVECONFIRMTIME_list"></span></td>
										
								</tr>
								<tr>
									<td class="td-label"><span class="tr-span">到货地址&nbsp;:</span></td>
									<td colspan="5"><span class="td-span" id="ARRIVALADDRESS_list"></span></td>
									
								</tr>
								<tr>
								<td id="linkTeles" style="text-align: left" colspan="8">
										<span>到货附件:</span>
										<div class="modal-body">
										<div id='arrivelUpload' class="uploader">
					  						<div class="uploader-message text-center">
						    					<div class="content"></div>
						    						<button type="button" class="close">×</button>
					  						</div>
					  							
					 							<div class="uploader-actions">
													<div class="uploader-status pull-right text-muted"></div>
													
					 	 						</div>
										</div>
									</div>
										
								</td>
								
							</tr>
						</table>
						
					</div>
					<div class="labe-lg" id="check_list">
						<i class="icon icon-tags"></i><label>到货验收单</label>
						<table class="table table-bordered table-condensed" id="table" style="margin-top: -5px;">
						<tbody>
							
							<tr>
								<td class="td-label"><span class="tr-span">验收单号&nbsp;:</span></td>
								<td ><span class="td-span" id="ACCEPTANCE_list"></span></td>
								<td class="td-label"><span class="tr-span">验收时间&nbsp;:</span></td>
								<td ><span class="td-span" id="ACCEPTANCETIME_list"></span></td>
									
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">验收单状态&nbsp;:</span></td>
								<td  style="text-align: left" colspan="8">
									<input type="radio" style="color: red" id="checkbox1" value="1" disabled="disabled"> 正常收货
									<input type="radio" id="checkbox2" value="2" disabled="disabled"> 换货处理
									<input type="radio" id="checkbox3" value="3" disabled="disabled"> 取消验收
									<input type="radio" id="checkbox4" value="4" disabled="disabled"> 补发ECP
								</td>
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">备注&nbsp;:</span></td>
								<td colspan="5"><span class="td-span" id="ACCEREMARK_list"></span></td>
								
							</tr>
							<tr>
								<td id="linkTeles" style="text-align: left" colspan="8">
										<span>验收附件:</span>
										<div class="modal-body">
										<div id='listUpload' class="uploader">
					  						<div class="uploader-message text-center">
						    					<div class="content"></div>
						    						<button type="button" class="close">×</button>
					  						</div>
					  							
					 							<div class="uploader-actions">
													<div class="uploader-status pull-right text-muted"></div>
													
					 	 						</div>
										</div>
									</div>
										
								</td>
								
							</tr>
							<tr>
								<td style="text-align: left" colspan="8">
										<h4>五方验收情况</h4>
				
					<div class="container" style="margin-top: 50px;">  
   					 <ul class="time-horizontal">  
        					<li ><b></b><span id="MATERIALCOMPANY_list" ></span>&nbsp;<span id="COMPANYSTAFF_list"> </span>
        						<br><span id="CONFIRMTIME_list"></span>
        						<br><span id="COMPANYSTATE"></span>
        					</li>  
        					<li ><b></b><span id="PROJECTSENDEE_list"></span>&nbsp;<span id="PROJECTSTAFF_list"> </span>
        						<br><span id="PROJECTCONFIRMTIME_list"></span>
        						<br><span id="PROJECTSTATE"></span>
        					</li>  
        					<li ><b></b><span id="SUPPLIERDELIVERER_list"></span>&nbsp;<span id="SUPPLIERSTAFF_list"> </span>
        						<br><span id="SUPPLIERCONFIRMTIME_list"></span>
        						<br><span id="SUPPLIERSTATE"></span>
        					</li>  
        					<li ><b></b><span id="SUPERVISORUNIT_list"></span>&nbsp;<span id="SUPERVISORSTAFF_list"> </span>
        						<br><span id="SUPERVISORCONFIRMTIME_list"></span>
        						<br><span id="SUPERVISORSTATE"></span>
        					</li>  
        					<li ><b></b><span id="CONSTRUCTIONUNIT_list"></span>&nbsp;<span id="CONSTRUCTIONSTAFF_list"> </span>
        						<br><span id="CONSTRUCTIONCONFIRMTIME_list"></span>
        						<br><span id="CONSTRUCTIONSTATE"></span>
        					</li>  
    				</ul> 
					</div> 
					</td>
					</tr>
					</tbody>
					</table>
					</div>
			
			</div>
				<div class="modal-footer " style="height: 40px;">
					<button type="button" class="btn btn-large bottom-btn" data-dismiss="modal">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
				
			</div>
		</div>
	</div>


</form>




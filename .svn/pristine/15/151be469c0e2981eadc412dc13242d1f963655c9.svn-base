<%@ page language="java" pageEncoding="UTF-8"%>

<form id="check_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="material_check">
		<div class="modal-dialog modal-lg" style="width:1100px;">
			<div class="modal-content">
			
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;"><i class="icon icon-check-board">&nbsp;五方验收</i></h4>
				</div>
			
				<div class="modal-body-check" style="padding:10px;">
					<div class="labe">
						<i class="icon icon-tags"></i><label>物资运输信息</label>
					</div>
					<div style="display:none">
						发货ID<input type="text" class="form-control" id="ID_check" name="materielFormBean.ids">
						engineerCode<input type="text" class="form-control" id="ENGINEERCODE_check" name="materielFormBean.checkInfoBean.engineerCode">
						handover <input type =text class="form-control"  id="ACCEPTANCES" name ="materielFormBean.checkInfoBean.acceptance"  />
						物资<input type="text" class="form-control" id="companys0" name="materielFormBean.checkInfoBean.materialCompany"/>
						<input type="text" class="form-control" id="names0" name="materielFormBean.checkInfoBean.companyStaff"/>
						<input type="text" class="form-control" id="dates0" name="materielFormBean.checkInfoBean.confirmTime"/>
						<input type="text" class="form-control" id="states0" name="materielFormBean.checkInfoBean.companyState"/>
						厂家<input type="text" class="form-control" id="companys1" name="materielFormBean.checkInfoBean.projectSendee"/>
						<input type="text" class="form-control" id="names1" name="materielFormBean.checkInfoBean.projectStaff"/>
						<input type="text" class="form-control" id="dates1" name="materielFormBean.checkInfoBean.projectConfirmTime"/>
						<input type="text" class="form-control" id="states1" name="materielFormBean.checkInfoBean.projectState"/>
						施工<input type="text" class="form-control" id="companys2" name="materielFormBean.checkInfoBean.constructionUnit"/>
						<input type="text" class="form-control" id="names2" name="materielFormBean.checkInfoBean.constructionStaff"/>
						<input type="text" class="form-control" id="dates2" name="materielFormBean.checkInfoBean.constructionConfirmTime"/>
						<input type="text" class="form-control" id="states2" name="materielFormBean.checkInfoBean.constructionState"/>
						监理<input type="text" class="form-control" id="companys3" name="materielFormBean.checkInfoBean.supervisorUnit"/>
						<input type="text" class="form-control" id="names3" name="materielFormBean.checkInfoBean.supervisorStaff"/>
						<input type="text" class="form-control" id="dates3" name="materielFormBean.checkInfoBean.supervisorConfirmTime"/>
						<input type="text" class="form-control" id="states3" name="materielFormBean.checkInfoBean.supervisorState"/>
						业主<input type="text" class="form-control" id="companys4" name="materielFormBean.checkInfoBean.supplierDeliverer"/>
						<input type="text" class="form-control" id="names4" name="materielFormBean.checkInfoBean.supplierStaff"/>
						<input type="text" class="form-control" id="dates4" name="materielFormBean.checkInfoBean.supplierConfirmTime"/>
						<input type="text" class="form-control" id="states4" name="materielFormBean.checkInfoBean.supplierState"/>
					</div>
					<table class="table table-bordered table-condensed" id="table" style="margin-top: -5px;">
						<tbody>
							<tr>
								<td class="td-label"><span class="tr-span">标题&nbsp;:</span></td>
								<td colspan="8"><span class="td-span" id="SUPPLYPLANTITLE_check"></span></td>
									
							</tr>
						
							<tr>
								<td class="td-label"><span class="tr-span">合同标题&nbsp;:</span></td>
								<td ><span class="td-span" id="CONTRACTNAME_check"></span></td>
								<td class="td-label-lg"><span class="tr-span">供应商(全称)&nbsp;:</span></td>
								<td><span class="autocut td-span"id="SUPPLIERNAME_check"></span></td>
								<td class="td-label"><span class="tr-span">到货数量&nbsp;:</span></td>
								<td ><span class="td-span" id="ACTUALDELIVERIE_check"></span></td>	
								<td class="td-label"><span class="tr-span">物料名称&nbsp;:</span></td>
								<td ><span class="td-span" id="MATERIELNAME_check"></span></td>	
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">计量单位&nbsp;:</span></td>
								<td ><span class="td-span" id="MEAUNIT_check" ></span></td>
								<td class="td-label"><span class="tr-span">需求单位&nbsp;:</span></td>
								<td ><span class="td-span" id="DEMANDUNIT_check"></span></td>
								<td class="td-label"><span class="tr-span">收货人&nbsp;:</span></td>
								<td ><span class="td-span" id="RECEIVECONTACT_check"></span></td>
								<td class="td-label"><span class="tr-span">收或人电话&nbsp;:</span></td>
								<td ><span class="td-span" id="LINKMODE_check"></span></td>
							</tr>
							
							<tr>
								<td class="td-label"><span class="tr-span">到货单号&nbsp;:</span></td>
								<td ><span class="td-span" id="ARRPLANNUM_check"></span></td>
								<td class="td-label"><span class="tr-span">项目单位&nbsp;:</span></td>
								<td ><span class="td-span" id="DEPTNAME_check"></span></td>
								<td class="td-label"><span class="tr-span">合同交货期&nbsp;:</span></td>
								<td ><span class="td-span" id="CONTRACTDELIVERYDATE_check"></span></td>
								<td class="td-label"><span class="tr-span">发货日期:</span></td>
								<td  ><span class="td-span"id="APPLYDATE_check"></span></td>
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">计划交货地&nbsp;:</span></td>
								<td colspan="8"><span class="td-span" id="DELIVERYPLACE_check"></span></td>
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">备注&nbsp;:</span></td>
								<td colspan="8"><span class="td-span" id="REMARK_check" ></span></td>
							</tr>
							
						</tbody>
					</table>
					
					<div class="labe">
						<i class="icon icon-tags"></i><label>到货验收单</label>
					</div>
					
					<table class="table table-bordered table-condensed" id="table" style="margin-top: -5px;    margin-bottom: -10px;">
						<tbody>
							
							<tr>
								<td class="td-label"><span class="tr-span">到货接收人&nbsp;:</span></td>
								<td ><span class="td-span" id="RECEIVEPARTY_check"></span></td>
								<td class="td-label"><span class="tr-span">到货数量&nbsp;:</span></td>
								<td><span class='autocut td-span' id="ARRIVALNUMBER_check"></span></td>
								<td class="td-label"><span class="tr-span">到货时间&nbsp;:</span></td>
								<td ><span class="td-span" id="RECEIVECONFIRMTIME_check"></span></td>
									
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">到货地址&nbsp;:</span></td>
								<td colspan="5"><span class="td-span" id="ARRIVALADDRESS_check"></span></td>
								
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">验收单状态&nbsp;:</span></td>
								<td id="linkTeles" style="text-align: left" colspan="8">
									<input type="radio" name="materielFormBean.checkInfoBean.acceptanceState" value="1"  checked="checked"> 正常收货
									<input type="radio" name="materielFormBean.checkInfoBean.acceptanceState" value="2" > 换货处理
									<input type="radio" name="materielFormBean.checkInfoBean.acceptanceState" value="3" > 取消验收
									<input type="radio" name="materielFormBean.checkInfoBean.acceptanceState" value="4" > 补发ECP
								</td>
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">备注&nbsp;:</span></td>
								<td colspan="5"><input type="text" id="remark_check" name="materielFormBean.checkInfoBean.remark" class="form-control"  /></td>
								
							</tr>
							<tr>
								<td id="linkTeles" style="text-align: left" colspan="8">
										<span>上传物资验收现场照片材料:</span>
										<div class="modal-body">
										<div id='checkUpload' class="uploader">
					  						<div class="uploader-message text-center">
						    					<div class="content"></div>
						    						<button type="button" class="close">×</button>
					  						</div>
					  							<div class="uploader-files file-list file-list-lg" data-drag-placeholder="请拖拽文件到此处"></div>
					 							<div class="uploader-actions">
													<div class="uploader-status pull-right text-muted"></div>
													<button type="button" class="btn btn-link uploader-btn-browse"><i class="icon icon-plus"></i> 选择文件</button>
													<button type="button" class="btn btn-link uploader-btn-start"><i class="icon icon-cloud-upload"></i> 开始导入</button>
					 	 						</div>
										</div>
									</div>
										
								</td>
								
							</tr>
							<tr>
								<td style="text-align: left" colspan="8">
										<h4>五方验收情况</h4>
				<div class="btn-group pull-right visible-lg visible-md visible-sm" style="margin-top: -30px;">
						<button class="btn btn-info" id="btn_check" type="button">
							   <div class="visible-md visible-lg"><i class="icon icon-plus"></i>&nbsp;添加验收信息</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-plus"></i></div>
						</button>
				</div>
				
				
					<div class="container" style="margin-top: 50px;">  
   					 <ul class="time-horizontal">  
        					<li style="color: green"><b></b><span id="company0" ></span>&nbsp;<span id="name0"> </span>
        						<br><span id="date0"></span>
        						<br><span id="state0"></span>
        					</li>  
        					<li style="color: green"><b></b><span id="company1"></span>&nbsp;<span id="name1"> </span>
        						<br><span id="date1"></span>
        						<br><span id="state1"></span>
        					</li>  
        					<li style="color: green"><b></b><span id="company2"></span>&nbsp;<span id="name2"> </span>
        						<br><span id="date2"></span>
        						<br><span id="state2"></span>
        					</li>  
        					<li style="color: green"><b></b><span id="company3"></span>&nbsp;<span id="name3"> </span>
        						<br><span id="date3"></span>
        						<br><span id="state3"></span>
        					</li>  
        					<li style="color: green"><b></b><span id="company4"></span>&nbsp;<span id="name4"> </span>
        						<br><span id="date4"></span>
        						<br><span id="state4"></span>
        					</li>  
    				</ul> 
					</div> 
							</td>
								
							</tr>
						</tbody>
					</table>
    			
				</div>
			
				<div class="modal-footer foter" >
					<button type="submit" id="check_save" class="btn btn-primary bottom-btn" >
						<i class="icon icon-download-alt" ></i> 保存
					</button>

					<button type="button" class="btn btn-large bottom-btn" data-dismiss="modal">
						<i class="icon icon-times" ></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>
	
</form>
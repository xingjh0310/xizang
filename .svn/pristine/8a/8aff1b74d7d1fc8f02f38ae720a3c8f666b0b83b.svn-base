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
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;"><i class="icon icon-home">&nbsp;退库退料审核</i></h4>
				</div>
				<div style="display:none;">
						id<input type="text" class="form-control" id="id_mail" name="mailFormBean.mail.id">
				</div>
				<div class="modal-body">
					<table class="table table-bordered">
						<tr>
							<td class="td-label"><span class="tr-span">单据标题：</span></td>
							<td ><span class="td-span" id="STAFFNAME_list"></span></td>
							<td class="td-label"><span class="tr-span">所属工程：</span></td>
							<td ><span class="td-span" id="POSITION_list"></span></td>
							<td class="td-label"><span class="tr-span">日期：</span></td>
							<td ><span class="td-span" id="LINKPHONE_list"></span></td>
						</tr>
						<tr>
							<td class="td-label"><span class="tr-span">原出库单：</span></td>
							<td ><span  class="td-span" id="FIXTELE_list"></span></td>
							<td class="td-label"><span class="tr-span">供应商：</span></td>
							<td ><span class="td-span" id="LINKPHONE_list"></span></td>
							<td class="td-label"><span class="tr-span">制单人：</span></td>
							<td ><span  class="td-span" id="FIXTELE_list"></span></td>
						</tr>
						
						<tr>
                 	 		<td class="td-label"><span class="tr-span">备注：</span></td>
                	 		<td colspan="5"><span class="td-span" id="REMARK_list"></span></td>
                  		</tr>
                  		<tr height="80px;">
								<td  class="td-label"><span class="tr-span">附件：</span></td>
								<td colspan="5">
									<a style="margin-left: 50px;" href="webapp/dow/app-debug.apk">合同附件</a>
								</td>
						</tr> 
					</table>
					<div class="btn-toolbar" style="margin-top: 20px;margin-left: 0px;margin-bottom: 10px;">
						<div style="float:left">
							<h3 style="font-size: 14px;"><i class="icon icon-tags"></i>&nbsp;物资信息</h3>
						</div>
						
					</div>
							<table id="" class="table table-bordered" >
								<thead>
									<tr style="text-align: center">
										<th data-halign="center" data-align="center" data-sortable="false" data-width="60" data-formatter="FMT_Num">  序号</th>
										<th data-halign="center" data-align="center" data-sortable="false"  data-field="linkManName" data-width="100" >物料名称</th> 
										<th data-halign="center" data-align="center" data-sortable="false"  data-field="linkManName" data-width="100" >计量单位</th> 
										<th data-halign="center" data-align="center" data-sortable="false"  data-field="linkManName" data-width="100" >物料描述</th> 
										<th data-halign="center" data-align="center" data-sortable="false"  data-field="linkManName" data-width="100" >原领用数量</th> 
										<th data-halign="center" data-align="center" data-sortable="false"  data-field="linkManName" data-width="100" >退回数量</th> 
										<th data-halign="center" data-align="center" data-sortable="false"  data-field="linkManName" data-width="100" >鉴定情况</th> 
										<th data-halign="center" data-align="center" data-sortable="false"  data-field="linkManName" data-width="100" >资料完备情况</th> 
										<th data-halign="center" data-align="center" data-sortable="false"  data-field="linkManName" data-width="100" >拟使用去向</th> 
										<th data-halign="center" data-align="center" data-sortable="false"  data-field="linkManName" data-width="100" >拟使用时间</th> 
										<th data-halign="center" data-align="center" data-sortable="false"  data-field="linkManName" data-width="100" >备注</th> 
									</tr>
								</thead>
							</table>
							<div class="model_title2_margin_top"><h4> <i class="icon icon-tags"></i>&nbsp;审核意见</h4></div>
					<hr class="model_title_margin_top">
					<div class="form-group" style="margin-bottom: 0px;">
                	    <div class="col-md-12">
                	    	<textarea id="AUDITOPINION" name="fArrivalFormBean.mArrival.auditOpinion" maxlength="300" rows='3' class='form-control' placeholder="请输入审核意见，字数限制500！"></textarea>
                	    </div>
                    </div>
							
				</div>
				
				<div class="modal-footer foter" >
					<button type="button" id="btn_audit_ok" class="btn btn-primary " style="margin-top: -14px" onclick="one_audit(true)">
						<i class="icon icon-check-circle"></i> 审核通过
					</button>

					<button type="button" id="btn_audit_no" class="btn btn-danger" style="margin-top: -14px;" onclick="one_audit(false)">
						<i class="icon icon icon-times"></i> 审核拒绝
					</button>

					<button type="button" class="btn btn-large bottom-btn" data-dismiss="modal" style="margin-right: -5px;">
						<i class="icon icon-times ion-left"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>

</form>




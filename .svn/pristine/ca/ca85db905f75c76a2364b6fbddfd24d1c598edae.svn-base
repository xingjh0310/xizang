<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="info_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_label">
		<div class="modal-dialog modal-lg" style="width:1200px">
			<div class="modal-content">
				
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">取消</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;margin-left: -5px;"><i class="icon icon-home"></i>退料退库</h4>
				</div>

					<div class="modal-body" id="print">
					<div style="display:none;">
						id<input type="text" class="form-control" id="id_mail" name="mailFormBean.mail.id">
						treeCode<input type="text" class="form-control" id="carCode_mail" name="mailFormBean.mail.treenmSysDept">
						nm<input type="text" class="form-control" id="nm_mail" name="mailFormBean.mail.nm">
					</div>
                    <div>
                    	<table class="table table-bordered" >
                    			<tr>
                    				<td colspan="6" style="text-align: center;font-size: 30px;">公司工程结余物资退料清单</td>
                    			</tr>
                    			<tr height="40px;">
                    				<td class="td-label-max">退库申请部门（公章）：</td>
                    				<td class="tdw"><span class="td-span" id=""></span></td>
                    				<td class="td-label-lg">项目（工单）名称：</td>
                    				<td class="tdw"><span class="td-span" id=""></span></td>
                    				<td class="td-label-lg">项目（工单）编号：</td>
                    				<td class="tdw"><span class="td-span" id=""></span></td>
                    			</tr>
                    			<tr height="40px;">
                    				<td class="td-label-max">原出库单：</td>
                    				<td colspan="3"><span class="td-span" id="LIBRARYNUM_out"></span></td>
                    				<td class="td-label">编号：</td>
                    				<td class="tdw"><span class="td-span" id="BILLCODE_out"></span></td>
                    			</tr>
                    			<tr height="40px;">
                    				<td class="td-label-max">结余物资说明：</td>
                    				<td colspan="5"><span class="td-span" id="EXPLAIN_out"></span></td>
                    				
                    			</tr>
                    				
                    	</table>
                    	<table class="table table-bordered">
								<thead>
									<tr>
										<th style="text-align: center;width:4%;background-color: #f9f9f9;">序号</th>
										<th style="text-align: center;width:8%;background-color: #f9f9f9;">物料名称</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">计量单位</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">物料描述</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">原领用数量</th>
										<th style="text-align: center;width:7%;background-color: #f9f9f9;">退回数量</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">鉴定情况</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">资料完备情况</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">拟使用去向</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">拟使用时间</th>
										<th style="text-align: center;width:9%;background-color: #f9f9f9;">备注</th>
									</tr>
								</thead>
								<tbody  id="list_label"></tbody>
							</table>
                    </div>
						<table class="table table-bordered">
									<tr >
										<td class="td-label-max" >说明：</td>
										<td colspan="5" ></td>
									</tr>
									<tr >
										<td class="td-label-max" >制单人：</td>
										<td ><span class="td-span" id="SINGLESTAFF_out"></span></td>
										<td class="td-label-max" >退料部门审核人：</td>
										<td ><span class="td-span" id="AUDITSTAFF_out"></span></td>
									</tr>
									<tr >
										<td class="td-label-max" >日期：</td>
										<td ><span class="td-span" id="SINGLEDATE_out"></span></td>
										<td class="td-label-max" >页面：</td>
										<td ><span class="td-span" id="LIBRARYNUM_list"></span></td>
									</tr>
						</table>
				</div>
				<div class="modal-footer foter" >
					<button type="button" id="print_out" class="btn btn-primary bottom-btn" >
						<i class="icon icon-download-alt ion-left"></i> 打印
					</button>

					<button type="button" class="btn btn-large bottom-btn" data-dismiss="modal">
						<i class="icon icon-times ion-left"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>

</form>




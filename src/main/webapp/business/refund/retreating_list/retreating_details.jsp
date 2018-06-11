<%@ page language="java" pageEncoding="UTF-8"%>

<form id="" name="" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="info_list">
		<div class="modal-dialog modal-lg" style="width:1200px">
			<div class="modal-content">
			
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;"><i class="icon icon-home">&nbsp;退库退料详细</i></h4>
				</div>
				
				<div class="modal-body">
					<table class="table table-bordered">
						<tr>
							<td class="td-label"><span class="tr-span">单据标题：</span></td>
							<td ><span class="td-span" id="BILLTITLE_list"></span></td>
							<td class="td-label"><span class="tr-span">所属工程：</span></td>
							<td ><span class="td-span" id="ENGINEER_list"></span></td>
							<td class="td-label"><span class="tr-span">日期：</span></td>
							<td ><span class="td-span" id="SINGLEDATE_list"></span></td>
						</tr>
						<tr>
							<td class="td-label"><span class="tr-span">原出库单：</span></td>
							<td ><span class="td-span" id="LIBRARYNUM_list"></span></td>
							<td class="td-label"><span class="tr-span">供应商：</span></td>
							<td ><span class="td-span" id="SUPPLIERCODE_list"></span></td>
							<td class="td-label"><span class="tr-span">制单人：</span></td>
							<td ><span  class="td-span" id="SINGLESTAFF_list"></span></td>
						</tr>
						
						<tr>
                 	 		<td class="td-label"><span class="tr-span">备注：</span></td>
                	 		<td colspan="5"><span class="td-span" id="REMARK_list"></span></td>
                  		</tr>
					</table>
					<div class="btn-toolbar" style="margin-top: 20px;margin-left: 0px;margin-bottom: 10px;">
						<div style="float:left">
							<h3 style="font-size: 14px;"><i class="icon icon-tags"></i>&nbsp;物资信息</h3>
						</div>
						
					</div>
							<table class="table table-bordered" style="text-align: center" >
								<thead>
									<tr style="text-align: center">
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
									</tr>
								</thead>
								<tbody  id="list_table"></tbody>
							</table>
				</div>
				
				<div class="modal-footer foter" >
					<button type="button" class="btn btn-large bottom-btn" data-dismiss="modal">
						<i class="icon icon-times "></i> 取消
					</button>
				</div>
			
			</div>
		</div>
	</div>
	
</form>
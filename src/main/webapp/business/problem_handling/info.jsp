<%@ page language="java" pageEncoding="UTF-8"%>
<style>
#photo_submit_handing{
	display:flex;
	flex-wrap:wrap;
}
.img_css{
	width:80px;
	height:80px;
	margin:8px;
}
#photo_submit_handing{
	display:flex;
	flex-wrap:wrap;
}
</style>

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
					<h4 class="modal-title modal_header_top"><i class="icon icon-home"></i>&nbsp;物资问题处理结果</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered" style="margin-bottom:-1px">
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</label>
							</td>
							<td colspan="3">
                	 			<lable  style="margin-left: 15px;" id="TITLE_handing"></lable>
                	 		</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">物资类型：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIELNAME_handing"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">问题类型：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="SUBDICTNAME_handing"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">上报单位：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="DEPTNAME_handing"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">上&nbsp;&nbsp;报&nbsp;人：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="SUBSTAFFNAME_handing"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">上报时间：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="REPORTTIME_handing"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">处理期限：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PROCESSLIMIT_handing"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">问题描述：</label>
							</td>
							<td colspan="3" style="padding:0">
								<lable style="margin-left: 15px;" id="PROBLEMDESC_handing"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">问题照片：</label>
							</td>
							<td colspan="3" style="padding:0">
								<div id="photo_submit_handing"></div>
							</td>
						</tr>
						</table>
						<table id="handing_table" class="table table-bordered info_table_css">
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">处&nbsp;&nbsp;理&nbsp;人：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="STAFFNAME_handing"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">处理时间：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PROCESSTIME_handing"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">处理方式：</label>
							</td>
							<td colspan="3" style="padding:0">
								<lable style="margin-left: 15px;" id="DICTNAME_handing"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">处理结果：</label>
							</td>
							<td colspan="3" style="padding:0">
								<lable style="margin-left: 15px;" id="HANDLERESULT_handing"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            	<label style="margin:0">图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片：</label>
							</td>
							<td colspan="3" style="padding:0">
								<div id="photo_handle_handing"></div>
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
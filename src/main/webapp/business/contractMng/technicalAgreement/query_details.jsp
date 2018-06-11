<%@ page language="java" pageEncoding="UTF-8"%>

<form id="query_form" name="info_form" class="form-inline" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="query_info_show">
		<div class="modal-dialog "style="width:800px">
			<div class="modal-content">

				<div class="modal-header" style="background-color: #FCFCFC;height: 40px">
					<button type="button"  class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;"><i class="icon icon-home"></i>&nbsp;&nbsp;技术协议签订详情</h4>
				</div>

				<div class="modal-body">
					<div style="display: none;">
						id<input type="text" class="form-control" id="id" name="formBean.registrationInfoBean.id">
                        create_time<input type="text" class="form-control" id="create_time" name="formBean.registrationInfoBean.create_time">
					</div>
					<table class="table table-bordered" style="margin-bottom: 0px;">
                    	<tr>
                    		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
	                    		<lable>项目名称：</lable>
                    		</td>
                    		<td style="width:300px;text-align:left" >
	                    		藏中联网
                    		</td>
                    		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
	                    		<lable>物料编码：</lable>
                    		</td>
                    		<td style="text-align:left">
	                    		WL001
                    		</td>
                    	</tr>
                    	<tr>
                    		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
	                    		<lable>物料名称：</lable>
                    		</td>
                    		<td style="width:300px;text-align:left" >
	                    		铁塔
                    		</td>
                    		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
	                    		<lable>数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量：</lable>
                    		</td>
                    		<td style="text-align:left">
	                    		50
                    		</td>
                    	</tr>
                    	<tr>
                    		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
	                    		<lable>计量单位：</lable>
                    		</td>
                    		<td style="text-align:left">
	                    		基/吨
                    		</td>
                    		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
	                    		<lable>需求日期：</lable>
                    		</td>
                    		<td style="text-align:left">
	                    		2017-12-12
                    		</td>
                    	</tr>
                    	<tr>
                    		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
	                    		<lable>中标厂家：</lable>
                    		</td>
                    		<td style="text-align:left">
	                    		安徽宏源铁塔有限公司
                    		</td>
                    		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
	                    		<lable>中标日期：</lable>
                    		</td>
                    		<td style="text-align:left">
	                    		2017-03-01
                    		</td>
                    	</tr>
                    	<tr>
                    		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
	                    		<lable>图纸确认：</lable>
                    		</td>
                    		<td style="text-align:left">
	                    		2017-03-03
	                    	</td>	
                    		<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
	                    		<lable>协议签订：</lable>
                    		</td>
                    		<td style="text-align:left">
                    			2017-03-09
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




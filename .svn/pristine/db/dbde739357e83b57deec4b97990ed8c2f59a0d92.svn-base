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
					<h4 class="modal-title modal_header_top"><i class="icon icon-home"></i>&nbsp;查看需求清单</h4>
				</div>
				
				<div class="modal-body">
					<table class="table table-bordered" style="margin-bottom:15px">
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">计划编号：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PLANCODE_need"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">计划年度：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PLANYEAR_need"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">项目名称：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PROJECTNAME_need"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">项目类型：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PROJECTTYPE_need"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:110px;padding-left:0px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">项目电压等级：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PROJECTVOLTAGELEVE_need"></lable>
							</td>
							<td style="width:120px;padding-left:0px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">物资电压等级：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIALVOLTAGELEVE_need"></lable>
							</td>
						</tr>
						<!-- <tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">物料名称：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIELNAME_need"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">物料规格：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIALNORMS_need"></lable>
							</td>
						</tr> -->
						<!-- <tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">物料数量：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIELNUM_need"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">计量单位：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="MATERIELUNIT_need"></lable>
							</td>
						</tr> -->
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">施工单位：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="CONSTRUCTDEPT_need"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">估算总价(万)：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="ESTIMATETOTALPRICE_need"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">需求单位：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="DEMANDUNIT_need"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">需求日期：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="PLANDATE_need"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">联&nbsp;&nbsp;系&nbsp;人：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="LINKMAN_need"></lable>
							</td>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">联系电话：</label>
							</td>
							<td style="width:200px;">
								<lable style="margin-left: 15px;" id="LINKPHONE_need"></lable>
							</td>
						</tr>
						<tr>
							<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                            	<label style="margin:0">需求地点：</label>
							</td>
							<td colspan="3">
								<lable style="margin-left: 15px;" id="DEMANDPLACE_need"></lable>
							</td>
						</tr>
						<tr>
                 	 		<td style="width:100px;text-align:right;font-weight:bold;background: #f9f9f9">
                	    		<label style="margin:0">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	 		</td>
                	 		<td colspan="3">
                	 			<lable  style="margin-left: 15px;" id="REMARK_need"></lable>
                	 		</td>
                  		</tr> 
					</table>
					
					<h3 style="margin: 0;padding:0;font-size: 14px;margin-bottom:10px"><i class="icon icon-tags"></i>&nbsp;物资明细</h3>
					
					<table class="table table-bordered need_material_table">
						<thead>
							<tr>
								<th style="text-align: center;width:6%;background-color: #f9f9f9;">序号</th>
								<th style="text-align: center;width:15%;background-color: #f9f9f9;">物料名称</th>
								<th style="text-align: center;width:9%;background-color: #f9f9f9;">计量单位</th>
								<th style="text-align: center;width:9%;background-color: #f9f9f9;">规格型号</th>
								<th style="text-align: center;width:9%;background-color: #f9f9f9;">数量</th>
								<!-- <th style="text-align: center;width:9%;">单价<br>（元）</th> -->
								<!-- <th style="text-align: center;width:9%;">总价<br>（元）</th> -->
								<th style="text-align: center;width:26%;background-color: #f9f9f9;">货物描述</th>
							</tr>
						</thead>
						<tbody id="data_body_need">
						</tbody>
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
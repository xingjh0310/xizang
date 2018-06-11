<%@ page language="java" pageEncoding="UTF-8"%>
<link href="../../common/zui/lib/uploader/zui.uploader.min.css" rel="stylesheet">
<script src="../../common/zui/lib/uploader/zui.uploader.min.js"></script>
<style>
#photo_quertion{
	display:flex;
	flex-wrap:wrap;
}
.img_css{
	width:80px;
	height:80px;
	margin:8px;
}
.form-group{
	margin-bottom:5px;
} 
.file-list .file[data-status=uploading] .file-status>.text, .uploader-files .file[data-status=uploading] .file-status>.text{
	display:none;
} 
.file-list .file-status>.text, .uploader-files .file-status>.text{
	display:inline;
}
.file-list, .uploader-files{
	min-height:36px;
	max-height:200px;
	overflow:auto;
} 
</style>
<form id="info_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog">
		<div class="modal-dialog modal-lg" style="width:700px">
			<div class="modal-content">
				
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">处理结果录入</h4>
				</div>

					<div  style="position: relative; padding: 15px;height: 480px; overflow:scroll;">
					<div style="display: none;">
						id<input type="text" class="form-control" id="HANDLEID" name="fHandlerFormBean.mHandler.id">
						processPerson<input type="text" class="form-control" id="PROCESSPERSON" name="fHandlerFormBean.mHandler.processPerson">
						submitId<input type="text" class="form-control" id="ID" name="fHandlerFormBean.mHandler.submitId">
						engineerCode<input type="text" class="form-control" id="ENGINEERCODE" name="fHandlerFormBean.mHandler.engineerCode">
						remark<input type="text" class="form-control" id="" name="fHandlerFormBean.mHandler.remark">
						HandleFileNm<input type="text" class="form-control" id="HANDLEFILENM" name="fHandlerFormBean.mHandler.fileNm">
					</div>
                    <table class="table table-bordered table-condensed" id="table" style="margin-top: -5px;">
						<tbody>
						<tr>
							<td colspan="8">问题情况</td>
						</tr>
							<tr>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">物资类型：</td>
								<td id="MATERIELNAME_query" style="text-align: left"></td>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">问题类型：</td>
								<td><div class='autocut' style="text-align: left"
										id="SUBDICTNAME_query"></div></td>
							</tr>
							<tr>
								
								
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">上报单位：</td>
								<td id="DEPTNAME_query" style="text-align: left"></td>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">上&nbsp;&nbsp;报&nbsp;人：</td>
								<td id="SUBSTAFFNAME_query" style="text-align: left"></td>
							</tr>
							
							<tr>
								
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">上报时间：</td>
								<td id="REPORTTIME_query" style="text-align: left"></td>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">处理期限：</td>
								<td id="PROCESSLIMIT_query" style="text-align: left"></td>
							</tr>
							
							
							<tr>
								<td
									style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">问题描述：</td>
								<td colspan="3" id="PROBLEMDESC_query" style="text-align: left">
									
								</td>
								
							</tr>
							<tr>
								<td style="width:100px;text-align:right;font-weight:bold;background: #f1f1f1">
                            		<label style="margin:0">问题照片：</label>
								</td>
								<td colspan="3" style="padding:0">
									<div id="photo_quertion"></div>
								</td>
							</tr>
						</tbody>
					</table>
					<div><h4>问题处理结果</h4></div>
					
					<hr>
					<div class="form-group" style="margin-top: -10px">
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 处理方式：
						</label>
						<div class="col-md-4">
							
		               			<select id="PROCESSMETHOD" name="fHandlerFormBean.mHandler.processMethod" class="form-control"
				               			data-bv-group=".rowGroup" 
										required 
										data-bv-notempty-message="请选择处理方式">
									<option value="">请选择</option>
								</select>
						</div>
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 处理时间：
						</label>
						<div class="col-md-4 rowGroup" style="margin-top: -7px;">
							<a class='input-group date'  style="float: left;">
							<label class="input" style="display:inline;">
               				<input type='text' class="form-control laydate" style="width: 163px; height: 32px;"  readonly="readonly" 
               					   id="PROCESSTIME" 
               					   data-bv-group=".rowGroup"
                                   name="fHandlerFormBean.mHandler.processTime" 
               					   placeholder="请选择处理时间 "/>
                			<span class="input-group-addon" style="float: left; width: 39px; height: 32px;">
                 				<span class="icon-calendar"></span>
             				</span>
             				</label>
             				</a>
						</div>
						
					</div>
				  <div class="form-group" >
                	    <label class="col-md-2 control-label" style="margin: 18px auto;"><span class="text-danger"> &nbsp;</span>处理结果：</label>
                	    <div class="col-md-10">
                	          <textarea id="HANDLERESULT" name="fHandlerFormBean.mHandler.handleResult" maxlength="300" rows='3' class='form-control' placeholder="请输入处理结果"></textarea>
                	    </div>
                  </div>  
                  <div class="form-group add_img">
	                  <label class="col-md-2 control-label" style="margin: 18px auto;"><span class="text-danger"> &nbsp;</span>图片上传：</label>
	                  <div class="col-md-10">
	                  	 <div id='myUploader' class="uploader">
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
                  </div>
				</div>
				<div class="modal-footer" style="height: 40px;">
					<button type="submit" id="btn_save" class="btn btn-primary" style="margin-top: -14px">
						<i class="icon icon-save"></i> 保存
					</button>

					<button type="button" class="btn btn-large btn-primary" data-dismiss="modal" style="margin-top: -14px;margin-right:-5px">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			</div>
		</div>
	</div>

</form>




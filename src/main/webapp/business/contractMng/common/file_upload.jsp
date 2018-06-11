<%@ page language="java" pageEncoding="UTF-8"%>
<link href="../../../common/zui/lib/uploader/zui.uploader.min.css" rel="stylesheet">
<script src="../../../common/zui/lib/uploader/zui.uploader.min.js"></script>
<!-- 文件上传modal -->
<div class="modal fade" id="uploading_data_dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header" style="background-color: #FCFCFC;height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true" >×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;"><i class="icon icon-home">&nbsp;附件上传</i></h4>
				</div>
				<div class="modal-body" style="padding-bottom:5px;">
				<div class="container" style="margin-bottom: -10px;">
					<div class="row">
						<div class="col-md-12"> 
							
							<div id='uploaderExample' class="uploader" >
							  <div class="uploader-message text-center">
							    <div class="content"></div>
							    <button type="button" class="close">×</button>
							  </div>
							  
							  <div class="uploader-files file-list file-list-lg" data-drag-placeholder="请拖拽文件到此处"></div>
							  
							  <div class="uploader-actions">
							    <div class="uploader-status pull-right text-muted"></div>
							    <button type="button" class="btn btn-link uploader-btn-browse"><i class="icon icon-plus"></i> 选择文件</button>
							    <button type="button" class="btn btn-link uploader-btn-start"><i class="icon icon-cloud-upload"></i> 开始上传</button>
							  </div>
							</div>
							
						</div>
					</div>
				</div> 
			</div> 
				<div class="modal-footer"  style="height: 40px;background-color: #FCFCFC;">
					<button type="button" class="btn btn-large btn-primary" data-dismiss="modal" style="margin-top: -14px;margin-right:-12px">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			
			
		</div>
	</div>
</div>


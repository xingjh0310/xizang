<%@ page language="java" pageEncoding="UTF-8"%>
<link href="../../../common/zui/lib/uploader/zui.uploader.min.css" rel="stylesheet">
<script src="../../../common/zui/lib/uploader/zui.uploader.min.js"></script>
<style>
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

<div class="modal fade" id="uploading_data_dialog">
		<div class="modal-dialog modal-lg" style="width:680px">
			<div class="modal-content">
			
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;"><i class="icon icon-upload-alt"></i>&nbsp;文件上传</h4>
				</div>
				
				<div class="modal-body">
					<h4 style="line-height:15px;margin-top: 0px;margin-bottom: 10px;"><i class="icon icon-tags"></i>&nbsp;主合同附件</h4>
					<div id='myUploader' class="uploader">
					  	<div class="uploader-message text-center">
						    <div class="content"></div>
						    <button type="button" class="close"><i class="icon icon-times"></i></button>
					  	</div>
					  <div class="uploader-files file-list file-list-lg" data-drag-placeholder="请拖拽文件到此处"></div>
					  <div class="uploader-actions">
							<div class="uploader-status pull-right text-muted"></div>
							<button type="button" class="btn btn-link uploader-btn-browse"><i class="icon icon-plus"></i> 选择文件</button>
							<button type="button" class="btn btn-link uploader-btn-start"><i class="icon icon-cloud-upload"></i> 开始导入</button>
					  </div>
					</div>
					<h4 style="line-height:15px;margin-top: -5px;margin-bottom: 10px;"><i class="icon icon-tags"></i>&nbsp;履约评价附件</h4>
					<div id='myUploader_eval' class="uploader">
					  	<div class="uploader-message text-center">
						    <div class="content"></div>
						    <button type="button" class="close"><i class="icon icon-times"></i></button>
					  	</div>
					  <div class="uploader-files file-list file-list-lg" data-drag-placeholder="请拖拽文件到此处"></div>
					  <div class="uploader-actions">
							<div class="uploader-status pull-right text-muted"></div>
							<button type="button" class="btn btn-link uploader-btn-browse"><i class="icon icon-plus"></i> 选择文件</button>
							<button type="button" class="btn btn-link uploader-btn-start"><i class="icon icon-cloud-upload"></i> 开始导入</button>
					  </div>
					</div>
					<div id="noneEvaluate" class="alert alert-danger">待评价</div>
				</div>
				<div class="modal-footer" style="height: 40px;">
					<button type="button" class="btn btn-large" data-dismiss="modal" style="margin-top: -14px;margin-right:-5px">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			</div>
		</div>
</div>
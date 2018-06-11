<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal fade" id="barcode">
	<div class="modal-dialog modal-lg" style="width: 400px;">
		<div class="modal-content">
			<div class="modal-header" style="height: 40px">
				<button type="button" class="btn btn-link close"
					data-dismiss="modal">
					<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title" style="line-height: 15px;"><i class="icon icon-qrcode"></i>&nbsp;手机版</h4>
			</div>
				<div style="display:none;">
				</div>
				
			<div id="qrcodeTable" style="height: 250px;width: 250px;margin-top:50px;margin-left: 100px;" ></div> 
			<div style="text-align: center;">
				<label>本地下载：</label>
				<a style="color: green;text-decoration:none;" href="<%=basePath %>dow/app-debug.apk">手机版</a>
			</div>
			<div class="modal-footer" style="height: 40px;">
				<button type="button" class="btn btn-large" data-dismiss="modal" style="margin-top: -14px; margin-right: -5px">
					<i class="icon icon-times"></i> 取消
				</button>
			</div>
		</div>
	</div>
</div>


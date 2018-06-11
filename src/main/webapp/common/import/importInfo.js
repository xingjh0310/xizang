/**
 * 文件上传或导入
 * 
 * 1、url：后台路径
 * 2、myUploader：导入控件唯一ID
 * 3、fun：刷新页面回调方法
 * 
 * */
function filesUpload(url,myUploader,fun){
	var options = {
		url:url,
		filters:{
			mime_types:[{title: 'Excel', extensions: 'xlsx,xls,XLSX,XLS'}],
			max_file_size: '10mb',
			prevent_duplicates: true
		},
		responseHandler:function(responseObject, file){
			if(responseObject.response=='error') {
				var msg = new $.zui.Messager("消息提示：导入失败", {placement: "center",type:"primary"});
				msg.show();	
				return '导入失败';
			}else if(responseObject.response=='success') {
				fun(); //刷新页面
			}
		}
	}
	$('#'+myUploader).uploader(options);
	
}

/**
 * 文件上传、下载、删除
 * 
 * 1、url：后台路径
 * 2、myUploader：控件唯一ID
 * 3、fun：刷新页面回调方法
 * 
 * */
function filesUpload_(url,myUploader,fun,responseData){
	var options = {
		url:url,
		filters:{
			//mime_types:[{title: 'Excel', extensions: 'xlsx,xls,XLSX,XLS'}],
			max_file_size: '10mb',
			//prevent_duplicates: true
		},
		multipart_params:function(file, params){
			return {"fileId": file.id};
		},
		responseHandler:function(responseObject, file){
			if(responseObject.response=='error') {
				var msg = new $.zui.Messager("消息提示：上传失败", {placement: "center",type:"primary"});
				msg.show();	
				fun();
				return '上传失败';
			}else if(responseObject.response=='success') {
				fun();
			}
		},
		staticFiles:responseData,
		deleteActionOnDone: function(file, doRemoveFile){
			doRemoveFile();
	        deleteFileById(file.id,fun);
		}
	}
	$('#'+myUploader).uploader(options);
}


//删除文件
function deleteFileById(fileId,fun){
	var url=basePath+"file/upload!deleteFileById.action?fileId="+fileId;
	common_ajax(null,url, function(response) {
		fun();
		var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
	    msg.show();
	})
}

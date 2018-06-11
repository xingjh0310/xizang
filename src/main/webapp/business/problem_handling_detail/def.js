(function($) {
	
	$.System_Bank = function(option) {
		
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 btn_add		:$('#btn_add')		//增加按钮
			,btn_del     	:$('#btn_del')		//删除按钮	
			,btn_ref     	:$('#btn_ref')		//刷新按钮
			,table 			:$('#tbinfo')		//BootStrapTable的ID
			,searchInput	:$('#searchName')	//模糊查询input
			,info_dialog	:$('#info_dialog')  // 新增和编辑对应的窗体，注意和info_form的区别
			,info_form		:$('#info_form')    // 新增和编辑对应的表单，注意和info_dialog的区别
			,btn_save		:$('#btn_save')		//保存按钮
			,info_list		:$('#info_list')  	// 查看列表
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"question/detail!queryQuestionHandlerDetail.action"			//查询数据URL
			,url_save		:basePath+"question/handler!saveHandler.action"			//保存数据URL
		}

		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
		var TableQueryParams;
	    //////////////////////////////////////////////////////////////////////////////////////////////////
	    //加载主表数据，必须提供url_list，toolbar,opt-传递查询条件，可覆盖
		this.InitData=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.table.bootstrapTable($.extend(g_bootstrapTable_Options,
					{
				      url			:opt_all.url_list   // 请求后台的URL（*）
			         ,queryParams	:queryParams		// 传递参数（*）
			         ,onDblClickRow	:onDblClickRow		// 行双击事件
			         ,onSort		:onSort             // 排序事件
		             ,rowStyle		:comm_rowStyle		//行样式，可自定义
		             ,onLoadSuccess	:comm_onLoadSuccess //数据加载错误
		             ,onCheckAll	:onCheckAll			//全选
			         ,onCheck		:onCheck			//单选
			         ,onUncheck		:onUncheck			//不选
			         ,onUncheckAll	:onUncheckAll		//全不选
			         ,uniqueId		:"ID"
			         ,sortName		:"ID"	
				     ,sortOrder		:"DESC"	
					}));
			
			//选中多行改变表格背景色
			function onCheckAll(rows){
				for(var i=0;i<rows.length;i++){
					commRowStyle(i);
				}
			}
			//循环改变所有行颜色
			function commRowStyle(i){
				$("#tbinfo tbody tr[data-index="+i+"]").addClass("success");
			}
			//全不选时颜色恢复
			function onUncheckAll(){
				$("#tbinfo tbody tr").removeClass("success");
			}
			//选中一行改变表格背景色
			function onCheck(rows){
				$("#tbinfo tbody tr[data-uniqueid="+rows.ID+"]").addClass("success");
			}
			//不选中时颜色恢复
			function onUncheck(rows){
				$("#tbinfo tbody tr[data-uniqueid="+rows.ID+"]").removeClass("success");
			}
			
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				//查询条件
				var opt_parms={
					 "fSubmitFormBean.searchName"		:opt_control.searchInput.val() // 查询关键字
					// 在此增加查询条件
					,"fSubmitFormBean.materielType"			:$("#material_type").val()
					,"fSubmitFormBean.mSubmit.problemType"	:$("#question_type").val()
					,"fSubmitFormBean.mSubmit.treenmSysDept":$("#up_dept").val()
					,"fSubmitFormBean.mSubmit.state"		:$("#question_state").val()
					,"fSubmitFormBean.mSubmit.engineerCode"	: sessionStorage.getItem("engineeringNm")
					
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "fSubmitFormBean.pageBean.limit"		: params.limit   // 页面大小
			      ,"fSubmitFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,"fSubmitFormBean.pageBean.sort"		: params.sort  // 排序列名
			      ,"fSubmitFormBean.pageBean.sortOrder"	: params.order// 排位命令（desc，asc）
			    };
			    temp=$.extend(temp,opt_parms,opt);
//			    	alert(temp);
							    return temp;
			}
			// 双击事件
			function onDblClickRow(row){
				$("#tbinfo tbody tr[data-uniqueid="+row.ID+"]").addClass("success");
				if (row) {
					list(row);
				}
			}
			// 排序事件
			function onSort(name, order){
				_refresh;
			}
		}////end this.InitData
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// 初始化增加、修改和删除,公开函数
		this.InitAddEditDel=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.btn_add.bind("click",event_add);
			opt_all.btn_del.bind("click",event_del);
			opt_all.btn_ref.bind("click",event_ref);
			opt_all.searchInput.bind("keyup",carriage);
			// 保存button类型为submit
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
		}
	    // 修改记录，调出窗体,公开函数
		this.edit=function(id,flag) {
			var ids=verdict_del_update(opt_all.table,"ID",id);
			if(ids == id || ids == "" || ids == null){
				_edit(id,flag);
			}
		}
		//单条删除
		this.del=function(id){
			var ids=verdict_del_update(opt_all.table,"ID",id);
			if(ids == id || ids == "" || ids == null){
				_del(id);
			}
		}
		//新增和编辑函数
		function _edit(id,flag){
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
					function(){ 
						LoadEditData(id,flag);
					}
			);
		}
		
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(id,flag){
			var url=opt_all.url_list;
			var data={
				   "fSubmitFormBean.pageBean.limit"		: g_treelist_size   // 页面大小
			      ,"fSubmitFormBean.pageBean.offset"	: 0  // 当前记录偏移条数
			      ,"fSubmitFormBean.pageBean.sort"		: "ID"  // 排序列名
			      ,"fSubmitFormBean.pageBean.sortOrder"	: "DESC"// 排位命令（desc，asc）
			      ,"fSubmitFormBean.mSubmit.id"			: id
			}
			common_ajax(data,url, function(response) {
				//将数据显示到页面上
				comm_loadFormData_flag_html(response.rows[0],'_query');
				comm_loadFormData(response.rows[0]);
				var title="";
				if(flag== null || flag == ""){
					title="<i class='icon icon-plus'></i> 物资问题处理";
					$("#HANDLEFILENM").val(response.HANDLEFILENM);
					filesUpload(response.HANDLEFILENM);//初始化文件上传功能
				}else{
					title="<i class='icon icon-edit'></i> 查看处理结果";
					filesUpload(response.rows[0].HANDLEFILENM);//初始化文件上传功能
				}
				opt_all.info_dialog.find('.modal-title').html(title) ;
				var url_queryFileUpload=basePath+"file/upload!queryFileUpload.action?tableName=question_submit&tablePkColumn="+response.rows[0].FILENM;
				common_ajax(null, url_queryFileUpload, function(response){//将问题图片循环遍历到页面中
					var fileUrlList=response.mFileUploadList;
					var html="";
					$("#photo_quertion").empty();
					for(i=0;i<fileUrlList.length;i++){
						html+="<img class='img_css' alt='"+fileUrlList[i].name+"' src='"+fileUrlList[i].url+"'>"
					}
					$("#photo_quertion").append(html);
				});
				opt_all.info_dialog.modal({
					show : true
					,backdrop : "static" // 背景遮挡
						,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				});
				opt_all.info_dialog.on('hidden.zui.modal',function(){
					window.location.reload();
				});
			});
			
		}
		
		function list(row){
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
					function(){ 
						LoadEditListData(row);
					}
			);
		}
		
		this.question_result=function (id){
			LoadEditListData($.parseJSON($("#info_"+id).val()));
		}
		
		//双击事件显示模态框
		function LoadEditListData(row){
			var url_queryFileUpload_submit=basePath+"file/upload!queryFileUpload.action?tableName=question_submit&tablePkColumn="+row.FILENM;
			common_ajax(null, url_queryFileUpload_submit, function(response){//将问题图片循环遍历到页面中
				var fileUrlList=response.mFileUploadList;
				var html_submit="";
				$("#photo_submit_handing").empty();
				for(i=0;i<fileUrlList.length;i++){
					html_submit+="<img class='img_css' alt='"+fileUrlList[i].name+"' src='"+fileUrlList[i].url+"'>"
				}
				$("#photo_submit_handing").append(html_submit);
			});
			if(row.STATE==0){
				$("#handing_table").hide();
			}else{
				$("#handing_table").show();
				var url_queryFileUpload_handle=basePath+"file/upload!queryFileUpload.action?tableName=question_handle&tablePkColumn="+row.HANDLEFILENM;
				common_ajax(null, url_queryFileUpload_handle, function(response){//将问题图片循环遍历到页面中
					var fileUrlList=response.mFileUploadList;
					var html_handle="";
					$("#photo_handle_handing").empty();
					for(i=0;i<fileUrlList.length;i++){
						html_handle+="<img class='img_css' alt='"+fileUrlList[i].name+"' src='"+fileUrlList[i].url+"'>"
					}
					$("#photo_handle_handing").append(html_handle);
				});
			}
			// 获取到数据，显示在界面上
			comm_loadFormData_flag_html(row,'_handing');
			opt_all.info_list.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {

            });
			opt_all.info_list.on('hidden.zui.modal',function(){
				$("#tbinfo tbody tr[data-uniqueid="+row.ID+"]").removeClass("success");
			})
			
		}
		
	    // 保存数据
		function _save() {
			//$("#HANDLEID").val(0);
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		var url=opt_all.url_save;
	 		common_ajax(json, url, function(response){
 				opt_all.info_dialog.modal("hide");
 				_refresh();
	 		});
	 		
		}
		
	    // 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"id");
           if (ids.length==0) return;
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");
           var url= opt_all.url_remove+"?infoFromBean.ids="+ids
            
			confirm("设备信息","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
			   if(result){
				   common_ajax(null, url, function(response){
					    _refresh();
					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
					    msg.show();	
					    destroyCarSelect();
					    queryAllParentEuipment();
			 		}); 
               }
			}); 
		}
		
		function _del(id){
			$(".table tbody tr[data-uniqueid="+id+"] td").addClass("row-bcground");
			var url= opt_all.url_remove+"?infoFromBean.ids="+id;
			confirm("设备信息","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
				   if(result){
					   common_ajax(null, url, function(response){
						    _refresh();
						    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
						    msg.show();	
						    destroyCarSelect();
						    queryAllParentEuipment();
				 		}); 
	               }
				}); 
		}
		
		/////////////////////////////////////////////////////////////////
		//****绑定事件
		//绑定新增事件
		function event_add(){
			var ids=g_select_and_tip(opt_all.table,"ID");
	        if (ids.length==0) return;
			_edit(ids);
		}
		//绑定刷新事件
		function event_ref(){
			 _refresh();
		}
		//绑定删除事件
		function event_del(){
			_removeids();
		}
		//绑定审核事件
		function event_flag(){
			_flag();
		}
		//****绑定事件******end
		this.zTreeRefresh=function(){
			_refresh()
		}
		
		//切换工程刷新事件
		this.flash=function(){
			_refresh();
		}
		
	    // 刷新
	    function _refresh(){
	    	opt_all.table.bootstrapTable('refresh');
	    }  
	    
		// 重置窗体
		function _reset(){
			opt_all.info_form.data('bootstrapValidator').resetForm(false);
		}
		//公开函数
		this.reset=function(){
			_reset();
		}
		
		////////////////////////////////////////////////////////////////////////////////
		//加载所有外键表到下拉框，无
		function Load_EditSelectData(callback){
			//所有编辑页面下拉框加载
			callback();
		}
		/////////////////////////////////////////////////////////////////////////////////
		//回车事件
		function carriage(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
            
		     if(e && e.keyCode==13){ // enter 键
		         //要做的事情
		    	 event_ref();
		    }
		}
		
		//文件上传
		function filesUpload(fileNm){
			var file_options;
			var url_queryFileUpload=basePath+"file/upload!queryFileUpload.action?tableName=question_handle&tablePkColumn="+fileNm;
			common_ajax_async(null,url_queryFileUpload,function(response){
	 			file_options = {
	 					//初始化参数
	 					 url:basePath+"file/upload!saveFileUpload.action?tableName=question_handle&tablePkColumn="+fileNm+"&engineerCode="+sessionStorage.getItem("engineeringNm")	//文件上传地址
	 					,filters:{//文件过滤器
	 						// 最大上传文件为 10MB
	 						max_file_size: '10mb'
	 					}
			 			,multipart_params:function(file, params) {
						    return {"fileId": file.id};
						}
			 			,responseHandler:function(responseObject, file){
			 				// 当服务器返回的文本内容包含 `'error'` 文本时视为上传失败
			 				if(responseObject.response=='error') {
			 					var msg = new $.zui.Messager("消息提示：上传失败", {placement: "center",type:"primary"});
			 					msg.show();	
			 					return '上传失败';
			 				}else if(responseObject.response=='success') {
			 					//var nub=$("#fileNub_id"+id).html();
								//$("#fileNub_id"+id).html(Number(nub)+1);
			 				}
			 			}
			 			,staticFiles:response.mFileUploadList//查询已上传文件
			 			,deleteActionOnDone: function(file, doRemoveFile) {//删除上传文件
			 		          doRemoveFile();
			 		          deleteFileById(file.id,id);
			 		      }
	 			}
	 			$('#myUploader').uploader(file_options);
	 		},true);
		}
	
		//删除文件
		function deleteFileById(fileId,id){
			var url=basePath+"file/upload!deleteFileById.action?fileId="+fileId;
			common_ajax(null,url, function(response) {
				if(response.retflag=="success"){
					var nub=$("#fileNub_id"+id).html();
					$("#fileNub_id"+id).html(nub-1);
				}else{
					var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
				    msg.show();	
				}
			})
		}
		
	};
})(jQuery);
			

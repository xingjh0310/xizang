(function($) {
	$.System_distributor = function(option) {
		//option自定义参数覆盖
		//界面控件变量
		var opt_control={
				table			:$("#tbinfo")	//BootStrapTable的ID
			   ,searchInput		:$("#searchInput")	//模糊查询input
			   ,btn_add			:$("#btn_add")	//新增按钮
			   ,btn_out			:$("#btn_out")	//导出全部按钮
			   ,btn_out_page	:$("#btn_out_page")	//导出当前页
			   ,btn_into		:$("#btn_into")	//导入按钮
			   ,btn_del			:$("#btn_del")	//批量删除按钮
			   ,btn_ref			:$("#btn_ref")	//查询按钮
			   ,info_dialog		:$('#info_dialog')  // 新增和编辑对应的窗体
			   ,info_form		:$('#info_form')    // 新增和编辑对应的表单
			   ,info_list		:$("#info_list")	// 详情信息
			   ,info_upload		:$("#info_upload")	//导入模态框
			   ,file_upload		:$("#file_upload")	//附件上传模态框
		}
		//请求地址URL
		var opt_url={
			url_list		:basePath+"distributor/distributor!queryAllDistributor.action"//查询数据URL
		   ,url_save		:basePath+"distributor/distributor!saveOrUpdate.action"//新增/修改数据URL
		   ,url_remove		:basePath+"distributor/distributor!removeids.action"//删除数据URL
		   ,url_edit		:basePath+"distributor/distributor!edit.action"//查询数据URL
		   ,url_downLoad	:basePath+"distributor/distributor!downLoadDistributorInfo.action"//导出数据URL
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
			
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				TableQueryParams=params;
				//查询条件
				var opt_parms={
					   "fDistributorFormBean.searchName"			: opt_control.searchInput.val() // 查询关键字
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				       "fDistributorFormBean.pageBean.limit"		: params.limit   // 页面大小
				      ,"fDistributorFormBean.pageBean.offset"		: params.offset  // 当前记录偏移条数
				      ,"fDistributorFormBean.pageBean.sort"			: params.sort  // 排序列名
				      ,"fDistributorFormBean.pageBean.sortOrder"	: params.order// 排位命令（desc，asc）
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
			opt_all.btn_ref.bind("click",event_ref);
			opt_all.btn_del.bind("click",event_del);
			opt_all.btn_add.bind("click",event_add);
			opt_all.btn_into.bind("click",upload_model_show);
			opt_all.btn_out.bind("click",downLoadAllDemand);
			opt_all.btn_out_page.bind("click",downLoadDemandInPage);
			opt_all.searchInput.bind("keydown",carriage);
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// //校验数据正确
			});	
				
		}
		
		//回车事件
		function carriage(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
		     if(e && e.keyCode==13){ // enter 键
		         //要做的事情
		    	 event_ref();
		    }
		}
		
		//绑定新增事件
		function event_add(){
			LoadEditData(0);
		}
		
		// 修改记录，调出窗体,公开函数
		this.edit=function(id,onlyread) {
			var ids=verdict_del_update(opt_all.table,"ID",id);
			if(ids == id || ids == "" || ids == null){
				Load_EditSelectData(function(){
					LoadEditData(id);
				});
			}
		}
		
		//修改/新增窗体
		function LoadEditData(id){
			var url = opt_all.url_edit+"?fDistributorFormBean.mDistributor.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
				comm_loadFormData(response.infoBean);
				var title="";
				if (id==0) {
					title="<i class='icon icon-plus-sign'></i> 新增供应商信息";
					$('#id').val(0)
				}
				if (id!=0) {
					title="<i class='icon icon-pencil'></i> 修改供应商信息";
				}
				
				//初始化选择-人员类型
				$('#listnmSysStaff').trigger('chosen:updated');
				
				opt_all.info_dialog.find('.modal-title').html(title) ;
				
				opt_all.info_dialog.modal({
					show : true
					,backdrop : "static" // 背景遮挡
						,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
					
				});
			});
			
		}
		
		//单条删除
		this.del=function(id){
			var ids=verdict_del_update(opt_all.table,"ID",id);
			if(ids == id || ids == "" || ids == null){
				_del(id);
			}
		}
		
		//绑定刷新事件
		function event_ref(){
			 _refresh();
		}
		
		//绑定删除事件
		function event_del(){
			_removeids();
		}
		
		// 刷新
	    function _refresh(){
	    	opt_all.table.bootstrapTable('refresh');
	    }
	    
	    // 重置窗体
		function _reset(){
			opt_all.info_form.data('bootstrapValidator').resetForm(false);
		}
		
		// 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"ID");
           if (ids.length==0) return;
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");
           var url= opt_all.url_remove+"?fDistributorFormBean.ids="+ids
            
           confirm("供应商信息","您确定要删除这些记录吗？","icon-remove-sign", function(result) {
			   if(result){
			 		common_ajax(null, url, function(response){
						// 删除后，从后台取出返回数据
					    _refresh();
					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
					    msg.show();	
						    
			 		}); 
               }
			});    
		   
		}
		
		//删除单条记录
		function _del(id){
			$(".table tbody tr[data-uniqueid="+id+"] td").addClass("row-bcground");
			var url= opt_all.url_remove+"?fDistributorFormBean.ids="+id
			confirm("供应商信息","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
			   if(result){
			 		common_ajax(null, url, function(response){
						// 删除后，从后台取出返回数据
					    _refresh();
					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
					    msg.show();	
						    
			 		}); 
               }
			}); 
		}
		
		//保存事件
		function _save(){
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		common_ajax(json, opt_all.url_save, function(response){
	 			opt_all.info_dialog.modal("hide");
			    // 增加数据
			    if ($('#id').val()==0){
			        // 增加表格数据
			    	opt_all.table.bootstrapTable("prepend", response.infoBean);								    
			    } else {
			        // 修改表格数据
			    	opt_all.table.bootstrapTable("updateByUniqueId", {
		                ID: response.infoBean.id,
		                row: response.infoBean
		            });		
	            }
	 			_refresh();
	 		}); 	
		}
		
		//详情模态框
		function list(row){
		   comm_loadFormData_flag_html(row,'_distributor');
		   opt_all.info_list.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
		   }).on('shown.zui.modal', function() {

           });
		   opt_all.info_list.on('hidden.zui.modal',function(){
			$("#tbinfo tbody tr[data-uniqueid="+row.ID+"]").removeClass("success");
		   });
		}
		
		//文件上传
		function filesUpload(){
			var file_options = {
				//初始化参数
				url:basePath+'distributor/distributor!importDistributorInfo.action'	//文件上传地址
			   ,filters:{//文件过滤器
				   	// 只允许上传Excel
				    mime_types: [
				        {title: 'Excel', extensions: 'xlsx,xls'},
				    ],
				    // 最大上传文件为 10MB
				    max_file_size: '10mb',
				    // 不允许上传重复文件
				    prevent_duplicates: true
				}
			   ,responseHandler:function(responseObject, file){
				   // 当服务器返回的文本内容包含 `'error'` 文本时视为上传失败
				   if(responseObject.response=='error') {
						return '导入失败';
				   }else if(responseObject.response=='success') {
					   opt_all.table.bootstrapTable('refresh');
				   }
			   }
			}
			$('#myUploader').uploader(file_options);
		}
		
		//文件上传模态框
		function upload_model_show(){
			filesUpload();
			opt_all.info_upload.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
	
	        });
		}
		
		//导出全部供应商信息
		function downLoadAllDemand(){
			var url= opt_all.url_downLoad+"?";
			var data="fDistributorFormBean.pageBean.limit="+g_treelist_size
					+"&fDistributorFormBean.pageBean.offset=0" 
					+"&fDistributorFormBean.pageBean.sortOrder="+TableQueryParams.order
					+"&fDistributorFormBean.pageBean.sort="+TableQueryParams.sort
					+"&fDistributorFormBean.searchName="+opt_all.searchInput.val();
			
			confirm("<i class='icon icon-reply'></i>&nbsp;供应商信息","您确定要导出全部信息吗？","icon-info", function(result) {
				   if(result){
					   window.location.href=url+data;
	               }
				});
		}
		
		//导出当前页
		function downLoadDemandInPage(){
			var url= opt_all.url_downLoad+"?";
			var ids = "";
			var bt_ids=opt_all.table.bootstrapTable('getSelections');
			if (bt_ids.length == 0) {
				var data_page="fDistributorFormBean.pageBean.limit="+TableQueryParams.limit
							 +"&fDistributorFormBean.pageBean.offset="+TableQueryParams.offset
							 +"&fDistributorFormBean.pageBean.sortOrder="+TableQueryParams.order
							 +"&fDistributorFormBean.pageBean.sort="+TableQueryParams.sort
							 +"&fDistributorFormBean.searchName"+opt_all.searchInput.val();
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;供应商信息","您确定要导出当前页中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data_page;
					   }
				});
			}else{
				for (var i = 0; i < bt_ids.length; i++) {
					ids = ids + "," + bt_ids[i]["ID"];
				}
				ids = ids.substring(1);
				var data="fDistributorFormBean.ids="+ids
						+"&fDistributorFormBean.pageBean.limit="+g_treelist_size
						+"&fDistributorFormBean.pageBean.offset=0" 
						+"&fDistributorFormBean.pageBean.sort=ID" 
						+"&fDistributorFormBean.pageBean.sortOrder=DESC";
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;供应商信息","您确定要导出选中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data;
					   }
				});
			}
		}
		
		//附件上传
		this.upload_file=function (id){
			var file_options;
			var url_queryFileUpload=basePath+"file/upload!queryFileUpload.action?tableName=pub_supplier&tablePkColumn="+id;
			common_ajax_async(null,url_queryFileUpload,function(response){
	 			file_options = {
	 					//初始化参数
	 					 url:basePath+"file/upload!saveFileUpload.action?tableName=pub_supplier&tablePkColumn="+id	//文件上传地址
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
			 					opt_all.table.bootstrapTable('refresh');
			 				}
			 			}
			 			,staticFiles:response.mFileUploadList//查询已上传文件
			 			,deleteActionOnDone: function(file, doRemoveFile) {//删除上传文件
			 		          doRemoveFile();
			 		          deleteFileById(file.id,id);
			 		      }
	 			}
	 			$('#fileUploader').uploader(file_options);
	 		},true);
			
			opt_all.file_upload.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
	
	        });
			opt_all.file_upload.on('hidden.zui.modal',function(){
				//window.location.reload();
			});
			
		}
		
		//删除文件
		function deleteFileById(fileId,id){
			var url=basePath+"file/upload!deleteFileById.action?fileId="+fileId;
			common_ajax(null,url, function(response) {
				if(response.retflag=="success"){
					opt_all.table.bootstrapTable('refresh');
				}else{
					opt_all.table.bootstrapTable('refresh');
					var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
				    msg.show();	
				}
			})
		}
		
		//下拉数据初始化
		this._InitStaffInfo = function (){
			Load_EditSelectData_ListnmSysStaff();
		}
		
		function Load_EditSelectData_ListnmSysStaff(){
			$('#listnmSysStaff').empty();
			var url=basePath+"system/sysstaffrefacct!list.action";
			$('#listnmSysStaff').append("<option value=''>请选择</option>");
			common_ajax(null,url, function(data) {
				for(var i=0,len=data.rows.length;i<len;i++){
					$('#listnmSysStaff').append('<option value=' + data.rows[i].NM + '>'+ data.rows[i].STAFFNAME + '</option>');
				}
			});
		}
		
		function Load_EditSelectData(callback){
			//所有编辑页面下拉框加载
			callback();
		}
		
	}
})(jQuery);
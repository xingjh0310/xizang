(function($) {
	
	$.System_Bank = function(option) {
		
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 btn_add		:$('#btn_add')		//增加按钮
			,btn_del     	:$('#btn_del')		//删除按钮	
			,btn_flag     	:$('#btn_flag')		//审核按钮	
			,btn_ref     	:$('#btn_ref')		//刷新按钮
			,table 			:$('#tbinfo')		//BootStrapTable的ID
			,searchInput	:$('#searchName')	//模糊查询input
			,info_dialog	:$('#info_dialog')  // 新增和编辑对应的窗体，注意和info_form的区别
			,info_form		:$('#info_form')    // 新增和编辑对应的表单，注意和info_dialog的区别
			,select_flag    :$('#select_flag')  //当前选中的审核状态
			,btn_save		:$('#material_save')//保存按钮
			,info_list		:$('#info_list')  	// 查看列表
			,createDate		:$('#createDate')	//下次保养时间
			,btn_into		:$("#btn_into")		//导入按钮
			,btn_out_page	:$("#btn_out_page")	//导出当前页按钮
			,btn_out		:$("#btn_out")		//导出全部按钮
			,info_upload	:$("#info_upload")  //文件上传模态框
		}
		//B、数据状态参数变量
		var opt_state={
			 flag_cur		:0 ////审核状态,当前List状态 flag=0
			,flag_new		:1 ////审核状态,审核后的状态     flag=1				
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"materielBase/materielBase!list.action"	 		//查询数据URL
			,url_edit		:basePath+"materielBase/materielBase!edit.action"			//编辑数据URL
			,url_save		:basePath+"materielBase/materielBase!save.action"			//保存数据URL
			,url_remove		:basePath+"materielBase/materielBase!removeids.action"		//删除数据URL
			,url_flag 		:basePath+"materielBase/materielBase!flag.action"			//审核数据URL
			,url_downLoad	:basePath+"materielBase/materielBase!downLoad.action"		//导出数据URL
		}

		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_state,opt_url,option));
		var tableParam;
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
//			         ,rowStyle		:rowStyle
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
				$("#tbinfo tbody tr[data-uniqueid="+rows.id+"]").addClass("success");
			}
			//不选中时颜色恢复
			function onUncheck(rows){
				$("#tbinfo tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
			}
			
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				tableParam=params;
				//查询条件
				var opt_parms={
					 "materielBaseFormBean.searchName"		:opt_control.searchInput.val(), // 查询关键字
					// 在此增加查询条件
					 "materielBaseFormBean.materielBase.materialGroup": $("#searchCode").val(),
					 /***物料查询计量单位******/
					 "materielBaseFormBean.materielBase.unit": $("#materiel_unit").val()
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "materielBaseFormBean.pageBean.limit"	: params.limit   // 页面大小
			      ,"materielBaseFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,"materielBaseFormBean.pageBean.sort"		: params.sort  // 排序列名
			      ,"materielBaseFormBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
			    };
			    temp=$.extend(temp,opt_parms,opt);
//			    	alert(temp);
							    return temp;
			}
			// 双击事件
			function onDblClickRow(row){
				$("#tbinfo tbody tr[data-uniqueid="+row.id+"]").addClass("success");
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
			opt_all.btn_into.bind("click",upload_model_show);
			opt_all.btn_out.bind("click",downLoadAllDemand);  //导出
			opt_all.btn_out_page.bind("click",downLoadDemandInPage);
			
			// 保存button类型为submit
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// //校验数据正确
			});	
		}
	    // 修改记录，调出窗体,公开函数
		this.edit=function(id,onlyread) {
			
				_edit(id);
		}
		//单条删除
		this.del=function(id){
			
				_del(id);
			
		}
		//新增和编辑函数
		function _edit(id,onlyread){
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
					function(){ 
						LoadEditData(id,onlyread);
						
					}
					
			);
		}
		
		//参数显示 调出窗体
		this.queryPara=function(id,onlyread) {
			_queryPara(id);
		}
		//回调
		function _queryPara(id,onlyread){
			Load_EditSelectData( 
					function(){ 
						LoadPara(id,onlyread);
					}
					
			);
		}
		//加载 并调用  
		function LoadPara(id,onlyread){
			var url = opt_all.url_edit+"?materielBaseFormBean.materielBase.id=" + id;
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
				comm_loadFormData(response.infoBean);
				
				opt_all.parameter_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。

	           });
			});
		}
		
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(id,onlyread){
			
			if(id==0){
				$("#id_materiel").val(id);
				var nm=$("#code").val();
				var name=$("#baseName").val();
				$("#carCode_materiel").val(nm)
				var arr =[];
				arr= nm.split(",")
				if(arr.length>1){
					var msg = new $.zui.Messager("消息提示："+"请先选择一个节点", {placement: "center",type:"primary"});
				    msg.show();
				    return
				}
				
			}
			
			var url = opt_all.url_edit+"?materielBaseFormBean.materielBase.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
				console.log(JSON.stringify(response.infoBean))
				
				comm_loadFormData_flag(response.infoBean,"_materiel");//显示数据
				
				if(id > 0){
					opt_all.info_dialog.find('.modal-title').html("修改物料信息") ;
					
				}else{
					if(nm==null||nm==""){
						var msg = new $.zui.Messager("消息提示："+"请先选择节点", {placement: "center",type:"primary"});
					    msg.show();
						return
					}
					
					
					opt_all.info_dialog.find('.modal-title').html("新增物料信息"+"【"+name+"】") ;
				}
				
				opt_all.info_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。

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
		
		function LoadEditListData(row){
			var name=$("#baseName").val();
			
			comm_loadFormData_flag_html(row,'_list'); //显示信息
			var state=row.STATE;
			
				if(state==0){
					$("#STATE_list").html("是")
				}
				if(state==1){
					$("#STATE_list").html("否")
				}
				
				//opt_all.info_list.find('.modal-title').html("查看物料信息"+"【"+name+"】") ;
				
				opt_all.info_list.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。

	            });
				opt_all.info_list.on('hidden.zui.modal',function(){
					
				})
		
		}
		
	    // 保存数据
		function _save() {
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		
	 		var url=opt_all.url_save;
	 		common_ajax(json, url, function(response){
	 			
	 			console.log(JSON.stringify(response))
	 			var result=response.result;
	 		
	 				opt_all.info_dialog.modal("hide");
	 				// 增加数据
	 				if ($('#id').val()==0){
	 					// 增加表格数据
	 					opt_all.table.bootstrapTable("prepend", response.infoBean);								    
	 				} else {
	 					// 修改表格数据
	 					opt_all.table.bootstrapTable("updateByUniqueId", {
	 						//id: response.infoBean.id,
	 						//row: response.infoBean
	 					});		
	 				}						    
	 				//location.reload();
	 				 _refresh();
	 		});
	 		
		}
		
	    // 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"ID");
           if (ids.length==0) return;
           
           var url= opt_all.url_remove+"?materielBaseFormBean.ids="+ids
            
			confirm("物料基础信息","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
			   if(result){
				   common_ajax(null, url, function(response){
					    _refresh();
					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
					    msg.show();	
					   
			 		}); 
               }
			}); 
		}
		
		function _del(id){
			
			var url= opt_all.url_remove+"?materielBaseFormBean.ids="+id;
			confirm("物料基础信息","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
				   if(result){
					   common_ajax(null, url, function(response){
						    _refresh();
						    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
						    msg.show();	
				 		}); 
	               }
				}); 
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
		//文件上传
		function filesUpload(){
			var file_options = {
				//初始化参数
				url:basePath+'materielBase/materielBase!upLoad.action'	//文件上传地址
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
					   var msg = new $.zui.Messager("消息提示：导入失败", {placement: "center",type:"primary"});
					   msg.show();	
					   return '导入失败';
				   }else if(responseObject.response=='success') {
					   _refresh();
				   }
			   }
			}
			$('#myUploader').uploader(file_options);
		}
		
		//导出全部物料基础信息
		function downLoadAllDemand(){
			var url= opt_all.url_downLoad+"?";
			var data="materielBaseFormBean.pageBean.limit="+g_treelist_size
					+"&materielBaseFormBean.pageBean.offset=0" 
					+"&materielBaseFormBean.pageBean.sortOrder="+tableParam.order
					+"&materielBaseFormBean.pageBean.sort="+tableParam.sort
					+"&materielBaseFormBean.searchName="+opt_all.searchInput.val()
					+"&materielBaseFormBean.materielBase.materialGroup="+$("#searchCode").val();
			confirm("物料基础信息","您确定要导出全部信息吗？","icon-info", function(result) {
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
				var data_page="materielBaseFormBean.pageBean.limit="+tableParam.limit
							 +"&materielBaseFormBean.pageBean.offset="+tableParam.offset
							 +"&materielBaseFormBean.pageBean.sortOrder="+tableParam.order
							 +"&materielBaseFormBean.pageBean.sort="+tableParam.sort
							 +"&materielBaseFormBean.searchName"+opt_all.searchInput.val()
							 +"&materielBaseFormBean.materielBase.materialGroup="+$("#searchCode").val();
				confirm("物料基础信息","您确定要导出当前页中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data_page;
					   }
				});
			}else{
				for (var i = 0; i < bt_ids.length; i++) {
					ids = ids + "," + bt_ids[i]["ID"];
				}
				ids = ids.substring(1);
				var data="materielBaseFormBean.ids="+ids
						+"&materielBaseFormBean.pageBean.limit="+g_treelist_size
						+"&materielBaseFormBean.pageBean.offset=0" 
						+"&materielBaseFormBean.pageBean.sort=ID" 
						+"&materielBaseFormBean.pageBean.sortOrder=DESC";
						+"&materielBaseFormBean.materielBase.materialGroup="+$("#searchCode").val();
				confirm("物料基础信息","您确定要导出选中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data;
					   }
				});
			}
		}
		
		/////////////////////////////////////////////////////////////////
		// 初始化审核,公开函数
		this.InitFlag=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.btn_flag.bind("click",event_flag);
		}
	 //////////////////////////////////////////////////////////////////
		//****绑定事件
		//绑定新增事件
		function event_add(){
			_edit(0);
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
		this.ref=function(){
			_refresh();
		}
	    // 刷新
	    function _refresh(){
	    	opt_all.table.bootstrapTable('refresh');
	    }  
	    
		// 重置窗体
		function _reset(){
			opt_all.info_form.data('bootstrapValidator').resetForm(false);
			//opt_all.info_form.data('bootstrapValidator').resetForm(false);
		}
		//公开函数
		this.reset=function(){
			_reset();
		}
		
		
		function _reset_Para(){
			opt_all.parameter_form.data('bootstrapValidator').resetForm(false);
		}
		
		this.reset_Para=function(){
			_reset_Para();
		}
		/////////////////////////////////////////////////////////////////////////////////////////////
		
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
		
		
		function testEquipmentName(){
			$('#info_dialog').bootstrapValidator({
		    	group: '.rowGroup',
		        message: 'This value is not valid',
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	"infoFromBean.mInfo.equipmentName": {
		                message: '设备名称验证',
		                validators: {
		                    notEmpty: {
		                        message: '设备名称不能为空'
		                    },
		                   
		                }
		            }
		        },
		        submitHandler: function (validator, form, submitButton) {
		           
		        }
		    });
		}
		
		function nextTimeChange(){
			setTimeout(function(){
				$('#info_dialog').bootstrapValidator('revalidateField', 'infoFromBean.mInfo.createDate');
				testNextTime();
				},500);
			
		}
		
		function testNextTime(){
			$('#info_dialog').bootstrapValidator({
		    	group: '.rowGroup',
		        message: 'This value is not valid',
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	"infoFromBean.mInfo.createDate": {
		                message: '时间验证',
		                validators: {
		                	notEmpty: {
		                        message: '保养开始时间不能为空'
		                    },
		                   
		                }
		            }
		        },
		        submitHandler: function (validator, form, submitButton) {
		           
		        }
		    });
		}
		
		
	};
})(jQuery);
			

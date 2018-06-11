(function($) {
	$.Storage_Query = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 query_table 			:$('#query_table')		//页面BootStrapTable的ID
			,query_searchName		:$('#query_searchName')	//页面模糊查询input
			,query_info_show		:$("#query_info_show")	//双击事件窗口id
			,query_ref				:$("#query_ref")		//模糊查询
			,query_form				:$("#query_form")
			,storage_name			:$("#storage_name")		//查询条件仓库名称
			,medium_name			:$("#medium_name")		//查询条件介质名称
			,edit_dialog			:$("#edit_dialog")		//添加编辑窗口
			,query_add				:$("#query_add")		//添加按钮
			,info_form				:$("#info_form")		//添加编辑的表单
			,uploading_data_dialog	:$('#uploading_data_dialog') //文件上传模态框
		}
		//B、请求地址URL
		var opt_url={
			url_list		:basePath+"business/contractMng/technicalAgreement/json.jsp"//查询数据URL
//			  url_list		:basePath+"warehouse/warehouse!listStorage.action"			//查询数据URL
//			 ,url_storage	:basePath+"stroage/stroage!listStorage.action"
//			 ,url_base		:basePath+"base/list!listMedium.action"
//			 ,url_unit_table:basePath+"registration/registration!list.action"
		}
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		this.InitData=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.query_table.bootstrapTable($.extend(g_bootstrapTable_Options,
					{
				      url			 	:opt_all.url_list   // 请求后台的URL（*）
			         ,queryParams	  	:queryParams		// 传递参数（*）
			         ,onDblClickRow 	:onDblClickRow		// 行双击事件
			         ,onSort			:onSort             // 排序事件
		             ,rowStyle			:comm_rowStyle		//行样式，可自定义
		             ,onLoadSuccess		:comm_onLoadSuccess //数据加载错误
		             ,onCheckAll 		:onCheckAll   //全选
		             ,onCheck  :onCheck   //单选
		             ,onUncheck  :onUncheck   //不选
		             ,onUncheckAll :onUncheckAll  //全不选
		             ,singleSelect		:false
		             ,pageSize:15
					}));
			//选中多行改变表格背景色
			   function onCheckAll(rows){
			    for(var i=0;i<rows.length;i++){
			     commRowStyle(i);
			    }
			   }
			   //循环改变所有行颜色
			   function commRowStyle(i){
			    $("#query_table tbody tr[data-index="+i+"]").addClass("success");
			   }
			   //全不选时颜色恢复
			   function onUncheckAll(){
			    $("#query_table tbody tr").removeClass("success");
			   }
			   //选中一行改变表格背景色
			   function onCheck(rows){
			    $("#query_table tbody tr[data-uniqueid="+rows.id+"]").addClass("success");
			   }
			   //不选中时颜色恢复
			   function onUncheck(rows){
			    $("#query_table tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
			   }
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				//查询条件
				var opt_parms={
					 "mMediumAddFormBean.mFuzzyQuery"			:opt_control.query_searchName.val() // 查询关键字
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "mMediumAddFormBean.mPageResults.limit"			: params.limit   // 页面大小
			      ,"mMediumAddFormBean.mPageResults.offset"			: params.offset  // 当前记录偏移条数
			      ,"mMediumAddFormBean.mPageResults.sort"			: params.sort  	  // 排序列名
			      ,"mMediumAddFormBean.mPageResults.sortOrder" 		: params.order   // 排位命令（desc，asc）
			      ,"mMediumAddFormBean.mStorageInfo.storageInfoName": opt_all.storage_name.val()
			      ,"mMediumAddFormBean.mDictMediumtype.mediumName"	: opt_all.medium_name.val()
			      
			    };
			    temp=$.extend(temp,opt_parms,opt);
				return temp;
			}
			// 双击事件
			function onDblClickRow(row){
				if (row) {
					_table(row.id);
					Load_unit_details(row.serialNumber);
				}
			}
			// 排序事件
			function onSort(name, order){
				_refresh();
			}
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// 初始化增加、修改和删除,公开函数
		this.InitAddEditDel=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.query_add.bind("click",event_add);
			opt_all.query_ref.bind("click",event_ref);
			opt_all.storage_name.bind("change",event_ref);
			opt_all.medium_name.bind("change",event_ref);
		}
		//加载单位表格详情
		function Load_unit_details(serial_number){
			var url = opt_control.url_unit_table+"?registrationFormBean" +
					".registrationInfoBean.serialNumber=" + serial_number;
			common_ajax(null,url, function(response) {
			if(response.rows[0].isSecret == 1){
				response.rows[0].isSecret = "是"; 
			}else{ //如果等于一就是涉密
				response.rows[0].isSecret = "否";
			}
			if(response.rows[0].transportModel == 1){
				response.rows[0].transportModel = "公司派车";
			}else{ //如果等一就是公司派车
				response.rows[0].transportModel = "单位自送";
			}
			
			comm_loadFormDataList(response.rows[0]);
			});
		}
		this.InitSelect = function (){
			Load_storage_data();
			Load_medium_data();
		}
	    // 修改记录，调出窗体,公开函数
		this.edit=function(id) {
			_edit(id);
		}
		//新增和编辑函数
		function _edit(id){
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
					function(){ 
						LoadEditData(id);
					}
			);
		}
		//显示详情
		function _table(queryId){
//			var url = opt_all.url_list+"?mMediumAddFormBean.mStorageManage.id=" + queryId;
//			common_ajax(null,url, function(response) {
//				if(response.rows[0].dense == "1") response.rows[0].dense = "机密";
//				if(response.rows[0].dense == "2") response.rows[0].dense = "绝密";
//				if(response.rows[0].dense == "3") response.rows[0].dense = "秘密";
//				response.rows[0].outStoreNum = response.rows[0].outStoreNum +"/"+ response.rows[0].mediumUnit;
//				if(response.rows[0].postionNum != null ){
//					if(response.rows[0].postionNum <= 9){//如果小于9是1-101,如果大于9是1-110
//						response.rows[0].postionNum = response.rows[0].layerNumber+"-10"+response.rows[0].postionNum;
//					}else{
//						response.rows[0].postionNum = response.rows[0].layerNumber+"-1"+response.rows[0].postionNum;
//					}
//				}
//				comm_loadFormDataList(response.rows[0]);
				opt_all.query_info_show.modal({
					 show 	   : true
					,backdrop  : "static" // 背景遮挡
					,moveable  : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
	            });
//			});
		}
		//加载仓库下拉
		function Load_storage_data(){
//			opt_control.storage_name.empty();
//			var url = opt_all.url_storage;
//			opt_control.storage_name.append("<option value=''>请选择物料名称</option>");
//			common_ajax(null,url, function(data) {
//				$.each(data.rows, function(i) {
//					opt_control.storage_name.append(
//							'<option value=' + data.rows[i].storageInfoName + '>'+ data.rows[i].storageInfoName + '</option>');
//				});
//			});
		}
		function Load_medium_data(){
//			opt_control.medium_name.empty();
//			opt_control.medium_name.append("<option value=''>请选择物料类型</option>");
//			var url = opt_all.url_base;
//			common_ajax(null,url, function(data) {
//				$.each(data.rows, function(i) {
//					opt_control.medium_name.append(
//							"<option value=" + data.rows[i].mediumName + ">"+ data.rows[i].mediumName + "</option>");
//				});
//			});
		}
		
		//上传文件
		this.uploading=function uploading_data(id){
			Load_EditSelectData( 
				function(){ 
					LoadUploadingData(id);
				}
			);
		}
		
		//附件信息
		function LoadUploadingData(id){
			var code=$("#code"+id).val();
			
			//查询已上传文件
//			var url=basePath+'file/upload!queryFileByColumnCode.action?tablePkColumn='+code;
//			common_ajax(null,url, function(response) {
//				//文件上传
//				filesUpload(code,response.staticFiles);
//			});
			opt_all.uploading_data_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
			});
			opt_all.uploading_data_dialog.on('hidden.zui.modal',function(){
				/*if(file_change_state==1){
					window.location.reload();
				}*/
				window.location.reload();
			})
		}
		
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(id,onlyread){
			
//			var url = opt_all.url_edit+"?infoFromBean.mInfo.id=" + id;
			
			// 动态加载页面数据
//			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
//				comm_loadFormData(response.infoBean);
//	            var title="信息维护";
//				if (id==0) {
//					title="<i class='icon icon-plus'></i> 新增设备信息";
//					$("#equipmentGrade").val("");
//				}
//				if (id!=0) title="<i class='icon icon-edit'></i> 修改设备信息";
//				
//				opt_all.info_dialog.find('.modal-title').html(title) ;
				
				///////////////////////////////////////////
				//初次调用/回显 状态
//				if(response.infoBean.equipmentState == 1){
//					$("#startOk").attr("checked",true);
//				}
//				if(response.infoBean.equipmentState == 0){
//					$("#startNo").attr("checked",true);
//				}
				///////////////////////////////////////////
				
				
//				if (onlyread){
//					opt_all.btn_save.hide();
//					
//				} else {
//					opt_all.btn_save.show();
//				}
				
				opt_all.edit_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。

	            });
//			});
		}
		//****绑定事件
		//绑定添加或修改事件
		function event_add(){
			_edit(0);
		}
		
		//绑定刷新事件
		function event_ref(){
			 _refresh();
		}
		
		//****绑定事件******end
	    // 刷新
	    function _refresh(){
	    	opt_all.query_table.bootstrapTable('refresh');
	    }  
		// 重置窗体
		function _reset(){
//			opt_all.info_form.data('bootstrapValidator').resetForm(false);
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
	};
})(jQuery);
			

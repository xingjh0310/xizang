(function($) {
	
	var globle=sessionStorage.getItem("engineeringNm");
	$.System_material = function(option) {
		//option自定义参数覆盖
		//界面控件变量
		var opt_control={
				table			:$("#tbinfo")			//BootStrapTable的ID
			   ,searchInput		:$("#searchInput")		//模糊查询input
			   ,material_type	:$("#material_type")	//物料种类select
			   ,material_name	:$("#material_name")	//物料名称select
			   ,btn_out_page	:$("#btn_out_page")		//导出当前页按钮
			   ,btn_out			:$("#btn_out")			//导出全部按钮
			   ,btn_into		:$("#btn_into")			//导入按钮
			   ,btn_del			:$("#btn_del")			//批量删除按钮
			   ,btn_ref			:$("#btn_ref")			//查询按钮
			   ,info_dialog		:$('#info_dialog')  	// 新增和编辑对应的窗体
			   ,info_form		:$('#transport_form')    // 新增和编辑对应的表单
			   ,details			:$('#material_details') //详情页面
			   ,material_check	:$('#material_check') 	//验收页面
			   ,btn_monitor 	:$('#btn_monitor')		//打开在途监测
			   ,btn_transfer	:$('#btn_transfer') 	//物资交接
			   ,btn_check		:$('#btn_check')		//新增五方验收信息
			   ,check_form		:$('#check_form')		//五方验收窗体
			   ,check_modal		:$('#check_modal')		//验收窗体
			   ,check_module	:$('#check_module')		//表单
			   ,module_save		:$('#check_module_save')//保存信息
			   ,query_form		:$('#query_form')
			   
			  
		}
		
		//请求地址URL
		var opt_url={
			url_list		:basePath+"materiel/materiel!list.action"			//查询数据URL
		   ,url_save		:basePath+"materiel/materiel!save.action"			//保存到货
		   ,url_check		:basePath+"materiel/materiel!save_check.action"		//验收信息
		   ,url_downLoad	:basePath+"materiel/materiel!downLoad.action"		//导出数据URL
		   ,url_transfer	:basePath+"materiel/materiel!transfer.action"		//物资交接URL
		}
		
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
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
				tableParam=params;
				var opt_parms={
					 "materielFormBean.searchName"			:opt_control.searchInput.val(), // 查询关键字
					 "materielFormBean.deliveryInfoBean.engineerCode": globle
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "materielFormBean.pageBean.limit"	: params.limit   // 页面大小
			      ,"materielFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,"materielFormBean.pageBean.sort"		: params.sort  // 排序列名
			      ,"materielFormBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
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
			opt_all.btn_monitor.bind("click",monitor);
			opt_all.btn_transfer.bind("click",transfer);
			opt_all.btn_check.bind("click",check);
			opt_all.btn_out.bind("click",downLoadAllDemand);  //导出
			opt_all.btn_out_page.bind("click",downLoadDemandInPage);
			opt_all.module_save.bind("click",module_save);
			
			
			//初始化主合同编辑表单 保存button类型为submit
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
			opt_all.check_modal.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				module_save();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
			
			opt_all.check_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				check_save();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
			
			opt_all.check_module.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
			
		}
		
		// 修改记录，调出窗体,公开函数
		this.edit=function(id,onlyread) {
			var ids=verdict_del_update(opt_all.table,"id",id);
			if(ids == id || ids == "" || ids == null){
				LoadEditData(id);
			}
			
		}
		//查看物资详情
		function list(row){
			
			comm_loadFormData_flag_html(row,"_list")
			$("#COMPANYSTATE").html("")
			$("#PROJECTSTATE").html("")
			$("#SUPPLIERSTATE").html("")
			$("#SUPERVISORSTATE").html("")
			$("#CONSTRUCTIONSTATE").html("")
			
			if(row.RECEIVESTATE==1){
				$("#arrive_list").show();
			}else{
				$("#arrive_list").hide();
			}
			if(row.ACCEPTANCESTATE!=null){
				$("#check_list").show();
			}else{
				$("#check_list").hide();
			}
			if(row.ACCEPTANCESTATE==1){
				$("#checkbox1").attr("checked",true);
			}
			if(row.ACCEPTANCESTATE==2){
				$("#checkbox2").attr("checked",true);
			}
			if(row.ACCEPTANCESTATE==3){
				$("#checkbox3").attr("checked",true);
			}
			if(row.ACCEPTANCESTATE==4){
				$("#checkbox4").attr("checked",true);
			}
			if(row.COMPANYSTATE==1){
				$("#COMPANYSTATE").html("合格")
			}
			if(row.COMPANYSTATE==2){
				$("#COMPANYSTATE").html("不合格")
			}
			if(row.PROJECTSTATE==1){
				$("#PROJECTSTATE").html("合格")
			}
			if(row.PROJECTSTATE==2){
				$("#PROJECTSTATE").html("不合格")
			}
			if(row.SUPPLIERSTATE==1){
				$("#SUPPLIERSTATE").html("合格")
			}
			if(row.SUPPLIERSTATE==2){
				$("#SUPPLIERSTATE").html("不合格")
			}
			if(row.SUPERVISORSTATE==1){
				$("#SUPERVISORSTATE").html("合格")
			}
			if(row.SUPERVISORSTATE==2){
				$("#SUPERVISORSTATE").html("不合格")
			}
			if(row.CONSTRUCTIONSTATE==1){
				$("#CONSTRUCTIONSTATE").html("合格")
			}
			if(row.CONSTRUCTIONSTATE==2){
				$("#CONSTRUCTIONSTATE").html("不合格")
			}
			filesUpload("arrivelUpload",row.ID,"MATE_RECEIPT");
			
			filesUpload("listUpload",row.ID,"MATE_ACCEPTANCE");
			
			setTimeout(function(){
				$(".btn-delete-file").hide();
				
				opt_all.details.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('hide.zui.modal', function() {
					//_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
					//$(".file-wrapper").remove();
					$(".btn-delete-file").show();
				});
				
			}, 100);
			
			
			opt_all.details.on('hidden.zui.modal',function(){
				$("#tbinfo tbody tr[data-uniqueid="+row.ID+"]").removeClass("success");
			 });
		}
		//获取数据填充窗体
		function LoadEditData(id){
			var url = opt_all.url_transfer+"?materielFormBean.deliveryInfoBean.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
			comm_loadFormDataList(response.infoBean);
			
			opt_all.info_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('hide.zui.modal', function() {
				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
			});
			
			});
		}
		//单条删除
		this.del=function(id){
			var ids=verdict_del_update(opt_all.table,"id",id);
			if(ids == id || ids == "" || ids == null){
				_del(id);
			}
		}
		//验收
		function check(){
			
			opt_all.check_modal.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('hide.zui.modal', function() {
				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				opt_all.check_module.data('bootstrapValidator').resetForm(true);
			});
		}
		
		//交接
		function transfer(){
			 var ids=g_select_and_tip(opt_all.table,"ID");
	         if (ids.length==0) return;
	         LoadEditDataTransfer(ids);
		}
		
		//绑定打开在途监测页面
		function monitor(){
			window.open(basePath+"business/transport/monitor/monitor.jsp");
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
			opt_all.check_form.data('bootstrapValidator').resetForm(true);
			
		}
		this.flash=function(nm){
			globle=nm
			_refresh()
		}
		//保存数据
		function _save(){
			
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		common_ajax(json, opt_all.url_save, function(response){
	 			opt_all.info_dialog.modal("hide");
			    //重置表单
			    _reset();
			    // 刷新列表
			    _refresh();
	 		});
		
		}
		//验货信息
		function module_save(){
			
			var com=$("#company").val()
			var comName=$("#company").find('option:selected').text();
			var time=$("#firmTime").val()
			var peo=$("#people").val()
			var sta=$("#state").val()
			var staName=$("#state").find('option:selected').text();
			
			if(com==""||com==null||time==""||time==null ||peo==""||peo==null||sta==""||sta==null){
				var msg = new $.zui.Messager("消息提示：请填写必填项目", {placement: "center",type:"primary"});
			    msg.show();	
				return
			}
		if(com==0){
			$("#company0").html(comName)
			$("#name0").html(peo)
			$("#date0").html(time)
			$("#state0").html(staName)
			
			$("#companys0").val(comName)
			$("#names0").val(peo)
			$("#dates0").val(time)
			$("#states0").val(sta)
			
		}
		if(com==1){
			$("#company1").html(comName)
			$("#name1").html(peo)
			$("#date1").html(time)
			$("#state1").html(staName)
			
			$("#companys1").val(comName)
			$("#names1").val(peo)
			$("#dates1").val(time)
			$("#states1").val(sta)
		}
		if(com==2){
			$("#company2").html(comName)
			$("#name2").html(peo)
			$("#date2").html(time)
			$("#state2").html(staName)
			
			$("#companys2").val(comName)
			$("#names2").val(peo)
			$("#dates2").val(time)
			$("#states2").val(sta)
		}
		if(com==3){
			$("#company3").html(comName)
			$("#name3").html(peo)
			$("#date3").html(time)
			$("#state3").html(staName)
			
			$("#companys3").val(comName)
			$("#names3").val(peo)
			$("#dates3").val(time)
			$("#states3").val(sta)
		}
		if(com==4){
			$("#company4").html(comName)
			$("#name4").html(peo)
			$("#date4").html(time)
			$("#state4").html(staName)
			
			$("#companys4").val(comName)
			$("#names4").val(peo)
			$("#dates4").val(time)
			$("#states4").val(sta)
		}
		
		opt_all.check_modal.modal("hide");	
		//opt_all.check_module.data('bootstrapValidator').resetForm(false);
		$("#company option:selected").remove();
		_refresh();
		}
		
		this.transfer=function(row){
			
			LoadEditDataTransfer(row)
		}
		this.check=function(row){
			
			LoadEditDataCheck(row)
		}
		//获取数据填充窗体物资交接
		function LoadEditDataTransfer(row,tab){
			$(".btn-delete-file").show();
			filesUpload("transportUpload",row.ID,"MATE_RECEIPT");
			
			$("#ID").val(row.ID)
			$("#ARRPLANNUMS").val(row.ARRPLANNUM)
			comm_loadFormData_flag_html(row,"")
			
			opt_all.info_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
				//_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				//_refresh()
			});
		}
		//验收
		function check_save(){
			
			var comp2=$("#companys2").val();
			var comp3=$("#companys3").val();
			var comp4=$("#companys4").val();
			if(comp2==""|| comp2==null ||comp3==""||comp3==null||comp4==""||comp4==null){
				
				var msg = new $.zui.Messager("消息提示：五方验收必须有施工单位、监理单位、业主单位验收。", {placement: "center",type:"primary"});
			    msg.show();	
			    
				return;
			}
			
			var json=opt_all.check_form.serialize();
	 		common_ajax(json, opt_all.url_check, function(response){
	 			opt_all.material_check.modal("hide");
			    //重置表单
			    _reset();
			    // 刷新列表
			    _refresh();
	 			
	 		});
			
		}
		//五方验收数据窗体
		function LoadEditDataCheck(row){
			$(".btn-delete-file").show();
			
			filesUpload("checkUpload",row.ID,"MATE_ACCEPTANCE");
			$("#ID_check").val(row.ID)
			$("#ACCEPTANCES").val(row.ARRPLANNUM)
			
			comm_loadFormData_flag_html(row,"_check")
			opt_all.material_check.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				
			});
		}
		//文件上传
		function filesUpload(name,id,tab){
			var file_options;
			var url_queryFileUpload=basePath+"file/upload!queryFileUpload.action?tableName="+tab+"&tablePkColumn="+id;
			common_ajax_async(null,url_queryFileUpload,function(response){
	 			file_options = {
	 					//初始化参数
	 					url:basePath+"file/upload!saveFileUpload.action?tableName="+tab+"&tablePkColumn="+id+"&engineerCode="+sessionStorage.getItem("engineeringNm")	//文件上传地址
	 					,filters:{//文件过滤器
	 						// 最大上传文件为 10MB
	 						 mime_types: [
	 					        {title: '图片', extensions: 'jpg,gif,png'},
	 					        {title: '文件', extensions: 'doc,docx' }
	 					    ],
	 						max_file_size: '10mb',
	 						prevent_duplicates: true
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
			 				}
			 			}
			 			,staticFiles:response.mFileUploadList//查询已上传文件
			 			,deleteActionOnDone: function(file, doRemoveFile) {//删除上传文件
			 		          doRemoveFile();
			 		          deleteFileById(file.id,id);
			 		      }
	 			}
	 			$('#'+name).uploader(file_options);
	 		},true);
			
		}
		//删除文件
		function deleteFileById(fileId,id){
			var url=basePath+"file/upload!deleteFileById.action?fileId="+fileId;
			common_ajax(null,url, function(response) {
				if(response.retflag=="success"){
				}else{
					var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
				    msg.show();	
				}
			})
		}
		//导出全部需求清单
		function downLoadAllDemand(){
			var url= opt_all.url_downLoad+"?";
			var data="materielFormBean.pageBean.limit="+g_treelist_size
					+"&materielFormBean.pageBean.offset=0" 
					+"&materielFormBean.pageBean.sortOrder="+tableParam.order
					+"&materielFormBean.pageBean.sort="+tableParam.sort
					+"&materielFormBean.searchName="+opt_all.searchInput.val();
			
			confirm("<i class='icon icon-reply'></i>&nbsp;物资状态","您确定要导出全部信息吗？","icon-info", function(result) {
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
				var data_page="materielFormBean.pageBean.limit="+tableParam.limit
							 +"&materielFormBean.pageBean.offset="+tableParam.offset
							 +"&materielFormBean.pageBean.sortOrder="+tableParam.order
							 +"&materielFormBean.pageBean.sort="+tableParam.sort
							 +"&materielFormBean.searchName"+opt_all.searchInput.val();
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;物资状态","您确定要导出当前页中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data_page;
					   }
				});
			}else{
				for (var i = 0; i < bt_ids.length; i++) {
					ids = ids + "," + bt_ids[i]["ID"];
				}
				ids = ids.substring(1);
				var data="materielFormBean.ids="+ids
						+"&materielFormBean.pageBean.limit="+g_treelist_size
						+"&materielFormBean.pageBean.offset=0" 
						+"&materielFormBean.pageBean.sort=ID" 
						+"&materielFormBean.pageBean.sortOrder=DESC";
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;物资状态","您确定要导出选中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data;
					   }
				});
			}
		}
		
		
		// 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"id");
           if (ids.length==0) return;
           
           //var url= opt_all.url_remove+"?formBean.ids="+ids
            
           confirm("甲供物资采购计划","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
			   if(result){
//			 		common_ajax(null, url, function(response){
//						// 删除后，从后台取出返回数据
//					    _refresh();
//					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
//					    msg.show();	
//						    
//			 		}); 
               }
			});    
		   
		}
		
		//删除单条记录
		function _del(id){
			//var url= opt_all.url_remove+"?euipmentTypeFromBean.ids="+id
            
			confirm("甲供物资采购计划","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
			   if(result){
//			 		common_ajax(null, url, function(response){
//						// 删除后，从后台取出返回数据
//					    _refresh();
//					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
//					    msg.show();	
//						    
//			 		}); 
               }
			}); 
		}
		
	}
})(jQuery);
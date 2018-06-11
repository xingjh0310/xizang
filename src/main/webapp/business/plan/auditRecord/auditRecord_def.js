(function($) {
	$.System_arrival = function(option) {
		//option自定义参数覆盖
		//界面控件变量
		var opt_control={
				table			:$("#tbinfo")	//BootStrapTable的ID
			   ,searchInput		:$("#searchInput")	//模糊查询input
			   ,material_type	:$("#material_type")	//物料种类select
			   ,material_name	:$("#material_name")	//物料名称select
			   ,btn_reported	:$("#btn_reported")	//批量上报按钮
			   ,btn_add			:$("#btn_add")	//新增按钮
			   ,btn_del			:$("#btn_del")	//批量删除按钮
			   ,btn_ref			:$("#btn_ref")	//查询按钮
			   ,info_dialog		:$('#info_dialog')  // 新增和编辑对应的窗体
			   ,info_form		:$('#info_form')    // 新增和编辑对应的表单
			   ,info_list		:$("#info_list")	// 详情信息
			   ,time_up_edit	:$("#time_up_edit")	//更新时间模态框id
			   ,btn_up_time_save:$("#btn_up_time_save")	//时间更改保存按钮
			   ,info_upload		:$("#info_upload")	//文件上传模态框
			   ,SUPPLIERPLANCODE:$("#SUPPLIERPLANCODE")	//供应计划--select
			   ,btn_audit_ok	:$("#btn_audit_ok")	//审核通过
			   ,btn_audit_no	:$("#btn_audit_no")	//审核拒绝
			   ,btn_moreAudit_ok:$("#btn_moreAudit_ok")//批量通过
			   ,btn_moreAudit_no:$("#btn_moreAudit_no")//批量拒绝
			   ,btn_goBack		:$("#btn_goBack")//返回审核记录
		}
		
		//请求地址URL
		var opt_url={
			url_list		:basePath+"plan/arrival!queryAllArrivalInfo.action"//查询数据URL
		   ,url_remove		:basePath+"plan/arrival!removeids.action"//删除数据URL
		   ,url_save		:basePath+"plan/arrival!saveArrival.action"//保存数据URL
		   ,url_reported	:basePath+"plan/arrival!reported.action"//上报URL
		   ,url_up_time		:basePath+"plan/supply!updateTime.action"//更新时间URL
		}
		
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
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
				//查询条件
				var opt_parms={
					 "fArrivalFormBean.searchName"				:opt_control.searchInput.val() // 查询关键字
					,"fArrivalFormBean.materielType"			: $("#material_type").val()	//物资类型
					,"fArrivalFormBean.materielBase"			: $("#material_name").val()	//物资名称
					,"fArrivalFormBean.mArrival.engineerCode"	: sessionStorage.getItem("engineeringNm")
					,"fArrivalFormBean.mArrival.planState"		:1
					,"fArrivalFormBean.auditFlag"		:"1,2"
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "fArrivalFormBean.pageBean.limit"	: params.limit   // 页面大小
			      ,"fArrivalFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,"fArrivalFormBean.pageBean.sort"		: params.sort  // 排序列名
			      ,"fArrivalFormBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
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
			opt_all.btn_reported.bind("click",event_reported);
			opt_all.btn_add.bind("click",event_add);
			opt_all.searchInput.bind("keydown",carriage);
			opt_all.SUPPLIERPLANCODE.bind("change",change_SUPPLIERPLANCODE);
			opt_all.btn_audit_ok.bind("click",auditOk);
			opt_all.btn_audit_no.bind("click",auditNo);
			opt_all.btn_moreAudit_ok.bind("click",moreAuditOk);
			opt_all.btn_moreAudit_no.bind("click",moreAuditNo);
			opt_all.btn_goBack.bind("click",skipGoBack)
			//opt_all.btn_up_time_save.bind("click",update_time);
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// //校验数据正确
			});	
			$("#time_up_edit_form").bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				update_time()
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// //校验数据正确
			});	
		}
		
		//跳转到审核记录页面
		function skipGoBack(){
			window.location.href = basePath+"business/plan/audit/audit.jsp";
		}
		
		//回车事件
		function carriage(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
		     if(e && e.keyCode==13){ // enter 键
		         //要做的事情
		    	 event_ref();
		    }
		}
		
		// 新增/修改记录，调出窗体,公开函数
		this.edit=function(id,onlyread) {
			var ids=verdict_del_update(opt_all.table,"ID",id);
			if(ids == id || ids == "" || ids == null){
				LoadEditData(id);
			}
			
		}
		
		// 新增/修改窗体
		function LoadEditData(id){
			var url=opt_all.url_list;
			var data={
				   "fArrivalFormBean.pageBean.limit"		: g_treelist_size   // 页面大小
			      ,"fArrivalFormBean.pageBean.offset"		: 0  // 当前记录偏移条数
			      ,"fArrivalFormBean.pageBean.sort"			: "ID"  // 排序列名
			      ,"fArrivalFormBean.pageBean.sortOrder"	: "DESC"// 排位命令（desc，asc）
			      ,"fArrivalFormBean.mArrival.id"			: id
			}
			// 动态加载页面数据
			common_ajax(data,url, function(response) {
				// 获取到数据，显示在界面上
				comm_loadFormData(response.rows[0]);
				comm_loadFormData_flag_html(response.rows[0],'_audit');
				opt_all.info_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
					
				});
			})
		}
		
		//单条删除
		this.del=function(id){
			var ids=verdict_del_update(opt_all.table,"ID",id);
			if(ids == id || ids == "" || ids == null){
				_del(id);
			}
		}
		
		//上报
		this.up_reported=function(id){
			var ids=verdict_del_update(opt_all.table,"ID",id);
			if(ids == id || ids == "" || ids == null){
				_up_reporteds(id);
			}
		}
		
		//绑定新增事件
		function event_add(){
			LoadEditData(0);
		}
		
		//绑定刷新事件
		function event_ref(){
			 _refresh();
		}
		
		//绑定删除事件
		function event_del(){
			_removeids();
		}
		
		//切换工程刷新事件
		this.flash=function(){
			_refresh();
		}
		
		//绑定批量上报时间
		function event_reported(){
			_reporteds();
		}
		
		// 刷新
	    function _refresh(){
	    	opt_all.table.bootstrapTable('refresh');
	    }
	    
	    // 重置窗体
		function _reset(){
			opt_all.info_form.data('bootstrapValidator').resetForm(false);
		}
		
		//审核通过
		function auditOk(){
			arrivalAudit('ok');
		}
		
		//审核拒绝
		function auditNo(){
			arrivalAudit('no');
		}
		
		//单个审核
		function arrivalAudit(flag){
			var data={
					"fArrivalFormBean.ids"				:$("#ID").val()
				   ,"fArrivalFormBean.mArrival.auditOpinion"	:$("#AUDITOPINION").val()
				}
			if(flag=="ok"){
				data["fArrivalFormBean.mArrival.auditState"]=1
			}else if(flag=="no"){
				data["fArrivalFormBean.mArrival.auditState"]=2
				/*if($("#AUDITOPINION").val()==""){
					var msg = new $.zui.Messager("消息提示：请填写拒绝理由", {placement: "center",type:"primary"});
				    msg.show();	
					return;
				}*/
			}
			var url=basePath+"plan/arrival!saveAuditInfo.action";
			common_ajax(data, url, function(response){
				$("#info_dialog").modal("hide");
				$("#tbinfo").bootstrapTable('refresh');
				var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
			    msg.show();
			});
		}
		
		//批量通过
		function moreAuditOk(){
			var ids=g_select_and_tip(opt_all.table,"ID");
	        if (ids.length==0) return;
	        moreArrivalAudit(ids,'ok');
		}
		
		//批量拒绝
		function moreAuditNo(){
			var ids=g_select_and_tip(opt_all.table,"ID");
	        if (ids.length==0) return;
	        moreArrivalAudit(ids,'no');
		}
		
		//批量审核
		function moreArrivalAudit(ids,flag){
			var html="";
			var icon="";
			var data={
					"fArrivalFormBean.ids":ids
				   ,"fArrivalFormBean.mArrival.auditOpinion":""
				}
			if(flag=="ok"){
				data["fArrivalFormBean.mArrival.auditState"]=1;
				html="您确定要通过这些计划吗？";
				icon="<i class='icon icon-check-circle'></i>&nbsp;到货计划审核";
			}else if(flag=="no"){
				data["fArrivalFormBean.mArrival.auditState"]=2;
				html="您确定要拒绝这些计划吗？";
				icon="<i class='icon icon-times'></i>&nbsp;到货计划审核";
			}
			var url=basePath+"plan/arrival!saveAuditInfo.action";
			confirm(icon,html,"icon-info", function(result) {
				if(result){
					common_ajax(data, url, function(response){
						_refresh();
					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
					    msg.show();	
					})
				}
			})
		}
		
		//批量上报
		function _reporteds(){
		   // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"ID");
           if (ids.length==0) return;
           var url= opt_all.url_reported+"?fArrivalFormBean.ids="+ids
           confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;到货计划","您确定要上报这些计划吗？","icon-info", function(result) {
        	   if(result){
			 		common_ajax(null, url, function(response){
					    _refresh();
					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
					    msg.show();	
						    
			 		}); 
              }
           })
		}
		
		// 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"ID");
           if (ids.length==0) return;
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");
           var url= opt_all.url_remove+"?fArrivalFormBean.ids="+ids
           confirm("到货计划","您确定要删除这些记录吗？","icon-remove-sign", function(result) {
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
		
		//上报单条记录
		function _up_reporteds(id){
			var url= opt_all.url_reported+"?fArrivalFormBean.ids="+id
			confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;到货计划","您确定要上报该计划吗？","icon-info", function(result) {
	        	   if(result){
				 		common_ajax(null, url, function(response){
						    _refresh();
						    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
						    msg.show();	
							    
				 		}); 
	              }
	           })
		}
		
		//删除单条记录
		function _del(id){
			$(".table tbody tr[data-uniqueid="+id+"] td").addClass("row-bcground");
			var url= opt_all.url_remove+"?fArrivalFormBean.ids="+id
			confirm("到货计划","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
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
			opt_all.info_dialog.modal("hide");
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		common_ajax(json, opt_all.url_save, function(response){
	 			opt_all.info_dialog.modal("hide");
	 			_refresh();			    
	 		}); 	
		}
		
		//详情模态框
		function list(row){
			comm_loadFormData_flag_html(row,'_arrival');
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
		
		//更新时间模态框
		this.up_time=function (id){
			var ids=verdict_del_update(opt_all.table,"ID",id);
			if(ids == id || ids == "" || ids == null){
				var url=opt_all.url_list;
				var data={
						"fArrivalFormBean.pageBean.limit"		: g_treelist_size   // 页面大小
						,"fArrivalFormBean.pageBean.offset"		: 0  // 当前记录偏移条数
						,"fArrivalFormBean.pageBean.sort"		: "ID"  // 排序列名
						,"fArrivalFormBean.pageBean.sortOrder"	: "DESC"// 排位命令（desc，asc）
						,"fArrivalFormBean.mArrival.id"			: id
				}
				// 动态加载页面数据
				common_ajax(data,url, function(response) {
					// 获取到数据，显示在界面上
					comm_loadFormData_flag_html(response.rows[0],'_up_time');
					$("#supply_play_code").val(response.rows[0].SUPPLIERPLANCODE);
					$("#DRAWECONFIRMTIME_up_time").val(response.rows[0].DRAWECONFIRMTIME)
					$("#SIGNTIME_up_time").val(response.rows[0].SIGNTIME)
					opt_all.time_up_edit.modal({
						show : true
						,backdrop : "static" // 背景遮挡
							,moveable : true
					}).on('shown.zui.modal', function() {
						_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
					});
				});
			}
		}
		
		//更改时间
		function update_time(){
			var json=$("#time_up_edit_form").serialize();
	 		common_ajax(json, opt_all.url_up_time, function(response){
	 			opt_all.time_up_edit.modal("hide");
	 			var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
			    msg.show();	
	 		}); 	
		}
		
		//点击查看上传文件
		this.upload_file=function(id){
			filesUpload(id);
			upload_model_show();
		}
		
		//文件上传模态框
		function upload_model_show(file_options){
			opt_all.info_upload.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
	
	        });
			opt_all.info_upload.on('hidden.zui.modal',function(){
				window.location.reload();
			});
		}
		
		//文件上传
		function filesUpload(id){
			var file_options;
			var url_queryFileUpload=basePath+"file/upload!queryFileUpload.action?tableName=plan_tocargo&tablePkColumn="+id;
			common_ajax_async(null,url_queryFileUpload,function(response){
	 			file_options = {
	 					//初始化参数
	 					 url:basePath+"file/upload!saveFileUpload.action?tableName=plan_tocargo&tablePkColumn="+id+"&engineerCode="+sessionStorage.getItem("engineeringNm")	//文件上传地址
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
			 					var nub=$("#fileNub_id"+id).html();
								$("#fileNub_id"+id).html(Number(nub)+1);
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
		
	//供应计划编号--change
	function change_SUPPLIERPLANCODE(){
		var supplierPlanCode=$("#SUPPLIERPLANCODE").val();
		if(supplierPlanCode==""){
			$("#SUPPLIERNAME").val("");//供应商名称
			$("#SUPPLIERCODE").val("");//供应商code
			$("#CONTRACTNO").val("");//合同编号
			$("#CONTRACTNAME").val("");//合同标题
			$("#CONTRACTNUM").val("");//合同数量
			$("#MATERIELCODE").val("");//物料编号
			$("#MATERIELNAME").val("");//物料名称
			$("#MEAUNIT").val("");//计量单位
			$("#PLANDELIVERIE").val("");//计划交货数量
			$("#DEMANDUNIT").val("");//需求单位
			$("#TREENMSYSDEPT").val("");//项目单位code
			$("#CONTRACTDELIVERYDATE").val("");//合同交货期
			$("#MATERIELDESC").val("");//物资描述
			$("#ENGINEERCODE").val("");//项目code
			$("#DEPTNAME").val("");//项目单位
			$("#MATERIALNORMS").val("");//物料规格
			return;
		}
		var supplyInfo=$.parseJSON($("#supply_"+supplierPlanCode).val());
		$("#SUPPLIERNAME").val(supplyInfo.SUPPLYFULLNAME);//供应商名称
		$("#SUPPLIERCODE").val(supplyInfo.SUPPLIERCODE);//供应商code
		$("#CONTRACTNO").val(supplyInfo.CONTRACTNO);//合同编号
		$("#CONTRACTNAME").val(supplyInfo.CONTTITLE);//合同标题
		$("#CONTRACTNUM").val(supplyInfo.CONTRACTNUM);//合同数量
		$("#MATERIELCODE").val(supplyInfo.MATERIELCODE);//物料编号
		$("#MATERIELNAME").val(supplyInfo.MATERIELNAME);//物料名称
		$("#MEAUNIT").val(supplyInfo.MEAUNIT);//计量单位
		$("#PLANDELIVERIE").val(supplyInfo.PLANDELIVERIE);//计划交货数量
		$("#DEMANDUNIT").val(supplyInfo.DEMANDUNIT);//需求单位
		$("#TREENMSYSDEPT").val(supplyInfo.TREENMSYSDEPT);//项目单位code
		$("#CONTRACTDELIVERYDATE").val(supplyInfo.SUPPLYENDDATE);//合同交货期
		$("#MATERIELDESC").val(supplyInfo.MATERIALDESC);//物资描述
		$("#ENGINEERCODE").val(supplyInfo.ENGINEERCODE);//项目code
		$("#DEPTNAME").val(supplyInfo.DEPTNAME);//项目单位
		$("#MATERIALNORMS").val(supplyInfo.MATERIALNORMS);//物料规格
	}
		
	}
})(jQuery);
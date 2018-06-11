(function($) {
	$.System_supply = function(option) {
		//option自定义参数覆盖
		//界面控件变量
		var opt_control={
				table			:$("#tbinfo")	//BootStrapTable的ID
			   ,searchInput		:$("#searchInput")	//模糊查询input
			   ,material_type	:$("#material_type")	//物料种类select
			   ,material_name	:$("#material_name")	//物料名称select
			   ,btn_reported	:$("#btn_reported")	//更新中标日期按钮
			   ,btn_add			:$("#btn_add")	//新增按钮
			   ,btn_del			:$("#btn_del")	//批量删除按钮
			   ,btn_ref			:$("#btn_ref")	//查询按钮
			   ,info_dialog		:$('#info_dialog')  // 新增和编辑对应的窗体
			   ,info_form		:$('#info_form')    // 新增和编辑对应的表单
			   ,btn_into		:$("#btn_into")	//导入按钮
			   ,btn_out			:$("#btn_out")	//导出按钮
			   ,btn_out_page	:$("#btn_out_page")	//导出当前页
			   ,info_list		:$("#info_list")	// 详情信息
			   ,info_upload		:$("#info_upload")	//文件上传模态框
			   ,time_up_edit	:$("#time_up_edit")	//更新时间模态框id
			   ,CONTRACTNO		:$("#CONTRACTNO")	//合同编号--select
			   ,MATERIELCODE	:$("#MATERIELCODE")	//物料名称--select
		}
		
		//请求地址URL
		var opt_url={
			url_list		:basePath+"plan/supply!queryAllSupplyInfo.action"//查询数据URL
		   ,url_remove		:basePath+"plan/supply!removeids.action"//删除数据URL
		   ,url_save		:basePath+"plan/supply!saveSupply.action"//保存数据URL
		   ,url_up_time		:basePath+"plan/supply!updateBiddingDate.action"//更新时间URL
		   ,url_downLoad	:basePath+"plan/supply!downLoadSupplyInfo.action"//导出数据URL
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
					 "fSupplyFormBean.searchName"		:opt_control.searchInput.val() // 查询关键字
					,"fSupplyFormBean.materielType"		: $("#material_type").val()	//物资类型
					,"fSupplyFormBean.materielBase"		: $("#material_name").val()	//物资名称
					,"fSupplyFormBean.mSupply.engineerCode"	: sessionStorage.getItem("engineeringNm")
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "fSupplyFormBean.pageBean.limit"		: params.limit   // 页面大小
			      ,"fSupplyFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,"fSupplyFormBean.pageBean.sort"		: params.sort  // 排序列名
			      ,"fSupplyFormBean.pageBean.sortOrder"	: params.order// 排位命令（desc，asc）
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
			opt_all.btn_out.bind("click",downLoadAllDemand);
			opt_all.btn_out_page.bind("click",downLoadDemandInPage);
			opt_all.CONTRACTNO.bind("change",change_CONTRACTNO);
			opt_all.MATERIELCODE.bind("change",change_MATERIELCODE);
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
			$("#time_up_edit_form").bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				update_time()
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
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
		
		// 新增/修改记录，调出窗体,公开函数
		this.edit=function(id,onlyread) {
			var ids=verdict_del_update(opt_all.table,"ID",id);
			if(ids == id || ids == "" || ids == null){
				LoadEditData(id);
			}
			
		}
		
		//状态
		this.operState=function(id){
			operState(id)
			
		}
		function operState(id){
			alert(id)
		}
		
		// 新增/修改窗体
		function LoadEditData(id){
			var url=opt_all.url_list;
			var data={
				   "fSupplyFormBean.pageBean.limit"		: g_treelist_size   // 页面大小
			      ,"fSupplyFormBean.pageBean.offset"	: 0  // 当前记录偏移条数
			      ,"fSupplyFormBean.pageBean.sort"		: "ID"  // 排序列名
			      ,"fSupplyFormBean.pageBean.sortOrder"	: "DESC"// 排位命令（desc，asc）
			      ,"fSupplyFormBean.mSupply.id"			: id
			}
			// 动态加载页面数据
			common_ajax(data,url, function(response) {
				var title="";
				var engineerNm="";
				if (id==0) {
					title="<i class='icon icon-plus-sign'></i> 新增供货计划信息";
					$("input[type='text' ]").val("");
					$("input[type='number' ]").val("");
					$("#CONTRACTNO").val("");
					$("textarea").val("");
					$("#ID").val(0);
					$("#radio1").prop("checked",true);
					$("#radio2").prop("checked",false);
					$("#MATERIELCODE").empty();
					$("#MATERIELCODE").append("<option value=''>请选择物料</option>");
					engineerNm=response.mSysDept.nm;
					$("#DEPTNAME").val(response.mSysDept.name)
					$("#TREENMSYSDEPT").val(engineerNm)
				}
				if (id!=0) {
					select_MATERIEL(response.rows[0].CONTRACTNO)
					title="<i class='icon icon-pencil'></i> 修改供货计划信息";
					if(response.rows[0].PLANSTATE==1){
						$("#radio1").prop("checked",true);
						$("#radio2").prop("checked",false);
					}else if(response.rows[0].PLANSTATE==2){
						$("#radio1").prop("checked",false);
						$("#radio2").prop("checked",true);
					}
					$("#MATERIEL_CODE").val(response.rows[0].MATERIELCODE);
					engineerNm=response.rows[0].TREENMSYSDEPT;
				}
				//查询项目单位
				queryUpDetp(engineerNm);
				// 获取到数据，显示在界面上
				comm_loadFormData(response.rows[0]);
				opt_all.info_dialog.find('.modal-title').html(title) ;
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
		
		//更新中标
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
		
		//切换工程刷新事件
		this.flash=function(){
			_refresh();
		}
		
		//绑定删除事件
		function event_del(){
			_removeids();
		}
		
		//绑定更新中标日期
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
		
		//更新中标日期
		function _reporteds(){
		   // 获取选中的ids
           var ids=g_select_and_tip(opt_all.table,"ID");
           if (ids.length==0) return;
           var msg = new $.zui.Messager("消息提示：更新中标日期", {placement: "center",type:"primary"});
		   msg.show();	
		}
		
		// 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"ID");
           if (ids.length==0) return;
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");
           var url= opt_all.url_remove+"?fSupplyFormBean.ids="+ids
            
           confirm("供货计划","您确定要删除这些记录吗？","icon-remove-sign", function(result) {
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
			var url= opt_all.url_remove+"?fSupplyFormBean.ids="+id
			confirm("供货计划","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
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
	 		common_ajax_async(json,opt_all.url_save,function(response){
	 			opt_all.info_dialog.modal("hide");	
	 			_refresh();
	 		},true);
		}
		
		//详情模态框
		function list(row){
			comm_loadFormData_flag_html(row,'_supply');
			var state=row.PLANSTATE;
			if(state==1){
				$("#PLAY_STATE_supply").html("未执行");
			}else if(state==2){
				$("#PLAY_STATE_supply").html("已执行");
			}
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
			var url_queryFileUpload=basePath+"file/upload!queryFileUpload.action?tableName=supply_plan&tablePkColumn="+id;
			common_ajax_async(null,url_queryFileUpload,function(response){
	 			file_options = {
	 					//初始化参数
	 					url:basePath+"file/upload!saveFileUpload.action?tableName=supply_plan&tablePkColumn="+id+"&engineerCode="+sessionStorage.getItem("engineeringNm")	//文件上传地址
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
		
		//更新时间模态框
		function _up_reporteds(id){
			var url=opt_all.url_list;
			var data={
				   "fSupplyFormBean.pageBean.limit"		: g_treelist_size   // 页面大小
			      ,"fSupplyFormBean.pageBean.offset"	: 0  // 当前记录偏移条数
			      ,"fSupplyFormBean.pageBean.sort"		: "ID"  // 排序列名
			      ,"fSupplyFormBean.pageBean.sortOrder"	: "DESC"// 排位命令（desc，asc）
			      ,"fSupplyFormBean.mSupply.id"			: id
			}
			// 动态加载页面数据
			common_ajax(data,url, function(response) {
				// 获取到数据，显示在界面上
				comm_loadFormData_flag_html(response.rows[0],'_up_time');
				$("#id_up_time").val(response.rows[0].ID);
				$("#BIDDINGDATE_up_time").val(response.rows[0].BIDDINGDATE);
				opt_all.time_up_edit.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				});
			});
		}
		
		//更改时间
		function update_time(){
			var json=$("#time_up_edit_form").serialize();
	 		common_ajax(json, opt_all.url_up_time, function(response){
	 			opt_all.time_up_edit.modal("hide");
	 			var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
			    msg.show();	
			    _refresh();
	 		}); 	
		}
		
		//导出全部供应计划
		function downLoadAllDemand(){
			var url= opt_all.url_downLoad+"?";
			var data="fSupplyFormBean.pageBean.limit="+g_treelist_size
					+"&fSupplyFormBean.pageBean.offset=0" 
					+"&fSupplyFormBean.pageBean.sortOrder="+TableQueryParams.order
					+"&fSupplyFormBean.pageBean.sort="+TableQueryParams.sort
					+"&fSupplyFormBean.searchName="+opt_all.searchInput.val()
					+"&fSupplyFormBean.mSupply.engineerCode="+sessionStorage.getItem("engineeringNm");
			confirm("<i class='icon icon-reply'></i>&nbsp;供货计划","您确定要导出全部信息吗？","icon-info", function(result) {
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
				var data_page="fSupplyFormBean.pageBean.limit="+TableQueryParams.limit
							 +"&fSupplyFormBean.pageBean.offset="+TableQueryParams.offset
							 +"&fSupplyFormBean.pageBean.sortOrder="+TableQueryParams.order
							 +"&fSupplyFormBean.pageBean.sort="+TableQueryParams.sort
							 +"&fSupplyFormBean.searchName="+opt_all.searchInput.val()
							 +"&fSupplyFormBean.mSupply.engineerCode="+sessionStorage.getItem("engineeringNm");
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;供货计划","您确定要导出当前页中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data_page;
					   }
				});
			}else{
				for (var i = 0; i < bt_ids.length; i++) {
					ids = ids + "," + bt_ids[i]["ID"];
				}
				ids = ids.substring(1);
				var data="fSupplyFormBean.ids="+ids
						+"&fSupplyFormBean.pageBean.limit="+g_treelist_size
						+"&fSupplyFormBean.pageBean.offset=0" 
						+"&fSupplyFormBean.pageBean.sort=ID" 
						+"&fSupplyFormBean.pageBean.sortOrder=DESC";
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;供货计划","您确定要导出选中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data;
					   }
				});
			}
		}
		
		//合同编号select--change
		function change_CONTRACTNO(){
			var contractNo=opt_all.CONTRACTNO.val();
			if(contractNo==""){
				  $("#CONTTITLE").val("");//合同标题
				  $("#SUPPLYFULLNAME").val("");//供应商
				  $("#SUPPLIERCODE").val("");//供应商code
				  $("#ENGINEERCODE").val("");//工程code
				  $("#ENGINEERNAME").val("");//工程名称
				  $("#CONTRACTDELIVERYDATE").val("");//合同交货期
				  $("#MATERIELCODE").empty();
				  $("#MATERIELCODE").append("<option value=''>请选择物料</option>");
				  return;
			}
			select_MATERIEL(contractNo)
		}
		
	//物料名称--select
	function select_MATERIEL(contractNo){
		var url=basePath+"contInfo/contInfo!getContAndMaterialByContNo.action";
		var data = {"contInfoFormBean.contInfoBean.contractNo":contractNo};
		common_ajax_noasync(data, url, function(response){
			  var contract=response.contract[0];//合同信息
			  $("#CONTTITLE").val(contract.CONTRACTTITLE);//合同标题
			  $("#SUPPLYFULLNAME").val(contract.SUPPLYFULLNAME);//供应商
			  $("#SUPPLIERCODE").val(contract.SUPPLY);//供应商code
			  $("#ENGINEERCODE").val(contract.ENGINEERCODE);//工程code
			  $("#ENGINEERNAME").val(contract.ENGINEERNAME);//工程名称
			  $("#CONTRACTDELIVERYDATE").val(contract.SUPPLYSTARTDATE);//合同交货期
			  var materialsArray=response.materials;//物料--Array
			  $("#MATERIELCODE").empty();
			  $("#materialInfo_div").empty();
			  var html="<option value=''>请选择物料</option>";
			  var html_material="";
			  for(i=0;i<materialsArray.length;i++){
				  html+="<option value='"+materialsArray[i].MATERIELCODE+"'>"+materialsArray[i].MATERIELNAME+"</option>";
				  html_material+="<input type='text' class='form-control' id='id_"+materialsArray[i].MATERIELCODE+"' value='"+JSON.stringify(materialsArray[i])+"'>";
			  }
			  $("#MATERIELCODE").append(html);
			  $("#materialInfo_div").append(html_material);
			});
	}
		
	//物料名称--select--change
	function change_MATERIELCODE(){
		var materielCode=opt_all.MATERIELCODE.val();
		if(materielCode==""){
			$("#MATERIALNORMS").val("");//物料编码
			$("#MEAUNIT").val("");//计量单位
			$("#PLANDELIVERIE").val("");//计划交货数量
			$("#MATERIELNAME").val("");//物料名称
			$("#MATERIALDESC").val("");//物料描述
			return;
		}
		var materiel=$.parseJSON($("#id_"+materielCode).val());
		$("#MATERIALNORMS").val(materiel.MATERIALNORMS);//物料编码
		$("#MEAUNIT").val(materiel.MATERIELUNIT);//计量单位
		$("#PLANDELIVERIE").val(materiel.MATERIELNUM);//计划交货数量
		$("#MATERIELNAME").val(materiel.MATERIELNAME);//物料名称
		$("#MATERIALDESC").val(materiel.GOODDESC);//物料描述
	}
		
	}
})(jQuery);
(function($) {
	$.System_SysDept = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 btn_add		:$('#btn_add')		//增加按钮
			,btn_del     	:$('#btn_del')		//删除按钮	
			,btn_ref     	:$('#btn_ref')		//刷新按钮
			,searchInput	:$('#searchName')	//模糊查询input
			,info_dialog	:$('#info_dialog')  // 新增和编辑对应的窗体，注意和info_form的区别
			,info_form		:$('#info_form')    // 新增和编辑对应的表单，注意和info_dialog的区别
			,treeGrid 		:$('#treeGrid')		//BootStrapTreeList的ID--
			,select_pcode	:$('#select_pcode') //选中根节点--
			,btn_basedata	:$('#btn_basedata') //刷新根节点下拉框数据按钮
			,btn_save		:$('#btn_save')		//保存按钮
			,import_dialog  :$('#import_dialog') //导入对话框
			,import_deptInfo:$('#import_deptInfo') //导入部门信息
			,export_whole_dept:$('#export_whole_dept')//导出全部数据
			,synchroDept	:$('#synchroDept')//同步通讯录
			,type_dept	:$('#type_dept')//单位类型
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"system/sysdept!list.action"			//查询数据URL
			,url_add		:basePath+"system/sysdept!add.action"			//新增子数据URL--
			,url_edit		:basePath+"system/sysdept!edit.action"			//编辑数据URL
			,url_save		:basePath+"system/sysdept!save.action"			//保存数据URL
			,url_remove		:basePath+"system/sysdept!removeids.action"		//删除数据URL
			,url_listroot   :basePath+"system/sysdept!listroot.action"		//获取根数据URL--
			,url_set		:basePath+"system/sysdept!set.action"
			,url_export_dept:basePath+"system/sysdept!exportDeptData.action" //导出部门信息
			,url_synchroDept:basePath+"system/sysdept!synchroDeptData.action"//同步通讯录
			,url_type	:basePath+"system/sysdept!queryType.action"
		}

		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		// 动态加载页面基础数据：下拉框数据
		function _LoadBaseData(){
			//加载根单位下拉框数据
			common_ajax(null,opt_all.url_listroot, function(response) {
				opt_all.select_pcode.empty();
				opt_all.select_pcode.append($("<option>").text("显示全部数据").val(""));
				for (var i = 0 ; i< response.rows.length;i++){
					var option = $("<option>").text("("+response.rows[i].CODE+")"+response.rows[i].NAME).val(response.rows[i].CODE);
					opt_all.select_pcode.append(option);
				}
				comm_chose_init(opt_all.select_pcode);
			});
			//加载单位类型下拉框数据
			common_ajax(null,opt_all.url_type, function(response) {
				opt_all.type_dept.empty();
				opt_all.type_dept.append($("<option>").text("请选择单位类型").val(""));
				for (var i = 0 ; i< response.rows.length;i++){
					var option = $("<option>").text(response.rows[i].NAME).val(response.rows[i].CODE);
					opt_all.type_dept.append(option);
				}
			});
		}
		
	    //加载treegrid数据
		function _LoadData(opt){
		    var temp = {   
				  "formBean.pageBean.limit": 100000000   // 页面大小
				 ,"formBean.pageBean.offset": 0  // 当前记录偏移条数	
		 	      // 在此增加查询条件
				 ,"formBean.infoBean.code":opt_all.select_pcode.val()
				 ,"formBean.infoBean.flag"	:""
		 	    };
		    
		    temp=$.extend(temp,opt);
			common_ajax(temp,opt_all.url_list, function(response) {
				var html=[];
				opt_all.treeGrid.html("");
				/**表头*/
			html.push("<thead><tr>");
			html.push("<th>单位名称</th>");
			html.push("<th>单位类型</th>");
		    html.push("<th width='100'>操作</th>");
			html.push("</tr></thead>");
			/**内容*/
			var rows=response.rows;
			console.log(JSON.stringify(rows))
			for (var i = 0 ; i< rows.length;i++){
				var TYPE=rows[i].TYPE==null?"":rows[i].TYPE;//单位类型
				html.push(" <tr class='treegrid-"+rows[i].NM);
				if (rows[i].PNM.length>0){
					html.push(" treegrid-parent-"+rows[i].PNM);
				}
				html.push("' >");
				html.push("   <td>"+rows[i].NAME+"</td>");
				html.push("   <td>"+TYPE+"</td>");
				html.push("   <td>");
				html.push("&nbsp;<a href='#' title='新增子项' onclick='_add("+rows[i].ID+")'><i class='icon icon-node'></i></a>");
				html.push("&nbsp;<a href='#' title='编辑信息，或在数据行上双击鼠标左键。' onclick='_edit("+rows[i].ID+")'><i class='icon icon-edit'></i></a>");
				html.push("&nbsp;<a href='#' title='删除本项及所有子项' onclick='_removeids("+rows[i].ID+")'><i class='icon icon-remove'></i></a>");
				if(rows[i].ENGINEER_CODE!=""){
					html.push('&nbsp;<a href="javascript:void(0)" title="查看工程" onclick="_showEngineer(\''+rows[i].ENGINEER_CODE+'\')"><i class="icon-window-alt"></i></a>');
				}
				html.push("   </td>");
				html.push(" </tr>");
			}
			opt_all.treeGrid.append(html.join(""));
			
			opt_all.treeGrid.addClass("table");
			opt_all.treeGrid.addClass("table-striped");
			opt_all.treeGrid.addClass("table-bordered");
			opt_all.treeGrid.addClass("table-condensed");//更为紧凑
			
			if ($("#select_pcode").val()==""){
				opt_all.treeGrid.treegrid({
					 initialState:'collapsed'
					,expanderExpandedClass:  'icon icon-minus'
		            ,expanderCollapsedClass: 'icon icon-plus'
		        });				
			} else {
				opt_all.treeGrid.treegrid({
					 initialState:'expanded'
					,expanderExpandedClass:  'icon icon-minus'
		            ,expanderCollapsedClass: 'icon icon-plus'
		        });	
			}
		});
	}
		
	    //加载主表数据，必须提供url_list，toolbar,opt-传递查询条件，可覆盖
		this.InitData=function(opt){
			opt_all=$.extend(opt_all,opt);
			_LoadBaseData();
			_LoadData(opt);
			
		}////end this.InitData
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// 初始化增加、修改和删除,公开函数
		this.InitAddEditDel=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.btn_add.bind("click",event_add);
			opt_all.btn_ref.bind("click",event_ref);
			opt_all.btn_basedata.bind("click",event_basedata);
			opt_all.import_deptInfo.bind("click",import_deptInfo); //导入部门信息
			opt_all.export_whole_dept.bind("click",export_whole_dept); //导出全部数据
			opt_all.synchroDept.bind("click",synchroDept);			   //同步通讯录
			
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
	    // 新增子记录，调出窗体,公开函数
		this.add=function(id) {
			_add(id);
		}
		//新增子记录函数
		function _add(id){
			//加载页面基本选择数据，成功后调用显示新增页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
					function(){ 
						LoadAddData(id);
					}
			);
		}		
		
	    // 修改记录，调出窗体,公开函数
		this.edit=function(id,onlyread) {
			_edit(id,onlyread);
		}
		//编辑函数
		function _edit(id,onlyread){
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
					function(){ 
						LoadEditData(id,onlyread);
					}
			);
		}
		
		//增加或修改时，加载父节点信息pdata对象数据到界面
		function showFormDataP(pdata){
	        //上级内码
			$('#pnm_dept').val(pdata.nm);
			$('#pcode_dept').val(pdata.code);
			if (pdata.code.length>0){
				$('#pname_dept').val("("+pdata.code+")"+pdata.name);
			} else {
				$('#pname_dept').val("根节点");
			}
		}
		
		//加载Edit页面上数据，并调出增加或编辑页面显示(点击新增子节点)
		function LoadAddData(id){
			var url = opt_all.url_add+"?formBean.infoBean.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
				comm_loadFormData_flag(response.infoBean,"_dept");//显示本级数据
				showFormDataP(response.pinfoBean);   //显示父节点数据
				
	            var title="<i class='icon icon-file-o'></i> 新增信息";
				opt_all.info_dialog.find('.modal-title').html(title) ;
				opt_all.btn_save.show();
				
				opt_all.info_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();
				});
			});
		}
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(id,onlyread){
			var url = opt_all.url_edit+"?formBean.infoBean.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
				comm_loadFormData_flag(response.infoBean,"_dept");//显示本级数据
				showFormDataP(response.pinfoBean);   //显示父节点数据
				
	            var title="信息维护";
				if (id==0) {
					title="<i class='icon icon-file-o'></i> 新增根信息";
				}
				if (id!=0){
					title="<i class='icon icon-edit'></i> 编辑信息";
				}
				
				$('#type').trigger('chosen:updated');//更新选项，此项需要执行，否则chosen更新不了
				opt_all.info_dialog.find('.modal-title').html(title) ;
				
				//是否显示保存按钮
				if (onlyread){
					opt_all.btn_save.hide();
				} else {
					opt_all.btn_save.show();
				}
				
				opt_all.info_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					 _reset();   // 当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
	            });
			});
		}
	    // 保存数据
		function _save() {
			//设置本级全编码
			$('#code_dept').val($('#pcode_dept').val()+$('#thiscode_dept').val());	
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		common_ajax(json, opt_all.url_save, function(response){
 				$('#info_dialog').modal("hide");
 				_refresh();						    
	 		});	
		}
		//公开函数删除
		this.removeids=function(id){
			_removeids(id);
		}
	    // 批量删除
		function _removeids(id){
	           var url= opt_all.url_remove+"?formBean.ids="+id
				bootbox.confirm("确认需要删除本级及所有子项记录吗?", function(result) {
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
		
	   //导入部门数据
	   function import_deptInfo(){
		   var url=basePath+"system/sysdept!importDeptData.action";
		   filesUpload(url,"myUploader",_refresh); 
		   opt_all.import_dialog.find('.modal-title').html("部门信息") ;
		   opt_all.import_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
				
          });
	   }
	   
	   //导出全部单位数据
	   function export_whole_dept(){
		   $.ajax({
				url:opt_all.url_export_dept,
				type: "POST",
				dataType:"json",
				success:function(response){
					window.location.href=basePath+response.Path;
				}
			});
	   }
	   
	   //同步通讯录
	   function synchroDept(){
		   $.ajax({
				url:opt_all.url_synchroDept,
				async: false,
				type: "POST",
				dataType:"json",
				success:function(response){
					if(response.flag=="success"){
						var msg = new $.zui.Messager("消息提示：已同步到通讯录！！！", {placement: "center",type:"primary"});
					    msg.show();	
					}else if(response.flag=="error"){
						var msg = new $.zui.Messager("消息提示：没有需要同步数据！！！", {placement: "center",type:"primary"});
					    msg.show();	
					}
				}
			});
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
		
		function event_basedata(){
			_LoadBaseData();
		}
		
		//****绑定事件******end
		
		this.refresh=function(){
			_refresh();
		}
		
	    // 刷新
	    function _refresh(){
	    	_LoadData();
	    }  
		// 重置窗体
		function _reset(){
			opt_all.info_form.data('bootstrapValidator').resetForm(false);
		}
		//公开函数
		this.reset=function(){
			_reset();
		}
		
		//加载单位类型信息
		this.initDeptType=function(){
			Load_EditSelectData_type();
		}
		
		//加载部门类型数据ctl_select:下拉框控件
		function Load_EditSelectData_type(){
			$('#type').empty();
			$('#type').append('<option value="">请选择</option>');
			var url=basePath+"system/sysdict!list.action";
			var obj={
				"formBean.infoBean.flag":""
				,"formBean.infoBean.sysflag":""
				,"formBean.mSysDictCateInfoBean.code":"demand_type"
			};
			common_ajax(obj,url, function(data) {
				for(var i=0,len=data.rows.length;i<len;i++){
					$('#type').append('<option value=' + data.rows[i].CODE + '>'+ data.rows[i].NAME + '</option>');
				}
			});
		}
		
		//加载所有外键表到下拉框，无
		function Load_EditSelectData(callback){
			callback();
		}
		
		/////////////////////////////////////////////////////////////////////////////////
	};
})(jQuery);
			

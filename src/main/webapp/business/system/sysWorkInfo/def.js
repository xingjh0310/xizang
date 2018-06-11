(function($) {
	$.System_SysDept = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 btn_add		:$('#btn_add')		//增加按钮
			,btn_del     	:$('#btn_del')		//删除按钮	
			,btn_ref     	:$('#btn_ref')		//刷新按钮
			,searchInput	:$('#searchName')	//模糊查询input
			,info_dialog	:$('#info_dialog_')  // 新增和编辑对应的窗体，注意和info_form的区别
			,info_form		:$('#info_form_')    // 新增和编辑对应的表单，注意和info_dialog的区别
			,treeGrid 		:$('#treeGrid')		//BootStrapTreeList的ID--
			,select_pcode	:$('#select_pcode') //选中根节点--
			,btn_basedata	:$('#btn_basedata') //刷新根节点下拉框数据按钮
			,btn_save		:$('#btn_save')		//保存按钮
			,info_dialog_dept:$('#info_dialog_dept') //设置参建单位窗体
			,info_form_dept:$('#info_form_dept')  //设置参建单位表单
			,btn_save_dept  :$('#btn_save_dept')  //保存设置单位信息
			,export_whole_project:$('#export_whole_project')//导出全部工程信息
			,import_EngineerInfo:$('#import_EngineerInfo')  //导入工程信息
			,switch_def		:$('#switch_def')  //切换工程名称及工工程标段
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"system/sysengineerinfo!list.action"			//查询数据URL
			,url_add		:basePath+"system/sysengineerinfo!add.action"			//新增子数据URL--
			,url_edit		:basePath+"system/sysengineerinfo!edit.action"			//编辑数据URL
			,url_save		:basePath+"system/sysengineerinfo!save.action"			//保存数据URL
			,url_remove		:basePath+"system/sysengineerinfo!removeids.action"		//删除数据URL
			,url_listroot   :basePath+"system/sysengineerinfo!listroot.action"		//获取根数据URL--
			,url_set_dept   :basePath+"system/sysengineerinfo!loadSetDept.action"   //设置参建单位
			,url_save_dept  :basePath+"system/sysengineerinfo!saveSetDept.action"   //保存设置单位信息
			,url_export_project:basePath+"system/sysengineerinfo!exportProjectInfo.action"
			,url_enterSys	:basePath+"login/system!enter.action" //进入后台
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
					var option = $("<option>").text("("+response.rows[i].ENGINEER_CODE+")"+response.rows[i].ENGINEER_NAME).val(response.rows[i].ENGINEER_CODE);
					opt_all.select_pcode.append(option);
				}
				comm_chose_init(opt_all.select_pcode);
			});
		}
		
	    //加载treegrid数据
		function _LoadData(opt){
		    var temp = {   
				"mSysEngineerInfoFormBean.pageBean.limit": 100000000   // 页面大小
				,"mSysEngineerInfoFormBean.pageBean.offset": 0  // 当前记录偏移条数	
				,"mSysEngineerInfoFormBean.mSysEngineerInfo.engineerCode":opt_all.select_pcode.val()
		 	};
		    temp=$.extend(temp,opt);
			common_ajax(temp,opt_all.url_list, function(response) {
			var html=[];
			opt_all.treeGrid.html("");
			html.push("<thead><tr>");
			html.push("<th style='text-align: center;width:242px;background-color:#F2F2F2'>工程名称</th>");
			html.push("<th style='text-align: center;width:108px;background-color:#F2F2F2'>建管单位</th>");
			html.push("<th style='text-align: center;width:140px;background-color:#F2F2F2'>电压等级</th>");
			html.push("<th style='text-align: center;width:150px;background-color:#F2F2F2'>建设线路长度(千米)</th>");
			html.push("<th style='text-align: center;width:132px;background-color:#F2F2F2'>设计投运时间</th>");
			html.push("<th style='text-align: center;width:140px;background-color:#F2F2F2'>参建人员</th>");
			html.push("<th style='text-align: center;width:400px;background-color:#F2F2F2'>操作</th>");
			html.push("<th style='text-align: center;width:100px;background-color:#F2F2F2'>后台管理</th>");
			html.push("</tr></thead>");
			var rows=response.rows;
			for (var i = 0 ; i< rows.length;i++){
				var STAFF = rows[i].STAFF==null?"":rows[i].STAFF;
				var TREENM_SYS_DEPT=rows[i].TREENM_SYS_DEPT==null?"":rows[i].TREENM_SYS_DEPT;
				var VOLTAGE_EVEL=rows[i].VOLTAGE_EVEL==null?"":rows[i].VOLTAGE_EVEL;
				html.push(" <tr class='treegrid-"+rows[i].NM);
				if (rows[i].PNM.length>0){
					html.push(" treegrid-parent-"+rows[i].PNM);
				}
				html.push("' ondblclick='_edit("+rows[i].ID+",true)'>");
				html.push("   <td>"+rows[i].ENGINEER_NAME+"</td>");
				html.push("   <td>"+TREENM_SYS_DEPT+"</td>");
				html.push("   <td>"+VOLTAGE_EVEL+"</td>");
				html.push("   <td>"+rows[i].LINE_LENGTH+"</td>");
				html.push("   <td>"+rows[i].DELIVERY_TIME+"</td>");
				html.push("<td><div class='autocut' title='"+STAFF+"'>"+STAFF+"</div></td>");
				html.push("   <td>");
				html.push("&nbsp;<button class='btn btn-xs btn-primary' title='新增子项' onclick='_add("+rows[i].ID+")'><div class='visible-md visible-lg'><i class='icon icon-node'></i>&nbsp;新增</div><div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div></button>&nbsp;");
				html.push("&nbsp;<button class='btn btn-xs btn-primary' title='编辑信息，或在数据行上双击鼠标左键。' onclick='_edit("+rows[i].ID+")'><div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div><div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div></button>&nbsp;");
				html.push("&nbsp;<button class='btn btn-xs btn-primary' title='删除本项及所有子项' onclick='_removeids("+rows[i].ID+")'><div class='visible-md visible-lg'><i class='icon icon-times'></i>&nbsp;删除</div><div class='visible-xs visible-sm'><i class='icon icon-times'></i></div></button>&nbsp;");
				html.push("&nbsp;<button class='btn btn-xs btn-primary' title='设置参建单位' onclick='_setUp("+rows[i].ID+")'><div class='visible-md visible-lg'><i class='icon icon-cogs'></i>&nbsp;设置参建单位</div><div class='visible-xs visible-sm'><i class='icon icon-times'></i></div></button>&nbsp;");
				html.push("   </td>");
				html.push("   <td>");
			    html.push("<button title='进入后台' onclick=\"_enterSys('"+rows[i].NM+"')\" class='btn btn-xs btn-primary'>");
				html.push("<div class='visible-md visible-lg'><i class='icon icon-hand-right'></i>&nbsp;进入后台</div>");
				html.push("<div class='visible-xs visible-sm'><i class='icon icon-hand-right'></i></div>");
				html.push("</button>");
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
			opt_all.export_whole_project.bind("click",export_whole_project);//绑定导出事件
			opt_all.import_EngineerInfo.bind("click",import_EngineerInfo);  //绑定导入事件
			opt_all.switch_def.bind("click",switch_def);  //绑定切换事件
			
			// 保存button类型为submit
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// //校验数据正确
			});	
			// 保存button类型为submit
			opt_all.info_form_dept.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				btn_save_dept();
				
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
			$('#pnm_sysWorkInfo').val(pdata.nm);
			$('#pcode_sysWorkInfo').val(pdata.engineerCode);
			if (pdata.engineerCode.length>0){
				$('#pname_sysWorkInfo').val("("+pdata.engineerCode+")"+pdata.engineerName);
			} else {
				$('#pname_sysWorkInfo').val("根节点");
			}
			
			
		}
		
		//加载Edit页面上数据，并调出增加或编辑页面显示(点击新增子节点)
		function LoadAddData(id){
			var url = opt_all.url_add+"?mSysEngineerInfoFormBean.mSysEngineerInfo.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				for(var key in response.infoBean){
				   if($("#"+key+"_sysWorkInfo")[0]){
					   $("#"+key+"_sysWorkInfo").val("");
				   }
				}
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
			var url = opt_all.url_edit+"?mSysEngineerInfoFormBean.mSysEngineerInfo.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				comm_loadFormData_flag(response.infoBean,"_sysWorkInfo");//显示本级数据
				showFormDataP(response.pinfoBean);   //显示父节点数据
	            var title="信息维护";
				if (id==0) {
					title="<i class='icon icon-file-o'></i> 新增根信息";
					$("#type_sysWorkInfo").val(1);
				}
				if (id!=0){
					title="<i class='icon icon-edit'></i> 编辑信息";
					$('#treenmSysDept').val(response.infoBean.DEPTNAME);
				} 
				
				$('#voltageEvel_sysWorkInfo').trigger('chosen:updated');//更新选项，此项需要执行，否则chosen更新不了
				
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
			$('#code_sysWorkInfo').val($('#pcode_sysWorkInfo').val()+$('#thiscode_sysWorkInfo').val());	
	 		var json=opt_all.info_form.serialize();
	 		common_ajax(json, opt_all.url_save, function(response){
 				$('#info_dialog_').modal("hide");
 				_refresh();						    
	 		});
		}
		//公开函数删除
		this.removeids=function(id){
			_removeids(id);
		}
	    // 批量删除
		function _removeids(id){
           var url= opt_all.url_remove+"?mSysEngineerInfoFormBean.ids="+id
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
		
	   this.setUp=function(id){
		   _setUp(id);
		}
	   //设置参建单位
	   function _setUp(id){
		   var url = opt_all.url_set_dept+"?mSysEngineerInfoFormBean.mSysEngineerInfo.id=" + id;
		   common_ajax(null,url, function(response) {
			   comm_loadFormData_flag(response.mSysEngineerInfo,"__sysWorkInfo");//显示本级数据
			   var title="信息维护";
			   opt_all.info_dialog_dept.find('.modal-title').html("<i class='icon icon-edit'></i> 设置参建单位") ;
			   loadTreeData();
			   opt_all.info_dialog_dept.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					$('#btn_save_dept').attr('disabled',false);
	            });
		   });
	   }
	   
	   //进入后台
	   this.enterSys=function(eNm){
		   _enterSys(eNm);
	   }
	   
	   function _enterSys(eNm){
		   var url = opt_all.url_enterSys;
		   $.ajax({
				url:url,
				type: "POST",
				dataType:"json",
				data:{"engNm":eNm},
				success:function(response){
					if(response.flag=="homePage"){
						window.open(basePath+"business/system/home_page/list.jsp");
					}else if(response.flag=="error"){
						var msg = new $.zui.Messager("消息提示：未设置工程信息！！！", {placement: "center",type:"primary"});
					    msg.show();	
					}
				}
			});
	   }
	   
	   function _btn_save_dept(){
		   var json=opt_all.info_form_dept.serialize();
		   json=decodeURIComponent(json,true);
		   $.ajax({
				url : opt_all.url_save_dept,
				type : "POST",
				async:false,
				data : json,
				success : function(response) {
				   $('#info_dialog_dept').modal("hide");
	 				_refresh();			
	 				var msg = new $.zui.Messager("消息提示："+JSON.parse(response).promt, {placement: "center",type:"primary"});
				    msg.show();	
				}
		   });
	   }
	   
	   //导出工程信息
	   function export_whole_project(){
		   $.ajax({
				url:opt_all.url_export_project,
				type: "POST",
				dataType:"json",
				success:function(response){
					window.location.href=basePath+response.Path;
				}
			});
	   }
	   
	   //导入工程信息
	   function import_EngineerInfo(){
		   var url=basePath+"system/sysengineerinfo!importProjectInfo.action";
		   filesUpload(url,"myUploader",_refresh); 
		   opt_all.import_dialog.find('.modal-title').html("工程信息") ;
		   opt_all.import_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
				
          });
	   }
	   
	   function switch_def(){
		   var name = $("#engineerName_span").html();
		   if(name == "工程名称："){
			   $("#engineerName_span").html("工程标段：");
			   $("#type_sysWorkInfo").val(2);
		   }else if(name == "工程标段："){
			   $("#engineerName_span").html("工程名称：");
			   $("#type_sysWorkInfo").val(1);
		   }
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
		
		//保存设置单位信息
		function btn_save_dept(){
			_btn_save_dept();
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
		
		//初始化建管单位
		this.initTreeNmSysDept=function(){
			Load_EditSelectData_treenmSysDept();
		}
		
		function Load_EditSelectData_treenmSysDept(){
			$('#treenmSysDept_sysWorkInfo').empty();
			$('#treenmSysDept_sysWorkInfo').append('<option value="">请选择</option>');
			var url=basePath+"system/sysdict!list.action";
			var obj={
				"formBean.infoBean.flag":""
				,"formBean.infoBean.sysflag":""
				,"formBean.mSysDictCateInfoBean.code":"demand_type"
			};
			common_ajax(obj,url, function(data) {
				console.log(JSON.stringify(data))
				for(var i=0,len=data.rows.length;i<len;i++){
					$('#treenmSysDept_sysWorkInfo').append('<option value=' + data.rows[i].CODE + '>'+ data.rows[i].NAME + '</option>');
				}
			});
		}
		
		//初始化电压等级
		this.initVoltageEvel=function(){
			Load_EditSelectData_voltageEvel();
		}
		
		function Load_EditSelectData_voltageEvel(){
			$("#voltageEvel_sysWorkInfo").empty();
			$('#voltageEvel_sysWorkInfo').append('<option value="">请选择数据</option>');
			var url=basePath+"system/sysdict!list.action";
			var obj={
				"formBean.infoBean.flag":""
				,"formBean.infoBean.sysflag":""
				,"formBean.mSysDictCateInfoBean.code":"dydj"
			};
			common_ajax(obj,url, function(data) {
				for(var i=0,len=data.rows.length;i<len;i++){
					$('#voltageEvel_sysWorkInfo').append('<option value=' + data.rows[i].CODE + '>'+ data.rows[i].NAME + '</option>');
				}
			});
		}
		
		//加载所有外键表到下拉框，无
		function Load_EditSelectData(callback){
			callback();
		}
	};
})(jQuery);
			

(function($) {
	$.System_Sysacct = function(option) {
		var opt_control={
			 btn_add		:$('#btn_add')		//增加按钮
			,btn_del     	:$('#btn_del')		//删除按钮	
			,btn_ref     	:$('#btn_ref')		//刷新按钮
			,table 			:$('#tbinfo')		//BootStrapTable的ID
			,searchInput	:$('#searchName')	//模糊查询input
			,info_dialog	:$('#info_dialog')  // 新增和编辑对应的窗体，注意和info_form的区别
			,info_form		:$('#info_form')    // 新增和编辑对应的表单，注意和info_dialog的区别
            ,btn_save		:$('#btn_save')		//保存按钮
		}
		var opt_url={
			 url_list		:basePath+"system/sysacct!list.action"			//查询数据URL
			,url_edit		:basePath+"system/sysacct!edit.action"			//编辑数据URL
			,url_save		:basePath+"system/sysacct!save.action"			//保存数据URL
			,url_remove		:basePath+"system/sysacct!removeids.action"		//删除数据URL
			,url_resetPwd	:basePath+"system/sysacct!reset.action"	    //重置密码URL
		}
		var opt_all=($.extend({},opt_control,opt_url,option));
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//查询条件及页面分页信息设置
		var opt_tb_query={};
		this.Init_TB_QueryParms=function(opt){
			opt_tb_query=$.extend({},opt_tb_query,opt);
		}
		//D、BootStrapTable参数
		var opt_tb_base={
	      	  url			:opt_all.url_list   // 请求后台的URL（*）
	         ,queryParams	:queryParams		// 传递参数（*）
	         ,onDblClickRow	:onDblClickRow		// 行双击事件
	         ,onSort		:onSort             // 排序事件
             ,rowStyle		:comm_rowStyle		//行样式，可自定义
             ,onLoadSuccess	:comm_onLoadSuccess //数据加载错误	
		}
		function queryParams(params) {  // 配置参数
			//查询条件
		    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		       "formBean.pageBean.limit"	: params.limit   // 页面大小
		      ,"formBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
		      ,"formBean.pageBean.sort"		: params.sort  // 排序列名
		      ,"formBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
		      ,"formBean.searchName"		: opt_control.searchInput.val() // 查询关键字
					// 在此增加查询条件
              ,"formBean.infoBean.sysflag"  : $("#select_sysflag").val()
              ,"formBean.infoBean.dictnmDllx":$("#S_DictnmDllx").val()
              ,"formBean.infoBean.dictnmZhlx":$("#dictnmZhlx_xx").val()
              ,"formBean.infoBean.listnmSysStaff":$("#S_ListnmSysStaff").val()
		    };
		    temp=$.extend({},temp,opt_tb_query);
		    return temp;
		}
		// 双击事件
		function onDblClickRow(row){
				if (row) {
					//if (row.sysflag == 0){
						_edit(row.ID,true);
					//}
				}
		}
		// 排序事件
		function onSort(name, order){
			_refresh;
		}
		
		//全部BootStrapTable参数变量
		var opt_tb_all=$.extend({},g_bootstrapTable_Options,opt_tb_base);
		//公用函数，初始化BootStrapTable参数变量，自定义覆盖
		this.Init_TB_Option=function(opt){
			opt_tb_all=$.extend({},opt_tb_all,opt);
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////
	    //加载主表数据，必须提供url_list，toolbar,opt-传递查询条件，可覆盖
		this.Init_TB_Data=function(){
			opt_all.table.bootstrapTable(opt_tb_all);
			// 提交查询函数
		}////end this.InitData
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// 初始化增加、修改和删除,公开函数
		this.InitAddEditDel=function(opt){
			opt_all=$.extend({},opt_all,opt);
			opt_all.btn_add.bind("click",event_add);
			opt_all.btn_del.bind("click",event_del);
			opt_all.btn_ref.bind("click",event_ref);
			
			// 保存button类型为submit
			opt_all.info_form.bootstrapValidator({
                excluded: ':disabled',
                fields: {
                    'formBean.infoBean.dictnmDllx': {
                        message: '登录类型没有校验',
                        validators: {
                            notEmpty: {
                                message: '登录类型不能为空'
                            }
                        }
                    }
                }
			}).on("success.form.bv", function(e) {
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
		//新增和编辑函数
		function _edit(id,onlyread){
			var nm = $("#S_ListnmSysStaff").val();
			if(nm == null || nm == ""){
				var msg = new $.zui.Messager("消息提示：请选择系统人员", {placement: "center",type:"primary"});
			    msg.show();	
				return;
			}
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
					function(){ 
						LoadEditData(id,onlyread);
					}
			);
		}
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(id,onlyread){
			var url = opt_all.url_edit+"?formBean.infoBean.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				console.log(response);
				$("#dictnmZhlx_xx").val(response.infoBean.dictnmZhlx);
				// 获取到数据，显示在界面上
				comm_loadFormData_flag(response.infoBean,"_acct");
	            var title="信息维护";
				if (id==0) {
					title="<i class='icon icon-file-o'></i> 新增信息";
					//初始化选择-登录类型
					$("#dictnmDllx").val($("#S_DictnmDllx").val());
					//初始化选择-账户类型
					$("#dictnmZhlx").val($("#S_DictnmZhlx").val());
				}else if (id!=0) {
					$("#dictnmDllx").val(response.infoBean.dictnmDllx);
					$("#dictnmZhlx").val(response.infoBean.dictnmZhlx);
					title="<i class='icon icon-edit'></i> 编辑信息";
				}
				
				//初始化选择-系统人员
				$("#listnmSysStaff").val($("#S_ListnmSysStaff").val());
				//初始化选择-登录类型
				$('#dictnmDllx').trigger('chosen:updated');//更新选项，此项需要执行，否则chosen更新不了
				//初始化选择-账户类型
				$('#dictnmZhlx').trigger('chosen:updated');//更新选项，此项需要执行，否则chosen更新不了
				//初始化选择-系统人员
				$('#listnmSysStaff').trigger('chosen:updated');//更新选项，此项需要执行，否则chosen更新不了
                
				//是否显示保存按钮
				if (onlyread){
					opt_all.btn_save.hide();
                    title="<i class='icon icon-file-o'></i> 查看信息";
				} else {
					opt_all.btn_save.show();
				}
                
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
	    // 保存数据
		function _save() {
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		//保存请求
	 		common_ajax(json, opt_all.url_save, function(response){
	 			//隐藏窗体
	 			opt_all.info_dialog.modal("hide");
	 			
	 			//为了保存后，同时更新表格中的外键字段名称
	            var info=response.infoBean;	
				var str;
				//取下拉框的文本后，去掉前面()，剩下的文本
				str=$("#dictnmDllx").find("option:selected").text();
				str=str.substring(str.indexOf(")")+1);
		    	info=$.extend(upperJSONKey(info),{"DllxName":str});
	            
				str=$("#dictnmZhlx").find("option:selected").text();
				str=str.substring(str.indexOf(")")+1);
		    	info=$.extend(upperJSONKey(info),{"ZhlxName":str});
	            
				str=$("#listnmSysStaff").find("option:selected").text();
				str=str.substring(str.indexOf(")")+1);
		    	info=$.extend(upperJSONKey(info),{"SysStaffName":str});
            
	 			
			    // 增加数据
			    if ($('#id').val()==0){
			        // 增加表格数据，插入到第一位
			    	opt_all.table.bootstrapTable("prepend", upperJSONKey(info));
			    	_refresh(); 
			    } else {
			    	_refresh();
	            }						    
	 		}); 	
		}
		
	    // 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"ID");
           if (ids.length==0) return;
           
           var url= opt_all.url_remove+"?formBean.ids="+ids
            
			bootbox.confirm("确认需要批量删除选中的多条记录吗?", function(result) {
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
		
		//重置密码
		this.resetPwd=function(id){
			_resetPwd(id);
		}
		
		function _resetPwd(id){
	       var url= opt_all.url_resetPwd+"?formBean.ids="+id
		   bootbox.confirm("确认密码重置【123456】吗?", function(result) {
		   if(result){
			  common_ajax(null, url, function(response){
				 _refresh();
				 var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
					msg.show();	
				 }); 
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
		//绑定删除事件
		function event_del(){
			_removeids();
		}
		//****绑定事件******end
		
	    // 刷新
	    function _refresh(){
	    	opt_all.table.bootstrapTable('refresh');
	    }  
	    //公开函数
	    this.refresh=function(){
	    	_refresh();
	    }
	    
		// 重置窗体
		function _reset(){
			opt_all.info_form.data('bootstrapValidator').resetForm(false);
		}
		//公开函数
		this.reset=function(){
			_reset();
		}
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		//##########################################################################	
		
////////////////////////////////////////////////////////////////////////////////
	//加载所有外键表到下拉框
	function Load_EditSelectData(callback){
		//所有编辑页面下拉框加载
        $('#dictnmDllx').empty();
		Load_EditSelectData_DictnmDllx( $('#dictnmDllx'),function(){
        $('#dictnmZhlx').empty();
		Load_EditSelectData_DictnmZhlx( $('#dictnmZhlx'),function(){
        $('#listnmSysStaff').empty();
		Load_EditSelectData_ListnmSysStaff( $('#listnmSysStaff'),function(){

                comm_chose_init($('#dictnmDllx'));
                comm_chose_init($('#dictnmZhlx'));
                comm_chose_init($('#listnmSysStaff'));
				callback();
		})
		})
		})
		;
	}
    
	//加载系统人员数据ctl_select:下拉框控件
	function Load_EditSelectData_ListnmSysStaff(ctl_select,callback){
		var url=basePath+"system/sysstaff!list.action";
		var parms=$.extend({},g_SelectParms,
				{
				   "formBean.infoBean.flag":""
				  ,"formBean.infoBean.sysflag":""
				}
		);
		//加载下拉框数据
		_comm_loadSelectData(url,parms,ctl_select,callback);
	}

	//加载登录类型数据ctl_select:下拉框控件
	function Load_EditSelectData_DictnmDllx(ctl_select,callback){
		var url=basePath+"system/sysdict!list.action";
		var parms=$.extend({},g_SelectParms,
				{
				   "formBean.infoBean.flag":""
				  ,"formBean.infoBean.sysflag":""
				  ,"formBean.infoBean.listnmSysDictCate":"dllx"
				}
		);
		//加载下拉框数据
		comm_loadSelectData(url,parms,ctl_select,callback);
	}
	//加载账户类型数据ctl_select:下拉框控件
	function Load_EditSelectData_DictnmZhlx(ctl_select,callback){
		var url=basePath+"system/sysdict!list.action";
		var parms=$.extend({},g_SelectParms,
				{
				   "formBean.infoBean.flag":""
				  ,"formBean.infoBean.sysflag":""
				  ,"formBean.infoBean.listnmSysDictCate":"Zhlx"
				}
		);
		//加载下拉框数据
		comm_loadSelectData(url,parms,ctl_select,callback);
	}
	////////////////////////////////////////////////////////////////////////////////
    
	};
})(jQuery);

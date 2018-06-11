(function($){
	$.SysStaffRefAcct=function(option){
		var opt_control={
			btn_add		    :$('#btn_add')		//增加按钮
			,btn_del     	:$('#btn_del')		//删除按钮	
			,btn_ref     	:$('#btn_ref')		//刷新按钮
			,table 			:$('#SysStaffRefAcctTable')		//BootStrapTable的ID
			,searchInput	:$('#searchName')	//模糊查询input
			,info_dialog	:$('#info_dialog')  // 新增和编辑对应的窗体，注意和info_form的区别
			,info_form		:$('#info_form')    // 新增和编辑对应的表单，注意和info_dialog的区别
			,select_flag    :$('#select_flag')  //当前选中的审核状态
	        ,btn_save		:$('#btn_save')		//保存按钮
	        ,export_current	:$('#export_current')//导出当前页
	        ,export_whole	:$('#export_whole')	//导出全部
	        ,import_dialog  :$('#import_dialog') //导入对话框
	        ,import_staffInfo:$('#batch_Import')//导入人员信息
	        ,roleRefStaff   :$('#roleRefStaff')  //角色配置人员
		};
		var opt_url={
			 url_list		:basePath+"system/sysstaffrefacct!list.action"	//查询数据URL
			,url_edit		:basePath+"system/sysstaffrefacct!edit.action"	//编辑数据URL
			,url_save		:basePath+"system/sysstaffrefacct!save.action"	//保存数据URL
			,url_remove		:basePath+"system/sysstaffrefacct!removeids.action"	//删除数据URL
			,url_export_current:basePath+"system/sysstaffrefacct!exportData.action" //导出数据URL
			,url_resetPwd	:basePath+"system/sysstaffrefacct!reset.action"	    //重置密码URL
		};
		var opt_all=($.extend({},opt_control,opt_url,option));
		var opt_tb_query={};
		this.Init_TB_QueryParms=function(opt){
			opt_tb_query=$.extend({},opt_tb_query,opt);
		};
		var opt_tb_base={
		     url			:opt_all.url_list   
		    ,queryParams	:queryParams		
		    ,onDblClickRow	:onDblClickRow		
		    ,onSort		    :onSort             
	        ,rowStyle		:comm_rowStyle		
	        ,onLoadSuccess	:comm_onLoadSuccess 
		};
		var pageParams=null;
		function queryParams(params) {
			pageParams=params;
			var temp = {
				"mSysStaffRefAcctFormBean.pageBean.limit":params.limit,
				"mSysStaffRefAcctFormBean.pageBean.offset":params.offset,
				"mSysStaffRefAcctFormBean.pageBean.sort":params.sort,
				"mSysStaffRefAcctFormBean.pageBean.order":params.order,
				"mSysStaffRefAcctFormBean.searchName":opt_control.searchInput.val(),
				"mSysStaffRefAcctFormBean.mSysStaffInfoBean.treenmSysDept":$("#S_TreenmSysDept").val(),
				"mSysStaffRefAcctFormBean.ids":$("#_treenmSysDept").val(),
				"mSysStaffRefAcctFormBean.mSysStaffInfoBean.dictnmXingbie":$("#S_DictnmXingbie").val()
			};
			temp=$.extend({},temp,opt_tb_query);
			return temp;
		}
		function onDblClickRow(row){
			if (row) {
				_edit(row.NM,true);
			}
		}
		function onSort(name, order){
			_refresh;
		}
		var opt_tb_all=$.extend({},g_bootstrapTable_Options,opt_tb_base);
		this.Init_TB_Option=function(opt){
			opt_tb_all=$.extend({},opt_tb_all,opt);
		}
		this.Init_TB_Data=function(){
			opt_all.table.bootstrapTable(opt_tb_all);
		}
		this.InitAddEditDel=function(opt){
			opt_all=$.extend({},opt_all,opt);
			opt_all.btn_add.bind("click",event_add);
			opt_all.btn_del.bind("click",event_del);
			opt_all.btn_ref.bind("click",event_ref);
			opt_all.export_current.bind("click",export_current);//导出当前页
			opt_all.export_whole.bind("click",export_whole);//导出全部
			opt_all.import_staffInfo.bind("click",import_staffInfo); //导入人员信息
			opt_all.roleRefStaff.bind("click",event_roleRefStaff); //角色配置人员
			opt_all.info_form.bootstrapValidator({
                excluded: ':disabled',
                fields: {
		            "mSysStaffRefAcctFormBean.mSysAcctInfoBean.name": {
		               validators: {
		                   	notEmpty: {
	                            message: '手机号码不能为空'
	                        },
	                        regexp:{
	                        	message:'输入正确的手机号码',
	                        	regexp:/^1[3|4|5|7|8]\d{9}$/
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
		};
		this.edit=function(id,onlyread) {
			_edit(id);
		};
		function _edit(id,onlyread){
			/*var nm = $("#S_TreenmSysDept").val();
			if(nm==""){
				var msg = new $.zui.Messager("消息提示：请选择单位部门", {placement: "center",type:"primary"});
			    msg.show();	
				return;
			}*/
			Load_EditSelectData(function(){
				LoadEditData(id,onlyread);
			});
		}
		function LoadEditData(id,onlyread){
			var obj={
				"mSysStaffRefAcctFormBean.mSysStaffInfoBean.nm":id
			};
			$.ajax({
				data:obj,
				url:opt_all.url_edit,
				type: "POST",
				dataType:"json",
				success:function(response){
					// 获取到数据，显示在界面上
					comm_loadFormData_flag(response.mSysStaffInfoBean[0],"_staff_");
					comm_loadFormData_flag(response.mSysAcctInfoBean,"_acct_");
					console.log(response.mSysStaffInfoBean[0])
					var title="信息维护";
					if (id=="") {
						title="<i class='icon icon-file-o'></i> 新增信息";
						initFormData();
					}else if (id!="") {
						title="<i class='icon icon-edit'></i> 编辑信息";
						backFillData(response.mSysStaffInfoBean[0]);
					}
					
					$('#duty_staff_').trigger('chosen:updated');//更新选项，此项需要执行，否则chosen更新不了
					$('#dictnmXingbie').trigger('chosen:updated');//更新选项，此项需要执行，否则chosen更新不了
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
						$('#btn_save').prop('disabled',false);
						_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
		            });
				}
			});
		}
		
		//初始化表单数据
		function initFormData(){
			$('#id_staff_').val(0);
			$('#name_staff_').val("");
			$('#code_staff_').val("");
			$('#treenmSysDept_').val("");
			$('#treenmSysDept').val("");
			$('#nm_staff_').val("");
			$('#isLogin_staff_').val("");
			$('#phone_staff_').val("");
			$('#memo_staff_').val("");
			$('#origin_staff_').val("");
			$('#telephone_staff_').val("");
			$('#engineerNm_staff_').val("");
		}
		
		//回填数据
		function backFillData(data){
			$('#id_staff_').val(data.ID);
			$('#nm_staff_').val(data.NM);
			$('#code_staff_').val(data.CODE);
			$('#name_staff_').val(data.NAME);
			$('#isLogin_staff_').val(data.ISLOGIN);
			$('#phone_staff_').val(data.PHONE);
			$('#treenmSysDept_').val(data.TREENM_SYS_DEPT);
			$('#treenmSysDept').val(data.DEPT_NAME);
			$('#sysflag_staff_').val(0);
			$('#flag_staff_').val(0);
			$('#memo_staff_').val(data.MEMO);
			$('#origin_staff_').val(data.ORIGIN);
			$('#telephone_staff_').val(data.TELEPHONE);
			$('#engineerNm_staff_').val(data.ENGINEER_NM);
		}
		
		//角色配置人员
		function event_roleRefStaff(){
			var ids=g_select_and_tip(opt_all.table,"NM");
			if (ids.length==0){
				return;
			}
			$('#staffNm').val(ids);
			getRoleCodeByStaffCode($('#staffNm').val());
			$('#set_role_dialog').modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
				
            });
		}
		
		//根据人员内码查询角色内码
		function getRoleCodeByStaffCode(ids){
			var url=basePath+"system/sysstaffrefacct!getRoleCodeByStaffCode.action";
			$.post(url,{ids:ids},function(data){
				var str="";
				var str_="";
				var obj=JSON.parse(data).mSysStaffInfoBean;
				for(var i=0;i<obj.length;i++){
					str+=obj[i].TA_NM+",";
				}
				str=(str.substring(str.length-1)==',')?str.substring(0,str.length-1):str;
				var idsArray=str.split(",");
				$("#tbinfo tbody tr input").prop('checked',false);
				for (var j = 0; j < idsArray.length; j++) {  
					for(var k=0;k<array_.length;k++){
						if(idsArray[j]==array_[k]){
							$("#tbinfo tbody tr input[data-index="+k+"]").prop('checked',true);
							str_+=array_[k]+",";
						}
					}
                }
				str_=(str_.substring(str_.length-1)==',')?str_.substring(0,str_.length-1):str_;
				$('#roleNm_').val(str_);
			});
		}
		
		//点击保存按钮
		function _save() {
	 		var json=opt_all.info_form.serialize();
	 		console.log(JSON.stringify(json))
	 		$.ajax({
				data:json,
				url:opt_all.url_save,
				type: "POST",
				dataType:"json",
				success:function(response){
					opt_all.info_dialog.modal("hide");
					_refresh();
					var msg = new $.zui.Messager("消息提示："+response.validate, {placement: "center",type:"primary"});
				    msg.show();	
				}
			});
		}
		
		//上传附件
		this.upLoadInfo=function(nm){
			upLoadInfo_(nm);
		}
		
		function upLoadInfo_(nm){
			opt_all.import_dialog.find('.modal-title').html("人员签名图片") ;
			loadData(nm);
			opt_all.import_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
				
            });
		}
		
		function loadData(nm){
			var url=basePath+"file/upload!queryFileUpload.action?tableName=SYS_STAFF&tablePkColumn="+nm;
			var urlSave=basePath+"file/upload!saveFileUpload.action?tableName=SYS_STAFF&tablePkColumn="+nm
			common_ajax_async(null,url,function(response){
				filesUpload_(urlSave,"myUploader",_refresh,response.mFileUploadList); 
			});
		}
		
		function _removeids(){
			var ids=g_select_and_tip(opt_all.table,"NM");
	        if (ids.length==0) return;
	        var url= opt_all.url_remove+"?mSysStaffRefAcctFormBean.mSysStaffInfoBean.nm="+ids
		    bootbox.confirm("确认需要批量删除选中的多条记录吗?", function(result) {
		    	if(result){
		    		$.ajax({
						url:url,
						type: "POST",
						dataType:"json",
						success:function(response){
							_refresh();
							var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
							msg.show();	
						}
		    		});
	            }
		    }); 
		}
		
		//重置密码
		this.resetPwd=function(id){
			_resetPwd(id);
		}
		
		function _resetPwd(id){
			var url= opt_all.url_resetPwd+"?mSysStaffRefAcctFormBean.mSysStaffInfoBean.nm="+id
			bootbox.confirm("确认密码重置【123456】吗?", function(result) {
				if(result){
					$.ajax({
						url:url,
						type: "POST",
						dataType:"json",
						success:function(response){
							_refresh();
							var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
							msg.show();	
						}
					});
				}
			});
		}
		
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
		//导出当前页
		function export_current(){
			var deptNm=$("#S_TreenmSysDept").val();
			var obj={
				"mSysStaffRefAcctFormBean.pageBean.limit":pageParams.limit,
				"mSysStaffRefAcctFormBean.pageBean.offset":pageParams.offset,
				"mSysStaffRefAcctFormBean.pageBean.sort":pageParams.sort,
				"mSysStaffRefAcctFormBean.pageBean.order":pageParams.order,
				"mSysStaffRefAcctFormBean.mSysStaffInfoBean.treenmSysDept":deptNm,
				"mSysStaffRefAcctFormBean.mSysStaffInfoBean.dictnmXingbie":$("#S_DictnmXingbie").val()
			}
			getDataByAjax(obj);
		}
		
		//导出全部数据
		function export_whole(){
			var deptNm=$("#S_TreenmSysDept").val();
			
			var obj={
					"mSysStaffRefAcctFormBean.pageBean.offset":0,
					"mSysStaffRefAcctFormBean.pageBean.limit":10000000,
					"mSysStaffRefAcctFormBean.pageBean.sort":pageParams.sort,
					"mSysStaffRefAcctFormBean.pageBean.order":pageParams.order,
					"mSysStaffRefAcctFormBean.mSysStaffInfoBean.treenmSysDept":deptNm,
					"mSysStaffRefAcctFormBean.mSysStaffInfoBean.dictnmXingbie":$("#S_DictnmXingbie").val()
			   
			}
			getDataByAjax(obj);
		}
		
		function getDataByAjax(obj){
			$.ajax({
				data:obj,
				url:opt_all.url_export_current,
				type: "POST",
				dataType:"json",
				success:function(response){
					window.location.href=basePath+response.Path;
				}
			});
		}
		
		//导入人员信息
		function import_staffInfo(){
			var url=basePath+"system/sysstaffrefacct!importStaffInfo.action";
			filesUpload(url,"myUploader",_refresh); 
			
			opt_all.import_dialog.find('.modal-title').html("人员信息") ;
			opt_all.import_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
				
            });
		}
		
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
		this._reset=function(){
			_reset();
		}
		
		//加载下拉列表数据
		function Load_EditSelectData(callback){
			$('#dictnmXingbie').empty();
			Load_EditSelectData_DictnmXingbie($('#dictnmXingbie'),function(){
				$('#duty_staff_').empty();
				Load_EditSelectData_DictnmDuty($('#duty_staff_'),function(){
					comm_chose_init($('#dictnmXingbie'));
					comm_chose_init($('#duty_staff_'));
					callback();
				});
			});
		};
		
		function Load_EditSelectData_DictnmXingbie(ctl_select,callback){
			var url=basePath+"system/sysdict!list.action";
			var parms=$.extend({},g_SelectParms,{
				"formBean.infoBean.flag":""
				,"formBean.infoBean.listnmSysDictCate":"xingbie"
			});
			comm_loadSelectData(url,parms,ctl_select,callback);
		}
		
		function Load_EditSelectData_DictnmDuty(ctl_select,callback){
			var url=basePath+"system/sysdict!getDictDataByClassify.action";
			var parms=$.extend({},g_SelectParms,{
				"mSysDictCateFormBean.infoBean.code":"zw"
			});
			comm_loadSelectData_(url,parms,ctl_select,callback);
		}
	}
})(jQuery);
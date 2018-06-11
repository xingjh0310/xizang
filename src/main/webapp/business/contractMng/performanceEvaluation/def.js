(function($) {
	$.System_Registration = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 btn_add			:$('#btn_add')		//新增单位按钮
//			,btn_save			:$('#btn_registration')		//保存登记信息
			,btn_del     		:$('#btn_del')		//删除按钮	
			,btn_flag     		:$('#btn_flag')		//审核按钮	
			,btn_ref     		:$('#btn_ref')		//刷新按钮
			,table 				:$('#tbinfo')		//BootStrapTable的ID
			,searchInput		:$('#searchName')	//模糊查询input
			,info_dialog		:$('#info_dialog')  // 新增和编辑对应的窗体，注意和info_form的区别
			,info_form			:$('#info_form')    // 新增和编辑对应的表单，注意和info_dialog的区别
			,registration		:$('#formRegistration')    // 新增预约的form表单
			,select_flag   	 	:$('#select_flag')  //当前选中的审核状态
			,departUnitName		:$('#departUnitName')//预约单位名称
			,link_man			:$('#box')		//预约联系人
			,addCompany			:$('#addCompany')	//返回增加单位
		}
		//B、数据状态参数变量
		var opt_state={
			 flag_cur		:0 ////审核状态,当前List状态 flag=0
			,flag_new		:1 ////审核状态,审核后的状态     flag=1				
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"company/company!list.action"			//查询数据URL
			,url_edit		:basePath+"company/company!edit.action"			//编辑数据URL
//			,url_save		:basePath+"registration/registration!save.action"			//保存数据URL
			,url_remove		:basePath+"company/company!removeids.action"		//删除数据URL
			,url_flag 		:basePath+"company/company!flag.action"			//审核数据URL
//			,url_linkMan 	:basePath+"linkMan/linkMan!modify.action"			//维护联系人、可修改 可添加
		}

		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_state,opt_url,option));
	    //////////////////////////////////////////////////////////////////////////////////////////////////
	    //初始化表单数据
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
			            
					}));
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				//查询条件
				var opt_parms={
					 "formBean.searchName"		:opt_control.searchInput.val() // 查询关键字
					// 在此增加查询条件
					,"formBean.infoBean.flag"	:opt_control.select_flag.val() //flag_cur
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "formBean.pageBean.limit"	: params.limit   // 页面大小
			      ,"formBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,"formBean.pageBean.sort"		: params.sort  // 排序列名
			      ,"formBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
			    };
			    temp=$.extend(temp,opt_parms,opt);
//			    	alert(temp);
							    return temp;
			}
			// 双击事件
			function onDblClickRow(row){
				if (row) {
					_edit(row.id,true);
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
			opt_all.departUnitName.bind("change",changeCompany);
			opt_all.link_man.bind("change",changeLinkMan);
			
			// 保存button类型为submit
			opt_all.registration.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				//_save();
				_add();
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
				// 获取到数据，显示在界面上
				comm_loadFormData(response.infoBean);
	            var title="单位维护";
				if (id==0) {
					title="<i class='icon icon-file-o'></i> 新增单位";
				}
				if (id!=0) title="<i class='icon icon-edit'></i> 编辑单位";
				
				opt_all.info_dialog.find('.modal-title').html(title) ;
				
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
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。

	            });
			});
		}
	    // 保存数据
		function _save() {
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
		                id: response.infoBean.id,
		                row: response.infoBean
		            });		
	            }						    
	 		}); 	
		}
		
	    // 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"id");
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
		
		/////////////////////////////////////////////////////////////////
		// 初始化审核,公开函数
		this.InitFlag=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.btn_flag.bind("click",event_flag);
		}
	   //批量审核
	   function _flag(){
           // 获取选中的ids
           var ids=g_select_and_tip(opt_all.table,"id");
           if (ids.length==0) return;
           
           var url= opt_all.url_flag+"?formBean.infoBean.flag="+opt_all.flag_new+"&formBean.ids="+ids
            
			bootbox.confirm("确认需要批量审核选中的多条记录吗?", function(result) {
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
	   ////////////////////////////
		 //回车事件
			 function carriage(event){
					var e = event || window.event || arguments.callee.caller.arguments[0];
		            
				     if(e && e.keyCode==13){ // enter 键
				         //要做的事情
				    	 event_ref();
				    }
				}
	 //////////////////////////////////////////////////////////////////
		//****绑定事件
		//绑定新增事件
		function event_save(){
			/*_edit(0);*/
			//_add();
		}
		//保存预约登记
		function _add(){
			// 参数需要保存的表单，保存url,
	 		var json=opt_all.registration.serialize();
	 		common_ajax(json, opt_all.url_save, function(response){
	 			//返回信息
	 			var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
			    msg.show();	
			    					    
	 		});
	 		//保存联系人或者修改联系人
//	 		common_ajax(json, opt_all.url_linkMan, function(response){
	 				
//	 		});
	 		
//	 			setTimeout(function(){
//				//跳转页面
//				window.location.href=basePath+"business/destroy/preserve/list.jsp"
//				},3000)
	 		
		}
		//跳转新增单位页面
		function event_add(){
			window.location.href=basePath+"business/destroy/companyAdd/list.jsp"
		}
		//筛选预约单位
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
		
	    //查询联系单位模糊查询从新加载窗体
	    function _refresh(){
	    	
	    	loadChoseData();
	    }  
	    
		// 重置窗体
		function _reset(){
			opt_all.info_form.data('bootstrapValidator').resetForm(false);
		}
		//公开函数
		this.reset=function(){
			_reset();
		}
		/////////////////////////////////////////////////
		this.check=function(){
			checkMessage();
			
		}
		function checkMessage(){
			$("#formRegistration").bootstrapValidator({
	        	group: '.rowGroup',
	        	/*container: 'tooltip',*/
	            message: 'This value is not valid',
	            feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
	            fields: {
	            	
	            	"registrationFormBean.registrationInfoBean.departUnitCode": {
	                    message: '单位名称',
	                    validators: {
	                        notEmpty: {
	                            message: '单位名称不能为空',
	                        },
	                        stringLength: {
	                            min: 5,
	                            max: 50,
	                            message: '单位地址长度必须在10到50位之间'
	                        },
	                       
	                    }
	                },
	                "linkManFormBean.linkManInfoBean.linkManName": {
	                	message: '联系人',
	                	validators: {
	                		notEmpty: {
	                			message: '联系人不能为空',
	                		},
	                		stringLength: {
	                			min: 2,
	                			max: 8,
	                			message: '联系人名称2到8位之间'
	                		},
	                		
	                	}
	                },
	            	
	            	
	            	"registrationFormBean.registrationInfoBean.orderAddress": {
	                    message: '单位地址验证失败',
	                    validators: {
	                        notEmpty: {
	                            message: '单位地址不能为空',
	                             
	                        },
	                        stringLength: {
	                            min: 5,
	                            max: 50,
	                            message: '单位地址长度必须在5到50位之间'
	                        },
	                       
	                    }
	                },
	                /*"registrationFormBean.registrationInfoBean.linkTele": {
	                    validators: {
	                        notEmpty: {
	                            message: '固定电话'
	                        },
	                        stringLength: {
	                            min: 5,
	                            max: 12,
	                            message: '固定电话长度为5到12位'
	                        },
	                        regexp: {
	                            regexp: /^[0-9]*$/,
	                            message: '固定电话只能数字'
	                        }
	                    }
	                },*/
	                "registrationFormBean.registrationInfoBean.linkPhone": {
	                	validators: {
	                		notEmpty: {
	                			message: '联系人手机不能为空'
	                		},
	                		stringLength: {
	                            min: 11,
	                            max: 11,
	                            message: '固定电话长度为5到12位'
	                        },
	                		regexp: {
	                            regexp: /^[0-9]*$/,
	                            message: '固定电话只能数字'
	                        }
	                	}
	                }
	            },
	            submitHandler: function (validator, form, submitButton) {
	            	addMessage()
	            }
			});
		}
		/////////////////////////////////////////////////////////////////////////////////////////////
		//加载单位基础信息
		this.Load_Chose=function Load_ChoseSelectData(){
			loadChoseData();
		}
		function loadChoseData(){
			//所有编辑页面下拉框加载 单位名称
	        $('#departUnitName').empty();
			Load_EditSelectData_TreenmSysDept( $('#departUnitName'),function(){
	                
			});
		}
		//加载单位
		function Load_EditSelectData_TreenmSysDept(ctl_select,callback){
			var urlCompany=basePath+"company/company!listSelect.action";
			var parms=$.extend({},g_SelectParms,
					{
					"companyFormBean.searchName"	:opt_control.searchInput.val() // 查询关键字
					}
			);
			//加载下拉框数据
//			comm_loadSelectDataChooseSmall(urlCompany,parms,ctl_select,callback);
			
		}
		//加载联系人方法
		function getLinkMan(){
			 $('#box').empty();
				Load_EditSelectData_LinkMan( $('#box'),function(){
					
		                //comm_chose_init($('#linkMan'));
				})
		}
		//加载联系人
		function Load_EditSelectData_LinkMan(ctl_select,callback){
			var code =$("#departUnitName").val();
			var url=basePath+"linkMan/linkMan!listLinkManName.action";
			var parms=$.extend({},g_SelectParms,
					{
						"linkManFormBean.linkManInfoBean.unitCode":code
					}
			);
			//加载下拉框数据
//			comm_loadSelectDataChooseSmall(url,parms,ctl_select,callback);
			
			//加载联系人电话
			setTimeout(function(){
				//将下拉值赋值到输入框
				var name =$("#box").find('option:selected').text();
				var linkManCode =$("#box").val();
				$("#linkManName").val(name);
				$("#linkMan").val(linkManCode)
				getLinkManPhone(linkManCode);
				
				},500)
			
		}
		function getOrderAddress(code){
			var url = basePath+"company/company!getOrderAddress.action"+"?companyFormBean.companyInfoBean.departUnitCode="+code;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
				var data =response.infoBean
				for(var key in data ){
					if(key==2){
						return
					   }
					$("#orderAddress").val(data[key].departUnitAddress);
				}
			});
		}
		function getLinkManPhone(code){
			$("#linkTele").val("");
			$("#linkPhone").val("");
			
			var url = basePath+"linkMan/linkMan!linkManPhone.action"+"?linkManFormBean.linkManInfoBean.linkManCode="+code;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
				var data =response.infoBean
				for(var key in data ){
					if(key==2){
						return
					   }
					$("#linkTele").val(data[key].linkManTele);
					$("#linkPhone").val(data[key].linkManPhone);
				}
			});
		}
		//=======================================
		
		//预约单位改变事件onchange 加载联系人
		function changeCompany(){
			var code =$("#departUnitName").val();
			//清除地址栏信息
			$('#orderAddress').empty();
			//清除联系人下拉框并且加载联系人
			 getLinkMan();
			//加载地址
			getOrderAddress(code);
			//标题
			var title = $("#departUnitName").find("option:selected").text();
			
			$("#bookTitle").val(code);
			setTimeout(function(){
			$('#formRegistration').bootstrapValidator('revalidateField', 'registrationFormBean.registrationInfoBean.orderAddress');
			checkMessage();
			},500)
		}
		
		//联系人改变事件 加载联系人电话手机
		function changeLinkMan(){
			var code =$("#box").val();
			var name =$("#box").find('option:selected').text();
			$("#linkMan").val(code)
			$("#linkManName").val(name);
			getLinkManPhone(code)
			setTimeout(function(){
			$('#formRegistration').bootstrapValidator('revalidateField', 'linkManFormBean.linkManInfoBean.linkManName');
			$('#formRegistration').bootstrapValidator('revalidateField', 'registrationFormBean.registrationInfoBean.linkPhone');
			checkMessage();
			},500)
		}
		/////////////////////////////////////////////////////////////////////////////////
	};
})(jQuery);
			

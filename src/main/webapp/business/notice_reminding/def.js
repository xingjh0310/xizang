(function($) {
	var globle=sessionStorage.getItem("engineeringNm");
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
			,info_form		:$('#notice_inform')    // 新增和编辑对应的表单，注意和info_dialog的区别
			,select_flag    :$('#select_flag')  //当前选中的审核状态
			,btn_save		:$('#notice_save')		//保存按钮
			,tree_dialog	:$('#tree_dialog')		//树形菜单
			,receiveDept	:$('#notice_receiveDept') 	//接收部门
			,dept			:$('#receiveDept') //添加页面选择部门
			,back			:$('#notice_back')
			
			
			
		}
		//B、数据状态参数变量
		var opt_state={
			 flag_cur		:0 ////审核状态,当前List状态 flag=0
			,flag_new		:1 ////审核状态,审核后的状态     flag=1				
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"notice/notice!list.action"			//查询数据URL
			,url_edit		:basePath+"notice/notice!edit.action"			//编辑数据URL
			,url_save		:basePath+"notice/notice!save.action"			//保存数据URL
			,url_remove		:basePath+"notice/notice!removeids.action"		//删除数据URL
			,url_flag 		:basePath+"notice/notice!flag.action"			//审核数据URL
		}

		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_state,opt_url,option));
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
				$("#tbinfo tbody tr[data-uniqueid="+rows.id+"]").addClass("success");
			}
			//不选中时颜色恢复
			function onUncheck(rows){
				$("#tbinfo tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
			}
			
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				//查询条件
				var opt_parms={
					// 在此增加查询条件
					 "noticeFormBean.searchName"			:opt_control.searchInput.val(), 	// 查询关键字
					 "noticeFormBean.notice.receiveDept"	:$("#notice_receiveDepts").val(), 	// 接收部门
					 "noticeFormBean.notice.releaseDept"	:$("#notice_releaseDepts").val(),	// 发布部门
					 "noticeFormBean.notice.classify"		:$("#notice_classify").val(), 		// 分类
					 "noticeFormBean.notice.engineerCode"	:globle,
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "noticeFormBean.pageBean.limit"	: params.limit   // 页面大小
			      ,"noticeFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,"noticeFormBean.pageBean.sort"		: params.sort  // 排序列名
			      ,"noticeFormBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
			    };
			    temp=$.extend(temp,opt_parms,opt);
							    return temp;
			}
			// 双击事件
			function onDblClickRow(row){
				$("#tbinfo tbody tr[data-uniqueid="+row.ID+"]").addClass("success");
				if (row) {
					list(row.ID);
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
			opt_all.receiveDept.bind("click",receiveDept);
			opt_all.dept.bind("click",edit_dept);
			opt_all.back.bind("click",back);
			
			// 保存button类型为submit
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				var typeNm=$("#typeNm").val();
				if(typeNm == ""){
					var msg = new $.zui.Messager("消息提示：设备类型不可为空", {placement: "center",type:"primary"});
				    msg.show();	
				    return;
				}
				_save();
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// //校验数据正确
			});	
			$("#tree_di").bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				
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
		//发布
		this.flag=function(id){
			_flag(id);
		}
		//取消发布
		this.cancelFlag=function(id){
			_cancelFlag(id);
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
		//加载 并调用  查看设备参数
		function LoadPara(id,onlyread){
			var url = opt_all.url_edit+"?noticeFormBean.notice.id=" + id;
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
			
			window.location.href=basePath+"business/notice_reminding/notice_edit.jsp?id="+id;
			
		}
		
		function list(id){
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			Load_EditSelectData( 
					function(){ 
						LoadEditListData(id);
					}
			);
		}
		
		function LoadEditListData(id){
			$("#notice_save").hide();
			var url = opt_all.url_edit+"?noticeFormBean.notice.id="+id;
			
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
				var json=response.infoBean;
				
				var	bean =json[0] 
				var htmlvalue=bean.context;
				htmlvalue= htmlvalue.replaceAll("&lt;", "<");
				htmlvalue= htmlvalue.replaceAll("&gt;", ">");
				htmlvalue= htmlvalue.replaceAll("&quot;", "\"");
				$("#ht").html(htmlvalue);
				
				comm_loadFormData_flag_html(bean,"")
				
				opt_all.info_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					//_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。

	            });
				opt_all.info_dialog.on('hidden.zui.modal',function(){
					$("#tbinfo tbody tr[data-uniqueid="+id+"]").removeClass("success");
				})
			});
		}
		
	    // 保存数据
		function _save() {
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
			//富文本内容
			var content = "";
			var cla=$("#classify").val()
			var rec= $("#receiveDepts").val()
			if(cla==""||cla==null||rec==""||rec==null){
				var msg = new $.zui.Messager("消息提示：请填写必填项目", {placement: "center",type:"primary"});
			    msg.show();	
				return
			}
			
			ue.ready(function() {
				content = ue.getContent();
			});
	 		var json=opt_all.info_form.serialize();
	 		var url=opt_all.url_save+"?noticeFormBean.notice.context="+encodeURIComponent(htmlEscape(content));
	 		common_ajax(json, url, function(response){
	 				
	 			 var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
				    msg.show();	
	 			
	 		});
	 		setTimeout(function(){
	 			window.location.href = document.referrer;
			}, 3000);
	 		
		}
		
		function htmlEscape(text){ 
			 return text.replace(/[<>"&]/g, function(match, pos, originalText){
			    switch(match){
			    case "<": return "&lt;"; 
			    case ">":return "&gt;";
			    case "\"":return "&quot;"; 
			 } 
			}); 
		}
		
	    // 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"ID");
           if (ids.length==0) return;
           
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");
           
           var url= opt_all.url_remove+"?noticeFormBean.ids="+ids
            
			confirm("通知","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
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
			var url= opt_all.url_remove+"?noticeFormBean.ids="+id;
			$(".table tbody tr[data-uniqueid="+id+"] td").addClass("row-bcground");
			confirm("通知","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
				   if(result){
					   common_ajax(null, url, function(response){
						    _refresh();
						    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
						    msg.show();	
				 		}); 
	               }
				}); 
		}
		//发布
		function _flag(id){
			var url= opt_all.url_flag+"?noticeFormBean.ids="+id+"&noticeFormBean.notice.state="+opt_all.flag_new;
			confirm("通知","您确定要发布该条记录吗？","icon-remove-sign", function(result) {
			  if(result){
					   common_ajax(null, url, function(response){
						    _refresh();
						    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
						    msg.show();	
						    
				 		}); 
	               }
				}); 
			
		}
		//取消发布
		function _cancelFlag(id){
			var url= opt_all.url_flag+"?noticeFormBean.ids="+id+"&noticeFormBean.notice.state="+opt_all.flag_cur;
			confirm("通知","您确定要取消发布该条记录吗？","icon-remove-sign", function(result) {
				  if(result){
						   common_ajax(null, url, function(response){
							    _refresh();
							    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
							    msg.show();	
							    
					 		}); 
		               }
				}); 
		}
		//接收部门
		function receiveDept(){
			dept("notice_receiveDept")
		}
		
		function edit_dept(){
			dept("receiveDept")
		}
		function dept(id){
			
			var url=basePath+"mail/mail!zTree.action";
			common_ajax(null,url, function(response) {
				loadzTreeInfo(response.zTree,id); //初始化ztree
			});
			
			opt_all.tree_dialog.modal({
				show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
//				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
			});
		}
		
		function loadzTreeInfo(zTreeArray,id){
			var code = $('#'+id+"s").val();
			
			var setting = {
					data: {
						simpleData: {
							enable: true,
							 idKey: "id",
							 pIdKey: "pId",
							 rootPId: ""
						},
					},
					view: {
						selectedMulti: false,
						},
					check: {
							enable: true,
							chkStyle: "checkbox",
							chkboxType:{ "Y" : "s", "N" : "" }
						},
					callback: {
							onCheck: zTreeOnCheck
							
						}
				};
			var zNodes = zTreeArray;
			var pid ;
			
			var strs= new Array(); //定义一数组 
			strs=code.split(","); //字符分割 
			
			for(var i=0;i<zNodes.length;i++){
				
				for(var j=0;j<strs.length;j++){
					
					if(strs[j]==zNodes[i].nm){
						zNodes[i].checked='true'; //默认勾选
						pid = zNodes[i].pId
					}
				}
				
			}
			for(var i=0;i<zNodes.length;i++){
				if(pid==zNodes[i].id){
					zNodes[i].open='true'; //默认展开
				}
			}
			var nm = "";
			//选中/取消
		    function zTreeOnCheck(event,treeId,treeNode) {
		    	
		    	var listNode="";
				var name="";
		        var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
		        nodes=treeObj.getCheckedNodes(true)
		     	 
		        for(var i=0;i<nodes.length;i++){
		      	listNode+=nodes[i].nm+",";
		      	name+=nodes[i].name+",";
		        }
				
				$('#'+id+"s").val(listNode.substring(0,listNode.length-1))	//节点code
				$('#'+id).val(name.substring(0,name.length-1)); 
		    	
		    	
			}
		    function check_all(){
		    	
		    	
		    	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	    			treeObj.checkAllNodes(true);
		    	
		    	var tree= treeObj.getNodes();
		    	
		    	var listNode="";
				var name="";
		    	for(var i=0;i<tree.length;i++){
		    		tree[i].checked=true;
			      	listNode+=tree[i].nm+",";
			      	name+=tree[i].name+",";
			     }
		    	$('#'+id+"s").val(listNode.substring(0,listNode.length-1))	//节点code
				$('#'+id).val(name.substring(0,name.length-1)); 
		    	
		    }
		    function cancel_all(){
		    	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		    		treeObj.checkAllNodes(false);
		    	var tree= treeObj.getNodes();
		    	for(var i=0;i<tree.length;i++){
		    		tree[i].checked="false";
			      	
			     }
		    	$('#'+id+"s").val("")	//节点code
				$('#'+id).val(""); 
		    	
		    }
		    
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#check_all").bind("click", {isParent:true}, check_all); //全选
			$("#cancel_all").bind("click", {isParent:true}, cancel_all); //全取消
		}
		
		/////////////////////////////////////////////////////////////////
		// 初始化审核,公开函数
		this.InitFlag=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.btn_flag.bind("click",event_flag);
		}
	 //////////////////////////////////////////////////////////////////
		//****绑定事件
		//返回
		function back(){
			window.location.href = document.referrer;			
		}
		
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
		this.flash=function(nm){
			globle=nm
			_refresh()
		}
		
	    // 刷新
	    function _refresh(){
	    	opt_all.table.bootstrapTable('refresh');
	    }  
	    
		// 重置窗体
		function _reset(){
			opt_all.info_dialog.data('bootstrapValidator').resetForm(false);
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
		
		//所有设备select
		function euipmentInfoSelect(){
			var url=basePath+"info/info!queryAllEuipmentInfoInterface.action";
			$("#parentCode").empty();
			var html="<option value=''>请选择</option>";
			common_ajax(null,url, function(response) {
				for(i=0;i<response.euipmentInfoList.length;i++){
						html+="<option value='"+response.euipmentInfoList[i].equipment_code+"'>"+response.euipmentInfoList[i].equipment_name+"</option>";
				}
				$("#parentCode").html(html);
			})
		}
		
		//查询销毁设备车select
		function destroyCarSelect(){
			var url=basePath+"info/info!queryDestroyCar.action";
			$("#destroyCar").empty();
			var html="<option value=''>请选择</option>";
			common_ajax(null,url, function(response) {
				for(i=0;i<response.destroyCarList.length;i++){
					if(response.destroyCarList[i].car_code>0){
						html+="<option value='"+response.destroyCarList[i].car_code+"'>"+response.destroyCarList[i].car_plate_number+"</option>";
					}
				}
				$("#destroyCar").html(html);
			})
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
		        	"noticeFormBean.notice.equipmentName": {
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
				$('#info_dialog').bootstrapValidator('revalidateField', 'noticeFormBean.notice.createDate');
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
		        	"noticeFormBean.notice.createDate": {
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
			

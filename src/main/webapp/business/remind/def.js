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
			,info_form		:$('#info_form')    // 新增和编辑对应的表单，注意和info_dialog的区别
			,select_flag    :$('#select_flag')  //当前选中的审核状态
			,btn_save		:$('#btn_save')		//保存按钮
			,select_flag	:$('#select_flag')	//阅读状态
			,read			:$('#read')			//已读
			,unread			:$('#unread')		//未读
			
		}
		//B、数据状态参数变量
		var opt_state={
			 flag_cur		:0 ////审核状态,当前List状态 flag=0
			,flag_new		:1 ////审核状态,审核后的状态     flag=1				
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"notice/notice!list.action?noticeFormBean.notice.classify=002"	//查询数据URL
			,url_edit		:basePath+"notice/notice!edit.action"				//编辑数据URL
			,url_save		:basePath+"info/info!saveEuipmentInfo.action"		//保存数据URL
			,url_remove		:basePath+"info/info!removeids.action"				//删除数据URL
			,url_flag 		:basePath+"notice/notice!read_flag.action"			//标记阅读数据URL
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
					 "noticeFormBean.searchName"			:opt_control.searchInput.val(), // 查询关键字
					// 在此增加查询条件
					 "noticeFormBean.notice.readState"		:$("#select_flag").val(),		//是否阅读
					 "noticeFormBean.notice.engineerCode"	:globle,
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "noticeFormBean.pageBean.limit"	: params.limit   // 页面大小
			      ,"noticeFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,"noticeFormBean.pageBean.sort"	: params.sort  // 排序列名
			      ,"noticeFormBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
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
			opt_all.btn_add.bind("click",event_add);
			opt_all.btn_del.bind("click",event_del);
			opt_all.btn_ref.bind("click",event_ref);
			opt_all.select_flag.bind("change",select_flag);
			opt_all.read.bind("click",read);
			opt_all.unread.bind("click",unread);
			
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
		}
	    // 修改记录，调出窗体,公开函数
		this.edit=function(id,onlyread) {
			var ids=verdict_del_update(opt_all.table,"id",id);
			if(ids == id || ids == "" || ids == null){
				_edit(id);
			}
		}
	
		function list(row){
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
					function(){ 
						LoadEditListData(row);
					}
			);
		}
		
		function LoadEditListData(row){
			
			var url = opt_all.url_edit+"?noticeFormBean.notice.id="+row.ID;
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
				var json=response.infoBean;
				var	bean =json[0] 
				
				$("#ht").html(bean.context)
				
				comm_loadFormData_flag_html(bean,"_n")
				opt_all.info_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。

	            });
				opt_all.info_dialog.on('hidden.zui.modal',function(){
					$("#tbinfo tbody tr[data-uniqueid="+row.ID+"]").removeClass("success");
				})
			});
		}
	
		//标记已读
		function read(){
			var ids=g_select_and_tip(opt_all.table,"ID");
	        if (ids.length==0) return;
	        
	        var url= opt_all.url_flag+"?noticeFormBean.ids="+ids+"&noticeFormBean.notice.readState="+opt_all.flag_new;
            
	           confirm("消息通知","您确定要标记已读？","icon-info", function(result) {
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
		//标记未读
		function unread(){
			var ids=g_select_and_tip(opt_all.table,"ID");
	           if (ids.length==0) return;
	           
	           var url= opt_all.url_flag+"?noticeFormBean.ids="+ids+"&noticeFormBean.notice.readState="+opt_all.flag_cur;
	            
	           confirm("消息通知","您确定要标记未读吗？","icon-info", function(result) {
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
	 //////////////////////////////////////////////////////////////////
		//****绑定事件
		function select_flag(){
			_refresh();
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
		//****绑定事件******end
		
	    // 刷新
	    function _refresh(){
	    	opt_all.table.bootstrapTable('refresh');
	    }  
	    
		// 重置窗体
		function _reset(){
			opt_all.info_form.data('bootstrapValidator').resetForm(false);
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
		
	};
})(jQuery);
			

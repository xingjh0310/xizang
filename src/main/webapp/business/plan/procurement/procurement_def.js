(function($) {
	$.System_procurement = function(option) {
		//option自定义参数覆盖
		//界面控件变量
		var opt_control={
				table			:$("#tbinfo")	//BootStrapTable的ID
			   ,searchInput		:$("#searchInput")	//模糊查询input
			   ,material_type	:$("#material_type")	//物料种类select
			   ,material_name	:$("#material_name")	//物料名称select
			   ,btn_out			:$("#btn_out")	//导出按钮
			   ,btn_into		:$("#btn_into")	//导入按钮
			   ,btn_del			:$("#btn_del")	//批量删除按钮
			   ,btn_ref			:$("#btn_ref")	//查询按钮
			   ,info_dialog		:$('#info_dialog')  // 新增和编辑对应的窗体
			   ,info_form		:$('#info_form')    // 新增和编辑对应的表单
		}
		
		//请求地址URL
		var opt_url={
			url_list		:basePath+"business/plan/procurement/json.jsp"//查询数据URL
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
			         //,onDblClickRow	:onDblClickRow		// 行双击事件
			         ,onSort		:onSort             // 排序事件
		             ,rowStyle		:comm_rowStyle		//行样式，可自定义
		             ,onLoadSuccess	:comm_onLoadSuccess //数据加载错误
		             ,onCheckAll	:onCheckAll			//全选
			         ,onCheck		:onCheck			//单选
			         ,onUncheck		:onUncheck			//不选
			         ,onUncheckAll	:onUncheckAll		//全不选
			         
					}));
			
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				//查询条件
				var opt_parms={
					 ""		:opt_control.searchInput.val() // 查询关键字
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       ".pageBean.limit"	: params.limit   // 页面大小
			      ,".pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,".pageBean.sort"		: params.sort  // 排序列名
			      ,".pageBean.sortOrder": params.order// 排位命令（desc，asc）
			    };
			    temp=$.extend(temp,opt_parms,opt);
//			    	alert(temp);
							    return temp;
			}
			
			// 双击事件
			function onDblClickRow(row){
				$("#tbinfo tbody tr[data-uniqueid="+row.id+"]").addClass("success");
				if (row) {
					list(row.id);
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
			opt_all.searchInput.bind("keydown",carriage);
		}
		
		//回车事件
		function carriage(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
		     if(e && e.keyCode==13){ // enter 键
		         //要做的事情
		    	 event_ref();
		    }
		}
		
		// 修改记录，调出窗体,公开函数
		this.edit=function(id,onlyread) {
			var ids=verdict_del_update(opt_all.table,"id",id);
			if(ids == id || ids == "" || ids == null){
				LoadEditData(id);
			}
			
		}
		
		//修改窗体
		function LoadEditData(id){
			opt_all.info_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				
			});
			
		}
		
		//单条删除
		this.del=function(id){
			var ids=verdict_del_update(opt_all.table,"id",id);
			if(ids == id || ids == "" || ids == null){
				_del(id);
			}
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
//			opt_all.info_form.data('bootstrapValidator').resetForm(false);
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
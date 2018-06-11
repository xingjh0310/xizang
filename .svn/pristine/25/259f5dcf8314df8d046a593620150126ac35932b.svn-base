(function($) {
	$.System_SysStaff_SysRole = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
				table 			:$('#tbinfo_ListnmSysRole')		//BootStrapTable的ID
		}
		//C、请求地址URL
		var opt_url={
			  url_rela			:basePath+"system/sysrela!rela.action?type_="+audmins  //关联关系URL
			 ,url_rela_sq		:basePath+"system/sysrela!relasq.action"			//关联关系授权URL
			 ,url_rela_qx		:basePath+"system/sysrela!relaqx.action"			//关联关系取消URL
		}

		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend({},opt_control,opt_url,option));
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		//查询条件及页面分页信息设置
		var opt_tb_query={};
		this.Init_TB_QueryParms=function(opt){
			opt_tb_query=$.extend({},opt_tb_query,opt);
		}
		// 提交查询函数
		function queryParams(params) {  // 配置参数

		    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		       "formBean.pageBean.limit"	: params.limit   // 页面大小
		      ,"formBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
		      ,"formBean.pageBean.sort"		: params.sort  // 排序列名
		      ,"formBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
			  ,"formBean.infoBean.taName"	:"sys_staff"
			  ,"formBean.infoBean.tbName"	:"sys_role"
			  ,"formBean.infoBean.taNm"		:""
			  ,"formBean.parmBean.parm1"	:"list"		//tbName表B是列表，  list-列表  tree-树状
		    };
		    temp=$.extend({},temp,opt_tb_query);
		    return temp;
		}
		// 排序事件
		function onSort(name, order){
			_refresh;
		}
		
		//D、BootStrapTable参数
		var opt_tb_base={
	      	  url			:opt_all.url_rela   // 请求后台的URL（*）
	         ,queryParams	:queryParams		// 传递参数（*）
	         ,onSort		:onSort             // 排序事件
             ,rowStyle		:comm_rowStyle		//行样式，可自定义
             ,onLoadSuccess	:comm_onLoadSuccess //数据加载错误	
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

	    // 刷新
	    function _refresh(){
	    	opt_all.table.bootstrapTable('refresh');
	    }  
	    //公开函数
	    this.refresh=function(){
	    	_refresh();
	    }
		
		this.rela_sq=function(callback){
		    temp=$.extend({},opt_tb_query);
			common_ajax(temp,opt_all.url_rela_sq, function(response) {
				callback(response);
			});		
			
		}
		
	    
};
})(jQuery);
			

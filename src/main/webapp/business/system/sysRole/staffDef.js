(function($){
	$.SysStaffRefAcct=function(option){
		var opt_control={
			 table 			:$('#staffInfo_table')		//BootStrapTable的ID
			,searchInput	:$('#searchName')	//模糊查询input
			,info_dialog	:$('#set_user_dialog')  // 新增和编辑对应的窗体，注意和info_form的区别
			,info_form		:$('#info_form')    // 新增和编辑对应的表单，注意和info_dialog的区别
			,staffName_selec:$('#staffName_selec')   //人员
			,btn_ref		:$('#btn_ref_staffInfo') //查询按钮
		};
		var opt_url={
			 url_list		:basePath+"system/sysstaffrefacct!list.action"	//查询数据URL
			,url_save		:basePath+"system/sysstaffrefacct!save.action"	//保存数据URL
		};
		var opt_all=($.extend({},opt_control,opt_url,option));
		var opt_tb_query={};
		this.Init_TB_QueryParms=function(opt){
			opt_tb_query=$.extend({},opt_tb_query,opt);
		};
		var opt_tb_base={
		     url			:opt_all.url_list   
		    ,queryParams	:queryParams		
		    //,onSort		    :onSort   
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
				"mSysStaffRefAcctFormBean.mSysStaffInfoBean.treenmSysDept":$("#treenmSysDept_").val(),
				"mSysStaffRefAcctFormBean.mSysStaffInfoBean.nm":$('#staffName_selec').val()
			};
			temp=$.extend({},temp,opt_tb_query);
			return temp;
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
			opt_all.btn_ref.bind("click",btn_ref); //查询按钮
		}
		function btn_ref(){
			opt_all.table.bootstrapTable('refresh');
		}
		this.initSelectData=function(){
			loadStaffName(); 
		}
		//加载人员信息
		function loadStaffName(){
			opt_control.staffName_selec.empty();
			opt_control.staffName_selec.append("<option value=''>请选择</option>")
			common_ajax(null, opt_url.url_list, function(response){
				var rows=response.rows;
				for(var i=0;i<rows.length;i++){
					opt_control.staffName_selec.append("<option value='"+rows[i].NM+"'>"+rows[i].STAFFNAME+"</option>");
				}
			})
		}
	}
})(jQuery);
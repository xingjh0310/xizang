(function($) {
	$.System_SysRole_SysMenu = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			treeGrid 			:$('#tbinfo_TreenmSysMenu')		//BootStrapTable的ID
		}
		//C、请求地址URL
		var opt_url={
			  url_rela			:basePath+"system/sysrela!rela.action?type="+audmins //关联关系URL
			 ,url_rela_sq		:basePath+"system/sysrela!relasq.action"			//关联关系授权URL
			 ,url_rela_qx		:basePath+"system/sysrela!relaqx.action"			//关联关系取消URL
		}

		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend({},opt_control,opt_url,option));
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		//设置查询条件
		var opt_tb_query={};
		this.Init_TB_QueryParms=function(opt){
			opt_tb_query=$.extend({},opt_tb_query,opt);
		}
		//加载数据
		this.Init_TB_Data=function(){
			_LoadData();
		}
	    //加载treegrid数据
		function _LoadData(){
		    var temp = {   
				  "formBean.pageBean.limit": 100000000   // 页面大小
				 ,"formBean.pageBean.offset": 0  // 当前记录偏移条数	
		 	      // 在此增加查询条件
				 ,"formBean.infoBean.taName":"sys_role"
				 ,"formBean.infoBean.tbName":"sys_menu"
				 ,"formBean.infoBean.taNm":""
				 ,"formBean.parmBean.parm1"	:"tree"		//tbName表B是列表，  list-列表  tree-树状
				 
		 	    };
		    
		    temp=$.extend({},temp,opt_tb_query);
			common_ajax(temp,opt_all.url_rela, function(response) {
				var html="";
				opt_all.treeGrid.html("");
				/**表头*/
			html="<thead><tr height='40' class='warning' >"
				+"<th>菜单功能名称</th>"
				+"<th width='150'>菜单编码</th>";
			html+="<th width='70'>授权状态</th>";
			html+="<th width='60'>授权</th>";
			html+="</tr></thead>";	
			/**内容*/
			var rows=response.rows;
			for (var i = 0 ; i< rows.length;i++){
				    html=html+" <tr class='treegrid-"+rows[i].NM;
				if (rows[i].PNM.length>0){
					html=html+" treegrid-parent-"+rows[i].PNM;
				}
				if (rows[i].RELAID>0){
					html=html+" success ";   
				}
					html=html+"' ondblclick='_edit("+rows[i].ID+",true)'>";
					html=html+"   <td>"+rows[i].NAME+"</td>";
					html=html+"   <td>"+rows[i].CODE+"</td>";
					
					if (rows[i].RELAID>0){
						html=html+"   <td>已授权</td>";
					} else {
						html=html+"   <td></td>";
					}
					html=html+"   <td>";
					
					if (rows[i].RELAID>0){
						html=html+"   &nbsp;<a href='#' title='取消授权' onclick='rela_sq(\""+rows[i].CODE+"\",\"qx\")'><i class='icon icon-times'></i></a>"				
					} else {
						html=html+"   &nbsp;<a href='#' title='功能授权' onclick='rela_sq(\""+rows[i].CODE+"\",\"sq\")'><i class='icon icon-check'></i></a>"
					}
					html=html+"   </td>";
	            	html=html+" </tr>";
			}
			
			opt_all.treeGrid.append(html);
			
			opt_all.treeGrid.addClass("table");
			opt_all.treeGrid.addClass("table-striped");	 //
			opt_all.treeGrid.addClass("table-bordered"); //边框
			opt_all.treeGrid.addClass("table-condensed");//更为紧凑
			
			opt_all.treeGrid.treegrid({
				 initialState:'expanded'
				,expanderExpandedClass:  'icon icon-minus'
	            ,expanderCollapsedClass: 'icon icon-plus'
	        });	
			
		}); //common_ajax
	}
	
		
	this.rela_sq=function(callback){
	    temp=$.extend({},opt_tb_query);
		common_ajax(temp,opt_all.url_rela_sq, function(response) {
			callback(response);
		});		
	}
  };
})(jQuery);
			

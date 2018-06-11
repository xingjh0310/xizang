(function($) {
	$.System_SysDeptQ = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 treeGrid 		:$('#tbinfo_TreenmSysDept')		//BootStrapTreeList的ID--
			,select_pcode	:$('#select_root_SysDept') 	//选中根节点--
		}
		//B、数据状态参数变量
		var opt_state={
			 flag_cur		:0 ////审核状态,当前List状态 flag=0
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"system/sysdept!list.action"			//查询数据URL
			,url_listroot   :basePath+"system/sysdept!listroot.action"		//获取根数据URL--
		}

		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend({},opt_control,opt_state,opt_url,option));
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		//加载根数据
		this.LoadRootData=function(ctl_select){
			_LoadRootData(ctl_select);
		}
		// 动态加载页面基础数据：下拉框数据
		function _LoadRootData(){
			//加载根单位下拉框数据
			common_ajax(null,opt_all.url_listroot, function(response) {
				ctl_select.empty();
				ctl_select.append($("<option>").text("显示全部数据").val(""));
				for (var i = 0 ; i< response.rows.length;i++){
					var option = $("<option>").text("("+response.rows[i].code+")"+response.rows[i].name).val(response.rows[i].code);
					ctl_select.append(option);
				}
			});
			
		}
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
				 ,"formBean.infoBean.code":opt_all.select_pcode.val()
				 ,"formBean.infoBean.flag":""
		 	    };
		    
		    temp=$.extend({},temp,opt_tb_query);
			common_ajax(temp,opt_all.url_list, function(response) {
				var html="";
				opt_all.treeGrid.html("");
				/**表头*/
			html="<thead><tr>"
				+"<th>名称</th>"
				+"<th width='60'>操作</th>"
				+"</tr></thead>";	
			/**内容*/
			var rows=response.rows;
			for (var i = 0 ; i< rows.length;i++){
				html=html+" <tr class='treegrid-"+rows[i].NM;
				if (rows[i].PNM.length>0){
					html=html+" treegrid-parent-"+rows[i].PNM;
				}
					html=html+"'>"
                        +"   <td>"+rows[i].NAME+"</td>"
	                    +"   <td>"
	                    +"       &nbsp;<a href='#' title='过滤' onclick='Select_TreenmSysDept(\""+rows[i].NM+"\")'><i class='icon icon-filter'></i></a>"
	                    +"   </td>"
                        +" </tr>";
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
		});
	}
  };
})(jQuery);
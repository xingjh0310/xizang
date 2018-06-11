(function($) {
	$.System_deliverGoods = function(option) {
		//option自定义参数覆盖
		//界面控件变量
		var opt_control={
				table			:$("#tbinfo")			//BootStrapTable的ID
			   ,searchInput		:$("#searchInput")		//模糊查询input
			   ,material_type	:$("#material_type")	//物料种类select
			   ,material_name	:$("#material_name")	//物料名称select
			   ,btn_add			:$("#btn_add")			//新增发货通知
			   ,material		:$("#material_add")		//新增物资信息
			   ,btn_out_page	:$("#btn_out_page")		//导出当前页按钮
			   ,btn_out			:$("#btn_out")			//导出全部按钮
			   ,btn_into		:$("#btn_into")			//导入按钮
			   ,btn_del			:$("#btn_del")			//批量删除按钮
			   ,btn_ref			:$("#btn_ref")			//查询按钮
			   ,info_dialog		:$('#info_dialog')  	// 新增和编辑对应的窗体
			   ,info_form		:$('#info_form')   	 	// 新增和编辑对应的表单
			   ,details			:$('#deliverGoods_details')//详情窗体
			   ,data_body		:$('#data_body')		//table行
		}
		
		//请求地址URL
		var opt_url={
			url_save			:basePath+"transport/transport!deliver.action", 	//保存发货数据URL
			url_list			:basePath+"plan/supply!queryAllSupplyInfo.action",	//查询数据URL
			url_downLoad		:basePath+"transport/transport!downLoad.action",
			url_materialData	:basePath+"contInfo/contInfo!queryAllMaterial.action" 	//查询所有物料信息
		}
		
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
		var tableParam;
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
			
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				//查询条件
				tableParam=params;
				var opt_parms={
					 "fSupplyFormBean.searchName"			:opt_control.searchInput.val() // 查询关键字
					,"fSupplyFormBean.materielType"			: $("#material_type").val()	//物资类型
					,"fSupplyFormBean.materielBase"			: $("#material_name").val()	//物资名称
					,"fSupplyFormBean.mSupply.engineerCode"	: sessionStorage.getItem("engineeringNm")
					,"fSupplyFormBean.mSupply.planState"	:"2"
					,"fSupplyFormBean.mSupply.deliveryState":"0"
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "fSupplyFormBean.pageBean.limit"		: params.limit   // 页面大小
			      ,"fSupplyFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,"fSupplyFormBean.pageBean.sort"		: params.sort  // 排序列名
			      ,"fSupplyFormBean.pageBean.sortOrder"	: params.order// 排位命令（desc，asc）
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
			opt_all.btn_add.bind("click",event_add)
			opt_all.btn_ref.bind("click",event_ref);
			opt_all.btn_del.bind("click",event_del);
			opt_all.material.bind("click",add_material)		//新增物资信息
			opt_all.btn_out.bind("click",downLoadAllDemand);  //导出
			opt_all.btn_out_page.bind("click",downLoadDemandInPage);
				
		}
		opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
			e.preventDefault(); // 去掉默认提交事件
			// 校验数据正确,执行保存数据
			_save();
		}).on("error.form.bv", function(e) {
			e.preventDefault(); // 去掉默认提交事件
			// //校验数据正确
		});
		
		// 修改记录，调出窗体,公开函数
		this.edit=function(id,onlyread) {
			var ids=verdict_del_update(opt_all.table,"id",id);
			if(ids == id || ids == "" || ids == null){
				LoadEditData(id);
			}
			
		}
		//双击查看详情
		function list(row){
			//var url = opt_all.url_transfer+"?materielFormBean.deliveryInfoBean.id=" + id;
			// 动态加载页面数据
			//common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
			//comm_loadFormDataList(response.infoBean);
			comm_loadFormData_flag_html(row,'_supply');
			var state=row.PLANSTATE;
			if(state==1){
				$("#PLAY_STATE_supply").html("未执行");
			}else if(state==2){
				$("#PLAY_STATE_supply").html("已执行");
			}
			
			opt_all.details.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				
			});
			opt_all.details.on('hidden.zui.modal',function(){
				$("#tbinfo tbody tr[data-uniqueid="+row.ID+"]").removeClass("success");
			 });
			
		}
		
		//新增数据和修改数据窗体
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
		//绑定新增发货通知事件
		function event_add(){
			
			LoadEditData(0);
			
		}
		/*物资信息新增一行*/
		function add_material(){
			 material()
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
			//opt_all.info_form.data('bootstrapValidator').resetForm(false);
		}
		/*拼接table图表*/
		function material(detailData){
			common_ajax(null, opt_all.url_materialData, function(response){
				var material_data = response.materials;
				if(detailData == undefined || detailData == null){
					var num = opt_all.data_body.find("tr").length +1;
					var selHtml = "<select required onchange='material.getMaterialInfo(this)' style='height:30px;border: 0px;' data-type='text' class='form-control' name='materialCode'>";
					selHtml += 	"<option value=''>请选择物料</option>";
					for(var i=0;i<material_data.length;i++){
						selHtml += 	"<option value='"+material_data[i].MATERIELCODE+"'>"+material_data[i].MATERIELNAME+"</option>";
					}
					selHtml +="</select>";
					
					var html = "<tr>";
					html += "<td class='num-td' style='padding: 0px;text-align: center;line-height: 30px;'>" + num + "</td>";
					html += "<td style='padding: 0px;display:none;'><input data-type='text' class='form-control' name='materialId'></td>";
					html += "<td style='padding: 0px;display:none;'><input data-type='text' class='form-control' value='' name='code'></td>";
					html += "<td style='padding: 0px;'>" + selHtml + "</td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: center;'  data-type='text' class='form-control materielUnit'  name='meaUnit'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: left;'data-type='text' class='form-control materialNorms'  name='materialDesc'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;text-align: right;' data-type='text' class='form-control materiel-num' onkeyup='countTotalPrice()' name='useNum'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;'data-type='text' class='form-control price' name='returnNum'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: center;'data-type='text' class='form-control proposal-price' onkeyup='' name='identySituation'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;' data-type='text' class='form-control' name='remark'></td>";
					html += "<td style='padding: 0px;text-align: center;line-height: 30px;'>";
					html += 	"<a href='#' onclick='removeSelf(0,this)'>";
					html +=			"<button class='btn btn-xs btn-danger btn_del_color'>";
					html +=				"<div class='visible-md visible-lg'><i class='icon icon-trash'></i>&nbsp;删除</div>";
					html +=				"<div class='visible-xs visible-sm'><i class='icon icon-trash'></i></div>";
					html +=			"</button>";
					html +=		"</a>";
					html +=	"</td>";
					html += "</tr>";
					opt_all.data_body.append(html);
				}else {
					var num = opt_all.data_body.find("tr").length +1;
					
					var selHtml = "<select required onchange='material.getMaterialInfo(this)' style='height:30px;border: 0px;' data-type='text' class='form-control' name='materialCode'>";
					selHtml += 	"<option value=''>请选择物料</option>";
					for(var i=0;i<material_data.length;i++){
						if(detailData.MATERIALCODE == material_data[i].MATERIELCODE){
							selHtml += 	"<option selected='true' value='"+material_data[i].MATERIELCODE+"'>"+material_data[i].MATERIELNAME+"</option>";
						}else {
							selHtml += 	"<option value='"+material_data[i].MATERIELCODE+"'>"+material_data[i].MATERIELNAME+"</option>";
						}
					}
					selHtml +="</select>";
					
					var html = "<tr>";
					html += "<td class='num-td' style='padding: 0px;text-align: center;line-height: 30px;'>" + num + "</td>";
					html += "<td style='padding: 0px;display:none;'><input data-type='text' class='form-control' value='"+detailData.ID+"' name='materialId'></td>";
					html += "<td style='padding: 0px;display:none;'><input data-type='text' class='form-control' value='"+detailData.CODE+"' name='code'></td>";
					html += "<td style='padding: 0px;'>" + selHtml + "</td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: center;' readonly='true' data-type='text' class='form-control materielUnit' value='"+detailData.MEAUNIT+"' name='meaUnit'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;'data-type='text' class='form-control materialNorms' value='"+(detailData.MATERIALDESC==null?"":detailData.MATERIALDESC)+"' name='materialDesc'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;text-align: right;' data-type='text' class='form-control materiel-num' onkeyup='countTotalPrice()' value='"+(detailData.USENUM==null?"0":detailData.USENUM)+"' name='useNum'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;'  data-type='text' class='form-control price' value='"+(detailData.RETURNNUM==null?"0":detailData.RETURNNUM)+"' name='returnNum'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;'  data-type='text' class='form-control proposal-price' onkeyup='' value='"+detailData.IDENTYSITUATION+"' name='identySituation'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;'  data-type='text' class='form-control proposal-price' onkeyup='' value='"+detailData.INFORSITUATION+"' name='inforSitution'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;' data-type='text' class='form-control proposal-price' onkeyup='' value='"+detailData.USEDIRECTION+"' name='useDirection'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;' data-type='text' class='form-control proposal-price' onkeyup='' value='"+detailData.USETIME+"' name='useTime'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;' data-type='text' class='form-control' value='"+detailData.REMARK+"' name='remark'></td>";
					html += "<td style='padding: 0px;text-align: center;line-height: 30px;'>";
					html += 	"<a href='#' onclick='removeSelf("+detailData.ID+",this)'>";
					html +=			"<button class='btn btn-xs btn-danger btn_del_color'>";
					html +=				"<div class='visible-md visible-lg'><i class='icon icon-trash'></i>&nbsp;删除</div>";
					html +=				"<div class='visible-xs visible-sm'><i class='icon icon-trash'></i></div>";
					html +=			"</button>";
					html +=		"</a>";
					html +=	"</td>";
					html += "</tr>";
					opt_all.data_body.append(html);
				}
	 		});
			var numTds = $(".num-td");
			for(var i=0;i<numTds.length;i++){
				numTds.eq(i).html(i+1);
			}
		}
		
		//发货
		this.deliver=function(row){
			
			comm_loadFormData(row)
			opt_all.info_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				
			});
			
		}
		 // 保存数据
		function _save() {
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		var url=opt_all.url_save;
	 		common_ajax(json, url, function(response){
	 			var result=response.result;
	 			console.log(JSON.stringify(result))
	 				opt_all.info_dialog.modal("hide");
	 									    
	 				 _refresh();	
	 		});
	 		
		}
		//导出全部需求清单
		function downLoadAllDemand(){
			var url= opt_all.url_downLoad+"?";
			var data="fSupplyFormBean.pageBean.limit="+g_treelist_size
					+"&fSupplyFormBean.pageBean.offset=0" 
					+"&fSupplyFormBean.pageBean.sortOrder="+tableParam.order
					+"&fSupplyFormBean.pageBean.sort="+tableParam.sort
					+"&fSupplyFormBean.searchName="+opt_all.searchInput.val();
			
			confirm("<i class='icon icon-reply'></i>&nbsp;物资发货","您确定要导出全部信息吗？","icon-info", function(result) {
				   if(result){
					   window.location.href=url+data;
	               }
				});
		}
		//导出当前页
		function downLoadDemandInPage(){
			var url= opt_all.url_downLoad+"?";
			var ids = "";
			var bt_ids=opt_all.table.bootstrapTable('getSelections');
			if (bt_ids.length == 0) {
				var data_page="fSupplyFormBean.pageBean.limit="+tableParam.limit
							 +"&fSupplyFormBean.pageBean.offset="+tableParam.offset
							 +"&fSupplyFormBean.pageBean.sortOrder="+tableParam.order
							 +"&fSupplyFormBean.pageBean.sort="+tableParam.sort
							 +"&fSupplyFormBean.searchName"+opt_all.searchInput.val();
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;物资发货","您确定要导出当前页中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data_page;
					   }
				});
			}else{
				for (var i = 0; i < bt_ids.length; i++) {
					ids = ids + "," + bt_ids[i]["ID"];
				}
				ids = ids.substring(1);
				var data="fSupplyFormBean.ids="+ids
						+"&fSupplyFormBean.pageBean.limit="+g_treelist_size
						+"&fSupplyFormBean.pageBean.offset=0" 
						+"&fSupplyFormBean.pageBean.sort=ID" 
						+"&fSupplyFormBean.pageBean.sortOrder=DESC";
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;发货状态","您确定要导出选中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data;
					   }
				});
			}
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
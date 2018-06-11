(function($) {
	$.System_need = function(option) {
		//option自定义参数覆盖
		//界面控件变量
		var opt_control={
				table			:$("#tbinfo")	//BootStrapTable的ID
			   ,searchInput		:$("#searchInput")	//模糊查询input
			   ,material_type	:$("#material_type")	//物料种类select
			   ,material_name	:$("#material_name")	//物料名称select
			   ,btn_out			:$("#btn_out")	//导出全部按钮
			   ,btn_out_page	:$("#btn_out_page")	//导出当前页
			   ,btn_into		:$("#btn_into")	//导入按钮
			   ,btn_del			:$("#btn_del")	//批量删除按钮
			   ,btn_ref			:$("#btn_ref")	//查询按钮
			   ,info_dialog		:$('#info_dialog')  // 新增和编辑对应的窗体
			   ,info_form		:$('#info_form')    // 新增和编辑对应的表单
			   ,info_list		:$("#info_list")	// 详情信息
			   ,info_upload		:$("#info_upload")	//文件上传模态框
			   ,MATERIELCODE	:$("#MATERIELCODE")	//物料名称select
			   ,material_add	:$("#material_add")		//添加物资按钮
			   ,data_body		:$("#data_body")		//物资添加列表tbody
			   ,self_remove		:$(".removeSelf")		//删除物资按钮
			   ,photo_upload	:$("#photo_upload")		//图片上传模态框
		}
		
		//请求地址URL
		var opt_url={
			url_list			:basePath+"plan/demand!queryAllDemandInfo.action"//查询数据URL
		   ,url_remove			:basePath+"plan/demand!removeids.action"//删除数据URL
		   ,url_downLoad		:basePath+"plan/demand!downLoadDemandInfo.action"//导出数据URL
		   ,url_update			:basePath+"plan/demand!updateDemandInfo.action"//修改数据URL
		   ,url_materialData	:basePath+"contInfo/contInfo!queryAllMaterial.action"//查询所有物料信息
		   ,url_materialInfo	:basePath+"plan/demand!queryMaterielBase.action"//查询物料详细信息
		   ,url_edit			:basePath+"plan/demand!edit.action"
		}
		
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
		var TableQueryParams;
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
				TableQueryParams=params;
				
				//查询条件
				var opt_parms={
					 "fDemandFormBean.searchName"			: opt_control.searchInput.val() // 查询关键字
					,"fDemandFormBean.materielType"			: $("#material_type").val()	//物资类型
					,"fDemandFormBean.materielBase"			: $("#material_name").val()	//物资名称
					,"fDemandFormBean.mDemand.engineerCode"	: sessionStorage.getItem("engineeringNm")
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "fDemandFormBean.pageBean.limit"		: params.limit   // 页面大小
			      ,"fDemandFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,"fDemandFormBean.pageBean.sort"		: params.sort  // 排序列名
			      ,"fDemandFormBean.pageBean.sortOrder"	: params.order// 排位命令（desc，asc）
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
			opt_all.btn_ref.bind("click",event_ref);
			opt_all.btn_del.bind("click",event_del);
			opt_all.btn_into.bind("click",upload_model_show);
			opt_all.btn_out.bind("click",downLoadAllDemand);
			opt_all.btn_out_page.bind("click",downLoadDemandInPage);
			opt_all.searchInput.bind("keydown",carriage);
			opt_all.MATERIELCODE.bind("change",change_MATERIELCODE);
			opt_all.material_add.bind("click",event_addMaterial);
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// //校验数据正确
			});	
				
		}
		
		//回车事件
		function carriage(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
		     if(e && e.keyCode==13){ // enter 键
		         //要做的事情
		    	 event_ref();
		    }
		}
		
		function event_addMaterial(){
			//新增物资时显示输入框
			_edit_material();
		}
		
		// 修改记录，调出窗体,公开函数
		this.edit=function(id,onlyread) {
			var ids=verdict_del_update(opt_all.table,"ID",id);
			if(ids == id || ids == "" || ids == null){
				LoadEditData(id);
			}
			
		}
		
		//修改窗体
		function LoadEditData(id){
			opt_all.data_body.html("");//清除物资明细
			var url=opt_all.url_edit+"?fDemandFormBean.mDemand.id="+id;
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
				var mDemand=response.infoBean;
				
				console.log(JSON.stringify(response))
				for (var key in mDemand){  
					mDemand[key.toUpperCase()] = mDemand[key];  
			        delete(mDemand[key]);  
			    }
				comm_loadFormData(mDemand);
				for(var i=0;i<response.mMaterialDetailList.length;i++){
					_edit_material(response.mMaterialDetailList[i]);
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
		
		//单条删除
		this.del=function(id){
			var ids=verdict_del_update(opt_all.table,"ID",id);
			if(ids == id || ids == "" || ids == null){
				_del(id);
			}
		}
		
		//绑定刷新事件
		function event_ref(){
			 _refresh();
		}
		
		//切换工程刷新事件
		this.flash=function(){
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
			opt_all.info_form.data('bootstrapValidator').resetForm(false);
		}
		
		// 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"ID");
           if (ids.length==0) return;
           
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");

           var url= opt_all.url_remove+"?fDemandFormBean.ids="+ids
            
           confirm("需求清单","您确定要删除这些记录吗？","icon-remove-sign", function(result) {
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
		
		//删除单条记录
		function _del(id){
			$(".table tbody tr[data-uniqueid="+id+"] td").addClass("row-bcground");
			var url= opt_all.url_remove+"?fDemandFormBean.ids="+id
			confirm("需求清单","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
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
		
		//保存事件
		function _save(){
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		common_ajax(json, opt_all.url_update, function(response){
	 			opt_all.info_dialog.modal("hide");
	 			
			    // 增加数据
//			    if ($('#ID').val()==0){
//			        // 增加表格数据
//			    	opt_all.table.bootstrapTable("prepend", response.infoBean);								    
//			    } else {
//			        // 修改表格数据
//			    	opt_all.table.bootstrapTable("updateByUniqueId", {
//		                ID: response.infoBean.id,
//		                row: response.infoBean
//		            });		
//			    	console.log(response)
//	            }
	 			_refresh();
	 		}); 	
		}
		
		//详情模态框
		function list(row){
			var url=opt_all.url_edit+"?fDemandFormBean.mDemand.id="+row.ID;
			common_ajax(null,url, function(response) {
				$("#data_body_need").html("");
				var html="";
				comm_loadFormData_flag_html(row,'_need');
				for(var i=0;i<response.mMaterialDetailList.length;i++){
					html+="<tr>";
					html+="<td style='text-align: center'>"+(i+1)+"</td>";
					html+="<td style='text-align: center'>"+response.mMaterialDetailList[i].MATERIELNAME+"</td>";
					html+="<td style='text-align: center'>"+response.mMaterialDetailList[i].MATERIELUNIT+"</td>";
					html+="<td style='text-align: left'>"+response.mMaterialDetailList[i].MATERIALNORMS+"</td>";
					html+="<td style='text-align: right'>"+response.mMaterialDetailList[i].MATERIELNUM+"</td>";
					html+="<td>"+(response.mMaterialDetailList[i].MATERIELDESC==null?"":response.mMaterialDetailList[i].MATERIELDESC)+"</td>";
					html+="</tr>";
				}
				$("#data_body_need").append(html)
			});
		   opt_all.info_list.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
		   }).on('shown.zui.modal', function() {

           });
		   opt_all.info_list.on('hidden.zui.modal',function(){
			$("#tbinfo tbody tr[data-uniqueid="+row.ID+"]").removeClass("success");
		   });
		}
		
		//文件上传
		function filesUpload(){
			var file_options = {
				//初始化参数
				url:basePath+'plan/demand!importDemandInfo.action'	//文件上传地址
			   ,filters:{//文件过滤器
				   	// 只允许上传Excel
				    mime_types: [
				        {title: 'Excel', extensions: 'xlsx,xls'},
				    ],
				    // 最大上传文件为 10MB
				    max_file_size: '10mb',
				    // 不允许上传重复文件
				    prevent_duplicates: true
				}
			   ,responseHandler:function(responseObject, file){
				   // 当服务器返回的文本内容包含 `'error'` 文本时视为上传失败
				   if(responseObject.response=='error') {
					   var msg = new $.zui.Messager("消息提示：导入失败", {placement: "center",type:"primary"});
					   msg.show();	
					   return '导入失败';
				   }else if(responseObject.response=='success') {
					   _refresh();
				   }
			   }
			}
			$('#myUploader').uploader(file_options);
		}
		
		//文件上传模态框
		function upload_model_show(){
			filesUpload();
			opt_all.info_upload.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
	
	        });
		}
		
		//导出全部需求清单
		function downLoadAllDemand(){
			var url= opt_all.url_downLoad+"?";
			var data="fDemandFormBean.pageBean.limit="+g_treelist_size
					+"&fDemandFormBean.pageBean.offset=0" 
					+"&fDemandFormBean.pageBean.sortOrder="+TableQueryParams.order
					+"&fDemandFormBean.pageBean.sort="+TableQueryParams.sort
					+"&fDemandFormBean.searchName="+opt_all.searchInput.val()
					+"&fDemandFormBean.mDemand.engineerCode="+sessionStorage.getItem("engineeringNm");
			confirm("<i class='icon icon-reply'></i>&nbsp;需求清单","您确定要导出全部信息吗？","icon-info", function(result) {
				   if(result){
					   window.location.href=url+data;
	               }
				});
		}
		
		//导出当前页
		function downLoadDemandInPage(){
			var url= opt_all.url_downLoad+"?";
			var ids = "";
			var engineerCode=sessionStorage.getItem("engineeringNm");
			var bt_ids=opt_all.table.bootstrapTable('getSelections');
			if (bt_ids.length == 0) {
				var data_page="fDemandFormBean.pageBean.limit="+TableQueryParams.limit
							 +"&fDemandFormBean.pageBean.offset="+TableQueryParams.offset
							 +"&fDemandFormBean.pageBean.sortOrder="+TableQueryParams.order
							 +"&fDemandFormBean.pageBean.sort="+TableQueryParams.sort
							 +"&fDemandFormBean.searchName="+opt_all.searchInput.val()
							 +"&fDemandFormBean.mDemand.engineerCode="+engineerCode;
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;需求清单","您确定要导出当前页中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data_page;
						  
					   }
				});
			}else{
				for (var i = 0; i < bt_ids.length; i++) {
					ids = ids + "," + bt_ids[i]["ID"];
				}
				ids = ids.substring(1);
				var data="fDemandFormBean.ids="+ids
						+"&fDemandFormBean.pageBean.limit="+g_treelist_size
						+"&fDemandFormBean.pageBean.offset=0" 
						+"&fDemandFormBean.pageBean.sort=ID" 
						+"&fDemandFormBean.pageBean.sortOrder=DESC";
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;需求清单","您确定要导出选中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data;
					   }
				});
			}
		}
		
		//物料名称select change事件
		function change_MATERIELCODE(){
			if(opt_all.MATERIELCODE.val()==""){
				$("#MATERIALNORMS").val("");
				$("#MATERIELNAME").val("");
				$("#MATERIELUNIT").val("");
				return;
			}
			var url=basePath+"materielBase/materielBase!all_list.action";
			var data={
					  "materielBaseFormBean.pageBean.limit"		: g_treelist_size   // 页面大小
					 ,"materielBaseFormBean.pageBean.offset"	: 0 				// 当前记录偏移条数
					 ,"materielBaseFormBean.pageBean.sort"		: 'ID'  			// 排序列名
					 ,"materielBaseFormBean.pageBean.sortOrder"	: 'DESC'			// 排位命令（desc，asc）
					 ,"materielBaseFormBean.materielBase.materielCode"	:opt_all.MATERIELCODE.val()
				}
			common_ajax(data, url, function(response){
				var materielInfo=response.rows[0];
				$("#MATERIALNORMS").val(materielInfo.MATERIALNORMS);
				$("#MATERIELNAME").val(materielInfo.MATERIELNAME);
				$("#MATERIELUNIT").val(materielInfo.UNIT);
			})
		}
		
		//点击新增物资时显示输入框
		function _edit_material(detailData){
			common_ajax(null, opt_all.url_materialData, function(response){
				var material_data = response.materials;
				if(detailData == undefined || detailData == null){
					var num = opt_all.data_body.find("tr").length +1;
					var selHtml = "<select required onchange='need.getMaterialInfo(this)' style='height:30px;border: 0px;' data-type='text' class='form-control' name='materielCode'>";
					selHtml += 	"<option value=''>请选择物料</option>";
					for(var i=0;i<material_data.length;i++){
						selHtml += 	"<option value='"+material_data[i].MATERIELCODE+"'>"+material_data[i].MATERIELNAME+"</option>";
					}
					selHtml +="</select>";
					
					var html = "<tr>";
					html += "<td class='num-td' style='padding: 0px;text-align: center;line-height: 30px;'>" + num + "</td>";
					html += "<td style='padding: 0px;display:none;'><input data-type='text' class='form-control' name='materielId'></td>";
					html += "<td style='padding: 0px;display:none;'><input data-type='text' class='form-control' value='' name='code'></td>";
					html += "<td style='padding: 0px;'>" + selHtml + "</td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;text-align: center;background-color: #fff;' readonly='true' data-type='text' class='form-control materielUnit'  name='materielUnit'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;' readonly='true' data-type='text' class='form-control materialNorms'  name='materialNorms'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;text-align: right;' data-type='text' class='form-control materiel-num' name='materielNum'></td>";
					/*html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;' readonly='true' data-type='text' class='form-control price' name='price'></td>";*/
					/*html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;' readonly='true' data-type='text' class='form-control proposal-price' onkeyup='' name='proposalPrice'></td>";*/
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;' data-type='text' class='form-control' name='goodDesc'></td>";
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
					
					var selHtml = "<select required onchange='need.getMaterialInfo(this)' style='height:30px;border: 0px;' data-type='text' class='form-control' name='materielCode'>";
					selHtml += 	"<option value=''>请选择物料</option>";
					for(var i=0;i<material_data.length;i++){
						if(detailData.MATERIELCODE == material_data[i].MATERIELCODE){
							selHtml += 	"<option selected='true' value='"+material_data[i].MATERIELCODE+"'>"+material_data[i].MATERIELNAME+"</option>";
						}else {
							selHtml += 	"<option value='"+material_data[i].MATERIELCODE+"'>"+material_data[i].MATERIELNAME+"</option>";
						}
					}
					selHtml +="</select>";
					
					var html = "<tr>";
					html += "<td class='num-td' style='padding: 0px;text-align: center;line-height: 30px;'>" + num + "</td>";
					html += "<td style='padding: 0px;display:none;'><input data-type='text' class='form-control' value='"+detailData.ID+"' name='materielId'></td>";
					html += "<td style='padding: 0px;display:none;'><input data-type='text' class='form-control' value='"+detailData.MATERIELCODE+"' name='code'></td>";
					html += "<td style='padding: 0px;'>" + selHtml + "</td>";
					html += "<td style='padding: 0px;'><input style='height:30px;text-align: center;border: 0px;background-color: #fff;' readonly='true' data-type='text' class='form-control materielUnit' value='"+detailData.MATERIELUNIT+"' name='materielUnit'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;' readonly='true' data-type='text' class='form-control materialNorms' value='"+(detailData.MATERIALNORMS==null?"":detailData.MATERIALNORMS)+"' name='materialNorms'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;text-align: right;border: 0px;' data-type='text' class='form-control materiel-num' value='"+detailData.MATERIELNUM+"' name='materielNum'></td>";
					/*html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;' readonly='true' data-type='text' class='form-control price' value='"+(detailData.MATERIELPRICE==null?"":Number(detailData.MATERIELPRICE).toFixed(2))+"' name='price'></td>";*/
					/*html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;' readonly='true' data-type='text' class='form-control proposal-price' onkeyup='' value='"+Number(detailData.PROPOSALPRICE).toFixed(2)+"' name='proposalPrice'></td>";*/
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;' data-type='text' class='form-control' value='"+(detailData.MATERIELDESC==null?"":detailData.MATERIELDESC)+"' name='goodDesc'></td>";
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
		
		//下拉框改变时获取物料信息
		this.getMaterialInfo=function(e){
			if(e.value==""){
				var trIndex = e.parentNode.parentNode.sectionRowIndex;//tr标签下标
				opt_all.data_body.eq(0).find(".materielUnit").eq(trIndex).val("");
 				opt_all.data_body.eq(0).find(".materialNorms").eq(trIndex).val("");
 				opt_all.data_body.eq(0).find(".price").eq(trIndex).val("");
				return;
			}
			var url= opt_all.url_materialInfo+"?materielBaseFormBean.materielBase.materielCode="+e.value;
			var data={
					  "materielBaseFormBean.pageBean.limit"		: g_treelist_size   // 页面大小
					 ,"materielBaseFormBean.pageBean.offset"	: 0 				// 当前记录偏移条数
					 ,"materielBaseFormBean.pageBean.sort"		: 'ID'  			// 排序列名
					 ,"materielBaseFormBean.pageBean.sortOrder"	: 'DESC'			// 排位命令（desc，asc）
				}
	 		common_ajax(data, url, function(response){
	 			var trIndex = e.parentNode.parentNode.sectionRowIndex;//tr标签下标
	 			if(e.value != null && e.value != ""){
		 			opt_all.data_body.eq(0).find(".materielUnit").eq(trIndex).val(response.rows[0].UNIT);
		 			opt_all.data_body.eq(0).find(".materialNorms").eq(trIndex).val(response.rows[0].MATERIALNORMS);
		 			opt_all.data_body.eq(0).find(".price").eq(trIndex).val(Number(response.rows[0].PRICE).toFixed(2));
		 			//countTotalPrice();
	 			}else {
	 				opt_all.data_body.eq(0).find(".materielUnit").eq(trIndex).val("");
	 				opt_all.data_body.eq(0).find(".materialNorms").eq(trIndex).val("");
	 				opt_all.data_body.eq(0).find(".price").eq(trIndex).val("");
	 				//countTotalPrice();
	 			}
	 		}); 
		}
		
		//点击查看上传文件
		this.upload_file=function(id){
			filesUpload_id(id);
			upload_photo_model_show();
		}
		
		//文件上传模态框
		function upload_photo_model_show(file_options){
			opt_all.photo_upload.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
	
	        });
			opt_all.photo_upload.on('hidden.zui.modal',function(){
				window.location.reload();
			});
		}
		
		//文件上传
		function filesUpload_id(id){
			var file_options;
			var url_queryFileUpload=basePath+"file/upload!queryFileUpload.action?tableName=plan_demand&tablePkColumn="+id;
			common_ajax_async(null,url_queryFileUpload,function(response){
	 			file_options = {
	 					//初始化参数
	 					url:basePath+"file/upload!saveFileUpload.action?tableName=plan_demand&tablePkColumn="+id+"&engineerCode="+sessionStorage.getItem("engineeringNm")	//文件上传地址
	 					,filters:{//文件过滤器
	 						// 最大上传文件为 10MB
	 						max_file_size: '10mb'
	 					}
			 			,multipart_params:function(file, params) {
						    return {"fileId": file.id};
						}
			 			,responseHandler:function(responseObject, file){
			 				// 当服务器返回的文本内容包含 `'error'` 文本时视为上传失败
			 				if(responseObject.response=='error') {
			 					var msg = new $.zui.Messager("消息提示：上传失败", {placement: "center",type:"primary"});
			 					msg.show();	
			 					return '上传失败';
			 				}else if(responseObject.response=='success') {
			 					var nub=$("#fileNub_id"+id).html();
								$("#fileNub_id"+id).html(Number(nub)+1);
			 				}
			 			}
			 			,staticFiles:response.mFileUploadList//查询已上传文件
			 			,deleteActionOnDone: function(file, doRemoveFile) {//删除上传文件
			 		          doRemoveFile();
			 		          deleteFileById(file.id,id);
			 		      }
	 			}
	 			$('#myUploader_photo').uploader(file_options);
	 		},true);
		}
		
		//删除文件
		function deleteFileById(fileId,id){
			var url=basePath+"file/upload!deleteFileById.action?fileId="+fileId;
			common_ajax(null,url, function(response) {
				if(response.retflag=="success"){
					var nub=$("#fileNub_id"+id).html();
					$("#fileNub_id"+id).html(nub-1);
				}else{
					var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
				    msg.show();	
				}
			})
		}
		
	}
})(jQuery);
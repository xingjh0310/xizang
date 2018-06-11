(function($) {
	
	var globle=sessionStorage.getItem("engineeringNm");
	
	$.System_material = function(option) {
		//option自定义参数覆盖
		//界面控件变量
		var opt_control={
				table			:$("#tbinfo")			//BootStrapTable的ID
			   ,searchInput		:$("#searchInput")		//模糊查询input
			   ,material_type	:$("#material_type")	//物料种类select
			   ,material_name	:$("#material_name")	//物料名称select
			   ,btn_add 		:$('#btn_add')			//新增
			   ,btn_upload		:$("#btn_upload")		//导入按钮
			   ,btn_out_page	:$("#btn_out_page")		//导出当前页按钮
			   ,btn_out			:$("#btn_out")			//导出全部按钮
			   ,btn_del			:$("#btn_del")			//批量删除按钮
			   ,btn_ref			:$("#btn_ref")			//查询按钮
			   ,info_dialog		:$('#info_dialog')  	// 新增和编辑对应的窗体
			   ,info_form		:$('#info_form')    	// 新增和编辑对应的表单
			   ,material_add	:$('#material_add')		//页面新增物料
			   ,data_body		:$('#data_body')		//table行
			   ,info_list		:$('#info_list')		//详细页面
			   ,info_label		:$("#info_label")		//清单列表
			   ,removeIds		:$("#removeIds")		//要删除的物资id
			   ,info_upload		:$("#info_upload")		//上传文件窗体
			   ,list_table		:$("#list_table")		//物资表格
			   ,list_label		:$("#list_label")		//单据
			   ,print_out		:$("#print_out")		//打印
		}
		
		//请求地址URL
		var opt_url={
			url_list			:basePath+"refund/refund!list.action"					//查询数据URL
		   ,url_staff			:basePath+"refund/refund!getDate.action"				//获取系统人员和时间
		   ,url_save			:basePath+"refund/refund!save.action"					//保存退库退料信息
		   ,url_edit			:basePath+"refund/refund!edit.action"					//修改数据
		   ,url_remove			:basePath+"refund/refund!removeids.action"				//删除
		   ,url_report			:basePath+"refund/refund!report.action"					//上报
		   ,url_downLoad		:basePath+"refund/refund!downLoad.action"			//导出数据URL
		   ,url_transfer		:basePath+"materiel/materiel!transfer.action"			//物资交接URL
		   ,url_materialData	:basePath+"contInfo/contInfo!queryAllMaterial.action" 	//查询所有物料信息
		   ,url_materialInfo	:basePath+"contInfo/contInfo!getMaterialInfo.action"	//查询物料详细信息
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
				$("#tbinfo tbody tr[data-uniqueid="+rows.ID+"]").addClass("success");
			}
			//不选中时颜色恢复
			function onUncheck(rows){
				$("#tbinfo tbody tr[data-uniqueid="+rows.ID+"]").removeClass("success");
			}
			
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				//查询条件
				tableParam=params;
				var opt_parms={
					 "refundFormBean.searchName"			:opt_control.searchInput.val(), // 查询关键字
					 "refundFormBean.refund.supplierCode"	:$("#supplier").val(),		//供应商
					 "refundFormBean.refund.engineerCode"	:globle,
					 //"refundFormBean.refund.upState"		:"0"
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "refundFormBean.pageBean.limit"	: params.limit   // 页面大小
			      ,"refundFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
			      ,"refundFormBean.pageBean.sort"		: params.sort  // 排序列名
			      ,"refundFormBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
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
			opt_all.print_out.bind("click",print_out);
			opt_all.material_add.bind("click",material);
			opt_all.btn_out.bind("click",downLoadAllDemand);  //导出
			opt_all.btn_out_page.bind("click",downLoadDemandInPage);
		}
		// 保存button类型为submit
		opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
			e.preventDefault(); // 去掉默认提交事件
			// 校验数据正确,执行保存数据
			/*var typeNm=$("#typeNm").val();
			if(typeNm == ""){
				var msg = new $.zui.Messager("消息提示：设备类型不可为空", {placement: "center",type:"primary"});
			    msg.show();	
			    return;
			}*/
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
		//查看退料退库详情
		function list(row){
			opt_all.list_table.html("");//清除物资明细
			//退库退料信息
			$("#ENGINEER_list").html($("#engineer").val())
			comm_loadFormData_flag_html(row,"_list");// 获取到数据，显示在界面上
			//物资信息
			
			var url = opt_all.url_edit+"?refundFormBean.refund.id=" + row.ID;
			
			common_ajax(null,url, function(res) {
				
				common_ajax(null, opt_all.url_materialData, function(response){
					
					for(var k=0;k<res.detail.length;k++){//物资明细

							var	detailData=res.detail[k]
							
							var material_data = response.materials;
							
								var num = opt_all.list_table.find("tr").length +1;
								
								var selHtml = "<select required  style='height:30px;border: 0px;' data-type='text' class='form-control' name='materialCode'>";
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
								html += "</tr>";
								opt_all.list_table.append(html);
						}
					});
			
				opt_all.info_list.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					//_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				});
				opt_all.info_list.on('hidden.zui.modal',function(){
					$("#tbinfo tbody tr[data-uniqueid="+row.ID+"]").removeClass("success");
				 });
			})
		}
		//获取数据填充窗体
		function LoadEditData(id){
			
			opt_all.data_body.html("");//清除物资明细
			
			/*获取系统人员和当前时间*/
			common_ajax(null,opt_all.url_staff, function(response) {
				var date = response.date;
				var name = response.staff.name;
				$("#singleDate_refund").val(date)
				$("#singleStaff_refund").val(name)
			});
			
			var url = opt_all.url_edit+"?refundFormBean.refund.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
			comm_loadFormData(response.infoBean)
			
			for(var i=0;i<response.detail.length;i++){//物资明细
				add_material(response.detail[i]);
			}
			
			opt_all.info_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('hide.zui.modal', function() {
				
				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
			});
				
			});
		}
		 // 保存数据
		function _save() {
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		var url=opt_all.url_save;
	 		common_ajax(json, url, function(response){
	 			var result=response.result;
	 			
	 				opt_all.info_dialog.modal("hide");
	 				// 增加数据
	 				if ($('#id').val()==0){
	 					// 增加表格数据
	 					opt_all.table.bootstrapTable("prepend", response.infoBean);								    
	 				} else {
	 					// 修改表格数据
	 					opt_all.table.bootstrapTable("updateByUniqueId", {
	 						
	 					});		
	 				}						    
	 				 _refresh();	
	 		});
	 		
		}
		//单条删除
		this.del=function(id){
			console.log(id)
			var ids=verdict_del_update(opt_all.table,"id",id);
			if(ids == id || ids == "" || ids == null){
				console.log(id)
				_del(id);
			}
		}
		// 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids=g_select_and_tip(opt_all.table,"ID");
           if (ids.length==0) return;
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");
           
           var url= opt_all.url_remove+"?refundFormBean.ids="+ids
            
           confirm("退料退库","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
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
			
			var url= opt_all.url_remove+"?refundFormBean.ids="+id
            
			confirm("退库退料","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
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
		//附件
		this.enclosure=function(id){
			filesUpload(id);
			opt_all.info_upload.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
	        });
			//模态框关闭刷新页面
			opt_all.info_upload.on('hidden.zui.modal',function(){
				window.location.reload();
			})
			
		}
		//文件上传
		function filesUpload(id){
			var file_options;
			//查询文件
			var url_queryFileUpload=basePath+"file/upload!queryFileUpload.action?tableName=BILLINFO&tablePkColumn="+id;
			common_ajax_async(null,url_queryFileUpload,function(response){
			file_options= {
				//初始化参数
				url:basePath+"file/upload!saveFileUpload.action?tableName=BILLINFO&tablePkColumn="+id+"&engineerCode="+sessionStorage.getItem("engineeringNm")	//文件上传地址
			   ,filters:{//文件过滤器
				   	//上传类型
				    mime_types: [
				        {title: '图片', extensions: 'jpg,gif,png'},
				        {title: '文件', extensions: 'doc,docx' }
				    ],
				    // 最大上传文件为 10MB
				    max_file_size: '10mb',
				    // 不允许上传重复文件
				    prevent_duplicates: true
				}
				,multipart_params:function(file, params) {
					return {"fileId": file.id};
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
			   ,staticFiles:response.mFileUploadList//查询已上传文件
	 			,deleteActionOnDone: function(file, doRemoveFile) {//删除上传文件
	 		          doRemoveFile();
	 		          deleteFileById(file.id,id);
	 		      }
			}
			$('#myUploader').uploader(file_options);
			},true);
		}
		//删除文件
		function deleteFileById(fileId,id){
			var url=basePath+"file/upload!deleteFileById.action?fileId="+fileId;
			common_ajax(null,url, function(response) {
				if(response.retflag=="success"){
				}else{
					var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
				    msg.show();	
				}
			})
		}
		//绑定新增
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
		//打印
		function print_out(){
			
			/*var dom = document.getElementById('print');
			var win = window.open('');
			win.document.write(dom.outerHTML);
			win.print();
			win.close();*/
			
			$("#print").jqprint();
			//window.print()
		}
		
		// 刷新
	    function _refresh(){
	    	opt_all.table.bootstrapTable('refresh');
	    }
	    
	    // 重置窗体
		function _reset(){
			opt_all.removeIds.val("");
			opt_all.info_form.data('bootstrapValidator').resetForm(true);
		}
		this.flash=function(nm){
			globle=nm
			_refresh()
		}
		//导出全部需求计划
		function downLoadAllDemand(){
			var url= opt_all.url_downLoad+"?";
			var data="refundFormBean.pageBean.limit="+g_treelist_size
					+"&refundFormBean.pageBean.offset=0" 
					+"&refundFormBean.pageBean.sortOrder="+tableParam.order
					+"&refundFormBean.pageBean.sort="+tableParam.sort
					+"&refundFormBean.refund.engineerCode="+globle
					+"&refundFormBean.searchName="+opt_all.searchInput.val();
			
			confirm("<i class='icon icon-reply'></i>&nbsp;物资状态","您确定要导出全部信息吗？","icon-info", function(result) {
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
				var data_page="refundFormBean.pageBean.limit="+tableParam.limit
							 +"&refundFormBean.pageBean.offset="+tableParam.offset
							 +"&refundFormBean.pageBean.sortOrder="+tableParam.order
							 +"&refundFormBean.pageBean.sort="+tableParam.sort
							 +"&refundFormBean.refund.engineerCode="+globle
							 +"&refundFormBean.searchName"+opt_all.searchInput.val();
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;物资状态","您确定要导出当前页中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data_page;
					   }
				});
			}else{
				for (var i = 0; i < bt_ids.length; i++) {
					ids = ids + "," + bt_ids[i]["ID"];
				}
				ids = ids.substring(1);
				var data="refundFormBean.ids="+ids
						+"&refundFormBean.pageBean.limit="+g_treelist_size
						+"&refundFormBean.pageBean.offset=0" 
						+"&refundFormBean.pageBean.sort=ID" 
						+"&refundFormBean.pageBean.sortOrder=DESC"
						+"&refundFormBean.refund.engineerCode="+globle;
				confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;物资状态","您确定要导出选中的信息吗？","icon-info", function(result) {
					   if(result){
						   window.location.href=url+data;
					   }
				});
			}
		}
		/*查看审核后的生成的单据*/
		this.label=function(data){
			openLable(data);
		}
		
		function openLable(row){
			opt_all.list_label.html("");//清除物资明细
			
			comm_loadFormData_flag_html(row,"_out");// 获取到数据，显示在界面上
			
			var url = opt_all.url_edit+"?refundFormBean.refund.id=" + row.ID;
			
			common_ajax(null,url, function(res) {
				
				common_ajax(null, opt_all.url_materialData, function(response){
					
					for(var i=0;i<res.detail.length;i++){//物资明细

							var	detailData=res.detail[i]
							console.log(detailData)
							var material_data = response.materials;
							
								var num = opt_all.list_label.find("tr").length +1;
								
								var selHtml = "<select required disabled='disabled'  style='height:30px;border: 0px;'background-color: #fff;data-type='text' class='form-control' name='materialCode'>";
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
								html += "<td class='num-td' style='padding: 0px;background-color: #fff;text-align: center;line-height: 30px;'>" + num + "</td>";
								html += "<td style='padding: 0px;display:none;'><input data-type='text' class='form-control' value='"+detailData.ID+"' name='materialId'></td>";
								html += "<td style='padding: 0px;display:none;'><input data-type='text' class='form-control' value='"+detailData.CODE+"' name='code'></td>";
								html += "<td style='padding: 0px;'>" + selHtml + "</td>";
								html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: center;' readonly='true' data-type='text' class='form-control materielUnit' value='"+detailData.MEAUNIT+"' name='meaUnit'></td>";
								html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;'data-type='text'readonly='true' class='form-control materialNorms' value='"+(detailData.MATERIALDESC==null?"":detailData.MATERIALDESC)+"' name='materialDesc'></td>";
								html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;' data-type='text'readonly='true' class='form-control materiel-num' onkeyup='countTotalPrice()' value='"+(detailData.USENUM==null?"0":detailData.USENUM)+"' name='useNum'></td>";
								html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;' readonly='true' data-type='text' class='form-control price' value='"+(detailData.RETURNNUM==null?"0":detailData.RETURNNUM)+"' name='returnNum'></td>";
								html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;'readonly='true'  data-type='text' class='form-control proposal-price' onkeyup='' value='"+detailData.IDENTYSITUATION+"' name='identySituation'></td>";
								html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;' readonly='true' data-type='text' class='form-control proposal-price' onkeyup='' value='"+detailData.INFORSITUATION+"' name='inforSitution'></td>";
								html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;' readonly='true'data-type='text' class='form-control proposal-price' onkeyup='' value='"+detailData.USEDIRECTION+"' name='useDirection'></td>";
								html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;' readonly='true'data-type='text' class='form-control proposal-price' onkeyup='' value='"+detailData.USETIME+"' name='useTime'></td>";
								html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;'data-type='text' readonly='true' class='form-control' value='"+detailData.REMARK+"' name='remark'></td>";
								html += "</tr>";
								opt_all.list_label.append(html);
						}
					});
			
				opt_all.info_label.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					//_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				});
			})
			
		}
		this.report=function(id){
			var url= opt_all.url_report+"?refundFormBean.refund.id="+id
			confirm("退库退料","您确定要上报该条记录吗？","icon-info", function(result) {
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
		/*物资信息新增一行*/
		function material(){
			add_material();
		}
		/*拼接table图表*/
		function add_material(detailData){
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
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: center;' readonly='true' data-type='text' class='form-control materielUnit'  name='meaUnit'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: left;'data-type='text' class='form-control materialNorms'  name='materialDesc'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;text-align: right;' data-type='text' class='form-control materiel-num' onkeyup='countTotalPrice()' name='useNum'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;'data-type='text' class='form-control price' name='returnNum'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: center;'data-type='text' class='form-control proposal-price' onkeyup='' name='identySituation'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: center;'data-type='text' class='form-control proposal-price' onkeyup='' name='inforSitution'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: left;'data-type='text' class='form-control proposal-price' onkeyup='' name='useDirection'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: left;'data-type='text' class='form-control proposal-price' onkeyup='' name='useTime'></td>";
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
		//下拉框改变时获取物料信息
		this.getMaterialInfo=function(e){
			var url= opt_all.url_materialInfo+"?contInfoFormBean.ids="+e.value;
	 		common_ajax(null, url, function(response){
	 			var trIndex = e.parentNode.parentNode.sectionRowIndex;//tr标签下标
	 			if(e.value != null && e.value != ""){
		 			opt_all.data_body.eq(0).find(".materielUnit").eq(trIndex).val(response.materialInfo[0].UNIT);
		 			countTotalPrice();
	 			}else {
	 				opt_all.data_body.eq(0).find(".materielUnit").eq(trIndex).val("");
	 				countTotalPrice();
	 			}
	 		}); 
		}
		
		function Load_EditSelectData(callback){
			//所有编辑页面下拉框加载
			callback();
		}
		
	}
})(jQuery);
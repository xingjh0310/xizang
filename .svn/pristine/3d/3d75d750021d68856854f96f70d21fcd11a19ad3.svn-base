(function($) {
	//当前工程内码
	var engineeringNm = sessionStorage.getItem("engineeringNm");
	$.Storage_Query = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 query_table 			:$('#query_table')		//页面BootStrapTable的ID
			,select_supply			:$("#select_supply")	//查询条件供货厂商
			,query_searchName		:$('#query_searchName')	//页面模糊查询input
			,query_info_show		:$("#query_info_show")	//双击事件窗口id
			,query_ref				:$("#query_ref")		//模糊查询
			,edit_dialog			:$("#edit_dialog")		//添加编辑窗口
			,info_form				:$("#info_form")		//添加编辑的表单
			,removeIds				:$("#removeIds")		//合同变更删除的id
			,uploading_data_dialog	:$('#uploading_data_dialog') //文件上传模态框
			,data_body				:$("#data_body")		//物资详细列表tbody
			,btn_out_all			:$("#btn_out_all")		//导出全部按钮
			,btn_out_page			:$("#btn_out_page")		//导出本页按钮
		}
		//B、请求地址URL
		var opt_url={
			url_list			:basePath + "contInfo/contInfo!list.action?contInfoFormBean.type=change"//查询数据URL
			,url_supply			:basePath + "contInfo/contInfo!queryAllsupply.action"//供货厂商下拉数据
			,url_remove			:basePath + "contDetail/contDetail!removeid.action"//删除合同物资数据
			,url_change			:basePath + "contChange/contChange!save.action"//删除合同物资数据
			,url_details		:basePath + "business/contractMng/mainContractMng/details.jsp"//查询物料详细信息
			,url_export			:basePath + "contInfo/contInfo!exportContract.action"//查询物料详细信息
		}
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
		var TableQueryParams;
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		this.InitData=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.query_table.bootstrapTable($.extend(g_bootstrapTable_Options,
					{
				      url			 	:opt_all.url_list   // 请求后台的URL（*）
			         ,queryParams	  	:queryParams		// 传递参数（*）
			         ,onDblClickRow 	:onDblClickRow		// 行双击事件
			         ,onSort			:onSort             // 排序事件
		             ,rowStyle			:comm_rowStyle		//行样式，可自定义
		             ,onLoadSuccess		:comm_onLoadSuccess //数据加载错误
		             ,onCheckAll 		:onCheckAll   //全选
		             ,onCheck  :onCheck   //单选
		             ,onUncheck  :onUncheck   //不选
		             ,onUncheckAll :onUncheckAll  //全不选
		             ,singleSelect		:false
		             ,pageSize:15
					}));
			//选中多行改变表格背景色
			function onCheckAll(rows){
				for(var i=0;i<rows.length;i++){
					commRowStyle(i);
				}
			}
			//循环改变所有行颜色
			function commRowStyle(i){
				$("#query_table tbody tr[data-index="+i+"]").addClass("success");
			}
			//全不选时颜色恢复
			function onUncheckAll(){
				$("#query_table tbody tr").removeClass("success");
			}
			//选中一行改变表格背景色
			function onCheck(rows){
				$("#query_table tbody tr[data-uniqueid="+rows.id+"]").addClass("success");
			}
			//不选中时颜色恢复
			function onUncheck(rows){
				$("#query_table tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
			}
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				TableQueryParams=params;
				//查询条件
				var opt_parms={
					 "contInfoFormBean.searchName"			:opt_control.query_searchName.val() // 查询关键字
					 ,"contInfoFormBean.supply"			:opt_control.select_supply.val() // 查询供应厂商
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		    		"contInfoFormBean.pageBean.limit"			: params.limit   // 页面大小
				      ,"contInfoFormBean.pageBean.offset"			: params.offset  // 当前记录偏移条数
				      ,"contInfoFormBean.pageBean.sort"				: params.sort  	  // 排序列名
				      ,"contInfoFormBean.pageBean.sortOrder" 		: params.order   // 排位命令（desc，asc）
				      ,"contInfoFormBean.contInfoBean.engineerCode"	:engineeringNm
			    };
			    temp=$.extend(temp,opt_parms,opt);
				return temp;
			}
			// 双击事件,显示合同详情
			function onDblClickRow(row){
				if (row) {
					window.location.href = opt_all.url_details + "?contractId="+row.ID;
				}
			}
			// 排序事件
			function onSort(name, order){
				_refresh();
			}
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// 初始化增加、修改和删除,公开函数
		this.InitAddEditDel=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.query_ref.bind("click",event_ref);
			opt_all.btn_out_all.bind("click",exportAllContract);
			opt_all.btn_out_page.bind("click",exportPageContract);
			
			//初始化主合同编辑表单 保存button类型为submit
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// //校验数据正确
			});	
		}
		//初始化下拉框
		this.InitSelect = function (){
			Load_select_supply();
		}
	    // 修改记录，调出窗体,公开函数
		this.edit=function(id) {
			_edit(id);
		}
		//删除单条物资
		this.del=function(id) {
			_del(id);
		}
		//新增和编辑函数
		function _edit(id){
			//加载Edit页面基本数据，成功后调用显示编辑页面
			LoadEditData(id);
		}
		//删除单条物资
		function _del(id){
			var url= opt_all.url_remove+"?contDetailFormBean.ids="+id
	 		common_ajax(null, url, function(response){
				// 删除后，从后台取出返回数据
			    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
			    msg.show();	
	 		}); 
		}

		//加载供货厂商下拉
		function Load_select_supply(){
			opt_control.select_supply.empty();
			var url = opt_all.url_supply;
			opt_control.select_supply.append("<option value=''>请选择供货厂商</option>");
			common_ajax(null,url, function(data) {
				$.each(data.supplys, function(i) {
					opt_control.select_supply.append(
						'<option value=' + data.supplys[i].SUPPLIER_CODE + '>'+ data.supplys[i].SUPPLY_FULL_NAME + '</option>');
				});
			});
		}
		
		//上传文件
		this.uploading=function(id){
			filesUpload(id);
			filesUploadShow();
		}
		
		//附件信息模态框
		function filesUploadShow(){
			opt_all.uploading_data_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
			});
			opt_all.uploading_data_dialog.on('hidden.zui.modal',function(){
				window.location.reload();
			})
		}
		
		//文件上传
		function filesUpload(id){
			var file_options;
			var url_queryFileUpload=basePath+"file/upload!queryFileUpload.action?tableName=CONT_INFO&tablePkColumn="+id;
			common_ajax_async(null,url_queryFileUpload,function(response){
	 			file_options = {
	 					//初始化参数
	 					url:basePath+"file/upload!saveFileUpload.action?tableName=CONT_INFO&tablePkColumn="+id	//文件上传地址
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
		
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(id){
			_reset();
			var url = opt_all.url_list+"&contInfoFormBean.contInfoBean.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				for(var key in response.rows[0]){
					//主合同信息
				   if($("#"+key+"_detail")[0]){
					   $("#"+key+"_detail").html(response.rows[0][key]);
				   }
				}
				//格式化合同状态
				var contState = "";
				if(response.rows[0]["CONTRACTSTATE"] == "1"){
					contState = "未发货";
				}else if(response.rows[0]["CONTRACTSTATE"] == "2"){
					contState = "已发货";
				}
				$("#CONTRACTSTATE_detail").html(contState);
				
				$("#CONTRACTDATE_detail").html(response.rows[0]["CONTRACTSTARTDATE"] 
				+ " 至 " + response.rows[0]["CONTRACTENDDATE"]);
				$("#SUPPLYDATE_detail").html(response.rows[0]["SUPPLYSTARTDATE"] 
				+ " 至 " + response.rows[0]["SUPPLYENDDATE"]);
				opt_all.data_body.html("");
				//加载合同物资
				for(var i=0;i < response.contDetails.length;i++){
					var html = "<tr>";
						//序号
						html += "<td class='num-td' style='text-align: center;line-height:14px;'>" + (i+1) + "</td>";
						//ID
						html += "<td class='hidden'><input data-type='text' value='"+response.contDetails[i].ID+"' class='form-control'  name='id'></td>";
						//合同编号
						html += "<td class='hidden'><input data-type='text' value='"+response.rows[0]["CONTRACTNO"]+"' class='form-control'  name='contractNo'></td>";
						//工程编号
						html += "<td class='hidden'><input data-type='text' value='"+response.rows[0]["ENGINEERCODE"]+"' class='form-control'  name='engineerCode'></td>";
						//标段
						html += "<td class='hidden'><input data-type='text' value='"+response.rows[0]["SECTION"]+"' class='form-control'  name='section'></td>";
						//物资编号
						html += "<td class='hidden'><input data-type='text' value='"+response.contDetails[i].CODE+"' class='form-control'  name='code'></td>";
						//物料编号
						html += "<td class='hidden'><input data-type='text' value='"+response.contDetails[i].MATERIELCODE+"' class='form-control'  name='materielCode'></td>";
						//货物描述
						html += "<td class='hidden'><input data-type='text' value='"+response.contDetails[i].GOODDESC+"' class='form-control'  name='goodDesc'></td>";
						//物资名称
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;' data-type='text' value='"+response.contDetails[i].MATERIELNAME+"' class='form-control' readonly='true' name='materielName'></td>";
						//单位
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;text-align: center;' data-type='text' value='"+response.contDetails[i].MATERIELUNIT+"' class='form-control' readonly='true' name='materielUnit'></td>";
						//规格
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;' data-type='text' value='"+(response.contDetails[i].MATERIALNORMS==null?"":response.contDetails[i].MATERIALNORMS)+"' class='form-control' readonly='true' name=''></td>";
						//变更前数量
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;text-align: right;' data-type='text' value='"+response.contDetails[i].MATERIELNUM+"' class='form-control' readonly='true' name='preChangeNum'></td>";
						//变更后数量
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;text-align: right;' data-type='text' value='' class='form-control after-change-num' onkeyup='countTotalPrice()' name='afterChangeNum'></td>";
						//单价
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;text-align: right;' data-type='text' value='"+(response.contDetails[i].PRICE==null?"":Number(response.contDetails[i].PRICE).toFixed(2))+"' class='form-control price' readonly='true' name=''></td>";
						//差额不含税
						//html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;' data-type='text' value='' class='form-control difference' readonly='true' name='difference'></td>";
						//差额含税
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;text-align: right;' data-type='text' value='' class='form-control difference-tax' readonly='true' name='differenceTax'></td>";
						//变更前价格
						html += "<td class='hidden'><input style='height:30px;border: 0px;text-align: right;' data-type='text' value='" + response.contDetails[i].PROPOSALPRICE + "' class='form-control pre-change-price' name='preChangePrice'></td>";
						//变更后价格
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;text-align: right;' data-type='text' value='" + Number(response.contDetails[i].PROPOSALPRICE).toFixed(2) + "' class='form-control after-change-price' readonly='true' name='afterChangePrice'></td>";
						//操作
						html += "<td style='padding: 0px;text-align: center;'>";
						html += 	"<a href='#' onclick='removeSelf("+response.contDetails[i].ID+",event)'>";
						html +=			"<button class='btn btn-xs btn-danger btn_del_color'>";
						html +=				"<div class='visible-md visible-lg'><i class='icon icon-trash'></i>&nbsp;删除</div>";
						html +=				"<div class='visible-xs visible-sm'><i class='icon icon-trash'></i></div>";
						html +=			"</button>";
						html +=		"</a>";
						html +=	"</td>";
						html += "</tr>";
					opt_all.data_body.append(html);
				}
				var bottomTr = "<tr>";
					bottomTr += "<td style='padding: 5px;'></td>";
					bottomTr += "<td style='padding: 5px;'></td>";
					bottomTr += "<td style='padding: 5px;'></td>";
					bottomTr += "<td style='padding: 5px;'></td>";
					bottomTr += "<td style='padding: 5px;'></td>";
					bottomTr += "<td style='padding: 5px;'></td>";
					bottomTr += "<td style='padding: 5px;'></td>";
//					bottomTr += "<td style='padding: 5px;'></td>";
					bottomTr += "<td style='padding: 5px;text-align:right;'>合计：</td>";
					bottomTr += "<td style='padding: 5px;padding-left:8px;text-align:right;' id='totalPrice'></td>";
					bottomTr += "<td style='padding: 5px;'></td>";
					bottomTr += "</tr>";
				opt_all.data_body.append(bottomTr);
				countTotalPrice();
				opt_all.edit_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。

	            });
			});
		}
		
		// 保存物资变更记录
		function _save() {
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		common_ajax(json, opt_all.url_change, function(response){
	 			opt_all.edit_dialog.modal("hide");
			    //重置表单
			    _reset();
			    // 刷新列表
			    _refresh();
	 		});
		}
		
		
		//导出全部
		function exportAllContract(){
			//当前工程内码
			var nm = sessionStorage.getItem("engineeringNm");
			var url= opt_all.url_export+"?";
			var data="contInfoFormBean.pageBean.limit=99999999"
					+"&contInfoFormBean.pageBean.offset=0" 
					+"&contInfoFormBean.pageBean.sortOrder="+TableQueryParams.order
					+"&contInfoFormBean.pageBean.sort="+TableQueryParams.sort
					+"&contInfoFormBean.searchName="+opt_all.query_searchName.val()
					+"&contInfoFormBean.supply="+opt_control.select_supply.val()
					+"&contInfoFormBean.engineerNm="+nm;
			confirm("<i class='icon icon-reply'></i>&nbsp;导出全部","您确定要导出全部信息吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url+data;
               }
			});
		}
		
		//导出当前页
		function exportPageContract(){
			//当前工程内码
			var nm = sessionStorage.getItem("engineeringNm");
			var url= opt_all.url_export+"?";
			var data_page="contInfoFormBean.pageBean.limit="+TableQueryParams.limit
						 +"&contInfoFormBean.pageBean.offset="+TableQueryParams.offset
						 +"&contInfoFormBean.pageBean.sortOrder="+TableQueryParams.order
						 +"&contInfoFormBean.pageBean.sort="+TableQueryParams.sort
						 +"&contInfoFormBean.searchName"+opt_all.query_searchName.val()
						 +"&contInfoFormBean.supply="+opt_control.select_supply.val()
						 +"&contInfoFormBean.engineerNm="+nm;
			confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;导出当前页","您确定要导出当前页吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url+data_page;
			   }
			});
		}
		
		//****绑定事件
		//绑定刷新事件
		function event_ref(){
			 _refresh();
		}
		
		//****绑定事件******end
	    // 刷新
	    function _refresh(){
	    	opt_all.query_table.bootstrapTable('refresh');
	    }  
	    this.flash=function(nm){
	    	engineeringNm=nm
			_refresh()
		}
		// 重置窗体
		function _reset(){
			opt_all.removeIds.val("");
			opt_all.info_form.data('bootstrapValidator').resetForm(true);
		}
		//公开函数
		this.reset=function(){
			_reset();
		}
		////////////////////////////////////////////////////////////////////////////////
		//加载所有外键表到下拉框，无
		function Load_EditSelectData(callback){
			//所有编辑页面下拉框加载
			callback();
		}
	};
})(jQuery);
			

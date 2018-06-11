(function($) {
	//当前工程内码
	var engineeringNm = sessionStorage.getItem("engineeringNm");
	$.Storage_Query = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 query_table 			:$('#query_table')		//页面BootStrapTable的ID
			,query_searchName		:$('#query_searchName')	//页面模糊查询input
			,query_info_show		:$("#query_info_show")	//双击事件窗口id
			,query_ref				:$("#query_ref")		//模糊查询
			,edit_dialog			:$("#edit_dialog")		//添加编辑窗口
			,query_add				:$("#query_add")		//添加按钮
			,info_form				:$("#info_form")		//添加编辑的表单
			,uploading_data_dialog	:$('#uploading_data_dialog') //文件上传模态框
			,data_body				:$("#data_body")		//物资详细列表tbody
			,select_contract		:$("#select_contract")	//查询条件合同下拉框
		}
		
		//B、请求地址URL
		var opt_url={
			url_list			:basePath + "contChange/contChange!list.action"//查询数据URL
			,url_contract		:basePath + "contInfo/contInfo!list.action"//合同下拉数据
			,url_remove			:basePath + "contDetail/contDetail!removeid.action"//删除合同物资数据
			,url_change			:basePath + "contChange/contChange!save.action"//删除合同物资数据
		}
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		this.InitData=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.query_table.bootstrapTable($.extend(g_bootstrapTable_Options,
					{
				      url			 	:opt_all.url_list   // 请求后台的URL（*）
			         ,queryParams	  	:queryParams		// 传递参数（*）
			         ,onDblClickRow		:onDblClickRow		// 行双击事件
			         ,onSort			:onSort             // 排序事件
		             ,rowStyle			:comm_rowStyle		//行样式，可自定义
		             ,onLoadSuccess		:comm_onLoadSuccess //数据加载错误
		             ,onCheckAll 		:onCheckAll   //全选
		             ,onCheck  :onCheck   //单选
		             ,onUncheck  :onUncheck   //不选
		             ,onUncheckAll :onUncheckAll  //全不选
		             ,singleSelect		:false
		             ,uniqueId			:"ID"
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
			    $("#query_table tbody tr[data-uniqueid="+rows.ID+"]").removeClass("success");
			   }
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				//查询条件
				var opt_parms={
					"contChangeFormBean.contChangeBean.contractNo"			:opt_all.select_contract.val() // 查询关键字
					,"contChangeFormBean.searchName"			:opt_control.query_searchName.val() // 查询关键字
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		    		"contChangeFormBean.pageBean.limit"			: params.limit   // 页面大小
				      ,"contChangeFormBean.pageBean.offset"		: params.offset  // 当前记录偏移条数
				      ,"contChangeFormBean.pageBean.sort"		: params.sort  	  // 排序列名
				      ,"contChangeFormBean.pageBean.sortOrder" 	: params.order   // 排位命令（desc，asc）
				      ,"contChangeFormBean.engineerNm"			:engineeringNm
			    };
			    temp=$.extend(temp,opt_parms,opt);
				return temp;
			}
			// 双击事件
			function onDblClickRow(row){
				$(".table tbody tr[data-uniqueid="+row.ID+"]").addClass("success");
				if (row) {
					_table(row.ID);
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
			opt_all.query_add.bind("click",event_add);
			opt_all.query_ref.bind("click",event_ref);
			
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
		
		this.InitSelect = function (){
			initSelectContract();
		}
	    // 修改记录，调出窗体,公开函数
		this.edit=function(id) {
			_edit(id);
		}
		//删除单条物资
		this.del=function(id) {
			_del(id);
		}
		//显示合同详细
		this.onClickContract = function(id){
			_table(id);
		}
		//新增和编辑函数
		function _edit(id){
			//加载Edit页面基本数据，成功后调用显示编辑页面
			LoadEditData(id);
		}
		//删除单条物资
		function _del(id){
			var url= opt_all.url_remove+"?contDetailFormBean.ids="+id
//			confirm("合同变更","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
//			   if(result){
			 		common_ajax(null, url, function(response){
						// 删除后，从后台取出返回数据
					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
					    msg.show();	
			 		}); 
//               }
//			}); 
		}
		//显示详情
		function _table(queryId){
			var url = opt_all.url_list+"?contChangeFormBean.contChangeBean.id=" + queryId;
			common_ajax(null,url, function(response) {
				for(var key in response.rows[0]){
				   if($("#"+key)[0]){
					   $("#"+key).html(response.rows[0][key]);
				   }
			   }
				if(response.rows[0]["MATERIALSTATE"] == 2){
					$("#MATERIALSTATE").html("<font color='red'>移除</font>");
				}else if(response.rows[0]["MATERIALSTATE"] == 1){
					$("#MATERIALSTATE").html("<font color='black'>变更</font>");
				}
				$("#PRICE").html(Number(response.rows[0]["PRICE"]).toFixed(2));
				$("#PRECHANGEPRICE").html(Number(response.rows[0]["PRECHANGEPRICE"]).toFixed(2));
				$("#AFTERCHANGEPRICE").html(Number(response.rows[0]["AFTERCHANGEPRICE"]).toFixed(2));
				$("#DIFFERENCE").html(Number(response.rows[0]["DIFFERENCE"]).toFixed(2));
				$("#DIFFERENCETAX").html(Number(response.rows[0]["DIFFERENCETAX"]).toFixed(2));
				
				
				opt_all.query_info_show.modal({
					 show 	   : true
					,backdrop  : "static" // 背景遮挡
					,moveable  : true
				}).on('hide.zui.modal', function() {
					$(".table tbody tr[data-uniqueid="+queryId+"]").removeClass("success");
	            });
			});
		}

		//上传文件
		this.uploading=function uploading_data(id){
			Load_EditSelectData( 
				function(){ 
					LoadUploadingData(id);
				}
			);
		}
		
		//附件信息
		function LoadUploadingData(id){
			var code=$("#code"+id).val();
			
			//查询已上传文件
//			var url=basePath+'file/upload!queryFileByColumnCode.action?tablePkColumn='+code;
//			common_ajax(null,url, function(response) {
//				//文件上传
//				filesUpload(code,response.staticFiles);
//			});
			opt_all.uploading_data_dialog.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
			});
			opt_all.uploading_data_dialog.on('hidden.zui.modal',function(){
				/*if(file_change_state==1){
					window.location.reload();
				}*/
				window.location.reload();
			})
		}
		
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(id){
			var url = opt_all.url_list+"?contInfoFormBean.contInfoBean.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				for(var key in response.rows[0]){
					//主合同信息
				   if($("#"+key+"_detail")[0]){
					   $("#"+key+"_detail").html(response.rows[0][key]);
				   }
				}
				$("#CONTRACTDATE_detail").html(response.rows[0]["CONTRACTSTARTDATE"] 
				+ " 至 " + response.rows[0]["CONTRACTENDDATE"]);
				$("#SUPPLYDATE_detail").html(response.rows[0]["SUPPLYSTARTDATE"] 
				+ " 至 " + response.rows[0]["SUPPLYENDDATE"]);
				
				opt_all.data_body.html("");
				//加载合同物资
				for(var i=0;i < response.contDetails.length;i++){
					var html = "<tr>";
						//序号
						html += "<td style='text-align: center;line-height:14px;'>" + (i+1) + "</td>";
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
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;' data-type='text' value='"+response.contDetails[i].MATERIELUNIT+"' class='form-control' readonly='true' name='materielUnit'></td>";
						//规格
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;' data-type='text' value='"+(response.contDetails[i].MATERIALNORMS==null?"":response.contDetails[i].MATERIALNORMS)+"' class='form-control' readonly='true' name=''></td>";
						//变更前数量
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;' data-type='text' value='"+response.contDetails[i].MATERIELNUM+"' class='form-control' readonly='true' name='preChangeNum'></td>";
						//变更后数量
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;' data-type='text' value='' class='form-control'  name='afterChangeNum'></td>";
						//单价
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color:#fff;' data-type='text' value='"+(response.contDetails[i].PRICE==null?"":response.contDetails[i].PRICE)+"' class='form-control' readonly='true' name=''></td>";
						//差额不含税
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;' data-type='text' value='' class='form-control' name='difference'></td>";
						//差额含税
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;' data-type='text' value='' class='form-control' name='differenceTax'></td>";
						//变更前价格
						html += "<td class='hidden'><input style='height:30px;border: 0px;' data-type='text' value='" + response.contDetails[i].PROPOSALPRICE + "' class='form-control' name='preChangePrice'></td>";
						//变更后价格
						html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;' data-type='text' value='' class='form-control' name='afterChangePrice'></td>";
						//操作
						html += "<td style='padding: 0px;text-align: center;'>";
						html += 	"<a href='#' onclick='removeSelf("+response.contDetails[i].ID+",event)'>";
						html +=			"<button class='btn btn-xs btn-danger'>";
						html +=				"<div class='visible-md visible-lg'><i class='icon icon-times'></i>&nbsp;删除</div>";
						html +=				"<div class='visible-xs visible-sm'><i class='icon icon-times'></i></div>";
						html +=			"</button>";
						html +=		"</a>";
						html +=	"</td>";
						html += "</tr>";
					opt_all.data_body.append(html);
				}
				
				opt_all.edit_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。

	            });
			});
		}
		
		function initSelectContract(){
			opt_all.select_contract.empty();
			var url = opt_all.url_contract+"?contInfoFormBean.pageBean.limit=9999999";
			opt_all.select_contract.append("<option value=''>请选择合同</option>");
			
			$.ajax({
				url: url, 
				success: function(json){
					var data =  $.parseJSON(json);
					$.each(data.rows, function(i) {
						opt_all.select_contract.append(
							'<option value=' + data.rows[i].CONTRACTNO + '>'+ data.rows[i].CONTRACTTITLE + '</option>');
					});
				}
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
		
		//****绑定事件
		//绑定添加或修改事件
		function event_add(){
			_edit(0);
		}
		
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
			opt_all.info_form.data('bootstrapValidator').resetForm(false);
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
			

(function($) {
	//当前工程内码
	var engineeringNm = sessionStorage.getItem("engineeringNm");
	var engineeringName = sessionStorage.getItem("engineeringName");
	$.Storage_Query = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 query_table 			:$('#query_table')		//页面BootStrapTable的ID
			,query_searchName		:$('#query_searchName')	//页面模糊查询input
			,query_info_show		:$("#query_info_show")	//双击事件窗口id
			,query_ref				:$("#query_ref")		//模糊查询
			,query_form				:$("#query_form")
			,select_supply			:$("#select_supply")	//查询条件供货厂商
			,select_engineerCode	:$("#engineerCode")		//查询条件供货厂商
			,edit_supply			:$("#supply")			//查询条件供货厂商
			,edit_demand			:$("#demandUnit")		//查询条件需求单位
			,medium_name			:$("#medium_name")		//查询条件介质名称
			,edit_dialog			:$("#edit_dialog")		//添加编辑窗口
			,evaluation_dialog		:$("#evaluation_dialog")//履约评价窗口
			,query_add				:$("#query_add")		//添加按钮
			,material_add			:$("#material_add")		//添加物资按钮
			,self_remove			:$(".removeSelf")		//删除物资按钮
			,data_body				:$("#data_body")		//物资添加列表tbody
			,detail_body			:$("#detail_body")		//物资详细列表tbody
			,evaluate_table			:$("#evaluate_table")	//履约评价详细列表
			,noneEvaluate			:$("#noneEvaluate")		//履约评价待评价提示
			,btn_del				:$("#btn_del")			//批量删除按钮
			,info_form				:$("#info_form")		//添加编辑的表单
			,removeIds				:$("#removeIds")		//要删除的物资id
			,evaluation_form		:$("#evaluation_form")		//履约评价的表单
			,uploading_data_dialog	:$('#uploading_data_dialog') //文件上传模态框
			,enginner_dialog		:$('#enginner_dialog') //选择工程模态框
			//,loadTreeDate			:$('#loadTreeDate') //树形选择框
			,zTreeDiv				:$('#zTreeDiv') //ztree域
			,treeDemo				:$('#treeDemo') //ztree
			,engineerCode			:$('#engineerCode') //编辑页工程编码
			,engineerName			:$('#engineerName')	//编辑页工程名称
		}
		
		//B、请求地址URL
		var opt_url={
			url_list			:basePath + "contInfo/contInfo!list.action"//查询数据URL
			,url_save			:basePath + "contInfo/contInfo!save.action"//添加或修改保存主合同URL
			,url_save_evaluate	:basePath + "contEvaluate/contEvaluate!saveEvaluate.action"//添加或修改保存履约评价URL
			,url_remove			:basePath + "contInfo/contInfo!removeids.action"//删除URL
			,url_edit			:basePath + "contInfo/contInfo!edit.action"//修改查询数据
			,url_edit_evaluate	:basePath + "contEvaluate/contEvaluate!editEvaluate.action"//修改履约评价时查询数据
			,url_supply			:basePath + "contInfo/contInfo!queryAllsupply.action"//供货厂商及需求单位下拉数据
			,url_engineer		:basePath + "contInfo/contInfo!queryAllEngineer.action"//所属工程下拉数据
			,url_demand			:basePath + "plan/demand!queryDemand.action"//需求单位下拉数据
			,url_materialInfo	:basePath + "contInfo/contInfo!getMaterialInfo.action"//查询物料详细信息
			,url_details		:basePath + "business/contractMng/mainContractMng/details.jsp"//查询物料详细信息
			,url_materialData	:basePath + "contInfo/contInfo!queryAllMaterial.action"//查询所有物料信息
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
			         ,onDblClickRow 	:onDblClickRow		// 行双击事件
			         ,onSort			:onSort             // 排序事件
		             ,rowStyle			:comm_rowStyle		//行样式，可自定义
		             ,onLoadSuccess		:comm_onLoadSuccess //数据加载错误
		             ,onCheckAll 		:onCheckAll   //全选
		             ,onCheck  			:onCheck   //单选
		             ,onUncheck  		:onUncheck   //不选
		             ,onUncheckAll 		:onUncheckAll  //全不选
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
			    $("#query_table tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
			   }
			// 提交查询函数
			function queryParams(params) {  // 配置参数
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
			      ,"contInfoFormBean.contInfoBean.engineerCode":engineeringNm
			    };
			    temp=$.extend(temp,opt_parms,opt);
				return temp;
			}
			// 双击事件
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
			opt_all.query_add.bind("click",event_add);
			opt_all.btn_del.bind("click",event_del);
			opt_all.query_ref.bind("click",event_ref);
			opt_all.material_add.bind("click",event_addMaterial);
			opt_all.medium_name.bind("change",event_ref);
			//opt_all.loadTreeDate.bind("click",event_showZtree);
			
			//初始化主合同编辑表单 保存button类型为submit
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
			
			//初始化履约评价编辑表单 保存button类型为submit
			//校验履约评价不能为0
			opt_all.evaluation_form.bootstrapValidator({
                excluded: ':disabled',
                fields: {
                    'contEvaluateFormBean.contEvaluateBean.materialArrival': {
                        message: '物资到货评分没有校验',
                        validators: {
                            notEmpty: {
                                message: '物资到货评分不能为空'
                            },regexp:{
	                        	message:'物资到货评分不能为空',
	                        	regexp:/^[1-9]$/
	                        }
                        }
                    },'contEvaluateFormBean.contEvaluateBean.productQuality': {
                        message: '产品质量评分没有校验',
                        validators: {
                            notEmpty: {
                                message: '产品质量评分不能为空'
                            },regexp:{
	                        	message:'产品质量评分不能为空',
	                        	regexp:/^[1-9]$/
	                        }
                        }
                    },'contEvaluateFormBean.contEvaluateBean.fieldService': {
                        message: '现场服务评分没有校验',
                        validators: {
                            notEmpty: {
                                message: '现场服务评分不能为空'
                            },regexp:{
	                        	message:'现场服务评分不能为空',
	                        	regexp:/^[1-9]$/
	                        }
                        }
                    },'contEvaluateFormBean.contEvaluateBean.materialOperation': {
                        message: '物资投运评分没有校验',
                        validators: {
                            notEmpty: {
                                message: '物资投运评分不能为空'
                            },regexp:{
	                        	message:'物资投运评分不能为空',
	                        	regexp:/^[1-9]$/
	                        }
                        }
                    },'contEvaluateFormBean.contEvaluateBean.warrantySituation': {
                        message: '质保情况评分没有校验',
                        validators: {
                            notEmpty: {
                                message: '质保情况评分不能为空'
                            },regexp:{
	                        	message:'质保情况评分不能为空',
	                        	regexp:/^[1-9]$/
	                        }
                        }
                    },'contEvaluateFormBean.contEvaluateBean.evaluation': {
                        message: '总体评价没有校验',
                        validators: {
                            notEmpty: {
                                message: '总体评价不能为空'
                            },regexp:{
	                        	message:'总体评价不能为空',
	                        	regexp:/^[1-9]$/
	                        }
                        }
                    }
                }
			}).on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save_evaluation();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
		}
		//下拉数据初始化
		this.InitSelect = function (){
			Load_select_demandUnit();
			Load_select_supply();
			//Load_select_engineer();
		}
		
	    // 修改记录，调出窗体,公开函数
		this.edit=function(id) {
			_edit(id);
		}
		// 履约评价
		this.evaluation=function(id) {
			_Evaluation(id);
		}
		//显示合同详细
		this.onClickContract = function(id){
			window.location.href = opt_all.url_details + "?contractId="+id;
		}
		//单条删除
		this.del=function(id){
			if(id != null && id != "" && id != 0){
				_del(id);
			}
		}
		//下拉框改变时获取物料信息
		this.getMaterialInfo=function(e){
			var url= opt_all.url_materialInfo+"?contInfoFormBean.ids="+e.value;
	 		common_ajax(null, url, function(response){
	 			var trIndex = e.parentNode.parentNode.sectionRowIndex;//tr标签下标
	 			if(e.value != null && e.value != ""){
		 			opt_all.data_body.eq(0).find(".materielUnit").eq(trIndex).val(response.materialInfo[0].UNIT);
		 			opt_all.data_body.eq(0).find(".materialNorms").eq(trIndex).val(response.materialInfo[0].MATERIAL_NORMS);
		 			opt_all.data_body.eq(0).find(".price").eq(trIndex).val(Number(response.materialInfo[0].PRICE).toFixed(2));
		 			countTotalPrice();
	 			}else {
	 				opt_all.data_body.eq(0).find(".materielUnit").eq(trIndex).val("");
	 				opt_all.data_body.eq(0).find(".materialNorms").eq(trIndex).val("");
	 				opt_all.data_body.eq(0).find(".price").eq(trIndex).val("");
	 				countTotalPrice();
	 			}
	 		}); 
		}
		
		//绑定删除事件
		function event_del(){
			_removeids();
		}
		// 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids = g_select_and_tip(opt_all.query_table,"ID");
           if (ids.length==0) return;
           
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");
           
           var url= opt_all.url_remove+"?contInfoFormBean.ids="+ids
            
           confirm("主合同管理","您确定要删除这些记录吗？","icon-remove-sign", function(result) {
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
			var url= opt_all.url_remove+"?contInfoFormBean.ids="+id
			$(".table tbody tr[data-uniqueid="+id+"] td").addClass("row-bcground");
			confirm("主合同管理","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
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
		
		//新增和编辑函数
		function _edit(id){
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
				function(){ 
					LoadEditData(id);
				}
			);
		}
		
		//初始化ztree
		/*function zTreeDataLoad(type){
//			opt_all.zTreeDiv.hide();
			
			var url = opt_all.url_engineer;
			common_ajax(null,url, function(data) {
				loadzTreeInfo(data.engineers,type);
			});
		}*/
		
		/*function loadzTreeInfo(zTreeArray,type){
			var setting = {
					data: {
						simpleData: {
							enable: true,
							 idKey: "THISCODE",
							 pIdKey: "PCODE",
							 rootPId: ""
						},
					},
					view: {
						selectedMulti: false,
						},
					check: {
							enable: true,
							chkStyle: "radio",  //单选框
				            radioType: "all"   //对所有节点设置单选
						},
					callback: {
							onCheck: zTreeOnCheck
						}
				};
			var zNodes = zTreeArray;
			
			var NM = "";
			//选中/取消
		    function zTreeOnCheck(event,treeId,treeNode) {
		    	if(NM != treeNode.NM){
		    		opt_all.engineerCode.val(treeNode.NM);
		    		opt_all.engineerName.val(treeNode.name);
		    		NM = treeNode.NM;
		    	}else {
		    		opt_all.engineerCode.val("");
		    		opt_all.engineerName.val("");
		    		NM = "";
		    	}
			}
			$.fn.zTree.init(opt_all.treeDemo, setting, zNodes);
			if(type == "edit"){
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				var node = treeObj.getNodeByParam("NM", opt_all.engineerCode.val());
				var parent = node.getParentNode();  
				if(parent != null && !parent.open){  
					treeObj.expandNode(parent,true,true);  
	            }
				treeObj.checkNode(node,true,true);//勾选节点  
				opt_all.engineerName.val(node.name);
			}
		}*/
		
		//点击新增物资时显示输入框
		function _edit_material(detailData){
			common_ajax(null, opt_all.url_materialData, function(response){
				var material_data = response.materials;
				if(detailData == undefined || detailData == null){
					var num = opt_all.data_body.find("tr").length +1;
					var selHtml = "<select required onchange='query.getMaterialInfo(this)' style='height:30px;border: 0px;' data-type='text' class='form-control' name='materielCode'>";
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
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: center;' readonly='true' data-type='text' class='form-control materielUnit'  name='materielUnit'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;' readonly='true' data-type='text' class='form-control materialNorms'  name='materialNorms'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;text-align: right;' data-type='text' class='form-control materiel-num' onkeyup='countTotalPrice()' name='materielNum'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;' onkeyup='countTotalPrice()'  data-type='text' class='form-control price' name='price'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;' readonly='true' data-type='text' class='form-control proposal-price' onkeyup='' name='proposalPrice'></td>";
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
					
					var selHtml = "<select required onchange='query.getMaterialInfo(this)' style='height:30px;border: 0px;' data-type='text' class='form-control' name='materielCode'>";
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
					html += "<td style='padding: 0px;display:none;'><input data-type='text' class='form-control' value='"+detailData.CODE+"' name='code'></td>";
					html += "<td style='padding: 0px;'>" + selHtml + "</td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: center;' readonly='true' data-type='text' class='form-control materielUnit' value='"+detailData.MATERIELUNIT+"' name='materielUnit'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;' readonly='true' data-type='text' class='form-control materialNorms' value='"+(detailData.MATERIALNORMS==null?"":detailData.MATERIALNORMS)+"' name='materialNorms'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;text-align: right;' data-type='text' class='form-control materiel-num' onkeyup='countTotalPrice()' value='"+detailData.MATERIELNUM+"' name='materielNum'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;' onkeyup='countTotalPrice()' data-type='text' class='form-control price' value='"+(detailData.PRICE==null?"":Number(detailData.PRICE).toFixed(2))+"' name='price'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;background-color: #fff;text-align: right;' readonly='true' data-type='text' class='form-control proposal-price' onkeyup='' value='"+Number(detailData.PROPOSALPRICE).toFixed(2)+"' name='proposalPrice'></td>";
					html += "<td style='padding: 0px;'><input style='height:30px;border: 0px;' data-type='text' class='form-control' value='"+detailData.GOODDESC+"' name='goodDesc'></td>";
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
		
		// 保存主合同数据
		function _save() {
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form.serialize();
	 		common_ajax(json, opt_all.url_save, function(response){
	 			opt_all.edit_dialog.modal("hide");
			    //重置表单
			    _reset();
			    // 刷新列表
			    _refresh();
	 		});
		}
		
		// 保存履约评价数据
		function _save_evaluation(){
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
			var json = opt_all.evaluation_form.serialize();
			common_ajax(json, opt_all.url_save_evaluate, function(response){
				opt_all.evaluation_dialog.modal("hide");
				//重置列表
				_reset_evaluation();
				// 刷新列表
				_refresh();
			});
		}
		
		//新增和编辑履约评价加载数据弹窗
		function _Evaluation(id){
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
				function(){ 
					LoadEvaluationData(id);
				}
			);
		}
		//显示详情
		function _table(queryId){
			opt_all.detail_body.html("");
			var url = opt_all.url_list+"?contInfoFormBean.contInfoBean.id=" + queryId;
			common_ajax(null,url, function(response) {
				for(var key in response.rows[0]){
					//主合同信息
				   if($("#"+key+"_detail")[0]){
					   $("#"+key+"_detail").html(response.rows[0][key]);
				   }
				   //履约评价信息
				   if($("#"+key+"_evaDetail")[0]){
					   $("#"+key+"_evaDetail").val(response.rows[0][key]);
				   }
				}
				$("#CONTRACTDATE_detail").html(response.rows[0]["CONTRACTSTARTDATE"] 
				+ " 至 " + response.rows[0]["CONTRACTENDDATE"]);
				$("#SUPPLYDATE_detail").html(response.rows[0]["SUPPLYSTARTDATE"] 
				+ " 至 " + response.rows[0]["SUPPLYENDDATE"]);

				//履约评价
				if(response.rows[0]['EVASTATE'] == '待评价'){
					opt_all.evaluate_table.hide();
					opt_all.noneEvaluate.show();
				}else {
					opt_all.evaluate_table.show();
					opt_all.noneEvaluate.hide();
				}

				//加载合同物资
				for(var i=0;i < response.contDetails.length;i++){
					var html = "<tr>";
						html += "<td style='text-align: center;'>" + (i+1) + "</td>";
						html += "<td style='text-align: left;'>" + response.contDetails[i].MATERIELNAME + "</td>";
						html += "<td style='text-align: center;'>" + response.contDetails[i].MATERIELUNIT + "</td>";
						html += "<td style='text-align: center;'>" + (response.contDetails[i].MATERIALNORMS==null?"":response.contDetails[i].MATERIALNORMS) + "</td>";
						html += "<td style='text-align: right;'>" + response.contDetails[i].MATERIELNUM + "</td>";
						html += "<td style='text-align: right;'>" + (response.contDetails[i].PRICE==null?"":response.contDetails[i].PRICE) + "</td>";
						html += "<td style='text-align: right;'>" + response.contDetails[i].PROPOSALPRICE + "</td>";
						html += "<td style='text-align: left;'>" + response.contDetails[i].GOODDESC + "</td>";
						html += "</tr>";
					opt_all.detail_body.append(html);
				}
				
				//初始化星级评价
				initDetailStars();
				
				opt_all.query_info_show.modal({
					 show 	   : true
					,backdrop  : "static" // 背景遮挡
					,moveable  : true
				}).on('shown.zui.modal', function() {
	            });
			});
		}
		
		//加载供货厂商及需求单位下拉
		function Load_select_supply(){
			opt_control.select_supply.empty();
			opt_control.edit_supply.empty();
			var url = opt_all.url_supply;
			opt_control.select_supply.append("<option value=''>请选择供货厂商</option>");
			opt_control.edit_supply.append("<option value=''>请选择供货厂商</option>");
			common_ajax(null,url, function(data) {
				$.each(data.supplys, function(i) {
					opt_control.select_supply.append(
						'<option value=' + data.supplys[i].SUPPLIER_CODE + '>'+ data.supplys[i].SUPPLY_FULL_NAME + '</option>');
					opt_control.edit_supply.append(
						'<option value=' + data.supplys[i].SUPPLIER_CODE + '>'+ data.supplys[i].SUPPLY_FULL_NAME + '</option>');
				});
			});
		}
		
		/*//加载所属工程下拉
		function Load_select_engineer(){
			opt_control.select_engineerCode.empty();
			var url = opt_all.url_engineer;
			opt_control.select_engineerCode.append("<option value=''>请选择所属工程</option>");
			common_ajax(null,url, function(data) {
				$.each(data.engineers, function(i) {
					opt_control.select_engineerCode.append(
						'<option value=' + data.engineers[i].NM + '>'+ data.engineers[i].ENGINEER_NAME + '</option>');
				});
			});
		}*/
		
		//加载需求单位下拉
		function Load_select_demandUnit(){
			opt_control.edit_demand.empty();
			var url = opt_all.url_demand;
			opt_control.edit_demand.append("<option value=''>请选择需求单位</option>");
			common_ajax(null,url, function(data) {
				$.each(data.demandList, function(i) {
					opt_control.edit_demand.append(
						'<option value=' + data.demandList[i] + '>'+ data.demandList[i] + '</option>');
				});
			});
		}
		
		//上传文件
		this.uploading=function(contId,evalId,engineerCode){
			//履约评价的文件上传
			filesUpload_eval(evalId,engineerCode);
			//主合同的文件上传
			filesUpload(contId,engineerCode);
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
		
		//主合同的文件上传
		function filesUpload(id,engineerCode){
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
		
		//履约评价的文件上传
		function filesUpload_eval(id,engineerCode){
			if(id == null || id == ""){
				$('#myUploader_eval').hide();
				$('#noneEvaluate').show();
				return;
			}else {
				$('#noneEvaluate').hide();
			}
			var file_options;
			var url_queryFileUpload=basePath+"file/upload!queryFileUpload.action?tableName=CONT_EVALUATE&tablePkColumn="+id+"&engineerCode="+engineerCode;
			common_ajax_async(null,url_queryFileUpload,function(response){
	 			file_options = {
	 					//初始化参数
	 					url:basePath+"file/upload!saveFileUpload.action?tableName=CONT_EVALUATE&tablePkColumn="+id+"&engineerCode="+engineerCode	//文件上传地址
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
	 			$('#myUploader_eval').uploader(file_options);
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
			//_reset();
			$("#engineerName").val(engineeringName)
			 $("#engineerCode").val(engineeringNm)
			if(id > 0){
				opt_all.data_body.html("");//清除物资明细
				var url = opt_all.url_edit+"?contInfoFormBean.contInfoBean.id=" + id;
				// 动态加载页面数据
				common_ajax(null,url, function(response) {
					// 获取到数据，显示在界面上
					comm_loadFormData(response.infoBean);//显示主合同信息
					for(var i=0;i<response.contDetail.length;i++){//物资明细
						_edit_material(response.contDetail[i]);
					}
					//zTreeDataLoad("edit");//初始化ztree
					
					opt_all.edit_dialog.find('.modal-title').html("<i class='icon icon-pencil'></i>&nbsp;修改合同信息");
					///////////////////////////////////////////
					opt_all.edit_dialog.modal({
						 show : true
						,backdrop : "static" // 背景遮挡
						,moveable : true
					}).on('hide.zui.modal', function() {
						_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
		            });
				});
			}else{
				//先清除添加过的数据再弹窗
				opt_all.edit_dialog.find('.modal-title').html("<i class='icon icon-plus-sign'></i>&nbsp;新增合同信息") ;
				opt_all.data_body.html("");
				//zTreeDataLoad("add");//初始化ztree
				opt_all.edit_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('hide.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
	            });
			}
		}
		
		//加载履约评价页面上数据，并调出页面显示
		function LoadEvaluationData(id,onlyread){
			var url = opt_all.url_edit_evaluate+"?contEvaluateFormBean.contEvaluateBean.id=" + id;
			// 动态加载页面数据
			common_ajax(null,url, function(response) {
				// 获取到数据，显示在界面上
				for(var key in response.info[0]){
				   if($("#"+key+"_evaluate")[0]){
					   $("#"+key+"_evaluate").val(response.info[0][key]);
				   }
				}
				initStars();
//	            var title="履约评价";
//				opt_all.evaluation_dialog.find('.modal-title').html(title) ;
				_reset_evaluation();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				///////////////////////////////////////////
				opt_all.evaluation_dialog.modal({
					show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('hide.zui.modal', function() {
					
				});
			});
		}
		
		//添加星级评价初始化
		function initStars(){
			var inp1=$("#MATERIALARRIVAL_evaluate"); 
			inp1.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			}); 
			var inp2=$("#PRODUCTQUALITY_evaluate"); 
			inp2.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			});
			var inp3=$("#FIELDSERVICE_evaluate"); 
			inp3.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			});
			var inp4=$("#MATERIALOPERATION_evaluate"); 
			inp4.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			});
			var inp5=$("#WARRANTYSITUATION_evaluate"); 
			inp5.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			});
			var inp6=$("#EVALUATION_evaluate"); 
			inp6.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			});
		}
		
		//详细页面星级评价初始化
		function initDetailStars(){
			var inp1=$("#MATERIALARRIVAL_evaDetail"); 
			inp1.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			}); 
			var inp2=$("#PRODUCTQUALITY_evaDetail"); 
			inp2.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			});
			var inp3=$("#FIELDSERVICE_evaDetail"); 
			inp3.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			});
			var inp4=$("#MATERIALOPERATION_evaDetail"); 
			inp4.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			});
			var inp5=$("#WARRANTYSITUATION_evaDetail"); 
			inp5.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			});
			var inp6=$("#EVALUATION_evaDetail"); 
			inp6.rating('refresh',{
				stars:5,
				min: 0, 
				max: 5, 
				step: 1, 
				showClear: false
			});
		}
		
		//****绑定事件
		//绑定添加或修改事件
		function event_showZtree(){
			opt_all.enginner_dialog.modal({
				show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
//				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
			});
		}
		
		function event_add(){
			_edit(0);
		}

		function event_addMaterial(){
			//新增物资时显示输入框
			_edit_material();
		}
		
		//绑定刷新事件
		function event_ref(){
			 _refresh();
		}
		
		//删除物资输入框
		function event_selfRemove(){
    		var myId = $(this).data("id");
    		if(myId==null){
    			$(this).parent("td").parent("tr").remove();
    		}
		}
		
		//****绑定事件******end
	    // 刷新
	    function _refresh(){
	    	opt_all.query_table.bootstrapTable('refresh');
	    }
	    this.flash=function(nm){
	    	engineeringNm=nm;
			_refresh();
		}
		// 重置主合同
		function _reset(){
			opt_all.removeIds.val("");
			opt_all.info_form.data('bootstrapValidator').resetForm(true);
		}
		// 重置履约评价表单
		function _reset_evaluation(){
			opt_all.evaluation_form.data('bootstrapValidator').resetForm(true);
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
			

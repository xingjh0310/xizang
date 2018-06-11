(function($) {
	$.Storage_Query = function(option) {
		// option自定义参数覆盖
		// A、界面控件变量
		var opt_control = {
			query_table : $('#query_table') // 页面BootStrapTable的ID
			,
			query_searchName : $('#query_searchName') // 页面模糊查询input
			,
			query_info_show : $("#query_info_show") // 双击事件窗口id
			,
			query_ref : $("#query_ref") // 模糊查询
			,
			query_form : $("#query_form"),
			select_supply : $("#select_supply") // 查询条件供货厂商
			,
			select_engineerCode : $("#engineerCode") // 查询条件供货厂商
			,
			edit_supply : $("#supply") // 查询条件供货厂商
			,
			medium_name : $("#medium_name") // 查询条件介质名称
			,
			edit_dialog : $("#edit_dialog") // 添加编辑窗口
			,
			evaluation_dialog : $("#evaluation_dialog")// 履约评价窗口
			,
			query_add : $("#query_add") // 添加按钮
			,
			btn_del : $("#btn_del") // 批量删除按钮
			,
			info_form : $("#info_form") // 添加编辑的表单
			,
			evaluation_form : $("#evaluation_form") // 履约评价的表单
			,
			uploading_data_dialog : $('#uploading_data_dialog')
		// 文件上传模态框
		}
		// B、请求地址URL
		var opt_url = {
			url_list : basePath + "contEvaluate/contEvaluate!list.action"// 查询数据URL
			,
			url_save : basePath + "contInfo/contInfo!save.action"// 添加或修改保存主合同URL
			,
			url_save_evaluate : basePath
					+ "contEvaluate/contEvaluate!saveEvaluate.action"// 添加或修改保存履约评价URL
			,
			url_remove : basePath
					+ "contEvaluate/contEvaluate!removeids.action"// 删除URL
			,
			url_edit : basePath + "contInfo/contInfo!edit.action"// 修改查询数据
			,
			url_edit_evaluate : basePath
					+ "contEvaluate/contEvaluate!editEvaluate.action"// 修改履约评价时查询数据
			,
			url_supply : basePath + "contInfo/contInfo!queryAllsupply.action",
			url_engineer : basePath
					+ "contInfo/contInfo!queryAllEngineer.action"
		}
		// 全部变量，自定义的覆盖默认变量
		var opt_all = ($.extend(opt_control, opt_url, option));
		// ////////////////////////////////////////////////////////////////////////////////////////////////
		this.InitData = function(opt) {
			opt_all = $.extend(opt_all, opt);
			opt_all.query_table.bootstrapTable($.extend(
					g_bootstrapTable_Options, {
						url : opt_all.url_list // 请求后台的URL（*）
						,
						queryParams : queryParams // 传递参数（*）
						,
						onDblClickRow : onDblClickRow // 行双击事件
						,
						onSort : onSort // 排序事件
						,
						rowStyle : comm_rowStyle // 行样式，可自定义
						,
						onLoadSuccess : comm_onLoadSuccess // 数据加载错误
						,
						onCheckAll : onCheckAll // 全选
						,
						onCheck : onCheck // 单选
						,
						onUncheck : onUncheck // 不选
						,
						onUncheckAll : onUncheckAll // 全不选
						,
						singleSelect : false,
						pageSize : 15
					}));
			// 选中多行改变表格背景色
			function onCheckAll(rows) {
				for (var i = 0; i < rows.length; i++) {
					commRowStyle(i);
				}
			}
			// 循环改变所有行颜色
			function commRowStyle(i) {
				$("#query_table tbody tr[data-index=" + i + "]").addClass(
						"success");
			}
			// 全不选时颜色恢复
			function onUncheckAll() {
				$("#query_table tbody tr").removeClass("success");
			}
			// 选中一行改变表格背景色
			function onCheck(rows) {
				$("#query_table tbody tr[data-uniqueid=" + rows.id + "]")
						.addClass("success");
			}
			// 不选中时颜色恢复
			function onUncheck(rows) {
				$("#query_table tbody tr[data-uniqueid=" + rows.id + "]")
						.removeClass("success");
			}
			// 提交查询函数
			function queryParams(params) { // 配置参数
				// 查询条件
				var opt_parms = {
					"contEvaluateFormBean.searchName" : opt_control.query_searchName
							.val() // 查询关键字
				};
				var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
					"contEvaluateFormBean.pageBean.limit" : params.limit // 页面大小
					,
					"contEvaluateFormBean.pageBean.offset" : params.offset // 当前记录偏移条数
					,
					"contEvaluateFormBean.pageBean.sort" : params.sort // 排序列名
					,
					"contEvaluateFormBean.pageBean.sortOrder" : params.order
				// 排位命令（desc，asc）
				};
				temp = $.extend(temp, opt_parms, opt);
				return temp;
			}
			// 双击事件
			function onDblClickRow(row) {
				if (row) {
					_table(row.ID);
					// Load_unit_details(row.serialNumber);
				}
			}
			// 排序事件
			function onSort(name, order) {
				_refresh();
			}
		}

		// //////////////////////////////////////////////////////////////////////////////////////////////////
		// 初始化增加、修改和删除,公开函数
		this.InitAddEditDel = function(opt) {
			opt_all = $.extend(opt_all, opt);
			opt_all.query_add.bind("click", event_add);
			opt_all.btn_del.bind("click", event_del);
			opt_all.query_ref.bind("click", event_ref);
			// opt_all.storage_name.bind("change",event_ref);
			opt_all.medium_name.bind("change", event_ref);

			// 初始化主合同编辑表单 保存button类型为submit
			opt_all.info_form.bootstrapValidator().on("success.form.bv",
					function(e) {
						e.preventDefault(); // 去掉默认提交事件
						// 校验数据正确,执行保存数据
						_save();

					}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// //校验数据正确
			});

			// 初始化履约评价编辑表单 保存button类型为submit
			opt_all.evaluation_form.bootstrapValidator().on("success.form.bv",
					function(e) {
						e.preventDefault(); // 去掉默认提交事件
						// 校验数据正确,执行保存数据
						_save_evaluation();
					}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});
		}
		// 加载单位表格详情
		function Load_unit_details(serial_number) {
			var url = opt_control.url_unit_table + "?registrationFormBean"
					+ ".registrationInfoBean.serialNumber=" + serial_number;
			common_ajax(null, url, function(response) {
				if (response.rows[0].isSecret == 1) {
					response.rows[0].isSecret = "是";
				} else { // 如果等于一就是涉密
					response.rows[0].isSecret = "否";
				}
				if (response.rows[0].transportModel == 1) {
					response.rows[0].transportModel = "公司派车";
				} else { // 如果等一就是公司派车
					response.rows[0].transportModel = "单位自送";
				}

				comm_loadFormDataList(response.rows[0]);
			});
		}
		this.InitSelect = function() {
			Load_select_supply();
			Load_select_engineer();
		}
		// 修改记录，调出窗体,公开函数
		this.edit = function(id) {
			_edit(id);
		}
		// 履约评价
		this.evaluation = function(id) {
			_Evaluation(id);
		}
		// 显示合同详细
		this.onClickContract = function(id) {
			_table(id);
			// Load_unit_details(row.serialNumber);
		}
		// 单条删除
		this.del = function(id) {
			var ids = verdict_del_update(opt_all.query_table, "ID", id);
			if (ids == id || ids == "" || ids == null) {
				_del(id);
			}
		}
		// 绑定删除事件
		function event_del() {
			_removeids();
		}
		// 批量删除
		function _removeids() {
			// 获取删除选中的ids
			var ids = g_select_and_tip(opt_all.query_table, "ID");
			if (ids.length == 0)
				return;

			var url = opt_all.url_remove + "?contEvaluateFormBean.ids=" + ids

			confirm("履约评价", "您确定要删除这些记录吗？", "icon-remove-sign",
					function(result) {
						if (result) {
							common_ajax(null, url, function(response) {
								// 删除后，从后台取出返回数据
								_refresh();
								var msg = new $.zui.Messager("消息提示："
										+ response.message, {
									placement : "center",
									type : "primary"
								});
								msg.show();

							});
						}
					});

		}

		// 删除单条记录
		function _del(id) {
			var url = opt_all.url_remove + "?contEvaluateFormBean.ids=" + id
			confirm("履约评价", "您确定要删除该条记录吗？", "icon-remove-sign",
					function(result) {
						if (result) {
							common_ajax(null, url, function(response) {
								// 删除后，从后台取出返回数据
								_refresh();
								var msg = new $.zui.Messager("消息提示："
										+ response.message, {
									placement : "center",
									type : "primary"
								});
								msg.show();
							});
						}
					});
		}

		// 新增和编辑函数
		function _edit(id) {
			// 加载Edit页面基本选择数据，成功后调用显示编辑页面
			// 加载字典分类数据下拉框数据
			Load_EditSelectData(function() {
				LoadEditData(id);
			});
		}

		// 保存主合同数据
		function _save() {
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
			var json = opt_all.info_form.serialize();
			common_ajax(json, opt_all.url_save, function(response) {
				opt_all.edit_dialog.modal("hide");
				// 重置表单
				_reset();
				// 刷新列表
				_refresh();
			});
		}

		// 保存履约评价数据
		function _save_evaluation() {
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
			var json = opt_all.evaluation_form.serialize();
			common_ajax(json, opt_all.url_save_evaluate, function(response) {
				opt_all.evaluation_dialog.modal("hide");
				// 重置列表
				_reset_evaluation();
				// 刷新列表
				_refresh();
			});
		}

		// 新增和编辑履约评价加载数据弹窗
		function _Evaluation(id) {
			// 加载Edit页面基本选择数据，成功后调用显示编辑页面
			// 加载字典分类数据下拉框数据
			Load_EditSelectData(function() {
				LoadEvaluationData(id);
			});
		}
		// 显示详情
		function _table(queryId) {
			var url = opt_all.url_list + "?contEvaluateFormBean.contEvaluateBean.id="
					+ queryId;
			common_ajax(null, url, function(response) {
				for ( var key in response.rows[0]) {
					if ($("#" + key + "_detail")[0]) {
						$("#" + key + "_detail").html(response.rows[0][key]);
					}
				}
				$("#CONTRACTDATE_detail").html(
						response.rows[0]["CONTRACTSTARTDATE"] + " 至 "
								+ response.rows[0]["CONTRACTENDDATE"]);
				$("#SUPPLYDATE_detail").html(
						response.rows[0]["SUPPLYSTARTDATE"] + " 至 "
								+ response.rows[0]["SUPPLYENDDATE"]);
				opt_all.query_info_show.modal({
					show : true,
					backdrop : "static" // 背景遮挡
					,
					moveable : true
				}).on('shown.zui.modal', function() {
					// _reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				});
			});
		}

		// 加载供货厂商下拉
		function Load_select_supply() {
			opt_control.select_supply.empty();
			opt_control.edit_supply.empty();
			var url = opt_all.url_supply;
			opt_control.select_supply
					.append("<option value=''>请选择供货厂商</option>");
			opt_control.edit_supply.append("<option value=''>请选择供货厂商</option>");
			common_ajax(null, url, function(data) {
				$.each(data.supplys, function(i) {
					opt_control.select_supply.append('<option value='
							+ data.supplys[i].SUPPLIER_CODE + '>'
							+ data.supplys[i].SUPPLY_FULL_NAME + '</option>');
					opt_control.edit_supply.append('<option value='
							+ data.supplys[i].SUPPLIER_CODE + '>'
							+ data.supplys[i].SUPPLY_FULL_NAME + '</option>');
				});
			});
		}

		// 加载所属工程下拉
		function Load_select_engineer() {
			opt_control.select_engineerCode.empty();
			var url = opt_all.url_engineer;
			opt_control.select_engineerCode
					.append("<option value=''>请选择所属工程</option>");
			common_ajax(null, url, function(data) {
				$.each(data.engineers, function(i) {
					opt_control.select_engineerCode.append('<option value='
							+ data.engineers[i].NM + '>'
							+ data.engineers[i].ENGINEER_NAME + '</option>');
				});
			});
		}

		// 上传文件
		this.uploading = function uploading_data(id) {
			Load_EditSelectData(function() {
				LoadUploadingData(id);
			});
		}

		// 附件信息
		function LoadUploadingData(id) {
			var code = $("#code" + id).val();

			// 查询已上传文件
			// var
			// url=basePath+'file/upload!queryFileByColumnCode.action?tablePkColumn='+code;
			// common_ajax(null,url, function(response) {
			// //文件上传
			// filesUpload(code,response.staticFiles);
			// });
			opt_all.uploading_data_dialog.modal({
				show : true,
				backdrop : "static" // 背景遮挡
				,
				moveable : true
			}).on('shown.zui.modal', function() {
			});
			opt_all.uploading_data_dialog.on('hidden.zui.modal', function() {
				/*
				 * if(file_change_state==1){ window.location.reload(); }
				 */
				window.location.reload();
			})
		}

		// 加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(id) {
			if (id > 0) {
				var url = opt_all.url_edit
						+ "?contInfoFormBean.contInfoBean.id=" + id;
				// 动态加载页面数据
				common_ajax(null, url, function(response) {
					// 获取到数据，显示在界面上
					comm_loadFormData(response.infoBean);
					opt_all.edit_dialog.find('.modal-title').html(
							"<i class='icon icon-edit'></i> 修改合同信息");
					// /////////////////////////////////////////
					opt_all.edit_dialog.modal({
						show : true,
						backdrop : "static" // 背景遮挡
						,
						moveable : true
					}).on('shown.zui.modal', function() {
						// _reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
					});
				});
			} else {
				// 先清除添加过的数据再弹窗
				opt_all.edit_dialog.find('.modal-title').html(
						"<i class='icon icon-plus'></i> 新增合同");
				_reset();
				opt_all.edit_dialog.modal({
					show : true,
					backdrop : "static" // 背景遮挡
					,
					moveable : true
				}).on('shown.zui.modal', function() {
					// _reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				});
			}
		}

		// 加载履约评价页面上数据，并调出页面显示
		function LoadEvaluationData(id, onlyread) {
			var url = opt_all.url_edit_evaluate
					+ "?contEvaluateFormBean.contEvaluateBean.id=" + id;

			// 动态加载页面数据
			common_ajax(null, url, function(response) {
				// 获取到数据，显示在界面上
				for ( var key in response.info[0]) {
					if ($("#" + key + "_evaluate")[0]) {
						$("#" + key + "_evaluate").val(response.info[0][key]);
					}
				}
				initStars();
				var title = "<i class='icon icon-edit'></i>编辑履约评价";
				opt_all.evaluation_dialog.find('.modal-title').html(title);

				// /////////////////////////////////////////
				opt_all.evaluation_dialog.modal({
					show : true,
					backdrop : "static" // 背景遮挡
					,
					moveable : true
				}).on('shown.zui.modal', function() {
					// _reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
				});
			});
		}

		// 星级评价初始化
		function initStars() {
			var inp1 = $("#MATERIALARRIVAL_evaluate");
			inp1.rating('refresh', {
				stars : 5,
				min : 0,
				max : 5,
				step : 1,
				showClear : false
			});
			var inp2 = $("#PRODUCTQUALITY_evaluate");
			inp2.rating('refresh', {
				stars : 5,
				min : 0,
				max : 5,
				step : 1,
				showClear : false
			});
			var inp3 = $("#FIELDSERVICE_evaluate");
			inp3.rating('refresh', {
				stars : 5,
				min : 0,
				max : 5,
				step : 1,
				showClear : false
			});
			var inp4 = $("#MATERIALOPERATION_evaluate");
			inp4.rating('refresh', {
				stars : 5,
				min : 0,
				max : 5,
				step : 1,
				showClear : false
			});
			var inp5 = $("#WARRANTYSITUATION_evaluate");
			inp5.rating('refresh', {
				stars : 5,
				min : 0,
				max : 5,
				step : 1,
				showClear : false
			});
			var inp6 = $("#EVALUATION_evaluate");
			inp6.rating('refresh', {
				stars : 5,
				min : 0,
				max : 5,
				step : 1,
				showClear : false
			});
		}

		// ****绑定事件
		// 绑定添加或修改事件
		function event_add() {
			_edit(0);
		}

		// 绑定刷新事件
		function event_ref() {
			_refresh();
		}

		// ****绑定事件******end
		// 刷新
		function _refresh() {
			opt_all.query_table.bootstrapTable('refresh');
		}
		// 重置主合同
		function _reset() {
			opt_all.info_form.data('bootstrapValidator').resetForm(true);
		}
		// 重置履约评价表单
		function _reset_evaluation() {
			opt_all.evaluation_form.data('bootstrapValidator').resetForm(true);
		}
		// 公开函数
		this.reset = function() {
			_reset();
		}
		////////////////////////////////////////////////////////////////////////////////
		//加载所有外键表到下拉框，无
		function Load_EditSelectData(callback) {
			//所有编辑页面下拉框加载
			callback();
		}
	};
})(jQuery);

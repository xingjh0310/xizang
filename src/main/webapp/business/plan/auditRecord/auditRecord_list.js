var opt={};
var arrival=new $.System_arrival();

$(function(){
	
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	arrival.InitData(opt);
	//初始化新增、编辑和删除
	arrival.InitAddEditDel();
	
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	//查询所有的物料类型--select
	queryMaterielType();
	//查询所有物料名称--select
	queryMaterielBase();
	//查询所有供货计划--select
	querySupplyPlan();
});


$("#font").bind("DOMNodeInserted",function(e){
	setTimeout(function(){
		arrival.flash();
	},500);
});

//调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo").bootstrapTable("resetView",{"height":comm_getHeight()-80});      
} 

//查询所有的物料类型--select
function queryMaterielType(){
	var html="<option value=''>请选择</option>";
	$("#material_type").empty();
	var url=basePath+"materielType/materielType!zTree.action";
	common_ajax(null, url, function(response){
		var materielType=response.zTree;
		for(i=0;i<materielType.length;i++){
			html+="<option value='"+materielType[i].id+"'>"+materielType[i].name+"</option>";
		}
		
		$("#material_type").append(html);
	});
}

//查询所有物料名称--select
function queryMaterielBase(){
	var html="<option value=''>请选择</option>";
	var data={
		  "materielBaseFormBean.pageBean.limit"		: g_treelist_size   // 页面大小
		 ,"materielBaseFormBean.pageBean.offset"	: 0 				// 当前记录偏移条数
		 ,"materielBaseFormBean.pageBean.sort"		: 'ID'  			// 排序列名
		 ,"materielBaseFormBean.pageBean.sortOrder"	: 'DESC'			// 排位命令（desc，asc）
	}
	$("#material_name").empty();
	var url=basePath+"materielBase/materielBase!all_list.action";
	common_ajax(data, url, function(response){
		var materielName=response.rows;
		for(i=0;i<materielName.length;i++){
			html+="<option value='"+materielName[i].MATERIELCODE+"'>"+materielName[i].MATERIELNAME+"</option>";
		}
		$("#material_name").append(html);
	});
}

//查询所有供货计划--select
function querySupplyPlan(){
	var html="<option value=''>请选择</option>";
	var html_supplyInfo="";
	var url=basePath+"plan/supply!queryAllSupplyInfo.action";
	var data={
		  "fSupplyFormBean.pageBean.limit"		: g_treelist_size   // 页面大小
		 ,"fSupplyFormBean.pageBean.offset"		: 0 				// 当前记录偏移条数
		 ,"fSupplyFormBean.pageBean.sort"		: 'ID'  			// 排序列名
		 ,"fSupplyFormBean.pageBean.sortOrder"	: 'DESC'			// 排位命令（desc，asc）
		 ,"fSupplyFormBean.mSupply.engineerCode"	: sessionStorage.getItem("engineeringNm")
	}
	common_ajax(data, url, function(response){
		$("#SUPPLIERPLANCODE").empty();
		$("#supplierInfo_div").empty();
		var supplyPlan=response.rows;
		for(i=0;i<supplyPlan.length;i++){
			html+="<option value='"+supplyPlan[i].SUPPLIERPLANCODE+"'>"+supplyPlan[i].SUPPLIERPLANCODE+"</option>";
			html_supplyInfo+="<textarea id='supply_"+supplyPlan[i].SUPPLIERPLANCODE+"'>"+JSON.stringify(supplyPlan[i])+"</textarea>";
		}
		$("#SUPPLIERPLANCODE").append(html);
		$("#supplierInfo_div").append(html_supplyInfo);
	})
}

////////////////////////////格式化BootStrap表中的格式
//列格式化-选择
function FMT_Check(value,row,index) {
  if (index<0) {
      return {
          disabled: true,
          checked: false
      };
  }
  return value;
}
//列格式化-序号
function FMT_Num(value,row,index) {
	return index+1;
//	 var pageNumber = $("#tbinfo").bootstrapTable('getOptions').pageNumber;
//	 var pageSize =   $("#tbinfo").bootstrapTable('getOptions').pageSize;
//	  return (pageNumber-1) * pageSize+index+1;
}
//审核状态
function FMT_AUDITSTATE(value,row){
	var html="";
	if(value==1){
		html="<span style='color:green'>通过</span>";
	}else{
		html="<span style='color:red'>拒绝</span>";
	}
	return html;
}
//操作
function FMT_Oper(value,row) {
    var html="";
    if(row.AUDITSTATE==1 || row.AUDITSTATE==2){
    	html+="<a href='#' title='已审核'>";
    	html+="<button class='btn btn-xs btn-primary'  disabled='disabled'>";
    }else{
    	html+="<a href='#' onclick='javascript:arrival.edit("+row.ID+")'>";
    	html+="<button class='btn btn-xs btn-primary'>";
    }
	html+="<div class='visible-md visible-lg'><i class='icon-star-empty'></i>&nbsp;审核</div>" +
	"<div class='visible-xs visible-sm'><i class='icon-star-empty'></i></div>" +
	"</button></a>";
	/*"&nbsp;&nbsp;" +
	"<a href='#' onclick='javascript:arrival.edit("+row.ID+")'>" +
	"<button class='btn btn-xs btn-danger btn_del_color'>" +
	"<div class='visible-md visible-lg'><i class='icon icon icon-times'></i>&nbsp;审核拒绝</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon icon-times'></i></div>" +
	"</button></a>" ; */
   return html;
}
//附件
function FMT_Accessory(value,row){
	var html="";
	var accessory=value==null?0:value;
	html="<a href='#' id='fileNub_id"+row.ID+"' onclick='javascript:arrival.upload_file("+row.ID+")'>"+accessory+"</a>";
	return html;
}
//物料编码
function FMT_MATERIELCODE(value,row){
	var html="<div class='text_flow_css' title='"+value+"'>"+value+"</div>";
	return html;
}
//交货地点
function FMT_DELIVERYPLACE(value,row){
	var html="<div class='text_flow_css' title='"+value+"'>"+value+"</div>";
	return html;
}
////////////////////////////格式化BootStrap表中的格式
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
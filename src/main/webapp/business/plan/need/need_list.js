var opt={};
var need=new $.System_need();

$(function(){
	
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	need.InitData(opt);
	//初始化新增、编辑和删除
	need.InitAddEditDel();
	
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	//查询所有的物料类型--select
	//queryMaterielType();
	//查询所有物料名称--select
	//queryMaterielBase();
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
	$("#MATERIELCODE").empty();
	var url=basePath+"materielBase/materielBase!all_list.action";
	common_ajax(data, url, function(response){
		var materielName=response.rows;
		for(i=0;i<materielName.length;i++){
			html+="<option value='"+materielName[i].MATERIELCODE+"'>"+materielName[i].MATERIELNAME+"</option>";
		}
		$("#material_name").append(html);
		$("#MATERIELCODE").append(html);
	});
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
}
//操作
function FMT_Oper(value,row) {
    var html="";
    html="<a href='#' onclick='javascript:need.edit("+row.ID+")'>" +
	"<button class='btn btn-xs btn-primary'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
	"</button></a>" +
	"&nbsp;&nbsp;" +
	"<a href='#' onclick='javascript:need.del("+row.ID+")'>" +
	"<button class='btn btn-xs btn-danger btn_del_color'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-trash'></i>&nbsp;删除</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-trash'></i></div>" +
	"</button></a>" ; 
   return html;
}
//附件
function FMT_Accessory(value,row){
	var html="";
	var accessory=value==null?0:value;
	html="<a href='#' id='fileNub_id"+row.ID+"' onclick='javascript:need.upload_file("+row.ID+")'>"+accessory+"</a>";
	return html;
}

//物资表格删除操作
function removeSelf(id,event){
	
	$(event).parent().parent().remove();//移除当前行
    var removeIds = $("#removeIds").val();
    $("#removeIds").val(removeIds + "," + id);
    var numTds = $(".num-td");
	for(var i=0;i<numTds.length;i++){
		numTds.eq(i).html(i+1);
	}
   
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
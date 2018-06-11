var opt={};
var procurement=new $.System_procurement();

$(function(){
	
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	procurement.InitData(opt);
	//初始化新增、编辑和删除
	procurement.InitAddEditDel();
	
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
});

//调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo").bootstrapTable("resetView",{"height":comm_getHeight()-80});      
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
   //return index+1;
	 var pageNumber = $("#tbinfo").bootstrapTable('getOptions').pageNumber;
	 var pageSize =   $("#tbinfo").bootstrapTable('getOptions').pageSize;
	  return (pageNumber-1) * pageSize+index+1;
}
//操作
function FMT_Oper(value,row) {
    var html="";
    html="<a href='#' onclick='javascript:procurement.edit("+row.id+")'>" +
	"<button class='btn btn-xs btn-primary'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
	"</button></a>" +
	"&nbsp;&nbsp;" +
	"<a href='#' onclick='javascript:procurement.del("+row.id+")'>" +
	"<button class='btn btn-xs btn-danger'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-times'></i>&nbsp;删除</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-times'></i></div>" +
	"</button></a>" ; 
   return html;
}
//附件
function FMT_Accessory(value,row){
	var html="";
	var accessory=value==null?0:value;
	html="<a href='#' onclick=''>"+accessory+"</a>";
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
	$("#tbinfo tbody tr[data-uniqueid="+rows.id+"]").addClass("success");
}
//不选中时颜色恢复
function onUncheck(rows){
	$("#tbinfo tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
}
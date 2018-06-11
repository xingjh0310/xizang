var opt={};
var deliverGoods=new $.System_deliverGoods();

$(function(){
	
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	deliverGoods.InitData(opt);
	//初始化新增、编辑和删除
	deliverGoods.InitAddEditDel();
	
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	//物料名称
	//查询所有的物料类型--select
	queryMaterielType();
	
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
	data =JSON.stringify(row)
    var html="";
    html="<a href='#' onclick='javascript:deliverGoods.deliver("+data+")'>" +
	"<button class='btn btn-xs btn-primary'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-share'></i>&nbsp;发货</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-share'></i></div>" +
	"</button></a>" ;
	/*"&nbsp;&nbsp;" +
	"<a href='#' onclick='javascript:material.del("+row.id+")'>" +
	"<button class='btn btn-xs btn-danger'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-times'></i>&nbsp;删除</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-times'></i></div>" +
	"</button></a>" ;*/ 
   return html;
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
//供应商名称
function FMT_Supplier(value,row){
	var html="";
	html += "<div class='autocut' title="+value+">"+value+"</div>";
	return html;
}
//发货通知状态
function FMT_State(value,row){
	var html="";
	if(value==0){html="<span style='color:#008000'>未发货</span>"}
	if(value==1){html="<span style='color:#66FF66'>已发货</span>"}
	return html;
}
//删除table 行
function removeSelf(id,opp){
	
	$(opp).parent().parent().remove();//移除当前行
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
	$("#tbinfo tbody tr[data-uniqueid="+rows.id+"]").addClass("success");
}
//不选中时颜色恢复
function onUncheck(rows){
	$("#tbinfo tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
}
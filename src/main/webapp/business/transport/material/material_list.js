var opt={};
var material=new $.System_material();

$(function(){
	
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	material.InitData(opt);
	//初始化新增、编辑和删除
	material.InitAddEditDel();
	
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	//物资类型
	queryMaterielType();
	
	currentTime();
	
	
});
$("#font").bind("DOMNodeInserted",function(e){
	
    //console.log($(e.target).html());
    setTimeout(function(){
    var nm  =sessionStorage.getItem("engineeringNm")
    $("#ENGINEERCODE").val(nm)
    $("#ENGINEERCODE_check").val(nm)
    material.flash(nm);
    
    },500)
    
})
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
	data=JSON.stringify(row)
    var html="";
	
	if(row.RECEIVESTATE==1 && row.ACCEPTANCESTATE==null){
		 html="<a href='#' onclick='javascript:material.check("+data+")'>" +
			"<button class='btn btn-xs btn-primary'>" +
			"<div class='visible-md visible-lg'><i class='icon icon-check-board'></i>&nbsp;验收</div>" +
			"<div class='visible-xs visible-sm'><i class='icon icon-check-board'></i></div>" +
			"</button></a>" ; 
	}
	if(row.RECEIVESTATE==null){
		 html="<a href='#' onclick='javascript:material.transfer("+data+")'>" +
			"<button class='btn btn-xs btn-primary'>" +
			"<div class='visible-md visible-lg'><i class='icon icon-cube'></i>&nbsp;到货</div>" +
			"<div class='visible-xs visible-sm'><i class='icon icon-cube'></i></div>" +
			"</button></a>"; 
	}
   
   return html;
}
//标题
function FMT_Title(value,row){
	var html="<div class='text_flow_css' title='"+value+"'>"+value+"</div>";
	return html;
}
//物资排产计划
function FMT_Plan(value,row){
	var html="";
	var accessory=value==null?0:value;
	html="<a href='#' onclick=''>"+accessory+"</a>";
	return html;
}
//物资状态
function FMT_State_Material(value,row){
	var html="";
	var accessory=value==null?0:value;
	html="<a href='#' onclick=''>"+accessory+"</a>";
	return html;
}
//交货状态
function FMT_receiveState(value,row){
	var html =""
    if (row.RECEIVESTATE==1){html="已交货"}
    else{
    	{html="未交货"}
    }
   return html;
}
//验收状态
function FMT_State_Check(value,row){
	var html="";
	 if (value==null){html="未验收"}
	 if (value==1){html="正常收货"}
	 if (value==2){html="换货处理"}
	 if (value==3){html="取消验收"}
	 if (value==4){html="补发ECP"}
	return html;
}
////////////////////////////格式化BootStrap表中的格式
//选中多行改变表格背景色
function onCheckAll(rows){
	for(var i=0;i<rows.length;i++){
		commRowStyle(i);
	}
}
//
function queryMaterielType(){
	var html="<option value=''>请选择物资类型</option>";
	$("#material_name").empty();
	var url=basePath+"materielType/materielType!zTree.action";
	common_ajax(null, url, function(response){
		var materielType=response.zTree;
		for(i=0;i<materielType.length;i++){
			html+="<option value='"+materielType[i].id+"'>"+materielType[i].name+"</option>";
		}
		
		$("#material_name").append(html);
	});
}
//当前时间
function currentTime(){
	
	 var nowDate= new Date();           
	 $("#receiveConfirmTime").val(nowDate.format("yyyy-MM-dd"));
	 $("#firmTime").val(nowDate.format("yyyy-MM-dd"));
	
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
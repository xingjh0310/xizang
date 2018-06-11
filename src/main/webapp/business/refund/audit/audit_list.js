var opt={};
var material=new $.System_material();

$(function(){
	
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	material.InitData(opt);
	//初始化新增、编辑和删除
	material.InitAddEditDel();
	 getSupplier();
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
$("#font").bind("DOMNodeInserted",function(e){
	$("#engineer").val($(e.target).html())
    setTimeout(function(){
    var nm  =sessionStorage.getItem("engineeringNm")
    
    //material.flash(nm);
    
    },200)
    
})

function removeSelf(id,event){
	var obj = event.srcElement||event.target;
    var tr  = obj.parentNode.parentNode.parentNode.parentNode;
    if(tr.localName == 'td'){
    	tr  = tr.parentNode;
    }
    tr.remove();
    
    var removeIds = $("#removeIds").val();
    $("#removeIds").val(removeIds + "," + id);
    var numTds = $(".num-td");
	for(var i=0;i<numTds.length;i++){
		numTds.eq(i).html(i+1);
	}
}
//加载供货厂商
function getSupplier(){
	$("#supplier").empty();
	$("#supplierCode").empty();
	var html="<option value=''>请选择供应商</option>";
	var url = basePath+"contInfo/contInfo!queryAllsupply.action"
	common_ajax(null,url, function(response) {
		var classify=response.supplys;
		
		for(i=0;i<classify.length;i++){
			html+="<option value='"+classify[i].SUPPLIER_CODE+"'>"+classify[i].SUPPLY_FULL_NAME+"</option>";
		}
		$("#supplier").append(html);
		$("#supplierCode").append(html);
		
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
//列格式化-选择
function FMT_State(value,row,index) {
	var html =""
	    if (value==0){html="<span style='color:black'>未审核</span>"}
	    if (value==1){html="<span style='color:green'>已通过</span>"}
	    if (value==-1){html="<span style='color:red'>未通过</span>"}
	   return html;
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
	var data =JSON.stringify(row);
    var html="";
    html="<a href='#' onclick='javascript:material.flag("+data+")'>" +
	"<button class='btn btn-xs btn-primary'>" +
	"<div class='visible-md visible-lg'><i class='icon-star-empty'></i>&nbsp;审核</div>" +
	"<div class='visible-xs visible-sm'><i class='icon-star-empty'></i></div>" +
	"</button></a>"; 
   return html;
}
/*格式化编号*/
function FMT_NUMBER(value,row){
	
	 var html="";
	 if(row.STATE==1){
		 html="<a href='#' onclick='javascript:material.label("+value+")'>"+value+"</a>";
	 }else{
		 html=value;
	 }
	   return html;
}
/*格式化附件*/
function FMT_ENCLOSURE(value,row){
	 var html="";
	    html="<a href='#' onclick='javascript:material.enclosure("+row.ID+")'>"+(value==null?"0":value)+"</a>";
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
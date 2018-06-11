var opt={};
var registration=new $.System_Registration();

$(function(){
	//检查session
	comm_checksession();
	//初始化预约单位的数据
	registration.InitData(opt);
	//初始化新增、编辑和删除
	registration.InitAddEditDel();
	//departClick();
	//加载下拉框
	registration.Load_Chose()
	//绑定审核
	registration.InitFlag();
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	/////////////////////////
	registration.check();
	//当前时间
	window.onload = function () {
	    var date = new Date();
	    document.getElementById("orderTime").value = date.format("yyyy-MM-dd hh:mm:ss");
	    onloadColor();
	    
	}
	
	/////////////////////////////////////////////////
	
	/////////////////////////////////////////////////
})


//加载预约单位
/*function departClick(){
	registration.Load_Chose();
}*/

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo").bootstrapTable("resetView",{"height":comm_getHeight()-55});      
} 

function _reset(){
	registration.reset();
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
//flag状态
function FMT_Flag(value,row) {
    var html="";
    if (row.flag==0){html="未生效"}
    if (row.flag==1){html="生效"}
   return html;
}
// 操作
function FMT_Oper(value,row) {
    var html="";
    html="<a href='#' onclick='javascript:registration.edit("+row.id+")'><i class='icon icon-edit'><i></a>"; 
   return html;
}
////////////////////////////格式化BootStrap表中的格式
//下拉框change()事件
 

var opt={};
var sysicon=new $.System_Sysicon();

$(function(){
	//检查session
	comm_checksession();
	
	comm_chose_init($('#select_flag'));
	
	//初始化BootStrapTable的数据
	sysicon.InitData(opt);
	//初始化新增、编辑和删除
	sysicon.InitAddEditDel();
	//绑定审核
	sysicon.InitFlag();
	
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	
})

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo").bootstrapTable("resetView",{"height":comm_getHeight()-55});      
} 

function _reset(){
	sysicon.reset();
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
    if (row.FLAG==0){html="未生效"}
    if (row.FLAG==1){html="生效"}
   return html;
}	
// 操作
function FMT_Oper(value,row) {
    var html="";
    html="<a href='#' onclick='javascript:sysicon.edit("+row.ID+")'><i class='icon icon-edit'><i></a>"; 
   return html;
}
////////////////////////////格式化BootStrap表中的格式


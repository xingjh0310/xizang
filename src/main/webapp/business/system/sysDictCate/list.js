
var opt={};
var opt_tb={
		rowStyle:rowStyle
}

var sysDictCate=new $.System_Sysdictcate();
$(function(){
	//检查session
	comm_checksession();
    comm_chose_init($('#select_flag'));
	//comm_chose_init($('#select_sysflag'));

	//初始化BootStrapTable的数据
	sysDictCate.Init_TB_Option(opt_tb);//设置表格参数
	sysDictCate.Init_TB_QueryParms(opt);//设置查询及分页参数
	sysDictCate.Init_TB_Data();//加载数据
	
	//初始化新增、编辑和删除
	sysDictCate.InitAddEditDel();
	//绑定审核
	//sysDictCate.InitFlag();
	
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
	sysDictCate.reset();
}

////////////////////////////////////////////////

////////////////////////////格式化BootStrap表中的格式
//列格式化-选择
function FMT_Check(value,row,index) {
    if (row.SYSFLAG == 1) {
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
// 操作
function FMT_Oper(value,row) {
    var html="";
    if (row.SYSFLAG==0){
        html="<a href='#' onclick='javascript:sysDictCate.edit("+row.ID+")'><i class='icon icon-edit'><i></a>"; 
    }
   return html;
}
//自定义行样式
function rowStyle(row,index){
    var classes = ['active', 'success', 'info', 'warning', 'danger'];
    if (row.SYSFLAG == 1) {
        return {
            classes: classes[3]
        };
    }
    return {};
}
////////////////////////////格式化BootStrap表中的格式

//tbinfo_Dict *******************************************************
function _InitData_Dict(ctl,dictcate){
	var option = $("<option>").text("全部数据").val("");
	ctl.empty();
	ctl.append(option);
	var sysDict=new $.System_SysDictQ();
	sysDict.Load_EditSelectData(dictcate,ctl,null);    	
}
//tbinfo_Dict *******************************************************
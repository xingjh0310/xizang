var opt={};
var opt_tb={
		rowStyle:rowStyle
}
var sysDept=new $.System_SysDeptQ();
var sysStaff=new $.System_Sysstaff();
$(function(){
	//检查session
	comm_checksession();

	//单位部门，设置过滤条件，生效的记录
	var opt_TreenmSysDept={
			"formBean.infoBean.flag"	:""
		}
	sysDept.Init_TB_QueryParms(opt_TreenmSysDept);  	//初始化查询参数
	sysDept.Init_TB_Data();							//加载数据	
	
	//字典分类:性别
    _InitData_Dict($("#S_DictnmXingbie"),"xingbie");

	//初始化BootStrapTable的数据
	sysStaff.Init_TB_Option(opt_tb);//设置表格参数
	sysStaff.Init_TB_QueryParms(opt);//设置查询及分页参数
	sysStaff.Init_TB_Data();//加载数据
	
	//初始化新增、编辑和删除
	sysStaff.InitAddEditDel();
	//绑定审核
	//sysStaff.InitFlag();
	
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
	$("#tbinfo_TreenmSysDept").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
} 

function _reset(){
	sysStaff.reset();
}

////////////////////////////////////////////////

//单位部门
function Select_TreenmSysDept(nm){
	$('#S_TreenmSysDept').val(nm);
	sysStaff.refresh();
}
////////////////////////////格式化BootStrap表中的格式
//列格式化-选择
function FMT_Check(value,row,index) {
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
        html+="<a href='#' title='修改' onclick='javascript:sysStaff.edit("+row.ID+")'><i class='icon icon-edit'></i></a>&nbsp"; 
    }
    if(row.ENGINEER_NM!=""){
    	html+="<a href='#' title='查看工程' onclick='javascript:engineer_show(\""+row.ENGINEER_NM+"\")'><i class='icon-window-alt'></i></a>&nbsp"; 
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
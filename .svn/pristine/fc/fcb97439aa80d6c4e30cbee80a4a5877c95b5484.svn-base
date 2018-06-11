
var opt={};
var opt_tb={
		rowStyle:rowStyle
}
var sysStaff=new $.System_SysStaffQ();
var sysAcct=new $.System_Sysacct();
$(function(){
	//检查session
	comm_checksession();
    comm_chose_init($('#select_flag'));
	comm_chose_init($('#select_sysflag'));
    
	//字典分类，设置双击事件
	var opt_tb_sysStaff={
		rowStyle: function rowStyle(row, index) {
			 	if (row.NM==$("#S_ListnmSysStaff").val()){
			 		return { classes: 'warning'};
			 	}
				return {};
		       }
		,onDblClickRow:onDblClickRow_ListnmSysStaff
	}
	//系统人员，设置过滤条件，生效的记录
	var opt_sysStaff={
			"formBean.infoBean.flag"	:1
		}
	sysStaff.Init_TB_Option(opt_tb_sysStaff);   	//初始化BootStrapTable的参数
	sysStaff.Init_TB_QueryParms(opt_sysStaff);  	//初始化查询参数
	sysStaff.Init_TB_Data();							//加载数据	

	
	//字典分类:登录类型
    _InitData_Dict($("#S_DictnmDllx"),"dllx");
	//字典分类:账户类型
    _InitData_Dict($("#S_DictnmZhlx"),"zhlx");

	//初始化BootStrapTable的数据
	sysAcct.Init_TB_Option(opt_tb);//设置表格参数
	sysAcct.Init_TB_QueryParms(opt);//设置查询及分页参数
	sysAcct.Init_TB_Data();//加载数据
	
	//初始化新增、编辑和删除
	sysAcct.InitAddEditDel();
	//绑定审核
	sysAcct.InitFlag();
	
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
	$("#tbinfo_ListnmSysStaff").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
} 

function _reset(){
	sysAcct.reset();
}

////////////////////////////////////////////////
//系统人员
//列格式化-序号
function FMT_Num_ListnmSysStaff(value,row,index) {
	 var pageNumber = $("#tbinfo_ListnmSysStaff").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#tbinfo_ListnmSysStaff").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
}
// 列格式化-操作
function FMT_Oper_ListnmSysStaff(value,row) {
    var html="";
    html="<a href='#' title='在数据行上双击鼠标左键，进行字典数据过滤。' onclick='javascript:Select_ListnmSysStaff(\""+row.NM+"\")'><i class='icon icon-filter'><i></a>"; 
   return html;
}
//格式化双击事件
function onDblClickRow_ListnmSysStaff(row){
	if (row) {
		Select_ListnmSysStaff(row.NM);
	}
}	
//选择一条过滤数据
function Select_ListnmSysStaff(nm){
	$("#S_ListnmSysStaff").val(nm);
	sysStaff.refresh();
	//设置查询及分页参数
	var opt_tb_queryParms={"formBean.infoBean.listnmSysStaff":$("#S_ListnmSysStaff").val()};
	sysAcct.Init_TB_QueryParms(opt_tb_queryParms);
	sysAcct.refresh();
}

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
//flag状态
function FMT_Flag(value,row) {
    var html="";
    if (row.FLAG==0){html="未生效"}
    if (row.FLAG==1){html="生效"}
   return html;
}	
function FMT_SysFlag(value,row,index) {
    var html="";
    if (row.SYSFLAG==0){html=""}
    if (row.SYSFLAG==1){html="内置"}
   return html;
}
// 操作
function FMT_Oper(value,row) {
    var html="";
    if (row.SYSFLAG==0){
        html="<a href='#' onclick='javascript:sysAcct.edit("+row.ID+")'><i class='icon icon-edit'><i></a>"; 
    }
   return html;
}

function FMT_Rest(value,row){
	var html="";
    if (row.SYSFLAG==0){
       html+="<a href='#' onclick='javascript:sysAcct.resetPwd("+row.ID+")'><i class='icon icon-file-o'></i></a>";
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
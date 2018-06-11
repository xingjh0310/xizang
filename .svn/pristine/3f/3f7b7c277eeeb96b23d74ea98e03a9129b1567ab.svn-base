
var opt={};
var opt_tb={
		rowStyle:rowStyle
}
var sysDictCate=new $.System_SysDictCateQ();

var sysDict=new $.System_Sysdict();
$(function(){
	//检查session
	comm_checksession();
    
	//字典分类，设置双击事件
	var opt_tb_sysDictCate={
		rowStyle: function rowStyle(row, index) {
			if (row.NM==$("#S_ListnmSysDictCate").val()){
				return { classes: 'warning'};
			}
				return {};
	    }
		,onDblClickRow:onDblClickRow_ListnmSysDictCate
	}
	//字典分类，设置过滤条件，生效的记录
	var opt_sysDictCate={
		"formBean.infoBean.flag"	:""
		,"formBean.infoBean.sysflag":""
	}
	sysDictCate.Init_TB_Option(opt_tb_sysDictCate);   	//初始化BootStrapTable的参数
	sysDictCate.Init_TB_QueryParms(opt_sysDictCate);  	//初始化查询参数
	sysDictCate.Init_TB_Data();							//加载数据	

	//初始化BootStrapTable的数据
	sysDict.Init_TB_Option(opt_tb);//设置表格参数
	sysDict.Init_TB_QueryParms(opt);//设置查询及分页参数
	sysDict.Init_TB_Data();//加载数据
	
	//初始化新增、编辑和删除
	sysDict.InitAddEditDel();
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
	$("#tbinfo_ListnmSysDictCate").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
} 

function _reset(){
	sysDict.reset();
}

////////////////////////////////////////////////
//字典分类
//列格式化-序号
function FMT_Num_ListnmSysDictCate(value,row,index) {
	 var pageNumber = $("#tbinfo_ListnmSysDictCate").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#tbinfo_ListnmSysDictCate").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
}
// 列格式化-操作
function FMT_Oper_ListnmSysDictCate(value,row,index) {
   var html="";
   html="<a href='#' title='在数据行上双击鼠标左键，进行字典数据过滤。' onclick='javascript:Select_ListnmSysDictCate(\""+row.NM+"\")'><i class='icon icon-filter'><i></a>"; 
   return html;
}
//格式化双击事件
function onDblClickRow_ListnmSysDictCate(row){
	if (row) {
		Select_ListnmSysDictCate(row.NM);
	}
}	
//选择一条过滤数据
function Select_ListnmSysDictCate(nm){
	$("#S_ListnmSysDictCate").val(nm);
	sysDictCate.refresh();
	//设置查询及分页参数
	var opt_tb_queryParms={"formBean.infoBean.listnmSysDictCate":$("#S_ListnmSysDictCate").val()};
	sysDict.Init_TB_QueryParms(opt_tb_queryParms);
	sysDict.refresh();
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
// 操作
function FMT_Oper(value,row) {
    var html="";
    if (row.SYSFLAG==0){
        html="<a href='#' onclick='javascript:sysDict.edit("+row.ID+")'><i class='icon icon-edit'><i></a>"; 
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
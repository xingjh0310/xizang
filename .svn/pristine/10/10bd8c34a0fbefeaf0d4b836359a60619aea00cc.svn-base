
var sysStaff=new $.System_SysStaffQ();

var sysRela=new $.System_SysStaff_SysRole();

$(function(){
	//检查session
	comm_checksession();
	
	//字典分类，设置双击事件
	var opt_tb_sysStaff={
			rowStyle: function rowStyle(row, index) {
			 	if (row.NM==$("#S_SysStaff").val()){
			 		return { classes: 'warning'};
			 	}
				return {};
		     }
		,onClickRow:onDblClickRow_sysStaff
	}

	//初始化BootStrapTable的数据
	sysStaff.Init_TB_Option(opt_tb_sysStaff);//设置表格参数
	sysStaff.Init_TB_QueryParms();//设置查询及分页参数
	sysStaff.Init_TB_Data();//加载数据    
	
	var opt_tb_sysRela={
			rowStyle: function rowStyle(row, index) {
			 	if (row.RELAID>0){
			 		return { classes: 'success'};
			 	}
				return {};
	       }	
	}
	//加载关系数据
	sysRela.Init_TB_Option(opt_tb_sysRela);
	sysRela.Init_TB_QueryParms();//设置查询及分页参数
	sysRela.Init_TB_Data();

	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	
	$("#btn_sq_all").bind("click",event_sq_all);
	$("#btn_qx_all").bind("click",event_qx_all);
	$("#btn_sq_xz").bind("click",event_sq_xz);
	$("#btn_qx_xz").bind("click",event_qx_xz);
	
})

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo_ListnmSysStaff").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
	$("#tbinfo_ListnmSysRole").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
		
} 

function onDblClickRow_sysStaff(row){
	if (row){
		SysStaff_filter(row.NM,row.CODE,row.NAME);
	}
}


//授权
function event_sq_all(){
	var code="";
	var sqtype="sq";
	rela_sq(code,sqtype);
}
//取消
function event_qx_all(){
	var code="";
	var sqtype="qx";
	rela_sq(code,sqtype);
}
//授权
function event_sq_xz(){
	var code=g_select_and_tip($("#tbinfo_ListnmSysRole"),"CODE");
	if (code.length==0) return;
	
	var sqtype="sq";
	rela_sq(code,sqtype);
}
//取消
function event_qx_xz(){
	var code=g_select_and_tip($("#tbinfo_ListnmSysRole"),"CODE");
	if (code.length==0) return;
	
	var sqtype="qx";
	rela_sq(code,sqtype);
}


//授权操作
function rela_sq(code,sqtype){
	
	var nm=$("#S_SysStaff").val();
	
	if (!(nm.length>0)){
	    var msg = new $.zui.Messager("消息提示：请先选中人员，再进行授权！", {placement: "center",type:"danger"});
	    msg.show();	
		return;
	}
	
	var opt={
			 "formBean.infoBean.taName":"sys_staff"
			,"formBean.infoBean.tbName":"sys_role"
			,"formBean.infoBean.taNm":nm
			,"formBean.infoBean.tbNm":code       //先传code，在后台处理
			,"formBean.parmBean.parm1":sqtype    //"sq" 授权 or "qx"  取消			
		};
		sysRela.Init_TB_QueryParms(opt);
		sysRela.rela_sq(function(response){
			SysStaff_filter(nm,"","");
		    var msg = new $.zui.Messager("消息提示：操作成功！", {placement: "center",type:"primary"});
		    msg.show();	
		});

}

//根据角色过滤权限
function SysStaff_filter(nm,code,name){
	$("#S_SysStaff").val(nm);
	if (code.length>0) {
		$("#S_SysStaff_Name").val("("+code+")"+name);
	}
	
	sysStaff.refresh();
	
	var opt={
		 "formBean.infoBean.taNm":nm
		,"formBean.infoBean.tbNm":""
		,"formBean.parmBean.parm1"	:"list"		//tbName表B是列表，  list-列表  tree-树状
	};
	sysRela.Init_TB_QueryParms(opt);
	sysRela.refresh();
}

//////////////////////////////////////////////////////////////////////////////////////
function FMT_Num_ListnmSysStaff(value,row,index){
	 var pageNumber = $("#tbinfo_ListnmSysStaff").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#tbinfo_ListnmSysStaff").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
}

//操作
function FMT_Oper_ListnmSysStaff(value,row) {
    var html="";
    if (row.CODE!="supadmin"){
    	var parm="\""+row.NM+"\",\""+row.CODE+"\",\""+row.NAME+"\"";
    	html="<a href='#' title='过滤信息，或在数据行上双击鼠标左键。' onclick='javascript:SysStaff_filter("+parm+")'><i class='icon icon-filter'><i></a>"; 
    }
    return html;
}

function FMT_Num_ListnmSysRole(value,row,index){
	 var pageNumber = $("#tbinfo_ListnmSysRole").bootstrapTable('getOptions').pageNumber;
 	 var pageSize =   $("#tbinfo_ListnmSysRole").bootstrapTable('getOptions').pageSize;
 	  return (pageNumber-1) * pageSize+index+1;
}

function FMT_Oper_ListnmSysRole(value,row) {
    var html="";
    if (row.RELAID>0){
    	html="已授权"; 
    }
    return html;
}

//操作（点击审核按钮）
function FMT_Oper_Button(value,row,index){
	var html="";
	var parms="\""+row.TA_NM+"\",\""+row.CODE+"\"";
    html="<a href='#' title='更改标记' onclick='javascript:_flag("+parms+")'><i class='icon icon-filter'><i></a>";
    return html;
}

//点击审核按钮
function _flag(nm,code){
	if(nm=="undefined" || nm==""){
		alert("请选择左侧列表！！！")
		return;
	}
	var opt={
		"formBean.infoBean.taName":"sys_staff"
		,"formBean.infoBean.tbName":"sys_role"
		,"formBean.infoBean.taNm":nm
		,"formBean.infoBean.tbNm":code       //先传code，在后台处理
		,"formBean.parmBean.parm1":"sh"    //"sq" 授权 or "qx"  取消		
	};
	common_ajax(opt,basePath+"system/sysrela!relasq.action", function(response) {
		sysRela.refresh();
	});
}


var sysRole=new $.System_SysRoleQ();

var sysMenu=new $.System_SysMenuQ();

var sysRela=new $.System_SysRole_SysMenu();

$(function(){
	//检查session
	comm_checksession();
	
	//字典分类，设置双击事件
	var opt_tb_sysRole={
		rowStyle: function rowStyle(row, index) {
			 	if (row.SYSFLAG==1){
			 		return { classes: 'danger'};
			 	}
				return {};
		       }
		,onDblClickRow:onDblClickRow_sysRole
	}

	//初始化BootStrapTable的数据
	sysRole.Init_TB_Option(opt_tb_sysRole);//设置表格参数
	sysRole.Init_TB_QueryParms();//设置查询及分页参数
	sysRole.Init_TB_Data();//加载数据    
	
	//加载下拉框数据
	sysMenu.LoadRootData($("#select_root_SysMenu"));
	//加载关系数据
	sysRela.Init_TB_Data();

	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	
	$("#btn_sq_all").bind("click",event_sq_all);
	$("#btn_qx_all").bind("click",event_qx_all);
	$("#select_root_SysMenu").bind("change",ChangeRoot);
	
})

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo_ListnmSysRole").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
		
} 

function onDblClickRow_sysRole(row){
	SysRole_filter(row.NM,row.CODE,row.NAME);
}

//下拉框变更事件
function ChangeRoot(){
	
	var rolenm=$("#S_SysRole").val();
	
	if (!(rolenm.length>0)){
	    var msg = new $.zui.Messager("消息提示：请先选中角色，再进行查看！", {placement: "center",type:"danger"});
	    msg.show();	
		return;
	}
	
	SysRole_filter(rolenm,"","");
} 

//全部授权
function event_sq_all(){
	var menucode="";
	var sqtype="qbsq";
	rela_sq(menucode,sqtype);
}
//取消
function event_qx_all(){
	var menucode="";
	var sqtype="qx";
	rela_sq(menucode,sqtype);
}
//授权操作
function rela_sq(menucode,sqtype){
	var rolenm=$("#roleNm").val();
	if (!(rolenm.length>0)){
	    var msg = new $.zui.Messager("消息提示：请先选中角色，再进行授权！", {placement: "center",type:"danger"});
	    msg.show();	
		return;
	}
	
	var opt={
			 "formBean.infoBean.taName":"sys_role"
			,"formBean.infoBean.tbName":"sys_menu"
			,"formBean.infoBean.taNm":rolenm
			,"formBean.infoBean.tbNm":menucode+"%"   //先传menucode，在后台处理
			,"formBean.parmBean.parm1":sqtype    //"sq" 授权 or "qx"  取消			
		};
		sysRela.Init_TB_QueryParms(opt);
		sysRela.rela_sq(function(response){
			SysRole_filter(rolenm,"","");
		    var msg = new $.zui.Messager("消息提示：操作成功！", {placement: "center",type:"primary"});
		    msg.show();	
		});

}

//根据角色过滤权限
function SysRole_filter(nm,code,name){
	if (code=="supadmins") return;
	$("#S_SysRole").val(nm);
	if (code.length>0) {
		$("#S_SysRole_Name").val("("+code+")"+name);
	}
	
	var opt={
		 "formBean.infoBean.taNm":nm
		,"formBean.infoBean.tbNm":$("#select_root_SysMenu").val()+"%"
		,"formBean.parmBean.parm1"	:"tree"		//tbName表B是列表，  list-列表  tree-树状
	};
	sysRela.Init_TB_QueryParms(opt);
	sysRela.Init_TB_Data();
}

//////////////////////////////////////////////////////////////////////////////////////
function FMT_Num_SysRole(value,row,index){
	 var pageNumber = $("#tbinfo_ListnmSysRole").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#tbinfo_ListnmSysRole").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
}

//操作
function FMT_Oper_SysRole(value,row) {
    var html="";
    //if (row.sysflag==0){
    if (row.CODE!="supadmins"){
    	var parm="\""+row.NM+"\",\""+row.CODE+"\",\""+row.NAME+"\"";
    	html="<a href='#' title='过滤信息，或在数据行上双击鼠标左键。' onclick='javascript:SysRole_filter("+parm+")'><i class='icon icon-filter'><i></a>"; 
    }
    return html;
}
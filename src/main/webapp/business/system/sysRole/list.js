var opt={};
var opt_tb={
	
}
var sysRole=new $.System_Sysrole();
$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	sysRole.Init_TB_Option(opt_tb);//设置表格参数
	sysRole.Init_TB_QueryParms(opt);//设置查询及分页参数
	sysRole.Init_TB_Data();//加载数据
	
	//初始化新增、编辑和删除
	sysRole.InitAddEditDel();
	
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
	sysRole._reset_();
}

function FMT_staffName(value,row,index){
	var html="";
	var value=value==null?"":value;
	html += "<div class='autocut' title="+value+">"+value+"</div>";
	return html;
}

////////////////////////////////////////////////

////////////////////////////格式化BootStrap表中的格式
//列格式化-选择
function FMT_Check(value,row,index) {
	if (index<0) {
        return {
            disabled: true,
            checked: false
        };
    }
	if(row.SYSFLAG>0){
		return {
            disabled: true,
            checked: false
        };
	}
    return value;
}

function FMT_Check_(value,row,index) {
	if (index<0) {
        return {
            disabled: true,
            checked: false
        };
    }
	if(row.SYSFLAG>0){
		return {
            disabled: true,
            checked: false
        };
	}
	array_.push(row.NM);
    return value;
}
//列格式化-序号
function FMT_Num(value,row,index) {
	 var pageNumber = $("#tbinfo").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#tbinfo").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
}
// 操作
function FMT_Oper(value,row) {
    var html="";
    if(row.SYSFLAG!="1"){
    	html="<a href='#' onclick='javascript:sysRole.edit("+row.ID+")'><i class='icon icon-edit'><i></a>"; 
    }
   return html;
}

//保存按钮
function btn_role_save(){
	$('#roleNm_').val('');
	var str="";
	var staffNm=$('#staffNm').val(); 
	$("#tbinfo tbody tr input").each(function(i){
		if($(this).prop('checked')==true){
			str+=array_[i]+",";
		}
	});
	if(str!=""){
		str=(str.substring(str.length-1)==',')?str.substring(0,str.length-1):str;
		$('#roleNm_').val(str)
	}
	var RoleNm=$('#roleNm_').val();
	var url=basePath+"system/sysrela!relasq.action";
	var sqtype=RoleNm==""?"qx":"sq";
	var obj_={
		"formBean.infoBean.taNm":RoleNm  
		,"formBean.infoBean.taName":"sys_role"
		,"formBean.infoBean.tbNm":staffNm    
		,"formBean.infoBean.tbName":"sys_staff"
		,"formBean.parmBean.parm1":sqtype 
		,"formBean.parmBean.parm2":"sys_staff" 
	}
	common_ajax(obj_,url, function(response) {
		$('#set_role_dialog').modal("hide");
		$('#SysStaffRefAcctTable').bootstrapTable('refresh');
		var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
		msg.show();	
	});	
}
var opt={};
var opt_tb={
	rowStyle:rowStyle
};
var sysDept=new $.System_SysDeptQ();
var SysStaffRefAcct=new $.SysStaffRefAcct();
$(function(){
	comm_checksession();
	var opt_TreenmSysDept={
		"formBean.infoBean.flag"	:""
	}
	sysDept.Init_TB_QueryParms(opt_TreenmSysDept);
	sysDept.Init_TB_Data();
	_InitData_Dict($("#S_DictnmXingbie"),"xingbie");
	_InitData_Dict($("#S_DictnmDuty"),"duty");
	SysStaffRefAcct.Init_TB_Option(opt_tb);
	SysStaffRefAcct.Init_TB_QueryParms(opt);
	SysStaffRefAcct.Init_TB_Data();
	SysStaffRefAcct.InitAddEditDel();
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
});

function _AutoSize(){
	$("#SysStaffRefAcctTable").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
	$("#tbinfo_TreenmSysDept").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
} 

function _reset(){
	SysStaffRefAcct._reset();
}

////////////////////////////////////////////////

//单位部门
function Select_TreenmSysDept(nm){
	$("#treenmSysDept_").val(nm);
	$("#treenmSysDept").val(""); //清空文本框部门名称
	selectDeptInfo(nm);
}

//查询部门内码
function selectDeptInfo(nm){
	var str="";
	var url=basePath+"system/sysdept!selectDeptInfo.action";
	$.post(url,{"nm":nm},function(data){
		var rows=JSON.parse(data).rows;
		for(var i=0;i<rows.length;i++){
			str+=rows[i].NM+",";
		}
		$('#_treenmSysDept').val(str+nm);
		SysStaffRefAcct.refresh();
	});
}

//格式化工程名称
function Fmt_engineerName(value,row,index){
	var html="";
	var value=value==null?"":value;
	html += "<div class='autocut' title="+value+">"+value+"</div>";
	return html;
}

//格式化角色 Fmt_roleName
function Fmt_roleName(value,row,index){
	var html="";
	var value=value==null?"":value;
	html += "<div class='autocut' title="+value+">"+value+"</div>";
	return html;
}

////////////////////////////格式化BootStrap表中的格式
//列格式化-选择
function FMT_Check(value,row,index) {
    return value;
}

//格式化附件
function FMT_FILENAME(value,row,index){
	if(row.FILESIZE==null && value==null){
		return "<a href='javascript:SysStaffRefAcct.upLoadInfo("+JSON.stringify(row.NM)+")'>0</a>";
	}
	return "<a href='javascript:SysStaffRefAcct.upLoadInfo("+JSON.stringify(row.NM)+")'>"+parseInt(row.FILESIZE)+"</a>";
}

//格式化部门名称
function FMT_deptName(value,row,index){
	var html="";
	var value=value==null?"":value;
	html += "<div class='autocut' title="+value+">"+value+"</div>";
}

//验证手机号重复
function event_CheckName(value){
	var nm=$('#nm_staff_').val();
	var url=basePath+"system/sysstaffrefacct!validatePhone.action";
	var obj={
			"mSysStaffRefAcctFormBean.mSysStaffInfoBean.nm":nm,
			"mSysStaffRefAcctFormBean.mSysAcctInfoBean.name":value
	};
	$.ajax({
		data:obj,
		url:url,
		type: "POST",
		dataType:"json",
		success:function(response){
			if(response.message=="1"){
				$("#name_acct_").val("");
				var msg = new $.zui.Messager("消息提示：手机号不能重复！！！", {placement: "center",type:"primary"});
			    msg.show();	
			}
		}
	});	
}

//列格式化-序号
function FMT_Num(value,row,index) {
	 var pageNumber = $("#SysStaffRefAcctTable").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#SysStaffRefAcctTable").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
}
// 操作
function FMT_Oper(value,row) {
    var html="";
    if (row.SYSFLAG==0){
        html+="<a href='#' title='修改' onclick='javascript:SysStaffRefAcct.edit(\""+row.NM+"\")'><i class='icon icon-edit'></i></a>&nbsp"; 
        html+="<a href='#' title='设置工程' onclick='javascript:engineerShow(\""+row.NM+"\",\""+row.STAFFNAME+"\")'><i class='icon icon-cogs'></i></a>&nbsp"; 
        html+="<a href='#' title='重置密码' onclick='javascript:SysStaffRefAcct.resetPwd(\""+row.NM+"\")'><i class='icon icon-undo'></i></a>&nbsp;&nbsp";
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


//点击加载部门树形数据
var jsonArray;
function loadDeptData(){
	loadDeptTree();
	$("#staffRefDept_dialog").modal({
		 show : true
		,backdrop : "static" // 背景遮挡
		,moveable : true
	}).on('shown.zui.modal', function() {
    
	});
}

//加载部门树形数据
function loadDeptTree(){
	jsonArray=new Array();
	var url_dept=basePath+"system/sysdept!getDeptInfo.action";
	common_ajax_noasync(null,url_dept, function(response) {
		var rows=response.rows;
		for(var i=0;i<rows.length;i++){
			json={'id':rows[i].nm,'pId':rows[i].pnm,'name':rows[i].name,type:"dept"};
			jsonArray.push(json);
		}
		initTreeData(jsonArray);
	});
}

//初始化部门树形
function initTreeData(jsonArray){
	var setting={
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		data:{
			key:{
				name: "name"
			},
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		view:{
			expandSpeed: "fast",
			selectedMulti: false,
			showIcon: true,
			showLine: true
		},
		callback:{
			onCheck: zTreeOnCheck
		}
	};
	var zTreeNodes=jsonArray;
	if(zTreeNodes!=null && zTreeNodes!=""){
		zTreeNodes[0].open=true;
	}
	$.fn.zTree.init($("#DeptTree"), setting, zTreeNodes);
	var treeObj = $.fn.zTree.getZTreeObj("DeptTree");
	initSetTreeNode(treeObj);
}

//初始化设置树节点被选中
function initSetTreeNode(treeObj){
	var nodes = treeObj.getNodes();
	if(nodes.length>0){
		var node = treeObj.getNodeByParam("id",$("#treenmSysDept_").val());
		if (node) {
			treeObj.checkNode(node,true,false);
			$("#treenmSysDept_").val(node.id);
			$("#treenmSysDept").val(node.name);
		 }
	}
}

//选择树节点事件
function zTreeOnCheck(event, treeId, treeNode) {
	$("#treenmSysDept_").val("");
	$("#treenmSysDept").val("");
	var treeObj = $.fn.zTree.getZTreeObj("DeptTree");
	var nodes = treeObj.getCheckedNodes(true);
	for(var i=0,len=nodes.length;i<len;i++){
		$("#treenmSysDept_").val(nodes[i].id);
		$("#treenmSysDept").val(nodes[i].name);
	}
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
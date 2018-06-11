var opt={};
var opt_tb={
	rowStyle:rowStyle
};
var SysStaffRefAcct=new $.SysStaffRefAcct();
$(function(){
	comm_checksession();
	SysStaffRefAcct.Init_TB_Option(opt_tb);
	SysStaffRefAcct.Init_TB_QueryParms(opt);
	SysStaffRefAcct.Init_TB_Data();
	SysStaffRefAcct.InitAddEditDel();
	SysStaffRefAcct.initSelectData(); //初始化下拉列表数据
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
});

function _AutoSize(){
	$("#staffInfo_table").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
} 

function _reset(){
	SysStaffRefAcct.reset();
}

//列格式化-选择
function FMT_Check(value,row,index) {
    return value;
}

function FMT_Check_staff(value,row,index){
	array.push(row.NM)
	return value;
}

//格式化部门名称
function FMT_deptName(value,row,index){
	var html="";
	var value=value==null?"":value;
	html += "<div class='autocut' title="+value+">"+value+"</div>";
}


//列格式化-序号
function FMT_Num(value,row,index) {
	 var pageNumber = $("#SysStaffRefAcctTable").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#SysStaffRefAcctTable").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
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

//保存按钮
function btn_staff_save(){
	$('#staffNm_').val('');
	var str="";
	var roleNm=$('#roleNm').val();
	$("#staffInfo_table tbody tr input").each(function(i){
		if($(this).prop('checked')==true){
			str+=array[i]+",";
		}
	});
	if(str!=""){
		str=(str.substring(str.length-1)==',')?str.substring(0,str.length-1):str;
		$('#staffNm_').val(str)
	}
	var staffNm=$('#staffNm_').val();
	var url=basePath+"system/sysrela!relasq.action";
	var sqtype=staffNm==""?"qx":"sq";
	var obj={
		"formBean.infoBean.taNm":roleNm
		,"formBean.infoBean.taName":"sys_role"
		,"formBean.infoBean.tbNm":staffNm
		,"formBean.infoBean.tbName":"sys_staff"
		,"formBean.parmBean.parm1":sqtype 
		,"formBean.parmBean.parm2":"sys_role" 
	}
	common_ajax(obj,url, function(response) {
		$('#set_user_dialog').modal("hide");
		$('#tbinfo').bootstrapTable('refresh');
		var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
		msg.show();	
	});
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

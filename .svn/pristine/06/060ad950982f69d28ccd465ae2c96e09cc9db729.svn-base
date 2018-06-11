var opt={};
var sysDept=new $.System_SysDept();
$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTreeList的数据
	sysDept.InitData(opt);
	//初始化新增、编辑和删除
	sysDept.InitAddEditDel();
	sysDept.initTreeNmSysDept(); //初始化建管单位
	sysDept.initVoltageEvel();   //初始化电压等级
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	
})
// 调整界面布局大小
function _AutoSize(){
	// 设置组件的高度
} 
function _reset(){
	sysDept.reset();
}
function _add(id){
	sysDept.add(id);
}
function _edit(id,onlyread){
	sysDept.edit(id,onlyread);
}
function _removeids(id){
	sysDept.removeids(id);
}
function _setUp(id){
	sysDept.setUp(id);
}
function _enterSys(eNm){
	sysDept.enterSys(eNm);
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
		var node = treeObj.getNodeByParam("id",$("#treenmSysDept_sysWorkInfo").val());
		if (node) {
			treeObj.checkNode(node,true,false);
			$("#treenmSysDept_sysWorkInfo").val(node.id);
			$("#treenmSysDept").val(node.name);
		 }
	}
}

//选择树节点事件
function zTreeOnCheck(event, treeId, treeNode) {
	$("#treenmSysDept_sysWorkInfo").val("");
	$("#treenmSysDept").val("");
	var treeObj = $.fn.zTree.getZTreeObj("DeptTree");
	var nodes = treeObj.getCheckedNodes(true);
	for(var i=0,len=nodes.length;i<len;i++){
		$("#treenmSysDept_sysWorkInfo").val(nodes[i].id);
		$("#treenmSysDept").val(nodes[i].name);
	}
}

<%@ page language="java" pageEncoding="UTF-8"%>

<style type="text/css">

.clear_flow{
	position:static;
}
</style>

<form id="info_form_staffRefEm" name="info_form_staffRefEm" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="info_dialog_staffRefEm">
		<div class="modal-dialog modal-lg" style="width: 800px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"><i class="icon-window-alt"></i>&nbsp;负责工程</h4>
				</div>
				
				<div class="modal-body">
					<div style="display:none ;">
					  engineerNm<input type="hidden" id="engineerNm" name="mSysEngineerInfoFormBean.ids"/>
					  staffNm<input type="hidden" id="staffNm_staff_" name="mSysEngineerInfoFormBean.mSysStaff.nm"/>
					  tableAName<input type="text" class="form-control" value="pub_engineering" id="tableAName_sysWorkInfo" name="mSysEngineerInfoFormBean.mSysRefEngineerInfoBean.tableAName"/>
					  tableBName<input type="text" class="form-control" value="sys_staff" id="tableBName_sysWorkInfo" name="mSysEngineerInfoFormBean.mSysRefEngineerInfoBean.tableBName"/>
					  parm1<input type="text" class="form-control" value="staffFlag" id="parm1_staff" name="mSysEngineerInfoFormBean.parmBean.parm1"/>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 人员名称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="staffName_staff_" 
                                   required readonly="readonly"
                                   data-bv-notempty-message="人员名称不能为空"   
                            >
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                             工程名称：
						</label>
						<div class="col-md-9">
						    <ul id="initTree" class="ztree" ></ul>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="btnStaffRefEmClick()" class="btn btn-primary" >
						<i class="icon icon-save"></i> 保存
					</button>
					
					<button type="button" class="btn" data-dismiss="modal">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			</div>
		</div>
	</div>
</form>

<script type="text/javascript">
var nodeArray,jsonArray;
function engineerShow(staffNm,staffName){
	$("#staffNm_staff_").val(staffNm);
	$("#staffName_staff_").val(staffName);
	loadTreeData();
	getEngineerInfo();
	$("#info_dialog_staffRefEm").modal({
		 show : true
		,backdrop : "static" // 背景遮挡
		,moveable : true
	}).on('shown.zui.modal', function() {
     
	});
}

//根据人员内码获取工程信息
function getEngineerInfo(){
	var staffNm=$("#staffNm_staff_").val();
	var url=basePath+"system/sysengineerinfo!getEngineerInfoByStaffNm.action";
	$.post(url,{"staffNm":staffNm},function(response){
		var rows=JSON.parse(response).rows;
		for(var i=0,len=rows.length;i<len;i++){
			var obj=new Object();
			obj.id=rows[i].NM;
			obj.name=rows[i].NAME;
			nodeArray.push(obj);
		}
		initTree(jsonArray,nodeArray);
	});
}

//加载所有工程信息树形数据 
function loadTreeData(){
	nodeArray=new Array();
	jsonArray=new Array();
	var url=basePath+"system/sysengineerinfo!list.action";
	$.post(url,null,function(response){
		var rows=response.rows;
		for(var i=0;i<rows.length;i++){
			var obj=new Object();
			obj.id=rows[i].NM;
			obj.pId=rows[i].PNM;
			obj.name=rows[i].ENGINEER_NAME;
			jsonArray.push(obj);
		}
		initTree(jsonArray,nodeArray);
	});
}

//设置树形参数并初始化
function initTree(jsonArray,nodeArray){
	var setting={
		view:{
			selectedMulti: false,
			showIcon: true,
			showLine: true,
			expandSpeed: "fast"
		},
		check:{
			enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "ps", "N": "ps" }
		},
		data:{
			key:{
				name:"name"
			},
			simpleData:{
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback:{
			onCheck: zTreeOnCheck_
		}
	};
	var zTreeNodes=jsonArray;
	if(zTreeNodes!=null && zTreeNodes!=""){
		zTreeNodes[0].open=true; 
	}
	$.fn.zTree.init($("#initTree"), setting, zTreeNodes);
	var tree = $.fn.zTree.getZTreeObj("initTree");
	checkNodes(nodeArray,tree);
}

//默认选中树节点
function checkNodes(nodeArray,tree){
	var nodes = tree.getNodes();
	var treeNodesArr=new Array();
	if(nodes.length>0){
		for(var i=0,len=nodeArray.length;i<len;i++){
			var node = tree.getNodeByParam("id", nodeArray[i].id);  
			if (node) {
				 tree.checkNode(node,true,false);
				 treeNodesArr.push(nodeArray[i]);
			 }
		}
		if(treeNodesArr!=null && treeNodesArr!=""){
			checkNodes_(treeNodesArr);
		}
	}
}

//选中树形复选框
function zTreeOnCheck_(event, treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("initTree");
	var nodes = treeObj.getCheckedNodes(true);
	checkNodes_(nodes);
}

//获取树节点赋值给隐藏域
function checkNodes_(nodes){
	var str="";
	var engineerName=new Array();
	for(var i=0,len=nodes.length;i<len;i++){
		str+=nodes[i].id+","
		engineerName.push(nodes[i].name);
	}
	if(str!=null && str!=""){
		str=(str.substring(str.length-1)==',')?str.substring(0,str.length-1):str;
	}
	$("#engineerNm").val(str);
}

//点击保存按钮
function btnStaffRefEmClick(){
	var json=$("#info_form_staffRefEm").serialize();
	var url=basePath+"system/sysengineerinfo!saveSetDept.action";
	$.ajax({
		url : url,
		type : "POST",
		async:false,
		data : json,
		success : function(response) {
		   $('#info_dialog_staffRefEm').modal("hide");
			$('#SysStaffRefAcctTable').bootstrapTable('refresh');
			var msg = new $.zui.Messager("消息提示："+JSON.parse(response).promt, {placement: "center",type:"primary"});
		    msg.show();	
		}
   });
}
</script>

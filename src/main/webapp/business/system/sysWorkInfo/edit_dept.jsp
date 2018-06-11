<%@ page language="java" pageEncoding="UTF-8"%>

<style type="text/css">

.clear_flow{
	position:static;
}
</style>

<form id="info_form_dept" name="info_form_dept" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog_dept">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">信息维护</h4>
				</div>

				<div class="modal-body">
	
					<div style="display:none ;">
						id<input type="text" class="form-control" id="id__sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.id">
                        nm<input type="text" class="form-control" id="nm__sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.nm">
                        pnm<input type="text" class="form-control" id="pnm__sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.pnm">
                        code<input type="text" class="form-control" id="code__sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.engineerCode">
                        pcode<input type="text" class="form-control" id="pcode__sysWorkInfo" name="mSysEngineerInfoFormBean.mSysEngineerInfo.pcode">
					    tableAName<input type="text" class="form-control" value="pub_engineering" id="tableAName__sysWorkInfo" name="mSysEngineerInfoFormBean.mSysRefEngineerInfoBean.tableAName">
					    tableBName<input type="text" class="form-control" value="sys_staff" id="tableBName__sysWorkInfo" name="mSysEngineerInfoFormBean.mSysRefEngineerInfoBean.tableBName">
					    staffNm<input type="text" class="form-control" id="staffNm">
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 工程名称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="engineerName__sysWorkInfo" 
                                   name="mSysEngineerInfoFormBean.mSysEngineerInfo.engineerName" 
                                   required readonly="readonly"
                                   data-bv-notempty-message="工程名称不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入工程名称，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
					
					<div class="form-group hidden">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 负责标段：
						</label>
						<div class="col-md-9 clear_flow">
                            <input type="text" class="form-control" id="section__sysWorkInfo" 
                                   name="formBean.infoBean.section" 
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 单位名称：
						</label>
						<div class="col-md-9">
						    <input type="hidden" id="dept_code__sysWorkInfo" required name="formBean.infoBean.code"/>
						    <input type="hidden" id="staff_code__sysWorkInfo" required name="formBean.ids"/>
					   		<input type="hidden" id="name__sysWorkInfo" required name="formBean.infoBean.name" />
					   		<ul id="initTree" class="ztree" ></ul>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" id="btn_save_dept" class="btn btn-primary" >
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
function loadTreeData(){
	var jsonArray=new Array();
	var nodeArray=new Array();
	var url_dept=basePath+"system/sysdept!getDeptInfo.action";
	var url_staff=basePath+"system/sysstaff!getStaffInfo.action";
	getStaffInfo(nodeArray);
	common_ajax_noasync(null,url_dept, function(response) {
		var rows=response.rows;
		for(var i=0;i<rows.length;i++){
			json={'id':rows[i].nm,'pId':rows[i].pnm,'name':rows[i].name,type:"dept"};
			jsonArray.push(json);
		}
	});
	common_ajax_noasync(null,url_staff,function(response){
		var rows=response.rows;
		for(var i=0;i<rows.length;i++){
			json={'id':rows[i].nm,'pId':rows[i].treenm_sys_dept,'name':rows[i].name,type:"staff"};
			jsonArray.push(json);
		}
	});
	initZtree(jsonArray,nodeArray);
}

//设置树属性初始化树
function initZtree(jsonArray,nodeArray){
	var setting={
		data:{
			simpleData:{
				enable: true
			}
		},
		view:{
			selectedMulti: false,
			expandSpeed: "fast"
		},
		check:{
			enable: true,
			autoCheckTrigger: false,
			chkboxType:{ "Y": "ps", "N": "ps" }
		},
		callback: {
			onCheck: zTreeOnCheck
		}
	}
	var zNodes =jsonArray;
	if(zNodes!=null && zNodes!=""){
		zNodes[0].open=true; //默认展开一级节点
	}
  	$.fn.zTree.init($("#initTree"), setting, zNodes);
  	var tree=$.fn.zTree.getZTreeObj("initTree");
  	setTimeout(function(){
  	  checkNodes(nodeArray,tree);
  	},1000);
}

//获取人员信息
function getStaffInfo(nodeArray){
	var engineerInfoCode=$("#nm__sysWorkInfo").val();
	var url = basePath+"system/sysengineerinfo!getStaffByEngineerNm.action";
	$.post(url,{"engineerInfoCode":engineerInfoCode},function(response){
		var str="";
		var rows = JSON.parse(response).rows;
		for(var i=0;i<rows.length;i++){
			var obj=new Object();
			obj.nm=rows[i].NM;
			obj.type="staff";
			nodeArray.push(obj);
			str+=rows[i].TREENMSYSDEPT+",";
		}
		str=(str.substring(str.length-1)==',')?str.substring(0,str.length-1):str;
		getDeptInfo(nodeArray,str);
	});
}

//获取部门信息
function getDeptInfo(nodeArray,deptNm){
	var url = basePath+"system/sysdept!getDeptByNm.action";
	$.post(url,{"deptNm":deptNm},function(response){
		var str="";
		var rows = JSON.parse(response).rows;
		for(var i=0;i<rows.length;i++){
			var obj=new Object();
			obj.nm=rows[i].NM;
			obj.type="dept";
			nodeArray.push(obj);
		}
	});
}

//默认选中树节点
function checkNodes(nodeArray,tree){
	var nodes = tree.getNodes();
	var deptStaffArray=new Array();
	if (nodes.length>0) {
		for(var i=0;i<nodeArray.length;i++){
			 var node = tree.getNodeByParam("id", nodeArray[i].nm); 
			 if (node) {
				 tree.checkNode(node,true,false);
				 deptStaffArray.push(nodeArray[i])
			 }
		}
		if(deptStaffArray!=null && deptStaffArray!="" ){
			setNodes(deptStaffArray);
		}
	}
}

//选中树节点复选框事件
function zTreeOnCheck(event,treeId,treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("initTree");
    var CheckedNodes =treeObj.getCheckedNodes(true);
    checkNodes_(CheckedNodes);
}

function checkNodes_(deptArray){
	var deptNm=new Array();
	var staffNm=new Array();
	var deptName=new Array();
	for(i=0;i<deptArray.length;i++){
	   if(deptArray[i].id.length==32){
		  if(deptArray[i].type == "dept"){
			  deptNm.push(deptArray[i].id);
			  deptName.push(deptArray[i].name);
		   }else if(deptArray[i].type == "staff"){
			  staffNm.push(deptArray[i].id);
		   }
		}
	 }
	 $("#dept_code__sysWorkInfo").val(deptNm);
	 $("#staff_code__sysWorkInfo").val(staffNm);
	 $("#name__sysWorkInfo").val(deptName);
}

//设置节点
function setNodes(deptArray){
	var deptNm=new Array();
	var staffNm=new Array();
	var deptName=new Array();
	for(i=0;i<deptArray.length;i++){
		if(deptArray[i].type == "dept"){
			 deptNm.push(deptArray[i].nm);
		 }else if(deptArray[i].type == "staff"){
			 staffNm.push(deptArray[i].nm);
		 }
	 }
	 $("#dept_code__sysWorkInfo").val(deptNm);
	 $("#staff_code__sysWorkInfo").val(staffNm);
}

</script>

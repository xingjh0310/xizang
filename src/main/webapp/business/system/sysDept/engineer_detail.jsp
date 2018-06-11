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
		<div class="modal-dialog modal-lg" style="width: 500px">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"><i class="icon-window-alt"></i>&nbsp;负责工程</h4>
				</div>

				<div class="modal-body">

					
					<div class="form-group">
					   		<ul id="initTree" class="ztree" ></ul>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			</div>
		</div>
	</div>
</form>

<script type="text/javascript">
  function _showEngineer(engineerNm){
	var nodeArray = engineerNm.split(",");
    var jsonArray=[];
    var json = {};
	var engineerInfoCode = $("#nm__sysWorkInfo").val();
	var url_eng = basePath + "contInfo/contInfo!queryAllEngineer.action"//所属工程下拉数据
	
	if (engineerNm.substr(0,1)==','){
		engineerNm=engineerNm.substr(1);
	}
	//查询默认勾选的部门
	$.ajax({
       type: "POST",//规定传输方式
       url: url_eng,//提交URL
       data: {"engineerNm":engineerNm},//提交的数据
       async:false,
       success: function(response){
			var json = JSON.parse(response);
			var rows=json.engineers;
	 		for(var i=0;i<rows.length;i++){
				json={'id':rows[i].THISCODE,'pId':rows[i].PCODE,'name':rows[i].name};
				jsonArray.push(json);
			}
      }
    });
	loadzTreeInfo(jsonArray,nodeArray);

	$("#info_dialog_dept").modal({
		 show : true
		,backdrop : "static" // 背景遮挡
		,moveable : true
	}).on('shown.zui.modal', function() {
   });
  }
  
  function loadzTreeInfo(zTreeArray,nodeArray){
  	var setting = {
  	  data: {
		 simpleData: {
		    enable: true
		 },
	  },
	  view: {
		 selectedMulti: false,
	  },
	  check: {
		 enable: false,
		 nocheckInherit: false,
		 autoCheckTrigger: false,
		 chkboxType:{ "Y": "", "N": "" }
	  },
	  callback: {
// 		 onCheck: zTreeOnCheck,
	  }
  	};
  	
  	var zNodes =zTreeArray;
  	zNodes[0].open=true; //默认展开一级节点
  	
  	//部门查询
    function otherDept(array){
		var deptArray=array;
		var deptNm=[];
		var staffNm=[];
		var deptName=[];
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
	
	//全不选
	function noAllCheckedNodes(){
		var treeObj = $.fn.zTree.getZTreeObj("initTree");
		var noCheckedNodes =treeObj.getCheckedNodes(false);
		return noCheckedNodes.length;
	}
  	$.fn.zTree.init($("#initTree"), setting, zNodes);
  	//默认勾选节点
  	var tree = $.fn.zTree.getZTreeObj("initTree");
	var nodes = tree.getNodes();  
	if (nodes.length>0) {
		for(var i=0;i<nodeArray.length;i++){
		    var node = tree.getNodeByParam("id", nodeArray[i]);  
		    if (node) {  
		    	tree.checkNode(node,true,false);
		    }  
		}
	}
  }
  
</script>

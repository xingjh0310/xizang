var opt={};
var mail=new $.System_Mail();

$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	//延迟500毫秒
	setTimeout(function(){
	mail.InitData(opt);
	},500)
	//初始化新增、编辑和删除
	mail.InitAddEditDel();
	// 窗体变化时，调整组件的大小
	//获取职务
	getPot();
	$(window).resize(function(){
		_AutoSize();
	});
	setTimeout(function(){
	_AutoSize();
	},600)
	loadMailzTree();
	
	getEngName();
	
})
function getEngName(){
	
var nm=	sessionStorage.getItem("engineeringNm"); //默认选中的节点内码
var name=sessionStorage.getItem("engineeringName");//选中的节点的名称
	//$("#nm_mail").val(nm);
	$("#mail_eng").val(nm);
	
}

function getPot(){
	$("#position_mail").empty();
	var html="<option value=''>请选择</option>";
	var url = basePath+"mail/mail!getPot.action";
	common_ajax(null,url, function(response) {
		var classify=response.rows;
		for(i=0;i<classify.length;i++){
			html+="<option value='"+classify[i].CODE+"'>"+classify[i].NAME+"</option>";
		}
		$("#position_mail").append(html);
		
	});
}

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo").bootstrapTable("resetView",{"height":comm_getHeight()-80});  
	$("#bc").height(comm_getHeight()-133)
} 


function _reset(){
	mail.reset();
}

function zTree_refresh(){
	mail.zTreeRefresh()
}

////////////////////////////格式化BootStrap表中的格式
//列格式化-选择
function FMT_Check(value,row,index) {
    if (index<0) {
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
//列格式化-性别
function FMT_Sex(value,row,index) {
	
	var html="";
	if(value==1){html="男"}
	if(value==0){html="女"}
	return html;
}

// 操作
function FMT_Oper(value,row) {
    var html="";
    html="<a href='#' onclick='javascript:mail.edit("+row.ID+")'>" +
		"<button class='btn btn-xs btn-primary'>" +
		"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div>" +
		"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
		"</button></a>" +
		"&nbsp;&nbsp;" +
		"<a href='#' onclick='javascript:mail.del("+row.ID+")'>" +
		"<button class='btn btn-xs btn-danger'>" +
		"<div class='visible-md visible-lg'><i class='icon icon-times'></i>&nbsp;删除</div>" +
		"<div class='visible-xs visible-sm'><i class='icon icon-times'></i></div>" +
		"</button></a>" ; 
    return html;
}


////////////////////////////格式化BootStrap表中的格式

function loadMailzTree(){
	
	var ztreeNodes=new Array()
	//树形菜单
  	common_ajax_noasync(null, basePath + "mailType/mailType!zTree.action", function(response) {
		ztreeNodes = response.zTree;
	});
	var setting = {
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType:{ "Y" : "s", "N" : "s" }
			},
			edit: {
				enable: true,
				editNameSelectAll: true,
				showRemoveBtn: showRemoveBtn,
				showRenameBtn: showRenameBtn
			},
			data: {
				simpleData: {
					enable: true,
				}
			},
			callback: {
				beforeDrag: beforeDrag,
				beforeEditName: beforeEditName,
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				onRemove: onRemove,
				onRename: onRename,
				beforeClick: beforeClick,
				onClick: onClick,
				onCheck: onCheck
			}
		};

	var	zNodes = ztreeNodes;
		for(var i=0;i<zNodes.length;i++){
			zNodes[0].checked='true'; //默认勾选
			zNodes[i].open='true'; //默认展开
			var code =zNodes[0].id
			var name =zNodes[0].name
			
			$("#searchCode").val(code+",")
			$("#code").val(code)		//节点code
			$("#baseName").val(name);   //节点名称
		}	
		var log, className = "dark";
		function beforeDrag(treeId, treeNodes) {
			return false;
		}
		function beforeEditName(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			setTimeout(function(){
				if (confirm(treeNode.name,"编辑节点 ","icon-info",function(){
					setTimeout(function() {
						zTree.editName(treeNode);
					
					},0)
				}));	
			},0);
			return false;
		}
		function beforeRemove(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			
		if(confirm(treeNode.name,"确认删除 节点 吗？","icon-remove-sign",function (){
			
			setTimeout(function() {
				//console.log("删除节点"+JSON.stringify(treeNode))
				zTree.removeNode(treeNode);	
				var id=treeNode.id;
				var url = basePath+"mailType/mailType!removes.action"+"?mailFormBean.mailType.code="+id;
				common_ajax_noasync(null, url, function(response) {
					//ztreeNodes = response.zTree;
				});
				
			},0)
					})){
				}
			return false;
		}
		function onRemove(e, treeId, treeNode) {
			console.log("删除节点")
			
		}
		function beforeRename(treeId, treeNode, newName, isCancel) {
			className = (className === "dark" ? "":"dark");
			
			if (newName.length == 0) {
				setTimeout(function() {
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					zTree.cancelEditName();
					var msg = new $.zui.Messager("消息提示：节点名称不能为空", {placement: "center",type:"danger"});
					   msg.show();	
				}, 0);
				return false;
			}
			return true;
		}
		
		function onRename(e, treeId, treeNode, isCancel) {
			//console.log("编辑节点"+treeId+JSON.stringify(treeNode)+"执行")
			
			var name =treeNode.name;
			var id=treeNode.id;
			var nm=treeNode.nm;
			var url = basePath+"mailType/mailType!edit.action"+"?mailFormBean.mailType.id="+nm+"&mailFormBean.mailType.deptName="+name;
			common_ajax_noasync(null, url, function(response) {														 
				//ztreeNodes = response.zTree;
			});
			
		}
		//设定节点是否可删除
		function showRemoveBtn(treeId, treeNode) {
			
			//return !treeNode.isFirstNode;第一个节点不能删除
			return true;
		}
		//设定节点是否可编辑
		function showRenameBtn(treeId, treeNode) {
			//return !treeNode.isLastNode;最后一个节点不能编辑
			return true;
		}
		
		function beforeClick(treeId, treeNode, clickFlag) {
			
			return true;
		}
		//点击节点
		function onClick(event, treeId, treeNode, clickFlag) {
			/*var name =treeNode.name;
			var id=treeNode.id;
			var nm=treeNode.nm;
			//console.log(id+"点击节点")
			$("#code").val(id);
			$("#baseName").val(name);*/
		}
		//勾选节点获取ID
		function onCheck(e, treeId, treeNode) {
			
			var listNode="";
			var name="";
	        var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
	        nodes=treeObj.getCheckedNodes(true)
	     	
	        for(var i=0;i<nodes.length;i++){
	      	listNode+=nodes[i].id+",";
	      	name+=nodes[i].name;
	        }
			//console.log(nodes)
			$("#searchCode").val(listNode)
			$("#code").val(listNode.substring(0,listNode.length-1))	//节点code
			$("#baseName").val(name);   //节点名称
			
			mail.ref();
			
		}
		
		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			
			var maxId=zNodes[zNodes.length-1].id;
			var num=parseInt(maxId)+newCount;
			
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='新增节点' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				
				treeNode =zTree.addNodes(treeNode, {id:num, pid:treeNode.id,pnm:treeNode.cnm, name:"新增节点" + (newCount++)});
				
				var id=treeNode[0].id;
				var pid=treeNode[0].pid;
				var name =treeNode[0].name;
				var pnm =treeNode[0].pnm
				
				var url = basePath+"mailType/mailType!addPid.action"+"?mailFormBean.mailType.code="+id+"&mailFormBean.mailType.deptName="+name+"&mailFormBean.mailType.pcode="+pid+"&mailFormBean.mailType.pnm="+pnm;
				
				common_ajax_noasync(null, url, function(response) {
			  		
				});
				
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		//增加根节点
		function add(e) {
			var list = [];
			if(zNodes.length>0){
				for(var i=0;i<zNodes.length;i++){
					if(zNodes[i].pId==null||zNodes[i].pId==""|| zNodes[i].pId==0){
						list.push(zNodes[i])
					}
				}
				var maxId=list[list.length-1].id;
				
			}else{
				maxId=0;
			}
			var num=parseInt(maxId)+newCount;
			
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			isParent = e.data.isParent,
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			
			treeNode = zTree.addNodes(null, {id:num, pid:0, isParent:isParent, name:"新增根节点" + (newCount++)});
			
			var id=treeNode[0].id;
			var pid=treeNode[0].pid;
			var name =treeNode[0].name;
			
			var url = basePath+"mailType/mailType!addPid.action"+"?mailFormBean.mailType.code="+id+"&mailFormBean.mailType.deptName="+name+"&mailFormBean.mailType.pcode="+pid
			
			common_ajax_noasync(null, url, function(response) {
				//ztreeNodes = response.zTree;
			});
			
			location.reload();
			
		};
		
		
	   $(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			
			$("#addParent").bind("click", {isParent:true}, add); //增加父节点
		});
	
}
////////////////////////////////////////////////////////////////////////////////////////

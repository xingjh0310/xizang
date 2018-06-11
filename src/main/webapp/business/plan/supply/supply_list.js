var opt={};
var supply=new $.System_supply();

$(function(){
	
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	supply.InitData(opt);
	//初始化新增、编辑和删除
	supply.InitAddEditDel();
	
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	//查询所有的物料类型--select
	queryMaterielType();
	//查询所有物料名称--select
	queryMaterielBase();
	//查询所有主合同信息
	queryContInfo();
	//查询项目单位
	//queryUpDetp();
});

//查询项目单位
function queryUpDetp(deptNm){
	var engineerInfoCode=sessionStorage.getItem("engineeringNm");
	var url=basePath+"plan/supply!getDeptInfoByEngineerInfoCode.action?engineerInfoCode="+engineerInfoCode;
	common_ajax(null, url, function(response){
		var zTreeArray=new Array();
		var data=response.rows;
		for(i=0;i<data.length;i++){
			var zTree;
			if(data[i].nm==deptNm){
				zTree={'id':data[i].nm,'pId':data[i].pnm,"name":data[i].name,"open":true,checked:true};
			}else{
				zTree={'id':data[i].nm,'pId':data[i].pnm,"name":data[i].name,"open":true};
			}
			zTreeArray.push(zTree);
		}
		loadzTreeInfo(zTreeArray)
	});
}

//加载树形切换
$("#loadTreeDate").on({
	mouseover:function(){
		$("#zTreeDiv_dept").show();
	},
	mouseout:function(){
		$("#zTreeDiv_dept").hide();
	}
});

//调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo").bootstrapTable("resetView",{"height":comm_getHeight()-80});      
} 

//查询所有的物料类型--select
function queryMaterielType(){
	var html="<option value=''>请选择</option>";
	$("#material_type").empty();
	var url=basePath+"materielType/materielType!zTree.action";
	common_ajax(null, url, function(response){
		var materielType=response.zTree;
		for(i=0;i<materielType.length;i++){
			html+="<option value='"+materielType[i].id+"'>"+materielType[i].name+"</option>";
		}
		
		$("#material_type").append(html);
	});
}

//查询所有物料名称--select
function queryMaterielBase(){
	var html="<option value=''>请选择</option>";
	var data={
		  "materielBaseFormBean.pageBean.limit"		: g_treelist_size   // 页面大小
		 ,"materielBaseFormBean.pageBean.offset"	: 0 				// 当前记录偏移条数
		 ,"materielBaseFormBean.pageBean.sort"		: 'ID'  			// 排序列名
		 ,"materielBaseFormBean.pageBean.sortOrder"	: 'DESC'			// 排位命令（desc，asc）
	}
	$("#material_name").empty();
	var url=basePath+"materielBase/materielBase!all_list.action";
	common_ajax(data, url, function(response){
		var materielName=response.rows;
		for(i=0;i<materielName.length;i++){
			html+="<option value='"+materielName[i].MATERIELCODE+"'>"+materielName[i].MATERIELNAME+"</option>";
		}
		$("#material_name").append(html);
	});
}

//查询所有主合同信息
function queryContInfo(){
	var url=basePath+"contInfo/contInfo!list.action";
	var data={
			  "contInfoFormBean.pageBean.limit"		: g_treelist_size   // 页面大小
			 ,"contInfoFormBean.pageBean.offset"	: 0 				// 当前记录偏移条数
			 ,"contInfoFormBean.pageBean.sort"		: 'ID'  			// 排序列名
			 ,"contInfoFormBean.pageBean.sortOrder"	: 'DESC'			// 排位命令（desc，asc）
			 ,"contInfoFormBean.contInfoBean.engineerCode"			:sessionStorage.getItem("engineeringNm")
		}
	var html="<option value=''>请选择合同</option>";
	$("#CONTRACTNO").empty();
	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
		data : data,
		success : function(response) {
			var contInfo=response.rows;
			for(i=0;i<contInfo.length;i++){
				html+="<option value='"+contInfo[i].CONTRACTNO+"'>"+contInfo[i].CONTRACTNO+"("+contInfo[i].CONTRACTTITLE+")</option>";
			}
			$("#CONTRACTNO").append(html);
		}
	});
}

////////////////////////////格式化BootStrap表中的格式

//标题
function FMT_Title(value,row){
	var html="<div class='autocutshort' title='"+value+"'>"+value+"</div>";
	return html;
}
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
   return index+1;
//	 var pageNumber = $("#tbinfo").bootstrapTable('getOptions').pageNumber;
//	 var pageSize =   $("#tbinfo").bootstrapTable('getOptions').pageSize;
//	  return (pageNumber-1) * pageSize+index+1;
}
//操作
function FMT_Oper(value,row) {
    var html="";
    html="<a href='#' onclick='javascript:supply.edit("+row.ID+")'>" +
	"<button class='btn btn-xs btn-primary'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
	"</button></a>" +
	"&nbsp;&nbsp;" +
	"<a href='#' onclick='javascript:supply.del("+row.ID+")'>" +
	"<button class='btn btn-xs btn-danger btn_del_color'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-trash'></i>&nbsp;删除</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-trash'></i></div>" +
	"</button></a>&nbsp;&nbsp;" ; 
    html+="<a href='#' onclick='javascript:supply.up_reported("+row.ID+")'>" +
    	  "<button class='btn btn-xs btn-primary'>" +
    	  "<div class='visible-md visible-lg'><i class='icon icon-refresh'></i>&nbsp;更新中标日期</div>" +
    	  "<div class='visible-xs visible-sm'><i class='icon icon-refresh'></i></div>" +
    	  "</button></a>&nbsp;&nbsp;";
	if(row.EVASTATE != null){
		html += "<a href='javascript:void(0)'>" + 
			"<button class='btn btn-xs btn-primary' disabled='true'>" +
			"<div class='visible-md visible-lg'><i class='icon icon-smile'></i>&nbsp;履约评价</div>" +
			"<div class='visible-xs visible-sm'><i class='icon icon-smile'></i></div>" +
			"</button></a>" ; 
	}else {
		html += "<a href='javascript:query.evaluation("+value+")'>" + 
		 	"<button class='btn btn-xs btn-primary'>" +
			"<div class='visible-md visible-lg'><i class='icon icon-smile'></i>&nbsp;履约评价</div>" +
			"<div class='visible-xs visible-sm'><i class='icon icon-smile'></i></div>" +
			"</button></a>" ; 
	}
   return html;
}
//附件
function FMT_Accessory(value,row){
	var html="";
	var accessory=value==null?0:value;
	html="<a href='#' id='fileNub_id"+row.ID+"' onclick='javascript:supply.upload_file("+row.ID+")'>"+accessory+"</a>";
	return html;
}
//状态
function FMT_planState(value,row){
	var html="";
	if(value == 1){
		html="未执行";
	}else if(value == 2){
		html="已执行";
	}
	return html;
}

//物料编码
function FMT_MATERIELCODE(value,row){
	var html="<div class='MATERIELCODE_css' title='"+value+"'>"+value+"</div>";
	return html;
}

////////////////////////////格式化BootStrap表中的格式
//选中多行改变表格背景色
function onCheckAll(rows){
	for(var i=0;i<rows.length;i++){
		commRowStyle(i);
	}
}
//循环改变所有行颜色
function commRowStyle(i){
	$("#tbinfo tbody tr[data-index="+i+"]").addClass("success");
}
//全不选时颜色恢复
function onUncheckAll(){
	$("#tbinfo tbody tr").removeClass("success");
}
//选中一行改变表格背景色
function onCheck(rows){
	$("#tbinfo tbody tr[data-uniqueid="+rows.ID+"]").addClass("success");
}
//不选中时颜色恢复
function onUncheck(rows){
	$("#tbinfo tbody tr[data-uniqueid="+rows.ID+"]").removeClass("success");
}

//zTree
function loadzTreeInfo(zTreeArray){
	
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
					chkStyle:"radio",
					enable: true,
					radioType: "all"
				},
			callback: {
					onCheck: zTreeOnCheck
				}
		};
	var zNodes =zTreeArray;
//	var zNodes =[{id:1,pId:0,name:"蓝宇汇通","nocheck":true,"open":true},
//				 {id:"28ba1401745247ca8bb252f06626d835",pId:1,name:"技术部"},
//				 {id:3,pId:1,name:"市场部"},];
	//选中/取消
    function zTreeOnCheck(event,treeId,treeNode) {
    	 var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    	 var CheckedNodes =treeObj.getCheckedNodes(true);
    	 radioOnAfter(CheckedNodes)
	} 
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
}

//zTree单选按钮点击后事件
function radioOnAfter(CheckedNodes){
	if(CheckedNodes.length>0){
		$("#DEPTNAME").val(CheckedNodes[0].name)
		$("#TREENMSYSDEPT").val(CheckedNodes[0].id)
	}else{
		$("#DEPTNAME").val("")
		$("#TREENMSYSDEPT").val("")
	}
}
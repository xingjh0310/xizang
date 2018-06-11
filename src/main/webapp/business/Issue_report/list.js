﻿var opt={};
var bank=new $.System_Bank();
$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	bank.InitData(opt);
	//初始化新增、编辑和删除
	bank.InitAddEditDel();
	
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	//查询所有的物料类型--select
	queryMaterielType();
	//查询所有物料名称--select
	queryMaterielBase();
	//查询上报单位
	queryUpDetp();
	//查询问题类型
	queryQuestionType();
	
	nowTime();
	var nowDate= new Date();           
	$("#REPORTTIME").val(nowDate.format("yyyy-MM-dd"));
	
	
});

//查询问题类型
function queryQuestionType(){
	var url=basePath+"question/submit!getProblemType.action";
	common_ajax(null, url, function(response){
		$("#question_type").empty();
		$("#PROBLEMTYPE").empty();
		var questionType=response.rows;
		var html="<option value=''>请选择</option>";
		for(i=0;i<questionType.length;i++){
			html+="<option value='"+questionType[i].NM+"'>"+questionType[i].NAME+"</option>";
		}
		$("#question_type").append(html);
		$("#PROBLEMTYPE").append(html);
	});
}


//当前时间
function nowTime(){
	var nowDate= new Date();           
	$("#REPORTTIME").val(nowDate.format("yyyy-MM-dd"));
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

// 调整界面布局大小
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
	$("#MATERIELCODE").empty();
	var url=basePath+"materielBase/materielBase!all_list.action";
	common_ajax(data, url, function(response){
		var materielName=response.rows;
		for(i=0;i<materielName.length;i++){
			html+="<option value='"+materielName[i].MATERIELCODE+"'>"+materielName[i].MATERIELNAME+"</option>";
		}
		$("#MATERIELCODE").append(html);
	});
}

//查询上报单位
function queryUpDetp(){
	var engineerInfoCode=sessionStorage.getItem("engineeringNm");
	var url=basePath+"plan/supply!getDeptInfoByEngineerInfoCode.action?engineerInfoCode="+engineerInfoCode;
	common_ajax(null, url, function(response){
		var zTreeArray=new Array();
		var data=response.rows;
		for(i=0;i<data.length;i++){
			var zTree;
			if(data[i].pnm=="" || data[i].nm==data[i].pnm){
				zTree={'id':data[i].nm,'pId':data[i].pnm,"name":data[i].name,"open":true};
			}else{
				zTree={'id':data[i].nm,'pId':data[i].pnm,"name":data[i].name,"open":true};
			}
			zTreeArray.push(zTree);
		}
		loadzTreeInfo(zTreeArray);
	});
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
     return index+1;
//	 var pageNumber = $("#tbinfo").bootstrapTable('getOptions').pageNumber;
//  	 var pageSize =   $("#tbinfo").bootstrapTable('getOptions').pageSize;
//  	  return (pageNumber-1) * pageSize+index+1;
}

// 操作
function FMT_Oper(value,row) {
    var html="";
    
    if(row.STATE==0){
    	html="<a href='#' onclick='javascript:bank.edit("+row.ID+")'>" +
    	"<button class='btn btn-xs btn-primary'>" +
    	"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div>" +
    	"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
    	"</button></a>" +
    	"&nbsp;&nbsp;" +
    	"<a href='#' onclick='javascript:bank.del("+row.ID+")'>" +
    	"<button class='btn btn-xs btn-danger btn_del_color'>" +
    	"<div class='visible-md visible-lg'><i class='icon icon-trash'></i>&nbsp;删除</div>" +
    	"<div class='visible-xs visible-sm'><i class='icon icon-trash'></i></div>" +
    	"</button></a>" ; 
    }
    
    return html;
}

//问题状态
function FMT_State(value,row){
	if(value == 0){
		return "<font color='red'>待处理</font>";
	}else{
		return "<font color='green'>已处理</font>";
	}
}

//附件
function FMT_Accessory(value,row){
	var html="";
	var accessory=value==null?0:value;
	html="<a href='#' id='fileNub_id"+row.ID+"' onclick='javascript:bank.upload_file("+row.ID+")'>"+accessory+"</a>";
	html+="<input type='hidden' id='fileNm_id"+row.ID+"' value='"+row.FILENM+"'>";
	return html;
}
////////////////////////////格式化BootStrap表中的格式
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
		$("#up_dept_name").val(CheckedNodes[0].name)
		$("#up_dept").val(CheckedNodes[0].id)
	}else{
		$("#up_dept_name").val("")
		$("#up_dept").val("")
	}
}

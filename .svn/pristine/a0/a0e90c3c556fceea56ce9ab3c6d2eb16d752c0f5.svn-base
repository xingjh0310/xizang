var opt={};
var bank=new $.System_Bank();

$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	bank.InitData(opt);
	//初始化新增、编辑和删除
	bank.InitAddEditDel();
	//绑定审核
	bank.InitFlag();
	
	//获取通知类型
	messageType();
	
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	setTimeout(function(){
		
	load();

	},200)
	getEngName();   
})
function getEngName(){
	
var nm=	sessionStorage.getItem("engineeringNm"); //默认选中的节点内码
var name=sessionStorage.getItem("engineeringName");//选中的节点的名称
	//$("#nm_mail").val(nm);
	$("#engineerCode").val(nm);
	
}

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo").bootstrapTable("resetView",{"height":comm_getHeight()-80});  
} 

function _reset(){
	bank.reset();
}
function a (value,row){
	return "<a href='javascript:'>"+value+"</a>";
}

////////////////////////////格式化BootStrap表中的格式
//格式化标题
//标题
function FMT_Title(value,row){
	var html="<div class='autocut' title='"+value+"'>"+value+"</div>";
	return html;
}
//格式化接收部门
function FMT_DeptName(value,row){
	var html="";
	html += "<div class='autocutshort' title="+value+">"+value+"</div>";
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
//状态
function FMT_State(value,row){
	html=""
	if(value == 0){html="<font color='red'>未发送</font>"}
	if(value == 1){html="<font color='green'>已发送</font>"}
	
	return html;
}
//列格式化-序号
function FMT_Num(value,row,index) {
     //return index+1;
	 var pageNumber = $("#tbinfo").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#tbinfo").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
}
//flag状态
function FMT_classify(value,row) {
    var html="";
    if (value==1){html="<font color='red'>警告</font>"}
    if (value==0){html="<font color='green'>通知</font>"}
    if (value==2){html="<font color='blue'>消息</font>"}
   return html;
}

// 操作
function FMT_Oper(value,row) {
	var html="";
	if(row.STATE == 0 ){
	    html="<a href='#' onclick='javascript:bank.edit("+row.ID+")'>" +
			"<button class='btn btn-xs btn-primary'>" +
			"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div>" +
			"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
			"</button></a>" +
			"&nbsp;&nbsp;" +
			"<a href='#' onclick='javascript:bank.del("+row.ID+")'>" +
			"<button class='btn btn-xs btn-danger'>" +
			"<div class='visible-md visible-lg'><i class='icon icon-times'></i>&nbsp;删除</div>" +
			"<div class='visible-xs visible-sm'><i class='icon icon-times'></i></div>" +
			"</button></a>"+
			"&nbsp;&nbsp;"+
			"<a href='#' onclick='javascript:bank.flag("+row.ID+")'>" +
			"<button class='btn btn-xs btn-primary'>" +
			"<div class='visible-md visible-lg'><i class='icon icon-ok'></i>&nbsp;发布</div>" +
			"<div class='visible-xs visible-sm'><i class='icon icon-ok'></i></div>" +
			"</button></a>";
	    }
	if(row.STATE== 1){
		html="<a href='#' onclick='javascript:bank.cancelFlag("+row.ID+")'>" +
		 "<button class='btn btn-xs btn-primary'>" +
		 "<div class='visible-md visible-lg'><i class='icon icon-reply'></i>&nbsp;取消发布</div>" +
		 "<div class='visible-xs visible-sm'><i class='icon icon-reply'></i></div>" +
		 "</button></a>" ; 
	}
			
	return html;
    
}

function rowStyle(row,index){
	var classes = ['active', 'success', 'info', 'warning', 'danger'];
	if (row.day<=0){
		return {
			classes: classes[4]
		};
	}
	if (row.day<=7 && row.day>0){
		return {
			classes: classes[3]
		};
	}
	return {};
}

function load(){
	
		var url = location.search;
		var id = 0;
		if(url.indexOf("?") != -1){    //判断是否有参数
			var str = url.substr(1); //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
			strs = str.split("=");   //用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
			var id = strs[1];
			$("#id").val(id)
			
			var url = basePath+"notice/notice!edit.action?noticeFormBean.notice.id=" +id;
			if(id > 0){
				common_ajax_noasync(null, url, function(response) {
					var json=response.infoBean;
					var	bean =json[0] 
					comm_loadFormData(bean);
					var htmlvalue=bean.context;
 
					htmlvalue= htmlvalue.replaceAll("&lt;", "<");
					htmlvalue= htmlvalue.replaceAll("&gt;", ">");
					htmlvalue= htmlvalue.replaceAll("&quot;", "\"");
					 
					$("#receiveDepts").val(bean.receiveCode);
					ue.ready(function() {
						 UE.getEditor('content').execCommand('insertHtml',htmlvalue );
						
					});
				});
				$("#mestit").html("（修改通知）")
			}
		}
}
 
function getSimpleText(html) {  
    return html.replace(/(\&|\&)gt;/g, ">")  
                .replace(/(\&|\&)lt;/g, "<")  
                .replace(/(\&|\&)quot;/g, "\"");  
 }  

//通知类型
function messageType(){
	$("#notice_classify").empty();
	$("#classify").empty();
	var html="<option value=''>请选择</option>";
	var url = basePath+"notice/notice!getMessageType.action";
	common_ajax(null,url, function(response) {
		var classify=response.rows;
		
		for(i=0;i<classify.length;i++){
			html+="<option value='"+classify[i].CODE+"'>"+classify[i].NAME+"</option>";
		}
		$("#notice_classify").append(html);
		$("#classify").append(html);
		
	});
	
	
}


////////////////////////////格式化BootStrap表中的格式

////////////////////////////////////////////////////////////////////////////////////////

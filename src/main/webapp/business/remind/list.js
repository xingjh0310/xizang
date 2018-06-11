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
	/*消息类型*/
	messageType();
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	
})
$("#font").bind("DOMNodeInserted",function(e){
	
     //console.log($(e.target).html());
     setTimeout(function(){
     var nm  =sessionStorage.getItem("engineeringNm")
     
     bank.flash(nm);
     
     },500)
     
})
//通知类型
function messageType(){
	
	$("#classify").empty();
	var html="<option value=''>请选择</option>";
	var url = basePath+"notice/notice!getMessageType.action";
	common_ajax(null,url, function(response) {
		var classify=response.rows;
		console.log(classify)
		for(i=0;i<classify.length;i++){
			html+="<option value='"+classify[i].CODE+"'>"+classify[i].NAME+"</option>";
		}
		$("#classify").append(html);
		
	});
	
}
// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo").bootstrapTable("resetView",{"height":comm_getHeight()-80});  
} 

//验证保养周期


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

////////////////////////////格式化BootStrap表中的格式

//消息状态
function FMT_State(value,row){
	if(value ==0){
		return "<font color='red'>未读</font>";
	}else{
		return "<font color='green'>已读</font>";
	}
}
function FMT_Title (value,row){
	return "<a href='javascript:'>"+value+"</a>";
}

////////////////////////////////////////////////////////////////////////////////////////

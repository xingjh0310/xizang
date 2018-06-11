var opt={};
var query=new $.Storage_Query

$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	setTimeout(function(){
		query.InitData(opt);
	},500)
	query.InitAddEditDel(opt);
	query.InitSelect();
	// 窗体变化时，调整组件的大小
//	$(window).resize(function(){
//		_AutoSize();
//	});
	setTimeout(function(){
		_AutoSize();
		$('#query_table').bootstrapTable('hideColumn', 'id');
	},600)
})

$("#font").bind("DOMNodeInserted",function(e){
    console.log($(e.target).html());
    setTimeout(function(){
    var nm  =sessionStorage.getItem("engineeringNm")
    query.flash(nm);
    },500)
})

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#query_table").bootstrapTable("resetView",{"height":comm_getHeight()-80});      
} 

function removeSelf(id,event){
	var obj = event.srcElement||event.target;
	var tr  = obj.parentNode.parentNode.parentNode.parentNode;
	tr.remove();
	query.del(id);
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
	 var pageNumber = $("#query_table").bootstrapTable('getOptions').pageNumber;
  	 var pageSize   = $("#query_table").bootstrapTable('getOptions').pageSize;
  	 return (pageNumber-1) * pageSize+index+1;
}
function FMT_amount(value,row){
	return Number(value).toFixed(2);
}
function FMT_date(value,row){
	return value.substring(0,10);
}
function FMT_handle(value,row){
	if(value == '2'){
		return "<font style='color:red'>移除</font>";
	}else if(value == '1'){
		return "<font style='color:black'>变更</font>";
	}
}

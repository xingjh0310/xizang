var opt={};
var query=new $.Storage_Query

$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	query.InitData(opt);
	query.InitAddEditDel(opt);
	query.InitSelect();
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	$('#query_table').bootstrapTable('hideColumn', 'id');
})

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#query_table").bootstrapTable("resetView",{"height":comm_getHeight()-80});      
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
//flag状态
function FMT_Flag(value,row) {
    var html="";
    if (row.dense==1) html="机密";
    if (row.dense==2) html="绝密";
    if (row.dense==3) html="秘密";
   return html;
}	
//数量/单位
function FMT_Unit(value,row){
   return row.outStoreNum+"/"+row.mediumUnit;
}
Date.prototype.toLocaleString = function() {
    return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + "  " + this.getHours() + ":" + this.getMinutes() + "";
};
function FMT_DATE(value,row) {
	var html="";
	var unixTimestamp = null;
	try{
		unixTimestamp = new Date(row.createDate);
		html = row.createDate.applyPattern("yyyy-MM-dd HH:mm:ss"); 
	}catch(e){
		return "";
	}
	return html;	
}
function fmt_flag(value,row){
	if(row.flags == 1 || row.flags == 2){
		return "<font style='color:green'>已入库</font>";
	}else if(row.flags == 3){
		return "<font style='color:green'>已出库</font>";
	}
}
function FMT_ellipsis(value,row){
	var html="";
	html += "<div class='autocut' style='text-align:left' title="+value+">"+value+"</div>";
	return html;
}
function number(value,row){
	var html="";
	html += "<div style='text-align:right'>"+value+"</div>";
	return html;
}
function FMT_position(value,row){
	var text = "";
	if(value == null) {
		return;
	}
	if(value <= 9){//如果小于9是1-101,如果大于9是1-110
		text = row.layerNumber+"-10"+value;
	}else{
		text = row.layerNumber+"-1"+value;
	}
	return text;
}
function FMT(value,row){
	 if (value)
	 {
	  value = value.substring(0,value.length-1)
	 }
	 return value;
	}
function FMT_file(value,row){
	return "<a href='javascript:query.uploading(1)'>"+value+"</a>";
}
function FMT_handle(value,row){
	return "<a href='javascript:query.edit(0)'>"+
    		"<button class='btn btn-xs btn-success'>"+
    		"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;图纸确认时间</div>"+
			"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>"+
			"</button></a>";
}
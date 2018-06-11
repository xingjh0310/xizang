var opt={};
var query=new $.Storage_Query

$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	//延迟500毫秒
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

function countTotalPrice(){
	var nums = $(".after-change-num");
	var prices = $(".price");
	//var differences = $(".difference");
	var differenceTaxs = $(".difference-tax");
	var preChangePrices = $(".pre-change-price");
	var afterChangePrices = $(".after-change-price");
	
	for(var i=0;i<nums.length;i++){
		nums[i].value=nums[i].value.replace(/[^\d]/g,'');
		if(nums[i].value == ""){
			afterChangePrices[i].value = Number(preChangePrices[i].value).toFixed(2);
			//differences[i].value = "";
			differenceTaxs[i].value = "";
			continue;
		}
		if(prices.length == nums.length 
			&& prices[i] != null
			&& preChangePrices.length == nums.length
			&& preChangePrices[i] != null){
			var num = Number(nums[i].value);
			var price = Number(prices[i].value);
			var preChangePrice = Number(preChangePrices[i].value);
			//计算总价
			if(afterChangePrices.length == nums.length 
				&& afterChangePrices[i] != null){
				afterChangePrices[i].value = (num * price).toFixed(2);
			}
			//计算差额
			if(differenceTaxs.length == nums.length
				&& differenceTaxs[i] != null){
				//不含税
				//differences[i].value = (num * price - preChangePrice).toFixed(2);
				//含税
				differenceTaxs[i].value = (num * price - preChangePrice).toFixed(2);
			}
		}
	}
	//合计
	var totalPrice = Number(0);
	for(var i=0;i<afterChangePrices.length;i++){
		totalPrice = totalPrice + Number(afterChangePrices[i].value);
	}
	$("#totalPrice").html(totalPrice.toFixed(2));
}

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#query_table").bootstrapTable("resetView",{"height":comm_getHeight()-80});      
} 

function removeSelf(id,event){
	var obj = event.srcElement||event.target;
	var tr  = obj.parentNode.parentNode.parentNode.parentNode;
    if(tr.localName == 'td'){
    	tr  = tr.parentNode;
    }
    tr.remove();
    
    countTotalPrice();
    
    var removeIds = $("#removeIds").val();
    $("#removeIds").val(removeIds + "," + id);
    var numTds = $(".num-td");
	for(var i=0;i<numTds.length;i++){
		numTds.eq(i).html(i+1);
	}
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

function FMT_file(value,row){
	if(value == null){
		return "<a href='javascript:query.uploading("+row.ID+")'>"+0+"</a>";
	}
	return "<a href='javascript:query.uploading("+row.ID+")'>"+value+"</a>";
}
function FMT_amount(value,row){
	return Number(value).toFixed(2);
}
//操作
function FMT_handle(value,row) {
    var html="";
    html+="<a href='#' onclick='javascript:query.edit("+value+")'>" +
		"<button class='btn btn-xs btn-primary'>" +
		"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;变更合同</div>" +
		"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
		"</button></a>" ; 
    return html;
}

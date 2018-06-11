
$(function(){
	
	var hei =document.body.clientHeight 
	$("#echa").height(hei-485)
	$("#echa1").height(hei-485)
	$("#echa2").height(hei-485)
	$("#echa3").height(hei-485)
	
	//加载物资需求年份
	loadYearMater();
	//加载物资类型
	loadMaterial();
	//物资需求统计
	
	 setTimeout(function(){
		 materialEchar();
	 },100)
	//物资到货统计图
	 setTimeout(function(){
		 arrivalEchar();
	 },100)
	/*问题处理统计图*/
	 setTimeout(function(){
		 problemEchar();
	 },200)
	/*问题处理统计图*/
	 setTimeout(function(){
		 returnEchar();
	 },200)
	
	
	//查询需求清单数量
	queryDemandPlanNub();
	//查询今日物资问题
	queryQuestionByDateNub();
	//查询待处理物资问题
	queryQuestionNub();
	//查询供货计划数量
	querySupplyNub();
	//查询到货计划数量
	queryArrivalNub();
	//查询待处理合同数量
	queryContractNub();
	//待处理通知消息
	queryMessageNub();
	//合同总金额
	queryConTotalAmount();
	//物资到货统计
	queryCountArrival();
	//物资需求下拉框改变事件
	$("#year_need").bind("change",changeMaterial)
	$("#mon_need").bind("change",changeMaterial)
	$("#mate").bind("change",changeMaterial)
	//物资到货统计图下拉框改变事件
	$("#year_arrive").bind("change",changeArrival)
	$("#mon_arrive").bind("change",changeArrival)
	$("#arrive").bind("change",changeArrival)
	//物资退库下拉框改变事件
	$("#year_ret").bind("change",changeReturn)
	$("#mon_ret").bind("change",changeReturn)
	$("#mate_ret").bind("change",changeReturn)
	
	
})
//下拉框改变事件
function changeMaterial(){
	materialEchar()
}
/*物资到货*/
function changeArrival(){
	arrivalEchar();
}
/*退库退料*/
function changeReturn(){
	returnEchar();
}
function loadYearMater(){
	var url=basePath+"plan/demand!getYear.action";
	common_ajax(null,url, function(response) {
		var yearList=response.year;
		var nowYear=response.nowYear;
		for(var i=0;i<yearList.length;i++){
			if(yearList[i]==nowYear){
				$("#year_need").append("<option value="+yearList[i].year+" selected='selected'>"+yearList[i].year+"</option>");
				$("#year_arrive").append("<option value="+yearList[i].year+" selected='selected'>"+yearList[i].year+"</option>");
				$("#year_ret").append("<option value="+yearList[i].year+" selected='selected'>"+yearList[i].year+"</option>");
			}else{
				$("#year_need").append("<option value="+yearList[i].year+">"+yearList[i].year+"</option>");
				$("#year_arrive").append("<option value="+yearList[i].year+">"+yearList[i].year+"</option>");
				$("#year_ret").append("<option value="+yearList[i].year+">"+yearList[i].year+"</option>");
				
			}
		}
	});
}
function loadMaterial(){
	
	var url=basePath+"materielBase/materielBase!getMaterialBase.action";
	common_ajax(null,url, function(response) {
		var row=response.rows;
		for(var i=0;i<row.length;i++){
				$("#mate").append("<option value="+row[i].MATERIELCODE+">"+row[i].MATERIELNAME+"</option>");
				$("#arrive").append("<option value="+row[i].MATERIELCODE+">"+row[i].MATERIELNAME+"</option>");
				$("#mate_ret").append("<option value="+row[i].MATERIELCODE+">"+row[i].MATERIELNAME+"</option>");
		}
	});
}
//切换工程
$("#font").bind("DOMNodeInserted",function(e){
    
     setTimeout(function(){
     
    //查询需求清单数量
 	queryDemandPlanNub();
 	//查询今日物资问题
 	queryQuestionByDateNub();
 	//查询待处理物资问题
 	queryQuestionNub();
 	//查询供货计划数量
 	querySupplyNub();
 	//查询到货计划数量
 	queryArrivalNub();
 	//查询待处理合同数量
 	queryContractNub();
 	//待处理通知消息
 	queryMessageNub();
    //物资到货统计
 	queryCountArrival();
 	
 	materialEchar();
 	
 	arrivalEchar();
 	
 	problemEchar();
 	
 	returnEchar();
     },200)
     
})

//查询合同总金额
function queryConTotalAmount(){
	var url=basePath+"contInfo/contInfo!queryConTotalAmount.action";
	var data={
			"contInfoFormBean.engineerNm":sessionStorage.getItem("engineeringNm")
	}
	common_ajax(data, url, function(response){
		$(".conTotalAmount_span").html("￥"+(response.contractAmount[0].CONTRACTAMOUNT==null?"0":parseFloat(response.contractAmount[0].CONTRACTAMOUNT).toLocaleString())+"万元");
	})
}

//查询待处理合同数量
function queryContractNub(){
	var url=basePath+"contInfo/contInfo!queryContractNub.action";
	var data={
			"contInfoFormBean.engineerNm":sessionStorage.getItem("engineeringNm")
	}
	common_ajax(data, url, function(response){
		$(".contractNub_span").html(response.contractNub[0].CONTRACTNUB);
	})
}

//查询到货计划数量
function queryArrivalNub(){
	var url=basePath+"plan/arrival!queryArrivalNub.action";
	var data={
			"fArrivalFormBean.mArrival.engineerCode":sessionStorage.getItem("engineeringNm")
	}
	common_ajax(data, url, function(response){
		$(".arrivalNub_span").html(response.arrivalNub);
	})
}

//供货计划数量查询
function querySupplyNub(){
	var url=basePath+"plan/supply!querySupplyNub.action";
	var data={
			"fSupplyFormBean.mSupply.engineerCode":sessionStorage.getItem("engineeringNm")
	}
	common_ajax(data, url, function(response){
		$(".supplyNub_span").html(response.supplyNub);
	})
}

//查询需求清单数量
function queryDemandPlanNub(){
	var url=basePath+"plan/demand!queryDemandPlanNub.action";
	var data={
			"fDemandFormBean.mDemand.engineerCode":sessionStorage.getItem("engineeringNm")
	}
	common_ajax(data, url, function(response){
		$(".demandPlanNub_span").html(response.demandPlanNub);
	})
}

//今日物资问题查询
function queryQuestionByDateNub(){
	var url=basePath+"question/submit!queryQuestionNub.action";
	var data={
			"fSubmitFormBean.nowDate":"yes"
		   ,"fSubmitFormBean.mSubmit.engineerCode":sessionStorage.getItem("engineeringNm")
	}
	common_ajax(data, url, function(response){
		$(".questionByDateNub_span").html(response.questionNub);
	})
}

//待处理物资问题
function queryQuestionNub(){
	var url=basePath+"question/submit!queryQuestionNub.action";
	var data={
			"fSubmitFormBean.nowDate":""
		   ,"fSubmitFormBean.mSubmit.engineerCode":sessionStorage.getItem("engineeringNm")
	}
	common_ajax(data, url, function(response){
		$(".questionNub_span").html(response.questionNub);
	})
}
//待处理消息数量
function queryMessageNub(){
	
	var url=basePath+"notice/notice!getMessageNub.action";
	var data={
			"noticeFormBean.nowDate":""
		   ,"noticeFormBean.notice.engineerCode":sessionStorage.getItem("engineeringNm")
	}
	common_ajax(data, url, function(response){
		$(".message_nub").html(response.messageNub);
	})
	
}
//物资到货
function queryCountArrival(){
	var url=basePath+"materiel/materiel!getMessageNub.action";
	var data={
			"materielFormBean.nowDate":""
		   ,"materielFormBean.deliveryInfoBean.engineerCode":sessionStorage.getItem("engineeringNm")
	}
	common_ajax(data, url, function(response){
		$(".arrivalCount_span").html(response.messageNub);
	})
}



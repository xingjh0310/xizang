<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
var type;
$(function(){
	var contractId ="<%=request.getParameter("contractId")%>";
	_table(contractId);
});

function goBack(){
	window.history.back(-1);
}

function _table(queryId){
	$("#detail_body").html("");
	var url = basePath + "contInfo/contInfo!list.action?contInfoFormBean.contInfoBean.id=" + queryId;
	common_ajax(null,url, function(response) {
		for(var key in response.rows[0]){
			//主合同信息
		   if($("#"+key+"_detail")[0]){
			   $("#"+key+"_detail").html(response.rows[0][key]);
		   }
		   //履约评价信息
		   if($("#"+key+"_evaDetail")[0]){
			   $("#"+key+"_evaDetail").val(response.rows[0][key]);
		   }
		}
		$("#CONTRACTDATE_detail").html(response.rows[0]["CONTRACTSTARTDATE"] 
		+ " 至 " + response.rows[0]["CONTRACTENDDATE"]);
		$("#SUPPLYDATE_detail").html(response.rows[0]["SUPPLYSTARTDATE"] 
		+ " 至 " + response.rows[0]["SUPPLYENDDATE"]);
		//格式化合同状态
		var contState = "";
		if(response.rows[0]["CONTRACTSTATE"] == "1"){
			contState = "未发货";
		}else if(response.rows[0]["CONTRACTSTATE"] == "2"){
			contState = "已发货";
		}
		$("#CONTRACTSTATE_detail").html(contState);
		
		if(response.rows[0]['EVASTATE'] == null){
			$("#evaluate_table").hide();
			$("#noneEvaluate").show();
		}else {
			$("#evaluate_table").show();
			$("#noneEvaluate").hide();
		}
		
		//加载合同物资
		for(var i=0;i < response.contDetails.length;i++){
			var html = "<tr>";
				html += "<td style='text-align: center;'>" + (i+1) + "</td>";
				html += "<td style='text-align: left;'>" + response.contDetails[i].MATERIELNAME + "</td>";
				html += "<td style='text-align: center;'>" + response.contDetails[i].MATERIELUNIT + "</td>";
				html += "<td style='text-align: left;'>" + (response.contDetails[i].MATERIALNORMS==null?"":response.contDetails[i].MATERIALNORMS) + "</td>";
				html += "<td style='text-align: right;'>" + response.contDetails[i].MATERIELNUM + "</td>";
				html += "<td style='text-align: right;'>" + (response.contDetails[i].PRICE==null?"":Number(response.contDetails[i].PRICE).toFixed(2)) + "</td>";
				html += "<td style='text-align: right;'>" + Number(response.contDetails[i].PROPOSALPRICE/10000).toFixed(2) + "</td>";
				html += "<td style='text-align: left;'>" + response.contDetails[i].GOODDESC + "</td>";
				html += "</tr>";
				$("#detail_body").append(html);
		}
		
		//初始化星级评价
		initDetailStars();
	});
}

//详细页面星级评价初始化
function initDetailStars(){
	var inp1=$("#MATERIALARRIVAL_evaDetail"); 
	inp1.rating('refresh',{
		stars:5,
		min: 0, 
		max: 5, 
		step: 1, 
		showClear: false
	}); 
	var inp2=$("#PRODUCTQUALITY_evaDetail"); 
	inp2.rating('refresh',{
		stars:5,
		min: 0, 
		max: 5, 
		step: 1, 
		showClear: false
	});
	var inp3=$("#FIELDSERVICE_evaDetail"); 
	inp3.rating('refresh',{
		stars:5,
		min: 0, 
		max: 5, 
		step: 1, 
		showClear: false
	});
	var inp4=$("#MATERIALOPERATION_evaDetail"); 
	inp4.rating('refresh',{
		stars:5,
		min: 0, 
		max: 5, 
		step: 1, 
		showClear: false
	});
	var inp5=$("#WARRANTYSITUATION_evaDetail"); 
	inp5.rating('refresh',{
		stars:5,
		min: 0, 
		max: 5, 
		step: 1, 
		showClear: false
	});
	var inp6=$("#EVALUATION_evaDetail"); 
	inp6.rating('refresh',{
		stars:5,
		min: 0, 
		max: 5, 
		step: 1, 
		showClear: false
	});
}
</script>
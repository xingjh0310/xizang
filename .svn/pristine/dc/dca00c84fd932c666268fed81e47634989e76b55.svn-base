/* 物资需求统计图*/
function materialEchar(){
	var globle=sessionStorage.getItem("engineeringNm");
	console.log("工程内码"+globle)
	var query_data={
			
			"fDemandFormBean.year" 			:$("#year_need").val(),		//年
			"fDemandFormBean.month" 		:$("#mon_need").val(),		//月
			"fDemandFormBean.materielType"	:$("#mate").val(),			//物资类型
			"fDemandFormBean.mDemand.engineerCode":globle,				//所属工程
	}
	
	var url_charts=basePath+"plan/demand!app_echarts.action";
	common_ajax(query_data,url_charts, function(response) {
		if(response!=null){
			
			var dayNub=response.xAxisData;
			var xAxisData=new Array();
			var axisLabel={}//设置倾斜
			var dataUnit="日";
			if(dayNub==12){
				dataUnit="月";
			}
			for(i=1;i<=dayNub;i++){
				xAxisData.push(i+dataUnit)
			}
			var array=new Array();
			array.push(response.carChartLegend);
			array.push(response.carChartSeries);
			array.push(xAxisData);
			
			loadMaterialData(array);
			
		}else{
			$("#echar").html("<center><font color='red'>统计结果无数据！！！</font></center>");
		}
	});
}
function loadMaterialData(array){	
	// 指定图表的配置项和数据
	option = {
		color:['#4ab8f7'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:array[0],
		        top:"2px;",
		        right:"20px;"
		    },
		    grid: {
		        left: '1%',
		        right: '2%',
		        bottom: '8%',
		        height:"75%",
		        containLabel: true
		    },
		    xAxis : [
		        {
		            data : array[2],		            
		           
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series :array[1]
		};
    // 使用刚指定的配置项和数据显示图表。
	try{
		var myChart = echarts.init(document.getElementById('echar'));
		myChart.setOption(option,true);
		window.onresize = myChart.resize;
	}catch(e){
		console.log("物资需求查询异常："+JSON.stringify(e));
	}
}
/********物资到货统计图**********/
function arrivalEchar(){
	var globle=sessionStorage.getItem("engineeringNm");
	var query_data={
			
			"fArrivalFormBean.year" 			:$("#year_arrive").val(),	//年
			"fArrivalFormBean.month" 			:$("#mon_arrive").val(),	//月
			"fArrivalFormBean.materielType"		:$("#arrive").val(),		//物资类型
			"fArrivalFormBean.mArrival.engineerCode":globle,				//所属工程
	}
	
	var url_charts=basePath+"plan/arrival!app_echarts.action";
	common_ajax(query_data,url_charts, function(response) {
		if(response!=null){
			
			var dayNub=response.xAxisData;
			var xAxisData=new Array();
			var axisLabel={}//设置倾斜
			var dataUnit="日";
			if(dayNub==12){
				dataUnit="月";
			}
			for(i=1;i<=dayNub;i++){
				xAxisData.push(i+dataUnit)
			}
			var array=new Array();
			array.push(response.carChartLegend);
			array.push(response.carChartSeries);
			array.push(xAxisData);
			
			loadArrivalData(array);
			
		}else{
			$("#echar1").html("<center><font color='red'>统计结果无数据！！！</font></center>");
		}
	});
}

function loadArrivalData(array){
	// 指定图表的配置项和数据
	option = {
			color:['#4ab8f7'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:array[0],
		        top:"2px;",
		        right:"20px;"
		    },
		    grid: {
		        left: '1%',
		        right: '2%',
		        bottom: '8%',
		        height:"75%",
		        containLabel: true
		    },
		    xAxis : [
		        {
		            data : array[2],
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series :array[1]
		};
    // 使用刚指定的配置项和数据显示图表。
	try{
		var myChart = echarts.init(document.getElementById('echar1'));
		myChart.setOption(option,true);
		window.onresize = myChart.resize;
	}catch(e){
		console.log("物资到货查询异常："+JSON.stringify(e));
	}
}
/*问题处理统计图*/
function problemEchar(){
	var globle=sessionStorage.getItem("engineeringNm");
	var query_data={
			
			/*"fArrivalFormBean.year" 			:$("#year_need").val(),		//年
			"fArrivalFormBean.month" 			:$("#mon_need").val(),		//月
			"fArrivalFormBean.materielType"		:$("#mate").val(),			//物资类型*/
			"fHandlerFormBean.mHandler.engineerCode":globle,				//所属工程
	}
	
	var url_charts=basePath+"question/handler!app_echarts.action";
	common_ajax(query_data,url_charts, function(response) {
		if(response!=null){
			
			var array=new Array();
			array.push(response.name);
			array.push(response.rows);
			
			loadProblemData(array);
			
		}else{
			$("#echar1").html("<center><font color='red'>统计结果无数据！！！</font></center>");
		}
	});
}
function loadProblemData(array){
	 var option = {
			 
		      title: {
		    	  title :'|    同名数量统计',
		          text: '',
		          left: 'center'
		      },
		      tooltip : {
		          trigger: 'item',
		          formatter: "{a} <br/>{b} : {c} ({d}%)"
		      },
		      legend: {
		          top: 'bottom',
		          orient: 'horizontal',
		          data: array[0]
		      },
		      series : [
		          {
		              name: '问题处理',
		              type: 'pie',
		              radius : '55%',
		              center: ['50%', '50%'],
		              selectedMode: 'single',
		              data:array[1],
		              color:['#4ab8f7','#f7a84b'],
		              itemStyle: {
		                  emphasis: {
		                      shadowBlur: 10,
		                      shadowOffsetX: 0,
		                      shadowColor: 'rgba(0, 0, 0, 0.5)'
		                  }
		              }
		          }
		      ]
		  };
	 
	// 使用刚指定的配置项和数据显示图表。
		try{
			var myChart = echarts.init(document.getElementById('echar2'));
			myChart.setOption(option,true);
			window.onresize = myChart.resize;
		}catch(e){
			console.log("问题处理查询异常："+JSON.stringify(e));
		}
	
}
//退库退料统计图
function returnEchar(){
	
	var globle=sessionStorage.getItem("engineeringNm");
	var query_data={
			
			"refundFormBean.year" 			:$("#year_ret").val(),		//年
			"refundFormBean.month" 			:$("#mon_ret").val(),		//月
			"refundFormBean.materielType"	:$("#mate_ret").val(),			//物资类型
			"refundFormBean.refund.engineerCode":globle,				//所属工程
	}
	
	var url_charts=basePath+"refund/refund!app_echarts.action";
	common_ajax(query_data,url_charts, function(response) {
		if(response!=null){
			
			var dayNub=response.xAxisData;
			var xAxisData=new Array();
			var axisLabel={}//设置倾斜
			var dataUnit="日";
			if(dayNub==12){
				dataUnit="月";
			}
			for(i=1;i<=dayNub;i++){
				xAxisData.push(i+dataUnit)
			}
			var array=new Array();
			array.push(response.carChartLegend);
			array.push(response.carChartSeries);
			array.push(xAxisData);
			
			loadReturnData(array);
			
		}else{
			$("#echar3").html("<center><font color='red'>统计结果无数据！！！</font></center>");
		}
	});
	
}	
	
function loadReturnData(array){
	
	// 指定图表的配置项和数据
	option = {
			color:['#4ab8f7'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:array[0],
		        top:"2px;",
		        right:"20px;"
		    },
		    grid: {
		        left: '1%',
		        right: '2%',
		        bottom: '8%',
		        height:"75%",
		        containLabel: true
		    },
		    xAxis : [
		        {
		            data : array[2],
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series :array[1]
		};
    // 使用刚指定的配置项和数据显示图表。
	try{
		var myChart = echarts.init(document.getElementById('echar3'));
		myChart.setOption(option,true);
		window.onresize = myChart.resize;
	}catch(e){
		console.log("退库退料查询异常："+JSON.stringify(e));
	}
	
}	



var opt={};
var monitor=new $.System_monitor();

$(function(){
	
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	monitor.InitData(opt);
	//初始化新增、编辑和删除
	monitor.InitAddEditDel();
	
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
//地图===============================================	
var map = new AMap.Map('mapContainer', {
 resizeEnable: true,
	  	zoom:11
});
AMapUI.load(['ui/misc/PathSimplifier', 'lib/$'], function(PathSimplifier, $) {

    if (!PathSimplifier.supportCanvas) {
        alert('当前环境不支持 Canvas！');
        return;
    }

    var pathSimplifierIns = new PathSimplifier({
        zIndex: 100,
        //autoSetFitView:false,
        map: map, //所属的地图实例

        getPath: function(pathData, pathIndex) {

            return pathData.path;
        },
        getHoverTitle: function(pathData, pathIndex, pointIndex) {

            if (pointIndex >= 0) {
                //point 
                return pathData.name + '，点：' + pointIndex + '/' + pathData.path.length;
            }

            return pathData.name + '，点数量' + pathData.path.length;
        },
        renderOptions: {

            renderAllPointsIfNumberBelow: 100 //绘制路线节点，如不需要可设置为-1
        }
    });

    window.pathSimplifierIns = pathSimplifierIns;
    
    function onload() {
        pathSimplifierIns.renderLater();
    }

    function onerror(e) {
        alert('图片加载失败！');
    }
    //设置数据
    pathSimplifierIns.setData([{
        name: '路线0',
        path: [
            [116.210911,39.894613],
      	  [116.322147,39.898037]     
        ]
    },
               {
        name: '路线1',
        path: [
            [116.210911,39.894613],
      	 [116.221951,39.973956]     
        ]
    }
    
    ]);
		 
    //对第一条线路（即索引 0）创建一个巡航器
    var navg0 = pathSimplifierIns.createPathNavigator(0, {
        loop: true, //循环播放
        speed: 1000, //巡航速度，单位千米/小时
         pathNavigatorStyle: {
                width: 24,
                height: 24,
                //使用图片
                content: PathSimplifier.Render.Canvas.getImageContent('./imgs/car.png', onload, onerror),
                strokeStyle: null,
                fillStyle: null,
                //经过路径的样式
                pathLinePassedStyle: {
                    lineWidth: 6,
                    strokeStyle: 'black',
                    dirArrowStyle: {
                        stepSpace: 15,
                        strokeStyle: 'red'
                    }
                }
            }
    });
    navg0.start();
    
   
    
    var navg1 = pathSimplifierIns.createPathNavigator(1, {
         loop: true, //循环播放
         speed: 1000, //巡航速度，单位千米/小时
          pathNavigatorStyle: {
                 width: 24,
                 height: 24,
                 //使用图片
                 content: PathSimplifier.Render.Canvas.getImageContent('./imgs/car.png', onload, onerror),
                 strokeStyle: null,
                 fillStyle: null,
                 //经过路径的样式
                 pathLinePassedStyle: {
                     lineWidth: 6,
                     strokeStyle: 'black',
                     dirArrowStyle: {
                         stepSpace: 15,
                         strokeStyle: 'red'
                     }
                 }
             }
     });
     navg1.start();
    
    //对第一条线路（即索引 0）创建一个巡航器
    var navg2 = pathSimplifierIns.createPathNavigator(0, {
        loop: true, //循环播放
        speed: 1000, //巡航速度，单位千米/小时
         pathNavigatorStyle: {
                width: 24,
                height: 24,
                //使用图片
                content: PathSimplifier.Render.Canvas.getImageContent('./imgs/car.png', onload, onerror),
                strokeStyle: null,
                fillStyle: null,
                //经过路径的样式
                pathLinePassedStyle: {
                    lineWidth: 6,
                    strokeStyle: 'black',
                    dirArrowStyle: {
                        stepSpace: 15,
                        strokeStyle: 'red'
                    }
                }
            }
    });
    navg2.start();
    
});
	
	
});

//调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo").bootstrapTable("resetView",{"height":comm_getHeight()-0});      
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
   //return index+1;
	 var pageNumber = $("#tbinfo").bootstrapTable('getOptions').pageNumber;
	 var pageSize =   $("#tbinfo").bootstrapTable('getOptions').pageSize;
	  return (pageNumber-1) * pageSize+index+1;
}
//操作
function FMT_Oper(value,row) {
    var html="";
    html="<a href='#' onclick='javascript:monitor.edit("+row.id+")'>" +
	"<button class='btn btn-xs btn-primary'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
	"</button></a>" +
	"&nbsp;&nbsp;" +
	"<a href='#' onclick='javascript:monitor.del("+row.id+")'>" +
	"<button class='btn btn-xs btn-danger'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-times'></i>&nbsp;删除</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-times'></i></div>" +
	"</button></a>" ; 
   return html;
}
//物资排产计划
function FMT_Plan(value,row){
	var html="";
	var accessory=value==null?0:value;
	html="<a href='#' onclick=''>"+accessory+"</a>";
	return html;
}
//物资状态
function FMT_State_Material(value,row){
	var html="";
	var accessory=value==null?0:value;
	html="<a href='#' onclick=''>"+accessory+"</a>";
	return html;
}
//交货状态
function FMT_State_Delivery(value,row){
	var html="";
	var accessory=value==null?0:value;
	html="<a href='#' onclick=''>"+accessory+"</a>";
	return html;
}
//验收状态
function FMT_State_Check(value,row){
	var html="";
	var accessory=value==null?0:value;
	html="<a href='#' onclick=''>"+accessory+"</a>";
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
	$("#tbinfo tbody tr[data-uniqueid="+rows.id+"]").addClass("success");
}
//不选中时颜色恢复
function onUncheck(rows){
	$("#tbinfo tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
}
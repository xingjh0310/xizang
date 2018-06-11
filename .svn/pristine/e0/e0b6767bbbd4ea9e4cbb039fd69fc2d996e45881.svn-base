<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script>
   //项目路径
   var basePath = '<%=basePath%>';
</script>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>派车分布</title>
    <!--CSS-->
    <link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/js/bootstrapValidator/css/bootstrapValidator.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>common/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/bootstraptable/bootstrap-table.min.css"/>
  	<link rel="stylesheet" type="text/css" href="css.css"/>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
    <link rel="stylesheet" type="text/css" href="map.css"/>
    <!--JS-->
    <script src="<%=basePath%>common/jquery/jquery-1.11.3.min.js"></script>
    <script src="<%=basePath%>common/inc/js.js"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.3&key=61c8a2f45cccec03fbf138622b9fe12d&plugin=AMap.Driving"></script>
    <script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
    <base href="//webapi.amap.com/ui/1.0/ui/misc/PathSimplifier/examples/"/>
    <!-- <script type="text/javascript" src="map.js"></script> -->
   
</head>
<body>
<div id="maincontent" class="row-fluid ">
		
		<div class="row-fluid col-md-9 pull-right" style="height: 100%">
           
			<div id="mapContainer" style="height: 100%"></div>
        </div>   
        
        <div class="input-group ">
			<input type="text" id="searchInput" autofocus="autofocus" class="form-control" placeholder="输入车牌号/物料编号/运输编号"> 
			<span class="input-group-btn">
					<button class="btn btn-success" id="btn_ref" type="button">
						查询
					</button>
			</span>
		</div> 
        
        <div class="row-fluid col-md-3">
        	<table id="tbinfo" class="table-condensed table-hover table-cursor" >
				<!-- <thead>
					<tr>
                        <th data-halign="center" data-align="center" data-sortable="false" data-width="50"  data-formatter="FMT_Num">  序号</th>
                        <th data-halign="center" data-align="left" data-sortable="false"  data-width="" data-field="a">采购编号</th>
                        <th data-halign="center" data-align="left" data-sortable="false"  data-width=""  data-field="b" >出发地址</th> 
                        <th data-halign="center" data-align="center" data-sortable="false"  data-width="50" data-field="c" >目地地址</th>
                        <th data-halign="center" data-align="left" data-sortable="false"  data-width=""  data-field="d">  承运人</th>
                        <th data-halign="center" data-align="left" data-sortable="false"  data-width=""  data-field="e">承运人电话</th>
					</tr>
				</thead> -->
			</table>
        	
        </div>
        
	  </div>
<script type="text/javascript">
//项目路径
var basePath ='<%=basePath%>';
    //初始化地图对象，加载地图
    ////初始化加载地图时，若center及level属性缺省，地图默认显示用户当前城市范围
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
      
  
  });
  AMapUI.loadUI(['misc/MarkerList'], function(MarkerList) {

      var markerList = new MarkerList({
          //关联的map对象
          map: map,

          //列表的dom容器的id
         

          //选中状态（通过点击列表或者marker）时在Marker和列表节点上添加的class，可以借此编写css控制选中时的展示效果
          selectedClassNames: 'my-active',

          //返回数据项的Id
          getDataId: function(dataItem, index) {
              //index表示该数据项在数组中的索引位置，从0开始，如果确实没有id，可以返回index代替
              return dataItem.id;
          },
          //返回数据项的位置信息，需要是AMap.LngLat实例，或者是经纬度数组，比如[116.789806, 39.904989]
          getPosition: function(dataItem) {
              return dataItem.position;
          },
          //返回数据项对应的Marker
          getMarker: function(dataItem, context, recycledMarker) {

              //marker的标注内容
              var content = '标注: ' + (context.index + 1) + '';

              var label = {
                  offset: new AMap.Pixel(16, 18), //修改label相对于marker的位置
                  content: content
              };

              //存在可回收利用的marker
              if (recycledMarker) {
                  //直接更新内容返回
                  recycledMarker.setLabel(label);
                  return recycledMarker;
              }

              //返回一个新的Marker
              return new AMap.Marker({
                  label: label
              });
          },
          //返回数据项对应的infoWindow
          getInfoWindow: function(dataItem, context, recycledInfoWindow) {

              var tpl = '<p>'+dataItem.id+':'+dataItem.desc+'<p>';

              //MarkerList.utils.template支持underscore语法的模板
              var content = MarkerList.utils.template(tpl, {
                  dataItem: dataItem,
                  dataIndex: context.index
              });

              if (recycledInfoWindow) {
                  //存在可回收利用的infoWindow, 直接更新内容返回
                  recycledInfoWindow.setContent(content);
                  return recycledInfoWindow;
              }

              //返回一个新的InfoWindow
              return new AMap.InfoWindow({
                  offset: new AMap.Pixel(0, -23),
                  content: content
              });
          },
          //返回数据项对应的列表节点
          getListElement: function(dataItem, context, recycledListElement) {

              var tpl = '<p>'+dataItem.id+':'+dataItem.desc+'<p>';

              var content = MarkerList.utils.template(tpl, {
                  dataItem: dataItem,
                  dataIndex: context.index
              });

              if (recycledListElement) {
                  //存在可回收利用的listElement, 直接更新内容返回
                  recycledListElement.innerHTML = content;
                  return recycledListElement;
              }

              //返回一段html，MarkerList将利用此html构建一个新的dom节点
              return '<li>' + content + '</li>';
          }

      });

      //监听选中改变
      markerList.on('selectedChanged', function(event, info) {
          //console.log(event, info);
      });

      //构建一个数据项数组，数据项本身没有格式要求，但需要支持下述的getDataId和getPosition
         var data = [{
            id: 'A',
            position: [116.210911,39.894613],
            desc: '石景山路'
        }, {
            id: 'B',
            position: [116.322147,39.898037],
            desc: '北京西站'
        }];

      //展示该数据
      markerList.render(data);
  });
 
</script>
<script src="monitor_def.js"></script> 
</body>
</html>

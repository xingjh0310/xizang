<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>通知提醒</title>
<%@include file="../../../../common/inc/inc.inc"%>
<%@include file="../../../../common/inc/bootstrapTable.inc"%>
<link rel="stylesheet" href="<%=basePath %>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=basePath %>common/js/ztree/js/jquery.ztree.all.js"></script>
 <!-- 配置文件 -->
 <script type="text/javascript" src="../../common/UEditor/ueditor.config.js"></script>
 <!-- 编辑器源码文件 -->
 <script type="text/javascript" src="../../common/UEditor/ueditor.all.js"></script>
 <link rel="stylesheet" type="text/css" href="css.css">
 <style>
     html, body{
       overflow:hidden;
     } 
</style>
 </style>
</head>

<body>
	<div class="container-fluid" >
		<%@include file="../../../../common/inc/top.jsp"%>
		<div class="row-fluid top-title">
			<h3 class="text-primary">
				<ol class="breadcrumb" >
					<li>消息</li>
                    <li class="content_top_title_black">通知提醒 <span id="mestit">（新增通知）</span></li>
				</ol>
			</h3>
		</div>
		<hr>

	<div id="maincontent" class="row-fluid top-body">
		<div class="col-md-12">
			<div class="col-md-1"></div>
			<form class="form-horizontal" id="notice_inform" >
			<div style="display:none;">
				id<input type="text" class="form-control" id="id" name="noticeFormBean.notice.id" value="<s:property value="noticeFormBean.notice.id"/> ">
				eng<input type="text" class="form-control" id="engineerCode" name="noticeFormBean.notice.engineerCode" value="<s:property value="noticeFormBean.notice.id"/> ">
			</div>
				<div class="col-md-10" >
				
					<div class="form-group">
						<label class="col-md-1 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 标题：
						</label>
						<div class="col-md-11 rowGroup">
		               			<input type="text" class="form-control" id="title"
                                   name="noticeFormBean.notice.title" 
                                   value="<s:property value='noticeFormBean.notice.title' />" 
                                   data-bv-group=".rowGroup" 
                                   required  
                                   maxlength="20"   
                                   data-bv-notempty-message="标题不能为空"   
                                   data-bv-stringlength-max="20" data-bv-stringlength-message="字数不能超过20"
                                placeholder="请输入标题"
                            >
						</div>
						
				
				</div>
				<div class="form-group">
						<label class="col-md-1 control-label"> 
                              <span class="text-danger">*&nbsp;</span>分类：
						</label>
						<div class="col-md-5 rowGroup">
		               			<select id="classify" name="noticeFormBean.notice.classify" class="form-control">
								
							</select>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 接收部门：
						</label>
						<div class="col-md-4 control-label">
							<input type="hidden" id="receiveDepts" data-bv-group=".rowGroup"  required name="noticeFormBean.notice.receiveDept" />
							<input type="text" class="form-control" id="receiveDept" 
	                            required readonly="readonly" 
	                            placeholder="选择部门">
						</div>
				</div>
				
				<div class="form-group sty-ms" >
						<label class="col-md-1 control-label"> 
                              <span class="text-danger">*&nbsp;</span>内容：
						</label>
					<div class="col-md-11 rowGroup"> 
                	    <script id="content" name="noticeFormBean.notice.content" type="text/plain" style="z-index: 999;">
    					</script>
					   
					    <!-- 实例化编辑器 -->
					    <script type="text/javascript">
					    var ue = UE.getEditor('content',{
					       initialFrameHeight :270,//高度
					       autoHeightEnabled: false
					    });
					  
					    </script>
					</div>
                  </div>
                
                  <div class="form-group" style="margin-top: 10px;">
                	<label class="col-md-10"></label>

					<button type="submit" id="notice_save" class="btn btn-primary" style="margin-left: 10px;">
						<i class="icon icon-download-alt ion-left"></i> 保存
					</button>
					
					<button type="button" id="notice_back" class="btn btn-large " style="margin-left: 10px;">
						<i class="icon icon-signout ion-left"></i> 返回
					</button>
                  
                  </div>
                  
                  
				</div>
				</form>
			<div class="col-md-1"></div>
		</div>
				
		</div>
	</div>
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="tree.jsp"%>
<script src="def.js"></script>
<script src="list.js"></script>

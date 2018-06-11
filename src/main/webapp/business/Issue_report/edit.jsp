<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
 .form-group{
	margin-bottom:5px;
} 
</style>
<form id="info_form" name="info_form" class="form-horizontal" 
	autocomplete="off" method="post" 
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog">
		<div class="modal-dialog modal-lg" style="width:680px">
			<div class="modal-content">
				
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close modal_header_top" data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title modal_header_top">新增问题信息</h4>
				</div>

					<div class="modal-body">
					<div style="display: none;">
						id<input type="text" class="form-control" id="ID" name="fSubmitFormBean.mSubmit.id">
						state<input type="text" class="form-control" id="STATE" name="fSubmitFormBean.mSubmit.state">
						engineerCode<input type="text" class="form-control" id="ENGINEERCODE" name="fSubmitFormBean.mSubmit.engineerCode">
						FILENM<input type="text" class="form-control" id="FILENM" name="fSubmitFormBean.mSubmit.fileNm">
					</div>
                    
                    <div  class="form-group" id="title_div">
                    	<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：
						</label>
                        <div class="col-md-10">
                        	<input type="text" class="form-control" id="TITLE" 
                                   name="fSubmitFormBean.mSubmit.title" 
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
					
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 物料名称：
						</label>
						<div class="col-md-4 rowGroup">
							<select id="MATERIELCODE" name="fSubmitFormBean.mSubmit.materielCode" class="form-control" 
								data-bv-group=".rowGroup" 
								required 
								data-bv-notempty-message="请选择物资类型">
								<option value="">请选择</option>
							</select>
							<input type="hidden" id="materielName" name="fSubmitFormBean.materielName">
						</div>
					
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 问题类型：
						</label>
						<div class="col-md-4 rowGroup">
							<select id="PROBLEMTYPE" name="fSubmitFormBean.mSubmit.problemType" class="form-control" 
								data-bv-group=".rowGroup" 
								required 
								data-bv-notempty-message="请选择问题类型">
								<option value="">请选择</option>
							</select>
							<input type="hidden" id="queryName" name="fSubmitFormBean.queryName">
						</div>
						
					</div>
					
					 <div class="form-group">
					 	<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 上报单位：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="DEPTNAME"
                                   data-bv-group=".rowGroup"  readonly="readonly"
                            >
                            <input type="hidden" class="form-control" id="TREENMSYSDEPT" name="fSubmitFormBean.mSubmit.treenmSysDept" >
						</div>
					 
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 上&nbsp;&nbsp;报&nbsp;人：
						</label>
						
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="STAFFNAME"
                                   data-bv-group=".rowGroup" readonly="readonly"
                            >
                            <input type="hidden" class="form-control" id="REPORTPERSON" name="fSubmitFormBean.mSubmit.reportPerson">
						</div>
                    </div>
					
					<div class="form-group">
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 上报时间：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date'  style="float: left;margin-top:0px">
							<label class="input" style="display:inline;">
               				<input type="text" class="form-control laydate" style="width: 163px; height: 32px;"
               					   id="REPORTTIME" 
               					   required
               					   readonly="readonly"
               					   data-bv-group=".rowGroup"
                                   name="fSubmitFormBean.mSubmit.reportTime" 
               					   placeholder="请选择上报时间 "/>
                			<span class="input-group-addon" style="float: left; width: 39px; height: 32px;">
                 				<span class="icon-calendar"></span>
             				</span>
             				</label>
             				</a>
						</div>
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 处理期限：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date'  style="float: left;margin-top:0px">
							<label class="input" style="display:inline;">
               				<input type='text' class="form-control laydate" style="width: 163px; height: 32px;"
               					   id="PROCESSLIMIT" 
               					   required
               					   data-bv-group=".rowGroup"
                                   name="fSubmitFormBean.mSubmit.processLimit" 
               					   placeholder="请选择处理期限 "/>
                			<span class="input-group-addon" style="float: left; width: 39px; height: 32px;">
                 				<span class="icon-calendar"></span>
             				</span>
             				</label>
             				</a>
						</div>
						
					</div>
                  <div class="form-group" >
                	    <label class="col-md-2 control-label" style="margin: 12px auto;"><span class="text-danger"> &nbsp;</span>问题描述：</label>
                	    <div class="col-md-10">
                	          <textarea id="PROBLEMDESC" name="fSubmitFormBean.mSubmit.problemDesc" maxlength="300" rows='2' class='form-control' placeholder="请输入问题描述，字数限制300！"></textarea>
                	    </div>
                  </div>  
				 
				 <div class="form-group">
                	    <label class="col-md-2 control-label" style="margin: 18px auto;"><span class="text-danger"> &nbsp;</span>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                	    <div class="col-md-10">
                	          <textarea id="REMARK" name="fSubmitFormBean.mSubmit.remark" maxlength="500" rows='3' class='form-control' placeholder="请输入问题描述，字数限制500！"></textarea>
                	    </div>
                  </div>
                  
                  <div class="form-group info_table_css">
                  		<label class="col-md-2 control-label" style="margin: 18px auto;"><span class="text-danger"> &nbsp;</span>问题照片：</label>
                  		<div class="col-md-10">
                  			<div id='fileUploader' class="uploader">
							  	<div class="uploader-message text-center">
								    <div class="content"></div>
								    <button type="button" class="close">×</button>
							  	</div>
							  <div class="uploader-files file-list file-list-lg" data-drag-placeholder="请拖拽文件到此处"></div>
							  <div class="uploader-actions">
									<div class="uploader-status pull-right text-muted"></div>
									<button type="button" class="btn btn-link uploader-btn-browse"><i class="icon icon-plus"></i> 选择文件</button>
									<button type="button" class="btn btn-link uploader-btn-start"><i class="icon icon-cloud-upload"></i> 开始导入</button>
							  </div>
							</div>
                  		</div>
                  </div>
                  	
				</div>
				<div class="modal-footer" style="height: 40px;">
					<button type="submit" id="btn_save" class="btn btn-primary" style="margin-top: -14px">
						<i class="icon icon-download-alt"></i> 保存
					</button>

					<button type="button" class="btn btn-large" data-dismiss="modal" style="margin-top: -14px;margin-right:-5px">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>

</form>




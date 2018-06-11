<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
	.rating-xs {
	    font-size: 1.5em;
	}
</style>
<form id="evaluation_form" name="evaluation_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	<div class="modal fade" id="evaluation_dialog">
		<div class="modal-dialog modal-lg" style="width: 770px">
			<div class="modal-content">

				<div class="modal-header"
					style="background-color: #FCFCFC; height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;">履约评价</h4>
				</div>
				<div class="modal-body" style="padding-top: 5px;">
					<div style="display: none;">
						ID<input type="text" class="form-control" id="ID_evaluate"
							name="contEvaluateFormBean.contEvaluateBean.id">
						ENGINEERCODE<input type="text" class="form-control" id="ENGINEERCODE_evaluate"
							name="contEvaluateFormBean.contEvaluateBean.engineerCode">
						CONTRACTNO<input type="text" class="form-control" id="CONTRACTNO_evaluate"
							name="contEvaluateFormBean.contEvaluateBean.contractNo">
					</div>
					<div class="form-group" style="margin-top: 7px;">
						<label class="col-md-2 control-label"> 
                           	<span class="text-danger">*&nbsp;</span>合&nbsp;&nbsp;同&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;题：
						</label>
						<div class="col-md-10 rowGroup">
                            <input type="text" class="form-control" readonly="readonly" id="CONTRACTTITLE_evaluate" value="">
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px">
						<label class="col-md-2 control-label"> 
                           	<span class="text-danger">*&nbsp;</span> 物资到货评分：
						</label>
						<div class="col-md-4">
							<input id="MATERIALARRIVAL_evaluate" name="contEvaluateFormBean.contEvaluateBean.materialArrival" type="number" class="rating" min=0 max=5 step=1 data-size="xs" >
						</div>
						<label class="col-md-2 control-label"> 
                           	<span class="text-danger">*&nbsp;</span> 产品质量评分：
						</label>
						<div class="col-md-4">
							<input id="PRODUCTQUALITY_evaluate" name="contEvaluateFormBean.contEvaluateBean.productQuality" type="number" class="rating" min=0 max=5 step=1 data-size="xs" >
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                           	<span class="text-danger">*&nbsp;</span> 现场服务评分：
						</label>
						<div class="col-md-4">
							<input id="FIELDSERVICE_evaluate" name="contEvaluateFormBean.contEvaluateBean.fieldService" type="number" class="rating" min=0 max=5 step=1 data-size="xs" >
						</div>
						<label class="col-md-2 control-label"> 
                           	<span class="text-danger">*&nbsp;</span> 物资投运评分：
						</label>
						<div class="col-md-4">
							<input id="MATERIALOPERATION_evaluate" name="contEvaluateFormBean.contEvaluateBean.materialOperation" type="number" class="rating" min=0 max=5 step=1 data-size="xs" >
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                           	<span class="text-danger">*&nbsp;</span> 质保情况评分：
						</label>
						<div class="col-md-4">
							<input id="WARRANTYSITUATION_evaluate" name="contEvaluateFormBean.contEvaluateBean.warrantySituation" type="number" class="rating" min=0 max=5 step=1 data-size="xs" >
						</div>
						<label class="col-md-2 control-label"> 
                           	<span class="text-danger">*&nbsp;</span> 总&nbsp;&nbsp;体&nbsp;&nbsp;评&nbsp;&nbsp;&nbsp;价：
						</label>
						<div class="col-md-4">
							<input id="EVALUATION_evaluate" name="contEvaluateFormBean.contEvaluateBean.evaluation" type="number" class="rating" min=0 max=5 step=1 data-size="xs" >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label" style="margin-top: 20px">
							备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
						<div class="col-md-10">
							<textarea rows='3' id="REMARK_evaluate" maxlength="250" name="contEvaluateFormBean.contEvaluateBean.remark" class='form-control' placeholder="请输入备注，字数限制250！"></textarea>
						</div>
					</div>
<!-- 					<div class="form-group" style="margin-top: 10px;"> -->
<!-- 						<label class="col-md-2 control-label"> -->
<!-- 							 附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件： -->
<!-- 						</label> -->
<!-- 						<div class="col-md-10 rowGroup"> -->
<!--                             <input type="file" class="form-control"/> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
				<div class="modal-footer"
					style="height: 40px; background-color: #FCFCFC;">
					<button type="submit" id="btn_save_evaluate" class="btn btn-success"
						style="margin-top: -14px">
						<i class="icon icon-save"></i> 保存
					</button>
					<button type="button" class="btn btn-large btn-primary"
						data-dismiss="modal"
						style="margin-top: -14px; margin-right: -12px">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			</div>
		</div>
	</div>

</form>




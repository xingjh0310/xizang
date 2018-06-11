<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="info_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog">
		<div class="modal-dialog modal-lg" style="width:670px">
			<div class="modal-content">
				
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;"><i class="icon icon-home">查看消息</i></h4>
				</div>

					<div class="modal-body-check" style="padding:10px;">
					<div style="display: none;">
						id<input type="text" class="form-control" id="id" name="noticeFormBean.notice.id">
						nextTime<input type="text" class="form-control" id="nextTime" name="noticeFormBean.notice.nextTime">
					</div>
					<table class=" table-bordered ">
						<tbody>
							<tr>
								<td class="td-label"><span class="tr-span">标题:</span></td>
								<td ><span class="td-span" id="title_n"></span></td>
								
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">所属分类:</span></td>
								<td ><span class="td-span" id="classifyName_n"></span></td>
								
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">发布部门:</span></td>
								<td ><span class="td-span" id="releaseDept_n"></span></td>
								
							</tr>
							<tr>
								<td class="td-label"><span class="tr-span">内容:</span></td>
								<td ><div id="ht" style="width: 545px;height:100%;min-height:200px;margin-left: 10px;">
                	          
                	    			</div>
                	    		</td>
							</tr>
						</tbody>
					
					</table>
				</div>
				<div class="modal-footer foter" >

					<button type="button" class="btn btn-large bottom-btn" data-dismiss="modal">
						<i class="icon icon-times "></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>

</form>




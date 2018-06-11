<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="tree_di">
<div class="modal fade" id="tree_dialog">
	<div class="modal-dialog modal-lg" style="width: 400px;">
		<div class="modal-content">
			<div class="modal-header"
				style="background-color: #FCFCFC; height: 40px">
				<button type="button" class="btn btn-link close"
					data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title" style="line-height: 15px;">请选择部门</h4>
			</div>

			<div class="modal-body" style="padding-top: 5px;">
				<div id="zTreeDiv" class="zTreeDiv">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>
			<div class="modal-footer"
				style="height: 40px; background-color: #FCFCFC;">
				<button type="button" class="btn btn-success" id="check_all" style="margin-top: -14px; ">
					<i class="icon icon-checked"></i> 全部选中
				</button>
				<button type="button" class="btn btn-success" id="cancel_all" style="margin-top: -14px; ">
					<i class="icon icon-save"></i> 全部取消
				</button>
				<button type="button" class="btn btn-success" data-dismiss="modal"
					style="margin-top: -14px; margin-right: -12px">
					<i class="icon icon-save"></i> 确定
				</button>
			</div>
		</div>
	</div>
</div>
</form>

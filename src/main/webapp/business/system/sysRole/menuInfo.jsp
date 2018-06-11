<%@ page language="java" pageEncoding="UTF-8"%>
<style>
    #setid{
      float:left;
    }
   #sassid{
    
   width: 898px;
   
    margin-left: -22px;
    height: 45px;
    background: white;
   }
   #footid{
    background-color: white;
   
   }
   #footid{
     padding:0px;
   }
   #gropl{
    position: absolute;
    width: 100%;
    bottom: -56px;
    height: 57px;
    background:#e0f2f3;
    border-top: 1px solid #e5e5e5;
   }
   #btn_staff_save{
   margin-top:12px;   
   }
   #areas{
    margin-right:22px;
    margin-top:12px;
   }
</style>
	<div class="modal fade" id="set_menu_dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">信息维护-用户管理</h4>
				</div>
				
				<input type="hidden" id="roleNm" name="roleNm"/>
				<input type="hidden" id="mark" name="mark"/>
				<div class="modal-body">
					<div id="maincontent" class="row-fluid">
						<div class="row-fluid col-md-12">
							<!-- 按钮工具条开始 -->
							<div id="tbar" class="btn-toolbar">
									<div class="btn-group col-lg-2 col-md-2 col-sm-4 col-xs-6">
										<div class="input-group ">
											<input type="text" class="form-control" id="S_SysRole_Name"
												readonly placeholder="请先选中角色，然后再授权">
										</div>
									</div>
			
									<div class="btn-group">
										<button type="button" id="btn_sq_all" class="btn btn-primary">
											<div class="visible-md visible-lg">
												<i class="icon icon-check"></i>&nbsp;授权全部
											</div>
											<div class="visible-xs visible-sm">
												<i class="icon icon-check"></i>
											</div>
										</button>
									</div>
			
									<div class="btn-group">
										<button type="button" id="btn_qx_all" class="btn btn-primary">
											<div class="visible-md visible-lg">
												<i class="icon icon-times"></i>&nbsp;取消全部
											</div>
											<div class="visible-xs visible-sm">
												<i class="icon icon-times"></i>
											</div>
										</button>
									</div>
									
								<!-- <div
									class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
									<select id="select_root_SysMenu" class="form-control">
										<option value="">显示全部数据</option>
									</select>
								</div> -->
			
							</div>
							<!-- 按钮工具条结束 -->
							<br>
							<div class="row-fluid col-md-12">
								<table id="tbinfo_TreenmSysMenu" class="table-hover">
								</table>
							</div>
	
						</div>
					</div>
				</div>
				<div class="modal-footer" id="footid">
				<div id="gropl"> 
					<button type="button" class="btn" id="areas" data-dismiss="modal">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>	
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
  //获取session中的数据
  var supadmin="${supadmin}"
  var admins="${admins}"; 
  var audmins="${audmins}"
</script>
<script src="<%=basePath%>business/system/sysRole_sysMenu/def_q.js"></script>
<script src="<%=basePath%>business/system/sysRole_sysMenu/menu_def_q.js"></script>
<script src="<%=basePath%>business/system/sysRole_sysMenu/def.js"></script>
<script src="<%=basePath%>business/system/sysRole_sysMenu/list.js"></script>
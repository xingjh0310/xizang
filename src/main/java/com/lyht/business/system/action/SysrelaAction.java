package com.lyht.business.system.action;

import java.util.Hashtable;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.formBean.SysRelaFormBean;
import com.lyht.business.system.service.SysRelaService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONObject;

@Namespace("/system")
@Controller
@Scope("prototype")
public class SysrelaAction extends BaseLyhtAction {
	
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("SysroleSysmenuAction");
	
	// struts2映射对象，参数增加到formBean中
	private SysRelaFormBean formBean = new SysRelaFormBean();
	// 注解注入控制层
	@Resource
	private SysRelaService sysRelaService;
	
	/**
	 * ajax  查询关系信息，调用此方法 /lyht/sysrela!rela.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public String rela() {
		log.info("SysroleSysmenuAction=rela:角色功能关系");
		// 获取前端参数
		Hashtable hashtable = new Hashtable();
		String type=this.getRequest().getParameter("type"); //角色授权菜单标识
		String type_=this.getRequest().getParameter("type_"); //人员授权角色标识
		try {
				PageResults prs = sysRelaService.s_findAll(formBean,type,type_);
				hashtable.put(Constants.AJAX_RETFLAG, Constants.AJAX_RETFLAG_SUCCESS);
				hashtable.put(Constants.AJAX_MESSAGE, "获取数据成功！");
				hashtable.put("total", prs.getTotalCount());
				hashtable.put("rows", prs.getResults());
		} catch (Exception e) {
			hashtable.put(Constants.AJAX_RETFLAG, Constants.AJAX_RETFLAG_ERROR);
			hashtable.put(Constants.AJAX_MESSAGE, Constants.ERROR_SERVICE_MSG + "调用角色功能关系！");
			log.error("========角色功能关系SysroleSysmenuAction-rela出错:", e);
		} finally {
			// 写入当前操作 成功状态 success 或 error
			JSONObject json = new JSONObject().fromObject(hashtable);
			CommonFunction.writeResponse(getResponse(), json.toString());
		}

		// 写到前端
		JSONObject json = new JSONObject().fromObject(hashtable);
		CommonFunction.writeResponse(getResponse(), json.toString());

		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public String relasq(){
		log.info("SysroleSysmenuAction=rela:角色功能关系");
		Hashtable hashtable = new Hashtable();
		try {
				sysRelaService.relasq_(formBean);
				hashtable.put(Constants.AJAX_RETFLAG, Constants.AJAX_RETFLAG_SUCCESS);
				hashtable.put(Constants.AJAX_MESSAGE, "设置数据成功！！！");
		} catch (Exception e) {
			hashtable.put(Constants.AJAX_RETFLAG, Constants.AJAX_RETFLAG_ERROR);
			hashtable.put(Constants.AJAX_MESSAGE, Constants.ERROR_SERVICE_MSG + "调用角色功能关系！");
			log.error("========角色功能关系SysroleSysmenuAction-rela出错:", e);
		} 
		
		// 写入当前操作 成功状态 success 或 error
		JSONObject json = new JSONObject().fromObject(hashtable);
		CommonFunction.writeResponse(getResponse(), json.toString());
		
		return null;
	}

	
	public SysRelaFormBean getFormBean() {
		return formBean;
	}

	public void setFormBean(SysRelaFormBean formBean) {
		this.formBean = formBean;
	}
	
	

}

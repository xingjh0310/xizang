package com.lyht.business.system.action;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.control.SysRoleControl;
import com.lyht.business.system.formBean.SysRoleFormBean;
import com.lyht.business.system.service.SysRoleService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/system")
@Results({ @Result(name = "list", location = "/business/system/sysRole/list.jsp"),
		@Result(name = "edit", location = "/business/system/sysRole/edit.jsp") })
@Controller
@Scope("prototype")
// 支持多例
public class SysroleAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("SysroleAction");
	// struts2映射对象，参数增加到formBean中
	private SysRoleFormBean formBean = new SysRoleFormBean();
	// 注解注入控制层
	@Resource 
	SysRoleControl ctl;
	@Resource 
	private SysRoleService sysRoleService;

	/**
	 * ajax 加载 单个信息加载页面，调用此方法 /lyht/sysRole!edit.do
	 */
	@SuppressWarnings({  "unchecked", "rawtypes" })
	public String edit() {
		log.info("查看对象==SysroleAction.edit");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		SysRole info = new SysRole();
		ret=ctl.view(formBean.getInfoBean().getId(),info);
		hashtable.put("infoBean", info);
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	/**
	 * ajax  单个信息保存，调用此方法 /lyht/sysRole!save.do
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String save() {
		log.info("保存对象==SysroleAction.save");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysRole info = new SysRole();
		if (formBean.getInfoBean().getId() == 0) {
			ret=ctl.create(formBean.getInfoBean(),info);
		} else {
			info= ctl.getByid(formBean.getInfoBean().getId());
			ret=ctl.update(formBean.getInfoBean(),info);
		}
		hashtable.put("infoBean", info);
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());	
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}

	/**
	 * ajax  查询列表信息，调用此方法 /lyht/sysRole!find_All.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String list() {
		log.info("查询列表==SysroleAction.list");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		ret= ctl.list(formBean, prs);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			hashtable.put("total", 0);
			hashtable.put("rows", jsonData);			
		} else {
			List<Map> list=sysRoleService.getStaffNameByRoleNm(prs.getResults());
			hashtable.put("total", prs.getTotalCount());
			hashtable.put("rows", list);	
		}
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}

	/**
	 * ajax  批量删除信息，调用此方法 /lyht/sysRole!removeids.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String removeids() {
		log.info("批量删除==SysroleAction.removeids");
		// 获取ids
		String ids = CommonUtil.trim(formBean.getIds());
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		prs=ctl.list_ByIds(ids);
		List<Map> list=prs.getResults();
		sysRoleService.deleteRefTableByRoleNm(list);
		ret= ctl.delByIds(ids, list);
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;

	}
	
	/**
	 * ajax  批量审核信息，调用此方法 /lyht/sysRole!flag.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String flag() {
		log.info("批量审核==SysroleAction.flag");
		// 获取ids
		String ids = CommonUtil.trim(formBean.getIds());
		int flag_new=formBean.getInfoBean().getFlag();
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取审核的数据
		PageResults prs=new PageResults(); 
		prs=ctl.list_ByIds(ids);
		//审核
		ret= ctl.flag(ids, prs.getResults(), flag_new);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	public SysRoleFormBean getFormBean() {
		return formBean;
	}

	public void setFormBean(SysRoleFormBean formBean) {
		this.formBean = formBean;
	}

}
package com.lyht.business.system.action;

import java.util.Hashtable;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.control.SysMenuControl;
import com.lyht.business.system.formBean.SysMenuFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/system")
@Results({ @Result(name = "list", location = "/business/system/sysMenu/list.jsp"),
		@Result(name = "edit", location = "/business/system/sysMenu/edit.jsp") })
@Controller
@Scope("prototype")
// 支持多例
public class SysmenuAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("SysMenuAction");
	private SysMenuFormBean formBean = new SysMenuFormBean();
	
	@Resource 
	private SysMenuControl sysMenuControl;
	
	/**
	 * ajax 增加信息加载页面，调用此方法 /lyht/sysdept!add.do
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String add() {
		log.info("SysMenuAction=add:新增子信息加载页面");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysMenu info = new SysMenu();
		SysMenu pinfo= new SysMenu();
		ret=sysMenuControl.add(formBean.getInfoBean().getId(),info,pinfo);
		hashtable.put("infoBean", info);
		hashtable.put("pinfoBean", pinfo);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	
	/**
	 * ajax 加载 单个信息加载页面，调用此方法 /lyht/sysdept!edit.do
	 */
	@SuppressWarnings({  "unchecked", "rawtypes" })
	public String edit() {
		log.info("查看对象==SysdeptAction.edit");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysMenu info = new SysMenu();
		SysMenu pinfo= new SysMenu();
		ret=sysMenuControl.view(formBean.getInfoBean().getId(),info,pinfo);
		hashtable.put("infoBean", info);
		hashtable.put("pinfoBean", pinfo);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}

	/**
	 * ajax  单个信息保存，调用此方法 /lyht/sysdept!save.do
	 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String save() {
		log.info("保存对象==SysdeptAction.save");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysMenu info = new SysMenu();
		if (formBean.getInfoBean().getId() == 0) {
			ret=sysMenuControl.create(formBean.getInfoBean(),info);
			
		} else {
			//获取旧数据
			info= sysMenuControl.getByid(formBean.getInfoBean().getId());
			ret=sysMenuControl.update(formBean.getInfoBean(),info);
		}
		hashtable.put("infoBean", info);
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}

	/**
	 * ajax  查询列表信息，调用此方法 /lyht/sysdept!list.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String list() {
		log.info("SysMenuAction=list:查询树状信息");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		PageResults prs=new PageResults(); 
		
		SysAcct sysacct = (SysAcct) getSession().getAttribute(Constants.SESSION_ACCT);
		
		ret= sysMenuControl.list(formBean, prs,sysacct);
		
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			hashtable.put("total", 0);
			hashtable.put("rows", jsonData);			
		} else {
			hashtable.put("total", prs.getTotalCount());
			hashtable.put("rows", prs.getResults());			
		}
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	
	/**
	 * ajax  查询列表信息，调用此方法 /lyht/sysdept!listroot.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String listroot() {
		log.info("SysMenuAction=listroot:查询树状根节点信息");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		PageResults prs=new PageResults(); 
		ret= sysMenuControl.list_root(formBean, prs);
		
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			hashtable.put("total", 0);
			hashtable.put("rows", jsonData);			
		} else {
			hashtable.put("total", prs.getTotalCount());
			hashtable.put("rows", prs.getResults());			
		}
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}

	/**
	 * ajax  批量删除信息，调用此方法 /lyht/sysdept!removeids.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String removeids() {
		log.info("SysMenuAction:根据IDS删除多个数据");
		// 获取ids
		String ids = CommonUtil.trim(formBean.getIds());
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		prs=sysMenuControl.list_ByIds(ids);
		
		ret= sysMenuControl.delByIds(ids, prs.getResults());

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;

	}

	public SysMenuFormBean getFormBean() {
		return formBean;
	}

	public void setFormBean(SysMenuFormBean formBean) {
		this.formBean = formBean;
	}

}
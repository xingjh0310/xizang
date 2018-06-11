package com.lyht.business.system.action;

import java.util.Hashtable;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.control.SysAcctControl;
import com.lyht.business.system.formBean.SysAcctFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/system")
@Results({ @Result(name = "list", location = "/business/system/sysIcon/list.jsp"),
		@Result(name = "edit", location = "/business/system/sysIcon/edit.jsp") })
@Controller
@Scope("prototype")
// 支持多例
public class SysacctAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("SysAcctAction");
	// struts2映射对象，参数增加到formBean中
	private SysAcctFormBean formBean = new SysAcctFormBean();
	// 注解注入控制层
	@Resource 
	private SysAcctControl sysAcctControl;

	/**
	 * ajax 加载 单个信息加载页面，调用此方法 /lyht/sysIcon!edit.do
	 */
	@SuppressWarnings({  "unchecked", "rawtypes" })
	public String edit() {
		log.info("查看对象==SysacctAction.edit");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysAcct info = new SysAcct();
		ret=sysAcctControl.view(formBean.getInfoBean().getId(),info);
		hashtable.put("infoBean", info);
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}

	/**
	 * ajax  单个信息保存，调用此方法 /lyht/sysIcon!save.do
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String save() {
		log.info("保存对象==SysacctAction.save");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysAcct info = new SysAcct();
		if (formBean.getInfoBean().getId() == 0) {
			ret=sysAcctControl.create(formBean.getInfoBean(),info);
		} else {
			//获取旧数据
			info= sysAcctControl.getByid(formBean.getInfoBean().getId());
			ret=sysAcctControl.update(formBean.getInfoBean(),info);
		}
		hashtable.put("infoBean", info);
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}

	/**
	 * ajax  查询列表信息，调用此方法 /lyht/sysIcon!find_All.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String list() {
		log.info("查询列表==SysacctAction.list");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		ret= sysAcctControl.list(formBean, prs);
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
	 * ajax  批量删除信息，调用此方法 /lyht/sysIcon!removeids.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String removeids() {
		log.info("批量删除==SysacctAction.removeids");
		// 获取ids
		String ids = CommonUtil.trim(formBean.getIds());
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		prs=sysAcctControl.list_ByIds(ids);
		
		ret= sysAcctControl.delByIds(ids, prs.getResults());

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;

	}
	
	/**
	 * ajax  初始化密码
	 */
	@SuppressWarnings({ "unchecked","rawtypes" })
	public String reset() {
		log.info("初始化密码==SysacctAction.reset");
		
		String id = CommonUtil.trim(formBean.getIds());
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		
		ret=sysAcctControl.updatePwdById(id);//根据id初始化密码
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	
	/**
	 * ajax  批量审核信息，调用此方法 /lyht/sysIcon!flag.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String flag() {
		log.info("批量审核==SysacctAction.flag");
		// 获取ids
		String ids = CommonUtil.trim(formBean.getIds());
		int flag_new=formBean.getInfoBean().getFlag();
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取审核的数据
		PageResults prs=new PageResults(); 
		prs=sysAcctControl.list_ByIds(ids);
		//审核
		ret= sysAcctControl.flag(ids, prs.getResults(), flag_new);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;

	}
	
	/**
	 * ajax  修改密码
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String editPwd(){
		log.info("==SysacctAction.editPwd");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		ret=sysAcctControl.updatePwdByUser(formBean);
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}

	public SysAcctFormBean getFormBean() {
		return formBean;
	}

	public void setFormBean(SysAcctFormBean formBean) {
		this.formBean = formBean;
	}

}
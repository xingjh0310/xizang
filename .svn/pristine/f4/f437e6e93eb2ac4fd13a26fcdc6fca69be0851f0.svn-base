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
import com.lyht.business.system.bean.SysDict;
import com.lyht.business.system.control.SysDictControl;
import com.lyht.business.system.formBean.SysDictCateFormBean;
import com.lyht.business.system.formBean.SysDictFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/system")
@Results({ @Result(name = "list", location = "/business/system/sysDict/list.jsp"),
		@Result(name = "edit", location = "/business/system/sysDict/edit.jsp") })


@Controller
@Scope("prototype")
// 支持多例
public class SysdictAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("SysDictAction");
	private SysDictFormBean formBean = new SysDictFormBean();
	
	private SysDictCateFormBean mSysDictCateFormBean=new SysDictCateFormBean();

	@Resource 
	private SysDictControl sysDictControl;

	/**
	 * ajax 加载 单个信息加载页面，调用此方法 /lyht/sysIcon!edit.do
	 */
	@SuppressWarnings({  "unchecked", "rawtypes" })
	public String edit() {
		log.info("查看对象==SysdictAction.edit");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysDict info = new SysDict();
		ret=sysDictControl.view(formBean.getInfoBean().getId(),info);
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
		log.info("保存对象==SysdictAction.save");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		SysDict info = new SysDict();
		if (formBean.getInfoBean().getId() == 0) {
			ret=sysDictControl.create(formBean.getInfoBean(),info);
		} else {
			//获取旧数据
			info= sysDictControl.getByid(formBean.getInfoBean().getId());
			ret=sysDictControl.update(formBean.getInfoBean(),info);
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
		log.info("查询列表==SysdictAction.list");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		PageResults prs=new PageResults(); 
		ret= sysDictControl.list(formBean, prs);
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
		log.info("批量删除==SysdictAction.removeids");
		// 获取ids
		String ids = CommonUtil.trim(formBean.getIds());
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		prs=sysDictControl.list_ByIds(ids);
		ret= sysDictControl.delByIds(ids, prs.getResults());
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
		log.info("批量审核==SysdictAction.flag");
		// 获取ids
		String ids = CommonUtil.trim(formBean.getIds());
		int flag_new=formBean.getInfoBean().getFlag();
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取审核的数据
		PageResults prs=new PageResults(); 
		prs=sysDictControl.list_ByIds(ids);
		//审核
		ret= sysDictControl.flag(ids, prs.getResults(), flag_new);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;

	}
	
	//获取字典基础数据 /system/SysDict!getDictDataByClassify.do
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDictDataByClassify(){
		log.info("查询列表==getDictDataByClassify.list");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		
		ret= sysDictControl.getDictData(mSysDictCateFormBean, prs);
		
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
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	//获取字典信息接口
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDictInfo(){
		log.info("查询列表==SysdictAction.getDictInfo");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		ret= sysDictControl.getDictInfos(prs,mSysDictCateFormBean);
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
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null; 
	}
	
	//根据名称与内码获取编码  /system/SysDict!getDictCode.do   参数：nm,name  返回：CODE
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDictCode(){
		log.info("查询列表==SysdictAction.getDictCode");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		
		String nm=this.getRequest().getParameter("nm");
		String name=this.getRequest().getParameter("name");
		
		ret= sysDictControl.getCodeByNmAndName(prs,nm,name);
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
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null; 
	}
	
	public SysDictFormBean getFormBean() {
		return formBean;
	}

	public void setFormBean(SysDictFormBean formBean) {
		this.formBean = formBean;
	}

	public SysDictCateFormBean getmSysDictCateFormBean() {
		return mSysDictCateFormBean;
	}

	public void setmSysDictCateFormBean(SysDictCateFormBean mSysDictCateFormBean) {
		this.mSysDictCateFormBean = mSysDictCateFormBean;
	}
	
}
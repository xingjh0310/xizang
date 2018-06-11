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
import com.lyht.business.system.bean.SysDictCate;
import com.lyht.business.system.control.SysDictCateControl;
import com.lyht.business.system.formBean.SysDictCateFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/system")
@Results({ @Result(name = "list", location = "/business/system/sysDictCate/list.jsp"),
		@Result(name = "edit", location = "/business/system/sysDictCate/edit.jsp") })
@Controller
@Scope("prototype")
// 支持多例
public class SysdictcateAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;
	
	// 调试日志
	private static Logger log = Logger.getLogger("SysDictCateAction");
	
	// struts2映射对象，参数增加到formBean中
	private SysDictCateFormBean formBean = new SysDictCateFormBean();
	
	// 注解注入控制层
	@Resource 
	private SysDictCateControl sysDictCateControl;

	/**
	 * ajax 加载 单个信息加载页面，调用此方法 /lyht/sysIcon!edit.do
	 */
	@SuppressWarnings({  "unchecked", "rawtypes" })
	public String edit() {
		log.info("查看对象==SysdictcateAction.edit");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysDictCate info = new SysDictCate();
		ret=sysDictCateControl.view(formBean.getInfoBean().getId(),info);
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
		log.info("保存对象==SysdictcateAction.save");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysDictCate info = new SysDictCate();
		if (formBean.getInfoBean().getId() == null || formBean.getInfoBean().getId() == 0) {
			ret=sysDictCateControl.create(formBean.getInfoBean(),info);
			
		} else {
			//获取旧数据
			info= sysDictCateControl.getByid(formBean.getInfoBean().getId());
			ret=sysDictCateControl.update(formBean.getInfoBean(),info);
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
		log.info("查询列表==SysdictcateAction.list");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		ret= sysDictCateControl.list(formBean, prs);
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
		log.info("批量删除==SysdictcateAction.removeids");
		// 获取ids
		String ids = CommonUtil.trim(formBean.getIds());
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		prs=sysDictCateControl.list_ByIds(ids);
		
		ret= sysDictCateControl.delByIds(ids, prs.getResults());

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;

	}
	
	public SysDictCateFormBean getFormBean() {
		return formBean;
	}

	public void setFormBean(SysDictCateFormBean formBean) {
		this.formBean = formBean;
	}

}
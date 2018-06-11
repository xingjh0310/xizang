package com.lyht.business.question.action;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.question.control.HandlerDetailControl;
import com.lyht.business.question.formBean.SubmitFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/question")
@Controller
@Scope("prototype")
@Action("/detail")
public class HandlerDetailAction extends BaseLyhtAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 调试日志
	private static Logger log = Logger.getLogger("HandlerDetailAction");
	private SubmitFormBean fSubmitFormBean=new SubmitFormBean();
	
	@Resource
	private HandlerDetailControl cHandlerDetailControl;
	
	/**
	 * 查询物资问题处理明细
	 */
	@SuppressWarnings("rawtypes")
	public String queryQuestionHandlerDetail(){
		log.info("查询物资问题处理==HandlerDetailAction.queryQuestionHandlerDetail");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults();
		//当前登录人部门
		SysDept dept=(SysDept)this.getSession().getAttribute(Constants.SESSION_DEPT);
		ret=cHandlerDetailControl.queryQuestionHandlerDetail(fSubmitFormBean, prs,dept);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			hashMap.put("total", 0);
			hashMap.put("rows", jsonData);			
		} else {
			hashMap.put("total", prs.getTotalCount());
			hashMap.put("rows", prs.getResults());			
		}
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}

	public SubmitFormBean getfSubmitFormBean() {
		return fSubmitFormBean;
	}

	public void setfSubmitFormBean(SubmitFormBean fSubmitFormBean) {
		this.fSubmitFormBean = fSubmitFormBean;
	}
}

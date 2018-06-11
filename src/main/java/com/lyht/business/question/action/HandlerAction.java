package com.lyht.business.question.action;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.question.control.HandlerControl;
import com.lyht.business.question.formBean.HandlerFormBean;
import com.lyht.business.question.formBean.SubmitFormBean;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/question")
@Controller
@Scope("prototype")
@Action("/handler")
public class HandlerAction extends BaseLyhtAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 调试日志
	private static Logger log = Logger.getLogger("HandlerAction");
	private SubmitFormBean fSubmitFormBean=new SubmitFormBean();
	private HandlerFormBean fHandlerFormBean=new HandlerFormBean();
	
	@Resource
	private HandlerControl cHandlerControl;
	
	/**
	 * 查询物资问题处理
	 */
	@SuppressWarnings("rawtypes")
	public String queryQuestionHandler(){
		log.info("查询物资问题处理==HandlerAction.queryQuestionHandler");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults();
		ret=cHandlerControl.queryQuestionHandler(fSubmitFormBean, prs);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			hashMap.put("total", 0);
			hashMap.put("rows", jsonData);			
		} else {
			hashMap.put("total", prs.getTotalCount());
			hashMap.put("rows", prs.getResults());			
		}
		UUID uuid = UUID.randomUUID();
		String fileNm=uuid.toString().replaceAll("-", "");
		hashMap.put("HANDLEFILENM", fileNm);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 新增/修改 物资问题处理
	 */
	public String saveHandler(){
		log.info("新增/修改 物资问题处理==HandlerAction.saveHandler");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		SysStaff mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		if(null != mSysStaff){
			String staffName=mSysStaff.getNm();//处理人NM
			fHandlerFormBean.getmHandler().setProcessPerson(staffName);
		}
		int SubmitId=Integer.parseInt(fHandlerFormBean.getmHandler().getSubmitId());
		ret=cHandlerControl.saveHandler(fHandlerFormBean.getmHandler(),SubmitId);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 导出问题处理
	 */
	@SuppressWarnings("rawtypes")
	public String downLoadHandlerInfo(){
		log.info("导出问题处理==HandlerAction.downLoadHandlerInfo");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		cHandlerControl.downLoadHandlerInfo(fSubmitFormBean,prs,req,res);
		return null;
	}
	
/******************************WEB-APP分割线********************/
	//问题处理饼形图
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String app_echarts(){
		log.info("问题处理==HandlerAction.app_echarts");
		Hashtable hashtable = new Hashtable();
		RetMessage retMessage = new RetMessage();
		PageResults pageResult = new PageResults();
		try {
			retMessage = cHandlerControl.count(fHandlerFormBean,pageResult);
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
		}
		//判断访问service层是否出现错误
		if (retMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)) {
		//如果出现错误那么返回一个空Json
			JSONArray jsonData = new JSONArray();
			hashtable.put("total", 0);
			hashtable.put("rows", jsonData);
			hashtable.put("name", "已处理,未处理");
		} else {
		//如果没有错误返回有数据的list
			hashtable.put("total", pageResult.getTotalCount());
			hashtable.put("rows",  pageResult.getResults());
			hashtable.put("name",  "已处理,未处理");
		}
		hashtable.put(RetMessage.AJAX_RETFLAG, retMessage.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, retMessage.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	
	public SubmitFormBean getfSubmitFormBean() {
		return fSubmitFormBean;
	}

	public void setfSubmitFormBean(SubmitFormBean fSubmitFormBean) {
		this.fSubmitFormBean = fSubmitFormBean;
	}

	public HandlerFormBean getfHandlerFormBean() {
		return fHandlerFormBean;
	}

	public void setfHandlerFormBean(HandlerFormBean fHandlerFormBean) {
		this.fHandlerFormBean = fHandlerFormBean;
	}
}

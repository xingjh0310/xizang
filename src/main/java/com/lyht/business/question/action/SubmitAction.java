package com.lyht.business.question.action;

import java.util.HashMap;
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
import com.lyht.business.question.control.SubmitControl;
import com.lyht.business.question.formBean.SubmitFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.control.SysDictControl;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/question")
@Controller
@Scope("prototype")
@Action("/submit")
public class SubmitAction extends BaseLyhtAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 调试日志
	private static Logger log = Logger.getLogger("SubmitAction");
	private SubmitFormBean fSubmitFormBean=new SubmitFormBean();
	
	@Resource
	private SubmitControl cSubmitControl;
	@Resource 
	private SysDictControl sysDictControl;
	
	   //获取问题类型（调用系统） 
		@SuppressWarnings({ "rawtypes" })
		public String getProblemType(){
			log.info("获取问题类型==SubmitAction.getProblemType");
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			RetMessage mRetMessage=new RetMessage();
			PageResults mPageResults=new PageResults(); 
			String flag="wenti";
			mRetMessage= sysDictControl.getProblemType(mPageResults,flag);
			if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
				JSONArray jsonData = new JSONArray();
				hashMap.put("total", 0);
				hashMap.put("rows", jsonData);			
			} else {
				hashMap.put("total", mPageResults.getTotalCount());
				hashMap.put("rows", mPageResults.getResults());			
			}
			
			hashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
			hashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());	
			getResponse().setHeader("Access-Control-Allow-Origin", "*"); //允许所有跨域访问
			CommonFunction.writeResponse(getResponse(), hashMap);
			return null;
		}
		
		//获取处理方式（调用系统） 
		@SuppressWarnings({ "rawtypes" })
		public String getProcessMethod(){
			log.info("获取处理方式==SubmitAction.getProcessMethod");
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			RetMessage mRetMessage=new RetMessage();
			PageResults mPageResults=new PageResults(); 
			String flag="chuli";
			mRetMessage= sysDictControl.getProcessMethod(mPageResults,flag);
			if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
				JSONArray jsonData = new JSONArray();
				hashMap.put("total", 0);
				hashMap.put("rows", jsonData);			
			} else {
				hashMap.put("total", mPageResults.getTotalCount());
				hashMap.put("rows", mPageResults.getResults());			
			}
			
			hashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
			hashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());	
			getResponse().setHeader("Access-Control-Allow-Origin", "*"); //允许所有跨域访问
			CommonFunction.writeResponse(getResponse(), hashMap);
			return null;
		}
	
	/**
	 * 查询物资问题
	 */
	@SuppressWarnings("rawtypes")
	public String queryAllQuestion(){
		log.info("查询物资问题==SubmitAction.queryAllQuestion");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults();
		SysDept mSysDept=(SysDept)this.getSession().getAttribute(Constants.SESSION_DEPT);
		//获取当前登录人员信息	
	    SysStaff mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		Integer id=fSubmitFormBean.getmSubmit().getId();
		if( null != id && id>=0){
			UUID uuid = UUID.randomUUID();
			String fileNm=uuid.toString().replaceAll("-", "");
			//当前登录人部门
		    hashMap.put("fileNm", fileNm);
		    hashMap.put("mSysDept", mSysDept);
		    hashMap.put("mSysStaff", mSysStaff);
			
		}
		ret=cSubmitControl.queryAllQuestion(fSubmitFormBean, prs,mSysDept);
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
	
	/**
	 * 新增/修改 物资问题上报
	 */
	public String saveSubmit(){
		log.info("新增/修改 物资问题上报==SubmitAction.saveSubmit");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		ret=cSubmitControl.saveSubmit(fSubmitFormBean);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 删除 物资问题上报
	 */
	public String removeids(){
		log.info("删除 物资问题上报==SubmitAction.removeids");
		String ids = CommonUtil.trim(fSubmitFormBean.getIds());// 获取ids
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		ret= cSubmitControl.delByIds(ids);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}

	/**
	 * 导出物资问题上报
	 */
	@SuppressWarnings("rawtypes")
	public String downLoadSubmitInfo(){
		log.info("导出物资问题上报==SubmitAction.downLoadSubmitInfo");
		SysDept dept=(SysDept)this.getSession().getAttribute(Constants.SESSION_DEPT);
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		cSubmitControl.downLoadSubmitInfo(fSubmitFormBean,prs,req,res,dept);
		return null;
	}
	
	/**
	 * 查询物料问题数量
	 */
	public String queryQuestionNub(){
		log.info("查询物料问题数量==SubmitAction.queryQuestionNub");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		int questionNub=cSubmitControl.queryQuestionNub(fSubmitFormBean);
		hashMap.put("questionNub", questionNub);
		hashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
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

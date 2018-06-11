package com.lyht.business.evaluate.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContInfo;
import com.lyht.business.contracMng.control.ContInfoControl;
import com.lyht.business.contracMng.formbean.ContInfoFormBean;
import com.lyht.business.evaluate.bean.ContEvaluate;
import com.lyht.business.evaluate.control.ContEvaluateControl;
import com.lyht.business.evaluate.formBean.ContEvaluateFormBean;
import com.lyht.business.pub.control.FileUploadControl;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

@Namespace("/contEvaluate")
	
@Controller
@Scope("prototype")
/**
 * @author 张琦
 */
@Action("/contEvaluate")
public class ContEvaluateAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("ContInfoAction");
	private ContEvaluateFormBean contEvaluateFormBean = new ContEvaluateFormBean();
	
	@Resource
	ContEvaluateControl contEvaluateControl;
	@Resource
	private FileUploadControl cFileUploadControl;

	//履约评价列表及单个履约评价详细信息
	@SuppressWarnings("rawtypes")
	public String list() {
		log.info("查询履约评价列表==ContEvaluateAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contEvaluateControl.listContEvaluate(contEvaluateFormBean, prs);
		hashMap.put("total", prs.getTotalCount());
		hashMap.put("rows", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	// 新增履约评价或修改履约评价
	@SuppressWarnings("deprecation")
	public String saveEvaluate() {
		log.info("保存履约评价==ContEvaluateAction.saveEvaluate");
		ContEvaluate mContEvaluate = new ContEvaluate();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		
		if (contEvaluateFormBean.getContEvaluateBean().getId() == null || "".equals(contEvaluateFormBean.getContEvaluateBean().getId())) {
			//添加履约评价
			ret = contEvaluateControl.saveContEvaluate(contEvaluateFormBean.getContEvaluateBean(), mContEvaluate);
		} else {
			// 根据id获取旧数据进行修改
			mContEvaluate = contEvaluateControl.getContEvaluateById(contEvaluateFormBean.getContEvaluateBean().getId());
			// 修改履约评价
			ret = contEvaluateControl.update(contEvaluateFormBean.getContEvaluateBean(), mContEvaluate);
		}
		
		hashMap.put("infoBean", mContEvaluate);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	//添加或修改履约评价时查询数据
	@SuppressWarnings("rawtypes")
	public String editEvaluate(){
		log.info("添加或修改履约评价时查询数据==ContEvaluateAction.editEvaluate");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contEvaluateControl.editEvaluate(contEvaluateFormBean, prs);
		hashMap.put("info", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	// 删除履约评价
	@SuppressWarnings("rawtypes")
	public String removeids() {
		log.info("批量删除==ContEvaluateAction.removeids");
		// 获取ids
		String ids = CommonUtil.trim(contEvaluateFormBean.getIds());
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		// 删除数据
		ret = contEvaluateControl.delContEvaluateByIds(ids);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/************************************web-app分界线**********************************/
	
	// 新增履约评价或修改履约评价
	@SuppressWarnings("deprecation")
	public String saveEvaluate_app() {
		log.info("保存履约评价==ContEvaluateAction.saveEvaluate");
		ContEvaluate mContEvaluate = new ContEvaluate();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		
		if (contEvaluateFormBean.getContEvaluateBean().getId() == null || "".equals(contEvaluateFormBean.getContEvaluateBean().getId())) {
			//添加履约评价
			ret = contEvaluateControl.saveContEvaluate(contEvaluateFormBean.getContEvaluateBean(), mContEvaluate);
		} else {
			// 根据id获取旧数据进行修改
			mContEvaluate = contEvaluateControl.getContEvaluateById(contEvaluateFormBean.getContEvaluateBean().getId());
			// 修改履约评价
			ret = contEvaluateControl.update(contEvaluateFormBean.getContEvaluateBean(), mContEvaluate);
		}
		
		//上传app图片
		cFileUploadControl.saveImageUpload_app(getRequest(),mContEvaluate.getId()+"",mContEvaluate.getEngineerCode(),"CONT_EVALUATE");
		
		hashMap.put("infoBean", mContEvaluate);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	public ContEvaluateFormBean getContEvaluateFormBean() {
		return contEvaluateFormBean;
	}
	
	public void setContEvaluateFormBean(ContEvaluateFormBean contEvaluateFormBean) {
		this.contEvaluateFormBean = contEvaluateFormBean;
	}

	
}

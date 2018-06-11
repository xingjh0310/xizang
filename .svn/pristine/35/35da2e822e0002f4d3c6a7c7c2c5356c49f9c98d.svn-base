package com.lyht.business.contracMng.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContChange;
import com.lyht.business.contracMng.bean.ContDetail;
import com.lyht.business.contracMng.control.ContChangeControl;
import com.lyht.business.contracMng.control.ContDetailControl;
import com.lyht.business.contracMng.formbean.ContDetailFormBean;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

@Namespace("/contDetail")
	
@Controller
@Scope("prototype")
/**
 * @author 张琦
 */
@Action("/contDetail")
public class ContDetailAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("ContDetailAction");
	private ContDetailFormBean contDetailFormBean = new ContDetailFormBean();
	
	@Resource
	ContDetailControl contDetailControl;
	@Resource
	ContChangeControl contChangeControl;

	
	//查询合同物资信息
	@SuppressWarnings("rawtypes")
	public String queryDetailByContNo(){
		log.info("查询合同物资信息==ContDetailAction.queryDetailByContNo");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contDetailControl.queryContDetailByContNo(contDetailFormBean.getContDetailBean().getContractNo(), prs);
		hashMap.put("contDetails", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	//删除一条物资数据
	@SuppressWarnings("rawtypes")
	public String removeid() {
		log.info("删除==ContDetailAction.removeids");
		// 获取ids
		String ids = CommonUtil.trim(contDetailFormBean.getIds());
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		//留存删除记录
		ContDetail detail = contDetailControl.getContDetailById(Integer.parseInt(ids));
		ContChange contChange = new ContChange();
		ContChange retContChange = new ContChange();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		contChange.setChangeTime(time);
		SysStaff mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		String staffCode = mSysStaff.getCode();
		contChange.setOperator(staffCode);
		contChange.setCode(detail.getCode());
		contChange.setContractNo(detail.getContractNo());
		contChange.setEngineerCode(detail.getEngineerCode());
		contChange.setMaterialState(2);//删除物资操作
		contChange.setMaterielCode(detail.getMaterielCode());
		contChange.setMaterielName(detail.getMaterielName());
		contChange.setPreChangeNum(detail.getMaterielNum());
		contChange.setPreChangePrice(detail.getProposalPrice());
		contChange.setSection(detail.getSection());
		contChangeControl.saveContChange(contChange, retContChange);
		
		// 删除数据
		ret = contDetailControl.delContDetailByIds(ids);
		
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/************************************web-app分界线**********************************/
	@SuppressWarnings("rawtypes")
	public String save_app(){
		log.info("查询合同物资信息==ContDetailAction.save_app");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ContDetail rtnCd = new ContDetail();
		RetMessage ret = new RetMessage();
		if(CommonUtil.trim(contDetailFormBean.getContDetailBean().getId()).length()>0){
			ret = contDetailControl.update(contDetailFormBean.getContDetailBean(), rtnCd);
		}else {
			contDetailFormBean.getContDetailBean().setCode(UUID.randomUUID().toString().replaceAll("-", ""));
			ret = contDetailControl.saveDetailInfo(contDetailFormBean.getContDetailBean(), rtnCd);
		}
		hashMap.put("rtnCd", rtnCd);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	//合同物资ID获取物料信息
	@SuppressWarnings("rawtypes")
	public String getMaterialById_app(){
		log.info("查询合同物资信息==ContDetailAction.getMaterialById");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contDetailControl.getMaterialById_app(contDetailFormBean, prs);
		hashMap.put("material", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	//通过合同编号及物料编号获取合同物资
	@SuppressWarnings("rawtypes")
	public String getMaterialByContNoAndMatCode_app(){
		log.info("查询合同物资信息==ContDetailAction.getMaterialByContNoAndMatCode_app");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contDetailControl.getMaterialByContNoAndMatCode_app(contDetailFormBean, prs);
		hashMap.put("material", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	
	public ContDetailFormBean getContDetailFormBean() {
		return contDetailFormBean;
	}
	
	public void setContDetailFormBean(ContDetailFormBean contDetailFormBean) {
		this.contDetailFormBean = contDetailFormBean;
	}
}

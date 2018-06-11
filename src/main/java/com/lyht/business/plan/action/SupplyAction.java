package com.lyht.business.plan.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;

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
import com.lyht.business.plan.bean.Supply;
import com.lyht.business.plan.control.SupplyControl;
import com.lyht.business.plan.formBean.MaterialDetailFormBean;
import com.lyht.business.plan.formBean.SupplyFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.control.SysDeptControl;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/plan")
@Controller
@Scope("prototype")
@Action("/supply")
public class SupplyAction extends BaseLyhtAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("DemandAction"); 
	private SupplyFormBean fSupplyFormBean=new SupplyFormBean();
	private MaterialDetailFormBean fMaterialDetailFormBean=new MaterialDetailFormBean();
	
	@Resource
	private SupplyControl cSupplyControl;
	@Resource 
	private SysDeptControl mSysDeptControl;
	

	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	
	@SuppressWarnings({ "rawtypes" })
	public String getDeptInfoByEngineerInfoCode(){
		log.info("根据工程信息编号获取部门信息==SupplyAction.getDeptInfoByEngineerInfoCode");
		String engineerInfoCode=this.getRequest().getParameter("engineerInfoCode");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		
		mRetMessage= mSysDeptControl.getDeptInfoByEngineerInfoCode(mPageResults, engineerInfoCode);
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
	 * 导出供货计划
	 */
	@SuppressWarnings("rawtypes")
	public String downLoadSupplyInfo(){
		log.info("导出供货计划==SupplyAction.downLoadSupplyInfo");
		
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		cSupplyControl.downLoadSupplyInfo(fSupplyFormBean,prs,req,res,dept);
		return null;
	}
	
	/**
	 * 查看全部供货计划
	 */
	@SuppressWarnings("rawtypes")
	public String queryAllSupplyInfo(){
		log.info("查看全部供货计划==SupplyAction.queryAllSupplyInfo");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults();
		//当前登录人部门角色
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		
		ret=cSupplyControl.queryAllSupplyInfo(fSupplyFormBean, prs,dept);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			hashMap.put("total", 0);
			hashMap.put("rows", jsonData);			
		} else {
			hashMap.put("total", prs.getTotalCount());
			hashMap.put("rows", prs.getResults());			
		}
		//当前登录人部门
		SysDept mSysDept=(SysDept)this.getSession().getAttribute(Constants.SESSION_DEPT);
	    hashMap.put("mSysDept", mSysDept);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 新增/修改 供货计划
	 */
	public String saveSupply(){
		log.info("新增/修改 供货计划==SupplyAction.saveSupply");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		Supply mSupply=new Supply();
		if(fSupplyFormBean.getmSupply().getId()==0){
			String supPlanTitle = fSupplyFormBean.getmSupply().getContractDeliveryDate()+
								  fSupplyFormBean.getSupPlyfullName()+"供应"+
								  fSupplyFormBean.getmSupply().getContractNum()+
								  fSupplyFormBean.getmSupply().getMeaUnit()+
								  fMaterialDetailFormBean.getmMaterialDetail().getMaterielName();
			fSupplyFormBean.getmSupply().setSupplyplantitle(supPlanTitle);
			ret=cSupplyControl.saveSupply(fSupplyFormBean.getmSupply(),mSupply,fMaterialDetailFormBean.getmMaterialDetail());
		}else{
			String supPlanTitle = fSupplyFormBean.getmSupply().getContractDeliveryDate()+
					  fSupplyFormBean.getSupPlyfullName()+"供应"+
					  fSupplyFormBean.getmSupply().getContractNum()+
					  fSupplyFormBean.getmSupply().getMeaUnit()+
					  fMaterialDetailFormBean.getmMaterialDetail().getMaterielName();
			fSupplyFormBean.getmSupply().setSupplyplantitle(supPlanTitle);
			ret=cSupplyControl.UpdateSupply(fSupplyFormBean.getmSupply(),fMaterialDetailFormBean.getmMaterialDetail());
		}
		hashMap.put("infoBean", mSupply);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 批量删除信息
	 */
	@SuppressWarnings("rawtypes")
	public String removeids() {
		log.info("批量删除供货计划==SupplyAction.removeids");
		String ids = CommonUtil.trim(fSupplyFormBean.getIds());// 获取ids
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		prs=cSupplyControl.listByIds(ids);
		ret= cSupplyControl.delByIds(ids, prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 更改中标日期
	 */
	public String updateBiddingDate(){
		log.info("更改中标日期==SupplyAction.updateBiddingDate");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		ret= cSupplyControl.updateBiddingDate(fSupplyFormBean);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 更新图纸、协议日期
	 */
	public String updateTime(){
		log.info("更新图纸、协议日期==SupplyAction.updateTime");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		ret= cSupplyControl.updateTime(fSupplyFormBean);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 查询供货计划数量
	 */
	public String querySupplyNub(){
		log.info("查询供货计划数量==SupplyAction.querySupplyNub");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		int supplyNub=cSupplyControl.querySupplyNub(fSupplyFormBean);
		hashMap.put("supplyNub", supplyNub);
		hashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////APP
	
	/**
	 * 查询供货计划信息--APP
	 */
	public String querySupplyInfo_App(){
		log.info("查询供货计划信息--APP==SupplyAction.querySupplyInfo_App");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		List<Supply> supplyList=cSupplyControl.querySupplyInfo_App();
		hashMap.put("supplyList", supplyList);
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}

	public SupplyFormBean getfSupplyFormBean() {
		return fSupplyFormBean;
	}


	public void setfSupplyFormBean(SupplyFormBean fSupplyFormBean) {
		this.fSupplyFormBean = fSupplyFormBean;
	}


	public MaterialDetailFormBean getfMaterialDetailFormBean() {
		return fMaterialDetailFormBean;
	}

	public void setfMaterialDetailFormBean(MaterialDetailFormBean fMaterialDetailFormBean) {
		this.fMaterialDetailFormBean = fMaterialDetailFormBean;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}
	
}

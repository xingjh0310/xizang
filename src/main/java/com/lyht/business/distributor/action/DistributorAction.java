package com.lyht.business.distributor.action;

import java.io.File;
import java.util.HashMap;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.distributor.bean.Distributor;
import com.lyht.business.distributor.control.DistributorControl;
import com.lyht.business.distributor.formBean.DistributorFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/distributor")
@Controller
@Scope("prototype")
@Action("/distributor")
public class DistributorAction extends BaseLyhtAction{

	private static final long serialVersionUID = 1L;
	
	// 调试日志
	private static Logger log = Logger.getLogger("DistributorAction");
	private DistributorFormBean fDistributorFormBean=new DistributorFormBean();
	
	@Resource
	private DistributorControl cDistributorControl;
	
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	
	/**
	 * 导入供应商信息
	 */
	public String importDistributorInfo(){
		log.info("导入供应商信息==DistributorAction.importDistributorInfo");
		RetMessage ret=new RetMessage();
		ret=cDistributorControl.importDistributorInfo(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), "error");
		}else{
			CommonFunction.writeResponse(getResponse(), "success");
		}
		return null;
	}
	
	/**
	 * 导出供应商信息
	 */
	@SuppressWarnings("rawtypes")
	public String downLoadDistributorInfo(){
		log.info("导出供应商信息==DistributorAction.downLoadDistributorInfo");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		cDistributorControl.downLoadDistributorInfo(fDistributorFormBean,prs,req,res);
		return null;
	}
	
	/**
	 * 查看供应商信息
	 */
	@SuppressWarnings("rawtypes")
	public String queryAllDistributor(){
		log.info("查看供应商信息==DistributorAction.queryAllDistributor");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults();
		ret=cDistributorControl.queryAllDistributor(fDistributorFormBean, prs);
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
	 * 查看单个供应商信息
	 */
	public String edit() {
		log.info("查看单个供应商信息==DistributorAction.edit");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		Distributor mDistributor = new Distributor();
		ret=cDistributorControl.view(fDistributorFormBean.getmDistributor().getId(),mDistributor);
		hashMap.put("infoBean", mDistributor);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());	
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 新增/修改 供应商信息
	 */
	public String saveOrUpdate(){
		log.info("新增/修改 供应商信息==DistributorAction.saveOrUpdate");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		Distributor mDistributor = new Distributor();
		if(fDistributorFormBean.getmDistributor().getId()==0){
			ret=cDistributorControl.saveDistributor(fDistributorFormBean.getmDistributor(),mDistributor);
		}else{
			//获取旧数据
			mDistributor=cDistributorControl.getByid(fDistributorFormBean.getmDistributor().getId());
			ret=cDistributorControl.updateDistributor(fDistributorFormBean.getmDistributor(),mDistributor);
		}
		hashMap.put("infoBean", mDistributor);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 批量删除供应商信息
	 */
	@SuppressWarnings("rawtypes")
	public String removeids(){
		log.info("批量删除供应商信息==DistributorAction.removeids");
		String ids = CommonUtil.trim(fDistributorFormBean.getIds());// 获取ids
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		prs=cDistributorControl.listByIds(ids);
		ret=cDistributorControl.delByIds(ids, prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
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

	/**
	 * formBean属性
	 */
	public DistributorFormBean getfDistributorFormBean() {
		return fDistributorFormBean;
	}
	public void setfDistributorFormBean(DistributorFormBean fDistributorFormBean) {
		this.fDistributorFormBean = fDistributorFormBean;
	}	
}

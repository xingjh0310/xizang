package com.lyht.business.transport.action;

import java.io.File;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.pub.control.FileUploadControl;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.transport.bean.Check;
import com.lyht.business.transport.bean.Delivery;
import com.lyht.business.transport.bean.Transfer;
import com.lyht.business.transport.control.MaterielControl;
import com.lyht.business.transport.formBean.MaterielFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/materiel")
@Results({ @Result(name = "list", location = "/business/destroy/company/list.jsp"),
		@Result(name = "edit", location = "/business/destroy/company/edit.jsp"),
		@Result(name = "editCompany", location = "/business/destroy/companyAdd/list.jsp") })

@Controller
@Scope("prototype")
/**
 * @author 陈洪强 
 * 功能 ：查看物资状态列表 list;
 */
public class MaterielAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("MaterielAction");

	MaterielFormBean materielFormBean = new MaterielFormBean();

	private File[] file; //上传文件域对象
	
	private String[] fileFileName; //上传文件名
	
	private String fileId; //文件删除标示
	
	@Resource
	MaterielControl materielControl;

	@Resource
	private FileUploadControl fileUploadControl;
	// 物资状态基础列表
	@SuppressWarnings("rawtypes")
	public String list() {
		log.info("物资状态列表==MaterielAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		
		ret = materielControl.list(materielFormBean, prs,dept);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)) {
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
	//保存到货信息
	public String save(){
		log.info("到货==MaterielAction.save");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		Transfer transfer = new Transfer();
		
		SysStaff staff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		//SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		if(staff!=null){
			
			materielFormBean.getTransferInfoBean().setReceiveParty(staff.getName());
		}
		ret = materielControl.save(materielFormBean.getTransferInfoBean(), transfer);
		/*if(ret.getRetflag().equals(RetMessage.RETFLAG_SUCCESS)){
			transportControl.update(fSupplyFormBean);
		}*/
		if(ret.getRetflag().equals(RetMessage.RETFLAG_SUCCESS)){
			materielControl.update(materielFormBean.getIds(),transfer.getHandover());
		}
		
		hashMap.put("infoBean", transfer);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		
		
		return null;
	}
	//验收信息
	public String save_check(){
		
		log.info("验货==MaterielAction.check");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		Check check = new Check();
		
		ret = materielControl.check(materielFormBean.getCheckInfoBean(), check);
		
		if(ret.getRetflag().equals(RetMessage.RETFLAG_SUCCESS)){
			materielControl.updateY(materielFormBean.getIds(),check.getAcceptance());
		}
		
		hashMap.put("infoBean", check);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		
		return null;
	}
	//物资交货窗体数据
	public String transfer(){
			
	log.info("物资交货数据==MaterielAction.transfer");
			
	HashMap<String, Object> hashMap = new HashMap<String, Object>();
			
	Delivery mDelivery = new Delivery();
			
	RetMessage ret = new RetMessage();
	ret = materielControl.getDeliveryById(materielFormBean.getDeliveryInfoBean().getId(), mDelivery);
			
	hashMap.put("infoBean", mDelivery);
	hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
	hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
	CommonFunction.writeResponse(getResponse(), hashMap);
			
	return null;
 }
	//物资统计到货数量
	public String getMessageNub(){
		
		log.info("通知消息未读数量==SubmitAction.queryQuestionNub");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		int nub=materielControl.getMessageNub(materielFormBean);
		hashMap.put("messageNub", nub);
		hashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
		CommonFunction.writeResponse(getResponse(), hashMap);
		
		return null;
	}
	//导出物资状态数据
	@SuppressWarnings("rawtypes")
	public String downLoad(){
		log.info("导出物资状态数据==MaterielAction.downLoad");
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		materielControl.downLoad(materielFormBean,prs,req,res,dept);
		return null;
	}
	//上传文件
	public String fileUpload(){
		log.info("==MaterielAction.fileUpload");
		RetMessage ret=new RetMessage();
		
		// 当前登录人信息
		SysStaff sysacct = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		String staffName=sysacct.getName();//上传人
		String tableName="mate_receipt";//附件关联表名
		String tablePkColumn=materielFormBean.getDeliveryInfoBean().getId().toString();//附件关联表主键
		String engineerCode="";//暂无
		
		ret=fileUploadControl.saveFileUpload(getFile(),getFileFileName(),tableName, tablePkColumn, staffName, engineerCode,getFileId());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), "error");
		}else{
			CommonFunction.writeResponse(getResponse(), "success");
		}
		return null;
	}
	

	public MaterielFormBean getMaterielFormBean() {
		return materielFormBean;
	}

	public void setMaterielFormBean(MaterielFormBean materielFormBean) {
		this.materielFormBean = materielFormBean;
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
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	
	

}

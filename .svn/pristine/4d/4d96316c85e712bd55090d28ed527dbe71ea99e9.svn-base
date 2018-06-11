package com.lyht.business.system.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.control.SysStaffRefAcctControl;
import com.lyht.business.system.formBean.SysStaffRefAcctFormBean;
import com.lyht.business.system.service.SysStaffRefAcctService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;

import net.sf.json.JSONArray;

@Namespace("/system")

@Controller
@Scope("prototype")
@Action("sysstaffrefacct")
@SuppressWarnings({ "rawtypes" })
public class SysStaffRefAcctAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(SysStaffRefAcctAction.class);
	private SysStaffRefAcctFormBean mSysStaffRefAcctFormBean=new SysStaffRefAcctFormBean();
	
	@Resource
	private SysStaffRefAcctControl mSysStaffRefAcctControl;
	@Resource
	private SysStaffRefAcctService mSysStaffRefAcctService;
	
	private File [] file;       //导入文件
	private String [] fileFileName; //文件名称

	/**
	 * 显示人员及账户列表信息
	 */
	@SuppressWarnings("unchecked")
	public String list() {
		log.info("查询列表==SysStaffRefAcctAction.list");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults(); 
		mRetMessage= mSysStaffRefAcctControl.list(mSysStaffRefAcctFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", jsonData);			
		} else {
			List<Map> list=mSysStaffRefAcctService.getDataList(mPageResults.getResults());
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", list);			
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());	
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
		
	}
	
	/**
	 * 初始化FORM表单
	 * */
	public String edit(){
		log.info("初始化FORM表单==SysStaffRefAcctAction.edit");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		String staffNm=mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getNm();
		SysAcct mSysAcctInfoBean = new SysAcct();
		List<Map> list=mSysStaffRefAcctService.getSysStaffByStaffNm(staffNm);
		mSysAcctInfoBean=mSysStaffRefAcctService.getSysAcctByStaffNm(staffNm);
		mHashMap.put("mSysStaffInfoBean", list);
		mHashMap.put("mSysAcctInfoBean", mSysAcctInfoBean);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存FORM表单数据
	 * */
	public String save(){
		log.info("保存FORM表单数据==SysStaffRefAcctAction.save");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		int id=mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getId();
		if(id==0){
			mHashMap=mSysStaffRefAcctService.insertStaffAndAcct(mHashMap,mSysStaffRefAcctFormBean);
		}else{
			mHashMap=mSysStaffRefAcctService.updateStaffAndAcct(mHashMap,mSysStaffRefAcctFormBean);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 删除数据
	 * */
	public String removeids(){
		log.info("删除数据==SysStaffRefAcctAction.removeids");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		String StaffNm=mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getNm();
		mHashMap= mSysStaffRefAcctService.getDeleteSysStaffAndAcctInfo(StaffNm);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 重置密码
	 * */
	public String reset(){
		log.info("重置密码==SysStaffRefAcctAction.reset");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		String StaffNm=mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getNm();
		mHashMap=mSysStaffRefAcctService.reset(StaffNm);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 导出数据
	 * */
	public String exportData(){
		log.info("导出数据==SysStaffRefAcctAction.exportData");
		String []title={"序号","人员名称","性别","手机号","职务","部门"};
		String []val={"STAFFNAME","SEX","ACCTNAME","DUTY","DEPTNAME"};
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		PageResults mPageResults=mSysStaffRefAcctService.s_findAll(mSysStaffRefAcctFormBean);
		String Path=ExcelUtils.SellerStat2Excel(mPageResults.getResults(), this.getRequest(), 
				DateUtil.getDate(), title, "人员信息表", val);
		mHashMap.put("Path", Path);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 导入数据
	 * */
	public String importStaffInfo(){
		log.info("导入数据==SysStaffRefAcctAction.importStaffInfo");
		RetMessage ret=new RetMessage();
		String prompt="";
		ret= mSysStaffRefAcctControl.importStaffInfo(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			prompt=Constants.AJAX_RETFLAG_ERROR;
		}else{
			prompt=Constants.AJAX_RETFLAG_SUCCESS;
		}
		CommonFunction.writeResponse(getResponse(), prompt);
		return null;
	}
	
	/**
	 * 验证手机号是否唯一
	 * */
	public String validatePhone(){
		log.info("验证手机号是否唯一==SysStaffRefAcctAction.validatePhone");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		mHashMap=mSysStaffRefAcctService.validatePhone(mSysStaffRefAcctFormBean);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 通过角色内码获取人员内码
	 * */
	public String getStaffCodeByRoleCode(){
		log.info("通过角色内码获取人员内码==SysStaffRefAcctAction.getStaffCodeByRoleCode");
		String ids=this.getRequest().getParameter("ids");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		List<Map> list=mSysStaffRefAcctService.getStaffCodeByRoleCode(ids);
		mHashMap.put("mSysStaffInfoBean", list);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 通过人员内码获取角色内码
	 * */
	public String getRoleCodeByStaffCode(){
		log.info("通过人员内码获取角色内码==SysStaffRefAcctAction.getRoleCodeByStaffCode");
		String ids=this.getRequest().getParameter("ids");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		List<Map> list=mSysStaffRefAcctService.getRoleCodeByStaffCode(ids);
		mHashMap.put("mSysStaffInfoBean", list);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	public SysStaffRefAcctFormBean getmSysStaffRefAcctFormBean() {
		return mSysStaffRefAcctFormBean;
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

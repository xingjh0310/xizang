package com.lyht.business.system.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
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
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.control.SysStaffControl;
import com.lyht.business.system.formBean.SysDeptFormBean;
import com.lyht.business.system.formBean.SysStaffFormBean;
import com.lyht.business.system.service.SysStaffService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;

import net.sf.json.JSONArray;

@Namespace("/system")
@Results({ @Result(name = "list", location = "/business/system/sysIcon/list.jsp"),
		@Result(name = "edit", location = "/business/system/sysIcon/edit.jsp") })
@Controller
@Scope("prototype")
// 支持多例
public class SysstaffAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("SysStaffAction");
	private SysStaffFormBean formBean = new SysStaffFormBean();
	private SysDeptFormBean  mSysDeptFormBean=new SysDeptFormBean();

	@Resource 
	private SysStaffControl sysStaffControl;
	@Resource 
	private SysStaffService mSysStaffService;
	
	private File [] file;       //导入文件
	private String [] fileFileName; //文件名称

	/**
	 * ajax 加载 单个信息加载页面，调用此方法 /lyht/sysIcon!edit.do
	 */
	@SuppressWarnings({  "unchecked", "rawtypes" })
	public String edit() {
		log.info("查看对象==SysstaffAction.edit");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysStaff info = new SysStaff();
		ret=sysStaffControl.view(formBean.getInfoBean().getId(),info);
		hashtable.put("infoBean", info);
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	/**
	 * ajax 校验是否已经存在手机号
	 */
	@SuppressWarnings({  "unchecked", "rawtypes" })
	public String check_phone() {
		log.info("查看对象==SysstaffAction.check_phone");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		
		PageResults prs=new PageResults(); 
		ret=sysStaffControl.check_phone(formBean.getInfoBean().getPhone(),prs);
		List results = prs.getResults();
		if(results == null || results.size() == 0){
			hashtable.put("valid", true);
		}else {
			hashtable.put("valid", false);
		}
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
		log.info("保存对象==SysstaffAction.save");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysStaff info = new SysStaff();
		if (formBean.getInfoBean().getId() == 0) {
			ret=sysStaffControl.create(formBean.getInfoBean(),info);
		} else {
			//获取旧数据
			info= sysStaffControl.getByid(formBean.getInfoBean().getId());
			ret=sysStaffControl.update(formBean.getInfoBean(),info);
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
		log.info("查询列表==SysstaffAction.list");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		PageResults prs=new PageResults(); 
		
		String type=this.getRequest().getParameter("type");
		ret= sysStaffControl.list(formBean, prs);
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
		log.info("批量删除==SysstaffAction.removeids");
		// 获取ids
		String ids = CommonUtil.trim(formBean.getIds());
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		prs=sysStaffControl.list_ByIds(ids);
		
		ret= sysStaffControl.delByIds(ids, prs.getResults());

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;

	}
	
	/**
	 * ajax  批量审核信息，调用此方法 /lyht/sysIcon!flag.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String flag() {
		log.info("批量审核==SysstaffAction.flag");
		// 获取ids
		String ids = CommonUtil.trim(formBean.getIds());
		int flag_new=formBean.getInfoBean().getFlag();
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取审核的数据
		PageResults prs=new PageResults(); 
		prs=sysStaffControl.list_ByIds(ids);
		//审核
		ret= sysStaffControl.flag(ids, prs.getResults(), flag_new);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;

	}
	
	//获取人员信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getStaffInfo() {
		log.info("查询列表==SysstaffAction.getStaffInfo");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		PageResults prs=new PageResults(); 
		
		ret= sysStaffControl.getStaffInfos(prs);
		
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
	
	//根据人员名称获取人员信息  
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getStaffInfoByName() {
		log.info("查询列表==SysstaffAction.getStaffInfoByName");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		
		String name=this.getRequest().getParameter("name");
		
		ret= sysStaffControl.getStaffInfosByName(prs,name);
		
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
	
	//导出人员信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String exportData() throws IOException{
		log.info("查询列表==SysstaffAction.exportData");
		Hashtable mHashtable=new Hashtable();
		String [] title={"序号","人员编号","人员名称","职务","性别","籍贯","系统内置","状态","备注"};
		String [] val={"CODE","NAME","DUTY","SEX","ORIGIN","SYSFLAG","FLAG","MEMO"};
		PageResults mPageResults=new PageResults();
		String type=this.getRequest().getParameter("type");
		if("export_whole".equals(type)){ 		 //导出全部数据
			mPageResults=mSysStaffService.getStaffInfoByDeptNm(mSysDeptFormBean);
		}else if("export_current".equals(type)){ //导出当前页
			mPageResults=mSysStaffService.getStaffInfoByDeptNm_(mSysDeptFormBean,formBean);
		}
		String Path=ExcelUtils.SellerStat2Excel(mPageResults.getResults(), this.getRequest(), 
				DateUtil.getDate(), title, "人员信息表", val);
		mHashtable.put("Path", Path);
		CommonFunction.writeResponse(getResponse(), mHashtable);
		return null;
	}
	
	//导入人员数据
	public String importStaffInfo(){
		log.info("查询列表==SysstaffAction.importStaffInfo");
		RetMessage ret=new RetMessage();
		String prompt="";
		String deptNm=this.getRequest().getParameter("deptNm");
		ret= sysStaffControl.importStaffInfo(getFile(),getFileFileName(),deptNm);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			prompt=Constants.AJAX_RETFLAG_ERROR;
		}else{
			prompt=Constants.AJAX_RETFLAG_SUCCESS;
		}
		CommonFunction.writeResponse(getResponse(), prompt);
		return null;
	}

	public SysStaffFormBean getFormBean() {
		return formBean;
	}

	public void setFormBean(SysStaffFormBean formBean) {
		this.formBean = formBean;
	}
	
	public SysDeptFormBean getmSysDeptFormBean() {
		return mSysDeptFormBean;
	}

	public void setmSysDeptFormBean(SysDeptFormBean mSysDeptFormBean) {
		this.mSysDeptFormBean = mSysDeptFormBean;
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
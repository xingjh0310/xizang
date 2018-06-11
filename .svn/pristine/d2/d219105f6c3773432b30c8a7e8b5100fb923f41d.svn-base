package com.lyht.business.system.action;

import java.io.File;
import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.*;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysEngineerInfo;
import com.lyht.business.system.control.SysEngineerInfoControl;
import com.lyht.business.system.dao.SysEngineerInfoDao;
import com.lyht.business.system.formBean.*;
import com.lyht.business.system.service.*;
import com.lyht.util.*;

import net.sf.json.JSONArray;


@Namespace("/system")
@Results({ @Result(name = "list", location = "/business/system/sysWorkInfo/list.jsp"),
		@Result(name = "edit", location = "/business/system/sysWorkInfo/edit.jsp") })
@Controller
@Scope("prototype")

@Action("sysengineerinfo")
@SuppressWarnings({ "unused", "rawtypes" })
public class SysEngineerInfoAction extends BaseLyhtAction{
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("SysEngineerInfoAction");
	private SysEngineerInfoFormBean mSysEngineerInfoFormBean=new SysEngineerInfoFormBean();
	private SysDeptFormBean formBean=new SysDeptFormBean();

	@Resource
	private SysEngineerInfoControl mSysEngineerInfoControl;
	@Resource
	private SysDeptService mSysDeptService;
	@Resource
	private SysEngineerInfoService mSysEngineerInfoService;
	@Resource
	private SysStaffService mSysStaffService;
	
	private File [] file;       //导入文件
	private String [] fileFileName; //文件名称
	
	/**
	 * 查询树状根节点信息
	 * */
	@SuppressWarnings("unchecked")
	public String listroot(){
		log.info("SysEngineerInfoAction=listroot:查询树状根节点信息");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		PageResults prs=new PageResults(); 
		ret= mSysEngineerInfoControl.list_root(mSysEngineerInfoFormBean, prs);
		
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
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	/**
	 * 查询能查看工程的员工
	 * */
	@SuppressWarnings({ "unchecked" })
	public String getStaffsByEngineerNm(){
		log.info("SysEngineerInfoAction=listroot:查询树状根节点信息");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		
		PageResults prs=new PageResults(); 
		ret= mSysEngineerInfoControl.getStaffsByEngineerNm(formBean.getIds(),prs);
		
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
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	/**
	 * 查询树状子节点信息
	 * */
	@SuppressWarnings({ "unchecked" })
	public String list(){
		log.info("SysEngineerInfoAction=list:查询树状子节点信息");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		ret= mSysEngineerInfoControl.list(mSysEngineerInfoFormBean, prs);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			hashtable.put("total", 0);
			hashtable.put("rows", jsonData);			
		} else {
			List<Map> list=prs.getResults();
			getResults(list);
			hashtable.put("total", prs.getTotalCount());
			hashtable.put("rows", list);
		}
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	/**
	 * 初始化表单数据
	 * */
	@SuppressWarnings({ "unchecked" })
	public String edit(){
		log.info("查看对象==SysEngineerInfoAction.edit");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysEngineerInfo info = new SysEngineerInfo();
		SysEngineerInfo pinfo= new SysEngineerInfo();
		
		ret=mSysEngineerInfoControl.view(mSysEngineerInfoFormBean.getmSysEngineerInfo().getId(),info,pinfo);
		hashtable.put("infoBean", info);
		hashtable.put("pinfoBean", pinfo);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	/**
	 * 新增子数据
	 * */
	@SuppressWarnings({ "unchecked" })
	public String add(){
		
		log.info("SysEngineerInfoAction=add:新增子信息加载页面");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysEngineerInfo info = new SysEngineerInfo();
		SysEngineerInfo pinfo= new SysEngineerInfo();
		
		ret=mSysEngineerInfoControl.add(mSysEngineerInfoFormBean.getmSysEngineerInfo().getId(),info,pinfo);
		hashtable.put("infoBean", info);
		hashtable.put("pinfoBean", pinfo);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	/**
	 * 保存数据
	 * */
	@SuppressWarnings({ "unchecked" })
	public String save(){
		log.info("保存对象==SysEngineerInfoAction.save");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysEngineerInfo info = new SysEngineerInfo();
		if (mSysEngineerInfoFormBean.getmSysEngineerInfo().getId() == 0) {
			ret=mSysEngineerInfoControl.create(mSysEngineerInfoFormBean.getmSysEngineerInfo(),info);
		} else {
			info= mSysEngineerInfoControl.getByid(mSysEngineerInfoFormBean.getmSysEngineerInfo().getId());
			ret=mSysEngineerInfoControl.update(mSysEngineerInfoFormBean.getmSysEngineerInfo(),info);
		}
		hashtable.put("infoBean", info);
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	/**
	 * 删除数据
	 * */
	@SuppressWarnings({ "unchecked" })
	public String removeids(){
		log.info("SysEngineerInfoAction:根据IDS删除多个数据");
		String ids = CommonUtil.trim(mSysEngineerInfoFormBean.getIds());
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		prs=mSysEngineerInfoControl.list_ByIds(ids);
		SysEngineerInfo	mSysEngineerInfo = mSysEngineerInfoControl.getBySysEngineerInfoById(ids,mSysEngineerInfoFormBean.getmSysEngineerInfo());
		if( "".equals(mSysEngineerInfo.getPcode())){ 
			ret= mSysEngineerInfoControl.delByIds(mSysEngineerInfo, prs.getResults());
		}else{
			ret= mSysEngineerInfoControl.delByIds_(mSysEngineerInfo, prs.getResults());
		}

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		
		return null;
	}
	
	/**
	 * 审核数据
	 * */
	@SuppressWarnings({ "unchecked"})
	public String flag(){
		log.info("DeptAction:根据IDS批量审核信息");
		String ids = CommonUtil.trim(mSysEngineerInfoFormBean.getIds());
		int flag_new=mSysEngineerInfoFormBean.getmSysEngineerInfo().getState();
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取审核的数据
		PageResults prs=new PageResults(); 
		prs=mSysEngineerInfoControl.list_ByIds(ids);
		//审核
		ret= mSysEngineerInfoControl.flag(ids, prs.getResults(), flag_new);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	//初始化参建单位表单
    @SuppressWarnings({ "unchecked" })
	public String loadSetDept(){
		log.info("初始化加载表单==SysEngineerInfoAction.loadSetDept");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		SysEngineerInfo mSysEngineerInfo=new SysEngineerInfo();
		ret=mSysEngineerInfoControl.edit_view(mSysEngineerInfoFormBean.getmSysEngineerInfo().getId(),mSysEngineerInfo);
	    hashtable.put("mSysEngineerInfo", mSysEngineerInfo);
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
    
    //保存设置单位信息
	public String saveSetDept(){
    	log.info("保存对象==SysEngineerInfoAction.saveSetDept");
		boolean flag=false;
    	HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		String staffFlag=mSysEngineerInfoFormBean.getParmBean().getParm1();
		if("staffFlag".equals(staffFlag)){
			flag=mSysStaffService.setEngineerInfoByStaffNm(mSysEngineerInfoFormBean);
		}else{
			flag=mSysDeptService.getDeptAndStaffInfoByCode(formBean,mSysEngineerInfoFormBean);
		}
		
		if(flag){
			mHashMap.put("promt", "设置成功！！！");
		}else{
			mHashMap.put("promt", "设置失败！！！");
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
    	return null;
    }
    
    //导出工程信息
    @SuppressWarnings({ "unchecked" })
	public String exportProjectInfo(){
    	log.info("保存对象==SysEngineerInfoAction.saveSetDept");
		Hashtable hashtable = new Hashtable();
		PageResults mPageResults=new PageResults();
		
		String []title={"序号","工程名称","简称","建管单位","电压等级","建设线路长度"," 设计投运时间"};
		String []val={"ENGINEER_NAME","ENGINEER_SHORT","TREENM_SYS_DEPT","VOLTAGE_EVEL","LINE_LENGTH","DELIVERY_TIME"};
		
		mPageResults=mSysEngineerInfoService.s_findAll(mSysEngineerInfoFormBean); //获取工程信息
		String Path=ExcelUtils.SellerStat2Excel(mPageResults.getResults(), this.getRequest(), 
				DateUtil.getDate(), title, "工程信息", val);
		
		hashtable.put("Path", Path);
		CommonFunction.writeResponse(getResponse(), hashtable);
    	return null;
    }
    
    //导入工程信息
    public String importProjectInfo(){
    	log.info("保存对象==SysEngineerInfoAction.importProjectInfo");
    	RetMessage ret=new RetMessage();
		String prompt="";
		ret= mSysEngineerInfoControl.importProjectInfo(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			prompt=Constants.AJAX_RETFLAG_ERROR;
		}else{
			prompt=Constants.AJAX_RETFLAG_SUCCESS;
		}
    	CommonFunction.writeResponse(getResponse(), prompt);
    	return null;
    }
    
    /**
     * 根据人员内码获取工程信息
     * */
    public String getSysEngineerInfoBystaffNm(){
    	String staffNm=this.getRequest().getParameter("staffNm");
    	List<Map> list=mSysEngineerInfoService.getSysEngineerInfoBystaffNm(staffNm);
		String json=JSONArray.fromObject(list).toString();
		CommonFunction.writeResponse(getResponse(), json);
    	return null;
    }
    
    /**
	 * 根据工程内码获取部门与人员信息
	 * */
	public String getStaffByEngineerNm(){
		log.info("根据工程内码获取部门与人员信息==SysEngineerInfoAction.getStaffByEngineerNm");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		String engineerInfoCode=this.getRequest().getParameter("engineerInfoCode");
		JSONArray mJSONArray=mSysEngineerInfoService.getStaffByEngineerNm(engineerInfoCode);
		mHashMap.put("rows", mJSONArray);
		CommonFunction.writeResponse(getResponse(), mHashMap);
    	return null;
	}
	
	/**
	 * 根据人员内码获取工程信息
	 * */
	public String getEngineerInfoByStaffNm(){
		log.info("根据人员内码获取工程信息==SysEngineerInfoAction.getEngineerInfoByStaffNm");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		String staffNm=this.getRequest().getParameter("staffNm");
		List<Map> list=mSysEngineerInfoService.getEngineerInfoByStaffNm(staffNm);
		mHashMap.put("rows", list);
		CommonFunction.writeResponse(getResponse(), mHashMap);
    	return null;
	}
	
	@SuppressWarnings({ "unchecked" })
	private void getResults(List<Map> list){
		if(list.size()>0){
			String str="";
			for(int i=0;i<list.size();i++){
				Object obj=list.get(i).get("NM");
				if(null!=obj){
					String []key=obj.toString().split(",");
					for(int j=0;j<key.length;j++){
						List<Map> list_=mSysEngineerInfoService.getStaffInfoNameByEngineerNm(key[j]);
						if(list_.size()>0){
							for(int k=0;k<list_.size();k++){
								Object staffName=list_.get(k).get("STAFFNAME");
								if(null!=staffName){
									str+=staffName.toString()+",";
								}
							}
						}
					}
					if(!"".equals(str)){
						if(",".equals(str.substring(str.length()-1))){
							str=str.substring(0,str.length()-1);
						}
					}
					list.get(i).put("STAFF", str);
					str="";
				}
			}
		}
    }
	
	public SysEngineerInfoFormBean getmSysEngineerInfoFormBean() {
		return mSysEngineerInfoFormBean;
	}

	public void setmSysEngineerInfoFormBean(SysEngineerInfoFormBean mSysEngineerInfoFormBean) {
		this.mSysEngineerInfoFormBean = mSysEngineerInfoFormBean;
	}
	
	public SysDeptFormBean getFormBean() {
		return formBean;
	}

	public void setFormBean(SysDeptFormBean formBean) {
		this.formBean = formBean;
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

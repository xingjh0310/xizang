package com.lyht.business.system.action;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.control.SysDeptControl;
import com.lyht.business.system.formBean.SysDeptFormBean;
import com.lyht.business.system.service.SysDeptService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;

import net.sf.json.JSONArray;

@SuppressWarnings("rawtypes")
@Namespace("/system")
@Results({ @Result(name = "list", location = "/business/system/dept/list.jsp"),
		@Result(name = "edit", location = "/business/system/dept/edit.jsp") })
@Controller
@Scope("prototype")

@Action("sysdept")

// 支持多例
public class SysDeptAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("SysDeptAction");
	private SysDeptFormBean formBean = new SysDeptFormBean();
	
	@Resource 
	private SysDeptControl sysDeptControl;
	@Resource
	private SysDeptService mSysDeptService;
	
	private File [] file;       //导入文件
	private String [] fileFileName; //文件名称
	
	/**
	 * ajax 增加信息加载页面，调用此方法 /lyht/dept!add.do
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String add() {
		log.info("DeptAction=add:新增子信息加载页面");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysDept info = new SysDept();
		SysDept pinfo= new SysDept();
		
		ret=sysDeptControl.add(formBean.getInfoBean().getId(),info,pinfo);
		hashtable.put("infoBean", info);
		hashtable.put("pinfoBean", pinfo);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	/**
	 * ajax 加载 单个信息加载页面，调用此方法 /lyht/dept!edit.do
	 */
	@SuppressWarnings({  "unchecked", "rawtypes" })
	public String edit() {
		log.info("查看对象==SysdeptAction.edit");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysDept info = new SysDept();
		SysDept pinfo= new SysDept();
		ret=sysDeptControl.view(formBean.getInfoBean().getId(),info,pinfo);
		hashtable.put("infoBean", info);
		hashtable.put("pinfoBean", pinfo);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}

	/**
	 * ajax  单个信息保存，调用此方法 /lyht/dept!save.do
	 */
	@SuppressWarnings({ "unchecked"})
	public String save() {
		log.info("保存对象==SysdeptAction.save");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		SysDept info = new SysDept();
		if (formBean.getInfoBean().getId() == 0) {
			ret=sysDeptControl.create(formBean.getInfoBean(),info);
		} else {
			info= sysDeptControl.getByid(formBean.getInfoBean().getId());
			ret=sysDeptControl.update(formBean.getInfoBean(),info);
		}
		hashtable.put("infoBean", info);
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}

	/**
	 * ajax  查询列表信息，调用此方法 /lyht/dept!list.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String list() {
		log.info("DeptAction=list:查询树状信息");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		PageResults prs=new PageResults(); 
		
		ret= sysDeptControl.list(formBean, prs);
		
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			hashtable.put("total", 0);
			hashtable.put("rows", jsonData);			
		} else {
			hashtable.put("total", prs.getTotalCount());
			hashtable.put("rows",  getPageResults(prs));			
		}
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	/**
	 * ajax  查询列表信息，调用此方法 /lyht/dept!listroot.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String listroot() {
		log.info("DeptAction=listroot:查询树状根节点信息");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();

		PageResults prs=new PageResults(); 
		ret= sysDeptControl.list_root(formBean, prs);
		
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			hashtable.put("total", 0);
			hashtable.put("rows", jsonData);			
		} else {
			hashtable.put("total", prs.getTotalCount());
			hashtable.put("rows", getPageResults(prs));			
		}
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	/**
	 * ajax  查询单位类型
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String queryType() {
		log.info("DeptAction=queryType:查询单位类型");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		
		PageResults prs=new PageResults(); 
		ret= sysDeptControl.queryType(formBean, prs);
		
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			hashtable.put("total", 0);
			hashtable.put("rows", jsonData);			
		} else {
			hashtable.put("total", prs.getTotalCount());
			hashtable.put("rows", getPageResults(prs));			
		}
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}

	/**
	 * ajax  批量删除信息，调用此方法 /lyht/dept!removeids.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String removeids() {
		log.info("DeptAction:根据IDS删除多个数据");
		String ids = CommonUtil.trim(formBean.getIds());
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		
		prs=sysDeptControl.list_ByIds(ids);
		SysDept	sysdept = sysDeptControl.getByid(Integer.parseInt(ids));
		if("".equals(sysdept.getPcode())){
			ret= sysDeptControl.delByIds(sysdept.getCode(), prs.getResults());
		}else{
			ret= sysDeptControl.delByIds_(sysdept.getCode(), prs.getResults());
		}

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;

	}
	
	/**Ids
	 * ajax  批量审核信息，调用此方法 /lyht/dept!flag.do
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String flag() {
		log.info("DeptAction:根据IDS批量审核信息");
		String ids = CommonUtil.trim(formBean.getIds());
		int flag_new=formBean.getInfoBean().getFlag();
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取审核的数据
		PageResults prs=new PageResults(); 
		prs=sysDeptControl.list_ByIds(ids);
		//审核
		ret= sysDeptControl.flag(ids, prs.getResults(), flag_new);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String set(){
		String ids = CommonUtil.trim(formBean.getIds());
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		prs=sysDeptControl.list_ByIds(ids); //查询父节点下的所有节点
		ret= sysDeptControl.updateFlag(ids, prs.getResults());
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	//获取部门信息   system/sysdept!getDeptInfo.do
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDeptInfo(){
		log.info("SysDeptAction=getDeptInfo:查询部门信息");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		
		ret= sysDeptControl.getDeptList(formBean, prs);
		
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
	
	//根据名称获取部门信息
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getDeptInfoByName(){
		log.info("SysDeptAction=getDeptInfoName:查询部门信息");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		
		String name=this.getRequest().getParameter("name");
		ret= sysDeptControl.getDeptInfosByName(name, prs);
		
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
	
	//根据人员内码查询所对应的部门信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDeptInfoByStaffNm(){
		log.info("SysDeptAction=getDeptInfoByStaffNm:根据人员内码查询所对应的部门信息");
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults(); 
		
		String nm=this.getRequest().getParameter("nm");
		ret= sysDeptControl.getDeptInfosByNm(nm, prs);
		
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
	
	//根据工程信息编号获取部门信息（APP接口）
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDeptInfoByEngineerInfoCode(){
		log.info("SysDeptAction=getDeptInfoByEngineerInfoCode:根据工程信息编号获取部门信息");
		String engineerInfoCode=this.getRequest().getParameter("engineerInfoCode");
		Hashtable mHashtable = new Hashtable();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		
		mRetMessage= sysDeptControl.getDeptInfoByEngineerInfoCode(mPageResults, engineerInfoCode);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			mHashtable.put("total", 0);
			mHashtable.put("rows", jsonData);			
		} else {
			mHashtable.put("total", mPageResults.getTotalCount());
			mHashtable.put("rows", mPageResults.getResults());			
		}
		
		mHashtable.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashtable.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());
		String results=JSONArray.fromObject(mHashtable).toString();
		getResponse().setHeader("Access-Control-Allow-Origin", "*"); //允许所有跨域访问
		CommonFunction.writeResponse(getResponse(), results);
		return null;
	}
	
	/**
	 * 查询部门内码
	 * */
	public String selectDeptInfo(){
		log.info("SysDeptAction=selectDeptInfo:查询部门内码");
		String nm=this.getRequest().getParameter("nm");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		List<Map> list=mSysDeptService.selectDeptInfo(nm);
		mHashMap.put("rows", list);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	//导出部门数据
	@SuppressWarnings({ "unchecked" })
	public String exportDeptData(){
		log.info("SysDeptAction=exportDeptData:导出部门数据");
		Hashtable hashtable = new Hashtable();
		PageResults mPageResults=new PageResults();
		
		String [] title={"序号","部门编号","部门名称","部门简称","负责工程","标段","状态"};
		String [] val={"CODE","NAME","SHORTNAME","ENGINEER_NAME","SECTION","FLAG"};
		
		mPageResults=mSysDeptService.getDeptWholeInfo();//获取部门信息
		
		String Path=ExcelUtils.SellerStat2Excel(mPageResults.getResults(), this.getRequest(), 
				DateUtil.getDate(), title, "部门信息", val);
		hashtable.put("Path", Path);
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	//导入部门信息数据
	public String importDeptData(){
		log.info("SysDeptAction=importDeptData:导入部门信息数据");
		RetMessage ret=new RetMessage();
		String prompt="";
		ret= sysDeptControl.importDeptInfo(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			prompt=Constants.AJAX_RETFLAG_ERROR;
		}else{
			prompt=Constants.AJAX_RETFLAG_SUCCESS;
		}
		CommonFunction.writeResponse(getResponse(), prompt);
		return null;
	}
	
	//同步通讯录
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String synchroDeptData(){
		log.info("SysDeptAction=synchroDeptData:同步通讯录数据");
		String flag="error";
		Hashtable mHashtable=new Hashtable();
		if(getDepInfoData()){
			flag=getDeptInfoByNm();
		}
		mHashtable.put("flag", flag);
		CommonFunction.writeResponse(getResponse(), mHashtable);
		return null;
	}
	
	//查询部门是否有数据
	private boolean getDepInfoData(){
		return mSysDeptService.getDepInfoData();
	}
	
	//根据内码查询部门信息
	private String getDeptInfoByNm(){
		String mark="error";
		List<Map> mSysDeptList=mSysDeptService.getDeptInfoData(); //查询部门所有数据
		for(int i=0;i<mSysDeptList.size();i++){
			String nm=mSysDeptList.get(i).get("NM").toString();
			//根据内码查询通讯录数据（如果通讯录没有相关的数据然后先通讯录中添加数据）
			if(mSysDeptService.getMailInfoByNm(nm)){  
				//根据内码查询部门信息
				List<Map> mSysDeptList_=mSysDeptService.getDeptInfoData(nm);
				for(int j=0;j<mSysDeptList_.size();j++){
					mSysDeptService.insetMailData(mSysDeptList_.get(j)); //然后向通讯录中添加数据
				}
				mark="success";
			}else{
				continue;
			}
		}
		return mark;
	}
	
	/**
	 * 获取部门信息
	 * */
	public String getDeptByNm(){
		log.info("获取部门信息==SysDeptAction.getDeptInfo");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		String deptNm=this.getRequest().getParameter("deptNm");
		JSONArray mJSONArray=mSysDeptService.getDeptByEngineerNm(deptNm);
		mHashMap.put("rows", mJSONArray);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	//处理工程名称
	@SuppressWarnings("unchecked")
	private List<Map> getPageResults(PageResults mPageResults){
		String str="";
		List<Map> results = mPageResults.getResults();
        for(int i=0;i<results.size();i++){
        	Object key=results.get(i).get("ENGINEER_CODE");
        	if(null!=key && !"".equals(key)){
        		String []value=key.toString().split(",");
            	for(int j=0;j<value.length;j++){
            		List<Map> map=mSysDeptService.getEngineeringByNm(value[j]);
            		if(str.length()>0){
            			if(map.size() > 0){            				
            				str+=map.get(0).get("engineer_name");
            			}
            		}else{
            			if(map.size() > 0){     
            				str+=map.get(0).get("engineer_name")+",";
            			}
            		}
            	}
            	results.get(i).put("engineer_name", str);
        	}
        	str="";
        }
        return results;
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
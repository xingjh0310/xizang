package com.lyht.business.materiel.action;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.bean.MaterielBase;
import com.lyht.business.materiel.control.MaterielBaseControl;
import com.lyht.business.materiel.formBean.MaterielBaseFormBean;
import com.lyht.business.system.control.SysDictControl;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/materielBase")
@Results({ @Result(name = "list", location = "/business/destroy/company/list.jsp"),
		@Result(name = "edit", location = "/business/destroy/company/edit.jsp"),
		@Result(name = "editCompany", location = "/business/destroy/companyAdd/list.jsp") })

@Controller
@Scope("prototype")
@Action("materielBase")
/**
 * @author 陈洪强 
 * 功能 ：查看物料基础数据 list;
 */
public class MaterielBaseAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("MaterielBaseAction");

	MaterielBaseFormBean materielBaseFormBean = new MaterielBaseFormBean();
	
	private File[] file; //文件
	
	private String[] fileFileName; //文件名称

	@Resource
	private	MaterielBaseControl   materielBaseControl;

	@Resource 
	private SysDictControl sysDictControl;
	// 物料基础信息列表
	@SuppressWarnings("rawtypes")
	public String list() {
		log.info("物料基础信息列表==MaterielBaseAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		
		ret = materielBaseControl.list(materielBaseFormBean,prs);
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
	// 物料基础信息列表
	@SuppressWarnings("rawtypes")
	public String all_list() {
		log.info("物料基础信息列表==MaterielBaseAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		
		ret = materielBaseControl.all_list(materielBaseFormBean,prs);
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
	//保存物料基础信息
	public String save(){
		log.info("增加物料基础信息==MaterielBaseAction.save");
		
		MaterielBase mMaterielBase = new MaterielBase();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		if (materielBaseFormBean.getMaterielBase().getId() == 0 ) {
			
			ret = materielBaseControl.save(materielBaseFormBean.getMaterielBase(), mMaterielBase);
			
		} else {
			// 根据id获取旧数据进行修改
			mMaterielBase = materielBaseControl.getBaseById(materielBaseFormBean.getMaterielBase().getId());
			// 修改单位信息
			ret = materielBaseControl.update(materielBaseFormBean.getMaterielBase(), mMaterielBase);
		}

		hashMap.put("infoBean", mMaterielBase);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		
		return null;
	}
	//删除物料基础信息
	@SuppressWarnings("rawtypes")
	public String removeids(){
		log.info("批量删除==MaterielBaseAction.removeids");
		// 获取ids
		String ids = CommonUtil.trim(materielBaseFormBean.getIds());

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		// 根据ID获取要删除的数据
		PageResults prs = new PageResults();
		prs = materielBaseControl.findByIds(ids);
		// 删除数据
		ret = materielBaseControl.delByIds(ids, prs.getResults());

		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
		
	}
	//修改物料基础信息
	public String edit(){
		log.info("修改物料信息==MaterielBaseAction.edit");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		MaterielBase mMaterielBase = new MaterielBase();
		ret = materielBaseControl.getBaseById(materielBaseFormBean.getMaterielBase().getId(), mMaterielBase);
		
		hashMap.put("infoBean", mMaterielBase);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		CommonFunction.writeResponse(getResponse(), hashMap);
		
		return null;
	}
	//导出物料数据
	@SuppressWarnings("rawtypes")
	public String downLoad(){
		log.info("导出物料数据==MaterielBaseAction.downLoad");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		materielBaseControl.downLoad(materielBaseFormBean,prs,req,res);
		return null;
	}
	//导入物料基础信息
	public String upLoad(){
		log.info("导入物料基础信息==MaterielBaseAction.upLoad");
		RetMessage ret=new RetMessage();
		ret=materielBaseControl.upLoad(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), "error");
		}else{
			CommonFunction.writeResponse(getResponse(), "success");
		}
		return null;
	}
	//计量单位
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getUnit(){
		log.info("查询计量单位==SysdictAction.getUnit");
		Hashtable mHashtable = new Hashtable();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults(); 
		String flag="jldw";
		mRetMessage= sysDictControl.getMessageType(mPageResults,flag);
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
		getResponse().setHeader("Access-Control-Allow-Origin", "*"); //允许所有跨域访问
		CommonFunction.writeResponse(getResponse(), mHashtable);
		return null;
		
	}
	/*****************WEB-APP分割线***************************/
	//app获取物料名称
	@SuppressWarnings("rawtypes")
	public String getMaterialBase(){
		
		log.info("获取物料名称==MaterielBaseAction.getMaterialBase");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();

		ret = materielBaseControl.getBase(materielBaseFormBean,prs);
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
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		
		return null;
	}
	
	
	
	public MaterielBaseFormBean getMaterielBaseFormBean() {
		return materielBaseFormBean;
	}

	public void setMaterielBaseFormBean(MaterielBaseFormBean materielBaseFormBean) {
		this.materielBaseFormBean = materielBaseFormBean;
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

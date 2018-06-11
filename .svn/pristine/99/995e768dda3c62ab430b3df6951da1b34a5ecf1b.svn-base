package com.lyht.business.pub.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.business.pub.bean.FileUpload;
import com.lyht.business.pub.control.FileUploadControl;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Namespace("/file")
@Controller
@Scope("prototype")
@Action("/upload")
public class FileUploadAction extends BaseLyhtAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("FileUploadAction"); 
	
	@Resource
	private FileUploadControl cFileUploadControl;
	
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	/**
	 * 附件关联表名
	 */
	private String tableName;
	/**
	 * 附件关联表主键
	 */
	private String tablePkColumn;
	/**
	 * 文件删除标识
	 */
	private String fileId;
	
	/**
	 * 关联工程信息code
	 */
	private String engineerCode;
	
	/**
	 * 附件上传
	 */
	public String saveFileUpload(){
		log.info("附件上传==FileUploadAction.saveFileUpload");
		RetMessage ret=new RetMessage();
		// 当前登录人信息
		SysStaff sysacct = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		String staffName=sysacct.getName();//上传人
		String tableName=getTableName();//附件关联表名
		String tablePkColumn=getTablePkColumn();//附件关联表主键
		String engineerCode=getEngineerCode();//关联工程信息code
		ret=cFileUploadControl.saveFileUpload(getFile(), getFileFileName(), tableName, tablePkColumn, staffName, engineerCode,getFileId());
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), "error");
		}else{
			CommonFunction.writeResponse(getResponse(), "success");
		}
		return null;
	}
	
	/**
	 * 查询已上传的文件
	 */
	public String queryFileUpload(){
		log.info("查询已上传的文件==FileUploadAction.queryFileUpload");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		List<FileUpload> mFileUploadList=cFileUploadControl.queryFileUpload(getTableName(), getTablePkColumn());
		JSONArray array=new JSONArray();
		String path = this.getRequest().getContextPath();
		String basePath = this.getRequest().getScheme()+"://"+this.getRequest().getServerName()+":"+this.getRequest().getServerPort()+path+"/";
		for(FileUpload mFileUpload:mFileUploadList){
			JSONObject json=new JSONObject();
			json.put("id", mFileUpload.getFileId());
			json.put("name", mFileUpload.getFileName());
			json.put("size", mFileUpload.getFileSize());
			json.put("url", basePath+mFileUpload.getFileUrl());
			json.put("type", basePath+mFileUpload.getFileType());
			array.add(json);
		}
		hashMap.put("mFileUploadList", array);
		hashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 删除已删除文件
	 */
	public String deleteFileById(){
		log.info("删除已删除文件==FileUploadAction.deleteFileById");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		ret=cFileUploadControl.deleteFileById(getFileId());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 *	app端图片上传 
	 */
	public String saveFileUpload_APP(){
		log.info("app端图片上传==FileUploadAction.saveFileUpload_APP");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String tableName=getRequest().getParameter("tableName");//附件关联表名
		String tablePkColumn=getRequest().getParameter("tablePkColumn");//附件关联表主键
		String engineerCode=getRequest().getParameter("engineerCode");//关联工程信息code
		RetMessage ret=new RetMessage();
		ret=cFileUploadControl.saveImageUpload_app(getRequest(),tablePkColumn,engineerCode,tableName);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());	
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
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
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTablePkColumn() {
		return tablePkColumn;
	}
	public void setTablePkColumn(String tablePkColumn) {
		this.tablePkColumn = tablePkColumn;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getEngineerCode() {
		return engineerCode;
	}

	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}
	
}

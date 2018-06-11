package com.lyht.business.pub.control;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.business.pub.bean.FileUpload;
import com.lyht.business.pub.service.FileUploadService;

@Controller
@Scope("prototype")
public class FileUploadControl {
	
	//调用日志־
	private static Logger log = Logger.getLogger("FileUploadControl");

	@Resource
	private FileUploadService sFileUploadService;
	
	/**
	 * 文件上传
	 * @param
	 * 1.上传文件域对象
	 * 2.上传文件名
	 * 3.附件关联表名
	 * 4.附件关联表主键
	 * 5.上传人
	 * 6.关联工程编码
	 * @throws Exception 
	 */
	public RetMessage saveFileUpload(File[] file,String[] fileFileName,String tableName,String tablePkColumn,String staffName,String engineerCode,String fileId){
		RetMessage ret=new RetMessage();
		try {
			sFileUploadService.saveFileUpload(file, fileFileName, tableName, tablePkColumn, staffName, engineerCode,fileId);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("文件上传成功");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"文件上传失败");
			e.printStackTrace();
			log.error("文件上传==错误",e);
		}
		return ret;
	}
	
	
	/**
	 * app上传图片
	 * @param file
	 * @param fileFileName
	 * @param tableName
	 * @param tablePkColumn
	 * @param staffName
	 * @param engineerCode
	 * @param fileId
	 * @return
	 */
	public RetMessage saveImageUpload_app(HttpServletRequest request,String tablePkColumn,String engineerCode,String tableName){
		RetMessage ret=new RetMessage();
		try {
			sFileUploadService.saveImageUpload_app(request,tablePkColumn,engineerCode,tableName);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("文件上传成功");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"文件上传失败");
			e.printStackTrace();
			log.error("文件上传==错误",e);
		}
		return ret;
	}
	
	public RetMessage saveImageUpload_app_test(String staffName_APP,String photoImageList,String tablePkColumn,String engineerCode,String tableName){
		RetMessage ret=new RetMessage();
		try {
			sFileUploadService.saveImageUpload_app_test(staffName_APP,photoImageList,tablePkColumn,engineerCode,tableName);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("文件上传成功");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"文件上传失败");
			e.printStackTrace();
			log.error("文件上传==错误",e);
		}
		return ret;
	}
	
	/**
	 * 查询已上传的文件
	 * @param
	 * 1.附件关联表
	 * 2.附件关联表主键
	 */
	public List<FileUpload> queryFileUpload(String tableName,String id){
		List<FileUpload> mFileUploadList=null;
		try{
			mFileUploadList=sFileUploadService.queryFileUpload(tableName, id);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("查询已上传的文件==错误",e);
		}
		return mFileUploadList;
	}
	
	/**
	 * 删除已上传文件
	 */
	public RetMessage deleteFileById(String fileId){
		RetMessage ret=new RetMessage();
		try {
			sFileUploadService.deleteFileById(fileId);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除失败！");
			e.printStackTrace();
			log.error("删除已上传文件==错误",e);
		}
		return ret;
	}
	
}

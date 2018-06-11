package com.lyht.business.pub.service;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.contracMng.bean.Image;
import com.lyht.business.pub.bean.FileUpload;
import com.lyht.business.pub.dao.FileUploadDao;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Scope("prototype")
@Transactional
public class FileUploadService {

	@Resource
	private FileUploadDao dFileUploadDao;
	
	private static final int BUFFER_SIZE = 16 * 1024;
	
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
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveFileUpload(File[] file,String[] fileFileName,String tableName,String tablePkColumn,String staffName,String engineerCode,String fileId) throws Exception{
		// 取当前日期并创建上传文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String dstPath = ServletActionContext.getServletContext().getRealPath("") + "/lyhtFile/" + ymd;
		// 如果不存在此目录，则创建该目录
		File dirFile = new File(dstPath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		// 非重复提交的情况下
		File[] srcFiles = file;
		for (int i = 0; i < srcFiles.length; i++) {
			String fileType = fileFileName[i].substring(fileFileName[i].indexOf(".") + 1, fileFileName[i].length()).toLowerCase();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			// 根据服务器的文件保存地址和原文件名创建目录文件全路径
			String url = dstPath + "\\" + uuid + "."+fileType;
			File dstFile = new File(url);
			FileUpload mFileUpload = new FileUpload();
			mFileUpload.setEngineerCode(engineerCode);//关联工程编码
			mFileUpload.setTableName(tableName);//附件关联表名
			mFileUpload.setTablePkColumn(tablePkColumn);//附件关联表主键
			mFileUpload.setFileName(fileFileName[i]);//附件名称
			mFileUpload.setFileType(fileType);//附件类型
			mFileUpload.setFileSize(CommonUtil.getFileSizes(srcFiles[i]) + "");//附件大小
			mFileUpload.setUploadStaff(staffName);//上传人
			mFileUpload.setUploadTime(DateUtil.getDateTime());//上传时间
			mFileUpload.setFileUrl("lyhtFile/" + ymd + "/" + uuid + "."+fileType);//存储路径
			mFileUpload.setFileId(fileId);
			// 写入数据库中
			dFileUploadDao.save(mFileUpload);
			// 上传文件到服务
			copy(srcFiles[i], dstFile);
		}
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
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveImageUpload_app(HttpServletRequest request,String tablePkColumn,String engineerCode,String tableName) throws Exception{
		//保存app上传的图片
		String photoImageList = request.getParameter("photoImageList");
		String staffName = request.getParameter("staffName");
		JSONArray json=JSONArray.fromObject(photoImageList);
		List<Image> imgList = JSONArray.toList(json, Image.class);
		if(imgList!=null && imgList.size()>0){
			for(int i=0;i<imgList.size();i++){
				if(imgList.get(i)!=null && imgList.get(i).getHyzpBase64()!=null 
						&& imgList.get(i).getHyzpBase64().trim().length()>0){
					String fileName = this.getImageFileName(i);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String ymd = sdf.format(new Date());
					//文件夹创建路径
					String dstPath = ServletActionContext.getServletContext().getRealPath("") + "lyhtFile\\" + ymd;
					//文件保存路径
					String saveFileName = uuid + "." + fileType;
					Map result = GenerateImage(imgList.get(i).getHyzpBase64(),saveFileName,dstPath);
					boolean flag = (boolean)result.get("flag");
					long fileSizes = (long)result.get("fileSizes");
					if(flag){
						// 根据服务器的文件保存地址和原文件名创建目录文件全路径
						FileUpload mFileUpload = new FileUpload();
						mFileUpload.setEngineerCode(engineerCode);//关联工程编码
						mFileUpload.setTableName(tableName);//附件关联表名
						mFileUpload.setTablePkColumn(tablePkColumn);//附件关联表主键
						mFileUpload.setFileName(fileName);//附件名称
						mFileUpload.setFileType(fileType);//附件类型
						mFileUpload.setFileSize(fileSizes + "");//附件大小
						mFileUpload.setUploadStaff(staffName);//上传人
						mFileUpload.setUploadTime(DateUtil.getDateTime());//上传时间
						mFileUpload.setFileUrl("lyhtFile/" + ymd + "/" + uuid + "."+fileType);//存储路径
						mFileUpload.setFileId(uuid);
						// 写入数据库中
						dFileUploadDao.save(mFileUpload);
					}
				}
			}
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveImageUpload_app_test(String staffName_APP,String photoImageList,String tablePkColumn,String engineerCode,String tableName) throws Exception{
		//保存app上传的图片
//		String photoImageList = request.getParameter("photoImageList");
//		String staffName = request.getParameter("staffName");
		JSONObject dataJson=JSONObject.fromObject(photoImageList);
		JSONArray imageJsonArr = dataJson.getJSONArray("photoImageList");
		List<Image> imgList = JSONArray.toList(imageJsonArr, Image.class);
		if(imgList!=null && imgList.size()>0){
			for(int i=0;i<imgList.size();i++){
				if(imgList.get(i)!=null && imgList.get(i).getHyzpBase64()!=null 
						&& imgList.get(i).getHyzpBase64().trim().length()>0){
					String fileName = this.getImageFileName(i);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String ymd = sdf.format(new Date());
					//文件夹创建路径
					String dstPath = ServletActionContext.getServletContext().getRealPath("") + "lyhtFile\\" + ymd;
					//文件保存路径
					String saveFileName = uuid + "." + fileType;
					Map result = GenerateImage(imgList.get(i).getHyzpBase64(),saveFileName,dstPath);
					boolean flag = (boolean)result.get("flag");
					long fileSizes = (long)result.get("fileSizes");
					if(flag){
						// 根据服务器的文件保存地址和原文件名创建目录文件全路径
						FileUpload mFileUpload = new FileUpload();
						mFileUpload.setEngineerCode(engineerCode);//关联工程编码
						mFileUpload.setTableName(tableName);//附件关联表名
						mFileUpload.setTablePkColumn(tablePkColumn);//附件关联表主键
						mFileUpload.setFileName(fileName);//附件名称
						mFileUpload.setFileType(fileType);//附件类型
						mFileUpload.setFileSize(fileSizes + "");//附件大小
						mFileUpload.setUploadStaff(staffName_APP);//上传人
						mFileUpload.setUploadTime(DateUtil.getDateTime());//上传时间
						mFileUpload.setFileUrl("lyhtFile/" + ymd + "/" + uuid + "."+fileType);//存储路径
						mFileUpload.setFileId(uuid);
						// 写入数据库中
						dFileUploadDao.save(mFileUpload);
					}
				}
			}
		}
	}
	
	/**
	 * 查询已上传的文件
	 * @param
	 * 1.附件关联表
	 * 2.附件关联表主键
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<FileUpload> queryFileUpload(String tableName,String id){
		return dFileUploadDao.queryFileUpload(tableName, id);
	}
	
	/**
	 * 删除已上传文件
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteFileById(String fileId){
		dFileUploadDao.deleteFileById(fileId);
	}
	
	private String getImageFileName(int i){
    	String fileName=CommonUtil.getDateTimeString(new Date());
		fileName=fileName.replace("-", "");
		fileName=fileName.replace(" ", "");
		fileName=fileName.replace(":", "");
		fileName=fileName+"_"+i+".jpg";
		return fileName;
    }
	
	public Map GenerateImage(String imgStr,String saveFileName,String dstPath) {  
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("flag", false);
		if (imgStr == null ||imgStr.trim().length()<1)
	        return map;  
		// 如果不存在此目录，则创建该目录
		File dirFile = new File(dstPath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
	    try {  
	        // Base64
	        byte[] b = java.util.Base64.getDecoder().decode(imgStr);  
	        //System.currentTimeMillis()  
	        String path=dstPath+File.separator+saveFileName;
	        path=path.replace("/", File.separator);
	        File file = new File(path);
	        if(!file.exists()){
	        	file.createNewFile();
	        }
	        FileOutputStream out = new FileOutputStream(file);  
            BufferedOutputStream bos = new BufferedOutputStream(out);
            bos.write(b);  //write()方法可以写入byte数组、int
            bos.flush();
            bos.close();
            long fileSizes = CommonUtil.getFileSizes(file);
            map.put("flag", true);
            map.put("fileSizes", fileSizes);
            return map;  
	    } catch (Exception e) {  
	    	e.printStackTrace();
	        return map;  
	    }  
	}
	
	// 自己封装的一个把源文件对象复制成目标文件对象
	private static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

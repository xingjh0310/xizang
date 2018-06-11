package com.lyht.business.pub.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者：李强
 * 日期：2018-03-13
 * 说明：文件上传
 */
@Entity
@Table(name = "pub_files")
public class FileUpload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;//主键ID
	private String	engineerCode;//关联工程编码
	private String	tableName;//附件关联表
	private String	tablePkColumn;//附件关联表主键
	private String	fileName;//附件名称
	private String	fileType;//附件类型
	private String	fileSize;//附件大小
	private String	uploadStaff;//上传人
	private String	uploadTime;//上传时间
	private String	fileUrl;//存储路径
	private String 	fileId;//文件删除标识
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "engineer_code")
	public String getEngineerCode() {
		return engineerCode;
	}
	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}
	
	@Column(name = "table_name")
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@Column(name = "table_pk_column")
	public String getTablePkColumn() {
		return tablePkColumn;
	}
	public void setTablePkColumn(String tablePkColumn) {
		this.tablePkColumn = tablePkColumn;
	}
	
	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "file_type")
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Column(name = "file_size")
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	@Column(name = "upload_staff")
	public String getUploadStaff() {
		return uploadStaff;
	}
	public void setUploadStaff(String uploadStaff) {
		this.uploadStaff = uploadStaff;
	}
	
	@Column(name = "upload_time")
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	@Column(name = "file_url")
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	@Column(name = "file_id")
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
}

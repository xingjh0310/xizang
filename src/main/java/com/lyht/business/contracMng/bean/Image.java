package com.lyht.business.contracMng.bean;

import java.io.Serializable;

public class Image implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String hyzpBase64;
	private String fileType;
	
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHyzpBase64() {
		return hyzpBase64;
	}
	public void setHyzpBase64(String hyzpBase64) {
		this.hyzpBase64 = hyzpBase64;
	}
}

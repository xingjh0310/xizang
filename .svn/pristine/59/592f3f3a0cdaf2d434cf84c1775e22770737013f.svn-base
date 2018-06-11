package com.lyht.business.question.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者：李强
 * 日期：2018-04-02
 * 说明：问题处理明细
 */
@Entity
@Table(name = "question_handle_detail")
public class HandlerDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;//主键
	private String  engineerCode;//关联工程信息
	private String  submitId;//关联问题上报id
	private String  fileNm;//附件关联业务码
	private String  detailTime;//明细生成时间
	private String  handleResult;//处理结果
	private String  processMethod;//处理方式
	private String  processTime;//处理时间
	private String  processPerson;//处理人
	private String  remark;//备注
	
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
	
	@Column(name = "submit_id")
	public String getSubmitId() {
		return submitId;
	}
	public void setSubmitId(String submitId) {
		this.submitId = submitId;
	}
	
	@Column(name = "file_nm")
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	
	@Column(name = "detail_time")
	public String getDetailTime() {
		return detailTime;
	}
	public void setDetailTime(String detailTime) {
		this.detailTime = detailTime;
	}
	
	@Column(name = "handle_result")
	public String getHandleResult() {
		return handleResult;
	}
	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	
	@Column(name = "process_method")
	public String getProcessMethod() {
		return processMethod;
	}
	public void setProcessMethod(String processMethod) {
		this.processMethod = processMethod;
	}
	
	@Column(name = "process_time")
	public String getProcessTime() {
		return processTime;
	}
	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}
	
	@Column(name = "process_person")
	public String getProcessPerson() {
		return processPerson;
	}
	public void setProcessPerson(String processPerson) {
		this.processPerson = processPerson;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

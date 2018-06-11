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
 * 日期：2018-03-15
 * 说明：问题上报
 */
@Entity
@Table(name = "question_submit")
public class Submit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;//主键
	private String  engineerCode;//关联工程信息
	private String  materielCode;//物料类型(编号)
	private String  title;//标题(生成)
	private String  problemType;//问题类型
	private String  problemDesc;//问题描述
	private String  reportPerson;//上报人
	private String  treenmSysDept;//上报单位
	private String  reportTime;//上报时间
	private String  processLimit;//处理期限
	private Integer state;//状态	 0.未处理	1.已处理
	private String  remark;//备注
	private String  fileNm;//附件关联业务码
	
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
	
	@Column(name = "materiel_code")
	public String getMaterielCode() {
		return materielCode;
	}
	public void setMaterielCode(String materielCode) {
		this.materielCode = materielCode;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "problem_type")
	public String getProblemType() {
		return problemType;
	}
	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}
	
	@Column(name = "problem_desc")
	public String getProblemDesc() {
		return problemDesc;
	}
	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}
	
	@Column(name = "report_person")
	public String getReportPerson() {
		return reportPerson;
	}
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}
	
	@Column(name = "treenm_sys_dept")
	public String getTreenmSysDept() {
		return treenmSysDept;
	}
	public void setTreenmSysDept(String treenmSysDept) {
		this.treenmSysDept = treenmSysDept;
	}
	
	@Column(name = "report_time")
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	
	@Column(name = "process_limit")
	public String getProcessLimit() {
		return processLimit;
	}
	public void setProcessLimit(String processLimit) {
		this.processLimit = processLimit;
	}
	
	@Column(name = "state")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "file_nm")
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
}

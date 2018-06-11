package com.lyht.business.notic.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 通知
 */
@Entity
@Table(name = "notice")
public class Notice implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 				//主键
	private String engineerCode; 		//工程信息	
	private String title; 				//标题
	private String classify; 			//分类
	private String releaseDept; 		//发布部门
	private String receiveDept;			//接收部门				
	private String context; 			//内容
	private String createPerson; 		//创建人
	private String releaseDate; 		//发布日期
	private Integer readTimes; 			//阅读次数
	private Integer state; 				//状态
	private Integer readState;			//阅读状态
	private String remark; 				//备注

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "engineer_code",length=50 )
	public String getEngineerCode() {
		return engineerCode;
	}

	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}
	
	@Column(name = "title",length=50 )
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "classify",length=20 )
	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	@Column(name = "release_dept",length=50 )
	public String getReleaseDept() {
		return releaseDept;
	}

	public void setReleaseDept(String releaseDept) {
		this.releaseDept = releaseDept;
	}
	@Column(name = "receive_dept",length=50 )
	public String getReceiveDept() {
		return receiveDept;
	}

	public void setReceiveDept(String receiveDept) {
		this.receiveDept = receiveDept;
	}
	@Column(name = "context" )
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	@Column(name = "create_person",length=10 )
	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	@Column(name = "release_date",length=30 )
	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	@Column(name = "read_times" )
	public Integer getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}
	@Column(name = "state" )
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	@Column(name = "read_state" )
	public Integer getReadState() {
		return readState;
	}

	public void setReadState(Integer readState) {
		this.readState = readState;
	}

	@Column(name = "remark",length=500 )
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	

}

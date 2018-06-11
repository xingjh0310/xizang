package com.lyht.business.refund.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者： 陈洪强 日期:2018年4月18日  说明: 退料退库
 */
@Entity
@Table(name = "billInfo")
public class Refund implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	
	private Integer id; 				//主键
	private String billCode; 			//单据编号	
	private String billTitle; 			//单据标题
	private String engineerCode; 		//工程编号
	private String libraryNum; 			//原出库单号
	private String supplierCode;		//供应商编号				
	private String singleStaff; 		//制单人
	private String singleDate; 			//制单日期
	private Integer upState;			//上报
	private Integer state; 				//状态
	private String auditStaff;			//审核人
	private String auditDate;			//审核日期
	private String explain;				//物资结余说明
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
	@Column(name = "bill_code",length=30 )
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	@Column(name = "bill_title",length=100 )
	public String getBillTitle() {
		return billTitle;
	}
	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}
	@Column(name = "engineer_code",length=50 )
	public String getEngineerCode() {
		return engineerCode;
	}
	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}
	@Column(name = "library_num",length=50 )
	public String getLibraryNum() {
		return libraryNum;
	}
	public void setLibraryNum(String libraryNum) {
		this.libraryNum = libraryNum;
	}
	@Column(name = "supplier_code",length=50 )
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	@Column(name = "single_staff",length=30 )
	public String getSingleStaff() {
		return singleStaff;
	}
	public void setSingleStaff(String singleStaff) {
		this.singleStaff = singleStaff;
	}
	@Column(name = "single_date",length=30 )
	public String getSingleDate() {
		return singleDate;
	}
	public void setSingleDate(String singleDate) {
		this.singleDate = singleDate;
	}
	@Column(name = "up_state")
	public Integer getUpState() {
		return upState;
	}
	public void setUpState(Integer upState) {
		this.upState = upState;
	}
	@Column(name = "state")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Column(name = "audit_staff",length=30 )
	public String getAuditStaff() {
		return auditStaff;
	}
	public void setAuditStaff(String auditStaff) {
		this.auditStaff = auditStaff;
	}
	@Column(name = "audit_date",length=30 )
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	@Column(name = "explain",length=500 )
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	@Column(name = "remark",length=500 )
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

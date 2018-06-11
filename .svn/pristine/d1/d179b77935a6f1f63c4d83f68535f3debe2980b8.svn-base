package com.lyht.business.refund.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者： 陈洪强 日期:2018年4月18日  说明: 退料退库单据明细
 */
@Entity
@Table(name = "bill_detial")
public class RefundDetailed implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id; 				//主键
	private String billCode; 			//单据编号	
	private String refundApply; 		//退库申请部门
	private String projectCode; 		//项目（工单）编号
	private String projectName; 		//项目（工单）编号
	private String libraryNum;			//原出库单号				
	private String supplierCode;		//工程编号				
	private String materialCode; 		//物料编号
	private String materialDesc; 		//物料描述
	private String meaUnit; 			//计量单位
	private Integer useNum; 			//原领用数量
	private Integer returnNum; 			//退回数量
	private String identySituation; 	//鉴定情况
	private String inforSitution; 		//资料完备情况
	private String useDirection; 		//拟使用去向
	private String useTime; 			//拟使用时间
	private String singleStaff; 		//制单人
	private String returnAuditor; 		//退料部门审核人
	private String currtDate; 			//审核日期
	private String surplusExplain; 		//结余物资情况说明
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
	@Column(name = "refund_apply",length=30 )
	public String getRefundApply() {
		return refundApply;
	}
	public void setRefundApply(String refundApply) {
		this.refundApply = refundApply;
	}
	@Column(name = "project_code",length=30 )
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	@Column(name = "project_name",length=30 )
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@Column(name = "library_num",length=30 )
	public String getLibraryNum() {
		return libraryNum;
	}
	public void setLibraryNum(String libraryNum) {
		this.libraryNum = libraryNum;
	}
	@Column(name = "supplier_code",length=30 )
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	@Column(name = "material_code",length=30 )
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	@Column(name = "material_desc",length=100 )
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	@Column(name = "mea_unit",length=20 )
	public String getMeaUnit() {
		return meaUnit;
	}
	public void setMeaUnit(String meaUnit) {
		this.meaUnit = meaUnit;
	}
	@Column(name = "use_num" )
	public Integer getUseNum() {
		return useNum;
	}
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}
	@Column(name = "return_num" )
	public Integer getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}
	@Column(name = "identy_situation",length=50 )
	public String getIdentySituation() {
		return identySituation;
	}
	public void setIdentySituation(String identySituation) {
		this.identySituation = identySituation;
	}
	@Column(name = "infor_situation",length=50 )
	public String getInforSitution() {
		return inforSitution;
	}
	public void setInforSitution(String inforSitution) {
		this.inforSitution = inforSitution;
	}
	@Column(name = "use_direction",length=30 )
	public String getUseDirection() {
		return useDirection;
	}
	public void setUseDirection(String useDirection) {
		this.useDirection = useDirection;
	}
	@Column(name = "use_time",length=30 )
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	@Column(name = "single_staff",length=30 )
	public String getSingleStaff() {
		return singleStaff;
	}
	public void setSingleStaff(String singleStaff) {
		this.singleStaff = singleStaff;
	}
	@Column(name = "return_auditor",length=30 )
	public String getReturnAuditor() {
		return returnAuditor;
	}
	public void setReturnAuditor(String returnAuditor) {
		this.returnAuditor = returnAuditor;
	}
	@Column(name = "currt_date",length=30 )
	public String getCurrtDate() {
		return currtDate;
	}
	public void setCurrtDate(String currtDate) {
		this.currtDate = currtDate;
	}
	@Column(name = "surplus_explain",length=50 )
	public String getSurplusExplain() {
		return surplusExplain;
	}
	public void setSurplusExplain(String surplusExplain) {
		this.surplusExplain = surplusExplain;
	}
	@Column(name = "remark",length=500 )
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

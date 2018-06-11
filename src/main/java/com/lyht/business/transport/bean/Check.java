package com.lyht.business.transport.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 物资验收单
 */
@Entity
@Table(name = "mate_acceptance")
public class Check implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 					//主键
	private String engineerCode; 			//工程信息编号
	private String acceptance; 				//验收单号
	private String materialCompany; 		//物资公司
	private String companyStaff;			//物资公司验收人
	private String confirmTime; 			//物资公司确认时间
	private Integer companyState; 			//物资公司确认状态
	private String projectSendee; 			//厂商单位
	private String projectStaff;			//厂商验收人
	private String projectConfirmTime; 		//厂商单位确认时间
	private Integer projectState; 			//厂商单位确认状态
	private String supplierDeliverer; 		//业主单位
	private String supplierStaff;			//业主验收人
	private String supplierConfirmTime; 	//业主单位确认时间
	private Integer supplierState; 			//业主单位确认状态
	private String supervisorUnit; 			//监理单位
	private String supervisorStaff;			//监理单位验收人
	private String supervisorConfirmTime; 	//监理单位确认时间
	private Integer supervisorState; 		//监理单位确认状态
	private String constructionUnit; 		//施工单位
	private String constructionStaff;		//施工单位验收人
	private String constructionConfirmTime; //施工单位确认时间
	private Integer constructionState; 		//施工单位确认状态
	private Integer acceptanceState;		//验收状态
	private String acceptanceTime;			//验收时间
	private String remark; 					//备注

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
	@Column(name = "acceptance",length=50 )
	public String getAcceptance() {
		return acceptance;
	}

	public void setAcceptance(String acceptance) {
		this.acceptance = acceptance;
	}
	
	@Column(name = "material_company",length=50 )
	public String getMaterialCompany() {
		return materialCompany;
	}

	public void setMaterialCompany(String materialCompany) {
		this.materialCompany = materialCompany;
	}
	@Column(name = "confirm_time",length=30 )
	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}
	@Column(name = "company_state")
	public Integer getCompanyState() {
		return companyState;
	}

	public void setCompanyState(Integer companyState) {
		this.companyState = companyState;
	}
	@Column(name = "project_sendee",length=30 )
	public String getProjectSendee() {
		return projectSendee;
	}

	public void setProjectSendee(String projectSendee) {
		this.projectSendee = projectSendee;
	}
	@Column(name = "project_confirm_time",length=30 )
	public String getProjectConfirmTime() {
		return projectConfirmTime;
	}

	public void setProjectConfirmTime(String projectConfirmTime) {
		this.projectConfirmTime = projectConfirmTime;
	}
	@Column(name = "project_state")
	public Integer getProjectState() {
		return projectState;
	}

	public void setProjectState(Integer projectState) {
		this.projectState = projectState;
	}
	@Column(name = "supplier_deliverer",length=30 )
	public String getSupplierDeliverer() {
		return supplierDeliverer;
	}

	public void setSupplierDeliverer(String supplierDeliverer) {
		this.supplierDeliverer = supplierDeliverer;
	}

	@Column(name = "supplier_confirm_time",length=30 )
	public String getSupplierConfirmTime() {
		return supplierConfirmTime;
	}

	public void setSupplierConfirmTime(String supplierConfirmTime) {
		this.supplierConfirmTime = supplierConfirmTime;
	}

	@Column(name = "supplier_state" )
	public Integer getSupplierState() {
		return supplierState;
	}

	public void setSupplierState(Integer supplierState) {
		this.supplierState = supplierState;
	}
	@Column(name = "supervisor_unit",length=30 )
	public String getSupervisorUnit() {
		return supervisorUnit;
	}

	public void setSupervisorUnit(String supervisorUnit) {
		this.supervisorUnit = supervisorUnit;
	}
	@Column(name = "supervisor_confirm_time",length=30 )
	public String getSupervisorConfirmTime() {
		return supervisorConfirmTime;
	}

	public void setSupervisorConfirmTime(String supervisorConfirmTime) {
		this.supervisorConfirmTime = supervisorConfirmTime;
	}
	@Column(name = "supervisor_state" )
	public Integer getSupervisorState() {
		return supervisorState;
	}

	public void setSupervisorState(Integer supervisorState) {
		this.supervisorState = supervisorState;
	}

	@Column(name = "construction_unit",length=30 )
	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}
	@Column(name = "construction_confirm_time",length=30 )
	public String getConstructionConfirmTime() {
		return constructionConfirmTime;
	}

	public void setConstructionConfirmTime(String constructionConfirmTime) {
		this.constructionConfirmTime = constructionConfirmTime;
	}
	@Column(name = "construction_state" )
	public Integer getConstructionState() {
		return constructionState;
	}

	public void setConstructionState(Integer constructionState) {
		this.constructionState = constructionState;
	}

	@Column(name = "remark",length=500 )
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "acceptance_state")
	public Integer getAcceptanceState() {
		return acceptanceState;
	}

	public void setAcceptanceState(Integer acceptanceState) {
		this.acceptanceState = acceptanceState;
	}
	@Column(name = "company_staff")
	public String getCompanyStaff() {
		return companyStaff;
	}

	public void setCompanyStaff(String companyStaff) {
		this.companyStaff = companyStaff;
	}
	@Column(name = "project_staff")
	public String getProjectStaff() {
		return projectStaff;
	}

	public void setProjectStaff(String projectStaff) {
		this.projectStaff = projectStaff;
	}
	@Column(name = "supplier_staff")
	public String getSupplierStaff() {
		return supplierStaff;
	}

	public void setSupplierStaff(String supplierStaff) {
		this.supplierStaff = supplierStaff;
	}
	@Column(name = "supervisor_staff")
	public String getSupervisorStaff() {
		return supervisorStaff;
	}

	public void setSupervisorStaff(String supervisorStaff) {
		this.supervisorStaff = supervisorStaff;
	}
	@Column(name = "construction_staff")
	public String getConstructionStaff() {
		return constructionStaff;
	}

	public void setConstructionStaff(String constructionStaff) {
		this.constructionStaff = constructionStaff;
	}
	@Column(name = "acceptance_time")
	public String getAcceptanceTime() {
		return acceptanceTime;
	}

	public void setAcceptanceTime(String acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}

	
	

}

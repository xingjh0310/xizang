package com.lyht.business.plan.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者：李强
 * 日期：2018-03-07
 * 说明：需求清单
 */
@Entity
@Table(name = "plan_demand")
public class Demand implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 关联工程编码
	 */
	private String  engineerCode;
	/**
	 * 计划年度
	 */
	private Integer planYear;
	/**
	 * 年度需求清单编号
	 */
	private String  planCode;
	/**
	 * 估算总价
	 */
	private BigDecimal  estimateTotalPrice;
	/**
	 * 施工单位
	 */
	private String  constructDept;
	/**
	 * 需求日期
	 */
	private String  planDate;
	/**
	 * 需求地点
	 */
	private String  demandPlace;
	/**
	 * 需求单位
	 */
	private String  demandUnit;
	/**
	 * 项目类型
	 */
	private String  projectType;
	/**
	 * 项目名称
	 */
	private String  projectName;
	/**
	 * 项目电压等级
	 */
	private String  projectVoltageLeve;
	/**
	 * 物资电压等级
	 */
	private String  materialVoltageLeve;
	/**
	 * 联系人
	 */
	private String  linkMan;
	/**
	 * 联系电话
	 */
	private String  linkPhone;
	/**
	 * 备注
	 */
	private String  remark;
	
	/**
	 * 主键
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 关联工程编码
	 */
	@Column(name = "engineer_code")
	public String getEngineerCode() {
		return engineerCode;
	}
	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}
	
	/**
	 * 计划年度
	 */
	@Column(name = "plan_year")
	public Integer getPlanYear() {
		return planYear;
	}
	public void setPlanYear(Integer planYear) {
		this.planYear = planYear;
	}
	
	/**
	 * 年度需求清单编号
	 */
	@Column(name = "plan_code")
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	
	/**
	 * 估算总价
	 */
	@Column(name = "estimate_total_price")
	public BigDecimal getEstimateTotalPrice() {
		return estimateTotalPrice;
	}
	public void setEstimateTotalPrice(BigDecimal estimateTotalPrice) {
		this.estimateTotalPrice = estimateTotalPrice;
	}
	
	/**
	 * 施工单位
	 */
	@Column(name = "construct_dept")
	public String getConstructDept() {
		return constructDept;
	}
	public void setConstructDept(String constructDept) {
		this.constructDept = constructDept;
	}
	
	/**
	 * 需求日期
	 */
	@Column(name = "plan_date")
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	
	/**
	 * 需求地点
	 */
	@Column(name = "demand_place")
	public String getDemandPlace() {
		return demandPlace;
	}
	public void setDemandPlace(String demandPlace) {
		this.demandPlace = demandPlace;
	}
	
	/**
	 * 需求单位
	 */
	@Column(name = "demand_unit")
	public String getDemandUnit() {
		return demandUnit;
	}
	public void setDemandUnit(String demandUnit) {
		this.demandUnit = demandUnit;
	}
	
	/**
	 * 项目类型
	 */
	@Column(name = "project_type")
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	/**
	 * 项目名称
	 */
	@Column(name = "project_name")
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	/**
	 * 项目电压等级
	 */
	@Column(name = "project_voltage_leve")
	public String getProjectVoltageLeve() {
		return projectVoltageLeve;
	}
	public void setProjectVoltageLeve(String projectVoltageLeve) {
		this.projectVoltageLeve = projectVoltageLeve;
	}
	
	/**
	 * 物资电压等级
	 */
	@Column(name = "material_voltage_leve")
	public String getMaterialVoltageLeve() {
		return materialVoltageLeve;
	}
	public void setMaterialVoltageLeve(String materialVoltageLeve) {
		this.materialVoltageLeve = materialVoltageLeve;
	}
	
	/**
	 * 联系人
	 */
	@Column(name = "link_man")
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	
	/**
	 * 联系电话
	 */
	@Column(name = "link_phone")
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	
	/**
	 * 备注
	 */
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

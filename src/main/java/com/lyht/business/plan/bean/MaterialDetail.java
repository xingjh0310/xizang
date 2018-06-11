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
 * 说明：计划物资明细
 */

@Entity
@Table(name = "plan_demand_detail")
public class MaterialDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 关联工程信息
	 */
	private String	engineerCode;
	/**
	 * 计划类型
	 * 001 需求清单
	 * 002 供货计划
	 * 003 到货计划
	 */
	private String	planType;
	/**
	 * 计划编号
	 */
	private String	contractNo;
	/**
	 * 物料编号
	 */
	private String	materielCode;
	/**
	 * 物料名称
	 */
	private String	materielName;
	/**
	 * 数量
	 */
	private BigDecimal	materielNum;
	/**
	 * 单位
	 */
	private String	materielUnit;
	/**
	 * 物资描述
	 */
	private String	materielDesc;
	/**
	 * 单价
	 */
	private BigDecimal	materielPrice;
	/**
	 * 备注
	 */
	private String	remark;
	
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
	 * 关联工程信息
	 */
	@Column(name = "engineer_code")
	public String getEngineerCode() {
		return engineerCode;
	}
	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}
	/**
	 * 计划类型
	 * 001 需求清单
	 * 002 供货计划
	 * 003 到货计划
	 */
	@Column(name = "plan_type")
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	/**
	 * 计划编号
	 */
	@Column(name = "contract_no")
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	/**
	 * 物料编号
	 */
	@Column(name = "materiel_code")
	public String getMaterielCode() {
		return materielCode;
	}
	public void setMaterielCode(String materielCode) {
		this.materielCode = materielCode;
	}
	/**
	 * 物料名称
	 */
	@Column(name = "materiel_name")
	public String getMaterielName() {
		return materielName;
	}
	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}
	/**
	 * 数量
	 */
	@Column(name = "materiel_num")
	public BigDecimal getMaterielNum() {
		return materielNum;
	}
	public void setMaterielNum(BigDecimal materielNum) {
		this.materielNum = materielNum;
	}
	/**
	 * 单位
	 */
	@Column(name = "materiel_unit")
	public String getMaterielUnit() {
		return materielUnit;
	}
	public void setMaterielUnit(String materielUnit) {
		this.materielUnit = materielUnit;
	}
	/**
	 * 物资描述
	 */
	@Column(name = "materiel_desc")
	public String getMaterielDesc() {
		return materielDesc;
	}
	public void setMaterielDesc(String materielDesc) {
		this.materielDesc = materielDesc;
	}
	/**
	 * 单价
	 */
	@Column(name = "materiel_price")
	public BigDecimal getMaterielPrice() {
		return materielPrice;
	}
	public void setMaterielPrice(BigDecimal materielPrice) {
		this.materielPrice = materielPrice;
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

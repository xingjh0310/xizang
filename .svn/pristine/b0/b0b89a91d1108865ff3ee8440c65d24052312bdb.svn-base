package com.lyht.business.contracMng.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者：张琦 日期:2018年03月07日 说明: 主合同信息
 */
@Entity
@Table(name = "cont_info")
public class ContInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; //主键
	private String engineerCode; //关联工程信息
	private String section; //标段
	private String contractNo; //合同编号
	private String contractTitle; //合同标题
	private String demandUnit; //需求单位
	private String supply; //供货厂商编号
	private String contractAmount; //合同金额
	private String biddeDate; //招标日期
	private String supplyStartDate; //供货开始日期
	private String supplyEndDate; //供货结束日期
	private String signDate; //签订日期
	private String contractStartDate; //合同开始时间
	private String contractEndDate; //合同结束时间
	private Integer contractState; //合同状态
	private String remark; //备注


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
	@Column(name = "engineer_code",length=50 )
	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}
	
	@Column(name = "section",length=30 )
	public String getSection() {
		return section;
	}
	@Column(name = "section",length=30 )
	public void setSection(String section) {
		this.section = section;
	}
	
	@Column(name = "contract_no",length=30 )
	public String getContractNo() {
		return contractNo;
	}
	@Column(name = "contract_no",length=30 )
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	@Column(name = "contract_title",length=50 )
	public String getContractTitle() {
		return contractTitle;
	}
	@Column(name = "contract_title",length=50 )
	public void setContractTitle(String contractTitle) {
		this.contractTitle = contractTitle;
	}
	
	@Column(name = "demand_unit",length=50 )
	public String getDemandUnit() {
		return demandUnit;
	}
	@Column(name = "demand_unit",length=50 )
	public void setDemandUnit(String demandUnit) {
		this.demandUnit = demandUnit;
	}
	
	@Column(name = "supply",length=50 )
	public String getSupply() {
		return supply;
	}
	@Column(name = "supply",length=50 )
	public void setSupply(String supply) {
		this.supply = supply;
	}
	
	@Column(name = "contract_amount",length=30 )
	public String getContractAmount() {
		return contractAmount;
	}
	@Column(name = "contract_amount",length=30 )
	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}
	
	@Column(name = "bidde_date",length=30 )
	public String getBiddeDate() {
		return biddeDate;
	}
	@Column(name = "bidde_date",length=30 )
	public void setBiddeDate(String biddeDate) {
		this.biddeDate = biddeDate;
	}
	
	@Column(name = "supply_start_date",length=30 )
	public String getSupplyStartDate() {
		return supplyStartDate;
	}
	@Column(name = "supply_start_date",length=30 )
	public void setSupplyStartDate(String supplyStartDate) {
		this.supplyStartDate = supplyStartDate;
	}
	
	@Column(name = "supply_end_date",length=30 )
	public String getSupplyEndDate() {
		return supplyEndDate;
	}
	@Column(name = "supply_end_date",length=30 )
	public void setSupplyEndDate(String supplyEndDate) {
		this.supplyEndDate = supplyEndDate;
	}
	
	@Column(name = "sign_date",length=30 )
	public String getSignDate() {
		return signDate;
	}
	@Column(name = "sign_date",length=30 )
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	
	@Column(name = "contract_start_date",length=30 )
	public String getContractStartDate() {
		return contractStartDate;
	}
	@Column(name = "contract_start_date",length=30 )
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	
	@Column(name = "contract_end_date",length=30 )
	public String getContractEndDate() {
		return contractEndDate;
	}
	@Column(name = "contract_end_date",length=30 )
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	
	@Column(name = "contract_state")
	public Integer getContractState() {
		return contractState;
	}
	@Column(name = "contract_state")
	public void setContractState(Integer contractState) {
		this.contractState = contractState;
	}
	
	@Column(name = "remark",length=500 )
	public String getRemark() {
		return remark;
	}
	@Column(name = "remark",length=500 )
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}

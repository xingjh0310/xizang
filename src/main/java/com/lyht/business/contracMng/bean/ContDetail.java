package com.lyht.business.contracMng.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者：张琦 日期:2018年03月14日 说明: 合同物资明细
 */
@Entity
@Table(name = "cont_detail")
public class ContDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; //主键
	private String code; //物资编号
	private String engineerCode; //关联工程信息
	private String contractNo; //合同编号
	private String section; //标段
	private String materielCode; //物料编号
	private String materielName; //物料名称
	private Integer materielNum; //数量
	private String materielUnit; //单位
	private String goodDesc; //货物描述
	private BigDecimal proposalPrice;//建议价格

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code",length=50 )
	public String getCode() {
		return code;
	}
	
	@Column(name = "code",length=50 )
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "engineer_code",length=50 )
	public String getEngineerCode() {
		return engineerCode;
	}
	
	@Column(name = "engineer_code",length=50 )
	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}

	@Column(name = "contract_no",length=50 )
	public String getContractNo() {
		return contractNo;
	}
	
	@Column(name = "contract_no",length=50 )
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	@Column(name = "section",length=30 )
	public String getSection() {
		return section;
	}
	
	@Column(name = "section",length=30 )
	public void setSection(String section) {
		this.section = section;
	}
	
	@Column(name = "materiel_code",length=50 )
	public String getMaterielCode() {
		return materielCode;
	}
	
	@Column(name = "materiel_code",length=50 )
	public void setMaterielCode(String materielCode) {
		this.materielCode = materielCode;
	}
	
	@Column(name = "materiel_name",length=30 )
	public String getMaterielName() {
		return materielName;
	}
	
	@Column(name = "materiel_name",length=30 )
	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}
	
	@Column(name = "materiel_num")
	public Integer getMaterielNum() {
		return materielNum;
	}
	
	@Column(name = "materiel_num")
	public void setMaterielNum(Integer materielNum) {
		this.materielNum = materielNum;
	}
	
	@Column(name = "materiel_unit",length=30 )
	public String getMaterielUnit() {
		return materielUnit;
	}
	
	@Column(name = "materiel_unit",length=30 )
	public void setMaterielUnit(String materielUnit) {
		this.materielUnit = materielUnit;
	}
	
	@Column(name = "good_desc",length=100 )
	public String getGoodDesc() {
		return goodDesc;
	}
	
	@Column(name = "good_desc",length=100 )
	public void setGoodDesc(String goodDesc) {
		this.goodDesc = goodDesc;
	}

	@Column(name = "proposal_price")
	public BigDecimal getProposalPrice() {
		return proposalPrice;
	}
	
	@Column(name = "proposal_price")
	public void setProposalPrice(BigDecimal proposalPrice) {
		this.proposalPrice = proposalPrice;
	}
	
	
}

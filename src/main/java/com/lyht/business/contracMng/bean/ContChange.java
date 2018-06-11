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
 * 作者：张琦 日期:2018年03月15日 说明: 合同物资变更历史记录
 */
@Entity
@Table(name = "cont_change")
public class ContChange implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; //主键
	private String code; //物资编号
	private String engineerCode; //关联工程信息
	private String section; //标段
	private String contractNo; //合同编号
	private String materielCode; //物料编号
	private String materielName; //物料名称
	private Integer preChangeNum; //变更前数量
	private Integer afterChangeNum; //变更后数量
	private BigDecimal preChangePrice; //变更前价格（不含税）
	private BigDecimal afterChangePrice; //变更后价格（不含税）
	private BigDecimal difference; //差额（不含税）
	private BigDecimal differenceTax; //差额（含税）
	private String changeTime; //变更时间
	private String operator; //操作人
	private Integer materialState; //物资状态
	

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
	
	@Column(name = "pre_change_num")
	public Integer getPreChangeNum() {
		return preChangeNum;
	}
	@Column(name = "pre_change_num")
	public void setPreChangeNum(Integer preChangeNum) {
		this.preChangeNum = preChangeNum;
	}
	
	@Column(name = "after_change_num")
	public Integer getAfterChangeNum() {
		return afterChangeNum;
	}
	@Column(name = "after_change_num")
	public void setAfterChangeNum(Integer afterChangeNum) {
		this.afterChangeNum = afterChangeNum;
	}
	
	@Column(name = "pre_change_price")
	public BigDecimal getPreChangePrice() {
		return preChangePrice;
	}
	@Column(name = "pre_change_price")
	public void setPreChangePrice(BigDecimal preChangePrice) {
		this.preChangePrice = preChangePrice;
	}
	
	@Column(name = "after_change_price")
	public BigDecimal getAfterChangePrice() {
		return afterChangePrice;
	}
	@Column(name = "after_change_price")
	public void setAfterChangePrice(BigDecimal afterChangePrice) {
		this.afterChangePrice = afterChangePrice;
	}
	
	@Column(name = "difference")
	public BigDecimal getDifference() {
		return difference;
	}
	@Column(name = "difference")
	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}
	
	@Column(name = "difference_tax")
	public BigDecimal getDifferenceTax() {
		return differenceTax;
	}
	@Column(name = "difference_tax")
	public void setDifferenceTax(BigDecimal differenceTax) {
		this.differenceTax = differenceTax;
	}
	
	@Column(name = "change_time",length=30 )
	public String getChangeTime() {
		return changeTime;
	}
	@Column(name = "change_time",length=30 )
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}
	
	@Column(name = "operator",length=30 )
	public String getOperator() {
		return operator;
	}
	@Column(name = "operator",length=30 )
	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "material_state")
	public Integer getMaterialState() {
		return materialState;
	}
	@Column(name = "material_state")
	public void setMaterialState(Integer materialState) {
		this.materialState = materialState;
	}
	
}

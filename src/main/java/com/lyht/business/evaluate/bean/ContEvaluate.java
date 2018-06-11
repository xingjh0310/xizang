package com.lyht.business.evaluate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者：张琦 日期:2018年03月12日 说明: 履约评价信息
 */
@Entity
@Table(name = "cont_evaluate")
public class ContEvaluate implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; //主键
	private String engineerCode; //关联工程信息
	private String contractNo; //合同编号
	private String evaluation; //五星评价
	private String materialArrival; //物资到货情况
	private String productQuality; //产品质量
	private String fieldService; //现场服务
	private String materialOperation; //物资投运
	private String warrantySituation; //质保情况
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
	
	@Column(name = "contract_no",length=30 )
	public String getContractNo() {
		return contractNo;
	}
	@Column(name = "contract_no",length=30 )
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	@Column(name = "evaluation",length=30 )
	public String getEvaluation() {
		return evaluation;
	}
	@Column(name = "evaluation",length=30 )
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	
	@Column(name = "material_arrival",length=50 )
	public String getMaterialArrival() {
		return materialArrival;
	}
	@Column(name = "material_arrival",length=50 )
	public void setMaterialArrival(String materialArrival) {
		this.materialArrival = materialArrival;
	}
	
	@Column(name = "product_quality",length=30 )
	public String getProductQuality() {
		return productQuality;
	}
	@Column(name = "product_quality",length=30 )
	public void setProductQuality(String productQuality) {
		this.productQuality = productQuality;
	}
	
	@Column(name = "field_service",length=30 )
	public String getFieldService() {
		return fieldService;
	}
	@Column(name = "field_service",length=30 )
	public void setFieldService(String fieldService) {
		this.fieldService = fieldService;
	}
	
	@Column(name = "material_operation",length=30 )
	public String getMaterialOperation() {
		return materialOperation;
	}
	@Column(name = "material_operation",length=30 )
	public void setMaterialOperation(String materialOperation) {
		this.materialOperation = materialOperation;
	}
	
	@Column(name = "warranty_situation",length=100 )
	public String getWarrantySituation() {
		return warrantySituation;
	}
	@Column(name = "warranty_situation",length=100 )
	public void setWarrantySituation(String warrantySituation) {
		this.warrantySituation = warrantySituation;
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

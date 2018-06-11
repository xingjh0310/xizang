package com.lyht.business.plan.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者：李强
 * 日期：2018-03-13
 * 说明：到货计划
 */
@Entity
@Table(name = "plan_tocargo")
public class Arrival implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;//主键
	private String	engineerCode;//关联工程编号
	private String	supplierPlanCode;//关联供应计划编号
	private String	supplierCode;//关联供应商编号
	private String	contractNo;//关联合同编号
	private String	arrPlanNum;//到货计划编号
	private String	planDeliveryBatch;//计划交货批次
	private String	materielCode;//物料编号
	private String	materielDesc;//物料描述
	private String	meaUnit;//计量单位
	private Integer	contractNum;//合同数量
	private Integer	planDeliverie;//计划交货数量
	private Integer	actualDeliverie;//实际交货数量
	private String	demandUnit;//需求单位
	private String	treenmSysDept;//项目单位
	private String	contractDeliveryDate;//合同交货期
	private String	confirmDeliveryDate;//确定交货期
	private String	deptDeliveryDate;//需求部门建议交货期
	private String	supplyDeliveryDate;//供应商建议交货期
	private String	applyDate;//申请日期
	private String	receiveContact;//收货联系人
	private String	linkMode;//联系方式
	private String	deliveryPlace;//交货地点
	private Integer planState;//到货计划状态		0.未上报		1.已上报
	private String	remark;//备注
	private Integer auditState;//审核状态	0.未审核		1.审核通过	2.审核拒绝
	private String  auditOpinion;//审核意见
	private String 	auditPerson;//审核人
	private String 	auditTime;//审核时间
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "engineer_code")
	public String getEngineerCode() {
		return engineerCode;
	}
	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}
	
	@Column(name = "supplier_plan_code")
	public String getSupplierPlanCode() {
		return supplierPlanCode;
	}
	public void setSupplierPlanCode(String supplierPlanCode) {
		this.supplierPlanCode = supplierPlanCode;
	}
	
	@Column(name = "supplier_code")
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	@Column(name = "contract_no")
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	@Column(name = "arr_plan_num")
	public String getArrPlanNum() {
		return arrPlanNum;
	}
	public void setArrPlanNum(String arrPlanNum) {
		this.arrPlanNum = arrPlanNum;
	}
	
	@Column(name = "Plan_delivery_batch")
	public String getPlanDeliveryBatch() {
		return planDeliveryBatch;
	}
	public void setPlanDeliveryBatch(String planDeliveryBatch) {
		this.planDeliveryBatch = planDeliveryBatch;
	}
	
	@Column(name = "materiel_code")
	public String getMaterielCode() {
		return materielCode;
	}
	public void setMaterielCode(String materielCode) {
		this.materielCode = materielCode;
	}
	
	@Column(name = "materiel_desc")
	public String getMaterielDesc() {
		return materielDesc;
	}
	public void setMaterielDesc(String materielDesc) {
		this.materielDesc = materielDesc;
	}
	
	@Column(name = "mea_unit")
	public String getMeaUnit() {
		return meaUnit;
	}
	public void setMeaUnit(String meaUnit) {
		this.meaUnit = meaUnit;
	}
	
	@Column(name = "contract_num")
	public Integer getContractNum() {
		return contractNum;
	}
	public void setContractNum(Integer contractNum) {
		this.contractNum = contractNum;
	}
	
	@Column(name = "plan_deliverie")
	public Integer getPlanDeliverie() {
		return planDeliverie;
	}
	public void setPlanDeliverie(Integer planDeliverie) {
		this.planDeliverie = planDeliverie;
	}
	
	@Column(name = "actual_deliverie")
	public Integer getActualDeliverie() {
		return actualDeliverie;
	}
	public void setActualDeliverie(Integer actualDeliverie) {
		this.actualDeliverie = actualDeliverie;
	}
	
	@Column(name = "demand_unit")
	public String getDemandUnit() {
		return demandUnit;
	}
	public void setDemandUnit(String demandUnit) {
		this.demandUnit = demandUnit;
	}
	
	@Column(name = "treenm_sys_dept")
	public String getTreenmSysDept() {
		return treenmSysDept;
	}
	public void setTreenmSysDept(String treenmSysDept) {
		this.treenmSysDept = treenmSysDept;
	}
	
	@Column(name = "contract_delivery_date")
	public String getContractDeliveryDate() {
		return contractDeliveryDate;
	}
	public void setContractDeliveryDate(String contractDeliveryDate) {
		this.contractDeliveryDate = contractDeliveryDate;
	}
	
	@Column(name = "confirm_delivery_date")
	public String getConfirmDeliveryDate() {
		return confirmDeliveryDate;
	}
	public void setConfirmDeliveryDate(String confirmDeliveryDate) {
		this.confirmDeliveryDate = confirmDeliveryDate;
	}
	
	@Column(name = "dept_delivery_date")
	public String getDeptDeliveryDate() {
		return deptDeliveryDate;
	}
	public void setDeptDeliveryDate(String deptDeliveryDate) {
		this.deptDeliveryDate = deptDeliveryDate;
	}
	
	@Column(name = "supply_delivery_date")
	public String getSupplyDeliveryDate() {
		return supplyDeliveryDate;
	}
	public void setSupplyDeliveryDate(String supplyDeliveryDate) {
		this.supplyDeliveryDate = supplyDeliveryDate;
	}
	
	@Column(name = "apply_date")
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	
	@Column(name = "receive_contact")
	public String getReceiveContact() {
		return receiveContact;
	}
	public void setReceiveContact(String receiveContact) {
		this.receiveContact = receiveContact;
	}
	
	@Column(name = "link_mode")
	public String getLinkMode() {
		return linkMode;
	}
	public void setLinkMode(String linkMode) {
		this.linkMode = linkMode;
	}
	
	@Column(name = "delivery_place")
	public String getDeliveryPlace() {
		return deliveryPlace;
	}
	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}
	
	@Column(name = "plan_state")
	public Integer getPlanState() {
		return planState;
	}
	public void setPlanState(Integer planState) {
		this.planState = planState;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "audit_state")
	public Integer getAuditState() {
		return auditState;
	}
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}
	
	@Column(name = "audit_opinion")
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	
	@Column(name = "audit_person")
	public String getAuditPerson() {
		return auditPerson;
	}
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}
	
	@Column(name = "audit_time")
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
}

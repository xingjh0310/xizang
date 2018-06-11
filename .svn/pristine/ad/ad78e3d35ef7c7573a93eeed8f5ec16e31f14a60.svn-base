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
 * 日期：2018-03-12
 * 说明：供应计划
 */
@Entity
@Table(name = "supply_plan")
public class Supply implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;//主键
	private String	supplyplantitle;//供应计划标题
	private String	engineerCode;//关联工程编号
	private String	supplierCode;//供应商编号
	private String	contractNo;//关联合同编号
	private String	supplierPlanCode;//供应计划编号
	private String	materielCode;//物料编号
	private String	meaUnit;//计量单位
	private String	materialDesc;//物料描述
	private Integer contractNum;//合同数量
	private Integer planDeliverie;//计划交货数量
	private Integer actualDeliverie;//实际交货数量
	private String	contractDeliveryDate;//合同交货期
	private String	confirmDeliveryDate;//确认交货期
	private String	deptDeliveryDate;//需求部门建议交货期
	private String	supplyDeliveryDate;//供应商建议交货期
	private String	treenmSysDept;//项目单位
	private String	linkMode;//联系方式
	private String	deliveryContact;//交货联系人
	private String 	supplyContact;//供应商联系人
	private String  supplyPhone;//供应商联系电话
	private String	deliveryPlace;//计划交货地点
	private String	applyDate;//申请日期
	private String	supplyPlanDesc;//供应计划描述
	private Integer planState;//计划状态		1.未执行	2.已执行
	private String	draweConfirmTime;//生产图纸确认时间
	private String	signTime;//技术协议签订时间
	private Integer deliveryState;//
	private String	remark;//备注
	private String 	biddingDate;//中标日期
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

	@Column(name = "supply_plan_title")
	public String getSupplyplantitle() {
		return supplyplantitle;
	}
	public void setSupplyplantitle(String supplyplantitle) {
		this.supplyplantitle = supplyplantitle;
	}
	@Column(name = "engineer_code")
	public String getEngineerCode() {
		return engineerCode;
	}
	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
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
	
	@Column(name = "supplier_plan_code")
	public String getSupplierPlanCode() {
		return supplierPlanCode;
	}
	public void setSupplierPlanCode(String supplierPlanCode) {
		this.supplierPlanCode = supplierPlanCode;
	}
	
	@Column(name = "materiel_code")
	public String getMaterielCode() {
		return materielCode;
	}
	public void setMaterielCode(String materielCode) {
		this.materielCode = materielCode;
	}
	
	@Column(name = "mea_unit")
	public String getMeaUnit() {
		return meaUnit;
	}
	public void setMeaUnit(String meaUnit) {
		this.meaUnit = meaUnit;
	}
	
	@Column(name = "material_desc")
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
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
	
	@Column(name = "treenm_sys_dept")
	public String getTreenmSysDept() {
		return treenmSysDept;
	}
	public void setTreenmSysDept(String treenmSysDept) {
		this.treenmSysDept = treenmSysDept;
	}
	
	@Column(name = "link_mode")
	public String getLinkMode() {
		return linkMode;
	}
	public void setLinkMode(String linkMode) {
		this.linkMode = linkMode;
	}
	
	@Column(name = "delivery_contact")
	public String getDeliveryContact() {
		return deliveryContact;
	}
	public void setDeliveryContact(String deliveryContact) {
		this.deliveryContact = deliveryContact;
	}
	
	@Column(name = "delivery_place")
	public String getDeliveryPlace() {
		return deliveryPlace;
	}
	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}
	
	@Column(name = "apply_date")
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	
	@Column(name = "supply_plan_desc")
	public String getSupplyPlanDesc() {
		return supplyPlanDesc;
	}
	public void setSupplyPlanDesc(String supplyPlanDesc) {
		this.supplyPlanDesc = supplyPlanDesc;
	}
	
	@Column(name = "plan_state")
	public Integer getPlanState() {
		return planState;
	}
	public void setPlanState(Integer planState) {
		this.planState = planState;
	}
	
	@Column(name = "drawe_confirm_time")
	public String getDraweConfirmTime() {
		return draweConfirmTime;
	}
	public void setDraweConfirmTime(String draweConfirmTime) {
		this.draweConfirmTime = draweConfirmTime;
	}
	
	@Column(name = "sign_time")
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "bidding_date")
	public String getBiddingDate() {
		return biddingDate;
	}
	public void setBiddingDate(String biddingDate) {
		this.biddingDate = biddingDate;
	}
	@Column(name = "supply_contact")
	public String getSupplyContact() {
		return supplyContact;
	}
	public void setSupplyContact(String supplyContact) {
		this.supplyContact = supplyContact;
	}
	@Column(name = "supply_phone")
	public String getSupplyPhone() {
		return supplyPhone;
	}
	public void setSupplyPhone(String supplyPhone) {
		this.supplyPhone = supplyPhone;
	}
	@Column(name = "delivery_state")
	public Integer getDeliveryState() {
		return deliveryState;
	}
	public void setDeliveryState(Integer deliveryState) {
		this.deliveryState = deliveryState;
	}
	
}

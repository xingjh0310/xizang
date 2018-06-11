package com.lyht.business.transport.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 物资发货
 */
@Entity
@Table(name = "mate_invoice")
public class Delivery implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 				//主键
	private String engineerCode; 		//工程信息编号
	private String handover; 			//交接单号
	private String purchaseNumber; 		//采购订单编号
	private String contractNo; 			//合同编号
	private String supplier; 			//供应商
	private String treenmSysDept; 		//项目单位
	private String supplierContact; 	//供应商联系人
	private String supplierLinkPhone; 	//供应商联系人电话
	private String carrier; 			//承运人
	private String carrierPhone; 		//承运人电话
	private String materialCode;		//物资编号
	private Integer packageNum; 		//包装件数
	private String unit;				//计量单位
	private String spec; 				//规格
	private String consignee; 			//收货人
	private String consigneePhone; 		//收货人联系电话
	private String deliveryPlace; 		//交货地点
	private String deliveryTime; 		//发货时间
	private String planTime;			//计划时间
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
	@Column(name = "engineer_code",length=50 )
	public String getEngineerCode() {
		return engineerCode;
	}

	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}
	@Column(name = "handover",length=50 )
	public String getHandover() {
		return handover;
	}

	public void setHandover(String handover) {
		this.handover = handover;
	}
	@Column(name = "purchase_number",length=50 )
	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	@Column(name = "contract_no",length=30 )
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	@Column(name = "supplier",length=30 )
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	@Column(name = "treenm_sys_dept",length=30 )
	public String getTreenmSysDept() {
		return treenmSysDept;
	}

	public void setTreenmSysDept(String treenmSysDept) {
		this.treenmSysDept = treenmSysDept;
	}

	@Column(name = "supplier_contact",length=30 )
	public String getSupplierContact() {
		return supplierContact;
	}

	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}
	@Column(name = "supplier_link_phone",length=30 )
	public String getSupplierLinkPhone() {
		return supplierLinkPhone;
	}

	public void setSupplierLinkPhone(String supplierLinkPhone) {
		this.supplierLinkPhone = supplierLinkPhone;
	}
	@Column(name = "carrier",length=30 )
	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	@Column(name = "carrier_phone",length=30 )
	public String getCarrierPhone() {
		return carrierPhone;
	}

	public void setCarrierPhone(String carrierPhone) {
		this.carrierPhone = carrierPhone;
	}
	@Column(name = "package_num" )
	public Integer getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(Integer packageNum) {
		this.packageNum = packageNum;
	}
	@Column(name = "spec",length=30 )
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	@Column(name = "consignee",length=30 )
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	@Column(name = "consignee_phone",length=30 )
	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	@Column(name = "delivery_place",length=30 )
	public String getDeliveryPlace() {
		return deliveryPlace;
	}

	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}

	@Column(name = "delivery_time",length=30 )
	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	@Column(name = "remark",length=500 )
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "material_code")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	@Column(name = "unit")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "plan_time")
	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	
	

}

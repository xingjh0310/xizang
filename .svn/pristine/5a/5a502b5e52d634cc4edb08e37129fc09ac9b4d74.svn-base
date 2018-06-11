package com.lyht.business.distributor.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者：李强
 * 日期：2018-03-08
 * 说明：供应商基础信息
 */
@Entity
@Table(name = "pub_supplier")
public class Distributor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 供应商编号
	 */
	private String  supplierCode;
	/**
	 * 供应商(全称)
	 */
	private String  supplyFullName;
	/**
	 * 供应商建成
	 */
	private String  supplyBuilt;
	/**
	 * 工商登记证号
	 */
	private String  registrationNo;
	/**
	 * 联系方式
	 */
	private String  linkPhone;
	/**
	 * 地址
	 */
	private String  address;
	/**
	 * 法人
	 */
	private String  legalPerson;
	/**
	 * 传真
	 */
	private String  fax;
	/**
	 * 人员内码
	 */
	private String listnmSysStaff;
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
	 * 供应商编号
	 */
	@Column(name = "supplier_code")
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	/**
	 * 供应商(全称)
	 */
	@Column(name = "supply_full_name")
	public String getSupplyFullName() {
		return supplyFullName;
	}
	public void setSupplyFullName(String supplyFullName) {
		this.supplyFullName = supplyFullName;
	}
	
	/**
	 * 供应商建成
	 */
	@Column(name = "supply_built")
	public String getSupplyBuilt() {
		return supplyBuilt;
	}
	public void setSupplyBuilt(String supplyBuilt) {
		this.supplyBuilt = supplyBuilt;
	}
	
	/**
	 * 工商登记证号
	 */
	@Column(name = "registration_no")
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	
	/**
	 * 联系方式
	 */
	@Column(name = "link_phone")
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	
	/**
	 * 地址
	 */
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 法人
	 */
	@Column(name = "legal_person")
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	
	/**
	 * 传真
	 */
	@Column(name = "fax")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/**
	 * 人员内码
	 */
	@Column(name = "listnm_sys_staff")
	public String getListnmSysStaff() {
		return listnmSysStaff;
	}
	public void setListnmSysStaff(String listnmSysStaff) {
		this.listnmSysStaff = listnmSysStaff;
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

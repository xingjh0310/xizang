package com.lyht.business.mail.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 通讯录信息
 */
@Entity
@Table(name = "address_book")
public class Mail implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 				//主键
	private String engineerCode; 		//工程信息	
	private String nm; 					//内码
	private String staffCode; 			//人员编号
	private String staffName; 			//人员名称
	private String treenmSysDept;		//单位编号				
	private String position; 			//职称
	private Integer sex; 				//性别
	private String linkPhone; 			//联系电话
	private String fixTele; 			//固定电话
	private String address; 			//地址
	private String email; 				//邮箱
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
	@Column(name = "nm",length=36 )
	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}
	@Column(name = "staff_code",length=36 )
	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	@Column(name = "staff_name",length=30 )
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	@Column(name = "treenm_sys_dept",length=36 )
	public String getTreenmSysDept() {
		return treenmSysDept;
	}
	
	public void setTreenmSysDept(String treenmSysDept) {
		this.treenmSysDept = treenmSysDept;
	}

	@Column(name = "position",length=30 )
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	@Column(name = "sex" )
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Column(name = "link_phone",length=30 )
	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	
	@Column(name = "address",length=100 )
	public String getAddress() {
		return address;
	}
	@Column(name = "Fix_tele",length=30 )
	public String getFixTele() {
		return fixTele;
	}

	public void setFixTele(String fixTele) {
		this.fixTele = fixTele;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "e_mail",length=30 )
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "remark",length=500 )
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	

}

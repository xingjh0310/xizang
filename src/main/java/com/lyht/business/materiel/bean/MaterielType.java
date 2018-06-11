package com.lyht.business.materiel.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 物料类型
 */
@Entity
@Table(name = "pub_materiel_type")
public class MaterielType implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 				//主键
	private String code; 				//编码
	private String name; 				//名称
	private String pid; 				//上级ID
	private String shortCode; 			//短编码
	private String fullCode; 			//全编码
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
	
	@Column(name = "code",length=30 )
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "name",length=30 )
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "pid",length=30 )
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	@Column(name = "short_code",length=30 )
	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	@Column(name = "full_code",length=30 )
	public String getFullCode() {
		return fullCode;
	}

	public void setFullCode(String fullCode) {
		this.fullCode = fullCode;
	}

	@Column(name = "remark",length=500 )
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	

}

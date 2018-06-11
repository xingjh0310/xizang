package com.lyht.business.system.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pub_engineering")
public class SysEngineerInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 * */
	private int id;
	/**
	 * 内码
	 * */
	private String nm;  
	/**
	 * 上级内码
	 * */
	private String pnm;
	/**
	 * 本级内码
	 * */
	private String thiscode;
	/**
	 * 工程编号
	 * */
	private String engineerCode;
	/**
	 * 上级编码
	 * */
	private String pcode;
	/**
	 * 工程名称
	 * */
	private String engineerName;
	/**
	 * 简称
	 * */
	private String engineerShort;
	/**
	 * 建管单位
	 * */
	private String treenmSysDept;
	/**
	 * 电压等级
	 * */
	private String voltageEvel;
	/**
	 * 建设线路长度
	 * */
	private double lineLength;
	/**
	 * 设计投运时间
	 * */
	private String deliveryTime;
	/**
	 * 状态
	 * */
	private int state;
	/**
	 * 备注
	 * */
	private String renark;
	/**
	 * 名称或标段
	 * */
	private int type;
	
	public SysEngineerInfo() {
		this.id = 0;
		this.nm = "";
		this.pnm = "";
		this.thiscode = "";
		this.engineerCode = "";
		this.pcode = "";
		this.engineerName = "";
		this.engineerShort = "";
		this.treenmSysDept = "";
		this.voltageEvel = "";
		this.lineLength = 0;
		this.deliveryTime = "";
		this.state = 0;
		this.renark = "";
		this.type = 0;
		
	}
	public SysEngineerInfo(int id, String nm, String pnm, String thiscode, String engineerCode, String pcode,
			String engineerName, String engineerShort, String treenmSysDept, String voltageEvel, double lineLength,
			String deliveryTime, int state, String renark,int type) {
		this.id = id;
		this.nm = nm;
		this.pnm = pnm;
		this.thiscode = thiscode;
		this.engineerCode = engineerCode;
		this.pcode = pcode;
		this.engineerName = engineerName;
		this.engineerShort = engineerShort;
		this.treenmSysDept = treenmSysDept;
		this.voltageEvel = voltageEvel;
		this.lineLength = lineLength;
		this.deliveryTime = deliveryTime;
		this.state = state;
		this.renark = renark;
		this.type = type;
	}
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id"   
     , unique = true
     , nullable = false 
           )
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "nm"   
		      ,length=50  
		     , unique = true
		     , nullable = false 
		           )
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	
	@Column(name = "pnm"   
		      ,length=50  
		     
		     , nullable = false 
		           )
	public String getPnm() {
		return pnm;
	}
	public void setPnm(String pnm) {
		this.pnm = pnm;
	}
	
	@Column(name = "thiscode"   
		      ,length=50  
		     
		     , nullable = false 
		           )
	public String getThiscode() {
		return thiscode;
	}
	public void setThiscode(String thiscode) {
		this.thiscode = thiscode;
	}
	
	@Column(name = "engineer_code"   
		      ,length=50  
		     
		     , nullable = false 
		           )
	public String getEngineerCode() {
		return engineerCode;
	}
	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}
	
	@Column(name = "pcode"   
		      ,length=50  
		     
		     , nullable = false 
		           )
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	
	@Column(name = "engineer_name"   
		      ,length=100  
		     
		     , nullable = false 
		           )
	public String getEngineerName() {
		return engineerName;
	}
	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	
	@Column(name = "engineer_short"   
		      ,length=50
		     
		     , nullable = false 
		           )
	public String getEngineerShort() {
		return engineerShort;
	}
	public void setEngineerShort(String engineerShort) {
		this.engineerShort = engineerShort;
	}
	
	@Column(name = "treenm_sys_dept"   
		      ,length=50
		     
		     , nullable = false 
		           )
	public String getTreenmSysDept() {
		return treenmSysDept;
	}
	public void setTreenmSysDept(String treenmSysDept) {
		this.treenmSysDept = treenmSysDept;
	}
	
	@Column(name = "voltage_evel"   
		      ,length=30
		     
		     , nullable = false 
		           )
	public String getVoltageEvel() {
		return voltageEvel;
	}
	public void setVoltageEvel(String voltageEvel) {
		this.voltageEvel = voltageEvel;
	}
	
	@Column(name = "line_length"   
		     , nullable = false 
		           )
	public double getLineLength() {
		return lineLength;
	}
	public void setLineLength(double lineLength) {
		this.lineLength = lineLength;
	}
	
	@Column(name = "delivery_time"   
		      ,length=30
		     
		     , nullable = false 
		           )
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	@Column(name = "state"   
		     , nullable = false 
		           )
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	@Column(name = "renark"   
		      ,length=500
		     
		     , nullable = false 
		           )
	public String getRenark() {
		return renark;
	}
	public void setRenark(String renark) {
		this.renark = renark;
	}
	
	@Column(name = "type" ,nullable = false)
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}

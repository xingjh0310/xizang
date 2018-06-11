package com.lyht.business.transport.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 物资交接单
 */
@Entity
@Table(name = "mate_receipt")
public class Transfer implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 					//主键
	private String engineerCode; 			//工程信息编号
	private String handover; 				//交接单号
	private String receiveParty; 			//收货方
	private String receiveConfirmTime; 		//收货时间
	private String arrivalNumber; 			//收货数量
	private String arrivalAddress; 			//收货地址
	private Integer receiveState;			//收货状态
	private String remark; 					//备注

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
	
	@Column(name = "receive_party",length=30 )
	public String getReceiveParty() {
		return receiveParty;
	}

	public void setReceiveParty(String receiveParty) {
		this.receiveParty = receiveParty;
	}

	@Column(name = "receive_confirm_time",length=30 )
	public String getReceiveConfirmTime() {
		return receiveConfirmTime;
	}

	public void setReceiveConfirmTime(String receiveConfirmTime) {
		this.receiveConfirmTime = receiveConfirmTime;
	}

	@Column(name = "remark",length=500 )
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "arrival_number")
	public String getArrivalNumber() {
		return arrivalNumber;
	}

	public void setArrivalNumber(String arrivalNumber) {
		this.arrivalNumber = arrivalNumber;
	}
	@Column(name = "arrival_address")
	public String getArrivalAddress() {
		return arrivalAddress;
	}

	public void setArrivalAddress(String arrivalAddress) {
		this.arrivalAddress = arrivalAddress;
	}
	@Column(name = "receive_state")
	public Integer getReceiveState() {
		return receiveState;
	}

	public void setReceiveState(Integer receiveState) {
		this.receiveState = receiveState;
	}

	
	

}

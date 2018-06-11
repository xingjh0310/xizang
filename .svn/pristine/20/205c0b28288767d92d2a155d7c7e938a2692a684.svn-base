package com.lyht.business.transport.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 物资交接单明细
 */
@Entity
@Table(name = "mate_receipt_detail")
public class TransferDetailed implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 				//主键
	private String engineerCode; 		//工程信息编号
	private String handover; 			//交接单号
	private String materialCode; 		//物料编号
	private String materialDesc; 		//物料描述
	private String materialUnit; 		//单位
	private Integer contractNum; 		//合同数量
	private Integer deliveryNum; 		//发货数量
	private Integer transferNum; 		//交接数量
	private String expectDeliveryDate; 	//预计发货日期
	private String arrivalDeliveryDate; //预计到货日期
	private String actualDeliveryDate; 	//实际交货日期
	

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
	@Column(name = "material_code",length=50 )
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	@Column(name = "material_desc",length=100 )
	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	@Column(name = "material_unit",length=30 )
	public String getMaterialUnit() {
		return materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	@Column(name = "contract_num" )
	public Integer getContractNum() {
		return contractNum;
	}

	public void setContractNum(Integer contractNum) {
		this.contractNum = contractNum;
	}

	@Column(name = "delivery_num" )
	public Integer getDeliveryNum() {
		return deliveryNum;
	}

	public void setDeliveryNum(Integer deliveryNum) {
		this.deliveryNum = deliveryNum;
	}

	@Column(name = "transfer_num" )
	public Integer getTransferNum() {
		return transferNum;
	}

	public void setTransferNum(Integer transferNum) {
		this.transferNum = transferNum;
	}
	@Column(name = "expect_delivery_date",length=30 )
	public String getExpectDeliveryDate() {
		return expectDeliveryDate;
	}

	public void setExpectDeliveryDate(String expectDeliveryDate) {
		this.expectDeliveryDate = expectDeliveryDate;
	}
	@Column(name = "arrival_delivery_date",length=30 )
	public String getArrivalDeliveryDate() {
		return arrivalDeliveryDate;
	}

	public void setArrivalDeliveryDate(String arrivalDeliveryDate) {
		this.arrivalDeliveryDate = arrivalDeliveryDate;
	}
	@Column(name = "actual_delivery_date",length=30 )
	public String getActualDeliveryDate() {
		return actualDeliveryDate;
	}

	public void setActualDeliveryDate(String actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}
	

	

}

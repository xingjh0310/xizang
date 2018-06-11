package com.lyht.business.materiel.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 物料基础信息
 */
@Entity
@Table(name = "pub_materiel")
public class MaterielBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 					//主键
	private String code;					//内码
	private String materielCode; 			//物料编号
	private String materielName; 			//物料名称
	private String materialNorms; 			//物料规格
	private String unit; 					//计量单位
	private String price;					//物料单价				
	private String materielDesc; 			//物料描述
	private String materialGroup; 			//物料组
	private String materielClassify; 		//物料分类
	private String unitDesc; 				//基本计量单位描述
	private Integer state; 					//是否国网标准物料
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
	@Column(name = "code",length=50 )
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "materiel_code",length=30 )
	public String getMaterielCode() {
		return materielCode;
	}

	public void setMaterielCode(String materielCode) {
		this.materielCode = materielCode;
	}
	@Column(name = "materiel_name",length=50 )
	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}
	@Column(name = "material_norms",length=30 )
	public String getMaterialNorms() {
		return materialNorms;
	}

	public void setMaterialNorms(String materialNorms) {
		this.materialNorms = materialNorms;
	}
	@Column(name = "unit",length=10 )
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "price",length=10 )
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "materiel_desc",length=100 )
	public String getMaterielDesc() {
		return materielDesc;
	}

	public void setMaterielDesc(String materielDesc) {
		this.materielDesc = materielDesc;
	}
	@Column(name = "material_group",length=30 )
	public String getMaterialGroup() {
		return materialGroup;
	}

	public void setMaterialGroup(String materialGroup) {
		this.materialGroup = materialGroup;
	}
	@Column(name = "materiel_classify",length=30 )
	public String getMaterielClassify() {
		return materielClassify;
	}

	public void setMaterielClassify(String materielClassify) {
		this.materielClassify = materielClassify;
	}
	@Column(name = "unit_desc",length=100 )
	public String getUnitDesc() {
		return unitDesc;
	}

	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
	}
	@Column(name = "state" )
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "remark",length=500 )
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	

}

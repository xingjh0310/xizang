package com.lyht.business.materiel.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.bean.MaterielType;
import com.lyht.business.materiel.bean.MaterielBase;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 物料基础信息FormBean
 */
public class MaterielBaseFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private MaterielBase materielBase = new MaterielBase();
	private MaterielType materielType = new MaterielType();

	private String searchName; // 模糊查询
	private String ids;
	
	
	@SuppressWarnings("rawtypes")
	private PageResults pageBean = new PageResults();


	public MaterielBase getMaterielBase() {
		return materielBase;
	}

	public void setMaterielBase(MaterielBase materielBase) {
		this.materielBase = materielBase;
	}
	
	public MaterielType getMaterielType() {
		return materielType;
	}

	public void setMaterialType(MaterielType materielType) {
		this.materielType = materielType;
	}

	@SuppressWarnings("rawtypes")
	public PageResults getPageBean() {
		return pageBean;
	}

	@SuppressWarnings("rawtypes")
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	
}

package com.lyht.business.system.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDept;

/**
  * 创建人： czy 
  * 脚本日期:2017年6月30日 16:37:49
  * 说明:  单位机构
  */
public class SysDeptFormBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private SysDept infoBean = new SysDept(); 
    /**
     * 用于快速模糊匹配关键字
     */
    private String searchName;
    
	/**
     * 用于批量选中多ids，以","分隔 如：1,2,3,4
     */
    private String ids;
        
    @SuppressWarnings("rawtypes")
	private PageResults pageBean=new PageResults();

	@SuppressWarnings("rawtypes")
	public PageResults getPageBean() {
		return pageBean;
	}

	@SuppressWarnings("rawtypes")
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
	
	public SysDept getInfoBean() {
		return infoBean;
	}

	public void setInfoBean(SysDept infoBean) {
		this.infoBean = infoBean;
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
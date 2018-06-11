package com.lyht.business.system.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.base.hibernate.common.ParmBean;
import com.lyht.business.system.bean.SysRela;

/**
  * 创建人： czy 
  * 脚本日期:2017年7月15日 11:10:40
  * 说明:  表关联
  */
public class SysRelaFormBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private SysRela infoBean = new SysRela(); 
    
    private ParmBean parmBean=new ParmBean();
    
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

	

	public SysRela getInfoBean() {
		return infoBean;
	}

	public void setInfoBean(SysRela infoBean) {
		this.infoBean = infoBean;
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

	public ParmBean getParmBean() {
		return parmBean;
	}

	public void setParmBean(ParmBean parmBean) {
		this.parmBean = parmBean;
	}
	
	
	
	
}
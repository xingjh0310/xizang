package com.lyht.business.system.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysStaff;

/**
  * 创建人： 陈震宇 
  * 脚本日期:2017年7月29日 21:12:01
  * 说明:  人员信息
  */
public class SysStaffFormBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private SysStaff infoBean = new SysStaff(); 
    
    
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

	public SysStaff getInfoBean() {
		return infoBean;
	}

	public void setInfoBean(SysStaff infoBean) {
		this.infoBean = infoBean;
	}
	
	
}
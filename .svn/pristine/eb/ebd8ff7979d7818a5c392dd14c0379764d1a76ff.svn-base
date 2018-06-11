package com.lyht.business.system.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysRole;

/**
  * 创建人： 陈震宇 
  * 脚本日期:2017年7月29日 14:27:06
  * 说明:  系统角色
  */
public class SysRoleFormBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private SysRole infoBean = new SysRole(); 
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

    public SysRole getInfoBean() {
		return infoBean;
	}

	public void setInfoBean(SysRole infoBean) {
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
}
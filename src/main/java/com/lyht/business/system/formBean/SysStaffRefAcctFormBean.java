package com.lyht.business.system.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysStaff;

@SuppressWarnings("rawtypes")
public class SysStaffRefAcctFormBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String ids;
	private String searchName;
	private SysStaff mSysStaffInfoBean = new SysStaff(); 
	private SysAcct mSysAcctInfoBean = new SysAcct();
	private PageResults pageBean=new PageResults();
	
	
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
	public PageResults getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
	public SysStaff getmSysStaffInfoBean() {
		return mSysStaffInfoBean;
	}
	public void setmSysStaffInfoBean(SysStaff mSysStaffInfoBean) {
		this.mSysStaffInfoBean = mSysStaffInfoBean;
	}
	public SysAcct getmSysAcctInfoBean() {
		return mSysAcctInfoBean;
	}
	public void setmSysAcctInfoBean(SysAcct mSysAcctInfoBean) {
		this.mSysAcctInfoBean = mSysAcctInfoBean;
	} 
	
}

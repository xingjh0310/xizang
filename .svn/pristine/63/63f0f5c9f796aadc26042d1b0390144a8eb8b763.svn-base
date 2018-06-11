package com.lyht.business.system.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.base.hibernate.common.ParmBean;
import com.lyht.business.system.bean.SysEngineerInfo;
import com.lyht.business.system.bean.SysRefEngineer;
import com.lyht.business.system.bean.SysStaff;

public class SysEngineerInfoFormBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ParmBean parmBean =new ParmBean();
	private SysEngineerInfo mSysEngineerInfo=new SysEngineerInfo();
	private SysRefEngineer mSysRefEngineerInfoBean=new SysRefEngineer();
	private SysStaff mSysStaff=new SysStaff();

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

	@SuppressWarnings("rawtypes")
	public PageResults getPageBean() {
		return pageBean;
	}

	@SuppressWarnings("rawtypes")
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}

	public SysEngineerInfo getmSysEngineerInfo() {
		return mSysEngineerInfo;
	}

	public void setmSysEngineerInfo(SysEngineerInfo mSysEngineerInfo) {
		this.mSysEngineerInfo = mSysEngineerInfo;
	}
	
	public SysRefEngineer getmSysRefEngineerInfoBean() {
		return mSysRefEngineerInfoBean;
	}

	public void setmSysRefEngineerInfoBean(SysRefEngineer mSysRefEngineerInfoBean) {
		this.mSysRefEngineerInfoBean = mSysRefEngineerInfoBean;
	}
	
	public ParmBean getParmBean() {
		return parmBean;
	}

	public void setParmBean(ParmBean parmBean) {
		this.parmBean = parmBean;
	}
	
	public SysStaff getmSysStaff() {
		return mSysStaff;
	}

	public void setmSysStaff(SysStaff mSysStaff) {
		this.mSysStaff = mSysStaff;
	}
	
}

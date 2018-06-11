package com.lyht.business.system.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDict;
import com.lyht.business.system.bean.SysDictCate;

/**
  * 创建人： 陈震宇 
  * 脚本日期:2017年7月29日 20:36:28
  * 说明:  字典
  */
public class SysDictFormBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private SysDict infoBean = new SysDict(); 
    private SysDictCate mSysDictCateInfoBean = new SysDictCate(); 
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


	public SysDict getInfoBean() {
		return infoBean;
	}

	public void setInfoBean(SysDict infoBean) {
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
	
	public SysDictCate getmSysDictCateInfoBean() {
		return mSysDictCateInfoBean;
	}

	public void setmSysDictCateInfoBean(SysDictCate mSysDictCateInfoBean) {
		this.mSysDictCateInfoBean = mSysDictCateInfoBean;
	}
}
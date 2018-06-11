package com.lyht.business.system.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;

/**
  * 创建人： 陈震宇 
  * 脚本日期:2017年7月29日 21:28:36
  * 说明:  账户信息
  */
public class SysAcctFormBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private SysAcct infoBean = new SysAcct(); 
    /**
     * 用于快速模糊匹配关键字
     */
    private String searchName;
    
    /**
     * 用于批量选中多ids，以","分隔 如：1,2,3,4
     */
    private String ids;
    
    private String oldPwd;//旧密码
    
    private String newPwd; //新密码
    
    private String confirmPwd; //确认密码

	@SuppressWarnings("rawtypes")
	private PageResults pageBean=new PageResults();

	public SysAcct getInfoBean() {
		return infoBean;
	}

	public void setInfoBean(SysAcct infoBean) {
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
	
	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	
}
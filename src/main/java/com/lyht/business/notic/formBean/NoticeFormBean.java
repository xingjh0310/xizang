package com.lyht.business.notic.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.notic.bean.Notice;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 通知FormBean
 */
public class NoticeFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private	Notice notice = new Notice();
	
	private String searchName; // 模糊查询
	private String ids;
	
	@SuppressWarnings("rawtypes")
	private PageResults pageBean = new PageResults();


	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
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

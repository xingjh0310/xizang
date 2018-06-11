package com.lyht.business.mail.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.mail.bean.Mail;
import com.lyht.business.mail.bean.MailType;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 通讯录FormBean
 */
public class MailFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private MailType mailType = new MailType();
	private Mail mail = new Mail(); //通讯录
	
	private String searchName; // 模糊查询
	private String ids;
	
	private String place;	//职位查询
	
	@SuppressWarnings("rawtypes")
	private PageResults pageBean = new PageResults();


	public MailType getMailType() {
		return mailType;
	}

	public void setMailType(MailType mailType) {
		this.mailType = mailType;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	
}

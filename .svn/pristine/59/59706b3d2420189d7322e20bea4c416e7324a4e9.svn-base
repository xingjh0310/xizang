package com.lyht.business.mail.control;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.mail.bean.Mail;
import com.lyht.business.mail.formBean.MailFormBean;
import com.lyht.business.mail.service.MailService;

@Controller
@Scope("prototype")
public class MailControl {
	@Resource
	private MailService mailService;

	private static Logger log = Logger.getLogger("MaterialAction");

	// 单位部门树形菜单
	@SuppressWarnings("rawtypes")
	public List<Map> zTree(PageResults prs) {

		try {

			return mailService.zTree();

		} catch (Exception e) {
			e.getStackTrace();
			log.error(e);
		}
		return null;

	}

	// 新增通讯录
	public RetMessage save(Mail infoBean, Mail retBean) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			if(checkName(infoBean.getStaffName(),infoBean.getTreenmSysDept())){
				
				BeanUtils.copyProperties(retBean, mailService.save(infoBean));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("新增新增通讯录信息成功！");
			}else{
				ret.setRetflag(RetMessage.RETFLAG_ERROR);
		    	ret.setMessage("同一部门不能出现相同名称！");
			}
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "新增新增通讯录信息失败！");
			e.printStackTrace();
		}

		return ret;
	}
	public  boolean checkName(String name,String dept){
					
		return mailService.checkMailName(name,dept);
	}

	// 查询通讯录信息根据Id
	public Mail getBaseById(Integer id) {
		Mail mail = new Mail();
		try {
			mail = mailService.getBaseById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mail;
	}

	// 查询通讯录信息根据Id
	public RetMessage getBaseById(Integer id, Mail mail) {
		RetMessage ret = new RetMessage();
		try {
			BeanUtils.copyProperties(mail, mailService.getBaseById(id));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询信息失败！");
			log.error(e);
		}
		return ret;
	}

	// 修改通讯录
	public RetMessage update(Mail infoBean, Mail retMail) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retMail, mailService.update(infoBean));

			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改通讯录信息成功！");

		} catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "修改通讯录信息失败！");
		}

		return ret;
	}

	// 通讯录列表
	@SuppressWarnings("rawtypes")
	public RetMessage list(MailFormBean mailFormBean, PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, mailService.list(mailFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}

		return ret;
	}

	// 删除通讯录

	public RetMessage delByIds(String ids) {
		RetMessage ret = new RetMessage();
		try {
			mailService.delByIds(ids);
			// results.clear();// 一定要执行此步骤，在AOP中数据就被清除
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "删除数据失败！");
		}

		return ret;
	}

	// 导入通讯录
	public RetMessage upLoad(File[] file, String[] fileFileName) {
		RetMessage ret = new RetMessage();
		try {
			mailService.upLoad(file, fileFileName);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导入通讯录成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "导入通讯录失败！");
			e.printStackTrace();
			log.error("导入通讯录错误", e);
		}
		return ret;
	}

	// 导出通讯录
	@SuppressWarnings("rawtypes")
	public RetMessage downLoad(MailFormBean infoBean, PageResults prs, HttpServletRequest request,
			HttpServletResponse response) {
		RetMessage ret = new RetMessage();
		try {
			mailService.downLoad(infoBean, prs, request, response);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导出通讯录成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "导出通讯录失败！");
			e.printStackTrace();
			log.error("导出物料基本信息错误", e);
		}
		return ret;

	}
/*************************************************************************
 ******************************* APP***************************************
 *************************************************************************
 *************************************************************************/
	
	// 通讯录列表
	@SuppressWarnings("rawtypes")
	public RetMessage app_list(MailFormBean mailFormBean,PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, mailService.app_list(mailFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		
		return ret;
	}
	//查询人员
	@SuppressWarnings("rawtypes")
	public RetMessage getStaffInfoBydeptCode(String deptCode,PageResults prs,MailFormBean mailFormBean) {
		
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,mailService.getStaffInfoBydeptCode(deptCode,mailFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		
		return ret;
		
	}

}

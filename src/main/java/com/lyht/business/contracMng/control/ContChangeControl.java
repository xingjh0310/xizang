package com.lyht.business.contracMng.control;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContChange;
import com.lyht.business.contracMng.formbean.ContChangeFormBean;
import com.lyht.business.contracMng.service.ContChangeService;

@Controller
@Scope("prototype")
public class ContChangeControl {
	@Resource
	private ContChangeService contChangeService;
	
	private static Logger log = Logger.getLogger("ContAction");
	
	//保存合同物资明细信息
	@Optlog(menuflag="cont_detail", opttype = "add")
	public RetMessage saveContChange(ContChange infoBean,ContChange retBean) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retBean,contChangeService.saveContChange(infoBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增合同物资明细变更记录成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增合同物资明细变更记录失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	public RetMessage queryChangeIds(String ids, PageResults prs) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contChangeService.queryDetailIds(ids));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	//根据ids删除多条合同物资变更记录
	@SuppressWarnings({ "rawtypes"})
	@Optlog(menuflag="cont_change", opttype = "delete")
	public RetMessage delContChangeByIds(String ids) {
		RetMessage ret=new RetMessage();
		try {
			contChangeService.delContChangeByIds(ids);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除合同物资变更记录成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除合同物资变更记录失败！");
			log.error(e);
		}
		return ret;
	}

	
	//查询变更记录列表
	@SuppressWarnings("rawtypes")
	public RetMessage list(ContChangeFormBean contChangeFormBean, PageResults prs) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contChangeService.list(contChangeFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	public RetMessage delChangeByMaterialIds(String ids, PageResults idsPrs) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(idsPrs,contChangeService.delChangeByMaterialIds(ids));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	public RetMessage delContChangeByContIds(String ids) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			contChangeService.delContChangeByContIds(ids);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改物资记录状态成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改物资记录状态成功失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

}

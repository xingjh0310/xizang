package com.lyht.business.contracMng.control;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContDetail;
import com.lyht.business.contracMng.bean.ContInfo;
import com.lyht.business.contracMng.formbean.ContDetailFormBean;
import com.lyht.business.contracMng.service.ContDetailService;

@Controller
@Scope("prototype")
public class ContDetailControl {
	@Resource
	private ContDetailService contDetailService;
	
	private static Logger log = Logger.getLogger("ContAction");
	
	//保存合同物资明细信息
	@Optlog(menuflag="cont_detail", opttype = "add")
	public RetMessage saveDetailInfo(ContDetail infoBean,ContDetail retBean) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retBean,contDetailService.saveContDetail(infoBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增合同物资明细成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增合同物资明细失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	public RetMessage queryContDetailByContNo(String contractNo, PageResults prs) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contDetailService.queryContDetailByContNo(contractNo));
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
	
	//根据ids删除多条合同物资信息
	@SuppressWarnings({ "rawtypes"})
	@Optlog(menuflag="cont_detail", opttype = "delete")
	public RetMessage delContDetailByIds(String ids) {
		RetMessage ret=new RetMessage();
		try {
			contDetailService.delContDetailByIds(ids);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除合同物资成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除合同物资失败！");
			log.error(e);
		}
		return ret;
	}
	
	//修改物资信息
	@Optlog(menuflag="cont_detail", opttype = "edit")
	public RetMessage update(ContDetail mContDetail, ContDetail retContDetail) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retContDetail,contDetailService.update(mContDetail));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改合同物资信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改合同物资信息失败！");
			log.error(e);
		}
		
		return ret;
	}

	public RetMessage queryDetailIds(String ids,PageResults prs) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contDetailService.queryDetailIds(ids));
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
	
	//获取根据id返回对象
	public ContDetail getContDetailById(Integer id) {
		ContDetail mContDetail=new ContDetail();
		try {
			mContDetail=contDetailService.getContDetailById(id);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return mContDetail;
	}

	public RetMessage getMaterialById_app(ContDetailFormBean contDetailFormBean, PageResults prs) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contDetailService.getMaterialById_app(contDetailFormBean));
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

	public RetMessage getMaterialByContNoAndMatCode_app(ContDetailFormBean contDetailFormBean, PageResults prs) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contDetailService.getMaterialByContNoAndMatCode_app(contDetailFormBean));
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
}

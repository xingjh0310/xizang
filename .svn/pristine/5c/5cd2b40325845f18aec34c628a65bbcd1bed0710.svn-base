package com.lyht.business.evaluate.control;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContInfo;
import com.lyht.business.contracMng.formbean.ContInfoFormBean;
import com.lyht.business.contracMng.service.ContInfoService;
import com.lyht.business.evaluate.bean.ContEvaluate;
import com.lyht.business.evaluate.service.ContEvaluateService;
import com.lyht.business.evaluate.formBean.ContEvaluateFormBean;

@Controller
@Scope("prototype")
public class ContEvaluateControl {
	@Resource
	private ContEvaluateService contEvaluateService;
	
	private static Logger log = Logger.getLogger("ContAction");
	
	
	//查询履约评价列表
	@SuppressWarnings("rawtypes")
	public RetMessage listContEvaluate(ContEvaluateFormBean contEvaluateFormBean, PageResults prs) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contEvaluateService.listContEvaluate(contEvaluateFormBean));
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
	
	//修改履约评价
	@Optlog(menuflag="cont_evaluate", opttype = "edit")
	public RetMessage update(ContEvaluate mContEvaluate, ContEvaluate retContEvaluate) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retContEvaluate,contEvaluateService.update(mContEvaluate));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改履约评价成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改履约评价失败！");
			log.error(e);
		}
		
		return ret;
	}

	//添加或修改履约评价时查询数据
	public RetMessage editEvaluate(ContEvaluateFormBean contEvaluateFormBean, PageResults prs) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contEvaluateService.editEvaluate(contEvaluateFormBean));
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
	
	//新增履约评价
	public RetMessage saveContEvaluate(ContEvaluate contEvaluateBean, ContEvaluate retBean) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retBean,contEvaluateService.saveContEvaluate(contEvaluateBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增履约评价成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增履约评价失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	//通过id获取履约评价
	public ContEvaluate getContEvaluateById(Integer id) {
		ContEvaluate mContEvaluate = new ContEvaluate();
		try {
			mContEvaluate=contEvaluateService.getContEvaluateById(id);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return mContEvaluate;
	}
	
	//根据ids删除多条履约评价
	@SuppressWarnings({ "rawtypes"})
	@Optlog(menuflag="cont_info", opttype = "delete")
	public RetMessage delContEvaluateByIds(String ids) {
		RetMessage ret=new RetMessage();
		try {
			contEvaluateService.delContEvaluateByIds(ids);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除履约评价成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除履约评价失败！");
			log.error(e);
		}
		return ret;
	}

	public RetMessage queryEvaluateIds(String ids, PageResults prs) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contEvaluateService.queryEvaluateIds(ids));
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

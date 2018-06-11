package com.lyht.business.transport.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.transport.bean.Check;
import com.lyht.business.transport.bean.Delivery;
import com.lyht.business.transport.bean.Transfer;
import com.lyht.business.transport.formBean.MaterielFormBean;
import com.lyht.business.transport.service.MaterielService;

@Controller
@Scope("prototype")
public class MaterielControl {
	@Resource
	private MaterielService materielService;

	private static Logger log = Logger.getLogger("MaterialAction");

	// 物资状态基础列表
	@SuppressWarnings("rawtypes")
	public RetMessage list(MaterielFormBean materielFormBean, PageResults prs,SysDept dept ) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, materielService.list(materielFormBean,dept));
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
	// 根据ID获取物资信息
	public RetMessage getDeliveryById(Integer id,Delivery retDelivery) {
		RetMessage ret = new RetMessage();
		try {
			BeanUtils.copyProperties(retDelivery, materielService.getDeliveryById(id));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询信息失败！");
		}
		return ret;
	}
	
	//导出物资发货状态
	@SuppressWarnings("rawtypes")
	public RetMessage downLoad(MaterielFormBean materielFormBean, PageResults prs, HttpServletRequest request,HttpServletResponse response,SysDept dept ) {
		RetMessage ret=new RetMessage();
		try{
			materielService.downLoad(materielFormBean,prs,request,response,dept);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导出物资状态成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导出物资状态失败！");
			e.printStackTrace();
			log.error("导出物资状态==错误",e);
		}
		return ret;
		
	}
	public RetMessage save(Transfer transferInfoBean, Transfer transfer) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(transfer, materielService.save(transferInfoBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增发货信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "新增发货信息失败！");
			e.printStackTrace();
		}

		return ret;
	}
	public RetMessage check(Check checkInfoBean, Check retCheck) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retCheck, materielService.check(checkInfoBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增发货信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "新增发货信息失败！");
			e.printStackTrace();
		}
		
		return ret;
	}
	//添加到货单号
	public void update(String ids, String handover) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			materielService.update(ids,handover);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增发货信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "新增发货信息失败！");
			e.printStackTrace();
		}
	}
	public void updateY(String ids, String handover) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			materielService.updateY(ids,handover);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增发货信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "新增发货信息失败！");
			e.printStackTrace();
		}
	}
	public int getMessageNub(MaterielFormBean materielFormBean) {
		int nub=0;
		try{
			nub=materielService.getMessageNub(materielFormBean);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("查询未读消息数量==错误",e);
		}
		return nub;
	}
	
}

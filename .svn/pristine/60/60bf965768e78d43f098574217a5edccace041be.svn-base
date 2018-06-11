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
import com.lyht.business.plan.formBean.SupplyFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.transport.bean.Delivery;
import com.lyht.business.transport.formBean.MaterielFormBean;
import com.lyht.business.transport.service.TransportService;

@Controller
@Scope("prototype")
public class TransportControl {
	@Resource
	private TransportService  transportService;

	private static Logger log = Logger.getLogger("TransportAction");

	// 物资发货基础列表
	@SuppressWarnings("rawtypes")
	public RetMessage list(MaterielFormBean materielFormBean, PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, transportService.list(materielFormBean));
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

	//导出物资发货状态
	@SuppressWarnings("rawtypes")
	public RetMessage downLoad(SupplyFormBean fSupplyFormBean, PageResults prs, HttpServletRequest request,HttpServletResponse response,SysDept dept) {
		RetMessage ret=new RetMessage();
		try{
			transportService.downLoad(fSupplyFormBean,prs,request,response,dept);
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
	public RetMessage save(Delivery infoBean, Delivery retBean) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retBean, transportService.save(infoBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增发货信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "新增发货信息失败！");
			e.printStackTrace();
		}

		return ret;
	}
	//修改供应计划发货状态
	public void update(SupplyFormBean fSupplyFormBean) {
		transportService.update(fSupplyFormBean);
		
		
	}

}

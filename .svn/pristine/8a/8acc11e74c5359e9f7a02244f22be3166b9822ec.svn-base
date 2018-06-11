package com.lyht.business.plan.control;


import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.service.MaterialDetailService;

@Controller
@Scope("prototype")
public class MaterialDetailControl {
	
	//调试日志
	private static Logger log = Logger.getLogger("MaterialDetailControl"); 
	
	@Resource
	private MaterialDetailService sMaterialDetailService;
	
	/**
	 * 根据需求清单code查询物资明细
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage queryDetailByPlanCode(String planCode,PageResults prs){
		RetMessage ret=new RetMessage();
		try {
			BeanUtils.copyProperties(prs,sMaterialDetailService.queryDetailByPlanCode(planCode));
		    ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
		    ret.setMessage("查询信息成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
			log.error("根据需求清单code查询物资明细==错误",e);
		}
		return ret;
	}

}

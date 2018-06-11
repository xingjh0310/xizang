package com.lyht.business.question.control;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.question.formBean.SubmitFormBean;
import com.lyht.business.question.service.HandlerDetailService;
import com.lyht.business.system.bean.SysDept;

@Controller
@Scope("prototype")
public class HandlerDetailControl {
	
	//调试日志
	private static Logger log = Logger.getLogger("HandlerDetailControl");
	
	@Resource
	private HandlerDetailService sHandlerDetailService;
	
	/**
	 * 查询物资问题处理明细
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage queryQuestionHandlerDetail(SubmitFormBean fSubmitFormBean,PageResults prs,SysDept dept){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,sHandlerDetailService.queryQuestionHandlerDetail(fSubmitFormBean,dept));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
			e.printStackTrace();
			log.error("查询物资问题处理明细==错误",e);
		}
		return ret;
	}

}

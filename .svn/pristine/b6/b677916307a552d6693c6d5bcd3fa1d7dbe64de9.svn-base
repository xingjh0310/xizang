package com.lyht.business.question.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.question.bean.Handler;
import com.lyht.business.question.formBean.HandlerFormBean;
import com.lyht.business.question.formBean.SubmitFormBean;
import com.lyht.business.question.service.HandlerDetailService;
import com.lyht.business.question.service.HandlerService;

@Controller
@Scope("prototype")
public class HandlerControl {
	
	//调试日志
	private static Logger log = Logger.getLogger("HandlerControl");
	
	@Resource
	private HandlerService sHandlerService;
	@Resource
	private HandlerDetailService sHandlerDetailService;
	
	/**
	 * 查询物资问题处理
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage queryQuestionHandler(SubmitFormBean fSubmitFormBean,PageResults prs){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,sHandlerService.queryQuestionHandler(fSubmitFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
			e.printStackTrace();
			log.error("查询物资问题处理==错误",e);
		}
		return ret;
	}
	
	/**
	 * 新增/修改 物资问题处理
	 */
	public RetMessage saveHandler(Handler mHandler,int SubmitId){
		RetMessage ret=new RetMessage();
		try {
			sHandlerService.saveHandler(mHandler,SubmitId);
			sHandlerDetailService.saveHandlerDetail(mHandler);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增/修改成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增/修改失败！");
			e.printStackTrace();
			log.error("新增/修改 物资问题处理==错误",e);
		}
		return ret;
	}
	
	/**
	 * 导出问题处理
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage downLoadHandlerInfo(SubmitFormBean fSubmitFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response){
		RetMessage ret=new RetMessage();
		try{
			sHandlerService.downLoadHandlerInfo(fSubmitFormBean,prs,request,response);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导出问题处理成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导出问题处理失败！");
			e.printStackTrace();
			log.error("导出问题处理==错误",e);
		}
		return ret;
	}
	
/***********************WEB-APP分割线****************************/
	@SuppressWarnings("rawtypes")
	public RetMessage count(HandlerFormBean fHandlerFormBean,PageResults pageResult) {
		RetMessage retMessage = new RetMessage();
		try {
			BeanUtils.copyProperties(pageResult, sHandlerService.count(fHandlerFormBean));
			retMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			retMessage.setMessage("查询数据成功！");
		} catch (Exception e) {
			retMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			retMessage.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询数据失败！");
			e.printStackTrace();
		}
		return retMessage;
	}

}

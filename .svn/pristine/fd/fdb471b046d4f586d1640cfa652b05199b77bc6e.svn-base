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
import com.lyht.business.question.formBean.SubmitFormBean;
import com.lyht.business.question.service.SubmitService;
import com.lyht.business.system.bean.SysDept;

@Controller
@Scope("prototype")
public class SubmitControl {
	
	//调试日志
	private static Logger log = Logger.getLogger("SubmitControl");
	
	@Resource
	private SubmitService sSubmitService;
	

	/**
	 * 查询物资问题
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage queryAllQuestion(SubmitFormBean fSubmitFormBean,PageResults prs,SysDept mSysDept){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,sSubmitService.queryAllQuestion(fSubmitFormBean,mSysDept));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
			e.printStackTrace();
			log.error("查询物资问题==错误",e);
		}
		return ret;
	}
	
	/**
	 * 新增/修改 物资问题上报
	 */
	public RetMessage saveSubmit(SubmitFormBean fSubmitFormBean){
		RetMessage ret=new RetMessage();
		try {
			sSubmitService.saveSubmit(fSubmitFormBean);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增/修改成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增/修改失败！");
			e.printStackTrace();
			log.error("新增/修改 物资问题上报==错误",e);
		}
		return ret;
	}
	
	/**
	 * 删除 物资问题上报
	 */
	public RetMessage delByIds(String ids){
		RetMessage ret=new RetMessage();
		try {
			sSubmitService.delByIds(ids);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
			e.printStackTrace();
			log.error("删除 物资问题上报==错误",e);
		}
		return ret;
	}
	
	/**
	 * 导出物资问题
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage downLoadSubmitInfo(SubmitFormBean fSubmitFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response,SysDept dept){
		RetMessage ret=new RetMessage();
		try{
			sSubmitService.downLoadSubmitInfo(fSubmitFormBean,prs,request,response,dept);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导出物资问题成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导出物资问题失败！");
			e.printStackTrace();
			log.error("导出物资问题==错误",e);
		}
		return ret;
	}
	
	/**
	 * 查询物料问题数量
	 */
	public int queryQuestionNub(SubmitFormBean fSubmitFormBean){
		int nub=0;
		try{
			nub=sSubmitService.queryQuestionNub(fSubmitFormBean);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("查询物料问题数量==错误",e);
		}
		return nub;
	}

}

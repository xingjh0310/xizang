package com.lyht.business.plan.control;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.bean.Arrival;
import com.lyht.business.plan.bean.MaterialDetail;
import com.lyht.business.plan.formBean.ArrivalFormBean;
import com.lyht.business.plan.service.ArrivalService;
import com.lyht.business.system.bean.SysDept;

@Controller
@Scope("prototype")
public class ArrivalControl {
	
	//调试日志
	private static Logger log = Logger.getLogger("ArrivalControl");
	
	@Resource
	private ArrivalService sArrivalService;

	/**
	 * 查看全部到货计划
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage queryAllArrivalInfo(ArrivalFormBean fArrivalFormBean,PageResults prs,SysDept dept){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,sArrivalService.queryAllArrivalInfo(fArrivalFormBean,dept));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
			e.printStackTrace();
			log.error("查看全部到货计划==错误",e);
		}
		return ret;
	}
	

	/**
	 * 新增/修改 到货计划
	 */
	public RetMessage saveArrival(Arrival infoBean,Arrival mArrival,MaterialDetail mMaterialDetail){
		RetMessage ret=new RetMessage();
		try {
			BeanUtils.copyProperties(infoBean,sArrivalService.saveArrival(mArrival,mMaterialDetail));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增/修改成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增/修改失败！");
			e.printStackTrace();
			log.error("新增/修改 到货计划==错误",e);
		}
		return ret;
	}
	
	/**
	 * 根据IDS获取到List
	 */
	@SuppressWarnings("rawtypes")
	public PageResults listByIds(String ids){
		PageResults prs=new PageResults();
		try {
			prs=sArrivalService.findByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据IDS获取到货计划List==错误",e);
		}
		return prs;
	}
	
	/**
	 * 批量删除信息
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage delByIds(String ids,List list){
		RetMessage ret=new RetMessage();
		try {
			sArrivalService.delByIds(ids);
			list.clear();//一定要执行此步骤，在AOP中数据就被清除
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
			e.printStackTrace();
			log.error("批量删除到货计划==错误",e);
		}
		return ret;
	}
	
	/**
	 * 上报 批量上报
	 */
	public RetMessage reported(ArrivalFormBean fArrivalFormBean){
		RetMessage ret=new RetMessage();
		try{
			sArrivalService.reported(fArrivalFormBean);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("上报成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"上报失败！");
			e.printStackTrace();
			log.error("上报 批量上报 到货计划==错误",e);
		}
		return ret;
	}
	
	/**
	 * 查询到货计划数量
	 */
	public int queryArrivalNub(ArrivalFormBean fArrivalFormBean){
		int nub=0;
		try{
			nub=sArrivalService.queryArrivalNub(fArrivalFormBean);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("查询到货计划数量==错误",e);
		}
		return nub;
	}

	/**
	 * 到货计划审核
	 */
	public RetMessage saveAuditInfo(ArrivalFormBean fArrivalFormBean){
		RetMessage ret=new RetMessage();
		try{
			sArrivalService.saveAuditInfo(fArrivalFormBean);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("审核成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"审核失败！");
			e.printStackTrace();
			log.error("到货计划审核==错误",e);
		}
		return ret;
	}
	
	/*************WEB-APP分割线************************/
	@SuppressWarnings("rawtypes")
	public List<Map> listChartsNum(ArrivalFormBean fArrivalFormBean) {
		
		List<Map> carCharts=null;
		try {
			carCharts = sArrivalService.listChartsNum(fArrivalFormBean);
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return carCharts;
		
	}
}

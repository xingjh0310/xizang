package com.lyht.business.plan.control;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.bean.MaterialDetail;
import com.lyht.business.plan.bean.Supply;
import com.lyht.business.plan.formBean.SupplyFormBean;
import com.lyht.business.plan.service.SupplyService;
import com.lyht.business.system.bean.SysDept;

@Controller
@Scope("prototype")
public class SupplyControl {
	
	//调试日志
	private static Logger log = Logger.getLogger("SupplyControl"); 
	
	@Resource
	private SupplyService sSupplyService;
	
	/**
	 * 导出供货计划
	 */
	@SuppressWarnings("rawtypes")
	public void downLoadSupplyInfo(SupplyFormBean fSupplyFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response,SysDept dept){
		try{
			sSupplyService.downLoadSupplyInfo(fSupplyFormBean,prs,request,response,dept);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("导出供货计划==错误",e);
		}
	}
	
	/**
	 * 查看全部供货计划
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage queryAllSupplyInfo(SupplyFormBean fSupplyFormBean,PageResults prs,SysDept dept){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,sSupplyService.queryAllSupplyInfo(fSupplyFormBean,dept));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
			e.printStackTrace();
			log.error("查看全部供货计划==错误",e);
		}
		return ret;
	}
	
	/**
	 * 新增供货计划
	 */
	public RetMessage saveSupply(Supply fSupplyFormBean,Supply mSupply,MaterialDetail mMaterialDetail){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(mSupply,sSupplyService.saveSupply(fSupplyFormBean,mMaterialDetail));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
			e.printStackTrace();
			log.error("新增供货计划==错误",e);
		}
		return ret;
	}
	
	/**
	 * 修改 供货计划
	 */
	public RetMessage UpdateSupply(Supply mSupply,MaterialDetail mMaterialDetail){
		RetMessage ret=new RetMessage();
		try{
			sSupplyService.UpdateSupply(mSupply,mMaterialDetail);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改信息成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
			e.printStackTrace();
			log.error("修改 供货计划==错误",e);
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
			prs=sSupplyService.findByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据IDS获取到供货计划List==错误",e);
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
			sSupplyService.delByIds(ids);
			list.clear();//一定要执行此步骤，在AOP中数据就被清除
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
			e.printStackTrace();
			log.error("批量删除供货计划==错误",e);
		}
		return ret;
	}
	
	/**
	 * 更改中标日期
	 */
	public RetMessage updateBiddingDate(SupplyFormBean fSupplyFormBean){
		RetMessage ret=new RetMessage();
		try {
			sSupplyService.updateBiddingDate(fSupplyFormBean);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("更改中标日期成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"更改中标日期失败！");
			e.printStackTrace();
			log.error("更改中标日期==错误",e);
		}
		return ret;
	}
	
	/**
	 * 更新图纸、协议日期
	 */
	public RetMessage updateTime(SupplyFormBean fSupplyFormBean){
		RetMessage ret=new RetMessage();
		try {
			sSupplyService.updateTime(fSupplyFormBean);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("更新图纸、协议日期成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"更新图纸、协议日期失败！");
			e.printStackTrace();
			log.error("更新图纸、协议日期==错误",e);
		}
		return ret;
	}
	
	/**
	 * 查询供货计划数量
	 */
	public int querySupplyNub(SupplyFormBean fSupplyFormBean){
		int nub=0;
		try {
			nub=sSupplyService.querySupplyNub(fSupplyFormBean);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("查询供货计划数量==错误",e);
		}
		return nub;
	}
	
	/**
	 * 查询供货计划信息--APP
	 */
	public List<Supply> querySupplyInfo_App(){
		List<Supply> supplyList=null;
		try {
			supplyList=sSupplyService.querySupplyInfo_App();
		}catch (Exception e) {
			e.printStackTrace();
			log.error("查询供货计划信息--APP",e);
		}
		return supplyList;
	}
	
}

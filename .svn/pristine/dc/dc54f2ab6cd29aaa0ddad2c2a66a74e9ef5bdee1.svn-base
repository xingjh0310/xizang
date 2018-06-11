package com.lyht.business.plan.control;

import java.io.File;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.formBean.MaterielBaseFormBean;
import com.lyht.business.plan.bean.Demand;
import com.lyht.business.plan.bean.MaterialDetail;
import com.lyht.business.plan.formBean.DemandFormBean;
import com.lyht.business.plan.formBean.MaterialDetailFormBean;
import com.lyht.business.plan.service.DemandService;
import com.lyht.business.plan.service.MaterialDetailService;

@Controller
@Scope("prototype")
public class DemandControl {
	
	//调试日志
	private static Logger log = Logger.getLogger("DemandControl"); 
	
	@Resource
	private DemandService sDemandService;
	@Resource
	private MaterialDetailService sMaterialDetailService;
	
	/**
	 * 导入需求清单数据
	 */
	public RetMessage importDemandInfo(File[] file,String[] fileFileName){
		RetMessage ret=new RetMessage();
		try {
			sDemandService.importDemandInfo(file,fileFileName);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导入需求清单成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入需求清单失败！");
			e.printStackTrace();
			log.error("导入需求清单数据==错误",e);
		}
		return ret;
	}
	
	/**
	 * 查看全部需求清单
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage queryAllDemandInfo(DemandFormBean fDemandFormBean,PageResults prs){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,sDemandService.queryAllDemandInfo(fDemandFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
			e.printStackTrace();
			log.error("查看全部需求清单==错误",e);
		}
		return ret;
	}
	
	/**
	 * 导出需求清单
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage downLoadDemandInfo(DemandFormBean fDemandFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response){
		RetMessage ret=new RetMessage();
		try{
			sDemandService.downLoadDemandInfo(fDemandFormBean,prs,request,response);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导出需求清单成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导出需求清单失败！");
			e.printStackTrace();
			log.error("导出需求清单==错误",e);
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
			prs=sDemandService.findByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据IDS获取到需求清单List==错误",e);
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
			sDemandService.delByIds(ids);
			list.clear();//一定要执行此步骤，在AOP中数据就被清除
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
			e.printStackTrace();
			log.error("批量删除需求清单==错误",e);
		}
		return ret;
	}
	
	/**
	 * 查看单个需求清单详情
	 */
	public RetMessage view(int id,Demand mDemand){
		RetMessage ret=new RetMessage();
		try {
			BeanUtils.copyProperties(mDemand,sDemandService.seeGet(id));
		    ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
		    ret.setMessage("查询信息成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
			log.error("查看单个需求清单详情==错误",e);
		}
		return ret;
	}
	
	/**
	 * 根据id查询需求清单信息
	 */
	public Demand getByid(int id){
		Demand mDemand=new Demand();
		try{
			mDemand=sDemandService.seeGet(id);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("根据id查询需求清单信息==错误",e);
		}
		return mDemand;
	}
	
	/**
	 * 修改需求清单信息
	 * 
	 * String[] materielIds = parameterMap.get("materielId");//物料ID
	 * String[] materielCodes = parameterMap.get("materielCode");//物料编号
	 * String[] materielUnits = parameterMap.get("materielUnit");//计量单位
	 * String[] materielNums = parameterMap.get("materielNum");//数量
	 * String[] goodDescs = parameterMap.get("goodDesc");//货物描述
	 * String[] codes = parameterMap.get("code");//物资编号
	 * 
	 */
	public RetMessage update(MaterialDetailFormBean fMaterialDetailFormBean,Demand mDemand,Demand retBean,String[] materielIds,String[] materielCodes,String[] materielUnits,String[] materielNums,String[] goodDescs,String[] codes){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retBean,sDemandService.update(mDemand));
			sMaterialDetailService.update(fMaterialDetailFormBean,mDemand,materielIds,materielCodes,materielUnits,materielNums,goodDescs,codes);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改信息成功！");
		}catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
			log.error("修改需求清单信息==错误",e);
		}
		return ret;
	}
	
	/**
	 * 查询全部需求单位
	 */
	public List<String> queryDemand(){
		List<String> demandList=null;
		try{
			demandList=sDemandService.queryDemand();
		}catch (Exception e) {
			e.printStackTrace();
			log.error("查询全部需求清单==错误",e);
		}
		return demandList;
	}
	
	/**
	 * 查询需求清单数量
	 */
	public int queryDemandPlanNub(DemandFormBean fDemandFormBean){
		int nub=0;
		try{
			nub=sDemandService.queryDemandPlanNub(fDemandFormBean);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("查询需求清单数量==错误",e);
		}
		return nub;
	}
	
	/**
	 * 查询物资信息
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage queryMaterielBase(MaterielBaseFormBean infoBean, PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, sDemandService.queryMaterielBase(infoBean));
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

	/*************WEB-APP分割线************************/
	
	/**
	 * 修改需求清单信息
	 */
	public RetMessage updateDemandInfo_APP(Demand mDemand,Demand retBean){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retBean,sDemandService.update(mDemand));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改信息成功！");
		}catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
			log.error("修改需求清单信息_APP==错误",e);
		}
		return ret;
	}
	
	/**
	 * 修改物资信息
	 */
	public RetMessage updateMaterial_APP(MaterialDetail mMaterialDetail,Demand mDemand){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			sMaterialDetailService.updateMaterial_APP(mMaterialDetail,mDemand);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改信息成功！");
		}catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
			log.error("修改需求清单信息_APP==错误",e);
		}
		return ret;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Map> listChartsNum(DemandFormBean fDemandFormBean) {
		
		List<Map> carCharts=null;
		try {
			carCharts = sDemandService.listChartsNum(fDemandFormBean);
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return carCharts;
		
	}
	//时间获取
	@SuppressWarnings("rawtypes")
	public List<Map> getAllYear() {
			List<Map> allYear=null;
			try{
				allYear=sDemandService.getAllYear();
				return allYear;
			}catch (Exception e) {
				e.getStackTrace();
				log.error("所有年份==错误",e);
			}
			return allYear;
		}

	//根据ids删除多条合同物资信息
	public RetMessage delDetailByIds(String ids) {
		RetMessage ret=new RetMessage();
		try {
			sDemandService.delDetailByIds(ids);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除物资成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除物资失败！");
			log.error(e);
		}
		return ret;
	}
}

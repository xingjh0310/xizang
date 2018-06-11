package com.lyht.business.distributor.control;

import java.io.File;
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
import com.lyht.business.distributor.bean.Distributor;
import com.lyht.business.distributor.formBean.DistributorFormBean;
import com.lyht.business.distributor.service.DistributorService;

@Controller
@Scope("prototype")
public class DistributorControl {
	
	//调试日志
	private static Logger log = Logger.getLogger("DistributorControl"); 
	
	@Resource
	private DistributorService sDistributorService;
	
	/**
	 * 导入供应商信息
	 */
	public RetMessage importDistributorInfo(File[] file,String[] fileFileName){
		RetMessage ret=new RetMessage();
		try {
			sDistributorService.importDistributorInfo(file,fileFileName);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导入供应商信息成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入供应商信息失败！");
			e.printStackTrace();
			log.error("导入供应商信息==错误",e);
		}
		return ret;
	}
	
	/**
	 * 导出供应商信息
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage downLoadDistributorInfo(DistributorFormBean fDistributorFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response){
		RetMessage ret=new RetMessage();
		try{
			sDistributorService.downLoadDistributorInfo(fDistributorFormBean,prs,request,response);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导出供应商信息成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导出供应商信息失败！");
			e.printStackTrace();
			log.error("导出需求清单==错误",e);
		}
		return ret;
	}

	/**
	 * 查看供应商信息
	 */
	@SuppressWarnings("rawtypes")
	public RetMessage queryAllDistributor(DistributorFormBean fDistributorFormBean,PageResults prs){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,sDistributorService.queryAllDistributor(fDistributorFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
			e.printStackTrace();
			log.error("查看供应商信息==错误",e);
		}
		return ret;
	}
	
	/**
	 * 查看单个供应商信息
	 */
	public RetMessage view(int id,Distributor mDistributor){
		RetMessage ret=new RetMessage();
		try {
			 BeanUtils.copyProperties(mDistributor,sDistributorService.seeGet(id));
			 ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
			log.error("查看单个供应商信息==错误",e);
		}
		return ret;
	}
	
	/**
	 * 新增供应商信息 
	 */
	public RetMessage saveDistributor(Distributor mDistributorFormBean,Distributor mDistributor){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(mDistributor,sDistributorService.saveDistributor(mDistributorFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
			e.printStackTrace();
			log.error("新增供应商信息==错误",e);
		}
		return ret;
	}
	
	/**
	 * 根据id查询供应商信息
	 */
	public Distributor getByid(int id){
		Distributor mDistributor = new Distributor();
		try {
			mDistributor=sDistributorService.seeGet(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据id查询供应商信息==错误",e);
		}
		return mDistributor;
	}
	
	/**
	 * 修改供应商信息 
	 */
	public RetMessage updateDistributor(Distributor mDistributor,Distributor retBean){
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retBean,sDistributorService.updateDistributor(mDistributor));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
			e.printStackTrace();
			log.error("修改供应商信息==错误",e);
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
			prs=sDistributorService.findByIds(ids);
		} catch (Exception e) {
			log.error("根据IDS获取到供应商List==错误",e);
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
			sDistributorService.delByIds(ids);
			list.clear();//一定要执行此步骤，在AOP中数据就被清除
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
			log.error("批量删除供应商信息==错误",e);
		}
		return ret;
	}
	
}

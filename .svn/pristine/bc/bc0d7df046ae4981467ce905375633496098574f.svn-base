package com.lyht.business.system.control;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.formBean.SysStaffFormBean;
import com.lyht.business.system.service.SysStaffService;

@Controller
@Scope("prototype")
public class SysStaffControl {
	 
	@Resource 
	private SysStaffService sysStaffService;
	
	//根据ID获取JavaBean对象
	public SysStaff getByid(int id){
		SysStaff retBean=new SysStaff();
		try {
			retBean=sysStaffService.s_get(id);
		} catch (Exception e) {
		}
		return retBean;
	}
	
	@Optlog(menuflag="menu_system_sysstaff", opttype = "view")  //记录日志
	public RetMessage view(int id,SysStaff retBean){
		RetMessage ret=new RetMessage();
		try {
				BeanUtils.copyProperties(retBean,sysStaffService.s_get(id));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return ret;
	}
	
	/**
	 * 
	 * @param infoBean--需要保存的新数据
	 * @param retBean --保存后返回的数据
	 * @return
	 */
	@Optlog(menuflag="menu_system_sysstaff", opttype = "add")  //记录日志
	public RetMessage create(SysStaff infoBean,SysStaff retBean){
		RetMessage ret=new RetMessage();
		try {
			    if(checkOnlyByCodeName(infoBean) && checkOnlyByPhone(infoBean)){
			    	BeanUtils.copyProperties(retBean,sysStaffService.s_create(infoBean));
					ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
					ret.setMessage("新增信息成功！");
			    }else if(!checkOnlyByPhone(infoBean)){
			    	ret.setRetflag(RetMessage.RETFLAG_ERROR);
			    	ret.setMessage("手机号码不能重复！");
			    }else{
			    	ret.setRetflag(RetMessage.RETFLAG_ERROR);
			    	ret.setMessage("人员编号或人员名称不能重复！");
			    }
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return ret;
	}
	
	/**
	 * 
	 * @param infoBean --需要修改的数据
	 * @param retBean  --传入旧数据，返回新数据
	 * @return
	 */
	@Optlog(menuflag="menu_system_sysstaff",opttype = "edit")  //记录日志
	public RetMessage update(SysStaff infoBean,SysStaff retBean){
		RetMessage ret=new RetMessage();
		try {
			   if(checkOnlyByCodeName(infoBean) && checkOnlyByPhone(infoBean)){
				   BeanUtils.copyProperties(retBean,sysStaffService.s_update(infoBean));
				   ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				   ret.setMessage("修改信息成功！");
			   }else if(!checkOnlyByPhone(infoBean)){
			    	ret.setRetflag(RetMessage.RETFLAG_ERROR);
			    	ret.setMessage("手机号码不能重复！");
			    }else{
				   ret.setRetflag(RetMessage.RETFLAG_ERROR);
			       ret.setMessage("人员名称不能重复！");
			   }
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return ret;
	}
	
	//验证人员编码与人员名称是否唯一
	private boolean checkOnlyByCodeName(SysStaff mSysStaff){
	   return sysStaffService.checkOnlyByCodeName(mSysStaff);
	}
	
	//验证手机号码是否唯一
	private boolean checkOnlyByPhone(SysStaff mSysStaff){
		return sysStaffService.checkOnlyByPhone(mSysStaff);
	}
	
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_sysstaff",opttype = "list")  //记录日志
	public RetMessage list(SysStaffFormBean formBean,PageResults prs){
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
				BeanUtils.copyProperties(prs,sysStaffService.s_findAll(formBean));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			e.printStackTrace();
		}
		
		return ret;
	}
	
	//获取人员信息
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_sysstaff",opttype = "getStaffInfos")  //记录日志
	public RetMessage getStaffInfos(PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		
		try{
			BeanUtils.copyProperties(mPageResults,sysStaffService.findAll());
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			e.printStackTrace();
		}
		return mRetMessage;
	}
	
	//根据人员名称获取人员信息
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_sysstaff",opttype = "getStaffInfosByName")  //记录日志
	public RetMessage getStaffInfosByName(PageResults mPageResults,String name){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,sysStaffService.findName(name));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			e.getStackTrace();
		}
		return mRetMessage;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Optlog(menuflag="menu_system_sysstaff",opttype = "flag")  //记录日志
	public RetMessage flag(String ids,List list,int flag_new){
		RetMessage ret=new RetMessage();
		try {
			    sysStaffService.s_flagByIds(ids,flag_new);
				list.clear();
				list.addAll(sysStaffService.s_findByIds(ids).getResults());
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("审核数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"审核数据失败！");
		}
		
		return ret;
		
	}
	
	@SuppressWarnings({ "rawtypes"})
	@Optlog(menuflag="menu_system_sysstaff",opttype = "del")  //记录日志
	public RetMessage delByIds(String ids,List list){
		
		RetMessage ret=new RetMessage();
		try {
			    sysStaffService.s_delByIds(ids);
				list.clear();//一定要执行此步骤，在AOP中数据就被清除
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("删除数据成功！");
			
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		
		return ret;
		
	}
	
	//导入人员信息数据
	@Optlog(menuflag="menu_system_sysstaff",opttype = "importStaffInfo")  //记录日志
	public RetMessage importStaffInfo(File[] file,String[] fileName,String deptNm){
		RetMessage mRetMessage=new RetMessage();
		try{
			sysStaffService.importStaffInfo(file,fileName,deptNm);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("导入人员信息数据成功！");
		}catch(Exception e){
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入人员信息失败！");
		    e.getStackTrace();
		}
		return mRetMessage;
	}
	
	//根据IDS获取到List
	@SuppressWarnings("rawtypes")
	public PageResults list_ByIds(String ids){
		PageResults prs=new PageResults();
		try {
		   prs=sysStaffService.s_findByIds(ids);
		} catch (Exception e) {
			
		}
		return prs;
	}

	public RetMessage check_phone(String phone, PageResults prs) {
		RetMessage ret=new RetMessage();
		try {
				BeanUtils.copyProperties(prs,sysStaffService.check_phone(phone));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return ret;
	}

	@SuppressWarnings("rawtypes")
	public RetMessage getEng(String deptNm,PageResults prs) {
		
		RetMessage ret=new RetMessage();
		try {
				BeanUtils.copyProperties(prs,sysStaffService.getEng(deptNm));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return ret;
		
	}
	

}

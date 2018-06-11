package com.lyht.business.system.control;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDict;
import com.lyht.business.system.formBean.SysDictCateFormBean;
import com.lyht.business.system.formBean.SysDictFormBean;
import com.lyht.business.system.service.SysDictService;

@Controller
@Scope("prototype")
public class SysDictControl {
	
	@Resource
	private SysDictService sysDictService;
	
	//根据ID获取JavaBean对象
	@Optlog(menuflag="menu_system_sysdict", opttype = "getByid")  //记录日志
	public SysDict getByid(int id){
		SysDict retBean=new SysDict();
		try {
			retBean=sysDictService.s_get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retBean;
	}
	
	@Optlog(menuflag="menu_system_sysdict", opttype = "view")  //记录日志
	public RetMessage view(int id,SysDict retBean){
		RetMessage ret=new RetMessage();
		try {
				BeanUtils.copyProperties(retBean,sysDictService.s_get(id));
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
	@Optlog(menuflag="menu_system_sysdict", opttype = "create")  //记录日志
	public RetMessage create(SysDict infoBean,SysDict retBean){
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
				BeanUtils.copyProperties(retBean,sysDictService.s_create(infoBean));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("新增信息成功！");
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
	@Optlog(menuflag="menu_system_sysdict",opttype = "edit")  //记录日志
	public RetMessage update(SysDict infoBean,SysDict retBean){
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
				BeanUtils.copyProperties(retBean,sysDictService.s_update(infoBean));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("修改信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return ret;
	}
	
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_sysdict",opttype = "list")  //记录日志
	public RetMessage list(SysDictFormBean formBean,PageResults prs){
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
				BeanUtils.copyProperties(prs,sysDictService.s_findAll(formBean));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		
		return ret;
	}
	
	//获取字典数据
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_sysdict",opttype = "getDictInfos")  //记录日志
	public RetMessage getDictInfos(PageResults prs,SysDictCateFormBean mSysDictCateFormBean){
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
				BeanUtils.copyProperties(prs,sysDictService.findAll(mSysDictCateFormBean));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return ret;
	}
	
	//根据名称与内码获取编码
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_sysdict",opttype = "getCodeByNmAndName")  //记录日志
	public RetMessage getCodeByNmAndName(PageResults prs,String nm,String name){
		RetMessage ret=new RetMessage();
		try{
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,sysDictService.findNmAndName(nm,name));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		}catch (Exception e) {
			e.getStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return ret;
	}
	
	//获取问题类型
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_sysdict",opttype = "getProblemType")  //记录日志
	public RetMessage getProblemType(PageResults mPageResults,String flag){
		RetMessage mRetMessage=new RetMessage();
		try{
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(mPageResults,sysDictService.getDictType(flag));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			e.getStackTrace();
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	
	//获取处理方式
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_sysdict",opttype = "getProcessMethod")  //记录日志
	public RetMessage getProcessMethod(PageResults mPageResults,String flag){
		RetMessage mRetMessage=new RetMessage();
		try{
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(mPageResults,sysDictService.getDictType(flag));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			e.getStackTrace();
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	//获取通知类型
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_sysdict",opttype = "getMessageType")  //记录日志
	public RetMessage getMessageType(PageResults mPageResults,String flag){
		RetMessage mRetMessage=new RetMessage();
		try{
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(mPageResults,sysDictService.getDictType(flag));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			e.getStackTrace();
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	//@Optlog(menuflag="menu_system_sysdict",opttype = "flag")  //记录日志
	public RetMessage flag(String ids,List list,int flag_new){
		RetMessage ret=new RetMessage();
		try {
			    sysDictService.s_flagByIds(ids,flag_new);
				list.clear();
				list.addAll(sysDictService.s_findByIds(ids).getResults());
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("审核数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"审核数据失败！");
		}
		return ret;
	}
	
	@SuppressWarnings({ "rawtypes"})
	@Optlog(menuflag="menu_system_sysdict",opttype = "del")  //记录日志
	public RetMessage delByIds(String ids,List list){
		RetMessage ret=new RetMessage();
		try {
			    sysDictService.s_delByIds(ids);
				list.clear();//一定要执行此步骤，在AOP中数据就被清除
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return ret;
	}
	
	//根据IDS获取到List
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_sysdict",opttype = "list_ByIds")  //记录日志
	public PageResults list_ByIds(String ids){
		PageResults prs=new PageResults();
		try {
		  prs=sysDictService.s_findByIds(ids);
		} catch (Exception e) {
			
		}
		return prs;
	}
	
	//获取字典基础数据
	@Optlog(menuflag="menu_system_sysdict",opttype = "getDictData")  //记录日志
	public RetMessage getDictData(SysDictCateFormBean formBean,PageResults prs){
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
				BeanUtils.copyProperties(prs,sysDictService.getDictData(formBean));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return ret;
	}

}

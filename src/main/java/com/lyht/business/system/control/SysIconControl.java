package com.lyht.business.system.control;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysIcon;
import com.lyht.business.system.formBean.SysIconFormBean;
import com.lyht.business.system.service.SysIconService;

@Controller
@Scope("prototype")
public class SysIconControl {
	
	@Resource
	private SysIconService sysIconService;

	
	//根据ID获取JavaBean对象
	@Optlog(menuflag="menu_supsystem_sysicon", opttype = "getByid")  //记录日志
	public SysIcon getByid(int id){
		SysIcon retBean=new SysIcon();
		try {
			retBean=sysIconService.s_get(id);
		} catch (Exception e) {
		}
		return retBean;
	}
	
	@Optlog(menuflag="menu_supsystem_sysicon", opttype = "view")  //记录日志
	public RetMessage view(int id,SysIcon retBean){
		RetMessage ret=new RetMessage();
		try {
				BeanUtils.copyProperties(retBean,sysIconService.s_get(id));
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
	@Optlog(menuflag="menu_supsystem_sysicon", opttype = "add")  //记录日志
	public RetMessage create(SysIcon infoBean,SysIcon retBean){
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
				BeanUtils.copyProperties(retBean,sysIconService.s_create(infoBean));
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
	@Optlog(menuflag="menu_supsystem_sysicon",opttype = "edit")  //记录日志
	public RetMessage update(SysIcon infoBean,SysIcon retBean){
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
				BeanUtils.copyProperties(retBean,sysIconService.s_update(infoBean));
				
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("修改信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return ret;
	}
	
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_supsystem_sysicon",opttype = "list")  //记录日志
	public RetMessage list(SysIconFormBean formBean,PageResults prs,SysAcct sysacct){
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
				BeanUtils.copyProperties(prs,sysIconService.s_findAll(formBean,sysacct));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return ret;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Optlog(menuflag="menu_supsystem_sysicon",opttype = "flag")  //记录日志
	public RetMessage flag(String ids,List list,Integer flag_new){
		RetMessage ret=new RetMessage();
		try {
			    sysIconService.s_flagByIds(ids,flag_new);
				list.clear();
				list.addAll(sysIconService.s_findByIds(ids).getResults());
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("审核数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"审核数据失败！");
		}
		
		return ret;
		
	}
	
	@SuppressWarnings({ "rawtypes"})
	@Optlog(menuflag="menu_supsystem_sysicon",opttype = "del")  //记录日志
	public RetMessage delByIds(String ids,List list){
		RetMessage ret=new RetMessage();
		try {
			    sysIconService.s_delByIds(ids);
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
	public PageResults list_ByIds(String ids){
		PageResults prs=new PageResults();
		try {
		  prs=sysIconService.s_findByIds(ids);
		} catch (Exception e) {
			
		}
		return prs;
	}
	

}

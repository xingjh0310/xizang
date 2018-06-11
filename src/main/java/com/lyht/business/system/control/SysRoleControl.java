package com.lyht.business.system.control;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.formBean.SysRoleFormBean;
import com.lyht.business.system.service.SysRoleService;

@Controller
@Scope("prototype")
public class SysRoleControl {
	@Resource 
	private SysRoleService sysRoleService;
	
	//根据ID获取JavaBean对象
		public SysRole getByid(int id){
			SysRole retBean=new SysRole();
			try {
				retBean=sysRoleService.s_get(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return retBean;
		}
		
		public RetMessage view(int id,SysRole retBean){
			RetMessage ret=new RetMessage();
			try {
				 BeanUtils.copyProperties(retBean,sysRoleService.s_get(id));
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
		@Optlog(menuflag="menu_system_SysRole", opttype = "add")  //记录日志
		public RetMessage create(SysRole infoBean,SysRole retBean){
			RetMessage ret=new RetMessage();
			try {
				if(checkRoleByCode(infoBean) && checkRoleByName(infoBean)){
			    	BeanUtils.copyProperties(retBean,sysRoleService.s_create(infoBean));
					ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
					ret.setMessage("修改信息成功！");
			    }else if(checkRoleByCode(infoBean)){
			    	ret.setRetflag(RetMessage.RETFLAG_ERROR);
					ret.setMessage("角色编号不能重复！");
			    }else if(checkRoleByName(infoBean)){
			    	ret.setRetflag(RetMessage.RETFLAG_ERROR);
					ret.setMessage("角色名称不能重复！");
			    }
			} catch (Exception e) {
				ret.setRetflag(RetMessage.RETFLAG_ERROR);
				ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
				e.printStackTrace();
			}
			return ret;
		}
		
		/**
		 * 
		 * @param infoBean --需要修改的数据
		 * @param retBean  --传入旧数据，返回新数据
		 * @return
		 */
		@Optlog(menuflag="menu_system_SysRole",opttype = "edit")  //记录日志
		public RetMessage update(SysRole infoBean,SysRole retBean){
			RetMessage ret=new RetMessage();
			try {
				    if(checkRoleByCode(infoBean) && checkRoleByName(infoBean)){
				    	BeanUtils.copyProperties(retBean,sysRoleService.s_update(infoBean));
						ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
						ret.setMessage("修改信息成功！");
				    }else if(checkRoleByCode(infoBean)){
				    	ret.setRetflag(RetMessage.RETFLAG_ERROR);
						ret.setMessage("角色编号不能重复！");
				    }else if(checkRoleByName(infoBean)){
				    	ret.setRetflag(RetMessage.RETFLAG_ERROR);
						ret.setMessage("角色名称不能重复！");
				    }
				
			} catch (Exception e) {
				e.printStackTrace();
				ret.setRetflag(RetMessage.RETFLAG_ERROR);
				ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
			}
			return ret;
		}
		
		//验证角色编号与角色名称是否唯一
		private boolean checkRoleByName(SysRole mSysRole){
			return sysRoleService.checkRoleByName(mSysRole);
		}
		//验证角色编号与角色名称是否唯一
		private boolean checkRoleByCode(SysRole mSysRole){
					return sysRoleService.checkRoleByCode(mSysRole);
		}
		
		@SuppressWarnings("rawtypes")
		public RetMessage list(SysRoleFormBean formBean,PageResults prs){
			RetMessage ret=new RetMessage();
			try {
					//必须使用此函数，否则AOP中的数值不会发生变化
					BeanUtils.copyProperties(prs, sysRoleService.s_findAll(formBean));
					ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
					ret.setMessage("查询数据成功！");
			} catch (Exception e) {
				ret.setRetflag(RetMessage.RETFLAG_ERROR);
				ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
				e.printStackTrace();
			}
			
			return ret;
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Optlog(menuflag="menu_system_SysRole",opttype = "flag")  //记录日志
		public RetMessage flag(String ids,List list,int flag_new){
			RetMessage ret=new RetMessage();
			try {
				sysRoleService.s_flagByIds(ids,flag_new);
				list.clear();
				list.addAll(sysRoleService.s_findByIds(ids).getResults());
					
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("审核数据成功！");
			} catch (Exception e) {
				ret.setRetflag(RetMessage.RETFLAG_ERROR);
				ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"审核数据失败！");
			}
			return ret;
		}
		
		@SuppressWarnings({ "rawtypes"})
		@Optlog(menuflag="menu_system_SysRole",opttype = "del")  //记录日志
		public RetMessage delByIds(String ids,List list){
			RetMessage ret=new RetMessage();
			try {
				sysRoleService.s_delByIds(ids);
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
			 prs=sysRoleService.s_findByIds(ids);
		  } catch (Exception e) {
			  
		  }
		  return prs;
	   }

}

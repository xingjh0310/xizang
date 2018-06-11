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
import com.lyht.business.system.bean.SysEngineerInfo;
import com.lyht.business.system.formBean.SysEngineerInfoFormBean;
import com.lyht.business.system.service.SysEngineerInfoService;

@Controller
@Scope("prototype")
public class SysEngineerInfoControl {

	@Resource
	private SysEngineerInfoService mSysEngineerInfoService;

	@SuppressWarnings("rawtypes")
	@Optlog(menuflag = "menu_system_SysEngineerInfo", opttype = "list_root") // 记录日志
	public RetMessage list_root(SysEngineerInfoFormBean formBean, PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			BeanUtils.copyProperties(prs, mSysEngineerInfoService.s_findRoot(formBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询数据失败！");
		}
		return ret;
	}

	@SuppressWarnings("rawtypes")
	@Optlog(menuflag = "menu_system_SysEngineerInfo", opttype = "list") // 记录日志
	public RetMessage list(SysEngineerInfoFormBean formBean, PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			BeanUtils.copyProperties(prs, mSysEngineerInfoService.s_findAll(formBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询数据失败！");
		}
		return ret;
	}

	@Optlog(menuflag = "menu_system_SysEngineerInfo", opttype = "view") // 记录日志
	public RetMessage view(int id, SysEngineerInfo retBean, SysEngineerInfo p_retBean) {
		RetMessage ret = new RetMessage();
		try {
			BeanUtils.copyProperties(retBean, mSysEngineerInfoService.s_get(id));
			if (retBean.getPnm().length() > 0) {
				BeanUtils.copyProperties(p_retBean, mSysEngineerInfoService.s_getByProp("nm", retBean.getPnm()));
			}
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询信息失败！");
		}
		return ret;
	}
	//新增节点
	@Optlog(menuflag = "menu_system_SysEngineerInfo", opttype = "add") // 记录日志
	public RetMessage create(SysEngineerInfo  infoBean, SysEngineerInfo retBean) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retBean, mSysEngineerInfoService.s_create(infoBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增信息成功！");

		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "新增信息失败！");
		}
		return ret;

	}

	//根据ID获取JavaBean对象
    public SysEngineerInfo getByid(int id){
    	SysEngineerInfo retBean=new SysEngineerInfo();
	  try {
		 retBean=mSysEngineerInfoService.s_get(id);
	  } catch (Exception e) {
		 e.printStackTrace();
	  }
	  return retBean;
	}

	@Optlog(menuflag="menu_system_SysEngineerInfo",opttype = "edit")  //记录日志
	public RetMessage update(SysEngineerInfo infoBean,SysEngineerInfo retBean){
		RetMessage ret=new RetMessage();
		try {
			 BeanUtils.copyProperties(retBean,mSysEngineerInfoService.s_update(infoBean));
			 ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("修改信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return ret;
	}

	//根据IDS获取到List
	@SuppressWarnings("rawtypes")
	public PageResults list_ByIds(String ids){
	  PageResults prs=new PageResults();
	  try {
	    prs=mSysEngineerInfoService.s_findByIds(ids);
	  } catch (Exception e) {
			
	  }
	  return prs;
	}

	@SuppressWarnings({ "rawtypes"})
	@Optlog(menuflag="menu_system_SysEngineerInfo",opttype = "del")  //记录日志
	public RetMessage delByIds(SysEngineerInfo mSysEngineerInfo,List list){
		RetMessage ret=new RetMessage();
		try {
			 mSysEngineerInfoService.s_delByIds(mSysEngineerInfo.getEngineerCode());
			 mSysEngineerInfoService.deleteSysRefEngineerInfo(mSysEngineerInfo.getNm());
			 list.clear();//一定要执行此步骤，在AOP中数据就被清除
			 ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return ret;
	}
	
	@SuppressWarnings({ "rawtypes"})
	@Optlog(menuflag="menu_system_SysEngineerInfo",opttype = "del")  //记录日志
	public RetMessage delByIds_(SysEngineerInfo mSysEngineerInfo,List list){
		RetMessage ret=new RetMessage();
		try {
			 mSysEngineerInfoService.s_delByIds_(mSysEngineerInfo.getEngineerCode());
			 mSysEngineerInfoService.deleteSysRefEngineerInfo(mSysEngineerInfo.getNm());
			 list.clear();//一定要执行此步骤，在AOP中数据就被清除
			 ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return ret;
	}


	@Optlog(menuflag="menu_system_SysEngineerInfo", opttype = "add")  //记录日志
 	public RetMessage add(int id,SysEngineerInfo retBean,SysEngineerInfo p_retBean){
     	RetMessage ret=new RetMessage();
 		try {
 			if (id>0){
 			  BeanUtils.copyProperties(p_retBean,mSysEngineerInfoService.s_get(id));
 			}
 			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
 			ret.setMessage("查询信息成功！");
 		} catch (Exception e) {
 			ret.setRetflag(RetMessage.RETFLAG_ERROR);
 			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
 		}
 		return ret;
 	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Optlog(menuflag="menu_system_SysEngineerInfo",opttype = "flag")  //记录日志
	public RetMessage flag(String ids,List list,int flag_new){
		RetMessage ret=new RetMessage();
		try {
			mSysEngineerInfoService.s_flagByIds(ids,flag_new);
			  list.clear();
			  list.addAll(list_ByIds(ids).getResults());
			  ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			  ret.setMessage("审核数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"审核数据失败！");
		}
		return ret;
	}
	
	//初始化设置参建单位表单
	@Optlog(menuflag="menu_system_SysEngineerInfo",opttype = "edit_view")  //记录日志
  	public RetMessage edit_view(int id,SysEngineerInfo mSysEngineerInfo){
  		RetMessage ret=new RetMessage();
  		try{
  			BeanUtils.copyProperties(mSysEngineerInfo,mSysEngineerInfoService.s_get(id));
  			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
  		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
	  		ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
  		return ret;
  	}
	
	//导入工程信息
	@Optlog(menuflag="menu_system_SysEngineerInfo",opttype = "importProjectInfo")  //记录日志
	public RetMessage importProjectInfo(File []file,String []fileName){
		RetMessage mRetMessage=new RetMessage();
		try{
			mSysEngineerInfoService.importProjectInfo(file,fileName);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("导入数据成功！");
		}catch (Exception e) {
			e.getStackTrace();
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入数据失败！");
		}
		return mRetMessage;
	}

	public RetMessage getDeptsByEngineerNm(String nm, PageResults retBean) {
		RetMessage ret=new RetMessage();
		try {
				BeanUtils.copyProperties(retBean,mSysEngineerInfoService.getDeptsByEngineerNm(nm));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return ret;
	}

	public RetMessage getStaffsByEngineerNm(String nm, PageResults retBean) {
		RetMessage ret=new RetMessage();
		try {
				BeanUtils.copyProperties(retBean,mSysEngineerInfoService.getStaffsByEngineerNm(nm));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return ret;
	}
	
	@SuppressWarnings("rawtypes")
	public SysEngineerInfo getBySysEngineerInfoById(String id,SysEngineerInfo mSysEngineerInfo){
		RetMessage ret=new RetMessage();
		try {
			BeanUtils.copyProperties(mSysEngineerInfo,mSysEngineerInfoService.getBySysEngineerInfoById(id));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
	} catch (Exception e) {
		ret.setRetflag(RetMessage.RETFLAG_ERROR);
		ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
	}
		return mSysEngineerInfo;
	}
}

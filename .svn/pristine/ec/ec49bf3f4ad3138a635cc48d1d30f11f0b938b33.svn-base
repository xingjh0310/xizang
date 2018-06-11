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
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.formBean.SysDeptFormBean;
import com.lyht.business.system.service.SysDeptService;

@Controller
@Scope("prototype")
public class SysDeptControl {
	
	@Resource 
	private SysDeptService sysDeptService;
	
	//根据ID获取JavaBean对象
    public SysDept getByid(int id){
      SysDept retBean=new SysDept();
	  try {
		 retBean=sysDeptService.s_get(id);
	  } catch (Exception e) {
		 e.printStackTrace();
	  }
	  return retBean;
	}
    
    
  	public RetMessage view(int id,SysDept retBean,SysDept p_retBean){
  	  RetMessage ret=new RetMessage();
  	  try {
  		 BeanUtils.copyProperties(retBean,sysDeptService.s_get(id));
  		 if(retBean.getPnm().length()>0){
  		   BeanUtils.copyProperties(p_retBean,sysDeptService.s_getByProp("nm",retBean.getPnm()));
  		 }
  		 ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
  		 ret.setMessage("查询信息成功！");
  	  } catch (Exception e) {
  		 ret.setRetflag(RetMessage.RETFLAG_ERROR);
  		 ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
  	  }
  	  return ret;
  	}
  	
  	@Optlog(menuflag="menu_system_dept", opttype = "add")  //记录日志
 	public RetMessage add(int id,SysDept retBean,SysDept p_retBean){
     	RetMessage ret=new RetMessage();
 		try {
 			if (id>0){
 			  BeanUtils.copyProperties(p_retBean,sysDeptService.s_get(id));
 			}
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
	@Optlog(menuflag="menu_system_dept", opttype = "add")  //记录日志
	public RetMessage create(SysDept infoBean,SysDept retBean){
		RetMessage ret=new RetMessage();
		try {
			  if(sysDeptService.checkThisCode(infoBean)){
				  ret.setRetflag(RetMessage.RETFLAG_ERROR);
				  ret.setMessage("部门编码不能重复！！！");
			  }else{
				  BeanUtils.copyProperties(retBean,sysDeptService.s_create(infoBean));
				  ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				  ret.setMessage("新增信息成功！");
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
	@Optlog(menuflag="menu_system_dept",opttype = "edit")  //记录日志
	public RetMessage update(SysDept infoBean,SysDept retBean){
		RetMessage ret=new RetMessage();
		try {
			 BeanUtils.copyProperties(retBean,sysDeptService.s_update(infoBean));
			 ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("修改信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return ret;
	}
	
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_dept",opttype = "list")  //记录日志
	public RetMessage list(SysDeptFormBean formBean,PageResults prs){
		RetMessage ret=new RetMessage();
		try {
		     BeanUtils.copyProperties(prs,sysDeptService.s_findAll(formBean));
		     ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return ret;
	}
	
	//获取部门信息
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_dept",opttype = "getDeptList")  //记录日志
	public RetMessage getDeptList(SysDeptFormBean formBean,PageResults prs){
		RetMessage ret=new RetMessage();
		try {
		     BeanUtils.copyProperties(prs,sysDeptService.getDeptAll(formBean));
		     ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return ret;
	}
	
	//根据名称获取部门信息
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_dept",opttype = "getDeptInfosByName")  //记录日志
	public RetMessage getDeptInfosByName(String name,PageResults prs){
		RetMessage ret=new RetMessage();
		try{
			BeanUtils.copyProperties(prs,sysDeptService.getDeptName(name));
		     ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("查询数据成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			e.getStackTrace();
		}
		
		return ret;
	}
	
	//根据名称获取部门信息
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_dept",opttype = "getDeptInfosByNm")  //记录日志
	public RetMessage getDeptInfosByNm(String nm,PageResults prs){
		RetMessage ret=new RetMessage();
		try{
			 BeanUtils.copyProperties(prs,sysDeptService.getDeptNm(nm));
		     ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("查询数据成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			e.getStackTrace();
		}
		return ret;
	}
	
	//根据工程信息编号获取部门信息
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_dept",opttype = "getDeptInfoByEngineerInfoCode")  //记录日志
	public RetMessage getDeptInfoByEngineerInfoCode(PageResults mPageResults,String engineerInfoCode){
		RetMessage mRetMessage=new RetMessage();
		try{
			 BeanUtils.copyProperties(mPageResults,sysDeptService.getDeptInfoByEngineerInfoCode(engineerInfoCode));
			 mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			e.getStackTrace();
		}
		return mRetMessage;
	}
	
	@SuppressWarnings("rawtypes")
	public RetMessage list_root(SysDeptFormBean formBean,PageResults prs){
		RetMessage ret=new RetMessage();
		try {
			 BeanUtils.copyProperties(prs,sysDeptService.s_findRoot(formBean));
			 ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return ret;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Optlog(menuflag="menu_system_dept",opttype = "flag")  //记录日志
	public RetMessage flag(String ids,List list,int flag_new){
		RetMessage ret=new RetMessage();
		try {
			  sysDeptService.s_flagByIds(ids,flag_new);
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Optlog(menuflag="menu_system_dept",opttype = "updateFlag")  //记录日志
	public RetMessage updateFlag(String ids,List list){
		RetMessage ret=new RetMessage();
		try{
			  sysDeptService.s_flagByIds(ids);
			  list.clear();
			  list.addAll(list_ByIds(ids).getResults());
			  ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			  ret.setMessage("设置数据成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"设置数据失败！");
		}
		return ret;
	}
	
	@SuppressWarnings({ "rawtypes"})
	@Optlog(menuflag="menu_system_dept",opttype = "del")  //记录日志
	public RetMessage delByIds(String ids,List list){
		RetMessage ret=new RetMessage();
		try {
			 sysDeptService.s_delByIds(ids);
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
	@Optlog(menuflag="menu_system_dept",opttype = "del")  //记录日志
	public RetMessage delByIds_(String ids,List list){
		RetMessage ret=new RetMessage();
		try {
			 sysDeptService.s_delByIds_(ids);
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
	    prs=sysDeptService.s_findByIds(ids);
	  } catch (Exception e) {
			
	  }
	  return prs;
	}
	
	//导入部门信息数据
	@Optlog(menuflag="menu_system_dept",opttype = "importDeptInfo")
	public RetMessage importDeptInfo(File []file,String []fileName){
		RetMessage mRetMessage=new RetMessage();
		try{
			sysDeptService.importDeptInfo(file,fileName);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("导入数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入数据失败！");
		}
		return mRetMessage;
	}


	public RetMessage queryType(SysDeptFormBean formBean, PageResults prs) {
		RetMessage ret=new RetMessage();
		try {
			 BeanUtils.copyProperties(prs,sysDeptService.queryType(formBean));
			 ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return ret;
	}
	

}

package com.lyht.business.system.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysRela;
import com.lyht.business.system.dao.SysMenuDao;
import com.lyht.business.system.dao.SysRelaDao;
import com.lyht.business.system.dao.SysRoleDao;
import com.lyht.business.system.formBean.SysRelaFormBean;
import com.lyht.util.CommonUtil;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月19日 21:36:48
 *说明:  系统角色
*/

@Service
@Scope("prototype")
@Transactional
public class SysRelaService {
	
	@Resource
	private SysRelaDao sysRelaDao;
	@Resource
	private SysMenuDao mSysMenuDao;
	@Resource
	private SysRoleDao mSysRoleDao;
	
    //根据FormBean中的条件查找实体对象List
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED) 
	public PageResults s_findAll(SysRelaFormBean formBean,String type,String type_){
		  String str="";
		  String taNm=CommonUtil.trim(formBean.getInfoBean().getTaNm()); 
		  String tbCode=CommonUtil.trim(formBean.getInfoBean().getTbNm());
	      String parm1=CommonUtil.trim(formBean.getParmBean().getParm1()); 
	      String taName=CommonUtil.trim(formBean.getInfoBean().getTaName());
	      String tbName=CommonUtil.trim(formBean.getInfoBean().getTbName()); 
	      
	      if("".equals(taNm) && "".equals(type)){ 
	    	  str=sysRelaDao.loadRoleAndMenuData(tbName,taName); //系统管理员登录页面初始化加载《角色授权菜单功能》
	      }else if(!"".equals(taNm) && "".equals(type)){
	    	  str=sysRelaDao.loadRoleAndMenuClickData(tbName,taName,tbCode,taNm); //系统管理员登录页面（点击）《角色授权菜单功能》
	      }else if("tree".equals(parm1) && "audmins".equals(type)){  
	    	  if(!"".equals(taNm)){  
	    		  str=sysRelaDao.loadRoleAndMenuAuditClickData(tbName,taName,tbCode,taNm); //审核管理员登录页面（点击）《角色授权菜单功能》
	    	  }else{ 
	    		  str=sysRelaDao.loadRoleAndMenuAuditData(tbName,taName); //审核账户页面初始加载《角色授权菜单功能》
	    	  }
	      }else if("".equals(taNm) && "".equals(type_)){  
	    	  str=sysRelaDao.loadStaffAndRoleData(tbName,taName); //系统管理员登录页面（页面初始化）《人员授权角色功能》
	      }else if(!"".equals(taNm) && "".equals(type_)){
	    	  str=sysRelaDao.loadStaffAndRoleClickData(tbName,taName,tbCode,taNm); //系统管理员登录页面（点击）《人员授权角色功能》
	      }else if("audmins".equals(type_) && "list".equals(parm1)){
	    	  if(!"".equals(taNm)){ 
	    		  str=sysRelaDao.loadStaffAndRoleAuditClickData(tbName,taName,tbCode,taNm); //审核账户页面（点击）《人员授权角色功能》
	    	  }else{ 
	    		  str=sysRelaDao.loadStaffAndRoleAuditData(tbName,taName); //审核账户页面初始加载《人员授权角色功能》
	    	  }
	      }
		return sysRelaDao.m_findAll(str);
	}
	
	
	/**
	 * 授权操作
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void relasq_(SysRelaFormBean formBean){
		String ta_nm	=CommonUtil.trim(formBean.getInfoBean().getTaNm());  
		String ta_name	=CommonUtil.trim(formBean.getInfoBean().getTaName()); 
		String tbcodes	=CommonUtil.trim(formBean.getInfoBean().getTbNm());  
	    String tb_name	=CommonUtil.trim(formBean.getInfoBean().getTbName()); 
	    String sqtype 	=CommonUtil.trim(formBean.getParmBean().getParm1());  
	    String flag 	=CommonUtil.trim(formBean.getParmBean().getParm2()); 
	    String tableRefName=ta_name+"+"+tb_name; 
	    
	    if("sys_menu".equals(tb_name)){
	    	String tbcode_[] = tbcodes.split(",");
	    	for(int i=0;i<tbcode_.length;i++){
		    	sysRelaDao.qx(tbcode_[i],ta_name,ta_nm,tb_name);
		    	if("qbsq".equals(sqtype) || "sq".equals(sqtype)){
		    		authorizedOper_(tbcode_[i],ta_name,ta_nm,tb_name);
		    	}
		    }
	    }else{
	    	if("sys_role".equals(flag)){
	    		staffAuthRole(tbcodes,ta_nm,ta_name,sqtype,tb_name,tableRefName);
	    	}else if("sys_staff".equals(flag)){
	    		roleAuthStaff(tbcodes,ta_nm,ta_name,sqtype,tb_name,tableRefName);
	    	}
	    }
	}
	
	/**
	 * 人员配置角色
	 * */
	private void staffAuthRole(String tbcodes,String ta_nm,String ta_name,String sqtype,
			String tb_name,String tableRefName){
		String []taNm=ta_nm.split(",");
		for(int i=0;i<taNm.length;i++){
			sysRelaDao.deleteRefTableByRoleCode(taNm[i],tableRefName);
			if(!"".equals(tbcodes)){
				String tbcode_[] = tbcodes.split(",");
				for(int j=0;j<tbcode_.length;j++){
					if("sq".equals(sqtype)){
	    				authorizedOper(taNm[i],ta_name,tbcode_[j],tb_name);
	    			}
				}
			}
		}
	}
	
	/**
	 * 角色配置人员
	 * */
	private void roleAuthStaff(String tbcodes,String ta_nm,String ta_name,String sqtype,
			String tb_name,String tableRefName){
		String tbcode_[] = tbcodes.split(",");
		for(int i=0;i<tbcode_.length;i++){
			sysRelaDao.deleteRefTableByStaffCode(tbcode_[i],tableRefName);
			if(!"".equals(ta_nm)){
				String []taNm=ta_nm.split(",");
				for(int j=0;j<taNm.length;j++){
					if("sq".equals(sqtype)){
	    				authorizedOper(taNm[j],ta_name,tbcode_[i],tb_name);
	    			}
				}
			}
		}
	}
	
	/**
	 * 授权操作
	 * */
	private void authorizedOper(String taNm,String taName,String tbcode,String tbName){
		sysRelaDao.authRole(taNm,taName,tbcode,tbName);
	}
	
	/**
	 * 授权操作(菜单)
	 * */
	@SuppressWarnings("rawtypes")
	private void authorizedOper_(String tbcode,String ta_name,String ta_nm,String tb_name){
		List<Map> list=mSysMenuDao.getSysMenuInfoByCode(tbcode);
		getRoleRefMenuInfo(list,ta_name,ta_nm,tb_name);
	}
	
	/**
	 * 获取集合中的信息（角色菜单设置）
	 * */
	@SuppressWarnings("rawtypes")
	private void getRoleRefMenuInfo(List<Map> list,String ta_name,String ta_nm,String tb_name){
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object obj=list.get(i).get("NM");
				Object pCode=list.get(i).get("PCODE");
				if(null!=obj){
					String key=obj.toString();
					sysRelaDao.removeSysMenuNm(key,ta_nm);
					if(null!=pCode && !"".equals(pCode)){
						List<Map> list_=mSysMenuDao.getSysMenuInfoByPCode(pCode.toString());
						getRoleRefMenuInfo(list_,ta_name,ta_nm,tb_name);
					}
					sysRelaDao.authRole(ta_nm,ta_name,key,tb_name);
				}
			}
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<SysRela> getSysRelaNmByStaffCode(String code){
		return sysRelaDao.getSysRelaNmByStaffCode(code);
	}

}

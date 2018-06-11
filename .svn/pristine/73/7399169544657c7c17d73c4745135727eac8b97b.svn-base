package com.lyht.business.system.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.dao.SysAcctDao;
import com.lyht.business.system.formBean.SysAcctFormBean;
import com.lyht.util.MD5;


/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 21:28:37
 *说明:  账户信息
*/
@Service
@Scope("prototype")
@Transactional
public class SysAcctService {
	
	 @Resource 
	 private SysAcctDao sysAcctdao;
	  
    //根据id获取实体对象
	public SysAcct s_get(int id){
		SysAcct ret_bean= new SysAcct();
		if (id>0) {
			ret_bean= sysAcctdao.get(id);
		}
		return ret_bean;
	}
    
	//增加实体对象
	public SysAcct s_create(SysAcct bean){
		String pwd=MD5.getInstance().getMD5ofStr("123456",16); //获取初始化密码同时加密
		UUID uuid = UUID.randomUUID();
		String str=uuid.toString().replaceAll("-", "");
		bean.setPwd(pwd);
		bean.setNm(str);
		bean.setDictnmZhlx("system");
		sysAcctdao.save(bean);
		return bean;
	}
    
	//修改实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysAcct s_update(SysAcct bean){
		sysAcctdao.merge(bean);	
		sysAcctdao.flush(bean);
		return bean;
	}	
	
    //删除实体对象
	public void s_remove(SysAcct bean){
		sysAcctdao.delete(bean);
	}
	
    //根据IDS(1,2,3,4,5)删除多个对象
	public void s_delByIds(String ids){
		  String[] idary=ids.split(",");
		  for(int i=0;i<idary.length;i++)
		  {
			  sysAcctdao.deleteById(Integer.parseInt(idary[i]));
		  }
		
	}
	
    //根据IDS(1,2,3,4,5)修改多个对象的Flag数值
	public void s_flagByIds(String ids,Integer flag_new){
		  String[] idary=ids.split(",");
		  for(int i=0;i<idary.length;i++)
		  {
			  SysAcct entity = sysAcctdao.get(Integer.parseInt(idary[i]));
			  entity.setFlag(flag_new);
			  sysAcctdao.update(entity);
		  }
		
	}
	
    //根据FormBean中的条件查找实体对象List
	@SuppressWarnings("rawtypes")
	public PageResults s_findAll(SysAcctFormBean formBean){
		PageResults m_findAll = sysAcctdao.m_findAll(formBean);
		return m_findAll;
	}
    
    //根据属性及属性值查找对象实体
	public SysAcct s_getByProp(String PropName,Object PropValue){
		return sysAcctdao.getByProp(PropName, PropValue);
	}
	
	@SuppressWarnings("rawtypes")
	public PageResults s_findByIds(String ids){
		return sysAcctdao.m_findByIds(ids);
	}	
	
	//验证账户是否唯一
	public boolean checkOnlyByName(SysAcct mSysAcct){
		return sysAcctdao.checkOnlyByName(mSysAcct);
	}
	
	//初始化密码
	public void updatePwdById(String id){
		sysAcctdao.updatePwdById(id);
	}
	
	//验证用户名与密码是否存在
	public boolean isExist(SysAcct mSysAcct){
		return sysAcctdao.isExist(mSysAcct);
	}
	
	//根据用户名修改密码
	public void updatePwdByName(SysAcct mSysAcct,String newPwd){
		sysAcctdao.updatePwdByName(mSysAcct,newPwd);
	}
	//修改用户密码
	public void editPass(SysAcctFormBean formBean) {
		sysAcctdao.editPass(formBean);
		
	}
	//校验用户名和密码
	public boolean check(SysAcctFormBean formBean) {
		
		return sysAcctdao.check(formBean);
	}
	
}

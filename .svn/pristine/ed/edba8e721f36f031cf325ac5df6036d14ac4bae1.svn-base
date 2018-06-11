package com.lyht.business.system.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.dao.SysMenuDao;
import com.lyht.business.system.formBean.SysMenuFormBean;


/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 16:08:50
 *说明:  系统菜单
*/
@Service
@Scope("prototype")
@Transactional
public class SysMenuService{
	
	@Resource 
	private SysMenuDao sysMenuDao;
	
    //根据id获取实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysMenu s_get(int id){
		SysMenu ret_bean= new SysMenu();
		if (id>0) {
		   ret_bean= sysMenuDao.get(id);
		}
		return ret_bean;
	}
    
	//增加实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysMenu s_create(SysMenu bean){
		UUID uuid = UUID.randomUUID(); //获取新内码
		String str=uuid.toString().replaceAll("-", "");
		bean.setNm(str); //将内码设置成编码
		sysMenuDao.save(bean);
		return bean;
	}
    
	//修改实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysMenu s_update(SysMenu bean){
	  sysMenuDao.merge(bean);
	  sysMenuDao.flush(bean);
	  return bean;
	}	
	
    //删除实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_remove(SysMenu bean){
	  sysMenuDao.delete(bean);
	}
	
    //根据IDS(1,2,3,4,5)删除多个对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_delByIds(String ids){
	  String[] idary=ids.split(",");
	  for(int i=0;i<idary.length;i++){
		  sysMenuDao.deleteAndChildById(idary[i]);
//		  sysMenuDao.deleteById(Integer.parseInt(idary[i]));
	  }
	}
	
    //根据IDS(1,2,3,4,5)修改多个对象的Flag数值
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_flagByIds(String ids,Integer flag_new){
	  String[] idary=ids.split(",");
	  for(int i=0;i<idary.length;i++){
		  sysMenuDao.s_flagById(idary[i],flag_new);
//		SysMenu entity = sysMenuDao.get(Integer.parseInt(idary[i]));
//		entity.setFlag(flag_new);
//		sysMenuDao.update(entity);
	  }
	}
	
    //根据FormBean中的条件查找实体对象List
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED) 
	public PageResults s_findAll(SysMenuFormBean formBean,SysAcct sysacct){
		return sysMenuDao.m_findAll(formBean,sysacct);
	}
    
    //根据属性及属性值查找对象实体
	@Transactional(propagation=Propagation.REQUIRED)
	public SysMenu s_getByProp(String PropName,Object PropValue){
	  return sysMenuDao.getByProp(PropName,PropValue);
	}
    //查找根记录List
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults s_findRoot(SysMenuFormBean formBean){
	  return sysMenuDao.m_findRoot(formBean);
	}
    
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults s_findByIds(String ids){
      SysMenu mSysMenu = sysMenuDao.get(Integer.parseInt(ids));
      return sysMenuDao.m_findByCodes(mSysMenu.getCode());
	}
	
}

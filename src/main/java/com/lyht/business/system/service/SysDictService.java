package com.lyht.business.system.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDict;
import com.lyht.business.system.dao.SysDictDao;
import com.lyht.business.system.formBean.SysDictCateFormBean;
import com.lyht.business.system.formBean.SysDictFormBean;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 20:36:29
 *说明:  字典
*/

@Service
@Scope("prototype")
@Transactional
public class SysDictService{
	
	@Resource
	private SysDictDao sysDictDao;
	
	
	//根据id获取实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysDict s_get(int id){
	  SysDict ret_bean= new SysDict();
	  if (id>0) {
		ret_bean= sysDictDao.get(id);
	  }
	  return ret_bean;
	}
	
	
	//增加实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysDict s_create(SysDict bean){
		UUID uuid = UUID.randomUUID();
		String str=uuid.toString().replaceAll("-", "");
		bean.setNm(str);
		sysDictDao.save(bean);
		return bean;
	}
	
	//修改实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysDict s_update(SysDict bean){
		sysDictDao.merge(bean);	
		sysDictDao.flush(bean);
		return bean;
	}	
	
    //删除实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_remove(SysDict bean){
		sysDictDao.delete(bean);
	}
	
    //根据IDS(1,2,3,4,5)删除多个对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_delByIds(String ids){
	   String[] idary=ids.split(",");
	   for(int i=0;i<idary.length;i++){
		   sysDictDao.deleteSysDict(Integer.parseInt(idary[i]));
	   }
	}
	
    //根据IDS(1,2,3,4,5)修改多个对象的Flag数值
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_flagByIds(String ids,Integer flag_new){
	  String[] idary=ids.split(",");
	  for(int i=0;i<idary.length;i++){
		 sysDictDao.updateSysDict(idary[i],flag_new);
	  }
	}
	
    //根据FormBean中的条件查找实体对象List
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)  
	public PageResults s_findAll(SysDictFormBean formBean){
	  return sysDictDao.m_findAll(formBean);
	}
	
	//获取字典数据
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)  
	public PageResults findAll(SysDictCateFormBean mSysDictCateFormBean){
		return sysDictDao.findAll(mSysDictCateFormBean);
	}
	
	//根据名称与内码获取编码
	@SuppressWarnings("rawtypes")
	public PageResults findNmAndName(String nm,String name){
		return sysDictDao.findNmAndName(nm,name);
	}
	
	//获取问题类型、处理方式、通知类型
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)  
	public PageResults getDictType(String flag){
		return sysDictDao.getDictType(flag);
	}
    
    //根据属性及属性值查找对象实体
	@Transactional(propagation=Propagation.REQUIRED)  
	public SysDict s_getByProp(String PropName,Object PropValue){
		return sysDictDao.getByProp(PropName, PropValue);
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)   
	public PageResults s_findByIds(String ids){
		return sysDictDao.m_findByIds(ids);
	}	
	
	//获取字典基础数据
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)  
	public PageResults getDictData(SysDictCateFormBean formBean){
	   return sysDictDao.getDictData(formBean);
	}
}

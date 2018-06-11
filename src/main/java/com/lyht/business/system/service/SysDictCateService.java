package com.lyht.business.system.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDictCate;
import com.lyht.business.system.dao.SysDictCateDao;
import com.lyht.business.system.formBean.SysDictCateFormBean;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 17:28:17
 *说明:  字典分类
*/

@Service
@Scope("prototype")
@Transactional
public class SysDictCateService {
	
	@Resource
	private SysDictCateDao sysDictCateDao;
	
    //根据id获取实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysDictCate s_get(int id){
		SysDictCate ret_bean= new SysDictCate();
		if (id>0) {
			ret_bean= sysDictCateDao.get(id);
		}
		return ret_bean;
	}
    
	//增加实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysDictCate s_create(SysDictCate bean){
		UUID uuid = UUID.randomUUID();
		String str=uuid.toString().replaceAll("-", "");
		bean.setNm(str);
		sysDictCateDao.save(bean);
		return bean;
	}
    
	//修改实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysDictCate s_update(SysDictCate bean){
	   sysDictCateDao.merge(bean);	
	   sysDictCateDao.flush(bean);
	   return bean;
	}	
	
    //删除实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_remove(SysDictCate bean){
		sysDictCateDao.delete(bean);
	}
	
    //根据IDS(1,2,3,4,5)删除多个对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_delByIds(String ids){
	  String[] idary=ids.split(",");
	  for(int i=0;i<idary.length;i++){
		 sysDictCateDao.deleteById(Integer.parseInt(idary[i]));
	  }
	}
	
    //根据IDS(1,2,3,4,5)修改多个对象的Flag数值
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_flagByIds(String ids,Integer flag_new){
	  String[] idary=ids.split(",");
      for(int i=0;i<idary.length;i++){
    	SysDictCate entity = sysDictCateDao.get(Integer.parseInt(idary[i]));
	    entity.setFlag(flag_new);
	    sysDictCateDao.update(entity);
	  }
	}
	
    //根据FormBean中的条件查找实体对象List
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults s_findAll(SysDictCateFormBean formBean){
		return sysDictCateDao.m_findAll(formBean);
	}
    
    //根据属性及属性值查找对象实体
	@Transactional(propagation=Propagation.REQUIRED)
	public SysDictCate s_getByProp(String PropName,Object PropValue){
	  return sysDictCateDao.getByProp(PropName, PropValue);
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults s_findByIds(String ids){
	  return sysDictCateDao.m_findByIds(ids);
	}	
}

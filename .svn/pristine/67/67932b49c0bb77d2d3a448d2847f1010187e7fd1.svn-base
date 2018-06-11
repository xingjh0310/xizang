package com.lyht.business.system.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysIcon;
import com.lyht.business.system.dao.SysIconDao;
import com.lyht.business.system.formBean.SysIconFormBean;

@Service
@Scope("prototype")
@Transactional
public class SysIconService {
	
	@Resource
	private SysIconDao sysIconDao;
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public SysIcon s_get(int id){
		SysIcon ret_bean= new SysIcon();
		if (id>0) {
			ret_bean= sysIconDao.get(id);
		}
		return ret_bean;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public SysIcon s_create(SysIcon bean){
		UUID uuid = UUID.randomUUID();
		String str=uuid.toString().replaceAll("-", "");
		bean.setNm(str);
		sysIconDao.save(bean);
		return bean;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public SysIcon s_update(SysIcon bean){
		sysIconDao.merge(bean);	
		sysIconDao.flush(bean);
		return bean;
	}	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_remove(SysIcon bean){
		sysIconDao.delete(bean);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_delByIds(String ids){
	  String[] idary=ids.split(",");
	  for(int i=0;i<idary.length;i++){
		  sysIconDao.deleteSysIcon(Integer.parseInt(idary[i]));
	  }
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_flagByIds(String ids,Integer flag_new){
	  String[] idary=ids.split(",");
	  for(int i=0;i<idary.length;i++){
		  sysIconDao.updateSysIcon(idary[i],flag_new);
	  }
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults s_findAll(SysIconFormBean formBean,SysAcct sysacct){
		return sysIconDao.m_findAll(formBean,sysacct);
	}
	
	
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)  
	public PageResults s_findByIds(String ids){
	  return sysIconDao.m_findByIds(ids);
	}	
	
}

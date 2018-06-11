package com.lyht.business.system.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.system.bean.QcXtSwitch;

@Repository
@Scope("prototype")
public class QcXtSwitchDao extends HibernateBaseDao<QcXtSwitch>{

   //根据状态获取开关信息
   @SuppressWarnings("unchecked")
   public Integer getSwicthInfo(Integer flag){
	   Integer ret=0;
       StringBuilder sql=new StringBuilder();
	   sql.append("SELECT * FROM SYS_SET WHERE IS_SQ="+flag+"");
	   List<QcXtSwitch> mQcXtSwitchList=this.getSession().createSQLQuery(sql.toString()).addEntity(QcXtSwitch.class).list();
	   if(mQcXtSwitchList.size()>0){
		   ret=mQcXtSwitchList.get(0).getFlag();
	   }
	   return ret;
   }
}

package com.lyht.business.system.dao;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysIcon;
import com.lyht.business.system.formBean.SysIconFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class SysIconDao extends HibernateBaseDao<SysIcon>{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults m_findAll(SysIconFormBean formBean,SysAcct sysacct){
      ArrayList parmValue=new ArrayList(); 
            
		String  sql="SELECT ";
				sql=sql+" P.ID,P.NM,P.CODE,P.NAME,P.MEMO,P.FLAG";
				sql=sql+" ";	
				sql=sql	+ " FROM SYS_ICON AS P  ";
				if("audmins".equals(sysacct.getName())){ //审核账户
					sql=sql	+ " WHERE P.FLAG=0 "; 
				}else{
					sql=sql	+ " WHERE 1=1 ";        
				}
					
		if (formBean!=null){
			if (CommonUtil.trim(formBean.getSearchName()).length()>0){
					 sql=sql+" AND ( ";
					 
                     sql=sql+"  (P.NM LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.CODE LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.NAME LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.MEMO LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                    sql=sql+" )";
                    
			}
			
			if (formBean.getInfoBean()!=null){
					if (CommonUtil.trim(formBean.getInfoBean().getNm()).length()>0) 
					{                      
						sql=sql+" AND P.NM LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getNm())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getCode()).length()>0) 
					{                      
						sql=sql+" AND P.CODE LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getCode())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getName()).length()>0) 
					{                      
						sql=sql+" AND P.NAME LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getName())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getMemo()).length()>0) 
					{                      
						sql=sql+" AND P.MEMO LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getMemo())+"%");
                    }
					
					if (CommonUtil.trim(formBean.getInfoBean().getFlag()).length()>0) 
					{                      
						sql=sql+" AND P.FLAG = ? ";
						parmValue.add(formBean.getInfoBean().getFlag());
                    }
		    }
		
		}
		
        String sql_all=sql+" ORDER BY P."+formBean.getPageBean().getSort()+" "+formBean.getPageBean().getSortOrder();
		
        PageResults retValue =new PageResults();
	    retValue =  this.findPageByFetchedSql(sql_all, null
			,formBean.getPageBean().getOffset()
			,formBean.getPageBean().getLimit()
			,parmValue.toArray());
        
		return retValue;
	}
	
	
	//根据实体属性及值获取实体
	public SysIcon getByProp(String PropName,Object PropValue){
	  return  this.findByObject(PropName, PropValue);
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findByIds(String ids){
      ArrayList parmValue=new ArrayList(); 
            
		String  sql="SELECT ";
				sql=sql+" NEW COM.LYHT.IMPL.SYSTEM.JAVABEAN.SYSICONBEAN(P.ID,P.NM,P.CODE,P.NAME,P.MEMO,P.FLAG)";
				sql=sql+" ";	
				sql=sql	+ " FROM SYSICON AS P  ";
				sql=sql	+" WHERE 1=1 ";        
					
		if (ids.length()>0){
		   sql=sql+" AND P.ID IN ("+ids+")";
		}
			
        String sql_all=sql+" ORDER BY P.ID";
		
		
        PageResults retValue =new PageResults();
        retValue=this.findPageByFetchedSql(sql_all, null, 0, 100000000, parmValue.toArray());
		return retValue;
	}	
	
	//图标管理审核
	public void updateSysIcon(String idary,Integer flag_new){
	   StringBuilder sql=new StringBuilder();
	   sql.append("UPDATE SYS_ICON SET FLAG="+flag_new+" WHERE ID='"+idary+"'");
	   this.exectueSQL(sql.toString());
	}
	
	//图标管理删除
	public void deleteSysIcon(Integer idary){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_ICON WHERE ID="+idary);
		this.exectueSQL(sql.toString());
	}

}

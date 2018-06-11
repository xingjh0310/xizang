package com.lyht.business.system.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.formBean.SysRoleFormBean;
import com.lyht.util.CommonUtil;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 14:27:06
 *说明:  系统角色
*/
@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysRoleDao extends HibernateBaseDao<SysRole>{

   public PageResults m_findAll(SysRoleFormBean formBean){
	      StringBuilder sql=new StringBuilder();
		  sql.append("SELECT P.ID,P.NM,P.CODE,P.NAME,P.MEMO,P.FLAG,P.SYSFLAG FROM SYS_ROLE AS P WHERE P.SYSFLAG!=1 ");
		  if (formBean!=null){
			  if (CommonUtil.trim(formBean.getSearchName()).length()>0){
				  sql.append(" AND ( (P.NM LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' ) OR  ");
				  sql.append("(P.CODE LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' ) OR  ");
				  sql.append("(P.NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' ) OR  ");
				  sql.append("(P.MEMO LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' ) )");
			  }
			  if (formBean.getInfoBean()!=null){
				  if (CommonUtil.trim(formBean.getInfoBean().getNm()).length()>0) {
					  sql.append(" AND P.NM LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getNm())+"%'");
				  }
				  if (CommonUtil.trim(formBean.getInfoBean().getCode()).length()>0) {
					  sql.append(" AND P.CODE LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getCode())+"%'");
				  }
				  if (CommonUtil.trim(formBean.getInfoBean().getName()).length()>0) {
					  sql.append(" AND P.NAME LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getName())+"%'");
				  }
				  if (CommonUtil.trim(formBean.getInfoBean().getMemo()).length()>0) {
					  sql.append(" AND P.MEMO LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getMemo())+"%'");
				  }
			  }
		  }
		  sql.append(" ORDER BY P."+formBean.getPageBean().getSort()+" "+formBean.getPageBean().getSortOrder()+"");
		  return this.findPageByFetchedSql(sql.toString(), null, 0, 1000000000, null);
	}
    
	//根据实体属性及值获取实体
    public SysRole getByProp(String PropName,Object PropValue){
	   return  this.findByObject(PropName, PropValue);
    }
	
	public PageResults m_findByIds(String ids){
      ArrayList parmValue=new ArrayList(); 
            
		String  sql="SELECT ";
				sql=sql+" P.ID,P.NM,P.CODE,P.NAME,P.MEMO,P.FLAG,P.SYSFLAG ";
                sql=sql+"  ";    
				sql=sql	+ " FROM SYS_ROLE AS P  ";
				sql=sql	+ " WHERE 1=1 ";    
					
		if (ids.length()>0){
		   sql=sql+" AND P.ID IN ("+ids+")";
		}
			
        String sql_all=sql+" ORDER BY P.ID";
		
        PageResults retValue =new PageResults();
	    retValue =this.findPageByFetchedSql(sql_all, null
			,0
			,100000000
			,parmValue.toArray());
		    
		return retValue;
	}	
	
	//验证角色编号与角色名称是否唯一
	public boolean checkRoleByCode(SysRole mSysRole){
		boolean flag=true;
	    String sql="SELECT * FROM SYS_ROLE WHERE CODE='"+mSysRole.getCode()+"'  and nm!='"+mSysRole.getNm()+"' ";
	    List<SysRole> list  = this.getListBySQL(sql, null);
	    if(list.size()>0){
	       return false;
	    }
	    return flag;
	}
	//验证角色编号与角色名称是否唯一
	public boolean checkRoleByName(SysRole mSysRole){
		boolean flag=true;
		String sql="SELECT * FROM SYS_ROLE WHERE NAME='"+mSysRole.getName()+"'  and nm!='"+mSysRole.getNm()+"' ";
		List<SysRole> list  = this.getListBySQL(sql, null);
		if(list.size()>0){
			return false;
		}
		return flag;
	}

	//获取系统人员名称
	@SuppressWarnings("unchecked")
	public List<SysRole> getNameByAccount(String code) {
		List<SysRole> mSysRoleList=null;
		try{
			String sql="SELECT * FROM SYS_ROLE WHERE NM='"+code+"'";
			mSysRoleList=this.getSession().createSQLQuery(sql).addEntity(SysRole.class).list();
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mSysRoleList;
	}
	
	/**
	 * 根据编号查询角色信息
	 * */
	public List<Map> getSysRoleInfoByCode(String tbcode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,NM,CODE,NAME,MEMO,FLAG,SYSFLAG FROM SYS_ROLE ");
		sql.append(" WHERE CODE IN ('"+tbcode+"') ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	
	/**
	 * 根据角色内码查询人员名称信息
	 * */
	public List<Map> getStaffNameByRoleNm(String nm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.ID,A.NAME AS STAFFNAME FROM SYS_STAFF A LEFT JOIN SYS_RELA B ON A.NM=B.TB_NM LEFT JOIN SYS_ROLE C "); 
		sql.append("ON B.TA_NAME=C.NM WHERE B.TA_NM='"+nm+"' AND B.CODE='sys_role+sys_staff' ORDER BY A.CODE");
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 根据角色内码删除角色及关联表信息
	 * */
	public void deleteRefTableByRoleNm(String value){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_RELA WHERE TA_NM='"+value+"' AND CODE='sys_role+sys_staff'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据角色内码删除角色及关联表信息(关联菜单)
	 * */
	public void deleteRefTableByRoleNm_(String value){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_RELA WHERE TA_NM='"+value+"' AND CODE='sys_role+sys_menu'");
		this.exectueSQL(sql.toString());
	}
	
}

package com.lyht.business.system.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysRela;

/**
 *作者： czy
 *脚本日期:2017年7月15日 11:09:51
 *说明:  表关联
*/
@Repository
@Scope("prototype")
public class SysRelaDao extends HibernateBaseDao<SysRela>{
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findAll(String str){
		return this.findPageByFetchedSql(str, null, 0, 1000000000, null);
	}
	
	//系统管理员登录页面初始化加载《角色授权菜单功能》
	public String loadRoleAndMenuData(String tbName,String taName){
		StringBuilder sql=new StringBuilder();
  	    sql.append("SELECT ID AS RELAID,ID,NM,CODE,NAME,PNM from (SELECT ISNULL(SYS_RELA.ID,0) AS RELAID,");
  	    sql.append("P.ID,P.NM,P.CODE,P.NAME,P.PNM FROM SYS_MENU AS P LEFT JOIN SYS_RELA ON TB_NAME='"+tbName+"' ");
  	    sql.append("AND TB_NM=P.NM AND TA_NAME='"+taName+"' AND TA_NM='') C WHERE C.CODE LIKE '10%' ORDER BY C.CODE");
  	    return sql.toString();
	}
	
	//系统管理员登录页面（点击）《角色授权菜单功能》
	public String loadRoleAndMenuClickData(String tbName,String taName,String tbCode,String taNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ISNULL(SYS_RELA.ID,0) AS RELAID,P.ID,P.NM,P.CODE,P.NAME,P.PNM,SYS_RELA.STATUS FROM sys_menu AS P  ");
  	    sql.append(" LEFT JOIN SYS_RELA ON TB_NAME='"+tbName+"' AND TB_NM=P.NM AND TA_NAME='"+taName+"' ");
  	    sql.append(" AND TA_NM='"+taNm+"' WHERE 1=1 ");
  	    sql.append(" AND P.CODE LIKE '10%' ");
  	    sql.append(" ORDER BY P.CODE");
		return sql.toString();
	}
	
	//审核账户页面初始加载《角色授权菜单功能》
	public String loadRoleAndMenuAuditData(String tbName,String taName){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT DISTINCT P.ID,ISNULL(P.ID,0) AS RELAID, P.NM,P.CODE,P.NAME ,P.PNM FROM sys_menu AS P  ");
        sql.append("left JOIN SYS_RELA ON TB_NAME='"+tbName+"' ");
        sql.append("AND TB_NM=P.NM  AND TA_NAME='"+taName+"' ");
        sql.append("AND TA_NM in (select distinct B.NM from SYS_RELA A ");
        sql.append("left join sys_role B on A.TA_NM=B.NM where B.NM IS NOT NULL) "); 
        sql.append("WHERE SYS_RELA.STATUS=0 and SYS_RELA.ID>0 ORDER BY P.CODE");
		return sql.toString();
	}
	
	//审核管理员登录页面（点击）《角色授权菜单功能》
	public String loadRoleAndMenuAuditClickData(String tbName,String taName,String tbCode,String taNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ISNULL(SYS_RELA.ID,0) AS RELAID,P.ID,P.NM,P.CODE,P.NAME,P.PNM FROM sys_menu AS P  "); 
  	    sql.append("LEFT JOIN SYS_RELA ON TB_NAME='"+tbName+"' ");
  	    sql.append(" AND TB_NM=P.NM  AND TA_NAME='"+taName+"' AND SYS_RELA.STATUS=0 ");
  	    sql.append(" AND TA_NM='"+taNm+"' "); 
  	    sql.append(" WHERE SYS_RELA.ID>0 ");
  	    if(tbCode.length()>0){
  		   sql.append(" AND P.CODE LIKE '"+tbCode+"' ");
  	    }
  	    sql.append(" ORDER BY P.CODE");
  	    return sql.toString();
	}
	
	//系统管理员登录页面（页面初始化）《人员授权角色功能》
	public String loadStaffAndRoleData(String tbName,String taName){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT P.ID,P.NM,P.CODE,P.NAME,ISNULL(SYS_RELA.ID,0) AS RELAID FROM sys_role AS P  ");
  	    sql.append("LEFT JOIN SYS_RELA ON TB_NAME='"+tbName+"' AND TB_NM=P.NM ");
  	    sql.append("AND TA_NAME='"+taName+"' AND TA_NM='' ");
  	    sql.append(" ORDER BY P.CODE");
  	    return sql.toString();
	}
	
	//系统管理员登录页面（点击）《人员授权角色功能》
	public String loadStaffAndRoleClickData(String tbName,String taName,String tbCode,String taNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT P.ID,SYS_RELA.TA_NM,P.CODE,P.NAME,ISNULL(SYS_RELA.ID,0) AS RELAID,SYS_RELA.STATUS FROM sys_role AS P  ");
  	    sql.append(" LEFT JOIN SYS_RELA ON TB_NAME='"+tbName+"' AND TB_NM=P.NM AND TA_NAME='"+taName+"' ");
  	    if(taNm.length()>0){
  		   sql.append("AND TA_NM='"+taNm+"' ");
  	    }
  	    sql.append(" ORDER BY P.CODE"); //and SYS_RELA.ID>0
  	    return sql.toString();
	}
	
	//审核账户页面初始加载《人员授权角色功能》
	public String loadStaffAndRoleAuditData(String tbName,String taName){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT DISTINCT P.ID,P.CODE,P.NAME,");
		sql.append("SYS_RELA.STATUS FROM sys_role AS P ");
		sql.append("LEFT JOIN SYS_RELA ON TB_NAME='"+tbName+"' AND TB_NM=P.NM AND TA_NAME='"+taName+"' ");
		sql.append("WHERE SYS_RELA.STATUS=0 and SYS_RELA.ID>0 ORDER BY P.CODE ");
		return sql.toString();
	}
	
	//审核账户页面（点击）《人员授权角色功能》
	public String loadStaffAndRoleAuditClickData(String tbName,String taName,String tbCode,String taNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT P.ID,SYS_RELA.TA_NM,P.CODE,P.NAME,ISNULL(SYS_RELA.ID,0) AS RELAID,");
		sql.append("SYS_RELA.STATUS FROM sys_role AS P ");
		sql.append("LEFT JOIN SYS_RELA ON TB_NAME='"+tbName+"' AND TB_NM=P.NM AND TA_NAME='"+taName+"' ");
  	    if(taNm.length()>0){
  		   sql.append("AND TA_NM='"+taNm+"' ");
  	    }
  	    sql.append("WHERE SYS_RELA.STATUS=0 and SYS_RELA.ID>0 ORDER BY P.CODE ");
		return sql.toString();
	}
	
	
	//取消授权
	public void qx(String tbcode,String ta_name,String ta_nm,String tb_name){
	    String sql="DELETE FROM SYS_RELA ";
  	  	sql+="WHERE TA_NAME='"+ta_name+"' AND TB_NAME='"+tb_name+"' AND TA_NM='"+ta_nm+"' ";
  	  	if (!"".equals(tbcode)){
  		  sql+="AND TB_NM IN (SELECT NM FROM "+tb_name+" WHERE CODE LIKE '"+tbcode+"' )";
  	  	}
  	    this.exectueSQL(sql);
	}
	
	//授权(角色或菜单)
	public void authRole(String ta_nm,String ta_name,String tb_nm,String tb_name){
	    StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SYS_RELA(CODE,TA_NM,TA_NAME,TB_NM,TB_NAME,FLAG,STATUS) ");
		sql.append(" VALUES ('"+ta_name+"+"+tb_name+"','"+ta_nm+"',");
		sql.append("'"+ta_name+"','"+tb_nm+"','"+tb_name+"',0,0)");
	    this.exectueSQL(sql.toString());
	}
	
	//审核
	public void sh(String tbcode,String ta_nm,String tb_name){
		StringBuilder mStringBuilder=new StringBuilder();
		mStringBuilder.append("UPDATE SYS_RELA SET STATUS=1 WHERE TA_NM='"+ta_nm+"'"); 
		mStringBuilder.append(" AND STATUS IS NOT NULL AND TB_NM in (SELECT NM FROM "+tb_name+""); 
		mStringBuilder.append(" WHERE CODE LIKE '"+tbcode+"' )");
  	    this.exectueSQL(mStringBuilder.toString());
	}
	
	@SuppressWarnings("unchecked")
	public List<SysRela> getSysRelaNmByStaffCode(String code) {
		List<SysRela> mSysRelaList=null;
		try{
			String sql="SELECT * FROM SYS_RELA WHERE TB_NM='"+code+"'";
			mSysRelaList=this.getSession().createSQLQuery(sql).addEntity(SysRela.class).list();
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mSysRelaList;
	}
	
	/**
	 * 根菜单内码与角色内码移除菜单信息
	 * */
	public void removeSysMenuNm(String key,String ta_nm){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_RELA WHERE TA_NM='"+ta_nm+"' AND TB_NM='"+key+"'"); 
  	    this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据角色编号与组合表名称删除关联表
	 * */
	public void deleteRefTableByRoleCode(String taNm,String tableRefName){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_RELA WHERE TA_NM='"+taNm+"' AND CODE='"+tableRefName+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员编号与组合表名称删除关联表
	 * */
	public void deleteRefTableByStaffCode(String taNm,String tableRefName){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_RELA WHERE TB_NM='"+taNm+"' AND CODE='"+tableRefName+"'");
		this.exectueSQL(sql.toString());
	}
}

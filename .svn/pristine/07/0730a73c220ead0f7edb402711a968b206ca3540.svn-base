package com.lyht.business.system.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.formBean.SysAcctFormBean;
import com.lyht.business.system.formBean.SysStaffRefAcctFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.MD5;
import com.lyht.util.Randomizer;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 21:28:36
 *说明:  账户信息
*/
@Repository
@Scope("prototype")
public class SysAcctDao extends HibernateBaseDao<SysAcct>{

	//根据FormBean中的条件查找实体对象List
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findAll(SysAcctFormBean formBean){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("SELECT P.ID,P.NM,P.NAME,P.FLAG,P.DICTNM_DLLX,P.PWD,P.YXQ,P.DICTNM_ZHLX,P.YZM,P.YZMYXQ,");
		sql.append("P.YZYXCS,P.LISTNM_SYS_STAFF,P.SYSFLAG,SYSDICT_DLLX.NAME AS DLLXNAME,");
		sql.append("SYSDICT_ZHLX.NAME AS ZHLXNAME,SYSSTAFF.NAME AS SYSSTAFFNAME FROM SYS_ACCT AS P");
		sql.append(" LEFT JOIN SYS_DICT AS SYSDICT_DLLX ON P.DICTNM_DLLX=SYSDICT_DLLX.NM AND SYSDICT_DLLX.LISTNM_SYS_DICT_CATE='dllx'");
		sql.append(" LEFT JOIN SYS_DICT AS SYSDICT_ZHLX ON P.DICTNM_ZHLX=SYSDICT_ZHLX.NM AND SYSDICT_ZHLX.LISTNM_SYS_DICT_CATE='zhlx'");
		sql.append(" LEFT JOIN SYS_STAFF AS SYSSTAFF ON P.LISTNM_SYS_STAFF= SYSSTAFF.NM ");
		sql.append("WHERE 1=1");
		
		if (formBean!=null){
			//字符串字段模糊匹配
			if (CommonUtil.trim(formBean.getSearchName()).length()>0){
					 sql.append(" AND ( ");
					 sql.append("  (P.NM LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
					 sql.append("  OR  (P.NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
					 sql.append("  OR  (SYSDICT_DLLX.NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
					 sql.append("  OR  (P.PWD LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
					 sql.append("  OR  (SYSDICT_ZHLX.NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
					 sql.append("  OR  (P.YZM LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
					 sql.append("  OR  (SYSSTAFF.NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
					 sql.append(" )");
			}
			//字段条件查询，根据需要自己增加
			if (formBean.getInfoBean()!=null){
					if (CommonUtil.trim(formBean.getInfoBean().getNm()).length()>0) 
					{                   
						sql.append(" AND P.NM LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getNm())+"%' ");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getName()).length()>0) 
					{                 
						sql.append(" AND P.NAME LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getName())+"%' ");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getDictnmDllx()).length()>0) 
					{               
						sql.append(" AND P.DICTNM_DLLX = '"+CommonUtil.trim(formBean.getInfoBean().getDictnmDllx())+"' ");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getPwd()).length()>0) 
					{              
						sql.append(" AND P.PWD LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getPwd())+"%' ");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getDictnmZhlx()).length()>0) 
					{            
						sql.append(" AND P.DICTNM_ZHLX = '"+CommonUtil.trim(formBean.getInfoBean().getDictnmZhlx())+"' ");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getYzm()).length()>0) 
					{                      
						sql.append(" AND P.YZM LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getYzm())+"%' ");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getListnmSysStaff()).length()>0) 
					{           
						sql.append(" AND P.LISTNM_SYS_STAFF = '"+CommonUtil.trim(formBean.getInfoBean().getListnmSysStaff())+"' ");
                    }
					
					if (CommonUtil.trim(formBean.getInfoBean().getSysflag()).length()>0) 
					{            
						sql.append(" AND P.SYSFLAG = '"+formBean.getInfoBean().getSysflag()+"' ");
                    }
		    }
		}
		
		sql.append(" ORDER BY P."+formBean.getPageBean().getSort()+" "+formBean.getPageBean().getSortOrder());
		
		PageResults retValue =new PageResults();
		retValue =this.findPageByFetchedSql(sql.toString(), null
    			,formBean.getPageBean().getOffset()
    			,formBean.getPageBean().getLimit()
    			,null);
		    
		return retValue;
	}
    
	//根据实体属性及值获取实体
	public SysAcct getByProp(String PropName,Object PropValue){
		return  this.findByObject(PropName, PropValue);
	}
	
	/**
	 * 验证手机号是否唯一
	 * */
	@SuppressWarnings({ "rawtypes" })
	public List<Map> getSysStaffRefAcctByPhone(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		String nm=mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getNm();
		String phone=mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getName();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT P.ID,P.NM,P.NAME,P.FLAG,P.DICTNM_DLLX,P.PWD,P.YXQ,");
		sql.append("P.DICTNM_ZHLX,P.YZM,P.YZMYXQ,P.YZYXCS,P.LISTNM_SYS_STAFF,");
		sql.append("P.SYSFLAG FROM SYS_ACCT AS P WHERE P.LISTNM_SYS_STAFF!='"+nm+"' AND P.NAME='"+phone+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
    
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findByIds(String ids){
		
	   StringBuilder sql=new StringBuilder();
				
	   sql.append("SELECT P.ID,P.NM,P.NAME,P.FLAG,P.DICTNM_DLLX,P.PWD,P.YXQ,P.DICTNM_ZHLX,P.YZM,P.YZMYXQ,P.YZYXCS,P.LISTNM_SYS_STAFF,P.SYSFLAG,");
	   sql.append("SYSDICT_DLLX.NAME AS DLLXNAME,SYSDICT_ZHLX.NAME AS ZHLXNAME,SYSSTAFF.NAME AS SYSSTAFFNAME FROM SYS_ACCT AS P");
	   sql.append(" LEFT JOIN SYS_DICT AS SYSDICT_DLLX ON P.DICTNM_DLLX=SYSDICT_DLLX.NM AND SYSDICT_DLLX.LISTNM_SYS_DICT_CATE='dllx'");
	   sql.append(" LEFT JOIN SYS_DICT AS SYSDICT_ZHLX ON P.DICTNM_ZHLX=SYSDICT_ZHLX.NM AND SYSDICT_ZHLX.LISTNM_SYS_DICT_CATE='zhlx'");
	   sql.append(" LEFT JOIN SYS_STAFF AS SYSSTAFF ON P.LISTNM_SYS_STAFF= SYSSTAFF.NM WHERE 1=1");	
				
		if (ids.length()>0){
		   sql.append(" AND P.ID IN ("+ids+")");
		}
			
		sql.append(" ORDER BY P.ID");

        PageResults retValue =new PageResults();
		    retValue =this.findPageByFetchedSql(sql.toString(), null
    			,0
    			,100000000
    			,null);
                
		return retValue;
	}	
	
	//验证账户密码(账户名登录)
	@SuppressWarnings({ "unchecked" })
	public SysAcct acct_check(SysAcct loginacct) {
		List<SysAcct> list  =null;
	    SysAcct ret=new SysAcct();
	    StringBuilder sql=new StringBuilder();
		sql.append("SELECT DISTINCT A.* FROM SYS_ACCT A LEFT JOIN SYS_STAFF B ON A.LISTNM_SYS_STAFF = B.NM");
		sql.append(" LEFT JOIN SYS_RELA C ON (B.NM = C.TA_NM AND C.CODE = 'sys_staff+sys_role' AND C.TA_NAME = 'sys_staff')");
		sql.append(" LEFT JOIN SYS_ROLE D ON (D.NM = C.TB_NM AND C.TB_NAME = 'sys_role' AND D.SYSFLAG = '1') WHERE 1=1 ");
		if (loginacct!=null){
			sql.append(" AND A.NAME='"+loginacct.getName()+"' AND A.DICTNM_ZHLX='"+loginacct.getDictnmZhlx()+"' ");
			sql.append("AND A.DICTNM_DLLX='"+loginacct.getDictnmDllx()+"'");
		}
		try{
			list=this.getSession().createSQLQuery(sql.toString()).addEntity(SysAcct.class).list();
			if(list.size() > 0 ){
				ret = list.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return ret;
		
	}
	
	//验证账户密码(手机号登录)
	@SuppressWarnings({ "unchecked" })
	public SysAcct acct_checkByPhone(SysAcct loginacct) {
		List<SysAcct> list  =null;
	    SysAcct ret=new SysAcct();
	    StringBuilder sql=new StringBuilder();
		sql.append("SELECT DISTINCT A.ID,A.NM,B.PHONE AS NAME,A.FLAG,A.DICTNM_DLLX,A.PWD,A.YXQ,A.DICTNM_ZHLX,A.YZM,A.YZMYXQ,A.YZYXCS,A.LISTNM_SYS_STAFF,A.SYSFLAG FROM SYS_ACCT A LEFT JOIN SYS_STAFF B ON A.LISTNM_SYS_STAFF = B.NM");
		sql.append(" LEFT JOIN SYS_RELA C ON (B.NM = C.TA_NM AND C.CODE = 'sys_staff+sys_role' AND C.TA_NAME = 'sys_staff')");
		sql.append(" LEFT JOIN SYS_ROLE D ON (D.NM = C.TB_NM AND C.TB_NAME = 'sys_role' AND D.SYSFLAG = '0') WHERE 1=1");
		if (loginacct!=null){
			sql.append(" AND B.PHONE='"+loginacct.getName()+"'");
		}
		try{
			list=this.getSession().createSQLQuery(sql.toString()).addEntity(SysAcct.class).list();
			if(list.size() > 0 ){
				ret = list.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return ret;
	}
	
	//查询账户功能权限
	@SuppressWarnings({ "rawtypes" })
	public PageResults acct_menu(SysStaff loginacct) {
	   StringBuilder sql=new StringBuilder();
		   
	   sql.append("SELECT ID,NM,CODE,PCODE,NAME,URL,CASE WHEN LEN(ICON)=0 THEN 'ICON-STAR' ELSE  ICON END AS ICON,ICONSIZE,ICONCOLOR,ICONSPIN,");
	   sql.append("CASE WHEN LEN(TITLECOLOR)=0 THEN 'TEXT-PRIMARY' ELSE TITLECOLOR END AS TITLECOLOR");
	   sql.append(" FROM SYS_MENU WHERE LEN(CODE)<10 AND NM IN ( SELECT TB_NM FROM SYS_RELA WHERE TA_NAME='SYS_ROLE' AND TB_NAME='SYS_MENU'");
	   sql.append(" AND TA_NM IN ( SELECT TB_NM FROM SYS_RELA WHERE TA_NAME='SYS_STAFF' AND TA_NM='"+loginacct.getNm()+"' AND TB_NAME='SYS_ROLE' ))");
	   sql.append(" ORDER BY CODE ");
	   
	   PageResults retValue =new PageResults();
	   retValue =this.findPageByFetchedSql(sql.toString(), null, 0,100000000,null);
	   return retValue; 
	}
	
	//获取账户功能权限
	@SuppressWarnings("rawtypes")
	public PageResults getMenuByAccount(String roleNm) {
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT DISTINCT A.ID,A.NM,A.CODE,A.PCODE,A.NAME,A.URL,A.ICON,A.ICONSIZE,");
        sql.append("A.ICONCOLOR,A.ICONSPIN,A.TITLECOLOR FROM  (SELECT ID,NM,CODE,PCODE,NAME,");
        sql.append("URL,CASE WHEN LEN(ICON)=0 THEN 'ICON-STAR' ELSE ICON END AS ICON,ICONSIZE,");
        sql.append("ICONCOLOR,ICONSPIN,CASE WHEN LEN(TITLECOLOR)=0 THEN 'TEXT-PRIMARY' ");
        sql.append("ELSE TITLECOLOR END AS TITLECOLOR FROM SYS_MENU WHERE LEN(CODE)<10 ");
        sql.append("AND NM IN (SELECT TB_NM FROM SYS_RELA WHERE TA_NAME='sys_role'"); 
        sql.append("AND TA_NM='"+roleNm+"' AND TB_NAME='sys_menu')) A ");
        sql.append("LEFT JOIN SYS_RELA B ON A.NM=B.TB_NM WHERE A.CODE LIKE '10%'");
		PageResults retValue =new PageResults();
		retValue =this.findPageByFetchedSql(sql.toString(), null, 0,100000000,null);
		return retValue;
	}
	
	//获取账户功能权限
	@SuppressWarnings("rawtypes")
	public PageResults getMenuByAccount() {
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT A.ID,A.NM,A.CODE,A.PCODE,A.NAME,A.URL,A.ICON,A.ICONSIZE,A.ICONCOLOR,");
        sql.append("A.ICONSPIN,A.TITLECOLOR FROM SYS_MENU A WHERE A.CODE LIKE '99%' ");
		PageResults retValue =new PageResults();
		retValue =this.findPageByFetchedSql(sql.toString(), null, 0,100000000,null);
		return retValue;
	}
	
	//获取账户功能权限（进入后台）
	@SuppressWarnings("rawtypes")
	public PageResults getMenuByAccount_() {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT DISTINCT A.ID,A.NM,A.CODE,A.PCODE,A.NAME,A.URL,A.ICON,A.ICONSIZE,A.ICONCOLOR,A.ICONSPIN,A.TITLECOLOR FROM ");
        sql.append(" (SELECT ID,NM,CODE,PCODE,NAME,URL,CASE WHEN LEN(ICON)=0 THEN 'ICON-STAR' ELSE ICON END AS ICON,ICONSIZE,ICONCOLOR,ICONSPIN,");
		sql.append("CASE WHEN LEN(TITLECOLOR)=0 THEN 'TEXT-PRIMARY' ELSE TITLECOLOR END AS TITLECOLOR FROM SYS_MENU ");
		sql.append("WHERE LEN(CODE)<10 AND NM IN (SELECT TB_NM FROM SYS_RELA WHERE TA_NAME='sys_role' ");
		sql.append("AND TB_NAME='sys_menu')) A LEFT JOIN SYS_RELA B ON A.NM=B.TB_NM WHERE A.CODE LIKE '10%'");
		PageResults retValue =new PageResults();
		retValue =this.findPageByFetchedSql(sql.toString(), null, 0,100000000,null);
		return retValue;
	}
	
	//验证账户是否唯一
    public boolean checkOnlyByName(SysAcct mSysAcct){
       boolean flag=true;
       String sql="SELECT * FROM SYS_ACCT WHERE NAME='"+mSysAcct.getName()+"' AND LISTNM_SYS_STAFF='"+mSysAcct.getListnmSysStaff()+"'";
       List<SysAcct> list  = this.getListBySQL(sql, null);
       if(list.size()>0){
    	   return false;
       }
       return flag;
    }
    
    //初始化密码
    public void updatePwdById(String id){
    	String pwd=MD5.getInstance().getMD5ofStr("123456",16);
    	String sql="UPDATE SYS_ACCT SET PWD='"+pwd+"' WHERE ID='"+id+"'";
    	this.exectueSQL(sql);
    }
    
    //验证用户名与密码是否存在
    public boolean isExist(SysAcct mSysAcct){
  		boolean flag=false;
  		String pwd=MD5.getInstance().getMD5ofStr(mSysAcct.getPwd(),16);
        String sql="SELECT * FROM SYS_ACCT WHERE NAME='"+mSysAcct.getName()+"' AND PWD='"+pwd+"'";
        List<SysAcct> list  = this.getListBySQL(sql, null);
        if(list.size()>0){
     	   return true;
        }
        return flag;
  	}
    
    //根据用户名修改密码
  	public void updatePwdByName(SysAcct mSysAcct,String newPwd){
  		String pwd=MD5.getInstance().getMD5ofStr(newPwd,16);
    	String sql="UPDATE SYS_ACCT SET PWD='"+pwd+"' WHERE NAME='"+mSysAcct.getName()+"'";
    	this.exectueSQL(sql);
  	}
  	
  	/**
	 * 根据人员内码查询人员信息
	 * */
	@SuppressWarnings("unchecked")
	public List<SysAcct> getSysAcctByStaffNm(String staffNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,NM,NAME,FLAG,DICTNM_DLLX,PWD,YXQ,DICTNM_ZHLX,YZM,");
		sql.append("YZMYXQ,YZYXCS,LISTNM_SYS_STAFF,SYSFLAG FROM SYS_ACCT ");
		sql.append(" WHERE LISTNM_SYS_STAFF='"+staffNm+"'");
		return this.getSession().createSQLQuery(sql.toString()).addEntity(SysAcct.class).list();
	}
	
	/**
	 * 根据账户名称查询账户信息
	 * */
	@SuppressWarnings("rawtypes")
	public List<Map> getSysAcctInfoByName(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,NM,NAME,FLAG,DICTNM_DLLX,PWD,YXQ,DICTNM_ZHLX,YZM,");
		sql.append("YZMYXQ,YZYXCS,LISTNM_SYS_STAFF,SYSFLAG FROM SYS_ACCT ");
		sql.append(" WHERE NAME='"+mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getName()+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 新增账户信息
	 * */
	public int insertSysAcctInfo(SysAcct mSysAcct,String staffNm){
		String acctNm=Randomizer.getRandom();
		String pwd=MD5.getInstance().getMD5ofStr("123456",16);
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SYS_ACCT (NM,NAME,FLAG,DICTNM_DLLX,PWD,DICTNM_ZHLX,");
		sql.append("YZM,YZMYXQ,YZYXCS,LISTNM_SYS_STAFF,SYSFLAG) VALUES ('"+acctNm+"',");
		sql.append("'"+mSysAcct.getName()+"','0','phone',");
		sql.append("'"+pwd+"','system','"+mSysAcct.getYzm()+"',");
		sql.append("'"+mSysAcct.getYzmyxq()+"','"+mSysAcct.getYzyxcs()+"',");
		sql.append("'"+staffNm+"',0)");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员内码修改账户信息
	 * */
	public int updateSysAcctInfo(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_ACCT SET NM='"+mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getNm()+"',");
		sql.append("NAME='"+mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getName()+"',");
		sql.append("FLAG='"+mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getFlag()+"',");
		sql.append("DICTNM_DLLX='"+mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getDictnmDllx()+"',");
		sql.append("PWD='"+mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getPwd()+"',");
		sql.append("DICTNM_ZHLX='"+mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getDictnmZhlx()+"',");
		sql.append("YZM='"+mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getYzm()+"',");
		sql.append("YZMYXQ='"+mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getYzmyxq()+"',");
		sql.append("YZYXCS='"+mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getYzyxcs()+"',");
		sql.append("LISTNM_SYS_STAFF='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getNm()+"',");
		sql.append("SYSFLAG='"+mSysStaffRefAcctFormBean.getmSysAcctInfoBean().getSysflag()+"' ");
		sql.append("WHERE LISTNM_SYS_STAFF='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getNm()+"'");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员内码删除账户信息
	 * */
	public int deleteSysAcctInfo(String StaffNm){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_ACCT WHERE LISTNM_SYS_STAFF='"+StaffNm+"'");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	 * 重置密码
	 * */
	public int resetSysAcctInfo(String staffNm){
		String pwd=MD5.getInstance().getMD5ofStr("123456",16);
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_ACCT SET PWD='"+pwd+"' WHERE LISTNM_SYS_STAFF='"+staffNm+"'");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	 * 通过手机号验证账户信息是否存在
	 * */
	@SuppressWarnings("unchecked")
	public List<SysAcct> checkSysAcctInfoByPhone(String acctName){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,NM,NAME,FLAG,DICTNM_DLLX,PWD,YXQ,DICTNM_ZHLX,YZM,");
		sql.append("YZMYXQ,YZYXCS,LISTNM_SYS_STAFF,SYSFLAG FROM SYS_ACCT ");
		sql.append(" WHERE NAME='"+acctName+"'");
		return this.getSession().createSQLQuery(sql.toString()).addEntity(SysAcct.class).list();
	}
	//校验用户名和密码
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean check(SysAcctFormBean formBean) {
		boolean flag=false;
		ArrayList list=new ArrayList(); 
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NAME,PWD FROM sys_acct WHERE NAME=? AND PWD =? ");
		String pwd=MD5.getInstance().getMD5ofStr(formBean.getOldPwd(),16);
		list.add(formBean.getInfoBean().getName());
		list.add(pwd);
		
		 List<SysAcct> acct  = this.getListBySQL(sql.toString(), list.toArray());
        
         if(acct.size()>0){
        	 flag=true;
         }
         return flag;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void editPass(SysAcctFormBean formBean) {
		ArrayList list=new ArrayList(); 
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE sys_acct  set pwd=?  WHERE NAME=?");
		String pwd=MD5.getInstance().getMD5ofStr(formBean.getNewPwd(),16);
		String name = formBean.getInfoBean().getName();
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		query.setParameter(0,pwd);
		query.setParameter(1,name);
		query.executeUpdate();
	}
	
}

package com.lyht.business.system.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.formBean.SysMenuFormBean;
import com.lyht.util.CommonUtil;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 16:08:49
 *说明:  系统菜单
*/
@Repository
@Scope("prototype")
public class SysMenuDao extends HibernateBaseDao<SysMenu>{

	//根据FormBean中的条件查找实体对象List
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults m_findAll(SysMenuFormBean formBean,SysAcct sysacct){
      ArrayList parmValue=new ArrayList(); 
      
		String  sql="SELECT ";
				sql=sql+" P.ID,P.NM,P.PNM,P.THISCODE,P.CODE,P.PCODE,P.NAME,P.FLAG,P.ICON,P.ICONSIZE,P.ICONCOLOR,P.ICONSPIN,P.TITLECOLOR,P.URL,P.MENUFLAG,P.ISBTN ";
                sql=sql+"  ";    
				sql=sql	+ " FROM SYS_MENU AS P  WHERE 1=1";
		if (formBean!=null){
			//字符串字段模糊匹配
			if (CommonUtil.trim(formBean.getSearchName()).length()>0){
					 sql=sql+" AND ( ";
					 
                     sql=sql+"  (P.NM LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.PNM LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.THISCODE LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.CODE LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.PCODE LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.NAME LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.ICON LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.ICONSIZE LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.ICONCOLOR LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.ICONSPIN LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.TITLECOLOR LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.URL LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.MENUFLAG LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+" )";
                    
			}
			//字段条件查询，根据需要自己增加
			if (formBean.getInfoBean()!=null){
					if (CommonUtil.trim(formBean.getInfoBean().getNm()).length()>0) 
					{                      
						sql=sql+" AND P.NM LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getNm())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getPnm()).length()>0) 
					{                      
						sql=sql+" AND P.PNM LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getPnm())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getThiscode()).length()>0) 
					{                      
						sql=sql+" AND P.THISCODE LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getThiscode())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getCode()).length()>0) 
					{                      
						sql=sql+" AND P.CODE LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getCode())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getPcode()).length()>0) 
					{                      
						sql=sql+" AND P.PCODE LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getPcode())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getName()).length()>0) 
					{                      
						sql=sql+" AND P.NAME LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getName())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getIcon()).length()>0) 
					{                      
						sql=sql+" AND P.ICON LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getIcon())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getIconsize()).length()>0) 
					{                      
						sql=sql+" AND P.ICONSIZE LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getIconsize())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getIconcolor()).length()>0) 
					{                      
						sql=sql+" AND P.ICONCOLOR LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getIconcolor())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getIconspin()).length()>0) 
					{                      
						sql=sql+" AND P.ICONSPIN LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getIconspin())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getTitlecolor()).length()>0) 
					{                      
						sql=sql+" AND P.TITLECOLOR LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getTitlecolor())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getUrl()).length()>0) 
					{                      
						sql=sql+" AND P.URL LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getUrl())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getMenuflag()).length()>0) 
					{                      
						sql=sql+" AND P.MENUFLAG LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getMenuflag())+"%");
                    }
					
					if (CommonUtil.trim(formBean.getInfoBean().getFlag()).length()>0) 
					{                      
						sql=sql+" AND P.FLAG = ? ";
						parmValue.add(formBean.getInfoBean().getFlag());
                    }
		    }
		}
		
        String sql_all=sql+" ORDER BY P.CODE";
		
        PageResults retValue =new PageResults();
	    retValue =  this.findPageByFetchedSql(sql_all, null
			,formBean.getPageBean().getOffset()
			,formBean.getPageBean().getLimit()
			,parmValue.toArray());
	    
		return retValue;
	}
    
	//根据实体属性及值获取实体
	public SysMenu getByProp(String PropName,Object PropValue){
		return  this.findByObject(PropName, PropValue);
	}
    
	public void  updataChildCode(SysMenu oldbean,SysMenu newbean){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYSMENU P SET P.PCODE='"+newbean.getCode()+"',");
		sql.append("P.CODE='"+newbean.getCode()+"'+P.THISCODE");
		sql.append(" WHERE P.CODE LIKE '"+oldbean.getCode()+"%' AND P.CODE<> '"+newbean.getCode()+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据编码删除本项及子项
	 * @param pcode
	 */
	public void removeByCode(String pcode){
	    StringBuilder sql=new StringBuilder();
	    sql.append("DELETE FROM SYSMENU P WHERE P.CODE LIKE '"+pcode+"%'");
	    this.exectueSQL(sql.toString());
	}
    
	/**
	 * 根据编码审核本项及子项
	 * @param pcode
	 */
	public void flagByCode(String pcode,Integer flag_new){
	    StringBuilder sql=new StringBuilder();
	    sql.append("UPDATE SYSMENU P SET P.FLAG='"+flag_new+"' WHERE P.CODE LIKE '"+pcode+"%'");
	    this.exectueSQL(sql.toString());
	}
    
    /**
     * 查找根记录List
	 * @param pcode
    */
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findRoot(SysMenuFormBean formBean){
      ArrayList parmValue=new ArrayList(); 
		String  sql="SELECT ";
				sql=sql+" P.ID,P.NM,P.PNM,P.THISCODE,P.CODE,P.PCODE,P.NAME,P.FLAG,P.ICON,P.ICONSIZE,P.ICONCOLOR,P.ICONSPIN,P.TITLECOLOR,P.URL,P.MENUFLAG,P.ISBTN ";
                sql=sql+"  ";    
				sql=sql	+ " FROM SYS_MENU AS P  ";
				sql=sql	+ " WHERE P.PNM='' ";        
        String sql_all=sql+" ORDER BY P.CODE ";
        PageResults retValue =new PageResults();
	    retValue =  this.findPageByFetchedSql(sql_all, null
			,formBean.getPageBean().getOffset()
			,formBean.getPageBean().getLimit()
			,parmValue.toArray());
	    
		return retValue;
	}
    
    
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findByCodes(String codes){
      ArrayList parmValue=new ArrayList(); 
            
		String  sql="SELECT ";
				sql=sql+" P.ID,P.NM,P.PNM,P.THISCODE,P.CODE,P.PCODE,P.NAME,P.FLAG,P.ICON,P.ICONSIZE,P.ICONCOLOR,P.ICONSPIN,P.TITLECOLOR,P.URL,P.MENUFLAG,P.ISBTN ";
                sql=sql+"  ";    
				sql=sql	+ " FROM SYS_MENU AS P  ";
           
				sql=sql	+ " WHERE 1=1 ";    
					
		if (codes.length()>0){
				sql=sql+" AND P.CODE LIKE '"+codes+"%'";
		} else {
			sql=sql+" AND 1<>1 ";
		}
			
        String sql_all=sql+" ORDER BY P.CODE";
		
        PageResults retValue =new PageResults();
	    retValue =  this.findPageByFetchedSql(sql_all, null
			,0,0,parmValue.toArray());
        
        return retValue;
	}

	public void s_flagById(String parseInt,Integer flag_new) {
		StringBuilder sql=new StringBuilder();
	    sql.append("UPDATE SYS_MENU SET FLAG='"+flag_new+"' WHERE CHARINDEX('"+parseInt+"',CODE) = 1");
	    this.exectueSQL(sql.toString());
	}

	public void deleteAndChildById(String parseInt) {
		StringBuilder sql=new StringBuilder();
	    sql.append("DELETE SYS_MENU WHERE CODE like  '"+parseInt+"%'");
	    this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据tbcode 与 tb_name 查询菜单信息
	 * */
	@SuppressWarnings("rawtypes")
	public List<Map> getSysMenuInfoByCode(String tbcode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,NM,PNM,THISCODE,CODE,PCODE,NAME,FLAG,ICON,");
		sql.append("ICONSIZE,ICONCOLOR,ICONSPIN,TITLECOLOR,URL,MENUFLAG,");
		sql.append("ISBTN FROM SYS_MENU ");
		if(!"".equals(tbcode)){
			sql.append("WHERE CODE LIKE '"+tbcode+"'");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据关联节点查询菜单信息
	 * */
	@SuppressWarnings("rawtypes")
	public List<Map> getSysMenuInfoByPCode(String pCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,NM,PNM,THISCODE,CODE,PCODE,NAME,FLAG,ICON,");
		sql.append("ICONSIZE,ICONCOLOR,ICONSPIN,TITLECOLOR,URL,MENUFLAG,");
		sql.append("ISBTN FROM SYS_MENU WHERE CODE='"+pCode+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}

}

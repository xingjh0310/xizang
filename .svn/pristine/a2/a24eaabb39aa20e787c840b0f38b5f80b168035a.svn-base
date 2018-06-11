package com.lyht.business.system.dao;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDictCate;
import com.lyht.business.system.formBean.SysDictCateFormBean;
import com.lyht.util.CommonUtil;


/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 17:28:17
 *说明:  字典分类
*/

@Repository
@Scope("prototype")
public class SysDictCateDao extends HibernateBaseDao<SysDictCate>{
	
	//根据实体属性及值获取实体
    public SysDictCate getByProp(String PropName,Object PropValue){
	  return  this.findByObject(PropName, PropValue);
    }

    //根据FormBean中的条件查找实体对象List
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findAll(SysDictCateFormBean formBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT P.ID,P.NM,P.CODE,P.NAME,P.MEMO,P.FLAG,P.SYSFLAG FROM SYS_DICT_CATE AS P  WHERE 1=1 ");
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
    
    
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findByIds(String ids){
		String  sql="SELECT ";
				sql=sql+" P.ID,P.NM,P.CODE,P.NAME,P.MEMO,P.FLAG,P.SYSFLAG ";
                sql=sql+"  ";    
				sql=sql	+ " FROM SYS_DICT_CATE AS P  ";
				sql=sql	+ " WHERE 1=1 ";    
					
		if (ids.length()>0){
				sql=sql+" AND P.ID IN ("+ids+")";
		}
			
        String sql_all=sql+" ORDER BY P.ID";
		
        PageResults retValue =new PageResults();
	    retValue =this.findPageByFetchedSql(sql_all, null, 0, 100000000, null);
		    
		return retValue;
	}	
    

}

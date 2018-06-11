package com.lyht.business.distributor.dao;

import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.distributor.bean.Distributor;
import com.lyht.business.distributor.formBean.DistributorFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class DistributorDao extends HibernateBaseDao<Distributor>{

	/**
	 * 查看供应商信息
	 */
	@SuppressWarnings({ "rawtypes" })
	public PageResults queryAllDistributor(DistributorFormBean fDistributorFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT SUPPLIER.ID,SUPPLIER.SUPPLIER_CODE,SUPPLIER.SUPPLY_FULL_NAME,SUPPLIER.SUPPLY_BUILT,SUPPLIER.REGISTRATION_NO,SYSSTAFF.NAME,");
		sql.append("SUPPLIER.LINK_PHONE,SUPPLIER.ADDRESS,SUPPLIER.LEGAL_PERSON,SUPPLIER.FAX,SUPPLIER.REMARK,FILETABLE.FILENUB AS FILENUB ");
		sql.append("FROM PUB_SUPPLIER SUPPLIER LEFT JOIN SYS_STAFF SYSSTAFF ON SUPPLIER.LISTNM_SYS_STAFF=SYSSTAFF.NM ");
		sql.append("LEFT JOIN (SELECT TABLE_PK_COLUMN,COUNT(TABLE_PK_COLUMN) FILENUB FROM PUB_FILES WHERE TABLE_NAME = 'PUB_SUPPLIER' GROUP BY TABLE_PK_COLUMN) FILETABLE ");
		sql.append("ON SUPPLIER.ID = FILETABLE.TABLE_PK_COLUMN WHERE 1=1 ");
		if(fDistributorFormBean != null){
			sql.append(" AND ( ");
			sql.append(" (SUPPLY_FULL_NAME LIKE '%"+CommonUtil.trim(fDistributorFormBean.getSearchName())+"%')");
			sql.append(" or (SUPPLY_BUILT LIKE '%"+CommonUtil.trim(fDistributorFormBean.getSearchName())+"%')");
			sql.append(" or (REGISTRATION_NO LIKE '%"+CommonUtil.trim(fDistributorFormBean.getSearchName())+"%')");
			sql.append(" or (LINK_PHONE LIKE '%"+CommonUtil.trim(fDistributorFormBean.getSearchName())+"%')");
			sql.append(" or (ADDRESS LIKE '%"+CommonUtil.trim(fDistributorFormBean.getSearchName())+"%')");
			sql.append(" or (LEGAL_PERSON LIKE '%"+CommonUtil.trim(fDistributorFormBean.getSearchName())+"%')");
			sql.append(" or (FAX LIKE '%"+CommonUtil.trim(fDistributorFormBean.getSearchName())+"%')");
			sql.append(" ) ");
		}
		sql.append(" ORDER BY ID DESC");
		String sql_all=sql.toString();
		PageResults retValue =new PageResults();
		retValue =  this.findPageByFetchedSql(sql_all, null
    			,fDistributorFormBean.getPageBean().getOffset()
    			,fDistributorFormBean.getPageBean().getLimit()
    			,null);
		return retValue;
	}
	
	/**
	 * 根据IDS获取到List
	 */
	@SuppressWarnings("rawtypes")
	public PageResults findByIds(String ids){
		ArrayList parmValue=new ArrayList();
		StringBuilder sql=new StringBuilder();
		sql.append( " FROM Distributor WHERE 1=1 ");
		if (ids.length()>0){
			sql.append(" AND ID IN ("+ids+")");
		}
		//sql.append(" ORDER BY ID");
		String sql_all=sql.toString();
		PageResults retValue =new PageResults();
		retValue =this.findPageByFetchedHql(sql_all, null
    			,0
    			,100000000
    			,parmValue.toArray());
		return retValue;
	}
	
	/**
	 * 查询最大到供应商编号
	 */
	public String queryMaxDisCode(){
		String maxCode="";
		String sql="SELECT MAX(SUPPLIER_CODE) AS SUPPLIER_CODE FROM PUB_SUPPLIER WHERE SUPPLIER_CODE LIKE 'GYS%'";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		maxCode=(String) query.uniqueResult();
		return maxCode;
	}
	
}

package com.lyht.business.plan.dao;

import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.bean.MaterialDetail;

@Repository
@Scope("prototype")
public class MaterialDetailDao extends HibernateBaseDao<MaterialDetail>{

	/**
	 * 根据计划code删除物资信息
	 */
	public void deleteDetailByPlanCode(String planCode){
		String sql="DELETE FROM PLAN_DEMAND_DETAIL WHERE CONTRACT_NO = ?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0, planCode);
		query.executeUpdate();
	}
	
	/**
	 * 根据需求清单code查询物资明细
	 */
	@SuppressWarnings("rawtypes")
	public PageResults queryDetailByPlanCode(String planCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT DETAIL.ID AS ID,DETAIL.ENGINEER_CODE AS ENGINEERCODE,DETAIL.PLAN_TYPE AS PLANTYPE,");
		sql.append("DETAIL.CONTRACT_NO AS CONTRACTNO,DETAIL.MATERIEL_CODE AS MATERIELCODE,DETAIL.MATERIEL_NAME AS MATERIELNAME,");
		sql.append("DETAIL.MATERIEL_NUM AS MATERIELNUM,DETAIL.MATERIEL_UNIT AS MATERIELUNIT,DETAIL.REMARK AS REMARK,");
		sql.append("DETAIL.MATERIEL_DESC AS MATERIELDESC,DETAIL.MATERIEL_PRICE AS MATERIELPRICE,");
		sql.append("MATERIEL.MATERIAL_NORMS AS MATERIALNORMS ");
		sql.append("FROM PLAN_DEMAND_DETAIL DETAIL ");
		sql.append("LEFT JOIN PUB_MATERIEL MATERIEL ");
		sql.append("ON DETAIL.MATERIEL_CODE=MATERIEL.MATERIEL_CODE ");
		sql.append("WHERE DETAIL.PLAN_TYPE= '001' AND DETAIL.CONTRACT_NO='"+planCode+"'");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,100000000, null);
		return retValue;
	}
	
}

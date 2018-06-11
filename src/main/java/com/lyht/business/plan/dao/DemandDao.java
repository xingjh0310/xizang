package com.lyht.business.plan.dao;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.formBean.MaterielBaseFormBean;
import com.lyht.business.plan.bean.Demand;
import com.lyht.business.plan.formBean.DemandFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class DemandDao extends HibernateBaseDao<Demand>{
	
	/**
	 * 查看全部需求清单
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults queryAllDemandInfo(DemandFormBean fDemandFormBean){
		ArrayList parmValue=new ArrayList();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT DEMAND.ID AS ID,DEMAND.ENGINEER_CODE AS ENGINEERCODE,DEMAND.PLAN_YEAR AS PLANYEAR,");
		sql.append("DEMAND.PLAN_CODE AS PLANCODE,DEMAND.ESTIMATE_TOTAL_PRICE AS ESTIMATETOTALPRICE,");
		sql.append("DEMAND.CONSTRUCT_DEPT AS CONSTRUCTDEPT,DEMAND.PLAN_DATE AS PLANDATE,");
		sql.append("DEMAND.DEMAND_PLACE AS DEMANDPLACE,DEMAND.DEMAND_UNIT AS DEMANDUNIT,");
		sql.append("DEMAND.PROJECT_TYPE AS PROJECTTYPE,DEMAND.PROJECT_NAME AS PROJECTNAME,");
		sql.append("DEMAND.PROJECT_VOLTAGE_LEVE AS PROJECTVOLTAGELEVE,DEMAND.REMARK AS REMARK,");
		sql.append("DEMAND.MATERIAL_VOLTAGE_LEVE AS MATERIALVOLTAGELEVE,DEMAND.LINK_MAN AS LINKMAN,");
		sql.append("DEMAND.LINK_PHONE AS LINKPHONE,FILETABLE.FILENUB AS FILENUB,");
		sql.append("DETAIL.MATERIEL_NAME AS MATERIELNAME,DETAIL.MATERIEL_NUM AS MATERIELNUM,DETAIL.MATERIEL_UNIT AS MATERIELUNIT ");
		sql.append("FROM PLAN_DEMAND DEMAND ");
		sql.append("LEFT JOIN (SELECT TABLE_PK_COLUMN,COUNT(TABLE_PK_COLUMN) FILENUB FROM PUB_FILES WHERE TABLE_NAME = 'PLAN_DEMAND' GROUP BY TABLE_PK_COLUMN) FILETABLE ");
		sql.append("ON DEMAND.ID = FILETABLE.TABLE_PK_COLUMN ");
		sql.append("LEFT JOIN PLAN_DEMAND_DETAIL DETAIL ON DEMAND.PLAN_CODE=DETAIL.CONTRACT_NO ");
		sql.append("WHERE 1=1 ");
		if(fDemandFormBean != null){
			//字符串字段模糊匹配
			if (CommonUtil.trim(fDemandFormBean.getSearchName()).length()>0){
				sql.append(" AND ( ");
				sql.append(" DEMAND.PLAN_YEAR LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" OR DEMAND.PLAN_CODE LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" OR DEMAND.ESTIMATE_TOTAL_PRICE LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" OR DEMAND.PLAN_DATE LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" OR DEMAND.DEMAND_PLACE LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" OR DEMAND.DEMAND_UNIT LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" OR DEMAND.PROJECT_TYPE LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" OR DEMAND.PROJECT_NAME LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" OR DEMAND.PROJECT_VOLTAGE_LEVE LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" OR DEMAND.MATERIAL_VOLTAGE_LEVE LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" OR DEMAND.LINK_MAN LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" OR DEMAND.LINK_PHONE LIKE ?");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" ) ");
			}
		}
		//字段条件查询，根据需要自己增加
		if(fDemandFormBean.getmDemand()!=null){
			if ( null != fDemandFormBean.getmDemand().getId() && fDemandFormBean.getmDemand().getId()>0){      
				sql.append(" AND DEMAND.ID = ? ");
				parmValue.add(fDemandFormBean.getmDemand().getId());
            }else{
            	if(CommonUtil.trim(fDemandFormBean.getmDemand().getEngineerCode()).length()>0){
            		sql.append(" AND DEMAND.ENGINEER_CODE = ? ");
            		parmValue.add(fDemandFormBean.getmDemand().getEngineerCode());
            	}
            }
		}
		if(null != fDemandFormBean.getIds() && fDemandFormBean.getIds().length()>0){
			sql.append(" AND DEMAND.ID IN ( ");
			sql.append(fDemandFormBean.getIds());
			sql.append(" ) ");
		}
		sql.append(" ORDER BY DEMAND.ID "+fDemandFormBean.getPageBean().getSortOrder());
		String sql_all=sql.toString();
		PageResults retValue =new PageResults();
		retValue =  this.findPageByFetchedSql(sql_all, null
    			,fDemandFormBean.getPageBean().getOffset()
    			,fDemandFormBean.getPageBean().getLimit()
    			,parmValue.toArray());
		return retValue;
	}
	
	/**
	 * 查看全部需求清单--old
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults queryAllDemandInfo_old(DemandFormBean fDemandFormBean){
		ArrayList parmValue=new ArrayList();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT DETAIL.ID AS ID,DEMAND.PROJECT_NAME AS PROJECTNAME,DETAIL.MATERIEL_CODE AS MATERIELCODE,");
		sql.append("DETAIL.MATERIEL_NAME AS MATERIELNAME,DETAIL.MATERIEL_NUM AS MATERIELNUM,DEMAND.PROJECT_TYPE AS PROJECTTYPE,");
		sql.append("DETAIL.MATERIEL_UNIT AS MATERIELUNIT,DEMAND.DEMAND_UNIT AS DEMANDUNIT,");
		sql.append("DEMAND.PLAN_DATE AS PLANDATE,DEMAND.DEMAND_PLACE AS DEMANDPLACE,");
		sql.append("DEMAND.LINK_MAN AS LINKMAN,DEMAND.LINK_PHONE AS LINKPHONE,DEMAND.REMARK AS REMARK,");
		sql.append("DEMAND.PLAN_YEAR AS PLANYEAR,DEMAND.PLAN_CODE AS PLANCODE,DEMAND.CONSTRUCT_DEPT AS CONSTRUCTDEPT,");
		sql.append("DEMAND.ESTIMATE_TOTAL_PRICE AS ESTIMATETOTALPRICE,DEMAND.PROJECT_VOLTAGE_LEVE AS PROJECTVOLTAGELEVE,");
		sql.append("DEMAND.MATERIAL_VOLTAGE_LEVE AS MATERIALVOLTAGELEVE,DEMAND.ENGINEER_CODE AS ENGINEERCODE,");
		sql.append("DEMAND.ID AS DEMANDID,DETAIL.ENGINEER_CODE AS DETAILENGINEERCODE,DETAIL.PLAN_TYPE AS PLANTYPE,");
		sql.append("DETAIL.CONTRACT_NO AS CONTRACTNO,DETAIL.MATERIEL_DESC AS MATERIELDESC,DETAIL.MATERIEL_PRICE AS MATERIELPRICE,");
		sql.append("DETAIL.REMARK AS DETAILREMARK,MATERIEL.MATERIAL_NORMS AS MATERIALNORMS ");
		sql.append("FROM PLAN_DEMAND DEMAND ");
		sql.append("RIGHT JOIN PLAN_DEMAND_DETAIL DETAIL ");
		sql.append("ON DEMAND.PLAN_CODE=DETAIL.CONTRACT_NO ");
		sql.append("LEFT JOIN PUB_MATERIEL MATERIEL ");
		sql.append("ON DETAIL.MATERIEL_CODE= MATERIEL.MATERIEL_CODE ");
		sql.append("LEFT JOIN PUB_MATERIEL_TYPE TYPE ");
		sql.append("ON MATERIEL.MATERIAL_GROUP = TYPE.CODE ");
		sql.append("WHERE DETAIL.PLAN_TYPE = '001' ");
		if(fDemandFormBean != null){
			//字符串字段模糊匹配
			if (CommonUtil.trim(fDemandFormBean.getSearchName()).length()>0){
				sql.append(" AND ( ");
				sql.append(" (DEMAND.PROJECT_NAME LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" or (DETAIL.MATERIEL_CODE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" or (DETAIL.MATERIEL_NAME LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" or (DETAIL.MATERIEL_NUM LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" or (DETAIL.MATERIEL_UNIT LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" or (DEMAND.DEMAND_UNIT LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" or (DEMAND.PLAN_DATE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" or (DEMAND.DEMAND_PLACE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" or (DEMAND.LINK_MAN LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" or (DEMAND.LINK_PHONE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fDemandFormBean.getSearchName())+"%");
				sql.append(" ) ");
			}
			if(CommonUtil.trim(fDemandFormBean.getMaterielType()).length()>0){
				sql.append(" AND TYPE.CODE = ? ");
				parmValue.add(fDemandFormBean.getMaterielType());
			}
			if(CommonUtil.trim(fDemandFormBean.getMaterielBase()).length()>0){
				sql.append(" AND DETAIL.MATERIEL_CODE = ? ");
				parmValue.add(fDemandFormBean.getMaterielBase());
			}
		}
		//字段条件查询，根据需要自己增加
		if(fDemandFormBean.getmDemand()!=null){
			if ( null != fDemandFormBean.getmDemand().getId() && fDemandFormBean.getmDemand().getId()>0){      
				sql.append(" AND DETAIL.ID = ? ");
				parmValue.add(fDemandFormBean.getmDemand().getId());
            }else{
            	if(CommonUtil.trim(fDemandFormBean.getmDemand().getEngineerCode()).length()>0){
            		sql.append(" AND DEMAND.ENGINEER_CODE = ? ");
            		parmValue.add(fDemandFormBean.getmDemand().getEngineerCode());
            	}
            }
		}
		if(null != fDemandFormBean.getIds() && fDemandFormBean.getIds().length()>0){
			sql.append(" AND DETAIL.ID IN ( ");
			sql.append(fDemandFormBean.getIds());
			sql.append(" ) ");
		}
		sql.append(" ORDER BY DETAIL.ID "+fDemandFormBean.getPageBean().getSortOrder());
		String sql_all=sql.toString();
		PageResults retValue =new PageResults();
		retValue =  this.findPageByFetchedSql(sql_all, null
    			,fDemandFormBean.getPageBean().getOffset()
    			,fDemandFormBean.getPageBean().getLimit()
    			,parmValue.toArray());
		return retValue;
	}
	
	/**
	 * 根据IDS获取到List
	 */
	@SuppressWarnings({ "rawtypes" })
	public PageResults findByIds(String ids){
		ArrayList parmValue=new ArrayList();
		StringBuilder sql=new StringBuilder();
		sql.append( "FROM Demand WHERE 1=1 ");
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
	 * 查询计划编号是否重复
	 */
	public int queryPlayCode(String code){
		int nub=0;
		String sql="SELECT COUNT(PLAN_CODE) FROM PLAN_DEMAND WHERE PLAN_CODE = ?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0,code);
		nub=(int) query.uniqueResult();
		return nub;
	}
	
	/**
	 * 查询全部需求单位
	 */
	@SuppressWarnings("unchecked")
	public List<String> queryDemand(){
		String sql="SELECT DISTINCT(DEMAND_UNIT) FROM PLAN_DEMAND";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		List<String> demandUnit=query.list();
		return demandUnit;
	}
	
	/**
	 * 查询需求清单数量
	 */
	public int queryDemandPlanNub(DemandFormBean fDemandFormBean){
		int nub=0;
		String sql="SELECT COUNT(ID) FROM PLAN_DEMAND WHERE ENGINEER_CODE = ?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0,fDemandFormBean.getmDemand().getEngineerCode());
		nub=(int) query.uniqueResult();
		return nub;
	}
	
	/**
	 * 查询物资信息
	 */
	@SuppressWarnings({ "rawtypes" })
	public PageResults queryMaterielBase(MaterielBaseFormBean materielBaseFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT M.ID AS ID,M.MATERIEL_CODE AS MATERIELCODE,M.MATERIEL_NAME AS MATERIELNAME,");
		sql.append("M.CODE AS CODE,M.MATERIEL_DESC AS MATERIELDESC,M.MATERIAL_NORMS AS MATERIALNORMS,");
		sql.append("Q.NAME AS UNIT,M.MATERIAL_GROUP AS MATERIALGROUP,M.MATERIEL_CLASSIFY AS MATERIELCLASSIFY,");
		sql.append("M.UNIT_DESC AS UNITDESC,M.STATE AS STATE,M.REMARK AS REMARK,");
		sql.append("M.PRICE AS PRICE");
		sql.append(" FROM PUB_MATERIEL AS M ");
		sql.append(" LEFT JOIN (SELECT D.NAME,D.CODE FROM SYS_DICT AS D LEFT JOIN SYS_DICT_CATE AS C ON D.LISTNM_SYS_DICT_CATE = C.NM WHERE C.CODE = 'JLDW') AS Q ON M.UNIT = Q.CODE ");
		sql.append(" WHERE 1=1 ");
		if (materielBaseFormBean != null) {
			//物料code
			if(materielBaseFormBean.getMaterielBase().getMaterielCode()!=null&&!"".equals(materielBaseFormBean.getMaterielBase().getMaterielCode())){
				sql.append(" AND MATERIEL_CODE ='"+materielBaseFormBean.getMaterielBase().getMaterielCode()+"'");
			}
		}
		sql.append(" ORDER BY ID " + materielBaseFormBean.getPageBean().getSortOrder());
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, materielBaseFormBean.getPageBean().getOffset(),
				materielBaseFormBean.getPageBean().getLimit(), list.toArray());
		return retValue;
	}
	
	/***************WEB-APP分割线*************************/

	@SuppressWarnings({ "rawtypes", "unused" })
	public List<Map> listChartsNum(DemandFormBean fDemandFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  sum(d.materiel_num) as num,DATEPART(year,p.plan_date)as yea,DATEPART(MONTH,p.plan_date)as mon ");
		if(fDemandFormBean.getYear().length()>5){
			sql.append(" ,DATEPART(day,p.plan_date) AS da ");
		}
		if(fDemandFormBean.getMaterielType()!=null &&!"".equals(fDemandFormBean.getMaterielType())){
			sql.append(",d.materiel_name as name ");
		}
		sql.append(" from plan_demand as p LEFT JOIN plan_demand_detail as d on p.plan_code=d.contract_no  ");
		
		sql.append(" where p.plan_date like '"+fDemandFormBean.getYear()+"%' ");
		
		if(fDemandFormBean.getMaterielType()!=null &&!"".equals(fDemandFormBean.getMaterielType())){
			  
			sql.append(" and d.materiel_code='"+fDemandFormBean.getMaterielType()+"' "); 
		  }
		if(fDemandFormBean.getmDemand().getEngineerCode() != null && fDemandFormBean.getmDemand().getEngineerCode().length()>0){
			sql.append(" AND p.engineer_code = '"+fDemandFormBean.getmDemand().getEngineerCode()+"' ");
		}
		sql.append("GROUP BY DATEPART(year,p.plan_date),DATEPART(MONTH,p.plan_date) ");
		
		if(fDemandFormBean.getMaterielType()!=null &&!"".equals(fDemandFormBean.getMaterielType())){
			sql.append(",d.materiel_name ");
		}
		
		 if(fDemandFormBean.getYear().length()>5){
				sql.append(",DATEPART(day,p.plan_date)");
	  }
		List<Map>  assign= createSQLQuerybyMap(sql.toString());
		
		return assign;
	}
	//获取年份
	@SuppressWarnings("rawtypes")
	public List<Map> getAllYear() {
		
			String sql="select DATEPART(year,plan_date) as id,DATEPART(year,plan_date) as year from plan_demand  group by DATEPART(year,plan_date)";
			
			List<Map>  assign=createSQLQuerybyMap(sql);
			
		return assign;
	}	
	
	

}

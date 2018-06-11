package com.lyht.business.plan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.bean.Arrival;
import com.lyht.business.plan.formBean.ArrivalFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class ArrivalDao extends HibernateBaseDao<Arrival>{
	
	/**
	 * 查看全部到货计划
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults queryAllArrivalInfo(ArrivalFormBean fArrivalFormBean,SysDept dept){
		ArrayList parmValue=new ArrayList();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT SUPPLY.supply_plan_title AS supply_plan_title,ARRIVA.ID AS ID,ARRIVA.ENGINEER_CODE AS ENGINEERCODE,ARRIVA.SUPPLIER_PLAN_CODE AS SUPPLIERPLANCODE,ARRIVA.AUDIT_TIME AS AUDITTIME,");
		sql.append("ARRIVA.SUPPLIER_CODE AS SUPPLIERCODE,ARRIVA.CONTRACT_NO AS CONTRACTNO,ARRIVA.ARR_PLAN_NUM AS ARRPLANNUM,ARRIVA.AUDIT_PERSON AS AUDITPERSON,");
		sql.append("ARRIVA.PLAN_DELIVERY_BATCH AS PLANDELIVERYBATCH,ARRIVA.MATERIEL_CODE AS MATERIELCODE,ARRIVA.MATERIEL_DESC AS MATERIELDESC,");
		sql.append("ARRIVA.MEA_UNIT AS MEAUNIT,ARRIVA.CONTRACT_NUM AS CONTRACTNUM,ARRIVA.PLAN_DELIVERIE AS PLANDELIVERIE,ARRIVA.ACTUAL_DELIVERIE AS ACTUALDELIVERIE,");
		sql.append("ARRIVA.DEMAND_UNIT AS DEMANDUNIT,ARRIVA.TREENM_SYS_DEPT AS TREENMSYSDEPT,ARRIVA.CONTRACT_DELIVERY_DATE AS CONTRACTDELIVERYDATE,");
		sql.append("ARRIVA.CONFIRM_DELIVERY_DATE AS CONFIRMDELIVERYDATE,ARRIVA.DEPT_DELIVERY_DATE AS DEPTDELIVERYDATE,ARRIVA.SUPPLY_DELIVERY_DATE AS SUPPLYDELIVERYDATE,");
		sql.append("ARRIVA.APPLY_DATE AS APPLYDATE,ARRIVA.RECEIVE_CONTACT AS RECEIVECONTACT,ARRIVA.LINK_MODE AS LINKMODE,DEPT.NAME AS DEPTNAME,");
		sql.append("ARRIVA.DELIVERY_PLACE AS DELIVERYPLACE,ARRIVA.PLAN_STATE AS PLANSTATE,ARRIVA.REMARK AS REMARK,MATERIEL.MATERIAL_NORMS AS MATERIALNORMS,");
		sql.append("DETAIL.MATERIEL_NAME AS MATERIELNAME,FILETABLE.FILENUB AS FILENUB,DETAIL.ID AS DETAILID,ENGINEERING.ENGINEER_NAME AS ENGINEERNAME,");
		sql.append("SUPPLY.BIDDING_DATE AS BIDDINGDATE,SUPPLY.DRAWE_CONFIRM_TIME AS DRAWECONFIRMTIME,SUPPLY.SIGN_TIME AS SIGNTIME,ARRIVA.AUDIT_OPINION AS AUDITOPINION,");
		sql.append("SUPPLIER.SUPPLY_FULL_NAME AS SUPPLIERNAME,CONTINFO.CONTRACT_TITLE AS CONTRACTNAME,ARRIVA.AUDIT_STATE AS AUDITSTATE ");
		sql.append("FROM PLAN_TOCARGO ARRIVA ");
		sql.append("LEFT JOIN PLAN_DEMAND_DETAIL DETAIL ");
		sql.append("ON ARRIVA.ARR_PLAN_NUM = DETAIL.CONTRACT_NO ");
		sql.append("LEFT JOIN (SELECT TABLE_PK_COLUMN,COUNT(TABLE_PK_COLUMN) FILENUB FROM PUB_FILES WHERE TABLE_NAME = 'PLAN_TOCARGO' GROUP BY TABLE_PK_COLUMN) FILETABLE ");
		sql.append("ON ARRIVA.ID = FILETABLE.TABLE_PK_COLUMN ");
		sql.append("LEFT JOIN SUPPLY_PLAN SUPPLY ");
		sql.append("ON ARRIVA.SUPPLIER_PLAN_CODE=SUPPLY.SUPPLIER_PLAN_CODE ");
		sql.append("LEFT JOIN PUB_SUPPLIER SUPPLIER ");
		sql.append("ON ARRIVA.SUPPLIER_CODE = SUPPLIER.SUPPLIER_CODE ");
		sql.append("LEFT JOIN CONT_INFO CONTINFO ");
		sql.append("ON ARRIVA.CONTRACT_NO = CONTINFO.CONTRACT_NO ");
		sql.append("LEFT JOIN PUB_MATERIEL MATERIEL ");
		sql.append("ON ARRIVA.MATERIEL_CODE= MATERIEL.MATERIEL_CODE ");
		sql.append("LEFT JOIN PUB_MATERIEL_TYPE TYPE ");
		sql.append("ON MATERIEL.MATERIAL_GROUP = TYPE.CODE ");
		sql.append("LEFT JOIN PUB_ENGINEERING ENGINEERING ");
		sql.append("ON ARRIVA.ENGINEER_CODE = ENGINEERING.NM ");
		sql.append("LEFT JOIN SYS_DEPT DEPT ");
		sql.append("ON ARRIVA.TREENM_SYS_DEPT = DEPT.NM ");
		sql.append("WHERE 1=1 ");
		//项目单位 施工单位只需看到本单位相关的信息
		if(dept!=null){
			if(dept.getType().equals("construction_unit")){
				if(dept.getNm()!=null||!"".equals(dept.getNm())){
					
					sql.append(" AND SUPPLY.TREENM_SYS_DEPT ='"+dept.getNm()+"'");
				}
			}
		}
		
		if(fArrivalFormBean != null){
			//字符串字段模糊匹配
			if (CommonUtil.trim(fArrivalFormBean.getSearchName()).length()>0){
				sql.append(" AND ( ");
				sql.append(" (ARRIVA.MATERIEL_CODE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fArrivalFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.CONTRACT_NUM LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fArrivalFormBean.getSearchName())+"%");
				sql.append(" or (DETAIL.MATERIEL_NAME LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fArrivalFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.PLAN_DELIVERIE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fArrivalFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.ACTUAL_DELIVERIE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fArrivalFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.TREENM_SYS_DEPT LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fArrivalFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.CONTRACT_DELIVERY_DATE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fArrivalFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.CONFIRM_DELIVERY_DATE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fArrivalFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.RECEIVE_CONTACT LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fArrivalFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.LINK_MODE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fArrivalFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.DELIVERY_PLACE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fArrivalFormBean.getSearchName())+"%");
				sql.append(" ) ");
			}
			if(CommonUtil.trim(fArrivalFormBean.getMaterielType()).length()>0){
				sql.append(" AND TYPE.CODE = ? ");
				parmValue.add(fArrivalFormBean.getMaterielType());
			}
			if(CommonUtil.trim(fArrivalFormBean.getMaterielBase()).length()>0){
				sql.append(" AND DETAIL.MATERIEL_CODE = ? ");
				parmValue.add(fArrivalFormBean.getMaterielBase());
			}
			
		}
		//字段条件查询，根据需要自己增加
		if(fArrivalFormBean.getmArrival()!=null){
			if ( null != fArrivalFormBean.getmArrival().getId() && fArrivalFormBean.getmArrival().getId()>=0){      
				sql.append(" AND ARRIVA.ID = ? ");
				parmValue.add(fArrivalFormBean.getmArrival().getId());
            }else{
            	if(CommonUtil.trim(fArrivalFormBean.getmArrival().getEngineerCode()).length()>0){
            		sql.append(" AND ARRIVA.ENGINEER_CODE = ? ");
            		parmValue.add(fArrivalFormBean.getmArrival().getEngineerCode());
            	}
            }
			/*if(CommonUtil.trim(fArrivalFormBean.getmArrival().getPlanState()).length()>0){
				sql.append(" AND ARRIVA.PLAN_STATE = ? ");
				parmValue.add(fArrivalFormBean.getmArrival().getPlanState());
			}*/
			if(CommonUtil.trim(fArrivalFormBean.getAuditFlag()).length()>0){
				sql.append(" AND ARRIVA.AUDIT_STATE IN ( ");
				sql.append(fArrivalFormBean.getAuditFlag());
				sql.append(" ) ");
			}
			if(CommonUtil.trim(fArrivalFormBean.getmArrival().getAuditState()).length()>0){
				sql.append(" AND ARRIVA.AUDIT_STATE = ? ");
				parmValue.add(fArrivalFormBean.getmArrival().getAuditState());
			}
		}
		if(null != fArrivalFormBean.getIds() && fArrivalFormBean.getIds().length()>0){
			sql.append(" AND ARRIVA.ID IN ( ");
			sql.append(fArrivalFormBean.getIds());
			sql.append(" ) ");
		}
		sql.append(" ORDER BY ARRIVA.CONTRACT_DELIVERY_DATE "+fArrivalFormBean.getPageBean().getSortOrder());
		String sql_all=sql.toString();
		PageResults retValue =new PageResults();
		retValue =  this.findPageByFetchedSql(sql_all, null
    			,fArrivalFormBean.getPageBean().getOffset()
    			,fArrivalFormBean.getPageBean().getLimit()
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
		sql.append( "FROM Arrival WHERE 1=1 ");
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
	 * 查询最大到货计划编号
	 */
	public String queryMaxArrPlanNum(String arrPlanNum){
		String maxCode="";
		String sql="SELECT MAX(ARR_PLAN_NUM) AS ARR_PLAN_NUM FROM PLAN_TOCARGO WHERE ARR_PLAN_NUM LIKE ?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0,arrPlanNum+'%');
		maxCode=(String) query.uniqueResult();
		return maxCode;
	}
	
	/**
	 * 上报 批量上报
	 */
	public void reported(int id,int flag){
		String sql="UPDATE PLAN_TOCARGO SET PLAN_STATE = ? ,AUDIT_STATE='0'  WHERE ID = ?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0,flag);
		query.setParameter(1,id);
		query.executeUpdate();
	}
	
	/**
	 * 查询到货计划数量
	 */
	public int queryArrivalNub(ArrivalFormBean fArrivalFormBean){
		int nub=0;
		String sql="SELECT COUNT(ID) FROM PLAN_TOCARGO WHERE ENGINEER_CODE = ?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0,fArrivalFormBean.getmArrival().getEngineerCode());
		nub=(int) query.uniqueResult();
		return nub;
	}
	
	/**
	 * 到货计划审核
	 */
	public void saveAuditInfo(Arrival mArrival,int id){
		String sql="UPDATE PLAN_TOCARGO SET AUDIT_STATE=?,AUDIT_OPINION=?,AUDIT_PERSON=?,AUDIT_TIME=? WHERE ID=?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0,mArrival.getAuditState());
		query.setParameter(1,mArrival.getAuditOpinion());
		query.setParameter(2,mArrival.getAuditPerson());
		query.setParameter(3,mArrival.getAuditTime());
		query.setParameter(4,id);
		query.executeUpdate();
	}

	/***************WEB-APP分割线*************************/

	@SuppressWarnings({ "rawtypes", "unused" })
	public List<Map> listChartsNum(ArrivalFormBean fArrivalFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sum(t.actual_deliverie) as num, DATEPART(year,t.confirm_delivery_date)as yea,DATEPART(MONTH,t.confirm_delivery_date) as mon ");
		if(fArrivalFormBean.getYear().length()>5){
			sql.append(" ,DATEPART(day,t.confirm_delivery_date) AS da ");
		}
		if(fArrivalFormBean.getMaterielType()!=null &&!"".equals(fArrivalFormBean.getMaterielType())){
			sql.append(",d.materiel_name as name ");
		}
		sql.append(" from plan_tocargo as t LEFT JOIN plan_demand_detail as d on t.supplier_plan_code=d.contract_no");
		
		sql.append(" where t.confirm_delivery_date like '"+fArrivalFormBean.getYear()+"%' ");
		
		if(fArrivalFormBean.getMaterielType()!=null &&!"".equals(fArrivalFormBean.getMaterielType())){
			  
			sql.append(" and d.materiel_code='"+fArrivalFormBean.getMaterielType()+"' "); 
		  }
		if (CommonUtil.trim(fArrivalFormBean.getmArrival().getEngineerCode()).length()>0){
			sql.append(" AND T.ENGINEER_CODE = '" + CommonUtil.trim(fArrivalFormBean.getmArrival().getEngineerCode()) + "'");
		}
		sql.append("GROUP BY DATEPART(year,t.confirm_delivery_date),DATEPART(MONTH,t.confirm_delivery_date) ");
		
		if(fArrivalFormBean.getMaterielType()!=null &&!"".equals(fArrivalFormBean.getMaterielType())){
			sql.append(",d.materiel_name ");
		}
		
		 if(fArrivalFormBean.getYear().length()>5){
				sql.append(",DATEPART(day,t.confirm_delivery_date)");
	  }
		List<Map>  assign= createSQLQuerybyMap(sql.toString());
		
		return assign;
	}
}

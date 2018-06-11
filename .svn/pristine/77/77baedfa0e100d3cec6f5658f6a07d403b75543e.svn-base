package com.lyht.business.plan.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.bean.Supply;
import com.lyht.business.plan.formBean.SupplyFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class SupplyDao extends HibernateBaseDao<Supply>{
	
	/**
	 * 查看全部供货计划
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults queryAllSupplyInfo(SupplyFormBean fSupplyFormBean,SysDept dept){
		ArrayList parmValue=new ArrayList();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT SUPPLY.supply_plan_title AS supply_plan_title,SUPPLY.ID AS ID,SUPPLY.ENGINEER_CODE AS ENGINEERCODE,SUPPLY.SUPPLIER_CODE AS SUPPLIERCODE,SUPPLY.BIDDING_DATE AS BIDDINGDATE,");
		sql.append("SUPPLY.CONTRACT_NO AS CONTRACTNO,SUPPLY.SUPPLIER_PLAN_CODE AS SUPPLIERPLANCODE,SUPPLY.MATERIEL_CODE AS MATERIELCODE,SUPPLY.SUPPLY_PHONE AS SUPPLYPHONE,");
		sql.append("SUPPLY.MEA_UNIT AS MEAUNIT,SUPPLY.MATERIAL_DESC AS MATERIALDESC,SUPPLY.CONTRACT_NUM AS CONTRACTNUM,SUPPLY.PLAN_DELIVERIE AS PLANDELIVERIE,");
		sql.append("SUPPLY.ACTUAL_DELIVERIE AS ACTUALDELIVERIE,SUPPLY.CONTRACT_DELIVERY_DATE AS CONTRACTDELIVERYDATE,SUPPLY.CONFIRM_DELIVERY_DATE AS CONFIRMDELIVERYDATE,");
		sql.append("SUPPLY.DEPT_DELIVERY_DATE AS DEPTDELIVERYDATE,SUPPLY.SUPPLY_DELIVERY_DATE AS SUPPLYDELIVERYDATE,SUPPLY.TREENM_SYS_DEPT AS TREENMSYSDEPT,");
		sql.append("SUPPLY.LINK_MODE AS LINKMODE,SUPPLY.DELIVERY_CONTACT AS DELIVERYCONTACT,SUPPLY.DELIVERY_PLACE AS DELIVERYPLACE,SUPPLY.SUPPLY_CONTACT AS SUPPLYCONTACT,");
		sql.append("SUPPLY.APPLY_DATE AS APPLYDATE,SUPPLY.SUPPLY_PLAN_DESC AS SUPPLYPLANDESC,SUPPLY.PLAN_STATE AS PLANSTATE,DEPT.NAME AS DEPTNAME,");
		sql.append("SUPPLY.DRAWE_CONFIRM_TIME AS DRAWECONFIRMTIME,SUPPLY.SIGN_TIME AS SIGNTIME,SUPPLY.REMARK AS REMARK,DETAIL.ID AS DETAILID,");
		sql.append("DETAIL.MATERIEL_NAME AS MATERIELNAME,ENGINEERING.ENGINEER_NAME AS ENGINEERNAME,SUPPLIER.SUPPLY_FULL_NAME AS SUPPLYFULLNAME,MATERIEL.MATERIAL_NORMS AS MATERIALNORMS,");
		sql.append("FILETABLE.FILENUB AS FILENUB,CONTINFO.CONTRACT_TITLE AS CONTTITLE,CONTINFO.DEMAND_UNIT AS DEMANDUNIT,CONTINFO.SUPPLY_END_DATE AS SUPPLYENDDATE ");
		sql.append("FROM SUPPLY_PLAN  SUPPLY ");
		sql.append("LEFT JOIN PLAN_DEMAND_DETAIL DETAIL ");
		sql.append("ON SUPPLY.SUPPLIER_PLAN_CODE = DETAIL.CONTRACT_NO ");
		sql.append("LEFT JOIN PUB_ENGINEERING ENGINEERING ");
		sql.append("ON SUPPLY.ENGINEER_CODE = ENGINEERING.NM ");
		sql.append("LEFT JOIN PUB_SUPPLIER SUPPLIER ");
		sql.append("ON SUPPLY.SUPPLIER_CODE = SUPPLIER.SUPPLIER_CODE ");
		sql.append("LEFT JOIN (SELECT TABLE_PK_COLUMN,COUNT(TABLE_PK_COLUMN) FILENUB FROM PUB_FILES WHERE TABLE_NAME = 'SUPPLY_PLAN' GROUP BY TABLE_PK_COLUMN) FILETABLE ");
		sql.append("ON SUPPLY.ID = FILETABLE.TABLE_PK_COLUMN ");
		sql.append("LEFT JOIN CONT_INFO CONTINFO ");
		sql.append("ON SUPPLY.CONTRACT_NO = CONTINFO.CONTRACT_NO ");
		sql.append("LEFT JOIN PUB_MATERIEL MATERIEL ");
		sql.append("ON SUPPLY.MATERIEL_CODE= MATERIEL.MATERIEL_CODE ");
		sql.append("LEFT JOIN PUB_MATERIEL_TYPE TYPE ");
		sql.append("ON MATERIEL.MATERIAL_GROUP = TYPE.CODE ");
		sql.append("LEFT JOIN SYS_DEPT DEPT ");
		sql.append("ON SUPPLY.TREENM_SYS_DEPT = DEPT.NM ");
		sql.append(" WHERE 1=1 ");
		
		//权限项目单位--施工单位只需看到本单位相关的信息
		if(dept!=null){
			if(dept.getType().equals("construction_unit")){
				if(dept.getNm()!=null||!"".equals(dept.getNm())){
					
					sql.append(" AND SUPPLY.TREENM_SYS_DEPT ='"+dept.getNm()+"'");
				}
			}
			
		}
		
		if(fSupplyFormBean != null){
			//字符串字段模糊匹配
			if (CommonUtil.trim(fSupplyFormBean.getSearchName()).length()>0){
				sql.append(" AND ( ");
				sql.append(" (SUPPLY.MATERIEL_CODE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (DETAIL.MATERIEL_NAME LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLY.MEA_UNIT LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLY.CONTRACT_NO LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLY.CONTRACT_NUM LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (ENGINEERING.ENGINEER_NAME LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLY.TREENM_SYS_DEPT LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLIER.SUPPLY_FULL_NAME LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLY.APPLY_DATE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLY.CONTRACT_DELIVERY_DATE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLY.SUPPLIER_PLAN_CODE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLY.PLAN_DELIVERIE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLY.ACTUAL_DELIVERIE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLY.DELIVERY_CONTACT LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" or (SUPPLY.LINK_MODE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSupplyFormBean.getSearchName())+"%");
				sql.append(" ) ");
			}
			if(CommonUtil.trim(fSupplyFormBean.getMaterielType()).length()>0){
				sql.append(" AND TYPE.CODE = ? ");
				parmValue.add(fSupplyFormBean.getMaterielType());
			}
			if(CommonUtil.trim(fSupplyFormBean.getMaterielBase()).length()>0){
				sql.append(" AND DETAIL.MATERIEL_CODE = ? ");
				parmValue.add(fSupplyFormBean.getMaterielBase());
			}
			if(CommonUtil.trim(fSupplyFormBean.getmSupply().getPlanState()).length()>0){
				sql.append(" AND SUPPLY.PLAN_STATE = ? ");
				parmValue.add(fSupplyFormBean.getmSupply().getPlanState());
			}
		}
		//字段条件查询，根据需要自己增加
		if(fSupplyFormBean.getmSupply()!=null){
			if ( null != fSupplyFormBean.getmSupply().getId() && fSupplyFormBean.getmSupply().getId()>=0){      
				sql.append(" AND SUPPLY.ID = ? ");
				parmValue.add(fSupplyFormBean.getmSupply().getId());
            }else{
            	if(CommonUtil.trim(fSupplyFormBean.getmSupply().getEngineerCode()).length()>0){
            		sql.append(" AND SUPPLY.ENGINEER_CODE = ? ");
            		parmValue.add(fSupplyFormBean.getmSupply().getEngineerCode());
            	}
            }
			if(CommonUtil.trim(fSupplyFormBean.getmSupply().getPlanState()).length()>0){
				sql.append(" AND SUPPLY.PLAN_STATE = ? ");
				parmValue.add(fSupplyFormBean.getmSupply().getPlanState());
			}
			if(CommonUtil.trim(fSupplyFormBean.getmSupply().getSupplierPlanCode()).length()>0){
				sql.append(" AND SUPPLY.SUPPLIER_PLAN_CODE = ? ");
				parmValue.add(fSupplyFormBean.getmSupply().getSupplierPlanCode());
			}
			if(CommonUtil.trim(fSupplyFormBean.getmSupply().getDeliveryState()).length()>0){
				sql.append(" AND SUPPLY.DELIVERY_STATE = ? ");
				parmValue.add(fSupplyFormBean.getmSupply().getDeliveryState());
			}
		}
		if(null != fSupplyFormBean.getIds() && fSupplyFormBean.getIds().length()>0){
			sql.append(" AND SUPPLY.ID IN ( ");
			sql.append(fSupplyFormBean.getIds());
			sql.append(" ) ");
		}
		sql.append(" ORDER BY SUPPLY.ID "+fSupplyFormBean.getPageBean().getSortOrder());
		String sql_all=sql.toString();
		PageResults retValue =new PageResults();
		retValue =  this.findPageByFetchedSql(sql_all, null
    			,fSupplyFormBean.getPageBean().getOffset()
    			,fSupplyFormBean.getPageBean().getLimit()
    			,parmValue.toArray());
		return retValue;
	}
	
	/**
	 * 查询最大供应计划编号
	 */
	public String queryMaxSupplyCode(String supplyPlanCode){
		String maxCode="";
		String sql="SELECT MAX(SUPPLIER_PLAN_CODE) AS SUPPLIER_PLAN_CODE FROM SUPPLY_PLAN WHERE SUPPLIER_PLAN_CODE LIKE ?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0,supplyPlanCode+'%');
		maxCode=(String) query.uniqueResult();
		return maxCode;
	}
	
	/**
	 * 根据IDS获取到List
	 */
	@SuppressWarnings({ "rawtypes" })
	public PageResults findByIds(String ids){
		ArrayList parmValue=new ArrayList();
		StringBuilder sql=new StringBuilder();
		sql.append( "FROM Supply WHERE 1=1 ");
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
	 * 更新中标日期
	 */
	public void updateBiddingDate(Supply mSupply){
		String sql="UPDATE SUPPLY_PLAN SET BIDDING_DATE= ? WHERE ID = ?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0,mSupply.getBiddingDate());
		query.setParameter(1,mSupply.getId());
		query.executeUpdate();
	}
	
	/**
	 * 更新图纸、协议日期
	 */
	public void updateTime(Supply mSupply){
		String sql="UPDATE SUPPLY_PLAN SET DRAWE_CONFIRM_TIME= ?,SIGN_TIME = ? WHERE SUPPLIER_PLAN_CODE = ?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0,mSupply.getDraweConfirmTime());
		query.setParameter(1,mSupply.getSignTime());
		query.setParameter(2,mSupply.getSupplierPlanCode());
		query.executeUpdate();
	}
	
	/**
	 * 查询供货计划数量
	 */
	public int querySupplyNub(SupplyFormBean fSupplyFormBean){
		int nub=0;
		String sql="SELECT COUNT(ID) FROM SUPPLY_PLAN WHERE ENGINEER_CODE = ?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0,fSupplyFormBean.getmSupply().getEngineerCode());
		nub=(int) query.uniqueResult();
		return nub;
	}
	
	/**
	 * 查询供货计划信息--APP
	 */
	@SuppressWarnings("unchecked")
	public List<Supply> querySupplyInfo_App(){
		String hql="FROM Supply";
		Query query=this.getSession().createQuery(hql);
		List<Supply> supplyList=query.list();
		return supplyList;
	}

}

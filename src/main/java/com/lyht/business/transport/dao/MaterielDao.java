package com.lyht.business.transport.dao;

import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.transport.bean.Delivery;
import com.lyht.business.transport.formBean.MaterielFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class MaterielDao extends HibernateBaseDao<Delivery> {

	// 查询物资状态基础列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults list(MaterielFormBean materielFormBean,SysDept dept) {
		ArrayList parmValue=new ArrayList();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT SUPPLY.SUPPLY_PLAN_TITLE AS SUPPLYPLANTITLE, ARRIVA.ID AS ID,ARRIVA.ENGINEER_CODE AS ENGINEERCODE,ARRIVA.SUPPLIER_PLAN_CODE AS SUPPLIERPLANCODE,ARRIVA.AUDIT_TIME AS AUDITTIME,");
		sql.append("ARRIVA.SUPPLIER_CODE AS SUPPLIERCODE,ARRIVA.CONTRACT_NO AS CONTRACTNO,ARRIVA.ARR_PLAN_NUM AS ARRPLANNUM,ARRIVA.AUDIT_PERSON AS AUDITPERSON,");
		sql.append("ARRIVA.PLAN_DELIVERY_BATCH AS PLANDELIVERYBATCH,ARRIVA.MATERIEL_CODE AS MATERIELCODE,ARRIVA.MATERIEL_DESC AS MATERIELDESC,");
		sql.append("ARRIVA.MEA_UNIT AS MEAUNIT,ARRIVA.CONTRACT_NUM AS CONTRACTNUM,ARRIVA.PLAN_DELIVERIE AS PLANDELIVERIE,ARRIVA.ACTUAL_DELIVERIE AS ACTUALDELIVERIE,");
		sql.append("ARRIVA.DEMAND_UNIT AS DEMANDUNIT,ARRIVA.TREENM_SYS_DEPT AS TREENMSYSDEPT,ARRIVA.CONTRACT_DELIVERY_DATE AS CONTRACTDELIVERYDATE,");
		sql.append("ARRIVA.CONFIRM_DELIVERY_DATE AS CONFIRMDELIVERYDATE,ARRIVA.DEPT_DELIVERY_DATE AS DEPTDELIVERYDATE,ARRIVA.SUPPLY_DELIVERY_DATE AS SUPPLYDELIVERYDATE,");
		sql.append("ARRIVA.APPLY_DATE AS APPLYDATE,ARRIVA.RECEIVE_CONTACT AS RECEIVECONTACT,ARRIVA.LINK_MODE AS LINKMODE,DEPT.NAME AS DEPTNAME,");
		sql.append("ARRIVA.DELIVERY_PLACE AS DELIVERYPLACE,ARRIVA.PLAN_STATE AS PLANSTATE,ARRIVA.REMARK AS REMARK,MATERIEL.MATERIAL_NORMS AS MATERIALNORMS,");
		sql.append("DETAIL.MATERIEL_NAME AS MATERIELNAME,FILETABLE.FILENUB AS FILENUB,DETAIL.ID AS DETAILID,ENGINEERING.ENGINEER_NAME AS ENGINEERNAME,");
		sql.append("SUPPLY.BIDDING_DATE AS BIDDINGDATE,SUPPLY.DRAWE_CONFIRM_TIME AS DRAWECONFIRMTIME,SUPPLY.SIGN_TIME AS SIGNTIME,ARRIVA.AUDIT_OPINION AS AUDITOPINION,");
		sql.append("SUPPLIER.SUPPLY_FULL_NAME AS SUPPLIERNAME,CONTINFO.CONTRACT_TITLE AS CONTRACTNAME,ARRIVA.AUDIT_STATE AS AUDITSTATE,");
		sql.append("MATE.RECEIVE_STATE AS RECEIVESTATE,MATE.RECEIVE_PARTY AS  RECEIVEPARTY,MATE.RECEIVE_CONFIRM_TIME AS RECEIVECONFIRMTIME,");
		sql.append("MATE.ARRIVAL_NUMBER AS ARRIVALNUMBER,MATE.ARRIVAL_ADDRESS AS ARRIVALADDRESS,");
		sql.append("ACCE.ACCEPTANCE_STATE AS ACCEPTANCESTATE,ACCE.ACCEPTANCE AS ACCEPTANCE,ACCE.CONFIRM_TIME AS CONFIRMTIME,");
		sql.append("ACCE.PROJECT_SENDEE AS PROJECTSENDEE,ACCE.PROJECT_CONFIRM_TIME AS PROJECTCONFIRMTIME,ACCE.SUPPLIER_DELIVERER AS SUPPLIERDELIVERER,");
		sql.append("ACCE.SUPPLIER_CONFIRM_TIME AS SUPPLIERCONFIRMTIME,ACCE.SUPERVISOR_UNIT AS SUPERVISORUNIT,ACCE.SUPERVISOR_CONFIRM_TIME AS SUPERVISORCONFIRMTIME,");
		sql.append("ACCE.CONSTRUCTION_UNIT AS CONSTRUCTIONUNIT,ACCE.CONSTRUCTION_CONFIRM_TIME AS CONSTRUCTIONCONFIRMTIME,ACCE.REMARK AS ACCEREMARK,");
		sql.append("ACCE.COMPANY_STATE AS COMPANYSTATE,ACCE.PROJECT_STATE AS PROJECTSTATE,ACCE.SUPPLIER_STATE AS SUPPLIERSTATE,");
		sql.append("ACCE.SUPERVISOR_STATE AS SUPERVISORSTATE,ACCE.CONSTRUCTION_STATE AS CONSTRUCTIONSTATE,ACCE.COMPANY_STAFF AS COMPANYSTAFF,");
		sql.append("ACCE.PROJECT_STAFF AS PROJECTSTAFF,ACCE.SUPPLIER_STAFF AS SUPPLIERSTAFF,ACCE.SUPERVISOR_STAFF AS SUPERVISORSTAFF,");
		sql.append("ACCE.CONSTRUCTION_STAFF AS CONSTRUCTIONSTAFF,ACCE.MATERIAL_COMPANY AS MATERIALCOMPANY,ACCE.ACCEPTANCE_TIME AS ACCEPTANCETIME ");
		sql.append("FROM PLAN_TOCARGO ARRIVA ");
		sql.append("LEFT JOIN PLAN_DEMAND_DETAIL DETAIL ON ARRIVA.ARR_PLAN_NUM = DETAIL.CONTRACT_NO ");
		sql.append("LEFT JOIN (SELECT TABLE_PK_COLUMN,COUNT(TABLE_PK_COLUMN) FILENUB FROM PUB_FILES WHERE TABLE_NAME = 'PLAN_TOCARGO' GROUP BY TABLE_PK_COLUMN) FILETABLE ON ARRIVA.ID = FILETABLE.TABLE_PK_COLUMN ");
		sql.append("LEFT JOIN SUPPLY_PLAN SUPPLY ON ARRIVA.SUPPLIER_PLAN_CODE=SUPPLY.SUPPLIER_PLAN_CODE ");
		sql.append("LEFT JOIN PUB_SUPPLIER SUPPLIER ON ARRIVA.SUPPLIER_CODE = SUPPLIER.SUPPLIER_CODE ");
		sql.append("LEFT JOIN CONT_INFO CONTINFO ON ARRIVA.CONTRACT_NO = CONTINFO.CONTRACT_NO ");
		sql.append("LEFT JOIN PUB_MATERIEL MATERIEL ON ARRIVA.MATERIEL_CODE= MATERIEL.MATERIEL_CODE ");
		sql.append("LEFT JOIN PUB_MATERIEL_TYPE TYPE ON MATERIEL.MATERIAL_GROUP = TYPE.CODE ");
		sql.append("LEFT JOIN PUB_ENGINEERING ENGINEERING ON ARRIVA.ENGINEER_CODE = ENGINEERING.NM ");
		sql.append("LEFT JOIN SYS_DEPT DEPT ON ARRIVA.TREENM_SYS_DEPT = DEPT.NM ");
		sql.append("LEFT JOIN MATE_RECEIPT MATE ON ARRIVA.ARR_PLAN_NUM =MATE.HANDOVER ");
		sql.append("LEFT JOIN MATE_ACCEPTANCE ACCE ON ARRIVA.ARR_PLAN_NUM =ACCE.ACCEPTANCE ");
		sql.append("WHERE ARRIVA.ENGINEER_CODE ='"+materielFormBean.getDeliveryInfoBean().getEngineerCode()+"' AND ARRIVA.PLAN_STATE='1' AND AUDIT_STATE='1' ");
		//判断当前登录人部门，施工单位只能看到自己单位的
		if(dept!=null){
				if(CommonUtil.trim( dept.getType()).equals("construction_unit")){
				if(dept.getNm()!=null||!"".equals(dept.getNm())){
					sql.append(" AND ARRIVA.TREENM_SYS_DEPT ='"+dept.getNm()+"'");
				}
			}
		}
		
		if(materielFormBean != null){
			//字符串字段模糊匹配
			if (CommonUtil.trim(materielFormBean.getSearchName()).length()>0){
				sql.append(" AND ( ");
				sql.append(" (ARRIVA.MATERIEL_CODE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(materielFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.CONTRACT_NUM LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(materielFormBean.getSearchName())+"%");
				sql.append(" or (DETAIL.MATERIEL_NAME LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(materielFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.PLAN_DELIVERIE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(materielFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.ACTUAL_DELIVERIE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(materielFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.TREENM_SYS_DEPT LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(materielFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.CONTRACT_DELIVERY_DATE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(materielFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.CONFIRM_DELIVERY_DATE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(materielFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.RECEIVE_CONTACT LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(materielFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.LINK_MODE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(materielFormBean.getSearchName())+"%");
				sql.append(" or (ARRIVA.DELIVERY_PLACE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(materielFormBean.getSearchName())+"%");
				sql.append(" ) ");
			}
		}
		//判断到货还是验收
		if(CommonUtil.trim(materielFormBean.getState()).length()>0){
			//到货的列表
			if(materielFormBean.getState()==1){
				
				sql.append(" AND MATE.RECEIVE_STATE is null ");
			}
			//验收的列表
			if(materielFormBean.getState()==2){
				sql.append(" AND MATE.RECEIVE_STATE ='1' ");
			}
			
		}
		sql.append(" ORDER BY ARRIVA.CONTRACT_DELIVERY_DATE "+materielFormBean.getPageBean().getSortOrder());
		String sql_all=sql.toString();
		PageResults retValue =new PageResults();
		retValue =  this.findPageByFetchedSql(sql_all, null,materielFormBean.getPageBean().getOffset(),materielFormBean.getPageBean().getLimit(),parmValue.toArray());
		return retValue;
	}
	//统计数量
	public int getMessageNub(MaterielFormBean materielFormBean) {
		
		int nub=0;
		StringBuilder sql  = new StringBuilder();
		sql.append("SELECT COUNT(I.ID) as NUB FROM MATE_INVOICE AS I ");
		sql.append("LEFT JOIN MATE_RECEIPT AS R ON I.HANDOVER = R.HANDOVER ");
		sql.append("LEFT JOIN MATE_ACCEPTANCE AS A ON I.ACCEPTANCE = A.ACCEPTANCE ");
		sql.append("WHERE I.ENGINEER_CODE='"+materielFormBean.getDeliveryInfoBean().getEngineerCode()+"' ");
		sql.append(" AND ( R.RECEIVE_STATE ='1' or R.RECEIVE_STATE is null )");
		
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		nub=(int) query.uniqueResult();
		return nub;
		
	}


}

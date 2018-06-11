package com.lyht.business.refund.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.refund.bean.Refund;
import com.lyht.business.refund.formBean.RefundFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class RefundDao extends HibernateBaseDao<Refund> {
	
	//退库退货列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults list(RefundFormBean refundFormBean,SysDept dept) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT b.ID AS ID, b.BILL_CODE AS BILLCODE, b.BILL_TITLE AS BILLTITLE,");
		sql.append("b.ENGINEER_CODE AS ENGINEERCODE, b.LIBRARY_NUM AS LIBRARYNUM, p.NAME AS SUPPLIERCODE,");
		sql.append("b.SINGLE_STAFF AS SINGLESTAFF, b.SINGLE_DATE AS SINGLEDATE, b.STATE AS STATE,B.SUPPLIER_CODE AS SUPPLIERID,");
		sql.append("b.AUDIT_STAFF AS AUDITSTAFF,b.AUDIT_DATE AS AUDITDATE,b.EXPLAIN AS EXPLAIN,");
		sql.append("b.UP_STATE AS UPSTATE, b.REMARK AS REMARK,e.ENGINEER_NAME AS ENGINEERNAME, f.NUB AS NUB FROM billInfo AS b ");
		sql.append("LEFT JOIN SYS_DEPT AS p ON b.supplier_code = p.nm ");
		sql.append("LEFT JOIN ( SELECT COUNT (TABLE_PK_COLUMN) NUB, TABLE_PK_COLUMN FROM PUB_FILES WHERE TABLE_NAME = 'BILLINFO' GROUP BY TABLE_PK_COLUMN) f ON b.ID = f.TABLE_PK_COLUMN ");
		sql.append("LEFT JOIN PUB_ENGINEERING AS e ON b.ENGINEER_CODE=e.NM ");
		sql.append("WHERE b.ENGINEER_CODE ='"+refundFormBean.getRefund().getEngineerCode()+"'");
		//判断当前登录人部门  施工单位上报、查看本单位退料退库信息
		if(dept!=null){
			if(CommonUtil.trim( dept.getType()).equals("construction_unit")){
				if(dept.getNm()!=null||!"".equals(dept.getNm())){
					sql.append(" AND b.SUPPLIER_CODE='"+dept.getNm()+"'");
				}
			}
			
		}
		//上报后的待审核的
		if(refundFormBean.getRefund().getUpState()!=null&&!"".equals(refundFormBean.getRefund().getUpState())){
				sql.append(" AND b.up_state='"+refundFormBean.getRefund().getUpState()+"'");
		}
		//根据状态查询
		if(CommonUtil.trim(refundFormBean.getState()).length()>0){
				//未审核的
				if(refundFormBean.getState()>0){
						sql.append(" AND b.state='0' ");
				}else{//审核的
						sql.append(" AND b.state!='0' ");
				}
		}
		
		if (refundFormBean != null) {
			// 字符串字段模糊匹配，
			if (CommonUtil.trim(refundFormBean.getSearchName()).length() > 0) {

				sql.append("AND BILL_CODE LIKE ? ");
				list.add("%" + CommonUtil.trim(refundFormBean.getSearchName()) + "%");

				sql.append("OR BILL_TITLE LIKE ? ");
				list.add("%" + CommonUtil.trim(refundFormBean.getSearchName()) + "%");

				sql.append("OR SINGLE_STAFF LIKE ? ");
				list.add("%" + CommonUtil.trim(refundFormBean.getSearchName()) + "%");
			}
			//精准查询供应商
			if(refundFormBean.getRefund().getSupplierCode()!=null &&!"".equals(refundFormBean.getRefund().getSupplierCode())){
				sql.append(" AND b.SUPPLIER_CODE ='"+refundFormBean.getRefund().getSupplierCode()+"' ");
			}
			
		}
		sql.append(" ORDER BY ID DESC" );
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, refundFormBean.getPageBean().getOffset(),
				refundFormBean.getPageBean().getLimit(), list.toArray());
		return retValue;
	}
	@SuppressWarnings("rawtypes")
	public PageResults getNumber(String num) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT BILL_CODE AS BILLCODE FROM BILLINFO WHERE 1=1 ");
		sql.append(" AND BILL_CODE LIKE '" + num + "%'");
		sql.append(" ORDER BY BILL_CODE DESC");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,10, null);
		return retValue;
		
	}
@SuppressWarnings({ "rawtypes", "unchecked" })
public PageResults getMaterial(String billCode) {
	ArrayList list = new ArrayList();
	StringBuilder sql = new StringBuilder();
	sql.append("SELECT b.ID AS ID,b.BILL_CODE AS BILLCODE,b.REFUND_APPLY AS REFUNDAPPLY,");
	sql.append("b.PROJECT_CODE AS PROJECTCODE,b.PROJECT_NAME AS PROJECTNAME,b.LIBRARY_NUM AS LIBRARYNUM,");
	sql.append("b.SUPPLIER_CODE AS SUPPLIERCODE,b.MATERIAL_CODE AS MATERIALCODE,b.MATERIAL_DESC AS MATERIALDESC,");
	sql.append("b.MEA_UNIT AS MEAUNIT,b.USE_NUM AS USENUM,b.RETURN_NUM AS RETURNNUM,");
	sql.append("b.IDENTY_SITUATION AS IDENTYSITUATION,b.INFOR_SITUATION AS INFORSITUATION,b.USE_DIRECTION AS USEDIRECTION,");
	sql.append("b.USE_TIME AS USETIME,b.SINGLE_STAFF AS SINGLESTAFF,b.RETURN_AUDITOR AS RETURNAUDITOR,");
	sql.append("b.CURRT_DATE AS CURRTDATE,b.SURPLUS_EXPLAIN AS SURPLUSEXPLAIN,b.REMARK AS REMARK,");
	sql.append("m.MATERIEL_NAME AS NAME ");
	sql.append("FROM BILL_DETIAL AS b LEFT JOIN pub_materiel AS m ON b.material_code= m.materiel_code WHERE 1=1 AND BILL_CODE=? ");
	list.add(billCode);
	sql.append(" ORDER BY ID ASC");
	PageResults retValue = new PageResults();
	retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1000000, list.toArray());
	return retValue;
}
@SuppressWarnings({ "rawtypes", "unused" })
	public List<Map> listChartsNum(RefundFormBean refundFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sum(d.return_num) as num,DATEPART(year,b.single_date)as yea,DATEPART(MONTH,b.single_date)as mon ");
		if(refundFormBean.getYear().length()>5){
			sql.append(" ,DATEPART(day,p.b.single_date) AS da ");
		}
		if(refundFormBean.getMaterielType()!=null &&!"".equals(refundFormBean.getMaterielType())){
			sql.append(",p.materiel_name as name ");
		}
		sql.append(" FROM billInfo as b LEFT JOIN bill_detial as d ON b.bill_code=d.bill_code ");
		sql.append(" LEFT JOIN pub_materiel  as p on d.material_code=p.materiel_code ");
		
		sql.append(" WHERE b.single_date like '"+refundFormBean.getYear()+"%' ");
		
		if(refundFormBean.getMaterielType()!=null &&!"".equals(refundFormBean.getMaterielType())){
			  
			sql.append(" AND p.materiel_code='"+refundFormBean.getMaterielType()+"' "); 
		  }
		if(refundFormBean.getRefund().getEngineerCode() != null && refundFormBean.getRefund().getEngineerCode().length()>0){
			sql.append(" AND b.engineer_code = '"+refundFormBean.getRefund().getEngineerCode() +"' ");
		}
		sql.append("GROUP BY DATEPART(YEAR, b.single_date),DATEPART(MONTH, b.single_date) ");
		
		if(refundFormBean.getMaterielType()!=null &&!"".equals(refundFormBean.getMaterielType())){
			sql.append(",p.materiel_name ");
		}
		
		 if(refundFormBean.getYear().length()>5){
				sql.append(",DATEPART(day,b.single_date)");
	  }
		List<Map>  assign= createSQLQuerybyMap(sql.toString());
		
		return assign;
	}
	@SuppressWarnings("rawtypes")
	public List<Map> getAllYear() {
	
		String sql="select DATEPART(year,plan_date) as id,DATEPART(year,plan_date) as year from plan_demand  group by DATEPART(year,plan_date)";
	
		List<Map>  assign=createSQLQuerybyMap(sql);
	
	return assign;
}




/********************************APP-WEB分割线**********************************/
 
	


	

}

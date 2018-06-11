package com.lyht.business.transport.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.formBean.SupplyFormBean;
import com.lyht.business.transport.bean.Check;
import com.lyht.business.transport.formBean.MaterielFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class CheckDao extends HibernateBaseDao<Check> {

	// 查询物资发货基础列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults list(MaterielFormBean materielFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID AS ID,ENGINEER_CODE AS ENGINEERCODE,HANDOVER AS HANDOVER,PURCHASE_NUMBER AS PURCHASENUMBER,");
		sql.append("CONTRACT_NO AS CONTRACTNO,SUPPLIER AS SUPPLIER,TREENM_SYS_DEPT AS TREENMSYSDEPT,");
		sql.append("SUPPLIER_CONTACT AS SUPPLIERCONTACT,SUPPLIER_LINK_PHONE AS SUPPLIERLINKPHONE,CARRIER AS CARRIER,");
		sql.append("CARRIER_PHONE AS CARRIERPHONE,PACKAGE_NUM AS PACKAGENUM,SPEC AS SPEC,UNIT AS UNIT,");
		sql.append("CONSIGNEE AS CONSIGNEE,CONSIGNEE_PHONE AS CONSIGNEEPHONE,DELIVERY_PLACE AS DELIVERYPLACE,");
		sql.append("DELIVERY_TIME AS DELIVERYTIME,REMARK AS REMARK,DELIVERY_STATE AS DELIVERYSTATE");
		sql.append(" FROM MATE_INVOICE WHERE 1 = 1 ");
		if (materielFormBean != null) {
			// 字符串字段模糊匹配，
			if (CommonUtil.trim(materielFormBean.getSearchName()).length() > 0) {
				
				sql.append("AND ENGINEER_CODE LIKE ? ");
				list.add("%" + CommonUtil.trim(materielFormBean.getSearchName()) + "%");
				
				sql.append("OR PURCHASE_NUMBER LIKE ? ");
				list.add("%" + CommonUtil.trim(materielFormBean.getSearchName()) + "%");
				
				sql.append("OR HANDOVER LIKE ? ");
				list.add("%" + CommonUtil.trim(materielFormBean.getSearchName()) + "%");
				
				sql.append("OR CONTRACT_NO LIKE ? ");
				list.add("%" + CommonUtil.trim(materielFormBean.getSearchName()) + "%");
				
				sql.append("OR PACKAGE_NUM LIKE ? ");
				list.add("%" + CommonUtil.trim(materielFormBean.getSearchName()) + "%");
			}

		}
		sql.append("ORDER BY ID " + materielFormBean.getPageBean().getSortOrder());
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, materielFormBean.getPageBean().getOffset(),
				materielFormBean.getPageBean().getLimit(), list.toArray());
		return retValue;
	}
	//修改供应计划发货状体
	public void updateState(SupplyFormBean fSupplyFormBean) {
		String sql ="update Supply SET DELIVERY_STATE=1 WHERE ID='"+fSupplyFormBean.getmSupply().getId()+"'";
		Query q = this.getSession().createQuery(sql);  
		q.executeUpdate();
		
	}
	//获取编号
	@SuppressWarnings("rawtypes")
	public PageResults getNumber(String num) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ACCEPTANCE AS ACCEPTANCE FROM MATE_ACCEPTANCE WHERE 1=1 ");
		sql.append(" AND ACCEPTANCE LIKE '" + num + "%'");
		sql.append(" ORDER BY ACCEPTANCE DESC");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,100, null);
		return retValue;
		
	}
	
	//添加到货单号
	public void updateY(String ids, String handover) {
		String sql = "update  Delivery SET ACCEPTANCE='"+handover+"' WHERE ID ='"+ids+"' ";
		Query q = this.getSession().createQuery(sql);  
		q.executeUpdate();
	}

}

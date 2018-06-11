package com.lyht.business.refund.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.refund.bean.RefundDetailed;
import com.lyht.business.refund.formBean.RefundFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class RefundDetailedDao extends HibernateBaseDao<RefundDetailed> {
	
	//退库退货列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults list(RefundFormBean refundFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID AS ID,BILL_CODE AS BILLCODE,BILL_TITLE AS BILLTITLE,");
		sql.append("ENGINEER_CODE AS ENGINEERCODE,LIBRARY_NUM AS LIBRARYNUM, SUPPLIER_CODE AS SUPPLIERCODE,");
		sql.append("SINGLE_STAFF AS SINGLESTAFF,SINGLE_DATE AS SINGLEDATE, STATE AS STATE,");
		sql.append("UP_STATE AS UPSTATE,REMARK AS REMARK FROM BILLINFO ");
		sql.append(" WHERE  ENGINEER_CODE ='"+refundFormBean.getRefund().getEngineerCode()+"'");
		
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
				sql.append(" AND SUPPLIER_CODE ='"+refundFormBean.getRefund().getSupplierCode()+"' ");
			}
			
		}
		sql.append(" ORDER BY ID " + refundFormBean.getPageBean().getSortOrder());
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
	//删除物资信息
	public void delMaterial(String billCode) {
		String sql ="DELETE FROM RefundDetailed where bill_code='"+billCode+"'";
		Query q = this.getSession().createQuery(sql);  
		q.executeUpdate();
	
	}
	
	
/*************************************************************************
 ******************************* APP***************************************
 *************************************************************************
 *************************************************************************/
	/*//app通讯录
	@SuppressWarnings("rawtypes")
	public PageResults app_list(MailFormBean refundFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID AS ID ,NM AS NM,ENGINEER_CODE AS ENGINEER_CODE,DEPT_NAME AS NAME,CODE AS CODE");
		sql.append(" FROM BOOK_DEPT WHERE ENGINEER_CODE ='"+refundFormBean.getMail().getEngineerCode()+"' ");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1000000, list.toArray());
		return retValue;
	}
	//app查询人员
	@SuppressWarnings("rawtypes")
	public PageResults getStaffInfoBydeptCode(String deptCode,MailFormBean refundFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT b.id AS id,b.staff_name AS name,q.name AS pot,b.position AS potCode,");
		sql.append("b.sex AS sex,b.link_phone AS phone,b.Fix_tele AS fixTele,b.nm as nm,");
		sql.append("b.address AS address,b.e_mail AS email,d.dept_name AS deptName,b.treenm_sys_dept as deptCode,");
		sql.append("e.engineer_name AS engineerName FROM address_book AS b ");
		sql.append("LEFT JOIN book_dept AS d ON b.treenm_sys_dept = d.code ");
		sql.append("LEFT JOIN pub_engineering AS e ON b.engineer_code = e.nm ");
		sql.append("LEFT JOIN (SELECT d.name,d.code FROM sys_dict as d LEFT JOIN sys_dict_cate as c ON d.listnm_sys_dict_cate=c.nm  WHERE c.code='zw' ) as q ON b.POSITION=q.code ");
		sql.append(" WHERE b.TREENM_SYS_DEPT='"+deptCode+"' and b.engineer_code ='"+refundFormBean.getMail().getEngineerCode()+"'");
		if(refundFormBean.getPlace()!=null && !"".equals(refundFormBean.getPlace())){
			if(refundFormBean.getPlace().equals("职员")){
				sql.append(" AND b.position in('002','003','004','005') ");
			}
		}
		if(refundFormBean.getMail().getStaffName()!=null &&!"".equals(refundFormBean.getMail().getStaffName())){
			sql.append(" AND b.staff_name like '"+refundFormBean.getMail().getStaffName()+"%' ");
		}
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1000000, list.toArray());
		
		return retValue;
		
	}*/

	
	
	

}

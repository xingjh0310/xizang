package com.lyht.business.mail.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.mail.bean.Mail;
import com.lyht.business.mail.formBean.MailFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class MailDao extends HibernateBaseDao<Mail> {

	// 查询物资发货基础列表
	@SuppressWarnings({ "rawtypes"})
	public List<Map> zTree() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  NM AS nm, CODE AS id,DEPT_NAME AS name,PCODE AS pId FROM  BOOK_DEPT WHERE 1=1");
		
		List<Map> list = createSQLQuerybyMap(sql.toString());
	    
		return list;
	}
	//通讯录列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults list(MailFormBean mailFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT B.ID AS ID,B.ENGINEER_CODE AS ENGINEERCODE,B.NM AS NM, ");
		sql.append("B.STAFF_CODE AS STAFFCODE,B.STAFF_NAME AS STAFFNAME,B.TREENM_SYS_DEPT AS TREENMSYSDEPT,");
		sql.append("Q.NAME AS POSITION,B.SEX AS SEX,B.LINK_PHONE AS LINKPHONE,Q.CODE AS POST,");
		sql.append("B.FIX_TELE AS FIXTELE,B.ADDRESS AS ADDRESS,B.E_MAIL AS EMAIL,");
		sql.append("B.REMARK AS REMARK ");
		sql.append(" FROM ADDRESS_BOOK AS B");
		sql.append(" LEFT JOIN (SELECT d.name,d.code FROM sys_dict as d LEFT JOIN sys_dict_cate as c ON d.listnm_sys_dict_cate=c.nm  WHERE c.code='zw' ) as q ON b.POSITION=q.code ");
		sql.append(" WHERE 1 = 1 AND B.ENGINEER_CODE='"+mailFormBean.getMail().getEngineerCode()+"'  ");
		String group = mailFormBean.getMail().getTreenmSysDept();
		if(mailFormBean.getMail().getTreenmSysDept()!=null && !"".equals(mailFormBean.getMail().getTreenmSysDept())){
			sql.append(" AND TREENM_SYS_DEPT in("+group.substring(0,group.length()-1)+")");
		}else{
			sql.append(" AND TREENM_SYS_DEPT in('')");
		}
		
		if (mailFormBean != null) {
			// 字符串字段模糊匹配，
			if (CommonUtil.trim(mailFormBean.getSearchName()).length() > 0) {

				sql.append("AND ( ENGINEER_CODE LIKE ? ");
				list.add("%" + CommonUtil.trim(mailFormBean.getSearchName()) + "%");

				sql.append("OR STAFF_CODE LIKE ? ");
				list.add("%" + CommonUtil.trim(mailFormBean.getSearchName()) + "%");

				sql.append("OR STAFF_NAME LIKE ? ");
				list.add("%" + CommonUtil.trim(mailFormBean.getSearchName()) + "%");

				sql.append("OR TREENM_SYS_DEPT LIKE ? ");
				list.add("%" + CommonUtil.trim(mailFormBean.getSearchName()) + "%");

				sql.append("OR POSITION LIKE ? ");
				list.add("%" + CommonUtil.trim(mailFormBean.getSearchName()) + "%");
				
				sql.append("OR E_MAIL LIKE ?  )");
				list.add("%" + CommonUtil.trim(mailFormBean.getSearchName()) + "%");
			}
			//性别
			if(mailFormBean.getMail().getSex()!=null &&!"".equals(mailFormBean.getMail().getSex())){
				sql.append(" AND SEX ='"+mailFormBean.getMail().getSex()+"' ");
			}
			//精准字段查询
			/*if(mailFormBean.getMail().getTreenmSysDept()!=null && !"".equals(mailFormBean.getMail().getTreenmSysDept())){
				String group = mailFormBean.getMail().getTreenmSysDept();
				sql.append(" AND TREENM_SYS_DEPT in("+group.substring(0,group.length()-1)+")");
			}*/
			/*else{
				sql.append(" AND TREENM_SYS_DEPT in('')");
			}*/
			
		}
		sql.append(" ORDER BY Q.CODE " + mailFormBean.getPageBean().getSortOrder());
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, mailFormBean.getPageBean().getOffset(),
				mailFormBean.getPageBean().getLimit(), list.toArray());
		
		List<Map> results = retValue.getResults();
		
		for(int i=0;i<results.size();i++){
			String post= results.get(i).get("POST").toString();
			//判断职位 经理隐藏联系方式
			if(post.equals("001")){
				results.get(i).put("LINKPHONE", "******");
				results.get(i).put("FIXTELE", "******");
				results.get(i).put("EMAIL", "******");
				results.get(i).put("ADDRESS", "******");
			}
			
		}
			
		return retValue;
	}
/*************************************************************************
 ******************************* APP***************************************
 *************************************************************************
 *************************************************************************/
	//app通讯录
	@SuppressWarnings("rawtypes")
	public PageResults app_list(MailFormBean mailFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID AS ID ,NM AS NM,ENGINEER_CODE AS ENGINEER_CODE,DEPT_NAME AS NAME,CODE AS CODE");
		sql.append(" FROM BOOK_DEPT WHERE 1=1 ");
		
		if(mailFormBean.getMail().getStaffName()!=null &&!"".equals(mailFormBean.getMail().getStaffName())){
			sql.append(" AND DEPT_NAME like '"+mailFormBean.getMail().getStaffName()+"%' ");
		}
		
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1000000, list.toArray());
		return retValue;
	}
	//app查询人员
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults getStaffInfoBydeptCode(String deptCode,MailFormBean mailFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT b.id AS id,b.staff_name AS name,q.name AS pot,b.position AS potCode,");
		sql.append("b.sex AS sex,b.link_phone AS phone,b.Fix_tele AS fixTele,b.nm as nm,");
		sql.append("b.address AS address,b.e_mail AS email,d.dept_name AS deptName,b.treenm_sys_dept as deptCode,");
		sql.append("e.engineer_name AS engineerName FROM address_book AS b ");
		sql.append("LEFT JOIN book_dept AS d ON b.treenm_sys_dept = d.code ");
		sql.append("LEFT JOIN pub_engineering AS e ON b.engineer_code = e.nm ");
		sql.append("LEFT JOIN (SELECT d.name,d.code FROM sys_dict as d LEFT JOIN sys_dict_cate as c ON d.listnm_sys_dict_cate=c.nm  WHERE c.code='zw' ) as q ON b.POSITION=q.code ");
		sql.append(" WHERE b.TREENM_SYS_DEPT='"+deptCode+"' and b.engineer_code ='"+mailFormBean.getMail().getEngineerCode()+"'");
		
		
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1000000, list.toArray());
		
		List<Map> results = retValue.getResults();
		
		for(int i=0;i<results.size();i++){
			String post= results.get(i).get("potCode").toString();
			//判断职位 经理隐藏联系方式
			if(post.equals("001")){
				results.get(i).put("phone", "******");
				results.get(i).put("fixTele", "******");
				results.get(i).put("email", "******");
				results.get(i).put("address", "******");
			}
			
		}
		
		
		return retValue;
		
	}
	public boolean checkMailName(String name,String dept) {
		boolean flag=true;
		
		String sql = "SELECT STAFF_NAME FROM ADDRESS_BOOK WHERE STAFF_NAME='"+name+"' AND treenm_sys_dept ='"+dept+"' ";
		List<Mail> list  = this.getListBySQL(sql, null);
        if(list.size()>0){
     	   return false;
        }
        return flag;
		
	}
	
	
	

}

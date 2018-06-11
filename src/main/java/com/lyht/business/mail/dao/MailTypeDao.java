package com.lyht.business.mail.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.mail.bean.MailType;
import com.lyht.business.mail.formBean.MailFormBean;

@Repository
@Scope("prototype")
public class MailTypeDao extends HibernateBaseDao<MailType> {

	// 查询物资发货基础列表
	@SuppressWarnings({ "rawtypes"})
	public List<Map> zTree() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID AS nm,NM AS cnm,PNM as pnm, ENGINEER_CODE as engineerCode, CODE AS id,DEPT_NAME AS name,PCODE AS pId FROM  BOOK_DEPT WHERE 1=1");
		
		List<Map> list = createSQLQuerybyMap(sql.toString());
	    
		return list;
	}
	//查询物料类型ID
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap<String, String> findByCodes(String codes){
		String ids="";
		String retcode="";
		HashMap<String, String> ret = new HashMap<String, String>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID AS ID,NM AS NM, CODE AS CODE,PCODE AS PCODE, DEPT_NAME AS DEPTNAME ");   
		sql.append(" FROM BOOK_DEPT  WHERE 1=1");   
					
		if (codes.length()>0){
		   sql.append(" AND CODE ='"+codes+"' OR PCODE ='"+codes+"' ");
		} else {
			sql.append(" AND 1<>1 ");
		}
			
        sql.append(" ORDER BY ID ");
		
        PageResults retValue =new PageResults();
        retValue =  this.findPageByFetchedSql(sql.toString(), null,0,1000000000,null);
        
        List results = retValue.getResults();
        
        for(int i=0;i<results.size();i++){
        	HashMap<String, Object> map = (HashMap<String, Object>) results.get(i);
        	 Object id = map.get("ID");
        	 Object code = map.get("CODE");
        	ids +=id+",";
        	retcode+=code+",";
        }
        ret.put("id", ids);
        ret.put("code", retcode);
        
		return ret;
	}
		//根据物料类型code删除人员
		public void delBaseById(String id) {
			String	code = id.substring(0,id.length()-1);
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM  Mail WHERE TREENM_SYS_DEPT IN("+code+")");
			Query q = this.getSession().createQuery(sql.toString());  
			q.executeUpdate();
			
		}
/*******************WEB-APP分割线***********************************/
		@SuppressWarnings("rawtypes")
		public PageResults getDeptName(MailFormBean mailFormBean) {
			ArrayList list = new ArrayList();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ID AS ID ,NM AS NM,ENGINEER_CODE AS ENGINEERCODE,DEPT_NAME AS NAME,CODE AS CODE");
			sql.append(" FROM BOOK_DEPT WHERE 1=1 ");
			PageResults retValue = new PageResults();
			retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1000000, list.toArray());
			return retValue;
		}

}

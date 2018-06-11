package com.lyht.business.materiel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.bean.MaterielType;

@Repository
@Scope("prototype")
public class MaterielTypeDao extends HibernateBaseDao<MaterielType> {

	// 查询物资发货基础列表
	@SuppressWarnings({ "rawtypes"})
	public List<Map> zTree() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  ID AS nm, CODE AS id,NAME AS name,PID AS pId FROM  PUB_MATERIEL_TYPE WHERE 1=1");
		
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
		sql.append("SELECT ID AS ID,CODE AS CODE,PID AS PID,SHORT_CODE AS SHORTCODE,FULL_CODE AS FULLCODE ");   
		sql.append(" FROM PUB_MATERIEL_TYPE  WHERE 1=1");   
					
		if (codes.length()>0){
		   sql.append(" AND CODE ='"+codes+"' OR PID ='"+codes+"' ");
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
		//根据物料类型code删除物料信息
		public void delBaseById(String id) {
			String	code = id.substring(0,id.length()-1);
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM  MaterielBase WHERE MATERIAL_GROUP IN("+code+")");
			Query q = this.getSession().createQuery(sql.toString());  
			q.executeUpdate();
			
		}

}

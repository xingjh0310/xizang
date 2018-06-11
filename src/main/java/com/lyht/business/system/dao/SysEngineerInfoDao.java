package com.lyht.business.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysEngineerInfo;
import com.lyht.business.system.formBean.SysEngineerInfoFormBean;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysEngineerInfoDao extends HibernateBaseDao<SysEngineerInfo> {

	    //根据FormBean中的条件查找实体对象List
		public PageResults m_findAll(SysEngineerInfoFormBean formBean){
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT A.ID,A.NM,A.PNM,A.THISCODE,A.ENGINEER_CODE,A.PCODE,A.ENGINEER_NAME,A.ENGINEER_SHORT,");
			sql.append("C.NAME AS VOLTAGE_EVEL,D.NAME AS TREENM_SYS_DEPT,A.LINE_LENGTH,A.DELIVERY_TIME,A.STATE,A.RENARK FROM PUB_ENGINEERING A ");
			sql.append("LEFT JOIN (SELECT A.NM,A.CODE,A.NAME FROM SYS_DICT A LEFT JOIN SYS_DICT_CATE B ON A.LISTNM_SYS_DICT_CATE=B.NM ");
			sql.append("WHERE B.CODE ='dydj') C ON A.VOLTAGE_EVEL=C.CODE LEFT JOIN (SELECT A_.NM,A_.CODE,A_.NAME FROM SYS_DICT A_ ");
			sql.append("LEFT JOIN SYS_DICT_CATE B_ ON A_.LISTNM_SYS_DICT_CATE=B_.NM WHERE B_.CODE ='demand_type') D ");
			sql.append("ON A.TREENM_SYS_DEPT=D.CODE WHERE 1=1 ");
			if (formBean!=null){
				if (CommonUtil.trim(formBean.getSearchName()).length()>0){
					sql.append(" AND ( (A.ENGINEER_CODE LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%') OR ");
					sql.append("( A.ENGINEER_NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' ) )");
				}
				if (formBean.getmSysEngineerInfo()!=null){
					if (CommonUtil.trim(formBean.getmSysEngineerInfo().getEngineerCode()).length()>0) {
						sql.append(" AND A.ENGINEER_CODE LIKE '"+CommonUtil.trim(formBean.getmSysEngineerInfo().getEngineerCode())+"%'");
					}
					if (CommonUtil.trim(formBean.getmSysEngineerInfo().getEngineerName()).length()>0) {
						sql.append(" AND A.ENGINEER_NAME LIKE '%"+CommonUtil.trim(formBean.getmSysEngineerInfo().getEngineerName())+"%'");
					}
				}
			}
			sql.append(" ORDER BY A.ENGINEER_CODE");
			return this.findPageByFetchedSql(sql.toString(), null, 
					formBean.getPageBean().getOffset(), 
					formBean.getPageBean().getLimit(), 
					null);
		}
		
		
		/**
	     * 查找根记录List
		 * @param pcode
	    */
		public PageResults m_findRoot(SysEngineerInfoFormBean formBean){
				String  sql="SELECT ";
						sql=sql+" P.ID,P.NM,P.PNM,P.THISCODE,P.ENGINEER_CODE,P.PCODE,P.ENGINEER_NAME,P.ENGINEER_SHORT,P.TREENM_SYS_DEPT, ";
		                sql=sql+" P.VOLTAGE_EVEL,P.LINE_LENGTH,P.DELIVERY_TIME,P.STATE,P.RENARK";    
						sql=sql	+ " FROM PUB_ENGINEERING AS P  ";
						sql=sql	+ " WHERE P.PNM='' ";        
							
		        String sql_all=sql+" ORDER BY P.ENGINEER_CODE ";
				
		        PageResults retValue =new PageResults();
		        retValue =  this.findPageByFetchedSql(sql_all, null
	    			,formBean.getPageBean().getOffset()
	    			,formBean.getPageBean().getLimit()
	    			,null); 
				return retValue;
		}
		
		//根据实体属性及值获取实体
		public SysEngineerInfo getByProp(String PropName,Object PropValue){
			return  this.findByObject(PropName, PropValue);
		}


	public PageResults m_findByCodes(String codes){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID AS ID,NM AS NM,PNM AS PNM ,THISCODE AS THISCODE ,ENGINEER_CODE AS ENGINEERCODE,PCODE AS PCODE,");   
		sql.append("ENGINEER_NAME AS ENGINEERNAME,ENGINEER_SHORT AS ENGINEERSHORT, TREENM_SYS_DEPT AS TREENMSYSDEPT,");   
		sql.append("VOLTAGE_EVEL AS VOLTAGEEVEL,DELIVERY_TIME AS DELIVERYTIME,STATE AS STATE,RENARK AS RENARK");   
		sql.append(" FROM PUB_ENGINEERING WHERE 1=1");   
					
		if (CommonUtil.getLength(codes)>0){
		   sql.append(" AND ENGINEER_CODE  IN ("+codes+")");
		} else {
			sql.append(" AND 1<>1 ");
		}
			
        sql.append(" ORDER BY ENGINEER_CODE ");
		
        PageResults retValue =new PageResults();
        retValue =  this.findPageByFetchedSql(sql.toString(), null,0,1000000000,null); 
		return retValue;
	}
	
	
	//获取工程信息
	public PageResults getSysEngineerInfo(){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ENGINEER_NAME,ENGINEER_SHORT,TREENM_SYS_DEPT,VOLTAGE_EVEL,");
		sql.append("LINE_LENGTH,DELIVERY_TIME,");
		sql.append("(CASE WHEN STATE = '0' THEN '未生效' WHEN STATE = '1' THEN '生效' ELSE '' END) STATE");
		sql.append(" FROM PUB_ENGINEERING");
		PageResults retValue =new PageResults();
        retValue =  this.findPageByFetchedSql(sql.toString(), null,0,1000000000,null); 
		return retValue;
	}
	
	//根据工程编号所对应的工程信息
	public PageResults getEngineerInfoByEngineerInfoCode(String code) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT * FROM PUB_ENGINEERING WHERE NM='"+code+"'");
		PageResults retValue =new PageResults();
        retValue =  this.findPageByFetchedSql(sql.toString(), null,0,1000000000,null); 
		return retValue;
	}
	
	//根据人员内码获取部门信息中的工程编号
	public List<Map> getEngineerInfoCodeByStaffNm(String listnmSysStaff){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ENGINEER_CODE FROM SYS_DEPT A ");
		sql.append(" LEFT JOIN SYS_STAFF B ON A.NM=B.TREENM_SYS_DEPT WHERE B.NM='"+listnmSysStaff+"'");
	    return this.createSQLQuerybyMap(sql.toString());
	}
	//根据人员内码获取人员信息中的工程编号
	public List<Map> getEngineerInfoCodeByStaffNm2(String listnmSysStaff){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ENGINEER_NM AS ENGINEER_CODE FROM SYS_STAFF WHERE NM='"+listnmSysStaff+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	//获取全部工程信息
	public List<Map> getWholeEngineerInfo(){
    	StringBuilder sql=new StringBuilder();
    	sql.append("SELECT NM,ENGINEER_NAME,PCODE,THISCODE FROM PUB_ENGINEERING ORDER BY ENGINEER_CODE ");
    	return this.createSQLQuerybyMap(sql.toString());
    }
    
   //根据IDS(1,2,3,4,5)删除多个对象
   public void deleteSysEngineerInfoById(String code){
	   StringBuilder sql=new StringBuilder();
	   sql.append("DELETE FROM PUB_ENGINEERING WHERE ENGINEER_CODE LIKE  '"+code+"%'");
	   this.exectueSQL(sql.toString());
   }

   //根据IDS(1,2,3,4,5)删除多个对象
   public void deleteSysEngineerInfoById_(String id){
	   StringBuilder sql=new StringBuilder();
	   sql.append("DELETE FROM PUB_ENGINEERING WHERE ENGINEER_CODE = '"+id+"'");
	   this.exectueSQL(sql.toString());
   }
   	
	public void s_flagByCode(String string, Integer flag_new) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
	   sql.append("UPDATE PUB_ENGINEERING SET STATE = '"+flag_new+"' WHERE CHARINDEX('"+string+"',ENGINEER_CODE) = 1");
	   this.exectueSQL(sql.toString());
	}


	public PageResults getDeptsByEngineerNm(String nm) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT SHORTNAME,NAME FROM SYS_DEPT WHERE ENGINEER_CODE LIKE '%"+nm+"%'");
		PageResults retValue =new PageResults();
        retValue =  this.findPageByFetchedSql(sql.toString(), null,0,1000000000,null); 
		return retValue;
	}


	public PageResults getStaffsByEngineerNm(String nm) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT NAME,NM,TREENM_SYS_DEPT FROM SYS_STAFF WHERE ENGINEER_NM LIKE '%"+nm+"%'");
		PageResults retValue =new PageResults();
        retValue =  this.findPageByFetchedSql(sql.toString(), null,0,1000000000,null); 
		return retValue;
	}
	
	/**
	 * 根据工程内码获取人员名称
	 * */
	public List<Map> getStaffInfoNameByEngineerNm(String key){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT DISTINCT A.ID,B.TABLE_B_CODE AS STAFFCODE,");
		sql.append("C.NAME AS STAFFNAME FROM PUB_ENGINEERING A");
		sql.append(" LEFT JOIN SYS_REF_ENGINEER B ON A.NM=B.TABLE_A_CODE");
		sql.append(" LEFT JOIN SYS_STAFF C ON B.TABLE_B_CODE=C.NM");
		sql.append(" AND B.REF_TABLE='pub_engineering+sys_staff'");
		sql.append(" WHERE TABLE_A_CODE ='"+key+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据人员内码获取工程信息
	 * */
	public List<Map> getEngineerInfoByStaffNm(String staffNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.NM,A.ENGINEER_NAME FROM PUB_ENGINEERING A");
		sql.append(" LEFT JOIN SYS_REF_ENGINEER B ON A.NM=B.TABLE_A_CODE");
		sql.append(" LEFT JOIN SYS_STAFF C ON B.TABLE_B_CODE=C.NM");
		sql.append(" AND B.REF_TABLE='pub_engineering+sys_staff'");
		sql.append(" WHERE TABLE_B_CODE ='"+staffNm+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据人员内码获取工程信息
	 * */
	public List<Map> getSysEngineerInfoBystaffNm(String staffNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.NM,A.ENGINEER_NAME,A.PCODE,A.THISCODE FROM PUB_ENGINEERING A ");
		sql.append("LEFT JOIN SYS_REF_ENGINEER B  ON A.NM=B.TABLE_A_CODE ");
		sql.append("AND REF_TABLE='pub_engineering+sys_staff' ");
		sql.append("WHERE TABLE_B_CODE='"+staffNm+"' ORDER BY A.ENGINEER_CODE ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据编号获取工程相关信息
	 * */
	public List<Map> getSysEngineerInfo(int id){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.ID,A.NM,A.PNM,A.THISCODE,A.ENGINEER_CODE,A.PCODE,A.ENGINEER_NAME,");
		sql.append("A.ENGINEER_SHORT,A.LINE_LENGTH,A.DELIVERY_TIME,A.STATE,A.RENARK,");
		sql.append("A.TREENM_SYS_DEPT,B.NAME AS DEPTNAME FROM PUB_ENGINEERING A ");
		sql.append("LEFT JOIN SYS_DEPT B ON A.TREENM_SYS_DEPT=B.NM WHERE A.ID='"+id+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}

}

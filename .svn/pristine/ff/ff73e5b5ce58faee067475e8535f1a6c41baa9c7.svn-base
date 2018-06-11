package com.lyht.business.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.formBean.SysDeptFormBean;
import com.lyht.util.CommonUtil;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月8日 12:28:29
 *说明:  单位部门
*/
@Repository
@Scope("prototype")
public class SysDeptDao extends HibernateBaseDao<SysDept>{
    
	//根据FormBean中的条件查找实体对象List
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findAll(SysDeptFormBean formBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT P.ID,P.NM,P.PNM,P.THISCODE,P.CODE,P.PCODE,P.NAME,");
		sql.append("P.SHORTNAME,P.SECTION,B.NAME AS TYPE,P.ENGINEER_CODE,P.FLAG ");
		sql.append("FROM SYS_DEPT P LEFT JOIN SYS_DICT B ON P.TYPE = B.CODE ");
		sql.append("WHERE 1=1 ");
		if (formBean!=null){
			if (CommonUtil.trim(formBean.getSearchName()).length()>0){
				sql.append(" AND ((P.CODE LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%') OR ");
				sql.append("( P.NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' ))");
			}
			if (formBean.getInfoBean()!=null){
				if (CommonUtil.trim(formBean.getInfoBean().getCode()).length()>0) {
					sql.append(" AND P.CODE LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getCode())+"%'");
				}
				if (CommonUtil.trim(formBean.getInfoBean().getName()).length()>0) {
					sql.append(" AND P.NAME LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getName())+"%'");
				}
			}
		}
		sql.append(" ORDER BY P.CODE");  
		return this.findPageByFetchedSql(sql.toString(), null, 
			   formBean.getPageBean().getOffset(), 
			   formBean.getPageBean().getLimit(), 
			   null);
	}
	
	//获取部门信息
	@SuppressWarnings("rawtypes")
	public PageResults getDeptAll(SysDeptFormBean formBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT * FROM SYS_DEPT");
		
		PageResults retValue =new PageResults();
	    retValue = this.findPageByFetchedSql(sql.toString(), null, 0, 10000000, null);
		return retValue;
	}
	
	//根据名称获取部门信息
	@SuppressWarnings("rawtypes")
	public PageResults getDeptName(String name){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT * FROM SYS_DEPT WHERE NAME='"+name+"'");
		PageResults retValue =new PageResults();
	    retValue = this.findPageByFetchedSql(sql.toString(), null, 0, 10000000, null);
		return retValue;
	}
	
	//根据部门编号获取部门信息
	@SuppressWarnings("rawtypes")
	public PageResults getDeptCode(String code){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT * FROM SYS_DEPT WHERE NM='"+code+"'");
		PageResults retValue =new PageResults();
	    retValue = this.findPageByFetchedSql(sql.toString(), null, 0, 10000000, null);
		return retValue;
	}
	
	//根据部门编号修改部门信息
	public void updateDeptInfoByCode(String engineerCode,SysDept mSysDept,String code){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_DEPT SET ENGINEER_CODE='"+engineerCode+"',");
		sql.append("SECTION='"+mSysDept.getSection()+"' WHERE NM='"+code+"'");
		this.exectueSQL(sql.toString());
	}
	
	//根据人员内码查询所对应的部门信息
	@SuppressWarnings("rawtypes")
	public PageResults getDeptNm(String nm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.CODE,A.NAME FROM SYS_DEPT A ");
		sql.append("LEFT JOIN SYS_STAFF B ON A.NM=B.TREENM_SYS_DEPT ");
		sql.append("WHERE B.NM='"+nm+"'");
		PageResults retValue =new PageResults();
	    retValue = this.findPageByFetchedSql(sql.toString(), null, 0, 10000000, null);
		return retValue;
	}
	
	/**
	 * 根据工程内码获取部门信息
	 * */
	@SuppressWarnings("rawtypes")
	public List<Map> getSysDeptInfoBySysEngineerNm(String sysEngineerNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,NM,PNM,THISCODE,CODE,PCODE,NAME,FLAG,SHORTNAME,");
		sql.append("ENGINEER_CODE,SECTION,TYPE FROM SYS_DEPT ");
		sql.append("WHERE ENGINEER_CODE LIKE '%"+sysEngineerNm+"%';");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据部门编号修改部门信息
	 * */
	public void updateDeptInfoById(String deptNm,String key){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_DEPT SET ENGINEER_CODE='"+key+"' WHERE NM='"+deptNm+"'");
		this.exectueSQL(sql.toString());
	}
	
	//根据工程信息编号获取部门信息
    @SuppressWarnings("rawtypes")
    public PageResults getDeptInfoByEngineerInfoCode(String engineerInfoCode){
    	StringBuilder sql=new StringBuilder();
    	sql.append("SELECT * FROM SYS_DEPT WHERE 1=1");
		PageResults retValue =new PageResults();
	    retValue = this.findPageByFetchedSql(sql.toString(), null, 0, 10000000, null);
		return retValue;
    }
    
	//根据实体属性及值获取实体
	public SysDept getByProp(String PropName,Object PropValue){
		return  this.findByObject(PropName, PropValue);
	}
	
	public void  updataChildCode(SysDeptFormBean oldbean,SysDeptFormBean newbean){
	    StringBuilder sql=new StringBuilder();
	    sql.append("UPDATE SYS_DEPT P SET P.PCODE='"+newbean.getInfoBean().getCode()+"',");
	    sql.append("P.CODE='"+newbean.getInfoBean().getCode()+"'+P.THISCODE");
	    sql.append(" WHERE P.CODE LIKE '"+oldbean.getInfoBean().getCode()+"%'");
	    sql.append(" AND P.CODE<> '"+newbean.getInfoBean().getCode()+"'");
	    
	    this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据编码删除本项及子项
	 * @param pcode
	 */
	public void removeByCode(String pcode){
	    String sql="DELETE FROM SYS_DEPT P WHERE P.CODE LIKE '"+pcode+"%'";
		this.exectueSQL(sql);
	}
    
	/**
	 * 根据编码审核本项及子项
	 * @param pcode
	 */
	public void flagByCode(String pcode,Integer flag_new){
	    String sql="UPDATE SYS_DEPT P SET P.FLAG='"+flag_new+"' WHERE P.CODE LIKE '"+pcode+"%'";
	    this.exectueSQL(sql);
	}
    
    /**
     * 查找根记录List
	 * @param pcode
    */
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findRoot(SysDeptFormBean formBean){
			String  sql="SELECT ";
					sql=sql+" P.ID,P.NM,P.PNM,P.THISCODE,P.CODE,P.PCODE,P.NAME,P.SHORTNAME,P.SECTION,P.ENGINEER_CODE,P.FLAG ";
	                sql=sql+"  ";    
					sql=sql	+ " FROM SYS_DEPT P  ";
	           
					sql=sql	+ " WHERE P.PNM='' ";        
						
	        String sql_all=sql+" ORDER BY P.CODE ";
			
	        PageResults retValue =new PageResults();
	        retValue =  this.findPageByFetchedSql(sql_all, null
    			,formBean.getPageBean().getOffset()
    			,formBean.getPageBean().getLimit()
    			,null); 
			return retValue;
	}
	

	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findByCodes(String codes){
		String  sql="SELECT ";
				sql=sql+" P.ID,P.NM,P.PNM,P.THISCODE,P.CODE,P.PCODE,P.NAME,P.SHORTNAME,P.SECTION,P.ENGINEER_CODE,P.FLAG ";
                sql=sql+"  ";    
				sql=sql	+ " FROM SYS_DEPT P ";
				sql=sql	+ " WHERE 1=1 ";    
					
		if (CommonUtil.getLength(codes)>0){
		    sql=sql+" AND P.CODE LIKE '"+codes+"%'";
		} else {
			sql=sql+" AND 1<>1 ";
		}
			
        String sql_all=sql+" ORDER BY P.CODE";
		
        PageResults retValue =new PageResults();
        retValue =  this.findPageByFetchedSql(sql_all, null,0,1000000000,null); 
		return retValue;
	}
	
	//处理工程名称
	@SuppressWarnings("rawtypes")
	public List<Map> getEngineeringByNm(String code){
		String sql="SELECT * FROM PUB_ENGINEERING WHERE NM='"+code+"'";
		return this.createSQLQuerybyMap(sql);
	}
	
	//获取部门信息（导出）
	@SuppressWarnings("rawtypes")
	public PageResults getDeptWholeInfo(){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.CODE,A.NAME,A.SHORTNAME,B.ENGINEER_NAME,A.SECTION,");
		sql.append("(CASE WHEN A.FLAG='0' THEN '未生效' WHEN A.FLAG='1' THEN '生效' ELSE '' END) FLAG");
		sql.append(" FROM SYS_DEPT A LEFT JOIN PUB_ENGINEERING B ON A.ENGINEER_CODE=B.NM");
		PageResults retValue =new PageResults();
        retValue =  this.findPageByFetchedSql(sql.toString(), null,0,1000000000,null); 
		return retValue;
	}
	
	/**
	 * 查询部门内码
	 * */
	@SuppressWarnings("rawtypes")
	public List<Map> selectDeptInfo(String nm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT NM,PNM FROM SYS_DEPT WHERE PNM='"+nm+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	//查询部门是否有数据
	@SuppressWarnings("rawtypes")
	public PageResults getDepInfoData(){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT NM,PNM,CODE,PCODE,NAME,ENGINEER_CODE,FLAG FROM SYS_DEPT");
		PageResults mPageResults =new PageResults();
		mPageResults =  this.findPageByFetchedSql(sql.toString(), null,0,1000000000,null); 
		return mPageResults;
	}
	
	//查询部门所有信息
	@SuppressWarnings("rawtypes")
	public List<Map> getDeptInfoData(){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT NM,PNM,CODE,PCODE,NAME,ENGINEER_CODE,FLAG FROM SYS_DEPT");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	//根据内码查询部门信息
	@SuppressWarnings("rawtypes")
	public List<Map> getDeptInfoData(String nm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT NM,PNM,CODE,PCODE,NAME,ENGINEER_CODE,FLAG FROM SYS_DEPT WHERE NM='"+nm+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	//根据内码查询通讯录数据
	@SuppressWarnings("rawtypes")
	public List<Map> getMailInfoByNm(String nm) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NM,PNM,CODE,PCODE,DEPT_NAME,ENGINEER_CODE,FLAG ");
		sql.append(" FROM BOOK_DEPT WHERE NM='"+nm+"' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	//然后向通讯录中添加数据
	@SuppressWarnings("rawtypes")
	public void insetMailData(Map map){
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO BOOK_DEPT (NM,PNM,CODE,PCODE,DEPT_NAME,ENGINEER_CODE,FLAG) ");
		sql.append("VALUES ('"+map.get("NM")+"','"+map.get("PNM")+"','"+map.get("CODE")+"',");
		sql.append("'"+map.get("PCODE")+"','"+map.get("NAME")+"',");
		sql.append("'"+map.get("ENGINEER_CODE")+"','"+map.get("FLAG")+"')");
		this.exectueSQL(sql.toString());
	}
	
	//根据IDS(1,2,3,4,5)删除多个对象
	public void deleteDeptById(String id){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_DEPT WHERE ID IN ('"+id+"')");
		this.exectueSQL(sql.toString());
	}

	public void deleteDeptByCode(String code) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_DEPT WHERE CODE LIKE  '"+code+"%'");
		this.exectueSQL(sql.toString());
	}
	
	public void deleteDeptByCode_(String code) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_DEPT WHERE CODE  LIKE '"+code+"%'");
		this.exectueSQL(sql.toString());
	}

	public void s_flagByCode(String string, Integer flag_new) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_DEPT SET FLAG='"+flag_new+"' WHERE CHARINDEX('"+string+"',CODE) = 3");
		this.exectueSQL(sql.toString());
	}

	@SuppressWarnings("rawtypes")
	public Object queryType(SysDeptFormBean formBean) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.CODE,A.NAME FROM SYS_DICT A LEFT JOIN SYS_DICT_CATE B ON A.LISTNM_SYS_DICT_CATE = B.NM WHERE B.CODE = 'demand_type'");
		PageResults mPageResults =new PageResults();
		mPageResults =  this.findPageByFetchedSql(sql.toString(), null,0,1000000000,null); 
		return mPageResults;
	}
	
	/**
	 * 根据部门编码获取部门信息
	 * */
	@SuppressWarnings("rawtypes")
	public List<Map> getSysDeptInfo(SysDept mSysDept){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT NM,PNM,CODE,PCODE,NAME,ENGINEER_CODE,FLAG FROM SYS_DEPT ");
		sql.append("WHERE CODE='"+(mSysDept.getPcode()+mSysDept.getThiscode())+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
}

package com.lyht.business.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.formBean.SysDeptFormBean;
import com.lyht.business.system.formBean.SysStaffFormBean;
import com.lyht.util.CommonUtil;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 21:12:02
 *说明:  人员信息
*/
@Repository
@Scope("prototype")
public class SysStaffDao extends HibernateBaseDao<SysStaff>{

	//根据FormBean中的条件查找实体对象List
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findAll(SysStaffFormBean formBean){
	  StringBuilder sql=new StringBuilder();
      
      sql.append("SELECT SF.ID,SF.NM,SF.CODE,SF.NAME,SF.TREENM_SYS_DEPT,SF.DICTNM_XINGBIE,SF.FLAG,SF.ORIGIN,SF.DUTY,");
      sql.append("SF.MEMO,SF.ISLOGIN,SF.SYSFLAG,DEPT.NAME AS SYSDEPTNAME,SD.NAME AS XINGBIENAME,SF.ENGINEER_NM,SF.PHONE FROM SYS_STAFF AS SF");
      sql.append(" LEFT JOIN SYS_DEPT AS DEPT ON SF.TREENM_SYS_DEPT= DEPT.NM");
      sql.append(" LEFT JOIN SYS_DICT AS SD ON SF.DICTNM_XINGBIE=SD.CODE AND SD.LISTNM_SYS_DICT_CATE='xingbie'");
      sql.append(" WHERE SF.SYSFLAG!='1' ");
	        
		if (formBean!=null){
			//字符串字段模糊匹配
			if (CommonUtil.trim(formBean.getSearchName()).length()>0){
				 sql.append(" AND ( ");
                 sql.append("  (SF.NM LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
                 sql.append("  OR  (SF.CODE LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
                 sql.append("  OR  (SF.NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
                 sql.append("  OR  (DEPT.NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
                 sql.append("  OR  (SD.NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
                 sql.append("  OR  (SF.MEMO LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' )");
                 sql.append(" )");
			}
			//字段条件查询，根据需要自己增加
			if (formBean.getInfoBean()!=null){
					if (CommonUtil.trim(formBean.getInfoBean().getNm()).length()>0) 
					{                      
						sql.append(" AND SF.NM LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getNm())+"%'");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getCode()).length()>0) 
					{             
						sql.append(" AND SF.CODE LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getCode())+"%'");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getName()).length()>0) 
					{         
						sql.append(" AND SF.NAME LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getName())+"%'");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getTreenmSysDept()).length()>0) 
					{           
						sql.append(" AND SF.TREENM_SYS_DEPT ='"+CommonUtil.trim(formBean.getInfoBean().getTreenmSysDept())+"'");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getDictnmXingbie()).length()>0) 
					{            
						sql.append(" AND SD.CODE = '"+CommonUtil.trim(formBean.getInfoBean().getDictnmXingbie())+"' ");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getMemo()).length()>0) 
					{              
						sql.append(" AND SF.MEMO LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getMemo())+"%' ");
                    }
		    }
		}
		
		sql.append(" ORDER BY SF."+formBean.getPageBean().getSort()+" "+formBean.getPageBean().getSortOrder());
		
        PageResults retValue =new PageResults();
		retValue =this.findPageByFetchedSql(sql.toString(), null
    			,formBean.getPageBean().getOffset()
    			,formBean.getPageBean().getLimit()
    			,null);
        
		return retValue;
	}
	
	//根据部门内码获取人员信息（导出全部数据）
	@SuppressWarnings("rawtypes")
	public PageResults getStaffInfoByDeptNm(SysDeptFormBean mSysDeptFormBean){
		String sql=getSqlString(mSysDeptFormBean);
		PageResults retValue =new PageResults();
		retValue =this.findPageByFetchedSql(sql, null,
    		    0,1000000000,null);
		
		return retValue;
	}
	
	//根据部门内码获取人员信息（导出当前页数据）
	@SuppressWarnings("rawtypes")
    public PageResults getStaffInfoByDeptNm_(SysDeptFormBean mSysDeptFormBean,SysStaffFormBean mSysStaffFormBean){
		String sql=getSqlString(mSysDeptFormBean);
		PageResults retValue =new PageResults();
		retValue =this.findPageByFetchedSql(sql, null,
				mSysStaffFormBean.getPageBean().getOffset(),
				mSysStaffFormBean.getPageBean().getLimit(),
				null);
			
		return retValue;
	}
	
	//拼接sql语句
	private String getSqlString(SysDeptFormBean mSysDeptFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.CODE,A.NAME,A.DUTY,B.NAME AS SEX,A.ORIGIN, ");
		sql.append("(CASE WHEN A.SYSFLAG='0' THEN '' WHEN A.SYSFLAG='1' THEN '系统内置' ELSE '' END) SYSFLAG,");
		sql.append("(CASE WHEN A.FLAG='0' THEN '未生效' WHEN A.FLAG='1' THEN '生效' ELSE '' END) FLAG,");
	    sql.append("A.MEMO FROM SYS_STAFF A LEFT JOIN SYS_DICT B ON A.DICTNM_XINGBIE=B.NM ");
		sql.append("WHERE A.TREENM_SYS_DEPT='"+mSysDeptFormBean.getInfoBean().getNm()+"'");
		return sql.toString();
	}
	
	//获取人员信息
	//findAll
	@SuppressWarnings("rawtypes")
	public PageResults findAll(){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT * FROM SYS_STAFF");
		PageResults retValue =new PageResults();
		retValue =this.findPageByFetchedSql(sql.toString(), null,
    		    0,1000000000,null);
		return retValue;
	}
	
	//根据人员名称获取人员信息
	@SuppressWarnings("rawtypes")
	public PageResults findName(String name){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT * FROM SYS_STAFF WHERE NAME='"+name+"'");
		PageResults retValue =new PageResults();
		retValue =this.findPageByFetchedSql(sql.toString(), null,
    		    0,1000000000,null);
		return retValue;
	}
    
	//根据实体属性及值获取实体
	public SysStaff getByProp(String PropName,Object PropValue){
		SysStaff staff=  this.findByObject(PropName, PropValue);
		return staff;
	}
    
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findByIds(String ids){
            
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT SF.ID,SF.NM,SF.CODE,SF.NAME,SF.TREENM_SYS_DEPT,SF.DICTNM_XINGBIE,SF.FLAG,SF.ISLOGIN,");
	    sql.append("SF.MEMO,SF.SYSFLAG,DEPT.NAME AS SYSDEPTNAME,SD.NAME AS XINGBIENAME FROM SYS_STAFF AS SF");
	    sql.append(" LEFT JOIN SYS_DEPT AS DEPT ON SF.TREENM_SYS_DEPT= DEPT.NM");
	    sql.append(" LEFT JOIN SYS_DICT AS SD ON SF.DICTNM_XINGBIE=SD.NM AND SD.LISTNM_SYS_DICT_CATE='XINGBIE'");
	    sql.append(" WHERE 1=1");
					
		if (CommonUtil.getLength(ids) > 0 ){
			sql.append(" AND SF.ID IN ("+ids+")");
		}
			
        sql.append(" ORDER BY SF.ID");
		
        PageResults retValue =new PageResults();
	    retValue =this.findPageByFetchedSql(sql.toString(), null
    			,0
    			,100000000
    			,null);
	    
		return retValue;
	}	
	
	
	//验证人员编码与人员名称是否唯一
	public boolean checkOnlyByCodeName(SysStaff mSysStaff){
    	 boolean flag=true;
         String sql="SELECT * FROM SYS_STAFF WHERE (NAME='"+mSysStaff.getName()+"'OR code='"+mSysStaff.getCode()+"') AND nm!='"+mSysStaff.getNm()+"'    ";
         List<SysStaff> list  = this.getListBySQL(sql, null);
         if(list.size()>0){
      	   return false;
         }
         return flag;
	}
	
	//验证手机号码
	public boolean checkOnlyByPhone(SysStaff mSysStaff) {
		boolean flag=true;
        String sql="SELECT * FROM SYS_STAFF WHERE PHONE='"+mSysStaff.getPhone()+"'  and nm!='"+mSysStaff.getNm()+"'  ";
        List<SysStaff> list  = this.getListBySQL(sql, null);
        if(list.size()>0){
     	   return false;
        }
        return flag;
	}
	
	//根据性别（汉字）获取性别（状态）
	@SuppressWarnings({ "rawtypes" })
	public PageResults getNmByChara(String characters){
		String sql="SELECT NM FROM SYS_DICT WHERE NAME='"+characters+"'";
		PageResults retValue =new PageResults();
	    retValue =this.findPageByFetchedSql(sql, null
    			,0
    			,100000000
    			,null);
	    
		return retValue;
	}
	
	/**
	 * 修改人员状态（取消登录）<工程信息模块>
	 * */
	public void updateStaffSateInfo(){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_STAFF SET ISLOGIN='0'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 修改人员状态（登录）<人员信息模块>
	 * */
	public int updateStaffSateInfo(String staffNm){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_STAFF SET ISLOGIN='1' WHERE NM IN ("+staffNm+")");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	 * 修改人员状态（取消登录）<人员信息模块>
	 * */
	public void updateStaffSateInfo_(String staffNm){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_STAFF SET ISLOGIN='0' WHERE NM ='"+staffNm+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员内码修改人员状态（登录）
	 * */
	public int updateStaffSateInfoByNm(String StaffNm){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_STAFF SET ISLOGIN='1'");
		sql.append(" WHERE NM='"+StaffNm+"'");
		return this.exectueSQL(sql.toString());
	}
	
	//根据部门内码与工程内码查询人员信息
	@SuppressWarnings("rawtypes")
	public List<Map> getStaffInfoByNm(String deptNm,String sysEngineerNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT NM,CODE,NAME,TREENM_SYS_DEPT,DICTNM_XINGBIE,FLAG,MEMO,");
		sql.append("SYSFLAG,ORIGIN,DUTY,ISLOGIN,PHONE,TELEPHONE,ENGINEER_NM ");
		sql.append("FROM SYS_STAFF WHERE TREENM_SYS_DEPT='"+deptNm+"' ");
		sql.append("AND ENGINEER_NM like '%"+sysEngineerNm+"%' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	//根据人员编号查询是否登录状态
	@SuppressWarnings("rawtypes")
	public PageResults getIsLoginByStaffNm(String staffNm){
		String sql="SELECT ISLOGIN FROM SYS_STAFF WHERE NM='"+staffNm+"'";
		PageResults retValue =new PageResults();
	    retValue =this.findPageByFetchedSql(sql, null
    			,0
    			,100000000
    			,null);
	    return retValue;
	}

	public PageResults getStaffInfoByNm(String staffNm) {
		String sql="SELECT ENGINEER_NM FROM SYS_STAFF WHERE NM='"+staffNm+"'";
		PageResults retValue =new PageResults();
	    retValue =this.findPageByFetchedSql(sql, null
    			,0
    			,100000000
    			,null);
	    return retValue;
	}

	public void updateStaffEngineerNm(String engineerNm, String staffNm) {
		String sql="UPDATE SYS_STAFF SET ENGINEER_NM= '"+engineerNm+"' WHERE NM='"+staffNm+"'";
		this.exectueSQL(sql);
	}

	public PageResults getByPhone(String phone) {
		String sql="SELECT ID FROM SYS_STAFF WHERE PHONE='"+phone+"'";
		PageResults retValue =new PageResults();
	    retValue =this.findPageByFetchedSql(sql, null
    			,0
    			,100000000
    			,null);
	    return retValue;
	}
	/**
	 * 增加实体对象
	 * */
	public void saveSysStaff(SysStaff sysstaff){
		String sql=execQL(sysstaff); //执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 执行新增语句
	 * */
	private String execQL(SysStaff sysstaff){
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SYS_STAFF (NM,CODE,NAME,TREENM_SYS_DEPT,DICTNM_XINGBIE,");
		sql.append("FLAG,MEMO,SYSFLAG,ORIGIN,DUTY,ISLOGIN,PHONE,TELEPHONE,ENGINEER_NM) ");
		sql.append("VALUES ('"+CommonUtil.trim(sysstaff.getNm())+"',");
		sql.append("'"+CommonUtil.trim(sysstaff.getCode())+"','"+CommonUtil.trim(sysstaff.getName())+"',");
		sql.append("'"+CommonUtil.trim(sysstaff.getTreenmSysDept())+"','"+CommonUtil.trim(sysstaff.getDictnmXingbie())+"',");
		sql.append("'"+CommonUtil.trim(sysstaff.getFlag())+"','"+CommonUtil.trim(sysstaff.getMemo())+"',");
		sql.append("'"+CommonUtil.trim(sysstaff.getSysflag())+"','"+CommonUtil.trim(sysstaff.getOrigin())+"',");
		sql.append("'"+CommonUtil.trim(sysstaff.getDuty())+"','"+CommonUtil.trim(sysstaff.getIsLogin())+"',");
		sql.append("'"+CommonUtil.trim(sysstaff.getPhone())+"','"+CommonUtil.trim(sysstaff.getTelephone())+"',");
		sql.append("'"+CommonUtil.trim(sysstaff.getEngineerNm())+"')");
		return sql.toString();
	}

	@SuppressWarnings("rawtypes")
	public PageResults getEng(String deptNm) {
		
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT 	eng.nm,eng.engineer_name AS engineername,eng.delivery_time AS deliverytime,eng.line_length AS linelength ");
			sql.append("FROM pub_engineering AS eng ");
			sql.append("LEFT JOIN (SELECT table_a_code FROM sys_ref_engineer AS ref ");
			sql.append("LEFT JOIN sys_staff AS sta ON ref.table_b_code = sta.nm ");
			sql.append("WHERE table_b_code ='"+deptNm+"') AS staffN ON eng.nm = staffN.table_a_code");
			sql.append(" WHERE 1=1");
			
			PageResults retValue =new PageResults();
			
			retValue =this.findPageByFetchedSql(sql.toString(), null,0,100000000,null);
			
			return retValue;
	}
	
	/**
	 * 通过角色内码获取人员内码
	 * */
	@SuppressWarnings("rawtypes")
	public List<Map> getStaffCodeByRoleCode(String ids){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT CODE,TA_NAME,TA_NM,TB_NAME,TB_NM FROM SYS_RELA ");
		sql.append("WHERE CODE='SYS_ROLE+SYS_STAFF' AND TA_NM IN ("+ids+")");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 通过人员内码获取角色内码
	 * */
	@SuppressWarnings("rawtypes")
	public List<Map> getRoleCodeByStaffCode(String ids){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT CODE,TA_NAME,TA_NM,TB_NAME,TB_NM FROM SYS_RELA ");
		sql.append("WHERE CODE='SYS_ROLE+SYS_STAFF' AND TB_NM IN ("+ids+")");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	
}

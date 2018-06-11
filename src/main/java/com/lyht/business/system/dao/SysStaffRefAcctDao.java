package com.lyht.business.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.formBean.SysStaffRefAcctFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.Randomizer;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysStaffRefAcctDao extends HibernateBaseDao<SysStaff>{

	/**
	 * 显示人员及账户列表信息
	 */
	public PageResults m_findAll(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.ID,A.NM,F.FILESIZE,A.NAME AS STAFFNAME,E.NAME AS DUTY,");
		sql.append("A.ISLOGIN AS ISLOGIN,D.NAME AS SEX,B.NAME AS ACCTNAME,");
		sql.append("B.DICTNM_ZHLX AS ACCTTYPE,A.ORIGIN AS ORIGIN,B.DICTNM_DLLX AS LOGINTYPE,");
		sql.append("B.YXQ AS VALIDITYTIME,B.PWD AS PWD,B.SYSFLAG AS SYSFLAG,A.ENGINEER_NM,");
		sql.append("A.TREENM_SYS_DEPT AS DEPTNM,C.NAME AS DEPTNAME FROM SYS_STAFF A ");
		sql.append("LEFT JOIN SYS_ACCT B ON A.NM=B.LISTNM_SYS_STAFF ");
		sql.append("LEFT JOIN SYS_DEPT C ON A.TREENM_SYS_DEPT=C.NM ");
		sql.append("LEFT JOIN SYS_DICT D ON A.DICTNM_XINGBIE=D.CODE ");
		sql.append("LEFT JOIN SYS_DICT E ON A.DUTY=E.NM ");
		sql.append("LEFT JOIN (SELECT COUNT(TABLE_PK_COLUMN) FILESIZE,");
		sql.append("TABLE_PK_COLUMN FROM PUB_FILES ");
		sql.append("WHERE TABLE_NAME = 'SYS_STAFF' GROUP BY TABLE_PK_COLUMN) F ");
		sql.append("ON A.NM=F.TABLE_PK_COLUMN WHERE B.SYSFLAG!=1");
		String str=appendStr(mSysStaffRefAcctFormBean);
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY A.CODE ");
		return this.findPageByFetchedSql(sql.toString(), null, 
				mSysStaffRefAcctFormBean.getPageBean().getOffset()
				,mSysStaffRefAcctFormBean.getPageBean().getLimit(), null);
	}
	
	/**
	 * 根据人员内码查询人员信息
	 * */
	public List<Map> getSysStaffByStaffNm(String staffNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.ID,A.NM,A.CODE,A.NAME,A.TREENM_SYS_DEPT,b.NAME AS DEPT_NAME,");
		sql.append("A.DICTNM_XINGBIE,A.FLAG,A.MEMO,A.SYSFLAG,A.ORIGIN,A.DUTY AS DUTY,");
		sql.append("A.ISLOGIN,A.PHONE,A.TELEPHONE,A.ENGINEER_NM FROM SYS_STAFF A ");
		sql.append("LEFT JOIN SYS_DEPT B ON A.TREENM_SYS_DEPT=B.NM ");
		sql.append("WHERE A.NM='"+staffNm+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据人员编号与人员名称查询人员信息
	 * */
	public List<Map> getSysStaffInfoByStaffCodeOrName(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,NM,CODE,NAME AS STAFFNAME,TREENM_SYS_DEPT,");
		sql.append("DICTNM_XINGBIE,FLAG,MEMO,SYSFLAG,ORIGIN,DUTY AS DUTY,");
		sql.append("ISLOGIN,PHONE,TELEPHONE,ENGINEER_NM FROM SYS_STAFF ");
		sql.append(" WHERE NAME='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getName()+"' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 新增人员信息
	 * */
	public int insertSysStaffInfo(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean,String staffNm){
		String staffCode=Randomizer.nextNumber("staff_", 3);
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SYS_STAFF (NM,CODE,NAME,TREENM_SYS_DEPT,DICTNM_XINGBIE,");
		sql.append("FLAG,MEMO,SYSFLAG,ORIGIN,ISLOGIN,PHONE,TELEPHONE,ENGINEER_NM,DUTY) ");
		sql.append("VALUES ('"+staffNm+"','"+staffCode+"',");
		sql.append("'"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getName()+"',");
		sql.append("'"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getTreenmSysDept()+"',");
		sql.append("'"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getDictnmXingbie()+"',");
		sql.append("0,'"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getMemo()+"',0,");
		sql.append("'"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getOrigin()+"',");
		sql.append("'"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getIsLogin()+"',");
		sql.append("'"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getPhone()+"',");
		sql.append("'"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getTelephone()+"',");
		sql.append("'"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getEngineerNm()+"',");
		sql.append("'"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getDuty()+"')");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员内码修改人员信息
	 * */
	public int updateSysStaffInfo(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_STAFF SET NM='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getNm()+"',");
		sql.append("CODE='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getCode()+"',");
		sql.append("NAME='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getName()+"',");
		sql.append("TREENM_SYS_DEPT='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getTreenmSysDept()+"',");
		sql.append("DICTNM_XINGBIE='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getDictnmXingbie()+"',");
		sql.append("FLAG='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getFlag()+"',");
		sql.append("MEMO='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getMemo()+"',");
		sql.append("SYSFLAG='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getSysflag()+"',");
		sql.append("ORIGIN='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getOrigin()+"',");
		sql.append("ISLOGIN='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getIsLogin()+"',");
		sql.append("PHONE='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getPhone()+"',");
		sql.append("TELEPHONE='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getTelephone()+"',");
		sql.append("ENGINEER_NM='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getEngineerNm()+"',");
		sql.append("DUTY='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getDuty()+"'");
		sql.append("WHERE NM='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getNm()+"'");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员内码删除人员信息
	 * */
	public int deleteSysStaffInfo(String StaffNm){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_STAFF WHERE NM ='"+StaffNm+"'");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员内码获取人员与账户信息
	 * */
	public List<Map> getSysStaffAndAcctInfoByNm(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.NM,A.CODE,A.NAME,B.NAME AS ACCTNAME FROM SYS_STAFF A ");
		sql.append("LEFT JOIN SYS_ACCT B ON A.NM=B.LISTNM_SYS_STAFF ");
		sql.append("WHERE A.NM!='"+mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getNm()+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据人员内码获取工程名称
	 * */
	public List<Map> getEngineerNameBystaffNm(String staffNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT C.ENGINEER_NAME FROM SYS_STAFF A LEFT JOIN SYS_REF_ENGINEER B");
		sql.append(" ON A.NM=B.TABLE_B_CODE LEFT JOIN PUB_ENGINEERING C");
		sql.append(" ON C.NM=B.TABLE_A_CODE WHERE A.NM='"+staffNm+"'");
		sql.append(" AND B.REF_TABLE='pub_engineering+sys_staff'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据人员内码获取表关联信息
	 * */
	public List<Map> getRefTableBystaffNm(String staffNm){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT DISTINCT ID, NAME AS ROLENAME FROM SYS_ROLE WHERE NM IN ");
		sql.append("(SELECT TA_NM from SYS_RELA ");
		sql.append("WHERE TB_NM='"+staffNm+"' AND CODE='sys_role+sys_staff')");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 拼接sql语句字符串
	 * */
	private String appendStr(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mSysStaffRefAcctFormBean){
			if (CommonUtil.trim(mSysStaffRefAcctFormBean.getSearchName()).length()>0){
				sql.append(" AND ( ( A.NAME LIKE '%"+CommonUtil.trim(mSysStaffRefAcctFormBean.getSearchName())+"%') OR ");
				sql.append("( A.DUTY LIKE '%"+CommonUtil.trim(mSysStaffRefAcctFormBean.getSearchName())+"%' ) OR ");
				sql.append("( D.NAME LIKE '%"+CommonUtil.trim(mSysStaffRefAcctFormBean.getSearchName())+"%') OR ");
				sql.append("( B.NAME LIKE '%"+CommonUtil.trim(mSysStaffRefAcctFormBean.getSearchName())+"%') OR ");
				sql.append("( B.DICTNM_ZHLX LIKE '%"+CommonUtil.trim(mSysStaffRefAcctFormBean.getSearchName())+"%') OR ");
				sql.append("( B.DICTNM_DLLX LIKE '%"+CommonUtil.trim(mSysStaffRefAcctFormBean.getSearchName())+"%' ) OR ");
				sql.append("( B.YXQ LIKE '%"+CommonUtil.trim(mSysStaffRefAcctFormBean.getSearchName())+"%' ) OR ");
				sql.append("( A.ORIGIN LIKE '%"+CommonUtil.trim(mSysStaffRefAcctFormBean.getSearchName())+"%' ) )");
			}
			if(null!=mSysStaffRefAcctFormBean.getmSysAcctInfoBean() && 
						null!=mSysStaffRefAcctFormBean.getmSysStaffInfoBean()){
				if(CommonUtil.trim(mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getNm()).length()>0){
					sql.append(" AND A.NM = '"+CommonUtil.trim(mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getNm())+"'");
				}
				if(CommonUtil.trim(mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getTreenmSysDept()).length()>0){
					sql.append(" AND A.TREENM_SYS_DEPT = '"+CommonUtil.trim(mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getTreenmSysDept())+"'");
				}
				if(CommonUtil.trim(mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getDictnmXingbie()).length()>0){
					sql.append(" AND D.CODE = '"+CommonUtil.trim(mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getDictnmXingbie())+"'");
				}
			}
			if(null!=mSysStaffRefAcctFormBean.getIds() && !"".equals(mSysStaffRefAcctFormBean.getIds())){
				String str="";
				String []ids=mSysStaffRefAcctFormBean.getIds().split(",");
				for(int i=0;i<ids.length;i++){
					str+="'"+ids[i]+"',";
				}
				if(!"".equals(str)){
					if(",".equals(str.substring(str.length()-1))){
						str=str.substring(0,str.length()-1);
					}
				}
				sql.append(" AND A.TREENM_SYS_DEPT IN ("+str+")");
			}
		}
		return sql.toString();
	}
	
	
	
}

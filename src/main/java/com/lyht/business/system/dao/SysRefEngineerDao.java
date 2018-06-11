package com.lyht.business.system.dao;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysRefEngineer;
import com.lyht.business.system.formBean.SysEngineerInfoFormBean;
import com.lyht.util.Randomizer;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysRefEngineerDao extends HibernateBaseDao<SysRefEngineer>{

	/**
	 * 根据工程内码与关联表获取工程关联信息
	 * */
	public List<Map> getSysRefEngineerInfo(String sysEngineerNm,String refTableName){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,NM,TABLE_A_CODE,TABLE_A_NAME,TABLE_B_CODE,TABLE_B_NAME,");
		sql.append("REF_TABLE FROM SYS_REF_ENGINEER WHERE TABLE_A_CODE='"+sysEngineerNm+"' ");
		sql.append(" AND REF_TABLE='"+refTableName+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据人员内码与关联表获取工程关联信息
	 * */
	public List<Map> getSysRefEngineerInfoByStaffNm(String staffNm,String refTableName){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,NM,TABLE_A_CODE,TABLE_A_NAME,TABLE_B_CODE,TABLE_B_NAME,");
		sql.append("REF_TABLE FROM SYS_REF_ENGINEER WHERE TABLE_B_CODE='"+staffNm+"' ");
		sql.append(" AND REF_TABLE='"+refTableName+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 获取人员内码
	 * */
	public List<Map> getSysRefEngineerInfo(String refTableName){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,NM,TABLE_A_CODE,TABLE_A_NAME,TABLE_B_CODE,TABLE_B_NAME,");
		sql.append("REF_TABLE FROM SYS_REF_ENGINEER WHERE REF_TABLE='"+refTableName+"' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据工程内码与关联表删除工程关联信息
	 * */
	public int deleteSysRefEngineerInfo(String sysEngineerNm,String refTableName){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_REF_ENGINEER WHERE TABLE_A_CODE='"+sysEngineerNm+"' ");
		sql.append("AND REF_TABLE='"+refTableName+"'");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员内码与关联表删除工程关联信息
	 * */
	public int deleteSysRefEngineerInfoByStaffNm(String staffNm,String refTableName){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_REF_ENGINEER WHERE TABLE_B_CODE='"+staffNm+"' ");
		sql.append("AND REF_TABLE='"+refTableName+"'");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	 * 新增关联工程信息
	 * */
	public int insertStaffRefPubInfo(String staffNm,String sysEngineerNm,String refTableName,String tableAName,String tableBName){
		String nm=Randomizer.getRandom();
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SYS_REF_ENGINEER (NM,TABLE_A_CODE,TABLE_A_NAME,");
		sql.append("TABLE_B_CODE,TABLE_B_NAME,REF_TABLE) VALUES ('"+nm+"','"+sysEngineerNm+"',");
		sql.append("'"+tableAName+"','"+staffNm+"','"+tableBName+"','"+refTableName+"')");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	  * 根据工程编号与表关联获取人员信息
	* */
	public PageResults getStaffInfoByEngineerInfoCode(String engineerInfoCode,String tableName){
	   StringBuilder sql=new StringBuilder();
	   sql.append("SELECT ID,NM,TABLE_A_CODE,TABLE_A_NAME,TABLE_B_CODE,TABLE_B_NAME,");
	   sql.append("REF_TABLE FROM SYS_REF_ENGINEER WHERE TABLE_A_CODE='"+engineerInfoCode+"' ");
	   sql.append(" AND REF_TABLE='"+tableName+"'");
	   return this.findPageByFetchedSql(sql.toString(), null, 0, 1000000000, null);
	}
	
	
}

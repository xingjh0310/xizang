package com.lyht.business.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDict;
import com.lyht.business.system.formBean.SysDictCateFormBean;
import com.lyht.business.system.formBean.SysDictFormBean;
import com.lyht.util.CommonUtil;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 20:36:28
 *说明:  字典
*/
@Repository
@Scope("prototype")
public class SysDictDao extends HibernateBaseDao<SysDict>{
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findAll(SysDictFormBean formBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT P.ID,P.NM,P.CODE,P.NAME,P.MEMO,P.FLAG,");
		sql.append(" P.LISTNM_SYS_DICT_CATE,P.SYSFLAG FROM SYS_DICT AS P ");
		sql.append(" LEFT JOIN SYS_DICT_CATE AS SYSDICTCATE ON ");
		sql.append(" P.LISTNM_SYS_DICT_CATE= SYSDICTCATE.NM WHERE 1=1 ");
		if (formBean!=null){
			if (CommonUtil.trim(formBean.getSearchName()).length()>0){
				sql.append(" AND ( (P.NM LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' ) OR  ");
				sql.append("(P.CODE LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' ) OR  ");
				sql.append("(P.NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' ) OR  ");
				sql.append("(P.MEMO LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' ) OR  ");
			    sql.append("(SYSDICTCATE.NAME LIKE '%"+CommonUtil.trim(formBean.getSearchName())+"%' ) )");
			}
			if (formBean.getInfoBean()!=null){
				if (CommonUtil.trim(formBean.getInfoBean().getNm()).length()>0){
					sql.append(" AND P.NM LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getNm())+"%'");
				}
				if (CommonUtil.trim(formBean.getInfoBean().getCode()).length()>0){
					sql.append(" AND P.CODE LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getCode())+"%'");
				}
				if (CommonUtil.trim(formBean.getInfoBean().getName()).length()>0){
					sql.append(" AND P.NAME LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getName())+"%'");
				}
				if (CommonUtil.trim(formBean.getInfoBean().getMemo()).length()>0){
					sql.append(" AND P.MEMO LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getMemo())+"%'");
				}
				if (CommonUtil.trim(formBean.getInfoBean().getListnmSysDictCate()).length()>0){
					sql.append(" AND P.LISTNM_SYS_DICT_CATE LIKE '%"+CommonUtil.trim(formBean.getInfoBean().getListnmSysDictCate())+"%'");
				}
				if (CommonUtil.trim(formBean.getmSysDictCateInfoBean().getCode()).length()>0){
					sql.append(" AND SYSDICTCATE.CODE LIKE '%"+CommonUtil.trim(formBean.getmSysDictCateInfoBean().getCode())+"%'");
				}
			}
		}
		sql.append(" ORDER BY P.ID");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 1000000000, null);
	}
	
	//获取字典数据
	@SuppressWarnings("rawtypes")
	public PageResults findAll(SysDictCateFormBean mSysDictCateFormBean){
		String code=mSysDictCateFormBean.getInfoBean().getCode();
		StringBuilder sql=new StringBuilder();
		
		sql.append("SELECT A.NM,A.NAME FROM SYS_DICT A ");
		sql.append("LEFT JOIN SYS_DICT_CATE B ON A.LISTNM_SYS_DICT_CATE=B.NM ");
		sql.append("WHERE B.CODE='"+code.toLowerCase()+"' ORDER BY A.ID");
		
		PageResults retValue =new PageResults();
	    retValue =  this.findPageByFetchedSql(sql.toString(), null
			,0
			,1000000000
			,null);
		    
		return retValue;
	}
	
	//根据名称与内码获取编码
	@SuppressWarnings("rawtypes")
	public PageResults findNmAndName(String nm,String name){
		StringBuilder sql=new StringBuilder();
		
		sql.append("SELECT A.CODE FROM SYS_DICT A LEFT JOIN SYS_DICT_CATE B ");
		sql.append("ON A.LISTNM_SYS_DICT_CATE=B.NM WHERE A.NAME='"+name+"' ");
		sql.append("AND B.CODE='"+nm.toLowerCase()+"'");
		
		PageResults retValue =new PageResults();
	    retValue =  this.findPageByFetchedSql(sql.toString(), null
			,0
			,1000000000
			,null);
		return retValue;
	}

	//获取问题类型、处理方式、通知类型
	@SuppressWarnings("rawtypes")
	public PageResults getDictType(String flag){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.NM,A.NAME,A.CODE,A.LISTNM_SYS_DICT_CATE FROM SYS_DICT A ");
		sql.append(" LEFT JOIN SYS_DICT_CATE B ON A.LISTNM_SYS_DICT_CATE=B.NM");
		sql.append(" WHERE B.CODE='"+flag+"'");
		PageResults mPageResults =new PageResults();
		mPageResults =  this.findPageByFetchedSql(sql.toString(), null
			,0
			,1000000000
			,null);
		return mPageResults;
	}
		
	//获取字典基础数据
	@SuppressWarnings({ "rawtypes" })
	public PageResults getDictData(SysDictCateFormBean formBean){
		StringBuilder sql=new StringBuilder();
		String code=formBean.getInfoBean().getCode();
		sql.append("SELECT A.NM AS NM,A.CODE AS CODE,A.NAME AS NAME FROM SYS_DICT A ");
		sql.append("LEFT JOIN SYS_DICT_CATE B ON A.LISTNM_SYS_DICT_CATE=B.NM ");
		sql.append("WHERE B.CODE='"+code.toLowerCase()+"' ");
		sql.append("ORDER BY A.CODE");
		PageResults retValue =new PageResults();
		retValue=this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
		return retValue;
	}
	
	//根据实体属性及值获取实体
	public SysDict getByProp(String PropName,Object PropValue){
	   return  this.findByObject(PropName, PropValue);
	}
    
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findByIds(String ids){
		String  sql="SELECT ";
				sql=sql+" P.ID,P.NM,P.CODE,P.NAME,P.MEMO,P.FLAG,P.LISTNM_SYS_DICT_CATE,P.SYSFLAG ";
                sql=sql+" ,SYSDICTCATE.NAME AS A ";    
				sql=sql	+ " FROM SYS_DICT AS P  ";
				sql=sql	+ " LEFT JOIN SYS_DICT_CATE AS SYSDICTCATE ON  P.LISTNM_SYS_DICT_CATE= SYSDICTCATE.NM  ";
				sql=sql	+ " WHERE 1=1 ";    
					
		if (ids.length()>0){
				sql=sql+" AND P.ID IN ("+ids+")";
		}
			
        String sql_all=sql+" ORDER BY P.ID";
		
        PageResults retValue =new PageResults();
        retValue =this.findPageByFetchedSql(sql_all, null, 0, 100000000, null);
		return retValue;
	}	
	
	//字典管理审核
	public void updateSysDict(String idary,Integer flag_new){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_DICT SET FLAG="+flag_new+" WHERE ID='"+idary+"'");
		this.exectueSQL(sql.toString());
	}
	
	//字典管理删除
	public void deleteSysDict(Integer idary){
		StringBuilder sql=new StringBuilder();
		sql.append("delete from sys_dict where id="+idary);
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 获取字典信息数据
	 * */
    @SuppressWarnings("rawtypes")
	public List<Map> getDictInfo(){
    	StringBuilder sql=new StringBuilder();
    	sql.append("SELECT NM,CODE,NAME FROM SYS_DICT");
    	return this.createSQLQuerybyMap(sql.toString());
    }

}

package com.lyht.business.contracMng.dao;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContChange;
import com.lyht.business.contracMng.formbean.ContChangeFormBean;
import com.lyht.business.contracMng.formbean.ContInfoFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class ContChangeDao extends HibernateBaseDao<ContChange> {

	public PageResults queryDetailIds(String ids) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID FROM CONT_CHANGE A LEFT JOIN CONT_INFO B");
		sql.append(" ON A.CONTRACT_NO = B.CONTRACT_NO WHERE B.ID IN (");
		sql.append(ids);
		sql.append(")");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,999999999, null);
		return retValue;
	}

	// 查询合同物资变更记录
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults list(ContChangeFormBean contChangeFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT B.CONTRACT_NO AS CONTRACTNO,B.CONTRACT_TITLE AS CONTRACTTITLE,A.CODE,A.ID,");
		sql.append("A.ENGINEER_CODE AS ENGINEERCODE,A.SECTION,A.MATERIEL_CODE AS MATERIELCODE,C.NAME,E.UNIT,E.PRICE,");
		sql.append("A.MATERIEL_NAME AS MATERIELNAME,A.PRE_CHANGE_NUM AS PRECHANGENUM,A.AFTER_CHANGE_NUM AS AFTERCHANGENUM,");
		sql.append("A.PRE_CHANGE_PRICE AS PRECHANGEPRICE,A.AFTER_CHANGE_PRICE AS AFTERCHANGEPRICE,D.ENGINEER_NAME AS ENGINEERNAME,");
		sql.append("A.DIFFERENCE,A.DIFFERENCE_TAX AS DIFFERENCETAX,A.CHANGE_TIME AS CHANGETIME,A.OPERATOR,E.MATERIAL_NORMS AS MATERIALNORMS,");
		sql.append("A.MATERIAL_STATE AS MATERIALSTATE FROM CONT_CHANGE A LEFT JOIN CONT_INFO B");
		sql.append(" ON A.CONTRACT_NO = B.CONTRACT_NO LEFT JOIN SYS_STAFF C ON A.OPERATOR = C.CODE");
		sql.append(" LEFT JOIN PUB_ENGINEERING D ON A.ENGINEER_CODE = D.NM LEFT JOIN (SELECT F.PRICE,F.MATERIAL_NORMS,F.MATERIEL_CODE,I.NAME AS 'UNIT' FROM PUB_MATERIEL F LEFT JOIN (SELECT G.CODE,G.NAME FROM SYS_DICT G LEFT JOIN SYS_DICT_CATE H ON G.LISTNM_SYS_DICT_CATE = H.NM WHERE H.CODE = 'jldw') I ON F.UNIT = I.CODE) E");
		sql.append(" ON A.MATERIEL_CODE = E.MATERIEL_CODE WHERE 1=1");
		if (contChangeFormBean!=null){
			//字符串字段模糊匹配，
			if (CommonUtil.trim(contChangeFormBean.getSearchName()).length()>0){
				//合同编号
				sql.append(" AND (");
				sql.append(" B.CONTRACT_NO LIKE ? ");
				list.add("%"+CommonUtil.trim(contChangeFormBean.getSearchName())+"%");
				//合同标题
				sql.append(" OR B.CONTRACT_TITLE LIKE ? ");
				sql.append(")");
				list.add("%"+CommonUtil.trim(contChangeFormBean.getSearchName())+"%");
			}
			//合同编号查询条件
			if (CommonUtil.trim(contChangeFormBean.getContChangeBean().getContractNo()).length()>0){
				//合同编号
				sql.append("AND B.CONTRACT_NO = ? ");
				list.add(contChangeFormBean.getContChangeBean().getContractNo());
			}
			//查询单个变更记录明细
			if (CommonUtil.trim(contChangeFormBean.getEngineerNm()).length()>0){
				sql.append(" AND D.NM = ? ");
				list.add(CommonUtil.trim(contChangeFormBean.getEngineerNm()));
			}
			//查询单个变更记录明细
			if (CommonUtil.trim(contChangeFormBean.getContChangeBean().getId()).length()>0){
				sql.append(" AND A.ID = ? ");
				list.add(CommonUtil.trim(contChangeFormBean.getContChangeBean().getId()));
			}
			//排序
			if (CommonUtil.trim(contChangeFormBean.getPageBean().getSort()).length()>0){
				if (CommonUtil.trim(contChangeFormBean.getPageBean().getSortOrder()).length()>0){
					if("CHANGETIME".equals(CommonUtil.trim(contChangeFormBean.getPageBean().getSort()))){
						sql.append(" ORDER BY A.CHANGE_TIME");
						sql.append(" ");
						sql.append(CommonUtil.trim(contChangeFormBean.getPageBean().getSortOrder()));
					}
				}
			}
		}
		PageResults retValue = new PageResults();
		int pageNo = 1;
		if(contChangeFormBean.getPageBean().getLimit() != 0){
			pageNo = contChangeFormBean.getPageBean().getOffset()/contChangeFormBean.getPageBean().getLimit() + 1;
		}
		retValue = this.findPageByFetchedSql(sql.toString(), null, pageNo,
				contChangeFormBean.getPageBean().getLimit(), list.toArray());
		return retValue;
	}

	public PageResults delChangeByMaterialIds(String ids) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CONT_CHANGE SET MATERIAL_STATE = 2 WHERE CODE IN (SELECT A.CODE FROM CONT_DETAIL A WHERE A.ID IN (");
		sql.append(ids);
		sql.append("))");
		PageResults retValue = new PageResults();
		this.exectueSQL(sql.toString());
		return retValue;
	}

	public void delContChangeByContIds(String ids) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CONT_CHANGE SET MATERIAL_STATE = 2 WHERE contract_no in (select a.contract_no from cont_info a WHERE a.id in (");
		sql.append(ids);
		sql.append("))");
		PageResults retValue = new PageResults();
		this.exectueSQL(sql.toString());
	}
}

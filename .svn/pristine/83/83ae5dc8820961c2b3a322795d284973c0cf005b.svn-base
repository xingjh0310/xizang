package com.lyht.business.contracMng.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContDetail;
import com.lyht.business.contracMng.formbean.ContDetailFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class ContDetailDao extends HibernateBaseDao<ContDetail> {

	public PageResults queryContDetailByContNO(String contractNo) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID,A.ENGINEER_CODE AS ENGINEERCODE,A.CONTRACT_NO AS CONTRACTNO,A.SECTION,A.MATERIEL_CODE AS MATERIELCODE,");
		sql.append("A.MATERIEL_NAME AS MATERIELNAME,A.MATERIEL_NUM AS MATERIELNUM,A.MATERIEL_UNIT AS MATERIELUNIT,B.PRICE,");
		sql.append("A.GOOD_DESC AS GOODDESC,A.PROPOSAL_PRICE AS PROPOSALPRICE,A.CODE,B.MATERIAL_NORMS AS MATERIALNORMS FROM CONT_DETAIL A LEFT JOIN PUB_MATERIEL B");
		sql.append(" ON A.MATERIEL_CODE = B.MATERIEL_CODE WHERE 1=1");
		sql.append(" AND CONTRACT_NO = ?");
		list.add(contractNo);
		sql.append(" ORDER BY ID ASC");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,999999999, list.toArray());
		return retValue;
	}

	public PageResults queryDetailIds(String ids) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID FROM CONT_DETAIL A LEFT JOIN CONT_INFO B");
		sql.append(" ON A.CONTRACT_NO = B.CONTRACT_NO WHERE B.ID IN (");
		sql.append(ids);
		sql.append(")");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,999999999, null);
		return retValue;
	}

	public PageResults getMaterialById_app(ContDetailFormBean contDetailFormBean) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID,A.CODE,B.UNIT,CONVERT(DECIMAL(18,2),B.PRICE) AS PRICE,B.MATERIAL_NORMS,A.MATERIEL_NUM,A.CONTRACT_NO,A.ENGINEER_CODE,A.SECTION,A.MATERIEL_CODE,A.MATERIEL_NAME,");
		sql.append("CONVERT(DECIMAL(18,2),A.PROPOSAL_PRICE) AS PROPOSAL_PRICE,A.GOOD_DESC FROM CONT_DETAIL A LEFT JOIN (SELECT F.PRICE,F.MATERIAL_NORMS,F.MATERIEL_CODE,I.NAME AS 'UNIT' FROM PUB_MATERIEL F LEFT JOIN (SELECT G.CODE,G.NAME FROM SYS_DICT G LEFT JOIN SYS_DICT_CATE H ON G.LISTNM_SYS_DICT_CATE = H.NM WHERE H.CODE = 'jldw') I ON F.UNIT = I.CODE) B ON A.MATERIEL_CODE = B.MATERIEL_CODE WHERE 1=1");
		if(CommonUtil.trim(contDetailFormBean.getContDetailBean().getId()).length()>0){
			sql.append("AND A.ID = ?");
			list.add(CommonUtil.trim(contDetailFormBean.getContDetailBean().getId()));
		}
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1, list.toArray());
		return retValue;
	}

	public Object getMaterialByContNoAndMatCode_app(ContDetailFormBean contDetailFormBean) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID,A.CODE,A.MATERIEL_NUM,A.CONTRACT_NO,A.MATERIEL_UNIT,A.ENGINEER_CODE,A.SECTION,A.MATERIEL_CODE,A.MATERIEL_NAME,");
		sql.append("A.PROPOSAL_PRICE,A.GOOD_DESC,B.MATERIAL_NORMS FROM CONT_DETAIL A LEFT JOIN pub_materiel B ON A.MATERIEL_CODE = B.MATERIEL_CODE WHERE 1=1");
		if(CommonUtil.trim(contDetailFormBean.getContractNo()).length()>0){
			sql.append(" AND A.CONTRACT_NO = ?");
			list.add(CommonUtil.trim(contDetailFormBean.getContractNo()));
		}
		if(CommonUtil.trim(contDetailFormBean.getMaterialCode()).length()>0){
			sql.append(" AND A.MATERIEL_CODE = ?");
			list.add(CommonUtil.trim(contDetailFormBean.getMaterialCode()));
		}
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1, list.toArray());
		return retValue;
	}
}

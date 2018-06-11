package com.lyht.business.evaluate.dao;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.formbean.ContInfoFormBean;
import com.lyht.business.evaluate.bean.ContEvaluate;
import com.lyht.business.evaluate.formBean.ContEvaluateFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class ContEvaluateDao extends HibernateBaseDao<ContEvaluate> {
	// 查询履约评价列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults listContEvaluate(ContEvaluateFormBean contEvaluateFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID,A.ENGINEER_CODE AS ENGINEERCODE,A.CONTRACT_NO AS CONTRACTNO,A.EVALUATION,A.MATERIAL_ARRIVAL AS MATERIALARRIVAL,A.PRODUCT_QUALITY AS PRODUCTQUALITY,");
		sql.append("A.FIELD_SERVICE AS FIELDSERVICE,A.MATERIAL_OPERATION AS MATERIALOPERATION,A.WARRANTY_SITUATION AS WARRANTYSITUATION,A.REMARK AS REMARK,B.CONTRACT_TITLE AS CONTRACTTITLE,");
		sql.append("C.ENGINEER_NAME AS ENGINEERNAME,E.NUB AS ACCESSORY FROM CONT_EVALUATE A LEFT JOIN CONT_INFO B ON A.CONTRACT_NO = B.CONTRACT_NO LEFT JOIN PUB_ENGINEERING C ON A.ENGINEER_CODE = C.NM");
		sql.append(" LEFT JOIN (SELECT COUNT(TABLE_PK_COLUMN) NUB,TABLE_PK_COLUMN FROM PUB_FILES WHERE TABLE_NAME = 'CONT_EVALUATE' GROUP BY TABLE_PK_COLUMN) E ON A.ID = E.TABLE_PK_COLUMN WHERE 1=1");
		
		if (contEvaluateFormBean!=null){
			//字符串字段模糊匹配，
			if (CommonUtil.trim(contEvaluateFormBean.getSearchName()).length()>0){
				//合同编号
				sql.append(" AND (");
				sql.append(" B.CONTRACT_NO LIKE ? ");
				list.add("%"+CommonUtil.trim(contEvaluateFormBean.getSearchName())+"%");
				//合同标题
				sql.append("OR B.CONTRACT_TITLE LIKE ? ");
				list.add("%"+CommonUtil.trim(contEvaluateFormBean.getSearchName())+"%");
				//工程名称
				sql.append("OR C.ENGINEER_NAME LIKE ? ");
				list.add("%"+CommonUtil.trim(contEvaluateFormBean.getSearchName())+"%");
				sql.append(")");
			}
			//查询单个合同详细信息弹窗时
			if (CommonUtil.trim(contEvaluateFormBean.getContEvaluateBean().getId()).length()>0){
				sql.append(" AND A.ID = ? ");
				list.add(CommonUtil.trim(contEvaluateFormBean.getContEvaluateBean().getId()));
			}
		}
		//排序
//		if (CommonUtil.trim(contEvaluateFormBean.getPageBean().getSort()).length()>0){
//			if (CommonUtil.trim(contInfoFormBean.getPageBean().getSortOrder()).length()>0){
//				if("CONTRACTNO".equals(CommonUtil.trim(contInfoFormBean.getPageBean().getSort()))){
//					sql.append(" ORDER BY A.CONTRACT_NO");
//				}else if("SIGNDATE".equals(CommonUtil.trim(contInfoFormBean.getPageBean().getSort()))){
//					sql.append(" ORDER BY A.SIGN_DATE");
//				}else {
//					sql.append(" ORDER BY A.ID");
//				}
//				sql.append(" ");
//				sql.append(CommonUtil.trim(contInfoFormBean.getPageBean().getSortOrder()));
//			}
//		}
		PageResults retValue = new PageResults();
		int pageNo = 1;
		if(contEvaluateFormBean.getPageBean().getLimit() != 0){
			pageNo = contEvaluateFormBean.getPageBean().getOffset()/contEvaluateFormBean.getPageBean().getLimit() + 1;
		}
		retValue = this.findPageByFetchedSql(sql.toString(), null, pageNo,
				contEvaluateFormBean.getPageBean().getLimit(), list.toArray());
		return retValue;
	}
	
	//添加或修改履约评价时查询数据
	@SuppressWarnings("rawtypes")
	public PageResults editEvaluate(ContEvaluateFormBean contEvaluateFormBean) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID AS CONTID,A.ENGINEER_CODE AS ENGINEERCODE,A.CONTRACT_NO AS CONTRACTNO,A.CONTRACT_TITLE AS CONTRACTTITLE,B.ID,B.EVALUATION,B.MATERIAL_ARRIVAL AS MATERIALARRIVAL,");
		sql.append("B.PRODUCT_QUALITY AS PRODUCTQUALITY,B.FIELD_SERVICE AS FIELDSERVICE,B.MATERIAL_OPERATION AS MATERIALOPERATION,B.WARRANTY_SITUATION AS WARRANTYSITUATION,B.REMARK");
		sql.append(" FROM CONT_INFO A LEFT JOIN CONT_EVALUATE B ON A.CONTRACT_NO = B.CONTRACT_NO WHERE 1=1");
		
		if (CommonUtil.trim(contEvaluateFormBean.getContEvaluateBean().getId()).length()>0){
			sql.append(" AND A.ID = ?");
			list.add(CommonUtil.trim(contEvaluateFormBean.getContEvaluateBean().getId()));
		}
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,100000000, list.toArray());
		return retValue;
	}

	public PageResults queryEvaluateIds(String ids) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID FROM CONT_EVALUATE A LEFT JOIN CONT_INFO B ON A.CONTRACT_NO = B.CONTRACT_NO WHERE 1=1");
		sql.append(" AND B.ID IN (");
		sql.append(ids);
		sql.append(")");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,100000000, null);
		return retValue;
	}
}

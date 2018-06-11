package com.lyht.business.contracMng.dao;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContInfo;
import com.lyht.business.contracMng.formbean.ContInfoFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class ContInfoDao extends HibernateBaseDao<ContInfo> {
	// 查询合同列表
	@SuppressWarnings({ "unchecked" })
	public PageResults listContract(ContInfoFormBean contInfoFormBean,SysDept dept) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID,A.ENGINEER_CODE AS ENGINEERCODE,A.SECTION AS SECTION,A.CONTRACT_NO AS CONTRACTNO,F.EVALUATION,F.MATERIAL_ARRIVAL AS MATERIALARRIVAL,F.PRODUCT_QUALITY AS PRODUCTQUALITY,");
		sql.append("A.CONTRACT_TITLE AS CONTRACTTITLE,A.SUPPLY AS SUPPLY,A.CONTRACT_AMOUNT AS CONTRACTAMOUNT,F.FIELD_SERVICE AS FIELDSERVICE,F.MATERIAL_OPERATION AS MATERIALOPERATION,F.WARRANTY_SITUATION AS WARRANTYSITUATION,");
		sql.append("A.BIDDE_DATE AS BIDDEDATE,A.SUPPLY_START_DATE AS SUPPLYSTARTDATE,A.SUPPLY_END_DATE AS SUPPLYENDDATE,F.ID AS EVASTATE,");
		sql.append("A.SIGN_DATE AS SIGNDATE,A.CONTRACT_START_DATE AS CONTRACTSTARTDATE,A.CONTRACT_END_DATE AS CONTRACTENDDATE,F.REMARK AS EVAREMARK,");
		sql.append("A.CONTRACT_STATE AS CONTRACTSTATE,A.DEMAND_UNIT AS DEMANDUNIT,B.ENGINEER_NAME AS ENGINEERNAME,C.SUPPLY_FULL_NAME AS SUPPLYFULLNAME,");
		sql.append(" A.REMARK,E.NUB AS ACCESSORY,G.NUB_E AS ACCESSORY_E FROM CONT_INFO A LEFT JOIN PUB_ENGINEERING B ON A.ENGINEER_CODE = B.NM");
		sql.append(" LEFT JOIN PUB_SUPPLIER C ON A.SUPPLY=C.SUPPLIER_CODE ");
		sql.append(" LEFT JOIN (SELECT COUNT(TABLE_PK_COLUMN) NUB,TABLE_PK_COLUMN FROM PUB_FILES WHERE TABLE_NAME = 'CONT_INFO' GROUP BY TABLE_PK_COLUMN) E ON A.ID = E.TABLE_PK_COLUMN LEFT JOIN CONT_EVALUATE F ON A.CONTRACT_NO = F.CONTRACT_NO");
		sql.append(" LEFT JOIN (SELECT COUNT(TABLE_PK_COLUMN) NUB_E,TABLE_PK_COLUMN FROM PUB_FILES WHERE TABLE_NAME = 'CONT_EVALUATE' GROUP BY TABLE_PK_COLUMN) G ON F.ID = G.TABLE_PK_COLUMN");
		sql.append(" WHERE 1=1");
		if(dept!=null){
			if(CommonUtil.trim( dept.getType()).equals("supply_unit")){
				
				if(dept.getNm()!=null||!"".equals(dept.getNm())){
					
					sql.append(" AND A.SUPPLY ='"+dept.getNm()+"'");
				}
			
			}
			
		}
		if (contInfoFormBean!=null){
			//字符串字段模糊匹配，
			if (CommonUtil.trim(contInfoFormBean.getSearchName()).length()>0){
				//合同编号
				sql.append(" AND (");
				sql.append(" A.CONTRACT_NO LIKE ? ");
				list.add("%"+CommonUtil.trim(contInfoFormBean.getSearchName())+"%");
				//合同标题
				sql.append("OR A.CONTRACT_TITLE LIKE ? ");
				list.add("%"+CommonUtil.trim(contInfoFormBean.getSearchName())+"%");
				//工程名称
				sql.append("OR B.ENGINEER_NAME LIKE ? ");
				list.add("%"+CommonUtil.trim(contInfoFormBean.getSearchName())+"%");
				sql.append(")");
			}
			//如果是变更合同页面,就不显示已评价的合同
			if (CommonUtil.trim(contInfoFormBean.getType()).length()>0
				&& "change".equals(CommonUtil.trim(contInfoFormBean.getType()))){
				sql.append(" AND F.ID IS NULL");
			}
			if (CommonUtil.trim(contInfoFormBean.getSupply()).length()>0){
				//供货厂商
				sql.append(" AND A.SUPPLY = ? ");
				list.add(CommonUtil.trim(contInfoFormBean.getSupply()));
			}
			//当前工程
			if (CommonUtil.trim(contInfoFormBean.getContInfoBean().getEngineerCode()).length()>0){
				sql.append(" AND B.NM = ? ");
				list.add(CommonUtil.trim(contInfoFormBean.getContInfoBean().getEngineerCode()));
			}
			//根据ID查询单个合同详细信息弹窗时
			if (CommonUtil.trim(contInfoFormBean.getContInfoBean().getId()).length()>0){
				sql.append(" AND A.ID = ? ");
				list.add(CommonUtil.trim(contInfoFormBean.getContInfoBean().getId()));
			}
			//根据合同编号查询单个合同详细信息弹窗时
			if (CommonUtil.trim(contInfoFormBean.getContInfoBean().getContractNo()).length()>0){
				sql.append(" AND A.CONTRACT_NO = ? ");
				list.add(CommonUtil.trim(contInfoFormBean.getContInfoBean().getContractNo()));
			}
		}
		//排序
		if (CommonUtil.trim(contInfoFormBean.getPageBean().getSort()).length()>0){
			if (CommonUtil.trim(contInfoFormBean.getPageBean().getSortOrder()).length()>0){
				if("CONTRACTNO".equals(CommonUtil.trim(contInfoFormBean.getPageBean().getSort()))){
					sql.append(" ORDER BY A.CONTRACT_NO");
				}else if("SIGNDATE".equals(CommonUtil.trim(contInfoFormBean.getPageBean().getSort()))){
					sql.append(" ORDER BY A.SIGN_DATE");
				}else {
					sql.append(" ORDER BY A.ID");
				}
				sql.append(" ");
				sql.append(CommonUtil.trim(contInfoFormBean.getPageBean().getSortOrder()));
			}
		}
		PageResults retValue = new PageResults();
		int pageNo = 1;
		if(contInfoFormBean.getPageBean().getLimit() != 0){
			pageNo = contInfoFormBean.getPageBean().getOffset()/contInfoFormBean.getPageBean().getLimit() + 1;
		}
		retValue = this.findPageByFetchedSql(sql.toString(), null, pageNo,
				contInfoFormBean.getPageBean().getLimit(), list.toArray());
		return retValue;
	}
	
	// 查询合同列表
	@SuppressWarnings({  "unchecked" })
	public PageResults listContract_export(ContInfoFormBean contInfoFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID,A.ENGINEER_CODE AS ENGINEERCODE,A.SECTION AS SECTION,A.CONTRACT_NO AS CONTRACTNO,F.EVALUATION,F.MATERIAL_ARRIVAL AS MATERIALARRIVAL,F.PRODUCT_QUALITY AS PRODUCTQUALITY,");
		sql.append("A.CONTRACT_TITLE AS CONTRACTTITLE,A.SUPPLY AS SUPPLY,A.CONTRACT_AMOUNT AS CONTRACTAMOUNT,F.FIELD_SERVICE AS FIELDSERVICE,F.MATERIAL_OPERATION AS MATERIALOPERATION,F.WARRANTY_SITUATION AS WARRANTYSITUATION,");
		sql.append("A.BIDDE_DATE AS BIDDEDATE,A.SUPPLY_START_DATE AS SUPPLYSTARTDATE,A.SUPPLY_END_DATE AS SUPPLYENDDATE,CASE WHEN F.ID IS NOT NULL THEN '已评价' ELSE '未评价' END AS EVASTATE ,");
		sql.append("A.SIGN_DATE AS SIGNDATE,A.CONTRACT_START_DATE AS CONTRACTSTARTDATE,A.CONTRACT_END_DATE AS CONTRACTENDDATE,F.REMARK AS EVAREMARK,");
		sql.append("A.CONTRACT_STATE AS CONTRACTSTATE,A.DEMAND_UNIT AS DEMANDUNIT,B.ENGINEER_NAME AS ENGINEERNAME,C.SUPPLY_FULL_NAME AS SUPPLYFULLNAME,");
		sql.append(" A.REMARK,E.NUB AS ACCESSORY FROM CONT_INFO A LEFT JOIN PUB_ENGINEERING B ON A.ENGINEER_CODE = B.NM");
		sql.append(" LEFT JOIN PUB_SUPPLIER C ON A.SUPPLY=C.SUPPLIER_CODE ");
		sql.append(" LEFT JOIN (SELECT COUNT(TABLE_PK_COLUMN) NUB,TABLE_PK_COLUMN FROM PUB_FILES WHERE TABLE_NAME = 'CONT_INFO' GROUP BY TABLE_PK_COLUMN) E");
		sql.append(" ON A.ID = E.TABLE_PK_COLUMN LEFT JOIN CONT_EVALUATE F ON A.CONTRACT_NO = F.CONTRACT_NO WHERE 1=1");
		if (contInfoFormBean!=null){
			//字符串字段模糊匹配，
			if (CommonUtil.trim(contInfoFormBean.getSearchName()).length()>0){
				//合同编号
				sql.append(" AND (");
				sql.append(" A.CONTRACT_NO LIKE ? ");
				list.add("%"+CommonUtil.trim(contInfoFormBean.getSearchName())+"%");
				//合同标题
				sql.append("OR A.CONTRACT_TITLE LIKE ? ");
				list.add("%"+CommonUtil.trim(contInfoFormBean.getSearchName())+"%");
				//工程名称
				sql.append("OR B.ENGINEER_NAME LIKE ? ");
				list.add("%"+CommonUtil.trim(contInfoFormBean.getSearchName())+"%");
				sql.append(")");
			}
			if (CommonUtil.trim(contInfoFormBean.getSupply()).length()>0){
				//供货厂商
				sql.append(" AND A.SUPPLY = ? ");
				list.add(CommonUtil.trim(contInfoFormBean.getSupply()));
			}
			//根据ID查询单个合同详细信息弹窗时
			if (CommonUtil.trim(contInfoFormBean.getContInfoBean().getId()).length()>0){
				sql.append(" AND A.ID = ? ");
				list.add(CommonUtil.trim(contInfoFormBean.getContInfoBean().getId()));
			}
			//根据合同编号查询单个合同详细信息弹窗时
			if (CommonUtil.trim(contInfoFormBean.getContInfoBean().getContractNo()).length()>0){
				sql.append(" AND A.CONTRACT_NO = ? ");
				list.add(CommonUtil.trim(contInfoFormBean.getContInfoBean().getContractNo()));
			}
			//当前工程
			if (CommonUtil.trim(contInfoFormBean.getEngineerNm()).length()>0){
				sql.append(" AND A.ENGINEER_CODE = ? ");
				sql.append(" AND F.ID IS NULL ");
				list.add(CommonUtil.trim(contInfoFormBean.getEngineerNm()));
			}
		}
		//排序
		if (CommonUtil.trim(contInfoFormBean.getPageBean().getSort()).length()>0){
			if (CommonUtil.trim(contInfoFormBean.getPageBean().getSortOrder()).length()>0){
				if("CONTRACTNO".equals(CommonUtil.trim(contInfoFormBean.getPageBean().getSort()))){
					sql.append(" ORDER BY A.CONTRACT_NO");
				}else if("SIGNDATE".equals(CommonUtil.trim(contInfoFormBean.getPageBean().getSort()))){
					sql.append(" ORDER BY A.SIGN_DATE");
				}else {
					sql.append(" ORDER BY A.ID");
				}
				sql.append(" ");
				sql.append(CommonUtil.trim(contInfoFormBean.getPageBean().getSortOrder()));
			}
		}
		PageResults retValue = new PageResults();
		int pageNo = 1;
		if(contInfoFormBean.getPageBean().getLimit() != 0){
			pageNo = contInfoFormBean.getPageBean().getOffset()/contInfoFormBean.getPageBean().getLimit() + 1;
		}
		retValue = this.findPageByFetchedSql(sql.toString(), null, pageNo,
				contInfoFormBean.getPageBean().getLimit(), list.toArray());
		return retValue;
	}

	//查询供应商下拉数据
	public PageResults queryAllsupply(ContInfoFormBean contInfoFormBean) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUPPLIER_CODE,SUPPLY_FULL_NAME FROM PUB_SUPPLIER");
		sql.append(" ORDER BY ID ASC");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,100000000, null);
		return retValue;
	}

	//查询工程信息的下拉数据
	public PageResults queryAllEngineer(ContInfoFormBean contInfoFormBean,String engineerNm) {
		String engineerNm_=handleData(engineerNm);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NM,ENGINEER_NAME AS 'name',PCODE,THISCODE FROM PUB_ENGINEERING WHERE 1=1");
		if(null!=contInfoFormBean){
			if(CommonUtil.trim(contInfoFormBean.getSearchName()).length()>0 ){
				sql.append(" AND ENGINEER_NAME LIKE '%"+CommonUtil.trim(contInfoFormBean.getSearchName())+"%'");
			}
			if(CommonUtil.trim(engineerNm_).length()>0){
				sql.append(" AND NM IN ("+engineerNm_+") ");
			}
		}
		sql.append(" ORDER BY THISCODE ASC");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,100000000, null);
		return retValue;
	}
	
	private String handleData(String engineerNm){
		String str="";
		String []engineerNm_=engineerNm.split(",");
		for(int i=0;i<engineerNm_.length;i++){
			str+="'"+engineerNm_[i]+"'"+",";
		}
		if(",".equals(str.substring(str.length()-1))){
			str=str.substring(0,str.length()-1);
		}
		return str;
	}

	public PageResults checkContractNo(String match) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CONTRACT_NO AS CONTRACTNO FROM CONT_INFO WHERE 1=1 ");
		sql.append(" AND CONTRACT_NO LIKE '" + match + "%'");
		sql.append(" ORDER BY CONTRACT_NO DESC");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,10, null);
		return retValue;
	}

	@SuppressWarnings("unchecked")
	public PageResults getMaterialInfo(ContInfoFormBean contInfoFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID,A.MATERIEL_CODE,A.MATERIEL_NAME,D.NAME AS 'UNIT',A.MATERIAL_NORMS,A.PRICE FROM PUB_MATERIEL A LEFT JOIN ");
		sql.append("(SELECT B.CODE,B.NAME FROM SYS_DICT B LEFT JOIN SYS_DICT_CATE C ON B.LISTNM_SYS_DICT_CATE = C.NM WHERE C.CODE = 'jldw') D ON A.UNIT = D.CODE WHERE 1 = 1");
		if (CommonUtil.trim(contInfoFormBean.getIds()).length()>0){
			sql.append(" AND A.MATERIEL_CODE = ?");
			list.add(CommonUtil.trim(contInfoFormBean.getIds()));
		}
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1, list.toArray());
		return retValue;
	}
	
	public PageResults getMaterialInfoById(ContInfoFormBean contInfoFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.ID,A.MATERIEL_CODE,A.MATERIEL_NAME,D.NAME AS 'UNIT',A.MATERIAL_NORMS,A.PRICE FROM PUB_MATERIEL A LEFT JOIN ");
		sql.append("(SELECT B.CODE,B.NAME FROM SYS_DICT B LEFT JOIN SYS_DICT_CATE C ON B.LISTNM_SYS_DICT_CATE = C.NM WHERE C.CODE = 'jldw') D ON A.UNIT = D.CODE WHERE 1 = 1");
		if (CommonUtil.trim(contInfoFormBean.getIds()).length()>0){
			sql.append(" AND A.ID = ?");
			list.add(CommonUtil.trim(contInfoFormBean.getIds()));
		}
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1, list.toArray());
		return retValue;
	}

	public PageResults queryAllMaterial(ContInfoFormBean contInfoFormBean) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID,MATERIEL_CODE AS MATERIELCODE,MATERIEL_NAME AS MATERIELNAME,MATERIAL_NORMS AS MATERIALNORMS,UNIT FROM PUB_MATERIEL");
		sql.append(" ORDER BY ID ASC");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,100000000, null);
		return retValue;
	}

	public PageResults listContract_app(ContInfoFormBean contInfoFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT C.ID,CASE WHEN C.CONTRACT_STATE = 1 THEN '未发货' WHEN C.CONTRACT_STATE = 2 THEN '已发货' END AS CONTRACT_STATE,C.CONTRACT_NO,C.CONTRACT_TITLE,B.MATERIEL_NAME,B.MATERIEL_NUM,B.MATERIEL_UNIT,B.PROPOSAL_PRICE,B.TOTAL,D.SUPPLY_FULL_NAME,C.SIGN_DATE,C.CONTRACT_AMOUNT,C.BIDDE_DATE,E.EVALUATION,C.CONTRACT_START_DATE,C.CONTRACT_END_DATE,");
		sql.append("C.SECTION,C.ENGINEER_CODE,F.ENGINEER_NAME,E.MATERIAL_ARRIVAL,E.PRODUCT_QUALITY,E.FIELD_SERVICE,E.MATERIAL_OPERATION,E.WARRANTY_SITUATION,E.REMARK,E.ID AS EVALUATEID FROM CONT_INFO C LEFT JOIN ");
		sql.append("(SELECT CONTRACT_NO,MATERIEL_NAME = (STUFF((SELECT ',' + MATERIEL_NAME FROM CONT_DETAIL WHERE CONTRACT_NO = A.CONTRACT_NO FOR XML PATH ('')),1,1,'')),");
		sql.append("MATERIEL_NUM = (STUFF((SELECT ',' + CONVERT(VARCHAR,MATERIEL_NUM) FROM CONT_DETAIL WHERE CONTRACT_NO = A.CONTRACT_NO FOR XML PATH ('')),1,1,'')),");
		sql.append("PROPOSAL_PRICE = (STUFF((SELECT ',' + CONVERT(VARCHAR,PROPOSAL_PRICE) FROM CONT_DETAIL WHERE CONTRACT_NO = A.CONTRACT_NO FOR XML PATH ('')),1,1,'')),");
		sql.append("MATERIEL_UNIT = (STUFF((SELECT ',' + MATERIEL_UNIT FROM CONT_DETAIL WHERE CONTRACT_NO = A.CONTRACT_NO FOR XML PATH ('')),1,1,'')),SUM(A.MATERIEL_NUM) AS 'TOTAL'");
		sql.append("FROM CONT_DETAIL A GROUP BY CONTRACT_NO) B ON C.CONTRACT_NO = B.CONTRACT_NO LEFT JOIN PUB_SUPPLIER D ON C.SUPPLY = D.SUPPLIER_CODE");
		sql.append(" LEFT JOIN CONT_EVALUATE E ON C.CONTRACT_NO = E.CONTRACT_NO LEFT JOIN PUB_ENGINEERING F ON C.ENGINEER_CODE = F.NM WHERE 1=1");
		
		if (contInfoFormBean!=null){
			//字符串字段模糊匹配
			if (CommonUtil.trim(contInfoFormBean.getSearchName()).length()>0
				&& !"undefined".equals(CommonUtil.trim(contInfoFormBean.getSearchName()))){
				//合同编号
				sql.append(" AND (");
				sql.append(" F.ENGINEER_NAME LIKE ? ");
				list.add("%"+CommonUtil.trim(contInfoFormBean.getSearchName())+"%");
				sql.append(")");
			}
			if (CommonUtil.trim(contInfoFormBean.getEngineerNm()).length()>0 && !"0".equals(CommonUtil.trim(contInfoFormBean.getEngineerNm()))){
				//所属工程
				sql.append(" AND C.ENGINEER_CODE = ? ");
				list.add(CommonUtil.trim(contInfoFormBean.getEngineerNm()));
			}
			//根据ID查询单个合同详细信息
			if (CommonUtil.trim(contInfoFormBean.getContInfoBean().getId()).length()>0){
				sql.append(" AND C.ID = ? ");
				list.add(CommonUtil.trim(contInfoFormBean.getContInfoBean().getId()));
			}
			//根据合同编号查询单个合同详细信息弹窗时
			if (CommonUtil.trim(contInfoFormBean.getContInfoBean().getContractNo()).length()>0){
				sql.append(" AND C.CONTRACT_NO = ? ");
				list.add(CommonUtil.trim(contInfoFormBean.getContInfoBean().getContractNo()));
			}
		}
		sql.append(" ORDER BY C.SIGN_DATE DESC");
		PageResults retValue = new PageResults();
		int pageNo = 1;
		if(contInfoFormBean.getPageBean().getLimit() != 0){
			pageNo = contInfoFormBean.getPageBean().getOffset()/contInfoFormBean.getPageBean().getLimit() + 1;
		}
		retValue = this.findPageByFetchedSql(sql.toString(), null, pageNo,
			contInfoFormBean.getPageBean().getLimit(), list.toArray());
		return retValue;
	}

	public Object queryContractNub(ContInfoFormBean contInfoFormBean) {
		StringBuilder sql = new StringBuilder();
		ArrayList list = new ArrayList();
		sql.append("SELECT COUNT(*) AS CONTRACTNUB FROM CONT_INFO WHERE 1=1");
		if (CommonUtil.trim(contInfoFormBean.getEngineerNm()).length()>0){
			sql.append(" AND ENGINEER_CODE = ? ");
			list.add(CommonUtil.trim(contInfoFormBean.getEngineerNm()));
		}
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,100000000, list.toArray());
		return retValue;
	}

	public Object queryConTotalAmount(ContInfoFormBean contInfoFormBean) {
		StringBuilder sql = new StringBuilder();
		ArrayList list = new ArrayList();
		sql.append("SELECT SUM(CASE WHEN CONTRACT_AMOUNT != '' THEN CONVERT(DECIMAL(19,2),CONTRACT_AMOUNT) ELSE 0 END) AS CONTRACTAMOUNT FROM CONT_INFO WHERE 1=1");
		if (CommonUtil.trim(contInfoFormBean.getEngineerNm()).length()>0){
			sql.append(" AND ENGINEER_CODE = ? ");
			list.add(CommonUtil.trim(contInfoFormBean.getEngineerNm()));
		}
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,100000000, list.toArray());
		return retValue;
	}
}

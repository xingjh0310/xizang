package com.lyht.business.materiel.dao;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.bean.MaterielBase;
import com.lyht.business.materiel.formBean.MaterielBaseFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class MaterielBaseDao extends HibernateBaseDao<MaterielBase> {

	// 物料基础信息列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults list(MaterielBaseFormBean materielBaseFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT M.ID AS ID,M.MATERIEL_CODE AS MATERIELCODE,M.MATERIEL_NAME AS MATERIELNAME,");
		sql.append("M.CODE AS CODE,M.MATERIEL_DESC AS MATERIELDESC,M.MATERIAL_NORMS AS MATERIALNORMS,");
		sql.append("Q.NAME AS UNIT,M.MATERIAL_GROUP AS MATERIALGROUP,M.MATERIEL_CLASSIFY AS MATERIELCLASSIFY,");
		sql.append("M.UNIT_DESC AS UNITDESC,M.STATE AS STATE,M.REMARK AS REMARK,");
		sql.append("M.PRICE AS PRICE");
		sql.append(" FROM PUB_MATERIEL AS M ");
		sql.append(" LEFT JOIN (SELECT d.name,d.code FROM sys_dict AS d LEFT JOIN sys_dict_cate AS c ON d.listnm_sys_dict_cate = c.nm WHERE c.code = 'jldw') AS q ON m.unit = q.code ");
		sql.append(" WHERE 1=1 ");
		String group = materielBaseFormBean.getMaterielBase().getMaterialGroup();
		if(materielBaseFormBean.getMaterielBase().getMaterialGroup()!=null && !"".equals(materielBaseFormBean.getMaterielBase().getMaterialGroup())){
			
			sql.append(" AND MATERIAL_GROUP in("+group.substring(0,group.length()-1)+")");
		}else{
			
			sql.append(" AND MATERIAL_GROUP in('') ");
		}
		
		if (materielBaseFormBean != null) {
			// 字符串字段模糊匹配，
			if (CommonUtil.trim(materielBaseFormBean.getSearchName()).length() > 0) {

				sql.append("AND M.MATERIEL_NAME LIKE ? ");
				list.add("%" + CommonUtil.trim(materielBaseFormBean.getSearchName()) + "%");

				sql.append("OR M.MATERIAL_NORMS LIKE ? ");
				list.add("%" + CommonUtil.trim(materielBaseFormBean.getSearchName()) + "%");

				sql.append("OR Q.NAME LIKE ? ");
				list.add("%" + CommonUtil.trim(materielBaseFormBean.getSearchName()) + "%");

				sql.append("OR M.MATERIAL_GROUP LIKE ? ");
				list.add("%" + CommonUtil.trim(materielBaseFormBean.getSearchName()) + "%");

				sql.append("OR M.MATERIEL_CLASSIFY LIKE ? ");
				list.add("%" + CommonUtil.trim(materielBaseFormBean.getSearchName()) + "%");
			}
			
			//计量单位
			if(materielBaseFormBean.getMaterielBase().getUnit()!=null&&!"".equals(materielBaseFormBean.getMaterielBase().getUnit())){
				sql.append(" AND Q.CODE ='"+materielBaseFormBean.getMaterielBase().getUnit()+"'");
			}
		}
		sql.append(" ORDER BY ID " + materielBaseFormBean.getPageBean().getSortOrder());
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, materielBaseFormBean.getPageBean().getOffset(),
				materielBaseFormBean.getPageBean().getLimit(), list.toArray());
		return retValue;
	}
	// 物料基础信息列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults all_list(MaterielBaseFormBean materielBaseFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID AS ID,MATERIEL_CODE AS MATERIELCODE,MATERIEL_NAME AS MATERIELNAME,");
		sql.append("CODE AS CODE,MATERIAL_NORMS AS MATERIALNORMS,UNIT AS UNIT,PRICE AS PRICE,");
		sql.append("MATERIEL_DESC AS MATERIELDESC,MATERIAL_GROUP AS MATERIALGROUP,");
		sql.append("MATERIEL_CLASSIFY AS MATERIELCLASSIFY,UNIT_DESC AS UNITDESC,");
		sql.append("STATE AS STATE,REMARK AS REMARK");
		sql.append(" FROM PUB_MATERIEL WHERE 1 = 1");
		if (materielBaseFormBean != null) {
			// 字符串字段模糊匹配，
			if (CommonUtil.trim(materielBaseFormBean.getSearchName()).length() > 0) {

				sql.append("AND MATERIEL_NAME LIKE ? ");
				list.add("%" + CommonUtil.trim(materielBaseFormBean.getSearchName()) + "%");

				sql.append("OR MATERIAL_NORMS LIKE ? ");
				list.add("%" + CommonUtil.trim(materielBaseFormBean.getSearchName()) + "%");

				sql.append("OR MATERIEL_DESC LIKE ? ");
				list.add("%" + CommonUtil.trim(materielBaseFormBean.getSearchName()) + "%");

				sql.append("OR MATERIAL_GROUP LIKE ? ");
				list.add("%" + CommonUtil.trim(materielBaseFormBean.getSearchName()) + "%");

				sql.append("OR MATERIEL_CLASSIFY LIKE ? ");
				list.add("%" + CommonUtil.trim(materielBaseFormBean.getSearchName()) + "%");
			}
			
			//物料code
			if(materielBaseFormBean.getMaterielBase().getMaterielCode()!=null&&!"".equals(materielBaseFormBean.getMaterielBase().getMaterielCode())){
				sql.append(" AND MATERIEL_CODE ='"+materielBaseFormBean.getMaterielBase().getMaterielCode()+"'");
			}
		}
		sql.append(" ORDER BY ID " + materielBaseFormBean.getPageBean().getSortOrder());
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, materielBaseFormBean.getPageBean().getOffset(),
				materielBaseFormBean.getPageBean().getLimit(), list.toArray());
		return retValue;
	}
	@SuppressWarnings({ "rawtypes" })
	public PageResults findByIds(String ids) {
		ArrayList parmValue = new ArrayList();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID AS ID,MATERIEL_CODE AS MATERIELCODE,MATERIEL_NAME AS MATERIELNAME,");
		sql.append("MATERIAL_NORMS AS MATERIALNORMS,UNIT AS UNIT,MATERIEL_DESC AS MATERIELDESC,");
		sql.append("MATERIAL_GROUP AS MATERIALGROUP,MATERIEL_CLASSIFY AS MATERIELCLASSIFY,");
		sql.append("UNIT_DESC AS UNITDESC,STATE AS STATE,REMARK AS REMARK");
		sql.append(" FROM PUB_MATERIEL WHERE 1 = 1");
		if (ids.length() > 0) {
			sql.append(" AND ID IN ('" + ids + "')");
		}
		sql.append(" ORDER BY ID");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedHql(sql.toString(), null, 0, 100000000, parmValue.toArray());
		
		return retValue;
	}
	/*******************WEB-APP分割线***************************/
	@SuppressWarnings("rawtypes")
	public PageResults getBase(MaterielBaseFormBean infoBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID AS ID,MATERIEL_CODE AS MATERIELCODE,MATERIEL_NAME AS MATERIELNAME,");
		sql.append("CODE AS CODE,MATERIAL_NORMS AS MATERIALNORMS,UNIT AS UNIT,PRICE AS PRICE,");
		sql.append("MATERIEL_DESC AS MATERIELDESC,MATERIAL_GROUP AS MATERIALGROUP,");
		sql.append("MATERIEL_CLASSIFY AS MATERIELCLASSIFY,UNIT_DESC AS UNITDESC,");
		sql.append("STATE AS STATE,REMARK AS REMARK");
		sql.append(" FROM PUB_MATERIEL WHERE 1 = 1");
		
		sql.append(" ORDER BY ID  DESC");
		PageResults retValue = new PageResults();
		
		retValue = this.findPageByFetchedSql(sql.toString(), null,0,100000,list.toArray());
		
		return retValue;
	}
	

	

}

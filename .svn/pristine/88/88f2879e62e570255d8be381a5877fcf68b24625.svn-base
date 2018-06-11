package com.lyht.business.question.dao;

import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.question.bean.Submit;
import com.lyht.business.question.formBean.SubmitFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class SubmitDao extends HibernateBaseDao<Submit>{

	/**
	 * 查询物资问题
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults queryAllQuestion(SubmitFormBean fSubmitFormBean,SysDept dept){
		ArrayList parmValue=new ArrayList();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT SUBMIT.ID AS ID,SUBMIT.ENGINEER_CODE AS ENGINEERCODE,SUBMIT.MATERIEL_CODE AS MATERIELCODE,");
		sql.append("SUBMIT.TITLE AS TITLE,SUBMIT.PROBLEM_TYPE AS PROBLEMTYPE,SUBMIT.PROBLEM_DESC AS PROBLEMDESC,MATERIEL.MATERIEL_NAME AS MATERIELNAME,");
		sql.append("SUBMIT.REPORT_PERSON AS REPORTPERSON,SUBMIT.TREENM_SYS_DEPT AS TREENMSYSDEPT,SUBMIT.STATE AS STATE,");
		sql.append("SUBMIT.REPORT_TIME AS REPORTTIME,SUBMIT.PROCESS_LIMIT AS PROCESSLIMIT,SUBMIT.REMARK AS REMARK,STAFF.NAME AS STAFFNAME,");
		sql.append("FILETABLE.FILENUB AS FILENUB,SUBMIT.FILE_NM AS FILENM,DEPT.NAME AS DEPTNAME,DICT.NAME AS DICTNAME ");
		sql.append("FROM QUESTION_SUBMIT SUBMIT ");
		sql.append("LEFT JOIN (SELECT TABLE_PK_COLUMN,COUNT(TABLE_PK_COLUMN) FILENUB FROM PUB_FILES WHERE TABLE_NAME = 'QUESTION_SUBMIT' GROUP BY TABLE_PK_COLUMN) FILETABLE ");
		sql.append("ON SUBMIT.FILE_NM = FILETABLE.TABLE_PK_COLUMN ");
		sql.append("LEFT JOIN PUB_MATERIEL MATERIEL ");
		sql.append("ON SUBMIT.MATERIEL_CODE = MATERIEL.MATERIEL_CODE ");
		sql.append("LEFT JOIN PUB_MATERIEL_TYPE TYPE ");
		sql.append("ON MATERIEL.MATERIAL_GROUP = TYPE.CODE ");
		sql.append("LEFT JOIN SYS_DICT DICT ");
		sql.append("ON SUBMIT.PROBLEM_TYPE = DICT.NM ");
		sql.append("LEFT JOIN SYS_DEPT DEPT ");
		sql.append("ON SUBMIT.TREENM_SYS_DEPT = DEPT.NM ");
		sql.append("LEFT JOIN SYS_STAFF STAFF ");
		sql.append("ON SUBMIT.REPORT_PERSON = STAFF.NM ");
		sql.append("WHERE 1=1 ");
		//判断当前部门 施工单位只需看到本单位相关的信息
		if(dept!=null){
			if(CommonUtil.trim( dept.getType()).equals("construction_unit")){
				if(dept.getNm()!=null||!"".equals(dept.getNm())){
					sql.append(" AND SUBMIT.TREENM_SYS_DEPT ='"+dept.getNm()+"'");
				}
			}
		}
		
		if(fSubmitFormBean != null){
			//字符串字段模糊匹配
			if (CommonUtil.trim(fSubmitFormBean.getSearchName()).length()>0){
				sql.append(" AND ( ");
				sql.append(" (SUBMIT.TITLE LIKE ?)");
				parmValue.add("%"+CommonUtil.trim(fSubmitFormBean.getSearchName())+"%");
				sql.append(" ) ");
			}
			if(CommonUtil.trim(fSubmitFormBean.getMaterielType()).length()>0){
				sql.append(" AND TYPE.CODE = ? ");
				parmValue.add(fSubmitFormBean.getMaterielType());
			}
		}
		//字段条件查询，根据需要自己增加
		if(fSubmitFormBean.getmSubmit()!=null){
			if ( null != fSubmitFormBean.getmSubmit().getId() && fSubmitFormBean.getmSubmit().getId()>=0){      
				sql.append(" AND SUBMIT.ID = ? ");
				parmValue.add(fSubmitFormBean.getmSubmit().getId());
            }else{
            	if(CommonUtil.trim(fSubmitFormBean.getmSubmit().getEngineerCode()).length()>0){
            		sql.append(" AND SUBMIT.ENGINEER_CODE = ? ");
            		parmValue.add(fSubmitFormBean.getmSubmit().getEngineerCode());
            	}
            }
			if(CommonUtil.trim(fSubmitFormBean.getmSubmit().getProblemType()).length()>0){
				sql.append(" AND SUBMIT.PROBLEM_TYPE = ? ");
				parmValue.add(fSubmitFormBean.getmSubmit().getProblemType());
			}
			if(CommonUtil.trim(fSubmitFormBean.getmSubmit().getTreenmSysDept()).length()>0){
				sql.append(" AND (SUBMIT.TREENM_SYS_DEPT = ? ");
				parmValue.add(fSubmitFormBean.getmSubmit().getTreenmSysDept());
				sql.append(" OR DEPT.PNM = ?) ");
				parmValue.add(fSubmitFormBean.getmSubmit().getTreenmSysDept());
			}
		}
		if(null != fSubmitFormBean.getIds() && fSubmitFormBean.getIds().length()>0){
			sql.append(" AND SUBMIT.ID IN ( ");
			sql.append(fSubmitFormBean.getIds());
			sql.append(" ) ");
		}
		sql.append(" ORDER BY SUBMIT.ID "+fSubmitFormBean.getPageBean().getSortOrder());
		String sql_all=sql.toString();
		PageResults retValue =new PageResults();
		retValue =  this.findPageByFetchedSql(sql_all, null
    			,fSubmitFormBean.getPageBean().getOffset()
    			,fSubmitFormBean.getPageBean().getLimit()
    			,parmValue.toArray());
		return retValue;
	}
	
	/**
	 * 修改问题状态为 已处理-1
	 */
	public void updateQuestionState(int id,int flag){
		String sql="UPDATE QUESTION_SUBMIT SET STATE = ? WHERE ID = ?";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setParameter(0,flag);
		query.setParameter(1,id);
		query.executeUpdate();
	}
	
	/**
	 * 查询物料问题数量
	 */
	public int queryQuestionNub(SubmitFormBean fSubmitFormBean,String nowDate){
		int nub=0;
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT COUNT(ID) FROM QUESTION_SUBMIT WHERE ENGINEER_CODE = ? ");
		if(CommonUtil.trim(fSubmitFormBean.getNowDate()).length()>0){
			sql.append(" AND  REPORT_TIME = ? ");
		}else{
			sql.append(" AND STATE = 0 ");
		}
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		query.setParameter(0,fSubmitFormBean.getmSubmit().getEngineerCode());
		if(CommonUtil.trim(fSubmitFormBean.getNowDate()).length()>0){
			query.setParameter(1,nowDate);
		}
		nub=(int) query.uniqueResult();
		return nub;
	}
}

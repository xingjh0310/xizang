package com.lyht.business.question.dao;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.question.bean.HandlerDetail;
import com.lyht.business.question.formBean.SubmitFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class HandlerDetailDao extends HibernateBaseDao<HandlerDetail>{
	
	/**
	 * 查询物资问题处理明细
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults queryQuestionHandlerDetail(SubmitFormBean fSubmitFormBean,SysDept dept){
		ArrayList parmValue=new ArrayList();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT SUBMIT.ID AS SUBMITID,SUBMIT.ENGINEER_CODE AS ENGINEERCODE,SUBMIT.MATERIEL_CODE AS MATERIELCODE,");
		sql.append("SUBMIT.TITLE AS TITLE,SUBMIT.PROBLEM_TYPE AS PROBLEMTYPE,SUBMIT.PROBLEM_DESC AS PROBLEMDESC,");
		sql.append("SUBMIT.REPORT_PERSON AS REPORTPERSON,SUBMIT.TREENM_SYS_DEPT AS TREENMSYSDEPT,SUBMIT.STATE AS STATE,");
		sql.append("SUBMIT.REPORT_TIME AS REPORTTIME,SUBMIT.PROCESS_LIMIT AS PROCESSLIMIT,SUBMIT.REMARK AS REMARK,");
		sql.append("HANDLE.ID AS ID,HANDLE.HANDLE_RESULT AS HANDLERESULT,HANDLE.PROCESS_METHOD AS PROCESSMETHOD,");
		sql.append("HANDLE.PROCESS_TIME AS PROCESSTIME,HANDLE.PROCESS_PERSON AS PROCESSPERSON,HANDLE.REMARK AS HANDLEREMARK,");
		sql.append("MATERIEL.MATERIEL_NAME AS MATERIELNAME,SUBDICT.NAME AS SUBDICTNAME,DICT.NAME AS DICTNAME,DEPT.NAME AS DEPTNAME,");
		sql.append("SUBSTAFF.NAME AS SUBSTAFFNAME,STAFF.NAME AS STAFFNAME,SUBMIT.FILE_NM AS FILENM,HANDLE.FILE_NM AS HANDLEFILENM ");
		sql.append("FROM QUESTION_SUBMIT SUBMIT ");
		sql.append("LEFT JOIN QUESTION_HANDLE_DETAIL HANDLE ");
		sql.append("ON SUBMIT.ID=HANDLE.SUBMIT_ID ");
		sql.append("LEFT JOIN PUB_MATERIEL MATERIEL ");
		sql.append("ON SUBMIT.MATERIEL_CODE = MATERIEL.MATERIEL_CODE ");
		sql.append("LEFT JOIN PUB_MATERIEL_TYPE TYPE ");
		sql.append("ON MATERIEL.MATERIAL_GROUP = TYPE.CODE ");
		sql.append("LEFT JOIN SYS_DICT SUBDICT ");
		sql.append("ON SUBMIT.PROBLEM_TYPE = SUBDICT.NM ");
		sql.append("LEFT JOIN SYS_DEPT DEPT ");
		sql.append("ON SUBMIT.TREENM_SYS_DEPT = DEPT.NM ");
		sql.append("LEFT JOIN SYS_STAFF SUBSTAFF ");
		sql.append("ON SUBMIT.REPORT_PERSON = SUBSTAFF.NM ");
		sql.append("LEFT JOIN SYS_DICT DICT ");
		sql.append("ON HANDLE.PROCESS_METHOD = DICT.NM ");
		sql.append("LEFT JOIN SYS_STAFF STAFF ");
		sql.append("ON HANDLE.PROCESS_PERSON = STAFF.NM ");
		sql.append("WHERE SUBMIT.STATE = 1 ");
		//判断当前登录人部门 施工单位只需看到本单位相关的信息 
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
			if(CommonUtil.trim(fSubmitFormBean.getmSubmit().getState()).length()>0){
				sql.append(" AND SUBMIT.STATE = ? ");
				parmValue.add(fSubmitFormBean.getmSubmit().getState());
			}
		}
		if(null != fSubmitFormBean.getIds() && fSubmitFormBean.getIds().length()>0){
			sql.append(" AND SUBMIT.ID IN ( ");
			sql.append(fSubmitFormBean.getIds());
			sql.append(" ) ");
		}
		sql.append(" ORDER BY HANDLE.ID "+fSubmitFormBean.getPageBean().getSortOrder());
		String sql_all=sql.toString();
		PageResults retValue =new PageResults();
		retValue =  this.findPageByFetchedSql(sql_all, null
    			,fSubmitFormBean.getPageBean().getOffset()
    			,fSubmitFormBean.getPageBean().getLimit()
    			,parmValue.toArray());
		return retValue;
	}

}

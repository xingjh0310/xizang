package com.lyht.business.notic.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.notic.bean.Notice;
import com.lyht.business.notic.formBean.NoticeFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class NoticeDao extends HibernateBaseDao<Notice> {

	// 通知列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults list(NoticeFormBean noticeFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT n.ID AS ID,q.name as TYPE,n.ENGINEER_CODE AS ENGINEERCODE,TITLE AS TITLE,");
		sql.append("CLASSIFY AS CLASSIFY,RELEASE_DATE AS RELEASEDATE,N.RECEIVE_DEPT AS RECEIVEDEPT,");
		sql.append("C.DEPT_NAME AS RELEASEDEPT,CONTEXT AS CONTEXT,CREATE_PERSON AS CREATEPERSON,");
		sql.append("READ_TIMES AS READTIMES,STATE AS STATE,REMARK AS REMARK,READ_STATE AS READSTATE");
		sql.append(" FROM NOTICE AS N ");
		sql.append(" LEFT JOIN (SELECT d.name,d.code FROM sys_dict as d LEFT JOIN sys_dict_cate as c ON d.listnm_sys_dict_cate=c.nm  WHERE c.code='tzlx' ) as q ON n.classify=q.code ");
		
		sql.append(" LEFT JOIN BOOK_DEPT AS C ON N.RELEASE_DEPT=C.NM");
		sql.append(" WHERE 1 = 1 AND n.ENGINEER_CODE ='"+noticeFormBean.getNotice().getEngineerCode()+"' ");
		sql.append(" AND n.release_dept ='"+noticeFormBean.getNotice().getReleaseDept()+"'");

		if (noticeFormBean != null) {
			// 字符串字段模糊匹配，
			if (CommonUtil.trim(noticeFormBean.getSearchName()).length() > 0) {
				//工程编号
				sql.append("AND N.ENGINEER_CODE LIKE ? ");
				list.add("%" + CommonUtil.trim(noticeFormBean.getSearchName()) + "%");
				//标题	
				sql.append("OR TITLE LIKE ? ");
				list.add("%" + CommonUtil.trim(noticeFormBean.getSearchName()) + "%");
				//日期
				sql.append("OR RELEASE_DATE LIKE ? ");
				list.add("%" + CommonUtil.trim(noticeFormBean.getSearchName()) + "%");
				//阅读次数
				sql.append("OR READ_TIMES LIKE ? ");
				list.add("%" + CommonUtil.trim(noticeFormBean.getSearchName()) + "%");
			}
			// 分类
			if (noticeFormBean.getNotice().getClassify() != null
					&& !"".equals(noticeFormBean.getNotice().getClassify())) {
				sql.append(" AND CLASSIFY ='" + noticeFormBean.getNotice().getClassify() + "' ");
			}
			// 接收部门
			if (noticeFormBean.getNotice().getReceiveDept() != null
					&& !"".equals(noticeFormBean.getNotice().getReceiveDept())) {
				sql.append(" AND RECEIVE_DEPT LIKE '%" + noticeFormBean.getNotice().getReceiveDept() + "%' ");
			}
			// 发布部门
			if (noticeFormBean.getNotice().getReleaseDept() != null
					&& !"".equals(noticeFormBean.getNotice().getReleaseDept())) {
				sql.append(" AND RELEASE_DEPT ='" + noticeFormBean.getNotice().getReleaseDept() + "'");
			}
			//是否阅读
			if(noticeFormBean.getNotice().getReadState()!=null){
				sql.append(" AND READ_STATE ='"+noticeFormBean.getNotice().getReadState()+"' ");
			}
		}
		
		sql.append(" ORDER BY ID " + noticeFormBean.getPageBean().getSortOrder());
		PageResults retValue = new PageResults();
		PageResults retdept = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, noticeFormBean.getPageBean().getOffset(),
				noticeFormBean.getPageBean().getLimit(), list.toArray());
		
			List <Map> results = retValue.getResults();
			String de="";
			for(int i=0;i<results.size();i++){
				
				String st = results.get(i).get("RECEIVEDEPT").toString();
				
				String[] split = st.split(",");
				
				for(int j=0;j<split.length;j++){
					
					String dsql ="SELECT DEPT_NAME AS NAME  FROM  BOOK_DEPT  WHERE NM='"+split[j]+"' ";
					
					retdept = this.findPageByFetchedSql(dsql.toString(), null,0,1000000, list.toArray());
					
					List <Map> deptName = retdept.getResults();
					if(deptName!=null && deptName.size()>0){
					String deptN = deptName.get(0).get("NAME").toString();
						de +=deptN+",";
					}
				}
				results.get(i).put("RECEIVEDEPT", de.substring(0,de.length()-1));
				de="";
			}
		return retValue;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults getBaseById(Integer id) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append("select n.id as id,n.engineer_code as engineerCode,title as title,classify as classify,");
		sql.append("q.name as classifyName,release_date as releaseDate,n.receive_dept as receiveDept,");
		sql.append("c.dept_name as releaseDept,context as context,create_person as createPerson,");
		sql.append("n.receive_dept as receiveCode, read_times as readTimes,state as state,remark as remark");
		sql.append(" from notice as n ");
		sql.append(" left join book_dept as c on n.release_dept=c.nm");
		sql.append(" LEFT JOIN (SELECT d.name,d.code FROM sys_dict AS d LEFT JOIN sys_dict_cate AS c ON d.listnm_sys_dict_cate = c.nm WHERE c.code = 'tzlx') AS q ON n.classify = q.code ");
		sql.append(" where 1 = 1  and n.id='"+id+"' ");
		sql.append(" order by id desc ");
		PageResults retValue = new PageResults();
		PageResults retdept = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null,0,1000000, list.toArray());
		
		List <Map> results = retValue.getResults();
		String de="";
		for(int i=0;i<results.size();i++){
			
			String st = CommonUtil.trim(results.get(i).get("receiveDept"));
			
			String[] split = st.split(",");
			
			for(int j=0;j<split.length;j++){
				
				String dsql ="SELECT  DEPT_NAME AS  NAME  FROM  BOOK_DEPT  WHERE NM='"+split[j]+"' ";
				
				retdept = this.findPageByFetchedSql(dsql.toString(), null,0,1000000, list.toArray());
				
				List <Map> deptName = retdept.getResults();
				if(deptName!=null && deptName.size()>0){
				String deptN = deptName.get(0).get("NAME").toString();
				
					de +=deptN+",";
				}
			}
			
			results.get(i).put("receiveDept", de);
		}
		return retValue;
	}
	//未读消息数量
	@SuppressWarnings({ "rawtypes", "unused" })
	public int getMessageNub(NoticeFormBean noticeFormBean) {
		int nub=0;
		ArrayList list = new ArrayList();
		StringBuilder sql  = new StringBuilder();
		sql.append("SELECT COUNT(ID) AS NUB FROM notice ");
		sql.append("WHERE engineer_code='"+noticeFormBean.getNotice().getEngineerCode()+"'");
		sql.append(" AND state=1 AND read_state=0");
		
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		nub=(int) query.uniqueResult();
		return nub;
	}


	/*************************************************************************
	 ******************************* APP**************************************
	 *************************************************************************
	 *************************************************************************/
	// 通知列表
	@SuppressWarnings({ "rawtypes"})
	public PageResults app_list(NoticeFormBean noticeFormBean) {
		ArrayList list = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append("select n.id as id,n.engineer_code as engineerCode,n.title as title,");
		sql.append("n.classify as classify,n.release_date as releaseDate,n.receive_dept as receiveDept,");
		sql.append("n.release_dept as releaseDept,n.context as context,n.create_person as createPerson,");
		sql.append("n.read_times as readTimes,n.state as state,n.remark as remark,d.name as releaseName");
		sql.append(" from notice as n LEFT JOIN  sys_dept as d ON n.release_dept = d.nm  ");
		sql.append(" where 1 = 1 and state='1' and n.receive_dept LIKE '%"+noticeFormBean.getNotice().getReceiveDept()+"%'");
		sql.append(" and n.classify='"+noticeFormBean.getNotice().getClassify()+"'  order by id desc ");
		PageResults retValue = new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1000000, list.toArray());
		return retValue;
	}
	// app获取发布的通知
		@SuppressWarnings({ "rawtypes"})
		public PageResults app_release(NoticeFormBean noticeFormBean) {
			ArrayList list = new ArrayList();
			StringBuilder sql = new StringBuilder();

			sql.append("select n.id as id,n.engineer_code as engineerCode,n.title as title,");
			sql.append("n.classify as classify,n.release_date as releaseDate,n.receive_dept as receiveDept,");
			sql.append("n.release_dept as releaseDept,n.context as context,n.create_person as createPerson,");
			sql.append("n.read_times as readTimes,n.state as state,n.remark as remark,d.name as releaseName");
			sql.append(" from notice as n LEFT JOIN  sys_dept as d ON n.release_dept = d.nm  ");
			sql.append(" where 1 = 1 and state='1' and create_person='"+noticeFormBean.getNotice().getCreatePerson()+"' order by id desc ");
			PageResults retValue = new PageResults();
			retValue = this.findPageByFetchedSql(sql.toString(), null, 0,1000000, list.toArray());
			return retValue;
		}
	

}

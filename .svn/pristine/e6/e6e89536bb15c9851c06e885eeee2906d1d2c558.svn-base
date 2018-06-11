package com.lyht.business.pub.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.pub.bean.FileUpload;

@Repository
@Scope("prototype")
public class FileUploadDao extends HibernateBaseDao<FileUpload>{
	
	/**
	 * 查询已上传的文件
	 * @param
	 * 1.附件关联表
	 * 2.附件关联表主键
	 */
	@SuppressWarnings("unchecked")
	public List<FileUpload> queryFileUpload(String tableName,String id){
		String hql="FROM FileUpload WHERE TABLE_NAME = ? AND TABLE_PK_COLUMN = ?";
		Query query=this.getSession().createQuery(hql);
		query.setParameter(0, tableName);
		query.setParameter(1, id);
		List<FileUpload> mFileUploadList=query.list();
		return mFileUploadList;
	}
	
	/**
	 * 删除已上传文件
	 */
	public void deleteFileById(String fileId){
		String hql="DELETE FROM FileUpload WHERE FILE_ID = ?";
		Query query=this.getSession().createQuery(hql);
		query.setParameter(0, fileId);
		query.executeUpdate();
	}
	
	/**
	 * 根据人员内码删除附件信息
	 * */
	public void deletePubFilesByStaffNm(String staffNm,String tableName){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM PUB_FILES WHERE TABLE_NAME='"+tableName+"' AND TABLE_PK_COLUMN ='"+staffNm+"' ");
		this.exectueSQL(sql.toString());
	}
	
}

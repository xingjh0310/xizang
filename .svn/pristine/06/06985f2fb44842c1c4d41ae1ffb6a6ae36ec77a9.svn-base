package com.lyht.business.contracMng.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContChange;
import com.lyht.business.contracMng.dao.ContChangeDao;
import com.lyht.business.contracMng.formbean.ContChangeFormBean;
import com.lyht.business.contracMng.formbean.ContInfoFormBean;
import com.lyht.util.CommonUtil;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class ContChangeService {
	@Resource
	private ContChangeDao contChangeDao;

	// 保存合同信息
	public ContChange saveContChange(ContChange bean) {
		contChangeDao.save(bean);
		return bean;
	}

//	public Object queryContChangeByContNo(String contractNo) {
//		return contChangeDao.queryContDetailByContNO(contractNo);
//	}
	
	// 根据id删除物资变更记录
	public void delContChangeByIds(String ids) {
		if(CommonUtil.trim(ids).length()>0){
			String[] idary = ids.split(",");
			for (int i = 0; i < idary.length; i++) {
				contChangeDao.deleteById(Integer.parseInt(idary[i]));
			}
		}
	}

	public Object queryDetailIds(String ids) {
		return contChangeDao.queryDetailIds(ids);
	}

	
	// 查询合同列表
	@SuppressWarnings("rawtypes")
	public Object list(ContChangeFormBean contChangeFormBean) {
		return contChangeDao.list(contChangeFormBean);
	}

	public Object delChangeByMaterialIds(String ids) {
		// TODO Auto-generated method stub
		ids = ids.substring(1, ids.length());
		return contChangeDao.delChangeByMaterialIds(ids);
	}

	public void delContChangeByContIds(String ids) {
		// TODO Auto-generated method stub
		contChangeDao.delContChangeByContIds(ids);
	}

}

package com.lyht.business.evaluate.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContInfo;
import com.lyht.business.contracMng.dao.ContInfoDao;
import com.lyht.business.contracMng.formbean.ContInfoFormBean;
import com.lyht.business.evaluate.bean.ContEvaluate;
import com.lyht.business.evaluate.dao.ContEvaluateDao;
import com.lyht.business.evaluate.formBean.ContEvaluateFormBean;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class ContEvaluateService {
	@Resource
	private ContEvaluateDao contEvaluateDao;

	// 查询履约评价列表
	@SuppressWarnings("rawtypes")
	public PageResults listContEvaluate(ContEvaluateFormBean contEvaluateFormBean) {
		return contEvaluateDao.listContEvaluate(contEvaluateFormBean);
	}
	
	//修改履约评价
	public ContEvaluate update(ContEvaluate contEvaluate) {
		contEvaluateDao.merge(contEvaluate);
		contEvaluateDao.flush(contEvaluate);
		return contEvaluate;
	}

	//添加或修改履约评价时查询数据
	public PageResults editEvaluate(ContEvaluateFormBean contEvaluateFormBean) {
		// TODO Auto-generated method stub
		return contEvaluateDao.editEvaluate(contEvaluateFormBean);
	}

	//添加履约评价
	public ContEvaluate saveContEvaluate(ContEvaluate contEvaluateBean) {
		// TODO Auto-generated method stub
		contEvaluateDao.save(contEvaluateBean);
		return contEvaluateBean;
	}

	//根据ID获取履约评价
	public ContEvaluate getContEvaluateById(Integer id) {
		return contEvaluateDao.get(id);
	}
	
	// 根据id删除履约评价
	public void delContEvaluateByIds(String ids) {
		String[] idary = ids.split(",");
		for (int i = 0; i < idary.length; i++) {
			contEvaluateDao.deleteById(Integer.parseInt(idary[i]));
		}
	}

	public PageResults queryEvaluateIds(String ids) {
		// TODO Auto-generated method stub
		return contEvaluateDao.queryEvaluateIds(ids);
	}
}

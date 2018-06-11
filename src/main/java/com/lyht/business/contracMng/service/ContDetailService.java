package com.lyht.business.contracMng.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.contracMng.bean.ContDetail;
import com.lyht.business.contracMng.bean.ContInfo;
import com.lyht.business.contracMng.dao.ContDetailDao;
import com.lyht.business.contracMng.formbean.ContDetailFormBean;
import com.lyht.util.CommonUtil;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class ContDetailService {
	@Resource
	private ContDetailDao contDetailDao;

	// 保存合同信息
	public ContDetail saveContDetail(ContDetail bean) {
		contDetailDao.save(bean);
		return bean;
	}

	public Object queryContDetailByContNo(String contractNo) {
		return contDetailDao.queryContDetailByContNO(contractNo);
	}
	
	// 根据id删除物资
	public void delContDetailByIds(String ids) {
		if(CommonUtil.trim(ids).length()>0){
			String[] idary = ids.split(",");
			for (int i = 0; i < idary.length; i++) {
				if(!"".equals(idary[i])){
					contDetailDao.deleteById(Integer.parseInt(idary[i]));
				}
			}
		}
	}
	
	// 修改合同物资信息
	public ContDetail update(ContDetail contDetail) {
		contDetailDao.merge(contDetail);
		contDetailDao.flush(contDetail);
		return contDetail;
	}

	public Object queryDetailIds(String ids) {
		// TODO Auto-generated method stub
		return contDetailDao.queryDetailIds(ids);
	}

	public ContDetail getContDetailById(Integer id) {
		// TODO Auto-generated method stub
		return contDetailDao.get(id);
	}

	public Object getMaterialById_app(ContDetailFormBean contDetailFormBean) {
		// TODO Auto-generated method stub
		return contDetailDao.getMaterialById_app(contDetailFormBean);
	}

	public Object getMaterialByContNoAndMatCode_app(ContDetailFormBean contDetailFormBean) {
		// TODO Auto-generated method stub
		return contDetailDao.getMaterialByContNoAndMatCode_app(contDetailFormBean);
	}
}

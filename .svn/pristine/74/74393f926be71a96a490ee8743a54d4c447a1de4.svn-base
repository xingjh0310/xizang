package com.lyht.business.question.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.question.bean.Handler;
import com.lyht.business.question.bean.HandlerDetail;
import com.lyht.business.question.dao.HandlerDetailDao;
import com.lyht.business.question.formBean.SubmitFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.DateUtil;

@Service
@Scope("prototype")
@Transactional
public class HandlerDetailService {

	@Resource
	private HandlerDetailDao dHandlerDetailDao;
	
	/**
	 * 新增物资问题处理明细
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveHandlerDetail(Handler mHandler){
		HandlerDetail mHandlerDetail = new HandlerDetail();
		mHandlerDetail.setEngineerCode(mHandler.getEngineerCode());
		mHandlerDetail.setSubmitId(mHandler.getSubmitId());
		mHandlerDetail.setFileNm(mHandler.getFileNm());
		mHandlerDetail.setDetailTime(DateUtil.getDateTime());
		mHandlerDetail.setHandleResult(mHandler.getHandleResult());
		mHandlerDetail.setProcessMethod(mHandler.getProcessMethod());
		mHandlerDetail.setProcessPerson(mHandler.getProcessPerson());
		mHandlerDetail.setProcessTime(mHandler.getProcessTime());
		mHandlerDetail.setRemark(mHandler.getRemark());
		dHandlerDetailDao.save(mHandlerDetail);
	}
	
	/**
	 * 查询物资问题处理明细
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults queryQuestionHandlerDetail(SubmitFormBean fSubmitFormBean,SysDept dept){
		return dHandlerDetailDao.queryQuestionHandlerDetail(fSubmitFormBean,dept);
	}
}

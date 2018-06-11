package com.lyht.business.system.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.system.dao.QcXtSwitchDao;

@Service
@Scope("prototype")
@Transactional
public class QcXtSwitchService {

	@Resource
	private QcXtSwitchDao mQcXtSwitchDao;
	
	//根据状态获取开关信息
	public Integer getSwicthInfo(Integer flag){
		return mQcXtSwitchDao.getSwicthInfo(flag);
	}
}

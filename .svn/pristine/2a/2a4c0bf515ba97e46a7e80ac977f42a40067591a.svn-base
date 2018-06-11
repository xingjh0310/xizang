package com.lyht.business.system.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.dao.SysAcctDao;
import com.lyht.util.CommonUtil;
import com.lyht.util.DesUtils;

@Service
@Scope("prototype")
public class SystemService {

	 @Resource 
	 private SysAcctDao sysacctDao;
	 
	/**
	 * 验证账户信息
	* */
	public SysAcct checkSysAcctInfo(SysAcct mSysAcct){
		String acctName=CommonUtil.trim(mSysAcct.getName());
		if(DesUtils.checkMobileNumber(acctName)){
			List<SysAcct> mSysAcctList=sysacctDao.checkSysAcctInfoByPhone(acctName);
			if(mSysAcctList.size()>0){
				mSysAcct=mSysAcctList.get(0);
			}else{
				mSysAcct=new SysAcct();
			}
		}else{
			mSysAcct=sysacctDao.acct_check(mSysAcct);
		}
		return mSysAcct;
	}
	
	//获取全部菜单
	@SuppressWarnings("rawtypes")
	public PageResults acct_menu(SysStaff loginacct) {
		return sysacctDao.acct_menu(loginacct);
	}
	
	
	@SuppressWarnings("rawtypes")
	public PageResults getMenuByAccount() {
		return sysacctDao.getMenuByAccount();
	}
	//获取菜单（进入后台）
	@SuppressWarnings("rawtypes")
	public PageResults getMenuByAccount_(){
		return sysacctDao.getMenuByAccount_();
	}
	
	@SuppressWarnings("rawtypes")
	public PageResults getMenuByAccount(String roleNm) {
		return sysacctDao.getMenuByAccount(roleNm);
	}
}

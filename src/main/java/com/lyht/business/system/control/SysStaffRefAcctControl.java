package com.lyht.business.system.control;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.formBean.SysStaffRefAcctFormBean;
import com.lyht.business.system.service.SysStaffRefAcctService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysStaffRefAcctControl {

	@Resource
	private SysStaffRefAcctService mSysStaffRefAcctService;
	
	@Optlog(menuflag="menu_system_sysstaff",opttype = "list")
	public RetMessage list(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean,PageResults mPageResults){
		RetMessage ret=new RetMessage();
		try {
				BeanUtils.copyProperties(mPageResults,mSysStaffRefAcctService.s_findAll(mSysStaffRefAcctFormBean));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			e.printStackTrace();
		}
		
		return ret;
	}
	
	//导入人员信息数据
	@Optlog(menuflag="menu_system_sysstaff",opttype = "importStaffInfo")  //记录日志
	public RetMessage importStaffInfo(File[] file,String[] fileName){
		RetMessage mRetMessage=new RetMessage();
		try{
			mSysStaffRefAcctService.importStaffInfo(file,fileName);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("导入人员信息数据成功！");
		}catch(Exception e){
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入人员信息失败！");
			e.getStackTrace();
		}
		return mRetMessage;
	}
	
	
	
	
	
}

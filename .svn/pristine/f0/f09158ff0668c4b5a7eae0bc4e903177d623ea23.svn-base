package com.lyht.business.system.control;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.formBean.SysAcctFormBean;
import com.lyht.business.system.service.SysAcctService;
import com.lyht.util.CommonUtil;

@Controller
@Scope("prototype")
public class SysAcctControl {
	 
	@Resource 
	private SysAcctService sysAcctService;
	
	//根据ID获取JavaBean对象
	public SysAcct getByid(int id){
		SysAcct retBean=new SysAcct();
		try {
			retBean=sysAcctService.s_get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retBean;
	}
	
	@Optlog(menuflag="menu_system_sysacct", opttype = "view")  //记录日志
	public RetMessage view(int id,SysAcct retBean){
		RetMessage ret=new RetMessage();
		try {
				BeanUtils.copyProperties(retBean,sysAcctService.s_get(id));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return ret;
	}
	
	/**
	 * 
	 * @param infoBean--需要保存的新数据
	 * @param retBean --保存后返回的数据
	 * @return
	 */
	@Optlog(menuflag="menu_system_sysacct", opttype = "add")  //记录日志
	public RetMessage create(SysAcct infoBean,SysAcct retBean){
		RetMessage ret=new RetMessage();
		try {
			    if(checkOnlyByName(infoBean)){
			    	BeanUtils.copyProperties(retBean,sysAcctService.s_create(infoBean));
					ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
					ret.setMessage("新增信息成功！");
			    }else{
			    	ret.setRetflag(RetMessage.RETFLAG_ERROR);
			    	ret.setMessage("账户不能重复！");
			    }
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		
		return ret;
	}
	
	/**
	 * 
	 * @param infoBean --需要修改的数据
	 * @param retBean  --传入旧数据，返回新数据
	 * @return
	 */
	@Optlog(menuflag="menu_system_sysacct",opttype = "edit")  //记录日志
	public RetMessage update(SysAcct retBean,SysAcct infoBean){
		RetMessage ret=new RetMessage();
		try {
			    if(checkOnlyByName(retBean)){
			    	BeanUtils.copyProperties(infoBean,sysAcctService.s_update(retBean));
					ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
					ret.setMessage("修改信息成功！");
			    }else{
			    	ret.setRetflag(RetMessage.RETFLAG_ERROR);
			    	ret.setMessage("账户不能重复！");
			    }
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		
		return ret;
	}
	
	/**
	 * 
	 * 根据id初始化密码
	 * 
	 */
	@Optlog(menuflag="menu_system_sysacct",opttype = "updatePwdById")  //记录日志
	public RetMessage updatePwdById(String id){
		RetMessage ret=new RetMessage();
		try{
			sysAcctService.updatePwdById(id);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("初始化密码成功！");
		}catch (Exception e) {
			e.getStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"初始化密码失败！");
		}
		return ret;
	}
	
	//验证账户是否唯一
	public boolean checkOnlyByName(SysAcct mSysAcct){
		return sysAcctService.checkOnlyByName(mSysAcct);
	}
	
	@SuppressWarnings("rawtypes")
	@Optlog(menuflag="menu_system_sysacct",opttype = "list")  //记录日志
	public RetMessage list(SysAcctFormBean formBean,PageResults prs){
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
			    BeanUtils.copyProperties(prs,sysAcctService.s_findAll(formBean));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询数据成功！");
			
		} catch (Exception e) {
			 
			e.getStackTrace();
		}
		
		return ret;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Optlog(menuflag="menu_system_sysacct",opttype = "flag")  //记录日志
	public RetMessage flag(String ids,List list,int flag_new){
		RetMessage ret=new RetMessage();
		try {
			    sysAcctService.s_flagByIds(ids,flag_new);
				list.clear();
				list.addAll(sysAcctService.s_findByIds(ids).getResults());
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("审核数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"审核数据失败！");
		}
		
		return ret;
		
	}
	
	@SuppressWarnings({ "rawtypes"})
	@Optlog(menuflag="menu_system_sysacct",opttype = "del")  //记录日志
	public RetMessage delByIds(String ids,List list){
		RetMessage ret=new RetMessage();
		try {
			    sysAcctService.s_delByIds(ids);
				list.clear();//一定要执行此步骤，在AOP中数据就被清除
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		
		return ret;
	}
	
	//根据IDS获取到List
	@SuppressWarnings("rawtypes")
	public PageResults list_ByIds(String ids){
		PageResults prs=new PageResults();
		try {
			prs=sysAcctService.s_findByIds(ids);
		} catch (Exception e) {
			
		}
		return prs;
	}
	
	//修改密码
	public RetMessage updatePwdByUser(SysAcctFormBean formBean){
		RetMessage mRetMessage=new RetMessage();
		try{
			String newPwd=CommonUtil.trim(formBean.getNewPwd());
			String confirmPwd=CommonUtil.trim(formBean.getConfirmPwd());
			if("".equals(newPwd) || "".equals(confirmPwd) ||
					"".equals(formBean.getInfoBean().getPwd()) ||
						"".equals(formBean.getInfoBean().getName())){
				mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
				mRetMessage.setMessage("用户名或密码不能为空！");
			}else{
				if(isExist(formBean.getInfoBean()) && newPwd.equals(confirmPwd)){
					sysAcctService.updatePwdByName(formBean.getInfoBean(),newPwd);
					mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
					mRetMessage.setMessage("修改密码成功！");
				}else{
					mRetMessage.setMessage("用户名或密码错误！");
				}
			}
		}catch (Exception e) {
			e.getStackTrace();
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改密码失败！");
		}
		return mRetMessage;
	}
	//APP修改密码
	public RetMessage editPass(SysAcctFormBean formBean){
		RetMessage ret=new RetMessage();
		try {
			boolean check = sysAcctService.check(formBean);
			if(check==true){
				sysAcctService.editPass(formBean);
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("修改密码成功！");
			}else{
				ret.setRetflag(RetMessage.RETFLAG_ERROR);
				ret.setMessage("旧密码不正确！");
			}
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "修改密码失败！");
		}
		return ret;
	}
	
	//验证用户名与旧密码是否存在
    private boolean isExist(SysAcct mSysAcct){
    	return sysAcctService.isExist(mSysAcct);
    }
}

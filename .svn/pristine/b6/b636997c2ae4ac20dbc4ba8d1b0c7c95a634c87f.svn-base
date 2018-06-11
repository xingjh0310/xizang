package com.lyht.business.login.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.bean.SysRela;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.control.SysAcctControl;
import com.lyht.business.system.formBean.SysAcctFormBean;
import com.lyht.business.system.service.QcXtSwitchService;
import com.lyht.business.system.service.SysDeptService;
import com.lyht.business.system.service.SysEngineerInfoService;
import com.lyht.business.system.service.SysRelaService;
import com.lyht.business.system.service.SysRoleService;
import com.lyht.business.system.service.SysStaffService;
import com.lyht.business.system.service.SystemService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;
import com.lyht.util.MD5;

@Namespace("/login")
@Results({ @Result(name = "index", location = "/firstpage.jsp"),
	@Result(name = "role", location = "/business/system/home_page/list.jsp"),
	@Result(name = "error", location = "/login.jsp") 
})

@Controller
@Scope("prototype")
@Action("/appSystem")
public class AppSystemAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;
	private Logger log= Logger.getLogger(AppSystemAction.class);
	
	private SysAcct loginfo=new SysAcct();
	
	private String loginmsg;
	
	@Resource
	private SystemService systemService;
	@Resource 
	private SysStaffService staffService;
	@Resource 
	private SysDeptService deptService;
	@Resource 
	private SysRoleService sysRoleService;
	@Resource 
	private QcXtSwitchService mQcXtSwitchService;
	@Resource 
	private SysRelaService mSysRelaService;
	@Resource 
	private SysAcctControl sysAcctControl;
	@Resource 
	private SysEngineerInfoService mSysEngineerInfoService;
	
	private SysAcctFormBean formBean = new SysAcctFormBean();
	//登录
	@SuppressWarnings("rawtypes")
	public String login_app(){
		String loginflag="error";
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try 
		{
			SysAcct sysacct = null;
			//根据账号，校验密码
			try {
				loginfo.setDictnmZhlx("system");
				loginfo.setDictnmDllx("account");
				sysacct=systemService.checkSysAcctInfo(loginfo);
			} catch (Exception e) {
				setLoginmsg("获取账户信息出错！");
				log.error("========编辑信息调用SystemAction-login出错:获取账户信息出错：", e);
			} finally {
				if (sysacct==null) sysacct = new SysAcct();
			}
			
			MD5 md= MD5.getInstance();
			String pwd=CommonUtil.trim(loginfo.getPwd());
			String pwdmd5=md.getMD5ofStr(pwd, 16);
			
			String account=sysacct.getName(); //获取账户
			
			//查询到账户
			if (loginfo.getName().equals(account)){
				boolean opflag=true;
				/*//判断账户是否过期
				if (CommonUtil.compareDateTime(getNowDateTime(),sysacct.getYxq()+" 23:59:59")) {
					setLoginmsg("账户已过期，请联系系统管理员。");
					opflag = false;
				}*/
				
				//判断密码是否一致
				if (opflag ) {
					if  (pwdmd5.equals(sysacct.getPwd()) ){
						loginflag = "success";
						//获取人员信息
						SysStaff staff=new SysStaff();
						SysDept dept = new SysDept();
						try {
								//获取人员信息
								if (sysacct.getListnmSysStaff().length()>0){
									staff = staffService.s_getByProp("nm", sysacct.getListnmSysStaff());
									staff.setPhone(account);
									hashMap.put("staff", staff);
									//获取部门信息
									if (staff !=null &&  CommonUtil.getLength(staff.getTreenmSysDept())>0){
										dept = deptService.s_getByProp("nm", staff.getTreenmSysDept());
										hashMap.put("dept", dept);
										List<Map> list = mSysEngineerInfoService.getSysEngineerInfoBystaffNm(sysacct.getListnmSysStaff());
										if(list.size()>0){
											
												//根据人员内码查询人员所对应的关联表信息
												SysRela mSysRela=getNmByStaffCode(sysacct.getListnmSysStaff());
												SysRole mSysRole=getRoleByAccount(mSysRela.getTaNm());
												hashMap.put("role", mSysRole);
												//根据人员内码查询所对应的工程信息
												hashMap.put("engineer", list);
											
										}else{
											loginflag = "error";
											setLoginmsg("该账号没有分配工程！请联系管理员。");
										}
									}
								}
						} catch (Exception e) {
							setLoginmsg("获取人员信息出错！");
							log.error("========编辑信息调用SystemAction-login出错:获取人员信息出错：", e);
						} finally {
							if (staff==null) staff = new SysStaff();
						}
						
					} else {
						setLoginmsg("账户密码错误！请重新输入。");
					}
				}
			} else {
				setLoginmsg("账户不存在！请重新输入。");
			}
		} catch (Exception e) {
			setLoginmsg("账户登录发生错误！");
			log.error("账户登录发生错误。");
		}
		hashMap.put("loginflag", loginflag);
		hashMap.put("loginmsg", getLoginmsg());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	
	//获取当前时间
	private String getNowDateTime(){
		SimpleDateFormat mSimpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar mCalendar=Calendar.getInstance();
		mCalendar.setTime(new Date());	
		return mSimpleDateFormat.format(mCalendar.getTime());
	}
	
	//根据账户获取人员信息
	private SysRole getRoleByAccount(String code){
		SysRole mSysRole=null;
		try{
			List<SysRole> mSysRoleList = sysRoleService.getNameByAccount(code); //获取系统人员名称
			if(mSysRoleList.size()>0){
				mSysRole=mSysRoleList.get(0);
			}
		}catch (Exception e) {
			log.error("根据账户获取人员信息失败============getRoleByAccount");
			e.getStackTrace();
		}
		return mSysRole;
	}
	
	private SysRela getNmByStaffCode(String code){
		SysRela mSysRela=null;
		try{
			List<SysRela> mSysRelaList = mSysRelaService.getSysRelaNmByStaffCode(code); 
			if(mSysRelaList.size()>0){
				mSysRela=mSysRelaList.get(0);
			}
		}catch(Exception e){
			log.error("根据人员内码获取关联内码失败============getNmByStaffCode");
			e.getStackTrace();
		}
		return mSysRela;
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private PageResults getEngineerInfoByEngineerInfoCode(String code){
		PageResults mPageResults=null;
		try{
			mPageResults = mSysEngineerInfoService.getEngineerInfoByEngineerInfoCode(code); 
		}catch (Exception e) {
			log.error("根据人员内码查询所对应的工程信息失败============getEngineerInfoByStaffCode");
			e.getStackTrace();
		}
		return mPageResults;
	}
	
	//修改用户密码
	public String editPass(){
		log.info("修改密码==AppSystemAction.editPass");
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		RetMessage ret=new RetMessage();
		ret = sysAcctControl.editPass(formBean);
		
		// 写入当前操作 成功状态 success 或 error
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());	
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	//根据人员编号查询是否登录状态
	private boolean getIsLogin(String staffNm){
		return staffService.getIsLoginByStaffNm(staffNm);
	}
	
	//获取开关标识
	private Integer getSwicth(Integer flag){
		return mQcXtSwitchService.getSwicthInfo(flag);
	}
	
	public SysAcct getLoginfo() {
		return loginfo;
	}

	public void setLoginfo(SysAcct loginfo) {
		this.loginfo = loginfo;
	}

	public String getLoginmsg() {
		return loginmsg;
	}

	public void setLoginmsg(String loginmsg) {
		this.loginmsg = loginmsg;
	}


	public SysAcctFormBean getFormBean() {
		return formBean;
	}


	public void setFormBean(SysAcctFormBean formBean) {
		this.formBean = formBean;
	}
	
	
	
}

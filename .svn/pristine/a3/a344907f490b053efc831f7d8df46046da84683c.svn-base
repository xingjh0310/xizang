package com.lyht.business.system.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.TokenSession;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.*;
import com.lyht.business.system.service.*;
import com.lyht.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Namespace("/login")
@Results({ @Result(name = "index", location = "/firstpage.jsp"),
	@Result(name = "role", location = "/business/system/home_page/list.jsp"),
	@Result(name = "error", location = "/login.jsp")
})

@Controller
@Scope("prototype")
public class SystemAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;
	private Logger log= Logger.getLogger(SystemAction.class);
	
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
	private SysEngineerInfoService mSysEngineerInfoService;
	@Resource
	private SysEngineerInfoAction mSysEngineerInfoAction;
	
	/**
	 * 登录
	 * */
	public String login(){
		String loginflag="error";
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		try{
			 // mSysAcct = (SysAcct) getSession().getAttribute(Constants.SESSION_ACCT);
			SysAcct mSysAcct=systemService.checkSysAcctInfo(loginfo);
			if(validateUserNameAndPwd(mSysAcct)){
					SysStaff mSysStaff=new SysStaff();
					SysDept mSysDept = new SysDept();
					try{
						String staffNm=mSysAcct.getListnmSysStaff();
						if(null!=staffNm && !"".equals(staffNm)){
							mSysStaff = staffService.s_getByProp("nm", mSysAcct.getListnmSysStaff());
							//获取部门信息
							if (null!=mSysStaff &&  CommonUtil.getLength(mSysStaff.getTreenmSysDept())>0){
								mSysDept = deptService.s_getByProp("nm", mSysStaff.getTreenmSysDept());
							}
							loginflag=loginInfo(mSysAcct,mSysStaff,mSysDept,loginflag);
							mHashMap.put("loginflag", loginflag);
							mHashMap.put("loginmsg", getLoginmsg());
							CommonFunction.writeResponse(this.getResponse(), mHashMap);
							return null;
						}
					}catch (Exception e) {
						log.error("========编辑信息调用SystemAction-login出错:获取人员信息出错：", e);
						setLoginmsg("获取人员信息出错。");
					}
				}else{
					loginflag="validatorUserPwd";
					setLoginmsg("用户名或密码不正确！！！");
				}
		}catch (Exception e) {
			log.error("账户登录发生错误。");
			setLoginmsg("用户名或密码不能为空！！！");
		}
		mHashMap.put("loginflag", loginflag);
		mHashMap.put("loginmsg", getLoginmsg());
		CommonFunction.writeResponse(this.getResponse(), mHashMap);
		return null;
	}
	
	//进入后台（系统人员）
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String enter(){
		HttpServletRequest request = ServletActionContext.getRequest();
        String engNm = request.getParameter("engNm") ;
		String flag="error";
		Hashtable mHashtable=new Hashtable();
		PageResults menu_prs=new PageResults();
		SysRole mSysRole=new SysRole();
		SysStaff mSysStaff=new SysStaff();
		SysAcct sysacct = (SysAcct) getSession().getAttribute(Constants.SESSION_ACCT);
		String account=sysacct.getName().trim(); //获取账户
		String staffNm=sysacct.getListnmSysStaff();
		if("supadmin".equals(account)){ 
			menu_prs = systemService.getMenuByAccount_();
			getSession().setAttribute(Constants.SESSION_MENU, menu_prs.getResults());
			getWholeEngineerInfo(engNm);
		    mSysRole=sysRoleService.s_getByProp("code", account);
		    getSession().setAttribute(Constants.SESSION_ROLE, 	mSysRole);
		    mSysStaff = staffService.s_getByProp("nm",staffNm);
		    getSession().setAttribute(Constants.SESSION_STAFF, 	mSysStaff);
		    getModuleByAccount(sysacct.getName());
		    getSession().setAttribute("sysNormal", 1);
			flag="homePage";
		}
		mHashtable.put("flag", flag);
		CommonFunction.writeResponse(this.getResponse(), mHashtable);
		return null;
	}
	
	//根据登录账号区分功能模块
	private void getModuleByAccount(String account){
		this.getSession().removeAttribute("admins");   //系统管理员
		this.getSession().removeAttribute("audmins");  //审核管理员
		this.getSession().removeAttribute("almins");   //日志管理员
		this.getSession().removeAttribute("supadmin");//超级管理员
		this.getSession().removeAttribute(Constants.SESSION_ROLE);//普通用户
		
		if("admins".equals(account)){ 
			this.getSession().setAttribute("admins", account);
		}else if("audmins".equals(account)){ 
			this.getSession().setAttribute("audmins", account);
		}else if("almins".equals(account)){ 
			this.getSession().setAttribute("almins", account);
		}else if("supadmin".equals(account)){
			this.getSession().setAttribute("supadmin", account);
		}else{
			this.getSession().setAttribute(Constants.SESSION_ROLE, account);
		}
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
	
	//根据人员内码获取部门信息中的工程编号
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void getEngineerInfoCodeByStaffNm(String staffNm){
		List<Map> list=mSysEngineerInfoService.getSysEngineerInfoBystaffNm(staffNm);
		if(list.size()>0){
			list.get(0).put("checked","true");
		}
		
		String json=JSONArray.fromObject(list).toString();
	    getSession().setAttribute(Constants.SESSION_ENGINEER, json);
	}
	
	//获取全部工程信息（存放在session中）
    @SuppressWarnings("rawtypes")
	private void getWholeEngineerInfo(){
    	List<Map> list=mSysEngineerInfoService.getWholeEngineerInfo();
    	if(list.size()>0){
    		String json=JSONArray.fromObject(list).toString();
	    	getSession().setAttribute(Constants.SESSION_ENGINEER, json);
    	}
    }
    //获取全部工程信息（存放在session中）
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void getWholeEngineerInfo(String engNm){
    	List<Map> list=mSysEngineerInfoService.getWholeEngineerInfo();
    	if(list.size()>0){
    		
    		for(int i=0;i<list.size();i++){
    			if(list.get(i).get("NM").equals(engNm)){
    				list.get(i).put("checked","true");
    			}else{
    				list.get(i).put("checked","false");
    			}
    		}
    		String json=JSONArray.fromObject(list).toString();
    		getSession().setAttribute(Constants.SESSION_ENGINEER, json);
    	}
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
	
	//根据人员编号查询是否登录状态
	private boolean getIsLogin(String staffNm){
		return staffService.getIsLoginByStaffNm(staffNm);
	}
	
	//注销用户信息
	public String logout(){
		clearSessionData();
		return "error";
	}
	
	//检查是否有session,直接返回成功标识就可以。
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public String checksession(){
		
		Hashtable hashtable = new Hashtable();
		// 标记
		hashtable.put("retflag", "success");
		// 信息提示
		hashtable.put("message", "检查到session存在");
		
		JSONObject json = new JSONObject().fromObject(hashtable);
		CommonFunction.writeResponse(getResponse(), json.toString());	
		return null;
	}
	
	//验证tokenid
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public String checktokenid(){
		//获取数据库中的tokenid
		UUID uuid = UUID.randomUUID();
		String tokenid= uuid.toString().replaceAll("-", "");
		tokenid = "123456789";
		Hashtable hashtable = new Hashtable();
		hashtable.put("retflag", "success");
		hashtable.put("tokenid", tokenid);
		// 信息提示
		hashtable.put("message", "tokenid 存在 exists");
		
		JSONObject json = new JSONObject().fromObject(hashtable);
		CommonFunction.writeResponse(getResponse(), json.toString());
		
		return null;
	}
	
	/**
	 * 登录跳转页面
	 * */
	@SuppressWarnings("rawtypes")
	private String loginInfo(SysAcct mSysAcct,SysStaff mSysStaff,SysDept mSysDept,String loginflag) throws IOException{
		clearSessionData();
		PageResults mPageResults=new PageResults();
		if("supadmin".equals(mSysAcct.getName())){ 
			mPageResults = systemService.getMenuByAccount();
			getSession().setAttribute(Constants.SESSION_MENU_S, mPageResults.getResults());
		    getWholeEngineerInfo(); //获取全部工程信息（存放在session中）
		    this.getSession().removeAttribute("sysNormal");
		    loginflag="success";
		}else{
			if(getIsLogin(mSysAcct.getListnmSysStaff())){  
				//根据人员内码查询人员所对应的关联表信息
				SysRela mSysRela=getNmByStaffCode(mSysAcct.getListnmSysStaff());
				SysRole mSysRole=getRoleByAccount(mSysRela.getTaNm());
				mPageResults = systemService.getMenuByAccount(mSysRole.getNm());
			    getSession().setAttribute(Constants.SESSION_MENU, mPageResults.getResults());
			    getSession().setAttribute(Constants.SESSION_ROLE, mSysRole);
			    //根据人员内码获取部门信息中的工程编号（存放session中）
			    getEngineerInfoCodeByStaffNm(mSysAcct.getListnmSysStaff());
			    //根据登录账户（区分系统管理与普通系统的首页）
			    getSession().setAttribute("sysNormal", 1);
			    loginflag="role";
			}else{
				loginflag = "no";
				setLoginmsg("该账号没有分配工程！请联系管理员。");
			}
		}
		setSessionData(mSysAcct,mSysStaff,mSysDept);
		return loginflag;
	}
	
	/**
	 * 验证用户名与密码是否相符
	 * */
	private boolean validateUserNameAndPwd(SysAcct mSysAcct){
		boolean opflag=true;
		String acctName=CommonUtil.trim(mSysAcct.getName());
		String acctPwd=CommonUtil.trim(mSysAcct.getPwd());
		String acctYxq=CommonUtil.trim(mSysAcct.getYxq());
		String encryptPwd=MD5.getInstance().getMD5ofStr(CommonUtil.trim(loginfo.getPwd()), 16);
		if(loginfo.getName().equals(acctName)){
			if (CommonUtil.getLength(CommonUtil.trim(acctYxq)) > 0 &&   CommonUtil.compareDateTime(getNowDateTime(),acctYxq+" 23:59:59")) {
				setLoginmsg("账户已过期，请联系系统管理员。");
				opflag = false;
			}
			if(opflag){
				if(acctPwd.equals(encryptPwd)){
					return opflag;
				}else{
					setLoginmsg("账户密码错误！请重新输入。");
					opflag = false;
				}
			}
		}else{
			setLoginmsg("账户不存在！请重新输入。");
			opflag = false;
		}
		return opflag;
	}
	
	/**
	 * 设置session数据
	 * */
	private void setSessionData(SysAcct mSysAcct,SysStaff mSysStaff,SysDept mSysDept){
		UUID uuid=UUID.randomUUID();
		TokenSession tkse=new TokenSession();
		tkse.setTokenid(uuid.toString());
		tkse.setFlag(1);
		tkse.setAcctnm(mSysAcct.getNm());
		getModuleByAccount(mSysAcct.getName()); //根据登录账号区分功能模块
		getSession().setAttribute(Constants.SESSION_SWICTH_ENABLE,getSwicth(Constants.FLAG_0)); //启用
		getSession().setAttribute(Constants.SESSION_SWICTH_NOENABLE,getSwicth(Constants.FLAG_1));//不启用
		getSession().setAttribute(Constants.SESSION_ACCT,  	mSysAcct);
		getSession().setAttribute(Constants.SESSION_STAFF, 	mSysStaff);
		getSession().setAttribute(Constants.SESSION_DEPT, 	mSysDept);
		getSession().setAttribute(Constants.SESSION_TOKENID, tkse);
		getSession().setMaxInactiveInterval(30 * 60);//设置单位为秒，设置为-1永不过期
	}
	
	/**
	 * 清空session数据
	 * */
	private void clearSessionData(){
		getSession().removeAttribute(Constants.SESSION_TOKENID);
		getSession().removeAttribute(Constants.SESSION_ACCT);
		getSession().removeAttribute(Constants.SESSION_STAFF);
		getSession().removeAttribute(Constants.SESSION_DEPT);
		getSession().removeAttribute(Constants.SESSION_MENU);
		getSession().removeAttribute(Constants.SESSION_MENU_S);
		getSession().removeAttribute(Constants.SESSION_ROLE);
		getSession().removeAttribute(Constants.SESSION_ENGINEER);
		getSession().removeAttribute("sysNormal");
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
	
}

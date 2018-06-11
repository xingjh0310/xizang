package com.lyht.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.lyht.Constants;
import com.lyht.TokenSession;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.bean.SysStaff;

/**
 * @author
 * 返回对应系统值   
 */
public class SysDefaultUtil {
	private static Logger log = Logger.getLogger(SysDefaultUtil.class);
	
	/** 获取当前系统登陆人员的人员编号
	 * @param hsdSession
	 * @return
	 */
	public static String getLoginStaffCode(HttpServletRequest request){
		String sysStaffCode = "";
		try{
			Object obj = request.getSession().getAttribute(Constants.SESSION_STAFF);
			if(obj != null && obj instanceof SysStaff){
				SysStaff mSysStaff =  (SysStaff) obj;
				sysStaffCode =  CommonUtil.trim(mSysStaff.getCode());
			}
		}catch (Exception e) {
			log.error("========获取当前系统登陆人员的人员编号出错", e);
			e.getStackTrace();
		}
		return sysStaffCode;
	}
	
	/** 获取当前登录人员名称
	 * @param request
	 * @return
	 */
	public static String getLoginStaffName(HttpServletRequest request){
		String sysStaff_Name = "";
		try{
			Object obj = request.getSession().getAttribute(Constants.SESSION_STAFF);
			if(obj != null && obj instanceof SysStaff){
				SysStaff mSysStaff =  (SysStaff) obj;
				sysStaff_Name =  CommonUtil.trim(mSysStaff.getName());
			}
		}catch (Exception e) {
			log.error("========获取当前登录人员名称出错", e);
			e.getStackTrace();
		}
		return sysStaff_Name;
	}
	
	/** 获取当前登录人员对象
	 * @param hsdSession
	 * @return
	 */
	public static Object getLoginStaff(HttpServletRequest request){
		try {
			Object obj = request.getSession().getAttribute(Constants.SESSION_STAFF);
			if(obj != null && obj instanceof SysStaff){
				return (SysStaff) obj;
			} 
		} catch (Exception e) {
			log.error("========获取当前登录人员对象出错", e);
			e.getStackTrace();
		}
		return null;
	}
	
   /** 获取当前单点登录信息
	 * @param hsdSession
	 * @return
    */
	public static Object getLoginTokenID(HttpServletRequest request){
		try {
			Object obj = request.getSession().getAttribute(Constants.SESSION_TOKENID);
			if(obj != null && obj instanceof TokenSession){
				return (TokenSession) obj;
			} 
		} catch (Exception e) {
			log.error("========获取当前单点登录信息出错", e);
			e.getStackTrace();
		}
		return null;
	}
	
	/** 获取当前菜单信息
	 * @param hsdSession
	 * @return
    */
	public static Object getLoginMenu(HttpServletRequest request){
		try {
			Object obj = request.getSession().getAttribute(Constants.SESSION_MENU);
			if(obj != null && obj instanceof SysMenu){
				return (SysMenu) obj;
			} 
		} catch (Exception e) {
			log.error("========获取当前菜单信息出错", e);
			e.getStackTrace();
		}
		return null;
	}
	
	/** 获取当前系统登陆人员的组织机构ID
	 * @param hsdSession
	 * @return
	 */
	public static int getLoginDeptID(HttpServletRequest request){
		int deptId = 0 ;
		try {
			Object obj = request.getSession().getAttribute(Constants.SESSION_STAFF);
			if(obj != null && obj instanceof SysStaff){
				SysStaff sysStaff = (SysStaff)request.getSession().getAttribute(Constants.SESSION_STAFF);
				deptId = CommonUtil.getIntValue(sysStaff.getTreenmSysDept()+"");
			} 
		} catch (Exception e) {
			log.error("========获取当前系统登陆人员的组织机构ID出错", e);
			e.getStackTrace();
		}
		return deptId;
	} 
	
	/** 获取当前系统登陆人员的组织机构内码
	 * @param hsdSession
	 * @return
	 */
	public static String getLoginAcctID(HttpServletRequest request){
		String sysAcctNm = "" ;
		try {
			Object obj = request.getSession().getAttribute(Constants.SESSION_ACCT);
			if(obj instanceof SysAcct ){
				SysAcct mSysAcct = (SysAcct) obj;
				sysAcctNm= mSysAcct.getNm();
			} 
		} catch (Exception e) {
			log.error("========获取当前系统登陆人员的组织机构内码出错", e);
			e.getStackTrace();
		}
		return sysAcctNm;
	}
	
}

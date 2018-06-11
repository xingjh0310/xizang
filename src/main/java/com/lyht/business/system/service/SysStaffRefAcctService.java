package com.lyht.business.system.service;

import java.io.*;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.pub.dao.FileUploadDao;
import com.lyht.business.system.bean.*;
import com.lyht.business.system.dao.*;
import com.lyht.business.system.formBean.SysStaffRefAcctFormBean;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;
import com.lyht.util.Randomizer;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class SysStaffRefAcctService {

	@Resource
	private SysStaffDao mSysStaffDao;
	@Resource
	private SysAcctDao mSysAcctDao;
	@Resource
	private SysStaffRefAcctDao mSysStaffRefAcctDao;
	@Resource
	private SysRefEngineerDao mSysRefEngineerDao;
	@Resource
	private SysDictDao mSysDictDao;
	@Resource
	private SysDeptDao mSysDeptDao;
	@Resource
	private FileUploadDao mFileUploadDao;
	
	/**
	 * 显示人员及账户列表信息
	 */
	public PageResults s_findAll(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		return mSysStaffRefAcctDao.m_findAll(mSysStaffRefAcctFormBean);
	}
	
	/**
	 * 根据人员内码查询人员信息
	 * */
	public List<Map> getSysStaffByStaffNm(String staffNm){
		return mSysStaffRefAcctDao.getSysStaffByStaffNm(staffNm);
	}
	
	/**
	 * 根据人员内码查询人员信息
	 * */
	public SysAcct getSysAcctByStaffNm(String staffNm){
		SysAcct mSysAcct=new SysAcct();
		List<SysAcct> mSysAcctList=mSysAcctDao.getSysAcctByStaffNm(staffNm);
		if(mSysAcctList.size()>0){
			mSysAcct=mSysAcctList.get(0);
		}
		return mSysAcct;
	}
	
	/**
	 * 新增人员与账户信息操作
	 * */
	public HashMap<String, Object> insertStaffAndAcct(HashMap<String, Object> mHashMap,SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		boolean flag=getSysStaffAndAcctInfo(mSysStaffRefAcctFormBean);
		if(flag){
			mHashMap.put("validate", "人员名称不能重复！！！");
		}else{
			String staffNm=Randomizer.getRandom(); 
			flag=insertSysStaffAndAcctInfo(mSysStaffRefAcctFormBean,staffNm);
			if(flag){
				mHashMap.put("validate", "新增信息成功！！！");
			}else{
				mHashMap.put("validate", "新增信息失败！！！");
			}
		}
		return mHashMap;
	}
	
	/**
	 * 根据人员内码获取工程名称并显示人员及账户信息列表中
	 * */
	@SuppressWarnings("unchecked")
	public List<Map> getDataList(List<Map> list){
		if(list.size()>0){
			String str="";
			String str_="";
			for(int i=0,len=list.size();i<len;i++){
				Object obj=list.get(i).get("NM");
				if(null!=obj && !"".equals(obj)){
					String staffNm=obj.toString();
					List<Map> list_=mSysStaffRefAcctDao.getEngineerNameBystaffNm(staffNm); 
					List<Map> _list=mSysStaffRefAcctDao.getRefTableBystaffNm(staffNm); 
					for(int j=0,len_=list_.size();j<len_;j++){
						Object obj_=list_.get(j).get("ENGINEER_NAME");
						if(null!=obj_ && !"".equals(obj_)){
							String engineerName=obj_.toString();
							str+=engineerName+",";
						}
					}
					for(int k=0;k<_list.size();k++){
						Object _obj_=_list.get(k).get("ROLENAME");
						if(null!=_obj_ && !"".equals(_obj_)){
							String roleName=_obj_.toString();
							str_+=roleName+",";
						}
					}
				}
				if(!"".equals(str)){
					if(",".equals(str.substring(str.length()-1))){
						str=str.substring(0,str.length()-1);
					}
				}
				if(!"".equals(str_)){
					if(",".equals(str_.substring(str_.length()-1))){
						str_=str_.substring(0,str_.length()-1);
					}
				}
				list.get(i).put("ENGINEER_NAME", str);
				list.get(i).put("ROLE_NAME", str_);
				str="";
				str_="";
			}
		}
		return list;
	}
	
	/**
	 * 修改人员与账户信息操作
	 * */
	public HashMap<String, Object> updateStaffAndAcct(HashMap<String, Object> mHashMap,SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		boolean flag=validateSysStaffAndAcctInfo(mSysStaffRefAcctFormBean);
		if(!flag){
			mHashMap.put("validate", "人员名称不能重复！！！");
		}else{
			flag=updateSysStaffAndAcctInfo(mSysStaffRefAcctFormBean);
			if(flag){
				mHashMap.put("validate", "修改信息成功！！！");
			}else{
				mHashMap.put("validate", "修改信息失败！！！");
			}
		}
		return mHashMap;
	}
	
	/**
	 * 获取删除提示信息
	 * */
	public HashMap<String, Object> getDeleteSysStaffAndAcctInfo(String staffNm){
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		boolean flag=deleteSysStaffAndAcctInfoByStaffNm(staffNm);
		if(flag){
			deleteSysRefEngineerInfoByStaffNm(staffNm);
			deletePubFilesByStaffNm(staffNm);
			mHashMap.put("message", "删除成功！！！");
		}else{
			mHashMap.put("message", "删除失败！！！");
		}
		return mHashMap;
	}
	
	/**
	 * 重置密码
	 * */
	public HashMap<String, Object> reset(String StaffNm){
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		boolean flag=resetSysAcctInfo(StaffNm);
		if(flag){
			mHashMap.put("message", "重置密码成功！！！");
		}else{
			mHashMap.put("message", "重置密码失败！！");
		}
		return mHashMap;
	}
	
	/**
	 * 验证手机号是否唯一
	 * */
	public HashMap<String, Object> validatePhone(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		boolean flag=validatePhone_(mSysStaffRefAcctFormBean);
		if(flag){
			mHashMap.put("message", "1");
		}
		return mHashMap;
	}
	
	//导入人员信息数据
	public void importStaffInfo(File []file,String []fileName) throws IOException{
		InputStream mInputStream=null;
		List<List<String>> dataList=null;
		ImportExeclUtil mImportExeclUtil=new ImportExeclUtil();
		try{
			for(int i=0;i<file.length;i++){
				mInputStream=new BufferedInputStream(new FileInputStream(file[i]));
				boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本  
		        if (ExcelVersionUtil.isExcel2007(fileName[i])) {  
		            isExcel2003 = false;  
		        }
				dataList=mImportExeclUtil.read(mInputStream, isExcel2003);
				for(int j=1;j<dataList.size();j++){
					setSysStaffInfo(dataList.get(j));
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	
	/**
	 * 验证手机号是否唯一
	 * */
	private boolean validatePhone_(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		boolean flag=false;
		List<Map> list=mSysAcctDao.getSysStaffRefAcctByPhone(mSysStaffRefAcctFormBean);
		if(list.size()>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 获取人员与账户信息
	 * */
	private boolean getSysStaffAndAcctInfo(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		boolean flag=false;
		boolean staffFlag=getSysStaffInfoByStaffCodeOrName(mSysStaffRefAcctFormBean);
		if(staffFlag){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 新增人员与账户信息
	 * */
	private boolean insertSysStaffAndAcctInfo(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean,String staffNm){
		boolean flag=false;
		boolean staffFlag=insertSysStaffInfo(mSysStaffRefAcctFormBean,staffNm);
		boolean acctFlag=insertSysAcctInfo(mSysStaffRefAcctFormBean,staffNm);
		if(staffFlag && acctFlag){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据人员编号与人员名称查询人员信息
	 * */
	private boolean getSysStaffInfoByStaffCodeOrName(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		boolean flag=false;
		List<Map> list=mSysStaffRefAcctDao.getSysStaffInfoByStaffCodeOrName(mSysStaffRefAcctFormBean);
		if(list.size()>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 新增人员信息
	 * */
	private boolean insertSysStaffInfo(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean,String staffNm){
		boolean flag=false;
		int i=mSysStaffRefAcctDao.insertSysStaffInfo(mSysStaffRefAcctFormBean,staffNm);
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 新增账户信息
	 * */
	private boolean insertSysAcctInfo(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean,String staffNm){
		boolean flag=false;
		int i=mSysAcctDao.insertSysAcctInfo(mSysStaffRefAcctFormBean.getmSysAcctInfoBean(),staffNm);
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 验证人员编码、人员名称及账户名称
	 * */
	private boolean validateSysStaffAndAcctInfo(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		boolean flag=false;
		String name=mSysStaffRefAcctFormBean.getmSysStaffInfoBean().getName();
		List<Map> list=mSysStaffRefAcctDao.getSysStaffAndAcctInfoByNm(mSysStaffRefAcctFormBean);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object staffName= list.get(i).get("NAME");
				if(null!=staffName && !"".equals(staffName)){
					String staffName_= staffName.toString();
					if(name.equals(staffName_)){
						break;
					}
				}
				if(null==staffName && "".equals(staffName)){
					flag=true;
					break;
				}
			}
			flag=true;
		}else{
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据人员内码修改人员与账户信息
	 * */
	private boolean updateSysStaffAndAcctInfo(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		boolean flag=false;
		boolean staffFlag=updateSysStaffInfo(mSysStaffRefAcctFormBean);
		boolean acctFlag=updateSysAcctInfo(mSysStaffRefAcctFormBean);
		if(staffFlag && acctFlag){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据人员内码修改人员信息
	 * */
	private boolean updateSysStaffInfo(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		boolean flag=false;
		int i=mSysStaffRefAcctDao.updateSysStaffInfo(mSysStaffRefAcctFormBean);
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据人员内码修改账户信息
	 * */
	private boolean updateSysAcctInfo(SysStaffRefAcctFormBean mSysStaffRefAcctFormBean){
		boolean flag=false;
		int i=mSysAcctDao.updateSysAcctInfo(mSysStaffRefAcctFormBean);
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据人员内码删除人员与账户信息
	 * */
	private boolean deleteSysStaffAndAcctInfoByStaffNm(String StaffNm){
		boolean flag=false;
		boolean staffFlag=deleteSysStaffInfo(StaffNm);
		boolean acctFlag=deleteSysAcctInfo(StaffNm);
		if(staffFlag && acctFlag){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据人员内码删除人员信息
	 * */
	private boolean deleteSysStaffInfo(String StaffNm){
		boolean flag=false;
		String[] idary=StaffNm.split(",");
		for(int i=0;i<idary.length;i++){
			int j=mSysStaffRefAcctDao.deleteSysStaffInfo(idary[i]);
			if(j>0){
				flag=true;
			}
		}
		return flag;
	}
	
	/**
	 * 根据人员内码删除账户信息
	 * */
	private boolean deleteSysAcctInfo(String StaffNm){
		boolean flag=false;
		String[] idary=StaffNm.split(",");
		for(int i=0;i<idary.length;i++){
			int j=mSysAcctDao.deleteSysAcctInfo(idary[i]);
			if(j>0){
				flag=true;
			}
		}
		return flag;
	}
	
	/**
	 * 重置密码
	 * */
	private boolean resetSysAcctInfo(String staffNm){
		boolean flag=false;
		int i=mSysAcctDao.resetSysAcctInfo(staffNm);
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据人员内码删除人员关联工程信息
	 * */
	private void deleteSysRefEngineerInfoByStaffNm(String staffNm){
		String refTableName="pub_engineering+sys_staff";
		String[] idary=staffNm.split(",");
		for(int i=0,len=idary.length;i<len;i++){
			mSysRefEngineerDao.deleteSysRefEngineerInfoByStaffNm(staffNm,refTableName);
		}
	}
	
	/**
	 * 根据人员内码删除附件信息
	 * */
	private void deletePubFilesByStaffNm(String staffNm){
		String tableName="SYS_STAFF";
		String[] idary=staffNm.split(",");
		for(int i=0,len=idary.length;i<len;i++){
			mFileUploadDao.deletePubFilesByStaffNm(staffNm,tableName);
		}
	}
	
	/**
	 * 数据导入到数据库中
	 * */
	private void setSysStaffInfo(List<String> list){
		String staffNm=Randomizer.getRandom();
		String staffCode=Randomizer.nextNumber("staff_", 3);
		String acctNm=Randomizer.getRandom();
		
		SysStaff mSysStaff=new SysStaff();
		SysAcct mSysAcct=new SysAcct();
		
		mSysStaff.setNm(staffNm); //人员内码
		mSysStaff.setCode(staffCode); //人员编码
		mSysStaff.setName(list.get(1)==null?"":list.get(1)); //人员名称
		mSysStaff.setDictnmXingbie(getDictCodeBySex(list.get(2))); //性别
		mSysStaff.setDuty(list.get(4)==null?"":list.get(4)); //职务
		mSysStaff.setTreenmSysDept(getSysDeptNmByName(list.get(5))); //部门
		mSysStaff.setIsLogin(0); //是否登录状态
		
		mSysAcct.setNm(acctNm); //账户内码
		mSysAcct.setName(list.get(3)==null?"":list.get(3)); //手机号
		
		mSysStaffDao.saveSysStaff(mSysStaff);
		mSysAcctDao.insertSysAcctInfo(mSysAcct,staffNm);
	}
	
	/**
	 * 根据性别名称获取相应的编号
	 * */
	public String getDictCodeBySex(String param){
		String key="";
		List<Map> list=mSysDictDao.getDictInfo();
		if(list.size()>0){
			for(int i=0,len=list.size();i<len;i++){
				Object code=list.get(i).get("CODE");
				Object name=list.get(i).get("NAME");
				if(null!=code && !"".equals(code) && null!=name && !"".equals(name)){
					String value=name.toString();
					if(value.equals(param)){
						key=code.toString();
						break;
					}
				}
			}
		}
		return key;
	}
	
	/**
	 * 根据部门名称获取相应的编号
	 * */
	@SuppressWarnings("unchecked")
	private String getSysDeptNmByName(String param){
		String key="";
		List<Map> list=mSysDeptDao.getDepInfoData().getResults();
		if(list.size()>0){
			for(int i=0,len=list.size();i<len;i++){
				Object nm=list.get(i).get("NM");
				Object name=list.get(i).get("NAME");
				if(null!=nm && !"".equals(nm) && null!=name && !"".equals(name)){
					String value=nm.toString();
					if(value.equals(name)){
						key=nm.toString();
						break;
					}
				}
			}
		}
		return key;
	}
	
	/**
	 * 通过角色内码获取人员内码
	 * */
	public List<Map> getStaffCodeByRoleCode(String ids){
		return mSysStaffDao.getStaffCodeByRoleCode(spliStr(ids));
	}
	
	/**
	 * 通过人员内码获取角色内码
	 * */
	public List<Map> getRoleCodeByStaffCode(String ids){
		return mSysStaffDao.getRoleCodeByStaffCode(spliStr(ids));
	}
	
	/**
	 * 拼接字符串
	 * */
	private String spliStr(String ids){
		String str="";
		String []ids_=ids.split(",");
		for(int i=0,len=ids_.length;i<len;i++){
			str+="'"+ids_[i]+"'"+",";
		}
		if(!"".equals(str)){
			if(",".equals(str.substring(str.length()-1))){
				str=str.substring(0,str.length()-1);
			}
		}
		return str;
	}
	
}

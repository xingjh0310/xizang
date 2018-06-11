package com.lyht.business.system.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysEngineerInfo;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.dao.SysDeptDao;
import com.lyht.business.system.dao.SysEngineerInfoDao;
import com.lyht.business.system.dao.SysRefEngineerDao;
import com.lyht.business.system.dao.SysStaffDao;
import com.lyht.business.system.formBean.SysEngineerInfoFormBean;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class SysEngineerInfoService {

	@Resource
	private SysEngineerInfoDao mSysEngineerInfoDao;
	@Resource
	private SysRefEngineerDao mSysRefEngineerDao;
	@Resource
	private SysStaffDao mSysStaffDao;
	@Resource
	private SysDeptDao mSysDeptDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults s_findRoot(SysEngineerInfoFormBean formBean){
		return mSysEngineerInfoDao.m_findRoot(formBean);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults s_findAll(SysEngineerInfoFormBean formBean){
		return mSysEngineerInfoDao.m_findAll(formBean);
	}
	
	//根据id获取实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysEngineerInfo s_get(int id){
	   SysEngineerInfo mSysEngineerInfo= new SysEngineerInfo();
	   if (id>0) {
		   mSysEngineerInfo=mSysEngineerInfoDao.get(id);
	   }
	   return mSysEngineerInfo;
	}
		
	//根据属性及属性值查找对象实体
	@Transactional(propagation=Propagation.REQUIRED)
    public SysEngineerInfo s_getByProp(String PropName,Object PropValue){
	  return mSysEngineerInfoDao.getByProp(PropName, PropValue);
	}

	//增加实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysEngineerInfo s_create(SysEngineerInfo bean){
		UUID uuid = UUID.randomUUID(); //获取新内码
		String str=uuid.toString().replaceAll("-", "");
		bean.setNm(str); //将内码设置成编码
		mSysEngineerInfoDao.save(bean);
		return bean;
	}

	//修改实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysEngineerInfo s_update(SysEngineerInfo bean){
		mSysEngineerInfoDao.merge(bean);
		mSysEngineerInfoDao.flush(bean);
	   return bean;
	}

	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults s_findByIds(String ids){
		SysEngineerInfo sysEngineerInfo=mSysEngineerInfoDao.get(Integer.parseInt(ids));
		return mSysEngineerInfoDao.m_findByCodes(sysEngineerInfo.getThiscode());
	}

	 //根据IDS(1,2,3,4,5)删除多个对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_delByIds(String code){
	  String[] idary=code.split(",");
	  for(int i=0;i<idary.length;i++){
		  mSysEngineerInfoDao.deleteSysEngineerInfoById(idary[i]);
	  }
	}
	
	/**
	 * 根据工程内码删除工程关联表
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteSysRefEngineerInfo(String sysEngineerNm){
		String refTableName="pub_engineering+sys_staff";
		String[] idary=sysEngineerNm.split(",");
		for(int i=0;i<idary.length;i++){
			mSysRefEngineerDao.deleteSysRefEngineerInfo(idary[i], refTableName);
		}
	}
	
	 //根据IDS(1,2,3,4,5)删除多个对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_delByIds_(String ids){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			mSysEngineerInfoDao.deleteSysEngineerInfoById_(idary[i]);
		}
	}

	 //根据IDS(1,2,3,4,5)修改多个对象的Flag数值
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_flagByIds(String ids,Integer flag_new){
	  String[] idary=ids.split(",");
	  for(int i=0;i<idary.length;i++){
		  mSysEngineerInfoDao.s_flagByCode(idary[i],flag_new);
	  }
	}
	
	//获取工程信息
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getSysEngineerInfo(){
		return mSysEngineerInfoDao.getSysEngineerInfo();
	}
	
	//根据工程编号所对应的工程信息
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getEngineerInfoByEngineerInfoCode(String code){
		return mSysEngineerInfoDao.getEngineerInfoByEngineerInfoCode(code);
	}
	
	//根据人员内码获取部门信息中的工程编号
	public List<Map> getEngineerInfoCodeByStaffNm(String listnmSysStaff){
		return mSysEngineerInfoDao.getEngineerInfoCodeByStaffNm(listnmSysStaff);
	}
	//根据人员内码获取部门信息中的工程编号
	public List<Map> getEngineerInfoCodeByStaffNm2(String listnmSysStaff){
		return mSysEngineerInfoDao.getEngineerInfoCodeByStaffNm2(listnmSysStaff);
	}
	
	//获取全部工程信息
    public List<Map> getWholeEngineerInfo(){
    	return mSysEngineerInfoDao.getWholeEngineerInfo();
    }
	
	//导入工程信息
	@Transactional(propagation=Propagation.REQUIRED)
	public void importProjectInfo(File []file,String []fileName){
		InputStream mInputStream=null;
		List<List<String>> dataList=null;
		ImportExeclUtil mImportExeclUtil=new ImportExeclUtil();
		try{
			for(int i=0;i<file.length;i++){
				mInputStream=new BufferedInputStream(new FileInputStream(file[i]));
				boolean isExcel2003=true;
				if(ExcelVersionUtil.isExcel2003(fileName[i])){
					isExcel2003=false;
				}
				dataList=mImportExeclUtil.read(mInputStream, isExcel2003);
				for(int j=1;j<dataList.size();j++){
					setProjectInfo(dataList.get(j));
				}
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	//设置工程信息
	private void setProjectInfo(List<String> list){
		SysEngineerInfo mSysEngineerInfo=new SysEngineerInfo();
		mSysEngineerInfo.setNm("");//内码
		mSysEngineerInfo.setPnm("");//上级内码
		mSysEngineerInfo.setThiscode("");//本级编码
		mSysEngineerInfo.setEngineerCode("");//工程编号
		mSysEngineerInfo.setPcode("");//上级编码
		mSysEngineerInfo.setEngineerName("");//工程名称
		mSysEngineerInfo.setEngineerShort("");//工程名称（简称）
		mSysEngineerInfo.setTreenmSysDept("");//建管单位
		mSysEngineerInfo.setVoltageEvel("");//电压等级
		mSysEngineerInfo.setLineLength(11);//建设线路长度
		mSysEngineerInfo.setDeliveryTime("");//设计投运时间
		mSysEngineerInfo.setState(0);//状态
		mSysEngineerInfo.setRenark("");//备注
		mSysEngineerInfoDao.save(mSysEngineerInfo);
	}

	public PageResults getDeptsByEngineerNm(String nm) {
		return mSysEngineerInfoDao.getDeptsByEngineerNm(nm);
	}

	public PageResults getStaffsByEngineerNm(String nm) {
		return mSysEngineerInfoDao.getStaffsByEngineerNm(nm);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public SysEngineerInfo getBySysEngineerInfoById(String id){
		SysEngineerInfo sysEngineerInfo=mSysEngineerInfoDao.get(Integer.parseInt(id));
		return sysEngineerInfo;
	}
	
   /**
    * 根据工程编号与表关联获取人员信息
    * */
	public PageResults getStaffInfoByEngineerInfoCode(String engineerInfoCode,String tableName){
    	return mSysRefEngineerDao.getStaffInfoByEngineerInfoCode(engineerInfoCode,tableName);
    }
	
	/**
	 * 根据工程内码获取人员名称
	 * */
	public List<Map> getStaffInfoNameByEngineerNm(String key){
		return mSysEngineerInfoDao.getStaffInfoNameByEngineerNm(key);
	}
	
	/**
	 * 根据工程内码获取部门与人员信息
	 * */
	public JSONArray getStaffByEngineerNm(String engineerInfoCode){
		JSONArray mJSONArray=new JSONArray();
		List<Map> list=mSysEngineerInfoDao.getStaffInfoNameByEngineerNm(engineerInfoCode);
		for(int i=0;i<list.size();i++){
			Object obj=list.get(i).get("STAFFCODE");
			if(null!=obj){
				String staffCode=obj.toString();
				SysStaff mSysStaff=mSysStaffDao.getByProp("nm", staffCode);
				JSONObject mJSONObject=new JSONObject();
				mJSONObject.put("ID", mSysStaff.getId());
				mJSONObject.put("NM", mSysStaff.getNm());
				mJSONObject.put("NAME", mSysStaff.getName());
				mJSONObject.put("TREENMSYSDEPT", mSysStaff.getTreenmSysDept());
				mJSONArray.add(mJSONObject);
			}
		}
		return mJSONArray;
	}
	
	/**
	 * 根据人员内码获取工程信息
	 * */
	public List<Map> getEngineerInfoByStaffNm(String staffNm){
		return mSysEngineerInfoDao.getEngineerInfoByStaffNm(staffNm);
	}
	
	/**
	 * 根据人员内码获取工程信息
	 * */
	public List<Map> getSysEngineerInfoBystaffNm(String staffNm){
		return mSysEngineerInfoDao.getSysEngineerInfoBystaffNm(staffNm);
	}
	
}

package com.lyht.business.system.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.dao.SysDeptDao;
import com.lyht.business.system.dao.SysRefEngineerDao;
import com.lyht.business.system.dao.SysStaffDao;
import com.lyht.business.system.formBean.SysDeptFormBean;
import com.lyht.business.system.formBean.SysEngineerInfoFormBean;
import com.lyht.util.CommonFunction;
import com.lyht.util.DesUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;
import com.lyht.util.Randomizer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 *作者： czy
 *脚本日期:2017年7月28日 23:07:43
 *说明:  单位机构
*/
@Service
@Scope("prototype")
@SuppressWarnings("rawtypes")
@Transactional
public class SysDeptService{
	
	@Resource
    private SysDeptDao sysDeptDao;
	@Resource
    private SysStaffDao mSysStaffDao;
	@Resource
	private SysRefEngineerDao mSysRefEngineerDao;
	
    //根据id获取实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysDept s_get(int id){
		SysDept mDept= new SysDept();
		if (id>0) {
			mDept= sysDeptDao.get(id);
		}
		return mDept;
	}
    
	//增加实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysDept s_create(SysDept bean){
		UUID uuid = UUID.randomUUID(); //获取新内码
		String str=uuid.toString().replaceAll("-", "");
		bean.setNm(str); //将内码设置成编码
		sysDeptDao.save(bean);
		return bean;
	}
	
	/**
	 * 验证部门编码
	 * */
	public boolean checkThisCode(SysDept mSysDept){
		boolean flag=false;
		List<Map> list=sysDeptDao.getSysDeptInfo(mSysDept);
		if(list.size()>0){
			flag=true;
		}
		return flag;
	}
    
	//修改实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public SysDept s_update(SysDept bean){
	   sysDeptDao.merge(bean);
	   sysDeptDao.flush(bean);
	   return bean;
	}	
	
    //删除实体对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_remove(SysDept bean){
		sysDeptDao.delete(bean);
	}
	
    //根据IDS(1,2,3,4,5)删除多个对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_delByIds(String ids){
	  String[] idary=ids.split(",");
	  for(int i=0;i<idary.length;i++){
		  sysDeptDao.deleteDeptByCode(idary[i]);
	  }
	}
	
	 //根据IDS(1,2,3,4,5)删除多个对象
		@Transactional(propagation=Propagation.REQUIRED)
		public void s_delByIds_(String ids){
		  String[] idary=ids.split(",");
		  for(int i=0;i<idary.length;i++){
			  sysDeptDao.deleteDeptByCode_(idary[i]);
		  }
		}
	
    //根据IDS(1,2,3,4,5)修改多个对象的Flag数值
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_flagByIds(String ids,Integer flag_new){
	  String[] idary=ids.split(",");
	  for(int i=0;i<idary.length;i++){
		  sysDeptDao.s_flagByCode(idary[i],flag_new);
	  }
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void s_flagByIds(String ids){
		String[] idary=ids.split(",");
		  for(int i=0;i<idary.length;i++){
			 SysDept entity = sysDeptDao.get(Integer.parseInt(idary[i]));
		     sysDeptDao.update(entity);
		  }
	}
	
    //根据FormBean中的条件查找实体对象List
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults s_findAll(SysDeptFormBean formBean){
	  return sysDeptDao.m_findAll(formBean);
	}
	
	//获取部门信息
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getDeptAll(SysDeptFormBean formBean){
		return sysDeptDao.getDeptAll(formBean);
	}
	
	//根据名称获取部门信息
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getDeptName(String name){
		return sysDeptDao.getDeptName(name);
	}
	
	//根据部门编号修改部门信息
	public void updateDeptInfoByCode(String engineerCode,SysDept mSysDept,String code){
		sysDeptDao.updateDeptInfoByCode(engineerCode,mSysDept,code);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getDeptNm(String nm){
		return sysDeptDao.getDeptNm(nm);
	}
	
	//根据工程信息编号获取部门信息
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getDeptInfoByEngineerInfoCode(String engineerInfoCode){
		return sysDeptDao.getDeptInfoByEngineerInfoCode(engineerInfoCode);
	}
	
    //根据属性及属性值查找对象实体
	@Transactional(propagation=Propagation.REQUIRED)
	public SysDept s_getByProp(String PropName,Object PropValue){
	   return sysDeptDao.getByProp(PropName, PropValue);
	}
   
	//查找根记录List
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults s_findRoot(SysDeptFormBean formBean){
	  return sysDeptDao.m_findRoot(formBean);
	}
	
	//处理工程名称
	public List<Map> getEngineeringByNm(String code){
		return sysDeptDao.getEngineeringByNm(code);
	}
    
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults s_findByIds(String ids){
		SysDept mDept=sysDeptDao.get(Integer.parseInt(ids));
		return sysDeptDao.m_findByCodes(mDept.getCode());
	}
	
	//获取部门信息（导出）
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getDeptWholeInfo(){
		return sysDeptDao.getDeptWholeInfo();
	}
	
	/**
	 * 查询部门内码
	 * */
	public List<Map> selectDeptInfo(String nm){
		return sysDeptDao.selectDeptInfo(nm);
	}
	
	//导入部门信息数据
	@Transactional(propagation=Propagation.REQUIRED)
	public void importDeptInfo(File []file,String []fileName) throws IOException{
		InputStream mInputStream=null;
		List<List<String>> dataList=null;
		ImportExeclUtil mImportExeclUtil=new ImportExeclUtil();
		try{
			for(int i=0;i<file.length;i++){
				mInputStream=new BufferedInputStream(new FileInputStream(file[i]));
				boolean isExcel2003=true;
				if(ExcelVersionUtil.isExcel2007(fileName[i])){
					isExcel2003=false;
				}
				dataList=mImportExeclUtil.read(mInputStream, isExcel2003);
				for(int j=1;j<dataList.size();j++){
					setDeptInfo(dataList.get(j));
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	
	
	//设置部门数据
	private void setDeptInfo(List<String> list){
		SysDept mSysDept=new SysDept();
		mSysDept.setNm(Randomizer.getRandom());//内码
		mSysDept.setPnm("");//上级内码
		mSysDept.setThiscode("");//本级编号
		mSysDept.setCode("");//部门编号
		mSysDept.setName("");//部门名称
		mSysDept.setShortName("");//部门简称
		mSysDept.setPcode("");//上级编号
		mSysDept.setEngineerCode("");//工程名称
		mSysDept.setSection("");//标段
		mSysDept.setFlag(0);//状态
		sysDeptDao.save(mSysDept); 
	}
	
	//查询部门是否有数据
	public boolean getDepInfoData(){
		boolean flag=false;
		PageResults mPageResults=sysDeptDao.getDepInfoData();
		if(mPageResults.getResults().size()>0){
			flag=true;
		}
		return flag;
	}
	
	//查询部门所有信息
	public List<Map> getDeptInfoData(){
		return sysDeptDao.getDeptInfoData();
	}
	
	//根据内码查询部门信息
	public List<Map> getDeptInfoData(String nm){
		return sysDeptDao.getDeptInfoData(nm);
	}
	
	//根据内码查询通讯录数据
	public boolean getMailInfoByNm(String nm){
		boolean flag = true;
		List<Map> mMailInfoList = sysDeptDao.getMailInfoByNm(nm);
		if(mMailInfoList.size()>0){
			flag=false;
		}
		return flag;
	}
	
	//然后向通讯录中添加数据
	public void insetMailData(Map map){
		sysDeptDao.insetMailData(map);
	}

	public Object queryType(SysDeptFormBean formBean) {
		return sysDeptDao.queryType(formBean);
	}
	
	/**
	 * 通过工程内码为部门及人员设置工程信息
	 * */
	public boolean getDeptAndStaffInfoByCode(SysDeptFormBean mSysDeptFormBean,SysEngineerInfoFormBean mSysEngineerInfoFormBean){
		boolean flag=false;
		String deptCode_=mSysDeptFormBean.getInfoBean().getCode(); //部门内码
		String staffCode=mSysDeptFormBean.getIds(); //人员内码
		String sysEngineerNm=mSysEngineerInfoFormBean.getmSysEngineerInfo().getNm();
		String tableAName=mSysEngineerInfoFormBean.getmSysRefEngineerInfoBean().getTableAName(); //工程表名
		String tableBName=mSysEngineerInfoFormBean.getmSysRefEngineerInfoBean().getTableBName(); //人员表名
		String refTableName=tableAName+"+"+tableBName;
		
		flag=removeStaffRefPubInfo(mSysEngineerInfoFormBean,sysEngineerNm,refTableName);
		if(!"".equals(deptCode_)){
			flag=getDeptAndStaffInfo(sysEngineerNm,staffCode,refTableName,tableAName,tableBName);
    	}
		return flag;
	}
	
	/**
	 * 取消全部部门及人员相应的工程
	 * */
	private boolean removeStaffRefPubInfo(SysEngineerInfoFormBean mSysEngineerInfoFormBean,String sysEngineerNm,String refTableName){
		boolean flag=false;
		List<Map> list=mSysRefEngineerDao.getSysRefEngineerInfo(sysEngineerNm,refTableName);
		if(list.size()>0){
			int i=mSysRefEngineerDao.deleteSysRefEngineerInfo(sysEngineerNm,refTableName);
			if(i>0){
				flag=true;
			}
		}
		flag=setStaffState(refTableName);
		return flag;
	}
	
	/**
	 * 获取部门及人员信息
	 * */
	private boolean getDeptAndStaffInfo(String sysEngineerNm,String staffCode,String refTableName,String tableAName,String tableBName){
		boolean flag=false;
		if(!"".equals(staffCode)){
			String [] _staffCode=staffCode.split(",");
			for(int i=0;i<_staffCode.length;i++){
				int j=mSysRefEngineerDao.insertStaffRefPubInfo(_staffCode[i],sysEngineerNm,refTableName,tableAName,tableBName);
				if(j>0){
					flag=true;
				}
			}
			flag=setStaffState(refTableName);
		}
		return flag;
	}
	
	/**
	 * 设置人员状态
	 * */
	private boolean setStaffState(String refTableName){
		String str="";
		boolean flag=false;
		List<Map> list=mSysRefEngineerDao.getSysRefEngineerInfo(refTableName);
		mSysStaffDao.updateStaffSateInfo();
		if(list.size()>0){
			for(int i=0,len=list.size();i<len;i++){
				Object obj=list.get(i).get("TABLE_B_CODE");
				if(null!=obj && !"".equals(obj)){
					str+="'"+obj.toString()+"',";
				}
			}
			if(str.length()>0){
				int j=mSysStaffDao.updateStaffSateInfo(DesUtils.removeDuplicate(str));
				if(j>0){
					flag=true;
				}
			}
		}else{
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 获取部门信息
	 * */
	public JSONArray getDeptByEngineerNm(String deptNm){
		JSONArray mJSONArray=new JSONArray();
		if(!"".equals(deptNm)){
			String []deptNm_= DesUtils.removeDuplicate(deptNm).split(",");
			for(int i=0;i<deptNm_.length;i++){
				JSONObject mJSONObject=new JSONObject();
				SysDept mSysDept=sysDeptDao.getByProp("nm", deptNm_[i]);
				if(null!=mSysDept){
					mJSONObject.put("ID",mSysDept.getId() );
					mJSONObject.put("NM", mSysDept.getNm());
					mJSONObject.put("NAME",mSysDept.getName() );
					mJSONArray.add(mJSONObject);
				}
			}
		}
		return mJSONArray;
	}
	
}

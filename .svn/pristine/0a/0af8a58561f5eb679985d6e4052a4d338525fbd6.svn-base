package com.lyht.business.system.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.dao.SysRefEngineerDao;
import com.lyht.business.system.dao.SysStaffDao;
import com.lyht.business.system.formBean.SysDeptFormBean;
import com.lyht.business.system.formBean.SysEngineerInfoFormBean;
import com.lyht.business.system.formBean.SysStaffFormBean;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;
import com.lyht.util.Randomizer;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 21:12:02
 *说明:  人员信息
*/
@Service
@Scope("prototype")
@Transactional
public class SysStaffService{
	
	@Resource 
	private SysStaffDao sysStaffDao;
	@Resource
	private SysRefEngineerDao mSysRefEngineerDao;
	
    //根据id获取实体对象
	public SysStaff s_get(int id){
		SysStaff ret_bean= new SysStaff();
		if (id>0) {
			ret_bean= sysStaffDao.get(id);
		}
		return ret_bean;
	}
    
	//增加实体对象
	public SysStaff s_create(SysStaff bean){
		UUID uuid = UUID.randomUUID();
		String str=uuid.toString().replaceAll("-", "");
		bean.setNm(str);
		bean.setIsLogin(0);
		sysStaffDao.saveSysStaff(bean);
		return bean;
	}
    
	//修改实体对象
	public SysStaff s_update(SysStaff bean){
		sysStaffDao.merge(bean);	
		sysStaffDao.flush(bean);
		return bean;
	}	
	
    //删除实体对象
	public void s_remove(SysStaff bean){
		sysStaffDao.delete(bean);
	}
	
    //根据IDS(1,2,3,4,5)删除多个对象
	public void s_delByIds(String ids){
		  String[] idary=ids.split(",");
		  for(int i=0;i<idary.length;i++)
		  {
			  sysStaffDao.deleteById(Integer.parseInt(idary[i]));
		  }
		
	}
	
    //根据IDS(1,2,3,4,5)修改多个对象的Flag数值
	public void s_flagByIds(String ids,Integer flag_new){
		  String[] idary=ids.split(",");
		  for(int i=0;i<idary.length;i++)
		  { 
			  SysStaff entity = sysStaffDao.get(Integer.parseInt(idary[i]));
			  entity.setFlag(flag_new);
			  sysStaffDao.update(entity);
		  }
		
	}
	
    //根据FormBean中的条件查找实体对象List
	@SuppressWarnings("rawtypes")
	public PageResults s_findAll(SysStaffFormBean formBean){
		return sysStaffDao.m_findAll(formBean);
	}
	
	//根据部门内码获取人员信息
	@SuppressWarnings("rawtypes")
	public PageResults getStaffInfoByDeptNm(SysDeptFormBean mSysDeptFormBean){
		return sysStaffDao.getStaffInfoByDeptNm(mSysDeptFormBean);
	}
	
	//根据部门内码获取人员信息
    @SuppressWarnings("rawtypes")
	public PageResults getStaffInfoByDeptNm_(SysDeptFormBean mSysDeptFormBean,SysStaffFormBean mSysStaffFormBean){
	   return sysStaffDao.getStaffInfoByDeptNm_(mSysDeptFormBean,mSysStaffFormBean);
	}
	
	//获取人员信息
	@SuppressWarnings("rawtypes")
	public PageResults findAll(){
		return sysStaffDao.findAll();
	}
	
	//根据人员名称获取人员信息
	@SuppressWarnings("rawtypes")
	public PageResults findName(String name){
		return sysStaffDao.findName(name);
	}
    
    //根据属性及属性值查找对象实体
	public SysStaff s_getByProp(String PropName,Object PropValue){
		return sysStaffDao.getByProp(PropName, PropValue);
	}
	
	@SuppressWarnings("rawtypes")
	public PageResults s_findByIds(String ids){
		return sysStaffDao.m_findByIds(ids);
	}	
	
	//验证人员编码与人员名称是否唯一
	public boolean checkOnlyByCodeName(SysStaff mSysStaff){
	   return sysStaffDao.checkOnlyByCodeName(mSysStaff);
	}
	
	//验证人员编码与人员名称是否唯一
	public boolean checkOnlyByPhone(SysStaff mSysStaff){
		return sysStaffDao.checkOnlyByPhone(mSysStaff);
	}
	
	//导入人员信息数据
	public void importStaffInfo(File []file,String []fileName,String deptNm) throws IOException{
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
					setSysStaffInfo(dataList.get(j),deptNm);
				}
			}
		}catch(Exception e){
			e.getStackTrace();
			
		}
	}
	
	//设置人员信息
	private void setSysStaffInfo(List<String> data,String deptNm){
		SysStaff mSysStaff=new SysStaff();
		mSysStaff.setNm(Randomizer.getRandom());//内码
		mSysStaff.setCode(data.get(0)==null?"":data.get(0).trim());//人员编号
		mSysStaff.setName(data.get(1)==null?"":data.get(1).trim());//人员名称
		mSysStaff.setDuty(data.get(2)==null?"":data.get(2).trim());//职务
		mSysStaff.setTreenmSysDept(deptNm);//部门
		mSysStaff.setDictnmXingbie(getSexByChara(data.get(3)));//性别
		mSysStaff.setOrigin(data.get(4)==null?"":data.get(4).trim());//籍贯
		if("".equals(data.get(5)) || null==data.get(5)){
			mSysStaff.setSysflag(0);
		}else if("内置".equals(data.get(5).trim())){
			mSysStaff.setSysflag(1);
		}
		if("".equals(data.get(6)) || null==data.get(6)){
			mSysStaff.setFlag(0);//状态
		}else{
			if("生效".equals(data.get(6).trim())){
				mSysStaff.setFlag(1);//状态
			}else if("未生效".equals(data.get(6).trim())){
				mSysStaff.setFlag(0);//状态
			}
		}
		mSysStaff.setMemo(data.get(7)==null?"":data.get(7).trim());//备注
		sysStaffDao.save(mSysStaff);
	}
	
	//根据性别（汉字）获取性别（状态）
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getSexByChara(String characters){
	    String nm="";
		if("男".equals(characters.trim()) || "女".equals(characters.trim())){
			PageResults mPageResults=sysStaffDao.getNmByChara(characters.trim());
			try{
				List<Map> list=null;
				if(mPageResults.getResults().size()>0){
					list=mPageResults.getResults();
					if(list.size()>0){
						nm=list.get(0).get("NM").toString();
					}
				}
			}catch (Exception e) {
				e.getStackTrace();
			}
		}
		return nm;
	}
	
	//根据部门内码与工程内码查询人员信息
	@SuppressWarnings("rawtypes")
	public List<Map> getStaffInfoByNm(String deptNm,String sysEngineerNm){
		return sysStaffDao.getStaffInfoByNm(deptNm,sysEngineerNm);
	}
	
	/*//根据部门内码与工程内码查询人员信息
	@SuppressWarnings("rawtypes")
	public List<Map> getSysStaffInfoByDeptNm(String deptNm){
		return sysStaffDao.getSysStaffInfoByDeptNm(deptNm);
	}*/
	
	//根据人员编号查询是否登录状态
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean getIsLoginByStaffNm(String staffNm){
		boolean flag=false;
		PageResults mPageResults=sysStaffDao.getIsLoginByStaffNm(staffNm);
		if(mPageResults.getResults().size()>0){
			List<Map> list=(List<Map>)mPageResults.getResults();
		    int i=(Integer)list.get(0).get("ISLOGIN");
		    if(i>0){
		    	flag=true;
		    }
		}
		return flag;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public PageResults getStaffInfoByNm(String staffNm){
		 return sysStaffDao.getStaffInfoByNm(staffNm);
	}

	public void updateStaffEngineerNm(String engineerNm, String staffNm) {
		// TODO Auto-generated method stub
		sysStaffDao.updateStaffEngineerNm(engineerNm,staffNm);
	}

	public PageResults check_phone(String phone) {
		// TODO Auto-generated method stub
		return sysStaffDao.getByPhone(phone);
	}
	
	/**
	 * 通过人员内码为人员信息设置工程信息
	 * */
	public boolean setEngineerInfoByStaffNm(SysEngineerInfoFormBean mSysEngineerInfoFormBean){
		boolean flag=false;
		String sysEngineerNm=mSysEngineerInfoFormBean.getIds();  //工程内码
		String staffNm=mSysEngineerInfoFormBean.getmSysStaff().getNm(); //人员内码
		String tableAName=mSysEngineerInfoFormBean.getmSysRefEngineerInfoBean().getTableAName(); //工程表名
		String tableBName=mSysEngineerInfoFormBean.getmSysRefEngineerInfoBean().getTableBName(); //人员表名
		String refTableName=tableAName+"+"+tableBName;
		
		flag=removeEngineerRefPubInfo(mSysEngineerInfoFormBean,staffNm,refTableName);
		if(!"".equals(sysEngineerNm)){
			flag=insertEngineerRefPubInfo(sysEngineerNm,staffNm,refTableName,tableAName,tableBName);
    	}
		return flag;
	}
	
	/**
	 * 取消工程相应的人员信息
	 * */
	@SuppressWarnings("rawtypes")
	private boolean removeEngineerRefPubInfo(SysEngineerInfoFormBean mSysEngineerInfoFormBean,String staffNm,String refTableName){
		boolean flag=false;
		List<Map> list=mSysRefEngineerDao.getSysRefEngineerInfoByStaffNm(staffNm,refTableName);
		if(list.size()>0){
			int i=mSysRefEngineerDao.deleteSysRefEngineerInfoByStaffNm(staffNm,refTableName);
			if(i>0){
				flag=true;
			}
		}else{
			flag=true;
		}
		flag=setStaffState(staffNm,refTableName);
		return flag;
	}
	
	/**
	 * 通过人员内码获取工程信息添加关联表
	 * */
	private boolean insertEngineerRefPubInfo(String sysEngineerNm,String staffNm,String refTableName,String tableAName,String tableBName){
		boolean flag=false;
		String []sysEngineerNm_=sysEngineerNm.split(",");
		for(int i=0,len=sysEngineerNm_.length;i<len;i++){
			int j=mSysRefEngineerDao.insertStaffRefPubInfo(staffNm,sysEngineerNm_[i],refTableName,tableAName,tableBName);
			if(j>0){
				flag=true;
			}
		}
		flag=setStaffState(staffNm,refTableName);
		return flag;
	}
	
	/**
	 * 根据人员内码查询关联工程信息
	 * */
	@SuppressWarnings("rawtypes")
	private boolean setStaffState(String staffNm,String refTableName){
		boolean flag=false;
		List<Map> list=mSysRefEngineerDao.getSysRefEngineerInfoByStaffNm(staffNm,refTableName);
		if(list.size()>0){
			int j=sysStaffDao.updateStaffSateInfo("'"+staffNm+"'");
			if(j>0){
				flag=true;
			}
		}else{
			sysStaffDao.updateStaffSateInfo_(staffNm);
			flag=true;
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public PageResults getEng(String deptNm) {
		
		return sysStaffDao.getEng(deptNm);
		
	}
	
}

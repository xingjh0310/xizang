package com.lyht.business.system.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.dao.SysRoleDao;
import com.lyht.business.system.formBean.SysRoleFormBean;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 14:27:06
 *说明:  系统角色
*/
@Service
@Scope("prototype")
@Transactional
public class SysRoleService{
	@Resource 
	private SysRoleDao sysRoledao;
	
	//根据id获取实体对象
	    @Transactional(propagation=Propagation.REQUIRED)
		public SysRole s_get(int id){
			SysRole ret_bean= new SysRole();
			if (id>0) {
				ret_bean= sysRoledao.get(id);
			}
			return ret_bean;
		}
	    
		//增加实体对象
	    @Transactional(propagation=Propagation.REQUIRED)
		public SysRole s_create(SysRole bean){
		    //获取新内码
			UUID uuid = UUID.randomUUID();
			String str=uuid.toString().replaceAll("-", "");
			bean.setNm(str);
	        //将内码设置成编码
			sysRoledao.save(bean);
			return bean;
		}
	    
		//修改实体对象
	    @Transactional(propagation=Propagation.REQUIRED)
		public SysRole s_update(SysRole bean){
	        //将内码设置成编码
			sysRoledao.merge(bean);	
			sysRoledao.flush(bean);
			return bean;
		}	
		
	    //删除实体对象
	    @Transactional(propagation=Propagation.REQUIRED)
		public void s_remove(SysRole bean){
			sysRoledao.delete(bean);
		}
		
	    //根据IDS(1,2,3,4,5)删除多个对象
	    @Transactional(propagation=Propagation.REQUIRED)
		public void s_delByIds(String ids){
			  String[] idary=ids.split(",");
			  for(int i=0;i<idary.length;i++)
			  {
				  sysRoledao.deleteById(Integer.parseInt(idary[i]));
			  }
		}
		
	    //根据IDS(1,2,3,4,5)修改多个对象的Flag数值
	    @Transactional(propagation=Propagation.REQUIRED)
		public void s_flagByIds(String ids,Integer flag_new){
			  String[] idary=ids.split(",");
			  for(int i=0;i<idary.length;i++)
			  {
				  SysRole entity = sysRoledao.get(Integer.parseInt(idary[i]));
				  entity.setFlag(flag_new);
				  sysRoledao.update(entity);
			  }
			
		}
		
	    //根据FormBean中的条件查找实体对象List
	    @SuppressWarnings({ "rawtypes" })
		public PageResults s_findAll(SysRoleFormBean formBean){
			return sysRoledao.m_findAll(formBean);
		}
	    
	    //根据属性及属性值查找对象实体
	    @Transactional(propagation=Propagation.REQUIRED)
		public SysRole s_getByProp(String PropName,Object PropValue){
			return sysRoledao.getByProp(PropName, PropValue);
		}
		
		@SuppressWarnings("rawtypes")
		@Transactional(propagation=Propagation.REQUIRED)
		public PageResults s_findByIds(String ids){
			return sysRoledao.m_findByIds(ids);
		}	
		
		//验证角色编号与角色名称是否唯一
		public boolean checkRoleByName(SysRole mSysRole){
		    return sysRoledao.checkRoleByName(mSysRole);
		}
		//验证角色编号与角色名称是否唯一
		public boolean checkRoleByCode(SysRole mSysRole){
			return sysRoledao.checkRoleByCode(mSysRole);
		}

		public List<SysRole> getNameByAccount(String code) {
		    return sysRoledao.getNameByAccount(code);
	    }

		/**
		 * 根据角色内码查询人员名称信息
		 * */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public List<Map> getStaffNameByRoleNm(List<Map> list){
			String str="";
			for(int i=0,len=list.size();i<len;i++){
				Object obj=list.get(i).get("NM");
				if(null!=obj && !"".equals(obj)){
					List<Map> list_=sysRoledao.getStaffNameByRoleNm(obj.toString());
					if(list_.size()>0){
						for(int j=0,len_=list_.size();j<len_;j++){
							Object value=list_.get(j).get("STAFFNAME");
							if(null!=value && !"".equals(value)){
								str+=value.toString()+",";
							}
						}
						if(!"".equals(str)){
							if(",".equals(str.substring(str.length()-1))){
								str=str.substring(0,str.length()-1);
							}
						}
					}
				}
				list.get(i).put("staff", str);
				str="";
			}
			return list;
		}
		
		/**
		 * 根据角色内码删除角色及关联表信息
		 * */
		@SuppressWarnings("rawtypes")
		public void deleteRefTableByRoleNm(List<Map> list){
			if(list.size()>0){
				for(int i=0,len=list.size();i<len;i++){
					Object obj=list.get(i).get("NM");
					if(null!=obj && !"".equals(obj)){
						String value=obj.toString();
						sysRoledao.deleteRefTableByRoleNm(value);
						sysRoledao.deleteRefTableByRoleNm_(value);
					}
				}
			}
		}
		
}

package com.lyht.business.materiel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.materiel.bean.MaterielType;
import com.lyht.business.materiel.dao.MaterielTypeDao;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class MaterielTypeService {
	@Resource
	private MaterielTypeDao materialTypeDao;

	// 查询单位列表
	@SuppressWarnings("rawtypes")
	public List<Map> zTree() {
		return materialTypeDao.zTree();
	}

	//新增根节点
	public MaterielType addPid(MaterielType mMaterielType) {
		
		materialTypeDao.save(mMaterielType);
		return mMaterielType;
	}

	
	@Transactional(propagation=Propagation.REQUIRED)
	public HashMap<String, String> findByIds(String ids){
		
		return materialTypeDao.findByCodes(ids);
	}

	 //根据IDS(1,2,3,4,5)删除多个对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void delByIds(String ids){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
	   materialTypeDao.deleteById(Integer.parseInt(idary[i]));
		}
	}
	//修改节点名称
	public void update(MaterielType mMaterielType) {
		Integer id = mMaterielType.getId();
		
		MaterielType entity = materialTypeDao.get(id);
		entity.setName(mMaterielType.getName());
		
		materialTypeDao.update(entity);
		}
	//删除物料信息
	public void delBaseById(String id) {
		materialTypeDao.delBaseById(id);
		
	}
		

	
}

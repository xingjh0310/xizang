package com.lyht.business.mail.service;

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
import com.lyht.business.mail.bean.MailType;
import com.lyht.business.mail.dao.MailTypeDao;
import com.lyht.business.mail.formBean.MailFormBean;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class MailTypeService {
	@Resource
	private MailTypeDao mailTypeDao;

	// 查询单位列表
	@SuppressWarnings("rawtypes")
	public List<Map> zTree() {
		return mailTypeDao.zTree();
	}

	//新增根节点
	public MailType addPid(MailType mMailType) {
		// 生成编码
		UUID uuid = UUID.randomUUID();
		String replace = uuid.toString().replace("-", "");
		mMailType.setNm(replace);
		mMailType.setFlag(1);
		mailTypeDao.save(mMailType);
		return mMailType;
	}

	
	@Transactional(propagation=Propagation.REQUIRED)
	public HashMap<String, String> findByIds(String ids){
		
		return mailTypeDao.findByCodes(ids);
	}

	 //根据IDS(1,2,3,4,5)删除多个对象
	@Transactional(propagation=Propagation.REQUIRED)
	public void delByIds(String ids){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
	   mailTypeDao.deleteById(Integer.parseInt(idary[i]));
		}
	}
	//修改节点名称
	public void update(MailType mMailType) {
		Integer id = mMailType.getId();
		
		MailType entity = mailTypeDao.get(id);
		entity.setDeptName(mMailType.getDeptName());
		
		mailTypeDao.update(entity);
		}
	//删除物料信息
	public void delBaseById(String id) {
		mailTypeDao.delBaseById(id);
		
	}
/*******************WEB-APP分割线***********************************/
	@SuppressWarnings("rawtypes")
	public PageResults getDeptName(MailFormBean mailFormBean) {
		return mailTypeDao.getDeptName(mailFormBean);
	}
		

	
}

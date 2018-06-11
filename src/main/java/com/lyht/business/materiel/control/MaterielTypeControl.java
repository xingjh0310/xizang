package com.lyht.business.materiel.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.bean.MaterielType;
import com.lyht.business.materiel.service.MaterielTypeService;

@Controller
@Scope("prototype")
public class MaterielTypeControl {
	@Resource
	private MaterielTypeService materialTypeService;

	private static Logger log = Logger.getLogger("MaterielTypeAction");

	// 物资发货基础列表
	@SuppressWarnings("rawtypes")
	public List<Map> zTree( PageResults prs) {
		
			try {
			
			return materialTypeService.zTree();
		
		}catch (Exception e) {
			e.getStackTrace();
			log.error(e);
		}
		return null;


	}
	//	添加根节点
	public RetMessage addPid(MaterielType mMaterielType) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			materialTypeService.addPid(mMaterielType);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增单位成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增单位失败！");
			log.error(e);
			e.printStackTrace();
		}
		
		return ret;
	}
	
		//根据ID获取到List
		public HashMap<String, String> listByIds(String ids){
			HashMap<String, String> findByIds = new HashMap<String, String>();
		  try {
		    findByIds = materialTypeService.findByIds(ids);
		    
		  } catch (Exception e) {
				
		  }
		  return findByIds;
		}
	
	public RetMessage delByIds(String ids){
		RetMessage ret=new RetMessage();
		try {
			materialTypeService.delByIds(ids);
			 ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return ret;
	}
	
	public RetMessage update(MaterielType mMaterielType) {
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
				materialTypeService.update(mMaterielType);
				
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("修改节点名称成功！");
			
		} catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改节点名称失败！");
			log.error(e);
		}
		
		return ret;
	}
	//删除物料信息
	public void delBaseById(String id) {
		materialTypeService.delBaseById(id);
		
	}
	

}

package com.lyht.business.mail.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.mail.bean.MailType;
import com.lyht.business.mail.formBean.MailFormBean;
import com.lyht.business.mail.service.MailTypeService;

@Controller
@Scope("prototype")
public class MailTypeControl {
	@Resource
	private MailTypeService mailTypeService; 

	private static Logger log = Logger.getLogger("MaterielTypeAction");

	// 物资发货基础列表
	@SuppressWarnings("rawtypes")
	public List<Map> zTree( PageResults prs) {
		
			try {
			
			return mailTypeService.zTree();
		
		}catch (Exception e) {
			e.getStackTrace();
			log.error(e);
		}
		return null;


	}
	//	添加根节点
	public RetMessage addPid(MailType mMailType) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			mailTypeService.addPid(mMailType);
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
		    findByIds = mailTypeService.findByIds(ids);
		    
		  } catch (Exception e) {
				
		  }
		  return findByIds;
		}
	
	public RetMessage delByIds(String ids){
		RetMessage ret=new RetMessage();
		try {
			mailTypeService.delByIds(ids);
			 ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			 ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return ret;
	}
	
	public RetMessage update(MailType mMailType) {
		RetMessage ret=new RetMessage();
		try {
				//必须使用此函数，否则AOP中的数值不会发生变化
				mailTypeService.update(mMailType);
				
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
	//删除人员
	public void delBaseById(String id) {
		mailTypeService.delBaseById(id);
		
	}
	/************************WEB-APP分割线**********************************/
	@SuppressWarnings("rawtypes")
	public RetMessage getDeptName(MailFormBean mailFormBean, PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, mailTypeService.getDeptName(mailFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		
		return ret;
	}
	

}

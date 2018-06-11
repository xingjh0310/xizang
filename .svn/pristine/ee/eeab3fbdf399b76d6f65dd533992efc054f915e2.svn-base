package com.lyht.business.contracMng.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContInfo;
import com.lyht.business.contracMng.formbean.ContInfoFormBean;
import com.lyht.business.contracMng.service.ContInfoService;
import com.lyht.business.plan.formBean.DemandFormBean;
import com.lyht.business.system.bean.SysDept;

@Controller
@Scope("prototype")
public class ContInfoControl {
	@Resource
	private ContInfoService contService;
	
	private static Logger log = Logger.getLogger("ContAction");
	
	//保存合同信息
	@Optlog(menuflag="cont_info", opttype = "add")
	public RetMessage saveContInfo(ContInfo infoBean,ContInfo retBean) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retBean,contService.saveContInfo(infoBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增合同成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增合同失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}
	
		//获取根据id合同信息返回对象
		public ContInfo getContInfoById(Integer id) {
			ContInfo mContInfo=new ContInfo();
			try {
				mContInfo=contService.getContInfoById(id);
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
			}
			return mContInfo;
		}
		
		//根据ids删除多条合同信息
		@SuppressWarnings({ "rawtypes"})
		@Optlog(menuflag="cont_info", opttype = "delete")
		public RetMessage delContInfoByIds(String ids) {
			new Exception("测试删除失败");
			RetMessage ret=new RetMessage();
			try {
				contService.delContInfoByIds(ids);
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("删除合同信息成功！");
			} catch (Exception e) {
				ret.setRetflag(RetMessage.RETFLAG_ERROR);
				ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除合同信息失败！");
				log.error(e);
			}
			return ret;
		}
	
	
	//查询合同信息列表
	@SuppressWarnings("rawtypes")
	public RetMessage listContract(ContInfoFormBean contInfoFormBean, PageResults prs,SysDept dept) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contService.listContract(contInfoFormBean,dept));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}
	
	//修改合同信息
	@Optlog(menuflag="cont_info", opttype = "edit")
	public RetMessage update(ContInfo mContInfo, ContInfo retContInfo) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retContInfo,contService.update(mContInfo));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改合同信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改合同信息失败！");
			log.error(e);
		}
		
		return ret;
	}

	public PageResults checkContractNo(String match) {
		// TODO Auto-generated method stub
		return contService.checkContractNo(match);
		
	}

	//查询供货厂商的下拉数据
	public RetMessage queryAllsupply(ContInfoFormBean contInfoFormBean, PageResults prs) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contService.queryAllsupply(contInfoFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	//查询工程信息的下拉数据
	public RetMessage queryAllEngineer(ContInfoFormBean contInfoFormBean, PageResults prs,String engineerNm) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contService.queryAllEngineer(contInfoFormBean,engineerNm));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	//查询要修改的合同信息
	public RetMessage getContInfoById(Integer id, ContInfo mContInfo) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(mContInfo,contService.getContInfoById(id));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	public RetMessage getMaterialInfo(ContInfoFormBean contInfoFormBean, PageResults prs) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contService.getMaterialInfo(contInfoFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}
	
	public RetMessage getMaterialInfoById(ContInfoFormBean contInfoFormBean, PageResults prs) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contService.getMaterialInfoById(contInfoFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	//查询所有的物料信息
	public RetMessage queryAllMaterial(ContInfoFormBean contInfoFormBean, PageResults prs) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contService.queryAllMaterial(contInfoFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	public RetMessage getContractByNo(ContInfoFormBean contInfoFormBean, PageResults prs,SysDept dept) {
		// TODO Auto-generated method stub
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contService.getContractByNo(contInfoFormBean,dept));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	public RetMessage listContract_app(ContInfoFormBean contInfoFormBean, PageResults prs) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contService.listContract_app(contInfoFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	@SuppressWarnings("rawtypes")
	public RetMessage exportContract(ContInfoFormBean contInfoFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response){
		RetMessage ret=new RetMessage();
		try{
			contService.exportContract(contInfoFormBean,prs,request,response);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导出合同信息成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导出合同信息失败！");
			e.printStackTrace();
			log.error("导出合同==错误",e);
		}
		return ret;
	}

	public RetMessage queryContractNub(ContInfoFormBean contInfoFormBean, PageResults prs) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contService.queryContractNub(contInfoFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}

	public RetMessage queryConTotalAmount(ContInfoFormBean contInfoFormBean, PageResults prs) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,contService.queryConTotalAmount(contInfoFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}
		return ret;
	}
}

package com.lyht.business.refund.control;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.refund.bean.Refund;
import com.lyht.business.refund.bean.RefundDetailed;
import com.lyht.business.refund.formBean.RefundFormBean;
import com.lyht.business.refund.service.RefundService;
import com.lyht.business.system.bean.SysDept;

@Controller
@Scope("prototype")
public class RefundControl {
	@Resource
	private RefundService refundService;

	private static Logger log = Logger.getLogger("MaterialAction");

	// 退料退库列表
	@SuppressWarnings("rawtypes")
	public RetMessage list(RefundFormBean refundFormBean, PageResults prs,SysDept dept) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, refundService.list(refundFormBean,dept));
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

	// 新增退料退库
	public RetMessage save(Refund infoRefund, Refund refund) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(refund, refundService.save(infoRefund));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "新增信息失败！");
			e.printStackTrace();
		}
		return ret;
	}

	// 退库退料物资
	public RetMessage saveDetailed(RefundDetailed infoDetailed, RefundDetailed retDetailed) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retDetailed, refundService.saveDetailed(infoDetailed));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "新增信息失败！");
			e.printStackTrace();
		}
		return ret;
	}

	// 查询通讯录信息根据Id
	public Refund getBaseById(Integer id) {
		Refund refund = new Refund();
		try {
			refund = refundService.getBaseById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return refund;
	}

	// 修改通讯录
	public RetMessage update(Refund infoRefund, Refund retRefund) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retRefund, refundService.update(infoRefund));

			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改信息成功！");

		} catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "修改信息失败！");
		}

		return ret;
	}
	//修改物资信息
	public RetMessage updateMaterial(RefundDetailed infoDetailed,RefundDetailed retDetailed) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retDetailed,refundService.updateMaterial(infoDetailed));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改物资信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改物资信息失败！");
			log.error(e);
		}
		
		return ret;
	}
	//根据ids删除多条合同物资信息
	public RetMessage delDetailByIds(String ids) {
		RetMessage ret=new RetMessage();
		try {
			refundService.delDetailByIds(ids);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除物资成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除物资失败！");
			log.error(e);
		}
		return ret;
	}

	// 查询通讯录信息根据Id
	public RetMessage getModelById(Integer id, Refund refund) {

		RetMessage ret = new RetMessage();
		try {
			BeanUtils.copyProperties(refund, refundService.getBaseById(id));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询信息失败！");
			log.error(e);
		}
		return ret;

	}
	// 查询通讯录信息根据Id
		public RetMessage getModelByIds(String ids, Refund refund) {

			RetMessage ret = new RetMessage();
			try {
				BeanUtils.copyProperties(refund, refundService.getBaseByIds(ids));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询信息成功！");
			} catch (Exception e) {
				ret.setRetflag(RetMessage.RETFLAG_ERROR);
				ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询信息失败！");
				log.error(e);
			}
			return ret;

		}
	//删除退库退料列表
	public RetMessage delByIds(String ids) {
		RetMessage ret = new RetMessage();
		try {
			refundService.delByIds(ids);
			// results.clear();//一定要执行此步骤，在AOP中数据就被清除
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "删除数据失败！");
		}

		return ret;

	}
	//删除物资
	public RetMessage delMaterial(String billCode) {
		RetMessage ret = new RetMessage();
		try {
			refundService.delMaterial(billCode);
			// results.clear();//一定要执行此步骤，在AOP中数据就被清除
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "删除数据失败！");
		}

		return ret;

	}
	
	@SuppressWarnings("rawtypes")
	public RetMessage getMaterial(String billCode, PageResults prs) {
		RetMessage ret=new RetMessage();
		try {
			//必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs,refundService.getMaterial(billCode));
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
	//上报
	@SuppressWarnings("rawtypes")
	public RetMessage report(Integer id,PageResults prs) {
		
		RetMessage ret = new RetMessage();
		try {
			refundService.report(id);

			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("上报数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "上报数据失败！");
		}

		return ret;
		
	}
	@SuppressWarnings("rawtypes") 
	public RetMessage downLoad(RefundFormBean refund, PageResults prs, HttpServletRequest request,HttpServletResponse response,SysDept dept){ 
		RetMessage ret = new RetMessage();
		try {
			refundService.downLoad(refund, prs, request, response,dept);
			  ret.setRetflag(RetMessage.RETFLAG_SUCCESS); ret.setMessage("导出通讯录成功！"); 
			  } catch (Exception e) {
			  ret.setRetflag(RetMessage.RETFLAG_ERROR);
			  ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "导出通讯录失败！");
			  e.printStackTrace(); log.error("导出物料基本信息错误", e); 
			 } 
		return ret;
			  
    }
	//审核
	public RetMessage flag(String ids,RefundFormBean refund) {
		RetMessage ret = new RetMessage();
		try {

			refundService.flagByIds(ids, refund);

			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("审核数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "审核数据失败！");
		}

		return ret;
		
	}

	@SuppressWarnings("rawtypes")
	public List<Map> listChartsNum(RefundFormBean refundFormBean) {
		
		List<Map> carCharts=null;
		try {
			carCharts = refundService.listChartsNum(refundFormBean);
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return carCharts;
		
	}

	//时间获取
	@SuppressWarnings("rawtypes")
	public List<Map> getAllYear() {
			List<Map> allYear=null;
			try{
				allYear=refundService.getAllYear();
				return allYear;
			}catch (Exception e) {
				e.getStackTrace();
				log.error("所有年份==错误",e);
			}
			return allYear;
		}
	
	

}

package com.lyht.business.transport.action;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.formBean.SupplyFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.transport.bean.Delivery;
import com.lyht.business.transport.control.TransportControl;
import com.lyht.business.transport.formBean.MaterielFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/transport")
@Results({ @Result(name = "list", location = "/business/destroy/company/list.jsp"),
		@Result(name = "edit", location = "/business/destroy/company/edit.jsp"),
		@Result(name = "editCompany", location = "/business/destroy/companyAdd/list.jsp") })

@Controller
@Scope("prototype")
/**
 * @author 陈洪强 功能 ：查看物资发货列表 list;
 */
public class TransportAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("TransportAction");

	MaterielFormBean materielFormBean = new MaterielFormBean();

	SupplyFormBean fSupplyFormBean = new SupplyFormBean();

	@Resource
	TransportControl transportControl;

	// 物资发货基础列表
	@SuppressWarnings("rawtypes")
	public String list() {
		log.info("物资发货列表==TransportAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();

		ret = transportControl.list(materielFormBean, prs);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)) {
			JSONArray jsonData = new JSONArray();
			hashMap.put("total", 0);
			hashMap.put("rows", jsonData);
		} else {
			hashMap.put("total", prs.getTotalCount());
			hashMap.put("rows", prs.getResults());
		}
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;

	}

	// 导出物资状态数据
	@SuppressWarnings("rawtypes")
	public String downLoad() {
		log.info("导出物资状态数据==MaterielAction.downLoad");
		//当前登录人部门
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res = getResponse();
		transportControl.downLoad(fSupplyFormBean, prs, req, res,dept);
		return null;
	}

	// 发货
	public String deliver() {
		log.info("执行发货==TransportAction.deliver");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		Delivery deliver = new Delivery();

		ret = transportControl.save(materielFormBean.getDeliveryInfoBean(), deliver);
		if(ret.getRetflag().equals(RetMessage.RETFLAG_SUCCESS)){
			transportControl.update(fSupplyFormBean);
		}
		
		hashMap.put("infoBean", deliver);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);

		return null;
	}

	/******************* APP-WEB分割线 *****************************/
	@ResponseBody
	public String app_list() {
		log.info("APP物资发货列表==TransportAction.app_list");

		System.out.println("请求");

		return null;
	}

	public MaterielFormBean getMaterielFormBean() {
		return materielFormBean;
	}

	public void setMaterielFormBean(MaterielFormBean materielFormBean) {
		this.materielFormBean = materielFormBean;
	}

	public SupplyFormBean getfSupplyFormBean() {
		return fSupplyFormBean;
	}

	public void setfSupplyFormBean(SupplyFormBean fSupplyFormBean) {
		this.fSupplyFormBean = fSupplyFormBean;
	}

}

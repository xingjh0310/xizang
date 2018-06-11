package com.lyht.business.contracMng.action;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContChange;
import com.lyht.business.contracMng.bean.ContDetail;
import com.lyht.business.contracMng.control.ContChangeControl;
import com.lyht.business.contracMng.control.ContDetailControl;
import com.lyht.business.contracMng.formbean.ContChangeFormBean;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

@Namespace("/contChange")
	
@Controller
@Scope("prototype")
/**
 * @author 张琦
 */
@Action("/contChange")
public class ContChangeAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("ContChangeAction");
	private ContChangeFormBean contChangeFormBean = new ContChangeFormBean();
	
	@Resource
	ContChangeControl contChangeControl;
	@Resource
	ContDetailControl contDetailControl;

	//物资变更历史记录查询列表
	@SuppressWarnings("rawtypes")
	public String list() {
		log.info("查询合同列表==ContChangeAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contChangeControl.list(contChangeFormBean, prs);
		
		hashMap.put("total", prs.getTotalCount());
		hashMap.put("rows", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	//新增物资变更记录
	@SuppressWarnings("deprecation")
	public String save() {
		log.info("保存物资变更记录==ContChange.save.action");
		ContChange afterContChange = new ContChange();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		RetMessage detailRet = new RetMessage();
		String flag = RetMessage.RETFLAG_SUCCESS;
		
		Map<String, String[]> parameterMap = getRequest().getParameterMap();
		String[] materielIds = parameterMap.get("id");//物料变更记录ID
		String[] codes = parameterMap.get("code");//物资编号
		String[] contractNos = parameterMap.get("contractNo");//合同编号
		String[] engineerCodes = parameterMap.get("engineerCode");//工程编号
		String[] sections = parameterMap.get("section");//标段
		String[] materielCodes = parameterMap.get("materielCode");//物料编号
		String[] materielNames = parameterMap.get("materielName");//物料名称
		String[] materielUnits = parameterMap.get("materielUnit");//物料规格
		String[] preChangeNums = parameterMap.get("preChangeNum");//变更前数量
		String[] afterChangeNums = parameterMap.get("afterChangeNum");//变更后数量
		String[] differences = parameterMap.get("difference");//差额不含税
		String[] differenceTaxs = parameterMap.get("differenceTax");//差额含税
		String[] preChangePrices = parameterMap.get("preChangePrice");//变更前价格
		String[] afterChangePrices = parameterMap.get("afterChangePrice");//变更后价格
		String[] goodDescs = parameterMap.get("goodDesc");//货物描述
		
		//删除物料
		if(CommonUtil.trim(contChangeFormBean.getIds()).length()>0){
			PageResults idsPrs = new PageResults();
			//先修改变更记录里的物资状态
			contChangeControl.delChangeByMaterialIds(contChangeFormBean.getIds(),idsPrs);
			//再删除物资
			contDetailControl.delContDetailByIds(contChangeFormBean.getIds());
		}
		
		if(materielIds != null){
			for(int i=0;i<materielIds.length;i++){
				//变更物资记录
				if(materielIds[i] != null && !"".equals(materielIds[i])){
					//变更后数量,差额,变更后价格不都为空时才保存物资变更记录
					if((afterChangeNums[i] != null && !"".equals(afterChangeNums[i]))
						&& (differenceTaxs[i] != null && !"".equals(differenceTaxs[i]))
						&& (afterChangePrices[i] != null && !"".equals(afterChangePrices[i]))){
						//先变更物资信息表
						ContDetail cd = new ContDetail();
						ContDetail rtnCd = new ContDetail();
						cd.setId(Integer.parseInt(materielIds[i]));
						cd.setCode(codes[i]);
						cd.setEngineerCode(engineerCodes[i]);
						cd.setContractNo(contractNos[i]);
						cd.setSection(sections[i]);
						cd.setMaterielCode(materielCodes[i]);
						cd.setMaterielName(materielNames[i]);
						cd.setMaterielNum(Integer.parseInt(afterChangeNums[i]));
						cd.setMaterielUnit(materielUnits[i]);
						cd.setGoodDesc(goodDescs[i]);
						cd.setProposalPrice(new BigDecimal(afterChangePrices[i]));
						detailRet = contDetailControl.update(cd, rtnCd);
						
						//再生成历史记录
						ContChange preContChange = new ContChange();
						preContChange.setCode(codes[i]);
						preContChange.setContractNo(contractNos[i]);
						preContChange.setEngineerCode(engineerCodes[i]);
						preContChange.setSection(sections[i]);
						preContChange.setMaterielCode(materielCodes[i]);
						preContChange.setMaterielName(materielNames[i]);
						preContChange.setPreChangeNum(Integer.parseInt(preChangeNums[i]));
						preContChange.setAfterChangeNum(Integer.parseInt(afterChangeNums[i]));
						preContChange.setPreChangePrice(new BigDecimal(preChangePrices[i]));
						preContChange.setAfterChangePrice(new BigDecimal(afterChangePrices[i]));
						preContChange.setDifferenceTax(new BigDecimal(differenceTaxs[i]));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time = sdf.format(new Date());
						preContChange.setChangeTime(time);
						SysStaff mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
						String staffCode = mSysStaff==null?"":mSysStaff.getCode();
						preContChange.setOperator(staffCode);
						preContChange.setMaterialState(1);//1.变更物资2.删除物资
						ret = contChangeControl.saveContChange(preContChange, afterContChange);
						
						if(RetMessage.RETFLAG_SUCCESS.equals(ret.getRetflag()) && RetMessage.RETFLAG_SUCCESS.equals(detailRet.getRetflag())){
							flag = RetMessage.RETFLAG_SUCCESS;
						}
					}
					//新增物资记录
				}else {
					
				}
			}
		}else {
			flag = RetMessage.RETFLAG_SUCCESS;
		}
		hashMap.put("mContChange", afterContChange);
		hashMap.put(RetMessage.AJAX_RETFLAG, flag);
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage() + detailRet.getMessage());
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	/************************************web-app分界线*************************************/
	//新增物资变更记录
	@SuppressWarnings("deprecation")
	public String save_app() {
		log.info("保存物资变更记录==ContChange.save.action");
		ContChange afterContChange = new ContChange();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		RetMessage detailRet = new RetMessage();
		String flag = RetMessage.RETFLAG_SUCCESS;
		
		Map<String, String[]> parameterMap = getRequest().getParameterMap();
		String[] materielIds = parameterMap.get("id");//物料变更记录ID
		String[] codes = parameterMap.get("code");//物资编号
		String[] contractNos = parameterMap.get("contractNo");//合同编号
		String[] engineerCodes = parameterMap.get("engineerCode");//工程编号
		String[] sections = parameterMap.get("section");//标段
		String[] materielCodes = parameterMap.get("materielCode");//物料编号
		String[] materielNames = parameterMap.get("materielName");//物料名称
		String[] materielUnits = parameterMap.get("materielUnit");//物料规格
		String[] preChangeNums = parameterMap.get("preChangeNum");//变更前数量
		String[] afterChangeNums = parameterMap.get("afterChangeNum");//变更后数量
		String[] differences = parameterMap.get("difference");//差额不含税
		String[] differenceTaxs = parameterMap.get("differenceTax");//差额含税
		String[] preChangePrices = parameterMap.get("preChangePrice");//变更前价格
		String[] afterChangePrices = parameterMap.get("afterChangePrice");//变更后价格
		String[] goodDescs = parameterMap.get("goodDesc");//货物描述
		String[] operators = parameterMap.get("operator");//变更人
		
		//删除物料
		if(CommonUtil.trim(contChangeFormBean.getIds()).length()>0){
			PageResults idsPrs = new PageResults();
			//先修改变更记录里的物资状态
			contChangeControl.delChangeByMaterialIds(contChangeFormBean.getIds(),idsPrs);
			//再删除物资
			contDetailControl.delContDetailByIds(contChangeFormBean.getIds());
		}
		
		if(materielIds != null){
			for(int i=0;i<materielIds.length;i++){
				//变更物资记录
				if(materielIds[i] != null && !"".equals(materielIds[i])){
					//变更后数量,差额,变更后价格不都为空时才保存物资变更记录
					if((afterChangeNums[i] != null && !"".equals(afterChangeNums[i]))
						&& (differenceTaxs[i] != null && !"".equals(differenceTaxs[i]))
						&& (afterChangePrices[i] != null && !"".equals(afterChangePrices[i]))){
						//先变更物资信息表
						ContDetail cd = new ContDetail();
						ContDetail rtnCd = new ContDetail();
						cd.setId(Integer.parseInt(materielIds[i]));
						cd.setCode(codes[i]);
						cd.setEngineerCode(engineerCodes[i]);
						cd.setContractNo(contractNos[i]);
						cd.setSection(sections[i]);
						cd.setMaterielCode(materielCodes[i]);
						cd.setMaterielName(materielNames[i]);
						cd.setMaterielNum(Integer.parseInt(afterChangeNums[i]));
						cd.setMaterielUnit(materielUnits[i]);
						cd.setGoodDesc(goodDescs[i]);
						cd.setProposalPrice(new BigDecimal(afterChangePrices[i]));
						detailRet = contDetailControl.update(cd, rtnCd);
						
						//再生成历史记录
						ContChange preContChange = new ContChange();
						preContChange.setCode(codes[i]);
						preContChange.setContractNo(contractNos[i]);
						preContChange.setEngineerCode(engineerCodes[i]);
						preContChange.setSection(sections[i]);
						preContChange.setMaterielCode(materielCodes[i]);
						preContChange.setMaterielName(materielNames[i]);
						preContChange.setPreChangeNum(Integer.parseInt(preChangeNums[i]));
						preContChange.setAfterChangeNum(Integer.parseInt(afterChangeNums[i]));
						preContChange.setPreChangePrice(new BigDecimal(preChangePrices[i]));
						preContChange.setAfterChangePrice(new BigDecimal(afterChangePrices[i]));
						preContChange.setDifferenceTax(new BigDecimal(differenceTaxs[i]));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time = sdf.format(new Date());
						preContChange.setChangeTime(time);
						preContChange.setOperator(operators[i]);
						preContChange.setMaterialState(1);//1.变更物资2.删除物资
						ret = contChangeControl.saveContChange(preContChange, afterContChange);
						
						if(RetMessage.RETFLAG_SUCCESS.equals(ret.getRetflag()) && RetMessage.RETFLAG_SUCCESS.equals(detailRet.getRetflag())){
							flag = RetMessage.RETFLAG_SUCCESS;
						}
					}
					//新增物资记录
				}else {
					
				}
			}
		}else {
			flag = RetMessage.RETFLAG_SUCCESS;
		}
		hashMap.put("mContChange", afterContChange);
		hashMap.put(RetMessage.AJAX_RETFLAG, flag);
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage() + detailRet.getMessage());
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	
	public ContChangeFormBean getContChangeFormBean() {
		return contChangeFormBean;
	}
	
	public void setContChangeFormBean(ContChangeFormBean contChangeFormBean) {
		this.contChangeFormBean = contChangeFormBean;
	}
}

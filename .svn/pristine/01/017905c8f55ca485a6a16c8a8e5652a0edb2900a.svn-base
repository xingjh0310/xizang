package com.lyht.business.contracMng.action;

import java.math.BigDecimal;
import java.text.*;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.*;
import com.lyht.business.contracMng.control.*;
import com.lyht.business.contracMng.formbean.ContInfoFormBean;
import com.lyht.business.evaluate.control.ContEvaluateControl;
import com.lyht.business.pub.control.FileUploadControl;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Namespace("/contInfo")
	
@Controller
@Scope("prototype")
/**
 * @author 张琦
 *  合同的增加和修改save; 删除 removeids;
 *  查询单个回显edit; 查询列表list; 
 *  加载下拉框listSelect; 
 */
@Action("/contInfo")
public class ContInfoAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("ContInfoAction");
	private ContInfoFormBean contInfoFormBean = new ContInfoFormBean();
	
	@Resource
	ContInfoControl contInfoControl;
	@Resource
	ContDetailControl contDetailControl;
	@Resource
	ContChangeControl contChangeControl;
	@Resource
	ContEvaluateControl contEvaluateControl;
	@Resource
	private FileUploadControl cFileUploadControl;
	
	// 新增合同信息或修改合同信息web-app公用
	@SuppressWarnings("deprecation")
	public String save() {
		log.info("保存合同对象==contInfo.save");
		ContInfo mContInfo = new ContInfo();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();//保存主合同提示
		RetMessage detailRet = new RetMessage();//保存合同物资明细提示
		RetMessage quaryDetailMsg = new RetMessage();//查询合同物资明细提示
		RetMessage delRet = new RetMessage();//删除合同物资明细提示
		Map<String, String[]> parameterMap = getRequest().getParameterMap();
		String[] materielIds = parameterMap.get("materielId");//物料ID
		String[] materielCodes = parameterMap.get("materielCode");//物料编号
		String[] materielUnits = parameterMap.get("materielUnit");//计量单位
		String[] materielNums = parameterMap.get("materielNum");//数量
		String[] proposalPrices = parameterMap.get("proposalPrice");//总价
		String[] goodDescs = parameterMap.get("goodDesc");//货物描述
		String[] codes = parameterMap.get("code");//物资编号
		
		//判断时间大小是否合理
		if(contInfoFormBean.getContInfoBean() != null){
			boolean flag1 = false;
			boolean flag2 = false;
			boolean flag3 = false;
			try {
				//"招标日期不能大于签订日期"
				flag1 = checkTime(contInfoFormBean.getContInfoBean().getBiddeDate(),
					contInfoFormBean.getContInfoBean().getSignDate());
				//合同开始日期不能大于合同结束日期
				flag2 = checkTime(contInfoFormBean.getContInfoBean().getContractStartDate(),
					contInfoFormBean.getContInfoBean().getContractEndDate());
				//供货开始日期不能大于供货结束日期
				flag3 = checkTime(contInfoFormBean.getContInfoBean().getSupplyStartDate(),
						contInfoFormBean.getContInfoBean().getSupplyEndDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String rtnMsg = "";
			if(flag1){
				rtnMsg = rtnMsg + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;招标日期不能大于签订日期！";
			}
			if(flag2){
				rtnMsg = rtnMsg + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同开始日期不能大于合同结束日期！";
			}
			if(flag3){
				rtnMsg = rtnMsg + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;供货开始日期不能大于供货结束日期！";
			}
			if(!"".equals(rtnMsg)){
				ret.setRetflag(RetMessage.RETFLAG_ERROR);
				ret.setMessage(rtnMsg);
				hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
				hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
				CommonFunction.writeResponse(getResponse(), hashMap);
				return null;
			}
		}
		
		//合同编号
		String contractNo = "";
		if (contInfoFormBean.getContInfoBean().getId() == null || "".equals(contInfoFormBean.getContInfoBean().getId())) {
			//生成合同编号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String match = sdf.format(new Date());
			match = "HT" + match;
			PageResults pg = contInfoControl.checkContractNo(match);
			List results = pg.getResults();
			if(results.size() > 0 && results.get(0) != null){
				contractNo = results.get(0).toString();
				contractNo = contractNo.substring(22, 25);
				int num = Integer.parseInt(contractNo) + 1;
				if(num < 10){
					contractNo = match + "00" + num;
				}else if(num < 100){
					contractNo = match + "0" + num;
				}
			}else {
				contractNo = match + "001";
			}
			
			//合同状态默认为1(1未签订,2已签订)
			contInfoFormBean.getContInfoBean().setContractState(1);
			contInfoFormBean.getContInfoBean().setContractNo(contractNo);
			ret = contInfoControl.saveContInfo(contInfoFormBean.getContInfoBean(), mContInfo);
		} else {
			// 根据id获取旧数据进行修改
			mContInfo = contInfoControl.getContInfoById(contInfoFormBean.getContInfoBean().getId());
			contractNo = mContInfo.getContractNo();
			// 修改合同信息
			ret = contInfoControl.update(contInfoFormBean.getContInfoBean(), mContInfo);
		}
		
		//上传app图片
		cFileUploadControl.saveImageUpload_app(getRequest(),mContInfo.getId()+"",mContInfo.getEngineerCode(),"CONT_INFO");
		if(CommonUtil.trim(contInfoFormBean.getIds()).length()>0){
			delRet = contDetailControl.delContDetailByIds(contInfoFormBean.getIds());
		}
		List<ContDetail> rtnContDetailList = new ArrayList<ContDetail>();
		boolean flag = false;//标记是否进行过保存或者修改
		//再保存合同物资明细
		for(int i=0;materielIds != null && i < materielIds.length;i++){
			//没有物资ID就添加
			if("".equals(materielIds[i])){
				if(!"".equals(materielCodes[i]) && !"".equals(materielNums[i])){
					ContDetail cd = new ContDetail();
					ContDetail rtnCd = new ContDetail();
					flag = true;
					//添加物资明细
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					if(codes != null && codes[i] != null && !"".equals(codes[i])){
						uuid = codes[i];
					}
					cd.setCode(uuid);
					cd.setContractNo(contractNo);
					cd.setEngineerCode(mContInfo.getEngineerCode());
					cd.setGoodDesc(goodDescs[i]);
					cd.setMaterielCode(materielCodes[i]);
					cd.setMaterielName(getMaterielName(materielCodes[i]));
					cd.setMaterielNum("".equals(materielNums[i])?null:Integer.parseInt(materielNums[i]));
					cd.setMaterielUnit(materielUnits[i]);
					cd.setProposalPrice("".equals(proposalPrices[i])?null:new BigDecimal(proposalPrices[i]));
					cd.setSection(mContInfo.getSection());
					detailRet = contDetailControl.saveDetailInfo(cd, rtnCd);
					rtnContDetailList.add(rtnCd);
				}
			}else {
				//有物资ID就更改
				ContDetail cd = new ContDetail();
				ContDetail rtnCd = new ContDetail();
				cd.setId(Integer.parseInt(materielIds[i]));
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				cd.setCode(uuid);
				cd.setContractNo(contractNo);
				cd.setEngineerCode(mContInfo.getEngineerCode());
				cd.setGoodDesc(goodDescs[i]);
				cd.setMaterielCode(materielCodes[i]);
				cd.setMaterielName(getMaterielName(materielCodes[i]));
				cd.setMaterielNum("".equals(materielNums[i])?null:Integer.parseInt(materielNums[i]));
				cd.setMaterielUnit(materielUnits[i]);
				cd.setProposalPrice("".equals(proposalPrices[i])?null:new BigDecimal(proposalPrices[i]));
				cd.setSection(mContInfo.getSection());
				detailRet = contDetailControl.update(cd, rtnCd);
				rtnContDetailList.add(rtnCd);
			}
		}
		
		String retFlag = RetMessage.RETFLAG_SUCCESS;//当主合同和合同物资都保存成功才返回success 
		if(RetMessage.RETFLAG_SUCCESS.equals(ret.getRetflag())
				&& RetMessage.RETFLAG_SUCCESS.equals(delRet.getRetflag())
				&& (RetMessage.RETFLAG_SUCCESS.equals(detailRet.getRetflag()) == flag)){
			retFlag = RetMessage.RETFLAG_SUCCESS;
		}
		hashMap.put("infoBean", mContInfo);
		hashMap.put("contDetailList", rtnContDetailList);
		
		// 写入当前操作 成功状态 success 或 error
		hashMap.put(RetMessage.AJAX_RETFLAG, retFlag);
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage() + detailRet.getMessage());
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	// 删除合同信息
	@SuppressWarnings("rawtypes")
	public String removeids() {
		log.info("批量删除==ContInfoAction.removeids");
		// 获取ids
		String ids = CommonUtil.trim(contInfoFormBean.getIds());
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		//查询合同物资明细的id,DetailIds
		PageResults detailPrs = new PageResults();
		contDetailControl.queryDetailIds(ids,detailPrs);
		//查询合同履约评价id
		PageResults evaluatePrs = new PageResults();
		contEvaluateControl.queryEvaluateIds(ids,evaluatePrs);
		//查询合同物资明细变更记录的id,changeIds
		PageResults changePrs = new PageResults();
		contChangeControl.queryChangeIds(ids,changePrs);
		
		List detailResults = detailPrs.getResults();
		for(Object obj:detailResults){
			Map map = (Map)obj;
			String id = map.get("ID").toString();
			contDetailControl.delContDetailByIds(id);
		}
		List evaluateResults = evaluatePrs.getResults();
		for(Object obj:evaluateResults){
			Map map = (Map)obj;
			String id = map.get("ID").toString();
			contEvaluateControl.delContEvaluateByIds(id);
		}
		List changeResults = changePrs.getResults();
		for(Object obj:changeResults){
			Map map = (Map)obj;
			String id = map.get("ID").toString();
			contChangeControl.delContChangeByIds(id);
		}
		
		//历史记录设置为移除
//		contChangeControl.delContChangeByContIds(ids);
		// 删除主合同
		ret = contInfoControl.delContInfoByIds(ids);
		
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	//查询待处理合同数量
	@SuppressWarnings("rawtypes")
	public String queryContractNub(){
		log.info("查询待处理合同数量==ContInfoAction.queryContractNub");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contInfoControl.queryContractNub(contInfoFormBean, prs);
		hashMap.put("contractNub", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	//查询合同总金额
	@SuppressWarnings("rawtypes")
	public String queryConTotalAmount(){
		log.info("查询待处理合同数量==ContInfoAction.queryConTotalAmount");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contInfoControl.queryConTotalAmount(contInfoFormBean, prs);
		hashMap.put("contractAmount", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	//查询供货厂商的下拉框数据web-app公用
	@SuppressWarnings("rawtypes")
	public String queryAllsupply(){
		log.info("查询供货厂商的下拉框数据==ContInfoAction.queryAllsupply");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contInfoControl.queryAllsupply(contInfoFormBean, prs);
		hashMap.put("supplys", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	//查询所属工程的下拉框数据
	@SuppressWarnings("rawtypes")
	public String queryAllEngineer(){
		log.info("查询供货厂商的下拉框数据==ContInfoAction.queryAllEngineer");
		String engineerNm=this.getRequest().getParameter("engineerNm");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contInfoControl.queryAllEngineer(contInfoFormBean, prs,engineerNm);
		hashMap.put("engineers", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	//查询物料信息的下拉框数据
	@SuppressWarnings("rawtypes")
	public String queryAllMaterial(){
		log.info("查询物料信息的下拉框数据==ContInfoAction.queryAllMaterial");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contInfoControl.queryAllMaterial(contInfoFormBean, prs);
		hashMap.put("materials", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	//查询物料详细信息
	@SuppressWarnings("rawtypes")
	public String getMaterialInfo(){
		log.info("查询物料详细信息==ContInfoAction.getMaterialInfo");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contInfoControl.getMaterialInfo(contInfoFormBean, prs);
		hashMap.put("materialInfo", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	// 根据id查询单位信息和联系人信息跳转修改页面web-app公用
	public String edit() {
		log.info("查询要修改合同信息==ContInfoAction.edit");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		RetMessage retDetail = new RetMessage();
		PageResults prs = new PageResults();//物资明细返回值
		ContInfo mContInfo = new ContInfo();
		ret = contInfoControl.getContInfoById(contInfoFormBean.getContInfoBean().getId(), mContInfo);
		retDetail = contDetailControl.queryContDetailByContNo(mContInfo.getContractNo(), prs);
		
		String retFlag = "";//当主合同和合同物资都查询成功才返回success
		if(RetMessage.RETFLAG_SUCCESS.equals(ret.getRetflag()) 
				&& RetMessage.RETFLAG_SUCCESS.equals(retDetail.getRetflag())){
			retFlag = RetMessage.RETFLAG_SUCCESS;
		}else {
			retFlag = RetMessage.RETFLAG_ERROR;
		}
		
		hashMap.put("infoBean", mContInfo);
		hashMap.put("contDetail", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, retFlag);
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage()+retDetail.getMessage());
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	//合同信息列表及单个合同详细信息
	@SuppressWarnings("rawtypes")
	public String list() {
		log.info("查询合同列表==ContInfoAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		//当前登录人部门
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		
		ret = contInfoControl.listContract(contInfoFormBean, prs,dept);
		
		String cno = "";
		List results = prs.getResults();
		if(results != null && results.size() > 0){
			Object obj = results.get(0);
			cno = ((Map)obj).get("CONTRACTNO").toString();
		}
		RetMessage dRet = new RetMessage();
		PageResults dPrs = new PageResults();
		if(contInfoFormBean.getContInfoBean().getId() != null
			&& contInfoFormBean.getContInfoBean().getId() != 0
			&& !"".equals(cno)){
			dRet = contDetailControl.queryContDetailByContNo(cno, dPrs);
		}
		
		String flag = "";
		if(RetMessage.RETFLAG_SUCCESS.equals(dRet.getRetflag())
			&& RetMessage.RETFLAG_SUCCESS.equals(ret.getRetflag())){
			flag = RetMessage.RETFLAG_SUCCESS;
		}
		
		hashMap.put("total", prs.getTotalCount());
		hashMap.put("rows", prs.getResults());
		hashMap.put("contDetails", dPrs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, flag);
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage() + dRet.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	//通过合同编号查询合同信息及合同物资web-app公用
	@SuppressWarnings("rawtypes")
	public String getContAndMaterialByContNo() {
		log.info("查询合同信息==ContInfoAction.queryContAndMaterialByCOntNo");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		//当前登录人部门
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		
		ret = contInfoControl.getContractByNo(contInfoFormBean, prs,dept);
		
		RetMessage dRet = new RetMessage();
		PageResults dPrs = new PageResults();
		dRet = contDetailControl.queryContDetailByContNo(contInfoFormBean.getContInfoBean().getContractNo(), dPrs);
		
		String flag = "";
		if(RetMessage.RETFLAG_SUCCESS.equals(dRet.getRetflag())
			&& RetMessage.RETFLAG_SUCCESS.equals(ret.getRetflag())){
			flag = RetMessage.RETFLAG_SUCCESS;
		}
		
		hashMap.put("contract", prs.getResults());
		hashMap.put("materials", dPrs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, flag);
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage() + dRet.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	//导出合同信息
	@SuppressWarnings("rawtypes")
	public String exportContract(){
		log.info("导出合同信息==ContInfoAction.exportContract");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		contInfoControl.exportContract(contInfoFormBean,prs,req,res);
		return null;
	}
	
	/************************************web-app分界线**********************************/
	//合同信息列表及单个合同详细信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String list_app() {
		log.info("查询合同列表==ContInfoAction.list_app");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		RetMessage dRet = new RetMessage();
		PageResults dPrs = new PageResults();
		ret = contInfoControl.listContract_app(contInfoFormBean, prs);
		List results = prs.getResults();
		if (CommonUtil.trim(contInfoFormBean.getContInfoBean().getId()).length()>0){
			String cno = "";
			if(results != null && results.size() > 0){
				Object obj = results.get(0);
				cno = ((Map)obj).get("CONTRACT_NO").toString();
			}
			if(contInfoFormBean.getContInfoBean().getId() != null
				&& contInfoFormBean.getContInfoBean().getId() != 0
				&& !"".equals(cno)){
				dRet = contDetailControl.queryContDetailByContNo(cno, dPrs);
			}
		}else {
			for(Object obj:results){
				String[] MATERIEL_NAME = null;
				String[] MATERIEL_NUM = null;
				String[] MATERIEL_UNIT = null;
				String[] PROPOSAL_PRICE = null;
				Map map = (Map)obj;
				if(map.get("MATERIEL_NAME") != null){
					String name = map.get("MATERIEL_NAME").toString();
					MATERIEL_NAME = name.split(",");
				}
				if(map.get("MATERIEL_NUM") != null){
					String name = map.get("MATERIEL_NUM").toString();
					MATERIEL_NUM = name.split(",");
				}
				if(map.get("MATERIEL_UNIT") != null){
					String name = map.get("MATERIEL_UNIT").toString();
					MATERIEL_UNIT = name.split(",");
				}
				if(map.get("PROPOSAL_PRICE") != null){
					String name = map.get("PROPOSAL_PRICE").toString();
					PROPOSAL_PRICE = name.split(",");
				}
				if(MATERIEL_NAME != null){
					List list = new ArrayList();
					for(int i=0;i<MATERIEL_NAME.length;i++){
						Map val = new HashMap();
						val.put("MATERIEL_NAME", MATERIEL_NAME[i]);
						val.put("MATERIEL_NUM", MATERIEL_NUM[i]);
						val.put("MATERIEL_UNIT", MATERIEL_UNIT[i]);
						val.put("PROPOSAL_PRICE", PROPOSAL_PRICE[i]);
						list.add(val);
					}
					map.put("MATERIELS", list);
				}
			}
		}
		
		hashMap.put("total", prs.getTotalCount());
		hashMap.put("rows", results);
		hashMap.put("contDetails", dPrs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	//查询供货厂商的下拉框数据web-app公用
	@SuppressWarnings("rawtypes")
	public String queryAllsupply_app(){
		log.info("查询供货厂商的下拉框数据==ContInfoAction.queryAllsupply");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contInfoControl.queryAllsupply(contInfoFormBean, prs);
		hashMap.put("supplys", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	//查询所属工程的下拉框数据
	@SuppressWarnings("rawtypes")
	public String queryAllEngineer_app(){
		log.info("查询供货厂商的下拉框数据==ContInfoAction.queryAllEngineer");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		String engineerNm=this.getRequest().getParameter("engineerNm");
		ret = contInfoControl.queryAllEngineer(contInfoFormBean, prs,engineerNm);
		hashMap.put("engineers", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	//查询物料信息的下拉框数据
	@SuppressWarnings("rawtypes")
	public String queryAllMaterial_app(){
		log.info("查询物料信息的下拉框数据==ContInfoAction.queryAllMaterial");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contInfoControl.queryAllMaterial(contInfoFormBean, prs);
		hashMap.put("materials", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	//查询物料详细信息
	@SuppressWarnings("rawtypes")
	public String getMaterialInfo_app(){
		log.info("查询物料详细信息==ContInfoAction.getMaterialInfo");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contInfoControl.getMaterialInfo(contInfoFormBean, prs);
		hashMap.put("materialInfo", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	//查询物料详细信息
	@SuppressWarnings("rawtypes")
	public String getMaterialInfoById_app(){
		log.info("查询物料详细信息==ContInfoAction.getMaterialInfo");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = contInfoControl.getMaterialInfoById(contInfoFormBean, prs);
		hashMap.put("materialInfo", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	private boolean checkTime(String firstDate,String lastDate) throws ParseException{
		if(firstDate != null && lastDate != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long firstDateTime = sdf.parse(firstDate).getTime();
			long lastDateTime = sdf.parse(lastDate).getTime();
			if(firstDateTime > lastDateTime){
				return true;
			}
		}
		return false;
	}
	
	private String getMaterielName(String MaterielCode){
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		contInfoFormBean.setIds(MaterielCode);
		ret = contInfoControl.getMaterialInfo(contInfoFormBean, prs);
		if(RetMessage.RETFLAG_SUCCESS.equals(ret.getRetflag())){
			List results = prs.getResults();
			if(results.size()>0){
				Object obj = results.get(0);
				Map map = (Map)obj;
				return map.get("MATERIEL_NAME").toString();
			}
		}
		return "";
	}
	
	public ContInfoFormBean getContInfoFormBean() {
		return contInfoFormBean;
	}
	
	public void setContInfoFormBean(ContInfoFormBean ContInfoFormBean) {
		this.contInfoFormBean = ContInfoFormBean;
	}
}

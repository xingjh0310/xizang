package com.lyht.business.refund.action;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.refund.bean.Refund;
import com.lyht.business.refund.bean.RefundDetailed;
import com.lyht.business.refund.control.RefundControl;
import com.lyht.business.refund.formBean.RefundFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.control.SysDictControl;
import com.lyht.business.system.control.SysStaffControl;
import com.lyht.business.system.formBean.SysStaffFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Namespace("/refund")
@Results({ @Result(name = "list", location = "/business/destroy/company/list.jsp"),
		@Result(name = "edit", location = "/business/destroy/company/edit.jsp"),
		@Result(name = "editCompany", location = "/business/destroy/companyAdd/list.jsp") })

@Controller
@Scope("prototype")
@Action("refund")
/**
 * @author 陈洪强 功能 ：
 */
public class RefundAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("RefundAction");

	RefundFormBean refundFormBean= new RefundFormBean();
	
	private SysStaffFormBean formBean = new SysStaffFormBean();
	
	private File[] file; // 文件

	private String[] fileFileName; // 文件名称

	@Resource
	private RefundControl refundControl;
	@Resource 
	private SysDictControl sysDictControl;
	
	@Resource 
	private SysStaffControl sysStaffControl;

	// 退料退库列表
	@SuppressWarnings("rawtypes")
	public String list() {
		log.info("退料退库列表=sdfsdfds =RefundAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		//当前登录人部门
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		ret = refundControl.list(refundFormBean, prs,dept);
		
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
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;

	}
	/*获取制单人和日期*/
	public String getDate(){
		
		log.info("获取时间和系统人员==RefundAction.getDate");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
		ret.setMessage("查询数据成功！");
		// 获取当前登录人名称和部门
		SysStaff staff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		//SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		String date = DateUtil.getDate();
		
		hashMap.put("staff",staff);
		hashMap.put("date", date);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		
		return null;
	}
	//查询工程
		@SuppressWarnings({ "rawtypes" })
		public String app_eng(){
			log.info("查询工程==SysstaffAction.app_eng");
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			RetMessage ret=new RetMessage();
			PageResults prs=new PageResults(); 
			
			ret = sysStaffControl.getEng(formBean.getInfoBean().getNm(), prs);
			if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
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
			HttpServletResponse response = getResponse();
			response.setHeader("Access-Control-Allow-Origin", "*");
			CommonFunction.writeResponse(getResponse(), hashMap);
			
			return null;
		}

	//增加退料退库
	@SuppressWarnings("unused")
	public String save() {
		log.info("增加退库退料信息==RefundAction.save");
		Refund refund = new Refund();
		RefundDetailed refundDetailed = new RefundDetailed();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		if (refundFormBean.getRefund().getId() == 0) {
			ret = refundControl.save(refundFormBean.getRefund(), refund);
			String billCode = refund.getBillCode();
			refundFormBean.getRefundDetailed().setBillCode(billCode);
			/*保存物资*/
			saveDetailed(refund);
		} else {
			// 根据id获取旧数据进行修改
			refund = refundControl.getBaseById(refundFormBean.getRefund().getId());
			// 修改单位信息
			ret = refundControl.update(refundFormBean.getRefund(), refund);
			/*保存物资*/
			saveDetailed(refund);
		}
		hashMap.put("infoBean", refund);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);

		return null;
	}
	@SuppressWarnings("unused")
	public String saveDetailed(Refund refund){
		RetMessage ret = new RetMessage();
		RetMessage delRet = new RetMessage();
		// 获取当前登录人名称和部门
		SysStaff staff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		String deptNm = staff.getTreenmSysDept();
		String deptName = dept.getName();
		Map<String, String[]> parameterMap = getRequest().getParameterMap();
		
		String[] materialIds = parameterMap.get("materialId");			//物料ID
		String[] materialCode = parameterMap.get("materialCode");		//物料编号
		String[] meaUnit = parameterMap.get("meaUnit");					//计量单位
		String[] materialDesc = parameterMap.get("materialDesc");		//物料描述
		String[] useNum = parameterMap.get("useNum");					//使用数量
		String[] returnNum = parameterMap.get("returnNum");				//返回数量
		String[] identySituation = parameterMap.get("identySituation");	//货物描述
		String[] inforSitution = parameterMap.get("inforSitution");		//货物描述
		String[] useDirection = parameterMap.get("useDirection");		//货物描述
		String[] useTime = parameterMap.get("useTime");					//物资编号
		String[] remark = parameterMap.get("remark");					//物资编号
		
		//删除退库退料物资信息（点击删除没有真正删除点击保存后执行删除）
		if(CommonUtil.trim(refundFormBean.getIds()).length()>0){
			delRet = refundControl.delDetailByIds(refundFormBean.getIds());
		}
		//添加物资
		for(int i=0;materialIds != null && i < materialIds.length;i++){
			//没有物资ID就添加
			if("".equals(materialIds[i])){
				if(!"".equals(materialCode[i]) && !"".equals(meaUnit[i])){
				RefundDetailed refundDetailed = new RefundDetailed();
				RefundDetailed retDetailed = new RefundDetailed();
					refundDetailed.setRefundApply(deptName);
					refundDetailed.setBillCode(refund.getBillCode());
					refundDetailed.setSingleStaff(refund.getSingleStaff());
					refundDetailed.setMeaUnit(meaUnit[i]);
					refundDetailed.setMaterialCode(materialCode[i]);
					refundDetailed.setMaterialDesc(materialDesc[i]);
					refundDetailed.setUseNum("".equals(useNum[i])?null:Integer.parseInt(useNum[i]));
					refundDetailed.setReturnNum("".equals(returnNum[i])?null:Integer.parseInt(returnNum[i]));
					refundDetailed.setIdentySituation(identySituation[i]);
					refundDetailed.setInforSitution(inforSitution[i]);
					refundDetailed.setUseDirection(useDirection[i]);
					refundDetailed.setUseTime(useTime[i]);
					refundDetailed.setRemark(remark[i]);
					ret=refundControl.saveDetailed(refundDetailed,retDetailed);
				}
			}else {
				//有物资ID就更改
				RefundDetailed refundDetailed = new RefundDetailed();
				RefundDetailed retDetailed = new RefundDetailed();
					refundDetailed.setRefundApply(deptName);
					refundDetailed.setId(Integer.parseInt(materialIds[i]));
					refundDetailed.setBillCode(refund.getBillCode());
					refundDetailed.setSingleStaff(refund.getSingleStaff());
					refundDetailed.setMeaUnit(meaUnit[i]);
					refundDetailed.setMaterialCode(materialCode[i]);
					refundDetailed.setMaterialDesc(materialDesc[i]);
					refundDetailed.setUseNum("".equals(useNum[i])?null:Integer.parseInt(useNum[i]));
					refundDetailed.setReturnNum("".equals(returnNum[i])?null:Integer.parseInt(returnNum[i]));
					refundDetailed.setIdentySituation(identySituation[i]);
					refundDetailed.setInforSitution(inforSitution[i]);
					refundDetailed.setUseDirection(useDirection[i]);
					refundDetailed.setUseTime(useTime[i]);
					refundDetailed.setRemark(remark[i]);
					ret = refundControl.updateMaterial(refundDetailed,retDetailed);
			}
		}
		
		return null;
	}
	// 修改退料退库
	@SuppressWarnings({ "rawtypes" })
	public String edit() {
		log.info("修改退料退库==MailAction.edit");

		Refund refund= new Refund();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		RetMessage retDetail = new RetMessage();
		PageResults prs = new PageResults();//物资明细返回值
		ret = refundControl.getModelById(refundFormBean.getRefund().getId(), refund);
		/*根据退库编号查询物资*/
		retDetail=refundControl.getMaterial(refund.getBillCode(),prs);

		String retFlag = "";//当主合同和合同物资都查询成功才返回success
		if(RetMessage.RETFLAG_SUCCESS.equals(ret.getRetflag()) 
				&& RetMessage.RETFLAG_SUCCESS.equals(retDetail.getRetflag())){
			retFlag = RetMessage.RETFLAG_SUCCESS;
		}else {
			retFlag = RetMessage.RETFLAG_ERROR;
		}
		
		hashMap.put("infoBean", refund);
		hashMap.put("detail", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, retFlag);
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage()+retDetail.getMessage());
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);

		return null;

	}
	// 删除退料退库
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public String removeids() {
		log.info("删除退料退库==RefundAction.removeids");
		Refund refund= new Refund();
		
		String ids = refundFormBean.getIds();
		Hashtable hashtable = new Hashtable();
		RetMessage ret = new RetMessage(); // 获取删除的数据
		PageResults prs = new PageResults();
		// 删除数据
		ret = refundControl.getModelByIds(ids, refund);
		
		refundControl.delMaterial(refund.getBillCode());
		
		ret = refundControl.delByIds(ids);

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);

		return null;
	}
	//上报
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public String report() {
		log.info("上报==RefundAction.report");
		Refund refund= new Refund();
		
		Hashtable hashtable = new Hashtable();
		RetMessage ret = new RetMessage(); // 获取删除的数据
		PageResults prs = new PageResults();
	
		ret=refundControl.report(refundFormBean.getRefund().getId(),prs);

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashtable);

		return null;
	}
	//导出列表
	@SuppressWarnings("rawtypes")
	public String downLoad() {
		log.info("导出列表==RefundAction.downLoad");
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res = getResponse();
		refundControl.downLoad(refundFormBean, prs, req, res,dept);
		return null;
	}	
	//审核功能
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String flag(){
		log.info("审核==RefundAction.flag");
		SysStaff staff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		//SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		String date = DateUtil.getDate();
		Hashtable hashtable = new Hashtable();
		RetMessage ret = new RetMessage();
		String ids = refundFormBean.getIds();
		refundFormBean.getRefund().setAuditStaff(staff.getName());
		refundFormBean.getRefund().setAuditDate(date);
		ret=refundControl.flag(ids,refundFormBean);
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	
	
	/******************APP-WEB分割线*****************************/
	@SuppressWarnings("rawtypes")
	public String app_list() {
		log.info("退料退库列表==RefundAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		ret = refundControl.list(refundFormBean, prs,dept);
	
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
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;

	}	
	
	@SuppressWarnings("unused")
	public String app_save(){
		log.info("增加退库退料信息==RefundAction.save");
		Refund refund = new Refund();
		RefundDetailed refundDetailed = new RefundDetailed();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		String date = DateUtil.getDate();
		refundFormBean.getRefund().setSingleDate(date);
		refundFormBean.getRefund().setState(0);
		refundFormBean.getRefund().setUpState(0);
		if (refundFormBean.getRefund().getId() == 0) {
			ret = refundControl.save(refundFormBean.getRefund(), refund);
			String billCode = refund.getBillCode();
			refundFormBean.getRefundDetailed().setBillCode(billCode);
			/*保存物资*/
			//save_appDetailed(refundFormBean);
		} else {
			// 根据id获取旧数据进行修改
			refund = refundControl.getBaseById(refundFormBean.getRefund().getId());
			// 修改单位信息
			ret = refundControl.update(refundFormBean.getRefund(), refund);
			/*保存物资*/
			//save_appDetailed(refundFormBean);
		}
		hashMap.put("infoBean", refund);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	@SuppressWarnings("unused")
	public String save_appDetailed(RefundFormBean refundFormBean){
		RetMessage ret = new RetMessage();
		RetMessage delRet = new RetMessage();
		// 获取当前登录人名称和部门
		
		String deptNm = refundFormBean.getRefund().getSingleStaff();
		String deptName = refundFormBean.getRefundDetailed().getRefundApply();
		Map<String, String[]> parameterMap = getRequest().getParameterMap();
		
		String[] materialIds = parameterMap.get("materialId");			//物料ID
		String[] materialCode = parameterMap.get("materialCode");		//物料编号
		String[] meaUnit = parameterMap.get("meaUnit");					//计量单位
		String[] materialDesc = parameterMap.get("materialDesc");		//物料描述
		String[] useNum = parameterMap.get("useNum");					//使用数量
		String[] returnNum = parameterMap.get("returnNum");				//返回数量
		String[] identySituation = parameterMap.get("identySituation");	//货物描述
		String[] inforSitution = parameterMap.get("inforSitution");		//货物描述
		String[] useDirection = parameterMap.get("useDirection");		//货物描述
		String[] useTime = parameterMap.get("useTime");					//物资编号
		String[] remark = parameterMap.get("remark");					//物资编号
		
		//删除退库退料物资信息（点击删除没有真正删除点击保存后执行删除）
		if(CommonUtil.trim(refundFormBean.getIds()).length()>0){
			delRet = refundControl.delDetailByIds(refundFormBean.getIds());
		}
		//添加物资
		for(int i=0;materialIds != null && i < materialIds.length;i++){
			//没有物资ID就添加
			if("".equals(materialIds[i])){
				if(!"".equals(materialCode[i]) && !"".equals(meaUnit[i])){
				RefundDetailed refundDetailed = new RefundDetailed();
				RefundDetailed retDetailed = new RefundDetailed();
					refundDetailed.setRefundApply(deptName);
					refundDetailed.setBillCode(refundFormBean.getRefund().getBillCode());
					refundDetailed.setSingleStaff(refundFormBean.getRefund().getSingleStaff());
					refundDetailed.setMeaUnit(meaUnit[i]);
					refundDetailed.setMaterialCode(materialCode[i]);
					refundDetailed.setMaterialDesc(materialDesc[i]);
					refundDetailed.setUseNum("".equals(useNum[i])?null:Integer.parseInt(useNum[i]));
					refundDetailed.setReturnNum("".equals(returnNum[i])?null:Integer.parseInt(returnNum[i]));
					refundDetailed.setIdentySituation(identySituation[i]);
					refundDetailed.setInforSitution(inforSitution[i]);
					refundDetailed.setUseDirection(useDirection[i]);
					refundDetailed.setUseTime(useTime[i]);
					refundDetailed.setRemark(remark[i]);
					ret=refundControl.saveDetailed(refundDetailed,retDetailed);
				}
			}else {
				//有物资ID就更改
				RefundDetailed refundDetailed = new RefundDetailed();
				RefundDetailed retDetailed = new RefundDetailed();
					refundDetailed.setRefundApply(deptName);
					refundDetailed.setId(Integer.parseInt(materialIds[i]));
					refundDetailed.setBillCode(refundFormBean.getRefund().getBillCode());
					refundDetailed.setSingleStaff(refundFormBean.getRefund().getSingleStaff());
					refundDetailed.setMeaUnit(meaUnit[i]);
					refundDetailed.setMaterialCode(materialCode[i]);
					refundDetailed.setMaterialDesc(materialDesc[i]);
					refundDetailed.setUseNum("".equals(useNum[i])?null:Integer.parseInt(useNum[i]));
					refundDetailed.setReturnNum("".equals(returnNum[i])?null:Integer.parseInt(returnNum[i]));
					refundDetailed.setIdentySituation(identySituation[i]);
					refundDetailed.setInforSitution(inforSitution[i]);
					refundDetailed.setUseDirection(useDirection[i]);
					refundDetailed.setUseTime(useTime[i]);
					refundDetailed.setRemark(remark[i]);
					ret = refundControl.updateMaterial(refundDetailed,retDetailed);
			}
		}
		
		return null;
	}
	@SuppressWarnings({ "rawtypes", "unused" })
	public String detail(){
		log.info("查看物料详细==MailAction.edit");

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		RetMessage retDetail = new RetMessage();
		PageResults prs = new PageResults();//物资明细返回值
		
		retDetail=refundControl.getMaterial(refundFormBean.getRefund().getBillCode(),prs);
		
		hashMap.put("detail", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);

		return null;
	}
	//审核功能
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public String app_flag(){
			log.info("审核==RefundAction.flag");
			String date = DateUtil.getDate();
			Hashtable hashtable = new Hashtable();
			RetMessage ret = new RetMessage();
			String ids = refundFormBean.getRefund().getId().toString();
			refundFormBean.getRefund().setAuditDate(date);
			ret=refundControl.flag(ids,refundFormBean);
			
			hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
			hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
			// 写入当前操作 成功状态 success 或 error
			HttpServletResponse response = getResponse();
			response.setHeader("Access-Control-Allow-Origin", "*");
			CommonFunction.writeResponse(getResponse(), hashtable);
			return null;
		}
		
		@SuppressWarnings("rawtypes")
		public String app_echarts(){
			log.info("统计退库退料==RefundAction.app_echarts");
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			
			JSONArray carChartLegend=new JSONArray();//
			JSONArray carChartSeries=new JSONArray();//统计数据
			JSONObject json = new JSONObject();
			
			Integer dataNub=12;
			
			String year = refundFormBean.getYear();	//年
			String month = refundFormBean.getMonth();	//月
			String type = refundFormBean.getMaterielType();//物资类型
			
			Calendar mCalendar = Calendar.getInstance();
			
			if (year!=null &&!"".equals(year)) {
				refundFormBean.setYear(year);
			} else {
				Integer nowYear = mCalendar.get(Calendar.YEAR);// 获取当前年份
				year=nowYear.toString();
				refundFormBean.setYear(year);
			}
			
			if( month!=null &&!"".equals(month)){
				if(Integer.valueOf(month)<10){
					year=year+"-0"+month+"-";
				}else{
					year=year+"-"+month+"-";
				}
				refundFormBean.setYear(year);
				mCalendar.set(Calendar.MONTH,Integer.valueOf(month)-1);
				dataNub=mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			}
			
			List<Map> carStroke=refundControl.listChartsNum(refundFormBean);
			
			//物料类型为空查询全部
			 if(type==null||"".equals(type)){
				
			 json.put("name", "全部");
			 json.put("type", "bar");
			 carChartLegend.add("全部");
			
			double [] carArray=createArray(dataNub);//统计图数据无数据用0填充
			for(int j=0;j<carStroke.size();j++){
				Map carMileage=carStroke.get(j);
				Integer carMonth=(Integer) carMileage.get("mon");
				Integer carNub=(Integer) carMileage.get("num");
				Integer carDay=(Integer) carMileage.get("da");
				if(dataNub==12){
					carArray[carMonth - 1] = carNub;
				}else{
					carArray[carDay - 1] = carNub;
				}
			}
				json.put("data", carArray);
				carChartSeries.add(json);
			 }else{
				 String name = "";
				 double [] carArray=createArray(dataNub);//统计图数据无数据用0填充
					for(int j=0;j<carStroke.size();j++){
						Map carMileage=carStroke.get(j);
						Integer carMonth=(Integer) carMileage.get("mon");
						Integer carNub=(Integer) carMileage.get("num");
						Integer carDay=(Integer) carMileage.get("da");
						name= carMileage.get("name").toString();
						if(dataNub==12){
							carArray[carMonth - 1] = carNub;
						}else{
							carArray[carDay - 1] = carNub;
						}
					}
					json.put("name",name);
					json.put("type", "bar");
					json.put("data", carArray);
					carChartSeries.add(json);
					carChartLegend.add(name);
			 }
			 
			hashMap.put("xAxisData", dataNub);
			hashMap.put("carChartLegend", carChartLegend);
			hashMap.put("carChartSeries", carChartSeries);
			HttpServletResponse response = getResponse();
			response.setHeader("Access-Control-Allow-Origin", "*");
			hashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
			CommonFunction.writeResponse(getResponse(), hashMap);
			
			return null;
		}
	
		private double[] createArray(int nub){
			double[] array=new double[nub];
			for(int i=0;i<nub;i++){
				array[i]=0;
			}
			return array;
		}
		//获取年份
		@SuppressWarnings("rawtypes")
		public String getYear(){
			log.info("年份==MileageAction.getYear");
			JSONArray years=new JSONArray();//统计数据
			JSONObject json = new JSONObject();
			
			Calendar mCalendar=Calendar.getInstance();
			Integer nowYear=mCalendar.get(Calendar.YEAR);
			List<Map> allYearList=refundControl.getAllYear();
			if(allYearList==null || "".equals(allYearList)){
				json.put("id", nowYear);
				json.put("year", nowYear);
				years.add(json);
			}else{
				for(int j=0;j<allYearList.size();j++){
					Map carMileage=allYearList.get(j);
					String id =carMileage.get("id").toString();
					String year=carMileage.get("year").toString();
					json.put("id", id);
					json.put("year", year);
					json.put("nowYear", nowYear);
					years.add(json);
				}
				
			}
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("year", years);
			hashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
			HttpServletResponse response = getResponse();
			response.setHeader("Access-Control-Allow-Origin", "*");
			CommonFunction.writeResponse(getResponse(), hashMap);
			return null;
			
		}	
		
	
	
	public RefundFormBean getRefundFormBean() {
		return refundFormBean;
	}
	
	public void setRefundFormBean(RefundFormBean refundFormBean) {
		this.refundFormBean = refundFormBean;
	}
	
	
	

	public SysStaffFormBean getFormBean() {
		return formBean;
	}
	public void setFormBean(SysStaffFormBean formBean) {
		this.formBean = formBean;
	}
	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

}

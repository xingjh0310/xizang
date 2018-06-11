package com.lyht.business.plan.action;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
import com.lyht.business.plan.bean.Arrival;
import com.lyht.business.plan.control.ArrivalControl;
import com.lyht.business.plan.formBean.ArrivalFormBean;
import com.lyht.business.plan.formBean.MaterialDetailFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Namespace("/plan")
@Controller
@Scope("prototype")
@Action("/arrival")
public class ArrivalAction extends BaseLyhtAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 调试日志
	private static Logger log = Logger.getLogger("DemandAction");
	private ArrivalFormBean fArrivalFormBean = new ArrivalFormBean();
	private MaterialDetailFormBean fMaterialDetailFormBean=new MaterialDetailFormBean();
	
	@Resource
	private ArrivalControl cArrivalControl;

	/**
	 * 查看全部到货计划
	 */
	@SuppressWarnings("rawtypes")
	public String queryAllArrivalInfo(){
		log.info("查看全部到货计划==ArrivalAction.queryAllArrivalInfo");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults();
		//当前登录人部门
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		ret=cArrivalControl.queryAllArrivalInfo(fArrivalFormBean, prs,dept);
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
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 新增/修改 到货计划
	 */
	public String saveArrival(){
		log.info("新增/修改 到货计划==ArrivalAction.saveSupply");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		Arrival mArrival=new Arrival();
		ret=cArrivalControl.saveArrival(mArrival,fArrivalFormBean.getmArrival(),fMaterialDetailFormBean.getmMaterialDetail());
		hashMap.put("infoBean", mArrival);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 批量删除信息
	 */
	@SuppressWarnings("rawtypes")
	public String removeids() {
		log.info("批量删除到货计划==ArrivalAction.removeids");
		String ids = CommonUtil.trim(fArrivalFormBean.getIds());// 获取ids
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		prs=cArrivalControl.listByIds(ids);
		ret= cArrivalControl.delByIds(ids, prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 上报 批量上报
	 */
	public String reported(){
		log.info("上报 批量上报到货计划==ArrivalAction.reported");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		ret= cArrivalControl.reported(fArrivalFormBean);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 查询到货计划数量
	 */
	public String queryArrivalNub(){
		log.info("查询到货计划数量==ArrivalAction.queryArrivalNub");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		int arrivalNub=cArrivalControl.queryArrivalNub(fArrivalFormBean);
		hashMap.put("arrivalNub", arrivalNub);
		hashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 到货计划审核
	 */
	public String saveAuditInfo(){
		log.info("到货计划审核==ArrivalAction.saveAuditInfo");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		//获取当前登录人员信息	
	    SysStaff mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
	    fArrivalFormBean.getmArrival().setAuditPerson(mSysStaff.getName());
	    String dateTime=DateUtil.getDateTime();
	    fArrivalFormBean.getmArrival().setAuditTime(dateTime);
		ret= cArrivalControl.saveAuditInfo(fArrivalFormBean);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/*******************WEB-APP分割线****************************/
	@SuppressWarnings("rawtypes")
	public String app_echarts(){
		log.info("到货统计==ArrivalAction.app_echarts");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		JSONArray carChartLegend=new JSONArray();//
		JSONArray carChartSeries=new JSONArray();//统计数据
		JSONObject json = new JSONObject();
		
		Integer dataNub=12;
		
		String year = fArrivalFormBean.getYear();	//年
		String month = fArrivalFormBean.getMonth();	//月
		String type = fArrivalFormBean.getMaterielType();//物资类型
		
		Calendar mCalendar = Calendar.getInstance();
		
		if (year!=null &&!"".equals(year)) {
			fArrivalFormBean.setYear(year);
		} else {
			Integer nowYear = mCalendar.get(Calendar.YEAR);// 获取当前年份
			year=nowYear.toString();
			fArrivalFormBean.setYear(year);
		}
		
		if( month!=null &&!"".equals(month)){
			if(Integer.valueOf(month)<10){
				year=year+"-0"+month+"-";
			}else{
				year=year+"-"+month+"-";
			}
			fArrivalFormBean.setYear(year);
			mCalendar.set(Calendar.MONTH,Integer.valueOf(month)-1);
			dataNub=mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		
		List<Map> carStroke=cArrivalControl.listChartsNum(fArrivalFormBean);
		
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
	
	public ArrivalFormBean getfArrivalFormBean() {
		return fArrivalFormBean;
	}

	public void setfArrivalFormBean(ArrivalFormBean fArrivalFormBean) {
		this.fArrivalFormBean = fArrivalFormBean;
	}

	public MaterialDetailFormBean getfMaterialDetailFormBean() {
		return fMaterialDetailFormBean;
	}

	public void setfMaterialDetailFormBean(MaterialDetailFormBean fMaterialDetailFormBean) {
		this.fMaterialDetailFormBean = fMaterialDetailFormBean;
	}
}

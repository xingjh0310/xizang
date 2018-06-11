package com.lyht.business.plan.action;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.formBean.MaterielBaseFormBean;
import com.lyht.business.plan.bean.Demand;
import com.lyht.business.plan.control.DemandControl;
import com.lyht.business.plan.control.MaterialDetailControl;
import com.lyht.business.plan.formBean.DemandFormBean;
import com.lyht.business.plan.formBean.MaterialDetailFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Namespace("/plan")
@Controller
@Scope("prototype")
@Action("/demand")
public class DemandAction extends BaseLyhtAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 调试日志
	private static Logger log = Logger.getLogger("DemandAction"); 
	private DemandFormBean fDemandFormBean = new DemandFormBean();
	private MaterialDetailFormBean fMaterialDetailFormBean=new MaterialDetailFormBean();
	private MaterielBaseFormBean materielBaseFormBean = new MaterielBaseFormBean();
	
	@Resource
	private DemandControl cDemandControl;
	@Resource
	private MaterialDetailControl cMaterialDetailControl;
	
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;

	/**
	 * 导入需求清单数据
	 */
	public String importDemandInfo(){
		log.info("导入需求清单数据==DemandAction.importDemandInfo");
		RetMessage ret=new RetMessage();
		ret=cDemandControl.importDemandInfo(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), "error");
		}else{
			CommonFunction.writeResponse(getResponse(), "success");
		}
		return null;
	}
	
	/**
	 * 查询需求清单数据
	 */
	@SuppressWarnings({ "rawtypes" })
	public String queryAllDemandInfo(){
		log.info("查询需求清单数据==DemandAction.queryAllDemandInfo");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		PageResults prs=new PageResults();
		ret=cDemandControl.queryAllDemandInfo(fDemandFormBean, prs);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			hashMap.put("total", 0);
			hashMap.put("rows", jsonData);			
		} else {
			for(int i=0;i<prs.getResults().size();i++){
				Map map = (Map)prs.getResults().get(i);
				String code = (String)map.get("PLANCODE");
				PageResults pr = new PageResults();
				cMaterialDetailControl.queryDetailByPlanCode(code,pr);
				map.put("mMaterialDetailList", pr.getResults());
			}
			
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
	 * 导出需求清单
	 */
	@SuppressWarnings("rawtypes")
	public String downLoadDemandInfo(){
		log.info("导出需求清单数据==DemandAction.downLoadDemandInfo");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		cDemandControl.downLoadDemandInfo(fDemandFormBean,prs,req,res);
		return null;
	}
	
	/**
	 * 批量删除信息
	 */
	@SuppressWarnings("rawtypes")
	public String removeids() {
		log.info("批量删除需求清单==DemandAction.removeids");
		String ids = CommonUtil.trim(fDemandFormBean.getIds());// 获取ids
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		prs=cDemandControl.listByIds(ids);
		ret= cDemandControl.delByIds(ids, prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 查看单个需求清单详情
	 */
	@SuppressWarnings({ "rawtypes" })
	public String edit() {
		log.info("查看单个需求清单详情==DemandAction.edit");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		Demand mDemand=new Demand();
		ret=cDemandControl.view(fDemandFormBean.getmDemand().getId(),mDemand);
		hashMap.put("infoBean", mDemand);
		if(ret.getRetflag() == "success"){
			PageResults prs = new PageResults();
			ret=cMaterialDetailControl.queryDetailByPlanCode(mDemand.getPlanCode(),prs);
			hashMap.put("mMaterialDetailList", prs.getResults());
		}
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());	
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 修改需求清单信息
	 */
	@SuppressWarnings("unused")
	public String updateDemandInfo(){
		log.info("修改需求清单信息==DemandAction.updateDemandInfo");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		RetMessage delRet = new RetMessage();
		Map<String, String[]> parameterMap = getRequest().getParameterMap();
		String[] materielIds = parameterMap.get("materielId");//物料ID
		String[] materielCodes = parameterMap.get("materielCode");//物料编号
		String[] materielUnits = parameterMap.get("materielUnit");//计量单位
		String[] materielNums = parameterMap.get("materielNum");//数量
		String[] goodDescs = parameterMap.get("goodDesc");//货物描述
		String[] codes = parameterMap.get("code");//物资编号
		
		//删除退库退料物资信息（点击删除没有真正删除点击保存后执行删除）
		if(CommonUtil.trim(fMaterialDetailFormBean.getIds()).length()>0){
			delRet = cDemandControl.delDetailByIds(fMaterialDetailFormBean.getIds());
		}
		
		Demand mDemand=cDemandControl.getByid(fDemandFormBean.getmDemand().getId());
		ret=cDemandControl.update(fMaterialDetailFormBean,fDemandFormBean.getmDemand(),mDemand,materielIds,materielCodes,materielUnits,materielNums,goodDescs,codes);
		hashMap.put("infoBean", mDemand);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 查询全部需求单位
	 */
	public String queryDemand(){
		log.info("查询全部需求单位==DemandAction.queryDemand");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		List<String> demandList=cDemandControl.queryDemand();
		hashMap.put("demandList", demandList);
		hashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 查询需求清单数量
	 */
	public String queryDemandPlanNub(){
		log.info("查询需求清单数量==DemandAction.queryDemandPlanNub");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		int demandPlanNub=cDemandControl.queryDemandPlanNub(fDemandFormBean);
		hashMap.put("demandPlanNub", demandPlanNub);
		hashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 查询物资信息
	 */
	@SuppressWarnings("rawtypes")
	public String queryMaterielBase() {
		log.info("物料基础信息列表==MaterielBaseAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		
		ret = cDemandControl.queryMaterielBase(materielBaseFormBean,prs);
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
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
		
	}
	
	/******************WEB-APP分割线************************/
	
	/**
	 * 修改需求清单信息
	 */
	public String updateDemandInfo_APP(){
		log.info("修改需求清单信息==DemandAction.updateDemandInfo_APP");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		Demand mDemand=cDemandControl.getByid(fDemandFormBean.getmDemand().getId());
		ret=cDemandControl.updateDemandInfo_APP(fDemandFormBean.getmDemand(),mDemand);
		hashMap.put("infoBean", mDemand);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	/**
	 * 修改物资信息
	 */
	public String updateMaterial_APP(){
		log.info("修改物资信息==DemandAction.updateDemandInfo_APP");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret=new RetMessage();
		ret=cDemandControl.updateMaterial_APP(fMaterialDetailFormBean.getmMaterialDetail(),fDemandFormBean.getmDemand());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public String app_echarts(){
		log.info("统计物资需求==DemandAction.app_echarts");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		JSONArray carChartLegend=new JSONArray();//
		JSONArray carChartSeries=new JSONArray();//统计数据
		JSONObject json = new JSONObject();
		
		Integer dataNub=12;
		
		String year = fDemandFormBean.getYear();	//年
		String month = fDemandFormBean.getMonth();	//月
		String type = fDemandFormBean.getMaterielType();//物资类型
		
		Calendar mCalendar = Calendar.getInstance();
		
		if (year!=null &&!"".equals(year)) {
			fDemandFormBean.setYear(year);
		} else {
			Integer nowYear = mCalendar.get(Calendar.YEAR);// 获取当前年份
			year=nowYear.toString();
			fDemandFormBean.setYear(year);
		}
		
		if( month!=null &&!"".equals(month)){
			if(Integer.valueOf(month)<10){
				year=year+"-0"+month+"-";
			}else{
				year=year+"-"+month+"-";
			}
			fDemandFormBean.setYear(year);
			mCalendar.set(Calendar.MONTH,Integer.valueOf(month)-1);
			dataNub=mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		
		List<Map> carStroke=cDemandControl.listChartsNum(fDemandFormBean);
		
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
					Double carNub=(Double) carMileage.get("num");
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
		List<Map> allYearList=cDemandControl.getAllYear();
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
	
	/**
	 * 上传文件域对象
	 */
	public File[] getFile() {
		return file;
	}
	public void setFile(File[] file) {
		this.file = file;
	}
	/**
	 * 上传文件名
	 */	
	public String[] getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * formBean
	 */
	public DemandFormBean getfDemandFormBean() {
		return fDemandFormBean;
	}

	public void setfDemandFormBean(DemandFormBean fDemandFormBean) {
		this.fDemandFormBean = fDemandFormBean;
	}

	public MaterialDetailFormBean getfMaterialDetailFormBean() {
		return fMaterialDetailFormBean;
	}

	public void setfMaterialDetailFormBean(MaterialDetailFormBean fMaterialDetailFormBean) {
		this.fMaterialDetailFormBean = fMaterialDetailFormBean;
	}

	public MaterielBaseFormBean getMaterielBaseFormBean() {
		return materielBaseFormBean;
	}

	public void setMaterielBaseFormBean(MaterielBaseFormBean materielBaseFormBean) {
		this.materielBaseFormBean = materielBaseFormBean;
	}
	
}

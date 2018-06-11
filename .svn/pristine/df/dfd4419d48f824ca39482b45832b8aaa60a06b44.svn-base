package com.lyht.business.plan.service;

import java.io.BufferedInputStream;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.formBean.MaterielBaseFormBean;
import com.lyht.business.plan.bean.Demand;
import com.lyht.business.plan.bean.MaterialDetail;
import com.lyht.business.plan.dao.DemandDao;
import com.lyht.business.plan.dao.MaterialDetailDao;
import com.lyht.business.plan.formBean.DemandFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

@Service
@Scope("prototype")
@Transactional
public class DemandService {

	@Resource
	private DemandDao dDemandDao;
	@Resource
	private MaterialDetailDao dMaterialDetailDao;
	
	/**
	 * 导入需求清单数据
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@Transactional(propagation=Propagation.REQUIRED)
	public void importDemandInfo(File[] file,String[] fileFileName) throws IOException{
		File[] srcFiles = file;
		InputStream in = null;
		ExcelVersionUtil ev=new ExcelVersionUtil();
		ImportExeclUtil importExeclUtil=new ImportExeclUtil();
		
		for(int i = 0; i < srcFiles.length; i++){
			in = new BufferedInputStream(new FileInputStream(srcFiles[i]));
			
			boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本  
            if (ev.isExcel2007(fileFileName[i])) {  
                isExcel2003 = false;  
            }
            //通过工具栏ImportExeclUtil中的read方法解析excel
            List<List<String>> dataLst =importExeclUtil.read(in,isExcel2003);
            for(int a=1;a<dataLst.size();a++){
            	Demand mDemand=new Demand();
            	MaterialDetail mMaterialDetail=new MaterialDetail();
            	mDemand.setProjectName(dataLst.get(a).get(0));
            	mMaterialDetail.setMaterielCode(dataLst.get(a).get(1));
            	mMaterialDetail.setMaterielName(dataLst.get(a).get(2));
            	mMaterialDetail.setMaterielNum(new BigDecimal(dataLst.get(a).get(3)));
            	mMaterialDetail.setMaterielUnit(dataLst.get(a).get(4));
            	mDemand.setDemandUnit(dataLst.get(a).get(5));
            	mDemand.setPlanDate(dataLst.get(a).get(6));
            	mDemand.setDemandPlace(dataLst.get(a).get(7));
            	mDemand.setLinkMan(dataLst.get(a).get(8));
            	mDemand.setLinkPhone(dataLst.get(a).get(9));
            	mDemand.setPlanYear(Double.valueOf(dataLst.get(a).get(10)).intValue());
            	mDemand.setPlanCode(dataLst.get(a).get(11));
            	mDemand.setEstimateTotalPrice(new BigDecimal(dataLst.get(a).get(12)));
            	mDemand.setConstructDept(dataLst.get(a).get(13));
            	mDemand.setProjectType(dataLst.get(a).get(14));
            	mDemand.setProjectVoltageLeve(dataLst.get(a).get(15));
            	mDemand.setMaterialVoltageLeve(dataLst.get(a).get(16));
            	mDemand.setRemark(dataLst.get(a).get(17));
            	mDemand.setEngineerCode(dataLst.get(a).get(18));
            	mMaterialDetail.setEngineerCode(dataLst.get(a).get(18));
            	/** 计划类型: 001 需求清单; 002 供货计划; 003 到货计划*/
            	mMaterialDetail.setPlanType("001");
            	mMaterialDetail.setContractNo(mDemand.getPlanCode());
            	dMaterialDetailDao.save(mMaterialDetail);
            	int nub=dDemandDao.queryPlayCode(mDemand.getPlanCode());
            	if(nub==0){
            		dDemandDao.save(mDemand);
            	}
            }
	            	
		}
	}
	
	/**
	 * 导出需求清单
	 * @throws IOException 
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public void downLoadDemandInfo(DemandFormBean fDemandFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="需求清单_"+year + "年" + method + "月" +day + "日";
		String title="需求清单";
		String [] tab = {"序号","项目名称","物料名称","数量","计量单位","需求单位","需求日期","需求地点","联系人","联系电话","备注"};
		String [] val = {"PROJECTNAME","MATERIELNAME","MATERIELNUM","MATERIELUNIT","DEMANDUNIT","PLANDATE","DEMANDPLACE","LINKMAN","LINKPHONE","REMARK"};
		List result=dDemandDao.queryAllDemandInfo(fDemandFormBean).getResults();
		String file = ExcelUtils.SellerStat2Excel(result, request, replace,tab,title,val);
		response.setContentType("multipart/form-data");  
		String path = request.getSession().getServletContext().getRealPath("/")+file;
		response.setHeader("Content-Disposition", "attachment;fileName="+new String(file.getBytes("UTF-8"),"ISO8859-1"));
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  
        File files = new File(path);
        FileInputStream inputStream = new FileInputStream(files);
        ServletOutputStream out= response.getOutputStream();
        int b = 0;  
        byte[] buffer = new byte[1024];  
        while (b != -1){  
            b = inputStream.read(buffer);  
            //4.写到输出流(out)中  
            out.write(buffer,0,b);  
        }  
        inputStream.close();  
        out.close();  
        out.flush();
	}
	
	
	/**
	 * 查看全部需求清单
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults queryAllDemandInfo(DemandFormBean fDemandFormBean){
		PageResults retValue =new PageResults();
		if(CommonUtil.trim(fDemandFormBean.getmDemand().getEngineerCode()).length()>0 || CommonUtil.trim(fDemandFormBean.getmDemand().getId()).length()>0){
			retValue=dDemandDao.queryAllDemandInfo(fDemandFormBean);
		}
		return retValue;
	}
	
	/**
	 * 根据IDS获取到List
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults findByIds(String ids){
		return dDemandDao.findByIds(ids);
	}
	
	/**
	 * 根据IDS(1,2,3,4,5)删除多个对象
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void delByIds(String ids){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			int id=Integer.parseInt(idary[i]);
			Demand mDemand=seeGet(id);
			dDemandDao.deleteById(id);
			dMaterialDetailDao.deleteDetailByPlanCode(mDemand.getPlanCode());
		}
	}
	
	/**
	 * 根据id查询需求清单信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Demand seeGet(int id){
		Demand mDemand=new Demand();
		if (id>0) {
			mDemand=dDemandDao.get(id);
		}
		return mDemand;
	}
	
	/**
	 * 修改需求清单信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Demand update(Demand mDemand){
		dDemandDao.merge(mDemand);	
		dDemandDao.flush(mDemand);
	    return mDemand;
	}
	
	/**
	 * 查询全部需求单位
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<String> queryDemand(){
		return dDemandDao.queryDemand();
	}
	
	/**
	 * 查询需求清单数量
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public int queryDemandPlanNub(DemandFormBean fDemandFormBean){
		int nub=0;
		if(CommonUtil.trim(fDemandFormBean.getmDemand().getEngineerCode()).length()>0){
			nub=dDemandDao.queryDemandPlanNub(fDemandFormBean);
		}
		return nub;
	}
	
	/**
	 * 查询物资信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@SuppressWarnings("rawtypes")
	public PageResults queryMaterielBase(MaterielBaseFormBean infoBean) {
		return dDemandDao.queryMaterielBase(infoBean);
	}
	
	/**
	 * String转double，并保留两位小数
	 */
//	private Double StringToDoule(String value){
//		Double d=0.00;
//		DecimalFormat df=new DecimalFormat("#.00");  
//		String result = df.format(Double.parseDouble(value));
//		d=Double.parseDouble(result);
//		return d;
//	}
	// 根据id删除物资
		public void delDetailByIds(String ids) {
			if(CommonUtil.trim(ids).length()>0){
				String[] idary = ids.split(",");
				for (int i = 0; i < idary.length; i++) {
					if(!"".equals(idary[i])){
						dMaterialDetailDao.deleteById(Integer.parseInt(idary[i]));
					}
				}
			}
		}
	
/***************WEB-APP分割线*************************/	
	//查询次数
	@SuppressWarnings("rawtypes")
	public List<Map> listChartsNum(DemandFormBean fDemandFormBean) {
		return dDemandDao.listChartsNum(fDemandFormBean);
	}

	//查询年份
	@SuppressWarnings("rawtypes")
	public List<Map> getAllYear() {
		return dDemandDao.getAllYear();
	}

	
	
}

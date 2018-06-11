package com.lyht.business.plan.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
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
import com.lyht.business.plan.bean.MaterialDetail;
import com.lyht.business.plan.bean.Supply;
import com.lyht.business.plan.dao.MaterialDetailDao;
import com.lyht.business.plan.dao.SupplyDao;
import com.lyht.business.plan.formBean.SupplyFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;

@Service
@Scope("prototype")
@Transactional
public class SupplyService {
	
	@Resource
	private SupplyDao dSupplyDao;
	@Resource
	private MaterialDetailDao dMaterialDetailDao;
	
	/**
	 * 导出供货计划
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(propagation=Propagation.REQUIRED)
	public void downLoadSupplyInfo(SupplyFormBean fSupplyFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response,SysDept dept) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="供货计划_"+year + "年" + method + "月" +day + "日";
		String title="供货计划";
		String [] tab = {"序号","物料名称","物料规格","计量单位","合同编号","合同数量","工程名称","项目单位","供应商名称","申请日期","交货日期","状态"};
		String [] val = {"MATERIELNAME","MATERIALNORMS","MEAUNIT","CONTRACTNO","CONTRACTNUM","ENGINEERNAME","DEPTNAME","SUPPLYFULLNAME","APPLYDATE","CONTRACTDELIVERYDATE","PLANSTATE"};
		List result=dSupplyDao.queryAllSupplyInfo(fSupplyFormBean,dept).getResults();
		for(int i=0;i<result.size();i++){
			String text="";
			Map map =(Map) result.get(i);
			int state=(int) map.get("PLANSTATE");
			if(state==1){
				text="未执行";
			}else if(state==2){
				text="已执行";
			}
			map.put("PLANSTATE", text);
		}
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
	 * 查看全部供货计划
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults queryAllSupplyInfo(SupplyFormBean fSupplyFormBean,SysDept dept){
		PageResults retValue =new PageResults();
		if(CommonUtil.trim(fSupplyFormBean.getmSupply().getEngineerCode()).length()>0 || CommonUtil.trim(fSupplyFormBean.getmSupply().getId()).length()>0){
			retValue =dSupplyDao.queryAllSupplyInfo(fSupplyFormBean,dept);
		}
		return retValue;
	}
	
	/**
	 * 新增供货计划
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Supply saveSupply(Supply mSupply,MaterialDetail mMaterialDetail){
		mMaterialDetail.setEngineerCode(mSupply.getEngineerCode());
		mMaterialDetail.setPlanType("002");//计划类型
		mMaterialDetail.setMaterielCode(mSupply.getMaterielCode());
		mMaterialDetail.setMaterielUnit(mSupply.getMeaUnit());
		mMaterialDetail.setMaterielDesc(mSupply.getMaterialDesc());
		
		
		if(null==mSupply.getSupplierPlanCode() || mSupply.getSupplierPlanCode().equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			Calendar mCalendar=Calendar.getInstance();
			String maxCode=dSupplyDao.queryMaxSupplyCode("GY"+sdf.format(mCalendar.getTime()));//查询最大供应计划编号
			String supplyPlanCode=getSupplyPlanCode(maxCode);
			mSupply.setSupplierPlanCode(supplyPlanCode);
		}
		
		mMaterialDetail.setContractNo(mSupply.getSupplierPlanCode());
		mSupply.setDeliveryState(0);
		dSupplyDao.save(mSupply);
		dMaterialDetailDao.save(mMaterialDetail);//新增物资明细
		return mSupply;
	}
	
	/**
	 * 修改 供货计划
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void UpdateSupply(Supply mSupply,MaterialDetail mMaterialDetail){
		mMaterialDetail.setEngineerCode(mSupply.getEngineerCode());
		mMaterialDetail.setPlanType("002");
		mMaterialDetail.setMaterielCode(mSupply.getMaterielCode());
		mMaterialDetail.setMaterielUnit(mSupply.getMeaUnit());
		mMaterialDetail.setMaterielDesc(mSupply.getMaterialDesc());
		mMaterialDetail.setContractNo(mSupply.getSupplierPlanCode());
		dSupplyDao.merge(mSupply);	
		dSupplyDao.flush(mSupply);
		dMaterialDetailDao.merge(mMaterialDetail);	
		dMaterialDetailDao.flush(mMaterialDetail);
	}
	
	/**
	 * 根据IDS获取到List
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults findByIds(String ids){
		return dSupplyDao.findByIds(ids);
	}
	
	/**
	 * 根据IDS(1,2,3,4,5)删除多个对象
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void delByIds(String ids){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			int id=Integer.parseInt(idary[i]);
			Supply mSupply=seeGet(id);
			dSupplyDao.deleteById(id);
			dMaterialDetailDao.deleteDetailByPlanCode(mSupply.getSupplierPlanCode());
		}	
	}
	
	/**
	 * 更改中标日期
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateBiddingDate(SupplyFormBean fSupplyFormBean){
		dSupplyDao.updateBiddingDate(fSupplyFormBean.getmSupply());
	}
	
	/**
	 * 更新图纸、协议日期
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateTime(SupplyFormBean fSupplyFormBean){
		dSupplyDao.updateTime(fSupplyFormBean.getmSupply());
	}
	
	/**
	 * 根据id查询供货计划信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Supply seeGet(int id){
		Supply mSupply=new Supply();
		if (id>0) {
			mSupply=dSupplyDao.get(id);
		}
		return mSupply;
	}
	
	/**
	 * 查询供货计划信息--APP
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Supply> querySupplyInfo_App(){
		return dSupplyDao.querySupplyInfo_App();
	}
	
	/**
	 * 查询供货计划数量
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public int querySupplyNub(SupplyFormBean fSupplyFormBean){
		int nub=0;
		if(CommonUtil.trim(fSupplyFormBean.getmSupply().getEngineerCode()).length()>0){
			nub=dSupplyDao.querySupplyNub(fSupplyFormBean);
		}
		return nub;
	}
	
	/**
	 * 生成供应计划编号 格式GY+yyyyMMdd+三位顺序数
	 */
	private String getSupplyPlanCode(String maxCode){
		String supplyPlanCode = "";
		if(null==maxCode || maxCode.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			Calendar mCalendar=Calendar.getInstance();
			supplyPlanCode="GY"+sdf.format(mCalendar.getTime())+"001";
		}else{
			String sCode=maxCode.substring(2);
			Long iCode=Long.valueOf(sCode).longValue();
			supplyPlanCode="GY"+(iCode+1);
		}
		return supplyPlanCode;
	}

}

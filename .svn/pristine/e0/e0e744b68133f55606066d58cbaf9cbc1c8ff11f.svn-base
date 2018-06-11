package com.lyht.business.plan.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.bean.Arrival;
import com.lyht.business.plan.bean.MaterialDetail;
import com.lyht.business.plan.dao.ArrivalDao;
import com.lyht.business.plan.dao.MaterialDetailDao;
import com.lyht.business.plan.formBean.ArrivalFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.CommonUtil;

@Service
@Scope("prototype")
@Transactional
public class ArrivalService {

	@Resource
	private ArrivalDao dArrivalDao;
	@Resource
	private MaterialDetailDao dMaterialDetailDao;
	
	/**
	 * 查看全部到货计划
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults queryAllArrivalInfo(ArrivalFormBean fArrivalFormBean,SysDept dept){
		PageResults retValue =new PageResults();
		if(CommonUtil.trim(fArrivalFormBean.getmArrival().getEngineerCode()).length()>0 || CommonUtil.trim(fArrivalFormBean.getmArrival().getId()).length()>0){
			retValue =dArrivalDao.queryAllArrivalInfo(fArrivalFormBean,dept);
		}
		return retValue;
	}
	
	/**
	 * 新增/修改 到货计划
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Arrival saveArrival(Arrival mArrival,MaterialDetail mMaterialDetail){
		mMaterialDetail.setPlanType("003");//计划类型
		mMaterialDetail.setEngineerCode(mArrival.getEngineerCode());//关联工程code
		mMaterialDetail.setMaterielCode(mArrival.getMaterielCode());//物料code
		mMaterialDetail.setMaterielNum(new BigDecimal(mArrival.getActualDeliverie()));//数量
		mMaterialDetail.setMaterielUnit(mArrival.getMeaUnit());//单位
		mMaterialDetail.setMaterielDesc(mArrival.getMaterielDesc());//物资描述
		if(null == mArrival.getArrPlanNum() || mArrival.getArrPlanNum().equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			Calendar mCalendar=Calendar.getInstance();
			String maxArrPlanNum=dArrivalDao.queryMaxArrPlanNum("DH"+sdf.format(mCalendar.getTime()));
			String arrPlanNum=getArrPlanNub(maxArrPlanNum);
			mArrival.setArrPlanNum(arrPlanNum);
		}
		mMaterialDetail.setContractNo(mArrival.getArrPlanNum());
		if(mArrival.getId()==0){
			mArrival.setAuditState(0);//审核状态	0.未审核	 1.审核通过	2.审核拒绝
			dArrivalDao.save(mArrival);
		}else{
			dArrivalDao.merge(mArrival);
			dArrivalDao.flush(mArrival);
		}
		dMaterialDetailDao.merge(mMaterialDetail);//新增物资明细
		dMaterialDetailDao.flush(mMaterialDetail);
		return mArrival;
	}
	
	/**
	 * 根据IDS获取到List
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults findByIds(String ids){
		return dArrivalDao.findByIds(ids);
	}
	
	/**
	 * 根据IDS(1,2,3,4,5)删除多个对象
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void delByIds(String ids){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			int id=Integer.parseInt(idary[i]);
			Arrival mArrival=seeGet(id);
			dArrivalDao.deleteById(id);
			dMaterialDetailDao.deleteDetailByPlanCode(mArrival.getArrPlanNum());
		}	
	}
	
	/**
	 * 根据id查询到货计划信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Arrival seeGet(int id){
		Arrival mArrival=new Arrival();
		if (id>0) {
			mArrival=dArrivalDao.get(id);
		}
		return mArrival;
	}
	
	/**
	 * 上报 批量上报
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void reported(ArrivalFormBean fArrivalFormBean){
		String ids=fArrivalFormBean.getIds();
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			int id=Integer.parseInt(idary[i]);
			dArrivalDao.reported(id,1);
		}
	}
	
	/**
	 * 查询到货计划数量
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public int queryArrivalNub(ArrivalFormBean fArrivalFormBean){
		int nub=0;
		if(CommonUtil.trim(fArrivalFormBean.getmArrival().getEngineerCode()).length()>0){
			nub=dArrivalDao.queryArrivalNub(fArrivalFormBean);
		}
		return nub;
	}
	
	/**
	 * 到货计划审核
	 */
	public void saveAuditInfo(ArrivalFormBean fArrivalFormBean){
		String ids=fArrivalFormBean.getIds();
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			dArrivalDao.saveAuditInfo(fArrivalFormBean.getmArrival(),Integer.parseInt(idary[i]));
		}
	}
	
	/**
	 * 生成到货计划编号 格式DH+yyyyMMdd+三位顺序数
	 */
	private String getArrPlanNub(String maxNub){
		String supplyPlanCode = "";
		if(null==maxNub || maxNub.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			Calendar mCalendar=Calendar.getInstance();
			supplyPlanCode="DH"+sdf.format(mCalendar.getTime())+"001";
		}else{
			String sCode=maxNub.substring(2);
			Long iCode=Long.valueOf(sCode).longValue();
			supplyPlanCode="DH"+(iCode+1);
		}
		return supplyPlanCode;
	}

	/***************WEB-APP分割线*************************/	
	//查询次数
	@SuppressWarnings("rawtypes")
	public List<Map> listChartsNum(ArrivalFormBean fArrivalFormBean) {
		return dArrivalDao.listChartsNum(fArrivalFormBean);
	}
}

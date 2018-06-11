package com.lyht.business.plan.service;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.dao.ContInfoDao;
import com.lyht.business.contracMng.formbean.ContInfoFormBean;
import com.lyht.business.plan.bean.Demand;
import com.lyht.business.plan.bean.MaterialDetail;
import com.lyht.business.plan.dao.MaterialDetailDao;
import com.lyht.business.plan.formBean.MaterialDetailFormBean;

@Service
@Scope("prototype")
@Transactional
public class MaterialDetailService {
	
	@Resource
	private MaterialDetailDao dMaterialDetailDao;
	@Resource
	private ContInfoDao contInfoDao;
	
	/**
	 * 修改物料信息
	 * 
	 * String[] materielIds = parameterMap.get("materielId");//物料ID
	 * String[] materielCodes = parameterMap.get("materielCode");//物料编号
	 * String[] materielUnits = parameterMap.get("materielUnit");//计量单位
	 * String[] materielNums = parameterMap.get("materielNum");//数量
	 * String[] goodDescs = parameterMap.get("goodDesc");//货物描述
	 * String[] codes = parameterMap.get("code");//物资编号
	 * 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(MaterialDetailFormBean fMaterialDetailFormBean,Demand mDemand,String[] materielIds,String[] materielCodes,String[] materielUnits,String[] materielNums,String[] goodDescs,String[] codes){
		for(int i=0;codes != null && i < codes.length;i++){
			if(!"".equals(materielCodes[i]) && !"".equals(materielNums[i])){
				MaterialDetail mMaterialDetail=new MaterialDetail();
				mMaterialDetail.setId(Integer.parseInt("".equals(materielIds[i])?"0":materielIds[i]));
				mMaterialDetail.setEngineerCode(mDemand.getEngineerCode());
				mMaterialDetail.setPlanType("001");
				mMaterialDetail.setContractNo(mDemand.getPlanCode());
				mMaterialDetail.setMaterielCode(materielCodes[i]);
				mMaterialDetail.setMaterielNum(new BigDecimal(materielNums[i]));
				mMaterialDetail.setMaterielUnit(materielUnits[i]);
				mMaterialDetail.setMaterielDesc(goodDescs[i]);
				mMaterialDetail.setMaterielName(getMaterielName(materielCodes[i]));
				dMaterialDetailDao.merge(mMaterialDetail);	
				dMaterialDetailDao.flush(mMaterialDetail);
			}
		}
	}
	
	public void updateMaterial_APP(MaterialDetail mMaterialDetail,Demand mDemand){
		mMaterialDetail.setPlanType("001");
		mMaterialDetail.setContractNo(mDemand.getPlanCode());
		mMaterialDetail.setEngineerCode(mDemand.getEngineerCode());
		mMaterialDetail.setMaterielName(getMaterielName(mMaterialDetail.getMaterielCode()));
		dMaterialDetailDao.merge(mMaterialDetail);	
		dMaterialDetailDao.flush(mMaterialDetail);
	}
	
	/**
	 * 根据物料code查询物料名称
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	private String getMaterielName(String MaterielCode){
		String materielName="";
		ContInfoFormBean contInfoFormBean=new ContInfoFormBean();
		contInfoFormBean.setIds(MaterielCode);
		PageResults prs=contInfoDao.getMaterialInfo(contInfoFormBean);
		List results = prs.getResults();
		if(results.size()>0){
			Object obj = results.get(0);
			Map map = (Map)obj;
			materielName=map.get("MATERIEL_NAME").toString();
		}
		return materielName;
	}
	
	/**
	 * 根据IDS(1,2,3,4,5)删除多个对象
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void delByIds(String ids){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			if(!"".equals(idary[i])){
				int id=Integer.parseInt(idary[i]);
				dMaterialDetailDao.deleteById(id);
			}
		}	
	}
	
	/**
	 * 根据需求清单code查询物资明细
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults queryDetailByPlanCode(String planCode){
		return dMaterialDetailDao.queryDetailByPlanCode(planCode);
	}
	
}

package com.lyht.business.contracMng.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import com.lyht.business.contracMng.bean.ContInfo;
import com.lyht.business.contracMng.dao.ContInfoDao;
import com.lyht.business.contracMng.formbean.ContInfoFormBean;
import com.lyht.business.plan.formBean.DemandFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class ContInfoService {
	@Resource
	private ContInfoDao contInfoDao;

	// 保存合同信息
	public ContInfo saveContInfo(ContInfo bean) {
		contInfoDao.save(bean);
		return bean;
	}

	public PageResults checkContractNo(String match) {
		// TODO Auto-generated method stub
		PageResults pageResults = contInfoDao.checkContractNo(match);
		return pageResults;
	}
	
	// 查询合同列表
	@SuppressWarnings("rawtypes")
	public PageResults listContract(ContInfoFormBean contInfoFormBean,SysDept dept) {
		return contInfoDao.listContract(contInfoFormBean,dept);
	}
	
	// 根据id删除合同信息
	public void delContInfoByIds(String ids) {
		String[] idary = ids.split(",");
		for (int i = 0; i < idary.length; i++) {
			contInfoDao.deleteById(Integer.parseInt(idary[i]));
		}
	}

	//查询供货厂商下拉数据
	@SuppressWarnings("rawtypes")
	public PageResults queryAllsupply(ContInfoFormBean contInfoFormBean) {
		return contInfoDao.queryAllsupply(contInfoFormBean);
	}

	//查询工程信息的下拉数据
	@SuppressWarnings("rawtypes")
	public PageResults queryAllEngineer(ContInfoFormBean contInfoFormBean,String engineerNm) {
		return contInfoDao.queryAllEngineer(contInfoFormBean,engineerNm);
	}

	public ContInfo getContInfoById(Integer id) {
		return contInfoDao.get(id);
	}
	// 修改合同信息
	public ContInfo update(ContInfo contInfo) {
		contInfoDao.merge(contInfo);
		contInfoDao.flush(contInfo);
		return contInfo;
	}

	public PageResults getMaterialInfo(ContInfoFormBean contInfoFormBean) {
		return contInfoDao.getMaterialInfo(contInfoFormBean);
	}
	
	public PageResults getMaterialInfoById(ContInfoFormBean contInfoFormBean) {
		return contInfoDao.getMaterialInfoById(contInfoFormBean);
	}

	//查询所有的物料信息
	@SuppressWarnings("rawtypes")
	public PageResults queryAllMaterial(ContInfoFormBean contInfoFormBean) {
		return contInfoDao.queryAllMaterial(contInfoFormBean);
	}

	@SuppressWarnings("rawtypes")
	public PageResults getContractByNo(ContInfoFormBean contInfoFormBean,SysDept dept) {
		return contInfoDao.listContract(contInfoFormBean,dept);
	}

	@SuppressWarnings("rawtypes")
	public PageResults listContract_app(ContInfoFormBean contInfoFormBean) {
		return contInfoDao.listContract_app(contInfoFormBean);
	}

	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public void exportContract(ContInfoFormBean contInfoFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="合同信息_"+year + "年" + method + "月" +day + "日";
		String title="合同信息";
		String [] tab = {"序号","合同编号","合同标题","工程名称","供货开始时间","供货结束时间","供货商名称","合同额（万元）","签订日期","是否评价"};
		String [] val = {"CONTRACTNO","CONTRACTTITLE","ENGINEERNAME","SUPPLYSTARTDATE","SUPPLYENDDATE","SUPPLYFULLNAME","CONTRACTAMOUNT","SIGNDATE","EVASTATE"};
		List result=contInfoDao.listContract_export(contInfoFormBean).getResults();
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

	public Object queryContractNub(ContInfoFormBean contInfoFormBean) {
		return contInfoDao.queryContractNub(contInfoFormBean);
	}

	public Object queryConTotalAmount(ContInfoFormBean contInfoFormBean) {
		return contInfoDao.queryConTotalAmount(contInfoFormBean);
	}
}

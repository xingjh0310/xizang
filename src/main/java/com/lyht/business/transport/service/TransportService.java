package com.lyht.business.transport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.dao.SupplyDao;
import com.lyht.business.plan.formBean.SupplyFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.transport.bean.Delivery;
import com.lyht.business.transport.dao.TransportDao;
import com.lyht.business.transport.formBean.MaterielFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class TransportService {
	@Resource
	private TransportDao transportDao;
	@Resource
	private SupplyDao dSupplyDao;
	// 物资发货列表
	@SuppressWarnings("rawtypes")
	public PageResults list(MaterielFormBean materielFormBean) {
		return transportDao.list(materielFormBean);
	}
	
	//导出物资发货数据
	@SuppressWarnings("rawtypes")
	public void downLoad(SupplyFormBean fSupplyFormBean, PageResults prs, HttpServletRequest request,HttpServletResponse response,SysDept dept) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="物资发货_"+year + "年" + method + "月" +day + "日";
		String title="物资发货";
		String [] tab = {"序号","物料名称","工程编号","采购编号","合同编号","供货厂商","项目单位","需求单位","计划交货数量","实际发货数量","交货联系人","交货人电话","交货地点"};
		String [] val = {"MATERIELNAME","ENGINEERCODE","SUPPLIERPLANCODE","CONTRACTNO","SUPPLYFULLNAME","DEPTNAME","DEMANDUNIT","PLANDELIVERIE","ACTUALDELIVERIE","DELIVERYCONTACT","LINKMODE","DELIVERYPLACE"};
		List result=dSupplyDao.queryAllSupplyInfo(fSupplyFormBean,dept).getResults();
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
	//保存发货信息
	public Delivery save(Delivery bean) {
		
		String date = DateUtil.getDate();
		bean.setDeliveryTime(date);
		transportDao.save(bean);
		return bean;
	}
	//修改供应计划发货状体
	public void update(SupplyFormBean fSupplyFormBean) {
		transportDao.updateState(fSupplyFormBean);
		
	}
}

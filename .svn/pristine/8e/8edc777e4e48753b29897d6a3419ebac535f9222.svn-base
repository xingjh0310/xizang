package com.lyht.business.distributor.service;

import java.io.BufferedInputStream;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.lyht.business.distributor.bean.Distributor;
import com.lyht.business.distributor.dao.DistributorDao;
import com.lyht.business.distributor.formBean.DistributorFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

@Service
@Scope("prototype")
@Transactional
public class DistributorService {
	
	@Resource
	private DistributorDao dDistributorDao;
	
	/**
	 * 导入供应商信息
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@Transactional(propagation=Propagation.REQUIRED)
	public void importDistributorInfo(File[] file,String[] fileFileName) throws IOException{
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
            	Distributor mDistributor=new Distributor();
            	mDistributor.setSupplierCode(dataLst.get(a).get(0));
            	mDistributor.setSupplyFullName(dataLst.get(a).get(1));
            	mDistributor.setSupplyBuilt(dataLst.get(a).get(2));
            	mDistributor.setRegistrationNo(dataLst.get(a).get(3));
            	mDistributor.setLegalPerson(dataLst.get(a).get(4));
            	mDistributor.setLinkPhone(dataLst.get(a).get(5));
            	mDistributor.setFax(dataLst.get(a).get(6));
            	mDistributor.setAddress(dataLst.get(a).get(7));
            	mDistributor.setRemark(dataLst.get(a).get(8));
            	dDistributorDao.save(mDistributor);
            }
		}
	}
	
	/**
	 * 导出供应商信息
	 * @throws IOException 
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public void downLoadDistributorInfo(DistributorFormBean fDistributorFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="供应商信息_"+year + "年" + method + "月" +day + "日";
		String title="供应商信息";
		String [] tab = {"序号","供应商全称","供应商建成","人员姓名","工商登记证号","法人","联系方式","传真","地址","备注"};
		String [] val = {"SUPPLY_FULL_NAME","SUPPLY_BUILT","NAME","REGISTRATION_NO","LEGAL_PERSON","LINK_PHONE","FAX","ADDRESS","REMARK"};
		List result=dDistributorDao.queryAllDistributor(fDistributorFormBean).getResults();
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
	 * 查看供应商信息
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults queryAllDistributor(DistributorFormBean fDistributorFormBean){
		return dDistributorDao.queryAllDistributor(fDistributorFormBean);
	}
	
	/**
	 * 查看单个供应商信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Distributor seeGet(int id){
		Distributor mDistributor= new Distributor();
		if (id>0) {
			mDistributor= dDistributorDao.get(id);
		}
		return mDistributor;
	}
	
	/**
	 * 新增供应商信息 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Distributor saveDistributor(Distributor mDistributor){
		String maxDisCode=dDistributorDao.queryMaxDisCode();
		String disCode=getDisCode(maxDisCode);
		mDistributor.setSupplierCode(disCode);
		dDistributorDao.save(mDistributor);
		return mDistributor;
	}
	
	/**
	 * 修改供应商信息 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Distributor updateDistributor(Distributor mDistributor){
		dDistributorDao.merge(mDistributor);	
		dDistributorDao.flush(mDistributor);
	    return mDistributor;
	}
	
	/**
	 * 根据IDS获取到List
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults findByIds(String ids){
		return dDistributorDao.findByIds(ids);
	}
	
	/**
	 * 根据IDS(1,2,3,4,5)删除多个对象
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void delByIds(String ids){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			dDistributorDao.deleteById(Integer.parseInt(idary[i]));
		}	
	}
	
	/**
	 * 生成供应商编号 格式GYS+yyyyMMdd+三位顺序数
	 */
	private String getDisCode(String maxCode){
		String DisCode = "";
		if(null==maxCode || maxCode.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			Calendar mCalendar=Calendar.getInstance();
			DisCode="GYS"+sdf.format(mCalendar.getTime())+"001";
		}else{
			String sCode=maxCode.substring(3);
			Long iCode=Long.valueOf(sCode).longValue();
			DisCode="GYS"+(iCode+1);
		}
		return DisCode;
	}
}

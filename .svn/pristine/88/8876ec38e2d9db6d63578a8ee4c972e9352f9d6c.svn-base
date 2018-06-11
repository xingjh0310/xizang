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
import com.lyht.business.pub.dao.FileUploadDao;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.transport.bean.Check;
import com.lyht.business.transport.bean.Delivery;
import com.lyht.business.transport.bean.Transfer;
import com.lyht.business.transport.dao.CheckDao;
import com.lyht.business.transport.dao.MaterielDao;
import com.lyht.business.transport.dao.TransferDao;
import com.lyht.business.transport.formBean.MaterielFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class MaterielService {
	@Resource
	private MaterielDao materielDao;

	@Resource
	private FileUploadDao fileUploadDao;
	@Resource
	private TransferDao  transferDao;
	@Resource
	private CheckDao  checkDao;
	
	// 物资状态列表
	@SuppressWarnings("rawtypes")
	public PageResults list(MaterielFormBean materielFormBean,SysDept dept) {
		return materielDao.list(materielFormBean,dept);
	}
	// 根据id获取物资信息
	public Delivery getDeliveryById(Integer id) {
		Delivery retDelivery = new Delivery();
		if (id > 0) {
			retDelivery = materielDao.get(id);
		}
		return retDelivery;
	}
	
	//导出物资状态数据
	@SuppressWarnings("rawtypes")
	public void downLoad(MaterielFormBean materielFormBean, PageResults prs, HttpServletRequest request,HttpServletResponse response,SysDept dept ) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="物资状态_"+year + "年" + method + "月" +day + "日";
		String title="物资状态";
		String [] tab = {"序号","采购编号","合同编号","供货厂商","负责人","联系电话","物资类型","数量","物流公司","物资状态","交货状态","交货时间","验收状态","验收时间"};
		String [] val = {"ENGINEERCODE","CONTRACTNO","SUPPLIER","SUPPLIERCONTACT","SUPPLIERLINKPHONE","PACKAGENUM","PACKAGENUM","CARRIER","PACKAGENUM","DELIVERSTATE","DELIVERCONFIRMTIME","CONSTRUCTIONSTATE","CONSTRUCTIONCONFIRMTIME"};
		List result=materielDao.list(materielFormBean,dept).getResults();
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
	
	//保存到货信息
	public Transfer save(Transfer bean) {
			
		//String number = getNumber();
		//bean.setHandover(number);
		bean.setReceiveState(1);
		transferDao.save(bean);
		return bean;
	}
	//保存验货信息
	public Check check(Check bean) {
		
		String date = DateUtil.getDate();
		bean.setAcceptanceTime(date);
		//String number = getNumberY();
		//bean.setAcceptance(number);
		checkDao.save(bean);
		return bean;
	}
	/*获取编号*/
	@SuppressWarnings("rawtypes")
	public String getNumber(){
		String tlNum="";
		String tl="";
		String date = DateUtil.getDate();
		String replace = date.replace("-", "");
		tl="DH"+replace;
		
		PageResults number = transferDao.getNumber(tl);
		
		List results = number.getResults();
		
		if(results.size() > 0 && results.get(0) != null){
			tlNum = results.get(0).toString();
			tlNum = tlNum.substring(20, 23);
			int num = Integer.parseInt(tlNum) + 1;
			if(num < 10){
				tlNum = tl + "00" + num;
			}else if(num < 100){
				tlNum = tl + "0" + num;
			}
		}else {
			tlNum = tl + "001";
		}
		
		return tlNum;
	}
	@SuppressWarnings("rawtypes")
	public String getNumberY(){
		String tlNum="";
		String tl="";
		String date = DateUtil.getDate();
		String replace = date.replace("-", "");
		tl="YH"+replace;
		
		PageResults number = checkDao.getNumber(tl);
		
		List results = number.getResults();
		
		if(results.size() > 0 && results.get(0) != null){
			tlNum = results.get(0).toString();
			tlNum = tlNum.substring(20, 23);
			int num = Integer.parseInt(tlNum) + 1;
			if(num < 10){
				tlNum = tl + "00" + num;
			}else if(num < 100){
				tlNum = tl + "0" + num;
			}
		}else {
			tlNum = tl + "001";
		}
		
		return tlNum;
	}
	//添加到货单号
	public void update(String ids, String handover) {
		
		transferDao.update(ids,handover);
	}
	//添加到货单号
	public void updateY(String ids, String handover) {
		
		checkDao.updateY(ids,handover);
	}
	//获取消息数据数量
	public int getMessageNub(MaterielFormBean materielFormBean) {
		
		return materielDao.getMessageNub(materielFormBean);
	}

}

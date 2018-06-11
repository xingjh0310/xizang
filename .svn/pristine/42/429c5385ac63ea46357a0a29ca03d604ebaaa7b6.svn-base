package com.lyht.business.question.service;

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
import com.lyht.business.question.bean.Handler;
import com.lyht.business.question.dao.HandlerDao;
import com.lyht.business.question.dao.SubmitDao;
import com.lyht.business.question.formBean.HandlerFormBean;
import com.lyht.business.question.formBean.SubmitFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;

@Service
@Scope("prototype")
@Transactional
public class HandlerService {
	
	@Resource
	private HandlerDao dHandlerDao;
	@Resource
	private SubmitDao dSubmitDao;
	
	/**
	 * 查询物资问题处理
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults queryQuestionHandler(SubmitFormBean fSubmitFormBean){
		PageResults retValue =new PageResults();
		if(CommonUtil.trim(fSubmitFormBean.getmSubmit().getEngineerCode()).length()>0 || CommonUtil.trim(fSubmitFormBean.getmSubmit().getId()).length()>0){
			retValue=dHandlerDao.queryQuestionHandler(fSubmitFormBean);
		}
		return retValue;
	}
	
	/**
	 * 新增/修改 物资问题处理
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveHandler(Handler mHandler,int SubmitId){
		dSubmitDao.updateQuestionState(SubmitId, 1);
		dHandlerDao.merge(mHandler);
		dHandlerDao.flush(mHandler);
	}
	
	/**
	 * 导出问题处理
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(propagation=Propagation.REQUIRED)
	public void downLoadHandlerInfo(SubmitFormBean fSubmitFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response)throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="物资问题处理_"+year + "年" + method + "月" +day + "日";
		String title="物资问题处理";
		String [] tab = {"序号","标题","物资类型","问题类型","上报单位","上报时间","处理方式","处理时间","处理人","状态"};
		String [] val = {"TITLE","MATERIELNAME","DICTNAME","DEPTNAME","REPORTTIME","DICTNAME","PROCESSTIME","STAFFNAME","STATE"};
		List result=dHandlerDao.queryQuestionHandler(fSubmitFormBean).getResults();
		for(int i=0;i<result.size();i++){
			String text="";
			Map map =(Map) result.get(i);
			int state=(int) map.get("STATE");
			if(state==0){
				text="未处理";
			}else if(state==1){
				text="已处理";
			}
			map.put("STATE", text);
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
/*************************WEB-APP分割线*************************/
	//问题处理统计
	@SuppressWarnings("rawtypes")
	public PageResults count(HandlerFormBean fHandlerFormBean) {
		return dHandlerDao.count(fHandlerFormBean);
	}

}

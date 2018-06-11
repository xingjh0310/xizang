package com.lyht.business.materiel.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.bean.MaterielBase;
import com.lyht.business.materiel.dao.MaterielBaseDao;
import com.lyht.business.materiel.formBean.MaterielBaseFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class MaterielBaseService {
	@Resource
	private MaterielBaseDao materialBaseDao;

	// 物料基础信息
	@SuppressWarnings("rawtypes")
	public PageResults list(MaterielBaseFormBean infoBean) {
		return materialBaseDao.list(infoBean);
	}
	// 物料基础信息
	@SuppressWarnings("rawtypes")
	public PageResults all_list(MaterielBaseFormBean infoBean) {
		return materialBaseDao.all_list(infoBean);
	}
	//	保存物料信息
	public MaterielBase save(MaterielBase bean) {
		// 生成编码
		UUID uuid = UUID.randomUUID();
		String replace = uuid.toString().replace("-", "");
		bean.setMaterielCode(replace);
		materialBaseDao.save(bean);
		return bean;
	}
	// 根据id获取物料基础
	public MaterielBase getBaseById(Integer id) {
		MaterielBase mMaterielBase = new MaterielBase();
		if (id > 0) {
			mMaterielBase = materialBaseDao.get(id);
		}
		return mMaterielBase;
	}
	//修改物料基础信息
	public MaterielBase update(MaterielBase materielBase) {
		
		materialBaseDao.merge(materielBase);
		materialBaseDao.flush(materielBase);
		
		return materielBase;
	}
	
	// 根据id获取物料信息
	@SuppressWarnings("rawtypes")
	public PageResults findByIds(String ids) {
		return materialBaseDao.findByIds(ids);
	}
	// 根据id删除物料信息
	public void delByIds(String ids) {
		String[] idary = ids.split(",");
		for (int i = 0; i < idary.length; i++) {
			materialBaseDao.deleteById(Integer.parseInt(idary[i]));
		}
		
	}
	//导出物料基础数据
	@SuppressWarnings("rawtypes")
	public void downLoad(MaterielBaseFormBean infoBean, PageResults prs, HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="物料基础信息_"+year + "年" + method + "月" +day + "日";
		String title="物料基础信息";
		String [] tab = {"序号","物料名称","物料规格","计量单位","物料描述"};
		String [] val = {"MATERIELNAME","MATERIALNORMS","UNIT","MATERIELDESC"};
		List result=materialBaseDao.list(infoBean).getResults();
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
	//导入物料基础信息
	@SuppressWarnings("static-access")
	public void upLoad(File[] file,String[] fileFileName) throws IOException{
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
            	
            	MaterielBase base = new MaterielBase();
            	
            	base.setMaterielName(dataLst.get(a).get(0));
            	base.setMaterialNorms(dataLst.get(a).get(1));
            	base.setUnit(dataLst.get(a).get(2));
            	base.setMaterielDesc(dataLst.get(a).get(3));
            	int intValue = Double.valueOf(dataLst.get(a).get(4)).intValue();
            	base.setMaterialGroup(intValue+"");
            	base.setMaterielClassify(dataLst.get(a).get(5));
            	base.setUnitDesc(dataLst.get(a).get(6));
            	base.setState(Double.valueOf(dataLst.get(a).get(7)).intValue());
            	base.setRemark(dataLst.get(a).get(8));
            	base.setPrice(dataLst.get(a).get(9));
            	materialBaseDao.save(base);
            }
	            	
		}
	}
/****************************WEB-APP分割线*************************/
	//物料类型
	@SuppressWarnings("rawtypes")
	public PageResults getBase(MaterielBaseFormBean infoBean) {
		return materialBaseDao.getBase(infoBean);
	}

}

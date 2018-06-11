package com.lyht.business.refund.service;

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
import com.lyht.business.refund.bean.Refund;
import com.lyht.business.refund.bean.RefundDetailed;
import com.lyht.business.refund.dao.RefundDao;
import com.lyht.business.refund.dao.RefundDetailedDao;
import com.lyht.business.refund.formBean.RefundFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class RefundService {
	@Resource
	private RefundDao refundDao;
	@Resource
	private RefundDetailedDao refundDetailedDao;
	
	//退库退料 
	@SuppressWarnings("rawtypes")
	public PageResults list(RefundFormBean refundFormBean,SysDept dept) {
		return refundDao.list(refundFormBean,dept);
	}
	//保存退料退库
	public Refund save(Refund bean) {
		// 生成编码
		bean.setState(0);
		bean.setUpState(0);
		String number = getNumber();
		String year = DateUtil.getYear();
		String month = DateUtil.getMonth();
		String day = DateUtil.getDay();
		bean.setBillTitle(year+"年"+month+"月"+day+"日"+bean.getBillTitle()+bean.getLibraryNum());
		bean.setBillCode(number);
		refundDao.save(bean);
		return bean;
	}
	//保存退料退库物资信息
	public RefundDetailed saveDetailed(RefundDetailed infoDetailed) {
		// 生成编码
		refundDetailedDao.save(infoDetailed);
		return infoDetailed;
	}
	// 根据ID查询通讯录
	public Refund getBaseById(Integer id) {
		Refund refund = new Refund();
		if (id > 0) {
			refund = refundDao.get(id);
		}
		return refund;
	}
	// 根据ID查询通讯录
	public Refund getBaseByIds(String ids) {
		Refund refund = new Refund();
		if(CommonUtil.trim(ids).length()>0){
			String[] idary = ids.split(",");
			for (int i = 0; i < idary.length; i++) {
				if(!"".equals(idary[i])){
					int id = Integer.parseInt(idary[i]);
					refund = refundDao.get(id);
				}
			}
		}
		return refund;
	}
	// 修改通讯录信息
	public Refund update(Refund refund) {
		refundDao.merge(refund);
		refundDao.flush(refund);
		return refund;
	}
	// 修改合同物资信息
	public RefundDetailed updateMaterial(RefundDetailed refundDetailed) {
		refundDetailedDao.merge(refundDetailed);
		refundDetailedDao.flush(refundDetailed);
		return refundDetailed;
	}
	// 根据id删除物资
	public void delDetailByIds(String ids) {
		if(CommonUtil.trim(ids).length()>0){
			String[] idary = ids.split(",");
			for (int i = 0; i < idary.length; i++) {
				if(!"".equals(idary[i])){
					refundDetailedDao.deleteById(Integer.parseInt(idary[i]));
				}
			}
		}
	}
	//删除退料
	public void delByIds(String ids) {
		String[] idary = ids.split(",");
		for (int i = 0; i < idary.length; i++) {
			refundDao.deleteById(Integer.parseInt(idary[i]));
		}

	}
	//删除退料物资
	public void delMaterial(String billCode) {
		refundDetailedDao.delMaterial(billCode);

	}
	// 导出
		@SuppressWarnings("rawtypes")
		public void downLoad(RefundFormBean refund, PageResults prs, HttpServletRequest request,HttpServletResponse response,SysDept dept) throws IOException {
			String year = DateUtil.getYear();// 年
			String method = DateUtil.getMonth();// 月
			String day = DateUtil.getDay();// 日
			String replace = "退库退料_" + year + "年" + method + "月" + day + "日";
			String title = "退库退料";
			String[] tab = { "单据编号", "单据标题", "工程编号", "原出库单号", "供应商", "制单人", "制单日期", "状态", "上报状态", "备注", "附件数量" };
			String[] val = { "BILLCODE", "BILLTITLE", "ENGINEERCODE", "LIBRARYNUM", "SUPPLIERCODE", "SINGLESTAFF", "SINGLEDATE","STATE", "UPSTATE", "REMARK", "NUB" };
			List result = refundDao.list(refund,dept).getResults();
			String file = ExcelUtils.SellerStat2Excel(result, request, replace, tab, title, val);
			response.setContentType("multipart/form-data");
			String path = request.getSession().getServletContext().getRealPath("/") + file;
			response.setHeader("Content-Disposition","attachment;fileName=" + new String(file.getBytes("UTF-8"), "ISO8859-1"));
			// 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
			File files = new File(path);
			FileInputStream inputStream = new FileInputStream(files);
			ServletOutputStream out = response.getOutputStream();
			int b = 0;
			byte[] buffer = new byte[1024];
			while (b != -1) {
				b = inputStream.read(buffer);
				// 4.写到输出流(out)中
				out.write(buffer, 0, b);
			}
			inputStream.close();
			out.close();
			out.flush();

		}
	//上报
	public void report(Integer id) {
		
		Refund entity = refundDao.get(id);
		entity.setState(0);
		entity.setUpState(1);
		refundDao.update(entity);
		
	}
	//审核
	public void flagByIds(String ids,RefundFormBean refundFormBean) {
		Refund refund = new Refund();
		String[] idary = ids.split(",");
		for (int i = 0; i < idary.length; i++) {
			 refund = refundDao.get(Integer.parseInt(idary[i]));
			refund.setState(refundFormBean.getRefund().getState());
			refund.setAuditStaff(refundFormBean.getRefund().getAuditStaff());
			refund.setExplain(refundFormBean.getRefund().getExplain());
			refund.setAuditDate(refundFormBean.getRefund().getAuditDate());
			refundDao.update(refund);
		}
	}	
	/*获取编号*/
	@SuppressWarnings("rawtypes")
	public String getNumber(){
		String tlNum="";
		String tl="";
		String date = DateUtil.getDate();
		String replace = date.replace("-", "");
		tl="TL"+replace;
		
		PageResults number = refundDao.getNumber(tl);
		
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
	//查询物料
	@SuppressWarnings("rawtypes")
	public PageResults getMaterial(String billCode) {
		return refundDao.getMaterial(billCode);
	}
	
	//查询退库退料
	@SuppressWarnings("rawtypes")
	public List<Map> listChartsNum(RefundFormBean refundFormBean) {
		return refundDao.listChartsNum(refundFormBean);
	}
	//查询年份
	@SuppressWarnings("rawtypes")
	public List<Map> getAllYear() {
		return refundDao.getAllYear();
	}
	
	
/*************************************************************************
 ******************************* APP***************************************
 *************************************************************************
 *************************************************************************/
	/*//app通讯录
	@SuppressWarnings("rawtypes")
	public PageResults app_list(MailFormBean mailFormBean) {
		return mailDao.app_list(mailFormBean);
	}
	//查询人员
	@SuppressWarnings("rawtypes")
	public PageResults getStaffInfoBydeptCode(String deptCode,MailFormBean mailFormBean) {
		return mailDao.getStaffInfoBydeptCode(deptCode,mailFormBean);
		
	}*/

}

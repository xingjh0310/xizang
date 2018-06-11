package com.lyht.business.mail.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
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
import com.lyht.business.mail.bean.Mail;
import com.lyht.business.mail.dao.MailDao;
import com.lyht.business.mail.formBean.MailFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class MailService {
	@Resource
	private MailDao mailDao;

	// 单位部门树形菜单
	@SuppressWarnings("rawtypes")
	public List<Map> zTree() {
		return mailDao.zTree();
	}

	// 保存通讯录
	public Mail save(Mail bean) {
		// 生成编码
		UUID uuid = UUID.randomUUID();
		String replace = uuid.toString().replace("-", "");
		bean.setNm(replace);
		mailDao.save(bean);
		return bean;
	}

	// 根据ID查询通讯录
	public Mail getBaseById(Integer id) {
		Mail mail = new Mail();
		if (id > 0) {
			mail = mailDao.get(id);
		}
		return mail;
	}

	// 修改通讯录信息
	public Mail update(Mail infoBean) {
		mailDao.merge(infoBean);
		mailDao.flush(infoBean);
		return infoBean;
	}
	//	验证通讯录同意单位人员名称
	public boolean checkMailName(String name,String dept) {
		
		return mailDao.checkMailName(name,dept);
	}
	// 通讯录列表
	@SuppressWarnings("rawtypes")
	public PageResults list(MailFormBean mailFormBean) {
		return mailDao.list(mailFormBean);
	}

	// 根据id删除物料信息
	public void delByIds(String ids) {
		String[] idary = ids.split(",");
		for (int i = 0; i < idary.length; i++) {
			mailDao.deleteById(Integer.parseInt(idary[i]));
		}

	}

	// 导入通讯录
	@SuppressWarnings("static-access")
	public void upLoad(File[] file, String[] fileFileName) throws IOException {
		File[] srcFiles = file;
		InputStream in = null;
		ExcelVersionUtil ev = new ExcelVersionUtil();
		ImportExeclUtil importExeclUtil = new ImportExeclUtil();

		for (int i = 0; i < srcFiles.length; i++) {
			in = new BufferedInputStream(new FileInputStream(srcFiles[i]));

			boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
			if (ev.isExcel2007(fileFileName[i])) {
				isExcel2003 = false;
			}
			// 通过工具栏ImportExeclUtil中的read方法解析excel
			List<List<String>> dataLst = importExeclUtil.read(in, isExcel2003);
			for (int a = 1; a < dataLst.size(); a++) {

				Mail mail = new Mail();
				mail.setEngineerCode(dataLst.get(a).get(0));
				mail.setNm(dataLst.get(a).get(1));
				mail.setStaffCode(dataLst.get(a).get(2));
				mail.setStaffName(dataLst.get(a).get(3));
				mail.setTreenmSysDept(dataLst.get(a).get(4));
				mail.setPosition(dataLst.get(a).get(5));
				mail.setSex(Double.valueOf(dataLst.get(a).get(6)).intValue());
				
				//将字符串转换double类型数字
				double parseDouble = Double.parseDouble(dataLst.get(a).get(7));
				//格式化设置 科学记数法转换普通数字
				DecimalFormat decimalFormat = new DecimalFormat("#");
				String format = decimalFormat.format(parseDouble);
				
				mail.setLinkPhone(format);
				mail.setFixTele(dataLst.get(a).get(8));
				mail.setAddress(dataLst.get(a).get(9));
				mail.setRemark(dataLst.get(a).get(10));
				mailDao.save(mail);
			}

		}

	}

	// 导出通讯录
	@SuppressWarnings("rawtypes")
	public void downLoad(MailFormBean infoBean, PageResults prs, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String year = DateUtil.getYear();// 年
		String method = DateUtil.getMonth();// 月
		String day = DateUtil.getDay();// 日
		String replace = "通讯录_" + year + "年" + method + "月" + day + "日";
		String title = "通讯录";
		String[] tab = { "序号", "工程编号", "内码", "人员编号", "人员名称", "部门编号", "职位", "性别", "移动电话", "固定电话", "地址", "邮箱", "备注" };
		String[] val = { "ENGINEERCODE", "NM", "STAFFCODE", "STAFFNAME", "TREENMSYSDEPT", "POSITION", "SEX",
				"LINKPHONE", "FIXTELE", "ADDRESS", "EMAIL", "REMARK" };
		List result = mailDao.list(infoBean).getResults();
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
	
/*************************************************************************
 ******************************* APP***************************************
 *************************************************************************
 *************************************************************************/
	//app通讯录
	@SuppressWarnings("rawtypes")
	public PageResults app_list(MailFormBean mailFormBean) {
		return mailDao.app_list(mailFormBean);
	}
	//查询人员
	@SuppressWarnings("rawtypes")
	public PageResults getStaffInfoBydeptCode(String deptCode,MailFormBean mailFormBean) {
		return mailDao.getStaffInfoBydeptCode(deptCode,mailFormBean);
		
	}

}

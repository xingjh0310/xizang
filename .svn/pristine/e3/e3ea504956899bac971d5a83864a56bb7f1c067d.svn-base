package com.lyht.business.mail.action;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.mail.bean.Mail;
import com.lyht.business.mail.control.MailControl;
import com.lyht.business.mail.formBean.MailFormBean;
import com.lyht.business.system.control.SysDictControl;
import com.lyht.business.system.formBean.SysEngineerInfoFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Namespace("/mail")
@Results({ @Result(name = "list", location = "/business/destroy/company/list.jsp"),
		@Result(name = "edit", location = "/business/destroy/company/edit.jsp"),
		@Result(name = "editCompany", location = "/business/destroy/companyAdd/list.jsp") })

@Controller
@Scope("prototype")
@Action("mail")
/**
 * @author 陈洪强 功能 ：查看单位树形菜单 ztree;
 */
public class MailAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("MailAction");

	MailFormBean mailFormBean = new MailFormBean();
	
	SysEngineerInfoFormBean engineerInfoFormBean = new SysEngineerInfoFormBean();

	private File[] file; // 文件

	private String[] fileFileName; // 文件名称

	@Resource
	private MailControl mailControl;
	@Resource 
	private SysDictControl sysDictControl;
	// 单位树形菜单
	@SuppressWarnings("rawtypes")
	public String zTree() {
		log.info("单位部门树形菜单==MailAction.zTree");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
		ret.setMessage("查询数据成功！");
		PageResults prs = new PageResults();
		List<Map> zTree = mailControl.zTree(prs);
		JSONArray jsonArray = JSONArray.fromObject(zTree);

		hashMap.put("zTree", jsonArray);

		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}

	// 通讯录列表
	@SuppressWarnings("rawtypes")
	public String list() {
		log.info("通讯录列表==MailAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();

		ret = mailControl.list(mailFormBean, prs);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)) {
			JSONArray jsonData = new JSONArray();
			hashMap.put("total", 0);
			hashMap.put("rows", jsonData);
		} else {
			hashMap.put("total", prs.getTotalCount());
			hashMap.put("rows", prs.getResults());
		}
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;

	}

	// 保存通讯录基础信息
	public String save() {
		log.info("增加通讯录基础信息==MailAction.save");
		Mail mMail = new Mail();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		if (mailFormBean.getMail().getId() == 0) {
			ret = mailControl.save(mailFormBean.getMail(), mMail);
		} else {
			// 根据id获取旧数据进行修改
			mMail = mailControl.getBaseById(mailFormBean.getMail().getId());
			// 修改单位信息
			ret = mailControl.update(mailFormBean.getMail(), mMail);
		}
		hashMap.put("infoBean", mMail);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);

		return null;
	}

	// 修改节点名称
	public String edit() {
		log.info("修改节点名称==MailAction.edit");

		Mail mMail = new Mail();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		ret = mailControl.getBaseById(mailFormBean.getMail().getId(), mMail);

		hashMap.put("infoBean", mMail);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		CommonFunction.writeResponse(getResponse(), hashMap);

		return null;

	}

	// 删除通讯录
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public String removeids() {
		log.info("删除节点==MailAction.removes");
		String ids = mailFormBean.getIds();
		Hashtable hashtable = new Hashtable();
		RetMessage ret = new RetMessage(); // 获取删除的数据
		PageResults prs = new PageResults();
		// 删除数据
		ret = mailControl.delByIds(ids);

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);

		return null;
	}

	// 导入通讯录基础信息
	public String upLoad() {
		log.info("导入通讯录信息==MailAction.upLoad");
		RetMessage ret = new RetMessage();
		ret = mailControl.upLoad(getFile(), getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)) {
			CommonFunction.writeResponse(getResponse(), "error");
		} else {
			CommonFunction.writeResponse(getResponse(), "success");
		}
		return null;
	}

	// 导出通讯录
	@SuppressWarnings("rawtypes")
	public String downLoad() {
		log.info("导出通讯录==MailAction.downLoad");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res = getResponse();
		mailControl.downLoad(mailFormBean, prs, req, res);
		return null;
	}
	//获取职务
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getPot(){
		log.info("查询列表==SysdictAction.getMessageType");
		Hashtable mHashtable = new Hashtable();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults(); 
		String flag="zw";
		mRetMessage= sysDictControl.getMessageType(mPageResults,flag);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray jsonData = new JSONArray();
			mHashtable.put("total", 0);
			mHashtable.put("rows", jsonData);			
		} else {
			mHashtable.put("total", mPageResults.getTotalCount());
			mHashtable.put("rows", mPageResults.getResults());			
		}
		
		mHashtable.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashtable.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());	
		getResponse().setHeader("Access-Control-Allow-Origin", "*"); //允许所有跨域访问
		CommonFunction.writeResponse(getResponse(), mHashtable);
		return null;
	}
	
	//切换工程
	@SuppressWarnings({"unchecked", "deprecation", "rawtypes" })
	public  void sess(){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		String nm = engineerInfoFormBean.getmSysEngineerInfo().getNm();
		
		String attribute =(String) getSession().getAttribute(Constants.SESSION_ENGINEER);
		JSONArray jsonArray = JSONArray.fromObject(attribute);
		
		List<Map> lists = JSONArray.toList(jsonArray,Map.class);//这里的t是Class<T> 
		
		for(int i=0;i<lists.size();i++){
			if(lists.get(i).get("NM").equals(nm)){
				lists.get(i).put("checked","true");
			}else{
				lists.get(i).put("checked","false");
			}
		}
		
		String json=JSONArray.fromObject(lists).toString();
		ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
		ret.setMessage("工程切换成功！");
		getSession().setAttribute(Constants.SESSION_ENGINEER, json);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		CommonFunction.writeResponse(getResponse(), hashMap);

	}
/***************************APP-WEB分割线************************************/
	//app 通讯录列表
	@SuppressWarnings("rawtypes")
	public String app_list(){
		log.info("通讯录列表==MailAction.app_list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();

		ret = mailControl.app_list(mailFormBean,prs);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)) {
			JSONArray jsonData = new JSONArray();
			hashMap.put("total", 0);
			hashMap.put("rows", jsonData);
		} else {
			hashMap.put("total", prs.getTotalCount());
			hashMap.put("rows", getJSONArray(prs));
		}
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(response, hashMap);
		return null;
	}
	
	//查询部门信息，根据部门编码查询所对应的人员信息，返回JSONArray数据
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	private JSONArray getJSONArray(PageResults mPageResults){
		JSONArray mJSONArray=new JSONArray();
		JSONObject mJSONObject = new JSONObject();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		List<Map> list=mPageResults.getResults();
		for(int i=0;i<list.size();i++){
			String deptCode=list.get(i).get("CODE").toString();
			String deptName=list.get(i).get("NAME").toString();
			ret= mailControl.getStaffInfoBydeptCode(deptCode,prs,mailFormBean);
			List<Map> people = prs.getResults();
			if(people!=null && people.size()>0){
				String id=list.get(i).get("ID").toString();
				mJSONObject.put("deptName", deptName);
				mJSONObject.put("id", id);
				mJSONObject.put("staff", JSONArray.fromObject(people));
				mJSONArray.add(mJSONObject);
			}
			
		}
		return mJSONArray;
	}
	
	//app 保存通讯录
		@SuppressWarnings({ "rawtypes", "unused" })
		public String app_save(){
			log.info("通讯录列表==MailAction.app_save");
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			RetMessage ret = new RetMessage();
			PageResults prs = new PageResults();
			
			log.info("增加通讯录基础信息==MailAction.save");
			Mail mMail = new Mail();
			if (mailFormBean.getMail().getId() == 0) {
				ret = mailControl.save(mailFormBean.getMail(), mMail);
			} else {
				// 根据id获取旧数据进行修改
				mMail = mailControl.getBaseById(mailFormBean.getMail().getId());
				// 修改单位信息
				ret = mailControl.update(mailFormBean.getMail(), mMail);
			}
			
			hashMap.put("infoBean", mMail);
			hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
			hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
			// 写入当前操作 成功状态 success 或 error
			HttpServletResponse response = getResponse();
			response.setHeader("Access-Control-Allow-Origin", "*");
			CommonFunction.writeResponse(response, hashMap);
			return null;
			
		}
	

	public MailFormBean getMailFormBean() {
		return mailFormBean;
	}

	public void setMailFormBean(MailFormBean mailFormBean) {
		this.mailFormBean = mailFormBean;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public SysEngineerInfoFormBean getEngineerInfoFormBean() {
		return engineerInfoFormBean;
	}

	public void setEngineerInfoFormBean(SysEngineerInfoFormBean engineerInfoFormBean) {
		this.engineerInfoFormBean = engineerInfoFormBean;
	}

	
	

}

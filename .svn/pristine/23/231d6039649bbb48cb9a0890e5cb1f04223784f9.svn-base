package com.lyht.business.mail.action;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.mail.bean.MailType;
import com.lyht.business.mail.control.MailTypeControl;
import com.lyht.business.mail.formBean.MailFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/mailType")
@Results({ @Result(name = "list", location = "/business/destroy/company/list.jsp"),
		@Result(name = "edit", location = "/business/destroy/company/edit.jsp"),
		@Result(name = "editCompany", location = "/business/destroy/companyAdd/list.jsp") })

@Controller
@Scope("prototype")
@Action("mailType")
/**
 * @author 陈洪强 
 * 功能 ：查看物料树形菜单 ztree;
 */
public class MailTypeAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("MailTypeAction");

	MailFormBean mailFormBean= new MailFormBean();

	@Resource
	private	MailTypeControl  mailTypeControl;

	// 物资发货基础列表
	@SuppressWarnings("rawtypes")
	public String zTree() {
		log.info("物料树形菜单==MailTypeAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
		ret.setMessage("查询数据成功！");
		PageResults prs = new PageResults();
		List<Map> zTree = mailTypeControl.zTree(prs);
		JSONArray jsonArray = JSONArray.fromObject(zTree);
		
		hashMap.put("zTree",jsonArray);
		 
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;

	}
	//增加根节点和子节点
	public String addPid(){
		log.info("增加根节点==MailTypeAction.addPid");
		
		MailType mMailType = new MailType();
		
		String id = mailFormBean.getMailType().getCode();
		String name = mailFormBean.getMailType().getDeptName();
		String pid = mailFormBean.getMailType().getPcode();
		String pnm= mailFormBean.getMailType().getPnm();
		
		if(id.length()==1){
			id="00"+id;
		}
		if(id.length()==2){
			id="0"+id;
		}
		if(!pid.equals("0")){
			
			if(pid.length()==1){
				pid="00"+pid;
			}
			if(pid.length()==2){
				pid="0"+pid;
			}
		}
		mMailType.setPnm(pnm);
		mMailType.setCode(id);
		mMailType.setDeptName(name);
		mMailType.setPcode(pid);
		mailTypeControl.addPid(mMailType);
		
		return null;
		
	}
	//修改节点名称
	public String edit(){
		log.info("修改节点名称==MailTypeAction.edit");

		MailType mMailType = new MailType();
		
		String id = mailFormBean.getMailType().getCode();
		String name = mailFormBean.getMailType().getDeptName();
		Integer nm = mailFormBean.getMailType().getId();
		
		mMailType.setId(nm);
		mMailType.setCode(id);
		mMailType.setDeptName(name);
		
		mailTypeControl.update(mMailType);
		
		
		return null;
	}
	
	//删除节点
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public String removes(){
		log.info("删除节点==MailTypeAction.removes");
		
		String id = mailFormBean.getMailType().getCode();
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		
		HashMap<String, String> map=mailTypeControl.listByIds(id);
		ret= mailTypeControl.delByIds(map.get("id"));

		//根据节点删除通讯录人员
		mailTypeControl.delBaseById(map.get("code"));
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		
		return null;
	}
	/**********************WEB-APP分割线****************************/
	//通讯录部门
	@SuppressWarnings("rawtypes")
	public String app_getDeptName(){
		
		log.info("通讯录列表111==MailAction.app_list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();

		ret = mailTypeControl.getDeptName(mailFormBean,prs);
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
		
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
		
		
	}
	
	public MailFormBean getMailFormBean() {
		return mailFormBean;
	}
	public void setMailFormBean(MailFormBean mailFormBean) {
		this.mailFormBean = mailFormBean;
	}
	

	

}

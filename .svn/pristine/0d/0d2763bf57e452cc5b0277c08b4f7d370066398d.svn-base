package com.lyht.business.notic.action;

import java.util.HashMap;
import java.util.Hashtable;

import javax.annotation.Resource;
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
import com.lyht.business.notic.bean.Notice;
import com.lyht.business.notic.control.NoticeControl;
import com.lyht.business.notic.formBean.NoticeFormBean;
import com.lyht.business.system.bean.SysDept;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.control.SysDictControl;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.DateUtil;

import net.sf.json.JSONArray;

@Namespace("/notice")
@Results({ @Result(name = "list", location = "/business/destroy/company/list.jsp"),
		@Result(name = "edit", location = "/business/destroy/company/edit.jsp"),
		@Result(name = "editCompany", location = "/business/destroy/companyAdd/list.jsp") })

@Controller
@Scope("prototype")
@Action("notice")
/**
 * @author 陈洪强 功能 ：查看通知列表 list;
 */
public class NoticeAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("NoticeAction");

	NoticeFormBean noticeFormBean = new NoticeFormBean();

	@Resource
	private NoticeControl noticeContrl;
	@Resource 
	private SysDictControl sysDictControl;
	// 通知列表
	@SuppressWarnings("rawtypes")
	public String list() {
		log.info("通知列表==NoticeAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);
		noticeFormBean.getNotice().setReleaseDept(dept.getNm());;
		ret = noticeContrl.list(noticeFormBean, prs);
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

	// 保存增加通知基础信息
	public String save() {
		log.info("增加通知==NoticeAction.save");
		Notice mNotice = new Notice();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		// 获取当前登录人名称和部门
		SysStaff staff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		SysDept dept = (SysDept) getSession().getAttribute(Constants.SESSION_DEPT);

		noticeFormBean.getNotice().setCreatePerson(staff.getName());
		noticeFormBean.getNotice().setReleaseDept(dept.getNm());
		noticeFormBean.getNotice().setReadTimes(0);
		noticeFormBean.getNotice().setState(0);
		noticeFormBean.getNotice().setReadState(0);
		
		if (noticeFormBean.getNotice().getId() == 0) {
			ret = noticeContrl.save(noticeFormBean.getNotice(), mNotice);
		} else {
			// 根据id获取旧数据进行修改
			mNotice = noticeContrl.getBaseById(noticeFormBean.getNotice().getId());
			// 修改单位信息
			ret = noticeContrl.update(noticeFormBean.getNotice(), mNotice);
		}
		hashMap.put("infoBean", mNotice);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashMap);

		return null;
	}

	// 修改通知
	@SuppressWarnings("rawtypes")
	public String edit() {
		log.info("修改通知名称==NoticeAction.edit");

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();
		ret = noticeContrl.getNoticeById(noticeFormBean.getNotice().getId(), prs);

		hashMap.put("infoBean", prs.getResults());
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		CommonFunction.writeResponse(getResponse(), hashMap);

		return null;

	}

	// 删除通知
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public String removeids() {
		log.info("删除通知==MailAction.removes");
		String ids = noticeFormBean.getIds();
		Hashtable hashtable = new Hashtable();
		RetMessage ret = new RetMessage(); // 获取删除的数据
		PageResults prs = new PageResults();
		// 删除数据
		ret = noticeContrl.delByIds(ids);

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);

		return null;
	}

	// 发布通知
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String flag() {
		log.info("删除通知==MailAction.flag");

		Hashtable hashtable = new Hashtable();
		RetMessage ret = new RetMessage();
		String ids = noticeFormBean.getIds();
		int flag_new = noticeFormBean.getNotice().getState();
		// PageResults prs = new PageResults();
		// prs = noticeContrl.findNoticeByIds(noticeFormBean.getIds());
		// 审核
		ret = noticeContrl.flag(ids, flag_new);

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);

		return null;
	}
	//阅读状态
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String read_flag(){
		
		log.info("修改阅读状态==MailAction.read_flag");
		Hashtable hashtable = new Hashtable();
		RetMessage ret = new RetMessage();
		String ids = noticeFormBean.getIds();
		int flag_new = noticeFormBean.getNotice().getReadState();
		
		// 修改状态
		ret = noticeContrl.read_flag(ids, flag_new);

		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		return null;
	}
	//获取未读取信息数量
	public String getMessageNub(){
		log.info("通知消息未读数量==SubmitAction.queryQuestionNub");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		int nub=noticeContrl.getMessageNub(noticeFormBean);
		hashMap.put("messageNub", nub);
		hashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
		//获取通知类型（APP接口） 
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public String getMessageType(){
			log.info("查询列表==SysdictAction.getMessageType");
			Hashtable mHashtable = new Hashtable();
			RetMessage mRetMessage=new RetMessage();
			PageResults mPageResults=new PageResults(); 
			String flag="tzlx";
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
		/*//图片上传
		
		public String upload(){  
			System.out.println("富文本上传");
					
			return null;
		}*/
	
/*************************************************************************
 *******************************APP***************************************
 *************************************************************************
 *************************************************************************/
	
	//APP获取通知列表
	@SuppressWarnings("rawtypes")
	public String app_list(){
		log.info("通知列表==NoticeAction.app_list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		PageResults prs = new PageResults();

		ret = noticeContrl.app_list(noticeFormBean, prs);
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
		
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	//app保存消息
	public String app_save(){
		log.info("增加通知==NoticeAction.save");
		Notice mNotice = new Notice();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		String date = DateUtil.getDate();
		noticeFormBean.getNotice().setReadTimes(0);
		noticeFormBean.getNotice().setState(1);
		noticeFormBean.getNotice().setReadState(0);
		noticeFormBean.getNotice().setReleaseDate(date);
		ret = noticeContrl.save(noticeFormBean.getNotice(), mNotice);
		
		hashMap.put("infoBean", mNotice);
		hashMap.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashMap.put(RetMessage.AJAX_MESSAGE, ret.getMessage());
		// 写入当前操作 成功状态 success 或 error
		HttpServletResponse response = getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		CommonFunction.writeResponse(getResponse(), hashMap);
		return null;
	}
	//APP获取通知列表
		@SuppressWarnings("rawtypes")
		public String app_release(){
			log.info("通知列表==NoticeAction.app_release");
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			RetMessage ret = new RetMessage();
			PageResults prs = new PageResults();

			ret = noticeContrl.app_release(noticeFormBean, prs);
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
			
			HttpServletResponse response = getResponse();
			response.setHeader("Access-Control-Allow-Origin", "*");
			CommonFunction.writeResponse(getResponse(), hashMap);
			return null;
		}
	

	public NoticeFormBean getNoticeFormBean() {
		return noticeFormBean;
	}

	public void setNoticeFormBean(NoticeFormBean noticeFormBean) {
		this.noticeFormBean = noticeFormBean;
	}

}

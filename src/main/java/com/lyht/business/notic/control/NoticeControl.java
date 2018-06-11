package com.lyht.business.notic.control;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.notic.bean.Notice;
import com.lyht.business.notic.formBean.NoticeFormBean;
import com.lyht.business.notic.service.NoticeService;

@Controller
@Scope("prototype")
public class NoticeControl {
	@Resource
	private NoticeService noticService;

	private static Logger log = Logger.getLogger("NoticAction");

	// 新增通知
	public RetMessage save(Notice infoBean, Notice retBean) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retBean, noticService.save(infoBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增通知信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "新增通知信息失败！");
			e.printStackTrace();
		}

		return ret;
	}

	// 查询通知信息根据Id
	public Notice getBaseById(Integer id) {
		Notice notice = new Notice();
		try {
			notice = noticService.getBaseById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notice;
	}

	// 查询通知信息根据Id
	public RetMessage getBaseById(Integer id, Notice notice) {
		RetMessage ret = new RetMessage();
		try {
			BeanUtils.copyProperties(notice, noticService.getBaseById(id));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询信息失败！");
			log.error(e);
		}
		return ret;
	}

	// 修改通知
	public RetMessage update(Notice infoBean, Notice retNotice) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retNotice, noticService.update(infoBean));

			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("修改通知信息成功！");

		} catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "修改通知信息失败！");
		}

		return ret;
	}

	// 通知列表
	@SuppressWarnings("rawtypes")
	public RetMessage list(NoticeFormBean noticFormBean, PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, noticService.list(noticFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}

		return ret;
	}

	// 删除通知

	public RetMessage delByIds(String ids) {
		RetMessage ret = new RetMessage();
		try {
			noticService.delByIds(ids);
			// results.clear();// 一定要执行此步骤，在AOP中数据就被清除
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "删除数据失败！");
		}

		return ret;
	}
	//审核
	public RetMessage flag(String ids, int flag_new) {
		RetMessage ret = new RetMessage();
		try {

			noticService.flagByIds(ids, flag_new);
			// list.clear();
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("审核数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "审核数据失败！");
		}

		return ret;
	}
	@SuppressWarnings("rawtypes")
	public RetMessage getNoticeById(Integer id,PageResults prs ) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, noticService.getNoticeById(id));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}

		return ret;

	}
	//修改阅读状态
	public RetMessage read_flag(String ids, int flag_new) {
		RetMessage ret = new RetMessage();
		try {

			noticService.read_flagByIds(ids, flag_new);
			// list.clear();
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("审核数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "审核数据失败！");
		}

		return ret;
	}
	//获取消息数量
	public int getMessageNub(NoticeFormBean noticeFormBean){
		int nub=0;
		try{
			nub=noticService.getMessageNub(noticeFormBean);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("查询未读消息数量==错误",e);
		}
		return nub;
	}
	/*************************************************************************
	 ******************************* APP**************************************
	 *************************************************************************
	 *************************************************************************/
	// 通知列表
	@SuppressWarnings("rawtypes")
	public RetMessage app_list(NoticeFormBean noticFormBean, PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, noticService.app_list(noticFormBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询数据失败！");
			log.error(e);
			e.printStackTrace();
		}

		return ret;
	}
	// 通知列表
		@SuppressWarnings("rawtypes")
		public RetMessage app_release(NoticeFormBean noticFormBean, PageResults prs) {
			RetMessage ret = new RetMessage();
			try {
				// 必须使用此函数，否则AOP中的数值不会发生变化
				BeanUtils.copyProperties(prs, noticService.app_release(noticFormBean));
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("查询数据成功！");
			} catch (Exception e) {
				ret.setRetflag(RetMessage.RETFLAG_ERROR);
				ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询数据失败！");
				log.error(e);
				e.printStackTrace();
			}

			return ret;
		}



}

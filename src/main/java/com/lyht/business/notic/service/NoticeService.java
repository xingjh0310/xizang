package com.lyht.business.notic.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.notic.bean.Notice;
import com.lyht.business.notic.dao.NoticeDao;
import com.lyht.business.notic.formBean.NoticeFormBean;
import com.lyht.util.DateUtil;

@Service
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRED)
public class NoticeService {
	@Resource
	private NoticeDao noticDao;

	// 保存通知
	public Notice save(Notice bean) {
		noticDao.save(bean);
		return bean;
	}

	// 根据ID查询通知
	public Notice getBaseById(Integer id) {
		Notice notice = new Notice();
		if (id > 0) {
			notice = noticDao.get(id);
		}
		return notice;
	}
	//查询通知
	@SuppressWarnings("rawtypes")
	public PageResults getNoticeById(Integer id){
		PageResults baseById = noticDao.getBaseById(id);
		return baseById;
		
	}
	// 修改通知信息
	public Notice update(Notice infoBean) {
		noticDao.merge(infoBean);
		noticDao.flush(infoBean);
		return infoBean;
	}

	// 通知列表
	@SuppressWarnings("rawtypes")
	public PageResults list(NoticeFormBean noticFormBean) {
		return noticDao.list(noticFormBean);
	}

	// 根据id删除物料信息
	public void delByIds(String ids) {
		String[] idary = ids.split(",");
		for (int i = 0; i < idary.length; i++) {
			noticDao.deleteById(Integer.parseInt(idary[i]));
		}

	}
	//发布
	public void flagByIds(String ids, int flag_new) {
		String dateTime = DateUtil.getDate();
		String[] idary = ids.split(",");
		for (int i = 0; i < idary.length; i++) {
			Notice notice = noticDao.get(Integer.parseInt(idary[i]));
			notice.setState(flag_new);
			notice.setReleaseDate(dateTime);
			noticDao.update(notice);
		}
		
	}
	//阅读状态
	public void read_flagByIds(String ids, int flag_new) {
		String[] idary = ids.split(",");
		for (int i = 0; i < idary.length; i++) {
			Notice notice = noticDao.get(Integer.parseInt(idary[i]));
			notice.setReadState(flag_new);
			noticDao.update(notice);
		}
		
	}
	//获取消息数据数量
	public int getMessageNub(NoticeFormBean noticeFormBean) {
		
		return noticDao.getMessageNub(noticeFormBean);
	}
		
/*************************************************************************
 ******************************* APP**************************************
 *************************************************************************
 *************************************************************************/
	// 通知列表
	@SuppressWarnings("rawtypes")
	public PageResults app_list(NoticeFormBean noticFormBean) {
		return noticDao.app_list(noticFormBean);
	}
	@SuppressWarnings("rawtypes")
	public PageResults app_release(NoticeFormBean noticFormBean) {
		return noticDao.app_release(noticFormBean);
	}
	

}

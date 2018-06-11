package com.lyht.business.materiel.control;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.bean.MaterielBase;
import com.lyht.business.materiel.formBean.MaterielBaseFormBean;
import com.lyht.business.materiel.service.MaterielBaseService;

@Controller
@Scope("prototype")
public class MaterielBaseControl {
	@Resource
	private MaterielBaseService materialBaseService;

	private static Logger log = Logger.getLogger("MaterialAction");

	// 物料基础信息列表
	@SuppressWarnings("rawtypes")
	public RetMessage list(MaterielBaseFormBean infoBean, PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, materialBaseService.list(infoBean));
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
	// 物料基础信息列表
	@SuppressWarnings("rawtypes")
	public RetMessage all_list(MaterielBaseFormBean infoBean, PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, materialBaseService.all_list(infoBean));
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

	public RetMessage save(MaterielBase infoBean, MaterielBase retBean) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retBean, materialBaseService.save(infoBean));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("新增物料基础信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "新增物料基础信息失败！");
			e.printStackTrace();
		}

		return ret;
	}

	public RetMessage update(MaterielBase materielBase, MaterielBase retMaterielBase) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(retMaterielBase, materialBaseService.update(materielBase));

			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("增加信息成功！");

		} catch (Exception e) {
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "增加信息失败！");
		}

		return ret;
	}

	// 根据id获取物料基础信息
	@SuppressWarnings({ "rawtypes" })
	public PageResults findByIds(String ids) {
		PageResults prs = new PageResults();
		try {
			prs = materialBaseService.findByIds(ids);
		} catch (Exception e) {
		}
		return prs;
	}

	// 删除物料基本信息
	@SuppressWarnings("rawtypes")
	public RetMessage delByIds(String ids, List results) {
		RetMessage ret = new RetMessage();
		try {
			materialBaseService.delByIds(ids);
			results.clear();// 一定要执行此步骤，在AOP中数据就被清除
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("删除数据成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "删除数据失败！");
		}

		return ret;

	}

	// 查询基础信息根据Id
	public MaterielBase getBaseById(Integer id) {
		MaterielBase mMaterielBase = new MaterielBase();
		try {
			mMaterielBase = materialBaseService.getBaseById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mMaterielBase;

	}

	// 根据ID获取物料基本信息
	public RetMessage getBaseById(Integer id, MaterielBase mMaterielBase) {
		RetMessage ret = new RetMessage();
		try {
			BeanUtils.copyProperties(mMaterielBase, materialBaseService.getBaseById(id));
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("查询信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "查询信息失败！");
			log.error(e);
		}
		return ret;
	}

	// 导出物料基本信息
	@SuppressWarnings("rawtypes")
	public RetMessage downLoad(MaterielBaseFormBean infoBean, PageResults prs, HttpServletRequest request,
			HttpServletResponse response) {
		RetMessage ret = new RetMessage();
		try {
			materialBaseService.downLoad(infoBean, prs, request, response);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导出物料基本信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "导出物料基本信息失败！");
			e.printStackTrace();
			log.error("导出物料基本信息错误", e);
		}
		return ret;

	}

	// 导入物料基础信息
	public RetMessage upLoad(File[] file, String[] fileFileName) {
		RetMessage ret = new RetMessage();
		try {
			materialBaseService.upLoad(file, fileFileName);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导入物料基础信息成功！");
		} catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG + "导入物料基础信息失败！");
			e.printStackTrace();
			log.error("导入物料基础信息错误", e);
		}
		return ret;
	}
	
	/********************WEB-APP分割线**************************/
	// 物料类型
	@SuppressWarnings("rawtypes")
	public RetMessage getBase(MaterielBaseFormBean infoBean, PageResults prs) {
		RetMessage ret = new RetMessage();
		try {
			// 必须使用此函数，否则AOP中的数值不会发生变化
			BeanUtils.copyProperties(prs, materialBaseService.getBase(infoBean));
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

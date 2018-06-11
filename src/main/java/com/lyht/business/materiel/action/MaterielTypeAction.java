package com.lyht.business.materiel.action;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.materiel.bean.MaterielType;
import com.lyht.business.materiel.control.MaterielTypeControl;
import com.lyht.business.materiel.formBean.MaterielBaseFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/materielType")
@Results({ @Result(name = "list", location = "/business/destroy/company/list.jsp"),
		@Result(name = "edit", location = "/business/destroy/company/edit.jsp"),
		@Result(name = "editCompany", location = "/business/destroy/companyAdd/list.jsp") })

@Controller
@Scope("prototype")
@Action("materielType")
/**
 * @author 陈洪强 
 * 功能 ：查看物料树形菜单 ztree;
 */
public class MaterielTypeAction extends BaseLyhtAction {
	private static final long serialVersionUID = 1L;
	// 调试日志
	private static Logger log = Logger.getLogger("MaterielTypeAction");

	MaterielBaseFormBean materielBaseFormBean = new MaterielBaseFormBean();

	@Resource
	private	MaterielTypeControl   materielTypeControl;

	// 物资发货基础列表
	@SuppressWarnings("rawtypes")
	public String zTree() {
		log.info("物料树形菜单==MaterielTypeAction.list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		RetMessage ret = new RetMessage();
		ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
		ret.setMessage("查询数据成功！");
		PageResults prs = new PageResults();
		List<Map> zTree = materielTypeControl.zTree(prs);
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
		log.info("增加根节点==MaterielTypeAction.addPid");
		
		MaterielType mMaterielType = new MaterielType();
		
		String id = materielBaseFormBean.getMaterielType().getCode();
		String name = materielBaseFormBean.getMaterielType().getName();
		String pid = materielBaseFormBean.getMaterielType().getPid();
		mMaterielType.setCode(id);
		mMaterielType.setName(name);
		mMaterielType.setPid(pid);
		materielTypeControl.addPid(mMaterielType);
		
		return null;
		
	}
	//修改节点名称
	public String edit(){
		log.info("修改节点名称==MaterielTypeAction.edit");

		MaterielType mMaterielType = new MaterielType();
		
		String id = materielBaseFormBean.getMaterielType().getCode();
		String name = materielBaseFormBean.getMaterielType().getName();
		Integer nm = materielBaseFormBean.getMaterielType().getId();
		
		mMaterielType.setId(nm);
		mMaterielType.setCode(id);
		mMaterielType.setName(name);
		
		materielTypeControl.update(mMaterielType);
		
		
		return null;
	}
	
	//删除节点
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public String removes(){
		log.info("删除节点==MaterielTypeAction.removes");
		
		String id = materielBaseFormBean.getMaterielType().getCode();
		Hashtable hashtable = new Hashtable();
		RetMessage ret=new RetMessage();
		//获取删除的数据
		PageResults prs=new PageResults(); 
		
		HashMap<String, String> map=materielTypeControl.listByIds(id);
		ret= materielTypeControl.delByIds(map.get("id"));

		//根据节点删除物料信息
		materielTypeControl.delBaseById(map.get("code"));
		
		hashtable.put(RetMessage.AJAX_RETFLAG, ret.getRetflag());
		hashtable.put(RetMessage.AJAX_MESSAGE, ret.getMessage());		
		// 写入当前操作 成功状态 success 或 error
		CommonFunction.writeResponse(getResponse(), hashtable);
		
		return null;
	}
	

	public MaterielBaseFormBean getMaterielBaseFormBean() {
		return materielBaseFormBean;
	}

	public void setMaterielBaseFormBean(MaterielBaseFormBean materielBaseFormBean) {
		this.materielBaseFormBean = materielBaseFormBean;
	}

	

}

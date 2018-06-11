package com.lyht;

import java.io.Serializable;

/**
 * 用于返回Action的值类型
 * @author czy
 *
 */
public class RetMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String RETFLAG_ERROR = "error";
	public static final String RETFLAG_SUCCESS = "success";
	
	public static final String AJAX_RETFLAG = "retflag";
	public static final String AJAX_MESSAGE = "message";
	
	public static final String ERROR_NOEJB_MSG = "没有找到对应的EJB接口服务，请查询EJB服务是否正常启动!";
	public static final String ERROR_SERVICE_MSG = "Service中操作出现错误:";

	//返回标识
	private String retflag =RETFLAG_ERROR;
	private String message ="操作无响应！";
	
	public String getRetflag() {
		return retflag;
	}
	public void setRetflag(String retflag) {
		this.retflag = retflag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}

package com.lyht.filter;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyht.Constants;
import com.lyht.business.system.bean.SysAcct;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONObject;
/**
 * Servlet Filter implementation class SessionFilter
 */
/*@WebFilter(
		filterName="SessionFilter"
	   ,urlPatterns = { "/business/*","/index.jsp","/system/*"}
)*/
public class SessionFilter implements Filter {

	private FilterConfig config;
    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = fConfig;
	}
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access", "unused" })
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//session中获取账户信息
		HttpServletRequest  requ=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		HttpSession session =  requ.getSession();
		SysAcct sysacct = (SysAcct) session.getAttribute(Constants.SESSION_ACCT);
		if (sysacct==null) {
			
			String url=requ.getRequestURI();
			if (url.indexOf(".jsp")>0){
				//jsp跳转
				String loginjsp=this.config.getInitParameter("login"); //web.xml中配置
				String path = requ.getContextPath();
				String basePath = requ.getScheme() + "://" + requ.getServerName() 
					+ ":" + requ.getServerPort() + path + "/";
				resp.sendRedirect(basePath+loginjsp);  //"login.jsp"
			} else {
				//ajax调用跳转
				Hashtable hashtable = new Hashtable();
				// 返回未登录标记
				hashtable.put("retflag", "SessionTimeOut");
				// 信息提示
				hashtable.put("message", "当前系统没有登录信息，需要重新登录！");
				JSONObject json = new JSONObject().fromObject(hashtable);
				CommonFunction.writeResponse(resp, json.toString());
			}
			
		} else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}



}

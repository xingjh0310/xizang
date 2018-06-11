<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page import="com.lyht.business.pub.action.ActionEnterPlugs" %>  
<%@ page trimDirectiveWhitespaces="true" %>
<%

	request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	
	out.write( new ActionEnter( request, rootPath ).exec() );

 /*    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	//String rootPath = application.getRealPath( "/" );
	//System.out.print("文件地址"+rootPath);
	//out.write( new ActionEnter( request, rootPath ).exec() );
	
	  String rootPath = application.getRealPath( "/" );  
	    //文件存储路径，可以读取配置文件  
	    String saveRootPath = "D:/lyhtFile";  
	    //获取图片路径前缀，可以读取配置文件  
	    //String getFilePath = PropertiesFileUtil.getInstance().get("SYSTEM_DOWN_IMAGE");  
	    String action = request.getParameter("action");  
	    //重点在这里，使用ActionEnterPlugs我们刚在新建的类  
	    String result = new ActionEnterPlugs( request, rootPath ,saveRootPath).exec();  
	   if( action!=null && (action.equals("uploadimage") || action.equals("listimage") ) ){  
	  
	        rootPath = saveRootPath.replace("\\", "/");  
	  
	        result = result.replaceAll(saveRootPath, "/");  
	  
	    }
	    //result = result.replaceAll("/file",getFilePath);  
	    out.write( result );   */
%>
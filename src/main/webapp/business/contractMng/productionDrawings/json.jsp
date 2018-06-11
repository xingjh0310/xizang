<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	Hashtable table = new Hashtable();
	JSONObject obj1=new JSONObject();
	List list = new ArrayList();
	obj1.put("id", "1");
	obj1.put("a", "2");
	obj1.put("b", "藏中联网");
	obj1.put("c", "WL001");
	obj1.put("d", "铁塔");
	obj1.put("e", "50");
	obj1.put("f", "基/吨");
	obj1.put("g", "2017-12-12");
	obj1.put("h", "安徽宏源铁塔有限公司");
	obj1.put("i", "2017-03-01");
	obj1.put("j", "2017-03-03");
	list.add(obj1);
	JSONObject obj2=new JSONObject();
	obj2.put("id", "2");
	obj2.put("a", "2");
	obj2.put("b", "藏中联网");
	obj2.put("c", "WL001");
	obj2.put("d", "铁塔");
	obj2.put("e", "50");
	obj2.put("f", "基/吨");
	obj2.put("g", "2017-12-12");
	obj2.put("h", "安徽宏源铁塔有限公司");
	obj2.put("i", "2017-03-01");
	obj2.put("j", "2017-03-03");
	list.add(obj2);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 2);
	table.put("total", 2);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>
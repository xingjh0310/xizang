<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	Hashtable table = new Hashtable();
	JSONObject obj1=new JSONObject();
	List list = new ArrayList();
	obj1.put("id", "1");
	obj1.put("a", "0");
	obj1.put("b", "WL001");
	obj1.put("c", "铁塔");
	obj1.put("d", "基");
	obj1.put("e", "HT001");
	obj1.put("f", "50");
	obj1.put("g", "西藏布达拉电网建设");
	obj1.put("h", "国电电网");
	obj1.put("i", "国电西藏");
	obj1.put("j", "安徽宏源铁塔有限公司");
	obj1.put("k", "2017-03-01");
	obj1.put("l", "2017-06-01");
	obj1.put("m", "已执行");
	list.add(obj1);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 1);
	table.put("total", 1);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>
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
	obj1.put("d", "50");
	obj1.put("e", "100");
	obj1.put("f", "100");
	obj1.put("g", "国网西藏");
	obj1.put("h", "2017-03-01");
	obj1.put("i", "2017-03-01");
	obj1.put("j", "张三");
	obj1.put("k", "17764328427");
	obj1.put("l", "西藏拉萨市区西北玛布日山");
	list.add(obj1);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 1);
	table.put("total", 1);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>
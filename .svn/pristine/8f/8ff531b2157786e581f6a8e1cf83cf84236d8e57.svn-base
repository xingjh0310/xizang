<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	Hashtable table = new Hashtable();
	JSONObject obj1=new JSONObject();
	List list = new ArrayList();
	obj1.put("id", "1");
	obj1.put("a", "0");
	obj1.put("b", "jx03");
	obj1.put("c", "钢绞线");
	obj1.put("d", "1*19-13.0-1270-B");
	obj1.put("e", "1");
	obj1.put("f", "米");
	obj1.put("g", "张三");
	obj1.put("h", "20万");
	obj1.put("i", "SN00001");
	obj1.put("j", "2018-02-27");
	obj1.put("k", "西藏");
	obj1.put("l", "100万");
	list.add(obj1);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 1);
	table.put("total", 1);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>
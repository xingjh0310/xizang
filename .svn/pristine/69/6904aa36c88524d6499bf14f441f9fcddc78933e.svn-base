<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	Hashtable table = new Hashtable();
	JSONObject obj1=new JSONObject();
	List list = new ArrayList();
	obj1.put("id", "1");
	obj1.put("a", "CG0001");
	obj1.put("b", "HT0001");
	obj1.put("c", "北京市");
	obj1.put("d", "上海市");
	obj1.put("e", "13466566676");
	obj1.put("f", "铁塔");
	obj1.put("g", "100");
	obj1.put("h", "XXX铁塔厂");
	obj1.put("i", "四川物流公司");
	obj1.put("j", "已到站");
	obj1.put("k", "已交接");
	obj1.put("l", "2018-01-08");
	obj1.put("m", "退货处理");
	obj1.put("n", "2018-01-16");
	list.add(obj1);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 1);
	table.put("total", 1);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>
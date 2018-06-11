package com.lyht.base.hibernate;


import java.sql.Types;
//org.hibernate.dialect.SQLServer2008Dialect; 
//org.hibernate.dialect.OscarDialect // 神通数据库

import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;


public class MyDialect extends org.hibernate.dialect.SQLServer2008Dialect{
	public MyDialect() {
		super();
		//-1
		this.registerHibernateType(Types.LONGVARCHAR, StringType.INSTANCE.getName());

		registerHibernateType(1, "string");     
	     registerHibernateType(-9, "string");     
	     registerHibernateType(-16, "string");     
	     registerHibernateType(3, "double");  
	       
	     registerHibernateType(Types.CHAR, StandardBasicTypes.STRING.getName());     
	     registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());     
	     registerHibernateType(Types.LONGNVARCHAR, StandardBasicTypes.STRING.getName());     
	     registerHibernateType(Types.DECIMAL, StandardBasicTypes.DOUBLE.getName());   
		
	}
}

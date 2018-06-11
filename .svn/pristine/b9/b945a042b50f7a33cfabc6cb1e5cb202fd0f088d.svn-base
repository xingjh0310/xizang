package com.lyht.business.system.bean;

import java.io.Serializable;
import javax.persistence.*;
/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 21:28:35
 *说明:  账户信息
*/
@Entity
@Table(name = "SYS_ACCT")
public class SysAcct implements Serializable{

	private static final long serialVersionUID = 1L;
    /**
    *主键
    */
    private Integer id;  
    /**
    *内码
    */
    private String nm;  
    /**
    *账户名称
    */
    private String name;  
    /**
    *状态
    */
    private Integer flag;  
    /**
    *登录类型
    */
    private String dictnmDllx;  
    /**
    *账户密码
    */
    private String pwd;  
    /**
    *有效期
    */
    private String yxq;  
    /**
    *账户类型
    */
    private String dictnmZhlx;  
    /**
    *验证码
    */
    private String yzm;  
    /**
    *验证码有效期
    */
    private String yzmyxq;  
    /**
    *验证错误次数
    */
    private Integer yzyxcs;  
    /**
    *系统人员
    */
    private String listnmSysStaff;  
    /**
    *系统内置
    */
    private Integer sysflag;  
    
    /** default constructor */
    public SysAcct() {
        
        this.id = 0;
        this.nm = "";
        this.name = "";
        this.flag = 0;
        this.dictnmDllx = "";
        this.pwd = "";
        this.dictnmZhlx = "";
        this.yzm = "";
        this.yzyxcs = 0;
        this.listnmSysStaff = "";
        this.sysflag = 0;
    
    }
    /** full constructor */
    public SysAcct(
                  Integer id ,
                  String nm ,
                  String name ,
                  Integer flag ,
                  String dictnmDllx ,
                  String pwd ,
                  String yxq ,
                  String dictnmZhlx ,
                  String yzm ,
                  String yzmyxq ,
                  Integer yzyxcs ,
                  String listnmSysStaff ,
                  Integer sysflag 
                  ) {
        this.id = id;
        this.nm = nm;
        this.name = name;
        this.flag = flag;
        this.dictnmDllx = dictnmDllx;
        this.pwd = pwd;
        this.yxq = yxq;
        this.dictnmZhlx = dictnmZhlx;
        this.yzm = yzm;
        this.yzmyxq = yzmyxq;
        this.yzyxcs = yzyxcs;
        this.listnmSysStaff = listnmSysStaff;
        this.sysflag = sysflag;
    }
    
    @Override
    public String toString() {
    	String s="";
    	s=	"{";
        s=s+"id="+id;
        s=s+" ,nm="+nm;
        s=s+" ,name="+name;
        s=s+" ,flag="+flag;
        s=s+" ,dictnmDllx="+dictnmDllx;
        s=s+" ,pwd="+pwd;
        s=s+" ,yxq="+yxq;
        s=s+" ,dictnmZhlx="+dictnmZhlx;
        s=s+" ,yzm="+yzm;
        s=s+" ,yzmyxq="+yzmyxq;
        s=s+" ,yzyxcs="+yzyxcs;
        s=s+" ,listnmSysStaff="+listnmSysStaff;
        s=s+" ,sysflag="+sysflag;
    	s=s+"}";
    	return s;
    }
 
    //属性get/set定义  

    //主键
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id"   
      
     , unique = true
     , nullable = false 
           )
    public  Integer getId() {
        return this.id;
    }
    public void setId( Integer id) {
        this.id = id;
    }

    //内码
    
    @Column(name = "nm"   
      ,length=50  
     , unique = true
     , nullable = false 
           )
    public  String getNm() {
        return this.nm;
    }
    public void setNm( String nm) {
        this.nm = nm;
    }

    //账户名称
    
    @Column(name = "name"   
      ,length=100  
     , unique = true
     , nullable = false 
           )
    public  String getName() {
        return this.name;
    }
    public void setName( String name) {
        this.name = name;
    }

    //状态
    
    @Column(name = "flag"   
      
     
     , nullable = false 
           )
    public  Integer getFlag() {
        return this.flag;
    }
    public void setFlag( Integer flag) {
        this.flag = flag;
    }

    //登录类型
    
    @Column(name = "dictnm_dllx"   
      ,length=50  
     
     , nullable = false 
           )
    public  String getDictnmDllx() {
        return this.dictnmDllx;
    }
    public void setDictnmDllx( String dictnmDllx) {
        this.dictnmDllx = dictnmDllx;
    }

    //账户密码
    
    @Column(name = "pwd"   
      ,length=50  
     
      
           )
    public  String getPwd() {
        return this.pwd;
    }
    public void setPwd( String pwd) {
        this.pwd = pwd;
    }

    //有效期
    
    @Column(name = "yxq"   
      
     
      
           )
    public  String getYxq() {
        return this.yxq;
    }
    public void setYxq( String yxq) {
        this.yxq = yxq;
    }

    //账户类型
    
    @Column(name = "dictnm_zhlx"   
      ,length=50  
     
     , nullable = false 
           )
    public  String getDictnmZhlx() {
        return this.dictnmZhlx;
    }
    public void setDictnmZhlx( String dictnmZhlx) {
        this.dictnmZhlx = dictnmZhlx;
    }

    //验证码
    
    @Column(name = "yzm"   
      ,length=50  
     
      
           )
    public  String getYzm() {
        return this.yzm;
    }
    public void setYzm( String yzm) {
        this.yzm = yzm;
    }

    //验证码有效期
    
    @Column(name = "yzmyxq"   
      
     
      
           )
    public  String getYzmyxq() {
        return this.yzmyxq;
    }
    public void setYzmyxq( String yzmyxq) {
        this.yzmyxq = yzmyxq;
    }

    //验证错误次数
    
    @Column(name = "yzyxcs"   
      
     
      
           )
    public  Integer getYzyxcs() {
        return this.yzyxcs;
    }
    public void setYzyxcs( Integer yzyxcs) {
        this.yzyxcs = yzyxcs;
    }

    //系统人员
    
    @Column(name = "listnm_sys_staff"   
      ,length=50  
     
      
           )
    public  String getListnmSysStaff() {
        return this.listnmSysStaff;
    }
    public void setListnmSysStaff( String listnmSysStaff) {
        this.listnmSysStaff = listnmSysStaff;
    }

    //系统内置
    
    @Column(name = "sysflag"   
      
     
     , nullable = false 
           )
    public  Integer getSysflag() {
        return this.sysflag;
    }
    public void setSysflag( Integer sysflag) {
        this.sysflag = sysflag;
    }
}
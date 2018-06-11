package com.lyht.business.system.bean;

import java.io.Serializable;
import javax.persistence.*;
/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 14:27:04
 *说明:  系统角色
*/
@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable{

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
    *角色编码
    */
    private String code;  
    /**
    *角色名称
    */
    private String name;  
    /**
    *备注
    */
    private String memo;  
    /**
    *状态
    */
    private Integer flag;  
    /**
    *系统内置
    */
    private Integer sysflag;  
    
    /** default constructor */
    public SysRole() {
        
        this.id = 0;
        this.nm = "";
        this.code = "";
        this.name = "";
        this.memo = "";
        this.flag = 0;
        this.sysflag = 0;
    
    }
    /** full constructor */
    public SysRole(
                  Integer id ,
                  String nm ,
                  String code ,
                  String name ,
                  String memo ,
                  Integer flag ,
                  Integer sysflag 
                  ) {
        this.id = id;
        this.nm = nm;
        this.code = code;
        this.name = name;
        this.memo = memo;
        this.flag = flag;
        this.sysflag = sysflag;
    }
    
    @Override
    public String toString() {
    	String s="";
    	s=	"{";
        s=s+"id="+id;
        s=s+" ,nm="+nm;
        s=s+" ,code="+code;
        s=s+" ,name="+name;
        s=s+" ,memo="+memo;
        s=s+" ,flag="+flag;
        s=s+" ,sysflag="+sysflag;
    	s=s+"}";
    	return s;
    }
 
    //属性get/set定义  

    //主键
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID"   
      
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
    
    @Column(name = "NM"   
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

    //角色编码
    
    @Column(name = "CODE"   
      ,length=50  
     , unique = true
     , nullable = false 
           )
    public  String getCode() {
        return this.code;
    }
    public void setCode( String code) {
        this.code = code;
    }

    //角色名称
    
    @Column(name = "NAME"   
      ,length=100  
     
     , nullable = false 
           )
    public  String getName() {
        return this.name;
    }
    public void setName( String name) {
        this.name = name;
    }

    //备注
    
    @Column(name = "MEMO"   
      ,length=500  
     
      
           )
    public  String getMemo() {
        return this.memo;
    }
    public void setMemo( String memo) {
        this.memo = memo;
    }

    //状态
    
    @Column(name = "FLAG"   
      
     
     , nullable = false 
           )
    public  Integer getFlag() {
        return this.flag;
    }
    public void setFlag( Integer flag) {
        this.flag = flag;
    }

    //系统内置
    
    @Column(name = "SYSFLAG"   
      
     
     , nullable = false 
           )
    public  Integer getSysflag() {
        return this.sysflag;
    }
    public void setSysflag( Integer sysflag) {
        this.sysflag = sysflag;
    }
}
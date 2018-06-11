package com.lyht.business.system.bean;

import java.io.Serializable;
import javax.persistence.*;
/**
 *作者： 陈震宇
 *脚本日期:2017年7月15日 11:07:22
 *说明:  表关联
*/
@Entity
@Table(name = "SYS_RELA")
public class SysRela implements Serializable{

	private static final long serialVersionUID = 1L;
    /**
    *主键
    */
    private Integer id;  
    /**
    *唯一编码
    */
    private String code;  
    /**
    *表A名称
    */
    private String taName;  
    /**
    *表A值内码
    */
    private String taNm;  
    /**
    *表B名称
    */
    private String tbName;  
    /**
    *表B值内码
    */
    private String tbNm;  
    /**
    *状态
    */
    private Integer flag;  
    
    /**
     * 审核状态
     */
    private Integer status;
    
    
    /** default constructor */
    public SysRela() {
        
        this.id = 0;
        this.code = "";
        this.taName = "";
        this.taNm = "";
        this.tbName = "";
        this.tbNm = "";
        this.flag = 0;
        this.status = 0;
    
    }
    /** full constructor */
    public SysRela(
                  Integer id ,
                  String code ,
                  String taName ,
                  String taNm ,
                  String tbName ,
                  String tbNm ,
                  Integer flag,
                  Integer status
                  ) {
        this.id = id;
        this.code = code;
        this.taName = taName;
        this.taNm = taNm;
        this.tbName = tbName;
        this.tbNm = tbNm;
        this.flag = flag;
        this.status=status;
    }
    
    @Override
    public String toString() {
    	String s="";
    	s=	"{";
        s=s+"id="+id;
        s=s+" ,code="+code;
        s=s+" ,taName="+taName;
        s=s+" ,taNm="+taNm;
        s=s+" ,tbName="+tbName;
        s=s+" ,tbNm="+tbNm;
        s=s+" ,flag="+flag;
        s=s+" ,status="+status;
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

    //唯一编码
    
    @Column(name = "code"   
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

    //表A名称
    
    @Column(name = "ta_name"   
      ,length=50  
     
     , nullable = false 
           )
    public  String getTaName() {
        return this.taName;
    }
    public void setTaName( String taName) {
        this.taName = taName;
    }

    //表A值内码
    
    @Column(name = "ta_nm"   
      ,length=50  
     
     , nullable = false 
           )
    public  String getTaNm() {
        return this.taNm;
    }
    public void setTaNm( String taNm) {
        this.taNm = taNm;
    }

    //表B名称
    
    @Column(name = "tb_name"   
      ,length=50  
     
     , nullable = false 
           )
    public  String getTbName() {
        return this.tbName;
    }
    public void setTbName( String tbName) {
        this.tbName = tbName;
    }

    //表B值内码
    
    @Column(name = "tb_nm"   
      ,length=50  
     
     , nullable = false 
           )
    public  String getTbNm() {
        return this.tbNm;
    }
    public void setTbNm( String tbNm) {
        this.tbNm = tbNm;
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
    
    //审核状态
    
    @Column(name = "status"   
      
     
     , nullable = false 
           )
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
    
    
}
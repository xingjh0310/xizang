package com.lyht.business.system.bean;

import java.io.Serializable;
import javax.persistence.*;
/**
 *作者： 陈震宇
 *脚本日期:2017年7月3日 13:09:22
 *说明:  单位机构
*/
@Entity
@Table(name = "sys_dept")
public class SysDept implements Serializable{

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
    *上级内码
    */
    private String pnm;  
    /**
    *本级编码
    */
    private String thiscode;  
    /**
    *全编码
    */
    private String code;  
    /**
    *上级编码
    */
    private String pcode;  
    /**
    *名称
    */
    private String name;  
    /**
     * 简称
     * */
    private String shortName; 
    /**
     * 标段
     * */
    private String section;
    /**
     * 负责工程
     * */
    private String engineerCode;
    /**
    *状态
    */
    private Integer flag;  
    /**
     *单位类型
     */
    private String type;  
    
    //供应商编号
    private String supplierCode;
    
    /** default constructor */
    public SysDept() {
        this.id = 0;
        this.nm = "";
        this.pnm = "";
        this.thiscode = "";
        this.code = "";
        this.pcode = "";
        this.name = "";
        this.section="";
        this.engineerCode="";
        this.shortName="";
        this.flag = 0;
        this.type = "";
        this.supplierCode="";
    }
    /** full constructor */
    public SysDept(
                  Integer id ,
                  String nm ,
                  String pnm ,
                  String thiscode ,
                  String code ,
                  String pcode ,
                  String name ,
                  String section,
                  String engineerCode,
                  String shortName,
                  Integer flag,
                  String type,
                  String supplierCode
                  ) {
        this.id = id;
        this.nm = nm;
        this.pnm = pnm;
        this.thiscode = thiscode;
        this.code = code;
        this.pcode = pcode;
        this.name = name;
        this.section=section;
        this.engineerCode=engineerCode;
        this.shortName=shortName;
        this.flag = flag;
        this.type = type;
        this.supplierCode=supplierCode;
    }
    
    @Override
    public String toString() {
    	String s="";
    	s=	"{";
        s=s+"id="+id;
        s=s+" ,nm="+nm;
        s=s+" ,pnm="+pnm;
        s=s+" ,thiscode="+thiscode;
        s=s+" ,code="+code;
        s=s+" ,pcode="+pcode;
        s=s+" ,name="+name;
        s=s+" ,shortName="+shortName;
        s=s+" ,section="+section;
        s=s+" ,engineerCode="+engineerCode;
        s=s+" ,flag="+flag;
        s=s+" ,type="+type;
        s=s+" ,supplierCode="+supplierCode;
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

    //上级内码
    
    @Column(name = "pnm"   
      ,length=50  
     
     , nullable = false 
           )
    public  String getPnm() {
        return this.pnm;
    }
    public void setPnm( String pnm) {
        this.pnm = pnm;
    }

    //本级编码
    
    @Column(name = "thiscode"   
      ,length=10  
     
     , nullable = false 
           )
    public  String getThiscode() {
        return this.thiscode;
    }
    public void setThiscode( String thiscode) {
        this.thiscode = thiscode;
    }

    //全编码
    
    @Column(name = "code"   
      ,length=200  
     , unique = true
     , nullable = false 
           )
    public  String getCode() {
        return this.code;
    }
    public void setCode( String code) {
        this.code = code;
    }

    //上级编码
    
    @Column(name = "pcode"   
      ,length=200  
     
      
           )
    public  String getPcode() {
        return this.pcode;
    }
    public void setPcode( String pcode) {
        this.pcode = pcode;
    }

    //名称
    
    @Column(name = "name"   
      ,length=100  
     
     , nullable = false 
           )
    public  String getName() {
        return this.name;
    }
    public void setName( String name) {
        this.name = name;
    }
   
    //简称
    @Column(name = "shortname",length=30, nullable = false)
    public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	//分类
	@Column(name = "section",length=30, nullable = false)
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	//负责工程
	@Column(name = "engineer_code",length=50, nullable = false)
	public String getEngineerCode() {
		return engineerCode;
	}
	public void setEngineerCode(String engineerCode) {
		this.engineerCode = engineerCode;
	}
	
	//供应商
	@Column(name = "supplier_code",length=50, nullable = false)
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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
	
	//状态
	
	@Column(name = "type", nullable = false)
	public  String getType() {
		return this.type;
	}
	public void setType( String type) {
		this.type = type;
	}
    
}
package com.lyht.business.system.bean;

import java.io.Serializable;
import javax.persistence.*;
/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 21:12:00
 *说明:  人员信息
*/
@Entity
@Table(name = "sys_staff")
public class SysStaff implements Serializable{

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
    *人员编码
    */
    private String code;  
    /**
    *人员名称
    */
    private String name;  
    /**
     *手机号码
     */
     private String phone;
     /**
      *电话号码
      */
      private String telephone;
    /**
    *单位部门
    */
    private String treenmSysDept;  
    /**
    *性别
    */
    private String dictnmXingbie;  
    /**
    *状态
    */
    private Integer flag;  
    /**
    *备注
    */
    private String memo;  
    /**
    *系统内置
    */
    private Integer sysflag;  
    /**
     *籍贯
     */
    private String origin;
    /**
     * 职务
     */
    private String duty;
    /**
     * 是否允许登录
     * */
    private int isLogin;
    /**
     * 工程
     * */
    private String engineerNm;
    
	/** default constructor */
    public SysStaff() {
        
        this.id = 0;
        this.nm = "";
        this.code = "";
        this.name = "";
        this.phone = "";
        this.telephone = "";        
        this.treenmSysDept = "";
        this.dictnmXingbie = "";
        this.flag = 0;
        this.memo = "";
        this.sysflag = 0;
        this.duty="";
        this.origin="";
        this.isLogin=0;
        this.engineerNm="";
    }
	/** full constructor */
    public SysStaff(
                  Integer id ,
                  String nm ,
                  String code ,
                  String name ,
                  String phone ,
                  String telephone ,
                  String treenmSysDept ,
                  String dictnmXingbie ,
                  Integer flag ,
                  String memo ,
                  Integer sysflag,
                  String duty,
                  String origin,
                  int islogin,
                  String engineerNm
                  ) {
        this.id = id;
        this.nm = nm;
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.telephone = telephone;
        this.treenmSysDept = treenmSysDept;
        this.dictnmXingbie = dictnmXingbie;
        this.flag = flag;
        this.memo = memo;
        this.sysflag = sysflag;
        this.duty=duty;
        this.origin=origin;
        this.isLogin=islogin;
        this.engineerNm=engineerNm;
    }
    
    @Override
    public String toString() {
    	String s="";
    	s=	"{";
        s=s+"id="+id;
        s=s+" ,nm="+nm;
        s=s+" ,code="+code;
        s=s+" ,name="+name;
        s=s+" ,phone="+phone;
        s=s+" ,telephone="+telephone;
        s=s+" ,treenmSysDept="+treenmSysDept;
        s=s+" ,dictnmXingbie="+dictnmXingbie;
        s=s+" ,flag="+flag;
        s=s+" ,memo="+memo;
        s=s+" ,sysflag="+sysflag;
        s=s+" ,duty="+duty;
        s=s+" ,origin="+origin;
        s=s+" ,isLogin="+isLogin;
        s=s+" ,engineerNm="+engineerNm;
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

    //人员编码
    
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

    //人员名称
    
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
    
    //手机号码
    
    @Column(name = "phone"   
    		,length=50  
    		
    		, nullable = false 
    		)
    public  String getPhone() {
    	return this.phone;
    }
    public void setPhone( String phone) {
    	this.phone = phone;
    }
    
    //电话号码
    
    @Column(name = "telephone"   
    		,length=50  
    		
    		, nullable = false 
    		)
    public  String getTelephone() {
    	return this.telephone;
    }
    public void setTelephone( String telephone) {
    	this.telephone = telephone;
    }

    //单位部门
    
    @Column(name = "treenm_sys_dept"   
      ,length=50  
     
      
           )
    public  String getTreenmSysDept() {
        return this.treenmSysDept;
    }
    public void setTreenmSysDept( String treenmSysDept) {
        this.treenmSysDept = treenmSysDept;
    }

    //性别
    
    @Column(name = "dictnm_xingbie"   
      ,length=50  
     
      
           )
    public  String getDictnmXingbie() {
        return this.dictnmXingbie;
    }
    public void setDictnmXingbie( String dictnmXingbie) {
        this.dictnmXingbie = dictnmXingbie;
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

    //备注
    
    @Column(name = "memo"   
      ,length=500  
     
      
           )
    public  String getMemo() {
        return this.memo;
    }
    public void setMemo( String memo) {
        this.memo = memo;
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
    
    //籍贯
    @Column(name = "origin",length=500)
    public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	//职务
	@Column(name = "duty",length=50)
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	//是否允许登录
	@Column(name = "islogin",nullable = false)
	public int getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}
	//是否允许登录
	@Column(name = "engineer_nm",nullable = false)
	public String getEngineerNm() {
		return engineerNm;
	}
	public void setEngineerNm(String engineerNm) {
		this.engineerNm = engineerNm;
	}
}
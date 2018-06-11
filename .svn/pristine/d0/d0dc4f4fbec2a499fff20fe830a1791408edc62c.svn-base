package com.lyht.business.system.bean;

import java.io.Serializable;
import javax.persistence.*;
/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 16:08:47
 *说明:  系统菜单
*/
@Entity
@Table(name = "sys_menu")
public class SysMenu implements Serializable{

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
    *状态
    */
    private Integer flag;  
    /**
    *图标
    */
    private String icon;  
    /**
    *图标大小
    */
    private String iconsize;  
    /**
    *图标颜色
    */
    private String iconcolor;  
    /**
    *是否旋转
    */
    private String iconspin;  
    /**
    *标题颜色
    */
    private String titlecolor;  
    /**
    *url
    */
    private String url;  
    /**
    *菜单唯一标识
    */
    private String menuflag;  
    /**
    *是否按钮
    */
    private Integer isbtn;  
    
    /** default constructor */
    public SysMenu() {
        
        this.id = 0;
        this.nm = "";
        this.pnm = "";
        this.thiscode = "";
        this.code = "";
        this.pcode = "";
        this.name = "";
        this.flag = 0;
        this.icon = "";
        this.iconsize = "";
        this.iconcolor = "";
        this.iconspin = "";
        this.titlecolor = "";
        this.url = "";
        this.menuflag = "";
        this.isbtn = 0;
    
    }
    /** full constructor */
    public SysMenu(
                  Integer id ,
                  String nm ,
                  String pnm ,
                  String thiscode ,
                  String code ,
                  String pcode ,
                  String name ,
                  Integer flag ,
                  String icon ,
                  String iconsize ,
                  String iconcolor ,
                  String iconspin ,
                  String titlecolor ,
                  String url ,
                  String menuflag ,
                  Integer isbtn 
                  ) {
        this.id = id;
        this.nm = nm;
        this.pnm = pnm;
        this.thiscode = thiscode;
        this.code = code;
        this.pcode = pcode;
        this.name = name;
        this.flag = flag;
        this.icon = icon;
        this.iconsize = iconsize;
        this.iconcolor = iconcolor;
        this.iconspin = iconspin;
        this.titlecolor = titlecolor;
        this.url = url;
        this.menuflag = menuflag;
        this.isbtn = isbtn;
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
        s=s+" ,flag="+flag;
        s=s+" ,icon="+icon;
        s=s+" ,iconsize="+iconsize;
        s=s+" ,iconcolor="+iconcolor;
        s=s+" ,iconspin="+iconspin;
        s=s+" ,titlecolor="+titlecolor;
        s=s+" ,url="+url;
        s=s+" ,menuflag="+menuflag;
        s=s+" ,isbtn="+isbtn;
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
     
     , nullable = false 
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

    //图标
    
    @Column(name = "icon"   
      ,length=50  
     
      
           )
    public  String getIcon() {
        return this.icon;
    }
    public void setIcon( String icon) {
        this.icon = icon;
    }

    //图标大小
    
    @Column(name = "iconsize"   
      ,length=20  
     
      
           )
    public  String getIconsize() {
        return this.iconsize;
    }
    public void setIconsize( String iconsize) {
        this.iconsize = iconsize;
    }

    //图标颜色
    
    @Column(name = "iconcolor"   
      ,length=20  
     
      
           )
    public  String getIconcolor() {
        return this.iconcolor;
    }
    public void setIconcolor( String iconcolor) {
        this.iconcolor = iconcolor;
    }

    //是否旋转
    
    @Column(name = "iconspin"   
      ,length=20  
     
      
           )
    public  String getIconspin() {
        return this.iconspin;
    }
    public void setIconspin( String iconspin) {
        this.iconspin = iconspin;
    }

    //标题颜色
    
    @Column(name = "titlecolor"   
      ,length=20  
     
      
           )
    public  String getTitlecolor() {
        return this.titlecolor;
    }
    public void setTitlecolor( String titlecolor) {
        this.titlecolor = titlecolor;
    }

    //url
    
    @Column(name = "url"   
      ,length=300  
     
      
           )
    public  String getUrl() {
        return this.url;
    }
    public void setUrl( String url) {
        this.url = url;
    }

    //菜单唯一标识
    
    @Column(name = "menuflag"   
      ,length=50  
     
     , nullable = false 
           )
    public  String getMenuflag() {
        return this.menuflag;
    }
    public void setMenuflag( String menuflag) {
        this.menuflag = menuflag;
    }

    //是否按钮
    
    @Column(name = "isbtn"   
      
     
     , nullable = false 
           )
    public  Integer getIsbtn() {
        return this.isbtn;
    }
    public void setIsbtn( Integer isbtn) {
        this.isbtn = isbtn;
    }
}
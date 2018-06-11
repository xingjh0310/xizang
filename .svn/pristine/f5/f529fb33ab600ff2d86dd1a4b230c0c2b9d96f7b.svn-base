package com.lyht.business.system.bean;

import javax.persistence.*;

import com.sun.org.glassfish.gmbal.Description;

/**
 * 
 * 系统开关
 * 
 * */
@Entity
@Table(name = "SYS_SET")
public class QcXtSwitch implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 *主键id
	*/
	@Description(key="primary",value="主键id")
	private Integer id;
	
	/**
	 *编码
	*/
	@Description(key="display",value="编码")
	private String code;
	
	/**
	 *开关
	*/
	@Description(key="display",value="编码")
	private Integer flag;
	
	public QcXtSwitch() {
	    
    }
	
	/**
     *主键id
   */
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name = "ID"   , nullable = false  )
   public  Integer getId() {
       return this.id;
   }
   public void setId( Integer id) {
       this.id = id;
   }   

   /**
     *编码
   */
   @Column(name = "CODE"  ,length=50     )
   public  String getCode() {
       return this.code;
   }
   public void setCode( String code) {
       this.code = code;
   }   
   
  /**
    *三权分立开关
  */
  @Column(name = "IS_SQ")
  public  Integer getFlag() {
      return this.flag;
  }
  public void setFlag( Integer flag) {
      this.flag = flag;
  } 
}

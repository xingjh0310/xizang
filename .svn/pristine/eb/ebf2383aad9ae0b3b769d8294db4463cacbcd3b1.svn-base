package com.lyht.business.system.bean;

import java.io.Serializable;

import javax.persistence.*;
import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "SYS_REF_ENGINEER")
@SuppressWarnings("restriction")
public class SysRefEngineer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="主键")
	private int id;
	@Description(key="display",value="唯一编码")
	private String nm;
	@Description(key="display",value="表A编码")
	private String tableACode;
	@Description(key="display",value="表A名称")
	private String tableAName;
	@Description(key="display",value="表B编码")
	private String tableBCode;
	@Description(key="display",value="表B名称")
	private String tableBName;
	@Description(key="display",value="表关联")
	private String refTable;
	
	public SysRefEngineer() {
		this.id=0;
        this.nm = "";
        this.tableACode = "";
        this.tableAName = "";
        this.tableBCode="";
        this.tableBName = "";
        this.refTable = "";
    }

	public SysRefEngineer(
			int id,
			String nm,
			String tableACode,
			String tableAName,
			String tableBCode,
			String tableBName,
			String refTable){
		this.id=0;
        this.nm = nm;
        this.tableACode = tableACode;
        this.tableAName = tableAName;
        this.tableBCode = tableBCode;
        this.tableBName = tableBName;
        this.refTable = refTable;
	}
	
	@Override
	public String toString() {
		String s="";
    	s=	"{";
    	s=s+"id="+id;
    	s=s+",nm="+nm;
        s=s+",tableACode="+tableACode;
        s=s+",tableAName="+tableAName;
        s=s+",tableBCode="+tableBCode;
        s=s+",tableBName="+tableBName;
        s=s+",refTable="+refTable;
    	s=s+"}";
    	return s;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id" )
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nm",length=30 )
	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	@Column(name = "table_a_code",length=30 )
	public String getTableACode() {
		return tableACode;
	}

	public void setTableACode(String tableACode) {
		this.tableACode = tableACode;
	}

	@Column(name = "table_a_name",length=30 )
	public String getTableAName() {
		return tableAName;
	}

	public void setTableAName(String tableAName) {
		this.tableAName = tableAName;
	}

	@Column(name = "table_b_code",length=30 )
	public String getTableBCode() {
		return tableBCode;
	}

	public void setTableBCode(String tableBCode) {
		this.tableBCode = tableBCode;
	}

	@Column(name = "table_b_name",length=30 )
	public String getTableBName() {
		return tableBName;
	}

	public void setTableBName(String tableBName) {
		this.tableBName = tableBName;
	}

	@Column(name = "ref_table",length=30 )
	public String getRefTable() {
		return refTable;
	}

	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}
	
}

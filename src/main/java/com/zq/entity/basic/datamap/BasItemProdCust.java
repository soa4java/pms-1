package com.zq.entity.basic.datamap;
/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
import java.io.Serializable;
import javax.persistence.*;
/**
 * 项目与产品客户关系
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="bas_item_prod_cust")
public class BasItemProdCust implements Serializable {
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @date 2017年7月16日 下午11:12:56 
	*/
	private static final long serialVersionUID = 6944472114715591945L;

	public BasItemProdCust() {
	}
	
	@Column(name="id", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="BASITEMPRODCUST_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="BASITEMPRODCUST_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="company_id", nullable=true, length=10)	
	private Integer companyId;
	
	@Column(name="create_time", nullable=true)	
	private java.sql.Timestamp createTime;
	
	@Column(name="creator", nullable=true, length=50)	
	private String creator;
	
	@Column(name="modify_time", nullable=true)	
	private java.sql.Timestamp modifyTime;
	
	@Column(name="last_reviser", nullable=true, length=50)	
	private String lastReviser;
	
	@Column(name="itemset_code", nullable=true, length=50)	
	private String itemsetCode;
	
	@Column(name="customer_code", nullable=true, length=50)	
	private String customerCode;
	
	@Column(name="product_code", nullable=true, length=50)	
	private String productCode;
	
	/**
	 * ID
	 */
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	/**
	 * 公司ID
	 */
	public void setCompanyId(int value) {
		setCompanyId(new Integer(value));
	}
	
	/**
	 * 公司ID
	 */
	public void setCompanyId(Integer value) {
		this.companyId = value;
	}
	
	/**
	 * 公司ID
	 */
	public Integer getCompanyId() {
		return companyId;
	}
	
	/**
	 * 创建时间
	 */
	public void setCreateTime(java.sql.Timestamp value) {
		this.createTime = value;
	}
	
	/**
	 * 创建时间
	 */
	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}
	
	/**
	 * 创建人
	 */
	public void setCreator(String value) {
		this.creator = value;
	}
	
	/**
	 * 创建人
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * 最后修改时间
	 */
	public void setModifyTime(java.sql.Timestamp value) {
		this.modifyTime = value;
	}
	
	/**
	 * 最后修改时间
	 */
	public java.sql.Timestamp getModifyTime() {
		return modifyTime;
	}
	
	/**
	 * 修改人
	 */
	public void setLastReviser(String value) {
		this.lastReviser = value;
	}
	
	/**
	 * 修改人
	 */
	public String getLastReviser() {
		return lastReviser;
	}
	
	/**
	 * 项目集编号 bas_ itemset中itemset_code
	 */
	public void setItemsetCode(String value) {
		this.itemsetCode = value;
	}
	
	/**
	 * 项目集编号 bas_ itemset中itemset_code
	 */
	public String getItemsetCode() {
		return itemsetCode;
	}
	
	/**
	 * 客户编号 bas_customer中customer_code
	 */
	public void setCustomerCode(String value) {
		this.customerCode = value;
	}
	
	/**
	 * 客户编号 bas_customer中customer_code
	 */
	public String getCustomerCode() {
		return customerCode;
	}
	
	/**
	 * 产品编号 bas_product中product_code
	 */
	public void setProductCode(String value) {
		this.productCode = value;
	}
	
	/**
	 * 产品编号 bas_product中product_code
	 */
	public String getProductCode() {
		return productCode;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}

package com.zq.vo.basic.datamap;
/**
 * 收入目标-按部门
 */

public class BasIncometargBydeptVO {
	
	private int id;
	
	private Integer companyId;
		
	private java.sql.Timestamp createTime;
	
	private String creator;
	
	private java.sql.Timestamp modifyTime;
	
	private String lastReviser;
	
	private String year;
	
	private Integer deptCode;
	
	private String annualGoal;
	
	/**
	 * ID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * 年度
	 */
	public void setYear(String value) {
		this.year = value;
	}
	
	/**
	 * 年度
	 */
	public String getYear() {
		return year;
	}
	
	/**
	 * 部门编号
	 */
	public void setDeptCode(int value) {
		setDeptCode(new Integer(value));
	}
	
	/**
	 * 部门编号
	 */
	public void setDeptCode(Integer value) {
		this.deptCode = value;
	}
	
	/**
	 * 部门编号
	 */
	public Integer getDeptCode() {
		return deptCode;
	}
	
	/**
	 * 全年目标
	 */
	public void setAnnualGoal(String value) {
		this.annualGoal = value;
	}
	
	/**
	 * 全年目标
	 */
	public String getAnnualGoal() {
		return annualGoal;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}

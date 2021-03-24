package zytb.bean;

import java.util.Arrays;
import java.util.List;

public class LookUpCommon implements Comparable {

	private String name;
	private String type;
	private String major;
	private String remark;
	private String professions;
	//private String majorProfessions;
	private List<String> professionList;
	// private List<String> majorProfessionList;
	private int professionsSize;
	private String professionRemarks;
	private List<String> professionRemarkList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getProfessionsSize() {
		return professionsSize;
	}

	public void setProfessionsSize(int professionsSize) {
		this.professionsSize = professionsSize;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProfessions() {
		return professions;
	}

	public void setProfessions(String professions) {
		this.professionList = Arrays.asList(professions.split(","));
		this.professions = professions;
	}

	/*public String getMajorProfessions() {
		return majorProfessions;
	}*/

	/*
	 * public void setMajorProfessions(String majorProfessions) {
	 * this.majorProfessionList = Arrays.asList(majorProfessions.split(","));
	 * this.majorProfessions = majorProfessions; }
	 */

	public List<String> getProfessionList() {
		return professionList;
	}

	public void setProfessionList(List<String> professionList) {
		this.professionList = professionList;
	}

	/*
	 * public List<String> getMajorProfessionList() { return
	 * majorProfessionList; }
	 * 
	 * public void setMajorProfessionList(List<String> majorProfessionList) {
	 * this.majorProfessionList = majorProfessionList; }
	 */

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		LookUpCommon common = (LookUpCommon) o;
		int professionsSize = common.getProfessionsSize();
		if (this.professionsSize >= professionsSize) {
			return -1;
		}
		return 1;
	}

	public String getProfessionRemarks() {
		return professionRemarks;
	}

	public void setProfessionRemarks(String professionRemarks) {
//		System.out.println(professionRemarks);
//		System.out.println(professionRemarks.split(",").length);
		this.professionRemarkList = Arrays.asList(professionRemarks.split(","));
		this.professionRemarks = professionRemarks;
	}

	public List<String> getProfessionRemarkList() {
		return professionRemarkList;
	}

	public void setProfessionRemarkList(List<String> professionRemarkList) {
		this.professionRemarkList = professionRemarkList;
	}

}

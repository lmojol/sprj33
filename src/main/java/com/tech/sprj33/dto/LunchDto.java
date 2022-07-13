package com.tech.sprj33.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;


//@Setter
//@Getter
public class LunchDto {
	private int lno;
	private String lmenu;
	private int lprice;
	private String lcontent;
	private int lgroup;
	private int lstep;
	private int lindent;
	private int lhit;
	private String lfileimg;
	
	public LunchDto() {
		
	}

	public LunchDto(int lno, String lmenu, int lprice, String lcontent, int lgroup, int lstep, int lindent, int lhit,
			String lfileimg) {
		this.lno = lno;
		this.lmenu = lmenu;
		this.lprice = lprice;
		this.lcontent = lcontent;
		this.lgroup = lgroup;
		this.lstep = lstep;
		this.lindent = lindent;
		this.lhit = lhit;
		this.lfileimg = lfileimg;
	}

	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	public String getLmenu() {
		return lmenu;
	}

	public void setLmenu(String lmenu) {
		this.lmenu = lmenu;
	}

	public int getLprice() {
		return lprice;
	}

	public void setLprice(int lprice) {
		this.lprice = lprice;
	}

	public String getLcontent() {
		return lcontent;
	}

	public void setLcontent(String lcontent) {
		this.lcontent = lcontent;
	}

	public int getLgroup() {
		return lgroup;
	}

	public void setLgroup(int lgroup) {
		this.lgroup = lgroup;
	}

	public int getLstep() {
		return lstep;
	}

	public void setLstep(int lstep) {
		this.lstep = lstep;
	}

	public int getLindent() {
		return lindent;
	}

	public void setLindent(int lindent) {
		this.lindent = lindent;
	}

	public int getLhit() {
		return lhit;
	}

	public void setLhit(int lhit) {
		this.lhit = lhit;
	}

	public String getLfileimg() {
		return lfileimg;
	}

	public void setLfileimg(String lfileimg) {
		this.lfileimg = lfileimg;
	}
	
	

}

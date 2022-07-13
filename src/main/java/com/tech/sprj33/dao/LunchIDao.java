package com.tech.sprj33.dao;

import java.util.ArrayList;

import com.tech.sprj33.dto.LunchDto;



public interface LunchIDao {
	public ArrayList<LunchDto> list(int start,int end,String searchKeyword,String selNum);
	public void write(String lmenu,
			String lprice, String lcontent,String fname);
	
	
	public LunchDto contentView(String sbid);
	public void upHit(String sbid);
	public void delete(String bid);
	public void modify(String lno, String lmenu,
			String lcontent, String lprice);
	public LunchDto replyView(String sbid);
	
//	reply
	public void reply(String bid, String bName,
			String bTitle, String bContent,
			String bgroup, String bstep,
			String bindent);
	
	
//	replyShape
	public void replyShape(String bgroup,String bstep);
	
	public int selectBoardTotCount(String searchKeyword,String selNum);
	/*public int selectBoardTotCount2(String searchKeyword);
	public int selectBoardTotCount3(String searchKeyword);
	public int selectBoardTotCount0(String searchKeyword);*/
	
	
	
}

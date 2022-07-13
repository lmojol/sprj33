package com.tech.sprj33.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.sprj33.dao.JoinIDao;
import com.tech.sprj33.vopage.SearchVO;

@Controller
@RequestMapping("/join/*")
public class JoinController {
//	BServiceInf commandInf;
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/joinform")
	public String joinform(HttpServletRequest request, SearchVO searchVO, Model model) {
		System.out.println("=========pass by joinform()=============");
//		db접속 데이터 가져오기
//		commandInf=new BListService();
//		commandInf.execute(model);
		
		return "join/joinform";
	}
	@RequestMapping("/join")
	public String join(HttpServletRequest request, SearchVO searchVO, Model model) {
		System.out.println("=========pass by join()=============");
//		db접속 데이터 가져오기
//		commandInf=new BListService();
//		commandInf.execute(model);
		String mid=request.getParameter("mid");
		String mpw=request.getParameter("mpw");
		String mnn=request.getParameter("mnn");
		
		JoinIDao dao=sqlSession.getMapper(JoinIDao.class);
		dao.join(mid, mpw, mnn);
		
		
		return "redirect:/lunchboard/list";
	}

}

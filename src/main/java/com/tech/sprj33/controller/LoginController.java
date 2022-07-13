package com.tech.sprj33.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.sprj33.dao.LoginIDao;
import com.tech.sprj33.dto.MemberDto;
import com.tech.sprj33.vopage.SearchVO;

@Controller
@RequestMapping("/login/*")
public class LoginController {
//	BServiceInf commandInf;
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/loginform")
	public String loginform(HttpServletRequest request, SearchVO searchVO, Model model) {
		System.out.println("=========pass by loginform()=============");
//		
		return "/login/loginform";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, SearchVO searchVO, Model model) {
		System.out.println("=========pass by login()=============");
//		
		String mid=request.getParameter("mid");
		String mpw=request.getParameter("mpw");
		
		LoginIDao dao=sqlSession.getMapper(LoginIDao.class);
		MemberDto dto=dao.login(mid);
		if(mpw.equals(dto.getMpw())) {
			System.out.println("login success!!!");
			//세션에 담기
			HttpSession session=request.getSession();
			session.setAttribute("mid", mid);
			session.setAttribute("mnn", dto.getMnickname());
			
		}
		
		return "redirect:/lunchboard/list";
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, SearchVO searchVO, Model model) {
		System.out.println("=========pass by logout()=============");
//		
		System.out.println("logout gogo!!!");
		//세션에 담기
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:/lunchboard/list";
	}
}

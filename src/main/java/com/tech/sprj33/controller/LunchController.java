package com.tech.sprj33.controller;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tech.sprj33.dao.LunchIDao;
import com.tech.sprj33.dto.LunchDto;
import com.tech.sprj33.vopage.SearchVO;

@Controller
@RequestMapping("/lunchboard/*")
public class LunchController {
//	BServiceInf commandInf;
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, SearchVO searchVO, Model model) {
		System.out.println("=========pass by list()=============");
//		db접속 데이터 가져오기
//		commandInf=new BListService();
//		commandInf.execute(model);
		
//		search
		String btitle="";
		String bcontent="";
		String[] brdtitle=request.getParameterValues("searchType");
		if(brdtitle!=null) {
			for(int i=0;i<brdtitle.length;i++) {
				System.out.println("brdtitle : "+brdtitle[i]);
			}
		}
		
//		ch 값 변수에 저장
		if(brdtitle!=null) {
			for (String val : brdtitle) {
				if(val.equals("btitle")) {
					model.addAttribute("btitle","true");//검색유지
					btitle="btitle";
				}else if(val.equals("bcontent")) {
					model.addAttribute("bcontent","true");//검색유지
					bcontent="bcontent";
				}
			}
		}
		
		
		String searchKeyword=request.getParameter("sk");
		if(searchKeyword==null)
			searchKeyword="";
		System.out.println("searchKeyWord : "+searchKeyword);
		
		
		
//		paging
		String strPage=request.getParameter("page");
		System.out.println("strPage1 : "+strPage);
		if(strPage==null)
			strPage="1";
		System.out.println("strPage2 : "+strPage);
		
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		
		LunchIDao dao=sqlSession.getMapper(LunchIDao.class);
//		totcnt
		//int total=dao.selectBoardTotCount();
		int total=0;
		if(btitle.equals("btitle") && bcontent.equals("")) {
			total=dao.selectBoardTotCount(searchKeyword,"1");
		}else if(btitle.equals("") && bcontent.equals("bcontent")) {
			total=dao.selectBoardTotCount(searchKeyword,"2");
		}else if(btitle.equals("btitle") && bcontent.equals("bcontent")) {
			total=dao.selectBoardTotCount(searchKeyword,"3");
		}else if(btitle.equals("") && bcontent.equals("")) {
			total=dao.selectBoardTotCount(searchKeyword,"0");
		}
		
		
		
		
		searchVO.pageCalculate(total);
		
		
		System.out.println("Totrowcnt : "+total);
		System.out.println("clickPage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
		
		if(btitle.equals("btitle") && bcontent.equals("")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"1"));
		}else if(btitle.equals("") && bcontent.equals("bcontent")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"2"));
		}else if(btitle.equals("btitle") && bcontent.equals("bcontent")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"3"));
		}else if(btitle.equals("") && bcontent.equals("")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"0"));
		}
		
		model.addAttribute("totRowCnt",total);
		model.addAttribute("searchVO",searchVO);
		return "lunchboard/list";
	}

	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("=========pass by write_view()=============");
		
		return "lunchboard/write_view";
	}
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) throws Exception {
		System.out.println("=========pass by write()=============");
		//db에 글쓰기진행
//		model.addAttribute("request",request);
//		commandInf=new BWriteService();
//		commandInf.execute(model);
//		String bName=request.getParameter("bName");
//		String bTitle=request.getParameter("bTitle");
//		String bContent=request.getParameter("bContent");
		
//		upload
		String attachPath="resources\\upload\\";
		String uploadPath=request.getSession().getServletContext().getRealPath("/");
		System.out.println("uploadPath : "+uploadPath);
		String path=uploadPath+attachPath;
//		
//		String path="C:\\webspring\\springwork\\springwebrboard90_updown2\\src\\main\\webapp\\resources\\upload";
		
//		multipart request 구성
		MultipartRequest req=
				new MultipartRequest(request, path, 1024*1024*20,"utf-8",
						new DefaultFileRenamePolicy());
		String lmenu=req.getParameter("lmenu");
		String lprice=req.getParameter("lprice");
		String lcontent=req.getParameter("lcontent");
		String fname=req.getFilesystemName("file");
		
		System.out.println("filename : "+fname);
		if(fname==null)
			fname="";
		
		
		
//		mybatis작업
		LunchIDao dao=sqlSession.getMapper(LunchIDao.class);
		//for(int i=0;i<1000;i++) {
			dao.write(lmenu, lprice, lcontent,fname);
			//Thread.sleep(50);
		//}
		return "redirect:list";
	}
	
	@RequestMapping("/download")
	public String download(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		System.out.println("=========pass by download()=============");
		String path=request.getParameter("p");
		String fname=request.getParameter("f");
		String bid=request.getParameter("bid");
		
		response.setHeader("Content-Disposition",
				"Attachment;filename="+URLEncoder.encode(fname,"utf-8"));//첨부파일임을 알려주고, 한글처리
		
		//down
		String attachPath="resources\\upload\\";
		String realPath=request.getSession().getServletContext().getRealPath(attachPath)+"\\"+fname;
		
		System.out.println("realpath : "+realPath);
		
		FileInputStream fin=new FileInputStream(realPath);
		ServletOutputStream sout=response.getOutputStream();
		
		byte[] buf=new byte[1024];
		int size=0;
		while ((size=fin.read(buf, 0, 1024))!=-1) {
			sout.write(buf,0,size);
		}
		fin.close();
		sout.close();
				
		return "lunchboard/content_view?bid="+bid;
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("=========pass by content_view()=============");
		
//		model.addAttribute("request",request);
//		commandInf=new BContentViewService();
//		commandInf.execute(model);
		String lno=request.getParameter("lno");
//		mybatis작업
		LunchIDao dao=sqlSession.getMapper(LunchIDao.class);
//		dao.upHit(lno);//upHit처리
		
		LunchDto dto=dao.contentView(lno);
		model.addAttribute("content_view",dto);
		return "lunchboard/content_view";
	}
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request, Model model) {
		System.out.println("=========pass by content_updateform()=============");
//		model.addAttribute("request",request);
//		commandInf=new BContentViewService();
//		commandInf.execute(model);
		
		String lno=request.getParameter("lno");
		LunchIDao dao=sqlSession.getMapper(LunchIDao.class);
		
		LunchDto dto=dao.contentView(lno);
		model.addAttribute("content_view",dto);
		
		return "lunchboard/content_update";
	}
	@RequestMapping(method=RequestMethod.POST,value="/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("=========pass by modify()=============");
//		model.addAttribute("request",request);
//		commandInf=new BModifyService();
//		commandInf.execute(model);
		
		String lno=request.getParameter("lno");
		String lmenu=request.getParameter("lmenu");
		String lcontent=request.getParameter("lcontent");
		String lprice=request.getParameter("lprice");
		
		
		LunchIDao dao=sqlSession.getMapper(LunchIDao.class);
		dao.modify(lno, lmenu, lcontent,lprice);
		
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("=========pass by delete()=============");
//		model.addAttribute("request",request);
//		commandInf=new BDeleteService();
//		commandInf.execute(model);
		
		String lno=request.getParameter("lno");
		LunchIDao dao=sqlSession.getMapper(LunchIDao.class);
		dao.delete(lno);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("=========pass by reply_view()=============");
//		model.addAttribute("request",request);
//		commandInf=new BReplyViewService();
//		commandInf.execute(model);
		
		String bid=request.getParameter("bid");
		LunchIDao dao=sqlSession.getMapper(LunchIDao.class);
		
		LunchDto dto=dao.replyView(bid);
		model.addAttribute("reply_view",dto);
		
		return "lunchboard/reply_view";
	}
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("=========pass by reply()=============");
//		model.addAttribute("request",request);
//		commandInf=new BReplyService();
//		commandInf.execute(model);
		String bid=request.getParameter("bid");
		String bName=request.getParameter("bName");
		String bTitle=request.getParameter("bTitle");
		String bContent=request.getParameter("bContent");
		String bgroup=request.getParameter("bgroup");
		String bstep=request.getParameter("bstep");
		String bindent=request.getParameter("bindent");
		
		LunchIDao dao=sqlSession.getMapper(LunchIDao.class);
		dao.replyShape(bgroup, bstep);
		dao.reply(bid, bName, bTitle, bContent, bgroup, bstep, bindent);
		return "redirect:list";
	}
}

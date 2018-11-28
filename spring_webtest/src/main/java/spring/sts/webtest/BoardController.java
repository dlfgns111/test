package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.model.board.BoardDAO;
import spring.model.board.BoardMgr;
import spring.model.board.BreplyDAO;
import spring.model.board.BreplyDTO;
import spring.utility.webtest.Utility;

@Controller
public class BoardController {

	@Autowired
	private BoardDAO dao;

	@Autowired
	private BoardMgr mgr;
	
	@Autowired
	private BreplyDAO rdao;
		
	@RequestMapping("/board/read")
	public String read(int num, Model model, HttpServletRequest request) {
		dao.upCount(num);
		BreplyDTO dto = rdao.read(num);

		model.addAttribute("dto", dto);
		model.addAttribute("content", dto.getContent().replaceAll("\r\n", "<br>"));
		
		/***댓글 처리***/
		
		String url = "read"; //댓글페이지의 매개변수
		int nPage = 1;
		if(request.getParameter("nPage")!=null) { 
			nPage=Integer.parseInt(request.getParameter("nPage"));
		}
		
		int recordPerPage = 3;
		int sno = ((nPage-1)*recordPerPage)+1;
		int eno = nPage * recordPerPage;
		
		Map map = new HashMap();
		
		map.put("sno", sno);	
		map.put("eno", eno);
		map.put("bbsno", num);
		
		List<BreplyDTO> rlist = rdao.list(map);
		int total = rdao.total(map);
		
		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		String col = request.getParameter("col");
		String word = request.getParameter("word");
		
		String paging = Utility.rpaging(total, nowPage, recordPerPage, col, word, num,
				nPage, url);
		
		model.addAttribute("rlist", rlist);
		model.addAttribute("nPage", nPage);
		model.addAttribute("paging", paging);
		
		/*** 댓글 처리 end ***/
				
		return "/board/read";
	}

	@RequestMapping("/board/list")
	public String list(HttpServletRequest request, Model model) {
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		if (col.equals("total"))
			word = "";

		int nowPage = 1;
		int recordPerPage = 5;

		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}

	   int sno = ((nowPage-1)*recordPerPage) + 1;
	   int eno = nowPage * recordPerPage;
	   
	   Map map = new HashMap();
	   map.put("col", col);
	   map.put("word", word);
	   map.put("sno", sno);
	   map.put("eno", eno);

		// 1. model 사용
		List<BreplyDTO> list = rdao.list(map);
		int total = rdao.total(map);
		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
		
		// 2. request 저장
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		
		return "/board/list";
	}

}

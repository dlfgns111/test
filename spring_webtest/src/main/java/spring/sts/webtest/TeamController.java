package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.team.TeamDAO;
import spring.model.team.TeamDTO;
import spring.model.team.TeamService;
import spring.utility.webtest.Utility;

@Controller
public class TeamController {
	
	@Autowired
	private TeamDAO dao;
	
	@Autowired
	private TeamService mgr;
		
	@RequestMapping(value="/team/reply",method=RequestMethod.POST)
		public String reply(TeamDTO dto, Model model, HttpServletRequest request) {
										
		boolean flag = mgr.reply(dto);
		if(flag) {
			request.setAttribute("flag", flag);
			return "redirect:/team/list";
		}else {
			return  "/error/error";
		}
	}
	@RequestMapping(value="/team/reply",method=RequestMethod.GET)
	public String reply(int no, Model model) {
		
		TeamDTO dto = dao.replyRead(no);
		
		model.addAttribute("dto", dto);
			
		return"/team/reply";
	}
	
	@RequestMapping(value="/team/delete", method=RequestMethod.GET)
	public String delete(int no, HttpServletRequest request, Model model) {
		
		TeamDTO dto = dao.read(no);
		
		
		boolean dflag = false;
		dflag = dao.checkRefnum(dto.getNo());
		
		boolean flag = false;
		flag = dao.delete(dto.getNo());
					
		model.addAttribute("flag", flag);
		model.addAttribute("dflag", dflag);
		
		return "redirect:/team/list";
		
	}
	
	@RequestMapping(value="/team/update", method=RequestMethod.POST)
	public String update(TeamDTO dto, Model model,HttpServletRequest request) {
								
		boolean flag = dao.update(dto);
		
		if(flag) {
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			return "redirect:/team/list";
		}else {
			return  "/error/error";
	}
		
}


	@RequestMapping(value="/team/update", method=RequestMethod.GET)
	public String update(int no, Model model) {
		
		TeamDTO dto = dao.read(no);
		
		model.addAttribute("dto", dto);
		
		return"/team/update";
	}
	
	@RequestMapping("/team/read")
	public String read(int no, HttpServletRequest request) {
	
			
		TeamDTO dto = dao.read(no);
		
		request.setAttribute("dto", dto);
				
		return"/team/read";
	}
	
	
	@RequestMapping(value="/team/create", method=RequestMethod.POST)
	public String create(HttpServletRequest request) {
		
		TeamDTO dto = new TeamDTO();
		
		dto.setName(request.getParameter("name"));
		dto.setGender(request.getParameter("gender"));
		dto.setHobby(request.getParameter("hobby"));
		dto.setSkill(request.getParameterValues("skill"));
		dto.setPhone(request.getParameter("phone"));
		dto.setZipcode(request.getParameter("zipcod"));
		dto.setAddress(request.getParameter("address"));
		dto.setAddress2(request.getParameter("address2"));
		
			
		boolean flag = dao.create(dto);
		
		if(flag) {
			return "redirect:/team/list";
		}else {
			return  "/error/error";
	}

}
	
	@RequestMapping(value="/team/create", method=RequestMethod.GET)
	public String create() {
		
		return"/team/create";
	}
	
	@RequestMapping("/team/list")
	public String list(HttpServletRequest request, Model model) {
				
		//검색관련 처리
		   String col = Utility.checkNull(request.getParameter("col"));
		   String word = Utility.checkNull(request.getParameter("word"));

		   if(col.equals("total")) word= "";
		   
		   //paging관련
		   int nowPage = 1;
		   int recordPerPage = 5;
		   
		   if(request.getParameter("nowPage")!=null){
		   	   nowPage = Integer.parseInt(request.getParameter("nowPage"));
		   }
		   
		   //DB에서 가져올 레코드의 순번
		   int sno = ((nowPage-1)*recordPerPage) + 1;
		   int eno = nowPage * recordPerPage;
		   
		   Map map = new HashMap();
		   map.put("col", col);
		   map.put("word", word);
		   map.put("sno", sno);
		   map.put("eno", eno);
		   		   		   
		   List<TeamDTO> list = dao.list(map);
		   //전체레코드 갯수(col,word)
		   int totalRecord = dao.total(map);
		   
		   String paging= Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		   
		   
		   model.addAttribute("list", list);
		   model.addAttribute("paging", paging);
		   model.addAttribute("nowPage", nowPage);
		   model.addAttribute("col", col);
		   model.addAttribute("word", word);
		
		return "/team/list";
	}
}

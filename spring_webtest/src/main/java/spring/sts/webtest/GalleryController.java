package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import spring.model.bbs.BbsDTO;
import spring.model.gallery.GalleryDAO;
import spring.model.gallery.GalleryDTO;
import spring.model.member.MemberDTO;

import spring.utility.webtest.Utility;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryDAO dao;
	
	
	@RequestMapping(value="/gallery/updatePasswd",method=RequestMethod.POST)
	public String updatePasswd(GalleryDTO dto, Model model) {
		
			Map map = new HashMap();
			
			map.put("gno", dto.getGno());
			map.put("pw", dto.getPw());
			
			map.put("newpw", "newpw");
			boolean gflag = dao.passwdCheck(map);
			
			boolean flag = dao.updatePasswd(map);
			
			if(gflag) {
				if(flag) {
				model.addAttribute("gflag", gflag);
				model.addAttribute("flag", flag);
							
				return"/gallery/updateProc";
			}else {
				return"/error/error";
			}
		}
			return"/error/error";
	}
	@RequestMapping(value="/gallery/updatePasswd",method=RequestMethod.GET)
	public String updatePasswd() {
		
		return"/gallery/updatePasswd";
	}
	
	@RequestMapping(value="/gallery/update",method=RequestMethod.POST)
	public String update(GalleryDTO dto, String oldfile, Model model, HttpServletRequest request) {
		
		String upDir = request.getRealPath("/gallery/storage");
		
		String fname = null;
		
		if (dto.getFnameMF().getSize() > 0) {
			if(oldfile != null&& !oldfile.equals("noimage.jpg")) {
				Utility.deleteFile(upDir, oldfile);
			}
			fname = Utility.saveFileSpring(dto.getFnameMF(), upDir);
		}else {
			fname = oldfile ;
		}
		
		dto.setFname(fname);
		
		Map map = new HashMap();
		map.put("gno", dto.getGno());
		map.put("pw", dto.getPw());
				
		boolean gflag = dao.passwdCheck(map);
		boolean flag=false;
		if(gflag) {
			flag=dao.updateInfo(map);
		}
		
		if(flag) {
			
			model.addAttribute("gno", dto.getGno());
			model.addAttribute("flag", flag);
			model.addAttribute("gflag", gflag);
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			
			return"redirect:/gallery/list";
		}else {
			Utility.deleteFile(upDir, fname);
			return "error";
		}
	}
	@RequestMapping(value="/gallery/update",method=RequestMethod.GET)
	public String update(GalleryDTO dto, HttpServletRequest request) {
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		dto = dao.read(gno);
		
		request.setAttribute("dto", dto);
		request.setAttribute("col", request.getParameter("col"));
		request.setAttribute("word", request.getParameter("word"));
		request.setAttribute("nowPage", request.getParameter("nowPage"));
		
		return"/gallery/update";
	}
	
	@RequestMapping("/gallery/read")
	public String read(HttpServletRequest request) {
		
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		dao.upViewCnt(gno);
		
		GalleryDTO dto = dao.read(gno);
		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");
		
		List list = dao.readList(gno);
		int[] lGno = (int[]) list.get(0);
		String[] lFname = (String[]) list.get(1);
		
		request.setAttribute("dto", dto);
		request.setAttribute("lGno", lGno);
		request.setAttribute("lFname", lFname);
		request.setAttribute("col", request.getParameter("col"));
		request.setAttribute("word", request.getParameter("word"));
		request.setAttribute("nowPage", request.getParameter("nowPage"));
		
		
		return"/gallery/read";
	}
	
	@RequestMapping(value="/gallery/create",method=RequestMethod.POST)
	public String create(GalleryDTO dto, HttpServletRequest request) {
				
	
		String upDir="/gallery/storage";
		

		upDir = request.getRealPath(upDir);
		
		//UploadSave upload = (UploadSave) request.getAttribute("upload");
						
		int size = (int)dto.getFnameMF().getSize();
		String fname = null;
		if(size>0){
			fname = Utility.saveFileSpring(dto.getFnameMF(), upDir);		
		} else {
			fname = "noimage.jpg";
		}
		
		dto.setFname(fname);
		
		boolean flag = dao.create(dto);
		
		request.setAttribute("flag", flag);
		
		return"redirect:/gallery/list";
	}
	
	@RequestMapping(value="/gallery/create",method=RequestMethod.GET)
	public String create() {
		
		return"/gallery/create";
	}
	
	@RequestMapping("/gallery/list")
	public String list(HttpServletRequest request) {
		
		// 검색관련

		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));

		if (col.equals("total"))
			word = "";

		// 페이징관련

		int nowPage = 1;
		int recordPerPage = 5;

		if (request.getParameter("nowPage") != null)
			nowPage = Integer.parseInt(request.getParameter("nowPage"));

		// DB에서 가져올 번호계산
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		List<GalleryDTO> list = dao.list(map);
		int totalRecord = dao.total(map);

		String paging = Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);

		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);

		
	return"/gallery/list";	
	}
}

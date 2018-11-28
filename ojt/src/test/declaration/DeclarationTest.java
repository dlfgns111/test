package test.declaration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import declaration.DeclarationDAO;
import declaration.DeclarationDTO;

public class DeclarationTest {
	public static void main(String[] args) {
		DeclarationDAO dao = new DeclarationDAO();
		
		//create(dao);
		//read(dao);
		//update(dao);
		//delete(dao);
		//list(dao);
		
	}




	private static void list(DeclarationDAO dao) {
		Map map = new HashMap();
		List<DeclarationDTO> list = dao.list(map);
		
		Iterator<DeclarationDTO> iter = list.iterator();
		
		while(iter.hasNext()) {
			DeclarationDTO dto = iter.next();
			p(dto);
			p("-------------------------------");
		}
		
	}



	private static void delete(DeclarationDAO dao) {
		int d_num = 1;
		if(dao.delete(d_num)) {
			p("delete성공");
		}else {
			p("delete실패");
		}
		
	}



	private static void update(DeclarationDAO dao) {
		DeclarationDTO dto = new DeclarationDTO();
		dto = dao.read(2);
		dto.setD_title("관리자답변");
		dto.setD_content("관리자답변");	
		dto.setA_ID("admin");		
		if(dao.update(dto)) {
			p("update성공");
		}else {
			p("update실패");
		}
		
	}



	private static void read(DeclarationDAO dao) {
		DeclarationDTO dto = new DeclarationDTO();
		int d_num = 1;
		dto = dao.read(d_num);
		p(dto);
		
	}



	private static void create(DeclarationDAO dao) {
		DeclarationDTO dto = new DeclarationDTO();
		
		dto.setD_title("신고");
		dto.setD_content("신고게시판내용");	
		dto.setC_ID("회사");
		
		
		if(dao.create(dto)) {
			p("create성공");
		}else {
			p("create실패");
		}
	}
	
	

	private static void p(String string) {
		System.out.println(string);
		
	}
	private static void p(DeclarationDTO dto) {
		System.out.println("번호"+ dto.getD_num());
		System.out.println("제목"+ dto.getD_title());
		System.out.println("내용"+ dto.getD_content());
		System.out.println("조회수"+ dto.getD_viewcnt());
		System.out.println("등록일"+ dto.getD_date());
		System.out.println("개인아이디"+ dto.getU_ID());
		System.out.println("회사아이디"+ dto.getC_ID());
		System.out.println("관리자아이디"+ dto.getA_ID());
	}
	
}

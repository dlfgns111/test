package test.notice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import notice.NoticeDAO;
import notice.NoticeDTO;

public class NoticeTest {
	public static void main(String[] args) {
		NoticeDAO dao = new NoticeDAO();
		
		//create(dao);
		//read(dao);
		//update(dao);
		//delete(dao);
		//list(dao);
		
	}




	private static void list(NoticeDAO dao) {
		Map map = new HashMap();
		List<NoticeDTO> list = dao.list(map);
		
		Iterator<NoticeDTO> iter = list.iterator();
		
		while(iter.hasNext()) {
			NoticeDTO dto = iter.next();
			p(dto);
			p("-------------------------------");
		}
		
	}



	private static void delete(NoticeDAO dao) {
		int d_num = 1;
		if(dao.delete(d_num)) {
			p("delete성공");
		}else {
			p("delete실패");
		}
		
	}



	private static void update(NoticeDAO dao) {
		NoticeDTO dto = new NoticeDTO();
		dto = dao.read(2);
		dto.setN_title("관리자답변");
		dto.setN_content("관리자답변");	
		dto.setA_ID("admin");		
		if(dao.update(dto)) {
			p("update성공");
		}else {
			p("update실패");
		}
		
	}



	private static void read(NoticeDAO dao) {
		NoticeDTO dto = new NoticeDTO();
		int d_num = 1;
		dto = dao.read(d_num);
		p(dto);
		
	}



	private static void create(NoticeDAO dao) {
		NoticeDTO dto = new NoticeDTO();
		
		dto.setN_title("공지사항");
		dto.setN_content("공지사항 내용");	
				
		if(dao.create(dto)) {
			p("create성공");
		}else {
			p("create실패");
		}
	}
	
	

	private static void p(String string) {
		System.out.println(string);
		
	}
	private static void p(NoticeDTO dto) {
		System.out.println("번호"+ dto.getN_num());
		System.out.println("제목"+ dto.getN_title());
		System.out.println("내용"+ dto.getN_content());
		System.out.println("조회수"+ dto.getN_viewcnt());
		System.out.println("등록일"+ dto.getN_date());
		System.out.println("관리자아이디"+ dto.getA_ID());
	}
}

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
		//udate(dao);
		//delete(dao);
		//list(dao);
		total(dao);
		
	}




	private static void total(NoticeDAO dao) {
		Map map = new HashMap();
		
		int total = dao.total(map);
		
		p("레코드 개수"+total);
		
	}




	private static void list(NoticeDAO dao) {
		Map map = new HashMap();
		map.put("sno", 1);
		map.put("eno", 5);
		
		List<NoticeDTO> list = dao.list(map);
		Iterator<NoticeDTO> iter = list.iterator();
		
		while(iter.hasNext()) {
			NoticeDTO dto = iter.next();
			p(dto);
			p("-------------------------------");
		}
		
	}



	private static void delete(NoticeDAO dao) {
		int n_num = 1;
		if(dao.delete(n_num)) {
			p("delete성공");
		}else {
			p("delete실패");
		}
		
	}



	private static void update(NoticeDAO dao) {
		NoticeDTO dto = new NoticeDTO();
		dto = dao.read(1);
		dto.setN_title("공지사항업데이트");
		dto.setN_content("공지사항업데이트");			
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

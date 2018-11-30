package test.n_comment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import n_comment.N_CommentDAO;
import n_comment.N_CommentDTO;
import notice.NoticeDTO;

public class N_CommentTest {
	public static void main(String[] args) {
		N_CommentDAO dao = new N_CommentDAO();

		

		// create(dao);
		// read(dao);
		// update(dao);
		// delete(dao);
		// bdelete(dao);
		// list(dao);
		 total(dao);

	}

	private static void total(N_CommentDAO dao) {
		
		int N_num = 2;
		
		int total = dao.total(N_num);

		p("전체 레코드수:" + total);

	}

	private static void list(N_CommentDAO dao) {
		
		int sno = 1;
		int eno = 5;
		
		Map map = new HashMap();

		map.put("n_num", 2);
		map.put("sno", 1);
		map.put("eno", 5);

		List<N_CommentDTO> list = dao.list(map);

		for (int i = 0; i < list.size(); i++) {
			N_CommentDTO dto = list.get(i);

			p(dto);
			System.out.println("-------------------");
		}
	}

	private static void read(N_CommentDAO dao) {
		int Nc_num = 1;
		N_CommentDTO dto = dao.read(Nc_num);
		p(dto);
	}

	private static void delete(N_CommentDAO dao) {

		int Nc_num = 1;

		if (dao.delete(Nc_num)) {
			p("DELETE 성공");
		} else {
			p("DELETE 실패");
		}
	}
	
	private static void bdelete(N_CommentDAO dao) {
		
		int N_num = 2;
		
		if (dao.bdelete(N_num)) {
			p("BDELETE 성공");
		} else {
			p("BDELETE 실패");
		}
	}

	private static void update(N_CommentDAO dao) {
		N_CommentDTO dto = new N_CommentDTO();

		dto.setNc_content("댓글 업데이트 테스트");		
		dto.setNc_num(1);

		if (dao.update(dto)) {
			p("UPDATE 성공");
		} else {
			p("UPDATE 실패");
		}
	}

	private static void create(N_CommentDAO dao) {
		N_CommentDTO dto = new N_CommentDTO();

		dto.setNc_content("댓글 테스트 입니다.");
		dto.setNc_writer("931110");
		dto.setN_num(2);

		if (dao.create(dto)) {
			p("CREATE 성공");
		} else {
			p("CREATE 실패");
		}
	}

	private static void p(String string) {
		System.out.println(string);
	}

	private static void p(N_CommentDTO dto) {

		System.out.println("번호 : " + dto.getNc_num());
		System.out.println("내용 : " + dto.getNc_content());
		System.out.println("날짜 : " + dto.getNc_date());
		System.out.println("아이디 : " + dto.getNc_writer());
		System.out.println("부모글번호:" + dto.getN_num());

	}

}

package spring.model.board;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;


public class BoardTest {
	public static void main(String[] args) {
		
		
		
		Resource resource = new ClassPathResource("daoTest.xml");
		
		BeanFactory factory = new XmlBeanFactory(resource);
		
		BoardDAO dao = (BoardDAO)factory.getBean("dao");
		
	// 	readReply(dao);	
	//	total(dao);
	//	create(dao);
	//	update(dao);
	//	delete(dao);
	//	read(dao);
	//	list(dao);
	//	upCount(dao);
		reply(dao);
	//	paasswdCheck(dao);
	//	checkRefnum(dao);
	
	}

	private static void checkRefnum(BoardDAO dao) {
		if(dao.checkRefnum(1)) {
			p("답변있는 글이므로 삭제할 수 없습니다.");
		}else {
			p("삭제 가능합니다.");
		}
		
	}

	private static void reply(BoardDAO dao) {
		
		BoardDTO dto = dao.readReply(11);
		
		dto.setName("MyBaTest");
		dto.setSubject("subject");
		dto.setContent("content");
		dto.setPasswd("passwd");
	/*	dto.setFilename("mybatisTest");
		dto.setFilesize(1);*/
		dto.setIp("127.0.0.1");
		
		Map map = new HashMap();
		map.put("ref", dto.getRef());
		map.put("ansnum", dto.getAnsnum());
			
		dao.upAnsnum(map);
		
		if(dao.insertReply(dto)) {
			p("성공");
		}else {
			p("실패");
		}
				
	}




	private static void readReply(BoardDAO dao) {
		int no = 2;
		BoardDTO dto = dao.readReply(no);
		p("번호:"+dto.getNum());
		p("부모글번호:"+dto.getRef());
		p("indent"+dto.getIndent());
		p("제목"+dto.getSubject());
	}

	
	
	private static void total(BoardDAO dao) {
		Map map = new HashMap();
		
		map.put("col", "name");
		map.put("word", "test");
		
		int total = dao.getTotal(map);
		
		p("전체 레코드수:"+total);
		
	}
	
	private static void paasswdCheck(BoardDAO dao) {
		Map map = new HashMap();
		map.put("num",  1);
		map.put("passwd",  "1243");
		if(dao.passwdCheck(map )) {
			p("비밀번호 일치");
		}else {
			p("비밀번호 불일치");
		}
	}

	private static void upCount(BoardDAO dao) {
		dao.upCount(1);
	}

	private static void list(BoardDAO dao) {
		
		Map map = new HashMap();

		map.put("col", "name");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);
		
		List<BoardDTO> list = dao.getList(map);
		
		Iterator<BoardDTO> iter = list.iterator();
		
		while(iter.hasNext()) {
			BoardDTO dto = iter.next();
			p(dto);
			p("----------------------");
		}
		
		for(int i=0; i<list.size(); i++) {
			BoardDTO dto = list.get(i);
			
			p(dto);
		}
	}

	private static void read(BoardDAO dao) {
		int no = 1;
		BoardDTO dto = dao.read(1);
		p(dto);
	}

	

	private static void delete(BoardDAO dao) {
		
		int no = 12;
		
		if(dao.delete(no)) {
			p("DELETE 성공");
		}else {
			p("DELETE 실패");
		}
	}

	private static void update(BoardDAO dao) {
		BoardDTO dto = new BoardDTO();
		
		dto.setName("updateTs");
		dto.setSubject("upSubject");
		dto.setContent("uptest");
		dto.setFilename("uptest");
		dto.setFilesize(2222);
		dto.setNum(1);
		
		if(dao.update(dto)) {
			p("UPDATE 성공");
		} else {
			p("UPDATE 실패");
		}
	}

	private static void create(BoardDAO dao) {
		BoardDTO dto = new BoardDTO();
		
		dto.setName("MyBaTest");
		dto.setSubject("subject");
		dto.setContent("content");
		dto.setPasswd("passwd");
		dto.setIp("127.0.0.1");
		/*dto.setFilename("mybatisTest");
		dto.setFilesize(1);*/
		
		if(dao.create(dto)) {
			p("CREATE 성공");
		}else {
			p("CREATE 실패");
		}
	}

	private static void p(String string) {
		System.out.println(string);
	}
	
	private static void p(BoardDTO dto) {
		
		System.out.println("번호 : " + dto.getNum());
		System.out.println("이름 : " + dto.getName());
		System.out.println("제목 : " + dto.getSubject());
		System.out.println("내용 : " + dto.getContent());
		System.out.println("조회수 : " + dto.getCount());
		System.out.println("날짜 : " + dto.getRegdate());
		System.out.println("뭐지1 : " + dto.getIp());
		System.out.println("뭐지2 : " + dto.getFilename());
		System.out.println("뭐지3 : " + dto.getFilesize());
		
	}
	
	
}

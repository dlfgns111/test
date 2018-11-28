package spring.model.bbs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class ReplyTest {

	public static void main(String[] args) {
		
		Resource resource = new ClassPathResource("daoTest.xml");
		
		BeanFactory factory = new XmlBeanFactory(resource);
		
		ReplyDAO dao = (ReplyDAO)factory.getBean("reply");
		
		//list(dao);
		//read(dao);
		//total(dao);
		create(dao);
		//update(dao);
		//delete(dao);
		//bdelete(dao);
	}


	private static void bdelete(ReplyDAO dao) {
		int bbsno = 3;
		if(dao.bdelete(bbsno)) {
			p("성공");
		}else {
			p("실패");
		}		
		
	}

	private static void delete(ReplyDAO dao) {
		int rnum = 1;
		if(dao.delete(rnum)){
			p("성공");
		}else {
			p("실패");
		}
		
	}


	

	private static void update(ReplyDAO dao) {
		ReplyDTO dto = dao.read(1);
		dto.setContent("내용변경");
		if(dao.update(dto)) {
			p("성공");
		}else {
			p("실패");
		}
		
	}

	private static void create(ReplyDAO dao) {

		ReplyDTO dto = new ReplyDTO();
		dto.setContent("냇글내용 입니다.");
		dto.setId("user4");
		dto.setBbsno(3);
		if(dao.create(dto )) {
			p("성공");
		}else {
			p("실패");
		}
		
	}

	private static void total(ReplyDAO dao) {
        int bbsno = 3;
		
		int total = dao.total(bbsno);
		
		p("전체레코드수:"+total);
		
	}

	private static void read(ReplyDAO dao) {
		ReplyDTO dto = dao.read(1);
		p(dto);
	}

	private static void list(ReplyDAO dao) {
		Map map = new HashMap();
		map.put("bbsno", 3);
		map.put("sno", 1);
		map.put("eno", 10);
		List<ReplyDTO> list = dao.list(map );
		
		Iterator<ReplyDTO> iter = list.iterator();
		
		while(iter.hasNext()) {
			ReplyDTO dto = iter.next();
			p(dto);
			p("------------------");
		}
		
	}

	private static void p(String string) {
		System.out.println(string);
		
	}

	private static void p(ReplyDTO dto) {
		p("번호:"+dto.getRnum());
		p("내용:"+dto.getContent());
		p("날짜:"+dto.getRegdate());
		p("아이디:"+dto.getId());
		p("부모글번호:"+dto.getBbsno());
		
	}

}

package spring.model.gallery;

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

public class GalleryTest {
	public static void main(String[] args) {

		Resource resource = new ClassPathResource("daoTest.xml");

		BeanFactory factory = new XmlBeanFactory(resource);

		GalleryDAO dao = (GalleryDAO) factory.getBean("gallery");

		// create(dao);
		// read(dao);
				
		// update(dao);
		
		// delete(dao);
		
		// total(dao);
		// list(dao);
		// updateViewcnt(dao);
		// replyCreate(dao);
		
		passwdCheck(dao);

	}

	private static void passwdCheck(GalleryDAO dao) {
		Map map = new HashMap();
		
		map.put("gno", "gno" );
		map.put("gno", "pw" );
		
		if(dao.passwdCheck(map)) {
			p("패스워드가 중복되었습니다.");
		}else {
			p("패스워드가 중복되지 않았습니다.");
		}
		
		
	}

	private static void replyCreate(GalleryDAO dao) {
		GalleryDTO dto = new GalleryDTO();

		int no = 1;

		if (dao.replyCreate(dto)) {
			p("답변등록 성공");
		} else {
			p("답변등록 실패");
		}

	}

	private static void total(GalleryDAO dao) {
		Map map = new HashMap();

		map.put("col", "title");
		map.put("word", "");
		
		int total = dao.total(map);

		p("전체 레코드수:" + total);

	}

	private static void updateViewcnt(GalleryDAO dao) {
		dao.upViewCnt(1);
	}

	private static void list(GalleryDAO dao) {

		Map map = new HashMap();

		map.put("col", "title");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);

		List<GalleryDTO> list = dao.list(map);

		Iterator<GalleryDTO> iter = list.iterator();

		while (iter.hasNext()) {
			GalleryDTO dto = iter.next();
			p(dto);
			p("----------------------");
		}

		for (int i = 0; i < list.size(); i++) {
			GalleryDTO dto = list.get(i);

			p(dto);
		}
	}

	private static void read(GalleryDAO dao) {
		int no = 1;
		GalleryDTO dto = dao.read(1);
		p(dto);
	}

	private static void delete(GalleryDAO dao) {

		int gno = 1;

		if (dao.delete(gno)) {
			p("DELETE 성공");
		} else {
			p("DELETE 실패");
		}
	}

	private static void updatePasswd(GalleryDAO dao) {
		Map map = new HashMap();
		
		map.put("pw", "1122");
		map.put("gno", "1");
		
		if(dao.updatePasswd(map)) {
			p("updatePasswd 성공");
		}else {
			p("updatePasswd 실패");
		}
	}
	private static void updateInfo(GalleryDAO dao) {
		Map map = new HashMap();
		
		map.put("fname", "infoTest,jpg");
		map.put("title", "infoTest");
		map.put("writer", "infoTest");
		map.put("content", "infoTest");
		map.put("gno", "1");

		if(dao.updateInfo(map)) {
			p("UPDATE INFO 성공");
		}else {
			p("UPDATE INFO 실패");
		}
	}
	private static void update(GalleryDAO dao) {
		GalleryDTO dto = new GalleryDTO();

		int no = 1;

		dto.setTitle("imgTest");
		dto.setContent("imgTest");
		dto.setGno(no);
		if (dao.updateInfo(dto)) {
			p("UPDATE 성공");
		} else {
			p("UPDATE 실패");
		}
	}

	private static void create(GalleryDAO dao) {
		GalleryDTO dto = new GalleryDTO();

		dto.setTitle("imgTest");

		dto.setWriter("imgTest");
		dto.setContent("imgTest");

		dto.setFname("imgTest.jpg");
		dto.setPw("1234");

		if (dao.create(dto)) {
			p("CREATE 성공");
		} else {
			p("CREATE 실패");
		}
	}

	private static void p(String string) {
		System.out.println(string);
	}

	private static void p(GalleryDTO dto) {

		System.out.println("번호 : " + dto.getGno());
		System.out.println("제목 : " + dto.getTitle());
		System.out.println("날짜 : " + dto.getUdate());
		System.out.println("작성자 : " + dto.getWriter());
		System.out.println("내용: " + dto.getContent());
		System.out.println("조회수 : " + dto.getViewcnt());
		System.out.println("사진이름 : " + dto.getFname());
		System.out.println("게시글패스워드 : " + dto.getPw());

	}

}

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

public class BreplyTest {
	public static void main(String[] args) {

		Resource resource = new ClassPathResource("daoTest.xml");

		BeanFactory factory = new XmlBeanFactory(resource);

		BreplyDAO dao = (BreplyDAO) factory.getBean("breply");

		// read(dao);
		// list(dao);
		 total(dao);

	}

	private static void total(BreplyDAO dao) {
		
		Map map = new HashMap();
		map.put("num", 19);
		
		int total = dao.total(map);

		p("전체 레코드수:" + total);

	}

	private static void list(BreplyDAO dao) {

		Map map = new HashMap();

		map.put("num", 19);
		map.put("sno", 1);
		map.put("eno", 5);

		List<BreplyDTO> list = dao.list(map);

		Iterator<BreplyDTO> iter = list.iterator();

		while (iter.hasNext()) {
			BreplyDTO dto = iter.next();
			p(dto);
			p("----------------------");
		}

		for (int i = 0; i < list.size(); i++) {
			BreplyDTO dto = list.get(i);

			p(dto);
		}
	}

	private static void read(BreplyDAO dao) {
		int rnum = 19;
		BreplyDTO dto = dao.read(rnum);
		p(dto);
	}

	
	private static void p(String string) {
		System.out.println(string);
	}

	private static void p(BreplyDTO dto) {

		System.out.println("번호 : " + dto.getRnum());
		System.out.println("내용 : " + dto.getContent());
		System.out.println("날짜 : " + dto.getRegdate());
		System.out.println("아이디 : " + dto.getId());
		System.out.println("부모글번호:" + dto.getNum());

	}

}

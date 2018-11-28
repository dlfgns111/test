package test.admin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import admin.AdminDAO;
import admin.AdminDTO;

public class AdminTest {
	public static void main(String[] args) {
		AdminDAO dao = new AdminDAO();
		
		//create(dao);
		//read(dao);
		//list(dao);
		//total(dao);
		//delete(dao);
	}

	private static void delete(AdminDAO dao) {
		String a_ID = "1234";
		
		if(dao.delete(a_ID)) {
		p("delete성공");
		}else {
		p("delete실패");	
		}
			
				
	}

	private static void total(AdminDAO dao) {
		Map map = new HashMap();
		
		int total = dao.total(map);
		
		p("레코드 개수"+total);
		
	}

	private static void list(AdminDAO dao) {
		Map map = new HashMap();
		map.put("sno", 1);
		map.put("eno", 5);
		
		List<AdminDTO> list = dao.list(map);
		Iterator<AdminDTO> iter = list.iterator();
		
		while(iter.hasNext()) {
			AdminDTO dto = iter.next();
			p("----------------");
			p(dto);
		}
	}

	private static void read(AdminDAO dao) {
		String a_ID = "admin";
		
		AdminDTO dto = dao.read(a_ID);
		p(dto);
		
	}


	private static void create(AdminDAO dao) {
		AdminDTO dto = new AdminDTO();
		
		dto.setA_ID("1234");
		dto.setPasswd("1234");
		
		if(dao.create(dto)) {
			p("create성공");
		}else {
			p("create실패");
		}
		
	}
	
	

	private static void p(String string) {
		System.out.println(string);
	}
	private static void p(AdminDTO dto) {
		System.out.println("a_ID : "+dto.getA_ID());		
		System.out.println("Passwd : "+dto.getPasswd());		
	}
		
}

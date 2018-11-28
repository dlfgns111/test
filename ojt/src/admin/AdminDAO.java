package admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sqlmap.MyAppSqlConfig;

public class AdminDAO {
	private static SqlSessionFactory sqlMapper;
	private static SqlSession session;
	static {
		sqlMapper = MyAppSqlConfig.getSqlMapInstance();
		session = sqlMapper.openSession(true);	
	}
	
	public boolean create(AdminDTO dto) {
		boolean flag = false;
			int cnt = session.insert("admin.create",dto);
			if(cnt>0) flag = true;
		return flag;		
	}

	public AdminDTO read(String a_ID) {
		
		return session.selectOne("admin.read",a_ID);
	}
	
	public List<AdminDTO> list(Map map) {
		
		return session.selectList("admin.list",map);
	}

	public int total(Map map) {
		
		return session.selectOne("admin.total",map);
	}
	
	public boolean delete(String a_ID) {
		boolean flag = false;
			int cnt = session.delete("admin.delete",a_ID);
			if(cnt>0) flag=true;
			
		return flag;
	}
}

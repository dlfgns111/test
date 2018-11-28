package notice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sqlmap.MyAppSqlConfig;

public class NoticeDAO {
	private static SqlSessionFactory sqlMapper;
	private static SqlSession session;
	static {
		sqlMapper = MyAppSqlConfig.getSqlMapInstance();
		session = sqlMapper.openSession(true);	
	}
	public boolean create(NoticeDTO dto) {
		boolean flag = false;
			int cnt = session.insert("notice.create",dto);
			if(cnt>0) flag = true;
		return flag;		
	}

	public NoticeDTO read(int d_num) {
		
		return session.selectOne("notice.read",d_num);
	}
	
	public List<NoticeDTO> list(Map map) {
		
		return session.selectList("notice.list",map);
	}

	public int total(Map map) {
		
		return session.selectOne("notice.total",map);
	}
	
	public boolean delete(int d_num) {
		boolean flag = false;
			int cnt = session.delete("notice.delete",d_num);
			if(cnt>0) flag=true;
			
		return flag;
	}
	
	public boolean update(NoticeDTO dto) {
		boolean flag = false;
			int cnt = session.update("notice.update",dto);
			if(cnt>0) flag = true;
		return flag;		
	}
}

package declaration;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import declaration.DeclarationDTO;
import sqlmap.MyAppSqlConfig;

public class DeclarationDAO {
	
	private static SqlSessionFactory sqlMapper;
	private static SqlSession session;
	static {
		sqlMapper = MyAppSqlConfig.getSqlMapInstance();
		session = sqlMapper.openSession(true);	
	}
	public boolean create(DeclarationDTO dto) {
		boolean flag = false;
			int cnt = session.insert("declaration.create",dto);
			if(cnt>0) flag = true;
		return flag;		
	}

	public DeclarationDTO read(int d_num) {
		
		return session.selectOne("declaration.read",d_num);
	}
	
	public List<DeclarationDTO> list(Map map) {
		
		return session.selectList("declaration.list",map);
	}

	public int total(Map map) {
		
		return session.selectOne("declaration.total",map);
	}
	
	public boolean delete(int d_num) {
		boolean flag = false;
			int cnt = session.delete("declaration.delete",d_num);
			if(cnt>0) flag=true;
			
		return flag;
	}
	
	public boolean update(DeclarationDTO dto) {
		boolean flag = false;
			int cnt = session.update("declaration.update",dto);
			if(cnt>0) flag = true;
		return flag;		
	}

}

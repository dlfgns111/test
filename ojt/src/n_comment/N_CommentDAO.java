package n_comment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import n_comment.N_CommentDTO;
import sqlmap.MyAppSqlConfig;

public class N_CommentDAO {

	private static SqlSessionFactory sqlMapper;
	private static SqlSession session;
	static {
		sqlMapper = MyAppSqlConfig.getSqlMapInstance();
		session = sqlMapper.openSession(true);	
	}
	public boolean create(N_CommentDTO dto) {
		boolean flag = false;
		
		int cnt = session.insert("n_comment.create", dto);
		
		if(cnt>0)flag=true;
		
		return flag;
	}
	
	public int total(int n_num) {
		
		return session.selectOne("n_comment.total", n_num);
	}
	
	public List<N_CommentDTO> list(Map map) {
		
		return session.selectList("n_comment.list", map);
	}
	
	public N_CommentDTO read(int rnum) {
		
		return session.selectOne("n_comment.read", rnum);
	}
	
	public boolean update(N_CommentDTO dto) {
		boolean flag = false;
		
		int cnt = session.update("n_comment.update", dto);
		
		if(cnt>0)flag=true;
				
		return flag;
	}
	
	public boolean delete(int nc_num) {
		boolean flag = false;
		
		int cnt = session.delete("n_comment.delete", nc_num);
		
		if(cnt>0)flag=true;
		
		return flag;
	}
	
	public boolean bdelete(int n_num) {
		boolean flag = false;
		
		int cnt = session.delete("n_comment.bdelete", n_num);
		
		if(cnt>0)flag=true;
		
		return flag;
	}
}


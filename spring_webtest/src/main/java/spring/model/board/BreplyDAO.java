package spring.model.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BreplyDAO {
	
	
	
	@Autowired
	private SqlSessionTemplate mybatis;

	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
		
	public int total(Map map) {
		
		return mybatis.selectOne("breply.total", map);
	}
	
	public List<BreplyDTO> list(Map map) {
		
		return mybatis.selectList("breply.list", map);
	}
	
	public BreplyDTO read(int num) {
		
		return mybatis.selectOne("breply.read", num);
	} 
	
}

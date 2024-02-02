package dfS.ysm.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dfS.ysm.DTO.CharacterDTO;



public class CSearchResultConn {
	static CSearchResultConn select = new CSearchResultConn();
	public static CSearchResultConn instance() {
		return select;
	}
	
	SqlSessionFactory sqlSessionFactory = SqlConnect.getSqlSession();
	
	public List<CharacterDTO> dbSelect(String characterName) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		CharacterDTO characterDTO = new CharacterDTO();
		characterDTO.setCharacterName(characterName);
		List<CharacterDTO> list = sqlSession.selectList("cSearchID", characterDTO);
		sqlSession.close();
		return list;
				
	}
}
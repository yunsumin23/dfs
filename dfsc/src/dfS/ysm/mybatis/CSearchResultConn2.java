package dfS.ysm.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dfS.ysm.DTO.CharacterDTO;



public class CSearchResultConn2 {
	static CSearchResultConn2 select = new CSearchResultConn2();
	public static CSearchResultConn2 instance() {
		return select;
	}
	
	SqlSessionFactory sqlSessionFactory = SqlConnect.getSqlSession();
	
	public List<CharacterDTO> dbSelect(String adventureName) { //name  >  adventureName
		SqlSession sqlSession = sqlSessionFactory.openSession();
		CharacterDTO characterDTO = new CharacterDTO();
		characterDTO.setAdventureName(adventureName);
		System.out.println("conn2="+adventureName);
		List<CharacterDTO> list = sqlSession.selectList("cSearchAdvenID", characterDTO);
		sqlSession.close();
		System.out.println("모험단조회conn2");
		return list;
	}
}
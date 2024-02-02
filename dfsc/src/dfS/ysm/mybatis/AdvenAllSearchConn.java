package dfS.ysm.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dfS.ysm.DTO.CharacterDTO;


public class AdvenAllSearchConn {
	static AdvenAllSearchConn insert = new AdvenAllSearchConn();
	public static AdvenAllSearchConn instance() {
		return insert;
	}
	
	SqlSessionFactory sqlSessionFactory = SqlConnect.getSqlSession();
	
	public void cSearchAndInsertConn(CharacterDTO characterDTO) {
		SqlSession session = sqlSessionFactory.openSession();
		
		int i = session.insert("cInsertID", characterDTO);
		
		session.commit();
		session.close();
	}
}
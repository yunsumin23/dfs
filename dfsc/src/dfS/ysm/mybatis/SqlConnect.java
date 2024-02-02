package dfS.ysm.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlConnect { // 마이바티스로 sql연결하는 파일입니다.
	
	private static SqlSessionFactory sqlSessionFactory; 
	
	public static SqlSessionFactory getSqlSession() {
		return sqlSessionFactory;
	}
	
	static {
		try {
			String resource = "dfS/ysm/mybatis/dbConnect.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			SqlSessionFactoryBuilder SqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
			sqlSessionFactory = SqlSessionFactoryBuilder.build(reader);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

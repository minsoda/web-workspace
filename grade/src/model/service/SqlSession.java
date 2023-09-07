package model.service;

import java.io.InputStream;

import javax.annotation.Resources;

public class SqlSession {

	
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		String resource="/mybatis-config.xml";
		InputStream stream = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(stream);
		session = factory.openSession(false);
		return session;
		
	}
}

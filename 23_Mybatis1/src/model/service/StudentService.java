package model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.Template;
import model.dao.StudentDAO;
import model.vo.StudentVO;

public class StudentService {

	public List<StudentVO> showStudent(String word){
		
		SqlSession sqlSession = Template.getSqlSession();
		List<StudentVO> student = StudentDAO.getInstance().showStudent(sqlSession, word);
		//commit 은 나머지만!
		sqlSession.close();
//		System.out.println("service : " + student);
		return student;
		
		
	}
}

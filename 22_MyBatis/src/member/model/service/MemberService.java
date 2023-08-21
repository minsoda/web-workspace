package member.model.service;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.SqlSessionTemplate;
import member.model.dao.MemberDAO;
import member.model.vo.MemberVO;

// DAO - Service - Controller
public class MemberService {

	public int registerMember(MemberVO vo) {
		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
		int result = MemberDAO.getInstance().registerMember(sqlSession, vo);
		
		// commit은 insert,update, delete에만!
		if(result > 0) sqlSession.commit();
		sqlSession.close();
		
		return result;
	}
	
	public List<MemberVO> showAllMember(){
		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
		List<MemberVO> list = MemberDAO.getInstance().showAllMember(sqlSession);
		sqlSession.close();
		
		return list;
	}
	
	// 체크된 정보를 전달해야해서 수정됨!
	public List<MemberVO> findByIdMember(String[] idList) {
		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
		List<String> list = Arrays.asList(idList);
		List<MemberVO> member = MemberDAO.getInstance().findByIdMember(sqlSession, list);
		sqlSession.close();
	return member;
	}
	
	public MemberVO login(MemberVO vo) {
		
		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
		MemberVO member = MemberDAO.getInstance().login(sqlSession, vo);
		System.out.println("service : " + vo);
		sqlSession.close();
	return member;
	}
	
	public int updateMember(MemberVO vo) {
		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
		int result = MemberDAO.getInstance().updateMember(sqlSession, vo);
		if(result > 0) sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	
}

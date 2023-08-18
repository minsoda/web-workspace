package servlet.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import servlet.model.dao.MemberRepository;
import servlet.model.vo.MemberDTO;

// 시험관련
public class MemberService {

	private MemberRepository repo = new MemberRepository();
			
	public ArrayList<MemberDTO> showAllMember() throws SQLException{
		return repo.showAllMember();
}
}

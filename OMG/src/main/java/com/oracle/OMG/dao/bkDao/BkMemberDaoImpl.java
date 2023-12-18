package com.oracle.OMG.dao.bkDao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.OMG.dto.Item;
import com.oracle.OMG.dto.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BkMemberDaoImpl implements BkMemberDao {
	
	// Mybatis DB 연동
	private final SqlSession session;

	@Override
	public Member login(Member member) {
		
		System.out.println("BkMemberDaoImpl login Start...");
		Member loginUser = null;
		
		try {
			loginUser = session.selectOne("bkLogin", member);
		} catch (Exception e) {
			System.out.println("BkMemberDaoImpl login Exception -> " + e.getMessage());
		}
		
		return loginUser;
	}

	
	
	@Override
	public Member checkNameAndTel(Member member) {
		
		System.out.println("BkMemberDaoImpl login Start...");
		Member checkResult = null;
		
		try {
			checkResult = session.selectOne("bkcheckNameAndTel", member);
		} catch (Exception e) {
			System.out.println("BkMemberDaoImpl checkNameAndTel Exception -> " + e.getMessage());
		}
		
		return checkResult;
	}


}

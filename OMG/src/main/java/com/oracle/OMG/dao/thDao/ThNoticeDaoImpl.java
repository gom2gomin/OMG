package com.oracle.OMG.dao.thDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.OMG.dto.Board;
import com.oracle.OMG.dto.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ThNoticeDaoImpl implements ThNoticeDao {
	
	private final SqlSession session;
	
	@Override
	public int getTotNotice() {
		int totNotice = 0;
		try {			
			totNotice = session.selectOne("getTotNotice");
			log.info("totNotice --> " + totNotice);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return totNotice;
	}
	
	
	@Override
	public List<Board> getNoticeList(Board board) {
		List<Board> noticeList = null;
		try {
			noticeList = session.selectList("getNoticeList", board);
			log.info("noticeList.size() --> " + noticeList.size());
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return noticeList;
	}



	@Override
	public List<Board> getListWithPaging(Criteria cri) {
		List<Board> noticeList = null;
		try {
			noticeList = session.selectList("getListWithPaging", cri);
			log.info("noticeList.size() --> " + noticeList.size());
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return noticeList;
	}
	
	
	@Override
	public int insertNotice(Board board) {
		int insertResult = 0;
		log.info("board : {}", board);
		try {
			insertResult = session.insert("insertNotice", board);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return insertResult;
	}

	@Override
	public int insertSelectKeyNotice(Board board) {
		int insertResult = 0;
		log.info("board: {}", board);
		try {
			insertResult = session.insert("insertSelectKeyNotice", board);
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return insertResult;
	}

	@Override
	public Board readNotice(int brd_id) {
		Board notice = null;
		try {
			notice= session.selectOne("readNotice", brd_id);
			log.info("notice: {}", notice);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return notice;
	}

	@Override
	public int deleteNotice(int brd_id) {
		int deleteResult = 0;
		try {
			deleteResult= session.delete("deleteNotice", brd_id);
			log.info("deleteResult --> " + deleteResult);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return deleteResult;
	}

	@Override
	public int updateNotice(Board board) {
		int updateResult = 0;
		try {
			updateResult = session.update("updateNotice", board);
			log.info("updateResult --> " + updateResult );
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return updateResult;
	}



}

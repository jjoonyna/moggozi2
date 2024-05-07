package com.choongang.moggozi2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.choongang.moggozi2.entity.CommonBoard;

public interface BoardRepository extends JpaRepository<CommonBoard, Integer>{

//	public Board save(Board board);				// 글작성, 글수정 
//	public long count();						// 글 갯수 
//	public void delete(Board board);			// 글삭제 
//	public BoardList findByNo(int no);				// 상세 정보
	
	// JPQL
	@Query(value="select * from commonboard order by boardNo desc limit :start, 10", nativeQuery = true)
	public List<CommonBoard> findAll(@Param("start")  int start);		// 전체 목록 검색	
	
	@Query(value="select * from commonboard where boardNo = :boardNo", nativeQuery = true)
	public CommonBoard findByBoard_no(@Param("boardNo") Integer boardNo);

	
}

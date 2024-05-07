package com.choongang.moggozi2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.choongang.moggozi2.entity.CommonBoard;
import com.choongang.moggozi2.repository.BoardRepository;

@Service
@Transactional
public class BoardService {

	private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
	}

	public CommonBoard saveCommonBoard(CommonBoard commonBoard) {
		return boardRepository.save(commonBoard);
	}

	public long getCount() {
		return boardRepository.count();
	}

	public List<CommonBoard> getList(int start) {
		return boardRepository.findAll(start);
	}

	public CommonBoard getContent(Integer boardNo) {
		return boardRepository.getById(boardNo);
	}
	
	public void boarddelete(Integer boardNo) {
		boardRepository.deleteById(boardNo);
	}

	public CommonBoard update(Integer boardNo) {
		return boardRepository.getById(boardNo);
	}

    public void increaseViews(Integer boardNo) {
        CommonBoard commonBoard = boardRepository.findById(boardNo).orElse(null);
        if (commonBoard != null) {
            int boardCnt = commonBoard.getBoardCnt();
            commonBoard.setBoardCnt(boardCnt + 1);
            boardRepository.save(commonBoard);
        }
    }
}
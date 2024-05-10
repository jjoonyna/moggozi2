package com.choongang.moggozi2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.choongang.moggozi2.entity.Mokkoji;
import com.choongang.moggozi2.repository.MokkojiRepository;

public interface MokkojiService {
    
	 void saveMokkoji(Mokkoji mokkoji);

	    Page<Mokkoji> findAllMokkoji(Pageable pageable);

	    Page<Mokkoji> searchMokkoji(String keyword, String category, Pageable pageable);

	    List<Mokkoji> searchMokkoji(String keyword, String category);

	    int countSearchResults(String keyword, String category);

		int countTotalMokkoji();

		List<Mokkoji> findAllMokkoji(String usernick);
		
		@Transactional
	    public void deleteMokkojisByMokkojiNos(List<Integer> mokkojiNos);
    
    // 다른 필요한 메서드들을 추가할 수 있습니다.
    

}
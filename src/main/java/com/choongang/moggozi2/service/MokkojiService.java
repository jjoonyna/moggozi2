package com.choongang.moggozi2.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.choongang.moggozi2.entity.Mokkoji;
import com.choongang.moggozi2.repository.MokkojiRepository;


@Service
@Transactional
public class MokkojiService {
    
    private final MokkojiRepository mokkojiRepository;

    @Autowired
    public MokkojiService(MokkojiRepository mokkojiRepository) {
        this.mokkojiRepository = mokkojiRepository;
    }
    
    public void saveMokkoji(Mokkoji mokkoji) {
        mokkojiRepository.save(mokkoji);//insert,update
    }
    
    public Page<Mokkoji> findAllMokkoji(Pageable pageable) {
        return mokkojiRepository.findAll(pageable);//모든 select를 가져오는거
    }
    
    // 다른 필요한 메서드들을 추가할 수 있습니다.
}
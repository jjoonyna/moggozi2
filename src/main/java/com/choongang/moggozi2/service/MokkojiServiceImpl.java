package com.choongang.moggozi2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.choongang.moggozi2.entity.Mokkoji;
import com.choongang.moggozi2.repository.MokkojiRepository;

@Service
@Transactional
public class MokkojiServiceImpl implements MokkojiService {

    private final MokkojiRepository mokkojiRepository;

    @Autowired
    public MokkojiServiceImpl(MokkojiRepository mokkojiRepository) {
        this.mokkojiRepository = mokkojiRepository;
    }

    @Override
    public void saveMokkoji(Mokkoji mokkoji) {
        mokkojiRepository.save(mokkoji);
    }

    @Override
    public Page<Mokkoji> findAllMokkoji(Pageable pageable) {
        return mokkojiRepository.findAll(pageable);
    }

    @Override
    public Page<Mokkoji> searchMokkoji(String keyword, String category, Pageable pageable) {
        if (category != null && !category.isEmpty()) {
            return mokkojiRepository.findByMokkojiTitleContainingAndMokkojiCategory(keyword, category, pageable);
        } else {
            return mokkojiRepository.findByMokkojiTitleContaining(keyword, pageable);
        }
    }

    @Override
    public List<Mokkoji> searchMokkoji(String keyword, String category) {
        return mokkojiRepository.findByMokkojiTitleContainingAndMokkojiCategory(keyword, category);
    }
    
    @Override
    public int countTotalMokkoji() {
        return (int) mokkojiRepository.count();
    }
    

    @Override
    public int countSearchResults(String keyword, String category) {
        if (category != null && !category.isEmpty()) {
            return mokkojiRepository.countByMokkojiTitleContainingAndMokkojiCategory(keyword, category);
        } else {
            return mokkojiRepository.countByMokkojiTitleContaining(keyword);
        }
    }
}
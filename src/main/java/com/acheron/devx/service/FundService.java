package com.acheron.devx.service;

import com.acheron.devx.entity.Fund;
import com.acheron.devx.repository.FundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FundService {
    private final FundRepository fundRepository;

    public Optional<Fund> findById(Long id){
        System.out.println(id);
        return fundRepository.findById(id);
    }
}

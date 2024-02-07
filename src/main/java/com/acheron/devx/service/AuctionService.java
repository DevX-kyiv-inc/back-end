package com.acheron.devx.service;

import com.acheron.devx.entity.Auction;
import com.acheron.devx.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuctionService {
    private final AuctionRepository auctionRepository;

    public List<Auction> findAll(String key, Integer size){
        return auctionRepository.findAll(key, Pageable.ofSize(size));
    }
    public Optional<Auction> findById(Long id){
        return auctionRepository.findById(id);
    }

    public Auction save(Auction auction){
        return auctionRepository.save(auction);
    }

    
}

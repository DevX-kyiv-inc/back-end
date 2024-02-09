package com.acheron.devx.service;

import com.acheron.devx.dto.BidSaveDto;
import com.acheron.devx.entity.Bid;
import com.acheron.devx.repository.BidRepository;
import com.amazonaws.services.mq.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BidService {
    private final BidRepository bidRepository;
    private final AuctionService auctionService;

    public Bid saveBid(BidSaveDto saveDto, Long id){
        return bidRepository.save(new Bid(null,saveDto.getName(), saveDto.getValue(), auctionService.findById(id).orElseThrow(()-> new NotFoundException("user not found"))));
    }
    public List<Bid> findAll(Long id){
        return bidRepository.findAll(id,Sort.by("amount"));
    }

}

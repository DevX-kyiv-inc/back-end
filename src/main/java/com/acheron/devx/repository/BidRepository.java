package com.acheron.devx.repository;

import com.acheron.devx.entity.Auction;
import com.acheron.devx.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {
    
}

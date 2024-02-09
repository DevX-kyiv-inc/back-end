package com.acheron.devx.repository;

import com.acheron.devx.entity.Auction;
import com.acheron.devx.entity.Bid;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {
    @Query("SELECT b FROM Bid b where b.auction.id =?1")
    List<Bid> findAll(Long id, Sort sort);
}

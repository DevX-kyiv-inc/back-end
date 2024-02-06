package com.acheron.devx.repository;

import com.acheron.devx.entity.Bid;
import com.acheron.devx.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    
}

package com.acheron.devx.service;

import com.acheron.devx.dto.MessageSaveDto;
import com.acheron.devx.entity.Message;
import com.acheron.devx.repository.MessageRepository;
import com.amazonaws.services.mq.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final AuctionService auctionService;
    public Message save(MessageSaveDto saveDto, Long id) {
        return messageRepository.save(new Message(null,saveDto.getMessage(), saveDto.getSender(), auctionService.findById(id).orElseThrow(()-> new NotFoundException("user not found"))));
    }
}

package com.acheron.devx.handler;

import com.acheron.devx.dto.BidSaveDto;
import com.acheron.devx.dto.MessageSaveDto;
import com.acheron.devx.entity.Bid;
import com.acheron.devx.entity.Message;
import com.acheron.devx.service.BidService;
import com.acheron.devx.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainHandler {
    private final MessageService messageService;
    private final BidService bidService;

    @MessageMapping("/app/bid/{id}")
    @SendTo("/topic/bid/{id}")
    public Bid bidHandler(@Payload BidSaveDto dto, @DestinationVariable Long id){
        return bidService.saveBid(dto,id);
    }

    @MessageMapping("/app/messages/{id}")
    @SendTo("/topic/messages/{id}")
    public Message messageHandler(@Payload MessageSaveDto str, @DestinationVariable Long id){
        return messageService.save(str,id);
    }
}

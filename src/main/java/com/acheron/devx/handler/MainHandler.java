package com.acheron.devx.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainHandler {
//    private final ChatRepository chatRepository;
//
//    @MessageMapping("/app/chat/{id}")
//    @SendTo("/topic/chat/{id}")
//    public String chatHandler(@Payload String str, @DestinationVariable Long id){
//        return chatRepository.findById(id);
//    }

    @MessageMapping("/app/slot/{id}")
    @SendTo("/topic/slot/{id}")
    public String slotHandler(@Payload String str, @DestinationVariable Long id){
        return str;
    }
}

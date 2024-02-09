package com.acheron.devx.service;

import com.acheron.devx.dto.AuctionSaveDto;
import com.acheron.devx.dto.BidSendDto;
import com.acheron.devx.dto.MessageSaveDto;
import com.acheron.devx.entity.Auction;
import com.acheron.devx.entity.Message;
import com.acheron.devx.repository.AuctionRepository;
import com.amazonaws.services.mq.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuctionService {
    private final AuctionRepository auctionRepository;
    private final FundService fundService;
    private final ImageService imageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public List<Auction> findAll(String key, Integer size,Integer status,String sort){
        Pageable pageable = PageRequest.of(0, size==null?40:size, sort.equals("old") ? Sort.by(Sort.Direction.ASC, "startTime","status") :Sort.by(Sort.Direction.DESC, "startTime","status"));
        return auctionRepository.findAll(key==null?"":key,status==1?1:0, pageable);
    }
    public List<Auction> findAll(){
        return auctionRepository.findAll();
    }
    public Optional<Auction> findById(Long id){
        return auctionRepository.findById(id);
    }

    public Auction save(AuctionSaveDto auctionDto, MultipartFile file){
        Auction auction = new Auction(
                null,
                auctionDto.getName(),
                auctionDto.getDescription(),
                null,
                auctionDto.getAuthorName(),
                auctionDto.getContact(),
                LocalDateTime.now().plusMinutes((long) auctionDto.getExpireTime()),
                LocalDateTime.now(),
                1,
                fundService.findById(auctionDto.getFundId()).orElseThrow(()-> new NotFoundException("fund not")),
                auctionDto.getFundStake(),
                auctionDto.getStartValue(),
                null,
                null
        );
        auction.setPhoto(imageService.saveImage(file));
        Thread thread = new Thread(() -> delay(auction));
        thread.start();
        return auctionRepository.save(auction);
    }

    @SneakyThrows
    private void delay(Auction auction){
        Thread.sleep(Duration.between(auction.getStartTime(), auction.getExpireTime()));
        auction.setStatus(0);
        Auction save = auctionRepository.save(auction);
        System.out.println("norm");
        simpMessagingTemplate.convertAndSend("/topic/bid/"+save.getId(),new BidSendDto("",1D,0));
    }
}

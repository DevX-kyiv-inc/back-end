package com.acheron.devx.controller;

import com.acheron.devx.dto.AuctionSaveDto;
import com.acheron.devx.entity.Auction;
import com.acheron.devx.entity.Fund;
import com.acheron.devx.entity.Message;
import com.acheron.devx.service.AuctionService;
import com.acheron.devx.service.FundService;
import com.acheron.devx.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class MainController {
    private final AuctionService auctionService;
    private final FundService fundService;
    private final MessageService messageService;

    //get
//    @RequestParam String key, @RequestParam Integer size
    @GetMapping("/allAuctions")
    @CrossOrigin("http://localhost:5173/")
    public List<Auction> getAllAuctions() {
        return auctionService.findAll();
    }

    @GetMapping("/funds")
    @CrossOrigin("http://localhost:5173/")
    public List<Fund> getAllFunds() {
        return fundService.findAll();
    }
    @GetMapping("/messages/{id}")
    @CrossOrigin("http://localhost:5173/")
    public List<Message> getAllMessages(@PathVariable Long id) {
        return messageService.findAll(id);
    }

    @GetMapping("/auction/{id}")
    @CrossOrigin("http://localhost:5173/")
    public ResponseEntity<Auction> findAuction(@PathVariable Long id) {
        return auctionService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //post
    @CrossOrigin("http://localhost:5173/")
    @PostMapping("/saveAuction")
    public Auction saveAuction(HttpServletRequest request, @RequestParam MultipartFile file) {
        AuctionSaveDto dto = new AuctionSaveDto(
                request.getHeader("name"),
                request.getHeader("desc"),
                request.getHeader("authorName"),
                request.getHeader("contact"),
                Integer.valueOf(request.getHeader("expirationTime")),
                Long.valueOf(request.getHeader("fund")),
                Double.valueOf(request.getHeader("fundPercentage")),
                Long.valueOf(request.getHeader("price"))
        );

        System.out.println(dto.getExpireTime());
        System.out.println(Long.valueOf(request.getHeader("fund")));
        System.out.println(dto);
        System.out.println(file.getOriginalFilename());
        return auctionService.save(dto, file);
    }
}

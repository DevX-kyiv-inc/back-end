package com.acheron.devx.controller;

import com.acheron.devx.entity.Auction;
import com.acheron.devx.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class MainController {
    private final AuctionService auctionService;

    //get

    @GetMapping("/allAuctions")
    public List<Auction> getAllAuctions(@RequestParam String key, @RequestParam Integer size) {
        return auctionService.findAll(key, size);
    }

    @GetMapping("/auction/{id}")
    public ResponseEntity<Auction> findAuction(@PathVariable Long id){
        return auctionService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //post

    @PostMapping("/saveAuction")
    public Auction saveAuction(@RequestBody Auction auction){
        return auctionService.save(auction);
    }
}

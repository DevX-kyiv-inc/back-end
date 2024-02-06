package com.acheron.devx.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bidder_name")
    private String bidderName;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;
}

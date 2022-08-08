package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity{
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;       //상품 코드

    @Column(nullable = false,length = 50)
    private String itemNm;  //상품명

    @Column(name="price",nullable = false)
    private int price;     //가격

    @Column(nullable = false)
    private int stockNumber;    //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  //상품 상세 설명

    @Enumerated(EnumType.STRING)   //@Enumrated는 enum타입 매핑시 사용함!
    private ItemSellStatus itemSellStatus;  //상품 판매 상태

    // Auditing 이용해서 BaseEntity로 부터 regTime, updateTime, createBy, modifyBy 상속 받고 알아서 지정되므로 기존에 필드는 주석처리
//    private LocalDateTime regTime;
//    private LocalDateTime updateTime;
}

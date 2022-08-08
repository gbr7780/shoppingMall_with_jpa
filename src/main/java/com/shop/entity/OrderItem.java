package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    // 하나의 상품은 여러 주문 상품으로 들어갈 수 있다. 주문 상품 기준으로 다대일 단방향 매핑
    @ManyToOne(fetch = FetchType.LAZY)      // 지연로딩 방식 지정 ( 사용하지 않는 데이터도 가져오기 때문에 즉시 로딩지정)
    @JoinColumn(name = "item_id")
    private Item item;

    // 한 번에 주문에 여러개 상품이 들어갈 수 있다. 주문 상품 엔티티와 주문 엔티티를 다대일 단뱡향 매핑
    @ManyToOne(fetch = FetchType.LAZY)      // 지연로딩 방식 지정 ( 사용하지 않는 데이터도 가져오기 때문에 즉시 로딩지정)
    @JoinColumn(name = "order_id")
    private Order order;

    // 주문가격
    private int orderPrice;

    // 수량
    private int count;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}

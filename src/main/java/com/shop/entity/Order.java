package com.shop.entity;

import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")     // 정려할때 사용하는 order 키워드가 있으므로 테이블 이름은 orders로 지정
@Getter @Setter
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // 한명의 회원은 여러번 주문할 수 있다. 주문 엔티티 기준 다대일 단방향 매핑
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 주문일
    private LocalDateTime orderDate;

    // 주문상태
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    // 주문 상품과 일대다 매핑 // 외래키가 order_item 테이블에 있으므로 연관 관계 주인은 OrderItem 엔티티 이다.
    // order테이블에 연관 관계 주인이 아니므로 mappedBy 설정! mappedBy에는 연관관계 주인 테이블의 필드를 적어주면 됨! ex) Order order 필드이므로 "order" 맞다!
    // 부모 엔티티의 영속성 상태 변화를 자식 엔티티에 모두 전이하는 CascadeType.ALL옵션 설정
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();    // 하나의 주문에는 여러개의 주문 상품을 갖으므로 List자료형 사용한다!

    // Auditing 이용해서 BaseEntity로 부터 regTime, updateTime, createBy, modifyBy 상속 받고 알아서 지정되므로 기존에 필드는 주석처리
//    private LocalDateTime regTime;
//    private LocalDateTime updateTime;
}

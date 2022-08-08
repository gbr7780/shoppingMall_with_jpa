package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter @Setter
@ToString
public class Cart extends BaseEntity {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)      // 일대일 관계 -> 회원 엔티티와 장바구니
    @JoinColumn(name = "member_id")     // 매핑할 외래키 지정 -> name 속성안에 매핑할 외래키 이름 지정함 // 장바구니 엔티티에만 joinColumn이 있으므로 단방향 일대일 관계
    private Member member;




}

package com.kiwi.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiwi.shop.entity.Cart;
import com.kiwi.shop.entity.Member;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberId(Long memberId);

}

package com.kiwi.shop.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kiwi.shop.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o from Order o "+
        "where o.member.email = :email "+
        "order by o.orderDate desc"
    )
    List<Order> findOrders(@Param("email") String email, Pageable pageable);    // 로그인 한 사용자의 주문 데이터를 페이징 조건에 맞춰서 조회

    @Query("select count(o) from Order o "+
            "where o.member.email = :email"
    )
    Long countOrder(@Param("email") String email);      // 로그인 한 회원의 주문 개수가 몇개인지 조회
}

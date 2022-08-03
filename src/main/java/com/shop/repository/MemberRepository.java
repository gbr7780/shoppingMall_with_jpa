package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    //회원 가입시 중복되지 않기 위해 이메일 검색하는 쿼리 메소드 추가
    Member findByEmail(String email);
}

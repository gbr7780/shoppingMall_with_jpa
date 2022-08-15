package com.shop.service;

import com.shop.dto.CartDetailDto;
import com.shop.dto.CartItemDto;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.repository.CartItemRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    // 장바구니에 상품을 담는 로직
    public Long addCart(CartItemDto cartItemDto, String email){

        // 장바구니에 담을 상품 엔티티 조회
        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        // 현재 로그인한 회원 조회
        Member member = memberRepository.findByEmail(email);
        // 현재 로그인한 회원의 장바구니 조회
        Cart cart = cartRepository.findByMemberId(member.getId());
        // cart가 null값이면 즉, 장바구니가 없을 경우 현재 로그인한 회원의 장바구니를 생성함
        if(cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }
        // 현재 장바구니 상품이 이미 들어가 있는지 조회함
        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(),item.getId());

        // 장바구니에 이미 있던 상품일 경우 기존 수량에 현재 장바구니에 담을 수량만큼 더한다
        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
          // 장바구니 엔티티, 상품 엔티티, 장바구니에 담을 수량을 이용하여 장바구니 상품 엔티티 생성
          CartItem cartItem = CartItem.createCartItem(cart,item, cartItemDto.getCount());
          // 장바구니에 담을 상품 저장
          cartItemRepository.save(cartItem);
          return cartItem.getId();
        }
    }

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email){

        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        Member member = memberRepository.findByEmail(email);
        // 현재 로그인한 회원의 아이디로 장바구니 조회
        Cart cart = cartRepository.findByMemberId(member.getId());
        // 장바구니가 null 즉, 상품을 한번도 안 담았을경우 장바구니 엔티티가 없으므로 빈 리스트 반환
        if(cart == null){
            return cartDetailDtoList;
        }

        // 장바구니에 담겨있는 상품 정보 조회
        cartDetailDtoList = cartItemRepository.findCartDetailList(cart.getId());

        return cartDetailDtoList;
    }


    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email){
        // 현재 로그인한 회원 조회
        Member curMember = memberRepository.findByEmail(email);
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        // 장바구니 상품을 저장할 회원 조회
        Member savedMember = cartItem.getCart().getMember();

        // 현재 로그인한 회원과 장바구니 상품을 저장한 회원이 다를 경우 false, 같을 경우 true
        if(!StringUtils.equals(curMember.getEmail(),savedMember.getEmail())){
            return false;
        }

        return true;
    }

    // 장바구니 상품의 수량을 업데이트 하는 메소드
    public void updateCartItemCount(Long cartItemId, int count){
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }

    // 장바구니 상품 삭제
    public void deleteCartItem(Long cartItemId){
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }
}
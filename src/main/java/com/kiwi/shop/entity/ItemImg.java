package com.kiwi.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item_img")
@Getter @Setter

public class ItemImg extends BaseEntity{

    @Id
    @Column(name = "item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 이미지 파일명
    private String imgName;

    // 원본 이미지 파일명
    private String oriImgName;

    // 이미지 조회 경로
    private String imgUrl;

    // 대표 이미지 여부
    private String repimgYn;

    @ManyToOne(fetch = FetchType.LAZY)      // 상품 엔티티와 다대일 단방향 매핑 // 지연로딩을 설정하여 매핑된 상품 엔티티 정보가 필요한 경우 데이터를 조회
    @JoinColumn(name = "item_id")
    private Item item;

    // 이미지 정보 업데이트
    public void updateItemImg(String oriImgName, String imgName, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}

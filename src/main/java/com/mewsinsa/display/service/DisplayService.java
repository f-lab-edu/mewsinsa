package com.mewsinsa.display.service;

import com.mewsinsa.display.controller.dto.DisplayProductResponseDto;
import com.mewsinsa.display.controller.dto.ProductDetailResponseDto;
import com.mewsinsa.display.domain.ProductDisplay;
import com.mewsinsa.display.repository.DisplayRepository;
import com.mewsinsa.member.domain.Tier;
import com.mewsinsa.product.domain.Product;
import com.mewsinsa.product.domain.ProductOption;
import com.mewsinsa.product.repository.ProductRepository;
import jakarta.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DisplayService {
  Logger log = LoggerFactory.getLogger(getClass());
  private final DisplayRepository displayRepository;
  private final ProductRepository productRepository;

  //==Constructor==//
  public DisplayService(DisplayRepository displayRepository, ProductRepository productRepository) {
    this.displayRepository = displayRepository;
    this.productRepository = productRepository;
  }

  /**
   * @param subcategory 상품 정보를 조회하려는 중분류 이름 (ex. 데님 팬츠, 코튼 팬츠, ..)
   * @param page 조회하려는 페이지
   * @param count 한 페이지 당 상품 개수
   * @return 해당 중분류의 상품 리스트
   */
  public List<DisplayProductResponseDto> productListBySubcategory(String subcategory, @Positive int page, @Positive int count) {
    List<DisplayProductResponseDto> productList = null;
    try {
      productList = displayRepository.displayProductListBySubcategory(subcategory, page, count);
    } catch (Exception e) {
      throw new IllegalArgumentException("상품 리스트 조회에 실패했습니다.", e);
    }

    return productList;
  }

  /**
   * @param productId 디테일 페이지를 조회하려는 상품 아이디
   * @return 해당 상품의 디테일 정보
   */
  public ProductDetailResponseDto productDetail(Long productId) {
    HashMap<String, Object> productHashMap;
    ProductDetailResponseDto productDetailResponseDto = null;
    HashMap<String, Long> tierPrice = new HashMap<>();

    try {
      // 해당 상품이 display db에 존재하는지 확인
      productHashMap = displayRepository.displayProductDetail(productId);
      if(productHashMap == null) { //
        productHashMap = productRepository.findProduct(productId);

      }

      // 옵션 리스트 가져오기
      List<ProductOption> productOptions = productRepository.findProductOptionsInfo(productId);


      Long originalPrice = (Long) productHashMap.get("originalPrice");

      // 티어별 가격 계산 -> 이거 스트림으로 고칠 수 있을 것 같다
      for (Tier tier : Tier.values()) { // 열거 순회
        tierPrice.put(tier.name(), (long)(((1 - (double)tier.getDiscountRate() / 10) * originalPrice)));
      }



      productDetailResponseDto = new ProductDetailResponseDto
          .Builder()
          .productId((Long) productHashMap.get("productId"))
          .productName((String) productHashMap.get("productName"))
          .brandId((Long) productHashMap.get("brandId"))
          .brandName((String) productHashMap.get("brandName"))
          .originalPrice((Long) productHashMap.get("originalPrice"))
          .countOfLike((Long) productHashMap.get("countOfLike"))
          .countOfClick((Long) productHashMap.get("countOfClick"))
          .category((String) productHashMap.get("category"))
          .subcategory((String) productHashMap.get("subcategory"))
          .promotionPrice((Long) productHashMap.get("promotionPrice"))
          .couponDiscount((Long) productHashMap.get("couponDiscount"))
          .productOptionList(productOptions)
          .tierPrice(tierPrice)
          .build();

    } catch(Exception e) {
      throw new IllegalArgumentException("상품 디테일 정보를 볼 수 없습니다.", e);
    }

    return productDetailResponseDto;


  }

}

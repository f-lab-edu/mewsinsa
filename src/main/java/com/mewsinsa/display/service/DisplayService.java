package com.mewsinsa.display.service;

import com.mewsinsa.display.controller.dto.ProductDetailResponseDto;
import com.mewsinsa.display.domain.ProductDisplay;
import com.mewsinsa.display.repository.DisplayRepository;
import com.mewsinsa.member.domain.Tier;
import com.mewsinsa.product.domain.Product;
import com.mewsinsa.product.domain.ProductOption;
import com.mewsinsa.product.repository.ProductRepository;
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

  public ProductDetailResponseDto productDetail(Long productId) {
    HashMap<String, Object> productHashMap = displayRepository.displayProductDetail(productId);
    ProductDetailResponseDto productDetailResponseDto = null;
    HashMap<String, Long> tierPrice = new HashMap<>();

    try {
      if(productHashMap == null) { //
        productHashMap = productRepository.findProduct(productId);

      }

      // 옵션 리스트 가져오기
      List<ProductOption> productOptions = productRepository.findProductOptionsInfo(productId);

      log.info("line 43: " + productOptions);
      if(productOptions != null) {
        log.info("ling45: " + productOptions.size());
      }


      Long originalPrice = (Long) productHashMap.get("originalPrice");

      // 티어별 가격 계산
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
      log.info(e.getMessage());
      throw new IllegalArgumentException("상품 디테일 정보를 볼 수 없습니다.", e);
    }

    return productDetailResponseDto;


  }

}

package com.mewsinsa.display.controller;

import com.mewsinsa.display.controller.dto.ProductDetailResponseDto;
import com.mewsinsa.display.domain.ProductDisplay;
import com.mewsinsa.display.service.DisplayService;
import com.mewsinsa.global.response.HttpStatusEnum;
import com.mewsinsa.global.response.SuccessResult;
import jakarta.validation.constraints.Positive;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/display")
@RestController
public class DisplayController {
  private final DisplayService displayService;

  public DisplayController(DisplayService displayService) {
    this.displayService = displayService;
  }

  /**
   * 상품 1개의 상세 정보를 조회합니다.
   * @return 상품의 상세 정보
   */
  @GetMapping("/products/{productId}")
  ResponseEntity<SuccessResult> productDetail(@Positive @PathVariable("productId") Long productId) {
    // 만약 displayTable에 있으면?
    ProductDetailResponseDto result = displayService.productDetail(productId);

    // 없으면
    SuccessResult successResult = new SuccessResult
        .Builder(HttpStatusEnum.OK)
        .data(result)
        .message("상품의 디테일 정보입니다.")
        .build();
    return new ResponseEntity<>(successResult, HttpStatus.OK);
  }

  @GetMapping("/products")
  ResponseEntity<SuccessResult> displayProducts(@Positive @PathVariable("product_id") Long productId) {
    // 만약 displayTable에 있으면?



    // 없으면

    return null;
  }
}

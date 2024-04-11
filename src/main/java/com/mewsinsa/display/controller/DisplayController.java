package com.mewsinsa.display.controller;

import com.mewsinsa.display.controller.dto.DisplayProductResponseDto;
import com.mewsinsa.display.controller.dto.ProductDetailResponseDto;
import com.mewsinsa.display.domain.ProductDisplay;
import com.mewsinsa.display.service.DisplayService;
import com.mewsinsa.global.response.DetailedStatus;
import com.mewsinsa.global.response.SuccessResult;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.apache.coyote.Response;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/display")
@RestController
public class DisplayController {
  Logger log = LoggerFactory.getLogger(getClass());

  private final DisplayService displayService;

  public DisplayController(DisplayService displayService) {
    this.displayService = displayService;
  }

  /**
   * 상품 1개의 상세 정보를 조회합니다.
   * @return 상품의 상세 정보
   */
  @GetMapping("/products/detail/{productId}")
  ResponseEntity<SuccessResult> productDetail(@Positive @PathVariable("productId") Long productId) {
    ProductDetailResponseDto result = displayService.productDetail(productId);

    SuccessResult successResult = new SuccessResult
        .Builder(DetailedStatus.OK)
        .data(result)
        .message("상품의 디테일 정보입니다.")
        .build();
    return new ResponseEntity<>(successResult, HttpStatus.OK);
  }

  @GetMapping("/products")
  ResponseEntity<SuccessResult> displayProducts(@RequestParam("subcategory") String subcategory,
      @RequestParam("page") @Positive int page,
      @RequestParam("count") @Positive int count) {
    List<DisplayProductResponseDto> list = displayService.productListBySubcategory(subcategory, page, count);


    SuccessResult successResult = new SuccessResult
        .Builder(DetailedStatus.OK)
        .data(list)
        .message("서브 카테고리: " + subcategory + ", page: " + page)
        .build();

    return new ResponseEntity<>(successResult, HttpStatus.OK);
  }
}

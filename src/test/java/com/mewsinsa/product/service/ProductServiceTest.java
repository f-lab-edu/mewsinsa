package com.mewsinsa.product.service;

import com.mewsinsa.product.controller.dto.AddProductOptionRequestDto;
import com.mewsinsa.product.controller.dto.AddProductRequestDto;
import com.mewsinsa.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {
  @Autowired
  private ProductService productService;

  @Autowired
  private ProductRepository productRepository;

  @BeforeEach
  void beforeEach() {
    // 먼저 DB의 값을 모두 삭제합니다.


  }

  //==상품 등록 테스트==//
  @Test
  void 상품_등록_테스트() {
    List<AddProductOptionRequestDto> productOptions = new ArrayList<>();
    productOptions.add(new AddProductOptionRequestDto("S", 100L));
    productOptions.add(new AddProductOptionRequestDto("M", 200L));
    productOptions.add(new AddProductOptionRequestDto("L", 100L));

    productService.addProduct(
        new AddProductRequestDto("상품 이름", 1L,
            "상의", "맨투맨", 10000L, productOptions));


  }
  @Test
  void 필드값은_비어있으면_안된다() {
    // final

  }

  @Test
  void 브랜드_아이디는_1이상이어야_한다() {

  }

  @Test
  void 가격은_0이상이어야_한다() {

  }


  //==상품 수정 테스트==//
  @Test
  void 상품_수정_후_값이_바뀌어야한다() {

  }

  //==상품 삭제 테스트==//
  @Test
  void 상품_삭제_후_그_상품과_옵션이_찾아지면_안된다() {

  }



}
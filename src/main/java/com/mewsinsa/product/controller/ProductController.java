package com.mewsinsa.product.controller;

import com.mewsinsa.global.response.HttpStatusEnum;
import com.mewsinsa.global.response.SuccessResponse;
import com.mewsinsa.product.controller.dto.AddProductRequestDto;
import com.mewsinsa.product.controller.dto.AddProductOptionRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductOptionRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductRequestDto;
import com.mewsinsa.product.repository.ProductRepository;
import com.mewsinsa.product.service.ProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/products")
@RestController

public class ProductController {
  private final ProductService productService;

  //==Constructor==//
  public ProductController(ProductService productService) {
    this.productService = productService;
  }
  //==Constructor 끝==//

  /**
   * 상품 등록
   * @param product 등록하려는 상품 정보
   * @return SuccessResponse
   */
  @PostMapping
  SuccessResponse addProduct(@Validated @RequestBody AddProductRequestDto product) {
    productService.addProduct(product);

    return new SuccessResponse
        .Builder(HttpStatusEnum.OK)
        .message("상품이 성공적으로 등록 되었습니다.")
        .data(product)
        .build();
  }

  /**
   * @param product 수정하려는 상품 정보
   * @return SuccessResponse
   */
  @PatchMapping("/{productId}")
  SuccessResponse updateProduct(@Validated @RequestBody UpdateProductRequestDto product) {
    productService.updateProduct(product);

    return new SuccessResponse
        .Builder(HttpStatusEnum.OK)
        .message("상품이 성공적으로 수정 되었습니다.")
        .data(product)
        .build();
  }


  @PatchMapping("/product-options/{productOptionId}")
  SuccessResponse updateProductOption(@Validated @RequestBody UpdateProductOptionRequestDto productOption) {
    productService.updateProductOption(productOption);

    return new SuccessResponse
        .Builder(HttpStatusEnum.OK)
        .message("상품 옵션이 성공적으로 수정 되었습니다.")
        .data(productOption)
        .build();
  }

  @DeleteMapping("/product-options/{productOptionId}")
  SuccessResponse deleteProductOption(@RequestBody Long productOptionId) {
    productService.deleteProductOption(productOptionId);

    return new SuccessResponse
        .Builder(HttpStatusEnum.OK)
        .message("상품 옵션이 성공적으로 삭제 되었습니다.")
        .build();
  }

  @DeleteMapping("/{productId}")
  SuccessResponse deleteProduct(@RequestBody Long productId) {
    productService.deleteProduct(productId);
    return new SuccessResponse
        .Builder(HttpStatusEnum.OK)
        .message("상품이 성공적으로 삭제 되었습니다.")
        .build();
  }



}

package com.mewsinsa.product.controller;

import com.mewsinsa.product.controller.dto.AddProductRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductRequestDto;
import com.mewsinsa.product.repository.ProductRepository;
import com.mewsinsa.product.service.ProductService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
  private final ProductService productService;
  private final ProductRepository productRepository;

  //==Constructor==//
  public ProductController(ProductService productService, ProductRepository productRepository) {
    this.productService = productService;
    this.productRepository = productRepository;
  }
  //==Constructor 끝==//

  /**
   * @param product 등록하려는 상품 정보
   * @return 등록에 성공하면 등록된 상품 정보를 보여줍니다.
   */
  @PostMapping("/products")
  AddProductRequestDto addProduct(@RequestBody AddProductRequestDto product) {
    productService.addProduct(product);

    return product;
  }

  @PatchMapping("/products/{productId}")
  UpdateProductRequestDto updateProduct(@RequestBody UpdateProductRequestDto product) {
    productService.updateProduct(product);
    return product;
  }



}

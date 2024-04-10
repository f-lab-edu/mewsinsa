package com.mewsinsa.product.service;

import com.mewsinsa.product.controller.dto.AddProductOptionDto;
import com.mewsinsa.product.controller.dto.AddProductRequestDto;
import com.mewsinsa.product.controller.dto.AddProductOptionRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductOptionRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductRequestDto;
import com.mewsinsa.product.domain.Product;
import com.mewsinsa.product.repository.ProductRepository;
import java.util.List;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private final ProductRepository productRepository;

  //==Constructor==//
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }
  //==Constructor 끝==//


  /**
   * @param product 등록하려는 상품 정보
   */
  public void addProduct(AddProductRequestDto productDto) {
    try {
      // 상품 정보를 등록
      Product product = new Product(
          productDto.getProductName(), productDto.getBrandId(),
          productDto.getCategory(), productDto.getSubcategory(),
          productDto.getOriginalPrice(), 0L, 0L
      );

      productRepository.addProduct(product);

      // 키값을 가져오기
      Long productId = product.getProductId();

      // 상품 옵션 정보를 등록
      List<AddProductOptionRequestDto> productOptions = productDto.getProductOptionList();

      for(AddProductOptionRequestDto productOption : productOptions) {
        AddProductOptionDto productOptionDto = new AddProductOptionDto(productOption.getProductOptionName(), productId, productOption.getStock());
        productRepository.addProductOption(productOptionDto);
      }

    } catch(Exception e) {
      throw new IllegalArgumentException("상품 등록에 실패하였습니다.", e);
    }
  }


  public void updateProduct(UpdateProductRequestDto product) {
    try {
      productRepository.updateProduct(product);
    } catch(Exception e) {
      throw new IllegalArgumentException("상품 수정에 실패하였습니다.", e);
    }
  }


  /**
   * @param productOption 수정하려는 옵션 정보
   */
  public void updateProductOption(UpdateProductOptionRequestDto productOption) {
    try {
      productRepository.updateProductOption(productOption);
    } catch(Exception e) {
      throw new IllegalArgumentException("상품 옵션 수정에 실패하였습니다.", e);
    }
  }

  public void deleteProductOption(Long productOptionId) {
    try {
      productRepository.deleteProductOption(productOptionId);
    } catch(Exception e) {
      throw new IllegalArgumentException("상품 옵션 삭제에 실패하였습니다.", e);
    }

  }

  public void deleteProduct(Long productId) {
    try {
      // ON DELETE CASCADE로 설정하여, 옵션도 함께 지워집니다.
      productRepository.deleteProduct(productId);
    } catch(Exception e) {
      throw new IllegalArgumentException("상품 삭제에 실패하였습니다.", e);
    }
  }

}

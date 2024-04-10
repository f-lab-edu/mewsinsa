package com.mewsinsa.product.service;

import com.mewsinsa.product.controller.dao.AddProductOptionDAO;
import com.mewsinsa.product.controller.dto.AddProductRequestDto;
import com.mewsinsa.product.controller.dto.AddProductOptionRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductOptionRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductRequestDto;
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
  public void addProduct(AddProductRequestDto product) {
    try {
      // 상품 정보를 등록
      productRepository.addProduct(product);

      // 키값을 가져오기
      Long productId = product.getProductId();

      // 상품 옵션 정보를 등록
      List<AddProductOptionRequestDto> productOptions = product.getProductOptionList();

      for(AddProductOptionRequestDto productOption : productOptions) {
        AddProductOptionDAO productOptionDao = new AddProductOptionDAO(productOption.getProductOptionName(), productId, productOption.getStock());
        productRepository.addProductOption(productOptionDao);
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

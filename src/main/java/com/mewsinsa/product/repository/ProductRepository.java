package com.mewsinsa.product.repository;

import com.mewsinsa.order.controller.dto.form.OrderedProductInfoDto;
import com.mewsinsa.order.domain.OrderedProduct;
import com.mewsinsa.product.controller.dto.AddProductOptionDto;
import com.mewsinsa.product.controller.dto.UpdateProductOptionRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductRequestDto;
import com.mewsinsa.product.domain.Product;
import com.mewsinsa.product.domain.ProductOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductRepository {

  void addProduct(Product productInfo);
  void addProductOption(AddProductOptionDto productOption);

  void updateProduct(UpdateProductRequestDto product);
  void updateProductOption(UpdateProductOptionRequestDto productOption);

  void deleteProductOption(Long productOptionId);

  void deleteProduct(Long productId);

  List<Long> findProductOptions(Long productId);

  HashMap<String, Object> findProduct(@Param("productId") Long productId);

  // 해당 상품의 옵션 정보 모두 가져오기
  List<ProductOption> findProductOptionsInfo(@Param("productId") Long productId);

  ProductOption findProductOptionByProductOptionId(@Param("productOptionId") Long productOptionId);


  // 재고 감소
  void reduceProductOptionStock(@Param("productOptionId") Long productOptionId);

  Product findOneProduct(@Param("productId") Long productId);

  OrderedProductInfoDto findOneOrderedProductInfo(@Param("productOptionId") Long productOptionId);

  // 주문 취소
  void updateIsCancelled(@Param("orderedProductId") Long orderedProductId, @Param("isCancelled") Boolean isCancelled);

//  OrderedProduct findOneOrderedProductByOrderedProductId(@Param("orderedProductId") Long orderedProductId);
}
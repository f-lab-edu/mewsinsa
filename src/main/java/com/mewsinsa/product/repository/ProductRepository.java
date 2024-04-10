package com.mewsinsa.product.repository;

import com.mewsinsa.product.controller.dto.AddProductOptionDto;
import com.mewsinsa.product.controller.dto.UpdateProductOptionRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductRequestDto;
import com.mewsinsa.product.domain.Product;
import com.mewsinsa.product.domain.ProductOption;
import java.util.HashMap;
import java.util.List;
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

  List<ProductOption> findProductOptionsInfo(@Param("productId") Long productId);


}

package com.mewsinsa.product.repository;

import com.mewsinsa.product.controller.dao.AddProductOptionDAO;
import com.mewsinsa.product.controller.dto.AddProductRequestDto;
import com.mewsinsa.product.controller.dto.AddProductOptionRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductOptionRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductRequestDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductRepository {

  void addProduct(AddProductRequestDto productInfo);
  void addProductOption(AddProductOptionDAO productOption);

  void updateProduct(UpdateProductRequestDto product);
  void updateProductOption(UpdateProductOptionRequestDto productOption);

  void deleteProductOption(Long productOptionId);

  void deleteProduct(Long productId);

  List<Long> findProductOptions(Long productId);


}

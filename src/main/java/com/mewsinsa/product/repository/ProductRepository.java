package com.mewsinsa.product.repository;

import com.mewsinsa.product.controller.dao.AddProductOptionDAO;
import com.mewsinsa.product.controller.dto.AddProductRequestDto;
import com.mewsinsa.product.controller.dto.UpdateProductOptionDto;
import com.mewsinsa.product.controller.dto.UpdateProductRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductRepository {

  void addProduct(AddProductRequestDto productInfo);
  void addProductOption(AddProductOptionDAO productOptionDAO);

  void updateProduct(UpdateProductRequestDto product);

  void updateProductOption(UpdateProductOptionDto productOption)
}

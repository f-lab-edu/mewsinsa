package com.mewsinsa.product.repository;

import com.mewsinsa.product.controller.dao.AddProductOptionDAO;
import com.mewsinsa.product.controller.dto.AddProductRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductRespository {

  void addProduct(AddProductRequestDto productInfo);
  void addProductOption(AddProductOptionDAO productOptionDAO);

}

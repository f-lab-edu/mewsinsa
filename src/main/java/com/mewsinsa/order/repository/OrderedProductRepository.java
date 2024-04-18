package com.mewsinsa.order.repository;

import com.mewsinsa.order.controller.dto.OrderedProductDto;
import com.mewsinsa.order.controller.dto.OrderedProductRequestDto;
import com.mewsinsa.order.domain.OrderedProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderedProductRepository {
  public void addOrderedProduct( @Param("orderId") Long orderId, @Param("orderedProduct") OrderedProductDto orderedProductDto);
  public void deleteOrderedProduct(@Param("orderedProductId") Long orderedProductId);

  public OrderedProduct findOrderedProductByOrderedProductId(@Param("orderedProductId") Long orderedProductId);
}

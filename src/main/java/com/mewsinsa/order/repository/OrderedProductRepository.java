package com.mewsinsa.order.repository;

import com.mewsinsa.order.domain.OrderedProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderedProductRepository {
  public void addOrderedProduct( @Param("orderId") Long orderId, @Param("orderedProduct") OrderedProduct orderedProduct);
  public void deleteOrderedProduct(@Param("orderedProductId") Long orderedProductId);

  public OrderedProduct findOneOrderedProductByOrderedProductId(@Param("orderedProductId") Long orderedProductId);
}

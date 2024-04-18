package com.mewsinsa.order.repository;

import com.mewsinsa.order.domain.History;
import java.time.LocalDateTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HistoryRepository {

  public void addHistory(@Param("orderedProductId") Long orderedProductId, @Param("orderId") Long orderId, @Param("orderedAt") LocalDateTime orderedAt, @Param("status") String status);
  public History findOneHistoryByOrderedProductId(@Param("orderedProductId") Long orderedProductId);
}

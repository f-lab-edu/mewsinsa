package com.mewsinsa.order.repository;

import com.mewsinsa.order.domain.History;
import com.mewsinsa.order.domain.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HistoryRepository {

  public void addHistory(@Param("orderedProductId") Long orderedProductId, @Param("orderId") Long orderId, @Param("orderedAt") LocalDateTime orderedAt, @Param("status") String status);
  public History findOneHistoryByOrderedProductId(@Param("orderedProductId") Long orderedProductId);
  void updateStatus(@Param("orderedProductId") Long orderedProductId, @Param("orderStatus") String orderStatus);
  public List<History> findHistoriesByOrderId(@Param("orderId") Long orderId);
}
